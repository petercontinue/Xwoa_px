package com.xwcloud.cloud.model.Vo;

import lombok.Data;

@Data
/**
 * 人工消课
 *
 * 班级考勤详情
 */
public class classkaoqingVo {
    private String ID;//补习ID
    private String campusName;
    private String stuID;
    private String zidingyiStuID;
    private String stuName;
    private String stuGradeName;
    private String kechengName;
    private String kaoqing;
}
