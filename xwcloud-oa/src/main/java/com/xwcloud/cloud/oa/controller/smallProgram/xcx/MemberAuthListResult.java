package com.xwcloud.cloud.oa.controller.smallProgram.xcx;

import lombok.Data;

import java.util.List;

/**
 * @author
 * @date 2018/11/7
 * 获取体验者列表
 */
@Data
public class MemberAuthListResult {
	/**
	 *错误码
	 */
	private Integer errcode;
	/**
	 *错误信息
	 */
	private String errmsg;

	/**
	 * 人员列表
	 */
	private List<Member> members;

	@Data
	public static  class Member{
		/**
		 * 人员对应的唯一字符串
		 */
		private String userstr;

	}

}
