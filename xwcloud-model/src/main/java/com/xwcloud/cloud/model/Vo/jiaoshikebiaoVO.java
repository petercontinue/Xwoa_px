package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
/**
 * 教室课表
 */
public class jiaoshikebiaoVO {
    private String id;
    private Date haveClassDate;
    private Time startLessonDateTime;
    private Time endLessonDateTime;
    private String weekN;
    private String classID;
    private String className;
    private String classRoomName;
    private String kechengName;
    private String subjectName;
    private String teacherNames;
    private String kechengContent;
    private String bgColor;
    private String xkstuName;//选课学员
}
