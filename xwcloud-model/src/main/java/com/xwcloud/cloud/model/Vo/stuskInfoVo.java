package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class stuskInfoVo {
    private String id;
    private String subjectID;
    private String kechengID;
    private String subjectNamem;
    private String kechengName;
    private String stuName;
    private BigDecimal keshiCount;
    private Date firstDate;
    private Date lastDate;
    private int haveke;

}
