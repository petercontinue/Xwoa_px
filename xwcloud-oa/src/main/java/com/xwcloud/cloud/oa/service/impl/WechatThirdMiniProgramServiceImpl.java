package com.xwcloud.cloud.oa.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xwcloud.cloud.oa.controller.smallProgram.auth.ApiAuthorizerTokenInfo;
import com.xwcloud.cloud.oa.controller.smallProgram.common.*;
import com.xwcloud.cloud.oa.controller.smallProgram.common.ApiModifyDomainInfo;
import com.xwcloud.cloud.oa.controller.smallProgram.error.WechatError;
import com.xwcloud.cloud.oa.controller.smallProgram.error.WechatErrorException;
import com.xwcloud.cloud.oa.controller.smallProgram.xcx.*;
import com.xwcloud.cloud.oa.service.RedisService;
import com.xwcloud.cloud.oa.service.WechatThirdAuthService;
import com.xwcloud.cloud.oa.service.WechatThirdMiniProgramService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author
 * @date 2018/12/18
 */
@Service
public class WechatThirdMiniProgramServiceImpl implements WechatThirdMiniProgramService {
	protected final Logger log = LoggerFactory.getLogger(this.getClass());


	@Autowired
	private WechatThirdConfig wechatThirdConfig;
	@Autowired
	private WechatThirdAuthService wechatThirdAuthService;
	@Autowired
	private RedisService redisService;

	private int retrySleepMillis = 1000;
	private int maxRetryTimes = 5;




	public String getAccessToken(String authorizerAppId) throws WechatErrorException {
		Object authorizerAccessToken= redisService.get(WechatConstant.AUTHORIZER_ACCESS_TOKEN_KEY+"_"+authorizerAppId);
		if(authorizerAccessToken==null){
			Object authorizerRefreshToken= redisService.get(WechatConstant.AUTHORIZER_REFRESH_TOKEN_KEY+"_"+authorizerAppId);
			ApiAuthorizerTokenInfo apiAuthorizerTokenInfo=wechatThirdAuthService.apiAuthorizerToken(authorizerAppId,authorizerRefreshToken.toString());
			authorizerAccessToken=apiAuthorizerTokenInfo.getAuthorizerAccessToken();
		}
		return authorizerAccessToken.toString();
	}



