package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class delstukehaoFrom {
    @ApiModelProperty(value = "删除课耗ID列表", required = true)
    private String ids;
}

