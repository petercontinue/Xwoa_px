package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2021-05-21
 */
@Data
@Accessors(chain = true)
@TableName("wsc_pingtuan_faqirecord")
public class WscPingtuanFaqirecord extends Model<WscPingtuanFaqirecord> {

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
     * 拼团活动发起人的微信用户ID
     */
	@TableField("pingtuanFaqiRenWxUserID")
	private Long pingtuanFaqiRenWxUserID;
    /**
     * 发起人的订单ID
     */
	@TableField("faqiRenOrderID")
	private Long faqiRenOrderID;
    /**
     * 0.进行中，1成功，2失败
     */
	@TableField("state")
	private Integer state;
    /**
     * 添加时间
     */
	@TableField("addTime")
	private Date addTime;
	@TableField("qiyeID")
	private Long qiyeID;

	private String nickName;

	private String headImage;

	private int joinCount;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WscPingtuanFaqirecord{" +
			", id=" + id +
			", pingtuanGoodsID=" + pingtuanGoodsID +
			", goodsshuxinglistpriceID=" + goodsshuxinglistpriceID +
			", pingtuanFaqiRenWxUserID=" + pingtuanFaqiRenWxUserID +
			", faqiRenOrderID=" + faqiRenOrderID +
			", state=" + state +
			", addTime=" + addTime +
			", qiyeID=" + qiyeID +
			"}";
	}
}
