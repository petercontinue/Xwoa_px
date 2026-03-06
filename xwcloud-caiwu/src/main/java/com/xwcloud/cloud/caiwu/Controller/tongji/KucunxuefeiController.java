package com.xwcloud.cloud.caiwu.Controller.tongji;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.caiwu.Service.IPxliushuizhangtableService;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.Sso.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
@RequestMapping("/caiwu/tongji/kucunxuefei")
@Api(tags = "库存学费")
public class KucunxuefeiController {

    @Autowired
    IPxliushuizhangtableService iPxliushuizhangtableService;

    @RequestMapping(value = "/getKucunxuefei", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "库存学费")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = false),
    })
    public AjaxJson getKucunxuefei(HttpServletResponse respons, HttpServletRequest request,
                                   @RequestParam(required = false,defaultValue = "10")long size,
                                   @RequestParam(required = false,defaultValue = "1")long current
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<HashMap<String, String>> page = new Page<>(current, size);
        page = iPxliushuizhangtableService.getKucunxuefei(page,loginUser.getQiyeID());
        ajaxJson.setObj(page);
        return ajaxJson;
    }
}
