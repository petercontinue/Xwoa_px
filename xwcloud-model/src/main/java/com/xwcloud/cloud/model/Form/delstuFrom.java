package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class delstuFrom {
    @ApiModelProperty(value = "学员ID列表", required = true)
    private String ids;

    @ApiModelProperty(value = "说明", required = true)
    private String massage;
}

