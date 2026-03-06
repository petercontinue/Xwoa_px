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
 * @since 2021-02-22
 */
@Data
@Accessors(chain = true)
@TableName("wsc_yongjin")
public class WscYongjin extends Model<WscYongjin> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 订单号
     */
	@TableField("orderNumber")
	private Long orderNumber;
	@TableField("money")
	private BigDecimal money;
	@TableField("fid")
	private Long fid;
	@TableField("fidMoney")
	private BigDecimal fidMoney;
	@TableField("fidJiesuanDateTime")
	private LocalDateTime fidJiesuanDateTime;
	@TableField("topfid")
	private Long topfid;
	@TableField("topfidMoney")
	private BigDecimal topfidMoney;
	@TableField("topfidJiesuanDateTime")
	private LocalDateTime topfidJiesuanDateTime;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Wscyongjin{" +
			", id=" + id +
			", orderNumber=" + orderNumber +
			", money=" + money +
			", fid=" + fid +
			", fidMoney=" + fidMoney +
			", fidJiesuanDateTime=" + fidJiesuanDateTime +
			", topfid=" + topfid +
			", topfidMoney=" + topfidMoney +
			", topfidJiesuanDateTime=" + topfidJiesuanDateTime +
			", qiyeID=" + qiyeID +
			"}";
	}
}
