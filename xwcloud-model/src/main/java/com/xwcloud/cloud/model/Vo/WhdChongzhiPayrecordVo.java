package com.xwcloud.cloud.model.Vo;


import com.xwcloud.cloud.model.entity.WhdChongzhiPayrecord;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WhdChongzhiPayrecordVo extends WhdChongzhiPayrecord {
    @ApiModelProperty("用户名")
    private String userName;
}
