package com.xwcloud.cloud.caiwu.Controller.other;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.caiwu.Service.IPxqiandansubjecttableService;
import com.xwcloud.cloud.caiwu.Service.IPxsubjecttableService;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.ExportExcel;
import com.xwcloud.cloud.model.Sso.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/shujutongji/other")
@Api(tags = "科目财务统计")
public class SubjectCaiwuTongjiController {

    @Autowired
    private IPxsubjecttableService pxsubjecttableService;
    @Autowired
    private IPxqiandansubjecttableService pxqiandansubjecttableService;


    /**
     * 查询所有科目（格式：校区_科目）
     * */
    @GetMapping("/getSubject")
    @ResponseBody
    @ApiOperation(value = "查询所有科目")
    @ApiImplicitParams({})
    public AjaxJson getSubject(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setObj(pxsubjecttableService.getSubject(loginUser.getQiyeID()));
        ajaxJson.setCode("success");
        return ajaxJson;
    }

    //region 科目收费统计

    /**
     * 获取科目收费统计
     * */
    @GetMapping("/getKemuShoufeiTongji")
    @ResponseBody
    @ApiOperation(value = "获取科目收费统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = true),
            @ApiImplicitParam(name = "current", value = "页码", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID"),
            @ApiImplicitParam(name = "subjectID", value = "科目ID"),
            @ApiImplicitParam(name = "datesoe", value = "签单日期区间：start_end"),
    })
    public AjaxJson getKemuShoufeiTongji(HttpServletRequest request, long size, long current, Long campusID, Long subjectID, String datesoe) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<HashMap<String, Object>> wrapper = new QueryWrapper<>();
        wrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (!ObjectUtils.isEmpty(campusID)) {
            wrapper.eq("e.campusID", campusID);
        }
        if (!ObjectUtils.isEmpty(subjectID)) {
            wrapper.eq("b.subjectID", subjectID);
        }
        if (!ObjectUtils.isEmpty(datesoe)) {
            String[] s = (datesoe + " 23:59:59").split("_");
            wrapper.between("a.qiandandate", s[0], s[1]);
        }
        Page<HashMap<String, Object>> page = pxqiandansubjecttableService.getKemuShoufeiTongji(new Page(current, size), wrapper);
        ajaxJson.setObj(page);
        ajaxJson.setCode("success");
        return ajaxJson;
    }

    /**
     * 导出科目收费统计
     * */
    @GetMapping("/exportKemuShoufeiTongji")
    @ResponseBody
    @ApiOperation(value = "导出科目收费统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID"),
            @ApiImplicitParam(name = "subjectID", value = "科目ID"),
            @ApiImplicitParam(name = "datesoe", value = "签单日期区间：start_end"),
    })
    public void exportKemuShoufeiTongji(HttpServletRequest request, HttpServletResponse response, Long campusID, Long subjectID, String datesoe) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<HashMap<String, Object>> wrapper = new QueryWrapper<>();
        wrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (!ObjectUtils.isEmpty(campusID)) {
            wrapper.eq("e.campusID", campusID);
        }
        if (!ObjectUtils.isEmpty(subjectID)) {
            wrapper.eq("b.subjectID", subjectID);
        }
        if (!ObjectUtils.isEmpty(datesoe)) {
            String[] s = (datesoe + " 23:59:59").split("_");
            wrapper.between("a.qiandandate", s[0], s[1]);
        }
        List<HashMap<String, Object>> list = pxqiandansubjecttableService.getKemuShoufeiTongji(new Page(1, Integer.MAX_VALUE), wrapper).getRecords();
        List<List<Object>> returnlist = ExportExcel.formatHashMapDataToList(
                new String[]{"校区", "科目", "一对一收费金额", "非一对一收费金额", "收费总额"},
                list,
                new String[]{"campusName", "subjectName", "money1v1", "moneyNvN", "moneyCount"});
        try {
            ExportExcel.exportExcel(response, returnlist, "导出科目收费统计", "导出科目收费统计.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //endregion

    //region 科目余额统计

    /**
     * 科目余额统计
     * */
    @GetMapping("/getSubjectYuETongji")
    @ResponseBody
    @ApiOperation(value = "获取科目余额统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = true),
            @ApiImplicitParam(name = "current", value = "页码", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID"),
            @ApiImplicitParam(name = "subjectID", value = "科目ID"),
            @ApiImplicitParam(name = "datesoe", value = "签单日期区间：start_end"),
    })
    public AjaxJson getSubjectYuETongji(HttpServletRequest request, long size, long current, Long campusID, Long subjectID, String datesoe) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<HashMap<String, Object>> wrapper = new QueryWrapper<>();
        wrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (!ObjectUtils.isEmpty(campusID)) {
            wrapper.eq("d.campusID", campusID);
        }
        if (!ObjectUtils.isEmpty(subjectID)) {
            wrapper.eq("b.subjectID", subjectID);
        }
        if (!ObjectUtils.isEmpty(datesoe)) {
            String[] s = (datesoe + " 23:59:59").split("_");
            wrapper.between("f.qiandandate", s[0], s[1]);
        }
        Page<HashMap<String, Object>> page = pxqiandansubjecttableService.getSubjectYuETongji(new Page(current, size), wrapper);
        ajaxJson.setObj(page);
        ajaxJson.setCode("success");
        return ajaxJson;
    }


    /**
     * 导出科目余额统计
     * */
    @GetMapping("/exportSubjectYuETongji")
    @ResponseBody
    @ApiOperation(value = "导出科目余额统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID"),
            @ApiImplicitParam(name = "subjectID", value = "科目ID"),
            @ApiImplicitParam(name = "datesoe", value = "签单日期区间：start_end"),
    })
    public void exportSubjectYuETongji(HttpServletRequest request, HttpServletResponse response, Long campusID, Long subjectID, String datesoe) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<HashMap<String, Object>> wrapper = new QueryWrapper<>();
        wrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (!ObjectUtils.isEmpty(campusID)) {
            wrapper.eq("d.campusID", campusID);
        }
        if (!ObjectUtils.isEmpty(subjectID)) {
            wrapper.eq("b.subjectID", subjectID);
        }
        if (!ObjectUtils.isEmpty(datesoe)) {
            String[] s = (datesoe + " 23:59:59").split("_");
            wrapper.between("f.qiandandate", s[0], s[1]);
        }
        List<HashMap<String, Object>> list = pxqiandansubjecttableService.getSubjectYuETongji(new Page(1, Integer.MAX_VALUE), wrapper).getRecords();
        List<HashMap<String, Object>> collect = list.stream().map(item -> {
            if (item.get("moneydate").toString().equals("0E-8")){
                item.replace("moneydate",0);
            }
            if (item.get("moneynodate").toString().equals("0E-8")){
                item.replace("moneynodate",0);
            }
            item.put("moneyCount", Double.parseDouble(item.get("moneydate").toString())+Double.parseDouble(item.get("moneynodate").toString()));
            return item;
        }).collect(Collectors.toList());
        List<List<Object>> returnlist = ExportExcel.formatHashMapDataToList(
                new String[]{ "校区", "科目", "按起止日期计费学费总额", "不按起止日期计费学费总额", "学费余额总额" },
                list,
                new String[]{"campusName", "subjectName", "moneydate", "moneynodate", "moneyCount"});
        try {
            ExportExcel.exportExcel(response, returnlist, "导出科目余额统计", "导出科目余额统计.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //endregion

}
