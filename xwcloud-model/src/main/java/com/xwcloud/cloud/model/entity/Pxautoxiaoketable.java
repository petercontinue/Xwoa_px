package com.xwcloud.cloud.model.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2021-01-05
 */
@Data
@Accessors(chain = true)
@TableName("pxautoxiaoketable")
public class Pxautoxiaoketable extends Model<Pxautoxiaoketable> {

	private static final long serialVersionUID = 1L;

	@TableId("id")
	@JsonSerialize(using= ToStringSerializer.class)
	private Long id;
	@TableField("buxiID")
	private Long buxiid;
	@TableField("classID")
	private Long classid;
	@TableField("keshiNum")
	private BigDecimal keshinum;
	@TableField("teaIDs")
	private String teaids;
	@TableField("teaNames")
	private String teanames;
	/**
	 * 状态：1，关闭。2，开启
	 */
	@TableField("state")
	private Integer state;
	@TableField("qiyeID")
	private Long qiyeid;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxautoxiaoketable{" +
				", id=" + id +
				", buxiid=" + buxiid +
				", classid=" + classid +
				", keshinum=" + keshinum +
				", teaids=" + teaids +
				", teanames=" + teanames +
				", state=" + state +
				", qiyeid=" + qiyeid +
				"}";
	}
}
