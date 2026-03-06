package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
/**
 * tiankc
 */
public class tiankcVo {
    private Long id;
    private Time startLessonDateTime;
    private Time endLessonDateTime;
    private String weekN;
    private Long classID;
    private Long kechengID;
    private Date haveClassDate;
    private String xiaoqu;
    private String className;
    private String campusID;
    private String classRoomName;
    private String classTimeStyleName;
    private String stuName;
    private String staffID;
    private String staffName;
    private int yshangcount;
    private int havekaoqingStuNum;
    private String dakaoqin;
}

