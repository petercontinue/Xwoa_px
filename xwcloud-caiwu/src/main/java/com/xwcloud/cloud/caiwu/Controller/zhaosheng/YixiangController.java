package com.xwcloud.cloud.caiwu.Controller.zhaosheng;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.caiwu.Service.IPxyxqiandantableService;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.ExportExcel;
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
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/shujutongji/yixiang")
@Api(tags = "招生统计")
public class YixiangController {

    @Autowired
    IPxyxqiandantableService iPxyxqiandantableService;

    @RequestMapping(value = "/getYixiangDetail", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "意向签单统计详细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "staffID", value = "教师ID", required = false),
            @ApiImplicitParam(name = "startDate", value = "开始时间", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束时间", required = false),
//            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson getYixiangDetail(HttpServletRequest request, HttpServletResponse respons,
                                     @RequestParam(required = false, defaultValue = "10") long size,
                                     @RequestParam(required = false, defaultValue = "1") long current,
                                     @RequestParam(required = false) String campusID,
                                     @RequestParam(required = false) String staffID,
                                     @RequestParam(required = false) String startDate,
                                     @RequestParam(required = false) String endDate
//                                     String qiyeID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        Page<HashMap<String, String>> page = new Page(current, size);
        queryWrapper.eq("yxqd.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("campus.id", campusID);
        }
        if (StringUtils.isNotBlank(staffID)) {
            queryWrapper.eq("staff.id", staffID);
        }
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("yxqd.yxQiandanDateTime", startDate);
        }
        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("yxqd.yxQiandanDateTime", endDate);
        }
        page = iPxyxqiandantableService.getYixiangDetail(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/getYixiangPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "意向签单统计分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "staffName", value = "教师名称", required = false),
            @ApiImplicitParam(name = "startDate", value = "开始时间", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束时间", required = false),
//            @ApiImplicitParam(name="qiyeID",value="企业ID",required=false),
    })
    public AjaxJson getYixiangPage(HttpServletRequest request, HttpServletResponse respons,
                                   @RequestParam(required = false, defaultValue = "10") long size,
                                   @RequestParam(required = false, defaultValue = "1") long current,
                                   @RequestParam(required = false) String campusID,
                                   @RequestParam(required = false) String staffName,
                                   @RequestParam(required = false) String startDate,
                                   @RequestParam(required = false) String endDate,
                                   Integer type,
                                   String datesoe
//                                    String qiyeID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        Page<HashMap<String, String>> page = new Page(current, size);
        queryWrapper.eq("yxqd.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.ge("campus.id", campusID);
        }
        if (StringUtils.isNotBlank(staffName)) {
            queryWrapper.like("staff.staffName", staffName);
        }
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("yxqd.yxQiandanDateTime", startDate);
        }
        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("yxqd.yxQiandanDateTime", endDate);
        }
        if (!ObjectUtils.isEmpty(type)) {
            LocalDate now = LocalDate.now();
            LocalDate first = now.with(TemporalAdjusters.firstDayOfMonth());
            LocalDate last = now.with(TemporalAdjusters.lastDayOfMonth());
            switch (type) {
                case 1:
//                    本月统计
                    queryWrapper.between("yxqd.yxQiandanDateTime", first,last);
                    break;
                case 2:
//                    下月统计
                    LocalDate localDate = now.plusMonths(1);
                    LocalDate first1 = localDate.with(TemporalAdjusters.firstDayOfMonth());
                    LocalDate last1 = localDate.with(TemporalAdjusters.lastDayOfMonth());
                    queryWrapper.between("yxqd.yxQiandanDateTime", first1,last1);
                    break;
                case 3:
//                    起止日期查询
                    if (!ObjectUtils.isEmpty(datesoe)){
                        String[] s = (datesoe + " 23:59:59").split("_");
                        queryWrapper.between("yxqd.yxQiandanDateTime", s[0],s[1]);
                    }
                    break;
            }
        }
        page = iPxyxqiandantableService.getYixiangPage(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/exportYixiang", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出意向统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "staffName", value = "教师名称", required = false),
            @ApiImplicitParam(name = "startDate", value = "开始时间", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束时间", required = false),
//            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = false),
    })
    public void exportYixiang(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(required = false) String startDate,
                              @RequestParam(required = false) String endDate
//                              String qiyeID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("yxqd.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("yxqd.yxQiandanDateTime", startDate);
        }
        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("yxqd.yxQiandanDateTime", endDate);
        }
        List<HashMap<String, Object>> list = iPxyxqiandantableService.getYixiangList(queryWrapper);
        List<List<Object>> returnlist = ExportExcel.formatHashMapDataToList(new String[]{"校区", "教师姓名", "人数", "业绩"},
                list,
                new String[]{"campusName", "staffName", "stuNum", "qianDanMoney"});
        try {
            ExportExcel.exportExcel(response, returnlist, "流水", "流水导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
