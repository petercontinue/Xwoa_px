package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

@Data
/**
 * 排课表
 */
public class paikeShowVo {
    private String id;
    private Date haveClassDate;
    private Time startLessonDateTime;
    private Time endLessonDateTime;
    private String weekN;
    private String kechengContent;
    private String subjectName;
    private String className;
    private long classID;
    private String kechengID;
    private String kechengName;
    private String classRoomID;
    private String classRoomName;
    private int MaxStuNum;
    private String teacherIDs;
    private String teacherNames;
    private String tags;
    private String bgColor;
    private String xkstuName;
    private int xkNum;
    private String huodongImg;
    private String huodongTitle;
    private Integer liulangtime;
    private Integer fenxiangtime;
    private String zixunphone;
    private BigDecimal shitingprice;
    private String kechengImg;
    private String kechengInfo;
    private String huodongshuoming;
}
