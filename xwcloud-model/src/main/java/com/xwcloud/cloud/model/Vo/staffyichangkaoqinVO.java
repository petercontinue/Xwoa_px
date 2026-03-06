package com.xwcloud.cloud.model.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class staffyichangkaoqinVO {
    @ApiModelProperty(value = "异常考勤数据ID")
    private String id;
    @ApiModelProperty(value = "校区名称")
    private String campusName;
    @ApiModelProperty(value = "岗位名称")
    private String staffpostName;
    @ApiModelProperty(value = "员工姓名")
    private String staffName;
    @ApiModelProperty(value = "添加时间")
    private String addDate;
    @ApiModelProperty(value = "考勤类别1.请假，2.迟到，3.早退，4.旷工")
    private Integer type;
    @ApiModelProperty(value = "说明")
    private String shuoMing;
    @ApiModelProperty(value = "考勤日期")
    private String riqi;
    @ApiModelProperty(value = "录入人")
    private String lururen;
}
