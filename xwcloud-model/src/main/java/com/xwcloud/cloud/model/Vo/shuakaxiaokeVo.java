package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

@Data
/**
 * 刷卡消课
 */
public class shuakaxiaokeVo {
    private String id;
    private String stuID;
    private String zidingyiStuID;
    private String cardNumber;
    private String campusName;
    private String stuName;
    private String paikeID;
    private String kechengName;
    private String teacherNames;
    private String teacherIDs;
    private String buxiStyleName;
    private String banzhuren;
    private BigDecimal keshi;
    private String qiandaoOrqiantui;
    private Date haveClassDate;
    private Time startLessonDateTime;
    private Time endLessonDateTime;
    private Date qianDatetime;
}
