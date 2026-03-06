package com.xwcloud.cloud.model.Form.caiwu;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AlloffForm {
    private String stuID;
    private String payStyleID;
    private String yejirenID;
    private String processingTime;
    private BigDecimal tuiallmoney;
    private String shuoming;
}
