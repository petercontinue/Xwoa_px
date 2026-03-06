package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
@Data
@Accessors(chain = true)
@TableName("pxshitingrecordtable")
public class Pxshitingrecordtable extends Model<Pxshitingrecordtable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    /**
     * 到访ID，不一定有到访ID，网上约的试听课就没有到访ID
     */
    @TableField("daofangID")
    private Long daofangID;
    /**
     * 插班试听：1    ，一对一：2
     */
    @TableField("chabanOr1v1")
    private Integer chabanOr1v1;
    /**
     * 科目ID
     */
    @TableField("subjectID")
    private Long subjectID;
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
    @TableField("haveClassDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date haveClassDate;
    /**
     * 上课时间
     */
    @TableField("startLessonDateTime")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date startLessonDateTime;
    /**
     * 下课时间
     */
    @TableField("endLessonDateTime")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date endLessonDateTime;
    /**
     * 星期几
     */
    @TableField("weekN")
    private String weekN;
    /**
     * 试听老师ID，多个老师用逗号隔开
     */
    @TableField("stTeacher")
    private String stTeacher;
    /**
     * 学员ID
     */
    @TableField("yxStuID")
    private Long yxStuID;
    /**
     * 试听是否给老师算上课人数，0不算，1算
     */
    @TableField("isAddStuNumToTeacher")
    private Integer isAddStuNumToTeacher;
    /**
     * 学生试听的课时单价，0表示不收费；
     */
    @TableField("shitingPrice")
    private BigDecimal shitingPrice;
    /**
     * 上课教室
     */
    @TableField("classRoomID")
    private String classRoomID;
    /**
     * 满意度ID
     */
    @TableField("shiTingManyiduID")
    private Long shiTingManyiduID;
    /**
     * 满意度说明
     */
    @TableField("shiTingShuoming")
    private String shiTingShuoming;
    /**
     * 添加人
     */
    @TableField("addStaffID")
    private Long addStaffID;
    /**
     * 添加时间
     */
    @TableField("addTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;
    /**
     * 企业ID
     */
    @TableField("qiyeID")
    private Long qiyeID;
    /**
     * 排课ID
     */
    @TableField("paikeID")
    private Long paikeID;
    /**
     * 流水账ID，有试听费用的需要存流水
     */
    @TableField("liushuiID")
    private Long liushuiID;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Pxshitingrecordtable{" +
                ", id=" + id +
                ", daofangID=" + daofangID +
                ", chabanOr1v1=" + chabanOr1v1 +
                ", subjectID=" + subjectID +
                ", kechengID=" + kechengID +
                ", classID=" + classID +
                ", haveClassDate=" + haveClassDate +
                ", startLessonDateTime=" + startLessonDateTime +
                ", endLessonDateTime=" + endLessonDateTime +
                ", weekN=" + weekN +
                ", stTeacher=" + stTeacher +
                ", yxStuID=" + yxStuID +
                ", isAddStuNumToTeacher=" + isAddStuNumToTeacher +
                ", shitingPrice=" + shitingPrice +
                ", classRoomID=" + classRoomID +
                ", shiTingManyiduID=" + shiTingManyiduID +
                ", shiTingShuoming=" + shiTingShuoming +
                ", addStaffID=" + addStaffID +
                ", addTime=" + addTime +
                ", qiyeID=" + qiyeID +
                ", paikeID=" + paikeID +
                "}";
    }
}
