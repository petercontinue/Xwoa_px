package com.xwcloud.cloud.model.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class WhdChoujiangRecordrVo extends WhdChoujiangJiangpinVo {
    @ApiModelProperty("商城用户名")
    private String userName;
    @ApiModelProperty("活动名称")
    private String choujiangHuodongName;
    @ApiModelProperty("奖品名称")
    private String jiangpingName;
    private Date choujiangTime;

}
