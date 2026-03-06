package com.xwcloud.cloud.model.Vo;

import lombok.Data;

@Data
/**
 * 自由签到签退
 */
public class ziyouqiandaoVo {
    private String campusName;
    private String className;
    private String classID;
    private String yingQianDaoCount;//应考勤人数
    private String yiQianDaoCount;//已考勤人数
    private String yituiDaoCount;//签退人数
}
