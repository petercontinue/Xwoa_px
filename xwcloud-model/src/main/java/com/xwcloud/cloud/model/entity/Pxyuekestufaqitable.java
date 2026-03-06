package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-04
 */
@Data
@Accessors(chain = true)
public class Pxyuekestufaqitable extends Model<Pxyuekestufaqitable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
    /**
     * 课程ID
     */
	@TableField("kechengID")
	private long kechengID;
    /**
     * 发起约课的学员的补习课程ID
     */
	@TableField("buxikechengID")
	private long buxikechengID;
    /**
     * 约课希望的上课方式，1一对一，2班课；只有是班课的情况下，其他学员才可以响应
     */
	@TableField("buxiStyle")
	private Integer buxiStyle;
    /**
     * 向哪个老师发起约课，老师ID
     */
	@TableField("teacherID")
	private long teacherID;
    /**
     * 上课日期
     */
	@TableField("haveClassDate")
	private Date haveClassDate;
    /**
     *  上课时间
     */
	@TableField("haveLessonStartTime")
	private Time haveLessonStartTime;
    /**
     * 下课时间
     */
	@TableField("haveLessonEndTime")
	private Time haveLessonEndTime;
    /**
     * 发起约课的学生留言
     */
	@TableField("faqiyuekeStuLiuyan")
	private String faqiyuekeStuLiuyan;
    /**
     * 约课审核状态，1未审核，2已审核通过，3审核未通过,默认值1
     */
	@TableField("yuekeShenheState")
	private Integer yuekeShenheState;
    /**
     * 约课审核答复,审核通过的答复内容，或审核未通过原因
     */
	@TableField("yuekeShenheDafu")
	private String yuekeShenheDafu;
    /**
     * 发起约课的学员ID
     */
	@TableField("faqiYuekeStuID")
	private long faqiYuekeStuID;
    /**
     * 发起约课的时间
     */
	@TableField("addTime")
	private Date addTime;
    /**
     * 约课审核老师
     */
	@TableField("shenheStaffID")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long shenheStaffID;
    /**
     * 审核时间
     */
	@TableField("shenheDatetime")
	private Date shenheDatetime;
    /**
     * 企业ID
     */
	@TableField("qiyeID")
	private Long qiyeID;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
