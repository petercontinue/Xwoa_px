package com.xwcloud.cloud.model.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GenjinTipSearchObj {
    @ApiModelProperty(value = "页数量")
    private Long size;
    @ApiModelProperty(value = "页码")
    private Long current;
    @ApiModelProperty(value = "学生id")
    private Long id;
    @ApiModelProperty(value = "年级ID")
    private Long stuGradeID;
    @ApiModelProperty(value = "校区id")
    private Long campusID;
    @ApiModelProperty(value = "岗位id")
    private Long staffpostID;
    @ApiModelProperty(value = "学生名")
    private String stuName;
    @ApiModelProperty(value = "登记时间")
    private String dengjiTime;
    @ApiModelProperty(value = "下次跟进时间")
    private String nextGenjinTime;
    @ApiModelProperty(value = "负责人")
    private String staffName;
    @ApiModelProperty(value = "查询等级")
    private Integer type;
}
