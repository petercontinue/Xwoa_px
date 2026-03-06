package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class delstuscoreFrom {
    @ApiModelProperty(value = "成绩表ID", required = true)
    private String ids;
}

