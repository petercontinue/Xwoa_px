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
 * @since 2021-04-01
 */
@Data
@Accessors(chain = true)
@TableName("whd_h5_huodongfabu")
public class WhdH5Huodongfabu extends Model<WhdH5Huodongfabu> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    /**
     * 模板ID
     */
    @TableField("mobanID")
    private Long mobanID;
    /**
     * 模板类型ID，对应wsc_huodong表的id
     */
    @TableField("mbTypeID")
    private Long mbTypeID;
    /**
     * 咨询二维码图片URL
     */
    @TableField("zixunEwm")
    private String zixunEwm;
    /**
     * 标题名称
     */
    @TableField("huodongTitle")
    private String huodongTitle;
    /**
     * 活动主图
     */
    @TableField("huodongImage")
    private String huodongImage;
    /**
     * 机构名称
     */
    @TableField("jigouName")
    private String jigouName;
    /**
     * 活动开始日期
     */
    @TableField("huodongStartDateTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date huodongStartDateTime;
    /**
     * 活动结束日期
     */
    @TableField("huodongEndDateTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date huodongEndDateTime;

    @TableField("maxStuNum")
    private Integer maxStuNum;
    /**
     * 机构电话
     */
    @TableField("jigouTel")
    private String jigouTel;
    /**
     * 背景音乐URL
     */
    @TableField("musicID")
    private Integer musicID;
    /**
     * 是否有学员性别
     */
    @TableField("haveStuSex")
    private Boolean haveStuSex;
    /**
     * 是否有学员年龄
     */
    @TableField("haveAge")
    private Boolean haveAge;
    /**
     * 是否有学员生日
     */
    @TableField("haveBirthday")
    private Boolean haveBirthday;
    /**
     * 是否有就读学校
     */
    @TableField("haveSchool")
    private Boolean haveSchool;
    /**
     * 是否有学员年级
     */
    @TableField("haveGrade")
    private Boolean haveGrade;
    /**
     * 是否有意向课程
     */
    @TableField("haveYxkecheng")
    private Boolean haveYxkecheng;
    /**
     * 活动说明
     */
    @TableField("huodongShuoMing")
    private String huodongShuoMing;
    /**
     * 查看次数，可以设置初始值
     */
    @TableField("lookNum")
    private Integer lookNum;
    /**
     * 已报名人数，可以设置初始值
     */
    @TableField("baomingStuNum")
    private Integer baomingStuNum;

    @TableField("fenxiangtimes")
    private Integer fenxiangtimes;
    /**
     * 是否已发布,默认未发布false,0
     */
    @TableField("isfabu")
    private Boolean isfabu;
    /**
     * 发布时间
     */
    @TableField("fabuTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date fabuTime;
    /**
     * 添加时间
     */
    @TableField("addTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date addTime;
    /**
     * 添加人
     */
    @TableField("addUser")
    private Long addUser;
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
        return "WhdH5Huodongfabu{" +
                ", id=" + id +
                ", mobanID=" + mobanID +
                ", mbTypeID=" + mbTypeID +
                ", zixunEwm=" + zixunEwm +
                ", huodongTitle=" + huodongTitle +
                ", huodongImage=" + huodongImage +
                ", jigouName=" + jigouName +
                ",huodongStartDateTime="+huodongStartDateTime+
                ", huodongEndDateTime=" + huodongEndDateTime +
                ", jigouTel=" + jigouTel +
                ", musicID=" + musicID +
                ", haveStuSex=" + haveStuSex +
                ", haveAge=" + haveAge +
                ", haveBirthday=" + haveBirthday +
                ", haveSchool=" + haveSchool +
                ", haveGrade=" + haveGrade +
                ", haveYxkecheng=" + haveYxkecheng +
                ", huodongShuoMing=" + huodongShuoMing +
                ", lookNum=" + lookNum +
                ", baomingStuNum=" + baomingStuNum +
                ",fenxiangtimes="+fenxiangtimes+
                ", isfabu=" + isfabu +
                ", fabuTime=" + fabuTime +
                ", addTime=" + addTime +
                ", addUser=" + addUser +
                ", qiyeID=" + qiyeID +
                "}";
    }
}
