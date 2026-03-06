package com.xwcloud.cloud.sys.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix="wx")
public class WeiXinConfig {
    private String appID;
    private String mchID;
    private String appsecret;
    private String key;

}
