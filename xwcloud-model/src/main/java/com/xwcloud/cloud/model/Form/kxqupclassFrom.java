package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class kxqupclassFrom {
    @ApiModelProperty(value = "学员ID", required = true)
    private String stuid;

    @ApiModelProperty(value = "补习ID", required = true)
    private String buxiid;

    @ApiModelProperty(value = "校区ID", required = true)
    private String campusid;

    @ApiModelProperty(value = "班级ID", required = true)
    private String classid;

    @ApiModelProperty(value = "是否加入排课", required = true)
    private Boolean cbckb;
}


