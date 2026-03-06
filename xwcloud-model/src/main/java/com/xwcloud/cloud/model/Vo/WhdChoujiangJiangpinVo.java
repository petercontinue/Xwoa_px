package com.xwcloud.cloud.model.Vo;

import com.xwcloud.cloud.model.entity.WhdChoujiangJiangping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WhdChoujiangJiangpinVo extends WhdChoujiangJiangping {
    @ApiModelProperty("活动名称")
    private String choujiangHuodongName;
}
