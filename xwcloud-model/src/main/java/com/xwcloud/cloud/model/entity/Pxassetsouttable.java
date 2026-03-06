package com.xwcloud.cloud.model.entity;


import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

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
public class Pxassetsouttable extends Model<Pxassetsouttable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonSerialize(using= ToStringSerializer.class)
	private Long id;
	@TableField("AssetsID")
	private Long AssetsID;
	@TableField("num")
	private BigDecimal num;
	@TableField("addStaffID")
	private Long addStaffID;
	@TableField("addTime")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date addTime;
	@TableField("beizhu")
	private String beizhu;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxassetsouttable{" +
			", id=" + id +
			", AssetsID=" + AssetsID +
			", num=" + num +
			", addStaffID=" + addStaffID +
			", addTime=" + addTime +
			", beizhu=" + beizhu +
			", qiyeID=" + qiyeID +
			"}";
	}
}
