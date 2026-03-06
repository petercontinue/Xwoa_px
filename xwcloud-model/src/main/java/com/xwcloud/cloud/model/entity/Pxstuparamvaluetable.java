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
 * @since 2020-11-23
 */
@Data
@Accessors(chain = true)
@TableName("pxstuparamvaluetable")
public class Pxstuparamvaluetable extends Model<Pxstuparamvaluetable> {

	private static final long serialVersionUID = 1L;

	@TableId("id")
	private Long id;
	/**
	 * 学员ID
	 */
	@TableField("stuID")
	private Long stuID;
	/**
	 * 学员自定义字段类型ID
	 */
	@TableField("stuParamTypeID")
	private Long stuParamTypeID;
	/**
	 * 自定义字段的值
	 */
	@TableField("paramValue")
	private String paramValue;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxstuparamvaluetable{" +
				", id=" + id +
				", stuID=" + stuID +
				", stuParamTypeID=" + stuParamTypeID +
				", paramValue=" + paramValue +
				", qiyeID=" + qiyeID +
				"}";
	}
}

