package com.xwcloud.cloud.model.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

//学员班级
@Data
public class classTyleVo {
    @ApiModelProperty(value = "班级ID")
    private String id;
    private String zidingyiClassID;
    @ApiModelProperty(value = "班级是否启用")
    private int isShow;
    @ApiModelProperty(value = "校区名称")
    private String campusName;
    @ApiModelProperty(value = "班级名称")
    private String className;
    @ApiModelProperty(value = "是否是一对一班级")
    private int is1v1Class;
    private String mingdan;
    @ApiModelProperty(value = "班级最大学生人数")
    private int maxStuNum;
    @ApiModelProperty(value = "经办人")
    private String jingbanren;
    @ApiModelProperty(value = "添加时间")
    private Date addTime;
    @ApiModelProperty(value = "一对一课程名称")
    private String Kc1v1Name;
    private String classtype;
}
