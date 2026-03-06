package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class exportclassstuVo {
    private String campusName;
    private String classID;
    private String className;
    private String kechengName;
    private String stuID;
    private String stuName;
    private String parentTel;
    private BigDecimal remainXuefei;
    private BigDecimal remainkeshi;
    private BigDecimal yingshou;
    private BigDecimal shishou;
    private BigDecimal jiaofeiNum;
    private String yejiren;
}
