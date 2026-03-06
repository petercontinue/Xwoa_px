package com.xwcloud.cloud.caiwu.Controller.gongzi;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.caiwu.Service.IPxsalarystyletableService;
import com.xwcloud.cloud.caiwu.Service.IPxsalarytableService;
import com.xwcloud.cloud.caiwu.Service.IPxsalaryxiangxitableService;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.ExportExcel;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.Vo.PxsalarytableVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/caiwu/pxgongzitable/gongzitiao")
@Api(tags = "工资条")
public class GongzitiaoController {

    @Autowired
    IPxsalarytableService iPxsalarytableService;
    @Autowired
    IPxsalaryxiangxitableService iPxsalaryxiangxitableService;
    @Autowired
    IPxsalarystyletableService iPxsalarystyletableService;

    @RequestMapping(value = "/getGongziPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取工资分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = true),
            @ApiImplicitParam(name = "current", value = "页码", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "staffPost", value = "岗位名称", required = false),
            @ApiImplicitParam(name = "staffName", value = "员工名称", required = false),

    })
    public AjaxJson getGongziPage(HttpServletRequest request,
                                  @RequestParam(required = false, defaultValue = "10") long size,
                                  @RequestParam(required = false, defaultValue = "1") long current,
                                  @RequestParam(required = false) String campusID, // 校区ID
                                  @RequestParam(required = false) String staffPost, // 岗位
                                  @RequestParam(required = false) String staffName // 员工名称
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<PxsalarytableVo> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("salary.shenheState", 1);
        queryWrapper.eq("salary.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("campus.id", campusID);
        }
        if (StringUtils.isNotBlank(staffPost)) {
            queryWrapper.like("staffpost.staffpostName", staffPost);
        }
        if (StringUtils.isNotBlank(staffName)) {
            queryWrapper.like("staff.staffName", staffName);
        }
        queryWrapper.orderByDesc("salary.lurudatetime");
        page = iPxsalarytableService.getPage(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/exportGongzitiao", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出工资条")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "staffPost", value = "岗位名称", required = false),
            @ApiImplicitParam(name = "staffName", value = "员工名称", required = false),
    })
    public void exportGongzitiao(HttpServletResponse response,
                                 HttpServletRequest request,
                                 @RequestParam(required = false) String campusID, // 校区ID
                                 @RequestParam(required = false) String staffPost, // 岗位
                                 @RequestParam(required = false) String staffName // 员工名称
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        List<PxsalarytableVo> list = null;
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.ne("campus.isOpen", 2);
        queryWrapper.eq("salary.shenheState", 1);
        queryWrapper.eq("salary.qiyeID", loginUser.getQiyeID());
        QueryWrapper headQW = new QueryWrapper();
        headQW.select("salaryStyle");
        List<String> headlist = iPxsalarystyletableService.listObjs(headQW);
        List<HashMap<String, String>> hashMapList = iPxsalarytableService.getGongzitiao(queryWrapper, headlist);
        List<List<Object>> rowList = new ArrayList<>();// 定义列数据
        List<Object> tableHeads = new ArrayList<>();
        tableHeads.add("id");
        tableHeads.add("校区");
        tableHeads.add("员工名称");
        tableHeads.add("岗位");
        tableHeads.add("工资时间");
        tableHeads.addAll(headlist);
        rowList.add(tableHeads);
        for (int i = 0; i < hashMapList.size(); i++) {
            HashMap<String, String> hashMap = hashMapList.get(i);
            List<Object> line = new ArrayList<>();//定义行数据
            for (int j = 0; j < tableHeads.size(); j++) {
                line.add(hashMap.get(tableHeads.get(j)));
            }
            rowList.add(line);
        }

       /* List<List<Object>> borrowlist = ExportExcel.formatDataToList(head,
                ,
                new String[]{"campusName", "booksName", "borrownum", "peopleName", "role", "endDate-DT", "beizhu",
                        "dostaffName", "doDate-DT", "returnNum"});*/
        try {
            ExportExcel.exportExcel(response, rowList, "工资条", "工资条.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
