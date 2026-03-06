package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class kehaoInfoVo {
    private String id;
    private String subjectName;
    private String kechengName;
    private BigDecimal kechengPrice;
    private BigDecimal keshiNum;
    private BigDecimal Payxuefei;
}
