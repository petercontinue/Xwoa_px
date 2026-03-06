package com.xwcloud.cloud.model.Vo;


import com.xwcloud.cloud.model.entity.WscOrdertuifei;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WscOrderTuifeiVo extends WscOrdertuifei {
    @ApiModelProperty("操作人名")
    private String staffName;
}
