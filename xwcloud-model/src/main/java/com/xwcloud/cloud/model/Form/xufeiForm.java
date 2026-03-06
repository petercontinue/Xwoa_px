package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class xufeiForm {
    @ApiModelProperty(value = "续费学生ID", required = true)
    public Long stuID;
    @ApiModelProperty(value = "实收金额",required = true)
    public BigDecimal shishouTotalMoney;
    @ApiModelProperty(value = "是否是转介绍",required = false)
    public boolean isZhuanIntroduce;
    @ApiModelProperty(value = "签单日期",required = true)
    public Date qiandandate;
    @ApiModelProperty(value = "签单备注信息", required = false)
    public String beizhu;
    @ApiModelProperty(value = "来源", required = false)
    public String telFromID;
    @ApiModelProperty(value = "补习课程信息", required = true)
    public String bxKcData;
    @ApiModelProperty(value = "物品信息", required = false)
    public String wpData;
    @ApiModelProperty(value = "杂费信息", required = false)
    public String zafeiData;
    @ApiModelProperty(value = "代金券金额", required = true)
    public BigDecimal daijinquan;
    @ApiModelProperty(value = "定金金额", required = true)
    public BigDecimal dingjin;
    @ApiModelProperty(value = "小程序支付", required = false)
    public String isAppPay;

    public String PayMoneyStyle;
    @ApiModelProperty(value = "签单方式（新签还是续费）", required = true)
    public int moneyStyle;
    @ApiModelProperty(value = "签单业绩人信息", required = true)
    public String qiandanstaffinfo;
    @ApiModelProperty(value = "支付方式信息", required = true)
    public String paytyles;
    @ApiModelProperty(value = "优惠政策", required = false)
    public Long youhuizhengce;
    @ApiModelProperty(value = "课程费用总额", required = true)
    public BigDecimal AmountKC;
    @ApiModelProperty(value = "物品费用总额", required = true)
    public BigDecimal AmountWp;
    @ApiModelProperty(value = "其他费用总额", required = true)
    public BigDecimal AmountOther;
}
