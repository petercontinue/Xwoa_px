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
 * @since 2021-04-08
 */
@Data
@Accessors(chain = true)
@TableName("pxstafftable")
public class Pxstafftable extends Model<Pxstafftable> {

    private static final long serialVersionUID = 1L;

    /**
     * 员工ID
     */
    @TableId("id")
    private Long id;
    /**
     * 员工姓名
     */
    @TableField("staffName")
    private String staffName;
    /**
     * 员工手机号，即登录账号
     */
    @TableField("staffTel")
    private String staffTel;
    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 账号角色，0普通账号，1管理员，2超级管理员，默认值是0
      */
    @TableField("role")
    private int role;
    /**
     * 员工性别
     */
    @TableField("staffSex")
    private String staffSex;
    /**
     * 员工生日
     */
    @TableField("staffBirthday")
    private String staffBirthday;
    /**
     * 校区ID
     */
    @TableField("campusID")
    private Long campusID;
    /**
     * 岗位ID
     */
    @TableField("staffPostID")
    private Long staffPostID;
    /**
     * 员工状态1正常，2冻结，3离职，默认值1
     */
    @TableField("staffState")
    private Integer staffState;
    /**
     * 头像图片
     */
    @TableField("photo")
    private String photo;
    @TableField("QQ")
    private String qq;
    @TableField("email")
    private String email;
    /**
     * 微信
     */
    @TableField("wx")
    private String wx;
    /**
     * 抖音
     */
    @TableField("douyin")
    private String douyin;
    /**
     * 入职日期
     */
    @TableField("joinTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date joinTime;
    @TableField("shuoMing")
    private String shuoMing;

    @TableField("jiaoxueJingyan")
    private String jiaoxueJingyan;
    /**
     * 微信openid
     */
    @TableField("openid")
    private String openid;
    /**
     * 微信unionid
     */
    @TableField("unionid")
    private String unionid;
    /**
     * 手机识别码，即手机设备的UUID
     */
    @TableField("phoneMac")
    private String phoneMac;
    @TableField("qiyeID")
    private Long qiyeID;

    @TableField("showInApp")
    private Boolean showInApp;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Pxstafftable{" +
                ", id=" + id +
                ", staffName=" + staffName +
                ", staffTel=" + staffTel +
                ", password=" + password +
                ", role=" + role +
                ", staffSex=" + staffSex +
                ", staffBirthday=" + staffBirthday +
                ", campusID=" + campusID +
                ", staffPostID=" + staffPostID +
                ", staffState=" + staffState +
                ", photo=" + photo +
                ", qq=" + qq +
                ", email=" + email +
                ", wx=" + wx +
                ", douyin=" + douyin +
                ", joinTime=" + joinTime +
                ", shuoMing=" + shuoMing +
                ", jiaoxueJingyan=" + jiaoxueJingyan +
                ", openid=" + openid +
                ", unionid=" + unionid +
                ", phoneMac=" + phoneMac +
                ", qiyeID=" + qiyeID +
                ", showInApp="+showInApp+
                "}";
    }
}
