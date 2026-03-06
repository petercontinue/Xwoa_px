package com.xwcloud.cloud.caiwu.Controller.zhaosheng;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.caiwu.Service.IPxoldschooltableService;
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
@RequestMapping("/shujutongji/oldschool")
@Api(tags = "招生统计")
public class OldschoolController {

    @Autowired
    IPxoldschooltableService iPxoldschooltableService;

    @RequestMapping(value = "/getOldschoolstuPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "学生来源分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name="size",value="分页大小",required=false),
            @ApiImplicitParam(name="current",value="页码",required=false),
            @ApiImplicitParam(name="campusID",value="校区ID",required=false),
            @ApiImplicitParam(name="stuXuehao",value="学号",required=false),
            @ApiImplicitParam(name="stuName",value="学生名称",required=false),
            @ApiImplicitParam(name="gradeID",value="年级ID",required=false),
            @ApiImplicitParam(name="oldSchool",value="来源学校",required=false),
//            @ApiImplicitParam(name="qiyeID",value="企业ID",required=true),
    })
    public AjaxJson getOldschoolstuPage(HttpServletRequest request, HttpServletResponse respons,
                                   @RequestParam(required = false,defaultValue = "10")long size,
                                   @RequestParam(required = false,defaultValue = "1")long current,
                                   @RequestParam(required = false) String campusID,
                                   @RequestParam(required = false) String stuXuehao,
                                   @RequestParam(required = false) String stuName,
                                   @RequestParam(required = false) String gradeID,
                                   @RequestParam(required = false) String oldSchool
//                                        String qiyeID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper =new QueryWrapper();
        Page<HashMap<String,String>> page = new Page(current,size);
        queryWrapper.eq("stu.qiyeID",loginUser.getQiyeID());
        if (StringUtils.isNotBlank(campusID)){
            queryWrapper.eq("campus.id",campusID);
        }
        if (StringUtils.isNotBlank(stuXuehao)){
            queryWrapper.like("stu.zidingyiStuID",stuXuehao);
        }
        if (StringUtils.isNotBlank(stuName)){
            queryWrapper.like("stu.stuName",stuName);
        }
        if (StringUtils.isNotBlank(gradeID)){
            queryWrapper.eq("grade.id",gradeID);
        }
        if (StringUtils.isNotBlank(oldSchool)){
            queryWrapper.like("oldschool.oldSchoolName",oldSchool);
        }
        page = iPxoldschooltableService.getOldschoolDetail(page,queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/getOldschoolBili", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "学生来源学校比例")
    @ApiImplicitParams({
            @ApiImplicitParam(name="startDate",value="开始日期",required=false),
            @ApiImplicitParam(name="endDate",value="结束日期",required=false),
//            @ApiImplicitParam(name="qiyeID",value="企业ID",required=true),
    })
    public AjaxJson getOldschoolBili(HttpServletRequest request, HttpServletResponse respons,
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
            queryWrapper.ge("stu.dengjiTime",startDate);
        }*/
        if (StringUtils.isNotBlank(startDate)){
            queryWrapper.ge("stu.dengjiTime",startDate);
        }
        if (StringUtils.isNotBlank(endDate)){
            queryWrapper.le("stu.dengjiTime",endDate);
        }

        List<HashMap<String,String>> list = iPxoldschooltableService.getOldschoolBili(queryWrapper);
        ajaxJson.setObj(list);
        return ajaxJson;
    }
}
