package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class delbuxiKCFrom {
    @ApiModelProperty(value = "补习ID列表", required = true)
    private String buxiids;
}

