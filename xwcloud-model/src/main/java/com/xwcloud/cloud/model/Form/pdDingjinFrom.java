package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
/**
 * 排课消课表达
 */
public class pdDingjinFrom {
    @ApiModelProperty(value = "(排课)天课程", required = true)
    private String tiankc;

    @ApiModelProperty(value = "(排课)学员数据", required = true)
    private String newstu;

    @ApiModelProperty(value = "类型", required = true)
    private int type;

    @ApiModelProperty(value = "(不排课)班级数据", required = true)
    private String classidStr;

    @ApiModelProperty(value = "(不排课)学员数据", required = true)
    private String npnewstu;
}
