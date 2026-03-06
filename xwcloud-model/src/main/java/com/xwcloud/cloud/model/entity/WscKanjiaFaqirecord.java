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
 * @since 2021-05-21
 */
@Data
@Accessors(chain = true)
@TableName("wsc_kanjia_faqirecord")
public class WscKanjiaFaqirecord extends Model<WscKanjiaFaqirecord> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 砍价商品ID
     */
	@TableField("kanjiaGoodsID")
	private Long kanjiaGoodsID;
    /**
     * 商品属性ID，对应wsc_goodsshuxinglistprice表的id
     */
	@TableField("goodsshuxinglistpriceID")
	private Long goodsshuxinglistpriceID;
    /**
     * 砍价活动发起人的微信用户ID
     */
	@TableField("kanjiaFaqiRenWxUserID")
	private Long kanjiaFaqiRenWxUserID;
    /**
     * 砍价底价，即砍价成功价
     */
	@TableField("minMoney")
	private BigDecimal minMoney;
    /**
     * 砍价初始价，即原价
     */
	@TableField("startMoney")
	private BigDecimal startMoney;
    /**
     * 当前价
     */
	@TableField("currentMoney")
	private BigDecimal currentMoney;
    /**
     * 添加时间
     */
	@TableField("addTime")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date addTime;
	@TableField("qiyeID")
	private Long qiyeID;
	@TableField("state")
	private int state;

	private String nickName;
	private String headImage;
	private int bangkancount;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WscKanjiaFaqirecord{" +
			", id=" + id +
			", kanjiaGoodsID=" + kanjiaGoodsID +
			", goodsshuxinglistpriceID=" + goodsshuxinglistpriceID +
			", kanjiaFaqiRenWxUserID=" + kanjiaFaqiRenWxUserID +
			", minMoney=" + minMoney +
			", startMoney=" + startMoney +
			", currentMoney=" + currentMoney +
			", addTime=" + addTime +
			", qiyeID=" + qiyeID +
			"}";
	}
}
