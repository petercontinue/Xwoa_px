package com.xwcloud.cloud.model.Form.sys;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class beofeiForm {
    private long id;
    private BigDecimal outNum;
    private long outStaffId;
    private String outDate;
    private String beizhu;
}
