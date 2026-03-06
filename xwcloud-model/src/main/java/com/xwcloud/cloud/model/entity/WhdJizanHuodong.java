package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-24
 */
@Data
@Accessors(chain = true)
@TableName("whd_jizan_huodong")
public class WhdJizanHuodong extends Model<WhdJizanHuodong> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    /**
     * 积赞活动名称
     */
    @TableField("jizanHuodongName")
    private String jizanHuodongName;
    /**
     * 积赞活动主图
     */
    @TableField("jizanLogoUrl")
    private String jizanLogoUrl;
    /**
     * 活动说明（活动规则，奖品等）
     */
    @TableField("jizanShuoming")
    private String jizanShuoming;
    /**
     * 活动开始时间
     */
    @TableField("startTime")
    private Date startTime;
    /**
     * 活动结束时间
     */
    @TableField("endTime")
    private Date endTime;
    /**
     * 是否启用，1启用，2不启用
     */
    @TableField("isOpen")
    private Integer isOpen;
    /**
     * 添加时间
     */
    @TableField("addTime")
    private Date addTime;
    /**
     * 添加人
     */
    @TableField("addUser")
    private String addUser;

    @TableField("liulantimes")
    private int liulantimes;

    @TableField("fenxiangtimes")
    private int fenxiangtimes;

    @TableField("qiyeID")
    private Long qiyeID;

   //private int canyucount;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "WhdJizanHuodong{" +
                ", id=" + id +
                ", jizanHuodongName=" + jizanHuodongName +
                ", jizanLogoUrl=" + jizanLogoUrl +
                ", jizanShuoming=" + jizanShuoming +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", isOpen=" + isOpen +
                ", addTime=" + addTime +
                ", addUser=" + addUser +
                ",liulantimes="+liulantimes+
                ",fenxiangtimes="+fenxiangtimes+
                ", qiyeID=" + qiyeID +
                "}";
    }
}
