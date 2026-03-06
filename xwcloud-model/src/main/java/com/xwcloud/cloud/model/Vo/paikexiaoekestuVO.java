package com.xwcloud.cloud.model.Vo;

import lombok.Data;

@Data
/**
 * 按排课消课，把班级学生及考勤返回
 */
public class paikexiaoekestuVO {
    private String id;//stuID
    private String stuName;
    private String yujing;
    private String kaoqingStyle;
    private String isShow;
    private String remainkeshi;
    private String classTimeStyleName;
}
