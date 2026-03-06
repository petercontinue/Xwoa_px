package com.xwcloud.cloud.model.Vo;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class stuqiandanInfoVo {
    private String stuID;
    private String stuName;
    private String remainXuefei;
    private String qdID;//签单ID
    private BigDecimal shishouTotalMoney;
    private int moneyStyle;
    private String qianDanStaffID;
    private String PayMoneyStyle;
    private String qiandandate;
    private String staffNames;
    private String campusName;
    private BigDecimal kechengMoney;
    private BigDecimal daijinquan;
    private BigDecimal youhuijine;//优惠金额
    private String youhuiID;//优惠ID
}
