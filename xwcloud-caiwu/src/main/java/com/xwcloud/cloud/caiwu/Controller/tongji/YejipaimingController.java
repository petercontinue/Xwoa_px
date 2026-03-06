package com.xwcloud.cloud.caiwu.Controller.tongji;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.caiwu.Service.IPxliushuizhangtableService;
import com.xwcloud.cloud.caiwu.Service.IPxqiandanstafftableService;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.ExportExcel;
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
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/caiwu/tongji/yejipaiming")
@Api(tags = "业绩排名")
public class YejipaimingController {

    @Autowired
    IPxliushuizhangtableService iPxliushuizhangtableService;
    @Autowired
    IPxqiandanstafftableService iPxqiandanstafftableService;

    @RequestMapping(value = "/getCampusYearPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "年度校区业绩排名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "year", value = "年份", required = false),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
    })
    public AjaxJson getCampusYearPage(HttpServletResponse respons, HttpServletRequest request,
                                      @RequestParam(required = false, defaultValue = "10") long size,
                                      @RequestParam(required = false, defaultValue = "1") long current,
                                      @RequestParam(required = false) String year
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<HashMap<String, String>> page = new Page<>(current, size);
        page = iPxliushuizhangtableService.getCampusYearPage(page, year, loginUser.getQiyeID());
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/getCampusMonthPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "月度校区业绩排名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "year", value = "年份", required = false),
            @ApiImplicitParam(name = "month", value = "月份", required = false)
    })
    public AjaxJson getCampusMonthPage(HttpServletResponse response, HttpServletRequest request,
                                       @RequestParam(required = false, defaultValue = "10") long size,
                                       @RequestParam(required = false, defaultValue = "1") long current,
                                       @RequestParam(required = false) String year,
                                       @RequestParam(required = false) String month
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<HashMap<String, String>> page = new Page<>(current, size);
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        page = iPxliushuizhangtableService.getCampusMonthPage(page, year, month, loginUser.getQiyeID());
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/getGerenYearPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "年度个人业绩排名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "year", value = "年份", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false)
    })
    public AjaxJson getGerenYearPage(HttpServletResponse response,HttpServletRequest request,
                                     @RequestParam(required = false, defaultValue = "10") long size,
                                     @RequestParam(required = false, defaultValue = "1") long current,
                                     @RequestParam(required = false) String year,
                                     @RequestParam(required = false) String campusID

    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser=(LoginUser)request.getAttribute("loginUser");
        Page<HashMap<String, String>> page = new Page<>(current, size);
        page = iPxqiandanstafftableService.getGerenYearPage(page, year, campusID, loginUser.getQiyeID());
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/getGerenMonthPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "月度个人业绩排名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "year", value = "年份", required = false),
            @ApiImplicitParam(name = "month", value = "月份", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false)
    })
    public AjaxJson getGerenMonthPage(HttpServletResponse response,HttpServletRequest request,
                                      @RequestParam(required = false, defaultValue = "10") long size,
                                      @RequestParam(required = false, defaultValue = "1") long current,
                                      @RequestParam(required = false) String year,
                                      @RequestParam(required = false) String month,
                                      @RequestParam(required = false) String campusID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser=(LoginUser)request.getAttribute("loginUser");
        Page<HashMap<String, String>> page = new Page<>(current, size);
        page = iPxqiandanstafftableService.getGerenMonthPage(page, year, month, campusID, loginUser.getQiyeID());
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/getGerenMonthDetailPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "月度个人业绩详细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "year", value = "年份"),
            @ApiImplicitParam(name = "month", value = "月份"),
            @ApiImplicitParam(name = "campusID", value = "校区ID"),
            @ApiImplicitParam(name = "stuffID", value = "教师ID"),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
    })
    public AjaxJson getGerenMonthDetailPage(HttpServletResponse response, HttpServletRequest request,
                                            @RequestParam(required = false, defaultValue = "10") long size,
                                            @RequestParam(required = false, defaultValue = "1") long current,
                                            String year,
                                            String month,
                                            String campusID,
                                            String staffID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<HashMap<String, String>> page = new Page<HashMap<String, String>>(current, size);
        page = iPxliushuizhangtableService.getGerenMonthDetailPage(page, year, month, staffID, campusID, loginUser.getQiyeID());
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/getDetailedIncomeDetails", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "收入明细详细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "liushuiID", value = "流水ID"),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
    })
    public AjaxJson getDetailedIncomeDetails(HttpServletResponse response, HttpServletRequest request,
                                             @RequestParam(required = false, defaultValue = "10") long size,
                                             @RequestParam(required = false, defaultValue = "1") long current,
                                             String liushuiID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<HashMap<String, String>> page = new Page<>(current, size);
        page = iPxliushuizhangtableService.getDetailedIncomeDetailsPage(page, liushuiID, loginUser.getQiyeID());
        ajaxJson.setObj(page);
        return ajaxJson;
    }


    @RequestMapping(value = "/getDetailedZafeiDetailsPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "收入杂费详细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "liushuiID", value = "流水ID"),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
    })
    public AjaxJson getDetailedZafeiDetailsPage(HttpServletResponse response, HttpServletRequest request,
                                                @RequestParam(required = false, defaultValue = "10") long size,
                                                @RequestParam(required = false, defaultValue = "1") long current,
                                                String liushuiID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<HashMap<String, String>> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("liushui.id", liushuiID);
        queryWrapper.eq("liushui.qiyeID", loginUser.getQiyeID());
        page = iPxliushuizhangtableService.getDetailedZafeiDetailsPage(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/getDetailedShangpingDetailsPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "收入商品详细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "liushuiID", value = "流水ID"),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
    })
    public AjaxJson getDetailedShangpingDetailsPage(HttpServletResponse response,
                                                    @RequestParam(required = false, defaultValue = "10") long size,
                                                    @RequestParam(required = false, defaultValue = "1") long current,
                                                    String liushuiID,
                                                    String qiyeID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<HashMap<String, String>> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("liushui.id", liushuiID);
        queryWrapper.eq("liushui.qiyeID", qiyeID);
        page = iPxliushuizhangtableService.getDetailedShangpingDetailsPage(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/exportGerenyejixiangxi", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出个人业绩详细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "staffPost", value = "岗位名称", required = false),
            @ApiImplicitParam(name = "staffName", value = "员工名称", required = false),
    })
    public void exportGongzitiao(HttpServletResponse response,
                                 HttpServletRequest request,
                                 String year,
                                 String month,
                                 String campusID,
                                 String staffID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");

        List<HashMap<String, Object>> list = iPxliushuizhangtableService.getGerenMonthDetailList(year, month, staffID, campusID, loginUser.getQiyeID());
        List<List<Object>> returnlist = ExportExcel.formatHashMapDataToList(new String[]{"校区", "学号", "姓名", "年龄段", "实收金额",
                        "支出(退费金额)", "费用时间", "业绩人", "类型", "费用说明"},
                list,
                new String[]{"campusName", "zidingyiStuID", "stuName", "stuGradeName", "shouruMoney", "zhichuMoney", "liushuidate",
                        "yejirenname", "shouzhiStyle", "liushuiZaiyao"});
        try {
            ExportExcel.exportExcel(response, returnlist, "个人业绩详细", "个人业绩详细导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
