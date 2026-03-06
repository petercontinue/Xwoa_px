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
 * @since 2021-03-08
 */
@Data
@Accessors(chain = true)
@TableName("pxstuparamtypetable")
public class Pxstuparamtypetable extends Model<Pxstuparamtypetable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 学员自定义字段名称
     */
	@TableField("stuParamTypeName")
	private String stuParamTypeName;
	@TableField("IsBiTian")
	private Boolean isBiTian;
    /**
     * null，0 短框 1.长框 2.下拉框
     */
	@TableField("widthType")
	private Integer widthType;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxstuparamtypetable{" +
			", id=" + id +
			", stuParamTypeName=" + stuParamTypeName +
			", isBiTian=" + isBiTian +
			", widthType=" + widthType +
			", qiyeID=" + qiyeID +
			"}";
	}
}
