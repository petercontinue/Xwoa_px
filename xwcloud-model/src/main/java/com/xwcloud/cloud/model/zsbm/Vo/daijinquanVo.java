package com.xwcloud.cloud.model.zsbm.Vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class daijinquanVo {
    private long id;
    private String zidingyiStuID;
    private Long campusID;
    private String campusName;
    private String stuNO;
    private String stuName;
    private String banzhuren;
    private String stuGradeName;
    private BigDecimal money;
    private String createTime;
    private String staffName;
}
