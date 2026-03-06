package com.xwcloud.cloud.oa.service;


import com.xwcloud.cloud.oa.controller.smallProgram.auth.*;
import com.xwcloud.cloud.oa.controller.smallProgram.error.WechatErrorException;

/**
 * @author
 * @date 2018/12/7
 */
public interface WechatThirdAuthService {


	String httpRequestRefreshComponentAccessToken(String requestUrl,String requestMethod, String param, String accessTokenKey) throws WechatErrorException;
	/**
	 * 获取第三方平台access_token  默认有效期是7200秒 120分
	 * @return
	 * @throws WechatErrorException
	 */
	String getComponentAccessToken() throws WechatErrorException;

	/**
	 * 获取预授权码  默认有效期是600秒 10分
	 * @return
	 * @throws WechatErrorException
	 */
	String getPreAuthCode() throws WechatErrorException;

	/**
	 * 进入授权页面
	 * @param redirectURI  回调URI
	 * @param authType   要授权的帐号类型， 1则商户扫码后，手机端仅展示公众号、2表示仅展示小程序，3表示公众号和小程序都展示。如果为未制定，则默认小程序和公众号都展示。第三方平台开发者可以使用本字段来控制授权的帐号类型。
	 * @param bizAppid   指定授权唯一的小程序或公众号
	 * @return
	 * @throws Exception
	 */
	String getPreAuthUrl(String redirectURI, String authType, String bizAppid) throws WechatErrorException;

	/**
	 * 4、使用授权码换取公众号或小程序的接口调用凭据和授权信息
	 * @param authorizationCode  授权code,会在授权成功时返回给第三方平台，详见第三方平台授权流程说明
	 */
	ApiQueryAuthInfo getApiQueryAuthInfo(String authorizationCode) throws WechatErrorException;

	/**
	 * 5、获取（刷新）授权公众号的令牌
	 * @param authorizerAppid  授权方appid
	 * @param authorizerRefreshToken  授权方的刷新令牌，刷新令牌主要用于第三方平台获取和刷新已授权用户的access_token，只会在授权时刻提供，请妥善保存。一旦丢失，只能让用户重新授权，才能再次拿到新的刷新令牌
	 */
	ApiAuthorizerTokenInfo apiAuthorizerToken(String authorizerAppid, String authorizerRefreshToken) throws WechatErrorException;

	/**
	 * 6、获取授权方的账户信息
	 * @param authorizerAppid  授权方appid
	 */
	ApiGetAuthorizerInfo apiGetAuthorizerInfo(String authorizerAppid) throws WechatErrorException;

	/**
	 * 7、获取授权方的选项设置信息
	 * @param authorizerAppid  授权方appid
	 * @param optionName  	选项名称
	 */
	ApiGetAuthorizerOption apiGetAuthorizerOption(String authorizerAppid, String optionName) throws WechatErrorException;

	/**
	 * 8、设置授权方的选项信息
	 * @param authorizerAppid  授权方appid
	 * @param optionName  	选项名称
	 * @param optionValue 设置的选项值
	 */
	ApiSetAuthorizerOption apiSetAuthorizerOption(String authorizerAppid, String optionName, String optionValue) throws WechatErrorException;


	/**
	 * 发送客服消息
	 * @param json
	 * @param accessToken
	 * @return
	 */
	String sendMessage(String json,String accessToken) throws WechatErrorException;
}
