package com.xwcloud.cloud.model.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class stafftelVo {
    @ApiModelProperty(value = "数据ID")
    private String id;
    @ApiModelProperty(value = "用户名称")
    private String UserName;
    @ApiModelProperty(value = "员工姓名")
    private String staffName;
    @ApiModelProperty(value = "校区名称")
    private String campusName;
    @ApiModelProperty(value = "岗位名称")
    private String staffPostName;
    @ApiModelProperty(value = "员工联系电话")
    private String stafftel;
}
