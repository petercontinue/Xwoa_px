package com.xwcloud.cloud.caiwu.Controller.zhaosheng;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.caiwu.Service.IPxkeshistuteachertableService;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.Vo.xflvVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping("/shujutongji/xvfei")
@Api(tags = "招生统计")
public class RenewController {

    @Autowired
    IPxkeshistuteachertableService iPxkeshistuteachertableService;

    @RequestMapping(value = "/getRenewPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取续费率")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "datesoe", value = "起止日期筛选"),
    })
    public AjaxJson getRenewPage(HttpServletRequest request, HttpServletResponse respons,
                                 @RequestParam(required = false, defaultValue = "10") long size,
                                 @RequestParam(required = false, defaultValue = "1") long current,
                                 @RequestParam(required = false) Long campusID,
                                 @RequestParam(required = false) String staffName,
                                 String datesoe, Integer type
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<HashMap<String, String>> page = new Page<>(current, size);

        QueryWrapper queryWrapper = new QueryWrapper();
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (campusID != 0) {
            queryWrapper.eq("a.campusID", campusID);
        }
        if (StringUtils.isNotBlank(staffName)) {
            queryWrapper.like("a.staffName", staffName);
        }
        if (type == 1) {
            String[] s = (datesoe + " 23:59:59").split("_");
            queryWrapper.between("c.haveclassDate", s[0], s[1]);

            queryWrapper1.between("o.qiandandate", s[0], s[1]);
        } else {
            queryWrapper1.eq("1", 1);
        }


        Page<xflvVo> getallshuju = iPxkeshistuteachertableService.getallshuju(page, queryWrapper, queryWrapper1);
        ajaxJson.setObj(getallshuju);

        return ajaxJson;
    }

}
