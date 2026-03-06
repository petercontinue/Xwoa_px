package com.xwcloud.cloud.overall.BizException;

import com.xwcloud.cloud.overall.BizException.BaseErrorInfoInterface;

public enum CommonEnum implements BaseErrorInfoInterface {
    // 数据操作错误定义
    SUCCESS("200", "成功!"),
    BODY_NOT_MATCH("400","请求的数据格式不符!"),
    SIGNATURE_NOT_MATCH("401","访问被拒绝!"),
    //401.1 登录失败
    LOGIN_NOT_MATCH("401.1","登录失败!"),
    NOT_FOUND("404", "未找到该资源!"),
    INTERNAL_SERVER_ERROR("500", "服务器内部错误!"),
    SERVER_BUSY("503","服务器正忙，请稍后再试!"),

    /**
     * 表示接口调用方异常提示
     */
    ACCESS_TOKEN_INVALID("1001","access_token无效"),
    REFRESH_TOKEN_INVALID("1002","refresh_token无效"),
    INSUFFICIENT_PERMISSIONS("1003","该用户权限不足以访问该资源接口"),
    UNAUTHORIZED("1004","接口没带Token"),
    ;

    /** 错误码 */
    private String resultCode;

    /** 错误描述 */
    private String resultMsg;

    CommonEnum(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public String getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }

}
