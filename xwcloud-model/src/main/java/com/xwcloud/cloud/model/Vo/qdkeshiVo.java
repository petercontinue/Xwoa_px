package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class qdkeshiVo {
    private String stuID;
    private String stuName;
    private String kechengName;
    private String buxiStyleName;
    private String stuGradeName;
    private String subjectName;
    private BigDecimal buykeshiNum;
    private BigDecimal originalprice;
    private BigDecimal kechengprice;
    private String keshichangeType;
    private String qiandandate;
    private String yejistaffName;
    private String campusName;
}
