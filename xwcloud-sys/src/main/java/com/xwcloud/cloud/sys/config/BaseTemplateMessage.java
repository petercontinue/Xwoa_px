package com.xwcloud.cloud.sys.config;

import lombok.Data;

@Data
public class BaseTemplateMessage<T> {
    private String touser;
    private String template_id;
    private String url;
    private T data;
}
