package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @since 2020-11-17
 */
@Data
@Accessors(chain = true)
public class Pxsalarytable extends Model<Pxsalarytable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
    /**
     * 工资金额
     */
	@TableField("salaryMoney")
	private String salaryMoney;
    /**
     * 审核状态，0未审核，1已审核通过，2已审核未通过
     */
	@TableField("shenheState")
	private Integer shenheState;
    /**
     * 员工ID
     */
	@TableField("staffID")
	private Long staffID;
    /**
     * 审核未通过原因
     */
	@TableField("shenheNopassReason")
	private String shenheNopassReason;
    /**
     * 审核时间
     */
	@TableField("shengHeTime")
	private Date shengHeTime;
    /**
     * 备注
     */
	@TableField("beizhu")
	private String beizhu;
    /**
     * 发工资的日期
     */
	@TableField("salaryDate")
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM")
	private Date salaryDate;

	/**
	 * 发工资的结束日期
	 */
	@TableField("salaryEndDate")
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM")
	private Date salaryEndDate;

    /**
     * 录入人
     */
	@TableField("lururen")
	private Long lururen;
    /**
     * 录入时间
     */
	@TableField("lurudatetime")
	private Date lurudatetime;
    /**
     * 审核人
     */
	@TableField("shengheren")
	private Long shengheren;
    /**
     * 发放状态，0是未发放 1是已发放，默认值0
     */
	@TableField("fafangzhuangtai")
	private Integer fafangzhuangtai;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxsalarytable{" +
			", id=" + id +
			", salaryMoney=" + salaryMoney +
			", shenheState=" + shenheState +
			", staffID=" + staffID +
			", shenheNopassReason=" + shenheNopassReason +
			", shengHeTime=" + shengHeTime +
			", beizhu=" + beizhu +
			", salaryDate=" + salaryDate +
			", lururen=" + lururen +
			", lurudatetime=" + lurudatetime +
			", shengheren=" + shengheren +
			", fafangzhuangtai=" + fafangzhuangtai +
			", qiyeID=" + qiyeID +
			"}";
	}
}
