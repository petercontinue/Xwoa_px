package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
//学员课时清零
public class SutClearVo {
    private String id;
    private String campusName;
    private String stuName;
    private String buxiName;
    private BigDecimal keshiNum;
    private BigDecimal xuefei;
    private String beizhu;
    private Date addDate;
}
