package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class tuiclassFrom {
    @ApiModelProperty(value = "buxiID列表", required = true)
    private String ids;

    @ApiModelProperty(value = "班级ID列表", required = true)
    private String classids;

    @ApiModelProperty(value = "只退1个班级时选择的排课ID", required = true)
    private String pkid;
}


