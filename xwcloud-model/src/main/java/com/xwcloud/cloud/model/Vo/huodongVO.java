package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class huodongVO {
    private long huodongID;
    private String huodongName;
    private String huodongImg;
    private String huodongTitle;
    private BigDecimal huodongPrice;
    private BigDecimal oldPrice;
    private int buySum;
    private Date endDatetime;
    private long id;
}
