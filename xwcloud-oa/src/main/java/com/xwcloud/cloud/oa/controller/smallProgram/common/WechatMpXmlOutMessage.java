package com.xwcloud.cloud.oa.controller.smallProgram.common;

import com.google.common.collect.Lists;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.xwcloud.cloud.oa.controller.smallProgram.crypto.WechatCryptUtil;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Calendar;

@XStreamAlias("xml")
@Data
@Slf4j
@Builder
public class WechatMpXmlOutMessage implements Serializable {
  private static final long serialVersionUID = -381382011286216263L;

  @XStreamAlias("ToUserName")
  protected String toUserName;

  @XStreamAlias("FromUserName")
  protected String fromUserName;

  @XStreamAlias("CreateTime")
  protected Long createTime;

  @XStreamAlias("MsgType")
  protected String msgType;

  @XStreamAlias("Content")
  protected String content;

  public static String wechatMpOutXmlMessageToEncryptedXml(WechatMpXmlOutMessage message, WechatThirdConfig wechatThirdConfig) {
    String plainXml =XmlUtil.toXmlWithCData(message,Lists.newArrayList("CreateTime"));
    log.info("加密后的原始xml消息内容：{}", plainXml);
    WechatCryptUtil pc = new WechatCryptUtil(wechatThirdConfig.getComponentToken(), wechatThirdConfig.getComponentAesKey(), wechatThirdConfig.getComponentAppId());
    return pc.encrypt(plainXml);
  }


  public static void main(String[] args){
    Long createTime = Calendar.getInstance().getTimeInMillis() / 1000;
    WechatMpXmlOutMessage wechatMpXmlOutMessage=WechatMpXmlOutMessage.builder()
            .content("TESTCOMPONENT_MSG_TYPE_TEXT_callback")
            .fromUserName("ozy4qt0Rsc9YJzR5nEeVAaTHg9DQ")
            .toUserName("gh_3c884a361561")
            .createTime(createTime)
            .msgType("text")
            .build();
    WechatThirdConfig wechatThirdConfig=new WechatThirdConfig();
    wechatThirdConfig.setComponentAppId("wxc063640eb0fd5cce");
    wechatThirdConfig.setComponentSecret("09fe375e0dfca184c7fe62865a16054f");
    wechatThirdConfig.setComponentAesKey("asasasasasasasasasasasasasasasasasasasasasa");
    wechatThirdConfig.setComponentToken("RPQLqJadlaXcrhbt");
    String wechatMpXmlMessage=wechatMpOutXmlMessageToEncryptedXml(wechatMpXmlOutMessage,wechatThirdConfig);
    System.out.println(wechatMpXmlMessage.toString());
  }

}
