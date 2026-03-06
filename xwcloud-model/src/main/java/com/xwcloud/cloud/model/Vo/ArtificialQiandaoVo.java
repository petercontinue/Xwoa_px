package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
/**
 * 人工签到签退---排课签到签退
 */
public class ArtificialQiandaoVo {
    private String campusName;
    private String stuID;//学员ID
    private String zidingyiStuID;//自定义学员ID
    private String stuName;
    private String kechengName;
    private String className;
    private Date haveClassDate;
    private Time startLessonDateTime;
    private Time endLessonDateTime;
    private String paikeID;
    private String qdpd;//签到状态  真|假
    private String qtpd;//签退状态  真|假

}
