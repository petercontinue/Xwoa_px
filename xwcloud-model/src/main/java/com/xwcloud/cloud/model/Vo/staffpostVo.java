package com.xwcloud.cloud.model.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class staffpostVo {
    @ApiModelProperty(value = "数据ID")
    public String id;
    @ApiModelProperty(value = "岗位名称")
    public String staffpostName;
    @ApiModelProperty(value = "校区名称")
    public String campusName;
    private long campusID;
}
