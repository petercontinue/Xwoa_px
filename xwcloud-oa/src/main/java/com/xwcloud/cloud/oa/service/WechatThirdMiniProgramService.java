package com.xwcloud.cloud.oa.service;



import com.xwcloud.cloud.oa.controller.smallProgram.common.ApiModifyDomainInfo;
import com.xwcloud.cloud.oa.controller.smallProgram.error.WechatErrorException;
import com.xwcloud.cloud.oa.controller.smallProgram.xcx.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author
 * @date 2018/12/18
 */
public interface WechatThirdMiniProgramService {


	String httpRequestRefreshAccessToken(String requestUrl,String requestMethod, String param, String accessTokenKey,String authorizerAppId) throws WechatErrorException;

	/**
	 * 获取代码模版库中的所有小程序代码模版
	 * @return getTemplateList
	 */
	List<TemplateListResult> getTemplateList() throws WechatErrorException;
	/**
	 * 设置小程序服务器域名
	 * @param authorizerAppId       小程序appId
	 * @param action              add添加, delete删除, set覆盖, get获取。当参数是get时不需要填四个域名字段
	 * @param requestdomain     request合法域名，当action参数是get时不需要此字段
	 * @param wsrequestdomain  socket合法域名，当action参数是get时不需要此字段
	 * @param uploaddomain   uploadFile合法域名，当action参数是get时不需要此字段
	 * @param downloaddomain   downloadFile合法域名，当action参数是get时不需要此字段
	 * @return ApiModifyDomainInfo
	 * @throws Exception
	 */
	 ApiModifyDomainInfo modifyDomain(String authorizerAppId, String action, List<String> requestdomain, List<String> wsrequestdomain, List<String> uploaddomain, List<String> downloaddomain) throws WechatErrorException;


	/**
	 * 设置小程序的业务域名
	 * @param authorizerAppId   小程序appId
	 * @param action     add添加, delete删除, set覆盖, get获取。当参数是get时不需要填webviewdomain字段。如果没有action字段参数，则默认将开放平台第三方登记的小程序业务域名全部添加到授权的小程序中
	 * @param webviewdomain  小程序业务域名，当action参数是get时不需要此字段
	 * @return
	 */
	 ApiSetWebviewDomainInfo setWebViewDomain(String authorizerAppId, String action, List<String> webviewdomain) throws WechatErrorException;

	/**
	 * 获取帐号基本信息
	 * @param authorizerAppId   小程序appId
	 * @return
	 */
	ApiAccountBasicInfo getAccountBasicInfo(String authorizerAppId) throws WechatErrorException;


	/**
	 * 绑定微信用户为小程序体验者
	 *
	 * @param wechatid 体验者微信号（不是openid）
	 * @param authorizerAppId   小程序appId
	 * @return
	 */
	ApiBindTesterInfo bindTester(String authorizerAppId, String wechatid) throws WechatErrorException;


	/**
	 * 解除绑定小程序的体验者
	 *
	 * @param wechatid 体验者微信号（不是openid）
	 * @param authorizerAppId   小程序appId
	 * @return
	 * @throws Exception
	 */
	ApiBindTesterInfo unbindTester(String authorizerAppId, String wechatid) throws WechatErrorException;


	/**
	 * 获取体验者列表
	 *
	 * @param action 体验者微信号（不是openid）
	 * @param authorizerAppId   小程序appId
	 * @return
	 */
	MemberAuthListResult getTesterList(String authorizerAppId, String action) throws WechatErrorException;


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
	ApiSetWebviewDomainInfo codeCommit(String authorizerAppId, Long templateId, String userVersion, String userDesc, ApiCodeCommitExtParameter apiCodeCommitExtParameter) throws WechatErrorException;

	/**
	 * 获取体验小程序的体验二维码
	 *
	 * @param authorizerAppId   小程序appId
	 * @param path   指定体验版二维码跳转到某个具体页面（如果不需要的话，则不需要填path参数，可在路径后以“?参数”方式传入参数）
	 * 具体的路径加参数需要urlencode，比如page/index?action=1编码后得到page%2Findex%3Faction%3D1
	 * @return
	 */
	String getTestQrcode(String authorizerAppId,String path) throws WechatErrorException, UnsupportedEncodingException;

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
	CategoryListResult getCategoryList(String authorizerAppId) throws WechatErrorException;


	/**
	 * 获取小程序的第三方提交代码的页面配置（仅供第三方开发者代小程序调用）
	 *    @param authorizerAppId   小程序appId
	 * @return PageListInfo
	 * @throws Exception
	 */
	PageListResult getPageList(String authorizerAppId) throws WechatErrorException;


	/**
	 * 将第三方提交的代码包提交审核（仅供第三方开发者代小程序调用）
	 *    @param authorizerAppId   小程序appId
	 * @param submitAuditInfoList
	 * @return
	 * @throws Exception
	 */
	SubmitAuditResult submitAudit(String authorizerAppId, SubmitAuditParameter submitAuditInfoList) throws WechatErrorException;


	/**
	 * 7. 查询某个指定版本的审核状态（仅供第三方代小程序调用）
	 *    @param authorizerAppId   小程序appId
	 * @param auditid
	 * @return
	 * @throws Exception
	 */
	AuditStatusResult getAuditStatus(String authorizerAppId, Long auditid) throws WechatErrorException;



	/**
	 * 8. 查询最新一次提交的审核状态（仅供第三方代小程序调用）
	 *    @param authorizerAppId   小程序appId
	 * @return
	 * @throws Exception
	 */
	AuditStatusResult getLatestAuditStatus(String authorizerAppId) throws WechatErrorException;



	/**
	 * 9. 发布已通过审核的小程序（仅供第三方代小程序调用）
	 *    @param authorizerAppId   小程序appId
	 * 请填写空的数据包，POST的json数据包为空即可。
	 * </p>
	 *
	 * @return
	 * @throws WechatErrorException
	 */
	ApiSetWebviewDomainInfo releaesAudited(String authorizerAppId) throws WechatErrorException;


	/**
	 * 11. 小程序版本回退（仅供第三方代小程序调用）
	 *    @param authorizerAppId   小程序appId
	 * @return
	 * @throws Exception
	 */
	ApiSetWebviewDomainInfo revertCodeRelease(String authorizerAppId) throws WechatErrorException;

	/**
	 * 15. 小程序审核撤回
	 * <p>
	 * 单个帐号每天审核撤回次数最多不超过1次，一个月不超过10次。
	 * </p>
	 *
	 * @return
	 * @throws Exception
	 */
	ApiSetWebviewDomainInfo undoCodeAudit(String authorizerAppId) throws WechatErrorException;


	/**
	 * 微信登录
	 * @param appId  	小程序的AppID
	 * @param jsCode   	登录时获取的 code
	 * @return
	 */
	 Jscode2SessionResult miniProgramJscode2Session(String appId, String jsCode) throws WechatErrorException;
}
