package com.xwcloud.cloud.oa.service.impl;


import com.xwcloud.cloud.oa.controller.smallProgram.common.WechatConstant;
import com.xwcloud.cloud.oa.controller.smallProgram.common.WechatThirdConfig;
import com.xwcloud.cloud.oa.controller.smallProgram.common.WechatThirdXmlMessage;
import com.xwcloud.cloud.oa.controller.smallProgram.crypto.SHA1;
import com.xwcloud.cloud.oa.controller.smallProgram.error.WechatErrorException;
import com.xwcloud.cloud.oa.service.RedisService;
import com.xwcloud.cloud.oa.service.WechatNotifyService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @date 2018/12/6
 */
@Service
public class WechatNotifyServiceImpl implements WechatNotifyService {
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WechatThirdConfig wechatThirdConfig;
	@Autowired
	private RedisService redisService;


	@Override
	public boolean checkSignature(String timestamp, String nonce, String signature) {
		try {
			return SHA1.gen(wechatThirdConfig.getComponentToken(), timestamp, nonce)
					.equals(signature);
		} catch (Exception e) {
			this.log.error("Checking signature failed, and the reason is :" + e.getMessage());
			return false;
		}
	}


	@Override
	public String route(WechatThirdXmlMessage wechatThirdXmlMessage) throws WechatErrorException {
		if (wechatThirdXmlMessage == null) {
			throw new NullPointerException("message is empty");
		}
		if (StringUtils.equalsIgnoreCase(wechatThirdXmlMessage.getInfoType(), WechatConstant.COMPONENT_VERIFY_TICKET_KEY)) {
			redisService.set(WechatConstant.COMPONENT_VERIFY_TICKET_KEY,wechatThirdXmlMessage.getComponentVerifyTicket(),600);
			return "success";
		}
		//停止授权
		else if (StringUtils.equals(wechatThirdXmlMessage.getInfoType(), "unauthorized")) {
			redisService.del(WechatConstant.AUTHORIZER_ACCESS_TOKEN_KEY+"_"+wechatThirdXmlMessage.getAuthorizerAppid(),WechatConstant.AUTHORIZER_REFRESH_TOKEN_KEY+"_"+wechatThirdXmlMessage.getAuthorizerAppid());
		}
		return "";
	}

}
