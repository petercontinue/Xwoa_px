package com.xwcloud.cloud.homeschool.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.homeschool.Service.IPxmanyidutableService;
import com.xwcloud.cloud.model.Sso.LoginUser;

import com.xwcloud.cloud.model.Vo.PxmanyidutableVo;
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
import java.util.Arrays;

@Controller
@RequestMapping("/homeschool/pxmanyidutable")
@Api(tags = "满意度评价")
public class PxmanyidutableController {

    @Autowired
    IPxmanyidutableService iPxmanyidutableService;


    @RequestMapping(value = "/getManyiduPage",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取满意度分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name="size",value="分页大小",required=true),
            @ApiImplicitParam(name="current",value="页码",required=true),
            @ApiImplicitParam(name="campusID",value="校区ID",required=false),
            @ApiImplicitParam(name="stuName",value="姓名",required=false),
            @ApiImplicitParam(name="parentTel",value="家长账号",required=false),
            @ApiImplicitParam(name="startDateTime",value="开始日期",required=false),
            @ApiImplicitParam(name="endDateTime",value="结束日期",required=false),
            @ApiImplicitParam(name="qiyeID",value="企业ID"),
    })
    public AjaxJson getManyiduPage(HttpServletRequest request,
                                   @RequestParam(required = false,defaultValue = "10")long size,
                                   @RequestParam(required = false,defaultValue = "1")long current,
                                   @RequestParam(required = false)String campusID ,
                                   @RequestParam(required = false)String stuName,
                                   @RequestParam(required = false)String parentTel,
                                   @RequestParam(required = false)String startDateTime,
                                   @RequestParam(required = false)String endDateTime
    ) {
        AjaxJson ajaxJson=new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID =loginUser.getQiyeID();
        Page<PxmanyidutableVo> page = new Page<>(current,size);
        QueryWrapper queryWrapper =new QueryWrapper();
        queryWrapper.eq("manyidu.qiyeID",qiyeID);
        if (StringUtils.isNotBlank(campusID)){
            queryWrapper.like("stu.campusID",campusID);
        }
        if (StringUtils.isNotBlank(stuName)){
            queryWrapper.like("stu.stuName",stuName);
        }
        if (StringUtils.isNotBlank(parentTel)){
            queryWrapper.like("stu.parentTel",parentTel);
        }
        if (StringUtils.isNotBlank(startDateTime)){
            queryWrapper.ge("pingjiaDate",startDateTime);
        }
        if (StringUtils.isNotBlank(endDateTime)){
            queryWrapper.le("pingjiaDate",endDateTime);
        }
        page=iPxmanyidutableService.getPage(page,queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/delManyidu",method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除满意度信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="IDs",value="满意度IDs,用逗号分隔",required=true),
    })
    public AjaxJson delManyidu(HttpServletRequest request,
                               String IDs
    ) {
        AjaxJson ajaxJson=new AjaxJson();
        String[] strings =IDs.split(",");
        ajaxJson.setSuccess(iPxmanyidutableService.removeByIds(Arrays.asList(strings)));
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");

        return ajaxJson;
    }
}
