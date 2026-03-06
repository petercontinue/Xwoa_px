package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class delKJFrom {
    @ApiModelProperty(value = "考级ID", required = true)
    private String ids;
}

