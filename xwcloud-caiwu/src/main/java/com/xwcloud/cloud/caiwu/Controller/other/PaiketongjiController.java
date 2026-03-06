package com.xwcloud.cloud.caiwu.Controller.other;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.caiwu.IService.ICaiwuService;
import com.xwcloud.cloud.caiwu.Service.IPxpaiketableService;
import com.xwcloud.cloud.caiwu.Service.IPxpowertableService;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.Sso.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;

@Controller
@RequestMapping("/shujutongji/paike")
@Api(tags = "排课统计")
public class PaiketongjiController {

    @Autowired
    IPxpaiketableService iPxpaiketableService;
    @Autowired
    ICaiwuService iCaiwuService;
    @Autowired
    IPxpowertableService iPxpowertableService;


    @RequestMapping(value = "/getStupaikePage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "学生排课统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "gradeID", value = "年级ID", required = false),
            @ApiImplicitParam(name = "stuName", value = "学生ID", required = false),
//            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson getStupaikePage(HttpServletRequest request, HttpServletResponse respons,
                                    @RequestParam(required = false, defaultValue = "10") long size,
                                    @RequestParam(required = false, defaultValue = "1") long current,
                                    @RequestParam(required = false) String campusID,
                                    @RequestParam(required = false) String gradeID,
                                    @RequestParam(required = false) String stuName,
                                    @RequestParam(required = false) String startDate,
                                    @RequestParam(required = false) String endDate,
                                    Integer type
//                                    String qiyeID
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        Page<HashMap<String, String>> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("paike.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("campus.id", campusID);
        }
        if (StringUtils.isNotBlank(gradeID)) {
            queryWrapper.eq("grade.id", gradeID);
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("stu.stuName", stuName);
        }
        if (!ObjectUtils.isEmpty(type)) {
            LocalDate now = LocalDate.now();
            switch (type) {
                case 2: {
//                    本月
                    LocalDate firstDayOfMonth = now.with(TemporalAdjusters.firstDayOfMonth());
                    LocalDate lastDayOfMonth = now.with(TemporalAdjusters.lastDayOfMonth());
                    startDate = firstDayOfMonth.toString();
                    endDate = lastDayOfMonth.toString() + " 23:59:59";
                    break;
                }
                case 3: {
//                    下月
                    LocalDate firstDayOfMonth = now.plusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
                    LocalDate lastDayOfMonth = firstDayOfMonth.with(TemporalAdjusters.lastDayOfMonth());
                    startDate = firstDayOfMonth.toString();
                    endDate = lastDayOfMonth.toString() + " 23:59:59";
                    break;
                }
            }
        }
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("paike.haveClassDate", startDate);
        }
        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("paike.haveClassDate", endDate);
        }


        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 443);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("campus.id", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("campus.id", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("campus.id", lookPower);
        }

        page = iPxpaiketableService.getStupaikePage(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/getTeacherpaikePage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "教师排课统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "stuName", value = "学生ID", required = false),
//            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson getTeacherpaikePage(HttpServletRequest request, HttpServletResponse respons,
                                        @RequestParam(required = false, defaultValue = "10") long size,
                                        @RequestParam(required = false, defaultValue = "1") long current,
                                        @RequestParam(required = false) String campusID,
                                        @RequestParam(required = false) String staffName,
                                        @RequestParam(required = false) String startDate,
                                        @RequestParam(required = false) String endDate,
                                        Integer type
//                                        String qiyeID
    ) {
        LoginUser login = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        Page<HashMap<String, String>> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("paiketeacher.qiyeID", login.getQiyeID());
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("campus.id", campusID);
        }
        if (StringUtils.isNotBlank(staffName)) {
            queryWrapper.like("staff.staffName", staffName);
        }
        if (!ObjectUtils.isEmpty(type)) {
            LocalDate now = LocalDate.now();
            switch (type) {
                case 2: {
//                    本月
                    LocalDate firstDayOfMonth = now.with(TemporalAdjusters.firstDayOfMonth());
                    LocalDate lastDayOfMonth = now.with(TemporalAdjusters.lastDayOfMonth());
                    startDate = firstDayOfMonth.toString();
                    endDate = lastDayOfMonth.toString() + " 23:59:59";
                    break;
                }
                case 3: {
//                    下月
                    LocalDate firstDayOfMonth = now.plusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
                    LocalDate lastDayOfMonth = firstDayOfMonth.with(TemporalAdjusters.lastDayOfMonth());
                    startDate = firstDayOfMonth.toString();
                    endDate = lastDayOfMonth.toString() + " 23:59:59";
                    break;
                }
            }
        }
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("paike.haveClassDate", startDate);
        }
        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("paike.haveClassDate", endDate);
        }


        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", login.getQiyeID());
        queryWrapper1.eq("staffpostID", login.getStaffPostID());
        queryWrapper1.eq("menuID", 443);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("campus.id", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("campus.id", login.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("campus.id", lookPower);
        }
        page = iPxpaiketableService.getTeacherpaikePage(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/getCampuspaikePage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "校区排课统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
//            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson getCampuspaikePage(HttpServletRequest request, HttpServletResponse respons,
                                       @RequestParam(required = false, defaultValue = "10") long size,
                                       @RequestParam(required = false, defaultValue = "1") long current,
                                       @RequestParam(required = false) Long campusID,
                                       @RequestParam(required = false) String startDate,
                                       @RequestParam(required = false) String endDate,
                                       Integer type
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        Page<HashMap<String, String>> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("paike.qiyeID", loginUser.getQiyeID());

        if (!ObjectUtils.isEmpty(type)) {
            LocalDate now = LocalDate.now();
            switch (type) {
                case 2: {
//                    本月
                    LocalDate firstDayOfMonth = now.with(TemporalAdjusters.firstDayOfMonth());
                    LocalDate lastDayOfMonth = now.with(TemporalAdjusters.lastDayOfMonth());
                    startDate = firstDayOfMonth.toString();
                    endDate = lastDayOfMonth.toString() + " 23:59:59";
                    break;
                }
                case 3: {
//                    下月
                    LocalDate firstDayOfMonth = now.plusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
                    LocalDate lastDayOfMonth = firstDayOfMonth.with(TemporalAdjusters.lastDayOfMonth());
                    startDate = firstDayOfMonth.toString();
                    endDate = lastDayOfMonth.toString() + " 23:59:59";
                    break;
                }
            }
        }
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("paike.haveClassDate", startDate);
        }
        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("paike.haveClassDate", endDate);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 443);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("campus.id", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("campus.id",loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("campus.id",lookPower);
        }
        if (campusID!=0) {
            queryWrapper.eq("campus.id", campusID);
        }
        page = iPxpaiketableService.getCampuspaikePage(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

}
