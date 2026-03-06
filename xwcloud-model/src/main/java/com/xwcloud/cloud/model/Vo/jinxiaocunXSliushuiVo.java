package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class jinxiaocunXSliushuiVo {
    private Long campusID;
    private String campusName;
    private BigDecimal BuyPrice;
    private BigDecimal BuySum;
    private Long SuppliesID;
    private String SuppliesName;
    private String SuppliesTiaoma;
    private String CreatDatetime;
    private Long CreatStaffID;
    private String OrderNo;
    private Long staffID;
    private String staffName;
    private BigDecimal OrderMoney;
}
