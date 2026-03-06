package com.xwcloud.cloud.model.Vo;


import com.xwcloud.cloud.model.entity.WhdToupiaoTprecord;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WhdToupiaoRecordVo extends WhdToupiaoTprecord {
    @ApiModelProperty("参赛人名")
    private String stuName;
    @ApiModelProperty("投票人名")
    private String userName;
    @ApiModelProperty("活动名")
    private String toupiaoHuodongName;
}
