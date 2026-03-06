package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class weikuanPayVo {
    private String paystyle;
    private String name;
    private BigDecimal paymoney;
}
