package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

//赠送课时
@Data
public class zengsongVo {
    private String id;
    private String stuID;
    private String zidingyiStuID;
    private String stuName;
    private String campusName;
    private String stuGradeName;
    private String kechengName;
    private BigDecimal keshiShu;
    private String jifeiStyle;
    private Date addDate;
    private String staffName;
    private String songYangyin;
    private String banzhuren;
}
