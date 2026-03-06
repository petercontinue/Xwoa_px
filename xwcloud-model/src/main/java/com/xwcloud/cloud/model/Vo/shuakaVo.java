package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.util.Date;

@Data
/**
 * 刷卡签到签退
 */
public class shuakaVo {
    private String id;
    private String campusName;
    private String stuGradeName;
    private String stuID;
    private String zidingyiStuID;
    private String stuName;
    private Date qianDatetime;
}
