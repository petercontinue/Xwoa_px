package com.xwcloud.cloud.model.Vo;

import com.xwcloud.cloud.model.entity.WscKanjiaBangkanrecord;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WscKanHelpRecordVo extends WscKanjiaBangkanrecord {
    @ApiModelProperty("用户名")
    private String userName;
}
