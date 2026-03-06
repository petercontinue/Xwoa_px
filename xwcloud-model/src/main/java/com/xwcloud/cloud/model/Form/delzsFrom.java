package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class delzsFrom {
    @ApiModelProperty(value = "证书ID", required = true)
    private String ids;
}

