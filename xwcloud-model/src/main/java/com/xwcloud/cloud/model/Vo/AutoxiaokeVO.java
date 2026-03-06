package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
/**
 * 自动消课
 */
public class AutoxiaokeVO {
    private String id;
    private String campusName;
    private String stuID;
    private String zidingyiStuID;
    private String stuName;
    private String banzhuren;//班主任
    private String subjectName;
    private String kechengName;
    private String className;
    private String autoID;//自动消课ID
    private String teaIDs;
    private String teaNames;
    private BigDecimal keshiNum;
    private Date startDate;
    private Date endDate;
    private String state;//启用状态
}
