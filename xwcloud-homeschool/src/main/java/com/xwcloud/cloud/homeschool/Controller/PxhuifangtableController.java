package com.xwcloud.cloud.homeschool.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.DateUtil;
import com.xwcloud.cloud.common.ExportExcel;
import com.xwcloud.cloud.homeschool.Service.IPxstuhuifangtableService;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.Vo.PxstuhuifangVo;
import com.xwcloud.cloud.model.Vo.oldstuhuifangVO;
import com.xwcloud.cloud.model.entity.Pxstuhuifangtable;
import com.xwcloud.cloud.model.entity.Pxstutable;
import com.xwcloud.cloud.overall.LogUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/homeschool/oldstupxhuifang")
@Api(tags = "老学员回访")
public class PxhuifangtableController {

    @Autowired
    IPxstuhuifangtableService iPxstuhuifangtableService;


    //region 老学员回访主表

    @RequestMapping(value = "/getStuPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取学生分页信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "每页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "classID", value = "班级ID", required = false),
            @ApiImplicitParam(name = "zidingyiStuID", value = "学号", required = false),
            @ApiImplicitParam(name = "stuName", value = "学生名称", required = false),
            @ApiImplicitParam(name = "isHaveAReturnVisit", value = "是否有回访", required = false)
    })
    public AjaxJson getStuPage(HttpServletRequest request,
                               @RequestParam(required = false, defaultValue = "10") long size,
                               @RequestParam(required = false, defaultValue = "1") long current,
                               @RequestParam(required = false) String campusID,
                               @RequestParam(required = false) String classID,
                               @RequestParam(required = false) String stuID,
                               @RequestParam(required = false) String stuName,
                               @RequestParam(required = false) Boolean isHaveAReturnVisit
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<PxstuhuifangVo> page = new Page<PxstuhuifangVo>(current, size);
        QueryWrapper<PxstuhuifangVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(classID)) {
            queryWrapper.eq("stuclass.classID", classID);
            page = iPxstuhuifangtableService.getStuPageByClass(page, queryWrapper);
        }
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("stu.campusID", campusID);
        }
        if (StringUtils.isNotBlank(stuID)) {
            queryWrapper.like("stu.zidingyiStuID", stuID).or(a -> a.eq("stu.id", stuID));
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.eq("stu.stuName", stuName);
        }
        if (isHaveAReturnVisit != null) {
            if (isHaveAReturnVisit==true) { // 有回访
                queryWrapper.gt("(select count(*) from pxstuhuifangtable where stuID=stu.id and hfType=2)",0);
            } else {// 无回访
                queryWrapper.eq("(select count(*) from pxstuhuifangtable where stuID=stu.id and hfType=2)",0);

            }
        }
        if (StringUtils.isBlank(classID)) { // 如果没有通过班级ID查询
            page = iPxstuhuifangtableService.getStuPage(page, queryWrapper);
        }
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/exportStuHuifang", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出老学员回访")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isHaveAReturnVisit", value = "是否有回访 boolen类型", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "startLastHuifangTime", value = "最后一次回访时间开始", required = false),
            @ApiImplicitParam(name = "endLastHuifangTime", value = "最后一次回访时间结束", required = false),
            @ApiImplicitParam(name = "startNextHuifangTime", value = "下次回访时间开始", required = false),
            @ApiImplicitParam(name = "endNextHuifangTime", value = "下次回访时间结束", required = false),
    })
    public void exportFeedback(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(required = false) Boolean isHaveAReturnVisit,
                               @RequestParam(required = false) String campusID, // 校区ID
                               @RequestParam(required = false) String startLastHuifangTime, // 开始日期
                               @RequestParam(required = false) String endLastHuifangTime, // 结束日期
                               @RequestParam(required = false) String startNextHuifangTime, // 结束日期
                               @RequestParam(required = false) String endNextHuifangTime // 结束日期
    ) {
        QueryWrapper queryWrapper = new QueryWrapper();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        queryWrapper.eq("stu.qiyeID", loginUser.getQiyeID());
        if (isHaveAReturnVisit != null) {
            if (isHaveAReturnVisit) {// 有回访
                queryWrapper.isNotNull("stu.lastHuifangTime");
            } else { // 无回访
                queryWrapper.isNull("stu.lastHuifangTime");
            }
        }
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("stu.campusID", campusID);
        }
        if (StringUtils.isNotBlank(startLastHuifangTime)) {
            queryWrapper.ge("stu.lastHuifangTime", startLastHuifangTime);
        }
        if (StringUtils.isNotBlank(endLastHuifangTime)) {
            queryWrapper.le("stu.lastHuifangTime", endLastHuifangTime);
        }

        if (StringUtils.isNotBlank(startNextHuifangTime)) {
            queryWrapper.ge("stu.nextHuifangTime", startNextHuifangTime);
        }
        if (StringUtils.isNotBlank(endNextHuifangTime)) {
            queryWrapper.le("stu.nextHuifangTime", endNextHuifangTime);
        }
        // 导出回访记录
        List<PxstuhuifangVo> stuVoList = iPxstuhuifangtableService.getStuJoinList(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "学号", "姓名", "电话", "性别", "班主任", "回访详情",
                        "最后一次回访时间", "下次回访时间"},
                stuVoList,
                new String[]{"campusName", "zidingyiStuID", "stuName", "stuTel", "stuSex", "banzhurenName", "totalDetails",
                        "lastHuifangTime-DT", "nextHuifangTime-DT"});

        ExportExcel.ExcelSource source = new ExportExcel.ExcelSource();
        source.setSheetName("回访记录");
        source.setTableData(list);
        List<ExportExcel.ExcelSource> sourceList = new ArrayList<>();
        sourceList.add(source);

        // 导出回访详细
        List<Long> stringList = new ArrayList<>();
        for (PxstuhuifangVo stu : stuVoList) {
            stringList.add(Long.valueOf(stu.getStuID()));
        }
        QueryWrapper qw = new QueryWrapper();
        qw.eq("huifang.qiyeID", loginUser.getQiyeID());
        qw.in("huifang.stuID", stringList);
        List<PxstuhuifangVo> huifangDetailed = iPxstuhuifangtableService.getJoinList(qw);
        List<List<Object>> DetailedList = ExportExcel.formatDataToList(new String[]{"校区", "学号", "姓名", "回访内容", "回访时间",
                        "添加时间", "回访人"},
                huifangDetailed,
                new String[]{"campusName", "zidingyiStuID", "stuName", "text", "huifangTime", "addTime-DT", "huifangTeacherName"});
        ExportExcel.ExcelSource sourcedetaile = new ExportExcel.ExcelSource();
        sourcedetaile.setSheetName("回访详细");
        sourcedetaile.setTableData(DetailedList);
        sourceList.add(sourcedetaile);
        try {
            // 需要将详细表一起导出
            ExportExcel.exportMultipleSheetExcel(response, sourceList, "回访.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion

    //region 老学员回访详细

    @RequestMapping(value = "/getStu", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取学生信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学生ID", required = true),
    })
    public AjaxJson getStu(HttpServletRequest request,
                           long stuID
    ) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setObj(iPxstuhuifangtableService.getStu(stuID, loginUser.getQiyeID()));

        return ajaxJson;
    }

    @RequestMapping(value = "/getStuKechengList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取学生课程信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学生ID", required = true)
    })
    public AjaxJson getStuKechengList(HttpServletRequest request,
                                      long stuID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setObj(iPxstuhuifangtableService.getStuKechengList(stuID, loginUser.getQiyeID()));
        return ajaxJson;
    }

    @RequestMapping(value = "/getHuifangPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取学生回访信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "stuID", value = "学生ID", required = true)
    })
    public AjaxJson getHuifangPage(HttpServletRequest request,
                                   @RequestParam(required = false, defaultValue = "10") long size,
                                   @RequestParam(required = false, defaultValue = "1") long current,
                                   String stuID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<oldstuhuifangVO> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("huifang.stuID", stuID);
        queryWrapper.eq("huifang.hfType", 2);
        queryWrapper.eq("huifang.qiyeID", loginUser.getQiyeID());
        queryWrapper.orderByDesc("huifang.huifangTime");
        page = iPxstuhuifangtableService.getPage(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/addHuifang", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class})
    @ApiOperation(value = "新增老学员回访")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学员ID或意向学员ID", required = true),
            @ApiImplicitParam(name = "text", value = "回访内容", required = true),
            @ApiImplicitParam(name = "huifangTime", value = "回访时间", required = true),
            @ApiImplicitParam(name = "nexthuifangTime", value = "下次回访时间", required = true),
    })
    public AjaxJson addHuifang(HttpServletRequest request,
                               long stuID,
                               String text,
                               String huifangTime,
                               String nexthuifangTime
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Pxstuhuifangtable pxstuhuifangtable = new Pxstuhuifangtable();
        pxstuhuifangtable.setStuID(stuID);
        pxstuhuifangtable.setText(text);
        pxstuhuifangtable.setHfstaffID(loginUser.getStaffID());
        pxstuhuifangtable.setHuifangTime(DateUtil.toDate(huifangTime, "yyyy-MM-dd"));
        pxstuhuifangtable.setHfType(2);
        pxstuhuifangtable.setAddstaffID(loginUser.getStaffID());
        pxstuhuifangtable.setAddTime(new Date());
        pxstuhuifangtable.setQiyeID(loginUser.getQiyeID());
        iPxstuhuifangtableService.save(pxstuhuifangtable);

        Pxstutable pxstutable = (Pxstutable) iPxstuhuifangtableService.getStu(stuID, loginUser.getQiyeID());
        Date nextdate = DateUtil.toDate(nexthuifangTime, "yyyy-MM-dd");
        Date huifangdate = DateUtil.toDate(huifangTime, "yyyy-MM-dd");
        pxstutable.setNextHuifangTime(nextdate);
        pxstutable.setLastHuifangTime(huifangdate);
        iPxstuhuifangtableService.editStu(pxstutable);
        ajaxJson.setSuccess(true);
        return ajaxJson;
    }

    @RequestMapping(value = "/editHuifang", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class})
    @ApiOperation(value = "编辑老学员回访")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true),
            @ApiImplicitParam(name = "stuID", value = "学员ID或意向学员ID", required = true),
            @ApiImplicitParam(name = "text", value = "回访内容", required = true),
            @ApiImplicitParam(name = "huifangTime", value = "回访时间", required = true),
            @ApiImplicitParam(name = "nexthuifangTime", value = "下次回访时间", required = true),
    })
    public AjaxJson editHuifang(HttpServletRequest request,
                                String id,
                                String text,
                                String huifangTime,
                                String nexthuifangTime
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Pxstuhuifangtable pxstuhuifangtable = iPxstuhuifangtableService.getById(id);
        pxstuhuifangtable.setText(text);
        Date huifangdate = DateUtil.toDate(huifangTime, "yyyy-MM-dd");
        pxstuhuifangtable.setHuifangTime(huifangdate);
        iPxstuhuifangtableService.updateById(pxstuhuifangtable);
        Pxstutable pxstutable = iPxstuhuifangtableService.getStu(pxstuhuifangtable.getStuID(), loginUser.getQiyeID());
        pxstutable.setNextHuifangTime(DateUtil.toDate(nexthuifangTime, "yyyy-MM-dd"));
        pxstutable.setLastHuifangTime(DateUtil.toDate(huifangTime, "yyyy-MM-dd"));
        iPxstuhuifangtableService.editStu(pxstutable);
        return ajaxJson;
    }

    @RequestMapping(value = "/getHuifang", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取老学员回访")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true)
    })
    public AjaxJson getHuifang(HttpServletRequest request,
                               String id
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        ajaxJson.setObj(iPxstuhuifangtableService.getById(queryWrapper));
        return ajaxJson;
    }

    @RequestMapping(value = "/delHuifang", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除老学员回访")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "回访IDs", required = true)
    })
    public AjaxJson delHuifang(HttpServletRequest request,
                               String ids
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] strings = ids.split(",");
        QueryWrapper queryWrapper = new QueryWrapper();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper.in("id", strings);
        List<Pxstuhuifangtable> list = iPxstuhuifangtableService.list(queryWrapper);
        if (list == null || list.size() <= 0) {
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }

        ajaxJson.setSuccess(iPxstuhuifangtableService.removeByIds(Arrays.asList(strings)));
        return ajaxJson;
    }


    //endregion
}
