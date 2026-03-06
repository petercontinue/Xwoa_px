package com.xwcloud.cloud.model.Vo;

import lombok.Data;

@Data
/**
 * 考勤统计
 */
public class KaoqingCountVo {
    private String id;
    private String campusName;
    private String stuID;
    private String zidingyiStuID;
    private String stuName;
    private String banzhuren;
    private int allN;
    private int qingjia;
    private int kuangke;
    private int chidao;
    private int zhengchang;
}
