package com.xwcloud.cloud.caiwu;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan({"com.xwcloud.cloud.caiwu.Dao","com.xwcloud.cloud.overall.Dao"})
@ComponentScan({"com.xwcloud.cloud"}) //获取配置文件
@EnableTransactionManagement
@EnableDiscoveryClient
@EnableFeignClients
@EnableAsync
@EnableOAuth2Sso
public class CaiwuApplication {
    public static void main(String[] args) {
        SpringApplication.run(CaiwuApplication.class, args);
    }
}
