package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
/**
 * 教师课表
 */
public class teacherkebiaoVo {
    private String id;
    private Date haveClassDate;
    private Time startLessonDateTime;
    private Time endLessonDateTime;
    private String weekN;
    private String classID;
    private String className;
    private String classRoomID;
    private String classRoomName;
    private String kechengID;
    private String kechengContent;
    private int MaxStuNum;
    private String teacherNames;
    private String teacherIDs;
    private String tags;
    private String bgColor;
    private int ysSum;
    private String subjectID;
    private String subjectName;
    private String xkstuName;//选课学员
}
