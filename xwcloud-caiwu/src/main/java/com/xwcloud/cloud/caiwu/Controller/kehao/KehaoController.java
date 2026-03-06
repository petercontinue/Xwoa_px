package com.xwcloud.cloud.caiwu.Controller.kehao;

import com.xwcloud.cloud.caiwu.IService.ICaiwuService;
import com.xwcloud.cloud.caiwu.Service.IPxpowertableService;
import com.xwcloud.cloud.common.AjaxJson;
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

@Controller
@RequestMapping("/shujutongji/kehao")
@Api(tags = "课耗统计")
public class KehaoController {

    @Autowired
    ICaiwuService iCaiwuService;
    @Autowired
    IPxpowertableService iPxpowertableService;


    @RequestMapping(value = "/getKemukehaoPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "科目课耗统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "kemuName", value = "科目名称", required = false),
            @ApiImplicitParam(name = "startDate", value = "开始时间", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束时间", required = false),
//            @ApiImplicitParam(name="qiyeID",value="企业ID",required=true),
    })
    public AjaxJson getKemukehaoPage(HttpServletRequest request, HttpServletResponse respons,
                                     @RequestParam(required = false, defaultValue = "10") long size,
                                     @RequestParam(required = false, defaultValue = "1") long current,
                                     @RequestParam(required = false) String campusID,
                                     @RequestParam(required = false) String kemuName,
                                     @RequestParam(required = false) String startDate,
                                     @RequestParam(required = false) String endDate
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = iCaiwuService.getKemukehaoPage(size, current, campusID, kemuName, startDate, endDate, loginUser.getQiyeID());
        return ajaxJson;
    }

    @RequestMapping(value = "/getKehaoPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "班级课耗统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "kemuName", value = "班级名称", required = false),
//            @ApiImplicitParam(name="qiyeID",value="企业ID",required=true),
    })
    public AjaxJson getBanjishoufeiPage(HttpServletRequest request, HttpServletResponse respons,
                                        @RequestParam(required = false, defaultValue = "10") long size,
                                        @RequestParam(required = false, defaultValue = "1") long current,
                                        @RequestParam(required = false) String campusID,
                                        @RequestParam(required = false) String banjiName
//                                        String qiyeID
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = iCaiwuService.getBanjishoufeiPage(size, current, Long.parseLong(campusID), banjiName, loginUser.getQiyeID());
        return ajaxJson;
    }

    @RequestMapping(value = "/getShoufeiDetail", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "班级课耗详细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "kemuName", value = "班级名称", required = false),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson getShoufeiDetail(HttpServletResponse respons,
                                     @RequestParam(required = false, defaultValue = "10") long size,
                                     @RequestParam(required = false, defaultValue = "1") long current,
                                     @RequestParam(required = false) long campusID,
                                     @RequestParam(required = false) long classID,
                                     long qiyeID
    ) {
        AjaxJson ajaxJson = iCaiwuService.getShoufeiDetail(size, current, campusID, classID, qiyeID);
        return ajaxJson;
    }

}
