package com.xwcloud.cloud.model.Vo;


import com.xwcloud.cloud.model.entity.Pxyxgengjintable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class GenjingVo extends Pxyxgengjintable {

    @ApiModelProperty(value = "下次跟进时间")
    private Date nextGenjinTime;
    @ApiModelProperty(value = "意向程度")
    private Long yxLevelID;

}
