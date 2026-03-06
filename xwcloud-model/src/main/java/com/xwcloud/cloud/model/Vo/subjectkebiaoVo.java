package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
/**
 * 科目课表
 */
public class subjectkebiaoVo {
    private String id;
    private Date haveClassDate;
    private Time startLessonDateTime;
    private Time endLessonDateTime;
    private String weekN;
    private String classID;
    private String className;
    private String kechengID;
    private String kechengName;
    private String kechengContent;
    private String classRoomID;
    private String classRoomName;
    private int MaxStuNum;
    private String teacherNames;
    private String teacherIDs;
    private String tags;
    private String subjectID;
    private String subjectName;
    private String bgColor;
    private int xkstuSum;//上课人数
    private String xkstuName;//选课学员
}
