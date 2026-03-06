package com.xwcloud.cloud.homeschool.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.homeschool.Service.IPxgonggaojiazhangtableService;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.entity.Pxgonggaojiazhangtable;
import com.xwcloud.cloud.model.Vo.PxgonggaojiazhangtableVo;
import com.xwcloud.cloud.overall.LogUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

@Controller
@RequestMapping("/homeschool/pxjiazhanggonggao")
@Api(tags = "家长微公告")
public class PxjiazhanggonggaoController {

    @Autowired
    IPxgonggaojiazhangtableService iPxgonggaojiazhangtableService;
    @Autowired
    LogUtils logUtils;


    @RequestMapping(value = "/getPxjiazhanggonggaoPage",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取家长微公告分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name="size",value="分页大小",required=false),
            @ApiImplicitParam(name="current",value="页码大小",required=false)
    })
    public AjaxJson getPxjiazhanggonggaoPage(HttpServletRequest request,
                                             @RequestParam(required = false,defaultValue = "10")long size,
                                             @RequestParam(required = false,defaultValue = "1")long current
    ) {
        AjaxJson ajaxJson=new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<PxgonggaojiazhangtableVo> page =new Page<>(current,size);
        QueryWrapper queryWrapper =new QueryWrapper();
        queryWrapper.eq("jiazhanggonggao.qiyeID",loginUser.getQiyeID());
        page = iPxgonggaojiazhangtableService.getPage(page,null);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/getPxjiazhanggonggao",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取家长微公告")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="id",required=true),
    })
    public AjaxJson getPxjiazhanggonggao(HttpServletRequest request,
                                         String id
    ) {
        AjaxJson ajaxJson=new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Pxgonggaojiazhangtable pxgonggaojiazhangtable = iPxgonggaojiazhangtableService.getById(id);
        ajaxJson.setObj(pxgonggaojiazhangtable);
        return ajaxJson;
    }

    @RequestMapping(value = "/addPxjiazhanggonggao",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "新增家长微公告")
    @ApiImplicitParams({
            @ApiImplicitParam(name="parameterContent",value="公告内容",required=true),
    })
    public AjaxJson addPxjiazhanggonggao(HttpServletRequest request,
                               String parameterContent
    ) {
        AjaxJson ajaxJson=new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Pxgonggaojiazhangtable pxgonggaojiazhangtable =new Pxgonggaojiazhangtable();
        pxgonggaojiazhangtable.setParameterContent(parameterContent);
        pxgonggaojiazhangtable.setBianLiangName("微信端家长公告设置");
        pxgonggaojiazhangtable.setModifyValue("家长你好");
        pxgonggaojiazhangtable.setType("2");
        pxgonggaojiazhangtable.setTianjiastaff(loginUser.getStaffID());
        pxgonggaojiazhangtable.setTianjiashijian(new Date());
        pxgonggaojiazhangtable.setQiyeID(loginUser.getQiyeID());
        ajaxJson.setSuccess(iPxgonggaojiazhangtableService.save(pxgonggaojiazhangtable));
        return ajaxJson;
    }

    @RequestMapping(value = "/editPxjiazhanggonggao",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "修改家长微公告")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="家长微公告ID",required=true),
            @ApiImplicitParam(name="parameterContent",value="公告内容",required=true),
    })
    public AjaxJson editPxjiazhanggonggao(HttpServletRequest request,
                                         String id,
                                         String parameterContent
    ) {
        AjaxJson ajaxJson=new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Pxgonggaojiazhangtable pxgonggaojiazhangtable = iPxgonggaojiazhangtableService.getById(id);
        pxgonggaojiazhangtable.setParameterContent(parameterContent);
        ajaxJson.setSuccess(iPxgonggaojiazhangtableService.updateById(pxgonggaojiazhangtable));

        return ajaxJson;
    }

    @RequestMapping(value = "/delPxjiazhanggonggao",method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除家长微公告")
    @ApiImplicitParams({
            @ApiImplicitParam(name="IDs",value="家长微公告IDs",required=true),
    })
    public AjaxJson delPxjiazhanggonggao(HttpServletRequest request,
                                          String IDs
    ) {
        AjaxJson ajaxJson=new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        String[] strings = IDs.split(",");
        ajaxJson.setSuccess(iPxgonggaojiazhangtableService.removeByIds(Arrays.asList(strings)));

        return ajaxJson;
    }
}
