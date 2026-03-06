package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @since 2021-08-23
 */
@Data
@Accessors(chain = true)
@TableName("wsc_tixian")
public class WscTixian extends Model<WscTixian> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
	@TableField("wsc_user_id")
	private Long wscUserId;
    /**
     * 姓名，冗余存储
     */
	@TableField("realName")
	private String realName;
    /**
     * 手机号，冗余存储，防止后面换手机号了
     */
	@TableField("phone")
	private String phone;
    /**
     * 银行卡号，冗余存储，防止后面换银行卡号了
     */
	@TableField("bankCard")
	private String bankCard;
    /**
     * 开户行，冗余存储，防止后面换银行卡号了
     */
	@TableField("bankName")
	private String bankName;
    /**
     * 申请提现的金额
     */
	@TableField("tixianMoney")
	private String tixianMoney;
    /**
     * 提现申请提交的时间
     */
	@TableField("shengqingTime")
	private Date shengqingTime;
    /**
     * 提现说明
     */
	@TableField("tixianShuoming")
	private String tixianShuoming;
    /**
     * 提现审批状态：1待审批，2审批未通过，3审批通过
     */
	@TableField("tixianShengpiState")
	private Integer tixianShengpiState;
    /**
     * 审批说明，审批不通过时，必须填本字段（审批不通过的原因）
     */
	@TableField("shengpiShuoming")
	private String shengpiShuoming;
    /**
     * 审批人
     */
	@TableField("tixianShengpiStaffID")
	private Long tixianShengpiStaffID;
    /**
     * 审批时间
     */
	@TableField("tixianShengpiTime")
	private Date tixianShengpiTime;
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
		return "WscTixian{" +
			", id=" + id +
			", wscUserId=" + wscUserId +
			", realName=" + realName +
			", phone=" + phone +
			", bankCard=" + bankCard +
			", bankName=" + bankName +
			", tixianMoney=" + tixianMoney +
			", shengqingTime=" + shengqingTime +
			", tixianShuoming=" + tixianShuoming +
			", tixianShengpiState=" + tixianShengpiState +
			", shengpiShuoming=" + shengpiShuoming +
			", tixianShengpiStaffID=" + tixianShengpiStaffID +
			", tixianShengpiTime=" + tixianShengpiTime +
			", qiyeID=" + qiyeID +
			"}";
	}
}
