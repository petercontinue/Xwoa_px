package com.xwcloud.cloud.model.Vo;

import com.xwcloud.cloud.model.entity.WhdToupiaoHuodong;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WhdToupiaoHuodongVo extends WhdToupiaoHuodong {
    @ApiModelProperty("添加人")
    private String staffName;

    private Integer toupiaoCount;
}
