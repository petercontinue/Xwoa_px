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
 * @since 2021-03-28
 */
@Data
@Accessors(chain = true)
@TableName("wsc_userjiaoyi")
public class WscUserjiaoyi extends Model<WscUserjiaoyi> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    /**
     * 订单号
     */
    @TableField("orderNumber")
    private Long orderNumber;
    /**
     * 商城用户ID
     */
    @TableField("wscUserID")
    private Long wscUserID;
    /**
     * 实际支付金额
     */
    @TableField("payMoney")
    private BigDecimal payMoney;
    /**
     * 赠送金额，支出暂为0
     */
    @TableField("giveMoney")
    private BigDecimal giveMoney;
    /**
     * 总金额（实际支付金额 + 赠送金额）
     */
    @TableField("totalMoney")
    private BigDecimal totalMoney;
    /**
     * 1.余额 2.积分
     */
    @TableField("style")
    private Integer style;
    /**
     * 1.收入  2.支出
     */
    @TableField("type")
    private Integer type;
    /**
     * 交易添加时间
     */
    @TableField("addDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date addDate;
    /**
     * 交易完成时间
     */
    @TableField("okDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date okDate;
    /**
     * true:成功的记录  false:失败的记录
     */
    @TableField("state")
    private Boolean state;
    @TableField("qiyeID")
    private Long qiyeID;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "WscUserjiaoyi{" +
                ", id=" + id +
                ", orderNumber=" + orderNumber +
                ", wscUserID=" + wscUserID +
                ", payMoney=" + payMoney +
                ", giveMoney=" + giveMoney +
                ", totalMoney=" + totalMoney +
                ", style=" + style +
                ", type=" + type +
                ", addDate=" + addDate +
                ", okDate=" + okDate +
                ", state=" + state +
                ", qiyeID=" + qiyeID +
                "}";
    }
}
