package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class zhutiForm {
    @ApiModelProperty(value = "id", required = true)
    private String id;

    @ApiModelProperty(value = "轮播图", required = true)
    private String lunboimgs;

    @ApiModelProperty(value = "机构名称", required = true)
    private String jigouname;

    @ApiModelProperty(value = "校区地址", required = true)
    private String campusaddress;

    @ApiModelProperty(value = "咨询热线", required = true)
    private String zxtel;

    @ApiModelProperty(value = "分享说明标题", required = true)
    private String fxtitle;

    @ApiModelProperty(value = "分享说明信息", required = true)
    private String fxshuoming;

    @ApiModelProperty(value = "logo", required = true)
    private String logo;

}

