package com.xwcloud.cloud.model.Vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class genjinTixingVo {
    @ApiModelProperty(value = "学生id")
    private String id;
    @ApiModelProperty(value = "自定义学生")
    private String zidingyiStuID;
    @ApiModelProperty(value = "学生姓名")
    private String stuName;
    @ApiModelProperty(value = "学生年级")
    private String stuGradeName;
    @ApiModelProperty(value = "登记时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dengjiTime;
    @ApiModelProperty(value = "跟进次数")
    private Integer genjinCount;
    @ApiModelProperty(value = "下次跟进时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date nextGenjinTime;
    @ApiModelProperty(value = "负责人")
    private String staffName;
    @ApiModelProperty(value = "校区名称")
    private String campusName;
    @ApiModelProperty(value = "负责人岗位")
    private String staffpostName;
}
