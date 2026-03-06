package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author xiaowei
 * @since 2021-03-01
 */
@Data
@Accessors(chain = true)
@TableName("pxtuisongtable")
public class Pxtuisongtable extends Model<Pxtuisongtable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    /**
     * 学员ID
     */
    @TableField("stuID")
    private Long stuID;

    @TableField("staffID")
    private Long staffID;
    /**
     * 推送文字内容
     */
    @TableField("note")
    private String note;
    /**
     * 推送类型，关联pxtuisongtypetable表的ID
     */
    @TableField("kechengID")
    private Long kechengID;

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
    private Date haveclassDate;

    @TableField("tuisongTypeName")
    private Long tuisongTypeName;
    @TableField("addStaffID")
    private Long addStaffID;
    @TableField("addTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;
    /**
     * NULL.1.学生     2.员工
     */
    @TableField("role")
    private Integer role;
    /**
     * 0：微信未推送成功。NUL（老数据）或1：微信推送成功。
     */
    @TableField("wxstate")
    private Integer wxstate;
    /**
     * app阅读状态 NULL（老数据默认已读）。0：未读。1：已读
     */
    @TableField("appread")
    private Integer appread;
    @TableField("qiyeID")
    private Long qiyeID;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Pxtuisongtable{" +
                ", id=" + id +
                ", stuID=" + stuID +
                ",staffID=" + staffID +
                ", note=" + note +
                ",kechengID=" + kechengID +
                ",haveclassDate=" + haveclassDate +
                ",startLessonDateTime=" + startLessonDateTime +
                ",endLessonDateTime=" + endLessonDateTime +
                ", tuisongTypeName=" + tuisongTypeName +
                ", addStaffID=" + addStaffID +
                ", addTime=" + addTime +
                ", role=" + role +
                ", wxstate=" + wxstate +
                ", appread=" + appread +
                ", qiyeID=" + qiyeID +
                "}";
    }
}
