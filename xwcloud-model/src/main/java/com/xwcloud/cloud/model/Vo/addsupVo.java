package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

//选择商品退费
@Data
public class addsupVo {
    private String id;
    private BigDecimal tuinum;
    private BigDecimal tuimoney;
}
