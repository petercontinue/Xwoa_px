package com.xwcloud.cloud.oauth.Controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@Api(tags = "获取用户信息并验证token")
@Slf4j
public class UserController {

    @RequestMapping(value = "/user")
    @ResponseBody
    public Object userInfo(Principal user, Authentication authentication) {

        return  user;
    }
}
