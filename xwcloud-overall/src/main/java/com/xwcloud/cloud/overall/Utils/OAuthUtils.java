package com.xwcloud.cloud.overall.Utils;

import com.xwcloud.cloud.common.JwtUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.Map;

/**
 * 因为不是所有项目的通用工具,是根据项目使用的验证框架使用的,所以写在这里
 */
public class OAuthUtils {

    public static Map<String,Object> getJwtTokenInfo(Authentication authentication,String key){
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        String token= details.getTokenValue();
        Map<String,Object> objectMap = null;
        try {
            objectMap = JwtUtils.extractInfo(token,key);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return objectMap;
    }
}
