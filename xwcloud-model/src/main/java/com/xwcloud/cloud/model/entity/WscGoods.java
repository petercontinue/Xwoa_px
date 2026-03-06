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
 * @since 2021-05-20
 */
@Data
@Accessors(chain = true)
@TableName("wsc_goods")
public class WscGoods extends Model<WscGoods> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 商品名称
     */
	@TableField("goodsName")
	private String goodsName;
    /**
     * 商品类别ID，对应wscgoodtype表里的id
     */
	@TableField("goodsTypeID")
	private Long goodsTypeID;
    /**
     * 一口价（没有商品属性的时候用一口价，如果同时还开启活动，这个价就是活动的原价）
     */
	@TableField("basicPrice")
	private BigDecimal basicPrice;
    /**
     * 积分价（不设置商品属性。不参加活动的商品才能用积分），如果设置商品属性，积分价详见wsc_goodsshuxinglistprice表里的jifenPrice
     */
	@TableField("jifenPrice")
	private BigDecimal jifenPrice;
    /**
     * 活动ID，0表示不参加活动，如果不为0，即对应活动表的ID，默认值0
     */
	@TableField("huodongID")
	private Long huodongID;
    /**
     * 活动开始时间（如果开启了活动的话）
     */
	@TableField("huodongStartTime")
	private LocalDateTime huodongStartTime;
    /**
     * 活动结束时间（如果开启了活动的话）
     */
	@TableField("huodongEndTime")
	private LocalDateTime huodongEndTime;
    /**
     * 限时抢购价（没有设置属性的限时抢购价），如果设置了属性，限时抢购价详见wsc_goodsshuxinglistprice
     */
	@TableField("onlyTimeBuyPrice")
	private BigDecimal onlyTimeBuyPrice;
    /**
     * 砍价一刀最少砍多少
     */
	@TableField("kanjiaOniceMinNum")
	private BigDecimal kanjiaOniceMinNum;
    /**
     * 砍价一刀最多砍多少
     */
	@TableField("kanjiaOniceMaxNum")
	private BigDecimal kanjiaOniceMaxNum;
    /**
     * 砍价成功的价格（没有设置属性时的砍价活动的底价-即砍价成功的价格），如果设置了属性，砍价成功价格详见wsc_goodsshuxinglistprice
     */
	@TableField("kanjiaSuccessPrice")
	private BigDecimal kanjiaSuccessPrice;
    /**
     * 商品详情
     */
	@TableField("goodInfo")
	private String goodInfo;
    /**
     * banner图1，也是商品主图
     */
	@TableField("img1")
	private String img1;
	@TableField("img2")
	private String img2;
	@TableField("img3")
	private String img3;
	@TableField("img4")
	private String img4;
	@TableField("img5")
	private String img5;
    /**
     * 录入人
     */
	@TableField("addUser")
	private Long addUser;
    /**
     * 录入时间
     */
	@TableField("addTime")
	private LocalDateTime addTime;
    /**
     * 上架状态：1,上架,0未上架或已下架
     */
	@TableField("shangjiaState")
	private Integer shangjiaState;
    /**
     * 分享次数
     */
	@TableField("fxNum")
	private Integer fxNum;
    /**
     * 最大报名人数，0表示人数不限
     */
	@TableField("maxNum")
	private Integer maxNum;
    /**
     * 商品说明1,这个字段不为空即显示，为空就不显示
     */
	@TableField("goodsShuomingOne")
	private String goodsShuomingOne;
    /**
     * 商品说明1字体是否加粗
     */
	@TableField("goodsShuomingOneIsBold")
	private String goodsShuomingOneIsBold;
    /**
     * 商品说明1字体颜色（十六进制）
     */
	@TableField("goodsShuomingOneFontColor")
	private String goodsShuomingOneFontColor;
    /**
     * 商品说明2,这个字段不为空即显示，为空就不显示
     */
	@TableField("goodsShuomingTwo")
	private String goodsShuomingTwo;
    /**
     * 商品说明2,字体是否加粗
     */
	@TableField("goodsShuomingTwoIsBold")
	private String goodsShuomingTwoIsBold;
    /**
     * 商品说明2，字体颜色（十六进制）
     */
	@TableField("goodsShuomingTwoFontColor")
	private String goodsShuomingTwoFontColor;
    /**
     * 加入购物车量
     */
	@TableField("cartNum")
	private Integer cartNum;
    /**
     * 购买的是课程的话，可选校区ID有哪些，逗号隔开
     */
	@TableField("campusIDs")
	private String campusIDs;
    /**
     * 1 课程商品，2实物商品，3其他虚拟商品
     */
	@TableField("style")
	private Integer style;
    /**
     * 商品排序
     */
	@TableField("paixu")
	private Integer paixu;
	@TableField("qiyeID")
	private Long qiyeID;
    /**
     * 是否是自动签单。1不自动录签单，2自动签单（新签），3自动签单（续费）
     */
	@TableField("isAutoQiandan")
	private Integer isAutoQiandan;
    /**
     * 父级返佣比例
     */
	@TableField("fidFanyongBili")
	private BigDecimal fidFanyongBili;
    /**
     * 祖级返佣比例
     */
	@TableField("gfidFanyongBili")
	private BigDecimal gfidFanyongBili;
    /**
     * 默认0：全部发布，1：只发布微信，2：只发布抖音
     */
	@TableField("fabuTo")
	private Integer fabuTo;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WscGoods{" +
			", id=" + id +
			", goodsName=" + goodsName +
			", goodsTypeID=" + goodsTypeID +
			", basicPrice=" + basicPrice +
			", jifenPrice=" + jifenPrice +
			", huodongID=" + huodongID +
			", huodongStartTime=" + huodongStartTime +
			", huodongEndTime=" + huodongEndTime +
			", onlyTimeBuyPrice=" + onlyTimeBuyPrice +
			", kanjiaOniceMinNum=" + kanjiaOniceMinNum +
			", kanjiaOniceMaxNum=" + kanjiaOniceMaxNum +
			", kanjiaSuccessPrice=" + kanjiaSuccessPrice +
			", goodInfo=" + goodInfo +
			", img1=" + img1 +
			", img2=" + img2 +
			", img3=" + img3 +
			", img4=" + img4 +
			", img5=" + img5 +
			", addUser=" + addUser +
			", addTime=" + addTime +
			", shangjiaState=" + shangjiaState +
			", fxNum=" + fxNum +
			", maxNum=" + maxNum +
			", goodsShuomingOne=" + goodsShuomingOne +
			", goodsShuomingOneIsBold=" + goodsShuomingOneIsBold +
			", goodsShuomingOneFontColor=" + goodsShuomingOneFontColor +
			", goodsShuomingTwo=" + goodsShuomingTwo +
			", goodsShuomingTwoIsBold=" + goodsShuomingTwoIsBold +
			", goodsShuomingTwoFontColor=" + goodsShuomingTwoFontColor +
			", cartNum=" + cartNum +
			", campusIDs=" + campusIDs +
			", style=" + style +
			", paixu=" + paixu +
			", qiyeID=" + qiyeID +
			", isAutoQiandan=" + isAutoQiandan +
			", fidFanyongBili=" + fidFanyongBili +
			", gfidFanyongBili=" + gfidFanyongBili +
			", fabuTo=" + fabuTo +
			"}";
	}
}
