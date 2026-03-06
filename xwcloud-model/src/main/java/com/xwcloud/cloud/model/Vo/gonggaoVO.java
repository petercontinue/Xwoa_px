package com.xwcloud.cloud.model.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class gonggaoVO {
    @ApiModelProperty(value = "数据ID")
    private String id;
    @ApiModelProperty(value = "员工ID")
    private String staffID;
    @ApiModelProperty(value = "员工姓名")
    private String staffName;
    @ApiModelProperty(value = "公告标题")
    private String gonggaoTitel;
    @ApiModelProperty(value = "公告内容")
    private String gonggaoContent;
    @ApiModelProperty(value = "公告日期")
    private String gonggaoDate;
}
