package com.xwcloud.cloud.oauth.config.aException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * Created on 2018/5/24 0024.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
@Component("customWebResponseExceptionTranslator")
public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator {
    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception exception) throws Exception {

        if (exception instanceof OAuth2Exception) {
            OAuth2Exception oAuth2Exception = (OAuth2Exception) exception;
            return ResponseEntity
                    .status(oAuth2Exception.getHttpErrorCode())
                    .body(new CustomOauthException(oAuth2Exception.getMessage()));
        }else if(exception instanceof AuthenticationException){
            AuthenticationException authenticationException = (AuthenticationException) exception;
            //String msg="用户名或密码错误";
            //String msg= authenticationException.getMessage();
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    //.status(HttpStatus.UNAUTHORIZED)
                    //.body(new CustomOauthException(msg));
                    .body(new CustomOauthException(authenticationException.getMessage()));

        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CustomOauthException(exception.getMessage()));


//        OAuth2Exception oAuth2Exception = (OAuth2Exception) exception;
//        return ResponseEntity
//                .status(oAuth2Exception.getHttpErrorCode())
//                .body(new CustomOauthException(oAuth2Exception.getMessage()));
    }
}
