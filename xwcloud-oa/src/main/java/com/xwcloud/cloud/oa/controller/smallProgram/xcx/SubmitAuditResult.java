package com.xwcloud.cloud.oa.controller.smallProgram.xcx;

import lombok.Data;

/**
 * @author
 * @date 2018/11/8
 * 微信开放平台小程序发布代码审核结果
 */
@Data
public class SubmitAuditResult {
	private String errcode;
	private String errmsg;

	/**
	 * 审核编号
	 */
	private Long auditId;
}
