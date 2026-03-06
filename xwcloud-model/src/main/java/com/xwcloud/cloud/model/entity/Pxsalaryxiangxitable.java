package com.xwcloud.cloud.model.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-25
 */
@Data
@Accessors(chain = true)
public class Pxsalaryxiangxitable extends Model<Pxsalaryxiangxitable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
    /**
     * 工资类别ID
     */
	@TableField("salarystyleID")
	private String salarystyleID;
    /**
     * 工资金额
     */
	@TableField("salarymoney")
	private BigDecimal salarymoney;
    /**
     * 工资ID
     */
	@TableField("salaryID")
	private Long salaryID;
    /**
     * 工资说明
     */
	@TableField("shuoming")
	private String shuoming;
    /**
     * 添加时间
     */
	@TableField("addTime")
	private Date addTime;
    /**
     * 添加人
     */
	@TableField("addStaffID")
	private Long addStaffID;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxsalaryxiangxitable{" +
			", id=" + id +
			", salarystyleID=" + salarystyleID +
			", salarymoney=" + salarymoney +
			", salaryID=" + salaryID +
			", shuoming=" + shuoming +
			", addTime=" + addTime +
			", addStaffID=" + addStaffID +
			", qiyeID=" + qiyeID +
			"}";
	}
}
