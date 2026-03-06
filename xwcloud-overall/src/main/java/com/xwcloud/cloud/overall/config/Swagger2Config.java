package com.xwcloud.cloud.overall.config;


import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.base.Predicates;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class Swagger2Config {
    //swagger-bootstrap-ui默认访问地址是：http://${host}:${port}/doc.html
    //https://192.168.2.8:8100/doc.html

//    @Value("${xw.auth_server}")
//    private String AUTH_SERVER;

    /**
     * 创建RestApi 并包扫描controller
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())// 对所有api进行监控
                //设置basePackage会将包下的所有被@Api标记类的所有方法作为api
                .apis(RequestHandlerSelectors.basePackage("com.xwcloud.cloud"))
                //只有标记了@ApiOperation的方法才会暴露出给swagger
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                //不显示错误的接口地址
                //.paths(Predicates.not(PathSelectors.regex("/error.*")))//错误路径不监控
                .build();
    }

    /**
     * 创建Swagger页面 信息
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("校君宝11接口文档")
                .description("API 描述")
                .version("1.0")
                .build();
    }
//
//
//    @Bean
//    public Docket docket(){
//        ParameterBuilder authorizationPar = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<Parameter>();
//        authorizationPar.name("Authorization").description("Authorization")
//                .modelRef(new ModelRef("string")).parameterType("header")
//                .required(false).build();
//        pars.add(authorizationPar.build());
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(getApiInfo())
////                .securitySchemes(Collections.singletonList(securityScheme()))
////                .securityContexts(Collections.singletonList(securityContext()))
//                .select()
//                //设置basePackage会将包下的所有被@Api标记类的所有方法作为api
//                .apis(RequestHandlerSelectors.basePackage("com.xwcloud.cloud"))
//                //只有标记了@ApiOperation的方法才会暴露出给swagger
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .paths(PathSelectors.any())
//                .build()
//                .globalOperationParameters(pars)
//                ;
//    }
//
//    private ApiInfo getApiInfo(){
//        return new ApiInfoBuilder()
//                // 页面标题
//                .title("校君宝11接口文档")
//                // 版本号
//                .version("1.0")
//                // 描述
//                .description("API 描述")
//                .build();
//    }
//
//    private List<ApiKey> securitySchemes() {
//        // 在请求头header添加一个名为Authorization的token
//        return Collections.singletonList(new ApiKey(HttpHeaders.AUTHORIZATION, "token", "header"));
//    }

    /**
     * 这个类决定了你使用哪种认证方式，我这里使用密码模式
     * 其他方式自己摸索一下，完全莫问题啊
     */
//    private SecurityScheme securityScheme() {
//        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant(AUTH_SERVER);
//
//        return new OAuthBuilder()
//                .name("web")
//                .grantTypes(Collections.singletonList(grantType))
//                .scopes(Arrays.asList(scopes()))
//                .build();
//    }

    /**
     * 这里设置 swagger2 认证的安全上下文
     */
//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .securityReferences(Collections.singletonList(new SecurityReference("web", scopes())))
//                .forPaths(PathSelectors.any())
//                .build();
//    }

    /**
     * 这里是写允许认证的scope
     */
//    private AuthorizationScope[] scopes() {
//        return new AuthorizationScope[]{
//                new AuthorizationScope("all", "All scope is trusted!")
//        };
//    }

}
