package com.xwcloud.cloud.oa.controller.smallProgram.xcx;

import lombok.Data;

/**
 * @author
 * @date 2018/11/7
 */
@Data
public class ApiBindTesterInfo {
	/**
	 *错误码
	 */
	private Integer errcode;
	/**
	 *错误信息
	 */
	private String errmsg;

	/**
	 * 人员对应的唯一字符串
	 */
	private String userstr;
}
