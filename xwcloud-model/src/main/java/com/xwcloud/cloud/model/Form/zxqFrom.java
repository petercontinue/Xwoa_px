package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class zxqFrom {
    @ApiModelProperty(value = "转校区参数", required = true)
    private String parmValue;

    @ApiModelProperty(value = "新的校区ID", required = true)
    private Long newcampus;

    @ApiModelProperty(value = "学员ID", required = true)
    private String stuid;
}

