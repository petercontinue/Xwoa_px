package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class teakehaoCountVo {
    private Long id;
    private String campusName;
    private String staffName;
    private String zhujiao;
    private String shichang;
    private String allNianji;
    private String className;
    private BigDecimal keshiNum;
    private Date haveClassDate;
    private String shuoMing;
}
