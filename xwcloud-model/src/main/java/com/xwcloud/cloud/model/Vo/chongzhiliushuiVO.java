package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class chongzhiliushuiVO {
    private long id;
    private String zidingyiStuID;
    private String campusName;
    private String stuName;
    private BigDecimal shijiChongzhiMoney;
    private BigDecimal songMoney;
    private BigDecimal shideTotalMoney;
    private String staffName;
    private String addTime;
    private String stuGradeName;
    private String paymoneystyle;
}
