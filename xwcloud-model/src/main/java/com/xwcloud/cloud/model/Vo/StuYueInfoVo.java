package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StuYueInfoVo {
    private Long id;
    private String campusName;
    private String stuName;
    private String parentTel;
    private BigDecimal remainChongzhi;
    private String stuGradeName;
    private String zidingyiStuID;
}
