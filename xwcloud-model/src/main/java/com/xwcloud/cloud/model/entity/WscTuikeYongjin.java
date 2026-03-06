package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2021-08-19
 */
@Data
@Accessors(chain = true)
@TableName("wsc_tuike_yongjin")
public class WscTuikeYongjin extends Model<WscTuikeYongjin> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 订单号
     */
	@TableField("orderNumber")
	private Long orderNumber;
    /**
     * 订单金额
     */
	@TableField("money")
	private BigDecimal money;
    /**
     * 父级ID
     */
	@TableField("fid")
	private Long fid;
    /**
     * 父级返佣金额
     */
	@TableField("fidMoney")
	private BigDecimal fidMoney;
    /**
     * 佣金结算时间（订单完成时间）
     */
	@TableField("fidJiesuanDateTime")
	private LocalDateTime fidJiesuanDateTime;
    /**
     * 祖级ID
     */
	@TableField("topfid")
	private Long topfid;
    /**
     * 祖级返佣金额
     */
	@TableField("topfidMoney")
	private BigDecimal topfidMoney;
    /**
     * 佣金结算时间（订单完成时间）
     */
	@TableField("topfidJiesuanDateTime")
	private LocalDateTime topfidJiesuanDateTime;
    /**
     * 拥金类别：1购买返佣，2下线成为推客返佣，默认值1
     */
	@TableField("yongjinType")
	private Integer yongjinType;
    /**
     * 说明
     */
	@TableField("shuoming")
	private String shuoming;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WscTuikeYongjin{" +
			", id=" + id +
			", orderNumber=" + orderNumber +
			", money=" + money +
			", fid=" + fid +
			", fidMoney=" + fidMoney +
			", fidJiesuanDateTime=" + fidJiesuanDateTime +
			", topfid=" + topfid +
			", topfidMoney=" + topfidMoney +
			", topfidJiesuanDateTime=" + topfidJiesuanDateTime +
			", yongjinType=" + yongjinType +
			", shuoming=" + shuoming +
			", qiyeID=" + qiyeID +
			"}";
	}
}
