package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 不排课消课获取班级学员信息
 */
@Data
public class nopaikegetStuVo {
    private String id;
    private String bxID;
    private String classID;
    private String stuName;
    private String classTimeStyleName;
    private String yujing;
    private BigDecimal remainkeshi;
    private String jifeiStyleID;
    private String kaoqingStyle;
}
