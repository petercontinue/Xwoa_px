package com.xwcloud.cloud.oa.controller.smallProgram.xcx;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author
 * @date 2018/11/13
 * 模板列表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateListResult {

	/**
	 * 模板小程序appId
	 */
	private String sourceMiniprogramAppid;

	/**
	 * 模板小程序开发者
	 */
	private String developer;

	/**
	 * 模板小程序昵称
	 */
	private String sourceMiniprogram;
	/**
	 * 模版id
	 */
	private Long templateId;
	/**
	 * 模版版本号，开发者自定义字段
	 */
	private String userVersion;
	/**
	 * 模版描述 开发者自定义字段
	 */
	private String userDesc;
	/**
	 * 开发者上传草稿时间 / 被添加为模版的时间
	 */
	private Long createTime;

}
