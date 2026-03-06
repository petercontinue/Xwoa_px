package com.xwcloud.cloud.caiwu.Controller.kehao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.caiwu.Service.IPxkeshistutableService;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/shujutongji/kehao")
@Api(tags = "课耗统计")
public class TeacherkehaoController {

    @Autowired
    IPxkeshistutableService iPxkeshistutableService;

    @RequestMapping(value = "/getNianjiPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "按年级统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "staffName", value = "教师名称", required = false),
            @ApiImplicitParam(name = "grade", value = "年级ID", required = false)
    })
    public AjaxJson getNianjiPage(HttpServletRequest request,
                                  @RequestParam(required = false, defaultValue = "10") long size,
                                  @RequestParam(required = false, defaultValue = "1") long current,
                                  @RequestParam(required = false) String campusID,
                                  @RequestParam(required = false) String staffName,
                                  @RequestParam(required = false) String grade,
                                  String datesoe
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        Page<HashMap<String, Object>> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("keshi.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("keshi.campusID", campusID);
        }
        if (StringUtils.isNotBlank(staffName)) {
            queryWrapper.like("staff.staffName", staffName);
        }
        if (StringUtils.isNotBlank(grade)) {
            queryWrapper.like("grade.stuGradeName", grade);
        }
        if (!ObjectUtils.isEmpty(datesoe)) {
            String[] s = (datesoe + " 23:59:59").split("_");
            queryWrapper.between("keshi.haveClassDate", s[0], s[1]);
        }
        page = iPxkeshistutableService.getNianjiPage(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 教师课耗按年级统计导出
     */
    @GetMapping("/exportNianjiPage")
    @ResponseBody
    @ApiOperation(value = "教师课耗按年级统计导出")
    @ApiImplicitParams({
    })
    public void exportNianjiPage(HttpServletRequest request, HttpServletResponse response) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("keshi.qiyeID", loginUser.getQiyeID());
        List<HashMap<String, Object>> stuVoList = iPxkeshistutableService.getNianjiPage(new Page(1, Integer.MAX_VALUE), queryWrapper).getRecords();
        List<List<Object>> list = ExportExcel.formatHashMapDataToList(new String[]{"校区", "教师姓名", "年级", "课时数", "课耗收入"},
                stuVoList,
                new String[]{"campusName", "staffName", "stuGradeName", "keshiNum", "kehao"});
        try {
            // 需要将详细表一起导出
            ExportExcel.exportExcel(response, list, "教师课耗按年级统计导出", "教师课耗按年级统计导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getBanjiPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "按班级统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "staffName", value = "教师名称", required = false),
            @ApiImplicitParam(name = "className", value = "班级名称", required = false),
//            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson getBanjiPage(HttpServletRequest request,
                                 @RequestParam(required = false, defaultValue = "10") long size,
                                 @RequestParam(required = false, defaultValue = "1") long current,
                                 @RequestParam(required = false) String campusID,
                                 @RequestParam(required = false) String staffName,
                                 @RequestParam(required = false) String className,
                                 String datesoe
//            String qiyeID
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        Page<HashMap<String, Object>> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("keshi.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("keshi.campusID", campusID);
        }
        if (StringUtils.isNotBlank(staffName)) {
            queryWrapper.like("staff.staffName", staffName);
        }
        if (StringUtils.isNotBlank(className)) {
            queryWrapper.like("classT.className", className);
        }
        if (!ObjectUtils.isEmpty(datesoe)) {
            String[] s = (datesoe + " 23:59:59").split("_");
            queryWrapper.between("keshi.haveClassDate", s[0], s[1]);
        }
        page = iPxkeshistutableService.getBanjiPage(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 教师课耗按班级统计导出
     */
    @GetMapping("/exportBanjiPage")
    @ResponseBody
    @ApiOperation(value = "教师课耗按班级统计导出")
    @ApiImplicitParams({
    })
    public void exportBanjiPage(HttpServletRequest request, HttpServletResponse response) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("keshi.qiyeID", loginUser.getQiyeID());
        List<HashMap<String, Object>> stuVoList = iPxkeshistutableService.getBanjiPage(new Page(1, Integer.MAX_VALUE), queryWrapper).getRecords();
        List<List<Object>> list = ExportExcel.formatHashMapDataToList(new String[]{"校区", "教师姓名", "班级", "课时数", "课耗收入"},
                stuVoList,
                new String[]{"campusName", "staffName", "className", "keshiNum", "kehao"});
        try {
            // 需要将详细表一起导出
            ExportExcel.exportExcel(response, list, "教师课耗按班级统计导出", "教师课耗按班级统计导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getBuxiStylePage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "按补习方式统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "staffName", value = "教师名称", required = false),
            @ApiImplicitParam(name = "buxiStyleName", value = "补习方式", required = false)
    })
    public AjaxJson getBuxiStylePage(HttpServletRequest request,
                                     @RequestParam(required = false, defaultValue = "10") long size,
                                     @RequestParam(required = false, defaultValue = "1") long current,
                                     @RequestParam(required = false) String campusID,
                                     @RequestParam(required = false) String staffName,
                                     @RequestParam(required = false) String buxiStyleName,
                                     String datesoe
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        Page<HashMap<String, Object>> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("keshi.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("keshi.campusID", campusID);
        }
        if (StringUtils.isNotBlank(staffName)) {
            queryWrapper.like("staff.staffName", staffName);
        }
        if (StringUtils.isNotBlank(buxiStyleName)) {
            queryWrapper.like("buxistyle.buxiStyleName", buxiStyleName);
        }
        if (!ObjectUtils.isEmpty(datesoe)) {
            String[] s = (datesoe + " 23:59:59").split("_");
            queryWrapper.between("keshi.haveClassDate", s[0], s[1]);
        }
        page = iPxkeshistutableService.getBuxiStylePage(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 教师课耗按补习方式统计导出
     */
    @GetMapping("/exportBuxiStylePage")
    @ResponseBody
    @ApiOperation(value = "教师课耗按补习方式统计导出")
    @ApiImplicitParams({
    })
    public void exportBuxiStylePage(HttpServletRequest request, HttpServletResponse response) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("keshi.qiyeID", loginUser.getQiyeID());
        List<HashMap<String, Object>> stuVoList = iPxkeshistutableService.getBuxiStylePage(new Page(1, Integer.MAX_VALUE), queryWrapper).getRecords();
        List<List<Object>> list = ExportExcel.formatHashMapDataToList(new String[]{"校区", "教师姓名", "补习方式", "课时数", "课耗收入"},
                stuVoList,
                new String[]{"campusName", "staffName", "buxiStyleName", "keshiNum", "kehao"});
        try {
            // 需要将详细表一起导出
            ExportExcel.exportExcel(response, list, "教师课耗按补习方式统计导出", "教师课耗按补习方式统计导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/getKemuPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "按科目统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "staffName", value = "教师名称", required = false),
            @ApiImplicitParam(name = "subjectName", value = "科目名称", required = false),
//            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson getKemuPage(HttpServletRequest request,
                                @RequestParam(required = false, defaultValue = "10") long size,
                                @RequestParam(required = false, defaultValue = "1") long current,
                                @RequestParam(required = false) String campusID,
                                @RequestParam(required = false) String staffName,
                                @RequestParam(required = false) String subjectName,
                                String datesoe
//            String qiyeID
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        Page<HashMap<String, Object>> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("keshi.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("keshi.campusID", campusID);
        }
        if (StringUtils.isNotBlank(staffName)) {
            queryWrapper.like("staff.staffName", staffName);
        }
        if (StringUtils.isNotBlank(subjectName)) {
            queryWrapper.like("sub.subjectName", subjectName);
        }
        if (!ObjectUtils.isEmpty(datesoe)) {
            String[] s = (datesoe + " 23:59:59").split("_");
            queryWrapper.between("keshi.haveClassDate", s[0], s[1]);
        }
        page = iPxkeshistutableService.getKemuPage(page, queryWrapper);
        ajaxJson.setObj(page);

        return ajaxJson;
    }

    /**
     * 教师课耗按补习方式统计导出
     */
    @GetMapping("/exportKemuPage")
    @ResponseBody
    @ApiOperation(value = "教师课耗按科目统计导出")
    @ApiImplicitParams({
    })
    public void exportKemuPage(HttpServletRequest request, HttpServletResponse response) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("keshi.qiyeID", loginUser.getQiyeID());
        List<HashMap<String, Object>> stuVoList = iPxkeshistutableService.getBuxiStylePage(new Page(1, Integer.MAX_VALUE), queryWrapper).getRecords();
        List<List<Object>> list = ExportExcel.formatHashMapDataToList(new String[]{"校区", "教师姓名", "科目", "课时数", "课耗收入"},
                stuVoList,
                new String[]{"campusName", "staffName", "subjectName", "keshiNum", "kehao"});
        try {
            // 需要将详细表一起导出
            ExportExcel.exportExcel(response, list, "教师课耗按科目统计导出", "教师课耗按科目统计导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
