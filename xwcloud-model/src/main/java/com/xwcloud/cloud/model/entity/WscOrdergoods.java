package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-24
 */
@Data
@Accessors(chain = true)
@TableName("wsc_ordergoods")
public class WscOrdergoods extends Model<WscOrdergoods> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    /**
     * 订单ID
     */
    @TableField("orderNumber")
    private Long orderNumber;
    /**
     * 商品ID,微商城的充值订单的商品ID存0，
     */
    @TableField("goodsID")
    private Long goodsID;
    /**
     * 商品属性组合id(wsc_goodsshuxinglistprice表的id)
     */
    @TableField("goodsshuxinglistpriceID")
    private String goodsshuxinglistpriceID;
    /**
     * 拼团发起ID(团长ID)，对应wsc_user表的ID
     */
    @TableField("pingtuanFaqiRenID")
    private Long pingtuanFaqiRenID;
    /**
     * 活动ID,0表示普通商品，未参加活动。其他值对应wsc_huodong表的id
     */
    @TableField("huodongID")
    private Integer huodongID;
    /**
     * 购买数量
     */
    @TableField("nums")
    private BigDecimal nums;
    /**
     * 支付单价
     */
    @TableField("payMoney")
    private BigDecimal payMoney;
    /**
     * 评价内容
     */
    @TableField("pingjia")
    private String pingjia;
    /**
     * 评价类别：0.非常满意 1.满意 2.一般 3.很糟糕
     */
    @TableField("pingjiaType")
    private Integer pingjiaType;
    /**
     * 评价日期
     */
    @TableField("pingjiaDate")
    private LocalDateTime pingjiaDate;
    @TableField("qiyeID")
    private Long qiyeID;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "WscOrdergoods{" +
                ", id=" + id +
                ", orderNumber=" + orderNumber +
                ", goodsID=" + goodsID +
                ", goodsshuxinglistpriceID=" + goodsshuxinglistpriceID +
                ", pingtuanFaqiRenID=" + pingtuanFaqiRenID +
                ", huodongID=" + huodongID +
                ", nums=" + nums +
                ", payMoney=" + payMoney +
                ", pingjia=" + pingjia +
                ", pingjiaType=" + pingjiaType +
                ", pingjiaDate=" + pingjiaDate +
                ", qiyeID=" + qiyeID +
                "}";
    }
}
