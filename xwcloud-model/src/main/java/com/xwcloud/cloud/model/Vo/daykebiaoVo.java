package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
/**
 * 天课表
 */
public class daykebiaoVo {
    private String id;//paikeID
    private Time startLessonDateTime;
    private Time endLessonDateTime;
    private String weekN;
    private String classID;
    private String kechengID;
    private String kechengName;
    private Date haveClassDate;
    private String xiaoqu;
    private String className;
    private String campusID;
    private String campusName;
    private String classRoomName;
    private String classTimeStyleName;
    private String stuName;
    private int yshangcount;
    private int havekaoqingStuNum;
    private String teacherIDs;
    private String teacherNames;
    private String dakaoqin;
}
