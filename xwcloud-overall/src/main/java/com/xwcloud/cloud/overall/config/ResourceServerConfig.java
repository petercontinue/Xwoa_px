package com.xwcloud.cloud.overall.config;


import com.xwcloud.cloud.overall.Interceptor.UserPermissionEvaluator;
import com.xwcloud.cloud.overall.aException.AuthExceptionEntryPoint;
import com.xwcloud.cloud.overall.aException.CustomAccessDeniedHandler;
import com.xwcloud.cloud.overall.aException.CustomAuthExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private CustomAuthExceptionHandler customAuthExceptionHandler;
    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.stateless(false)
//                .accessDeniedHandler(customAuthExceptionHandler)
//                .authenticationEntryPoint(customAuthExceptionHandler);
                .accessDeniedHandler(customAccessDeniedHandler)
                .authenticationEntryPoint(new AuthExceptionEntryPoint());
    }

    /**
     * 注入自定义PermissionEvaluator
     */
    @Bean
    public DefaultWebSecurityExpressionHandler userSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(new UserPermissionEvaluator());
        return handler;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/v2/*").permitAll()
                .antMatchers("/csrf").permitAll()
                .antMatchers("/doc.html").permitAll()
                .antMatchers("/weixin/**").permitAll()
                .antMatchers("/wechatThirdAuth/**").permitAll()
                .antMatchers("/WechatThird/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable().cors()  //允许跨域访问
                .and().formLogin()
        ;
        // 基于Token不需要session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 禁用缓存
        http.headers().cacheControl();
    }

}
