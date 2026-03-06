package com.xwcloud.cloud.gateway.Sentinel;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSONObject;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.SendMsgUtil;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class SentinelExceptionHandler implements BlockRequestHandler {
    @Override
    public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable throwable) {
        AjaxJson ajaxJson =new AjaxJson();
        //限流响应
        if (throwable instanceof FlowException) {
            ajaxJson.setMsg("gateway:被限流了,稍等一下");
            ajaxJson.setSuccess(false);
        }
        //服务降级响应
        else if (throwable instanceof DegradeException) {
            ajaxJson.setMsg("gateway:被降级了,请重新请求");
            ajaxJson.setSuccess(false);
        }
        //热点参数限流响应
        else if (throwable instanceof ParamFlowException) {
            ajaxJson.setMsg("gateway:热点参数限流,请重新请求");
            ajaxJson.setSuccess(false);
        }
        //触发系统保护规则响应
        else if (throwable instanceof SystemBlockException) {
            ajaxJson.setMsg("gateway:触发系统保护规则,请重新请求");
            ajaxJson.setSuccess(false);
        }
        //授权规则不通过响应
        else if (throwable instanceof AuthorityException) {
            ajaxJson.setMsg("gateway:授权规则不通过,请重新请求");
            ajaxJson.setSuccess(false);
        }
        //返回固定响应信息
        ServerHttpResponse response = serverWebExchange.getResponse();
        return SendMsgUtil.sendMsgSR(response,ajaxJson);
    }

}
