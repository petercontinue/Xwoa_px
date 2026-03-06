package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class stuclassFrom {
    @ApiModelProperty(value = "班级ID列表", required = true)
    private String ids;

    @ApiModelProperty(value = "修改状态", required = true)
    private int isShow;
}

