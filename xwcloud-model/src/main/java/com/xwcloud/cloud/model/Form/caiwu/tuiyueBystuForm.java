package com.xwcloud.cloud.model.Form.caiwu;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class tuiyueBystuForm {
    private String stuID;
    private String payStyleID;
    private String yejirenID;
    private String processingTime;
    private BigDecimal tuiyue;
    private String shuoming;
}
