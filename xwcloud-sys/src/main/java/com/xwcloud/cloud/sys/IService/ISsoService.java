package com.xwcloud.cloud.sys.IService;

import com.xwcloud.cloud.sys.FallbackService.SsoServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "xwcloud-sso", fallback = SsoServiceImpl.class)
public interface ISsoService {
    @RequestMapping(value = "/test/hi")
    @ResponseBody
    String test();
}
