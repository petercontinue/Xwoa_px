package com.xwcloud.cloud.caiwu.Controller.other;

import com.xwcloud.cloud.caiwu.Service.IPxqiandansubjecttableService;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.Sso.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
import java.util.List;

@Controller
@RequestMapping("/shujutongji/other")
@Api(tags = "其他统计")
public class KemuzhanbiController {

    @Autowired
    IPxqiandansubjecttableService iPxqiandansubjecttableService;

    @RequestMapping(value = "/getKumuStu", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "科目学员占比")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDate", value = "开始日期", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束日期", required = false),
//            @ApiImplicitParam(name = "qiyeID", value = "qiyeID", required = true),
    })
    public AjaxJson getKumuStu(HttpServletRequest request, HttpServletResponse respons,
                               @RequestParam(required = false) String startDate,
                               @RequestParam(required = false) String endDate,
                               Integer type
//                               String qiyeID
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        if (!ObjectUtils.isEmpty(type)) {
            LocalDate now = LocalDate.now();
            switch (type) {
                case 2: {
                    LocalDate firstDayOfMonth = now.with(TemporalAdjusters.firstDayOfMonth());
                    LocalDate lastDayOfMonth = now.with(TemporalAdjusters.lastDayOfMonth());
                    startDate = firstDayOfMonth.toString();
                    endDate = lastDayOfMonth.toString();
                    break;
                }
                case 3: {
                    LocalDate firstDayOfMonth = now.plusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
                    LocalDate lastDayOfMonth = firstDayOfMonth.with(TemporalAdjusters.lastDayOfMonth());
                    startDate = firstDayOfMonth.toString();
                    endDate = lastDayOfMonth.toString();
                    break;
                }
            }
        }
        if (!ObjectUtils.isEmpty(endDate)) {
            endDate += " 23:59:59";
        }
        List<HashMap<String, String>> list = iPxqiandansubjecttableService.getKumuStu(startDate, endDate, loginUser.getQiyeID());
        ajaxJson.setObj(list);
        return ajaxJson;
    }

    @RequestMapping(value = "/getKumuXinqianXvfei", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "科目新签和续费占比")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDate", value = "开始日期", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束日期", required = false),
            @ApiImplicitParam(name = "moneyStyle", value = "1新签；2续费；3 新签续费", required = false),
    })
    public AjaxJson getKumuXinqianXvfei(HttpServletRequest request, HttpServletResponse respons,
                                        @RequestParam(required = false) String startDate,
                                        @RequestParam(required = false) String endDate,
                                        @RequestParam(required = true) int moneyStyle,
                                        Integer type
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        if (!ObjectUtils.isEmpty(type)) {
            LocalDate now = LocalDate.now();
            switch (type) {
                case 2: {
                    LocalDate firstDayOfMonth = now.with(TemporalAdjusters.firstDayOfMonth());
                    LocalDate lastDayOfMonth = now.with(TemporalAdjusters.lastDayOfMonth());
                    startDate = firstDayOfMonth.toString();
                    endDate = lastDayOfMonth.toString();
                    break;
                }
                case 3: {
                    LocalDate firstDayOfMonth = now.plusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
                    LocalDate lastDayOfMonth = firstDayOfMonth.with(TemporalAdjusters.lastDayOfMonth());
                    startDate = firstDayOfMonth.toString();
                    endDate = lastDayOfMonth.toString();
                    break;
                }
            }
        }
        if (!ObjectUtils.isEmpty(endDate)) {
            endDate += " 23:59:59";
        }
        List<HashMap<String, String>> list = null;
        if (moneyStyle == 3) {
            list = iPxqiandansubjecttableService.getKumuXinqian(startDate, endDate, loginUser.getQiyeID(), 1, 2);
        } else {
            list = iPxqiandansubjecttableService.getKumuXinqian(startDate, endDate, loginUser.getQiyeID(), moneyStyle);
        }
        ajaxJson.setObj(list);
        return ajaxJson;
    }

    @RequestMapping(value = "/getKumukeshi", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "科目收费课时占比")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDate", value = "开始日期", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束日期", required = false)
    })
    public AjaxJson getKumukeshi(HttpServletRequest request, HttpServletResponse respons,
                                 @RequestParam(required = false) String startDate,
                                 @RequestParam(required = false) String endDate,
                                 Integer type
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        if (!ObjectUtils.isEmpty(type)) {
            LocalDate now = LocalDate.now();
            switch (type) {
                case 2: {
                    LocalDate firstDayOfMonth = now.with(TemporalAdjusters.firstDayOfMonth());
                    LocalDate lastDayOfMonth = now.with(TemporalAdjusters.lastDayOfMonth());
                    startDate = firstDayOfMonth.toString();
                    endDate = lastDayOfMonth.toString();
                    break;
                }
                case 3: {
                    LocalDate firstDayOfMonth = now.plusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
                    LocalDate lastDayOfMonth = firstDayOfMonth.with(TemporalAdjusters.lastDayOfMonth());
                    startDate = firstDayOfMonth.toString();
                    endDate = lastDayOfMonth.toString();
                    break;
                }
            }
        }
        if (!ObjectUtils.isEmpty(endDate)) {
            endDate += " 23:59:59";
        }
        List<HashMap<String, String>> list = iPxqiandansubjecttableService.getKumuKeshi(startDate, endDate, loginUser.getQiyeID());
        ajaxJson.setObj(list);
        return ajaxJson;
    }

    @RequestMapping(value = "/getKumukexiao", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "科目课消占比")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDate", value = "开始日期", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束日期", required = false),
//            @ApiImplicitParam(name = "qiyeID", value = "qiyeID", required = true),
    })
    public AjaxJson getKumukexiao(HttpServletRequest request, HttpServletResponse respons,
                                  @RequestParam(required = false) String startDate,
                                  @RequestParam(required = false) String endDate,
                                  Integer type
//                                  String qiyeID
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        if (!ObjectUtils.isEmpty(type)) {
            LocalDate now = LocalDate.now();
            switch (type) {
                case 2: {
                    LocalDate firstDayOfMonth = now.with(TemporalAdjusters.firstDayOfMonth());
                    LocalDate lastDayOfMonth = now.with(TemporalAdjusters.lastDayOfMonth());
                    startDate = firstDayOfMonth.toString();
                    endDate = lastDayOfMonth.toString();
                    break;
                }
                case 3: {
                    LocalDate firstDayOfMonth = now.plusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
                    LocalDate lastDayOfMonth = firstDayOfMonth.with(TemporalAdjusters.lastDayOfMonth());
                    startDate = firstDayOfMonth.toString();
                    endDate = lastDayOfMonth.toString();
                    break;
                }
            }
        }
        List<HashMap<String, String>> list = iPxqiandansubjecttableService.getKumukexiao(startDate, endDate, loginUser.getQiyeID());
        ajaxJson.setObj(list);
        return ajaxJson;
    }

}
