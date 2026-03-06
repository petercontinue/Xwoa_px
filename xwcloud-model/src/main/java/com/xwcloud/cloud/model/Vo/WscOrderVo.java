package com.xwcloud.cloud.model.Vo;

import com.xwcloud.cloud.model.entity.WscOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WscOrderVo extends WscOrder {
    @ApiModelProperty("下单用户名")
    private String orderUserName;
    @ApiModelProperty("商品数量")
    private Integer goodsCount;
    @ApiModelProperty("活动名称")
    private String huodongName;
    @ApiModelProperty("下单时间：格式start_end")
    private String datesoe;
    @ApiModelProperty("快捷查询类型：2：今日，3：本周，4：本月")
    private Integer searchType;
}
