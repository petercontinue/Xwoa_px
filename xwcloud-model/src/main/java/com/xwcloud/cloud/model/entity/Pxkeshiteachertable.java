package com.xwcloud.cloud.model.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.sql.Time;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2020-12-04
 */
@Data
@Accessors(chain = true)
@TableName("pxkeshiteachertable")
public class Pxkeshiteachertable extends Model<Pxkeshiteachertable> {

	private static final long serialVersionUID = 1L;

	@TableId("id")
	private Long id;
	/**
	 * 老师ID
	 */
	@TableField("teacherID")
	private Long teacherID;
	/**
	 * 班级ID
	 */
	@TableField("classID")
	private Long classID;
	/**
	 * 学员ID
	 */
	@TableField("stuID")
	private Long stuID;
	/**
	 * 课程ID
	 */
	@TableField("kechengID")
	private Long kechengID;
	/**
	 * 课程内容
	 */
	@TableField("kechengContent")
	private String kechengcontent;
	/**
	 * 补习方式ID
	 */
	@TableField("buxiStyleID")
	private Long buxiStyleID;
	/**
	 * 课程时长ID
	 */
	@TableField("classTimeStyleID")
	private Long classTimeStyleID;
	/**
	 * 上课日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField("haveClassDate")
	private Date haveClassDate;
	/**
	 * 星期几
	 */
	@TableField("weekN")
	private String weekN;
	/**
	 * 上课时间
	 */
	@DateTimeFormat(pattern = "HH:mm:ss")
	@TableField("startLessonDateTime")
	private Time startLessonDateTime;
	/**
	 * 下课时间
	 */
	@DateTimeFormat(pattern = "HH:mm:ss")
	@TableField("endLessonDateTime")
	private Time endLessonDateTime;
	/**
	 * 课时数
	 */
	@TableField("keshiNum")
	private BigDecimal keshiNum;
	/**
	 * 应上几人
	 */
	@TableField("ysStuNum")
	private Integer ysStuNum;
	/**
	 * 实上几人
	 */
	@TableField("ssStuNum")
	private Integer ssStuNum;
	/**
	 * 校区ID
	 */
	@TableField("campusID")
	private Long campusID;
	/**
	 * 老师考勤说明
	 */
	@TableField("teacherkaoqing")
	private String teacherkaoqing;
	@TableField("shuoMing")
	private String shuoMing;
	/**
	 * 上课助教
	 */
	@TableField("zhujiao")
	private String zhujiao;
	/**
	 * 上课学员的年级，多个年级的用逗号隔开
	 */
	@TableField("allstuNianji")
	private String allstuNianji;
	/**
	 * 企业ID
	 */
	@TableField("qiyeID")
	private Long qiyeID;

}
