package com.xwcloud.cloud.model.Sso;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashMap;

@Data
public class LoginUser {
    /**
     * 员工ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long staffID;
    /**
     * 员工姓名
     */
    private String staffName;
    /**
     * 员工手机号，即登录账号
     */
    private String staffTel;
    /**
     * 员工性别
     */
    private String staffSex;
    /**
     * 员工生日
     */
    private String staffBirthday;
    /**
     * 校区ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long campusID;

    /**
     * 岗位ID
     */
    private Long staffPostID;
    /**
     * 员工状态1正常，2冻结，3离职，默认值1
     */
    private Integer staffState;
    /**
     * 头像图片
     */
    private String photo;
    private String qq;

    private String email;

    /**
     * 微信
     */
    private String wx;
    /**
     * 抖音
     */

    private String douyin;
    /**
     * 入职日期
     */

    private Date joinTime;

    private String shuoMing;
    /**
     * 微信openid
     */

    private String openid;
    /**
     * 微信unionid
     */

    private String unionid;
    /**
     * 手机识别码，即手机设备的UUID
     */

    private String phoneMac;

    private long qiyeID;
    /**
     * 登录IP,key=登录设备类型(web,weixin,app),value=登录IP
     * 是否允许同一个账号在多个设备登录,在数据字典设置,默认允许
     */
    private HashMap<String,Object> loginIP;
    /**
     * 1.电脑登录,2.手机微信登录,3.手机APP登录
     */
    private Integer loginDev;

    private Long wscUserID;

    private Integer loginType;

}
