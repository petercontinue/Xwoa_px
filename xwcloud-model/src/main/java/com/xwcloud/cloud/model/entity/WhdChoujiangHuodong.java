package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2021-05-25
 */
@Data
@Accessors(chain = true)
@TableName("whd_choujiang_huodong")
public class WhdChoujiangHuodong extends Model<WhdChoujiangHuodong> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 抽奖活动名称
     */
	@TableField("choujiangHuodongName")
	private String choujiangHuodongName;
    /**
     * 说明
     */
	@TableField("shuoming")
	private String shuoming;
    /**
     * 机构简介
     */
	@TableField("jigouJianjie")
	private String jigouJianjie;
    /**
     * 开始时间
     */
	@TableField("startTime")
	private LocalDateTime startTime;
    /**
     * 结束时间
     */
	@TableField("endTime")
	private LocalDateTime endTime;
    /**
     * 添加时间
     */
	@TableField("addTime")
	private LocalDateTime addTime;
    /**
     * 添加人
     */
	@TableField("addUser")
	private Long addUser;
    /**
     * 1 总共能抽多少次，2每天能抽多少次
     */
	@TableField("choujiangStyle")
	private Integer choujiangStyle;
    /**
     * 抽奖次数,默认值0
     */
	@TableField("cishu")
	private Integer cishu;
    /**
     * 是否上架，1上架，2不上架
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
		return "WhdChoujiangHuodong{" +
			", id=" + id +
			", choujiangHuodongName=" + choujiangHuodongName +
			", shuoming=" + shuoming +
			", jigouJianjie=" + jigouJianjie +
			", startTime=" + startTime +
			", endTime=" + endTime +
			", addTime=" + addTime +
			", addUser=" + addUser +
			", choujiangStyle=" + choujiangStyle +
			", cishu=" + cishu +
			", isUp=" + isUp +
			", qiyeID=" + qiyeID +
			"}";
	}
}
