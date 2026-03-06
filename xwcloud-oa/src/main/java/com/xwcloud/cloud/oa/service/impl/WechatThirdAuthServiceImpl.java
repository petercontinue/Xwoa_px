package com.xwcloud.cloud.oa.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xwcloud.cloud.oa.controller.smallProgram.auth.*;
import com.xwcloud.cloud.oa.controller.smallProgram.common.FastJsonUtils;
import com.xwcloud.cloud.oa.controller.smallProgram.common.HttpClientUtil;
import com.xwcloud.cloud.oa.controller.smallProgram.common.WechatConstant;
import com.xwcloud.cloud.oa.controller.smallProgram.common.WechatThirdConfig;
import com.xwcloud.cloud.oa.controller.smallProgram.crypto.URIUtil;
import com.xwcloud.cloud.oa.controller.smallProgram.error.WechatError;
import com.xwcloud.cloud.oa.controller.smallProgram.error.WechatErrorException;
import com.xwcloud.cloud.oa.controller.smallProgram.error.WechatType;
import com.xwcloud.cloud.oa.service.RedisService;
import com.xwcloud.cloud.oa.service.WechatThirdAuthService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @date 2018/12/7
 * https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1453779503&token=&lang=       （微信API文档）
 */
@Service
public class WechatThirdAuthServiceImpl implements WechatThirdAuthService {

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RedisService redisService;
	@Autowired
	private WechatThirdConfig wechatThirdConfig;

	private int retrySleepMillis = 1000;
	private int maxRetryTimes = 5;

