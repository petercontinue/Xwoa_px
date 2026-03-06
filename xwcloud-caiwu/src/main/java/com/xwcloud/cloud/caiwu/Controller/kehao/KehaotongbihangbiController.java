package com.xwcloud.cloud.caiwu.Controller.kehao;

import com.xwcloud.cloud.caiwu.Service.IPxkeshistutableService;
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
@RequestMapping("/shujutongji/kehao")
@Api(tags = "课耗统计")
public class KehaotongbihangbiController {

    @Autowired
    IPxkeshistutableService iPxkeshistutableService;

    @RequestMapping(value = "/getKehaotongbihuanbi", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "课耗收入同比环比")
    @ApiImplicitParams({
            @ApiImplicitParam(name="campusID",value="校区ID",required=false),
            @ApiImplicitParam(name="startDate",value="开始时间",required=false),
            @ApiImplicitParam(name="endDate",value="结束时间",required=false),
//            @ApiImplicitParam(name="qiyeID",value="企业ID",required=true),
    })
    public AjaxJson getKehaotongbihuanbi(HttpServletRequest request, HttpServletResponse respons,
                                         @RequestParam(required = false) long campusID,
                                         @RequestParam(required = false) String startDate,
                                         @RequestParam(required = false) String endDate
//                                     String qiyeID
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        LocalDate now = LocalDate.now();
        LocalDate localEnd = now.with(TemporalAdjusters.firstDayOfYear());
        LocalDate localStart = now.plusYears(-1).with(TemporalAdjusters.lastDayOfYear());
        if(!ObjectUtils.isEmpty(startDate) && !ObjectUtils.isEmpty(endDate)){
            localStart = localStart.plusYears(Long.parseLong(startDate) - localStart.getYear());
            localEnd = localEnd.plusYears(Long.parseLong(endDate) - localEnd.getYear());
        }
        AjaxJson ajaxJson = new AjaxJson();
        List<HashMap<String,String>> list = iPxkeshistutableService.getKehaotongbihuanbi(campusID, localStart.getYear(), localEnd.getYear(), loginUser.getQiyeID());
        ajaxJson.setObj(list);
        return ajaxJson;
    }
}
