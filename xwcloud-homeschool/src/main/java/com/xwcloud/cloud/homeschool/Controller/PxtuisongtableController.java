package com.xwcloud.cloud.homeschool.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.ExportExcel;
import com.xwcloud.cloud.homeschool.Service.IPxevaluationtableService;
import com.xwcloud.cloud.homeschool.Service.IPxtuisongtableService;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.Vo.exporttuisongVO;
import com.xwcloud.cloud.model.Vo.wchatmessageVO;

import com.xwcloud.cloud.model.entity.Pxcampustable;
import com.xwcloud.cloud.model.entity.Pxstutable;
import com.xwcloud.cloud.model.entity.Pxtuisongtable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-06
 */
@Controller
@RequestMapping("/homeschool/pxtuisongtable")
@Api(tags = "微信群发")
public class PxtuisongtableController {

    @Autowired
    IPxtuisongtableService iPxtuisongtableService;
    @Autowired
    IPxevaluationtableService iPxevaluationtableService;

    @Autowired
    SendMessagehomeschool sendMessagehomeschool;

    @RequestMapping(value = "/GetStuWechatMessagePages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页查询学生推送信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "每页加载数据条数", required = true),
            @ApiImplicitParam(name = "current", value = "当前页码", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "stuNo", value = "学生自定义学号", required = false),
            @ApiImplicitParam(name = "stuName", value = "学生姓名", required = false),
            @ApiImplicitParam(name = "type", value = "推送类型", required = false),
    })
    public AjaxJson GetStuWechatMessagePages(HttpServletRequest request, @RequestParam(required = false, defaultValue = "10") long size,
                                             @RequestParam(required = false, defaultValue = "1") long current, long campusID, String stuNo, String stuName, long type) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<wchatmessageVO> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (campusID != 0) {
            queryWrapper.eq("b.campusID", campusID);
        }
        if (StringUtils.isNotBlank(stuNo)) {
            queryWrapper.like("b.zidingyiStuID", stuNo);
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("b.stuName", stuName);
        }
        if (type != 0) {
            queryWrapper.eq("d.id ", type);
        }
        ajaxJson.setObj(iPxtuisongtableService.GetStuWechatMessagePages(page, queryWrapper));
        return ajaxJson;
    }

    @RequestMapping(value = "/wechatGroupSending", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "学员微信群发")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuIDs", value = "学生ID数组,以英文逗号','分割", required = true),
            @ApiImplicitParam(name = "tuisongType", value = "推送类型ID", required = true),
            @ApiImplicitParam(name = "content", value = "推送内容", required = true)
    })
    public AjaxJson wechatGroupSending(HttpServletRequest request,
                                       String stuIDs,
                                       String tuisongType,
                                       String content
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        // 获取推送学生列表
        List<Pxstutable> stuList = iPxtuisongtableService.getStuList(stuIDs, loginUser.getQiyeID());
        // 获取推送类型
        // 进行消息推送
        // 写入推送历史表
        for (Pxstutable stu : stuList) {
            Pxtuisongtable pxtuisongtable = new Pxtuisongtable();
            pxtuisongtable.setAddTime(new Date());
            pxtuisongtable.setAppread(0);// 阅读状态 0未读,1已读
            pxtuisongtable.setNote(content);
            pxtuisongtable.setRole(1); // 1 学生 2 员工
            pxtuisongtable.setStuID(stu.getId());//
            pxtuisongtable.setTuisongTypeName(Long.valueOf(tuisongType));// 推送类型ID
            pxtuisongtable.setWxstate(0);// 0 未成功,1 成功
            pxtuisongtable.setAddStaffID(loginUser.getStaffID());
            pxtuisongtable.setQiyeID(loginUser.getQiyeID());
            iPxtuisongtableService.save(pxtuisongtable);
            sendMessagehomeschool.sendMessage(pxtuisongtable.getId());
        }
        ajaxJson.setSuccess(true);
        return ajaxJson;
    }

    @RequestMapping(value = "/GetAllClassInfoList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "加载选择所有班级列表")
    public AjaxJson GetAllClassInfoList(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        ajaxJson.setObj(iPxtuisongtableService.GetAllClassListInfo(queryWrapper));
        return ajaxJson;
    }

    @RequestMapping(value = "/classGroupPosting", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "班级群发", notes = "只获取了学生数据未写推送代码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classIds", value = "班级ID数组,以英文逗号','分割", required = true),
            @ApiImplicitParam(name = "tuisongType", value = "推送类型ID", required = true),
            @ApiImplicitParam(name = "content", value = "推送内容", required = true)
    })
    public AjaxJson classGroupPosting(HttpServletRequest request,
                                      String classIds,
                                      String tuisongType,
                                      String content) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        // 获取推送学生列表
        List<Pxstutable> stuList = iPxtuisongtableService.getStuByClass(classIds, loginUser.getQiyeID());
        // 获取推送类型
        // 进行消息推送
        // 写入推送历史表
        for (Pxstutable stu : stuList) {
            long tstype = Long.valueOf(tuisongType);
            Pxtuisongtable pxtuisongtable = new Pxtuisongtable();
            pxtuisongtable.setAddTime(new Date());
            pxtuisongtable.setAppread(0);// 阅读状态 0未读,1已读
            pxtuisongtable.setNote(content);
            pxtuisongtable.setRole(1); // 1 学生 2 员工
            pxtuisongtable.setStuID(stu.getId());//
            pxtuisongtable.setTuisongTypeName(tstype);// 推送类型ID
            pxtuisongtable.setWxstate(0);// 0 未成功,1 成功
            pxtuisongtable.setQiyeID(loginUser.getQiyeID());
            pxtuisongtable.setAddStaffID(loginUser.getStaffID());
            iPxtuisongtableService.save(pxtuisongtable);
            sendMessagehomeschool.sendMessage(pxtuisongtable.getId());
        }
        ajaxJson.setSuccess(true);
        return ajaxJson;
    }

    @RequestMapping(value = "/campusMassRelease", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "校区群发", notes = "只获取了学生数据未写推送代码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusIds", value = "校区ID数组,以英文逗号','分割", required = true),
            @ApiImplicitParam(name = "tuisongType", value = "推送类型ID", required = true),
            @ApiImplicitParam(name = "content", value = "推送内容", required = true)
    })
    public AjaxJson campusMassRelease(HttpServletRequest request, String campusIds, String tuisongType, String content) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        // 获取推送学生列表
        List<Pxstutable> stuList = iPxtuisongtableService.getStuByCampus(campusIds, loginUser.getQiyeID());
        // 获取推送类型
        // 进行消息推送
        // 写入推送历史表
        for (Pxstutable stu : stuList) {
            Pxtuisongtable pxtuisongtable = new Pxtuisongtable();
            pxtuisongtable.setAddTime(new Date());
            pxtuisongtable.setAppread(0);// 阅读状态 0未读,1已读
            pxtuisongtable.setNote(content);
            pxtuisongtable.setRole(1); // 1 学生 2 员工
            pxtuisongtable.setStuID(stu.getId());//
            pxtuisongtable.setTuisongTypeName(Long.parseLong(tuisongType));// 推送类型ID
            pxtuisongtable.setWxstate(0);// 0 未成功,1 成功
            pxtuisongtable.setQiyeID(loginUser.getQiyeID());
            pxtuisongtable.setAddStaffID(loginUser.getStaffID());
            iPxtuisongtableService.save(pxtuisongtable);
            sendMessagehomeschool.sendMessage(pxtuisongtable.getId());
        }
        ajaxJson.setSuccess(true);
        return ajaxJson;
    }

    /**
     * 导出老师评价学生
     */
    @RequestMapping(value = "/exportMassPostingHistory", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出群发历史", notes = "未实现查询数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "推送类型", required = false),
            @ApiImplicitParam(name = "startDate", value = "开始时间", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束时间", required = false)
    })
    public void exportMassPostingHistory(HttpServletResponse response, HttpServletRequest request,
                                         @RequestParam(required = false) long type, // 推送类型
                                         @RequestParam(required = false) String startDate, // 开始日期
                                         @RequestParam(required = false) String endDate // 结束日期
    ) {
        QueryWrapper queryWrapper = new QueryWrapper();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (type != 0) {
            queryWrapper.eq("a.tuisongTypeName", type);
        }
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("a.addtime", startDate);
        }
        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("a.addtime", endDate);
        }

        List<exporttuisongVO> pxtuisongtableVoList = iPxtuisongtableService.SearchTuisongMessageList(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "学号", "姓名", "班主任", "类型", "微信通知", "推送时间"},
                pxtuisongtableVoList,
                new String[]{"campusName", "zidingyiStuID", "stuName", "staffName", "tuisongType", "note", "addTime-DT"});

        try {
            ExportExcel.exportExcel(response, list, "sheet1", "微信群发信息.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getCampusList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取校区")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusName", value = "校区名称", required = true)
    })
    public AjaxJson getCampusList(@RequestParam(required = false) String campusName, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("isOpen", 1);
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

    @RequestMapping(value = "/GetAllTuisongType", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询推送类型")
    public AjaxJson GetAllTuisongType(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setObj(iPxtuisongtableService.getTuisongTypeList());
        return ajaxJson;
    }
}
