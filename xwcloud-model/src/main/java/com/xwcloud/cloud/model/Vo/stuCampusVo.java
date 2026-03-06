package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

//学员档案
@Data
public class stuCampusVo {
    private String stuID;//ID
    private String zidingyiStuID;
    private String campusName;
    private String stuName;
    private String parentTel;
    private String banzhuren;
    private String stuGradeName;
    private String buxiStateID;
    private int ZongKaoQing;
    private int ZhangChangkaoqin;
    private int Kuangke;
    private int qdSum;
    private int ShangkeCount;
    private BigDecimal jifenNum;
    private BigDecimal daijingquan;
    private int kehaoSum;
    private int ManyiduCount;
    private int huifangCount;
}
