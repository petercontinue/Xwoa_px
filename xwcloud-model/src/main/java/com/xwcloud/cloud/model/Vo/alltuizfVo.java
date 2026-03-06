package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class alltuizfVo {
    private String id;
    private String otherMoneyName;
    private BigDecimal zongMoney;
    private BigDecimal tuiMoney;
    private String qianInfoTabID;
}
