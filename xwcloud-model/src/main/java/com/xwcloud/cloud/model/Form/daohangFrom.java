package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class daohangFrom {

    @ApiModelProperty(value = "导航保存信息", required = true)
    private String dhmessage;

}

