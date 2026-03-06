package com.xwcloud.cloud.model.Vo;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class qiandanpaymoneyVO {
    private Long paymoneyStyleID;
    private String zhifustyleName;
    private BigDecimal payMoney;
}
