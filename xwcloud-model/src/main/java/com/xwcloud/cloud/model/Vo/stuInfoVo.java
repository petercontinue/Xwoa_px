package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class stuInfoVo {
    private String id;
    private String zidingyiStuID;
    private String stuName;
    private String stuSex;
    private String dengjiTime;
    private String dengjiTeachers;
    private String dengjiTeacherID;
    private String parentTelRelation;
    private String campusID;
    private String campusName;
    private String stuGradeID;
    private String buxiStateID;
    private String stuGradeName;
    private String oldSchoolName;
    private String oldSchoolTeacherName;
    private String stuTel;
    private String parentTel;
    private Date stubirth;
    private String stuXuexi;
    private int nianling;
    private String stuPhoto;
    private String laoshiyaoqiu;
    private String jijixing;
    private String ruxuechengji;
    private String xingge;
    private String zhuanjieshao;
    private BigDecimal remainXuefei;
    private BigDecimal remainChongzhi;
    private BigDecimal jifenNum;
}
