package com.xwcloud.cloud.model.Vo;

import lombok.Data;


import java.math.BigDecimal;
import java.util.Date;

@Data
public class caigoushenqingVo {
    private Long campusID;
    private Long id;
    private String shangpingName;
    private String guigeID;
    private String typeName;
    private String addDate;
    private Long shangpingTypeID;
    private Long addStaffID;
    private Integer isshenhe;
    private BigDecimal buyNum;
    private String danwei;
    private String shenheNopassReason;
    private String campusName;
    private String staffName;
    private String beizhu;
}
