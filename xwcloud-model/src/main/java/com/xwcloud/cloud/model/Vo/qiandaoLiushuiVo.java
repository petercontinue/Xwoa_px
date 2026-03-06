package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
/**
 * 签到流水
 */
public class qiandaoLiushuiVo {
    private String id;
    private String stuID;
    private String zidingyiStuID;
    private String stuName;
    private String qiandao;//签到还是签退
    private String qianStyle;//签到类型
    private String TStype;//推送状态
    private Date qianDatetime;
    private String staffName;
    private Date haveClassDate;
    private Time startLessonDateTime;
    private Time endLessonDateTime;
}
