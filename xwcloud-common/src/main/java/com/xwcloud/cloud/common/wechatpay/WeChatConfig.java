package com.xwcloud.cloud.common.wechatpay;

public class WeChatConfig {
    /**
     * 微信appId
     */
    public static final String WECHAT_APPID = "wxca12aabb9d440265";

    /**
     * 微信商户号
     */
    public static final String WECHAT_MACH_ID = "1523009911";

    /**
     * key（微信支付API秘钥）
     */
    public static final String WECHAT_key = "wwwxw3qcomwwwxw3qnetwwwxw3qcomok";

    /**
     * 支付类型，小程序用:JSAPI
     */
    public static final String tradeType = "JSAPI";

    /**
     * 回调地址
     */
    public static final String NOTIFYURL = "https://px.xw3q.com:8100/xwcloud-sys/weixin/weChatPay";

    /**
     * 统一下单地址
     */
    public static final String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
}
