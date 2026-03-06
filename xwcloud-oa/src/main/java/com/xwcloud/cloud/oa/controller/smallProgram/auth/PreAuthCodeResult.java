package com.xwcloud.cloud.oa.controller.smallProgram.auth;

import lombok.Data;

/**
 * @author
 * @date 2018/12/14
 */
@Data
public class PreAuthCodeResult {

	private String preAuthCode;
	private Integer expiresIn;
}
