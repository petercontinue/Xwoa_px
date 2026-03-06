package com.xwcloud.cloud.model.Vo;


import com.xwcloud.cloud.model.entity.Pxyxinvitedaofangtable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Daofang2Vo extends Pxyxinvitedaofangtable {
    @ApiModelProperty("学生ID")
    private Long stuID;
}
