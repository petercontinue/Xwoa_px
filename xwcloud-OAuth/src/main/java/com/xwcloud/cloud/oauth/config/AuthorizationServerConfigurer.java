package com.xwcloud.cloud.oauth.config;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.DruidDataSourceCreator;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.xwcloud.cloud.oauth.Service.ISsoPxstafftableService;
import com.xwcloud.cloud.oauth.Store.RedisTokenStore;


import com.xwcloud.cloud.oauth.config.aException.CustomWebResponseExceptionTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;
import java.util.*;


@Configuration
@EnableAuthorizationServer   //使用@EnableAuthorizationServer注解告诉Spring激活authorization server
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    @Value("${xw.client_id}")
    String CLIENT_ID;
    @Value("${xw.client_secret}")
    String CLIENT_SECRET;

    //access_token失效时间
    @Value("${xw.ACCESSTOKENVALIDITYSECONDS}")
    int ACCESSTOKENVALIDITYSECONDS;

    //refresh_token失效时间
    @Value("${xw.REFRESHTOKENVALIDSECONDS}")
    int REFRESHTOKENVALIDSECONDS;


//    @Autowired
//    UserDetailsService userDetailsService;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CustomWebResponseExceptionTranslator customWebResponseExceptionTranslator;


    /**
     * 添加客户端信息
     * 配置appid、appKey、回调地址、token有限期
     * @param clients
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                //withClient和secret是client的client_id和client_secret,相当于appid和appKey
                .withClient(CLIENT_ID)
                .secret(bCryptPasswordEncoder.encode(CLIENT_SECRET))
                //重定向地址
                .redirectUris("http://www.baidu.com")
                //授权类型
                .authorizedGrantTypes("password", "client_credentials", "refresh_token", "authorization_code")
                //scopes是授权范围  .scopes("read", "write", "execute")
                .scopes("all")
                //accessToken有效期
                .accessTokenValiditySeconds(ACCESSTOKENVALIDITYSECONDS)
                //刷新accessToken
                .refreshTokenValiditySeconds(REFRESHTOKENVALIDSECONDS)
        ;
    }

    // 设置token类型
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)throws Exception {
        endpoints.authenticationManager(authenticationManager())   ////配置授权管理认证对象
                //.tokenEnhancer(tokenEnhancerChain)// 添加token增强 redis 版
                //配置tokenStore管理、配置客户端详情
                // .tokenStore(tokenStore())
                .userDetailsService(myUserDetailsService)   //配置加载用户信息的服务
                .pathMapping("/oauth/token","/staff/login")
        ;

//        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        tokenServices.setTokenStore(endpoints.getTokenStore());
//        tokenServices.setSupportRefreshToken(true);
//        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
//        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
//        tokenServices.setAccessTokenValiditySeconds(ACCESSTOKENVALIDITYSECONDS);//token有效期设置60*60*2 2个小时
//        tokenServices.setRefreshTokenValiditySeconds(REFRESHTOKENVALIDSECONDS);//Refresh_token:60*60*12 12个小时
//        endpoints.tokenServices(tokenServices);


        /** 以下是JWT配置  通过TokenEnhancerChain向JWT中添加额外的信息**/
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtTokenEnhancer());   //配置JWT的内容增强器.jwtTokenEnhancer里面把loginUser加进去
        delegates.add(jwtAccessTokenConverter());
        enhancerChain.setTokenEnhancers(delegates);

        endpoints.tokenStore(jwtTokenStore()) //配置JWT令牌存储策略  //redis存储的话，endpoints.tokenStore(tokenStore_Redis())
                .accessTokenConverter(jwtAccessTokenConverter())
                .tokenEnhancer(enhancerChain);


        endpoints.exceptionTranslator(customWebResponseExceptionTranslator);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer)throws Exception {
        // 允许表单认证
        oauthServer.allowFormAuthenticationForClients();
        // 允许check_token访问
        oauthServer.checkTokenAccess("permitAll()");
        //oauthServer.addTokenEndpointAuthenticationFilter(dbSwitchFilter);
    }

    @Bean
    AuthenticationManager authenticationManager() {
        AuthenticationManager authenticationManager = new AuthenticationManager() {

            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                return daoAuhthenticationProvider().authenticate(authentication);
            }
        };
        return authenticationManager;
    }

    @Bean
    public AuthenticationProvider daoAuhthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return daoAuthenticationProvider;
    }

    /**
     * TokenEnhancer：在AuthorizationServerTokenServices 实现存储访问令牌之前增强访问令牌的策略。
     * @return
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            final Map<String, Object> additionalInfo = new HashMap<>();
            UserDetails user = (UserDetails) authentication.getUserAuthentication().getPrincipal();
            String userName = user.getUsername();
            additionalInfo.put("qiyeID","123456");
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }


    @Bean
    public JwtTokenEnhancer jwtTokenEnhancer() {
        return new JwtTokenEnhancer();
    }

//    /**
//     * 配置Token的存储方式；Redis存储
//     */
//    @Autowired
//    private RedisConnectionFactory redisConnectionFactory;
//
//    /**
//     * 配置tokenStore的存储方式是redis存储
//     * @return
//     */
//    @Bean
//    public TokenStore tokenStore_Redis(){
//        return new RedisTokenStore(redisConnectionFactory);
//    }

    //配置Token的存储方式；如果是Redis存储的话，看前面注释的内容
    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * JwtAccessTokenConverter:帮助程序在JWT编码的令牌值和OAuth身份验证信息之间进行转换（在两个方向上），同时充当TokenEnhancer授予令牌的时间。
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey("xw_key");//配置JWT使用的秘钥
        return accessTokenConverter;
    }

}
