package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

@Data
/**
 * 消课打印
 */
public class xiaokedayingVo {
    private String id;
    private String campusName;
    private String stuID;
    private String zidingyiStuID;//自定义学号
    private String stuName;
    private String StuGradeID;
    private String stuGradeName;
    private String className;
    private String buxiStyleID;
    private String buxiStyleName;
    private String staffName;
    private String zhujiao;
    private Date haveClassDate;
    private Time startLessonDateTime;
    private Time endLessonDateTime;
    private String buxikechengID;
    private BigDecimal keshiNum;
    private BigDecimal remainkeshi;
    private String adduser;
    private Date addTime;
}
