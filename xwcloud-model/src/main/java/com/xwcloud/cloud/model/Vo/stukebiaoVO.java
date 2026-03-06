package com.xwcloud.cloud.model.Vo;

import lombok.Data;

@Data
/**
 * 学员课表
 */
public class stukebiaoVO {
    private String id;
    private String campusID;
    private String haveClassDate;
    private String startLessonDateTime;
    private String endLessonDateTime;
    private String weekN;
    private String classID;
    private String kechengName;
    private String subjectName;
    private String className;
    private String kechengContent;
    private String teacherNames;
    private String bgColor;
    private String classRoomName;
    private String stuID;
    private String stuName;
    private String xkstuName;//选课学员
}
