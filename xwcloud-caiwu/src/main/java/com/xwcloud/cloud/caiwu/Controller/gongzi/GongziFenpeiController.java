package com.xwcloud.cloud.caiwu.Controller.gongzi;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.caiwu.Service.IPxsalarystaffposttableService;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.Vo.PxsalarystaffposttableVo;
import com.xwcloud.cloud.model.entity.Pxsalarystaffposttable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/caiwu/pxgongzitable/gongzifenpei")
@Api(tags = "工资项目分配")
public class GongziFenpeiController {

    @Autowired
    IPxsalarystaffposttableService iPxsalarystaffposttableService;

    @RequestMapping(value = "/getGongzifenpeiPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取工资项目分配分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
    })
    public AjaxJson getGongzifenpeiPage(HttpServletResponse response,
                                        @RequestParam(required = false, defaultValue = "10") long size,
                                        @RequestParam(required = false, defaultValue = "1") long current,
                                        String qiyeID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", qiyeID);
        Page<PxsalarystaffposttableVo> page = new Page<>(current, size);
        page = iPxsalarystaffposttableService.getPage(page, null);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/editGongzifenpei", method = RequestMethod.POST)
    @Transactional(rollbackFor = {Exception.class})
    @ResponseBody
    @ApiOperation(value = "修改工资分配")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "postID", value = "岗位ID"),
            @ApiImplicitParam(name = "xiangmuIDs", value = "项目IDs"),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
    })
    public AjaxJson editGongzifenpei(Authentication authentication,
                                     String postID, // 岗位ID
                                     String xiangmuIDs, HttpServletRequest request

    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("staffPostID", postID);
        // 删除对应岗位的项目
        iPxsalarystaffposttableService.remove(queryWrapper);
        // 重新添加对应岗位的项目
        String[] IDs = xiangmuIDs.split(",");
        List<Pxsalarystaffposttable> list = new ArrayList<>();
        for (int i = 0; i < IDs.length; i++) {
            Pxsalarystaffposttable staffpost = new Pxsalarystaffposttable();
            staffpost.setStaffPostID(postID);
            staffpost.setSalaryStyleID(IDs[i]);
            staffpost.setQiyeID(loginUser.getQiyeID());
            list.add(staffpost);
        }
        ajaxJson.setSuccess(iPxsalarystaffposttableService.saveBatch(list));
        return ajaxJson;
    }

    @RequestMapping(value = "/getGongzifenpei", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取工资分配")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "postID", value = "岗位ID"),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
    })
    public AjaxJson getGongzifenpei(HttpServletResponse response,
                                    String postID,
                                    String qiyeID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("post.id", postID);
        queryWrapper.eq("salarypost.qiyeID", qiyeID);
        List<PxsalarystaffposttableVo> pxsalarystaffposttableVo = iPxsalarystaffposttableService.getJoinList(queryWrapper);
        PxsalarystaffposttableVo obj = null;
        if (pxsalarystaffposttableVo.size() > 0) {
            obj = pxsalarystaffposttableVo.get(0);
        }
        if (obj == null) {
            ajaxJson.setSuccess(false);
        } else {
            ajaxJson.setObj(obj);
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/getGongzixiangmuList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取工资项目列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
    })
    public AjaxJson getGongzixiangmuList(HttpServletRequest request
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        List<HashMap<String, String>> hashMapList = iPxsalarystaffposttableService.getGongzixiangmuList(queryWrapper);
        if (hashMapList == null) {
            ajaxJson.setSuccess(false);
        } else {
            ajaxJson.setObj(hashMapList);
        }
        return ajaxJson;
    }
}
