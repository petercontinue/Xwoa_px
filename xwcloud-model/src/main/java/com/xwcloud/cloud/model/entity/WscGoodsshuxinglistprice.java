package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2021-05-22
 */
@Data
@Accessors(chain = true)
@TableName("wsc_goodsshuxinglistprice")
public class WscGoodsshuxinglistprice extends Model<WscGoodsshuxinglistprice> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
	@TableField("goodsID")
	private Long goodsID;
    /**
     * 商品的属性组合
     */
	@TableField("goodsShuxingListAll")
	private String goodsShuxingListAll;
    /**
     * 商品原价(设置了属性的商品原价)，如果没有设置属性，原价详见wsc_goods表的basicPrice
     */
	@TableField("originalPrice")
	private BigDecimal originalPrice;
    /**
     * 普通售价（没有活动时的售价）
     */
	@TableField("price")
	private BigDecimal price;
    /**
     * 积分价（设置商品属性。不参加活动的商品才能用积分），如果没设置商品属性，积分价详见wsc_goods表里的jifenPrice
     */
	@TableField("jifenPrice")
	private BigDecimal jifenPrice;
    /**
     * 限时抢购价，如果没有设置属性，限时抢购价详见wsc_goods表的onlyTimeBuyPrice
     */
	@TableField("onlyTimeBuyPrice")
	private BigDecimal onlyTimeBuyPrice;
    /**
     * 砍价成功的价格，如果没设置属性，砍价成功价格详见wsc_goods里的kanjiaSuccessPrice
     */
	@TableField("kanjiaSuccessPrice")
	private BigDecimal kanjiaSuccessPrice;
	@TableField("qiyeID")
	private Long qiyeID;
    /**
     * 组合后的库存数量
     */
	@TableField("kcnum")
	private Integer kcnum;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WscGoodsshuxinglistprice{" +
			", id=" + id +
			", goodsID=" + goodsID +
			", goodsShuxingListAll=" + goodsShuxingListAll +
			", originalPrice=" + originalPrice +
			", price=" + price +
			", jifenPrice=" + jifenPrice +
			", onlyTimeBuyPrice=" + onlyTimeBuyPrice +
			", kanjiaSuccessPrice=" + kanjiaSuccessPrice +
			", qiyeID=" + qiyeID +
			", kcnum=" + kcnum +
			"}";
	}
}
