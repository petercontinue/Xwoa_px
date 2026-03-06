package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>a
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
@Data
@Accessors(chain = true)
@TableName("wsc_goodsshuxinglist")
public class WscGoodsshuxinglist extends Model<WscGoodsshuxinglist> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 商品ID
     */
	@TableField("goodsID")
	private Long goodsid;
    /**
     * 商品规格类别ID，一个规格类别对应多个商品规格（但只能选中一个商品规格），比如颜色
     */
	@TableField("goodsGuigeTypeID")
	private Long goodsguigetypeid;
    /**
     * 商品属性名称(某个商品规格类别下的属性名称)，比如红色，蓝色……
     */
	@TableField("shuxingMing")
	private String shuxingming;
    /**
     * 商品属性排序
     */
	@TableField("shuxingPaixu")
	private Integer shuxingpaixu;
    /**
     * 是否要更换图片，1不需要，2需要更换，默认1
     */
	@TableField("isNeedChangImg")
	private Integer isneedchangimg;
	@TableField("addStaffID")
	private Long addstaffid;
	@TableField("addTime")
	private LocalDateTime addtime;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WscGoodsshuxinglist{" +
			", id=" + id +
			", goodsid=" + goodsid +
			", goodsguigetypeid=" + goodsguigetypeid +
			", shuxingming=" + shuxingming +
			", shuxingpaixu=" + shuxingpaixu +
			", isneedchangimg=" + isneedchangimg +
			", addstaffid=" + addstaffid +
			", addtime=" + addtime +
			", qiyeid=" + qiyeID +
			"}";
	}
}
