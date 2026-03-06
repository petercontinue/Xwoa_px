package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
@TableName("wsc_tuike_buy")
public class WscTuikeBuy extends Model<WscTuikeBuy> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 商城用户ID
     */
	@TableField("wsc_user_id")
	private Long wscUserId;
    /**
     * 真实姓名
     */
	@TableField("realName")
	private String realName;
    /**
     *  手机号
     */
	@TableField("phone")
	private String phone;
    /**
     * 原级别是普通用户的填0
     */
	@TableField("oldTuikeLevelID")
	private Long oldTuikeLevelID;
    /**
     *  购买的推客级别
     */
	@TableField("buyTuikeLevelID")
	private Long buyTuikeLevelID;
    /**
     *  支付金额
     */
	@TableField("paymoney")
	private BigDecimal paymoney;
    /**
     * 购买时间
     */
	@TableField("buyTime")
	private Date buyTime;
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
		return "WscTuikeBuy{" +
			", id=" + id +
			", wscUserId=" + wscUserId +
			", realName=" + realName +
			", phone=" + phone +
			", oldTuikeLevelID=" + oldTuikeLevelID +
			", buyTuikeLevelID=" + buyTuikeLevelID +
			", paymoney=" + paymoney +
			", buyTime=" + buyTime +
			", shuoming=" + shuoming +
			", qiyeID=" + qiyeID +
			"}";
	}
}
