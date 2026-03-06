package com.xwcloud.cloud.model.Vo;

import com.xwcloud.cloud.model.entity.WscGoods;
import com.xwcloud.cloud.model.entity.WscGoodsshuxinglistprice;
import com.xwcloud.cloud.model.entity.WscOrdergoods;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WscOrderGoodsVo extends WscOrdergoods {
    @ApiModelProperty("商品")
    private WscGoods goods;
    @ApiModelProperty("商品价格属性组合")
    private WscGoodsshuxinglistprice goodsshuxinglistprice;
    @ApiModelProperty("拼团团长名字")
    private String ptFaqirenName;
}
