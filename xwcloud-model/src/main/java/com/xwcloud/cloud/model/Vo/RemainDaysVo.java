package com.xwcloud.cloud.model.Vo;

import lombok.Data;

@Data
/**
*@Description: 方法作用:剩余天数
*@param:
*@return:
*@auter:yyl
*@data:2020/12/8 9:49
*/
public class RemainDaysVo {
    private String id;
    private String stuID;
    private String zidingyiStuID;//学员自定义学号
    private String stuName;
    private String subjectName;
    private String campusName;
    private String kechengName;
    private String JifeiStyleName;//计费方式
    private String staffName;//任课老师
    private int DAYs;//剩余天数
    private String className;
    private String startDate;
    private String endDate;
    private String isKsyj;//课时预警
    private String isShow;//是否显示
}
