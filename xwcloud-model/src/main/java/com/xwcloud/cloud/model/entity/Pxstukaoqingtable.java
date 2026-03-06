package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @since 2020-11-23
 */
@Data
@Accessors(chain = true)
@TableName("pxstukaoqingtable")
public class Pxstukaoqingtable extends Model<Pxstukaoqingtable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    @TableField("stuID")
    private Long stuid;
    @TableField("kechengID")
    private Long kechengid;
    @TableField("classID")
    private Long classid;
    @TableField("teacherIDs")
    private String teacherids;
    @TableField("teacherNames")
    private String teachernames;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("haveclassdate")
    private Date haveclassdate;
    @DateTimeFormat(pattern = "HH:mm:ss")
    @TableField("startClassDateTime")
    private Time startclassdatetime;
    @DateTimeFormat(pattern = "HH:mm:ss")
    @TableField("endClassDateTime")
    private Time endclassdatetime;
    /**
     * 1正常、2请假、3旷课、4迟到、5早退、6补课
     */
    @TableField("kaoqingStyle")
    private Integer kaoqingstyle;
    @TableField("kaoqingBeizhu")
    private String kaoqingbeizhu;
    @TableField("qiyeID")
    private Long qiyeid;


    @Override
    protected Serializable pkVal() {
        return this.haveclassdate;
    }

    @Override
    public String toString() {
        return "Pxstukaoqingtable{" +
                ", id=" + id +
                ", stuid=" + stuid +
                ", kechengid=" + kechengid +
                ", classid=" + classid +
                ", teacherids=" + teacherids +
                ", teachernames=" + teachernames +
                ", haveclassdate=" + haveclassdate +
                ", startclassdatetime=" + startclassdatetime +
                ", endclassdatetime=" + endclassdatetime +
                ", kaoqingstyle=" + kaoqingstyle +
                ", kaoqingbeizhu=" + kaoqingbeizhu +
                ", qiyeid=" + qiyeid +
                "}";
    }
}
