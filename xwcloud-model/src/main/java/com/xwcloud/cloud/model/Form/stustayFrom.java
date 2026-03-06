package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class stustayFrom {
    @ApiModelProperty(value = "学员ID列表", required = true)
    private String ids;

    @ApiModelProperty(value = "新的宿舍ID", required = true)
    private String roomID;
}

