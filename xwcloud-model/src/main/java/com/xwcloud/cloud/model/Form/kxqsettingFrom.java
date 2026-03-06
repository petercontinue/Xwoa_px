package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class kxqsettingFrom {
    @ApiModelProperty(value = "补习ID", required = true)
    private String buxiID;

    @ApiModelProperty(value = "要的跨校区", required = true)
    private String campusStrs;
}

