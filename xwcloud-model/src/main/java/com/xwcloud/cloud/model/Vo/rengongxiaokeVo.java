package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
/**
 * 人工消课
 */
public class rengongxiaokeVo {
    private Long id;//排课id做ID
    private Time startLessonDateTime;
    private Time endLessonDateTime;
    private Date haveClassDate;
    private String kechengID;
    private String weekN;
    private String classID;
    private String campusName;
    private String className;
    private String classRoomName;
    private String classTimeStyleName;
    private int is1v1Class;

    private String teacherIDs;
    private String teacherNames;
    private String stuName;//上课学员名单
    private int yshangcount;//应该上课人数
    private int havekaoqingStuNum;//打考勤人数
    private String dakaoqin;//排课的考勤状态
}
