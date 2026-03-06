package com.xwcloud.cloud.model.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LiushilvStuVo {
    private String campusName;
    private String staffName;
    @ApiModelProperty("在上人数")
    private Long zaistunum;
    @ApiModelProperty("总人数")
    private Long allstunum;
}
