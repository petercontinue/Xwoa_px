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
 * @since 2021-05-20
 */
@Data
@Accessors(chain = true)
@TableName("wsc_pingtuan_huodong")
public class WscPingtuanHuodong extends Model<WscPingtuanHuodong> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 活动标题
     */
	@TableField("huodongTitle")
	private String huodongTitle;
    /**
     * 活动图片
     */
	@TableField("huodongImg")
	private String huodongImg;
    /**
     * 活动说明信息
     */
	@TableField("huodongshuoming")
	private String huodongshuoming;
    /**
     * 浏览次数
     */
	@TableField("liulangTimes")
	private Integer liulangTimes;
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
		return "WscPingtuanHuodong{" +
			", id=" + id +
			", huodongTitle=" + huodongTitle +
			", huodongImg=" + huodongImg +
			", huodongshuoming=" + huodongshuoming +
			", liulangTimes=" + liulangTimes +
			", fenxiangTimes=" + fenxiangTimes +
			", qiyeID=" + qiyeID +
			"}";
	}
}
