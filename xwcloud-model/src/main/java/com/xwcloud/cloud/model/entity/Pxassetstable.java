package com.xwcloud.cloud.model.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-22
 */
@Data
@Accessors(chain = true)
public class Pxassetstable extends Model<Pxassetstable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonSerialize(using= ToStringSerializer.class)
	private Long id;
	@TableField("campusID")
	private Long campusID;
	@TableField("assetsName")
	private String assetsName;
	@TableField("leibie")
	private String leibie;
	@TableField("guige")
	private String guige;
	@TableField("num")
	private BigDecimal num;
	@TableField("danwei")
	private String danwei;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxassetstable{" +
			", id=" + id +
			", campusID=" + campusID +
			", assetsName=" + assetsName +
			", leibie=" + leibie +
			", guige=" + guige +
			", num=" + num +
			", danwei=" + danwei +
			", qiyeID=" + qiyeID +
			"}";
	}
}
