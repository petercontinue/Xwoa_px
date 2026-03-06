package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class AddDaofangForm {
    @ApiModelProperty(value = "添加人ID", required = true)
    private Long staffID;
    @ApiModelProperty(value = "学生ID", required = true)
    private Long stuID;
    @ApiModelProperty(value = "到访时间", required = true)
    private Date daofangTime;
    @ApiModelProperty(value = "到访说明", required = true)
    private String daofangShuoMing;
    @ApiModelProperty(value = "到访ID", required = true)
    private Long daofangID;
    @ApiModelProperty(value = "企业ID", required = true)
    private String qiyeID;
    @ApiModelProperty(value = "邀约ID",required = false)
    private Long inviteID;
}
