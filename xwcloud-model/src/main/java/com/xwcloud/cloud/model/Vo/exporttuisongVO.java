package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.util.Date;

@Data
public class exporttuisongVO {
    private String campusName;
    private String zidingyiStuID;
    private String stuName;
    private String staffName;
    private String tuisongType;
    private String note;
    private Date addTime;
}
