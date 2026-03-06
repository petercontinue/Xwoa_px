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
 * @since 2021-05-25
 */
@Data
@Accessors(chain = true)
@TableName("whd_toupiao_huodong")
public class WhdToupiaoHuodong extends Model<WhdToupiaoHuodong> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 投票活动名称
     */
	@TableField("toupiaoHuodongName")
	private String toupiaoHuodongName;
    /**
     * 活动描述
     */
	@TableField("miaoshu")
	private String miaoshu;
    /**
     * 活动logo
     */
	@TableField("logo")
	private String logo;
    /**
     * 1.一人只能投一票 2.一人一天一票
     */
	@TableField("toupiaoStyle")
	private Integer toupiaoStyle;
    /**
     * 活动奖品
     */
	@TableField("jiangpin")
	private String jiangpin;
    /**
     * 活动规则
     */
	@TableField("rules")
	private String rules;
    /**
     * 机构简介
     */
	@TableField("jigouJianjie")
	private String jigouJianjie;
    /**
     * 添加时间
     */
	@TableField("addTime")
	private Date addTime;
    /**
     * 添加人
     */
	@TableField("addUser")
	private Long addUser;
    /**
     * 开始时间
     */
	@TableField("startTime")
	private Date startTime;
    /**
     * 结束时间
     */
	@TableField("endTime")
	private Date endTime;
    /**
     * 是否上架，0不上架，1上架，默认值0
     */
	@TableField("isUp")
	private Integer isUp;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WhdToupiaoHuodong{" +
			", id=" + id +
			", toupiaoHuodongName=" + toupiaoHuodongName +
			", miaoshu=" + miaoshu +
			", logo=" + logo +
			", toupiaoStyle=" + toupiaoStyle +
			", jiangpin=" + jiangpin +
			", rules=" + rules +
			", jigouJianjie=" + jigouJianjie +
			", addTime=" + addTime +
			", addUser=" + addUser +
			", startTime=" + startTime +
			", endTime=" + endTime +
			", isUp=" + isUp +
			", qiyeID=" + qiyeID +
			"}";
	}
}
