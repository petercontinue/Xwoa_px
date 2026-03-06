package com.xwcloud.cloud.oa.controller.smallProgram.error;

import com.xwcloud.cloud.oa.controller.smallProgram.common.FastJsonUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 微信错误码.
 * 请阅读：
 * 公众平台：<a href="http://mp.weixin.qq.com/wiki/10/6380dc743053a91c544ffd2b7c959166.html">全局返回码说明</a>
 * 企业微信：<a href="https://work.weixin.qq.com/api/doc#10649">全局错误码</a>
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WechatError implements Serializable {
  private static final long serialVersionUID = 7869786563361406291L;

  /**
   * 微信错误代码.
   */
  private Integer errCode;

  /**
   * 微信错误信息.
   * （如果可以翻译为中文，就为中文）
   */
  private String errMsg;

  /**
   * 微信接口返回的错误原始信息（英文）.
   */
  private String errorMsgEn;

  private String json;

  public static WechatError fromJson(String json) {
    return fromJson(json, null);
  }

  public static WechatError fromJson(String json, WechatType type) {
    WechatError wechatError= (WechatError) FastJsonUtils.json2object(json,WechatError.class);
    if (StringUtils.isNotEmpty(wechatError.getErrMsg())) {
      wechatError.setErrorMsgEn(wechatError.getErrMsg());
    }
    if (wechatError.getErrCode() ==null || type == null) {
      return wechatError;
    }

    if (type == WechatType.MP) {
      final String msg = WechatMpErrorMsgEnum.findMsgByCode(wechatError.getErrCode());
      if (msg != null) {
        wechatError.setErrMsg(msg);
      }
    } else if (type == WechatType.CP) {
      final String msg = WechatCpErrorMsgEnum.findMsgByCode(wechatError.getErrCode());
      if (msg != null) {
        wechatError.setErrMsg(msg);
      }
    }else if (type == WechatType.Open) {
      final String msg = WechatOpenErrorMsgEnum.findMsgByCode(wechatError.getErrCode());
      if (msg != null) {
        wechatError.setErrMsg(msg);
      }
    }

    return wechatError;
  }

  @Override
  public String toString() {
    if (this.json != null) {
      return this.json;
    }
    return "错误: Code=" + this.errCode + ", Msg=" + this.errMsg;
  }

}
