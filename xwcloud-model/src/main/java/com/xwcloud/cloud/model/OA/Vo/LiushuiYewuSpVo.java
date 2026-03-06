package com.xwcloud.cloud.model.OA.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LiushuiYewuSpVo implements Serializable {

    private Long id;
    private Long qiyeID;
    private String kehucompanyname;
    private Integer liushuiType;
    private Integer addCampusNum;
    private Long campusID;
    private String campusName;
    private Date nextpayDateTime;
    private String liushuishuoming;
    private Long addUser;
    private Date addTime;
    private Integer shengpiState;
    private String shenpiNopassReason;
    private Date shengpiDate;
    private String shengpiStaff;
    private String staffName;

}
