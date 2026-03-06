package com.xwcloud.cloud.model.entity;

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
public class Pxqingjiatable extends Model<Pxqingjiatable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
    /**
     * 上课老师ID，多个老师逗号隔开
     */
	@TableField("teacherIDs")
	private String teacherIDs;
    /**
     * 上课老师姓名，多个老师逗号隔开
     */
	@TableField("teacherNames")
	private String teacherNames;
    /**
     * 课程ID
     */
	@TableField("kechengID")
	private Long kechengID;
    /**
     * 班级ID
     */
	@TableField("classID")
	private Long classID;
    /**
     * 上课日期
     */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField("haveClassDate")
	private Date haveClassDate;
    /**
     * 上课时间
     */
	@DateTimeFormat(pattern = "HH:mm:ss")
	@TableField("startLessonDateTime")
	private Date startLessonDateTime;
    /**
     * 下课时间
     */
	@DateTimeFormat(pattern = "HH:mm:ss")
	@TableField("endLessonDateTime")
	private Date endLessonDateTime;
    /**
     * 请假学员ID
     */
	@TableField("stuid")
	private Long stuid;
    /**
     * 请假备注
     */
	@TableField("beizhu")
	private String beizhu;
    /**
     * 请假时间
     */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField("qingjiaDateTime")
	private Date qingjiaDateTime;
    /**
     * 请假状态：1正常，2学生自己取消了请假。默认值为1
     */
	@TableField("qjState")
	private Integer qjState;
    /**
     * 取消请假的时间
     */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField("cancelQjDateTime")
	private Date cancelQjDateTime;
    /**
     * 取消请假的原因，即学生操作取消请假的时间要填写原因；
     */
	@TableField("cancelQjReason")
	private String cancelQjReason;
    /**
     * 请假审核人
     */
	@TableField("shenheRen")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long shenheRen;
    /**
     * 审核状态，0未审核，1已审核通过，2已审核未通过
     */
	@TableField("shenheState")
	private Integer shenheState;
    /**
     * 审核未通过原因
     */
	@TableField("shenheNopassReason")
	private String shenheNopassReason;
    /**
     * 添加时间
     */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField("addDate")
	private Date addDate;
    /**
     * 排课ID
     */
	@TableField("paikeID")
	private Long paikeID;
    /**
     * 班主任老师ID
     */
	@TableField("banzhurenID")
	private String banzhurenID;
	@TableField("qiyeID")
	private Long qiyeID;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField("shenheDateTime")
	private Date shenheDateTime;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxqingjiatable{" +
			", id=" + id +
			", teacherIDs=" + teacherIDs +
			", teacherNames=" + teacherNames +
			", kechengID=" + kechengID +
			", classID=" + classID +
			", haveClassDate=" + haveClassDate +
			", startLessonDateTime=" + startLessonDateTime +
			", endLessonDateTime=" + endLessonDateTime +
			", stuid=" + stuid +
			", beizhu=" + beizhu +
			", qingjiaDateTime=" + qingjiaDateTime +
			", qjState=" + qjState +
			", cancelQjDateTime=" + cancelQjDateTime +
			", cancelQjReason=" + cancelQjReason +
			", shenheRen=" + shenheRen +
			", shenheState=" + shenheState +
			", shenheNopassReason=" + shenheNopassReason +
			", addDate=" + addDate +
			", paikeID=" + paikeID +
			", banzhurenID=" + banzhurenID +
			", qiyeID=" + qiyeID +
			"}";
	}
}
