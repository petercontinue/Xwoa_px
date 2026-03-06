package com.xwcloud.cloud.model.Vo;

import com.xwcloud.cloud.model.entity.WhdChongzhiRecord;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WhdChongzhiRecordVo extends WhdChongzhiRecord {
    @ApiModelProperty("用户名")
    private String userName;
}
