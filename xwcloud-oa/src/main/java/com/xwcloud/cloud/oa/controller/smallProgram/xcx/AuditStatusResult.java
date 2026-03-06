package com.xwcloud.cloud.oa.controller.smallProgram.xcx;

import lombok.Data;

/**
 * @author
 * @date 2018/11/8
 * 查询某个指定版本的审核状态（仅供第三方代小程序调用）
 */
@Data
public class AuditStatusResult {
	private String errcode;
	private String errmsg;

	/**
	 * 最新的审核ID
	 */
	private Long auditid;
	/**
	 * 审核状态，其中0为审核成功，1为审核失败，2为审核中
	 */
	private Integer status;

	/**
	 * 当status=1，审核被拒绝时，返回的拒绝原因
	 */
	private String reason;

}
