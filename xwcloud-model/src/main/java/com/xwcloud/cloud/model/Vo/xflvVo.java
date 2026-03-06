package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class xflvVo {
    private String id;
    private String staffName;
    private String subjectID;
    private String subjectName;
    private String campusID;
    private String campusName;
    private int stunum;
    private BigDecimal xfnum;
}
