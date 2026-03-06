package com.xwcloud.cloud.oa.controller.smallProgram.common;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.xwcloud.cloud.oa.controller.smallProgram.crypto.WechatCryptUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

/**
 * <pre>
 * 微信推送过来的消息，xml格式.
 * 部分未注释的字段的解释请查阅相关微信开发文档：
 * <a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140453&token=&lang=zh_CN">接收普通消息</a>
 * <a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140454&token=&lang=zh_CN">接收事件推送</a>
 * </pre>
 *
 */
@Data
@Slf4j
@XStreamAlias("xml")
public class WechatMpXmlMessage implements Serializable {
  private static final long serialVersionUID = -3586245291677274914L;

  ///////////////////////
  // 以下都是微信推送过来的消息的xml的element所对应的属性
  ///////////////////////

  @XStreamAlias("ToUserName")
  private String toUser;

  @XStreamAlias("FromUserName")
  private String fromUser;

  @XStreamAlias("CreateTime")
  private Long createTime;

  @XStreamAlias("MsgType")
  private String msgType;

  @XStreamAlias("Content")
  private String content;

  /**
   * 	审核失败的原因
   */
  @XStreamAlias("Reason")
  private String failReason;

  /**
   * 审核失败时的时间（整型），时间戳
   */
  @XStreamAlias("FailTime")
  private Long failTime;

  @XStreamAlias("Event")
  private String event;


  /**
   * 从加密字符串转换.
   *
   * @param encryptedXml      密文
   * @param timestamp         时间戳
   * @param nonce             随机串
   * @param msgSignature      签名串
   */
  public static WechatMpXmlMessage fromEncryptedMpXml(String encryptedXml, WechatThirdConfig wechatThirdConfig,
													  String timestamp, String nonce, String msgSignature) {
    WechatCryptUtil cryptUtil = new WechatCryptUtil(wechatThirdConfig.getComponentToken(), wechatThirdConfig.getComponentAesKey(), wechatThirdConfig.getComponentAppId());
    String plainText = cryptUtil.decrypt(msgSignature, timestamp, nonce, encryptedXml);
    log.debug("解密后的原始xml消息内容：{}", plainText);
    return XmlUtil.toBean(plainText, WechatMpXmlMessage.class);
  }

  public static WechatMpXmlMessage fromEncryptedXml(InputStream is, WechatThirdConfig wechatThirdConfig, String timestamp,
													String nonce, String msgSignature) {
    try {
      return fromEncryptedMpXml(IOUtils.toString(is, "UTF-8"), wechatThirdConfig, timestamp, nonce, msgSignature);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }

  public static void main(String[] args){
    String xml="<xml><ToUserName><![CDATA[gh_936782c4810a]]></ToUserName>\n" +
            "<FromUserName><![CDATA[o_gTy5ENe8mQRoj18GPa0O7nikTU]]></FromUserName>\n" +
            "<CreateTime>1543387708</CreateTime>\n" +
            "<MsgType><![CDATA[event]]></MsgType>\n" +
            "<Event><![CDATA[weapp_audit_success]]></Event>\n" +
            "<SuccTime>1543387708</SuccTime>\n" +
            "</xml>";
    WechatMpXmlMessage wechatMpXmlMessage=XmlUtil.toBean(xml, WechatMpXmlMessage.class);
    System.out.println(wechatMpXmlMessage.toString());
  }

}
