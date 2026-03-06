package com.xwcloud.cloud.model.Form;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class addyouhuizcForm {
    private Integer youhuiType;
    private BigDecimal xianzhijine;
    private BigDecimal youhui;
    private String startDateTime;
    private String endDatetime;
    private Long campusID;
    private String stuGradeIDs;
    private Integer useTimes;
}
