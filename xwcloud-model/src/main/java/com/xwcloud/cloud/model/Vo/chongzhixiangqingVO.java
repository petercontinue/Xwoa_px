package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class chongzhixiangqingVO {
    private long id;
    private BigDecimal money;
    private BigDecimal zengsongMoney;
    private BigDecimal remainChongzhi;
    private String yejistaffName;
    private String beizhu;
    private String addTime;
}
