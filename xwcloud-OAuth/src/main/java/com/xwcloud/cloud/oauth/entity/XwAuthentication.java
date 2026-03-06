package com.xwcloud.cloud.oauth.entity;


import com.xwcloud.cloud.model.Sso.LoginUser;
import lombok.Data;

import java.util.Map;

/**
 * 自定义认证信息类
 */
@Data
public class XwAuthentication {
    private String authType;
    private String username;
    private Map<String,String[]> authParameters;
    private LoginUser loginUser;

    public LoginUser getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(LoginUser loginUser) {
        this.loginUser = loginUser;
    }

    public String getAuthParameter(String paramter){
        String[] values = this.authParameters.get(paramter);
        if(values != null && values.length > 0){
            return values[0];
        }
        return null;
    }

    public Map<String, String[]> getAuthParameters() {
        return authParameters;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAuthParameters(Map<String, String[]> authParameters) {
        this.authParameters = authParameters;
    }
}
