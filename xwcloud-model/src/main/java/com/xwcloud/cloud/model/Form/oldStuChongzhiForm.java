package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class oldStuChongzhiForm {
    @ApiModelProperty(value = "充值学员id", required = true)
    private Long oldStuId;
    @ApiModelProperty(value = "业绩人ID", required = true)
    private Long yeJiRenId;
    @ApiModelProperty(value = "充值金额", required = true)
    private BigDecimal chongzhiMoney;
    @ApiModelProperty(value = "赠送金额", required = true)
    private BigDecimal zengsongMoney;
    @ApiModelProperty(value = "实得总金额", required = true)
    private BigDecimal totalMoney;
    @ApiModelProperty(value = "支付方式", required = true)
    private String payMoneyStyleId;
    @ApiModelProperty(value = "添加充值时间", required = true)
    private String addDateTime;
    @ApiModelProperty(value = "支付方式及支付金额",required = true)
    private String paystylemoney;
}
