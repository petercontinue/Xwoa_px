package com.xwcloud.cloud.model.Form.stu;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class updateRemainXFForm {
    private String stuID;
    private BigDecimal newXF;
}
