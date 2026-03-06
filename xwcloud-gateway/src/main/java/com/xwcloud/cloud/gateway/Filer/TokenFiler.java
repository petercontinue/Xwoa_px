package com.xwcloud.cloud.gateway.Filer;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * token拦截器
 */
@Component
public class TokenFiler implements GlobalFilter, Ordered {
//    @Autowired
//    RedisUtil redisUtil;
    @Value("${xw.ignore}")
    String[] IgnorePaths;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestUri = exchange.getRequest().getURI().getRawPath();// 返回原始路径
        String contextPath = exchange.getRequest().getPath().toString();
        //String[] IgnorePaths = new String[]{"/getVerificationCode","/Login","/webjars"};// 忽略不判断的路径
        // 忽略路径列表中的请求
        for (int i = 0; i < IgnorePaths.length; i++) {
            if (requestUri.contains(IgnorePaths[i])){
                return chain.filter(exchange);
            }
        }
        System.out.println(IgnorePaths);
        // 分片上传不过滤
        if (exchange.getRequest().getMethod().name().equalsIgnoreCase("OPTIONS")){
            return chain.filter(exchange);
        }
        // 获取参数中的token
        String token = "";
        String P_token=exchange.getRequest().getQueryParams().getFirst("token");
        String H_token=exchange.getRequest().getHeaders().getFirst("token");
        // 如果传参中有token
        if (StringUtils.isNotBlank(P_token)){
            token=P_token;
        }
        // 如果请求头中有token
        if (StringUtils.isNotBlank(H_token)){
            token=H_token;
        }
        // 如果没有获取到token
        /*if (StringUtils.isBlank(token)){
            // 返回报错信息,要求前台跳转到登录界面
            AjaxJson ajaxJson =new AjaxJson();
            ajaxJson.setMsg("gateway:没有获取到token,请返回登录页面!");
            ajaxJson.setSuccess(false);
            return SendMsgUtil.sendMsg(exchange.getResponse(),ajaxJson);
        }*/
        // 如果在参数中获取到了token
        // 检查token是否过期
        /*if (!redisUtil.hasKey(token)){
            // 如果不存在token说明过期
            // 返回报错信息,要求前台跳转到登录界面
            AjaxJson ajaxJson =new AjaxJson();
            ajaxJson.setMsg("gateway:token已经过期,请重新登录!");
            ajaxJson.setSuccess(false);
            return SendMsgUtil.sendMsg(exchange.getResponse(),ajaxJson);
        }*/
        // 如果存在token,进行合法标识,并且延长token时间
        //向headers中放数据，记得build
        exchange.getRequest().mutate().header("isGateway", "Y").build();
        // 每访问一个接口增加2分钟缓存过期时间
       /* long expiretime = redisUtil.getExpire(token);
        redisUtil.expire(token,expiretime+60*2);*/
        return chain.filter(exchange);
    }

    /**
     * 设置过滤器的执行顺序
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }


}
