package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class stayFrom {
    @ApiModelProperty(value = "宿舍ID列表", required = true)
    private String ids;

}

