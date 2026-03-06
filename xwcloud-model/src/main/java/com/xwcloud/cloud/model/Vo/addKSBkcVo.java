package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class addKSBkcVo {
    private String id;
    private String name;
    private BigDecimal buyZonjia;
    private BigDecimal kechengOriginalPrice;
    private BigDecimal kechengprice;
    private BigDecimal keshiNum;
}
