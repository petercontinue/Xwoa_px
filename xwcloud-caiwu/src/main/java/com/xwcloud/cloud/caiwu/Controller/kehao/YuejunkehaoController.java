package com.xwcloud.cloud.caiwu.Controller.kehao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.caiwu.Service.IPxkeshistutableService;
import com.xwcloud.cloud.caiwu.Service.IPxpowertableService;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.Sso.LoginUser;
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
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/shujutongji/kehao")
@Api(tags = "课耗统计")
public class YuejunkehaoController {

    @Autowired
    IPxkeshistutableService iPxkeshistutableService;
    @Autowired
    IPxpowertableService iPxpowertableService;

    @RequestMapping(value = "/getYuejunkehaoPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "月均课耗")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "Ym", value = "年月", required = false),
//            @ApiImplicitParam(name="qiyeID",value="企业ID",required=true),
    })
    public AjaxJson getYuejunkehaoPage(HttpServletRequest request,
                                       @RequestParam(required = false, defaultValue = "10") long size,
                                       @RequestParam(required = false, defaultValue = "1") long current,
                                       @RequestParam(required = false) String campusID,
                                       @RequestParam(required = false) String Ym
//            String qiyeID
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Page<HashMap<String, String>> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("a.campusID", campusID);
        }
        if (StringUtils.isNotBlank(Ym)) {
            queryWrapper.eq("DATE_FORMAT(a.haveClassDate,'%Y-%m')", Ym);
        } else {
            String nym = sdf.format(new Date());
            queryWrapper.eq("DATE_FORMAT(a.haveClassDate,'%Y-%m')", nym);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 425);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("c.teacherID", loginUser.getStaffID());
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }


        page = iPxkeshistutableService.getYuejunkehaoPage(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/getJiaoshiyuejunkehaoPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "教师月均课耗")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "Ym", value = "年月", required = false),
//            @ApiImplicitParam(name="qiyeID",value="企业ID",required=true),
    })
    public AjaxJson getJiaoshiyuejunkehaoPage(HttpServletRequest request,
                                              @RequestParam(required = false, defaultValue = "10") long size,
                                              @RequestParam(required = false, defaultValue = "1") long current,
                                              @RequestParam(required = false) String campusID,
                                              @RequestParam(required = false) String Ym
//            String qiyeID
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        AjaxJson ajaxJson = new AjaxJson();
        Page<HashMap<String, Object>> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("d.id", campusID);
        }
        if (StringUtils.isNotBlank(Ym)) {
            queryWrapper.eq("DATE_FORMAT(a.haveClassDate,'%Y-%m')", Ym);
        } else {
            String nym = sdf.format(new Date());
            queryWrapper.eq("DATE_FORMAT(a.haveClassDate,'%Y-%m')", nym);
        }


        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 425);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("b.teacherID", loginUser.getStaffID());
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("c.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("c.campusID", lookPower);
        }

        page = iPxkeshistutableService.getJiaoshiyuejunkehaoPage(page, queryWrapper);

        List<HashMap<String, Object>> records = page.getRecords();
        BigDecimal allmoney = new BigDecimal(0);

        for (int i = 0; i < records.size(); i++) {
            HashMap<String, Object> objs = records.get(i);
            String keshishouru = objs.get("keshishouru").toString();
            BigDecimal one = new BigDecimal(keshishouru);
            allmoney = allmoney.add(one);
        }
        page.setCountId(allmoney.toString());
        ajaxJson.setObj(page);
        return ajaxJson;
    }
}
