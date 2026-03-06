package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
/**
 * 学员缴费详情qiandanStuShow
 */
public class qiandanstuVo {
    private String stuID;
    private String zidingyiStuID;
    private String campusName;
    private String stuGradeName;
    private String stuName;
    private BigDecimal shiMoney;
    private BigDecimal daijiMoney;
    private String moneystyleName;
    private String moneyStyle;
    private Date qiandandate;
    private String jingbanren;
}
