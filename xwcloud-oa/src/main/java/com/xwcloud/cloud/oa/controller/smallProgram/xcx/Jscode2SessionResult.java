package com.xwcloud.cloud.oa.controller.smallProgram.xcx;

import lombok.Data;

/**
 * @author
 * @date 2018/11/26
 */
@Data
public class Jscode2SessionResult {

	/**
	 * 会话密钥
	 */
	private String sessionKey;
	/**
	 * 用户唯一标识的openid
	 */
	private String openId;
}
