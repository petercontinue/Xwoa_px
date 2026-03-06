package com.xwcloud.cloud.overall.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.xwcloud.cloud.overall.Interceptor.OverallInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@EnableWebMvc
@Configuration("XwConfing")
public class XwCloudConfig implements WebMvcConfigurer {

    @Bean
    public OverallInterceptor OverallInterceptor() {
        OverallInterceptor overallInterceptor = new OverallInterceptor();
        return overallInterceptor;
    }



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //配置拦截器
        // 网页登录时不拦截
        String[] excludeLogins = {"/error","/Login","/getVerificationCode",
                "/swagger-ui.html","/webjars/**","/swagger-resources/**","/v2/**",
                "/csrf","/doc.html","/captcha.jpg","/getJigouListData","/WechatThird"
        };
        registry.addInterceptor(OverallInterceptor()).addPathPatterns("/**").excludePathPatterns(excludeLogins);// 公共拦截器
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        /**
         * 序列换成json时,将所有的long变成string
         * 因为js中得数字类型不能包含所有的java long值
         */
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(jackson2HttpMessageConverter);
    }
}