	@Override
	public String httpRequestRefreshComponentAccessToken(String requestUrl, String requestMethod, String param, String accessTokenKey) throws WechatErrorException {
		int retryTimes = 0;
		String result = null;
		do {
			String uriWithComponentAccessToken = requestUrl + (requestUrl.contains("?") ? "&" : "?") + accessTokenKey + "=" + getComponentAccessToken();
			log.info("\n httpRequestRefreshComponentAccessToken ：requestUrl=[{}],requestMethod=[{}],param=[{}],accessTokenKey=[{}] ", uriWithComponentAccessToken, requestMethod, param, accessTokenKey);
			if (requestMethod.equals("GET")) {
				result = HttpClientUtil.get(uriWithComponentAccessToken, param == null ? null : FastJsonUtils.json2map(param));
			} else if (requestMethod.equals("POST")) {
				result = HttpClientUtil.post(uriWithComponentAccessToken, param);
			}
			log.info("\n httpRequestRefreshComponentAccessToken ：result=[{}] ", result);
			WechatError error = WechatError.fromJson(result);
			if (error.getErrCode() == null || error.getErrCode().equals(0)) {
				break;
			} else if (!WechatConstant.ACCESS_TOKEN_ERRCODE_LIST.contains(error.getErrCode())) {
				log.error("\n【请求地址】: {}\n【请求参数】：{}\n【错误信息】：{}", uriWithComponentAccessToken, param, error);
				throw new WechatErrorException(error);
			}
			// 强制设置access token过期了，这样在下一次请求里就会刷新access token
			redisService.del(WechatConstant.COMPONENT_ACCESS_TOKEN_KEY);
			if (retryTimes + 1 > this.maxRetryTimes) {
				log.error("httpRequestRefreshAccessToken accessToken is invalid 重试达到最大次数【{}】,错误【{}】", maxRetryTimes, error);
				//最后一次重试失败后，直接抛出异常，不再等待
				throw new RuntimeException("微信服务端异常，超出重试次数");
			}
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
	 * 获取第三方平台access_token  默认有效期是7200秒 120分
	 * @return
	 * @throws WechatErrorException
	 */
	@Override
	public String getComponentAccessToken() throws WechatErrorException {
		Object componentAccessToken=redisService.get(WechatConstant.COMPONENT_ACCESS_TOKEN_KEY);
		if(componentAccessToken!=null){
			return componentAccessToken.toString();
		}
		int retryTimes = 0;
		Object componentVerifyTicket= null;
		do {
			componentVerifyTicket = redisService.get(WechatConstant.COMPONENT_VERIFY_TICKET_KEY);
			if(componentVerifyTicket!=null){
				break;
			}
			if (retryTimes + 1 > this.maxRetryTimes) {
				log.error("getComponentAccessToken componentVerifyTicket is null 重试达到最大次数【{}】", maxRetryTimes);
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
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("component_appid", wechatThirdConfig.getComponentAppId());
		jsonObject.put("component_appsecret", wechatThirdConfig.getComponentSecret());
		jsonObject.put("component_verify_ticket", componentVerifyTicket.toString());
		String result = HttpClientUtil.post(WechatConstant.API_COMPONENT_TOKEN_URL, jsonObject.toString());
		WechatError error = WechatError.fromJson(result, WechatType.Open);
		if (error.getErrCode()!=null) {
			log.error("\n【请求地址】: {}\n【请求参数】：{}\n【错误信息】：{}", WechatConstant.API_COMPONENT_TOKEN_URL, jsonObject.toString(), error);
			throw new WechatErrorException(error);
		}
		ComponentAccessTokenResult componentAccessTokenResult= (ComponentAccessTokenResult) FastJsonUtils.json2object(result,ComponentAccessTokenResult.class);
		redisService.set(WechatConstant.COMPONENT_ACCESS_TOKEN_KEY,componentAccessTokenResult.getComponentAccessToken(),componentAccessTokenResult.getExpiresIn());
		return componentAccessTokenResult.getComponentAccessToken();
	}


	/**
	 * 获取预授权码  默认有效期是600秒 10分
	 * @return
	 * @throws WechatErrorException
	 */
	@Override
	public String getPreAuthCode() throws WechatErrorException {
		Object preAuthCode=redisService.get(WechatConstant.PRE_AUTH_CODE_KEY);
		if(preAuthCode!=null){
			return preAuthCode.toString();
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("component_appid", wechatThirdConfig.getComponentAppId());
		String result = httpRequestRefreshComponentAccessToken(WechatConstant.API_CREATE_PREAUTHCODE_URL,"POST",jsonObject.toJSONString(),"component_access_token");
		PreAuthCodeResult preAuthCodeResult= (PreAuthCodeResult) FastJsonUtils.json2object(result,PreAuthCodeResult.class);
		redisService.set(WechatConstant.PRE_AUTH_CODE_KEY,preAuthCodeResult.getPreAuthCode(),preAuthCodeResult.getExpiresIn());
		return preAuthCodeResult.getPreAuthCode();
	}


	/**
	 * 进入授权页面
	 * @param redirectURI  回调URI
	 * @param authType   要授权的帐号类型， 1则商户扫码后，手机端仅展示公众号、2表示仅展示小程序，3表示公众号和小程序都展示。如果为未制定，则默认小程序和公众号都展示。第三方平台开发者可以使用本字段来控制授权的帐号类型。
	 * @param bizAppid   指定授权唯一的小程序或公众号
	 * @return
	 * @throws Exception
	 */
	@Override
	public String getPreAuthUrl(String redirectURI, String authType, String bizAppid) throws WechatErrorException {
		StringBuilder preAuthUrl = new StringBuilder(String.format(WechatConstant.COMPONENT_LOGIN_PAGE_URL,
				wechatThirdConfig.getComponentAppId(),
				this.getPreAuthCode(),
				URIUtil.encodeURIComponent(redirectURI)));
		if (StringUtils.isNotEmpty(authType)) {
			preAuthUrl.append("&auth_type=").append(authType);
		}
		if (StringUtils.isNotEmpty(bizAppid)) {
			preAuthUrl.append("&biz_appid=").append(bizAppid);
		}

		return preAuthUrl.toString();
	}


	/**
	 * 4、使用授权码换取公众号或小程序的接口调用凭据和授权信息
	 * @param authorizationCode  授权code,会在授权成功时返回给第三方平台，详见第三方平台授权流程说明
	 */
	@Override
	public ApiQueryAuthInfo getApiQueryAuthInfo(String authorizationCode) throws WechatErrorException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("component_appid", wechatThirdConfig.getComponentAppId());
		jsonObject.put("authorization_code", authorizationCode);
		String result = httpRequestRefreshComponentAccessToken(WechatConstant.API_QUERY_AUTH_URL,"POST",jsonObject.toJSONString(),"component_access_token");
		ApiQueryAuthInfo apiQueryAuthInfo= (ApiQueryAuthInfo) FastJsonUtils.json2object(result,ApiQueryAuthInfo.class);
		redisService.set(WechatConstant.AUTHORIZER_ACCESS_TOKEN_KEY+"_"+apiQueryAuthInfo.getAuthorizationInfo().getAuthorizerAppid(),apiQueryAuthInfo.getAuthorizationInfo().getAuthorizerAccessToken(),apiQueryAuthInfo.getAuthorizationInfo().getExpiresIn());
		redisService.set(WechatConstant.AUTHORIZER_REFRESH_TOKEN_KEY+"_"+apiQueryAuthInfo.getAuthorizationInfo().getAuthorizerAppid(),apiQueryAuthInfo.getAuthorizationInfo().getAuthorizerRefreshToken(),-1);
		return apiQueryAuthInfo;
	}

	/**
	 * 5、获取（刷新）授权公众号的令牌
	 * @param authorizerAppid  授权方appid
	 * @param authorizerRefreshToken  授权方的刷新令牌，刷新令牌主要用于第三方平台获取和刷新已授权用户的access_token，只会在授权时刻提供，请妥善保存。一旦丢失，只能让用户重新授权，才能再次拿到新的刷新令牌
	 */
	@Override
	public ApiAuthorizerTokenInfo apiAuthorizerToken(String authorizerAppid, String authorizerRefreshToken) throws WechatErrorException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("component_appid", wechatThirdConfig.getComponentAppId());
		jsonObject.put("authorizer_appid", authorizerAppid);
		jsonObject.put("authorizer_refresh_token", authorizerRefreshToken);
		String result =httpRequestRefreshComponentAccessToken(WechatConstant.API_AUTHORIZER_TOKEN_URL,"POST",jsonObject.toString(),"component_access_token");
		/*WechatError error = WechatError.fromJson(result);
		if (error.getErrCode()!=null && error.getErrCode().equals(61023)) {
			redisService.del(WechatConstant.AUTHORIZER_REFRESH_TOKEN_KEY+"_"+authorizerAppid);
			ApiGetAuthorizerInfo authorizerInfo=apiGetAuthorizerInfo(authorizerAppid);
			return apiAuthorizerToken(authorizerAppid,authorizerInfo.getAuthorizationInfo().getAuthorizer_refresh_token());
		}*/
		ApiAuthorizerTokenInfo apiAuthorizerTokenInfo = (ApiAuthorizerTokenInfo) FastJsonUtils.json2object(result,ApiAuthorizerTokenInfo.class);
		redisService.set(WechatConstant.AUTHORIZER_ACCESS_TOKEN_KEY+"_"+authorizerAppid,apiAuthorizerTokenInfo.getAuthorizerAccessToken(),apiAuthorizerTokenInfo.getExpiresIn());
		redisService.set(WechatConstant.AUTHORIZER_REFRESH_TOKEN_KEY+"_"+authorizerAppid,apiAuthorizerTokenInfo.getAuthorizerRefreshToken(),-1);
		return apiAuthorizerTokenInfo;
	}

	/**
	 * 6、获取授权方的账户信息
	 * @param authorizerAppid  授权方appid
	 */
	@Override
	public ApiGetAuthorizerInfo apiGetAuthorizerInfo(String authorizerAppid) throws WechatErrorException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("component_appid", wechatThirdConfig.getComponentAppId());
		jsonObject.put("authorizer_appid", authorizerAppid);
		String result =httpRequestRefreshComponentAccessToken(WechatConstant.API_GET_AUTHORIZER_INFO_URL,"POST",jsonObject.toString(),"component_access_token");
		ApiGetAuthorizerInfo apiGetAuthorizerInfo = (ApiGetAuthorizerInfo) FastJsonUtils.json2object(result,ApiGetAuthorizerInfo.class);
		return apiGetAuthorizerInfo;
	}


	/**
	 * 7、获取授权方的选项设置信息
	 * @param authorizerAppid  授权方appid
	 * @param optionName  	选项名称
	 */
	@Override
	public ApiGetAuthorizerOption apiGetAuthorizerOption(String authorizerAppid, String optionName) throws WechatErrorException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("component_appid", wechatThirdConfig.getComponentAppId());
		jsonObject.put("authorizer_appid", authorizerAppid);
		jsonObject.put("option_name", optionName);
		String result = httpRequestRefreshComponentAccessToken(WechatConstant.API_GET_AUTHORIZER_OPTION_URL,"POST", jsonObject.toString(),"component_access_token");
		ApiGetAuthorizerOption apiGetAuthorizerOption = (ApiGetAuthorizerOption) FastJsonUtils.json2object(result,ApiGetAuthorizerOption.class);
		return apiGetAuthorizerOption;
	}


	/**
	 * 8、设置授权方的选项信息
	 * @param authorizerAppid  授权方appid
	 * @param optionName  	选项名称
	 * @param optionValue 设置的选项值
	 */
	@Override
	public ApiSetAuthorizerOption apiSetAuthorizerOption(String authorizerAppid, String optionName, String optionValue) throws WechatErrorException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("component_appid", wechatThirdConfig.getComponentAppId());
		jsonObject.put("authorizer_appid", authorizerAppid);
		jsonObject.put("option_name", optionName);
		jsonObject.put("option_value", optionValue);
		String result =httpRequestRefreshComponentAccessToken(WechatConstant.API_SET_AUTHORIZER_OPTION_URL,"POST",jsonObject.toString(),"component_access_token");
		ApiSetAuthorizerOption authorizerOption = (ApiSetAuthorizerOption) FastJsonUtils.json2object(result.toString(),ApiSetAuthorizerOption.class);
		return authorizerOption;
	}


	/**
	 * 发送客服消息
	 * @param json
	 * @param accessToken
	 * https://developers.weixin.qq.com/miniprogram/dev/api/sendCustomerMessage.html  （微信API文档）
	 * @return
	 */
	@Override
	public String sendMessage(String json,String accessToken) throws WechatErrorException {
		String url = WechatConstant.SEND_MESSAGE_URL.replace("ACCESS_TOKEN",accessToken);
		String result = HttpClientUtil.post(url, json);
		WechatError error = WechatError.fromJson(result);
		if (!error.getErrCode().equals(0)) {
			log.error("\n【请求地址】: {}\n【请求参数】：{}\n【错误信息】：{}", url, json, error);
			throw new WechatErrorException(error);
		}
		return result;
	}

}
