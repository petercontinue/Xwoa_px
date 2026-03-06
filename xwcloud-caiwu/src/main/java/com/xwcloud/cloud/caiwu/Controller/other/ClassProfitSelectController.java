package com.xwcloud.cloud.caiwu.Controller.other;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.caiwu.Service.IPxkeshistutableService;
import com.xwcloud.cloud.caiwu.Service.IPxpowertableService;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.ExportExcel;
import com.xwcloud.cloud.model.Sso.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/shujutongji/other")
@Api(tags = "班课收益查询")
public class ClassProfitSelectController {

    @Autowired
    private IPxkeshistutableService pxkeshistutableService;
    @Autowired
    IPxpowertableService iPxpowertableService;

    @GetMapping("/getClassProfit")
    @ResponseBody
    @ApiOperation(value = "班课收益查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = true),
            @ApiImplicitParam(name = "current", value = "页码", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID"),
            @ApiImplicitParam(name = "small", value = "区间中小的部分"),
            @ApiImplicitParam(name = "big", value = "区间中大的部分"),
    })
    public AjaxJson getClassProfit(HttpServletRequest request, long size, long current, Long campusID, BigDecimal small, BigDecimal big) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper<HashMap<String, Object>> wrapper = new QueryWrapper<>();
        wrapper.eq("qiyeID", loginUser.getQiyeID());
        if (!ObjectUtils.isEmpty(campusID)) {
            wrapper.eq("campusID", campusID);
        }
        if (!ObjectUtils.isEmpty(small) && !ObjectUtils.isEmpty(big)) {
            wrapper.between("priceSum", small, big);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 444);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            wrapper.eq("stu.banzhurenTeacherID", loginUser.getStaffID());
        } else if (lookPower.equals("-1")) {//所在校区权限
            wrapper.eq("campusID",loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            wrapper.in("campusID",lookPower);
        }
        ajaxJson.setObj(pxkeshistutableService.getClassProfit(new Page<HashMap<String, Object>>(current, size), wrapper));
        return ajaxJson;
    }

    @GetMapping("/exportClassProfit")
    @ResponseBody
    @ApiOperation(value = "导出班课收益")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID"),
            @ApiImplicitParam(name = "datesoe", value = "最后一次上课的日期区间：start_end"),
    })
    public void exportClassProfit(HttpServletRequest request, HttpServletResponse response, Long campusID, String datesoe) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<HashMap<String, Object>> wrapper = new QueryWrapper<>();
        wrapper.eq("qiyeID", loginUser.getQiyeID());
        if (!ObjectUtils.isEmpty(campusID)) {
            wrapper.eq("campusID", campusID);
        }
        if (!ObjectUtils.isEmpty(datesoe)) {
            String[] s = (datesoe + " 23:59:59").split("_");
            wrapper.between("endDate", s[0], s[1]);
        }
        List<HashMap<String, Object>> list = pxkeshistutableService.getClassProfit(new Page<HashMap<String, Object>>(1, Integer.MAX_VALUE), wrapper).getRecords();
        List<List<Object>> returnlist = ExportExcel.formatHashMapDataToList(
                new String[]{"校区", "班级", "收益（元）", "最后一次的上课日期"},
                list,
                new String[]{"campusName", "className", "priceSum", "endDate"});
        try {
            ExportExcel.exportExcel(response, returnlist, "导出班课收益", "导出班课收益.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
