package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.util.Date;

@Data
//考级申请
public class kjSqVo {
    private String id;
    private String stuID;
    private String campusName;
    private String stuGradeName;
    private String stuName;
    private String subjectName;
    private String subjectID;
    private String sqjibie;
    private String shjibie;
    private String adduser;
    private String shenheuser;
    private Date addDate;
    private Date shenheDate;

}
