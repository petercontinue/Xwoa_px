package com.xwcloud.cloud.oa.controller.smallProgram.common;

import lombok.Data;

import java.util.List;

/**
 * @author
 * @date 2018/11/7
 * 设置小程序服务器域名
 */
@Data
public class ApiModifyDomainInfo {

	/**
	 *错误码
	 */
	private String errcode;
	/**
	 *错误信息
	 */
	private String errmsg;
	/**
	 *request合法域名，当action参数是get时不需要此字段
	 */
	List<String> requestdomain;
	/**
	 *socket合法域名，当action参数是get时不需要此字段
	 */
	List<String> wsrequestdomain;
	/**
	 *uploadFile合法域名，当action参数是get时不需要此字段
	 */
	List<String> uploaddomain;
	/**
	 *downloadFile合法域名，当action参数是get时不需要此字段
	 */
	List<String> downloaddomain;

}
