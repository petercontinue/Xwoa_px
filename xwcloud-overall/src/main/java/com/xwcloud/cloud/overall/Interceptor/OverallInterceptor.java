package com.xwcloud.cloud.overall.Interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.JwtUtils;
import com.xwcloud.cloud.common.SendMsgUtil;
import com.xwcloud.cloud.common.redis.RedisUtil;
import com.xwcloud.cloud.model.Sso.LoginUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class OverallInterceptor implements HandlerInterceptor {

//    @Autowired
//    RedisUtil redisUtil;

    /**
     * 在业务处理器处理请求之前被调用
     * 如果返回false
     * 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * 如果返回true
     * 执行下一个拦截器,直到所有的拦截器都执行完毕
     * 再执行被拦截的Controller
     * 然后进入拦截器链,
     * 从最后一个拦截器往回执行所有的postHandle()
     * 接着再从最后一个拦截器往回执行所有的afterCompletion()
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断是否是由网关过来的请求,并且写入登录用户信息到请求头中
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        AjaxJson ajaxJson = new AjaxJson();
        // 分片上传不过滤
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            System.out.println("Func:分片上传放行");
            return true;
        }
        String isGateway = null;
        try {
            isGateway=request.getHeader("isGateway");
        }catch (Exception e){
            e.printStackTrace();
        }

        if (StringUtils.isBlank(isGateway)||!isGateway.equals("Y")) {
            // 不是网关过来的
            SendMsgUtil.sendJsonMsg(response,false,"overall:非法请求,未经过网关验证","N");
            return false;
        }
        String[] baimindang={"/weixin"};
        for (int i = 0; i < baimindang.length; i++) {
            if (requestUri.contains(baimindang[i])){
                return true;
            }
        }
        String[] baimindang1={"/WechatThird"};
        for (int i = 0; i < baimindang1.length; i++) {
            if (requestUri.contains(baimindang1[i])){
                return true;
            }
        }
        String[] baimindang2={"/wechatThirdAuth"};
        for (int i = 0;i<baimindang2.length;i++){
            if(requestUri.contains(baimindang2[i])){
                return true;
            }
        }
        // 获取token
        String[] tokens = request.getHeader("authorization").split(" ");
        if (tokens.length<0){
            SendMsgUtil.sendJsonMsg(response,false,"overall:没有获取到token","N");
            return false;
        }
        String token = tokens[1];
        Map<String, Object> tokenInfo = JwtUtils.extractInfo(token,"xw_key");
        Object obj=  tokenInfo.get("loginUser");
        ObjectMapper objectMapper = new ObjectMapper();
        LoginUser loginUser =objectMapper.convertValue(obj, LoginUser.class);
        request.setAttribute("loginUser",loginUser);
        return true;
    }

    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
     * 可在modelAndView中加入数据，比如当前时间
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
     * <p>
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
