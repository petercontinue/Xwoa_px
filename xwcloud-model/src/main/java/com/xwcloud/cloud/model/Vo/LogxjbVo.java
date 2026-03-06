package com.xwcloud.cloud.model.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LogxjbVo{
    @ApiModelProperty(value = "日志内容")
    private String systemContent;
    @ApiModelProperty(value = "方法名称")
    private String funcName;
    @ApiModelProperty(value = "员工姓名")
    private String staffName;
    @ApiModelProperty(value = "添加时间")
    private String addTime;
    @ApiModelProperty(value = "校区名称")
    private String campusName;
    @ApiModelProperty(value = "日志类型")
    private Integer logType;
}
