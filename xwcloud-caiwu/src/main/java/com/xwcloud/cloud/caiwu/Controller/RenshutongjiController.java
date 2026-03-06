package com.xwcloud.cloud.caiwu.Controller;

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
@RequestMapping("/shujutongji/renshu")
@Api(tags = "人数统计")
public class RenshutongjiController {

    @Autowired
    IPxkeshistutableService iPxkeshistutableService;
    @Autowired
    IPxpowertableService iPxpowertableService;

    @RequestMapping(value = "/getXuesherenshu", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "学生人数统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false)
    })
    public AjaxJson getXuesherenshu(HttpServletRequest request, HttpServletResponse respons,
                                    @RequestParam(required = false) String campusID,
                                    Long current, Long size, String subjectID
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        Page<HashMap<String, Object>> page = new Page<>(current, size);
        QueryWrapper<HashMap<String, Object>> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());

        QueryWrapper<HashMap<String, Object>> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("qiyeID", loginUser.getQiyeID());

        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("a.campusID", campusID);
            queryWrapper2.eq("campusID", campusID);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 443);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }
        Page<HashMap<String, Object>> pageall = iPxkeshistutableService.getXuesherenshu(page, queryWrapper, queryWrapper2);
        ajaxJson.setObj(pageall);
        return ajaxJson;
    }

    @RequestMapping(value = "/getTeacherStu", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "教师学员统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "teacherName", value = "教师姓名", required = false),
//            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson getTeacherStu(HttpServletRequest request, HttpServletResponse respons,
                                  @RequestParam(required = false, defaultValue = "10") long size,
                                  @RequestParam(required = false, defaultValue = "1") long current,
                                  @RequestParam(required = false) long campusID,
                                  @RequestParam(required = false) String teacherName
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        Page<HashMap<String, String>> page = new Page<>(current, size);
        page = iPxkeshistutableService.getTeacherStu(page, campusID, teacherName, loginUser.getQiyeID());
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/getTeacherStuDetaile", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "教师学员详细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "teacherName", value = "教师姓名", required = false),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson getTeacherStuDetaile(HttpServletResponse respons,
                                         @RequestParam(required = false, defaultValue = "10") long size,
                                         @RequestParam(required = false, defaultValue = "1") long current,
                                         @RequestParam(required = false) long campusID,
                                         @RequestParam(required = false) long teacherID, HttpServletRequest request
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        Page<HashMap<String, String>> page = new Page<>(current, size);
        page = iPxkeshistutableService.getTeacherStuDetaile(page, campusID, teacherID, loginUser.getQiyeID());
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/getBanzhurenStu", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "班主任学员统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "teacherName", value = "教师姓名", required = false)
    })
    public AjaxJson getBanzhurenStu(HttpServletRequest request, HttpServletResponse respons,
                                    @RequestParam(required = false, defaultValue = "10") long size,
                                    @RequestParam(required = false, defaultValue = "1") long current,
                                    @RequestParam(required = false) long campusID,
                                    @RequestParam(required = false) String teacherName
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        Page<HashMap<String, String>> page = new Page(current, size);
        page = iPxkeshistutableService.getBanzhurenStu(page, campusID, teacherName, loginUser.getQiyeID());
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/getBanzhurenStuDetaile", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "班主任学员详细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "teacherID", value = "教师ID", required = false)
    })
    public AjaxJson getBanzhurenStuDetaile(HttpServletRequest request, HttpServletResponse respons,
                                           @RequestParam(required = false, defaultValue = "10") long size,
                                           @RequestParam(required = false, defaultValue = "1") long current,
                                           @RequestParam(required = false) long campusID,
                                           @RequestParam(required = false) long teacherID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<HashMap<String, String>> page = new Page<>(current, size);
        page = iPxkeshistutableService.getBanzhurenStuDetaile(page, campusID, teacherID, loginUser.getQiyeID());
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/exportXuesherenshu", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出学生人数统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false)
    })
    public void exportXuesherenshu(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam(required = false) String campusID

    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<HashMap<String, Object>> queryWrapper = new QueryWrapper<>();
        QueryWrapper<HashMap<String, Object>> queryWrapper2 = new QueryWrapper<>();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        queryWrapper2.eq("qiyeID", loginUser.getQiyeID());

        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("a.campusID", campusID);
            queryWrapper2.eq("campusID", campusID);
        }

        List<HashMap<String, Object>> list = iPxkeshistutableService.exportxueyuanrenshu(queryWrapper, queryWrapper2);
        List<List<Object>> returnlist = ExportExcel.formatHashMapDataToList(new String[]{"年级/年龄段", "(在读+停课)总人数", "在读人数",
                        "活跃学员数(当月有课耗人数）", "停课1-2个月人数", "停课3-5个月人数", "停课6个月以上人数"},
                list,
                new String[]{"stuGradeName", "zaiting", "zaidu", "huoyue", "tingke1", "tingke2", "tingke3"});
        try {
            ExportExcel.exportExcel(response, returnlist, "学生人数统计", "学生人数统计.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
