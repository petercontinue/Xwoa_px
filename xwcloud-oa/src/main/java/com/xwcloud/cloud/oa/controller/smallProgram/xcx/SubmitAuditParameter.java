package com.xwcloud.cloud.oa.controller.smallProgram.xcx;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author
 * @date 2018/11/8
 * 第三方平台提交小程序代码审核
 */
@Data
@Builder
public class SubmitAuditParameter {

	/**
	 * 提交审核项的一个列表（至少填写1项，至多填写5项）
	 */
	private List<Item> item_list;

	@Data
	@Builder
	public static class Item{
		/**
		 * 一级类目名称
		 */
		private String first_class;
		/**
		 * 二级类目名称
		 */
		private String second_class;
		/**
		 * 三级类目名称
		 */
		private String third_class;
		/**
		 * 一级类目的ID编号
		 */
		private Integer first_id;
		/**
		 * 二级类目的ID编号
		 */
		private Integer second_id;
		/**
		 * 三级类目的ID编号
		 */
		private Integer third_id;

		/**
		 * 小程序的页面，可通过“获取小程序的第三方提交代码的页面配置”接口获得
		 */
		private String address;
		/**
		 * 小程序的标签，多个标签用空格分隔，标签不能多于10个，标签长度不超过20
		 */
		private String tag;
		/**
		 * 小程序页面的标题,标题长度不超过32
		 */
		private String title;
	}


}
