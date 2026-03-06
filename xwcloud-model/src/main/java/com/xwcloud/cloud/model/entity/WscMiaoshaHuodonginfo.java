package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2021-05-24
 */
@Data
@Accessors(chain = true)
@TableName("wsc_miaosha_huodonginfo")
public class WscMiaoshaHuodonginfo extends Model<WscMiaoshaHuodonginfo> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 活动标题
     */
	@TableField("huodongtitle")
	private String huodongtitle;
    /**
     * 活动主图
     */
	@TableField("huodongImg")
	private String huodongImg;
    /**
     * 活动说明
     */
	@TableField("huodongshuoming")
	private String huodongshuoming;
    /**
     * 浏览次数
     */
	@TableField("liulanTimes")
	private Integer liulanTimes;
    /**
     * 分享次数
     */
	@TableField("fenxiangTimes")
	private Integer fenxiangTimes;
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
		return "WscMiaoshaHuodonginfo{" +
			", id=" + id +
			", huodongtitle=" + huodongtitle +
			", huodongImg=" + huodongImg +
			", huodongshuoming=" + huodongshuoming +
			", liulanTimes=" + liulanTimes +
			", fenxiangTimes=" + fenxiangTimes +
			", qiyeID=" + qiyeID +
			"}";
	}
}
