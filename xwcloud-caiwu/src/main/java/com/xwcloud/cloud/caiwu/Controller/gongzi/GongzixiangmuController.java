package com.xwcloud.cloud.caiwu.Controller.gongzi;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.caiwu.Service.IPxsalarystyletableService;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.Vo.PxsalarystyletableVo;
import com.xwcloud.cloud.model.entity.Pxsalarystyletable;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/caiwu/pxgongzitable/gongzixiangmu")
@Api(tags = "工资项目")
public class GongzixiangmuController {

    @Autowired
    IPxsalarystyletableService iPxsalarystyletableService;

    @RequestMapping(value = "/getGongzixiangmuPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取工资项目分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "salaryStyle", value = "工资类型名称", required = false),
            @ApiImplicitParam(name = "isJia", value = "加项还是减项", required = false),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = false),
    })
    public AjaxJson getGongzixiangmuPage(HttpServletResponse response,
                                         @RequestParam(required = false, defaultValue = "10") long size,
                                         @RequestParam(required = false, defaultValue = "1") long current,
                                         @RequestParam(required = false) String salaryStyle, // 项目名称
                                         @RequestParam(required = false) String isJia, // 加项减项
                                         String qiyeID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<PxsalarystyletableVo> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("style.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(salaryStyle)) {
            queryWrapper.like("style.salaryStyle", salaryStyle);
        }
        if (StringUtils.isNotBlank(isJia)) {
            queryWrapper.like("style.isJia", isJia);
        }
        page = iPxsalarystyletableService.getPage(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/addGongzixiangmu", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加工资项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id"),
            @ApiImplicitParam(name = "salaryStyle", value = "工资类别"),
            @ApiImplicitParam(name = "isJiaOrJianOrQiuhe", value = "是加项还是减项"),
            @ApiImplicitParam(name = "staffID", value = "录入人"),
            @ApiImplicitParam(name = "lurudate", value = "录入时间"),
            @ApiImplicitParam(name = "qiyeID", value = "qiyeID"),
    })
    public AjaxJson addGongzixiangmu(HttpServletRequest request,
                                     String salaryStyle,
                                     int isJiaOrJianOrQiuhe
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Pxsalarystyletable pxsalarystyletable = new Pxsalarystyletable();
        pxsalarystyletable.setQiyeID(loginUser.getQiyeID());
        pxsalarystyletable.setSalaryStyle(salaryStyle);
        pxsalarystyletable.setIsJiaOrJianOrQiuhe(isJiaOrJianOrQiuhe);
        pxsalarystyletable.setLurudate(new Date());
        pxsalarystyletable.setStaffID(loginUser.getStaffID());
        ajaxJson.setSuccess(iPxsalarystyletableService.save(pxsalarystyletable));
        return ajaxJson;
    }

    @RequestMapping(value = "/editGongzixiangmu", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "编辑工资项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id"),
            @ApiImplicitParam(name = "salaryStyle", value = "工资类别"),
            @ApiImplicitParam(name = "isJiaOrJianOrQiuhe", value = "是加项还是减项"),
            @ApiImplicitParam(name = "staffID", value = "录入人"),
            @ApiImplicitParam(name = "lurudate", value = "录入时间"),
            @ApiImplicitParam(name = "qiyeID", value = "qiyeID"),
    })
    public AjaxJson editGongzixiangmu(HttpServletResponse response,
                                      Pxsalarystyletable pxsalarystyletable
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(iPxsalarystyletableService.updateById(pxsalarystyletable));
        return ajaxJson;
    }

    @RequestMapping(value = "/getGongzixiangmu", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取工资项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID", value = "id"),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
    })
    public AjaxJson getGongzixiangmu(HttpServletResponse response,
                                     String ID,
                                     String qiyeID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", qiyeID);
        queryWrapper.eq("id", ID);
        Pxsalarystyletable pxsalarystyletable = iPxsalarystyletableService.getOne(queryWrapper);
        ajaxJson.setObj(pxsalarystyletable);
        return ajaxJson;
    }

    @RequestMapping(value = "/delGongzixiangmu", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除工资项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "IDs", value = "IDs"),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
    })
    public AjaxJson delGongzixiangmu(HttpServletRequest request,
                                     String IDs,
                                     String qiyeID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        String[] strings = IDs.split(",");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", qiyeID);
        queryWrapper.in("id", strings);
        List<Pxsalarystyletable> list = iPxsalarystyletableService.list(queryWrapper);
        if (list == null || list.size() <= 0) {
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        ajaxJson.setSuccess(iPxsalarystyletableService.removeByIds(Arrays.asList(strings)));
        return ajaxJson;
    }
}
