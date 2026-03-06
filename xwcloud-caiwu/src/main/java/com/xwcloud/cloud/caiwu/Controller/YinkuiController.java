package com.xwcloud.cloud.caiwu.Controller;


import com.xwcloud.cloud.caiwu.Service.IPxliushuizhangtableService;
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
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/caiwu/liushui")
@Api(tags = "财务流水")
public class YinkuiController {

    @Autowired
    IPxliushuizhangtableService iPxliushuizhangtableService;

    @RequestMapping(value = "/getYinkuiList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "盈亏总账")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year", value = "年份", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
    })
    public AjaxJson getYinkuiList(String year,
                                  HttpServletRequest request,
                                  @RequestParam(required = false) String campusID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");

        List<HashMap<String, Object>> list = iPxliushuizhangtableService.getYinkuiList(year, campusID, loginUser.getQiyeID());
        for (int i = 0; i < list.size(); i++) {
            BigDecimal bigDecimal = new BigDecimal(list.get(i).get("APR").toString());
            BigDecimal bigDecimalSum = bigDecimal.add(new BigDecimal(list.get(i).get("AUG").toString()))
                    .add(new BigDecimal(list.get(i).get("DECE").toString()))
                    .add(new BigDecimal(list.get(i).get("FEB").toString()))
                    .add(new BigDecimal(list.get(i).get("JAN").toString()))
                    .add(new BigDecimal(list.get(i).get("JUL").toString()))
                    .add(new BigDecimal(list.get(i).get("JUN").toString()))
                    .add(new BigDecimal(list.get(i).get("MAR").toString()))
                    .add(new BigDecimal(list.get(i).get("MAY").toString()))
                    .add(new BigDecimal(list.get(i).get("NOV").toString()))
                    .add(new BigDecimal(list.get(i).get("OCT").toString()))
                    .add(new BigDecimal(list.get(i).get("SEP").toString()));

            list.get(i).put("SUM", String.valueOf(bigDecimalSum));
        }
        ajaxJson.setObj(list);
        return ajaxJson;
    }


    @ApiOperation("导出盈亏总账")
    @ResponseBody
    @RequestMapping(value = "ExportYinkuiList", method = RequestMethod.GET)
    @ApiImplicitParam(name = "campusID", value = "校区ID", required = false)
    public void exportYinkuiList(String year,
                                 HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(required = false) String campusID
    ) {

        LoginUser LoginUser = (LoginUser) request.getAttribute("loginUser");
        List<HashMap<String, Object>> list = iPxliushuizhangtableService.getYinkuiList(year, campusID, LoginUser.getQiyeID());
        for (int i = 0; i < list.size(); i++) {
            BigDecimal bigDecimal = new BigDecimal(list.get(i).get("APR").toString());
            BigDecimal bigDecimalSum = bigDecimal.add(new BigDecimal(list.get(i).get("AUG").toString()))
                    .add(new BigDecimal(list.get(i).get("DECE").toString()))
                    .add(new BigDecimal(list.get(i).get("FEB").toString()))
                    .add(new BigDecimal(list.get(i).get("JAN").toString()))
                    .add(new BigDecimal(list.get(i).get("JUL").toString()))
                    .add(new BigDecimal(list.get(i).get("JUN").toString()))
                    .add(new BigDecimal(list.get(i).get("MAR").toString()))
                    .add(new BigDecimal(list.get(i).get("MAY").toString()))
                    .add(new BigDecimal(list.get(i).get("NOV").toString()))
                    .add(new BigDecimal(list.get(i).get("OCT").toString()))
                    .add(new BigDecimal(list.get(i).get("SEP").toString()));

            list.get(i).put("SUM", String.valueOf(bigDecimalSum));
        }


        List<List<Object>> exist = ExportExcel.formatDataToList(new String[]{"类别", "合计", "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"},
                list,
                new String[]{"shouzhiStyle", "SUM", "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DECE"});

        try {
            ExportExcel.exportExcel(response, exist, "Sheet1", "盈亏总账导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
