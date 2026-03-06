package com.xwcloud.cloud.oa.controller.smallProgram.error;

import lombok.Getter;

/**
 * <pre>
 * 微信第三方平台全局错误码.
 * 参考文档：<a href="https://work.weixin.qq.com/api/doc#10649">企业微信全局错误码</a>
 * </pre>
 *
 */
@Getter
public enum WechatOpenErrorMsgEnum {
  /**
   * 系统繁忙；服务器暂不可用，建议稍候重试。建议重试次数不超过3次。
   */
  CODE_1(-1, "系统繁忙；服务器暂不可用，建议稍候重试。建议重试次数不超过3次。"),

  /**
   * component ticket is invalid hint
   */
  CODE_61006(61006, "component ticket失效"),
  /**
   * 请求成功；接口调用成功
   */
  CODE_0(0, "请求成功；接口调用成功");

  private int code;
  private String msg;

  WechatOpenErrorMsgEnum(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  /**
   * 通过错误代码查找其中文含义.
   */
  public static String findMsgByCode(int code) {
    WechatOpenErrorMsgEnum[] values = WechatOpenErrorMsgEnum.values();
    for (WechatOpenErrorMsgEnum value : values) {
      if (value.code == code) {
        return value.msg;
      }
    }

    return null;
  }
}
