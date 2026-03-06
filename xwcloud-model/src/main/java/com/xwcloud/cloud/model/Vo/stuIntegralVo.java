package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

//学员积分
@Data
public class stuIntegralVo {
    private String id;//积分变动Id
    private String stuID;//学员ID
    private String zidingyiStuID;//自定义学号
    private String banzhuren;
    private String stuName;
    private String campusName;
    private String stuGradeName;
    private BigDecimal oldIntegral;
    private BigDecimal integral;
    private Date createTime;
    private String jingbanStaff;
    private String type;
    private String remark;
}
