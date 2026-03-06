package com.xwcloud.cloud.overall.Interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xwcloud.cloud.common.HTTPUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class FeignRequestInterceptor implements RequestInterceptor {

//    @Value("${xw.auth_server}")
    String authServer;
    @Override
    public void apply(RequestTemplate requestTemplate) {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        assert attributes != null;
//        HttpServletRequest request = attributes.getRequest();
//        HashMap<String,String> map =new HashMap<>();
//        map.put("grant_type","client_credentials");
//        map.put("username","admin");
//        map.put("password","123456");
//        map.put("client_id","feign");
//        map.put("scope","all");
//        map.put("client_secret","123456");
//        JSONObject json = null;
//        try {
//            String body = HTTPUtils.doPost(authServer,map,3000,3000);
//             json = JSON.parseObject(body);
//            System.out.println(body);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // 设置请求头
//        Enumeration<String> headerNames = request.getHeaderNames();
//        if (headerNames != null) {
//            while (headerNames.hasMoreElements()) {
//                String name = headerNames.nextElement();
//                String value = request.getHeader(name);
//                if (name.toLowerCase().equals("authorization")){
//                    requestTemplate.header(name, "Bearer "+json.getString("access_token"));
//                }else {
//                    requestTemplate.header(name, value);
//                }
//            }
//        }

       // requestTemplate.header("Authorization", "Bearer "+json.getString("access_token"));


    }
}
