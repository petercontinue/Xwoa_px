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
 * <p>a
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
@Data
@Accessors(chain = true)
@TableName("whd_chongzhi_payrecord")
public class WhdChongzhiPayrecord extends Model<WhdChongzhiPayrecord> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 微商城用户ID
     */
	@TableField("wxUserID")
	private Long wxuserid;
    /**
     * 支付金额
     */
	@TableField("payMoney")
	private BigDecimal paymoney;
    /**
     * 说明
     */
	@TableField("shuoming")
	private String shuoming;
    /**
     * 支付时间
     */
	@TableField("payTime")
	private LocalDateTime paytime;
    /**
     * 企业ID
     */
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WhdChongzhiPayrecord{" +
			", id=" + id +
			", wxuserid=" + wxuserid +
			", paymoney=" + paymoney +
			", shuoming=" + shuoming +
			", paytime=" + paytime +
			", qiyeid=" + qiyeID +
			"}";
	}
}
