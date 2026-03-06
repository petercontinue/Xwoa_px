package com.xwcloud.cloud.model.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-08
 */
@Data
@Accessors(chain = true)
@TableName("pxkeshiresettable")
public class Pxkeshiresettable extends Model<Pxkeshiresettable> {

	private static final long serialVersionUID = 1L;

	@TableId("id")
	private Long id;
	@TableField("stuID")
	private Long stuid;
	@TableField("buxiID")
	private Long buxiid;
	/**
	 * 补习课程名称
	 */
	@TableField("buxiName")
	private String buxiname;
	@TableField("keshiNum")
	private BigDecimal keshinum;
	@TableField("xuefei")
	private BigDecimal xuefei;
	@TableField("beizhu")
	private String beizhu;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField("addDate")
	private Date adddate;
	@TableField("addStaffID")
	private Long addstaffid;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxkeshiresettable{" +
				", id=" + id +
				", stuid=" + stuid +
				", buxiid=" + buxiid +
				", buxiname=" + buxiname +
				", keshinum=" + keshinum +
				", xuefei=" + xuefei +
				", beizhu=" + beizhu +
				", adddate=" + adddate +
				", addstaffid=" + addstaffid +
				", qiyeid=" + qiyeID +
				"}";
	}
}
