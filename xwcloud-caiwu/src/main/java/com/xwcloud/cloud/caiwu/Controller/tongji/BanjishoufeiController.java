package com.xwcloud.cloud.caiwu.Controller.tongji;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.caiwu.Service.IPxpowertableService;
import com.xwcloud.cloud.caiwu.Service.IPxqiandaninfotableService;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.ExportExcel;
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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/caiwu/tongji/banjishoufei")
@Api(tags = "班级收费")
public class BanjishoufeiController {

    @Autowired
    IPxqiandaninfotableService iPxqiandaninfotableService;
    @Autowired
    IPxpowertableService iPxpowertableService;


    @RequestMapping(value = "/getBanjitongjiPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "班级收费统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "banjiName", value = "班级名称", required = false),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
    })
    public AjaxJson getBanjitongjiPage(HttpServletRequest request, HttpServletResponse respons,
                                        @RequestParam(required = false, defaultValue = "10") long size,
                                        @RequestParam(required = false, defaultValue = "1") long current,
                                        @RequestParam(required = false) String campusID,
                                        @RequestParam(required = false) String banjiName

    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        Page<HashMap<String, String>> page = new Page<>(current, size);

        QueryWrapper<HashMap<String, String>> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("campus.id", campusID);
        }
        if (StringUtils.isNotBlank(banjiName)) {
            queryWrapper.like("classT.className", banjiName);
        }
        queryWrapper.eq(" classT.qiyeID", loginUser.getQiyeID());

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 421);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("campus.id", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("campus.id", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("campus.id", lookPower);
        }

        page = iPxqiandaninfotableService.getBanjitongjiPage(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/getShoufeiDetail", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "收费详细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "classID", value = "班级ID", required = false),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
    })
    public AjaxJson getShoufeiDetail(HttpServletResponse respons, HttpServletRequest request,
                                     @RequestParam(required = false, defaultValue = "10") long size,
                                     @RequestParam(required = false, defaultValue = "1") long current,
                                     @RequestParam(required = false) String campusID,
                                     @RequestParam(required = false) String classID

    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<HashMap<String, String>> page = new Page<>(current, size);
        page = iPxqiandaninfotableService.getShoufeiDetail(page, campusID, classID, loginUser.getQiyeID());
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/exportBanjishoufei", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出班级数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
    })
    public void exportBanjishoufei(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam(required = false) String campusID

    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");

        // 导出回访记录
        List<HashMap<String, Object>> stuVoList = iPxqiandaninfotableService.getBanjitongjiList(campusID, "", loginUser.getQiyeID());
        List<List<Object>> list = ExportExcel.formatHashMapDataToList(new String[]{"校区", "班级名称", "收费统计", "剩余学费统计",
                        "课耗收入统计", "课时统计", "剩余课时统计"},
                stuVoList,
                new String[]{"campusName", "className", "zongjia", "remainXuefei", "totalkehao", "keshi", "remainkeshi"});

        ExportExcel.ExcelSource source = new ExportExcel.ExcelSource();
        source.setSheetName("班级课耗统计");
        source.setTableData(list);
        List<ExportExcel.ExcelSource> sourceList = new ArrayList<>();
        sourceList.add(source);
        List<HashMap<String, Object>> huifangDetailed = iPxqiandaninfotableService.getShoufeiDetailList(campusID, "", loginUser.getQiyeID());
        List<List<Object>> DetailedList = ExportExcel.formatHashMapDataToList(new String[]{"校区", "班级名称", "学员姓名", "交费", "剩余学费",
                        "消耗课时", "剩余课时"},
                huifangDetailed,
                new String[]{"campusName", "className", "stuName", "zongjia", "remainXuefei", "xiaohaokeshi", "remainkeshi"});
        ExportExcel.ExcelSource sourcedetaile = new ExportExcel.ExcelSource();
        sourcedetaile.setSheetName("详细");
        sourcedetaile.setTableData(DetailedList);
        sourceList.add(sourcedetaile);
        try {
            // 需要将详细表一起导出
            ExportExcel.exportMultipleSheetExcel(response, sourceList, "班级课耗.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
