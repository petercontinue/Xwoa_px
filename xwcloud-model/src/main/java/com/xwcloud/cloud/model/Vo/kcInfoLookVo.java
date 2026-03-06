package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class kcInfoLookVo {
    private String id;
    private BigDecimal kechengprice;
    private BigDecimal zongjia;
    private String kechengName;
    private String buxiStyleName;
    private String subjectName;
    private BigDecimal remainkeshi;
    private String startDate;
    private String endDate;
    private String JifeiStyle;
    private BigDecimal zhekou;
}
