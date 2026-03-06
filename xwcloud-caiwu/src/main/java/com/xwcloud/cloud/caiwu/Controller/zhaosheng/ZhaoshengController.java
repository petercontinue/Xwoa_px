package com.xwcloud.cloud.caiwu.Controller.zhaosheng;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.caiwu.Service.IPxyxtelfromtableService;
import com.xwcloud.cloud.common.AjaxJson;
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
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/shujutongji/zhaosheng")
@Api(tags = "招生统计")
public class ZhaoshengController {

    @Autowired
    IPxyxtelfromtableService iPxyxtelfromtableService;


    @RequestMapping(value = "/getZhaoshengBili", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "招生途径比例")
    @ApiImplicitParams({
            @ApiImplicitParam(name="startDate",value="开始时间",required=false),
            @ApiImplicitParam(name="endDate",value="结束时间",required=false),
//            @ApiImplicitParam(name="qiyeID",value="企业ID",required=true),
    })
    public AjaxJson getZhaoshengBili(HttpServletRequest request, HttpServletResponse respons,
                                     @RequestParam(required = false) String startDate,
                                     @RequestParam(required = false) String endDate
//                                     String qiyeID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper =new QueryWrapper();
        queryWrapper.eq("stu.qiyeID",loginUser.getQiyeID());
        /*if (StringUtils.isBlank(startDate)&&StringUtils.isBlank(endDate)){
            startDate = DateUtil.getNowYear()+"-01-01";
            queryWrapper.ge("qdinfo.qiandandate",startDate);
        }*/
        if (StringUtils.isNotBlank(startDate)){
            queryWrapper.ge("qdinfo.qiandandate",startDate);
        }
        if (StringUtils.isNotBlank(endDate)){
            queryWrapper.le("qdinfo.qiandandate",endDate);
        }

        List<HashMap<String,String>> list = iPxyxtelfromtableService.getZhaoshengBili(queryWrapper);
        ajaxJson.setObj(list);
        return ajaxJson;
    }


    @RequestMapping(value = "/getZhaoshengList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "招生途径")
    @ApiImplicitParams({
            @ApiImplicitParam(name="size",value="分页大小",required=false),
            @ApiImplicitParam(name="current",value="页码",required=false),
            @ApiImplicitParam(name="campusID",value="校区ID",required=false),
            @ApiImplicitParam(name="xuehao",value="学号",required=false),
            @ApiImplicitParam(name="stuName",value="学生名称",required=false),
            @ApiImplicitParam(name="gradeID",value="年级ID",required=false),
            @ApiImplicitParam(name="lytj",value="来源途径",required=false),
            @ApiImplicitParam(name="startTime",value="开始时间",required=false),
            @ApiImplicitParam(name="endTime",value="结束时间",required=false),
//            @ApiImplicitParam(name="qiyeID",value="企业ID",required=true),
    })
    public AjaxJson getZhaoshengList(HttpServletRequest request, HttpServletResponse respons,
                                     @RequestParam(required = false,defaultValue = "10")long size,
                                     @RequestParam(required = false,defaultValue = "1")long current,
                                     @RequestParam(required = false)String campusID,
                                     @RequestParam(required = false)String xuehao,
                                     @RequestParam(required = false)String stuName,
                                     @RequestParam(required = false)String gradeID,
                                     @RequestParam(required = false)String lytj, // 来源途径
                                     @RequestParam(required = false)String startTime,
                                     @RequestParam(required = false)String endTime
//                                     String qiyeID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<HashMap<String,String>> page = new Page(current,size);
        QueryWrapper queryWrapper =new QueryWrapper();
        queryWrapper.eq("stu.qiyeID",loginUser.getQiyeID());
        if (StringUtils.isNotBlank(campusID)){
            queryWrapper.eq("campus.id",campusID);
        }
        if (StringUtils.isNotBlank(xuehao)){
            queryWrapper.like("stu.zidingyiStuID",xuehao);
        }
        if (StringUtils.isNotBlank(stuName)){
            queryWrapper.like("stu.stuName",stuName);
        }
        if (StringUtils.isNotBlank(gradeID)){
            queryWrapper.eq("grade.id",gradeID);
        }
        if (StringUtils.isNotBlank(lytj)){
            queryWrapper.eq("yxly.id",lytj);
        }
        if (StringUtils.isNotBlank(startTime)){
            queryWrapper.ge("qdinfo.qiandandate",startTime);
        }
        if (StringUtils.isNotBlank(endTime)){
            queryWrapper.le("qdinfo.qiandandate",endTime);
        }
        page = iPxyxtelfromtableService.getZhaoshengList(page,queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/getLaiyuantujingList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "来源途径")
    @ApiImplicitParams({
            @ApiImplicitParam(name="startDate",value="开始时间",required=false),
            @ApiImplicitParam(name="endDate",value="结束时间",required=false),
//            @ApiImplicitParam(name="qiyeID",value="企业ID",required=true),
    })
    public AjaxJson getLaiyuantujingList(HttpServletRequest request, HttpServletResponse respons,
                                     @RequestParam(required = false) String startDate,
                                     @RequestParam(required = false) String endDate
//                                     String qiyeID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper =new QueryWrapper();
        queryWrapper.eq("qiyeID",loginUser.getQiyeID());
        List<HashMap<String,String>> list = iPxyxtelfromtableService.getLaiyuantujingList(queryWrapper);
        ajaxJson.setObj(list);
        return ajaxJson;
    }
}
