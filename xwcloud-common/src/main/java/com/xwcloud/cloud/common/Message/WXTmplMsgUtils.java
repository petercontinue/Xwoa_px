package com.xwcloud.cloud.common.Message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xwcloud.cloud.common.Httprequests;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

public class WXTmplMsgUtils {
    /**
     * Post
     *
     * @param url  url
     * @param json json
     * @return
     */
    public static String doPostJson(String url, String json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                // e.printStackTrace();
                System.out.println("doPostJson异常:" + e);
            }
        }
        return resultString;
    }

    /**
     * 消息发送通知
     *
     * @param templateId 模板id
     * @param openid     openid
     * @param url        url
     * @param paras      模板样式
     * @return
     */
    public static boolean sendWXTmplMsg( String templateId, String openid, String url, List<TemplateParam> paras) {
        boolean flag = false;
        // 获取appid
        String appid = "wx105072705814cae3";
        // 获取sercet
        String sercet = "060f475e084f44d6bdbbc8615203c192";
        Template tem = new Template();
        // 模板id
        tem.setTemplateId(templateId);
        // 颜色---没啥用
        tem.setTopColor("#00DD00");
        // openid
        tem.setToUser(openid);
        // 地址
        tem.setUrl(url);
        tem.setTemplateParamList(paras);
        // 获取token
        String accesstoken = "https://api.weixin.qq.com/cgi-bin/token";
        String content = Httprequests.sendPost(accesstoken,"grant_type=client_credential&appid=" + appid + "&secret=" + sercet);
        // MyHttpUtils.doPost(accesstoken, "","UTF_8")
        // 获取token
        JSONObject jsonObj = JSON.parseObject(content);
        String accessToken = (String) jsonObj.get("access_token");
        // 发送
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken;
        String json = doPostJson(requestUrl, tem.toJSON());
        JSONObject jsonResult = JSON.parseObject(json.toString());
        if (jsonResult != null) {
            // 状态码
            int errorCode = jsonResult.getInteger("errcode");
            // 状态信息
            String errorMessage = jsonResult.getString("errmsg");
            if (errorCode == 0) {
                flag = true;
            } else {
                System.out.println("sendTemplateMsg===>模板消息发送失败:" + errorCode + "," + errorMessage);
                System.out.println(json.toString());
                flag = false;
            }
        }
        return flag;
    }

}
