package com.xwcloud.cloud.model.Form.stu;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MergeCourseForm {
    private BigDecimal new_allkeshi;
    private String newbxID;
    private BigDecimal newkc_keshi;
    private BigDecimal newkc_price;
    private long oldbxID;
    private BigDecimal oldkcprice;
    private BigDecimal oldkeshi;
}