	@Override
	public String httpRequestRefreshAccessToken(String requestUrl,String requestMethod, String param, String accessTokenKey,String authorizerAppId) throws WechatErrorException {
		int retryTimes = 0;
		String result=null;
		do {
			String uriWithAccessToken = requestUrl + (requestUrl.contains("?") ? "&" : "?") + accessTokenKey + "=" + getAccessToken(authorizerAppId);
			log.info("\n httpRequestRefreshComponentAccessToken ：requestUrl=[{}],requestMethod=[{}],param=[{}],accessTokenKey=[{}],authorizerAppId=[{}] ",uriWithAccessToken,requestMethod,param,accessTokenKey,authorizerAppId);
			if(requestMethod.equals("GET")){
				result = HttpClientUtil.get(uriWithAccessToken,param==null?null: FastJsonUtils.json2map(param));
			}else if(requestMethod.equals("POST")){
				result = HttpClientUtil.post(uriWithAccessToken, param);
			}
			log.info("\n httpRequestRefreshAccessToken ：result=[{}] ",result);
			WechatError error = WechatError.fromJson(result);
			//if (error.getErrCode()!=null) {
				if(error.getErrCode()==null || error.getErrCode().equals(0)){
					break;
				}else if(!WechatConstant.ACCESS_TOKEN_ERRCODE_LIST.contains(error.getErrCode())){
					log.error("\n【请求地址】: {}\n【请求参数】：{}\n【错误信息】：{}", uriWithAccessToken, param, error);
					throw new WechatErrorException(error);
				}
			//}
			// 强制设置access token过期了，这样在下一次请求里就会刷新access token
			redisService.del(WechatConstant.AUTHORIZER_ACCESS_TOKEN_KEY+"_"+authorizerAppId);
			if (retryTimes + 1 > this.maxRetryTimes) {
				log.error("httpRequestRefreshAccessToken accessToken is invalid 重试达到最大次数【{}】,错误【{}】", maxRetryTimes,error);
				//最后一次重试失败后，直接抛出异常，不再等待
				throw new RuntimeException("微信服务端异常，超出重试次数");
			}
			// -1 系统繁忙, 1000ms后重试
			int sleepMillis = this.retrySleepMillis * (1 << retryTimes);
			try {
				log.warn("微信系统繁忙，{} ms 后重试(第{}次)", sleepMillis, retryTimes + 1);
				Thread.sleep(sleepMillis);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

		} while (retryTimes++ < this.maxRetryTimes);


		return result;
	}

	/**
	 * 获取代码模版库中的所有小程序代码模版
	 * @return getTemplateList
	 * @throws Exception
	 */
	@Override
	public List<TemplateListResult> getTemplateList() throws WechatErrorException {
		String result = wechatThirdAuthService.httpRequestRefreshComponentAccessToken(WechatConstant.GET_TEMPLATE_LIST_URL,"GET",null,"access_token");
		WechatError error = WechatError.fromJson(result);
		if (!error.getErrCode().equals(0)) {
			log.error("\n【请求地址】: {}\n【请求参数】：{}\n【错误信息】：{}", WechatConstant.GET_TEMPLATE_LIST_URL, null, error);
			throw new WechatErrorException(error);
		}
		JSONObject jsonObject=FastJsonUtils.json2JsonObject(result);
		Object templateObject = jsonObject.get("template_list");
		if (templateObject!=null) {
			return FastJsonUtils.json2list(jsonObject.get("template_list").toString(),TemplateListResult.class);
		} else {
			return null;
		}
	}

	/**
	 * 设置小程序服务器域名
	 * @param action              add添加, delete删除, set覆盖, get获取。当参数是get时不需要填四个域名字段
	 * @param requestdomain     request合法域名，当action参数是get时不需要此字段
	 * @param wsrequestdomain  socket合法域名，当action参数是get时不需要此字段
	 * @param uploaddomain   uploadFile合法域名，当action参数是get时不需要此字段
	 * @param downloaddomain   downloadFile合法域名，当action参数是get时不需要此字段
	 * @return ApiModifyDomainInfo
	 * @throws Exception
	 */
	@Override
	public ApiModifyDomainInfo modifyDomain(String authorizerAppId, String action, List<String> requestdomain, List<String> wsrequestdomain, List<String> uploaddomain, List<String> downloaddomain) throws WechatErrorException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("action",action);
		if(!action.equals("get")){
			jsonObject.put("requestdomain",requestdomain);
			jsonObject.put("wsrequestdomain",wsrequestdomain);
			jsonObject.put("uploaddomain",uploaddomain);
			jsonObject.put("downloaddomain",downloaddomain);
		}
		String result=httpRequestRefreshAccessToken(WechatConstant.API_MODIFY_DOMAIN,"POST",jsonObject.toJSONString(),"access_token",authorizerAppId);
		ApiModifyDomainInfo apiModifyDomainInfo = (ApiModifyDomainInfo) FastJsonUtils.json2object(result,ApiModifyDomainInfo.class);
		return apiModifyDomainInfo;

	}

