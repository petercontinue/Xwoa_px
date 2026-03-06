package com.xwcloud.cloud.model.Form.stu;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class addKeShiZhsForm {
    private Long songstuID;
    private int zhuansongtype;
    private Long songbuxikechengID;
    private BigDecimal skeshi;
    private BigDecimal songPrice;
    private Long shoustuID;
    private Long shoubuxikechengID;
    private BigDecimal shkeshi;
    private BigDecimal shPrice;
    private String shuoming;
    private String startDate;
    private String endDate;
}
