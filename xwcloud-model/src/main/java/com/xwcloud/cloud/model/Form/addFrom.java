package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class addFrom {
    @ApiModelProperty(value = "选择的数据", required = true)
    private String xadata;

    @ApiModelProperty(value = "退费信息", required = true)
    private String tuimessage;

    @ApiModelProperty(value = "签单IDs", required = true)
    private String qiandanids;
}

