package com.xwcloud.cloud.oa.controller.smallProgram.error;


public class WechatErrorException extends Exception {
  private static final long serialVersionUID = -6357149550353160810L;

  private WechatError error;

  public WechatErrorException(WechatError error) {
    super(error.toString());
    this.error = error;
  }

  public WechatErrorException(WechatError error, Throwable cause) {
    super(error.toString(), cause);
    this.error = error;
  }

  public WechatError getError() {
    return this.error;
  }


}
