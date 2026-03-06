package com.xwcloud.cloud.caiwu.Controller.tongji;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.caiwu.Service.IPxpowertableService;
import com.xwcloud.cloud.caiwu.Service.IPxqiandansubjecttableService;
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
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/caiwu/tongji/kemushoufei")
@Api(tags = "科目收费统计")
public class KemushoufeiController {

    @Autowired
    IPxqiandansubjecttableService iPxqiandansubjecttableService;
    @Autowired
    IPxpowertableService iPxpowertableService;

    @RequestMapping(value = "/getKemushoufeiPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "科目收费")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "kemuName", value = "科目名称", required = false),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
    })
    public AjaxJson getKemushoufeiPage(HttpServletResponse respons, HttpServletRequest request,
                                       @RequestParam(required = false, defaultValue = "10") long size,
                                       @RequestParam(required = false, defaultValue = "1") long current,
                                       @RequestParam(required = false) String campusID,
                                       @RequestParam(required = false) String kemuName
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");

        Page<HashMap<String, String>> page = new Page<>(current, size);
        QueryWrapper<HashMap<String, String>> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("qdsub.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("campus.id", campusID);
        }
        if (StringUtils.isNotBlank(kemuName)) {
            queryWrapper.like("sub.subjectName", kemuName);
        }

        page = iPxqiandansubjecttableService.getKemushoufeiPage(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/getKemukehaoPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "科目课耗")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "kemuName", value = "科目名称", required = false),
            @ApiImplicitParam(name = "startDate", value = "开始时间", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束时间", required = false),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = false),
    })
    public AjaxJson getKemukehaoPage(HttpServletResponse respons, HttpServletRequest request,
                                     @RequestParam(required = false, defaultValue = "10") long size,
                                     @RequestParam(required = false, defaultValue = "1") long current,
                                     @RequestParam(required = false) String campusID,
                                     @RequestParam(required = false) String kemuName,
                                     @RequestParam(required = false) String startDate,
                                     @RequestParam(required = false) String endDate
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<HashMap<String, String>> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("campus.id", campusID);
        }
        if (StringUtils.isNotBlank(kemuName)) {
            queryWrapper.like("sub.subjectName", kemuName);
        }
        if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {
            queryWrapper.ge("keshistu.haveClassDate", startDate);
            queryWrapper.le("keshistu.haveClassDate", endDate);
        }

        queryWrapper.eq("keshistu.qiyeID", loginUser.getQiyeID());

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
        Page<HashMap<String, String>> page = new Page<>(current, size);
        page = iPxqiandansubjecttableService.getKemukehaoPage(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }


    @RequestMapping(value = "/getKemuyufeePage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "科目余额")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "kemuName", value = "科目名称", required = false),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = false),
    })
    public AjaxJson getKemuyufeePage(HttpServletResponse respons, HttpServletRequest request,
                                     @RequestParam(required = false, defaultValue = "10") long size,

                                     @RequestParam(required = false, defaultValue = "1") long current,
                                     @RequestParam(required = false) String campusID,
                                     @RequestParam(required = false) String kemuName
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<HashMap<String, String>> page = new Page<>(current, size);
        QueryWrapper<HashMap<String, String>> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("buxikecheng.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("campus.id", campusID);
        }
        if (StringUtils.isNotBlank(kemuName)) {
            queryWrapper.like("sub.subjectName", kemuName);
        }
        page = iPxqiandansubjecttableService.getKemuyufeePage(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/exportKemushoufei", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出科目收费")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "kemuName", value = "科目名称", required = false),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
    })
    public void exportKemushoufei(HttpServletResponse response,
                                  @RequestParam(required = false) String campusID,
                                  @RequestParam(required = false) String kemuName,
                                  @RequestParam(required = false) String startDate,
                                  @RequestParam(required = false) String endDate,
                                  String qiyeID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        List<HashMap<String, Object>> list = iPxqiandansubjecttableService.getKemushoufeiList(campusID, kemuName, startDate, endDate, qiyeID);
        List<List<Object>> returnlist = ExportExcel.formatHashMapDataToList(new String[]{"校区", "科目", "一对一收费金额", "非一对一收费金额", "收费总金额"},
                list,
                new String[]{"campusName", "subName", "ONEVONE", "NOONEVONE", "zongjia"});
        try {
            ExportExcel.exportExcel(response, returnlist, "科目统计", "科目统计导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/exportKemukehao", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出科目课耗")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "kemuName", value = "科目名称", required = false),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
    })
    public void exportKemukehao(HttpServletResponse response,
                                @RequestParam(required = false) String campusID,
                                @RequestParam(required = false) String kemuName,
                                @RequestParam(required = false) String startDate,
                                @RequestParam(required = false) String endDate,
                                String qiyeID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        List<HashMap<String, Object>> list = iPxqiandansubjecttableService.getKemukehaoList(campusID, kemuName, startDate, endDate, qiyeID);
        List<List<Object>> returnlist = ExportExcel.formatHashMapDataToList(new String[]{"校区", "科目", "一对一收费金额", "非一对一收费金额", "课耗总额"},
                list,
                new String[]{"campusName", "subjectName", "ONEVONE", "NOONEVONE", "kehaozonge"});
        try {
            ExportExcel.exportExcel(response, returnlist, "科目统计", "科目统计导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/exportKemuyufee", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出科目余额")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "kemuName", value = "科目名称", required = false),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
    })
    public void exportKemuyufee(HttpServletResponse response,
                                @RequestParam(required = false) String campusID,
                                @RequestParam(required = false) String kemuName,
                                @RequestParam(required = false) String startDate,
                                @RequestParam(required = false) String endDate,
                                String qiyeID
    ) {
        List<HashMap<String, Object>> list = iPxqiandansubjecttableService.getKemuyufeeList(campusID, kemuName, startDate, endDate, qiyeID);
        List<List<Object>> returnlist = ExportExcel.formatHashMapDataToList(new String[]{"校区", "科目", "按起止日期计费学费总额", "不按起止日期计费学费总额", "学费余额总额"},
                list,
                new String[]{"campusName", "subName", "qzrq", "noqzrq", "zonge"});
        try {
            ExportExcel.exportExcel(response, returnlist, "科目统计", "科目统计导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