	/**
	 * 设置小程序的业务域名
	 * @param authorizerAppId   小程序appId
	 * @param action     add添加, delete删除, set覆盖, get获取。当参数是get时不需要填webviewdomain字段。如果没有action字段参数，则默认将开放平台第三方登记的小程序业务域名全部添加到授权的小程序中
	 * @param webviewdomain  小程序业务域名，当action参数是get时不需要此字段
	 * @return
	 */
	@Override
	public ApiSetWebviewDomainInfo setWebViewDomain(String authorizerAppId,String action, List<String> webviewdomain) throws WechatErrorException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("action",action);
		if(!action.equals("get")){
			jsonObject.put("webviewdomain",webviewdomain);
		}
		String result=httpRequestRefreshAccessToken(WechatConstant.API_SET_WEBVIEW_DOMAIN,"POST",jsonObject.toJSONString(),"access_token",authorizerAppId);
		ApiSetWebviewDomainInfo apiSetWebviewDomainInfo = (ApiSetWebviewDomainInfo) FastJsonUtils.json2object(result,ApiSetWebviewDomainInfo.class);
		return apiSetWebviewDomainInfo;
	}

	/**
	 * 获取帐号基本信息
	 * @param authorizerAppId   小程序appId
	 * @return
	 */
	@Override
	public ApiAccountBasicInfo getAccountBasicInfo(String authorizerAppId) throws WechatErrorException {
		String result=httpRequestRefreshAccessToken(WechatConstant.API_GET_ACCOUNT_BASICINFO,"GET",null,"access_token",authorizerAppId);
		ApiAccountBasicInfo apiAccountBasicInfo = (ApiAccountBasicInfo) FastJsonUtils.json2object(result,ApiAccountBasicInfo.class);
		return apiAccountBasicInfo;

	}


	/**
	 * 绑定微信用户为小程序体验者
	 *
	 * @param wechatid 体验者微信号（不是openid）
	 * @param authorizerAppId   小程序appId
	 * @return
	 */
	@Override
	public ApiBindTesterInfo bindTester(String authorizerAppId, String wechatid) throws WechatErrorException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("wechatid",wechatid);
		String result=httpRequestRefreshAccessToken(WechatConstant.API_BIND_TESTER,"POST",jsonObject.toString(),"access_token",authorizerAppId);
		ApiBindTesterInfo apiBindTesterInfo = (ApiBindTesterInfo) FastJsonUtils.json2object(result,ApiBindTesterInfo.class);
		return apiBindTesterInfo;
	}


	/**
	 * 解除绑定小程序的体验者
	 *
	 * @param wechatid 体验者微信号（不是openid）
	 * @param authorizerAppId   小程序appId
	 * @return
	 * @throws Exception
	 */
	@Override
	public ApiBindTesterInfo unbindTester(String authorizerAppId, String wechatid) throws WechatErrorException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("wechatid",wechatid);
		String result=httpRequestRefreshAccessToken(WechatConstant.API_UNBIND_TESTER,"POST",jsonObject.toString(),"access_token",authorizerAppId);
		ApiBindTesterInfo apiBindTesterInfo = (ApiBindTesterInfo) FastJsonUtils.json2object(result.toString(),ApiBindTesterInfo.class);
		return apiBindTesterInfo;
	}


	/**
	 * 获取体验者列表
	 *
	 * @param action 体验者微信号（不是openid）
	 * @param authorizerAppId   小程序appId
	 * @return
	 * @throws Exception
	 */
	@Override
	public MemberAuthListResult getTesterList(String authorizerAppId, String action) throws WechatErrorException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("action",action);
		String result=httpRequestRefreshAccessToken(WechatConstant.API_GET_TESTERLIST,"POST",jsonObject.toString(),"access_token",authorizerAppId);
		MemberAuthListResult memberAuthInfo = (MemberAuthListResult) FastJsonUtils.json2object(result,MemberAuthListResult.class);
		return memberAuthInfo;
	}


	/**
	 * 1、为授权的小程序帐号上传小程序代码
	 * @param authorizerAppId   小程序appId
	 * @param templateId  代码模板ID
	 * @param userVersion 用户定义版本
	 * @param userDesc    用户定义版本描述
	 * @param apiCodeCommitExtParameter     第三方自定义的配置
	 * @return
	 * @throws Exception
	 */
	@Override
	public ApiSetWebviewDomainInfo codeCommit(String authorizerAppId, Long templateId, String userVersion, String userDesc, ApiCodeCommitExtParameter apiCodeCommitExtParameter) throws WechatErrorException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("template_id",templateId);
		jsonObject.put("ext_json","\""+FastJsonUtils.object2json(apiCodeCommitExtParameter).toString().replaceAll("\"","\\\"")+"\"");
		jsonObject.put("user_version",userVersion);;
		jsonObject.put("user_desc",userDesc);
		String result=httpRequestRefreshAccessToken(WechatConstant.API_CODE_COMMIT,"POST",jsonObject.toString(),"access_token",authorizerAppId);
		ApiSetWebviewDomainInfo apiSetWebviewDomainInfo = (ApiSetWebviewDomainInfo) FastJsonUtils.json2object(result,ApiSetWebviewDomainInfo.class);
		return apiSetWebviewDomainInfo;
	}


	/**
	 * 获取体验小程序的体验二维码
	 *
	 * @param authorizerAppId   小程序appId
	 * @param path   指定体验版二维码跳转到某个具体页面（如果不需要的话，则不需要填path参数，可在路径后以“?参数”方式传入参数）
	 * 具体的路径加参数需要urlencode，比如page/index?action=1编码后得到page%2Findex%3Faction%3D1
	 * @return
	 */
	@Override
	public String getTestQrcode(String authorizerAppId,String path) throws WechatErrorException, UnsupportedEncodingException {
		String requestUrl = WechatConstant.API_TEST_QRCODE.replace("ACCESS_TOKEN", getAccessToken(authorizerAppId));
		final StringBuilder url = new StringBuilder(requestUrl);
		if (StringUtils.isNotBlank(path)) {
			url.append("&path=").append(URLEncoder.encode(path, StandardCharsets.UTF_8.name()));
		}
		byte[] response = HttpClientUtil.getByte(url.toString(),null);
		String content = new String(response);
		if (content.contains("errcode")) {
			WechatError error = WechatError.fromJson(content);
			log.error("\n【请求地址】: {}\n【请求参数】：{}\n【错误信息】：{}", url, null, error);
			throw new WechatErrorException(error);
		}
		return response==null?null: Base64.encodeBase64String(response);
	}


	/**
	 * 获取授权小程序帐号的可选类目
	 * <p>
	 *    @param authorizerAppId   小程序appId
	 * 注意：该接口可获取已设置的二级类目及用于代码审核的可选三级类目。
	 * </p>
	 *
	 * @return CategoryListResult
	 * @throws Exception
	 */
	@Override
	public CategoryListResult getCategoryList(String authorizerAppId) throws WechatErrorException {
		String result=httpRequestRefreshAccessToken(WechatConstant.API_GET_CATEGORY,"GET",null,"access_token",authorizerAppId);
		CategoryListResult categoryListInfo = (CategoryListResult) FastJsonUtils.json2object(result,CategoryListResult.class);
		return categoryListInfo;

	}

	/**
	 * 获取小程序的第三方提交代码的页面配置（仅供第三方开发者代小程序调用）
	 *    @param authorizerAppId   小程序appId
	 * @return PageListInfo
	 * @throws Exception
	 */
	@Override
	public PageListResult getPageList(String authorizerAppId) throws WechatErrorException {
		String result=httpRequestRefreshAccessToken(WechatConstant.API_GET_PAGE,"GET",null,"access_token",authorizerAppId);
		PageListResult pageListInfo = (PageListResult) FastJsonUtils.json2object(result,PageListResult.class);
		return pageListInfo;
	}


	/**
	 * 将第三方提交的代码包提交审核（仅供第三方开发者代小程序调用）
	 *    @param authorizerAppId   小程序appId
	 * @param submitAuditInfoList
	 * @return
	 * @throws Exception
	 */
	@Override
	public SubmitAuditResult submitAudit(String authorizerAppId, SubmitAuditParameter submitAuditInfoList) throws WechatErrorException {
		String result=httpRequestRefreshAccessToken(WechatConstant.API_SUBMIT_AUDIT,"POST",FastJsonUtils.object2json(submitAuditInfoList),"access_token",authorizerAppId);
		/**
		 * 85009  已经有正在审核的版本
		 * 86002  请完善当前小程序
		 */
		SubmitAuditResult submitAuditResult = (SubmitAuditResult) FastJsonUtils.json2object(result,SubmitAuditResult.class);
		return submitAuditResult;
	}

	/**
	 * 7. 查询某个指定版本的审核状态（仅供第三方代小程序调用）
	 *    @param authorizerAppId   小程序appId
	 * @param auditid
	 * @return
	 * @throws Exception
	 */
	@Override
	public AuditStatusResult getAuditStatus(String authorizerAppId, Long auditid) throws WechatErrorException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("auditid",auditid);
		String result=httpRequestRefreshAccessToken(WechatConstant.API_GET_AUDIT_STATUS,"POST",jsonObject.toString(),"access_token",authorizerAppId);
		AuditStatusResult auditStatusResult = (AuditStatusResult) FastJsonUtils.json2object(result,AuditStatusResult.class);
		return auditStatusResult;
	}


	/**
	 * 8. 查询最新一次提交的审核状态（仅供第三方代小程序调用）
	 *    @param authorizerAppId   小程序appId
	 * @return
	 * @throws Exception
	 */
	@Override
	public AuditStatusResult getLatestAuditStatus(String authorizerAppId) throws WechatErrorException {
		String result=httpRequestRefreshAccessToken(WechatConstant.API_GET_LATEST_AUDIT_STATUS,"GET",null,"access_token",authorizerAppId);
		AuditStatusResult auditStatusResult = (AuditStatusResult) FastJsonUtils.json2object(result,AuditStatusResult.class);
		return auditStatusResult;
	}


	/**
	 * 9. 发布已通过审核的小程序（仅供第三方代小程序调用）
	 *    @param authorizerAppId   小程序appId
	 * 请填写空的数据包，POST的json数据包为空即可。
	 * </p>
	 *
	 * @return
	 * @throws WechatErrorException
	 */
	@Override
	public ApiSetWebviewDomainInfo releaesAudited(String authorizerAppId) throws WechatErrorException {
		String result=httpRequestRefreshAccessToken(WechatConstant.API_RELEASE,"POST",null,"access_token",authorizerAppId);
		ApiSetWebviewDomainInfo apiSetWebviewDomainInfo = (ApiSetWebviewDomainInfo) FastJsonUtils.json2object(result,ApiSetWebviewDomainInfo.class);
		return apiSetWebviewDomainInfo;
	}

	/**
	 * 11. 小程序版本回退（仅供第三方代小程序调用）
	 *    @param authorizerAppId   小程序appId
	 * @return
	 * @throws Exception
	 */
	@Override
	public ApiSetWebviewDomainInfo revertCodeRelease(String authorizerAppId) throws WechatErrorException {
		String result=httpRequestRefreshAccessToken(WechatConstant.API_REVERT_CODE_RELEASE,"POST",null,"access_token",authorizerAppId);
		ApiSetWebviewDomainInfo apiSetWebviewDomainInfo = (ApiSetWebviewDomainInfo) FastJsonUtils.json2object(result.toString(),ApiSetWebviewDomainInfo.class);
		return apiSetWebviewDomainInfo;
	}

	/**
	 * 15. 小程序审核撤回
	 * <p>
	 * 单个帐号每天审核撤回次数最多不超过1次，一个月不超过10次。
	 * </p>
	 *
	 * @return
	 * @throws Exception
	 */
	@Override
	public ApiSetWebviewDomainInfo undoCodeAudit(String authorizerAppId) throws WechatErrorException {
		String result=httpRequestRefreshAccessToken(WechatConstant.API_UNDO_CODE_AUDIT,"GET",null,"access_token",authorizerAppId);

		/**
		 * 87013    撤回次数达到上限（每天一次，每个月10次）
		 */
		ApiSetWebviewDomainInfo apiSetWebviewDomainInfo = (ApiSetWebviewDomainInfo) FastJsonUtils.json2object(result,ApiSetWebviewDomainInfo.class);
		return apiSetWebviewDomainInfo;
	}




	/**
	 * 微信登录
	 * @param appId  	小程序的AppID
	 * @param jsCode   	登录时获取的 code
	 * @return
	 */
	@Override
	public Jscode2SessionResult miniProgramJscode2Session(String appId, String jsCode) throws WechatErrorException {
		String url = String.format(WechatConstant.MINIAPP_JSCODE_2_SESSION, appId, jsCode, wechatThirdConfig.getComponentAppId());
		String result = wechatThirdAuthService.httpRequestRefreshComponentAccessToken(url, "GET", null, "component_access_token");
		WechatError error = WechatError.fromJson(result);
		if (!error.getErrCode().equals(0)) {
			log.error("\n【请求地址】: {}\n【请求参数】：{}\n【错误信息】：{}", url, null, error);
			throw new WechatErrorException(error);
		}
		Jscode2SessionResult jscode2SessionResult = (Jscode2SessionResult) FastJsonUtils.json2object(result.toString(), Jscode2SessionResult.class);
		return jscode2SessionResult;
	}

}
