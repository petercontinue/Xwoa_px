package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class qianDanInFoVo {
    private Long id;
    private Long campusID;
    private String campusName;
    private String zidingyiStuID;
    private String stuName;
    private String stuGradeName;
    private BigDecimal shishouTotalMoney;
    private BigDecimal HetongMoney;
    private BigDecimal youhuijine;
    private String beizhu;
    private String zhuanjieshaoID;
    private BigDecimal daijinquanMoney;
    private BigDecimal SumotherMoney;
    private BigDecimal kechengMoney;
    private String jinbanStaffName;
    private String yejistaff;
    private Integer moneyStyle;
    private String parentTel;
    private String qiandandate;
    private Integer qiandanstaffcount;
    private String yejistaffName;
    private BigDecimal yingshouMoney;
    private BigDecimal weikuan;
    private BigDecimal qiandansuppliesMoney;
    private String hetong;
    private String shichangtea;
}

