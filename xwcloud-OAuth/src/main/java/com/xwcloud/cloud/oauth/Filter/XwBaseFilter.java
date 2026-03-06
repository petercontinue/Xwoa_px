package com.xwcloud.cloud.oauth.Filter;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.DruidDataSourceCreator;
import com.xwcloud.cloud.common.JwtUtils;
import com.xwcloud.cloud.oauth.entity.XwAuthentication;
import com.xwcloud.cloud.oauth.entity.XwAuthenticationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class XwBaseFilter extends GenericFilterBean {

//    @Autowired
//    DataSource dataSource;
//    @Autowired
//    DynamicRoutingDataSource dynamicRoutingDataSource;
//    @Autowired
//    DruidDataSourceCreator dataSourceCreator;

    /*@Autowired
    DbSwitchInterceptor dbSwitchInterceptor;*/

    @Autowired
    VerificationCodeInterceptor verificationCodeInterceptor;

    @Value("${xw.baimingdang}")
    String[] baimingdang;   //白名单

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        XwHttpServletRequest request=new XwHttpServletRequest((HttpServletRequest) servletRequest);
        String requestUri = request.getRequestURI();
        for (int i = 0; i < baimingdang.length; i++) {
            if (requestUri.contains(baimingdang[i])){
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
       // String contextPath = request.getContextPath();
        if (!requestUri.equals("/oauth/check_token")){
            XwAuthentication xwAuthentication = new XwAuthentication();
            xwAuthentication.setAuthType(request.getParameterMap().get("grant_type")[0]);
            xwAuthentication.setAuthParameters(new HashMap<>(request.getParameterMap()));

            xwAuthentication.setUsername(xwAuthentication.getAuthParameter("username"));

            if ("refresh_token".contains(xwAuthentication.getAuthType().toLowerCase())){
                Map<String,Object> map = JwtUtils.extractInfo(xwAuthentication.getAuthParameter("refresh_token"), "xw_key");
                xwAuthentication.setUsername(map.get("user_name").toString());
                xwAuthentication.getAuthParameters().put("qiyeID",new String[]{map.get("qiyeID").toString()});
                XwAuthenticationContext.set(xwAuthentication);
            }else {
                XwAuthenticationContext.set(xwAuthentication);

                String client_id =  request.getParameterMap().get("client_id")[0];
                // 验证码拦截器
                //verificationCodeInterceptor.verification(xwAuthentication,(HttpServletRequest) servletRequest);  //验证码校验
                // 切换数据库拦截器
                // dbSwitchInterceptor.verification(request,xwAuthentication);
            }

            filterChain.doFilter(request, servletResponse);
        }else {
            filterChain.doFilter(request, servletResponse);
        }
        XwAuthenticationContext.remove();
    }
}
