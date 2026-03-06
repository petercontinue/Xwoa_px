package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExportReKeshiVo {
    private String campusName;
    private String stuName;
    private String subjectName;
    private String kechengName;
    private String className;
    private String skTea;//上课老师
    private BigDecimal kechengprice;
    private BigDecimal remainkeshi;
    private String isShow;
}
