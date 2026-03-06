package com.xwcloud.cloud.oa.controller.smallProgram.common;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.xwcloud.cloud.oa.controller.smallProgram.crypto.WechatCryptUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
@Data
@Slf4j
@XStreamAlias("xml")
public class WechatThirdXmlMessage implements Serializable {
  private static final long serialVersionUID = -5641769554709507771L;

  @XStreamAlias("AppId")
//  @XStreamConverter(value = XStreamCDataConverter.class)
  private String appId;

  @XStreamAlias("CreateTime")
  private Long createTime;

  @XStreamAlias("InfoType")
//  @XStreamConverter(value = XStreamCDataConverter.class)
  private String infoType;

  @XStreamAlias("ComponentVerifyTicket")
//  @XStreamConverter(value = XStreamCDataConverter.class)
  private String componentVerifyTicket;

  @XStreamAlias("AuthorizerAppid")
//  @XStreamConverter(value = XStreamCDataConverter.class)
  private String authorizerAppid;

  @XStreamAlias("AuthorizationCode")
//  @XStreamConverter(value = XStreamCDataConverter.class)
  private String authorizationCode;

  @XStreamAlias("AuthorizationCodeExpiredTime")
  private Long authorizationCodeExpiredTime;

  @XStreamAlias("PreAuthCode")
//  @XStreamConverter(value = XStreamCDataConverter.class)
  private String preAuthCode;


  /**
   * 从加密字符串转换
   *
   * @param encryptedXml        密文
   * @param wechatThirdConfig 配置存储器对象
   * @param timestamp           时间戳
   * @param nonce               随机串
   * @param msgSignature        签名串
   */
  public static WechatThirdXmlMessage fromEncryptedXml(String encryptedXml, WechatThirdConfig wechatThirdConfig,
                                                       String timestamp, String nonce, String msgSignature) {
    WechatCryptUtil cryptUtil = new WechatCryptUtil(wechatThirdConfig.getComponentToken(), wechatThirdConfig.getComponentAesKey(), wechatThirdConfig.getComponentAppId());
    String plainText = cryptUtil.decrypt(msgSignature, timestamp, nonce, encryptedXml);
    log.info("解密后的原始xml消息内容：{}", plainText);
    return XmlUtil.toBean(plainText, WechatThirdXmlMessage.class);
  }


  public static WechatThirdXmlMessage fromEncryptedXml(InputStream is, WechatThirdConfig wechatThirdConfig,
                                                  String timestamp, String nonce, String msgSignature) {
    try {
      return fromEncryptedXml(IOUtils.toString(is, "UTF-8"),
              wechatThirdConfig, timestamp, nonce, msgSignature);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
