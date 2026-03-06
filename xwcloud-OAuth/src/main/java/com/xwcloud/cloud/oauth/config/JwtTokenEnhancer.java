package com.xwcloud.cloud.oauth.config;

import com.xwcloud.cloud.oauth.entity.XwAuthentication;
import com.xwcloud.cloud.oauth.entity.XwAuthenticationContext;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class JwtTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        XwAuthentication xwAuthentication = XwAuthenticationContext.get();
        Map<String, Object> additionalInfo  = new HashMap<>();
        additionalInfo .put("loginUser",xwAuthentication.getLoginUser());
        additionalInfo .put("code","200");
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo );
        return accessToken;
    }
}
