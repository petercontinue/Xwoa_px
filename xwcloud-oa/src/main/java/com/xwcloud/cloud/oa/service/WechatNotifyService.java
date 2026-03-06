package com.xwcloud.cloud.oa.service;


import com.xwcloud.cloud.oa.controller.smallProgram.common.WechatThirdXmlMessage;
import com.xwcloud.cloud.oa.controller.smallProgram.error.WechatErrorException;

/**
 * @author
 * @date 2018/12/6
 */
public interface WechatNotifyService {


	boolean checkSignature(String timestamp, String nonce, String signature);

	String route(WechatThirdXmlMessage wechatThirdXmlMessage) throws WechatErrorException;


}
