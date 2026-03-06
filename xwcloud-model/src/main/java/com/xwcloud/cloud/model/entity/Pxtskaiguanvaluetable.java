package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-08
 * @since 2021-01-05
 */
@Data
@Accessors(chain = true)
@TableName("pxtskaiguanvaluetable")
public class Pxtskaiguanvaluetable extends Model<Pxtskaiguanvaluetable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
    /**
     * 关联pxtskaiguandefaulttable表的ID
     */
	@TableField("TSTypeID")

	private Long TSTypeID;
    /**
     * 推送的值
     */
	@TableField("value")
	private String value;
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
		return "Pxtskaiguanvaluetable{" +
			", id=" + id +
			", tstypeid=" + TSTypeID +
			", value=" + value +
			", qiyeid=" + qiyeID +
			"}";
	}
}
