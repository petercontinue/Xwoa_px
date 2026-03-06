package com.xwcloud.cloud.homeschool.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.homeschool.Service.IPxevaluationtableService;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.entity.Pxcampustable;
import com.xwcloud.cloud.model.entity.Pxclasstable;
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
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/homeschool/public")
@Api(tags = "公共方法")
public class PublicMethodController {

    @Autowired
    IPxevaluationtableService iPxevaluationtableService;

    @RequestMapping(value = "/getCampusList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取校区")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusName", value = "校区名称", required = true),
//            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson getCampusList(HttpServletRequest request, @RequestParam(required = false) String campusName/*, String qiyeID*/) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper.ne("isOpen", 2);
        if (StringUtils.isNotBlank(campusName)) {
            queryWrapper.like("campusName", campusName);
        }
        List<Pxcampustable> pxcampustableList = iPxevaluationtableService.getCampusList(queryWrapper);
        if (pxcampustableList == null) {
            ajaxJson.setSuccess(false);
        } else {
            ajaxJson.setSuccess(true);
            ajaxJson.setObj(pxcampustableList);
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/getClassList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取班级列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false)
    })
    public AjaxJson getClassList(@RequestParam(required = false) String campusID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("campusID", campusID);
        }
        List<Pxclasstable> pxclasstableList = iPxevaluationtableService.getClassList(queryWrapper);
        if (pxclasstableList == null) {
            ajaxJson.setSuccess(false);
        } else {
            ajaxJson.setSuccess(true);
            ajaxJson.setObj(pxclasstableList);
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/getStaffList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取教师列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false)
    })
    public AjaxJson getStaffList(@RequestParam(required = false) String campusID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("campusID", campusID);
        }
        List<HashMap<String, String>> pxclasstableList = iPxevaluationtableService.getPublicStaffList(queryWrapper);
        if (pxclasstableList == null) {
            ajaxJson.setSuccess(false);
        } else {
            ajaxJson.setSuccess(true);
            ajaxJson.setObj(pxclasstableList);
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/getClassRoomList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取教室列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false)
    })
    public AjaxJson getClassRoomList(@RequestParam(required = false) String campusID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("campusID", campusID);
        }
        List<HashMap<String, String>> pxclasstableList = iPxevaluationtableService.getClassRoomList(queryWrapper);
        if (pxclasstableList == null) {
            ajaxJson.setSuccess(false);
        } else {
            ajaxJson.setSuccess(true);
            ajaxJson.setObj(pxclasstableList);
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/getPublicStuList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取学生列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson getPublicStuList(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        List<HashMap<String, String>> pxclasstableList = iPxevaluationtableService.getPublicStuList(queryWrapper);
        if (pxclasstableList == null) {
            ajaxJson.setSuccess(false);
        } else {
            ajaxJson.setSuccess(true);
            ajaxJson.setObj(pxclasstableList);
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/getPublicStaffPostList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取员工岗位列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true)
    })
    public AjaxJson getPublicStaffPostList(@RequestParam(required = false) String campusID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        LoginUser loginUser=(LoginUser) request.getAttribute("loginUser");
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("campusID", campusID);
        }
        List<HashMap<String, String>> pxclasstableList = iPxevaluationtableService.getPublicStaffPostList(queryWrapper);

        if (pxclasstableList == null) {
            ajaxJson.setSuccess(false);
        } else {
            ajaxJson.setSuccess(true);
            ajaxJson.setObj(pxclasstableList);
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/getPublicKemuList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取科目列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true),
    })
    public AjaxJson getPublicKemuList(@RequestParam(required = false) String campusID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        LoginUser loginUser=(LoginUser)request.getAttribute("loginUser");
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("campusID", campusID);
        }
        List<HashMap<String, String>> pxclasstableList = iPxevaluationtableService.getPublicKemuList(queryWrapper);

        if (pxclasstableList == null) {
            ajaxJson.setSuccess(false);
        } else {
            ajaxJson.setSuccess(true);
            ajaxJson.setObj(pxclasstableList);
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/getStugradeList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取年级列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true),
    })
    public AjaxJson getStugradeList(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser=(LoginUser)request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        List<HashMap<String, String>> pxclasstableList = iPxevaluationtableService.getStugradeList(queryWrapper);
        if (pxclasstableList == null) {
            ajaxJson.setSuccess(false);
        } else {
            ajaxJson.setSuccess(true);
            ajaxJson.setObj(pxclasstableList);
        }
        return ajaxJson;
    }
}
