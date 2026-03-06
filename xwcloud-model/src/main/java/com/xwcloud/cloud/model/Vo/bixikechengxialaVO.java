package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class bixikechengxialaVO {
    private long id;
    private String kechengName;
    private long buxistyleid;
    private String buxiStyleName;
    private String subjectName;
    private BigDecimal keshiNum;
    private BigDecimal zongjia;
    private long subjectID;
    private int byMonthOrDay;
}
