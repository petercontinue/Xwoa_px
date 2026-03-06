package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class stuIntegerVo {
    private String id;
    private String stuName;
    private String banzhuren;
    private BigDecimal integral;
    private String type;
    private BigDecimal oldIntegral;
    private Date createTime;
    private String stuID;
    private BigDecimal shengyujifen;
    private String staffName;
    private String remark;
}
