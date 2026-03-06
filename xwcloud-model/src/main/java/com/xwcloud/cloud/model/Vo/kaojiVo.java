package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.util.Date;

//考级
@Data
public class kaojiVo {
    private String id;
    private String campusName;
    private String stuGradeName;
    private String stuName;
    private String stuID;
    private String subjectName;
    private String subjectID;
    private String jibie;//级别
    private String kaojisq;//考级申请记录
    private String staffName;//添加人
    private Date addDateTime;//时间
}
