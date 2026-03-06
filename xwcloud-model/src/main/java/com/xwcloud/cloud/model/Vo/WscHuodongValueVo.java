package com.xwcloud.cloud.model.Vo;

import com.xwcloud.cloud.model.entity.WscHuodongValue;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WscHuodongValueVo extends WscHuodongValue {
    @ApiModelProperty("活动名")
    private String huodongName;
}
