package com.xwcloud.cloud.model.Form.zsbm;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class czyhzcForm {
    private long id;
    private BigDecimal zongmoney;
    private BigDecimal huodongmoney;
    private String sdate;
    private String edate;
}
