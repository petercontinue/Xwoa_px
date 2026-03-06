package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

//剩余课时
@Data
public class stuRemainVo {
//    private String id;
    private String stuID;
    private String stuName;
    private String campusName;
    private String stuGradeName;
    private String zidingyiStuID;//自定义学号
    private String banzhuren;
    private int buxiStateID;
    private BigDecimal remainXuefei;
    private BigDecimal weikuan;
    private String shuoming;

}
