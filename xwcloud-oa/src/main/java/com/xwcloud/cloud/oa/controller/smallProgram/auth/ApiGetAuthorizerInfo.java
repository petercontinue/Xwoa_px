package com.xwcloud.cloud.oa.controller.smallProgram.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author
 * @date 2018/11/6
 * 获取授权方的帐号基本信息
 */
@Data
@NoArgsConstructor
public class ApiGetAuthorizerInfo {

	private ApiAuthorizerInfo authorizerInfo;
	private ApiAuthorizationInfo authorizationInfo;

}
