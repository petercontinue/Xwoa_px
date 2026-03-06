package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-04
 */
@Data
@Accessors(chain = true)
@TableName("pxtskaiguandefaulttable")
public class Pxtskaiguandefaulttable extends Model<Pxtskaiguandefaulttable> {

	private static final long serialVersionUID = 1L;

	@TableId("id")
	private Long id;
	/**
	 * 推送开关名称
	 */
	@TableField("TSTypeName")
	private String tstypename;
	/**
	 * 默认值
	 */
	@TableField("value")
	private String value;
	/**
	 * 说明
	 */
	@TableField("beizhu")
	private String beizhu;
	/**
	 * 类别，1学员消息，2老师消息
	 */
	@TableField("type")
	private String type;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxtskaiguandefaulttable{" +
				", id=" + id +
				", tstypename=" + tstypename +
				", value=" + value +
				", beizhu=" + beizhu +
				", type=" + type +
				"}";
	}
}
