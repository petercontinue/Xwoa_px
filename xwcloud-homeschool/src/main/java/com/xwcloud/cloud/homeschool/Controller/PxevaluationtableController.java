package com.xwcloud.cloud.homeschool.Controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.DateUtil;
import com.xwcloud.cloud.common.ExportExcel;
import com.xwcloud.cloud.homeschool.Service.*;
import com.xwcloud.cloud.model.Form.AddteaPingjiaFrom;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.Vo.PxevaluationtableVo;
import com.xwcloud.cloud.model.Vo.addtearateVo;
import com.xwcloud.cloud.model.entity.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 课后评价
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-04
 */
@Controller
@RequestMapping("/homeschool/pxevaluationtable")
@Api(tags = "课后评价")
public class PxevaluationtableController {

    @Autowired
    IPxevaluationtableService iPxevaluationtableService;
    @Autowired
    IPxpowertableService iPxpowertableService;
    @Autowired
    IEvaluationpingfenService iEvaluationpingfenService;
    @Autowired
    ITeaevaluationvalueService iTeaevaluationvalueService;
    @Autowired
    IPxpaiketableService iPxpaiketableService;
    @Autowired
    IPxstutableService iPxstutableService;
    @Autowired
    IPxkeshiteachertableService iPxkeshiteachertableService;
    @Autowired
    IPxtuisongtableService iPxtuisongtableService;
    @Autowired
    SendMessagehomeschool sendMessagehomeschool;


