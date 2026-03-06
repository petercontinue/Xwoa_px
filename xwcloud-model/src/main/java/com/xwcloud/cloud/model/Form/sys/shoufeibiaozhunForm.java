package com.xwcloud.cloud.model.Form.sys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class shoufeibiaozhunForm {
    private String id;
    private String buxiStyleId;
    private String stuGradeId;
    private BigDecimal minimumCharge;
}
