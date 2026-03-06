package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2021-05-10
 */
@Data
@Accessors(chain = true)
@TableName("pxczhuodongtable")
public class Pxczhuodongtable extends Model<Pxczhuodongtable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
	@TableField("zongmoney")
	private BigDecimal zongmoney;
	@TableField("huodongmoney")
	private BigDecimal huodongmoney;
    /**
     * 活动类型 1.满减
     */
	@TableField("type")
	private Integer type;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField("Sdate")
	private Date sdate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField("Edate")
	private Date edate;
	@TableField("addStaffID")
	private Long addStaffID;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@TableField("addTime")
	private Date addTime;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxczhuodongtable{" +
			", id=" + id +
			", zongmoney=" + zongmoney +
			", huodongmoney=" + huodongmoney +
			", type=" + type +
			", sdate=" + sdate +
			", edate=" + edate +
			", addStaffID=" + addStaffID +
			", addTime=" + addTime +
			", qiyeID=" + qiyeID +
			"}";
	}
}
