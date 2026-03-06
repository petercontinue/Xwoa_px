package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2021-04-15
 */
@Data
@Accessors(chain = true)
@TableName("pxkeshistutable")
public class Pxkeshistutable extends Model<Pxkeshistutable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 学员ID
     */
	@TableField("stuID")
	private Long stuID;
    /**
     * 班级ID
     */
	@TableField("classID")
	private Long classID;
    /**
     * 校区ID
     */
	@TableField("campusID")
	private Long campusID;
    /**
     * 课程ID
     */
	@TableField("kechengID")
	private Long kechengID;
    /**
     * 通课课程ID  只有是通课课程时存通课课程ID（不区分一对一、班课）
     */
	@TableField("tongkekechengID")
	private Long tongkekechengID;
    /**
     * 课程内容
     */
	@TableField("kechengContent")
	private String kechengContent;
    /**
     * 学员年级ID
     */
	@TableField("StuGradeID")
	private Long stuGradeID;
    /**
     * 上课老师ID，多个老师的话，用逗号隔开
     */
	@TableField("teacherIDs")
	private String teacherIDs;
    /**
     * 上课老师姓名，多个老师的话，用逗号隔开
     */
	@TableField("teacherNames")
	private String teacherNames;
    /**
     * 上课日期
     */
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
	@TableField("startLessonDateTime")
	private Time startLessonDateTime;
    /**
     * 下课时间
     */
	@TableField("endLessonDateTime")
	private Time endLessonDateTime;
    /**
     * 上课的课时数
     */
	@TableField("keshiNum")
	private BigDecimal keshiNum;
    /**
     * 补习方式ID
     */
	@TableField("buxiStyleID")
	private Long buxiStyleID;
    /**
     * 课程时长
     */
	@TableField("classTimeStyleID")
	private Long classTimeStyleID;
    /**
     * 课程消课价格
     */
	@TableField("kechengPrice")
	private BigDecimal kechengPrice;
    /**
     * 考勤类别：1正常、2请假、3旷课、4迟到、5早退、6补课
     */
	@TableField("stuKaoqingStyle")
	private String stuKaoqingStyle;
    /**
     * 打考勤方法：0未知，1电脑消课不排课消课，2电脑按排课消课，3微信消课，4刷卡消课，5自动消课，6刷脸消课,7 余额消课
     */
	@TableField("dakaoqingStyle")
	private Integer dakaoqingStyle;
    /**
     * 说明（当打考勤方式=7时，为使用余额消课，因为没有固定班级、任课老师、补习课程、补习方式，保存时值为0 ，保存上课日期、与上课时间<=>消课时间 ）
     */
	@TableField("shuoMing")
	private String shuoMing;
    /**
     * 添加人
     */
	@TableField("adduser")
	private Long adduser;
    /**
     * 添加时间
     */
	@TableField("addtime")
	private Date addtime;
    /**
     * 上课的助教老师，记录老师的姓名
     */
	@TableField("zhujiao")
	private String zhujiao;
    /**
     * 补习课程ID
     */
	@TableField("buxikechengID")
	private Long buxikechengID;

	/**
	 * 共享补课程ID，没扣共享课程课时的时候，为0，默认值0
	 * 如果在pxbuxikechengtable表里设置了共享课时ID的，消完自己的课时以后，就扣减共享课程的课时；但在学员课时记录表里要有记录；
	 */
	@TableField("shareBuxiID")
	private Long shareBuxiID;
    /**
     * 学员班主任ID
     */
	@TableField("banzhurenStaffID")
	private Long banzhurenStaffID;
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
		return "Pxkeshistutable{" +
			", id=" + id +
			", stuID=" + stuID +
			", classID=" + classID +
			", campusID=" + campusID +
			", kechengID=" + kechengID +
			", tongkekechengID=" + tongkekechengID +
			", kechengContent=" + kechengContent +
			", stuGradeID=" + stuGradeID +
			", teacherIDs=" + teacherIDs +
			", teacherNames=" + teacherNames +
			", haveClassDate=" + haveClassDate +
			", weekN=" + weekN +
			", startLessonDateTime=" + startLessonDateTime +
			", endLessonDateTime=" + endLessonDateTime +
			", keshiNum=" + keshiNum +
			", buxiStyleID=" + buxiStyleID +
			", classTimeStyleID=" + classTimeStyleID +
			", kechengPrice=" + kechengPrice +
			", stuKaoqingStyle=" + stuKaoqingStyle +
			", dakaoqingStyle=" + dakaoqingStyle +
			", shuoMing=" + shuoMing +
			", adduser=" + adduser +
			", addtime=" + addtime +
			", zhujiao=" + zhujiao +
			", buxikechengID=" + buxikechengID +
			", banzhurenStaffID=" + banzhurenStaffID +
			", qiyeID=" + qiyeID +
			"}";
	}
}
