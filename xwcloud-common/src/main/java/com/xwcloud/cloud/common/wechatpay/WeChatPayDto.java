package com.xwcloud.cloud.common.wechatpay;


import lombok.Data;


@Data
public class WeChatPayDto {
    /**
     * 商品描述
     */
    private String body;

    /**
     * 订单号
     */
    private String outTradeNo;

    /**
     * 金额
     */
    private String totalFee;
    

    /**
     * 支付类型
     */
    private String payType;
}
