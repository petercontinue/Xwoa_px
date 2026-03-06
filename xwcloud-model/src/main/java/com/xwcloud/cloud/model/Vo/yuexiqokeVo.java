package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
/**
 * 余额消课
 */
public class yuexiqokeVo {
    private String id;
    private String campusName;
    private String stuID;
    private String zidingyiStuID;//自定义学号
    private String stuName;
    private String kechengName;//课程Name
    private String cardNum;//卡号
    private String beizhu;
    private BigDecimal chongzhiPayMoney;//支付金额
    private String addUser;
    private Date addTime;
}
