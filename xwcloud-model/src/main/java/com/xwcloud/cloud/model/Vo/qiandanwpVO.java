package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class qiandanwpVO {
    private Long id;
    private String name;
    private BigDecimal salePrice;
    private BigDecimal StockNum;
}
