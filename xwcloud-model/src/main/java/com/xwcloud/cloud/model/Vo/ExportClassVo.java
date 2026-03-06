package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.util.Date;

//导出学员班级
@Data
public class ExportClassVo {
    private String id;
    private String isShow;
    private String campusName;
    private String className;
    private String is1v1Class;
    private String mingdan;
    private String mingdanstu;
    private String maxStuNum;
    private String jingbanren;
    private Date addTime;
    private String Kc1v1Name;
}
