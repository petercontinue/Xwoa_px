package com.xwcloud.cloud.common.wechattuisong;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class tuisong {

    /**
     * 获取用户Access_Token
     * @return
     */
    public String getAccessToken() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("APPID", "wxca12aabb9d440265");  //微信小程序AppID
        params.put("APPSECRET", "6d385686fedfe8c8dea57de9c87a2e1e");  //微信小程序AppSecret
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={APPID}&secret={APPSECRET}", String.class, params);
        String body = responseEntity.getBody();
        JSONObject object = JSON.parseObject(body);
        String Access_Token = object.getString("access_token");
        String expires_in = object.getString("expires_in");
        System.out.println("有效时长expires_in：" + expires_in);
        return Access_Token;
    }

    /**
     * 模板消息推送
     * @param openid
     * @return
     */
    public String push(String openid) {
        RestTemplate restTemplate = new RestTemplate();
        //这里简单起见我们每次都获取最新的access_token（时间开发中，应该在access_token快过期时再重新获取）
        String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + getAccessToken();
        //拼接推送的模版
        WxMssVo wxMssVo = new WxMssVo();
        wxMssVo.setTouser("oH3Ol5NtxEJY-2b6qcfYslVirBbw");//用户的openid（要发送给那个用户，通常这里应该动态传进来的）
        wxMssVo.setTemplate_id("8FABo7ciTlDelK9lF73o_cIxNUZ0ZPbVycIzt_NSM-E");//订阅消息模板id
        wxMssVo.setPage("pages/index/index");

        Map<String, TemplateData> m = new HashMap<>(3);
        m.put("thing1", new TemplateData("小程序入门课程"));
        m.put("thing4", new TemplateData("杭州浙江大学"));
        m.put("thing5", new TemplateData("第一章第一节"));
        m.put("time2",new TemplateData("2021年6月18日 18:00:00"));
        m.put("time3",new TemplateData("2020年12月16日 20:00:00"));
        wxMssVo.setData(m);
        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(url, wxMssVo, String.class);
        return responseEntity.getBody();
    }
}
