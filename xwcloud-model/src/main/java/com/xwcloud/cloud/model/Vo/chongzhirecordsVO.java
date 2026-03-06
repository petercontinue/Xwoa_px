package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class chongzhirecordsVO {
    private Long  id;
    private Long stuID;
    private String stuName;
    private BigDecimal allmoney;
    private BigDecimal shimoney;
    private BigDecimal zengmoney;
    private String adddate;
    private String staffName;
    private String beizhu;
    private BigDecimal yue;
}
