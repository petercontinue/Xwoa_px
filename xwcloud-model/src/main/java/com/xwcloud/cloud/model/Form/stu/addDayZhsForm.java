package com.xwcloud.cloud.model.Form.stu;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class addDayZhsForm {
    private Long daysongstuID;
    private Long daysongbxkechengID;
    private BigDecimal sdays;
    private BigDecimal daysprice;
    private Long dayshoustuID;
    private Long dayshoubxkechengID;
    private BigDecimal dayshdays;
    private BigDecimal dayshprice;
    private String dayshuoming;
    private int zhuansongdayType;
}
