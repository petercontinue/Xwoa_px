package com.xwcloud.cloud.model.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class wechatMessageVO {
    @ApiModelProperty(value = "数据ID")
    private String id;
    @ApiModelProperty(value = "员工姓名")
    private String staffName;
    @ApiModelProperty(value = "校区名称")
    private String campusName;
    @ApiModelProperty(value = "推送类型")
    private String tuisongType;
    @ApiModelProperty(value = "推送内容")
    private String note;
    @ApiModelProperty(value = "推送时间")
    private Date addTime;
}
