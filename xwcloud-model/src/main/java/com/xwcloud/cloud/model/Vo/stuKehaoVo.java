package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

@Data
public class stuKehaoVo {
    private String id;
    private String stuID;
    private String zidingyiID;//自定义学号
    private String stuName;
    private String stuGradeName;
    private String className;
    private String kechengName;
    private String banzhuren;
    private String buxiStyleName;
    private String teacherNames;
    private String zhujiao;
    private Date haveClassDate;
    private String weekN;
    private Time startLessonDateTime;
    private Time endLessonDateTime;
    private BigDecimal keshiNum;
    private BigDecimal kechengPrice;
    private String campusName;
    private String stukaoqing;
    private String shuoMing;
    private String adduser;
    private Date addtime;
}
