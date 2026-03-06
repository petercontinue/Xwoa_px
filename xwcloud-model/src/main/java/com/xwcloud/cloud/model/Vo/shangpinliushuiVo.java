package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class shangpinliushuiVo {
    private Long campusID;
    private String campusName;
    private String stuID;
    private String zidingyiStuID;
    private String stuName;
    private String stuGradeName;
    private String Name;
    private BigDecimal BuySum;
    private BigDecimal BuyPrice;
    private BigDecimal SumMoney;
    private String qiandandate;
}
