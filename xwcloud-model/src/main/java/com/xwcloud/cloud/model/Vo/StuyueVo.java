package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StuyueVo {
    private Long campusID;
    private String campusName;
    private String stuName;
    private  String parentTel;
    private BigDecimal remainChongzhi;
}
