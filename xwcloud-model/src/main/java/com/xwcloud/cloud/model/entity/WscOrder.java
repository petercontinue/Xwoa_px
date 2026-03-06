package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * @since 2021-05-24
 */
@Data
@Accessors(chain = true)
@TableName("wsc_order")
public class WscOrder extends Model<WscOrder> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 订单号
     */
	@TableField("orderNumber")
	private String orderNumber;
    /**
     * 支付金额
     */
	@TableField("payMoney")
	private BigDecimal payMoney;
    /**
     * 支付积分
     */
	@TableField("payJifen")
	private BigDecimal payJifen;
    /**
     * 优惠券ID
     */
	@TableField("couponsID")
	private Long couponsID;
    /**
     * 0送货上门，1自取，2虚拟课程(商品)
     */
	@TableField("type")
	private Integer type;
    /**
     * 收货人。type=2为联系人
     */
	@TableField("receiveName")
	private String receiveName;
    /**
     * 收货地址
     */
	@TableField("receiveDizhi")
	private String receiveDizhi;
    /**
     * 收货电话(联系电话)
     */
	@TableField("lianxiTel")
	private String lianxiTel;
    /**
     * 意向校区，多个意向用逗号分隔；当type=2时,才设置意向校区
     */
	@TableField("yxCampusIDs")
	private String yxCampusIDs;
    /**
     * 订单备注
     */
	@TableField("beizhu")
	private String beizhu;
    /**
     * 下单时间
     */
	@TableField("orderDateTime")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date orderDateTime;
    /**
     * 0.余额支付 1.微信支付 2.积分支付
     */
	@TableField("payStyle")
	private Integer payStyle;
    /**
     * 支付时间
     */
	@TableField("payDateTime")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date payDateTime;
    /**
     * 活动ID，0表示普通订单，其他值对应wsc_huodong表的id
     */
	@TableField("huodongID")
	private Integer huodongID;
    /**
     * 订单来源，1微信端，2抖音，默认1
     */
	@TableField("orderFrom")
	private Integer orderFrom;
    /**
     * 下单用户
     */
	@TableField("orderUserID")
	private Long orderUserID;
    /**
     * 订单状态：1下单未支付、2下单已支付（未发货），3已发货（待收货），4已完成，5退款，6已关闭的订单
     */
	@TableField("orderState")
	private Integer orderState;
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
		return "WscOrder{" +
			", id=" + id +
			", orderNumber=" + orderNumber +
			", payMoney=" + payMoney +
			", payJifen=" + payJifen +
			", couponsID=" + couponsID +
			", type=" + type +
			", receiveName=" + receiveName +
			", receiveDizhi=" + receiveDizhi +
			", lianxiTel=" + lianxiTel +
			", yxCampusIDs=" + yxCampusIDs +
			", beizhu=" + beizhu +
			", orderDateTime=" + orderDateTime +
			", payStyle=" + payStyle +
			", payDateTime=" + payDateTime +
			", huodongID=" + huodongID +
			", orderFrom=" + orderFrom +
			", orderUserID=" + orderUserID +
			", orderState=" + orderState +
			", qiyeID=" + qiyeID +
			"}";
	}
}
