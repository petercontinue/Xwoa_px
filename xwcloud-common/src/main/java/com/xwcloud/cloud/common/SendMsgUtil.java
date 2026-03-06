package com.xwcloud.cloud.common;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class SendMsgUtil {

    public static Mono<Void> sendMsg(ServerHttpResponse response, AjaxJson ajaxJson) {
        // 将信息封装为response信息
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] data = null;
        try {
            data = objectMapper.writeValueAsBytes(ajaxJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        DataBuffer buffer = response.bufferFactory().wrap(data);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }

    public static Mono<ServerResponse> sendMsgSR(ServerHttpResponse response, AjaxJson ajaxJson) {
        // 将信息封装为response信息
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] data = null;
        try {
            data = objectMapper.writeValueAsBytes(ajaxJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        DataBuffer buffer = response.bufferFactory().wrap(data);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return ServerResponse.status(200).body(Mono.just(buffer), DataBuffer.class);
    }

    public static boolean sendJsonMsg(HttpServletResponse response, boolean isSuccess, String msg,String code){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(200);
        PrintWriter out = null ;
        try{
            JSONObject res = new JSONObject();
            res.put("success",isSuccess);
            res.put("msg",msg);
            res.put("code",code);
            out = response.getWriter();
            out.append(res.toString());
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
