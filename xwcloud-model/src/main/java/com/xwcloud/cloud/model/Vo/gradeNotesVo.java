package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.util.Date;

//调级记录
@Data
public class gradeNotesVo {
    private String id;
    private String campusName;
    private String stuID;
    private String zidingyiStuID;
    private String stuName;
    private String oldGrade;
    private String newGrade;
    private String jingbanren;
    private Date addDate;
}
