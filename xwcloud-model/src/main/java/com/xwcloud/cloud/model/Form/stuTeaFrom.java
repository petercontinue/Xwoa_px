package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class stuTeaFrom {
    @ApiModelProperty(value = "学员ID列表", required = true)
    private String ids;

    @ApiModelProperty(value = "教师ID", required = true)
    private Long teacher;
}

