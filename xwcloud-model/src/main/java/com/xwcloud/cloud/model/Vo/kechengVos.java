package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class kechengVos {
    private String id;
    private String name;
    private String type;
    private BigDecimal remainkeshi;
    private BigDecimal kechengprice;

}
