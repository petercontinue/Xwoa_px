package com.xwcloud.cloud.model.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GenjinLiushuiSearchObj {
    @ApiModelProperty("页数量")
    private Long size;
    @ApiModelProperty("页码")
    private Long current;
    @ApiModelProperty("校区ID")
    private Long campusID;
    @ApiModelProperty("学生姓名")
    private String stuName;
    @ApiModelProperty("跟进时间：格式->start_end")
    private String gengjinTime;
    @ApiModelProperty("跟进人名")
    private String staffName;
}
