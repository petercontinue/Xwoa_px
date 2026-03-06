package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ordergoodsVO {
    private BigDecimal buynum;
    private long goodID;
    private long goodTypeID;
    private String guige;
    private String guigeID;
    private String img;
    private String orderName;
    private BigDecimal price;
}
