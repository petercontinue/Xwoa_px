package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

//剩余课时详情
@Data
public class RemainkeshiDetailsVo {
    private String id;
    private String stuID;
    private String zidingyiStuID;//自定义学号
    private String stuName;
    private String kechengName;
    private String campusName;
    private String className;
    private String subjectName;
    private String JifeiStyleName;//计费方式
    private BigDecimal allremainkeshi;//剩余课时
    private BigDecimal buyN;//购买课时数
    private BigDecimal beGiven;//得到的赠送课时
    private BigDecimal forwarded;//获取的转送课时
    private BigDecimal give;//送出的
    private BigDecimal userd;//上了的
    private BigDecimal refund;//退费的
    private String yujing;//课时预警
}
