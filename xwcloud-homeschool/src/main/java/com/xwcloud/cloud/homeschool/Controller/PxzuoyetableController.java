package com.xwcloud.cloud.homeschool.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.ExportExcel;
import com.xwcloud.cloud.homeschool.Service.IPxzuoyetableService;
import com.xwcloud.cloud.overall.LogUtils;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.entity.Pxzuoyetable;
import com.xwcloud.cloud.model.Vo.PxzuoyetableVo;
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
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/homeschool/pxzuoyetable")
@Api(tags = "微作业")
public class PxzuoyetableController {
    @Autowired
    IPxzuoyetableService iPxzuoyetableService;
    @Autowired
    LogUtils logUtils;

    @RequestMapping(value = "/addZuoye", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加作业信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "neirong", value = "作业内容", required = true),
            @ApiImplicitParam(name = "classID", value = "班级ID", required = true),
            @ApiImplicitParam(name = "endDate", value = "作业上交的截止时间", required = true),
            @ApiImplicitParam(name = "zuoyeImg", value = "图片作业附件，多个文件逗号隔开", required = false),
            @ApiImplicitParam(name = "zuoyeMp3", value = "音频作业附件，多个文件逗号隔开", required = false),
            @ApiImplicitParam(name = "zuoyeVideo", value = "视频作业附件，多个文件逗号隔开", required = false),
            @ApiImplicitParam(name = "otherFile", value = "其他作业附件，多个文件逗号隔开", required = false),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson addZuoye(HttpServletRequest request, Pxzuoyetable pxzuoyetable) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        pxzuoyetable.setAddStaffID(loginUser.getStaffID());
        pxzuoyetable.setAddTime(new Date());
        pxzuoyetable.setQiyeID(loginUser.getQiyeID());
        ajaxJson.setSuccess(iPxzuoyetableService.save(pxzuoyetable));
        return ajaxJson;
    }

    @RequestMapping(value = "/editZuoye", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改作业信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "作业ID", required = true),
            @ApiImplicitParam(name = "neirong", value = "作业内容", required = true),
            @ApiImplicitParam(name = "classID", value = "班级ID", required = true),
            @ApiImplicitParam(name = "endDate", value = "作业上交的截止时间", required = true),
            @ApiImplicitParam(name = "zuoyeImg", value = "图片作业附件，多个文件逗号隔开", required = false),
            @ApiImplicitParam(name = "zuoyeMp3", value = "音频作业附件，多个文件逗号隔开", required = false),
            @ApiImplicitParam(name = "zuoyeVideo", value = "视频作业附件，多个文件逗号隔开", required = false),
            @ApiImplicitParam(name = "otherFile", value = "其他作业附件，多个文件逗号隔开", required = false),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson editZuoye(HttpServletRequest request, Pxzuoyetable pxzuoyetable) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setSuccess(iPxzuoyetableService.updateById(pxzuoyetable));
        return ajaxJson;
    }

    @RequestMapping(value = "/getZuoyeById", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取作业信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "作业ID", required = true),
    })
    public AjaxJson getZuoyeById(HttpServletRequest request, String id) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        Pxzuoyetable pxzuoyetable = iPxzuoyetableService.getOne(queryWrapper);
        ajaxJson.setObj(pxzuoyetable);
        return ajaxJson;
    }

    @RequestMapping(value = "/delZuoye", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除作业信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Ids", value = "班级ID数组,以英文逗号','分割", required = true),
    })
    public AjaxJson delZuoye(HttpServletRequest request, String Ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] IDs = Ids.split(",");
        ajaxJson.setSuccess(iPxzuoyetableService.removeByIds(Arrays.asList(IDs)));
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        return ajaxJson;
    }


    @RequestMapping(value = "/getZuoyePage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取作业信息分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = true),
            @ApiImplicitParam(name = "current", value = "页码", required = true),
            @ApiImplicitParam(name = "teacherName", value = "老师名称", required = false),
            @ApiImplicitParam(name = "className", value = "班级名称", required = false),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson getZuoyePage(HttpServletRequest request,
                                 @RequestParam(required = false, defaultValue = "10") long size,
                                 @RequestParam(required = false, defaultValue = "1") long current,
                                 @RequestParam(required = false) String teacherName,
                                 @RequestParam(required = false) String className
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<PxzuoyetableVo> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("zuoye.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(teacherName)) {
            queryWrapper.like("staff.staffName", teacherName);
        }
        if (StringUtils.isNotBlank(className)) {
            queryWrapper.like("class.className", className);
        }
        page = iPxzuoyetableService.getPage(page, queryWrapper);
        ajaxJson.setObj(page);

        return ajaxJson;
    }

    @RequestMapping(value = "/getZuoyeDetaile", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取作业详细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Ids", value = "班级ID数组,以英文逗号','分割", required = true),
    })
    public AjaxJson getZuoyeDetaile(HttpServletRequest request,
                                    @RequestParam(required = false, defaultValue = "10") long size,
                                    @RequestParam(required = false, defaultValue = "1") long current) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<HashMap<String, String>> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("stuzuoye.zuoyeID", loginUser.getQiyeID());
        page = iPxzuoyetableService.getZuoyeDetaile(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/exportZuoye", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出作业信息")
    public void exportZuoye(HttpServletRequest request, HttpServletResponse response) {
        QueryWrapper queryWrapper = new QueryWrapper();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        queryWrapper.eq("zuoye.qiyeID", loginUser.getQiyeID());
        List<PxzuoyetableVo> pxzuoyetables = iPxzuoyetableService.getJoinList(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"作业要求", "老师", "班级", "截止时间", "添加时间"},
                pxzuoyetables,
                new String[]{"neirong", "teacherName", "className", "endDate-DT", "addTime-DT"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "微作业.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
