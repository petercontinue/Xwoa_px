package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @since 2021-02-23
 */
@Data
@Accessors(chain = true)
@TableName("wsc_shoppingcat")
public class WscShoppingcat extends Model<WscShoppingcat> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 商品ID
     */
	@TableField("goodsid")
	private Long goodsid;
    /**
     * 商品价格属性ID
     */
	@TableField("goodsShuxingPriceID")
	private Long goodsShuxingPriceID;
    /**
     * 活动ID（普通商品默认0）
     */
	@TableField("huodongID")
	private Long huodongID;
    /**
     * 数量
     */
	@TableField("num")
	private Long num;
	@TableField("addUser")
	private Long addUser;
	@TableField("addDateTime")
	private Date addDateTime;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WscShoppingcat{" +
			", id=" + id +
			", goodsid=" + goodsid +
			", goodsShuxingPriceID=" + goodsShuxingPriceID +
			", huodongID=" + huodongID +
			", num=" + num +
			", addUser=" + addUser +
			", addDateTime=" + addDateTime +
			", qiyeID=" + qiyeID +
			"}";
	}
}
