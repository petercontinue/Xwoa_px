package com.xwcloud.cloud.oa.controller.smallProgram.xcx;

import lombok.Data;

import java.util.List;

/**
 * @author
 * @date 2018/11/8
 */
@Data
public class PageListResult {

	private String errcode;
	private String errmsg;

	/**
	 * page_list 页面配置列表
	 */
	private List<String> pageList;

}
