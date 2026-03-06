package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class allChaRuFrom {
    @ApiModelProperty(value = "buxiID列表", required = true)
    private String ids;

    @ApiModelProperty(value = "班级ID", required = true)
    private String classid;

    @ApiModelProperty(value = "是否加入排课", required = true)
    private Boolean cbckb;

    @ApiModelProperty(value = "排课id", required = true)
    private String pkid;
}


