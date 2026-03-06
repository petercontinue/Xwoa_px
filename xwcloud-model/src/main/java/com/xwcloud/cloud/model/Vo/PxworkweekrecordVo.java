package com.xwcloud.cloud.model.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PxworkweekrecordVo {
    @ApiModelProperty(value = "数据ID")
    public String id;
    @ApiModelProperty(value = "校区名称")
    public String campusName;
    @ApiModelProperty(value = "员工岗位名称")
    public String staffpostName;
    @ApiModelProperty(value = "员工姓名")
    public String staffName;
    @ApiModelProperty(value = "本周工作总结")
    public String thisWeekRecord;
    @ApiModelProperty(value = "下周工作总结")
    public String nextWeekRecord;
    @ApiModelProperty(value = "开始日期")
    public Date startDate;
    @ApiModelProperty(value = "结束日期")
    public Date endDate;
    @ApiModelProperty(value = "录入日期")
    public Date luruDate;
}
