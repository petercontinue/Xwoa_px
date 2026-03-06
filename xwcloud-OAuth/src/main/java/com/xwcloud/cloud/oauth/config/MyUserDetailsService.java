package com.xwcloud.cloud.oauth.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xwcloud.cloud.common.redis.RedisUtil;
import com.xwcloud.cloud.model.OA.OaKehu;
import com.xwcloud.cloud.model.OA.OaStaff;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.entity.Pxcampustable;
import com.xwcloud.cloud.model.entity.Pxstafftable;
import com.xwcloud.cloud.model.entity.WscUser;
import com.xwcloud.cloud.model.log.Logxjbtable;
import com.xwcloud.cloud.oauth.Service.*;
import com.xwcloud.cloud.oauth.entity.XwAuthentication;
import com.xwcloud.cloud.oauth.entity.XwAuthenticationContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    ISsoPxstafftableService iSsoPxstafftableService;

    @Autowired
    IPxstutableService iPxstutableService;
    @Autowired
    IWscUserService iWscUserService;

    @Autowired
    IOaStaffService iOaStaffService;

    @Autowired
    IPxcampustableService iPxcampustableService;

    @Autowired
    IOaKehuService iOaKehuService;

    @Autowired
    ILogxjbtableService iLogxjbtableService;

    @Autowired
    IPxstafftableService iPxstafftableService;



    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        XwAuthentication xwAuthentication = XwAuthenticationContext.get();
        if (xwAuthentication == null) {
            throw new UsernameNotFoundException("没有经过拦截器验证");
        }
        // 数据库读取用户信息
