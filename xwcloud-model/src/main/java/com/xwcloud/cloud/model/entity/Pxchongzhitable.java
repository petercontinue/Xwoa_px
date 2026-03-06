package com.xwcloud.cloud.model.entity;

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
 * @since 2020-11-23
 */
@Data
@Accessors(chain = true)
public class Pxchongzhitable extends Model<Pxchongzhitable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    /**
     * 学员ID
     */
    @TableField("stuID")
    private Long stuID;
    /**
     * 实际充值金额
     */
    @TableField("shijiChongzhiMoney")
    private BigDecimal shijiChongzhiMoney;
    /**
     * 赠送金额
     */
    @TableField("songMoney")
    private BigDecimal songMoney;
    /**
     * 实得金额
     */
    @TableField("shideTotalMoney")
    private BigDecimal shideTotalMoney;
    /**
     * 备注说明
     */
    @TableField("shuoming")
    private String shuoming;
    /**
     * 业绩人
     */
    @TableField("yejiStaffID")
    private Long yejiStaffID;
    /**
     * 充值时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("chongzhiDatetime")
    private Date chongzhiDatetime;
	/**
	 * 支付时间
	 */
	@TableField("payDatetime")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date payDatetime;
	/**
	 * 支付状态
	 */
	@TableField("payState")
	private  Integer payState;
    /**
     * 充值支付方式，逗号分割
     */
    @TableField("payMoneyStyle")
    private String payMoneyStyle;
    /**
     * 录入人
     */
    @TableField("addStaffID")
    private Long addStaffID;
    /**
     * 录入时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("addTime")
    private Date addTime;
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
        return "Pxchongzhitable{" +
                ", id=" + id +
                ", stuID=" + stuID +
                ", shijiChongzhiMoney=" + shijiChongzhiMoney +
                ", songMoney=" + songMoney +
                ", shideTotalMoney=" + shideTotalMoney +
                ", shuoming=" + shuoming +
                ", yejiStaffID=" + yejiStaffID +
                ", chongzhiDatetime=" + chongzhiDatetime +
				",payMoneyStyle="+payMoneyStyle+
				",payDatetime="+payDatetime+
				",payState="+payState+
                ", addStaffID=" + addStaffID +
                ", addTime=" + addTime +
                ", qiyeID=" + qiyeID +
                "}";
    }
}
