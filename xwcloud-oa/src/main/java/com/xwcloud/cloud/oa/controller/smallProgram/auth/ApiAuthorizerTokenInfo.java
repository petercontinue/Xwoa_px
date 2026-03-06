package com.xwcloud.cloud.oa.controller.smallProgram.auth;

import lombok.Data;

/**
 * 获取（刷新）授权公众号或小程序的接口调用凭据（令牌）
 */
@Data
public class ApiAuthorizerTokenInfo {


  /**
   * 授权方令牌
   */
  private String authorizerAccessToken;
  /**
   * 有效期，为2小时
   */
  private int expiresIn;
  /**
   * 刷新令牌
   */
  private String authorizerRefreshToken;
}
