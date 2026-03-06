package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class AllzhuanbanFrom {
    @ApiModelProperty(value = "buxiID列表", required = true)
    private String ids;

    @ApiModelProperty(value = "班级ID", required = true)
    private String classid;

    @ApiModelProperty(value = "开始插入的排课ID", required = true)
    private String pkid;

}


