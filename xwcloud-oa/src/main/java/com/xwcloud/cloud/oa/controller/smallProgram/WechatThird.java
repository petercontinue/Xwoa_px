package com.xwcloud.cloud.oa.controller.smallProgram;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.oa.controller.smallProgram.auth.ApiQueryAuthInfo;
import com.xwcloud.cloud.oa.controller.smallProgram.common.*;
import com.xwcloud.cloud.oa.controller.smallProgram.error.WechatErrorException;
import com.xwcloud.cloud.oa.service.WechatNotifyService;
import com.xwcloud.cloud.oa.service.WechatThirdAuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/WechatThird")
@JacksonXmlRootElement(localName = "xml")
public class WechatThird {
    @Autowired
    private WechatNotifyService wechatNotifyService;
    @Autowired
    private WechatThirdConfig wechatThirdConfig;
    @Autowired
    private WechatThirdAuthService wechatThirdAuthService;

    /**
     * 微信授权事件的接收
     *
     * @param requestBody
     * @param timestamp
     * @param nonce
     * @param signature
     * @param encType
     * @param msgSignature
     * @return
     */
    @RequestMapping("/receive_ticket")
    public Object receiveTicket(@RequestBody(required = false) String requestBody, @RequestParam("timestamp") String timestamp,
                                @RequestParam("nonce") String nonce, @RequestParam("signature") String signature,
                                @RequestParam(name = "encrypt_type", required = false) String encType,
                                @RequestParam(name = "msg_signature", required = false) String msgSignature) {
        System.out.println(
                "\n接收微信请求：[signature=[{" + signature + "}], encType=[{" + encType + "}], msgSignature=[{" + msgSignature + "}],"
                        + " timestamp=[{" + timestamp + "}], nonce=[{" + nonce + "}], requestBody=[\n{" + requestBody + "}\n] ");

        if (!StringUtils.equalsIgnoreCase("aes", encType)
                || !wechatNotifyService.checkSignature(timestamp, nonce, signature)) {
            throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
        }

        // aes加密的消息
        WechatThirdXmlMessage inMessage = WechatThirdXmlMessage.fromEncryptedXml(requestBody,
                wechatThirdConfig, timestamp, nonce, msgSignature);
        System.out.println("\n消息解密后内容为：\n{" + inMessage.toString() + "} ");
        try {
            String out = wechatNotifyService.route(inMessage);
            System.out.println("\n组装回复信息：{" + out + "}");
        } catch (WechatErrorException e) {
            System.out.println("receive_ticket:" + e);
        }


        return "success";
    }


    /**
     * 消息与事件接收URL
     *
     * @param requestBody
     * @param appId
     * @param signature
     * @param timestamp
     * @param nonce
     * @param openid
     * @param encType
     * @param msgSignature
     * @return
     */
    @RequestMapping(value = "/{appId}/callback", method = RequestMethod.POST)
    public Object callback(@RequestBody(required = false) String requestBody,
                           @PathVariable("appId") String appId,
                           @RequestParam("signature") String signature,
                           @RequestParam("timestamp") String timestamp,
                           @RequestParam("nonce") String nonce,
                           @RequestParam("openid") String openid,
                           @RequestParam("encrypt_type") String encType,
                           @RequestParam("msg_signature") String msgSignature) {
        System.out.println(
                "\n callback接收微信请求：[appId=[{" + appId + "}], openid=[{" + openid + "}], signature=[{" + signature + "}], encType=[{" + encType + "}], msgSignature=[{" + msgSignature + "}],"
                        + " timestamp=[{" + timestamp + "}], nonce=[{" + nonce + "}], requestBody=[\n{" + requestBody + "}\n] ");
        if (!StringUtils.equalsIgnoreCase("aes", encType)
                || !wechatNotifyService.checkSignature(timestamp, nonce, signature)) {
            throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
        }
        String out = "";
        // aes加密的消息
        WechatMpXmlMessage wechatMpXmlMessage = WechatMpXmlMessage.fromEncryptedMpXml(requestBody, wechatThirdConfig, timestamp, nonce, msgSignature);
        System.out.println("\n消息解密后内容为：\n{" + wechatMpXmlMessage.toString() + "} ");
        // 全网发布测试用例
        try {
            if (StringUtils.equals(wechatMpXmlMessage.getMsgType(), "text")) {
                /*返回普通文本信息*/
                if (StringUtils.equals(wechatMpXmlMessage.getContent(), "TESTCOMPONENT_MSG_TYPE_TEXT")) {
                    String returnContent = wechatMpXmlMessage.getContent() + "_callback";
                    Long createTime = Calendar.getInstance().getTimeInMillis() / 1000;
                    WechatMpXmlOutMessage wechatMpXmlOutMessage = WechatMpXmlOutMessage.builder()
                            .content(returnContent)
                            .fromUserName(wechatMpXmlMessage.getToUser())
                            .toUserName(wechatMpXmlMessage.getFromUser())
                            .createTime(createTime)
                            .msgType("text")
                            .build();
                    out = WechatMpXmlOutMessage.wechatMpOutXmlMessageToEncryptedXml(wechatMpXmlOutMessage, wechatThirdConfig);
                } else if (StringUtils.startsWith(wechatMpXmlMessage.getContent(), "QUERY_AUTH_CODE:")) {
                    /*全网发布返回api文本信息*/
                    String authCode = wechatMpXmlMessage.getContent().split(":")[1];
                    ApiQueryAuthInfo apiQueryAuthInfo = wechatThirdAuthService.getApiQueryAuthInfo(authCode);
                    Map<String, Object> obj = new HashMap<String, Object>();
                    Map<String, Object> msgMap = new HashMap<String, Object>();
                    String msg = authCode + "_from_api";
                    msgMap.put("content", msg);
                    obj.put("touser", wechatMpXmlMessage.getFromUser());
                    obj.put("msgtype", "text");
                    obj.put("text", msgMap);
                    out = wechatThirdAuthService.sendMessage(FastJsonUtils.map2json(obj), apiQueryAuthInfo.getAuthorizationInfo().getAuthorizerAccessToken());
                }
            } else if (StringUtils.equals(wechatMpXmlMessage.getMsgType(), "event")) {
                /*发送事件信息(返回文本信息)*/
                String content = wechatMpXmlMessage.getEvent() + "from_callback";
                Long createTime = Calendar.getInstance().getTimeInMillis() / 1000;
                WechatMpXmlOutMessage wechatMpXmlOutMessage = WechatMpXmlOutMessage.builder()
                        .content(content)
                        .fromUserName(wechatMpXmlMessage.getToUser())
                        .toUserName(wechatMpXmlMessage.getFromUser())
                        .createTime(createTime)
                        .msgType("text")
                        .build();
                out = WechatMpXmlOutMessage.wechatMpOutXmlMessageToEncryptedXml(wechatMpXmlOutMessage, wechatThirdConfig);
            }
        } catch (Exception e) {
            System.out.println("callback:" + e);
        }
        System.out.println("\n消息与事件接收URL：out=[{" + out + "}] ");
        return out;
    }


    @ResponseBody
    @RequestMapping(value = "/TestFunction", method = RequestMethod.GET)
    public AjaxJson TestFunction() {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setMsg("测试成功！");
        ajaxJson.setSuccess(true);
        return ajaxJson;
    }
}
