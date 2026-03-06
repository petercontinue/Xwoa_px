package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;
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
 * @since 2021-05-21
 */
@Data
@Accessors(chain = true)
@TableName("wsc_pingtuan_joinrecord")
public class WscPingtuanJoinrecord extends Model<WscPingtuanJoinrecord> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 拼团商品ID
     */
	@TableField("pingtuanGoodsID")
	private Long pingtuanGoodsID;
    /**
     * 商品属性ID，对应wsc_goodsshuxinglistprice表的id
     */
	@TableField("goodsshuxinglistpriceID")
	private Long goodsshuxinglistpriceID;
    /**
     * 拼团活动发起记录ID，对应wsc_pingtuan_faqirecord表的id
     */
	@TableField("pingtuanFaqiRecordID")
	private Long pingtuanFaqiRecordID;
    /**
     * 参与人的订单ID
     */
	@TableField("joinRenOrderID")
	private Long joinRenOrderID;
    /**
     * 参与人的微商城用户ID
     */
	@TableField("joinRenWxUserID")
	private Long joinRenWxUserID;
    /**
     * 添加时间
     */
	@TableField("addTime")
	private LocalDateTime addTime;
	@TableField("qiyeID")
	private Long qiyeID;

	private String nickName;

	private String headImage;
	private String payDateTime;
	private BigDecimal payMoney;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WscPingtuanJoinrecord{" +
			", id=" + id +
			", pingtuanGoodsID=" + pingtuanGoodsID +
			", goodsshuxinglistpriceID=" + goodsshuxinglistpriceID +
			", pingtuanFaqiRecordID=" + pingtuanFaqiRecordID +
			", joinRenOrderID=" + joinRenOrderID +
			", joinRenWxUserID=" + joinRenWxUserID +
			", addTime=" + addTime +
			", qiyeID=" + qiyeID +
			"}";
	}
}
