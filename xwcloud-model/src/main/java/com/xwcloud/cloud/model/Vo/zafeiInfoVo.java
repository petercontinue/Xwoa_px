package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class zafeiInfoVo {
    private String id;
    private BigDecimal zongMoney;
    private int type;
    private String otherMoneyName;
}
