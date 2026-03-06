package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class delzengsongFrom {
    @ApiModelProperty(value = "增送ID列表", required = true)
    private String ids;
}

