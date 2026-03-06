package com.xwcloud.cloud.sys.FallbackService;

import com.xwcloud.cloud.sys.IService.ISsoService;
import org.springframework.stereotype.Component;

@Component
public class SsoServiceImpl implements ISsoService {

    @Override
    public String test() {
        return "tingle";
    }
}
