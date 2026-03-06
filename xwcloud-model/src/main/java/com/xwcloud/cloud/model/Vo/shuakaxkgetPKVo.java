package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
/**
 * 刷卡消课获取排课
 */
public class shuakaxkgetPKVo {
    private Long id;
    private Time startLessonDateTime;
    private Time endLessonDateTime;
    private String weekN;
    private Long classID;
    private Date haveClassDate;
    private Long buxiStyleID;
    private Long classTimeStyleID;
    private String classTimeStyleName;
    private Long buxiID;
    private String kechengprice;
    private String teacherIDs;
    private String teacherNames;
    private Long kechengID;
    private Long classRoomID;
    private String classRoomName;
}
