package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class delzhuangsongFrom {
    @ApiModelProperty(value = "转送ID列表", required = true)
    private String ids;
}

