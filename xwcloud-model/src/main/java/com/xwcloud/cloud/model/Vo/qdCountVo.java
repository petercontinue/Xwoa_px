package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class qdCountVo {
    private String stuID;
    private String stuName;
    private String campusName;
    private String parentTel;
    private String stuGradeName;
    private BigDecimal HetongMoney;
    private BigDecimal shishouTotalMoney;
    private BigDecimal SumotherMoney;
    private BigDecimal daijinquanMoney;
    private BigDecimal kechengMoney;
    private BigDecimal weikuan;
    private BigDecimal youhuijine;

}
