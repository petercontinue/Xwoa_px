package com.xwcloud.cloud.caiwu.Controller.kehao;

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
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/shujutongji/kehao")
@Api(tags = "课耗统计")
public class BanzhurenkehaoController {

    @Autowired
    IPxkeshistutableService iPxkeshistutableService;

    @Autowired
    IPxpowertableService iPxpowertableService;

    @RequestMapping(value = "/getBanzhurenPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "班主任课耗统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "staffName", value = "班主任名称", required = false)
    })
    public AjaxJson getBanzhurenPage(HttpServletRequest request,
                                     @RequestParam(required = false, defaultValue = "10") long size,
                                     @RequestParam(required = false, defaultValue = "1") long current,
                                     @RequestParam(required = false) String campusID,
                                     @RequestParam(required = false) String staffName,
                                     String datesoe
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        AjaxJson ajaxJson = new AjaxJson();
        Page<HashMap<String, String>> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("tmp.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("campus.id", campusID);
        }
        if (StringUtils.isNotBlank(staffName)) {
            queryWrapper.like("staff.staffName", staffName);
        }
        if (!ObjectUtils.isEmpty(datesoe)) {
            String[] s = (datesoe + " 23:59:59").split("_");
            queryWrapper.between("tmp.haveClassDate", s[0], s[1]);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 424);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("tmp.staffid", loginUser.getStaffID());
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("campus.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("campus.campusID", lookPower);
        }


        page = iPxkeshistutableService.getBanzhurenPage(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/exportBanzhuren", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出班主任课耗统计")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public void exportBanzhuren(HttpServletRequest request, HttpServletResponse response
//                                String qiyeID
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("tmp.qiyeID", loginUser.getQiyeID());
        List<HashMap<String, Object>> list = iPxkeshistutableService.getBanzhurenList(queryWrapper);
        List<List<Object>> returnlist = ExportExcel.formatHashMapDataToList(new String[]{"校区", "班主任", "一对一上课人数",
                        "班课上课人数", "一对一课时数", "班课课时数", "一对一课耗金额", "班课课耗金额", "课耗总金额"},
                list,
                new String[]{"campusName", "staffName", "1v1kcNum", "bankeNum", "1v1keshiNum", "keshiNum", "1v1kehaoNum", "kehaoNum", "total"});
        try {
            ExportExcel.exportExcel(response, returnlist, "科目统计", "科目统计导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
