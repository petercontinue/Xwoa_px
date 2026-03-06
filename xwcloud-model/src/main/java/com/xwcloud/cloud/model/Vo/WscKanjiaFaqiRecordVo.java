package com.xwcloud.cloud.model.Vo;

import com.xwcloud.cloud.model.entity.WscKanjiaFaqirecord;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class WscKanjiaFaqiRecordVo extends WscKanjiaFaqirecord {
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("商品名")
    private String goodsName;
    @ApiModelProperty("属性列表的砍价成功价格")
    private BigDecimal kanjiaSuccessPrice;
    @ApiModelProperty("属性列表的原价")
    private BigDecimal originalPrice;
}
