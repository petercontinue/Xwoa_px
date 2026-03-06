package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

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
 * @since 2021-05-25
 */
@Data
@Accessors(chain = true)
@TableName("wsc_huodong_others")
public class WscHuodongOthers extends Model<WscHuodongOthers> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    /**
     * 活动ID
     */
    @TableField("huodongID")
    private Integer huodongID;
    /**
     * 活动标题
     */
    @TableField("huodongtitle")
    private String huodongtitle;
    /**
     * 活动图片
     */
    @TableField("huodongImg")
    private String huodongImg;
    /**
     * 活动说明
     */
    @TableField("huodongshuoming")
    private String huodongshuoming;
    /**
     * 活动开始时间
     */
    @TableField("startDatetime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDatetime;
    /**
     * 活动结束时间
     */
    @TableField("endDatetime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDatetime;

    @TableField("liulantimes")
    private int liulantimes;

    @TableField("fenxiangtimes")
    private int fenxiangtimes;
    /**
     * 企业ID
     */
    @TableField("qiyeID")
    private Long qiyeID;

    @TableField("huodongState")
    private int huodongState;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "WscHuodongOthers{" +
                ", id=" + id +
                ", huodongID=" + huodongID +
                ", huodongtitle=" + huodongtitle +
                ", huodongImg=" + huodongImg +
                ", huodongshuoming=" + huodongshuoming +
                ", startDatetime=" + startDatetime +
                ", endDatetime=" + endDatetime +
                ",huodongState=" + huodongState +
                ",liulantimes=" + liulantimes +
                ",fenxiangtimes=" + fenxiangtimes +
                ", qiyeID=" + qiyeID +
                "}";
    }
}
