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
 * @since 2021-04-08
 */
@Data
@Accessors(chain = true)
@TableName("pxsysparamvaluetable")
public class Pxsysparamvaluetable extends Model<Pxsysparamvaluetable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 关联pxsysparamdefaulttable表里的ID
     */
	@TableField("sysparamTypeID")
	private Long sysparamTypeID;
    /**
     * 变量值
     */
	@TableField("modifyValue")
	private String modifyValue;
    /**
     * 企业ID
     */
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxsysparamvaluetable{" +
			", id=" + id +
			", sysparamTypeID=" + sysparamTypeID +
			", modifyValue=" + modifyValue +
			", qiyeID=" + qiyeID +
			"}";
	}
}
