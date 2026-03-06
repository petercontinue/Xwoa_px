package com.xwcloud.cloud.oa.controller.smallProgram.xcx;

import lombok.Data;

import java.util.List;

/**
 * @author
 * @date 2018/11/8
 * 可填选的类目列表
 */
@Data
public class CategoryListResult {
	private String errcode;
	private String errmsg;

	/**
	 * 可填选的类目列表
	 */
	private List<CategoryList> categoryList;

	@Data
	public static class CategoryList{
		/**
		 *一级类目名称
		 */
		private String firstClass;
		/**
		 *二级类目名称
		 */
		private String secondClass;
		/**
		 *三级类目名称
		 */
		private String thirdClass;
		/**
		 *一级类目的ID编号
		 */
		private Integer firstId;
		/**
		 *二级类目的ID编号
		 */
		private Integer secondId;
		/**
		 *三级类目的ID编号
		 */
		private Integer thirdId;
	}


}
