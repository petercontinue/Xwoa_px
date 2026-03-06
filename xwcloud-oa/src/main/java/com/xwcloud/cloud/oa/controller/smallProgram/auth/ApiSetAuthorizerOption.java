package com.xwcloud.cloud.oa.controller.smallProgram.auth;

import lombok.Data;

/**
 * @author
 * @date 2018/11/6
 * 设置授权方的选项信息
 */
@Data
public class ApiSetAuthorizerOption {

	/**
	 * 错误码
	 */
	private Integer errcode;
	/**
	 * 错误信息
	 */
	private String errmsg;
}
