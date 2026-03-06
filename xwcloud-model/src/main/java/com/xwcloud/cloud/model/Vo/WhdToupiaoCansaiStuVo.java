package com.xwcloud.cloud.model.Vo;


import com.xwcloud.cloud.model.entity.WhdToupiaoCansaistu;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WhdToupiaoCansaiStuVo extends WhdToupiaoCansaistu {
    @ApiModelProperty("添加人")
    private String staffName;
    @ApiModelProperty("活动名称")
    private String toupiaoHuodongName;
}
