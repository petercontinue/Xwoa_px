package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2021-05-25
 */
@Data
@Accessors(chain = true)
@TableName("whd_coupons")
public class WhdCoupons extends Model<WhdCoupons> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 优惠券名称
     */
	@TableField("couponsName")
	private String couponsName;
    /**
     * 优惠金额
     */
	@TableField("Money")
	private BigDecimal money;
    /**
     * 满多少金额可用
     */
	@TableField("manMoney")
	private BigDecimal manMoney;
    /**
     * 1：商品种类优惠券 2：通用优惠券
     */
	@TableField("type")
	private Integer type;
    /**
     * 商品优惠券必填
     */
	@TableField("GoodTypeID")
	private Long GoodTypeID;
    /**
     * 开始时间
     */
	@TableField("stratDate")
	private Date stratDate;
    /**
     * 结束时间
     */
	@TableField("endDate")
	private Date endDate;
    /**
     * 0.手动赠送，1新人赠送，2满赠，3~
     */
	@TableField("giveType")
	private Integer giveType;
    /**
     * 满赠要求金额
     */
	@TableField("minGiveMoney")
	private BigDecimal minGiveMoney;
    /**
     * 添加人
     */
	@TableField("addStaffID")
	private Long addStaffID;
    /**
     * 添加时间
     */
	@TableField("addDate")
	private Date addDate;
    /**
     * qiyeID
     */
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WhdCoupons{" +
			", id=" + id +
			", couponsName=" + couponsName +
			", money=" + money +
			", manMoney=" + manMoney +
			", type=" + type +
			", GoodTypeID=" + GoodTypeID +
			", stratDate=" + stratDate +
			", endDate=" + endDate +
			", giveType=" + giveType +
			", minGiveMoney=" + minGiveMoney +
			", addStaffID=" + addStaffID +
			", addDate=" + addDate +
			", qiyeID=" + qiyeID +
			"}";
	}
}
