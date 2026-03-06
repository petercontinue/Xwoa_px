package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class savewpSaleForm {
    @ApiModelProperty(value = "总价", required = true)
    private BigDecimal amountmoney;
    @ApiModelProperty(value = "优惠金额", required = true)
    private BigDecimal youhuijine;
    @ApiModelProperty(value = "实际总价", required = true)
    private BigDecimal shijiamount;
    @ApiModelProperty(value = "支付方式", required = true)
    private String paystyle;
    @ApiModelProperty(value = "学生ID", required = false)
    private Long stuID;
    @ApiModelProperty(value = "购买物品信息", required = true)
    private String wpData;
    @ApiModelProperty(value = "支付方式及支付金额", required = true)
    private String zhifustylemoney;
}