    @RequestMapping(value = "/gethavepjrate", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取已有的评分")
    public AjaxJson gethavepjrate(String pjID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");

        ajaxJson.setObj(iTeaevaluationvalueService.gethavepjrate(new QueryWrapper<addtearateVo>()
                .eq("b.id", pjID)
                .eq("a.qiyeID", loginUser.getQiyeID())
        ));
        return ajaxJson;
    }


    @RequestMapping(value = "/getFeedbackPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取任课老师课后反馈")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = true),
            @ApiImplicitParam(name = "current", value = "页码", required = true),
            @ApiImplicitParam(name = "type", value = "评价类型 1教师评价学生。2学生评价老师", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "stuId", value = "学号", required = false),
            @ApiImplicitParam(name = "stuName", value = "姓名", required = false),
            @ApiImplicitParam(name = "teacherName", value = "教师名称", required = false)
    })
    public AjaxJson getFeedbackPage(
            @RequestParam(required = false, defaultValue = "10") long size,
            @RequestParam(required = false, defaultValue = "1") long current,
            int type,
            @RequestParam(required = false) String campusID,
            @RequestParam(required = false) String stuId,
            @RequestParam(required = false) String stuName,
            @RequestParam(required = false) String teacherName,
            HttpServletRequest request

    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<HashMap<String, String>> page = new Page(current, size);
        QueryWrapper<HashMap<String, String>> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
//        queryWrapper.eq("evalua.type", type);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("g.id", campusID);
        }
        if (StringUtils.isNotBlank(stuId)) {
            queryWrapper
                    .and(a -> a.isNotNull("c.zidingyiStuID").like("c.zidingyiStuID", stuId))
                    .or(b -> b.isNull("c.zidingyiStuID").eq("b.stuID", stuId));
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("c.stuName", stuName);
        }
        if (StringUtils.isNotBlank(teacherName)) {
            queryWrapper.like("a.teacherNames", teacherName);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 261);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }

        page = iPxevaluationtableService.getPage(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/addFeedback", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class})
    @ApiOperation(value = "添加评价")
    public AjaxJson addFeedback(HttpServletRequest request, @RequestBody AddteaPingjiaFrom from) {
        AjaxJson ajaxJson = new AjaxJson();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String imgurl = from.getImgurl();
        String mp3url = from.getMp3url();
        String videourl = from.getVideourl();
        String pfvalue = from.getPfvalue();
        String pkid = from.getPkid();
        String stuID = from.getStuID();
        String sturateshuoming = from.getSturateshuoming();
        String teacherID = from.getTeacherID();


//        String campusID, String classID, String sksj, int type, String stuID, String teacherID, String images, String pjmp3Url, String pjvideoUrl, String note
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        if (sturateshuoming.length() > 500) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("评语只能输入500字以内");
            return ajaxJson;
        }
        // 上课时间
        Pxpaiketable pk = iPxpaiketableService.getById(pkid);
        Date haveClassDate = pk.getHaveClassDate();
        Pxstutable stu = iPxstutableService.getById(stuID);


        // 查询老师课时表
        Pxkeshiteachertable teacherkeshi = iPxkeshiteachertableService.getOne(new QueryWrapper<Pxkeshiteachertable>()
                .eq("teacherID", teacherID)
                .eq("campusID", stu.getCampusID())
                .eq("classID", pk.getClassID())
                .eq("startLessonDateTime", pk.getStartLessonDateTime().toString())
                .eq("endLessonDateTime", pk.getEndLessonDateTime().toString())
                .eq(" DATE_FORMAT( haveClassDate, '%Y-%m-%d' )", sdf.format(pk.getHaveClassDate()))
                .eq("qiyeID", loginUser.getQiyeID())
        );
//        stu.getCampusID().toString(), pk.getClassID().toString(), teacherID,
//                pk.getHaveClassDate().toString(), pk.getStartLessonDateTime().toString(),
//                pk.getEndLessonDateTime().toString(), loginUser.getQiyeID());
        Pxevaluationtable one = iPxevaluationtableService.getOne(
                new QueryWrapper<Pxevaluationtable>()
                        .eq("classID", pk.getClassID())
                        .eq("haveClassDate", pk.getHaveClassDate())
                        .eq("startLessonDateTime", pk.getStartLessonDateTime().toString())
                        .eq("endLessonDateTime", pk.getEndLessonDateTime().toString())
                        .eq("stuid", stuID)
                        .eq("qiyeID", loginUser.getQiyeID())
        );
        Long pjID = null;
        if (one == null) {
            Pxevaluationtable evalua = new Pxevaluationtable();
            evalua.setNote(sturateshuoming);
            evalua.setKechengID(pk.getKechengID());
            evalua.setClassID(pk.getClassID());
            evalua.setHaveClassDate(pk.getHaveClassDate());
            evalua.setStartLessonDateTime(pk.getStartLessonDateTime());
            evalua.setEndLessonDateTime(pk.getEndLessonDateTime());
            evalua.setWeekN(pk.getWeekN());
            evalua.setKeshiTeachTabID(teacherkeshi.getId());
            evalua.setStuid(Long.valueOf(stuID));
            evalua.setTeacherid(loginUser.getStaffID());//jia
            evalua.setAddtime(new Date());
            evalua.setImages(imgurl);
            evalua.setPjmp3Url(mp3url);
            evalua.setPjvideoUrl(videourl);
            evalua.setType(1);
            evalua.setQiyeID(loginUser.getQiyeID());
            ajaxJson.setSuccess(iPxevaluationtableService.save(evalua));
            pjID = evalua.getId();

            //给家长发送课后点评
            Pxtuisongtable pxtuisongtable=new Pxtuisongtable()
                    .setWxstate(0)
                    .setAppread(0)
                    .setRole(1)
                    .setStuID(Long.valueOf(stuID))
                    .setAddStaffID(loginUser.getStaffID())
                    .setQiyeID(loginUser.getQiyeID())
                    .setAddTime(new Date())
                    .setTuisongTypeName(9l)
                    .setNote("你"+ DateUtil.formatDate2(pk.getHaveClassDate())+" "+DateUtil.formatDate7(pk.getStartLessonDateTime())
                    +" "+DateUtil.formatDate7(pk.getEndLessonDateTime())+"的课程有新的评价，请注意查看！");
            iPxtuisongtableService.save(pxtuisongtable);
            sendMessagehomeschool.sendMessage(pxtuisongtable.getId());
        } else {
            one
                    .setNote(sturateshuoming)
                    .setImages(imgurl)
                    .setPjmp3Url(mp3url)
                    .setPjvideoUrl(videourl);
            iPxevaluationtableService.updateById(one);
            pjID = one.getId();
        }

        List<addtearateVo> addtearateVos = JSON.parseArray(pfvalue, addtearateVo.class);
        for (addtearateVo item : addtearateVos) {
            Teaevaluationvalue pjrate = iTeaevaluationvalueService.getOne(new QueryWrapper<Teaevaluationvalue>()
                    .eq("pjid", pjID)
                    .eq("rateid", item.getId())
                    .eq("qiyeID", loginUser.getQiyeID())
            );
            if (pjrate != null) {
                pjrate.setPfvalue(item.getPvalue());
                iTeaevaluationvalueService.updateById(pjrate);
            } else {
                Teaevaluationvalue twe = new Teaevaluationvalue();
                twe
                        .setPfvalue(item.getPvalue())
                        .setRateid(item.getId())
                        .setPjid(pjID)
                        .setQiyeID(loginUser.getQiyeID());
                iTeaevaluationvalueService.save(twe);
            }
        }


        return ajaxJson;
    }

    @RequestMapping(value = "/editFeedback", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class})
    @ApiOperation(value = "修改评价")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "评价id", required = true),
            @ApiImplicitParam(name = "note", value = "评价文字", required = true)
    })
    public AjaxJson editFeedback(String id, String note, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper wrapper = new QueryWrapper();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        wrapper.eq("id", id);
        wrapper.eq("qiyeID", loginUser.getQiyeID());
        Pxevaluationtable pxevaluationtable = iPxevaluationtableService.getOne(wrapper);
        pxevaluationtable.setNote(note);
        ajaxJson.setSuccess(iPxevaluationtableService.updateById(pxevaluationtable));
        return ajaxJson;
    }

    @RequestMapping(value = "/setpingyu", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "学员评价老师")
    public AjaxJson setpingyu(HttpServletRequest request,
                              String id,
                              String stuID,
                              String teacherid,
                              String keshiTeachTabID,
                              String startLessonDateTime,
                              String endLessonDateTime,
                              String haveClassDate,
                              String kechengID,
                              String classID,
                              String weekN,
                              String note
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df = new SimpleDateFormat("hh:mm");
        if (!StringUtils.isNotBlank(id)) {
            //添加
            Pxevaluationtable pj = new Pxevaluationtable();
            pj.setNote(note);
            pj.setKechengID(Long.valueOf(kechengID));
            pj.setClassID(Long.valueOf(classID));
            Date HD = null;
            try {
                HD = sdf.parse(haveClassDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date st = null;
            try {
                st = df.parse(startLessonDateTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date et = null;
            try {
                et = df.parse(endLessonDateTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            pj.setHaveClassDate(HD);
            pj.setStartLessonDateTime(st);
            pj.setEndLessonDateTime(et);
            pj.setWeekN(weekN);
            pj.setKeshiTeachTabID(Long.valueOf(keshiTeachTabID));
            pj.setStuid(Long.valueOf(stuID));
            pj.setTeacherid(Long.valueOf(teacherid));
            pj.setAddtime(new Date());
            pj.setType(2);
            pj.setQiyeID(loginUser.getQiyeID());
            iPxevaluationtableService.save(pj);
        } else {
            //修改
            Pxevaluationtable pjone = iPxevaluationtableService.getById(id);
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.HOUR, -2);
            if (pjone.getAddtime().getTime() < cal.getTime().getTime()) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("超过2小时的评价，不能修改");
                return ajaxJson;
            }
            pjone.setNote(note);
            iPxevaluationtableService.updateById(pjone);
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/getFeedback", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取评价")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "评价ID", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson getFeedback(String id, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("evalua.id", id);
        queryWrapper.eq("evalua.qiyeID", loginUser.getQiyeID());
        ajaxJson.setObj(iPxevaluationtableService.getJoinList(queryWrapper).get(0));
        return ajaxJson;
    }

    @RequestMapping(value = "/delFeedback", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class})
    @ApiOperation(value = "删除评价")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID", value = "评价ID", required = true)
    })
    public AjaxJson delFeedback(String ID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        List<Teaevaluationvalue> ratelist = iTeaevaluationvalueService.list(new QueryWrapper<Teaevaluationvalue>()
                .eq("pjid", ID)
                .eq("qiyeID", loginUser.getQiyeID())
        );
        if (ratelist.size() > 0) {
            iTeaevaluationvalueService.remove(new QueryWrapper<Teaevaluationvalue>()
                    .eq("pjid", ID)
                    .eq("qiyeID", loginUser.getQiyeID()));
        }
        ajaxJson.setSuccess(iPxevaluationtableService.removeById(ID));
        return ajaxJson;
    }


    @RequestMapping(value = "/exportFeedback", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出课后反馈")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "评价类型 1教师评价学生。2学生评价老师", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "startDate", value = "开始时间", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束时间", required = false)
    })
    public void exportFeedback(HttpServletResponse response, HttpServletRequest request, int type,
                               @RequestParam(required = false) String campusID, // 校区ID
                               @RequestParam(required = false) String startDate, // 开始日期
                               @RequestParam(required = false) String endDate // 结束日期
    ) {
        QueryWrapper queryWrapper = new QueryWrapper();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        queryWrapper.eq("evalua.qiyeID", loginUser.getQiyeID());
        queryWrapper.like("evalua.type", type);
        switch (type) {
            case 1: // 教师评价学生
                if (StringUtils.isNotBlank(campusID)) {
                    queryWrapper.like("stu.campusID", campusID);
                }
                break;
            case 2: // 学生评价老师
                if (StringUtils.isNotBlank(campusID)) {
                    queryWrapper.like("teacher.campusID", campusID);
                }
                break;
        }
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("stu.campusID", campusID);
        }

        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("evalua.addtime", startDate);
        }

        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("evalua.addtime", endDate);
        }

        List<PxevaluationtableVo> pxevaluationtableVoList = iPxevaluationtableService.getJoinList(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "学号", "姓名", "班级", "课程", "上课时间", "评语",
                        "教师姓名", "评价时间"},
                pxevaluationtableVoList,
                new String[]{"campusName", "zidingyiStuID", "stuName", "className", "kechengName", "haveClassDate-D", "note",
                        "staffName", "addtime-DT"});

        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "任课老师课后反馈导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getStuList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取学生列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true),
            @ApiImplicitParam(name = "classID", value = "班级ID", required = true),
            @ApiImplicitParam(name = "shangkesjd", value = "上课时间段", required = true)
    })
    public AjaxJson getStuList(String campusID, String classID, String shangkesjd, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        queryWrapper.eq("keshi.campusID", campusID);
        queryWrapper.eq("keshi.classID", classID);
        queryWrapper.eq("keshi.qiyeID", loginUser.getQiyeID());

        String[] shangkesjds = shangkesjd.split(" ");
        String[] shangkeTime = shangkesjds[1].split("~");

        queryWrapper.eq("keshi.haveClassDate", shangkesjds[0]);
        queryWrapper.eq("keshi.startLessonDateTime", shangkeTime[0]);
        queryWrapper.eq("keshi.endLessonDateTime", shangkeTime[1]);
        List<Pxstutable> pxstutableList = iPxevaluationtableService.getStuList(queryWrapper);
        if (pxstutableList == null) {
            ajaxJson.setSuccess(false);
        } else {
            ajaxJson.setSuccess(true);
            ajaxJson.setObj(pxstutableList);
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/getPeriodList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取时间段列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classID", value = "班级ID", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true)
    })
    public AjaxJson getPeriodList(String campusID, String classID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        queryWrapper.eq("classID", classID);
        queryWrapper.eq("campusID", campusID);
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        List<HashMap<String, String>> pxstutableList = iPxevaluationtableService.getPeriodList(queryWrapper);
        if (pxstutableList == null) {
            ajaxJson.setSuccess(false);
        } else {
            ajaxJson.setSuccess(true);
            ajaxJson.setObj(pxstutableList);
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/getStaffList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取老师列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classID", value = "班级ID", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true),
            @ApiImplicitParam(name = "shangkesjd", value = "上课时间段", required = true)
    })
    public AjaxJson getStaffList(String campusID, String classID, String shangkesjd, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        queryWrapper.eq("laoshikeshi.campusID", campusID);
        queryWrapper.eq("laoshikeshi.classID", classID);
        queryWrapper.eq("laoshikeshi.qiyeID", loginUser.getQiyeID());

        String[] shangkesjds = shangkesjd.split(" ");
        String[] shangkeTime = shangkesjds[1].split("~");
        queryWrapper.eq("laoshikeshi.startLessonDateTime", shangkeTime[0]);
        queryWrapper.eq("laoshikeshi.endLessonDateTime", shangkeTime[1]);
        queryWrapper.apply("DATE_FORMAT(laoshikeshi.haveClassDate,'%y-%m-%d') = DATE_FORMAT({0},'%y-%m-%d')", shangkesjds[0]);

        List<Pxstafftable> pxstutableList = iPxevaluationtableService.getStaffList(queryWrapper);
        if (pxstutableList == null) {
            ajaxJson.setSuccess(false);
        } else {
            ajaxJson.setSuccess(true);
            ajaxJson.setObj(pxstutableList);
        }
        return ajaxJson;
    }
}
