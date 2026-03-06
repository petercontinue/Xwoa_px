package com.xwcloud.cloud.model.Vo;


import com.xwcloud.cloud.model.entity.WscGoodsshuxinglist;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WscGoodsAttributeVo extends WscGoodsshuxinglist {
    @ApiModelProperty("商品规格名")
    private String goodsGuigeTypeName;
    @ApiModelProperty("添加人")
    private String addStaffName;
}
