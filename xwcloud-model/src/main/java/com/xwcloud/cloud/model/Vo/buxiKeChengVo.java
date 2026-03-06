package com.xwcloud.cloud.model.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

//学员课程
@Data
public class buxiKeChengVo {
    private String id;
    @ApiModelProperty(value = "校区名称")
    private String campusName;
    @ApiModelProperty(value = "学生ID")
    private String stuID;
    @ApiModelProperty(value = "自定义学号")
    private String zidingyiStuID;
    @ApiModelProperty(value = "年级")
    private String stuGradeName;//年级
    @ApiModelProperty(value = "学生姓名")
    private String stuName;
    @ApiModelProperty(value = "补习科目")
    private String parentTel;
    private String bxsubject;//补习科目
    @ApiModelProperty(value = "学员账号状态")
    private String stuStatus;
    @ApiModelProperty(value = "学员补习状态")
    private String buxiStateID;
    @ApiModelProperty(value = "补习类型")
    private String buxiStatus;//补习类型
    private String kechengID;
    @ApiModelProperty(value = "补习课程")
    private String buxiCourse;//补习课程
    @ApiModelProperty(value = "课程时长")
    private String CourseTime;//课程时长

    @ApiModelProperty(value = "课程时长ID")
    private String CourseTimeID;//课程时长
    //    private String type;//补习课程类型：买/转送得/赠送/转送出
    @ApiModelProperty(value = "计费方式")
    private int buxiPayStyle;//计费方式
    @ApiModelProperty(value = "开始时间")
    private Date startTime;//开始时间
    @ApiModelProperty(value = "结束时间")
    private Date endTime;//结束时间
    @ApiModelProperty(value = "原单价")
    private BigDecimal OldCoursePrice;//原单价
    @ApiModelProperty(value = "现单价")
    private BigDecimal coursePrice;//现单价
    @ApiModelProperty(value = "购买课时")
    private BigDecimal Courses;//购买课时
    @ApiModelProperty(value = "剩余课时")
    private BigDecimal remainkeshi;//剩余课时
    @ApiModelProperty(value = "启用状态")
    private int isShow;//启用状态
    private int crossCampus;//跨校区
    private int type;
    private String shareBuxiID;
}
