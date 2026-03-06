package com.xwcloud.cloud.model.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @since 2020-11-04
 */
@Data
@Accessors(chain = true)
public class Pxyueketeacherfabutable extends Model<Pxyueketeacherfabutable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
    /**
     * 老师ID，多个老师逗号隔开
     */
	@TableField("teacherIDs")
	private String teacherIDs;
    /**
     * 老师姓名，多个老师逗号隔开
     */
	@TableField("teacherNames")
	private String teacherNames;
    /**
     * 班级ID
     */
	@TableField("classID")
	private long classID;
    /**
     * 上课日期
     */
	@TableField("haveLessonDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date haveLessonDate;
    /**
     * 上课时间
     */
	@TableField("startLessonTime")
	@DateTimeFormat(pattern = "HH:mm:ss")
	private Date startLessonTime;
    /**
     * 下课时间
     */
	@TableField("endLessonTime")
	@DateTimeFormat(pattern = "HH:mm:ss")
	private Date endLessonTime;
    /**
     * 课时数
     */
	@TableField("keshiNum")
	private BigDecimal keshiNum;
    /**
     * 课程ID
     */
	@TableField("KechengID")
	private long KechengID;
    /**
     * 教室ID,发布约课时可以先不明确教室
     */
	@TableField("classroomID")
	private long classroomID;
    /**
     * 最小人数，即约课成功的最小人数
     */
	@TableField("minSuccessYuekeStuNum")
	private Integer minSuccessYuekeStuNum;
    /**
     * 最大人数不填表示不限制
     */
	@TableField("maxStuNum")
	private Integer maxStuNum;
    /**
     * 排课ID
     */
	@TableField("paikeID")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long paikeID;
    /**
     * 约课状态，1未约满正常可约，2约满了，3过期了
     */
	@TableField("yuekeState")
	private Integer yuekeState;
    /**
     * 校区ID
     */
	@TableField("campusID")
	private long campusID;
	@TableField("addTime")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date addTime;
	@TableField("addStaffID")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long addStaffID;
	@TableField("qiyeID")
	private Long qiyeID;
	/**
	 * 约课活动标题（移动端显示）
	 */
	@TableField("yuekeTitle")
	private String yuekeTitle;
	/**
	 * 约课说明（移动端显示）
	 */
	@TableField("yuekeshuoming")
	private  String yuekeshuoming;
	/**
	 * 约课活动图片（约课活动图片）
	 */
	@TableField("yuekeImg")
	private  String yuekeImg;

	@TableField("liulanTimes")
	private Integer liulanTimes;

	@TableField("fenxiangTimes")
	private Integer fenxiangTimes;



	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxyueketeacherfabutable{" +
			", id=" + id +
			", teacherIDs=" + teacherIDs +
			", teacherNames=" + teacherNames +
			", classID=" + classID +
			", haveLessonDate=" + haveLessonDate +
			", startLessonTime=" + startLessonTime +
			", endLessonTime=" + endLessonTime +
			", keshiNum=" + keshiNum +
			", KechengID=" + KechengID +
			", classroomID=" + classroomID +
			", minSuccessYuekeStuNum=" + minSuccessYuekeStuNum +
			", maxStuNum=" + maxStuNum +
			", paikeID=" + paikeID +
			", yuekeState=" + yuekeState +
			", campusID=" + campusID +
			", addTime=" + addTime +
			", addStaffID=" + addStaffID +
			", qiyeID=" + qiyeID +
			"}";
	}
}
