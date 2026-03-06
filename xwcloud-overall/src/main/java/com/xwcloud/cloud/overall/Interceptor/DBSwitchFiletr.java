package com.xwcloud.cloud.overall.Interceptor;

//import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
//import com.baomidou.dynamic.datasource.creator.DruidDataSourceCreator;
//import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.xwcloud.cloud.common.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//@Component
public class DBSwitchFiletr implements Filter {

//    @Value("${xw.oaDB}")
//    String OaDB;
//    @Value("${xw.oaUsername}")
//    String oaUsername;
//    @Value("${xw.oaPassword}")
//    String oaPassword;
//    @Value("${xw.oaDriver}")
//    String oaDriver;
//
//    @Autowired
//    DataSource dataSource;
//    @Autowired
//    DynamicRoutingDataSource dynamicRoutingDataSource;
//    @Autowired
//    DruidDataSourceCreator dataSourceCreator;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        XwHttpServletRequest request=new XwHttpServletRequest((HttpServletRequest) servletRequest);
//// 判断是否是由网关过来的请求,并且写入登录用户信息到请求头中
//        String requestUri = request.getRequestURI();
//        String contextPath = request.getContextPath();
//        AntPathMatcher matcher = new AntPathMatcher();
//        String[] ignorePath = {"/swagger-ui.html","/webjars/**","/swagger-resources/**","/v2/*","/csrf"};
//        for (int i = 0; i < ignorePath.length; i++) {
//            if (matcher.match(ignorePath[i],requestUri)){
//                filterChain.doFilter(servletRequest,servletResponse);
//                return;
//            }
//        }
//        // 分片上传不过滤
//        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
//            System.out.println("Func:分片上传放行");
//            filterChain.doFilter(request,servletResponse);
//            return;
//        }
//        // 获取token
//        String[] tokens = request.getHeader("authorization").split(" ");
//        if (tokens.length<0){
//            SendMsgUtil.sendJsonMsg((HttpServletResponse) servletResponse,false,"overall:没有获取到token","N");
//            return;
//        }
//        String token = tokens[1];
//        Map<String, Object> tokenInfo = JwtUtils.extractInfo(token,"xw_key");
//        String qiyeID = tokenInfo.get("qiyeID").toString();
//
//
//        Map<String, String[]> objectMap = request.getParameterMap();
//        DBUtil dbUtil = null;
//        try {
//            dbUtil = new DBUtil(OaDB, oaUsername, oaPassword,oaDriver);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        List<Map<String, Object>> dbName = null;
//        try {
//            dbName = dbUtil.excuteQueryToList("select id,dbName from kehu where id=? ",qiyeID);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        dbUtil.close();
//        String DbName =dbName.get(0).get("dbName").toString();
//        String DbQiyeid = dbName.get(0).get("id").toString();
//        System.out.println("数据库是:"+DbName);
//        // 将目前的数据源全部删除,添加新数据源
//        // 使用时需要在各个微服务的ServiceImpl类上添加@DS("user")
//        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
//        // 获取缓存中的连接
//        Set<String> dataSourceSet = ds.getCurrentDataSources().keySet();
//        boolean isAddDb= true;
//        for (String dataName: dataSourceSet){
//            if (dataName.equals(DbQiyeid)){ // 如果缓存的连接没中有企业对应的连接,就不需要添加
//                isAddDb=false;
//            }
//        }
//        if (isAddDb){// 如果需要添加
//            DataSourceProperty dataSourceProperty = new DataSourceProperty();
//            dataSourceProperty.setDriverClassName("com.mysql.cj.jdbc.Driver");
//            dataSourceProperty.setPassword("123456");
//            dataSourceProperty.setUrl("jdbc:mysql://"+DbName+"?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8&useSSL=false");
//            dataSourceProperty.setUsername("root");
//            dataSourceProperty.getDruid().setMaxPoolPreparedStatementPerConnectionSize(2);
//            dataSourceProperty.getDruid().setRemoveAbandoned(true);
//            dataSourceProperty.getDruid().setRemoveAbandonedTimeoutMillis(300);
//            dataSourceProperty.getDruid().setResetStatEnable(true);
//            dataSourceProperty.getDruid().setMaxActive(2);
//            DataSource dataSource = dataSourceCreator.createDataSource(dataSourceProperty);
//            ds.addDataSource(DbQiyeid, dataSource);
//        }
//        request.addHeader("DBname",DbQiyeid);
//        filterChain.doFilter(request,servletResponse);
    }
}
