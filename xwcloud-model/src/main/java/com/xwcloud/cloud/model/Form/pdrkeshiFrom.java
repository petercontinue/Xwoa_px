package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
/**
 * 排课消课表达
 */
public class pdrkeshiFrom {
    @ApiModelProperty(value = "排课信息", required = true)
    private String tabdata;

    @ApiModelProperty(value = "(排课)学员数据", required = true)
    private String newstu;

}
