package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class updategradeFrom {
    @ApiModelProperty(value = "调级ID列表", required = true)
    private String ids;

}

