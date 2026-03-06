package com.xwcloud.cloud.oa.controller.smallProgram.auth;

import lombok.Data;


@Data
public class ComponentAccessTokenResult {

	private String componentAccessToken;
	private Integer expiresIn;


}
