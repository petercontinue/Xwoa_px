package com.xwcloud.cloud.model.Vo;

import com.xwcloud.cloud.model.entity.WscGoods;
import com.xwcloud.cloud.model.entity.WscGoodsshuxinglistprice;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class WscGoodsVo extends WscGoods {
    @ApiModelProperty("商品价格属性组合")
    private WscGoodsshuxinglistprice goodsshuxinglistprice;
    @ApiModelProperty("商品类型")
    private String goodsType;
    @ApiModelProperty("是否显示")
    private Integer isShow;
    @ApiModelProperty("商品属性的组合")
    private String goodsShuxingListAll;
    @ApiModelProperty("积分价格（属性列表）")
    private BigDecimal jifenPriceShuxing;
    @ApiModelProperty("砍价成功价格（属性列表）")
    private BigDecimal kanjiaSuccessPriceShuxing;
    @ApiModelProperty("显示抢购价（属性列表）")
    private BigDecimal onlyTimeBuyPriceShuxing;
    @ApiModelProperty("商品原价（属性列表）")
    private BigDecimal originalPrice;
    @ApiModelProperty("普通价（属性列表）")
    private BigDecimal priceShuxing;
    @ApiModelProperty("活动名")
    private String huodongName;
    @ApiModelProperty("是否可以更换图片")
    private Integer isNeedChangImg;
    @ApiModelProperty("属性说明")
    private String shuxingMing;
    @ApiModelProperty("属性排序")
    private String shuxingPaixu;
    @ApiModelProperty("规格名")
    private String guigeTypeName;
    @ApiModelProperty("购物车商品数量")
    private Integer gwcshuliang;
    private Integer Sales;
    private BigDecimal qiangoujia;
    private String huodongEnddate;
    private String shijianchuo;
}
