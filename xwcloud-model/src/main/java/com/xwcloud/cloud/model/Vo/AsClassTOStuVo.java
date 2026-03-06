package com.xwcloud.cloud.model.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
//按照学员的班级获取学员
public class AsClassTOStuVo {
    @ApiModelProperty(value = "学员班级表ID")
    private String id;
    @ApiModelProperty(value = "学生ID")
    private String stuID;
    @ApiModelProperty(value = "自定义学号")
    private String zidingyiStuID;
    @ApiModelProperty(value = "学生姓名")
    private String stuName;
    @ApiModelProperty(value = "父母联系电话")
    private String parentTel;
    @ApiModelProperty(value = "课程名称")
    private String kechengName;
    @ApiModelProperty(value = "学生年级")
    private String stuGradeName;
}
