package com.xwcloud.cloud.caiwu.Controller.tongji;


import com.xwcloud.cloud.caiwu.Service.IPxqiandaninfotableService;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.Sso.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/caiwu/tongji/yejitongbihuanbi")
@Api(tags = "业绩同比环比")
public class YejitongbihuanbiController {

    @Autowired
    IPxqiandaninfotableService iPxqiandaninfotableService;

    @RequestMapping(value = "/getYejitongbihuanbi", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "业绩同比环比")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "moneyStyle", value = "1新签；2续费；3 退费 4 转送 5 换课换出 6 换课得到"),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "startYear", value = "开始年份", required = false),
            @ApiImplicitParam(name = "endYear", value = "结束年份", required = false),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
    })
    public AjaxJson getYejitongbihuanbiList(HttpServletResponse respons, HttpServletRequest request,
                                            String moneyStyle, // 1新签；2续费；3 退费 4 转送 5 换课换出 6 换课得到
                                            @RequestParam(required = false) String campusID,
                                            @RequestParam(required = false) String startYear,
                                            @RequestParam(required = false) String endYear
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        String[] strings = moneyStyle.split(",");
        List<HashMap<String,String>> list = iPxqiandaninfotableService.getYejitongbihuanbiList(strings, campusID, startYear, endYear,loginUser.getQiyeID());
        ajaxJson.setObj(list);
        return ajaxJson;
    }
}
