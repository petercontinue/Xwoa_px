package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 剩余课时，查看已上课记录
 */
@Data
public class stukehaoShowVo {
    private String id;
    private String zidingyiStuID;
    private String stuID;
    private String stuName;
    private String className;
    private Date haveClassDate;
    private BigDecimal kechengPrice;
    private BigDecimal keshiNum;
    private BigDecimal payMoney;
}
