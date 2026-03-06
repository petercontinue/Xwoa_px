package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
/**
 * 转换学员考勤信息
 */
public class stupdrkeshiVo {
    private boolean cked;
    private String id;
    private String bxID;
    private String stuName;
    private int type;
    private String kcName;
    private int classtype;

}
