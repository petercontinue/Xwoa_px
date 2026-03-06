package com.xwcloud.cloud.model.Form;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class addscordersForm {
    private BigDecimal payMoney;
    private BigDecimal payJifen;
    private long couponsID;
    private Integer type;
    private long shouhuoID;
    private String beizhu;
    private String orderGoods;
    private Integer huodongID;
}
