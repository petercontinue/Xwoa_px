package com.xwcloud.cloud.oa.controller.smallProgram.auth;

import lombok.Data;

/**
 * @author
 * @date 2018/11/6
 * 获取授权方的选项设置信息
 */
@Data
public class ApiGetAuthorizerOption {
	/**
	 *授权公众号或小程序的appid
	 */
	private String authorizerAppid;
	/**
	 *选项名称
	 */
	private String optionName;
	/**
	 *选项值
	 */
	private String optionValue;
}