//        if (xwAuthentication.getAuthParameter("logintype").equals("1")) {//学员登录
//            QueryWrapper queryWrapper = new QueryWrapper();
//            queryWrapper.eq("parentTel", xwAuthentication.getUsername());
//            queryWrapper.eq("qiyeID", xwAuthentication.getAuthParameter("qiyeID"));
//            queryWrapper.eq("buxiStateID", "2");  //补习状态,1意向，2在读，3停课，4结课，5退费，6休眠，7新签待审批
//            queryWrapper.eq("activity", "2");     //activity 学生账号登录状态，1.冻结2.正常
//            Pxstutable stutable = iPxstutableService.getStuInfoData(queryWrapper);
//            if (stutable == null) {
//                throw new CustomOauthException("用户名不存在");
//                //throw new UsernameNotFoundException("用户名不存在");
//            }
//            String passwd = xwAuthentication.getAuthParameter("password");
//            if (!new BCryptPasswordEncoder().matches(passwd, stutable.getPasswd())) {
//                throw new CustomOauthException("密码不正确");
//                //throw new BadCredentialsException("密码不正确");
//            }
//            LoginUser loginUser = new LoginUser();
//            loginUser.setCampusID(stutable.getCampusID());
//            loginUser.setQiyeID(stutable.getQiyeID());
//            loginUser.setStaffID(stutable.getId());
//            loginUser.setStaffTel(stutable.getParentTel());
//            loginUser.setStaffName(stutable.getStuName());
//            loginUser.setWscUserID(1352165054480678915L);
//            loginUser.setLoginType(1);
//            xwAuthentication.setLoginUser(loginUser);
//            // 获取用户拥有的权限
//            List<GrantedAuthority> authorities = new ArrayList<>();
//            //authorities.add(new SimpleGrantedAuthority("ROLE_aaa"));
//            return new User(stutable.getParentTel(), stutable.getPasswd(), authorities);
//        }
        if (xwAuthentication.getAuthParameter("logintype").equals("1")) {//OA登录
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("stafftel", xwAuthentication.getUsername());
            queryWrapper.eq("staffstate", "1");    //staffState 员工状态 1正常，2冻结，3离职，默认值1
            OaStaff staff = iOaStaffService.getOne(queryWrapper);
            if (staff == null) {
                throw new UsernameNotFoundException("用户名不存在");
            }
            String passwd = xwAuthentication.getAuthParameter("password");
            if (!new BCryptPasswordEncoder().matches(passwd, staff.getPasswd())) {
                throw new BadCredentialsException("密码不正确");
            }
            LoginUser loginUser = new LoginUser();
            BeanUtils.copyProperties(staff, loginUser);
            loginUser.setStaffID(staff.getId());
            loginUser.setLoginType(1);
            loginUser.setStaffPostID(staff.getStaffpostID());
            xwAuthentication.setLoginUser(loginUser);
            // 获取用户拥有的权限
            List<GrantedAuthority> authorities = new ArrayList<>();
            //authorities.add(new SimpleGrantedAuthority("ROLE_aaa"));
            return new User(staff.getStafftel(), staff.getPasswd(), authorities);
        } else if (xwAuthentication.getAuthParameter("logintype").equals("2")) {//教师PC端登录
            //先校验验证码
            String verificationCode = xwAuthentication.getAuthParameter("vcode").split("_")[1].toUpperCase();
            String vcodeKey = xwAuthentication.getAuthParameter("vcode").split("_")[0];
//            if (!verificationCode.equals("123456")){
//                throw new CustomOauthException("验证码错误");
//                //throw new OAuth2Exception("验证码错误");
//            }
            String redisInVeriCode = redisUtil.get(vcodeKey).toString();
            if (!verificationCode.equals(redisInVeriCode)) {
                throw new UsernameNotFoundException("验证码错误");
            }

            String qiyeID = xwAuthentication.getAuthParameter("qiyeID");

            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("staffTel", xwAuthentication.getUsername());
            queryWrapper.eq("staffState", "1");    //staffState 员工状态 1正常，2冻结，3离职，默认值1
            queryWrapper.eq("qiyeID", qiyeID);
            Pxstafftable staff = iSsoPxstafftableService.getOne(queryWrapper);
            if (staff == null) {
                throw new UsernameNotFoundException("用户名不存在");
            }
            String passwd = xwAuthentication.getAuthParameter("password");
            if (!new BCryptPasswordEncoder().matches(passwd, staff.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }

            OaKehu oaKehu = iOaKehuService.getById(Long.valueOf(qiyeID));
            Pxcampustable campus = iPxcampustableService.getById(staff.getCampusID());
            Integer kehuType = oaKehu.getKehuType();
            //客户类别：1意向客户，2已购买客户(仍有效)，3已退费客户，4没续费了
            if (kehuType == 1) {
                if (campus.getNextPayTime().getTime() < (new Date()).getTime()) {
                    throw new UsernameNotFoundException("试用账号已过期，请联系客服帮您试用续期！");
                }
            } else if (kehuType == 2) {
                if (campus.getNextPayTime().getTime() < (new Date()).getTime()) {
                    throw new UsernameNotFoundException("系统已到期，请续费后再使用");
                }
                //kehuUseState 客户使用状态：1使用中，2未使用，3暂时停用
                if (oaKehu.getKehuUseState() == 3) {
                    throw new UsernameNotFoundException("系统当前处于停用状态，请联系客服");
                }
            } else {
                throw new UsernameNotFoundException("请联系客服续费后再使用");
            }

            LoginUser loginUser = new LoginUser();
            BeanUtils.copyProperties(staff, loginUser);
            loginUser.setStaffID(staff.getId());
            loginUser.setLoginType(2);
            loginUser.setStaffPostID(staff.getStaffPostID());
            xwAuthentication.setLoginUser(loginUser);

            Logxjbtable log = new Logxjbtable();
            log
                    .setSystemContent("用户登录系统")
                    .setFuncName("/Oauth/config/aException/MyUserDetailsService/loadUserByUsername")
                    .setStaffID(staff.getId())
                    .setStaffName(staff.getStaffName())
                    .setLogType(3)
                    .setAddTime(new Date())
                    .setQiyeID(oaKehu.getId());
            iLogxjbtableService.save(log);
            // 获取用户拥有的权限
            List<GrantedAuthority> authorities = new ArrayList<>();
            //authorities.add(new SimpleGrantedAuthority("ROLE_aaa"));
            return new User(staff.getStaffTel(), staff.getPassword(), authorities);
        }
//        if (xwAuthentication.getAuthParameter("logintype").equals("4")) {//教师移动端登录
//            QueryWrapper queryWrapper = new QueryWrapper();
//            queryWrapper.eq("staffTel", xwAuthentication.getUsername());
//            queryWrapper.eq("staffState", "1");    //staffState 员工状态 1正常，2冻结，3离职，默认值1
//            queryWrapper.eq("qiyeID", xwAuthentication.getAuthParameter("qiyeID"));
//            Pxstafftable staff = iSsoPxstafftableService.getOne(queryWrapper);
//            if (staff == null) {
//                throw new UsernameNotFoundException("用户名不存在");
//            }
//            String passwd = xwAuthentication.getAuthParameter("password");
//            if (!new BCryptPasswordEncoder().matches(passwd, staff.getPassword())) {
//                throw new BadCredentialsException("密码不正确");
//            }
//            LoginUser loginUser = new LoginUser();
//            BeanUtils.copyProperties(staff, loginUser);
//            loginUser.setStaffID(staff.getId());
//            loginUser.setLoginType(2);
//            loginUser.setStaffPostID(staff.getStaffPostID());
//            xwAuthentication.setLoginUser(loginUser);
//            // 获取用户拥有的权限
//            List<GrantedAuthority> authorities = new ArrayList<>();
//            //authorities.add(new SimpleGrantedAuthority("ROLE_aaa"));
//            return new User(staff.getStaffTel(), staff.getPassword(), authorities);
//        }

        else {
            //小程序端 openID登录 logintype=3
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("openid", xwAuthentication.getUsername());
            queryWrapper.eq("isdongjie", 0);   //isdongjie 0未冻结，1已冻结
            queryWrapper.eq("qiyeID", xwAuthentication.getAuthParameter("qiyeID"));
            WscUser wscUser = iWscUserService.getOne(queryWrapper);
            if (wscUser == null) {
                throw new UsernameNotFoundException("用户不存在");
            }
            List<HashMap<String,Object>> staffinfo= iPxstafftableService.getappteaInfo(new QueryWrapper<Pxstafftable>()
                    .eq("a.qiyeID",wscUser.getQiyeID())
                    .eq("a.staffTel", wscUser.getPhoneNumber())
            );
            LoginUser loginUser = new LoginUser();
            loginUser.setStaffName(wscUser.getNickName());
            loginUser.setOpenid(wscUser.getOpenid());
            loginUser.setWscUserID(wscUser.getId());
            loginUser.setLoginType(3);
            loginUser.setQiyeID(wscUser.getQiyeID());
            loginUser.setStaffTel(wscUser.getPhoneNumber());
            loginUser.setStaffPostID(Long.valueOf(staffinfo.get(0).get("staffPostID").toString()));
            loginUser.setStaffID(Long.valueOf(staffinfo.get(0).get("id").toString()));
            loginUser.setCampusID(Long.valueOf(staffinfo.get(0).get("campusID").toString()));
            xwAuthentication.setLoginUser(loginUser);
            // 获取用户拥有的权限
            List<GrantedAuthority> authorities = new ArrayList<>();
            //authorities.add(new SimpleGrantedAuthority("ROLE_aaa"));
            //return new User(xwAuthentication.getUsername(), "123456", authorities);
            //return new User(wscUser.getOpenid(), "123456", authorities);


            return new User(wscUser.getOpenid(), new BCryptPasswordEncoder().encode("123456"), authorities);

        }

    }
}
