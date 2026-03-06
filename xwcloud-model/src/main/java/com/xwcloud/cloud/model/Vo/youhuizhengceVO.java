package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class youhuizhengceVO {
    private Long id;
    private Integer youhuiType;
    private BigDecimal xianzhijine;
    private BigDecimal youhui;
    private String startDateTime;
    private String endDatetime;
    private Long campusID;
    private String qiyeID;
    private String stuGradeIDs;
    private Integer useTimes;
    private String stugradeNams;
    private String campusName;
    private String addTime;
}
