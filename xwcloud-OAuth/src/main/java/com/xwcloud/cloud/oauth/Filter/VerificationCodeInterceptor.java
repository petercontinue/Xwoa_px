package com.xwcloud.cloud.oauth.Filter;

import com.xwcloud.cloud.common.redis.RedisUtil;
import com.xwcloud.cloud.oauth.entity.XwAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Component
public class VerificationCodeInterceptor {

    @Autowired
    private RedisUtil redisUtil;

    private String InterceptorName="password";

    public void  verification(XwAuthentication xwAuthentication,HttpServletRequest  servletRequest){
        if (xwAuthentication.getAuthType().contains(InterceptorName)){

            String verificationCode = xwAuthentication.getAuthParameter("vcode").toUpperCase();
//            if (!verificationCode.equals("123456")){
//                throw new CustomOauthException("验证码错误");
//                //throw new OAuth2Exception("验证码错误");
//            }
            //String sessionVeriCode = getSession(servletRequest);
            String redisInVeriCode = redisUtil.get("veriCode").toString();
            if (!verificationCode.equals(redisInVeriCode)){
                throw new OAuth2Exception("验证码错误");
                //throw new ZidongException(401,"验证码错误");

//                AjaxJson ajaxJson = new AjaxJson();
//                ajaxJson.setMsg("验证码错误");
//                ajaxJson.setCode("401");
//                ajaxJson.setSuccess(false);
//                return ajaxJson;
            }
        }
    }
    public static String getSession(HttpServletRequest  request){
        String sessionVeriCode="";
        HttpSession session=request.getSession();//获取session
        Object name=session.getAttribute("RANDOMREDISKEY");
        System.out.println(name);
        Enumeration enumeration =session.getAttributeNames();//获取session中所有的键值对
        //遍历enumeration中的键值对
        String[] names=session.getValueNames();
        for(int i=0;i<names.length;i++){
            if(names[i].equals("RANDOMREDISKEY")){
                sessionVeriCode+=session.getValue(names[i])+"@";
                System.out.println(sessionVeriCode);
                break;
            }
        }
        return sessionVeriCode;
    }
}
