package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

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
@TableName("pxpaiketable")
public class Pxpaiketable extends Model<Pxpaiketable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    /**
     * 上课时间
     */
    @DateTimeFormat(pattern = "hh:mm:ss")
    @TableField("startLessonDateTime")
    private Time startLessonDateTime;
    /**
     * 下课时间
     */
    @DateTimeFormat(pattern = "hh:mm:ss")
    @TableField("endLessonDateTime")
    private Time endLessonDateTime;
    /**
     * 上课日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("haveClassDate")
    private Date haveClassDate;
    /**
     * 排课老师ID，多个老师逗号隔开
     */
    @TableField("teacherIDs")
    private String teacherIDs;
    /**
     * 助教teacherID，每次排课可增加一个助教
     */
    @TableField("zhujiaoID")
    private Long zhujiaoID;
    /**
     * 排课老师姓名，多个老师逗号隔开
     */
    @TableField("teacherNames")
    private String teacherNames;
    /**
     * 班级ID
     */
    @TableField("classID")
    private Long classID;
    /**
     * 教室ID
     */
    @TableField("classRoomID")
    private Long classRoomID;
    /**
     * 星期几
     */
    @TableField("weekN")
    private String weekN;
    /**
     * 最大学员人数
     */
    @TableField("MaxStuNum")
    private Integer maxStuNum;
    /**
     * 课程ID
     */
    @TableField("kechengID")
    private Long kechengID;
    /**
     * 课程内容
     */
    @TableField("kechengContent")
    private String kechengContent;
    /**
     * 是否已完成打考勤,true已完成，false未完成
     */
    @TableField("dakaoqin")
    private Boolean dakaoqin;
    /**
     * 排课的批次，相同tags表示是同一个批次
     */
    @TableField("tags")
    private String tags;
    /**
     * 允许开课前几小时内请假
     */
    @TableField("canqingjiaBeforeHours")
    private Integer canqingjiaBeforeHours;
    /**
     * 是否推送上课提醒，1：推送；2：不推送，默认1
     */
    @TableField("istuisongTixingMsg")
    private Integer istuisongTixingMsg;
    /**
     * 刷卡或刷脸消课时间段，1课前，2课中，3课后，默认1
     */
    @TableField("shuakaTimeArea")
    private Integer shuakaTimeArea;

    @TableField("qiyeID")
    private Long qiyeID;

    @TableField("huodongImg")
    private String huodongImg;

    @TableField("huodongTitle")
    private String huodongTitle;


    @TableField("huodongshuoming")
    private String huodongshuoming;

    @TableField("liulangtime")
    private Integer liulangtime;

    @TableField("fenxiangtime")
    private Integer fenxiangtime;

    @TableField("zixunphone")
    private String zixunphone;

    @TableField("shitingprice")
    private BigDecimal shitingprice;

    @TableField("paikeType")
    private Integer paikeType;


    public void setTongke1v1KechengID(long l) {
    }
}
