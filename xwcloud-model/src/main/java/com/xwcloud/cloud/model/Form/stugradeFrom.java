package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class stugradeFrom {
    @ApiModelProperty(value = "学员ID列表", required = true)
    private String ids;

    @ApiModelProperty(value = "新的年级ID", required = true)
    private Long stuGradeID;
}

