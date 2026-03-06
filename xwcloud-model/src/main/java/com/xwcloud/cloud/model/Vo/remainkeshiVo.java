package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class remainkeshiVo {
    private String campusName;
    private String stuID;
    private String stuName;
    private String subjectName;
    private String buxiStyleName;
    private String kechengName;
    private String classTimeStyleName;
    private Date startDate;
    private Date endDate;
    private BigDecimal originalprice;
    private BigDecimal kechengprice;
    private BigDecimal keshiNum;
    private BigDecimal remainkeshi;
}

