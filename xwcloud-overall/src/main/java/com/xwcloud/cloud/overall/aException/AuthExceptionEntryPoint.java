package com.xwcloud.cloud.overall.aException;

import com.alibaba.fastjson.JSON;
import com.xwcloud.cloud.overall.BizException.CommonEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * tokan校验失败返回信息
 */
@Slf4j
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException)
            throws ServletException, IOException {

//        Map map = new HashMap();
//        map.put("error", "401");
//        String obj = authException.getCause().toString();
//        String msg= authException.getMessage();
//        if(obj.contains("invalid_token")){
//            msg="无效的Token";
//        }
//        //map.put("message", authException.getMessage());
//        map.put("message",msg);
//        map.put("path", request.getServletPath());
//        map.put("timestamp", String.valueOf(new Date().getTime()));
//        response.setContentType("application/json");
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.writeValue(response.getOutputStream(), map);
//        } catch (Exception e) {
//            throw new ServletException();
//        }

        Throwable cause = authException.getCause();
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // CORS "pre-flight" request
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Cache-Control","no-cache");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.addHeader("Access-Control-Max-Age", "1800");
        if (cause instanceof InvalidTokenException) {
            log.error("InvalidTokenException : {}",cause.getMessage());
            //Token无效,即错误的Token
            response.getWriter().write(JSON.toJSONString(ResponseVo.error(CommonEnum.ACCESS_TOKEN_INVALID)));
        }
        else {
            log.error("AuthenticationException : NoAuthentication");
            //没有Token
            response.getWriter().write(JSON.toJSONString(ResponseVo.error(CommonEnum.UNAUTHORIZED)));
        }
    }
}