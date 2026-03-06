package com.xwcloud.cloud.model.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class workdayrecordVo {
    @ApiModelProperty(value = "校区名称")
    public String CampusName;
    @ApiModelProperty(value = "员工岗位名称")
    public String StaffPostName;
    @ApiModelProperty(value = "员工姓名")
    public String StaffName;
    @ApiModelProperty(value="数据ID")
    public String Id;
    @ApiModelProperty(value = "日志日期")
    public String LogDate;
    @ApiModelProperty(value = "图片路径")
    public String ImgsUrl;
    @ApiModelProperty(value = "日志内容")
    public String LogContent;
}
