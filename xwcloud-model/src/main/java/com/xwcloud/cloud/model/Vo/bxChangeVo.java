package com.xwcloud.cloud.model.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

//课程变动流水
@Data
public class bxChangeVo {
    @ApiModelProperty(value = "数据ID")
    private String id;
    @ApiModelProperty(value = "校区名称")
    private String campusName;
    @ApiModelProperty(value = "变动类型")
    private int type;
    @ApiModelProperty(value = "原学生ID")
    private String oldStuID;
    @ApiModelProperty(value = "原学生姓名")
    private String oldStu;
    @ApiModelProperty(value = "原课程名称")
    private String oldkechengName;
    @ApiModelProperty(value = "变动前价格")
    private BigDecimal oldprice;
    @ApiModelProperty(value = "变动前课时")
    private BigDecimal oldrkeshi;
    @ApiModelProperty(value = "变动前总价")
    private BigDecimal oldzongjia;
    @ApiModelProperty(value = "变动前开始日期")
    private Date oldstartDate;
    @ApiModelProperty(value = "变动前结束日期")
    private Date oldendDate;
    @ApiModelProperty(value = "新学员ID")
    private String newStuID;
    @ApiModelProperty(value = "新学员姓名")
    private String newStu;
    @ApiModelProperty(value = "新课程名字")
    private String newkechengName;
    @ApiModelProperty(value = "新课程单价")
    private BigDecimal newprice;
    @ApiModelProperty(value = "新课程课时数")
    private BigDecimal newrkeshi;
    @ApiModelProperty(value = "新课程总价")
    private BigDecimal newzongjia;
    @ApiModelProperty(value = "新课程开始日期")
    private Date newstartDate;
    @ApiModelProperty(value = "新课程结束日期")
    private Date newendDate;
    @ApiModelProperty(value = "经办人")
    private String jingbanren;
    @ApiModelProperty(value = "添加时间")
    private Date addDate;
}
