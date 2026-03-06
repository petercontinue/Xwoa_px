package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class deljifenFrom {
    @ApiModelProperty(value = "积分ID列表", required = true)
    private String ids;

}

