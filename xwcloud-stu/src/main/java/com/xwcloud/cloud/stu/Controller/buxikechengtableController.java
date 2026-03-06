package com.xwcloud.cloud.stu.Controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.ExportExcel;
import com.xwcloud.cloud.common.importExcel;
import com.xwcloud.cloud.model.Form.*;
import com.xwcloud.cloud.model.Form.stu.*;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.*;
import com.xwcloud.cloud.stu.Service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-06
 */
@Controller
@RequestMapping("/stu/buxikecheng")
@Api(tags = "学员课程管理")
public class buxikechengtableController {

    //region 注入服务
    @Autowired
    IPxbuxikechengtableService iPxbuxikechengtableService;

    @Autowired
    IPxcertificatetableService iPxcertificatetableService;

    @Autowired
    IPxkechengtableService iPxkechengtableService;

    @Autowired
    IPxkaojitableService iPxkaojitableService;

    @Autowired
    IPxkaojisqtableService iPxkaojisqtableService;

    @Autowired
    IPxscoretableService iPxscoretableService;

    @Autowired
    IPxsubjecttableService iPxsubjecttableService;

    @Autowired
    IPxkeshizengsongtableService iPxkeshizengsongtableService;

    @Autowired
    IPxstutableService iPxstutableService;

    @Autowired
    IPxbxkcchangetableService iPxbxkcchangetableService;

    @Autowired
    IPxbuxistyletableService iPxbuxistyletableService;

    @Autowired
    IPxclasstableService iPxclasstableService;

    @Autowired
    IPxstuclasstableService iPxstuclasstableService;

    @Autowired
    IPxkeshizhuansongtableService iPxkeshizhuansongtableService;

    @Autowired
    IPxqiandaninfotableService iPxqiandaninfotableService;

    @Autowired
    IPxqiandansubjecttableService iPxqiandansubjecttableService;

    @Autowired
    IPxdaijinquantableService iPxdaijinquantableService;

    @Autowired
    IPxkeshistutableService iPxkeshistutableService;

    @Autowired
    IPxpaiketableService iPxpaiketableService;

    @Autowired
    IPxxuanketableService iPxxuanketableService;

    @Autowired
    IPxstukxqtableService iPxstukxqtableService;

    @Autowired
    IPxpaiketeachertableService iPxpaiketeachertableService;

    @Autowired
    IPxcampustableService iPxcampustableService;

    @Autowired
    IPxtesttypetableService iPxtesttypetableService;

    @Autowired
    IPxfazhengtableService iPxfazhengtableService;

    @Autowired
    IPxpowertableService iPxpowertableService;

    @Autowired
    IPxclasstimestyletableService iPxclasstimestyletableService;

    //endregion

    //region 学员课程管理


    @ApiOperation(value = "获取补习科目列表")
    @ResponseBody
    @GetMapping("getAllpxsubjectList")
    public AjaxJson getAllpxsubjectList(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setObj(iPxsubjecttableService.getallkemuName(loginUser.getQiyeID()));
        return ajaxJson;
    }


    @ApiOperation(value = "获取补习方式列表")
    @ResponseBody
    @GetMapping("getAllbuxiStyleList")
    public AjaxJson getAllbuxiStyleList(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setObj(iPxbuxistyletableService.getAllbuxiStyleList(loginUser.getQiyeID()));
        return ajaxJson;
    }

    @ApiOperation(value = "获取课程时长列表")
    @ResponseBody
    @GetMapping("getAllClassTimeList")
    public AjaxJson getAllClassTimeList(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setObj(iPxclasstimestyletableService.getAllClassTimeList(loginUser.getQiyeID()));
        return ajaxJson;
    }

    /**
     * 获取所有学生（带校区，年级）
     *
     * @param menuID
     * @param request
     * @return
     */
    @ApiOperation(value = "获取所有学生（带校区，年级）")
    @ResponseBody
    @RequestMapping(value = "GetAllSelectedStuNames", method = RequestMethod.GET)
    public AjaxJson GetAllSelectedStuNames(int menuID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", qiyeID);

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", menuID);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }
        ajaxJson.setObj(iPxstutableService.GetAllSelectedStuNames(queryWrapper));
        return ajaxJson;
    }

    /**
     * 以校区获取科目(分校区)
     *
     * @param campusID
     * @param request
     * @return
     */
    @ApiOperation(value = "按校区获取科目(分校区)")
    @ResponseBody
    @RequestMapping(value = "GetcampusIDkemu", method = RequestMethod.GET)
    public AjaxJson GetcampusIDkemu(String campusID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        ajaxJson.setObj(iPxsubjecttableService.GetcampusIDkemu(Long.valueOf(campusID), qiyeID));
        return ajaxJson;
    }


    /**
     * @Description: getbuxiCoursePage方法作用:分页获取学员课程
     * @param:[current, size, campusName, stuID, stuName, bxsubject, stuStatus, buxiStatus, buxiCourse, buxiPayStyle, startTime, isShow, endTime]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:05
     */
    @ApiOperation(value = "分页获取学员课程")
    @RequestMapping(value = "/getbuxiCoursePage", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区", required = false),
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = false),
            @ApiImplicitParam(name = "stuName", value = "学员名称", required = false),
            @ApiImplicitParam(name = "subjectID", value = "补习科目ID", required = false),
            @ApiImplicitParam(name = "stuStatus", value = "学员状态", required = false),
            @ApiImplicitParam(name = "buxiStatus", value = "补习状态", required = false),
            @ApiImplicitParam(name = "buxiCourse", value = "补习课程", required = false),
            @ApiImplicitParam(name = "buxijifeiStyle", value = "计费方式", required = false),
            @ApiImplicitParam(name = "startDateTime", value = "开始日期的开始时间", required = false),
            @ApiImplicitParam(name = "startEndTime", value = "开始日期的结束时间", required = false),
            @ApiImplicitParam(name = "endstartTime", value = "结束日期的开始时间", required = false),
            @ApiImplicitParam(name = "endTime", value = "结束日期的结束时间", required = false),
            @ApiImplicitParam(name = "isShow", value = "启用状态：1启用 0不启用 （需要前端传）传-1时显示全部", example = "-1", required = false),
            @ApiImplicitParam(name = "buxistyleid", value = "培训方式ID", example = "1", required = false),
            @ApiImplicitParam(name = "classTimeID", value = "课程时长ID", example = "1", required = false),
            @ApiImplicitParam(name = "ksType", value = "按大小还是小于某个课时值来查询", example = "-1", required = false),
            @ApiImplicitParam(name = "maxkeshi", value = "按剩余课时区间查询的查询条件，最大剩余课时", example = "100", required = false),
            @ApiImplicitParam(name = "minkeshi", value = "按剩余课时区间查询的查询条件，最小剩余课时", example = "0", required = false),
            @ApiImplicitParam(name = "keshi", value = "查询的课时值", example = "10", required = false)
    })
    public AjaxJson getbuxiCoursePage(
            Long current,
            Long size,
            String campusID,
            String stuID,
            String stuName,
            String subjectID,
            String stuStatus,
            String buxiStatus,
            String buxiCourse,
            String buxijifeiStyle,
            String startDateTime,
            String startEndTime,
            String endstartTime,
            String endTime,
            int isShow,
            String buxistyleid,
            String classTimeID,
            String ksType,
            String maxkeshi,
            String minkeshi,
            String keshi,
            HttpServletRequest request
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<buxiKeChengVo> page = new Page(current, size);
        QueryWrapper<buxiKeChengVo> queryWrapper = new QueryWrapper();
        queryWrapper
                .eq("1", 1)
                .eq("kc.qiyeID", qiyeID)

                .ne("pxstutable.buxiStateID", 1)
                .ne("pxstutable.buxiStateID", 4)
                .ne("pxstutable.buxiStateID", 5)
                .ne("pxstutable.buxiStateID", 7);

        if (isShow != -1) {
            queryWrapper.eq("kc.isShow", isShow);
        }

        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("pxstutable.campusID", campusID);
        }

        if (StringUtils.isNotBlank(keshi)) {
            if (ksType.equals("1")) {  //大于
                queryWrapper.ge("kc.remainkeshi", keshi);
            } else {
                queryWrapper.le("kc.remainkeshi", keshi);
            }
        }

        if (StringUtils.isNotBlank(maxkeshi) && StringUtils.isNotBlank(minkeshi)) {
            queryWrapper.between("kc.remainkeshi", minkeshi, maxkeshi);
        }

        if (StringUtils.isNotBlank(stuID)) {
            queryWrapper
                    .and(a -> a.isNotNull("pxstutable.zidingyiStuID").like("pxstutable.zidingyiStuID", stuID))
                    .or(b -> b.isNull("pxstutable.zidingyiStuID").eq("pxstutable.id", stuID));
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("pxstutable.stuName", stuName);
        }
        if (StringUtils.isNotBlank(subjectID)) {
            queryWrapper.eq("pxkechengtable.subjectID", subjectID);
        }
        if (StringUtils.isNotBlank(classTimeID)) {
            queryWrapper.eq("pxclasstimestyletable.id", classTimeID);
        }
        if (StringUtils.isNotBlank(stuStatus)) {
            queryWrapper.eq("pxstutable.activity", stuStatus);
        }

        if (StringUtils.isNotBlank(buxistyleid)) {
            queryWrapper.eq("pxbuxistyletable.id", buxistyleid);
        }
        if (StringUtils.isNotBlank(buxiStatus)) {
            queryWrapper.eq("pxkechengtable.buxiStyleID", buxiStatus);
        }
        if (StringUtils.isNotBlank(buxiCourse)) {
            queryWrapper.like("pxkechengtable.kechengName", buxiCourse);
        }
        if (StringUtils.isNotBlank(buxijifeiStyle)) {
            queryWrapper.eq("kc.jifeiStyleID", buxijifeiStyle);
        }
        if (StringUtils.isNotBlank(startDateTime) && StringUtils.isNotBlank(startEndTime)) {
            queryWrapper.ge("kc.startDate", startDateTime).le("kc.startDate", startEndTime);
        }
        if (StringUtils.isNotBlank(endstartTime) && StringUtils.isNotBlank(endTime)) {
            queryWrapper.ge("kc.endDate", endTime).le("kc.endDate", endTime);
        }
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 221);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("pxstutable.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("pxstutable.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("pxstutable.campusID", lookPower);
        }
        ajaxJson.setObj(iPxbuxikechengtableService.getbuxiCourse(page, queryWrapper));
        return ajaxJson;
    }


    //region 学员课程的按钮


    //region 共享课时

    @ResponseBody
    @GetMapping("getstuOtherbuxi")
    @ApiOperation("获取学员的其他同课程时长补习课程")
    public AjaxJson getstuOtherbuxi(String stuID, String buxiID, String classTime, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<HashMap<String, Object>> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("a.stuID", stuID)
                .eq("b.classTimeStyleID", classTime) //课程时长一样才可以
                .eq("a.qiyeID", loginUser.getQiyeID());

        if (StringUtils.isNotBlank(buxiID)) {
            queryWrapper.ne("a.id", buxiID);
        }
        List<HashMap<String, Object>> list = iPxbuxikechengtableService.getstuOtherbuxi(queryWrapper);
        ajaxJson.setObj(list);
        return ajaxJson;
    }


    @ResponseBody
    @GetMapping("getshareInfo")
    @ApiOperation("获取已设置的共享课程")
    public AjaxJson getshareInfo(String buxiIDs, Long current, Long size, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        String[] ids = buxiIDs.split(",");

        Page<HashMap<String, Object>> page = new Page(current, size);
        QueryWrapper<HashMap<String, Object>> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .in("a.id", ids)
                .eq("a.qiyeID", loginUser.getQiyeID());
        Page<HashMap<String, Object>> Infopage = iPxbuxikechengtableService.getshareInfo(page, queryWrapper);
        ajaxJson.setObj(Infopage);
        return ajaxJson;
    }


    @ResponseBody
    @PostMapping("savesharekeshi")
    @ApiOperation("保存共享课时")
    public AjaxJson savesharekeshi(@RequestBody savesharekeshiForm form) {
        AjaxJson ajaxJson = new AjaxJson();
        Pxbuxikechengtable bx = iPxbuxikechengtableService.getById(form.getBuxiID());
        bx.setShareBuxiID(form.getSharebuxiID());
        iPxbuxikechengtableService.updateById(bx);
        return ajaxJson;
    }
    //endregion

    /**
     * 分页获取补习课程跨校区设置详情
     *
     * @param current
     * @param size
     * @param buxiID
     * @param request
     * @return
     */
    @ApiOperation(value = "分页获取补习课程跨校区设置详情")
    @RequestMapping(value = "getkxqinfo", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "buxiID", value = "补习ID", required = false),
    })
    public AjaxJson getkxqinfo(Long current,
                               Long size,
                               String buxiID,
                               HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<kxqKcInfoVo> page = new Page(current, size);
        ajaxJson.setObj(iPxbuxikechengtableService.getkxqinfo(page, Long.valueOf(buxiID), qiyeID));
        return ajaxJson;
    }

    /**
     * @Description: exportFeedback方法作用:导出学员课程
     * @param:[response, campusID, GradeID, startDate, endDate]
     * @return:void
     * @auter:yyl
     * @data:2020/11/30 16:00
     */
    @RequestMapping(value = "ExportbuxiCourse", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出学员补习课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "GradeID", value = "年级ID", required = false),
            @ApiImplicitParam(name = "startDate", value = "开始时间", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束时间", required = false),
    })
    public void exportFeedback(HttpServletResponse response, HttpServletRequest request,
                               @RequestParam(required = false) String campusID, // 校区ID
                               @RequestParam(required = false) String GradeID, // 年级ID
                               @RequestParam(required = false) String startDate, // 开始日期
                               @RequestParam(required = false) String endDate // 结束日期
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper<buxiKeChengVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("1", 1).eq("kc.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("pxstutable.campusID", campusID);
        }
        if (StringUtils.isNotBlank(GradeID)) {
            queryWrapper.eq("pxstutable.stuGradeID", GradeID);
        }
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("kc.startDate", startDate);
        }
        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("kc.endDate", endDate);
        }
        List<buxiKeChengVo> buxiKeChengVos = iPxbuxikechengtableService.ExportbuxiCourse(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "姓名", "联系电话", "年级", "培训科目", "培训课程", "培训方式", "开始时间",
                        "结束时间", "原单价", "现单价", "购买课时", "剩余课时", "学员状态"},
                buxiKeChengVos,
                new String[]{"campusName", "stuName", "parentTel", "stuGradeName", "bxsubject", "buxiCourse", "buxiStatus", "startTime-D",
                        "endTime-D", "OldCoursePrice", "coursePrice", "Courses", "remainkeshi", "stuStatus"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "导出学员补习课程.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @Description: delBuXi方法作用:删除补习课程
     * @param:[bxIDs]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:01
     */
    @ApiOperation(value = "删除补习课程")
    @RequestMapping(value = "delbuxi", method = RequestMethod.DELETE)
    @ResponseBody()
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delBuXi(@RequestBody delbuxiKCFrom from, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();

        String xiugaiInfo = "";

        String buxiids = from.getBuxiids();
        List<stuIDVo> buxiIDS = JSON.parseArray(buxiids, stuIDVo.class);
        for (stuIDVo item : buxiIDS) {
            Pxbuxikechengtable del = iPxbuxikechengtableService.getById(item.getId());
            if (del.getJifeiStyleID() == 3) {
                if (del.getType() == 2) {
                    List<Pxkeshistutable> keshi = iPxkeshistutableService.getBybxID(del.getId(), Long.valueOf(qiyeID));
                    if (keshi.size() > 0) {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        ajaxJson.setMsg("该课程存在课耗，请先删除课耗信息");
                        ajaxJson.setCode("N");
                        return ajaxJson;
                    }
                    iPxbuxikechengtableService.removeById(item.getId());
                } else {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    ajaxJson.setMsg("按起止日期计费的课程不允许删除，只能换课或单科退费!");
                    ajaxJson.setCode("N");
                    return ajaxJson;
                }
            } else {
                //如果是按课时或按课时包计费的课程
                if (del.getRemainkeshi().intValue() != 0) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    ajaxJson.setMsg("剩余课时不为零的课程不允许删除");
                    ajaxJson.setCode("N");
                    return ajaxJson;
                }
                //操作纪录
                Pxbxkcchangetable addupTab = new Pxbxkcchangetable();
                addupTab.setOldStuID(del.getStuID());
                addupTab.setOldkcID(del.getKechengID());
                addupTab.setOldbxkcID(del.getId());
                addupTab.setOldbxkcName(iPxkechengtableService.getById(del.getKechengID()).getKechengName());
                addupTab.setOldqiandanID(del.getQianDanInfoID());
                addupTab.setOldzongjia(del.getZongjia());
                addupTab.setOldstartDate(del.getStartDate());
                addupTab.setOldendDate(del.getEndDate());
                addupTab.setOldprice(del.getKechengprice());
                addupTab.setOldrkeshi(del.getRemainkeshi());
                addupTab.setQiyeID(qiyeID);


                Pxstutable stu = iPxstutableService.getById(del.getStuID());
                Pxkechengtable kecheng = iPxkechengtableService.getById(del.getKechengID());
                xiugaiInfo += ",删除课程：" + kecheng.getKechengName() + "，只有按课时或课时包计费的课程且剩余课时为零才能删除";

                List<Pxkeshistutable> stuks = iPxkeshistutableService.getBybxID(del.getId(), Long.valueOf(qiyeID));
                if (stuks.size() > 0) {
                    xiugaiInfo += ",这个学员这个课程有课耗";
                    //看看这个学员的这个课程是不是只有一条记录，如果有多条记录，就可以删除；
                    List<Pxbuxikechengtable> stuThisKC = iPxbuxikechengtableService.getBxToStuAKc(del.getStuID(), del.getKechengID(), Long.valueOf(qiyeID));
                    if (stuThisKC.size() > 1) {
                        iPxbuxikechengtableService.removeById(item.getId());
                        xiugaiInfo += ",这个学员该课程有多条记录，删除本条";
                    }
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    ajaxJson.setMsg("有课耗记录的课程不允许删除");
                    ajaxJson.setCode("N");
                    return ajaxJson;
                } else {
                    iPxbuxikechengtableService.removeById(item.getId());

                    addupTab.setAddDate(new Date());
                    addupTab.setAddStaffID(staffID);
                    addupTab.setType(7);
                    iPxbxkcchangetableService.save(addupTab);
                }
            }
        }
        ajaxJson.setMsg("删除成功");
        return ajaxJson;
    }


    /**
     * @Description: updateIsshow方法作用:设置课程启用|不启用
     * @param:[buxiID, show]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/3 11:04
     */
    @ApiOperation("课程启用|不启用")
    @RequestMapping(value = "updateIsshow", method = RequestMethod.POST)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "buxiID", value = "补习ID", required = true),
            @ApiImplicitParam(name = "show", value = "补习ID 1：已启用 0：未启用", required = true)
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson updateIsshow(@RequestBody isshowkcForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();

        Pxbuxikechengtable bxkc = iPxbuxikechengtableService.getById(form.getBuxiID());
        Pxkechengtable kc = iPxkechengtableService.getById(bxkc.getKechengID());
        String buxiStyleName = iPxbuxistyletableService.getById(kc.getBuxiStyleID()).getBuxiStyleName();
        Integer is1v1 = iPxbuxistyletableService.getById(kc.getBuxiStyleID()).getIs1v1();
        //Integer isShow = bxkc.getIsShow();

        if (form.getShow() == 0) {
            //课程要设置为不启用（隐藏）当前是启用
            if (buxiStyleName.equals("一对一") && is1v1 == 1) {
                List<Pxstuclasstable> stucla = iPxstuclasstableService.getBybxID(bxkc.getId(), Long.valueOf(qiyeID));
                if (stucla.size() > 0) {
                    for (Pxstuclasstable oneStucla : stucla) {
                        Pxclasstable cla = iPxclasstableService.getById(oneStucla.getClassID());
                        if (cla != null) {
                            cla.setIsShow(0);
                            iPxclasstableService.updateById(cla);
                        }
                    }
                }
            } else {
                List<Pxstuclasstable> stucla = iPxstuclasstableService.getBybxID(bxkc.getId(), Long.valueOf(qiyeID));
                if (stucla.size() > 0) {
                    for (Pxstuclasstable onestucla : stucla) {
                        List<Pxpaiketable> claPaikeAll = iPxpaiketableService.getBykq(onestucla.getClassID(), Long.valueOf(qiyeID));
                        if (claPaikeAll.size() > 0) {
                            for (Pxpaiketable oneClapk : claPaikeAll) {
                                List<Pxkeshistutable> iskaoqin = iPxkeshistutableService.otherStuks(oneClapk.getClassID(), oneClapk.getHaveClassDate(),
                                        oneClapk.getStartLessonDateTime(), oneClapk.getEndLessonDateTime(), bxkc.getStuID(), Long.valueOf(qiyeID));
                                //没有打考勤才退班
                                if (iskaoqin.size() == 0) {
                                    //从选课表里删除之后再退班
                                    List<Pxxuanketable> stuXuankeAll = iPxxuanketableService.xxkbypkbx(oneClapk.getId(), bxkc.getId(), Long.valueOf(qiyeID));
                                    if (stuXuankeAll.size() > 0) {
                                        for (Pxxuanketable xkId : stuXuankeAll) {
                                            iPxxuanketableService.removeById(xkId.getId());
                                        }
                                    }
                                }
                            }
                        }
                        iPxstuclasstableService.removeById(onestucla.getId());
                    }
                }
            }
            bxkc.setIsShow(0);
        } else {
            //课程要设置启用（显示）当前是未启用
            bxkc.setIsShow(1);

            if (buxiStyleName.equals("一对一") && is1v1 == 1) {
                List<Pxstuclasstable> stucla = iPxstuclasstableService.getBybxID(bxkc.getId(), Long.valueOf(qiyeID));
                if (stucla.size() > 0) {
                    Pxclasstable cla = iPxclasstableService.getById(stucla.get(0).getClassID());
                    if (cla != null) {
                        cla.setIsShow(1);
                        List<Pxpaiketable> hvpaike = iPxpaiketableService.getpkBYClassID(stucla.get(0).getClassID(), Long.valueOf(qiyeID));
                        if (hvpaike.size() > 0) {
                            for (Pxpaiketable item : hvpaike) {
                                List<Pxxuanketable> addxuanke = iPxxuanketableService.allxuankebypkID(item.getId(), Long.valueOf(qiyeID));
                                if (addxuanke.size() == 0) {
                                    Pxxuanketable addtab = new Pxxuanketable();
                                    addtab.setPaikeID(item.getId());
                                    addtab.setBuxiID(bxkc.getId());
                                    addtab.setRecordDate(new Date());
                                    addtab.setStuID(bxkc.getStuID());
                                    addtab.setType(1);
                                    addtab.setQiyeID(qiyeID);
                                    iPxxuanketableService.save(addtab);
                                }
                            }
                        }
                    }
                }

            }
            ajaxJson.setMsg("该课程已经启用成功！！\n" + "【温馨提示：如果是非一对一课程，记得要插班！】");
        }

        ajaxJson.setSuccess(iPxbuxikechengtableService.updateById(bxkc));
        return ajaxJson;
    }


    /**
     * @Description: KechengYanqi方法作用:课程延期
     * @param:[bxkeID, endDate]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:02
     */
    @ApiOperation("课程延期")
    @ResponseBody
    @RequestMapping(value = "KechengYanqi", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson KechengYanqi(@RequestBody KechengYanqiForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        BigDecimal l = new BigDecimal(0);
        Date nendDate = null;
        try {
            nendDate = df.parse(form.getEndDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Pxbuxikechengtable kechengTab = iPxbuxikechengtableService.getById(form.getBxkeID());
        Date oldendDate = kechengTab.getEndDate();
        if (nendDate.getTime() <= oldendDate.getTime()) {
            ajaxJson.setMsg("延期结束时间有误");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        if (kechengTab.getJifeiStyleID() == 3 && nendDate.getTime() > oldendDate.getTime()) {
            long time = (nendDate.getTime() - kechengTab.getStartDate().getTime()) / (24 * 1000 * 60 * 60);
            long day = time + 1;
            BigDecimal days = new BigDecimal(day);
            //按起止日期计费的课程 添加一条新的补习课程
            Pxbuxikechengtable newbuxi = new Pxbuxikechengtable();
            newbuxi.setBuykeshiDateTime(new Date());
            newbuxi.setEndDate(nendDate);
            newbuxi.setIsShow(1);
            newbuxi.setJifeiStyleID(kechengTab.getJifeiStyleID());
            newbuxi.setKechengprice(l);
            newbuxi.setOriginalprice(l);
            newbuxi.setKeshiNum(kechengTab.getKeshiNum());
            newbuxi.setRemainkeshi(days);
            newbuxi.setStartDate(oldendDate);
            newbuxi.setStuID(kechengTab.getStuID());
            newbuxi.setType(2);
            newbuxi.setZongjia(l);
            newbuxi.setQiyeID(qiyeID);
            newbuxi.setKechengID(kechengTab.getKechengID());
            iPxbuxikechengtableService.save(newbuxi);
        } else {
            kechengTab.setEndDate(nendDate);

            iPxbuxikechengtableService.updateById(kechengTab);
        }

        //添加操作记录（课程变动流水）
        Pxbxkcchangetable addupTab = new Pxbxkcchangetable();
        addupTab.setOldStuID(kechengTab.getStuID());
        addupTab.setOldkcID(kechengTab.getKechengID());
        addupTab.setOldbxkcID(kechengTab.getId());
        addupTab.setOldbxkcName(iPxkechengtableService.getById(kechengTab.getKechengID()).getKechengName());
        addupTab.setOldqiandanID(kechengTab.getQianDanInfoID());
        addupTab.setOldzongjia(kechengTab.getZongjia());
        addupTab.setOldstartDate(kechengTab.getStartDate());
        addupTab.setOldendDate(oldendDate);
        addupTab.setOldprice(kechengTab.getKechengprice());
        addupTab.setOldrkeshi(kechengTab.getRemainkeshi());
        addupTab.setNewendDate(kechengTab.getEndDate());
        addupTab.setAddStaffID(staffID);
        addupTab.setAddDate(new Date());
        addupTab.setQiyeID(qiyeID);
        addupTab.setType(13);
        iPxbxkcchangetableService.save(addupTab);
        ajaxJson.setMsg("保存成功");
        return ajaxJson;
    }


    /**
     * @Description: editkcPrice方法作用:修改课程单价
     * @param:[bxkeID, newprice, newkeshi]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:02
     */
    @ApiOperation("改单价")
    @ResponseBody
    @RequestMapping(value = "editkcPrice", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bxkcID", value = "补习课程ID", required = true),
            @ApiImplicitParam(name = "newprice", value = "新的课程单价", required = true),
            @ApiImplicitParam(name = "newkeshi", value = "新的课时数", required = true),
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson editkcPrice(@RequestBody editkcPriceForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();

        BigDecimal Nprice = new BigDecimal(form.getNewprice());
        BigDecimal Nkeshi = new BigDecimal(form.getNewkeshi());
        Pxbuxikechengtable buxikc = iPxbuxikechengtableService.getById(form.getBxkeID());
//        Pxstutable stu = iPxstutableService.getById(buxikc.getStuID());
//        Pxkechengtable kecheng = iPxkechengtableService.getById(buxikc.getKechengID());

        //原单价|剩余课时
        BigDecimal oldprice = buxikc.getKechengprice();
        BigDecimal oldkeshi = buxikc.getRemainkeshi();

        //修改现单价|剩余课时
        buxikc.setKechengprice(Nprice);
        buxikc.setRemainkeshi(Nkeshi);
        iPxbuxikechengtableService.updateById(buxikc);

        //添加课程变动流水
        Pxbxkcchangetable addipTab = new Pxbxkcchangetable();
        addipTab.setOldStuID(buxikc.getStuID());
        addipTab.setOldkcID(buxikc.getKechengID());
        addipTab.setOldbxkcID(buxikc.getId());
        addipTab.setOldbxkcName(iPxkechengtableService.getById(buxikc.getKechengID()).getKechengName());
        addipTab.setOldqiandanID(buxikc.getQianDanInfoID());
        addipTab.setOldzongjia(buxikc.getZongjia());
        addipTab.setOldstartDate(buxikc.getStartDate());
        addipTab.setOldendDate(buxikc.getEndDate());
        addipTab.setOldprice(oldprice);
        addipTab.setOldrkeshi(oldkeshi);
        addipTab.setNewprice(Nprice);
        addipTab.setNewrkeshi(Nkeshi);
        addipTab.setAddStaffID(staffID);
        addipTab.setQiyeID(qiyeID);
        addipTab.setAddDate(new Date());
        addipTab.setType(10);
        iPxbxkcchangetableService.save(addipTab);
        ajaxJson.setMsg("修改完成");
        return ajaxJson;
    }

    /**
     * @Description: Getnewkc()方法作用:添加课时计费课程/课时包计费课程 时获取所有方式课程树
     * @param:[request, stuID, type]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/2/18 16:07
     */
    @ApiOperation("添加课时计费课程获取所有方式课程树（按校区）")
    @ResponseBody
    @RequestMapping(value = "Getnewkc", method = RequestMethod.GET)
    public AjaxJson Getnewkc(HttpServletRequest request, String stuID, int type) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Long staffID = Long.valueOf(loginUser.getStaffID());

        Pxstutable stu = iPxstutableService.getById(stuID);
        Long campusID = stu.getCampusID();
        ajaxJson.setObj(iPxbuxikechengtableService.Getnewkc(campusID, type, qiyeID));
        return ajaxJson;
    }

    /**
     * @Description: addKSCourse方法作用:添加课时计费课程
     * @param:[stuID, kcID, bx_startDate, bx_endDate, bx_odj, bx_dj]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:02
     */
    @ApiOperation("添加课时计费课程")
    @ResponseBody
    @RequestMapping(value = "addKSCourse", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true),
            @ApiImplicitParam(name = "kcID", value = "课程ID", required = true),
            @ApiImplicitParam(name = "bx_startDate", value = "开始时间", required = true),
            @ApiImplicitParam(name = "bx_endDate", value = "结束时间", required = true),
            @ApiImplicitParam(name = "bx_odj", value = "原单价", required = true),
            @ApiImplicitParam(name = "bx_dj", value = "现单价", required = true),
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addKSCourse(Long stuID, Long kcID, String bx_startDate, String bx_endDate, String bx_odj, String bx_dj, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        BigDecimal olddj = new BigDecimal(bx_odj);
        BigDecimal newdj = new BigDecimal(bx_dj);
        BigDecimal l = new BigDecimal(0);
        //时间转换
        Date endDate = null;
        Date startDate = null;
        try {
            startDate = df.parse(bx_startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            endDate = df.parse(bx_endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (olddj.intValue() < newdj.intValue()) {
            ajaxJson.setMsg("原单价不得低于现单价");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        if (startDate.getTime() > endDate.getTime()) {
            ajaxJson.setMsg("开始日期不得晚于结束日期");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        List<Pxbuxikechengtable> bxkc = iPxbuxikechengtableService.getStubxkc(stuID, kcID, Long.valueOf(qiyeID));
        if (bxkc.size() > 0) {
            //要添加的这个课程已经有了；
            ajaxJson.setMsg("该学员已经有这门课程了，不能重复添加!");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        Pxbuxikechengtable one = new Pxbuxikechengtable();
        one.setStuID(stuID);
        one.setKechengID(kcID);
        one.setKechengprice(newdj);
        one.setOriginalprice(olddj);
        one.setRemainkeshi(l);
        one.setKeshiNum(l);
        one.setZongjia(l);
        one.setStartDate(startDate);
        one.setEndDate(endDate);
        one.setBuykeshiDateTime(new Date());
        one.setIsShow(1);
        one.setType(1);
        one.setJifeiStyleID(1);
        one.setQiyeID(qiyeID);
        iPxbuxikechengtableService.save(one);

        //操作记录
        Pxbxkcchangetable addupTab = new Pxbxkcchangetable();
        addupTab.setOldStuID(one.getStuID());
        addupTab.setOldkcID(one.getKechengID());
        addupTab.setOldbxkcID(one.getId());
        addupTab.setOldbxkcName(iPxkechengtableService.getById(one.getKechengID()).getKechengName());
        addupTab.setOldqiandanID(one.getQianDanInfoID());
        addupTab.setOldzongjia(one.getZongjia());
        addupTab.setOldstartDate(one.getStartDate());
        addupTab.setOldendDate(one.getEndDate());
        addupTab.setOldprice(one.getKechengprice());
        addupTab.setOldrkeshi(one.getRemainkeshi());
        addupTab.setAddStaffID(staffID);
        addupTab.setAddDate(new Date());
        addupTab.setType(6);
        addupTab.setQiyeID(qiyeID);
        iPxbxkcchangetableService.save(addupTab);

        //如果添加的是一对一的课程，还要为他建一个一对一的班；
        List<stubxStyleVo> stubxStyle = iPxbuxikechengtableService.getstubxStyle(kcID, Long.valueOf(qiyeID));
        if (stubxStyle.size() > 0) {
            Pxstutable stu = iPxstutableService.getById(stuID);
            Long subjectID = iPxkechengtableService.getById(kcID).getSubjectID();
            Pxsubjecttable subjectName = iPxsubjecttableService.getById(subjectID);
            Pxclasstable cla = new Pxclasstable();
            boolean ii = false;
            while (ii == false) {
                Random rd = new Random();
                int sjs = rd.nextInt(999);
                String className = stu.getStuName() + "_" + subjectName + "_一对一" + sjs;
                List<Pxclasstable> pdclass = iPxclasstableService.FkClassName(className, Long.valueOf(qiyeID));
                if (pdclass.size() == 0) {
                    cla.setMaxStuNum(1);
                    cla.setClassName(className);
                    cla.setZidingyiClassID("");
                    cla.setCampusID(stu.getCampusID());
                    cla.setIsShow(1);
                    cla.setAddStaffID(staffID);
                    cla.setAddTime(new Date());
                    cla.setIs1v1Class(1);
                    cla.setQiyeID(qiyeID);
                    cla.setClassState(0);
                    iPxclasstableService.save(cla);
                    ii = true;
                }
            }
            Pxstuclasstable stucla = new Pxstuclasstable();
            stucla.setBuxiID(one.getId());
            stucla.setClassID(cla.getId());
            stucla.setQiyeID(qiyeID);
            iPxstuclasstableService.save(stucla);
        }

        return ajaxJson;
    }


    /**
     * @Description: addKSCourseBag方法作用:添加课时包计费课程
     * @param:[stuID, kcID, bx_startDate, bx_endDate, bx_odj, bx_dj, bx_ksKSB, bx_priceKSB]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:03
     */
    @ApiOperation("添加课时包计费课程")
    @ResponseBody
    @RequestMapping(value = "addKSCourseBag", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true),
            @ApiImplicitParam(name = "kcID", value = "课程ID", required = true),
            @ApiImplicitParam(name = "bx_startDate", value = "开始时间", required = true),
            @ApiImplicitParam(name = "bx_endDate", value = "结束时间", required = true),
            @ApiImplicitParam(name = "bx_odj", value = "原单价", required = true),
            @ApiImplicitParam(name = "bx_dj", value = "现单价", required = true),
            @ApiImplicitParam(name = "bx_ksKSB", value = "课时", required = true),
            @ApiImplicitParam(name = "bx_priceKSB", value = "总价", required = true),
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addKSCourseBag(Long stuID, Long kcID, String bx_startDate, String bx_endDate, String bx_odj, String bx_dj, String bx_ksKSB,
                                   String bx_priceKSB, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();

        BigDecimal olddj = new BigDecimal(bx_odj);//原单价
        BigDecimal newdj = new BigDecimal(bx_dj);//现单价
        BigDecimal ksKSB = new BigDecimal(bx_ksKSB);//课时数
        BigDecimal zongjia = new BigDecimal(bx_priceKSB);//总价

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();

        //时间转换
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date endDate = new Date();
        Date startDate = new Date();
        try {
            startDate = df.parse(bx_startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            endDate = df.parse(bx_endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //如果原单价低于现单价
        if (olddj.intValue() < newdj.intValue()) {
            ajaxJson.setMsg("原单价不得低于现单价！");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        //如果开始日期晚于结束日期
        if (startDate.getTime() > endDate.getTime()) {
            ajaxJson.setMsg("开始日期不得晚于结束日期！");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        List<Pxbuxikechengtable> bxkc = iPxbuxikechengtableService.getStubxkc(stuID, kcID, Long.valueOf(qiyeID));
        if (bxkc.size() > 0) {
            //要添加的这个课程已经有了；
            ajaxJson.setMsg("该学员已经有这门课程了，不能重复添加!");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        Pxbuxikechengtable one = new Pxbuxikechengtable();
        one.setStuID(stuID);
        one.setKechengID(kcID);
        one.setKechengprice(newdj);
        one.setOriginalprice(olddj);
        one.setRemainkeshi(ksKSB);
        one.setKeshiNum(ksKSB);
        one.setZongjia(zongjia);
        one.setStartDate(startDate);
        one.setEndDate(endDate);
        one.setBuykeshiDateTime(new Date());
        one.setIsShow(1);
        one.setJifeiStyleID(2);
        one.setType(1);
        one.setQiyeID(qiyeID);
        iPxbuxikechengtableService.save(one);

        //操作记录
        Pxbxkcchangetable addupTab = new Pxbxkcchangetable();
        addupTab.setOldStuID(one.getStuID());
        addupTab.setOldkcID(one.getKechengID());
        addupTab.setOldbxkcID(one.getId());
        addupTab.setOldbxkcName(iPxkechengtableService.getById(one.getKechengID()).getKechengName());
        addupTab.setOldqiandanID(one.getQianDanInfoID());
        addupTab.setOldzongjia(one.getZongjia());
        addupTab.setOldstartDate(one.getStartDate());
        addupTab.setOldendDate(one.getEndDate());
        addupTab.setOldprice(one.getKechengprice());
        addupTab.setOldrkeshi(one.getRemainkeshi());
        addupTab.setAddStaffID(staffID);
        addupTab.setAddDate(new Date());
        addupTab.setType(6);
        addupTab.setQiyeID(qiyeID);
        iPxbxkcchangetableService.save(addupTab);

        //如果添加的是一对一的课程，还要为他建一个一对一的班；
        stubxStyleVo stubxStyle = iPxbuxikechengtableService.getstubxStyle(kcID, Long.valueOf(qiyeID)).get(0);
        if (stubxStyle != null) {
            Pxstutable stu = iPxstutableService.getById(stuID);
            Long subjectID = iPxkechengtableService.getById(kcID).getSubjectID();
            Pxsubjecttable subjectName = iPxsubjecttableService.getById(subjectID);
            Pxclasstable cla = new Pxclasstable();
            boolean ii = false;
            while (ii == false) {
                Random rd = new Random();
                int sjs = rd.nextInt(999);
                String className = stu.getStuName() + "_" + subjectName + "_一对一" + sjs;
                List<Pxclasstable> pdclass = iPxclasstableService.FkClassName(className, Long.valueOf(qiyeID));
                if (pdclass.size() == 0) {
                    cla.setClassName(className);
                    cla.setClassState(0);
                    cla.setZidingyiClassID("");
                    cla.setCampusID(stu.getCampusID());
                    cla.setIsShow(1);
                    cla.setAddStaffID(staffID);
                    cla.setAddTime(new Date());
                    cla.setIs1v1Class(1);
                    cla.setMaxStuNum(1);
                    cla.setQiyeID(qiyeID);
                    iPxclasstableService.save(cla);
                    ii = true;
                }
            }
            Pxstuclasstable stucla = new Pxstuclasstable();
            stucla.setBuxiID(one.getId());
            stucla.setClassID(cla.getId());
            stucla.setQiyeID(qiyeID);
            iPxstuclasstableService.save(stucla);
        }
        return ajaxJson;
    }


    /**
     * @Description: getHebingTokechengCount()方法作用:检测是否可课程合并
     * @param:[oldbuxiID, request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/2/19 9:44
     */
    @ApiOperation("检测是否可课程合并")
    @ResponseBody
    @RequestMapping(value = "getHebingTokechengCount", method = RequestMethod.GET)
    public AjaxJson getHebingTokechengCount(Long oldbuxiID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Pxbuxikechengtable oldbuxikc = iPxbuxikechengtableService.getById(oldbuxiID);
        if (oldbuxikc.getType() == 2) {
            ajaxJson.setCode("N");
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("该课程为赠送课程，无法合并！");
            return ajaxJson;
        }
        QueryWrapper<Pxbuxikechengtable> bkQueryWrapper = new QueryWrapper<>();
        bkQueryWrapper
                .ne("id", oldbuxikc.getId())
                .eq("qiyeID", qiyeID)
                .eq("stuID", oldbuxikc.getStuID())
                //.eq("isShow", 1)     //课程有没有启用，不影响课程是否能合并
                .and(a -> a.eq("jifeiStyleID", 1).or(b -> b.eq("jifeiStyleID", 2)));
        List<Pxbuxikechengtable> selectbxkc = iPxbuxikechengtableService.selectbxkc(bkQueryWrapper);
        if (selectbxkc.size() == 0) {
            ajaxJson.setCode("N");
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("该学员没有可以合并的课程！");
            return ajaxJson;
        }

        ajaxJson.setObj(selectbxkc);
        return ajaxJson;
    }


    /**
     * @Description: getHebingTokecheng()方法作用:获取可合并课程
     * @param:[oldbuxiID, request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/2/19 10:56
     */
    @ApiOperation("获取可合并课程")
    @ResponseBody
    @RequestMapping(value = "getHebingTokecheng", method = RequestMethod.GET)
    public AjaxJson getHebingTokecheng(Long oldbuxiID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Pxbuxikechengtable oldbuxi = iPxbuxikechengtableService.getById(oldbuxiID);
        ajaxJson.setObj(iPxbuxikechengtableService.getHebingTokecheng(oldbuxiID, oldbuxi.getStuID(), qiyeID));
        return ajaxJson;
    }


    /**
     * @Description: MergeCourse方法作用:课程合并
     * @param:[buxiKCIDold, buxiKCIDnew]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:03
     */
    @ApiOperation("课程合并")
    @ResponseBody
    @RequestMapping(value = "MergeCourse", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldbxID", value = "原补习ID", required = true),
            @ApiImplicitParam(name = "newbxID", value = "合并到补习ID", required = true),
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson MergeCourse(@RequestBody MergeCourseForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();
        String xiugaiInfo = "";

        BigDecimal l = new BigDecimal(0);
        Pxbuxikechengtable buxikcold = iPxbuxikechengtableService.getById(form.getOldbxID());
        Pxbuxikechengtable buxiKCnew = iPxbuxikechengtableService.getById(form.getNewbxID());
        if ((buxikcold.getType() != null && buxikcold.getType() != 1) || (buxiKCnew.getType() != null && buxiKCnew.getType() != 1)) {
            ajaxJson.setMsg("操作失败，存在赠送课程");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        //课程合并判断合并课程是否有排课，并且排课没有被消过课
        List<Pxpaiketable> paike = iPxpaiketableService.getBykc(buxikcold.getKechengID(), Long.valueOf(qiyeID));
        for (int i = 0; i < paike.size(); i++) {
            Pxpaiketable item = paike.get(i);
            Long paikeID = item.getId();
            Date haveClassDate = item.getHaveClassDate();

            Time startLessonDateTime = item.getStartLessonDateTime();
            Time endLessonDateTime = item.getEndLessonDateTime();
            List<Pxxuanketable> xuanke = iPxxuanketableService.getxkByPkStuBx(paikeID, buxikcold.getStuID(), form.getOldbxID(), Long.valueOf(qiyeID));
            for (int j = 0; j < xuanke.size(); j++) {
                Pxxuanketable item2 = xuanke.get(j);
                List<Pxkeshistutable> keshiStu = iPxkeshistutableService.getByStuKcDateTime(buxikcold.getStuID(), buxikcold.getKechengID(), haveClassDate,
                        startLessonDateTime, endLessonDateTime, Long.valueOf(qiyeID));
                if (keshiStu.size() == 0) {
                    //排课没有被消过课
                    iPxxuanketableService.removeById(item2.getId());
                }
            }

        }

        Pxstutable stu = iPxstutableService.getById(buxikcold.getStuID());
        Pxkechengtable oldkc = iPxkechengtableService.getById(buxikcold.getKechengID());
        Pxkechengtable newkc = iPxkechengtableService.getById(buxiKCnew.getKechengID());

        xiugaiInfo = "，【按课时或课时包计费的课程】将原课程《" + oldkc.getKechengName() + "》合并到课程《" + newkc.getKechengName() + "》";

        if (buxikcold.getKechengprice().compareTo(buxiKCnew.getKechengprice()) == 0) {//相等
            xiugaiInfo += "，和要合并的课程单价相同，都是" + buxikcold.getKechengprice().toString() + "，把原课程的剩余课时合并到新课程上去";
            xiugaiInfo += ",原课程剩余课时" + buxikcold.getRemainkeshi().toString() + ",合并前，新课程的剩余课时" + buxiKCnew.getRemainkeshi().toString();
            buxiKCnew.setRemainkeshi(buxiKCnew.getRemainkeshi().add(buxikcold.getRemainkeshi()));
            iPxbuxikechengtableService.updateById(buxiKCnew);
            xiugaiInfo += "合并后，新课程剩余课时" + buxiKCnew.getRemainkeshi().toString();
        } else {
            BigDecimal hbMoney = buxikcold.getKechengprice().multiply(buxikcold.getRemainkeshi());
            xiugaiInfo += "，要合并的两个课程单价不同；原课程单价" + buxikcold.getKechengprice().toString() + ",剩余课时" + buxikcold.getRemainkeshi().toString() + "，要合并的学费为" + hbMoney.toString() + ",新课时单价" + buxiKCnew.getKechengprice().toString();
            BigDecimal tokeshi = hbMoney.divide(buxiKCnew.getKechengprice());
            xiugaiInfo += ",要合并的学费除以新课程单价后，折算的课时为" + tokeshi.toString();
            xiugaiInfo += ",新课程合并前的剩余课时为" + buxiKCnew.getRemainkeshi().toString();

            BigDecimal addks = buxiKCnew.getRemainkeshi().add(tokeshi);
            if (addks.compareTo(form.getNew_allkeshi()) == 0) {
                xiugaiInfo += ",并入后总课时正常。";
            } else {
                xiugaiInfo += ",并入后总课时异常。";
            }
            buxiKCnew.setRemainkeshi(addks);
            iPxbuxikechengtableService.updateById(buxiKCnew);
            xiugaiInfo += ",新课程收到并入课时" + tokeshi.toString() + "后，剩余课时为" + addks.toString();
        }
        //要合并的原课程剩余课时置零
        buxikcold.setRemainkeshi(l);
        iPxbuxikechengtableService.updateById(buxikcold);

        List<Pxkeshistutable> stukeshi = iPxkeshistutableService.getByStuKc(buxikcold.getStuID(), buxikcold.getKechengID(), Long.valueOf(qiyeID));
        if (stukeshi.size() > 0) {
            //即这个学员的这个课程，不只一条记录，则可以删除
            List<Pxbuxikechengtable> stuThisKc = iPxbuxikechengtableService.getBxToStuAKc(buxikcold.getStuID(), buxikcold.getKechengID(), Long.valueOf(qiyeID));
            if (stuThisKc.size() > 1) {
                buxikcold.setIsShow(0);
                xiugaiInfo += ",这个学员的这个课程，不只一条记录，（不再删除课程，仅仅只是清零隐藏）";
            } else if (stuThisKc.size() == 1) {
                buxikcold.setIsShow(0);
                xiugaiInfo += ",这个学员的这个课程，只一条记录，将本条补习课程记录设置为隐藏（即不启用）";
            }
        } else {
            buxikcold.setIsShow(0);
            xiugaiInfo += "，合并的原课程没有课耗，（不再删除课程，仅仅只是清零隐藏）";
        }

        //操作记录
        Pxbxkcchangetable addupTab = new Pxbxkcchangetable();
        addupTab.setOldStuID(buxikcold.getStuID());
        addupTab.setOldkcID(buxikcold.getKechengID());
        addupTab.setOldbxkcID(buxikcold.getId());
        addupTab.setOldbxkcName(iPxkechengtableService.getById(buxikcold.getKechengID()).getKechengName());
        addupTab.setOldqiandanID(buxikcold.getQianDanInfoID());
        addupTab.setOldzongjia(buxikcold.getZongjia());
        addupTab.setOldstartDate(buxikcold.getStartDate());
        addupTab.setOldendDate(buxikcold.getEndDate());
        addupTab.setOldprice(buxikcold.getKechengprice());
        addupTab.setOldrkeshi(buxikcold.getRemainkeshi());
        addupTab.setNewStuID(buxiKCnew.getStuID());
        addupTab.setNewkcID(buxiKCnew.getKechengID());
        addupTab.setNewbxkcID(buxiKCnew.getId());
        addupTab.setNewprice(buxiKCnew.getKechengprice());
        addupTab.setNewrkeshi(buxiKCnew.getRemainkeshi());
        addupTab.setNewzongjia(buxiKCnew.getZongjia());
        addupTab.setNewstartDate(buxiKCnew.getStartDate());
        addupTab.setNewendDate(buxiKCnew.getEndDate());
        addupTab.setAddStaffID(staffID);
        addupTab.setAddDate(new Date());
        addupTab.setQiyeID(qiyeID);
        addupTab.setType(11);
        iPxbxkcchangetableService.save(addupTab);

        ajaxJson.setMsg(xiugaiInfo);
        return ajaxJson;
    }

    @ApiOperation("获取科目课程")
    @ResponseBody
    @RequestMapping(value = "getnewkcInfo", method = RequestMethod.GET)
    public AjaxJson getnewkcInfo(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        ajaxJson.setObj(iPxbuxikechengtableService.getnewkcInfo(qiyeID));
        return ajaxJson;
    }

    /**
     * @Description: saveChangekecheng方法作用:换课
     * @param:[hcbuxiKCIDold, KCjifeiStyleOld, remainxuefeiOld, KCIDnew, KCjifeiStyleNew, hkStartDate, hkEndDate]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 17:42
     */
    @ApiOperation("换课")
    @ResponseBody
    @RequestMapping(value = "saveChangekecheng", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson saveChangekecheng(@RequestBody ChangekechengForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();

        BigDecimal l = new BigDecimal(0);
        BigDecimal rxuefeiOld = new BigDecimal(form.getOldkcMoney());
        BigDecimal HKKCprice = new BigDecimal(form.getHkKechengprice());
        BigDecimal HKKS = new BigDecimal(form.getHkKeshi());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date NStartDate = new Date();
        Date NEndDate = new Date();
        try {
            NStartDate = df.parse(form.getHkStartDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            NEndDate = df.parse(form.getHkEndDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Long oldkcID = null;//老的课程
        BigDecimal oldkcprice = l;
        BigDecimal oldkckeshi = l;//老的补习课时
        BigDecimal newhkKcprice = l;//新课程单价
        BigDecimal newhkKS = l;//新课程课时
        BigDecimal zk = new BigDecimal(10);  //折扣

        if (form.getKcjifeistyleNew() == 1 ||form.getKcjifeistyleNew() == 2) {
            newhkKcprice = HKKCprice;
            newhkKS = HKKS;
        }
        if (form.getKcjifeistyleNew() == 1 &&form.getIszk()== true) {
            zk = new BigDecimal(form.getZk());
        }

        Pxbuxikechengtable oldbxlsTab = new Pxbuxikechengtable();
        Pxbuxikechengtable newbxlsTab = new Pxbuxikechengtable();

        //看看原来的课是否有课耗
        Pxbuxikechengtable oldbuxikc = iPxbuxikechengtableService.getById(form.getHkbuxiID());
        //记录原补习课程
        oldbxlsTab = oldbuxikc;

        //操作记录
        Pxbxkcchangetable addupTab = new Pxbxkcchangetable();
        addupTab.setOldStuID(oldbxlsTab.getStuID());
        addupTab.setOldkcID(oldbxlsTab.getKechengID());
        addupTab.setOldbxkcID(oldbxlsTab.getId());
        addupTab.setOldbxkcName(iPxkechengtableService.getById(oldbxlsTab.getKechengID()).getKechengName());
        addupTab.setOldqiandanID(oldbxlsTab.getQianDanInfoID());
        addupTab.setOldzongjia(oldbxlsTab.getZongjia());
        addupTab.setOldstartDate(oldbxlsTab.getStartDate());
        addupTab.setOldendDate(oldbxlsTab.getEndDate());
        addupTab.setOldprice(oldbxlsTab.getKechengprice());
        addupTab.setOldrkeshi(oldbxlsTab.getRemainkeshi());

        oldkcID = oldbuxikc.getKechengID();
        oldkcprice = oldbuxikc.getKechengprice();
        oldkckeshi = oldbuxikc.getRemainkeshi();

        Pxkechengtable oldkc = iPxkechengtableService.getById(oldbxlsTab.getKechengID());
        String oldkcBuxiStyleName = iPxbuxistyletableService.getById(oldkc.getBuxiStyleID()).getBuxiStyleName();
        Integer oldis1v1 = iPxbuxistyletableService.getById(oldkc.getBuxiStyleID()).getIs1v1();
        Pxstutable stu = iPxstutableService.getById(oldbxlsTab.getStuID());

        List<Pxkeshistutable> oldkehao = iPxkeshistutableService.getByStuKc(oldbuxikc.getStuID(), oldbuxikc.getKechengID(), Long.valueOf(qiyeID));
        if (oldkehao.size() != 0) {
            //有课耗，再看这个学生的这个课程有几条记录
            List<Pxbuxikechengtable> kcCount = iPxbuxikechengtableService.getBxToStuAKc(oldbuxikc.getStuID(), oldbuxikc.getKechengID(), Long.valueOf(qiyeID));
            if (kcCount.size() > 1) {
                List<Pxstuclasstable> stucla = iPxstuclasstableService.getBybxID(oldbuxikc.getId(), Long.valueOf(qiyeID));
                if (stucla.size() > 0) {
                    if (oldkcBuxiStyleName.equals("一对一") && oldis1v1 == 1) {
                        //如果是一对一，还要删除班级
                        for (int i = 0; i < stucla.size(); i++) {
                            Pxstuclasstable item = stucla.get(i);
                            Pxclasstable cla = iPxclasstableService.getById(item.getClassID());
                            if (cla != null) {
                                //删除班级之前，把这个班的排课全删除掉
                                List<Pxpaiketable> claPaike = iPxpaiketableService.getpkBYClassID(item.getClassID(), Long.valueOf(qiyeID));
                                if (claPaike.size() > 0) {
                                    for (int j = 0; j < claPaike.size(); j++) {
                                        Pxpaiketable onep = claPaike.get(j);
                                        paiketeacherVo paikeT = iPxpaiketeachertableService.getTkTpaike(onep.getId(), Long.valueOf(qiyeID));
                                        if (paikeT != null) {
                                            String[] split = paikeT.getIDTS().split(",");
                                            iPxpaiketeachertableService.removeByIds(Arrays.asList(split));
                                        }
                                    }
                                    //删除选课
                                    List<ReclassVo> xuankes = iPxxuanketableService.getJoinPaikeL(item.getClassID(), Long.valueOf(qiyeID));
                                    if (xuankes.size() != 0) {
                                        String[] xkL = xuankes.get(0).getClassIDs().split(",");
                                        iPxxuanketableService.removeByIds(Arrays.asList(xkL));
                                    }

                                }
                                //假删除班级
                                cla.setIsdelete(true);
                                iPxclasstableService.updateById(cla);
                            }
                            iPxstuclasstableService.removeById(item.getId());
                        }
                    } else {
                        //不是一对一，退班即可,删除选课表
                        for (int i = 0; i < stucla.size(); i++) {
                            Pxstuclasstable item = stucla.get(i);
                            Pxclasstable cla = iPxclasstableService.getById(item.getClassID());
                            if (cla != null) {
                                //退班之前，把这个班的排课全删除掉
                                List<Pxpaiketable> claPaike = iPxpaiketableService.getpkBYClassID(item.getClassID(), Long.valueOf(qiyeID));
                                if (claPaike.size() > 0) {
                                    //删除选课
                                    List<ReclassVo> xuankes = iPxxuanketableService.getJoinPaikeStuL(item.getClassID(), oldbuxikc.getStuID(),
                                            Long.valueOf(qiyeID));
                                    if (xuankes.size() != 0) {
                                        String[] xkL = xuankes.get(0).getClassIDs().split(",");
                                        iPxxuanketableService.removeByIds(Arrays.asList(xkL));
                                    }
                                }
                            }
                            iPxstuclasstableService.removeById(item.getId());
                        }
                    }
                }
                oldbuxikc.setIsShow(0);
                oldbuxikc.setKeshiNum(l);
                oldbuxikc.setRemainkeshi(l);
                oldbuxikc.setZongjia(l);
                //保存修改
                iPxbuxikechengtableService.updateById(oldbuxikc);
            }
        } else {
            //说明没有课耗，可以删除本条
            oldbuxikc.setRemainkeshi(l);
            oldbuxikc.setZongjia(l);
            oldbuxikc.setIsShow(0);
            //iPxbuxikechengtableService.updateById(oldbuxikc);
            if (oldbuxikc.getJifeiStyleID() == 3) {
                //如果是按起止日期计费的课程，需要设置成过期状态

                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.DATE, -1);
                oldbuxikc.setEndDate(cal.getTime());
            }
            //保存修改
            iPxbuxikechengtableService.updateById(oldbuxikc);
        }


        //原课程往签单表里加一条记录
        Pxqiandaninfotable qdinfoOld = new Pxqiandaninfotable();
        qdinfoOld.setStuID(oldbuxikc.getStuID());
        qdinfoOld.setQiandandate(new Date());
        qdinfoOld.setShishouTotalMoney(l.subtract(rxuefeiOld)); //0-rxuefeiOld
        qdinfoOld.setMoneyStyle(5);  //1新签；2续费；3 退费 4 转送 5 换课换出 6 换课得到
        qdinfoOld.setQianDanStaffID(staffID);
        qdinfoOld.setRecordInStaffID(staffID);
        qdinfoOld.setRecordInTime(new Date());
        qdinfoOld.setPayMoneyStyle(9L); //1.现金  2.刷卡 3.微信 4.支付宝 5.对公转账 6.个人网银  7.转账支票 8.现金支票 9.其他
        qdinfoOld.setCampusID(stu.getCampusID());
        qdinfoOld.setQiyeID(qiyeID);
        qdinfoOld.setIsdingjing(1);
        qdinfoOld.setHetongMoney(l.subtract(rxuefeiOld));
        iPxqiandaninfotableService.save(qdinfoOld);


        Pxqiandansubjecttable qdsubold = new Pxqiandansubjecttable();
        qdsubold.setStuID(oldbuxikc.getStuID());
        qdsubold.setQiandandate(new Date());
        qdsubold.setKechengID(oldbuxikc.getKechengID());
        qdsubold.setKechengprice(oldbuxikc.getKechengprice());
        qdsubold.setOriginalprice(oldbuxikc.getOriginalprice());
        if (oldbuxikc.getJifeiStyleID() == 3) {
            qdsubold.setBuykeshiNum(l);
        } else {
            qdsubold.setBuykeshiNum(l.subtract(oldbuxikc.getRemainkeshi())); //0-remainkeshi
        }
        qdsubold.setZongjia(l);
        qdsubold.setQianDanInfoID(qdinfoOld.getId());
        qdsubold.setKechengStyle(5);//1买的 2 接受的赠送 3 送出的 4 退费 5 换课换出 6 换课得到

        qdsubold.setDiscount(zk); //折扣10（不打折）
        qdsubold.setQiyeID(qiyeID);
        iPxqiandansubjecttableService.save(qdsubold);

        //新课程往签单表里加一条记录
        Pxqiandaninfotable qdinfoNew = new Pxqiandaninfotable();
        qdinfoNew.setStuID(oldbuxikc.getStuID());
        qdinfoNew.setQiandandate(new Date());
        qdinfoNew.setShishouTotalMoney(rxuefeiOld);
        qdinfoNew.setHetongMoney(rxuefeiOld);
        qdinfoNew.setMoneyStyle(6);   //1新签；2续费；3 退费 4 转送 5 换课换出 6 换课得到
        qdinfoNew.setQianDanStaffID(staffID);
        qdinfoNew.setRecordInStaffID(staffID);
        qdinfoNew.setRecordInTime(new Date());
        qdinfoNew.setPayMoneyStyle(9L);   //1.现金  2.刷卡 3.微信 4.支付宝 5.对公转账 6.个人网银  7.转账支票 8.现金支票 9.其他
        qdinfoNew.setCampusID(stu.getCampusID());
        qdinfoNew.setQiyeID(qiyeID);
        qdinfoNew.setIsdingjing(1);
        iPxqiandaninfotableService.save(qdinfoNew);

        Pxqiandansubjecttable qdsubnew = new Pxqiandansubjecttable();
        qdsubnew.setStuID(oldbuxikc.getStuID());
        qdsubnew.setQiandandate(new Date());
        qdsubnew.setKechengID(oldbuxikc.getKechengID());
        qdsubnew.setKechengprice(newhkKcprice);
        qdsubnew.setOriginalprice(newhkKcprice);
        if (oldbuxikc.getJifeiStyleID() == 3) {
            qdsubnew.setBuykeshiNum(l);
        } else {
            qdsubnew.setBuykeshiNum(oldkckeshi);
        }
        qdsubnew.setZongjia(rxuefeiOld);
        qdsubnew.setQianDanInfoID(qdinfoOld.getId());
        qdsubnew.setKechengStyle(6);//1买的 2 接受的赠送 3 送出的 4 退费 5 换课换出 6 换课得到
        qdsubnew.setDiscount(zk);
        qdsubnew.setQiyeID(qiyeID);
        iPxqiandansubjecttableService.save(qdsubnew);

        oldkcID = oldbuxikc.getId();
        oldkcprice = oldbuxikc.getKechengprice();
        oldkckeshi = oldbuxikc.getRemainkeshi();

        //加新课程
        Pxkechengtable newkc = iPxkechengtableService.getById(form.getKcID());
        Pxbuxistyletable bxStyle = iPxbuxistyletableService.getById(newkc.getBuxiStyleID());
        String newkcBuxiStyleName = bxStyle.getBuxiStyleName();
        Integer newis1v1 = bxStyle.getIs1v1();
        String newkcSubjectName = iPxsubjecttableService.getById(newkc.getSubjectID()).getSubjectName();

        //看看要换的课程，这个学生是否已经有这个课了（并且单价相同）
        List<Pxbuxikechengtable> ishaveNewkc = iPxbuxikechengtableService.getBxToType(oldbuxikc.getStuID(),form.getKcID(), HKKCprice, Long.valueOf(qiyeID));
        if (ishaveNewkc.size() != 0) {
            Pxbuxikechengtable ishavekc = ishaveNewkc.get(0);
            ishavekc.setRemainkeshi(ishavekc.getRemainkeshi().add(HKKS));
            newbxlsTab = ishavekc;
        } else if (ishaveNewkc.size() != 0 && ishaveNewkc.get(0).getIsShow() == 1) {
            Long classID = null;
            if (newkcBuxiStyleName.equals("一对一") && newis1v1 == 1) {
                boolean ii = false;
                while (ii == false) {
                    Random rd = new Random();
                    int sjs = rd.nextInt(999);
                    String className = stu.getStuName() + "_" + newkcSubjectName + "_一对一" + sjs;
                    List<Pxclasstable> pdclass = iPxclasstableService.FkClassName(className, Long.valueOf(qiyeID));
                    if (pdclass.size() == 0) {
                        Pxclasstable classtable = new Pxclasstable();
                        classtable.setMaxStuNum(1);
                        classtable.setClassName(className);
                        classtable.setCampusID(stu.getCampusID());
                        classtable.setIsShow(1);
                        classtable.setQiyeID(qiyeID);
                        classtable.setIs1v1Class(1);
                        classtable.setIsdelete(false);
                        classtable.setAddTime(new Date());
                        classtable.setAddStaffID(staffID);
                        classtable.setZidingyiClassID("");
                        classtable.setClassState(0);
                        iPxclasstableService.save(classtable);
                        classID = classtable.getId();
                        ii = true;
                    }
                }
            }
            Pxbuxikechengtable buxi = new Pxbuxikechengtable();
            buxi.setStuID(oldbuxikc.getStuID());
            buxi.setKechengID(form.getKcID());

            long day = (NStartDate.getTime() - NEndDate.getTime()) / (24 * 3600 * 1000);
            BigDecimal days = new BigDecimal(day);
            if (form.getKcjifeistyleNew() == 3) {
                buxi.setKechengprice(rxuefeiOld.divide(days));
                buxi.setOriginalprice(l);
                buxi.setKeshiNum(days);
                buxi.setRemainkeshi(days);
            } else {
                buxi.setKechengprice(HKKCprice);
                buxi.setOriginalprice(HKKCprice);
                buxi.setKeshiNum(HKKS);
                buxi.setRemainkeshi(HKKS);
            }
            buxi.setBuykeshiDateTime(new Date());
            buxi.setIsShow(0);//补习课程为隐藏，即不启用
            buxi.setZongjia(rxuefeiOld);
            buxi.setStartDate(NStartDate);
            buxi.setEndDate(NEndDate);
            buxi.setJifeiStyleID(form.getKcjifeistyleNew());
            buxi.setType(oldbuxikc.getType());
            buxi.setQianDanInfoID(qdinfoNew.getId());
            buxi.setQianDanSubjectID(qdsubnew.getId());
            buxi.setQiyeID(qiyeID);
            if (oldbxlsTab.getType() == 2) {
                buxi.setType(2); //这门课是赠送的要换课，换课之后也该是赠送的（防止扣多学费）
            }
            iPxbuxikechengtableService.save(buxi);
            //记录新补习课程id
            newbxlsTab = buxi;

            //一对一才插班
            if (newkcBuxiStyleName.equals("一对一") && newis1v1 == 1) {
                Pxstuclasstable stuclass = new Pxstuclasstable();
                stuclass.setBuxiID(buxi.getId());
                stuclass.setClassID(classID);
                stuclass.setQiyeID(qiyeID);
                iPxstuclasstableService.save(stuclass);
            }
        } else {
            //没这个课，或者单价不同，添加一条记录
            Long classID = null;
            if (newkcBuxiStyleName.equals("一对一") && newis1v1 == 1) {
                boolean ii2 = false;
                while (ii2 == false) {
                    Random rd = new Random();
                    int sjs = rd.nextInt(999);
                    String className = stu.getStuName() + "_" + newkcSubjectName + "_一对一" + sjs;
                    List<Pxclasstable> pdclass = iPxclasstableService.FkClassName(className, Long.valueOf(qiyeID));
                    if (pdclass.size() == 0) {
                        Pxclasstable classtable = new Pxclasstable();
                        classtable.setClassName(className);
                        classtable.setCampusID(stu.getCampusID());
                        classtable.setIsShow(1);
                        classtable.setQiyeID(qiyeID);
                        classtable.setIs1v1Class(1);
                        classtable.setIsdelete(false);
                        classtable.setAddTime(new Date());
                        classtable.setAddStaffID(staffID);
                        classtable.setZidingyiClassID("");
                        classtable.setMaxStuNum(1);
                        classtable.setClassState(0);
                        iPxclasstableService.save(classtable);
                        classID = classtable.getId();
                        ii2 = true;
                    }
                }
            }
            Pxbuxikechengtable buxi = new Pxbuxikechengtable();
            buxi.setStuID(oldbuxikc.getStuID());
            buxi.setKechengID(form.getKcID());


            if (form.getKcjifeistyleNew() == 3) {
                buxi.setKechengprice(l);
                buxi.setOriginalprice(l);
                buxi.setKeshiNum(l);
                buxi.setRemainkeshi(l);
            } else {
                buxi.setKechengprice(HKKCprice);
                buxi.setOriginalprice(HKKCprice);
                buxi.setKeshiNum(HKKS);
                buxi.setRemainkeshi(HKKS);
            }

            buxi.setBuykeshiDateTime(new Date());
            buxi.setIsShow(1);
            buxi.setZongjia(rxuefeiOld);
            buxi.setStartDate(NStartDate);
            buxi.setEndDate(NEndDate);
            buxi.setJifeiStyleID(form.getKcjifeistyleNew());
            buxi.setType(oldbuxikc.getType());
            buxi.setQianDanInfoID(qdinfoNew.getId());
            buxi.setQianDanSubjectID(qdsubnew.getId());
            buxi.setQiyeID(qiyeID);
            if (oldbxlsTab.getType() == 2) {
                buxi.setType(2); //这门课是赠送的要换课，换课之后也该是赠送的（防止扣多学费）
            }
            iPxbuxikechengtableService.save(buxi);
            //记录新补习课程id
            newbxlsTab = buxi;

            //一对一才插班
            if (newkcBuxiStyleName.equals("一对一") && newis1v1 == 1) {
                Pxstuclasstable stuclass = new Pxstuclasstable();
                stuclass.setBuxiID(buxi.getId());
                stuclass.setClassID(classID);
                stuclass.setQiyeID(qiyeID);
                iPxstuclasstableService.save(stuclass);
            }

        }

        addupTab.setNewStuID(newbxlsTab.getStuID());
        addupTab.setNewbxkcID(newbxlsTab.getId());
        addupTab.setNewkcID(newbxlsTab.getKechengID());
        addupTab.setNewprice(newbxlsTab.getKechengprice());
        addupTab.setNewrkeshi(newbxlsTab.getRemainkeshi());
        addupTab.setNewzongjia(newbxlsTab.getZongjia());
        addupTab.setNewstartDate(newbxlsTab.getStartDate());
        addupTab.setNewendDate(newbxlsTab.getEndDate());
        addupTab.setNewqiandanID(qdinfoNew.getId());
        addupTab.setAddDate(new Date());
        addupTab.setAddStaffID(staffID);
        addupTab.setQiyeID(qiyeID);
        addupTab.setType(12);
        iPxbxkcchangetableService.save(addupTab);

        ajaxJson.setMsg("保存成功");
        return ajaxJson;
    }

    @ApiOperation("获取学员能跨的校区")
    @ResponseBody
    @RequestMapping(value = "getStuKxqCanCampus", method = RequestMethod.GET)
    @ApiImplicitParams(
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true)
    )
    public AjaxJson getStuKxqCanCampus(Long stuID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Pxstutable stu = iPxstutableService.getById(stuID);
        ajaxJson.setObj(iPxcampustableService.getStuKxqCanCampus(stu.getCampusID(), qiyeID));
        return ajaxJson;
    }


    /**
     * @Description: kxqbuxikcsave方法作用:跨校区上课设置
     * @param:[bxkcID, campusStrs]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:03
     */
    @ApiOperation("跨校区上课设置")
    @ResponseBody
    @RequestMapping(value = "kxqbuxikcsave", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson kxqbuxikcsave(@RequestBody kxqsettingFrom from, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());

        String bxkcID = from.getBuxiID();
        List<stuIDVo> CampusList = JSON.parseArray(from.getCampusStrs(), stuIDVo.class);

        Pxbuxikechengtable bxkcTab = iPxbuxikechengtableService.getById(bxkcID);
        Pxkechengtable kcTab = iPxkechengtableService.getById(bxkcTab.getKechengID());


        for (stuIDVo item : CampusList) {
            List<Pxstukxqtable> ishave = iPxstukxqtableService.getBystubx(bxkcTab.getStuID(), bxkcTab.getId(), Long.valueOf(item.getId()),
                    Long.valueOf(qiyeID));
            if (ishave.size() != 0) {
                ajaxJson.setMsg("该学生已设置过该校区");
                ajaxJson.setCode("N");
                return ajaxJson;
            }
        }
        for (stuIDVo it : CampusList) {
            Pxstukxqtable one = new Pxstukxqtable();
            one.setStuID(bxkcTab.getStuID());
            one.setBxkcID(bxkcTab.getId());
            one.setKcID(bxkcTab.getKechengID());
            one.setKxqCampusID(it.getId());
            one.setAddStaffID(staffID);
            one.setQiyeID(qiyeID);
            one.setAddTime(new Date());
            iPxstukxqtableService.save(one);

            //针对一对一添加跨校区，建班插班
            Pxbuxistyletable bxTab = iPxbuxistyletableService.getById(kcTab.getBuxiStyleID());
            Pxsubjecttable subTab = iPxsubjecttableService.getById(kcTab.getSubjectID());
            if (bxTab.getBuxiStyleName().equals("一对一") && bxTab.getIs1v1() == 1) {
                String classID = "";
                String stuName = iPxstutableService.getById(bxkcTab.getStuID()).getStuName();

                boolean ii = false;
                while (ii == false) {
                    Random rd = new Random();
                    int sjs = rd.nextInt(999);
                    String className = stuName + "_" + subTab.getSubjectName() + "跨校区_一对一" + sjs;
                    List<Pxclasstable> pdclass = iPxclasstableService.FkClassName(className, Long.valueOf(qiyeID));
                    if (pdclass.size() == 0) {
                        Pxclasstable classtable = new Pxclasstable();
                        classtable.setClassName(className);
                        classtable.setIsShow(1);
                        classtable.setQiyeID(qiyeID);
                        classtable.setIs1v1Class(1);
                        classtable.setMaxStuNum(1);
                        classtable.setClassState(0);
                        classtable.setAddTime(new Date());
                        classtable.setAddStaffID(staffID);
                        classtable.setZidingyiClassID("");
                        classtable.setCampusID(it.getId());
                        iPxclasstableService.save(classtable);

                        Pxstuclasstable stuclass = new Pxstuclasstable();
                        stuclass.setClassID(classtable.getId());
                        stuclass.setBuxiID(bxkcTab.getId());
                        stuclass.setQiyeID(qiyeID);
                        iPxstuclasstableService.save(stuclass);
                        ii = true;
                    }
                }
            }

        }
        ajaxJson.setMsg("保存成功");
        return ajaxJson;
    }


    //endregion

    //endregion

    //region 课程转送

    /**
     * @Description: getTransferPage方法作用:分页获取课程转送
     * @param:[current, size, songstuID, songcampus, songstu, shoucampus, shoustuID, shoustu, zhuansongDate, jingbanren]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:04
     */
    @ApiOperation("分页获取课程转送")
    @RequestMapping(value = "getTransferPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "songcampus", value = "转送人校区", required = false),
            @ApiImplicitParam(name = "songstuID", value = "转送人学号", required = false),
            @ApiImplicitParam(name = "songstu", value = "转送人", required = false),
            @ApiImplicitParam(name = "shoucampus", value = "接收人校区", required = false),
            @ApiImplicitParam(name = "shoustuID", value = "接收人学号", required = false),
            @ApiImplicitParam(name = "shoustu", value = "接收人", required = false),
            @ApiImplicitParam(name = "zhuansongDate", value = "转送时间", required = false),
            @ApiImplicitParam(name = "jingbanren", value = "处理人", required = false),
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson getTransferPage(
            Long current,
            Long size,
            String songstuID,
            String songcampus,
            String songstu,
            String shoucampus,
            String shoustuID,
            String shoustu,
            String zhuansongDate,
            String jingbanren,
            HttpServletRequest request
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<transferVo> page = new Page(current, size);
        QueryWrapper<transferVo> queryWrapper = new QueryWrapper<>();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(new Date());

        Calendar c = new GregorianCalendar();
        c.setTime(new Date());
        int toyear = c.get(Calendar.YEAR);
        int tomonth = c.get(Calendar.MONTH) + 1;

        queryWrapper.eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(songstuID)) {
            queryWrapper
                    .and(a -> a.isNotNull("stua.zidingyiStuID").like("stua.zidingyiStuID", songstuID))
                    .or(b -> b.isNull("stua.zidingyiStuID").eq("stua.id", songstuID));
        }
        if (StringUtils.isNotBlank(songcampus)) {
            queryWrapper.eq("cama.id", songcampus);
        }
        if (StringUtils.isNotBlank(songstu)) {
            queryWrapper.like("stua.stuName", songstu);
        }
        if (StringUtils.isNotBlank(songstuID)) {
            queryWrapper
                    .and(a -> a.isNotNull("stub.zidingyiStuID").like("stub.zidingyiStuID", shoustuID))
                    .or(b -> b.isNull("stub.zidingyiStuID").eq("stub.id", shoustuID));
        }
        if (StringUtils.isNotBlank(shoucampus)) {
            queryWrapper.eq("camb.id", shoucampus);
        }
        if (StringUtils.isNotBlank(shoustu)) {
            queryWrapper.like("stub.stuName", shoustu);
        }
        if (StringUtils.isNotBlank(zhuansongDate)) {
            if (zhuansongDate.equals("today")) {
                queryWrapper.eq(" (SELECT DATE_FORMAT(a.zhuansongDate,'%Y-%m-%d'))", today);
            } else if (zhuansongDate.equals("toyear")) {
                queryWrapper.eq("YEAR(a.zhuansongDate)", toyear);
            } else if (zhuansongDate.equals("tomonth")) {
                queryWrapper.eq("YEAR(a.zhuansongDate)", toyear).le("month(a.zhuansongDate)", tomonth).gt("month(a.zhuansongDate)", tomonth - 1);
            }

        }
        if (StringUtils.isNotBlank(jingbanren)) {
            queryWrapper.like("pxstafftable.staffName", jingbanren);
        }
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 222);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("stua.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("stua.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("stua.campusID", lookPower);
        }
        queryWrapper.orderByDesc("a.addtime");
        ajaxJson.setObj(iPxbuxikechengtableService.getTransfer(page, queryWrapper));
        return ajaxJson;
    }

    //region 课程转送的按钮集

    //endregion


    /**
     * @Description: ExportTransfe方法作用:导出课程转送
     * @param:[response, songcampusID, shoucampusID, startDate, endDate]
     * @return:void
     * @auter:yyl
     * @data:2020/11/30 16:06
     */
    @RequestMapping(value = "ExportTransfer", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出课程转送")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "songcampusID", value = "送学员校区ID", required = false),
            @ApiImplicitParam(name = "shoucampusID", value = "收学员校区ID", required = false),
            @ApiImplicitParam(name = "startDate", value = "开始时间", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束时间", required = false),
    })
    @Transactional(rollbackFor = Exception.class)
    public void ExportTransfe(HttpServletResponse response, HttpServletRequest request,
                              @RequestParam(required = false) String songcampusID, // 送学员校区ID
                              @RequestParam(required = false) String shoucampusID, // 收学员校区ID
                              @RequestParam(required = false) String startDate, // 开始日期
                              @RequestParam(required = false) String endDate // 结束日期
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        QueryWrapper<transferVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(songcampusID)) {
            queryWrapper.eq("stua.campusID", songcampusID);
        }
        if (StringUtils.isNotBlank(shoucampusID)) {
            queryWrapper.eq("stub.campusID", shoucampusID);
        }
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("a.zhuansongDate", startDate);
        }
        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("a.zhuansongDate", endDate);
        }
        List<transferVo> transferVos = iPxbuxikechengtableService.ExportTransfer(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"赠送人校区", "赠送人学号", "赠送人姓名", "赠送人课程", "送出课时", "送出剩余课时", "接收人校区",
                        "接收人学号", "接收人学员", "接收人课程", "收到课时", "收到剩余课时", "转送详情", "转送时间", "操作人"},
                transferVos,
                new String[]{"songcampus", "songstuID", "songstu", "songkc", "songKeshiNum", "songyukeshi", "shoucampus", "shoustuID",
                        "shoustu", "shoukc", "shouKeshi", "shouyukeshi", "shuoMing", "zhuansongDate-D", "jingbanren"});

        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "转送课时导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @ApiOperation(value = "获得转送课程")
    @ResponseBody()
    @RequestMapping(value = "getzhuangsongkecheng", method = RequestMethod.GET)
    public AjaxJson getzhuangsongkecheng(Long stuID, int type, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper<kechengVos> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("a.stuID", stuID)
                .eq("a.qiyeID", qiyeID)
                .ne("a.type", 2);//收的课程不能是赠送课程
        if (type == 1) {
            //送
            queryWrapper.gt("a.remainkeshi", 0);
        }
        ajaxJson.setObj(iPxbuxikechengtableService.getzhuangsongkecheng(queryWrapper));
        return ajaxJson;
    }

    @ApiOperation(value = "获得转送天课程")
    @ResponseBody()
    @RequestMapping(value = "getzhuangsongdaykecheng", method = RequestMethod.GET)
    public AjaxJson getzhuangsongdaykecheng(long stuID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        ajaxJson.setObj(iPxbuxikechengtableService.getzhuangsongdaykecheng(stuID, qiyeID));
        return ajaxJson;
    }


    @ApiOperation(value = "扩科转送获取课程")
    @ResponseBody()
    @RequestMapping(value = "getcamkecheng", method = RequestMethod.GET)
    public AjaxJson getcamkecheng(String stuID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Pxstutable stu = iPxstutableService.getById(stuID);
        ajaxJson.setObj(iPxbuxikechengtableService.getcamkecheng(stu.getCampusID(), qiyeID));
        return ajaxJson;
    }


    @ApiOperation(value = "获得赠送课程")
    @ResponseBody()
    @RequestMapping(value = "getZSkechengs", method = RequestMethod.GET)
    public AjaxJson getZSkechengs(String stuID, int type, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper<listVo> queryWrapper = new QueryWrapper<>();
        if (type == 3) {
            queryWrapper.eq("b.jifeiStyleID", type);
        } else {
            queryWrapper.eq("b.jifeiStyleID", 1).or(a -> a.eq("b.jifeiStyleID", 2));
        }
        ajaxJson.setObj(iPxbuxikechengtableService.getZSkechengs(stuID, qiyeID, queryWrapper));
        return ajaxJson;
    }

    @ApiOperation(value = "获得赠送的其他课程")
    @ResponseBody()
    @RequestMapping(value = "getOtherKechengs", method = RequestMethod.GET)
    public AjaxJson getOtherKechengs(String stuID, int type, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper<Pxkechengtable> queryWrapper = new QueryWrapper<>();
        if (type == 3) {
            queryWrapper.eq("jifeiStyleID", type);
        } else {
            queryWrapper.eq("jifeiStyleID", 1).or(a -> a.eq("jifeiStyleID", 2));
        }
        ajaxJson.setObj(iPxbuxikechengtableService.getOtherKechengs(stuID, qiyeID, queryWrapper));
        return ajaxJson;
    }


    /**
     * @Description: addKeShiZhs方法作用:添加课时转送
     * @param:[songstuID, zhuansongtype, songbuxikechengID, skeshi, shoustuID, shoubuxikechengID, shkeshi, shPrice, shuoming, stuxuefei]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:06
     */
    @ApiOperation(value = "课时转送")
    @ResponseBody()
    @RequestMapping(value = "addKeShiZhs", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "songstuID", value = "送学员ID", required = true),
            @ApiImplicitParam(name = "zhuansongtype", value = "课时转送方式：1按单价折算 2等课时转送 3扩科转送", required = true),
            @ApiImplicitParam(name = "songbuxikechengID", value = "转送补习课程", required = true),
            @ApiImplicitParam(name = "songkeshi", value = "转送课时", required = true),
            @ApiImplicitParam(name = "shoustuID", value = "收学员ID", required = true),
            @ApiImplicitParam(name = "shoubuxikechengID", value = "转送到的补习课程", required = true),
            @ApiImplicitParam(name = "shoukeshi", value = "收到课时", required = true),
            @ApiImplicitParam(name = "shouPrice", value = "单价", required = true),
            @ApiImplicitParam(name = "shuoming", value = "转送课时说明", required = true),
            @ApiImplicitParam(name = "xuefei", value = "学费", required = true)
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addKeShiZhs(@RequestBody addKeShiZhsForm form,
            HttpServletRequest request
    ) throws ParseException {
        BigDecimal f = new BigDecimal(-1);
        BigDecimal s = new BigDecimal(10);
        AjaxJson ajaxJson = new AjaxJson();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        Pxbuxikechengtable oldbxlsTab = new Pxbuxikechengtable();
        Pxbuxikechengtable newbxlsTab = new Pxbuxikechengtable();
        Pxstutable songstu = iPxstutableService.getById(form.getSongstuID());
        Pxstutable shoustu = iPxstutableService.getById(form.getShoustuID());
        String xiugaiInfo = "";
        String typel = "";

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();

        Pxbuxikechengtable songStubuxikecheng = iPxbuxikechengtableService.getById(form.getSongbuxikechengID());
        Pxkechengtable songkecheng = iPxkechengtableService.getById(songStubuxikecheng.getKechengID());
        Pxbuxikechengtable shouStubuxikecheng = null;
        Pxkechengtable shoukecheng = null;
        if (form.getZhuansongtype() == 1) {
            shouStubuxikecheng = iPxbuxikechengtableService.getById(form.getShoubuxikechengID());
            shoukecheng = iPxkechengtableService.getById(shouStubuxikecheng.getKechengID());
        } else if (form.getZhuansongtype() == 2) {
            shouStubuxikecheng = iPxbuxikechengtableService.getShoubxkc(form.getShoubuxikechengID(),form.getShPrice(), qiyeID).get(0);
            Pxbuxikechengtable sbxkc = iPxbuxikechengtableService.getById(form.getShoubuxikechengID());
            shoukecheng = iPxkechengtableService.getById(sbxkc.getKechengID());
        } else {
            //shouStubuxikecheng = iPxbuxikechengtableService.getById(-1);
            //shoubuxikechengID这里为扩科课程ID
            shoukecheng = iPxkechengtableService.getById(form.getShoubuxikechengID());
        }
        oldbxlsTab = songStubuxikecheng;
        newbxlsTab = shouStubuxikecheng;

        //添加操作纪录
        Pxbxkcchangetable addupTab = new Pxbxkcchangetable();
        addupTab.setOldStuID(oldbxlsTab.getStuID());
        addupTab.setOldkcID(oldbxlsTab.getKechengID());
        addupTab.setOldbxkcID(oldbxlsTab.getId());
        addupTab.setOldbxkcName(iPxkechengtableService.getById(oldbxlsTab.getKechengID()).getKechengName());
        addupTab.setOldqiandanID(oldbxlsTab.getQianDanInfoID());
        addupTab.setOldzongjia(oldbxlsTab.getZongjia());
        addupTab.setOldstartDate(oldbxlsTab.getStartDate());
        addupTab.setOldendDate(oldbxlsTab.getEndDate());
        addupTab.setOldprice(oldbxlsTab.getKechengprice());
        addupTab.setOldrkeshi(oldbxlsTab.getRemainkeshi());

        BigDecimal songkechengPrice = songStubuxikecheng.getKechengprice();
        BigDecimal songMoney =form.getSkeshi().multiply(songkechengPrice);
        xiugaiInfo += "（转送出学员）学号：" +form.getSongstuID() + ",姓名：" + songstu.getStuName() + ",送出课程：" + songkecheng.getKechengName() + ",送出课程单价：" + songkechengPrice.toString() + ",送出课时：" +form.getSkeshi()+ ",送出课程学费：" + songMoney.toString();
        if (form.getZhuansongtype() == 1) {
            typel = "(按单价折算)";
            xiugaiInfo += "，（接受转送学员）学号:" +form.getShoustuID() + ",姓名" + shoustu.getStuName() + ",接受转送课程" + shoukecheng.getKechengName() + ",接收转送课程单价:" + shouStubuxikecheng.getKechengprice().toString() + "，接收转送课时：" +form.getShkeshi().toString();
        } else if (form.getZhuansongtype() == 2) {
            typel = "(等课时转送)";
            xiugaiInfo += "，（接受转送学员）学号:" +form.getShoustuID() + ",姓名" + shoustu.getStuName() + ",接受转送课程" + shoukecheng.getKechengName() + ",接收转送课程单价:" +form.getShPrice().toString() + "，接收转送课时：" +form.getShkeshi().toString();
        } else {
            typel = "(扩科转送)";
            xiugaiInfo += "，（接受转送学员）学号:" +form.getShoustuID()+ ",姓名" + shoustu.getStuName() + ",接受转送课程" + shoukecheng.getKechengName() + ",接收转送课程单价:" +form.getShPrice().toString() + "，接收转送课时：" +form.getShkeshi().toString();
        }

        BigDecimal yhMoney = new BigDecimal(0);

        List<djqSumVo> songStuDJQ = iPxdaijinquantableService.getstudjqSum(form.getSongstuID(), Long.valueOf(qiyeID));
        if (songStuDJQ.size() > 0) {
            yhMoney.add(songStuDJQ.get(0).getSMoney());
        }
        //10.0有的优惠政策
        if (songStubuxikecheng.getQianDanInfoID() != null) {
            Pxqiandaninfotable songqiandanTab = iPxqiandaninfotableService.getById(songStubuxikecheng.getQianDanInfoID());
            if (songqiandanTab != null) {
                if (songqiandanTab.getYouhuijine() != null) {
                    String youhuijine = songqiandanTab.getYouhuijine();
                    BigDecimal youhuiJE = new BigDecimal(youhuijine);
                    yhMoney.add(youhuiJE);
                }
            }
        }
        if ((songstu.getRemainXuefei().intValue() - songMoney.intValue()) < yhMoney.intValue() &&form.getSongstuID() !=form.getShoustuID()) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ajaxJson.setMsg("该生的剩余学费减去代金券后不足以送出这么多学费！");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        //送出学员 剩余学费扣减、剩余课时扣减、
        songstu.setRemainXuefei(songstu.getRemainXuefei().subtract(songMoney));
        songStubuxikecheng.setRemainkeshi(songStubuxikecheng.getRemainkeshi().subtract(form.getSkeshi()));
        shoustu.setRemainXuefei(shoustu.getRemainXuefei().add(songMoney));
        xiugaiInfo += ",送出学员 剩余学费扣减" + songMoney.toString() + "送出课程剩余课时扣减" +form.getSkeshi().toString();
        iPxstutableService.updateById(songstu);
        iPxbuxikechengtableService.updateById(songStubuxikecheng);
        iPxstutableService.updateById(shoustu);

        //课时转送表添加记录
        Pxkeshizhuansongtable one = new Pxkeshizhuansongtable();
        one.setAddtime(new Date());
        one.setAdduser(staffID);
        one.setShoukechengID(shoukecheng.getId());
        one.setShouKeshi(form.getShkeshi());
        one.setShoustuID(form.getShoustuID());
        one.setShouXueFei(songMoney.multiply(f));
        one.setShuoMing(typel + "-" +form.getShuoming());
        one.setSongkechengID(songStubuxikecheng.getKechengID());
        one.setSongKeshiNum(form.getSkeshi());
        one.setSongstuID(form.getSongstuID());
        one.setSongXueFei(songMoney);
        one.setZhuansongDate(new Date());
        one.setZhuansongXinXi(xiugaiInfo);
        one.setQiyeID(qiyeID);
        one.setSongkechengPrice(form.getSongPrice());
        one.setShoukechengPrice(form.getShPrice());
        iPxkeshizhuansongtableService.save(one);

        //添加签单表的记录
        //送出
        Pxqiandaninfotable zsqian = new Pxqiandaninfotable();
        zsqian.setStuID(form.getSongstuID());
        zsqian.setQiandandate(new Date());
        zsqian.setShishouTotalMoney(songMoney.multiply(f));
        zsqian.setJiazhangDemand("");
        zsqian.setMoneyStyle(4);
        zsqian.setBeizhu("送出课程");
        zsqian.setQianDanStaffID(staffID);
        zsqian.setRecordInStaffID(staffID);
        zsqian.setRecordInTime(new Date());
        zsqian.setPayMoneyStyle(9L);
        zsqian.setZhuansongID(one.getId());
        zsqian.setCampusID(shoustu.getCampusID());
        zsqian.setIsdingjing(1);
        zsqian.setQiyeID(qiyeID);
        zsqian.setHetongMoney(songMoney.multiply(f));
        iPxqiandaninfotableService.save(zsqian);

        //送出签单科目
        Pxqiandansubjecttable qdsb = new Pxqiandansubjecttable();
        qdsb.setStuID(form.getSongstuID());
        qdsb.setQianDanInfoID(zsqian.getId());
        qdsb.setKechengID(songStubuxikecheng.getKechengID());
        qdsb.setKechengprice(songkechengPrice);
        qdsb.setKechengStyle(3);
        qdsb.setOriginalprice(songkechengPrice);
        qdsb.setQiandandate(new Date());
        qdsb.setBuykeshiNum(form.getSkeshi().multiply(f));
        qdsb.setQiyeID(qiyeID);
        qdsb.setZongjia(songMoney.multiply(f));
        qdsb.setDiscount(s);
        iPxqiandansubjecttableService.save(qdsb);

        //接收
        Pxqiandaninfotable jsqian = new Pxqiandaninfotable();
        jsqian.setStuID(form.getShoustuID());
        jsqian.setQiandandate(new Date());
        jsqian.setShishouTotalMoney(songMoney);
        jsqian.setJiazhangDemand("");
        jsqian.setMoneyStyle(4);
        jsqian.setBeizhu("接收转送课程");
        jsqian.setQianDanStaffID(staffID);
        jsqian.setRecordInStaffID(staffID);
        jsqian.setRecordInTime(new Date());
        jsqian.setPayMoneyStyle(9L);
        jsqian.setZhuansongID(one.getId());
        jsqian.setQiyeID(qiyeID);
        jsqian.setCampusID(shoustu.getCampusID());
        jsqian.setIsdingjing(1);
        jsqian.setHetongMoney(songMoney);
        iPxqiandaninfotableService.save(jsqian);

        //接收签单科目
        Pxqiandansubjecttable jsqdsb = new Pxqiandansubjecttable();
        jsqdsb.setStuID(form.getShoustuID());
        jsqdsb.setQianDanInfoID(jsqian.getId());
        jsqdsb.setKechengID(shoukecheng.getId());
        jsqdsb.setKechengprice(form.getShPrice());
        jsqdsb.setKechengStyle(2);
        jsqdsb.setOriginalprice(form.getShPrice());
        jsqdsb.setQiandandate(new Date());
        jsqdsb.setBuykeshiNum(form.getShkeshi());
        jsqdsb.setQiyeID(qiyeID);
        jsqdsb.setZongjia(songMoney);
        jsqdsb.setDiscount(s);
        iPxqiandansubjecttableService.save(jsqdsb);


        if (shouStubuxikecheng == null) {
            Date st = sdf.parse(form.getStartDate());
            Date et = sdf.parse(form.getEndDate());

            Pxbuxikechengtable copyStubuxikecheng = new Pxbuxikechengtable();
            if (form.getZhuansongtype() == 3) {
                copyStubuxikecheng = songStubuxikecheng;
            } else {
                copyStubuxikecheng = iPxbuxikechengtableService.getById(form.getShoubuxikechengID());
            }
            Pxbuxikechengtable newbuxi = new Pxbuxikechengtable();
            newbuxi.setBuykeshiDateTime(new Date());
            newbuxi.setEndDate(et);
            newbuxi.setIsShow(1);
            newbuxi.setJifeiStyleID(copyStubuxikecheng.getJifeiStyleID());
            newbuxi.setKechengID(shoukecheng.getId());
            newbuxi.setKechengprice(form.getShPrice());
            newbuxi.setKeshiNum(form.getShkeshi());
            newbuxi.setOriginalprice(form.getShPrice());
            newbuxi.setQianDanInfoID(jsqian.getId());
            newbuxi.setQianDanSubjectID(jsqdsb.getId());
            newbuxi.setRemainkeshi(form.getShkeshi());
            newbuxi.setStartDate(st);
            newbuxi.setStuID(form.getShoustuID());
            newbuxi.setType(3);
            newbuxi.setZongjia(songMoney);
            newbuxi.setQiyeID(qiyeID);
            iPxbuxikechengtableService.save(newbuxi);

            newbxlsTab = newbuxi;
            Pxkechengtable kechenginfo = iPxkechengtableService.getById(newbuxi.getKechengID());
            Pxbuxistyletable bxstyle = iPxbuxistyletableService.getById(kechenginfo.getBuxiStyleID());
            if (bxstyle.getBuxiStyleName().equals("一对一")) {
                Long nnclassID = null;
                Boolean ii = false;
                while (ii == false) {
                    Random rd = new Random();
                    int sjs = rd.nextInt(999);
                    String className = shoustu.getStuName() + "_" + shoukecheng.getKechengName() + "(扩科转送)_一对一" + sjs;
                    List<Pxclasstable> pdclass = iPxclasstableService.FkClassName(className, Long.valueOf(qiyeID));
                    if (pdclass.size() == 0) {
                        Pxclasstable classtable = new Pxclasstable();
                        classtable.setClassName(className);
                        classtable.setMaxStuNum(1);
                        classtable.setClassState(0);
                        classtable.setCampusID(shoustu.getCampusID());
                        classtable.setAddStaffID(staffID);
                        classtable.setAddTime(new Date());
                        classtable.setIsShow(1);
                        classtable.setIs1v1Class(1);
                        classtable.setQiyeID(qiyeID);
                        iPxclasstableService.save(classtable);
                        nnclassID = classtable.getId();
                        ii = true;
                    }
                }

                Pxstuclasstable stucla = new Pxstuclasstable();
                stucla.setBuxiID(newbuxi.getId());
                stucla.setClassID(nnclassID);
                stucla.setQiyeID(qiyeID);
                iPxstuclasstableService.save(stucla);
            }

        } else {
            //BigDecimal shouKechengPrice = shouStubuxikecheng.getKechengprice();
            shouStubuxikecheng.setRemainkeshi(shouStubuxikecheng.getRemainkeshi().add(form.getShkeshi()));
            iPxbuxikechengtableService.updateById(shouStubuxikecheng);
        }

        addupTab.setNewStuID(newbxlsTab.getStuID());
        addupTab.setNewbxkcID(newbxlsTab.getId());
        addupTab.setNewkcID(newbxlsTab.getKechengID());
        addupTab.setNewprice(newbxlsTab.getKechengprice());
        addupTab.setNewzongjia(newbxlsTab.getZongjia());
        addupTab.setNewstartDate(newbxlsTab.getStartDate());
        addupTab.setNewendDate(newbxlsTab.getEndDate());
        addupTab.setNewqiandanID(jsqian.getId());
        addupTab.setAddStaffID(staffID);
        addupTab.setAddDate(new Date());
        addupTab.setType(8);
        addupTab.setQiyeID(qiyeID);
        ajaxJson.setSuccess(iPxbxkcchangetableService.save(addupTab));
        return ajaxJson;
    }


    /**
     * @Description: addDayZhs方法作用:添加天转送
     * @param:[daysongstuID, daysongkechengID, sdays, daysprice, dayshoustuID, dayshoukechengID, dayshdays, dayshprice, dayshuoming, zhuansongdayType]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:07
     */
    @ApiOperation(value = "天转送")
    @ResponseBody()
    @RequestMapping(value = "addDayZhs", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addDayZhs(HttpServletRequest request,@RequestBody addDayZhsForm form) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();

        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();

        BigDecimal l = new BigDecimal(0);
        BigDecimal f = new BigDecimal(-1);
        BigDecimal s = new BigDecimal(10);

//        BigDecimal songdays = new BigDecimal(sdays);
        //BigDecimal daysongprice = new BigDecimal(daysprice);
//        BigDecimal dayshoudays = new BigDecimal(dayshdays);        //BigDecimal dayshouprice = new BigDecimal(dayshprice);

        Pxbuxikechengtable oldbxlsTab = new Pxbuxikechengtable();
        Pxbuxikechengtable newbxlsTab = new Pxbuxikechengtable();
        //送|收的学员
        Pxstutable songStu = iPxstutableService.getById(form.getDaysongstuID());
        Pxstutable shouStu = iPxstutableService.getById(form.getDayshoustuID());
        //送|收的补习课程
        Pxbuxikechengtable songbxkc = iPxbuxikechengtableService.getById(form.getDaysongbxkechengID());
        Pxbuxikechengtable shoubxkc = iPxbuxikechengtableService.getById(form.getDayshoubxkechengID());

        oldbxlsTab = songbxkc;
        newbxlsTab = shoubxkc;

        //送|收的课程
        Pxkechengtable songkecheng = iPxkechengtableService.getById(songbxkc.getKechengID());
        Pxkechengtable shoukecheng = iPxkechengtableService.getById(shoubxkc.getKechengID());

        String xiugaiInfo =
                "(转送学员)学员：" +form.getDaysongstuID() + ",姓名：" + songStu.getStuName() + ",送出课程《" + songkecheng.getKechengName() + "》" + form.getSdays().toString() +
                "天，（接收转送）学员：" +form.getDayshoustuID()+ ",姓名：" + shouStu.getStuName() + ",接收课程《" + shoukecheng.getKechengName() + "》" + form.getDayshdays() + "天";

        //增加课时转送表记录
        Pxkeshizhuansongtable one = new Pxkeshizhuansongtable();
        one.setAddtime(new Date());
        one.setAdduser(staffID);
        one.setShoukechengID(newbxlsTab.getKechengID());
        one.setShouKeshi(form.getDayshdays());
        one.setShoustuID(form.getDayshoustuID());
        one.setShouXueFei(l);
        one.setShuoMing(form.getDayshuoming());
        one.setSongkechengID(oldbxlsTab.getKechengID());
        one.setSongKeshiNum(form.getSdays());
        one.setSongstuID(form.getDaysongstuID());
        one.setSongXueFei(l);
        one.setZhuansongDate(new Date());
        one.setZhuansongXinXi(xiugaiInfo);
        one.setQiyeID(qiyeID);
        one.setSongkechengPrice(form.getDaysprice());
        one.setShoukechengPrice(form.getDayshprice());
        iPxkeshizhuansongtableService.save(one);

        //操作记录
        Pxbxkcchangetable addupTab = new Pxbxkcchangetable();
        addupTab.setOldStuID(oldbxlsTab.getStuID());
        addupTab.setOldkcID(oldbxlsTab.getKechengID());
        addupTab.setOldbxkcID(oldbxlsTab.getId());
        addupTab.setOldbxkcName(iPxkechengtableService.getById(oldbxlsTab.getKechengID()).getKechengName());
        addupTab.setOldqiandanID(oldbxlsTab.getQianDanInfoID());
        addupTab.setOldzongjia(oldbxlsTab.getZongjia());
        addupTab.setOldstartDate(oldbxlsTab.getStartDate());
        addupTab.setOldendDate(oldbxlsTab.getEndDate());
        addupTab.setOldprice(oldbxlsTab.getKechengprice());
        addupTab.setOldrkeshi(oldbxlsTab.getRemainkeshi());

        //添加签单表的记录
        //送出
        Pxqiandaninfotable zsqian = new Pxqiandaninfotable();
        zsqian.setStuID(songStu.getId());
        zsqian.setQiandandate(new Date());
        zsqian.setShishouTotalMoney((form.getSdays().multiply(form.getDaysprice())).multiply(f));
        zsqian.setHetongMoney((form.getSdays().multiply(form.getDaysprice())).multiply(f));
        zsqian.setJiazhangDemand("");
        zsqian.setMoneyStyle(4);
        zsqian.setBeizhu("天转送");
        zsqian.setQianDanStaffID(staffID);
        zsqian.setRecordInStaffID(staffID);
        zsqian.setRecordInTime(new Date());
        zsqian.setPayMoneyStyle(9L);
        zsqian.setZhuansongID(one.getId());
        zsqian.setCampusID(songStu.getCampusID());
        zsqian.setIsdingjing(1);
        zsqian.setQiyeID(qiyeID);
        iPxqiandaninfotableService.save(zsqian);

        Pxqiandansubjecttable qdsb = new Pxqiandansubjecttable();
        qdsb.setStuID(songStu.getId());
        qdsb.setQianDanInfoID(zsqian.getId());
        qdsb.setKechengID(songbxkc.getKechengID());
        qdsb.setKechengprice(form.getDayshdays());
        qdsb.setZongjia((form.getSdays().multiply(form.getDaysprice())).multiply(f));
        qdsb.setKechengStyle(3);
        qdsb.setOriginalprice(form.getDaysprice());
        qdsb.setQiandandate(new Date());
        qdsb.setBuykeshiNum(form.getSdays().multiply(f));
        qdsb.setQiyeID(qiyeID);
        qdsb.setDiscount(s);
        iPxqiandansubjecttableService.save(qdsb);

        //接收
        Pxqiandaninfotable jsqian = new Pxqiandaninfotable();
        jsqian.setStuID(shouStu.getId());
        jsqian.setQiandandate(new Date());
        jsqian.setShishouTotalMoney(form.getSdays().multiply(form.getDaysprice()));
        jsqian.setHetongMoney(form.getSdays().multiply(form.getDaysprice()));
        jsqian.setJiazhangDemand("");//家长要求
        jsqian.setMoneyStyle(4);
        jsqian.setBeizhu("接收转送天");
        jsqian.setQianDanStaffID(staffID);
        jsqian.setRecordInStaffID(staffID);
        jsqian.setRecordInTime(new Date());
        jsqian.setPayMoneyStyle(9L);
        jsqian.setZhuansongID(one.getId());
        jsqian.setIsdingjing(1);
        jsqian.setCampusID(shouStu.getCampusID());
        jsqian.setQiyeID(qiyeID);
        iPxqiandaninfotableService.save(jsqian);

        Pxqiandansubjecttable jsqdsb = new Pxqiandansubjecttable();
        jsqdsb.setStuID(shouStu.getId());
        jsqdsb.setQianDanInfoID(jsqian.getId());
        jsqdsb.setKechengID(shoubxkc.getKechengID());
        jsqdsb.setKechengprice(form.getDayshprice());
        jsqdsb.setKechengStyle(2);
        jsqdsb.setOriginalprice(form.getDayshprice());
        jsqdsb.setQiandandate(new Date());
        jsqdsb.setBuykeshiNum(form.getDayshdays());
        jsqdsb.setQiyeID(qiyeID);
        jsqdsb.setDiscount(s);
        jsqdsb.setZongjia(form.getDayshdays().multiply(form.getDaysprice()));
        iPxqiandansubjecttableService.save(jsqdsb);

        if (form.getZhuansongdayType() == 1) {
            if (songbxkc == null) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                ajaxJson.setMsg("送出学员的课程数据格式有误！");
                ajaxJson.setCode("N");
                return ajaxJson;
            } else {
                if (songbxkc.getEndDate() == null) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    ajaxJson.setMsg("送出学员的课程的结束日期有误！");
                    ajaxJson.setCode("N");
                    return ajaxJson;
                } else {
                    Date endDate = songbxkc.getEndDate();
                    Date NDate = new Date();
                    long songRemainDay = (endDate.getTime() - NDate.getTime()) / (24 * 3600 * 1000);
                    if (songRemainDay <form.getSdays().intValue()) {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        ajaxJson.setMsg("课程天数太少，不足以赠送!");
                        ajaxJson.setCode("N");
                        return ajaxJson;
                    } else {
                        //扣减送出学员的课程结束日期对应的天数
                        cal.setTime(endDate);
                        cal.add(Calendar.DATE, form.getSdays().intValue() * (-1));
                        songbxkc.setEndDate(cal.getTime());
                        iPxbuxikechengtableService.updateById(songbxkc);
                        songStu.setRemainXuefei(songStu.getRemainXuefei().subtract(form.getSdays().multiply(form.getDaysprice())));
                        iPxstutableService.updateById(songStu);
                        if (shoubxkc == null) {
                            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                            ajaxJson.setMsg("接收学员的课程数据格式有误");
                            ajaxJson.setCode("N");
                            return ajaxJson;
                        }
                        if (shoubxkc.getEndDate() == null) {
                            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                            ajaxJson.setMsg("接收学员的课程的结束日期有误!");
                            ajaxJson.setCode("N");
                            return ajaxJson;
                        } else {
                            Date endDate1 = shoubxkc.getEndDate();
                            cal.setTime(endDate1);
                            cal.add(Calendar.DATE,form.getDayshdays().intValue());
                            shoubxkc.setEndDate(cal.getTime());
                            iPxbuxikechengtableService.updateById(shoubxkc);
                            shouStu.setRemainXuefei(shouStu.getRemainXuefei().add(form.getDayshdays().multiply(form.getDayshprice())));
                            iPxstutableService.updateById(shouStu);
                        }
                    }
                }
            }
        } else {
            //扣减送出学员的课程结束日期对应的天数
            cal.setTime(songbxkc.getEndDate());
            cal.add(Calendar.DATE, form.getSdays().intValue() * (-1));
            songbxkc.setEndDate(cal.getTime());
            iPxbuxikechengtableService.updateById(songbxkc);
            songStu.setRemainXuefei(songStu.getRemainXuefei().subtract(form.getSdays().multiply(form.getDaysprice())));
            iPxstutableService.updateById(songStu);
            shouStu.setRemainXuefei(shouStu.getRemainXuefei().add(form.getSdays().multiply(form.getDayshprice())));
            iPxstutableService.updateById(shouStu);

            Pxbuxikechengtable newbuxi = new Pxbuxikechengtable();
            newbuxi.setBuykeshiDateTime(new Date());
            cal.setTime(shoubxkc.getEndDate());

            cal.add(Calendar.DATE, form.getDayshdays().intValue() + 1);
            newbuxi.setEndDate(cal.getTime());
            newbuxi.setIsShow(1);
            newbuxi.setJifeiStyleID(shoubxkc.getJifeiStyleID());
            newbuxi.setKechengID(shoubxkc.getKechengID());
            newbuxi.setKechengprice(form.getDayshprice());
            newbuxi.setKeshiNum(form.getDayshprice());
            newbuxi.setOriginalprice(form.getDayshdays());
            newbuxi.setQianDanInfoID(jsqian.getId());
            newbuxi.setQianDanSubjectID(jsqdsb.getId());
            newbuxi.setRemainkeshi(form.getDayshdays());
            cal.setTime(shoubxkc.getEndDate());
            cal.add(Calendar.DATE, 1);
            newbuxi.setStartDate(cal.getTime());
            newbuxi.setStuID(shouStu.getId());
            newbuxi.setType(3);
            newbuxi.setZongjia(form.getDayshdays().multiply(form.getDayshprice()));
            newbuxi.setQiyeID(qiyeID);
            iPxbuxikechengtableService.save(newbuxi);
            newbxlsTab = newbuxi;
        }

        addupTab.setNewStuID(newbxlsTab.getStuID());
        addupTab.setNewbxkcID(newbxlsTab.getId());
        addupTab.setNewkcID(newbxlsTab.getKechengID());
        addupTab.setNewprice(newbxlsTab.getKechengprice());
        addupTab.setNewrkeshi(newbxlsTab.getRemainkeshi());
        addupTab.setNewzongjia(newbxlsTab.getZongjia());
        addupTab.setNewstartDate(newbxlsTab.getStartDate());
        addupTab.setNewendDate(newbxlsTab.getEndDate());
        addupTab.setAddStaffID(staffID);
        addupTab.setAddDate(new Date());
        addupTab.setType(8);
        addupTab.setQiyeID(qiyeID);
        ajaxJson.setSuccess(iPxbxkcchangetableService.save(addupTab));
        return ajaxJson;
    }


    /**
     * @Description: delTransfer方法作用:删除转送记录
     * @param:[IDs]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:07
     */
    @RequestMapping(value = "delTransfer", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除转送")
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delTransfer(@RequestBody delzhuangsongFrom from, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        AjaxJson ajaxJson = new AjaxJson();
        Calendar cal = Calendar.getInstance();
        BigDecimal f = new BigDecimal(-1);
        String ids = from.getIds();
        if (ids != null) {
            List<stuIDVo> zhuangsongVoList = JSON.parseArray(ids, stuIDVo.class);
            for (stuIDVo item : zhuangsongVoList) {
                Pxkeshizhuansongtable kszs = iPxkeshizhuansongtableService.getById(item.getId());
                Pxstutable songstu = iPxstutableService.getById(kszs.getSongstuID());
                Pxstutable shoustu = iPxstutableService.getById(kszs.getShoustuID());
                Pxkechengtable songkecheng = iPxkechengtableService.getById(kszs.getShoukechengID());
                if (songkecheng.getJifeiStyleID() == 3) {
                    //按起止日期计费的课程
                    //先把送出课时的学员的天数还回去
                    List<Pxbuxikechengtable> songstubuxikcList = iPxbuxikechengtableService.getStubxkc(kszs.getSongstuID(), kszs.getSongkechengID(),
                            Long.valueOf(qiyeID));
                    if (songstubuxikcList.size() != 0) {
                        Pxbuxikechengtable songstubuxikcT = songstubuxikcList.get(0);
                        if (songstubuxikcT.getEndDate() == null) {
                            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                            ajaxJson.setMsg("送出学员的课程结束日期有误，不允许删除!");
                            ajaxJson.setCode("N");
                            return ajaxJson;
                        } else {
                            //退还天数
                            Date endDate = songstubuxikcT.getEndDate();
                            cal.setTime(endDate);
                            int backDays = kszs.getSongKeshiNum().intValue();//要返回的课时数
                            cal.add(Calendar.DATE, backDays);
                            songstubuxikcT.setEndDate(cal.getTime());
                            BigDecimal ks = new BigDecimal(backDays);
                            BigDecimal addks = songstubuxikcT.getRemainkeshi().add(ks);
                            songstubuxikcT.setRemainkeshi(addks);
                            iPxbuxikechengtableService.updateById(songstubuxikcT);
                        }
                    } else {
                        List<Pxbuxikechengtable> songstubuxikcList2 = iPxbuxikechengtableService.getStubxkc(kszs.getSongstuID(), kszs.getSongkechengID(),
                                Long.valueOf(qiyeID));
                        if (songstubuxikcList2.size() != 0) {
                            //退还天数
                            Pxbuxikechengtable songstubuxikcT2 = songstubuxikcList2.get(0);
                            Date endDate = songstubuxikcT2.getEndDate();
                            cal.setTime(endDate);
                            int backDays = kszs.getSongKeshiNum().intValue();
                            cal.add(Calendar.DATE, backDays);
                            songstubuxikcT2.setEndDate(cal.getTime());
                            BigDecimal ks = new BigDecimal(backDays);
                            songstubuxikcT2.setRemainkeshi(songstubuxikcT2.getRemainkeshi().add(ks));
                            iPxbuxikechengtableService.updateById(songstubuxikcT2);
                        } else {
                            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                            ajaxJson.setMsg("送出学员的课程已不存在，不允许删除!");
                            ajaxJson.setCode("N");
                            return ajaxJson;
                        }

                        //再把接收天数的学员，接收的天数减下来
                        List<Pxbuxikechengtable> shoustubuxikcTs = iPxbuxikechengtableService.getStubxkc(kszs.getShoustuID(), kszs.getShoukechengID(),
                                Long.valueOf(qiyeID));
                        if (shoustubuxikcTs.size() != 0) {
                            Pxbuxikechengtable shoustubuxikcT = shoustubuxikcTs.get(0);
                            if (shoustubuxikcT.getEndDate() == null) {
                                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                                ajaxJson.setMsg("送出学员的课程结束日期有误，不允许删除!");
                                ajaxJson.setCode("N");
                                return ajaxJson;
                            } else {
                                //接收的天数减下来
                                Date endDate = shoustubuxikcT.getEndDate();
                                cal.setTime(endDate);
                                cal.add(Calendar.DATE, kszs.getShouKeshi().intValue() * (-1));
                                shoustubuxikcT.setRemainkeshi(shoustubuxikcT.getRemainkeshi().subtract(kszs.getShouKeshi()));
                            }
                        } else {
                            List<Pxbuxikechengtable> stubxkcNoShow = iPxbuxikechengtableService.getStubxkcNoShow(kszs.getShoustuID(), kszs.getShoukechengID()
                                    , Long.valueOf(qiyeID));
                            if (stubxkcNoShow.size() != 0) {
                                //退还天数
                                Pxbuxikechengtable shoustubuxikcT2 = stubxkcNoShow.get(0);
                                Date endDate = shoustubuxikcT2.getEndDate();
                                cal.setTime(endDate);
                                cal.add(Calendar.DATE, kszs.getShouKeshi().intValue() * (-1));
                                shoustubuxikcT2.setEndDate(cal.getTime());
                            } else {
                                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                                ajaxJson.setMsg("送出学员的课程已不存在，不允许删除!");
                                ajaxJson.setCode("N");
                                return ajaxJson;
                            }
                        }

                    }
                } else {
                    //按课时或课时包计费的课程
                    //先把送出课时的学员的课时还回去；
                    BigDecimal songMoney = kszs.getSongXueFei();
                    List<Pxbuxikechengtable> stubxkcNoShow = iPxbuxikechengtableService.getStubxkcNoShow(kszs.getSongstuID(), kszs.getSongkechengID(),
                            Long.valueOf(qiyeID));
                    Pxbuxikechengtable songstubuxikc2 = null;
                    if (stubxkcNoShow.size() != 0) {
                        songstubuxikc2 = stubxkcNoShow.get(0);
                        songstubuxikc2.setRemainkeshi(songstubuxikc2.getRemainkeshi().add(kszs.getSongKeshiNum()));
                        songstu.setRemainXuefei(songstu.getRemainXuefei().add(songMoney));
                        iPxbuxikechengtableService.updateById(songstubuxikc2);
                        iPxstutableService.updateById(songstu);
                    } else {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        ajaxJson.setMsg("送出学员的课程已不存在，不允许删除!");
                        ajaxJson.setCode("N");
                        return ajaxJson;
                    }

                    //把接收学员的课时减下来
                    List<Pxbuxikechengtable> stubxkc = iPxbuxikechengtableService.getStubxkc(kszs.getShoustuID(), kszs.getShoukechengID(),
                            Long.valueOf(qiyeID));
                    if (stubxkc.size() != 0) {
                        Pxbuxikechengtable shoustubuxikc = stubxkc.get(0);
                        shoustubuxikc.setRemainkeshi(shoustubuxikc.getRemainkeshi().subtract(kszs.getShouKeshi()));//减去接收的课时
                        shoustu.setRemainXuefei(shoustu.getRemainXuefei().subtract(songMoney));
                        iPxbuxikechengtableService.updateById(shoustubuxikc);
                        iPxstutableService.updateById(shoustu);
                    } else {
                        List<Pxbuxikechengtable> stubxkcNoShow1 = iPxbuxikechengtableService.getStubxkcNoShow(kszs.getShoustuID(), kszs.getShoukechengID(),
                                Long.valueOf(qiyeID));
                        if (stubxkcNoShow1.size() != 0) {
                            Pxbuxikechengtable shoustubuxikc2 = stubxkcNoShow1.get(0);
                            shoustubuxikc2.setRemainkeshi(shoustubuxikc2.getRemainkeshi().subtract(kszs.getShouKeshi()));
                            shoustu.setRemainXuefei(shoustu.getRemainXuefei().subtract(songMoney));
                            iPxbuxikechengtableService.updateById(songstubuxikc2);
                            iPxstutableService.updateById(shoustu);
                        } else {
                            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                            ajaxJson.setMsg("接收学员的课程已不存在，不允许删除!");
                            ajaxJson.setCode("N");
                            return ajaxJson;
                        }
                    }

                    //删除qianDanInfoTable签单信息表里当时转送课时的时候添加的信息；
                    BigDecimal fsongMoney = songMoney.multiply(f);
                    List<Pxqiandaninfotable> pxqiandaninfotables = iPxqiandaninfotableService.getdelzsQD(kszs.getSongstuID(), fsongMoney, Long.valueOf(qiyeID));
                    if (pxqiandaninfotables.size() != 0) {
                        iPxqiandaninfotableService.removeById(pxqiandaninfotables.get(0).getId()); //删除送的签单
                    }
                    List<Pxqiandaninfotable> qiandaninfo2 = iPxqiandaninfotableService.getdelzsQD(kszs.getShoustuID(), songMoney, Long.valueOf(qiyeID));
                    if (qiandaninfo2.size() != 0) {
                        iPxqiandaninfotableService.removeById(qiandaninfo2.get(0).getId()); //删除收的签单
                    }

                    //删除qianDanSubjectTable签单课程表里当时转送课时的时候添加的信息；kechengStyle: 2 接受的赠送，3 送出的
                    BigDecimal fsongks = kszs.getSongKeshiNum().multiply(f);
                    List<Pxqiandansubjecttable> qiandansubject = iPxqiandansubjecttableService.getdelzsQdSub(3, kszs.getSongstuID(), kszs.getSongkechengID(),
                            fsongks, Long.valueOf(qiyeID));
                    if (qiandansubject.size() != 0) {
                        iPxqiandansubjecttableService.removeById(qiandansubject.get(0).getId()); //删除送的qianSubject
                    }
                    List<Pxqiandansubjecttable> qiandansubject2 = iPxqiandansubjecttableService.getdelzsQdSub(2, kszs.getShoustuID(), kszs.getShoukechengID()
                            , kszs.getShouKeshi(), Long.valueOf(qiyeID));
                    if (qiandansubject2.size() != 0) {
                        iPxqiandansubjecttableService.removeById(qiandansubject2.get(0).getId()); //删除接收的qianSubject
                    }
                }
                iPxkeshizhuansongtableService.removeById(kszs);
            }
        }
        ajaxJson.setMsg("删除成功");
        return ajaxJson;
    }
    //endregion

    //region 课程赠送


    /**
     * @Description: getZengSong方法作用:分页获取课程赠送
     * @param:[current, size, stuID, stuName, campusName, stuGradeName, kechengName, staffName, songYangyin, banzhuren]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:08
     */
    @ApiOperation(value = "分页获取课程赠送")
    @RequestMapping(value = "getZengSongPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "stuID", value = "学号", required = false),
            @ApiImplicitParam(name = "stuName", value = "学员姓名", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "stuGradeID", value = "年级", required = false),
    })
    public AjaxJson getZengSong(
            Long current,
            Long size,
            String stuID,
            String stuName,
            String campusID,
            String stuGradeID,
            HttpServletRequest request
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Page<zengsongVo> page = new Page<>(current, size);
        QueryWrapper<zengsongVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(stuID)) {
            queryWrapper
                    .and(a -> a.isNotNull("stu.zidingyiStuID").like("stu.zidingyiStuID", stuID))
                    .or(b -> b.isNull("stu.zidingyiStuID").eq("stu.id", stuID));
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("stu.stuName", stuName);
        }
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("stu.campusID", campusID);
        }
        if (StringUtils.isNotBlank(stuGradeID)) {
            queryWrapper.eq("pxstugradetable.id", stuGradeID);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 223);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("stu.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("stu.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("stu.campusID", lookPower);
        }
        queryWrapper.orderByDesc("a.id");
        ajaxJson.setObj(iPxbuxikechengtableService.getZengSong(page, queryWrapper));
        return ajaxJson;
    }

    //region 课程赠送的按钮


    /**
     * @Description: ExportZskeshi方法作用:导出课时赠送
     * @param:[response, campusID, startDate, endDate]
     * @return:void
     * @auter:yyl
     * @data:2020/11/30 16:09
     */
    @RequestMapping(value = "ExportZskeshi", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出课时赠送")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "startDate", value = "开始时间", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束时间", required = false),
    })
    public void ExportZskeshi(HttpServletResponse response, HttpServletRequest request,
                              @RequestParam(required = false) String campusID, // 校区ID
                              @RequestParam(required = false) String startDate, // 开始日期
                              @RequestParam(required = false) String endDate // 结束日期
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        QueryWrapper<zengsongVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("stu.campusID", campusID);
        }

        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("a.addDate", startDate);
        }

        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("a.addDate", endDate);
        }
        List<zengsongVo> zengsongVos = iPxbuxikechengtableService.ExportZengSong(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"学号", "姓名", "校区", "年级", "赠送课程", "课时数", "赠送原因",
                        "赠送时间", "经办人"},
                zengsongVos,
                new String[]{"stuID", "stuName", "campusName", "stuGradeName", "kechengName", "keshiShu", "songYangyin", "addDate-D", "staffName"});

        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "导出课时赠送.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @Description: delZsKeshi方法作用:删除赠送课时
     * @param:[IDs]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:09
     */
    @ApiOperation("删除赠送课时")
    @ResponseBody
    @RequestMapping(value = "delZsKeshi", method = RequestMethod.DELETE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "IDs", value = "赠送ID", required = true)
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delZsKeshi(String ids, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        Pxbuxikechengtable buxikc = new Pxbuxikechengtable();
        Pxbuxikechengtable oldbxlsTab = new Pxbuxikechengtable();
        Pxbuxikechengtable newbxlsTab = new Pxbuxikechengtable();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();
//        String ids = from.getIds();
        if (ids != null) {
//            List<stuIDVo> zengsongList = JSON.parseArray(ids, stuIDVo.class);
            for (String item : ids.split(",")) {
                Pxkeshizengsongtable kszs = iPxkeshizengsongtableService.getById(item);
                if (kszs.getJifeiStyle() == 3) {
                    buxikc = iPxbuxikechengtableService.getBxToStuAKc(kszs.getStuID(), kszs.getKechengID(), Long.valueOf(qiyeID)).get(0);
                } else {
                    buxikc = iPxbuxikechengtableService.getBxToType(kszs.getStuID(), kszs.getKechengID(), kszs.getKechengPrice(), Long.valueOf(qiyeID)).get(0);
                }
                oldbxlsTab = buxikc;

                Pxbxkcchangetable addupTab = new Pxbxkcchangetable();
                //添加操作记录
                if (oldbxlsTab != null) {
                    addupTab.setOldStuID(oldbxlsTab.getStuID());
                    addupTab.setOldkcID(oldbxlsTab.getKechengID());
                    addupTab.setOldbxkcID(oldbxlsTab.getId());
                    addupTab.setOldbxkcName(iPxkechengtableService.getById(oldbxlsTab.getKechengID()).getKechengName());
                    addupTab.setOldqiandanID(oldbxlsTab.getQianDanInfoID());
                    addupTab.setOldzongjia(oldbxlsTab.getZongjia());
                    addupTab.setOldstartDate(oldbxlsTab.getStartDate());
                    addupTab.setOldendDate(oldbxlsTab.getEndDate());
                    addupTab.setOldprice(oldbxlsTab.getKechengprice());
                    addupTab.setOldrkeshi(oldbxlsTab.getRemainkeshi());
                }
                if (buxikc != null) {
                    //Pxstutable stu = iPxstutableService.getById(kszs.getStuID());
                    Pxkechengtable kc = iPxkechengtableService.getById(kszs.getKechengID());
                    if (kc != null) {
                        if (kc.getJifeiStyleID() == 3) {
                            if (buxikc.getEndDate() == null) {
                                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                                ajaxJson.setMsg("学员按起止日期计费课程的结束日期为NULL，无法删除赠送!");
                                ajaxJson.setCode("N");
                                return ajaxJson;
                            } else {
                                Date endDate = buxikc.getEndDate();
                                //使用Canlender工具类来进行日期加减
                                Calendar cal = Calendar.getInstance();
                                cal.setTime(endDate); //设置起时间
                                //把decimal类型转为int型
                                BigDecimal keshiShu = kszs.getKeshiShu();
                                int ks = keshiShu.intValue() * (-1);
                                cal.add(Calendar.DATE, ks);//减去ks天
                                buxikc.setEndDate(cal.getTime());
                                iPxbuxikechengtableService.updateById(buxikc);
                                iPxkeshizengsongtableService.removeById(kszs);
                                newbxlsTab = buxikc;
                            }
                        } else {
                            //减去赠送的课时和学费
                            buxikc.setRemainkeshi(buxikc.getKeshiNum().subtract(kszs.getKeshiShu()));
                            buxikc.setKeshiNum(buxikc.getKeshiNum().subtract(kszs.getKeshiShu()));
                            iPxkeshizengsongtableService.removeById(kszs);
                        }
                        addupTab.setNewStuID(newbxlsTab.getStuID());
                        addupTab.setNewbxkcID(newbxlsTab.getId());
                        addupTab.setNewkcID(newbxlsTab.getKechengID());
                        addupTab.setNewprice(newbxlsTab.getKechengprice());
                        addupTab.setNewrkeshi(newbxlsTab.getRemainkeshi());
                        addupTab.setNewzongjia(newbxlsTab.getZongjia());
                        addupTab.setNewstartDate(newbxlsTab.getStartDate());
                        addupTab.setNewendDate(newbxlsTab.getEndDate());
                        addupTab.setAddStaffID(staffID);
                        addupTab.setAddDate(new Date());
                        addupTab.setQiyeID(qiyeID);
                        addupTab.setType(8);
                        ajaxJson.setSuccess(iPxbxkcchangetableService.save(addupTab));
                    } else {
                        //如果课程表里没有这个课
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        ajaxJson.setMsg("学员没有该课程了，无法删除赠送!");
                        ajaxJson.setCode("N");
                        return ajaxJson;
                    }
                } else {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    ajaxJson.setMsg("学员没有该课程了，无法删除赠送!");
                    ajaxJson.setCode("N");
                    return ajaxJson;
                }
            }
        }


        ajaxJson.setMsg("删除成功!");
        return ajaxJson;
    }


    /**
     * @Description: addZsKeShi方法作用:添加赠送课时
     * @param:[stuID, oldshoukechengID, newshoukechengID, skeshi, kcPrice, startDate, endDate, songYangyin]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:09
     */
    @ApiOperation(value = "赠送课时")
    @ResponseBody()
    @RequestMapping(value = "addZsKeShi", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "songstuID", value = "学员ID", required = true),
            @ApiImplicitParam(name = "zhuansongtype", value = "类型：1已有课程 2扩科课程", required = true),
            @ApiImplicitParam(name = "songbuxikechengID", value = "赠送课程", required = true),
            @ApiImplicitParam(name = "shoukechengID", value = "赠送到的课程", required = true),
            @ApiImplicitParam(name = "shoukeshi", value = "赠送课时", required = true),
            @ApiImplicitParam(name = "otherkcPrice", value = "单价", required = true),
            @ApiImplicitParam(name = "startDate", value = "开始日期", required = true),
            @ApiImplicitParam(name = "endDate", value = "结束日期", required = true),
            @ApiImplicitParam(name = "songYangyin", value = "赠送说明", required = true)
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addZsKeShi( HttpServletRequest request,@RequestBody addZsKeShiForm form) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());

        BigDecimal shoukeshi = new BigDecimal(form.getSkeshi());
        BigDecimal otherkcPrice = new BigDecimal(form.getKcPrice());
        Pxstutable stu = iPxstutableService.getById(form.getSongstuID());

        if (stu == null) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ajaxJson.setMsg("学员不存在");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        if (shoukeshi.intValue() < 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ajaxJson.setMsg("课时数太少");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
//        if (StringUtils.isNotBlank(songYangyin)) {
//            ajaxJson.setMsg("请填写赠送原因");
//            return ajaxJson;
//        }

        BigDecimal l = new BigDecimal(0);
        Pxbuxikechengtable oldbxlsTab = new Pxbuxikechengtable();
        Pxbuxikechengtable newbxlsTab = new Pxbuxikechengtable();
        Pxbuxikechengtable buxikecheng = null;

        if (form.getSongbuxikechengID() != null) {
            Long buxiID =form.getSongbuxikechengID();
            List<Pxbuxikechengtable> bxList = iPxbuxikechengtableService.getByIDAStuID(buxiID,form.getSongstuID(), Long.valueOf(qiyeID));
            if (bxList.size() > 0) {
                buxikecheng = bxList.get(0);
                Pxkechengtable kecheng = iPxkechengtableService.getById(buxikecheng.getKechengID());
                if (kecheng == null || buxikecheng == null) {
                    ajaxJson.setMsg("课程不存在");
                    ajaxJson.setCode("N");
                    return ajaxJson;
                }
                Pxkeshizengsongtable keshiZS = new Pxkeshizengsongtable();
                keshiZS.setKechengID(buxikecheng.getKechengID());
                keshiZS.setKechengPrice(buxikecheng.getKechengprice());
                keshiZS.setKeshiShu(shoukeshi);
                keshiZS.setSongYangyin(form.getSongYangyin());
                keshiZS.setStuID(form.getSongstuID());
                keshiZS.setAddDate(new Date());
                keshiZS.setCaozuoStaffId(staffID);
                keshiZS.setQiyeID(qiyeID);
                keshiZS.setJifeiStyle(buxikecheng.getJifeiStyleID());
                iPxkeshizengsongtableService.save(keshiZS); //保存一天赠送记录

                String subjectName = iPxsubjecttableService.getById(kecheng.getSubjectID()).getSubjectName();
                String buxiStyleName = iPxbuxistyletableService.getById(kecheng.getBuxiStyleID()).getBuxiStyleName();
                Integer is1v1 = iPxbuxistyletableService.getById(kecheng.getBuxiStyleID()).getIs1v1();
                Long newbuxiID = null;
                List<Pxbuxikechengtable> pdbuxikclist = iPxbuxikechengtableService.getBxToType(form.getSongstuID(), buxikecheng.getKechengID(),
                        buxikecheng.getKechengprice(), Long.valueOf(qiyeID));
                if (pdbuxikclist.size() != 0) {
                    Pxbuxikechengtable pdbuxikc = pdbuxikclist.get(0);
                    oldbxlsTab.setStuID(pdbuxikc.getStuID());
                    oldbxlsTab.setKechengID(pdbuxikc.getKechengID());
                    oldbxlsTab.setId(pdbuxikc.getId());
                    oldbxlsTab.setQianDanInfoID(pdbuxikc.getQianDanInfoID());
                    oldbxlsTab.setZongjia(pdbuxikc.getZongjia());
                    oldbxlsTab.setStartDate(pdbuxikc.getStartDate());
                    oldbxlsTab.setEndDate(pdbuxikc.getEndDate());
                    oldbxlsTab.setKechengprice(pdbuxikc.getKechengprice());
                    oldbxlsTab.setRemainkeshi(pdbuxikc.getRemainkeshi());
                    oldbxlsTab.setQiyeID(qiyeID);
                    //若补习课程表内有过赠送课时，便只添加课时
                    pdbuxikc.setRemainkeshi(pdbuxikc.getRemainkeshi().add(shoukeshi));
                    pdbuxikc.setKeshiNum(pdbuxikc.getKeshiNum().add(shoukeshi));

                    newbxlsTab = pdbuxikc;

                } else {
                    Pxbuxikechengtable onebx = new Pxbuxikechengtable();
                    onebx.setStuID(buxikecheng.getStuID());
                    onebx.setKechengID(buxikecheng.getKechengID());
                    onebx.setKechengprice(buxikecheng.getKechengprice());
                    onebx.setOriginalprice(buxikecheng.getOriginalprice());
                    onebx.setRemainkeshi(shoukeshi);
                    onebx.setKeshiNum(shoukeshi);
                    onebx.setZongjia(l);
                    onebx.setStartDate(buxikecheng.getStartDate());
                    onebx.setEndDate(buxikecheng.getEndDate());
                    onebx.setBuykeshiDateTime(new Date());
                    onebx.setIsShow(1);
                    onebx.setJifeiStyleID(buxikecheng.getJifeiStyleID());
                    onebx.setType(2);
                    onebx.setQiyeID(qiyeID);
                    iPxbuxikechengtableService.save(onebx);
                    newbuxiID = onebx.getId();
                    oldbxlsTab = onebx;
                    newbxlsTab = onebx;
                    Long classID = null;
                    if (buxiStyleName.equals("一对一")) {
                        //建班插班
                        boolean ii = false;
                        while (ii == false) {
                            Random rd = new Random();
                            int sjs = rd.nextInt(999);
                            String className = stu.getStuName() + "_" + subjectName + "_一对一" + sjs;
                            List<Pxclasstable> pdclass = iPxclasstableService.FkClassName(className, Long.valueOf(qiyeID));
                            if (pdclass.size() == 0) {
                                Pxclasstable classtable = new Pxclasstable();
                                classtable.setClassName(className);
                                classtable.setMaxStuNum(1);
                                classtable.setClassState(0);
                                classtable.setCampusID(stu.getCampusID());
                                classtable.setIs1v1Class(1);
                                classtable.setAddTime(new Date());
                                classtable.setAddStaffID(staffID);
                                classtable.setIsShow(1);
                                classtable.setQiyeID(qiyeID);
                                iPxclasstableService.save(classtable);
                                classID = classtable.getId();
                                ii = true;
                            }
                        }

                        Pxstuclasstable stucla = new Pxstuclasstable();
                        stucla.setQiyeID(qiyeID);
                        stucla.setClassID(classID);
                        stucla.setBuxiID(newbuxiID);
                        iPxstuclasstableService.save(stucla);
                    }
                }
            }
        } else {
            //赠送的是这个学生没有的课程
            //传的是新课程ID
            List<Pxbuxikechengtable> ckt = iPxbuxikechengtableService.getByKechengID(form.getShoukechengID(), Long.valueOf(qiyeID));
            Pxkechengtable kechengTab = iPxkechengtableService.getById(form.getShoukechengID());
            buxikecheng = new Pxbuxikechengtable();
            buxikecheng.setBuykeshiDateTime(new Date());
            buxikecheng.setIsShow(0);
            buxikecheng.setJifeiStyleID(kechengTab.getJifeiStyleID());
            buxikecheng.setKechengID(form.getShoukechengID());
            buxikecheng.setKechengprice(otherkcPrice);
            buxikecheng.setKeshiNum(shoukeshi);
            buxikecheng.setOriginalprice(otherkcPrice);
            buxikecheng.setRemainkeshi(shoukeshi);
            buxikecheng.setStuID(form.getSongstuID());
            buxikecheng.setType(2);
            buxikecheng.setZongjia(l);
            buxikecheng.setQiyeID(qiyeID);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            //把字符变量转为日类型 Java中字符串类型与日期类型的互相转化
            Date STime = null;
            try {
                STime = df.parse(form.getStartDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            buxikecheng.setStartDate(STime);

            Date ETime = null;
            try {
                ETime = df.parse(form.getEndDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            buxikecheng.setEndDate(ETime);
            iPxbuxikechengtableService.save(buxikecheng);
            oldbxlsTab = buxikecheng;
            newbxlsTab = buxikecheng;

            Pxkeshizengsongtable keshizengsong = new Pxkeshizengsongtable();
            keshizengsong.setKechengID(buxikecheng.getKechengID());
            keshizengsong.setKechengPrice(buxikecheng.getKechengprice());
            keshizengsong.setKeshiShu(shoukeshi);
            keshizengsong.setSongYangyin(form.getSongYangyin());
            keshizengsong.setStuID(form.getSongstuID());
            keshizengsong.setAddDate(new Date());
            keshizengsong.setCaozuoStaffId(staffID);
            keshizengsong.setQiyeID(qiyeID);
            iPxkeshizengsongtableService.save(keshizengsong);

            //插记录
            String buxiStyleName = iPxbuxistyletableService.getById(kechengTab.getBuxiStyleID()).getBuxiStyleName();
            Integer is1v1 = iPxbuxistyletableService.getById(kechengTab.getBuxiStyleID()).getIs1v1();
            if (buxiStyleName.equals("一对一") && is1v1 == 1) {
                //建班插班
                Long classID = null;
                String subjectName = iPxsubjecttableService.getById(kechengTab.getSubjectID()).getSubjectName();
                boolean ii = false;
                while (ii == false) {
                    Random rd = new Random();
                    int sjs = rd.nextInt(999);
                    String className = stu.getStuName() + "_" + subjectName + "_一对一" + sjs;
                    List<Pxclasstable> pdclass = iPxclasstableService.FkClassName(className, Long.valueOf(qiyeID));
                    if (pdclass.size() == 0) {
                        Pxclasstable classtable = new Pxclasstable();
                        classtable.setClassName(className);
                        classtable.setMaxStuNum(1);
                        classtable.setCampusID(stu.getCampusID());
                        classtable.setIs1v1Class(1);
                        classtable.setAddTime(new Date());
                        classtable.setAddStaffID(staffID);
                        classtable.setIsShow(1);
                        classtable.setQiyeID(qiyeID);
                        classtable.setClassState(0);
                        iPxclasstableService.save(classtable);
                        classID = classtable.getId();
                        ii = true;
                    }
                }
                //如果是一对一，要插班
                Pxstuclasstable stucla = new Pxstuclasstable();
                stucla.setQiyeID(qiyeID);
                stucla.setClassID(classID);
                stucla.setBuxiID(buxikecheng.getId());
                iPxstuclasstableService.save(stucla);
            }
        }
        //操作记录
        Pxbxkcchangetable addupTab = new Pxbxkcchangetable();
        addupTab.setOldStuID(oldbxlsTab.getStuID());
        addupTab.setOldkcID(oldbxlsTab.getKechengID());
        addupTab.setOldbxkcID(oldbxlsTab.getId());
        addupTab.setOldbxkcName(iPxkechengtableService.getById(oldbxlsTab.getKechengID()).getKechengName());
        addupTab.setOldqiandanID(oldbxlsTab.getQianDanInfoID());
        addupTab.setOldzongjia(oldbxlsTab.getZongjia());
        addupTab.setOldstartDate(oldbxlsTab.getStartDate());
        addupTab.setOldendDate(oldbxlsTab.getEndDate());
        addupTab.setOldprice(oldbxlsTab.getKechengprice());
        addupTab.setOldrkeshi(oldbxlsTab.getRemainkeshi());
        addupTab.setNewStuID(newbxlsTab.getStuID());
        addupTab.setNewbxkcID(newbxlsTab.getId());
        addupTab.setNewkcID(newbxlsTab.getKechengID());
        addupTab.setNewprice(newbxlsTab.getKechengprice());
        addupTab.setNewrkeshi(newbxlsTab.getRemainkeshi());
        addupTab.setNewzongjia(newbxlsTab.getZongjia());
        addupTab.setNewstartDate(newbxlsTab.getStartDate());
        addupTab.setNewendDate(newbxlsTab.getEndDate());
        addupTab.setAddStaffID(staffID);
        addupTab.setAddDate(new Date());
        addupTab.setType(9);
        addupTab.setQiyeID(qiyeID);
        iPxbxkcchangetableService.save(addupTab);
        ajaxJson.setMsg("保存成功");

        return ajaxJson;
    }


    /**
     * @Description: addDay方法作用:添加赠送天
     * @param:[shoustuIDT, shoukechengIDT, shuomingT, skeshiT, newshoukechengIDT, otherStartDate, otherEndDate]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:10
     */
    @ApiOperation(value = "赠送天")
    @ResponseBody()
    @RequestMapping(value = "addZsDay", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "songstuID", value = "学员ID", required = true),
            @ApiImplicitParam(name = "zhuansongtype", value = "类型：1已有课程 2其他课程", required = true),
            @ApiImplicitParam(name = "songbuxikechengID", value = "赠送课程", required = true),
            @ApiImplicitParam(name = "shoukechengID", value = "赠送到的课程", required = true),
            @ApiImplicitParam(name = "skeshiT", value = "赠送天", required = true),
            @ApiImplicitParam(name = "otherStartDate", value = "开始日期", required = false),
            @ApiImplicitParam(name = "otherEndDate", value = "结束日期", required = false),
            @ApiImplicitParam(name = "songYangyin", value = "赠送说明", required = true)
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addDay( HttpServletRequest request,@RequestBody addDayForm form) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();

//        BigDecimal shoukeshiT = new BigDecimal(skeshiT);
        BigDecimal l = new BigDecimal(0);
        Pxstutable stu = iPxstutableService.getById(form.getSongstuID());
        if (stu == null) {
            ajaxJson.setMsg("学员不存在");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        if (form.getSkeshi().intValue() < 0) {
            ajaxJson.setMsg("赠送天数应该大于0");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
//        if (StringUtils.isNotBlank(songYangyin)) {
//            ajaxJson.setMsg("请填写赠送原因");
//        }
        Pxbuxikechengtable oldbxlsTab = new Pxbuxikechengtable();
        Pxbuxikechengtable newbxlsTab = new Pxbuxikechengtable();
        if (form.getShoukechengID() == null) {
            List<Pxbuxikechengtable> buxilist = iPxbuxikechengtableService.getJF3(form.getSongbuxikechengID(),form.getSongstuID(), Long.valueOf(qiyeID));
            if (buxilist.size() != 0) {
                Pxbuxikechengtable buxikecheng = buxilist.get(0);
                oldbxlsTab = buxikecheng;
                Pxkechengtable kecheng = iPxkechengtableService.getById(buxikecheng.getKechengID());
                if (kecheng == null || buxikecheng == null) {
                    ajaxJson.setMsg("课程不存在");
                    ajaxJson.setCode("N");
                    return ajaxJson;
                }

                Pxkeshizengsongtable keshizengsong = new Pxkeshizengsongtable();
                keshizengsong.setKechengID(buxikecheng.getKechengID());
                keshizengsong.setKeshiShu(form.getSkeshi());
                keshizengsong.setSongYangyin(form.getSongYangyin());
                keshizengsong.setStuID(form.getSongstuID());
                keshizengsong.setAddDate(new Date());
                keshizengsong.setCaozuoStaffId(staffID);
                keshizengsong.setJifeiStyle(3);
                keshizengsong.setQiyeID(qiyeID);
                keshizengsong.setJifeiStyle(buxikecheng.getJifeiStyleID());
                keshizengsong.setKechengPrice(buxikecheng.getKechengprice());
                iPxkeshizengsongtableService.save(keshizengsong);

                Date endDate = buxikecheng.getEndDate();
                Calendar cal = Calendar.getInstance();
                cal.setTime(endDate); //设置起时间
                cal.add(Calendar.DATE,form.getSkeshi().intValue());//加上赠送的时间
                buxikecheng.setEndDate(cal.getTime());
                iPxbuxikechengtableService.updateById(buxikecheng);
                newbxlsTab = buxikecheng;
            }
        } else {
            //赠送的是这个学生没有的课程
            if (form.getShoukechengID() == null) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                ajaxJson.setMsg("请选择课程");
                ajaxJson.setCode("N");
                return ajaxJson;
            }
            if (!StringUtils.isNotBlank(form.getStartDate())) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                ajaxJson.setMsg("请填写开始日期");
                ajaxJson.setCode("N");
                return ajaxJson;
            }
            if (!StringUtils.isNotBlank(form.getEndDate())) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                ajaxJson.setMsg("请填写结束日期");
                ajaxJson.setCode("N");
                return ajaxJson;
            }
            // -----------------------------字符对象转日期对象--------------------------------
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            Date StartDate = null;
            Date EndDate = null;
            try {
                StartDate = df.parse(form.getStartDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                EndDate = df.parse(form.getEndDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // -------------------------------日期对象比较大小------------------------------
            if (StartDate.getTime() > EndDate.getTime()) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                ajaxJson.setMsg("开始时间大于结束时间");
                ajaxJson.setCode("N");
                return ajaxJson;
            }

            Pxkechengtable kecheng = iPxkechengtableService.getById(form.getShoukechengID());
            Pxbuxikechengtable buxikecheng = new Pxbuxikechengtable();
            buxikecheng.setBuykeshiDateTime(new Date());
            buxikecheng.setEndDate(EndDate);
            buxikecheng.setIsShow(0);
            buxikecheng.setJifeiStyleID(kecheng.getJifeiStyleID());
            buxikecheng.setKechengID(form.getShoukechengID());
            buxikecheng.setKechengprice(l);
            buxikecheng.setKeshiNum(l);
            buxikecheng.setOriginalprice(l);
            buxikecheng.setRemainkeshi(l);
            buxikecheng.setStartDate(StartDate);
            buxikecheng.setStuID(form.getSongstuID());
            buxikecheng.setType(2);
            buxikecheng.setZongjia(l);
            buxikecheng.setQiyeID(qiyeID);

            iPxbuxikechengtableService.save(buxikecheng);
            oldbxlsTab = buxikecheng;
            newbxlsTab = buxikecheng;

            long days = (StartDate.getTime() - EndDate.getTime()) / (24 * 3600 * 1000);
            BigDecimal Ds = new BigDecimal(days);
            Pxkeshizengsongtable keshizengsong = new Pxkeshizengsongtable();
            keshizengsong.setQiyeID(qiyeID);
            keshizengsong.setAddDate(new Date());
            keshizengsong.setCaozuoStaffId(staffID);
            keshizengsong.setKechengID(buxikecheng.getKechengID());
            keshizengsong.setKechengPrice(l);
            keshizengsong.setKeshiShu(Ds);
            keshizengsong.setSongYangyin(form.getSongYangyin());
            keshizengsong.setStuID(form.getSongstuID());
            keshizengsong.setJifeiStyle(3);
        }

        Pxbxkcchangetable addupTab = new Pxbxkcchangetable();
        addupTab.setOldStuID(oldbxlsTab.getStuID());
        addupTab.setOldkcID(oldbxlsTab.getKechengID());
        addupTab.setOldbxkcID(oldbxlsTab.getId());
        addupTab.setOldbxkcName(iPxkechengtableService.getById(oldbxlsTab.getKechengID()).getKechengName());
        addupTab.setOldqiandanID(oldbxlsTab.getQianDanInfoID());
        addupTab.setOldzongjia(oldbxlsTab.getZongjia());
        addupTab.setOldstartDate(oldbxlsTab.getStartDate());
        addupTab.setOldendDate(oldbxlsTab.getEndDate());
        addupTab.setOldprice(oldbxlsTab.getKechengprice());
        addupTab.setOldrkeshi(oldbxlsTab.getRemainkeshi());
        addupTab.setNewStuID(newbxlsTab.getStuID());
        addupTab.setNewbxkcID(newbxlsTab.getId());
        addupTab.setNewkcID(newbxlsTab.getKechengID());
        addupTab.setNewprice(newbxlsTab.getKechengprice());
        addupTab.setNewrkeshi(newbxlsTab.getRemainkeshi());
        addupTab.setNewzongjia(newbxlsTab.getZongjia());
        addupTab.setNewstartDate(newbxlsTab.getStartDate());
        addupTab.setNewendDate(newbxlsTab.getEndDate());
        addupTab.setAddStaffID(staffID);
        addupTab.setAddDate(new Date());
        addupTab.setQiyeID(qiyeID);
        addupTab.setType(9);
        iPxbxkcchangetableService.save(addupTab);
        ajaxJson.setMsg("保存成功");
        return ajaxJson;
    }

    //endregion

    //endregion

    //region 课程变动流水


    /**
     * @Description: getkcLiuShuiPage方法作用:分页获取课程变动流水
     * @param:[current, size, type, stuName, campusName, newStu]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:10
     */
    @ApiOperation(value = "分页获取课程变动流水")
    @RequestMapping(value = "getkcLiuShuiPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "type", value = "变动类型 -1：全部类型", example = "-1", required = false),
            @ApiImplicitParam(name = "stuName", value = "学员姓名", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "newStu", value = "（新）学员名字", required = false),

    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson getkcLiuShuiPage(
            Long current,
            Long size,
            int type,
            String stuName,
            String campusID,
            String newStu,
            HttpServletRequest request
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<bxChangeVo> page = new Page<>(current, size);
        QueryWrapper<bxChangeVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("kccg.qiyeID", qiyeID);
        if (type != -1) {
            queryWrapper.eq("kccg.type", type);
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("stu.stuName", stuName);
        }
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("stu.campusID", campusID);
        }
        if (StringUtils.isNotBlank(newStu)) {
            queryWrapper.like("(select stuName from pxstutable where id=kccg.newStuID)", newStu);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 224);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("stu.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("stu.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("stu.campusID", lookPower);
        }

        ajaxJson.setObj(iPxbuxikechengtableService.getkcLiuShui(page, queryWrapper));
        return ajaxJson;
    }

    //region 课程变动流水的按钮


    /**
     * @Description: exportFeedback方法作用:导出课程变动流水
     * @param:[response]
     * @return:void
     * @auter:yyl
     * @data:2020/11/30 16:11
     */
    @RequestMapping(value = "ExportkcLiuShui", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出课程变动流水")
    public void exportFeedback(HttpServletResponse response, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        List<bxChangeVo> bxChangeVos = iPxbuxikechengtableService.ExportkcLiuShui(qiyeID);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "学员姓名", "变动类型", "原课程", "原单价", "原课时", "原总价", "原开始时间", "原结束时间", "新学员", "新课程",
                        "新单价", "新课时", "新总价", "新开始时间", "新结束时间", "操作人", "操作时间"},
                bxChangeVos,
                new String[]{"campusName", "oldStu", "type", "oldkechengName", "oldprice", "oldrkeshi", "oldzongjia", "oldstartDate-D", "oldendDate-D",
                        "newStu", "newkechengName", "newprice", "newrkeshi", "newzongjia", "newstartDate-D", "newendDate-D", "jingbanren", "addDate-D"});

        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "课程变动流水导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //endregion
    //endregion

    //region 成绩


    /**
     * @Description: getScorePage方法作用:分页获取学员成绩
     * @param:[current, size, stuName, campusName, scoreType]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:11
     */
    @ApiOperation(value = "分页获取学员成绩")
    @RequestMapping(value = "getScorePage", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "scoreType", value = "成绩类型 0:培训机构成绩 1:在校成绩", example = "-1", required = false),
            @ApiImplicitParam(name = "stuName", value = "学员姓名", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
    })
    public AjaxJson getScorePage(
            Long current,
            Long size,
            String stuName,
            String campusID,
            int scoreType,
            HttpServletRequest request
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<scoreVo> page = new Page<>(current, size);
        QueryWrapper<scoreVo> queryWrapper = new QueryWrapper<>();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        queryWrapper.eq("sc.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.eq("stu.stuName", stuName);
        }
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.like("stu.campusID", campusID);
        }
        if (scoreType != -1) {
            queryWrapper.eq("(sc.scoreType)", scoreType);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 225);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("stu.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("stu.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("stu.campusID", lookPower);
        }
        queryWrapper.orderByDesc("sc.scoreDate");
        ajaxJson.setObj(iPxbuxikechengtableService.getScore(page, queryWrapper));
        return ajaxJson;
    }

    //region 成绩的按钮集


    @ApiOperation("获取考试类别")
    @ResponseBody
    @RequestMapping(value = "getTesttype", method = RequestMethod.GET)
    public AjaxJson getTesttype(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        ajaxJson.setObj(iPxtesttypetableService.getTesttype(qiyeID));
        return ajaxJson;
    }

    @ApiOperation("获取科目下课程")
    @ResponseBody
    @RequestMapping(value = "getKcBySubject", method = RequestMethod.GET)
    public AjaxJson getKcBySubject(String subjectID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        ajaxJson.setObj(iPxkechengtableService.getKcBySubject(Long.valueOf(subjectID), qiyeID));
        return ajaxJson;
    }


    /**
     * @Description: addScore方法作用:添加学员成绩
     * @param:[pxscoretable]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:11
     */
    @ApiOperation("添加学员成绩")
    @ResponseBody
    @RequestMapping(value = "addScore", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true),
            @ApiImplicitParam(name = "subjectID", value = "科目ID", required = true),
            @ApiImplicitParam(name = "kechengID", value = "课程ID", required = true),
            @ApiImplicitParam(name = "score", value = "成绩", required = true),
            @ApiImplicitParam(name = "dankepaiming", value = "单科排名", required = false),
            @ApiImplicitParam(name = "zongfenpaiming", value = "总分排名", required = false),
            @ApiImplicitParam(name = "scoreType", value = "成绩类型", required = true),
            @ApiImplicitParam(name = "testTypeID", value = "考试类型", required = true),
            @ApiImplicitParam(name = "testTitle", value = "考试主题", required = true),
            @ApiImplicitParam(name = "beiZhu", value = "备注", required = false),
            @ApiImplicitParam(name = "scoreDate", value = "考试时间", required = true),
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addScore(String stuID, String subjectID, String kechengID, BigDecimal score, int dankepaiming, int zongfenpaiming, int scoreType,
                             String testTypeID, String testTitle, String scoreDate, String beiZhu, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Long staffID = Long.valueOf(loginUser.getStaffID());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date stuscoreDate = null;
        try {
            stuscoreDate = df.parse(scoreDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Pxkechengtable kc = iPxkechengtableService.getById(kechengID);
        Pxsubjecttable sub = iPxsubjecttableService.getById(subjectID);
        List<Pxscoretable> tTitle = iPxscoretableService.getTestT(testTitle, qiyeID);
        //课程名称和科目和测试标题重复
        if ((kc != null && sub != null) && tTitle.size() != 0) {
            ajaxJson.setMsg("学员课程和科目不能重复哦");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        Pxscoretable sc = new Pxscoretable();
        sc.setQiyeID(qiyeID);
        sc.setAddStaffID(staffID);
        sc.setAddDateTime(new Date());
        sc.setStuID(Long.valueOf(stuID));
        sc.setSubjectID(Long.valueOf(subjectID));
        sc.setKechengID(Long.valueOf(kechengID));
        sc.setScore(score);
        sc.setDankepaiming(dankepaiming);
        sc.setZongfenpaiming(zongfenpaiming);
        sc.setScoreType(scoreType);
        sc.setTestTypeID(Long.valueOf(testTypeID));
        sc.setTestTitle(testTitle);
        sc.setBeiZhu(beiZhu);
        sc.setScoreDate(stuscoreDate);
        ajaxJson.setSuccess(iPxscoretableService.save(sc));
        return ajaxJson;
    }


    /**
     * @Description: updateScore方法作用:修改学员成绩
     * @param:[pxscoretable]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:11
     */
    @ApiOperation("修改学员成绩")
    @ResponseBody
    @RequestMapping(value = "updateScore", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true),
            @ApiImplicitParam(name = "subjectID", value = "科目ID", required = true),
            @ApiImplicitParam(name = "kechengID", value = "课程ID", required = true),
            @ApiImplicitParam(name = "score", value = "成绩", required = true),
            @ApiImplicitParam(name = "dankepaiming", value = "单科排名", required = false),
            @ApiImplicitParam(name = "zongfenpaiming", value = "总分排名", required = false),
            @ApiImplicitParam(name = "scoreType", value = "成绩类型", required = true),
            @ApiImplicitParam(name = "testTypeID", value = "考试类型", required = true),
            @ApiImplicitParam(name = "testTitle", value = "考试主题", required = true),
            @ApiImplicitParam(name = "beiZhu", value = "备注", required = false),
            @ApiImplicitParam(name = "scoreDate", value = "考试时间", required = true),
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson updateScore(String id, int scoreType, BigDecimal score, int dankepaiming, int zongfenpaiming, String testTypeID, String scoreDate,
                                String testTitle, String beiZhu, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date stuscoreDate = null;
        try {
            stuscoreDate = df.parse(scoreDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Pxscoretable sc = iPxscoretableService.getById(id);
        sc.setScoreType(scoreType);
        sc.setScore(score);
        sc.setDankepaiming(dankepaiming);
        sc.setZongfenpaiming(zongfenpaiming);
        sc.setTestTypeID(Long.valueOf(testTypeID));
        sc.setScoreDate(stuscoreDate);
        sc.setTestTitle(testTitle);
        sc.setBeiZhu(beiZhu);
        ajaxJson.setSuccess(iPxscoretableService.updateById(sc));
        return ajaxJson;
    }


    /**
     * @Description: delScore方法作用:删除成绩
     * @param:[IDs]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:12
     */
    @ApiOperation("删除成绩")
    @ResponseBody
    @RequestMapping(value = "delScore", method = RequestMethod.DELETE)
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delScore(@RequestBody delstuscoreFrom from, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        String ids = from.getIds();
        if (ids != null) {
            List<stuIDVo> stuScoreList = JSON.parseArray(ids, stuIDVo.class);
            for (stuIDVo item : stuScoreList) {
                iPxscoretableService.removeById(item.getId());
            }
        }
        return ajaxJson;
    }

    /**
     * @Description: stuScorereadExcel()方法作用:导入学员成绩
     * @param:[file, request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/3/3 18:19
     */
    @ApiOperation("导入学员成绩")
    @ResponseBody
    @RequestMapping(value = "stuScorereadExcel", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson stuScorereadExcel(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Long staffID = loginUser.getStaffID();
        String errList = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date testDate = null;

        long t1 = System.currentTimeMillis();
        List<stuScoreImportVo> list = importExcel.readExcel("", stuScoreImportVo.class, file);
        if (list.size() > 0) {
//            for (stuScoreImportVo item : list) {
            for (int i = 0; i < list.size(); i++) {
                stuScoreImportVo item = list.get(i);
                if (item.getStuName() == null || item.getStuName() == "") {
                    errList += "第" + (i + 1) + "行:学员姓名为空,";
                } else if (item.getScoreType() == null || item.getScoreType() == "") {
                    errList += "第" + (i + 1) + "行:成绩类型为空,";
                } else if (item.getSubjectName() == null || item.getSubjectName() == "") {
                    errList += "第" + (i + 1) + "行:学科科目为空,";
                } else if (item.getKechengName() == null || item.getKechengName() == "") {
                    errList += "第" + (i + 1) + "行:课程为空,";
                } else if (item.getScore() == null || item.getScore() == "") {
                    errList += "第" + (i + 1) + "行:分数为空,";
                } else if (item.getTestType() == null || item.getTestType() == "") {
                    errList += "第" + (i + 1) + "行:考试类别为空,";
                } else if (item.getScoreDate() == null || item.getScoreDate() == "") {
                    errList += "第" + (i + 1) + "行:考试日期为空,";
                } else if (item.getTestTitle() == null || item.getTestTitle() == "") {
                    errList += "第" + (i + 1) + "行:测试内容为空,";
                } else {
                    QueryWrapper<Pxsubjecttable> subq = new QueryWrapper<>();
                    subq
                            .eq("qiyeID", qiyeID)
                            .eq("subjectName", item.getSubjectName());
                    List<Pxsubjecttable> subjectList = iPxsubjecttableService.selectsub(subq);
                    if (subjectList.size() == 0) {
                        errList += "第" + (i + 1) + "行:系统找不到此科目,请确认科目名称";
                    } else {
                        Pxsubjecttable subject = subjectList.get(0);

                        QueryWrapper<Pxkechengtable> kcq = new QueryWrapper<>();
                        kcq
                                .eq("subjectid", subject.getId())
                                .eq("qiyeID", qiyeID)
                                .eq("kechengname", item.getKechengName());
                        List<Pxkechengtable> kclist = iPxkechengtableService.selectkc(kcq);

                        QueryWrapper<Pxstutable> stuq = new QueryWrapper<>();
                        stuq
                                .eq("qiyeID", qiyeID)
                                .eq("stuName", item.getStuName());
                        List<Pxstutable> stulist = iPxstutableService.selectstu(stuq);

                        QueryWrapper<Pxtesttypetable> testq = new QueryWrapper<>();
                        testq
                                .eq("qiyeID", qiyeID)
                                .eq("testType", item.getTestType());
                        List<Pxtesttypetable> testList = iPxtesttypetableService.selecttesttype(testq);

                        if (stulist.size() == 0) {
                            errList += "第" + (i + 1) + "行:系统找不到此学生,请确认学员名字";
                        } else if (testList.size() == 0) {
                            errList += "第" + (i + 1) + "行:系统找不到此考试类别,请确认类别";
                        } else if (kclist.size() == 0) {
                            errList += "第" + (i + 1) + "行:系统找不到科目对应下的课程,请确认课程名称";
                        } else {
                            Pxkechengtable kc = kclist.get(0);
                            Pxstutable stu = stulist.get(0);
                            Pxtesttypetable test = testList.get(0);
                            BigDecimal score = new BigDecimal(item.getScore());

                            Pxscoretable sc = new Pxscoretable();
                            sc.setStuID(stu.getId());
                            sc.setSubjectID(subject.getId());
                            sc.setKechengID(kc.getId());
                            sc.setScore(score);
                            if (item.getDankepaiming() != null || item.getDankepaiming() != "") {
                                sc.setDankepaiming(Integer.parseInt(item.getDankepaiming()));
                            }
                            if (item.getZongfenpaiming() != null || item.getZongfenpaiming() != "") {
                                sc.setZongfenpaiming(Integer.parseInt(item.getZongfenpaiming()));
                            }
                            if (item.getScoreType().equals("在校成绩")) {
                                sc.setScoreType(1);
                            } else if (item.getScoreType().equals("培训机构成绩")) {
                                sc.setScoreType(0);
                            } else {
                                errList += "第" + (i + 1) + "行:系统找不到此成绩类型";
                            }
                            sc.setTestTypeID(test.getId());
                            sc.setTestTitle(item.getTestTitle());
                            sc.setAddDateTime(new Date());
                            sc.setAddStaffID(staffID);
                            sc.setBeiZhu(item.getBeiZhu());
                            try {
                                testDate = sdf.parse(item.getScoreDate());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            sc.setScoreDate(testDate);
                            sc.setQiyeID(qiyeID);
                            if (errList != "") {
                                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                                ajaxJson.setMsg(errList);
                                ajaxJson.setCode("N");
                                return ajaxJson;
                            } else {
                                iPxscoretableService.save(sc);
                            }
                        }
                    }
                }
            }
        } else {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ajaxJson.setMsg("空数据表！");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        ajaxJson.setMsg("导入成功");
        return ajaxJson;
    }


    @ApiOperation("导入学员成绩下载模板")
    @ResponseBody
    @RequestMapping(value = "ExportstuScoreMuban", method = RequestMethod.GET)
    public void ExportstuScoreMuban(HttpServletResponse response, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Pxkechengtable kc = iPxkechengtableService.getBysubject(qiyeID);

        Pxsubjecttable onesub = null;
        if (kc.getSubjectID() != null) {
            onesub = iPxsubjecttableService.getById(kc.getSubjectID());
        }
        Pxtesttypetable onetest = iPxtesttypetableService.getOnetest(qiyeID);
        stuScoreImportVo stuScoreImportVo = new stuScoreImportVo();
        stuScoreImportVo.setStuName("张三");
        stuScoreImportVo.setScoreType("在校成绩");
        if (kc != null) {
            stuScoreImportVo.setKechengName(kc.getKechengName());
        } else {
            stuScoreImportVo.setKechengName("语文xx");
        }
        if (onesub != null) {
            stuScoreImportVo.setSubjectName(onesub.getSubjectName());

        } else {
            stuScoreImportVo.setSubjectName("语文");
        }
        stuScoreImportVo.setScore("100");
        stuScoreImportVo.setDankepaiming("1");
        stuScoreImportVo.setZongfenpaiming("1");
        if (onetest != null) {
            stuScoreImportVo.setTestType(onetest.getTestType());
        } else {
            stuScoreImportVo.setTestType("月考");
        }
        stuScoreImportVo.setScoreDate("2021-01-01");
        stuScoreImportVo.setTestTitle("考试内容");
        stuScoreImportVo.setBeiZhu("备注信息");
        List<stuScoreImportVo> stuscore = Collections.singletonList(stuScoreImportVo);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"学员名字", "成绩类型", "科目名称", "课程名称", "学员成绩", "单科排名", "总分排名", "考试类型", "考试时间", "考试内容",
                        "备注"},
                stuscore,
                new String[]{"stuName", "scoreType", "subjectName", "kechengName", "score", "dankepaiming", "zongfenpaiming", "testType", "scoreDate",
                        "testTitle", "beiZhu"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "学员成绩导入模板.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @Description: exportFeedback方法作用:导出学员成绩
     * @param:[response, campusID, startDate, endDate]
     * @return:void
     * @auter:yyl
     * @data:2020/11/30 16:12
     */
    @RequestMapping(value = "ExportScore", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出学员成绩")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "startDate", value = "开始时间", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束时间", required = false),
    })
    @Transactional(rollbackFor = Exception.class)
    public void exportFeedback(HttpServletResponse response, HttpServletRequest request,
                               @RequestParam(required = false) String campusID, // 校区ID
                               @RequestParam(required = false) String startDate, // 开始日期
                               @RequestParam(required = false) String endDate) {
        QueryWrapper<scoreVo> queryWrapper = new QueryWrapper<>();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        queryWrapper.eq("sc.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("stu.campusID", campusID);
        }
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("sc.addDateTime", startDate);
        }

        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("sc.addDateTime", endDate);
        }
        List<scoreVo> scoreVos = iPxbuxikechengtableService.ExportScore(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "年级", "学员姓名", "科目", "测试内容", "分数", "单科排名",
                        "总分排名", "考试类别", "成绩类型", "录入日期", "录入人", "备注"},
                scoreVos,
                new String[]{"campusName", "stuGradeName", "stuName", "subjectName", "testTitle", "score", "dankepaiming",
                        "zongfenpaiming", "testType", "scoreType", "addDateTime-D", "staffName", "beiZhu"});

        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "学员成绩.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion
    //endregion

    //region 考级


    /**
     * @Description: getKaoJiPage方法作用:分页获取学员考级
     * @param:[current, size, stuName, campusName]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:12
     */
    @ApiOperation(value = "分页获取学员考级")
    @RequestMapping(value = "getKaoJiPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "stuName", value = "学员姓名", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区", required = false),
    })
    public AjaxJson getKaoJiPage(
            Long current,
            Long size,
            String stuName,
            String campusID,
            HttpServletRequest request
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Page<kaojiVo> page = new Page<>(current, size);
        QueryWrapper<kaojiVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("kj.qiyeID", qiyeID);

        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("stu.stuName", stuName);
        }
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("stu.campusID", campusID);
        }
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 226);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("stu.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("stu.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("stu.campusID", lookPower);
        }
        queryWrapper.orderByDesc("kj.time");
        ajaxJson.setObj(iPxbuxikechengtableService.getKaoJi(page, queryWrapper));
        return ajaxJson;
    }


    //region 考级的按钮集


    /**
     * @Description: addKaoJi方法作用:添加考级
     * @param:[pxkaojitable]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:13
     */
    @RequestMapping(value = "addKaoJI", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加考级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true),
            @ApiImplicitParam(name = "kemuid", value = "科目ID", required = true),
            @ApiImplicitParam(name = "jibie", value = "级别", required = true),
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addKaoJi(@RequestBody addKaoJiForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());

        List<Pxkaojitable> stuASub = iPxkaojitableService.getStuASub(form.getStuID(),form.getKemuid(), Long.valueOf(qiyeID));
        if (stuASub.size() > 0) {
            ajaxJson.setMsg("记录已存在!");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        Pxkaojitable kaoji = new Pxkaojitable();

        kaoji.setTime(new Date());
        kaoji.setJibie(form.getJibie());
        kaoji.setKemuid(form.getKemuid());
        kaoji.setStuid(form.getStuID());
        kaoji.setQiyeID(qiyeID);
        kaoji.setAddsatff(staffID);
        ajaxJson.setSuccess(iPxkaojitableService.save(kaoji));
        return ajaxJson;
    }


    /**
     * @Description: updateKaoJI方法作用:修改考级
     * @param:[pxkaojitable]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:13
     */
    @RequestMapping(value = "updateKaoJI", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改考级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "kjID", value = "考级ID", required = true),
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true),
            @ApiImplicitParam(name = "kemuid", value = "科目ID", required = true),
            @ApiImplicitParam(name = "jibie", value = "级别", required = true),
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson updateKaoJI(@RequestBody addKaoJiForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();

        Pxkaojitable kaoji = iPxkaojitableService.getById(form.getKjID());
        kaoji.setTime(new Date());
        kaoji.setJibie(form.getJibie());
        kaoji.setKemuid(form.getKemuid());
        kaoji.setStuid(form.getStuID());
        kaoji.setQiyeID(qiyeID);
        kaoji.setAddsatff(staffID);
        ajaxJson.setSuccess(iPxkaojitableService.updateById(kaoji));
        return ajaxJson;
    }


    /**
     * @Description: delFeedback方法作用:删除考级
     * @param:[IDs]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:13
     */
    @RequestMapping(value = "delKaoJi", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除考级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "IDs", value = "考级ID", required = true),
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delFeedback(String ids, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());
      //  String ids = from.getIds();
        if (ids == null) {
            ajaxJson.setMsg("数据错误");
            ajaxJson.setCode("N");
            return ajaxJson;
        }

        //List<stuIDVo> kjVoList = JSON.parseArray(ids, stuIDVo.class);
        for (String item : ids.split(",")) {
            iPxkaojitableService.removeById(item);
        }
        return ajaxJson;
    }


    /**
     * @Description: ExportKaoJi方法作用:导出考级
     * @param:[response]
     * @return:void
     * @auter:yyl
     * @data:2020/11/30 16:13
     */
    @RequestMapping(value = "ExportKaoJi", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出考级")
    public void ExportKaoJi(HttpServletResponse response, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        List<kaojiVo> kaojiVos = iPxbuxikechengtableService.ExportKaoJi(qiyeID);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "年级/年龄段", "学员姓名", "科目", "当前等级", "添加人", "添加时间"},
                kaojiVos,
                new String[]{"campusName", "stuGradeName", "stuName", "subjectName", "jibie", "staffName", "addDateTime-D"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "导出考级.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //region 考级申请


    /**
     * @Description: getKJsqPage方法作用:分页获取考级申请
     * @param:[current, size]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:14
     */
    @RequestMapping(value = "getKJsqPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页获取考级申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "stuID", value = "学员ID", example = "10", required = true),

    })
    public AjaxJson getKJsqPage(long current, long size, String stuID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<kjSqVo> page = new Page<>(current, size);
        QueryWrapper<kjSqVo> q = new QueryWrapper<>();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        q
                .eq("kj.qiyeID", qiyeID)
                .eq("kj.stuid", stuID);

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 226);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            q.eq("pxstutable.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            q.eq("pxstutable.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            q.in("pxstutable.campusID", lookPower);
        }
        q.orderByDesc("kj.addDate");
        ajaxJson.setObj(iPxkaojisqtableService.getKJsqPage(page, q));
        return ajaxJson;
    }


    /**
     * @Description: addFeedback方法作用:添加考级申请
     * @param:[pxkaojisqtable]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:14
     */
    @RequestMapping(value = "/addKaoJiSq", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加考级申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true),
            @ApiImplicitParam(name = "kemuid", value = "科目ID", required = true),
            @ApiImplicitParam(name = "sqjibie", value = "申请等级", required = true),
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addFeedback(@RequestBody addFeedbackForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();

        Pxkaojisqtable kjsq = new Pxkaojisqtable();
        kjsq.setSqjibie(form.getSqjibie());
        kjsq.setStuid(form.getStuID());
        kjsq.setKemuid(form.getKemuid());
        kjsq.setShjibie("");
        kjsq.setAddDate(new Date());
        kjsq.setShenheren(-1L);
        kjsq.setLururen(staffID);
        kjsq.setQiyeID(qiyeID);
        ajaxJson.setSuccess(iPxkaojisqtableService.save(kjsq));
        return ajaxJson;
    }


    /**
     * @Description: updateKaoJiSq方法作用:修改考级申请
     * @param:[pxkaojisqtable]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:14
     */
    @RequestMapping(value = "updateKaoJiSq", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改考级申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sqID", value = "申请ID", required = true),
            @ApiImplicitParam(name = "sqjibie", value = "申请等级", required = true),
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson updateKaoJiSq(@RequestBody addFeedbackForm form) {
        AjaxJson ajaxJson = new AjaxJson();
        Pxkaojisqtable kjsq = iPxkaojisqtableService.getById(form.getSqID());
        kjsq.setSqjibie(form.getSqjibie());
        ajaxJson.setSuccess(iPxkaojisqtableService.updateById(kjsq));
        return ajaxJson;
    }


    /**
     * @Description: shKaoJisq方法作用:审核考级
     * @param:[kaojisqID]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:15
     */
    @ApiOperation("审核考级")
    @ResponseBody
    @RequestMapping(value = "shKaoJisq", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "kaojisqID", value = "申请ID", required = true)
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson shKaoJisq(@RequestBody shKaoJisqForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();
        Pxkaojisqtable kjsq = iPxkaojisqtableService.getById(form.getKaojisqID());
        kjsq.setShenheDate(new Date());
        kjsq.setShenheren(staffID);
        kjsq.setShjibie(kjsq.getSqjibie());
        ajaxJson.setObj(iPxkaojisqtableService.updateById(kjsq));
        return ajaxJson;
    }

    //endregion

    //endregion

    //endregion

    //region 发证


    /**
     * @Description: getfazhengPage方法作用:分页获取学员发证
     * @param:[current, size, stuName, FZstate]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:15
     */
    @ApiOperation(value = "分页获取学员发证")
    @RequestMapping(value = "getfazhengPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "stuName", value = "学员姓名", required = false),
            @ApiImplicitParam(name = "FZstate", value = "发证状态", example = "-1", required = false),
    })
    public AjaxJson getfazhengPage(Long current,
                                   Long size,
                                   String stuName,
                                   int FZstate,
                                   HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<fazhengVo> page = new Page<>(current, size);
        QueryWrapper<fazhengVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("stu.stuName", stuName);
        }
        if (FZstate != -1) {
            if (FZstate > 0) {
                queryWrapper.ge("(SELECT COUNT(id) from pxfazhengtable where Stuid=stu.id AND ZSid=pxkechengtable.ZSid)", FZstate);
            } else {
                queryWrapper.eq("(SELECT COUNT(id) from pxfazhengtable where Stuid=stu.id AND ZSid=pxkechengtable.ZSid)", 0);
            }

        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 227);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("stu.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("stu.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("stu.campusID", lookPower);
        }

        ajaxJson.setObj(iPxbuxikechengtableService.getfazhengPage(page, queryWrapper));
        return ajaxJson;
    }

    //region 发证的按钮


    /**
     * @Description: getzhengshuPage方法作用：分页获取课程证书设置查询
     * @param:[current, size, zsName]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:16
     */
    @ApiOperation("分页获取课程证书设置查询")
    @ResponseBody
    @RequestMapping(value = "getzhengshuPage", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "zsName", value = "证书名称", required = false),
    })
    public AjaxJson getzhengshuPage(Long current,
                                    Long size,
                                    String zsName,
                                    HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<zhengshuSTVo> page = new Page<>(current, size);
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());

        QueryWrapper<Pxkechengtable> kcQ = new QueryWrapper<>();
        kcQ.eq("qiyeID", qiyeID);

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 227);

        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            kcQ.eq("campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            kcQ.eq("campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            kcQ.in("campusID", lookPower);
        }

        QueryWrapper<zhengshuSTVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("zs.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(zsName)) {
            queryWrapper.like("zsName", zsName);
        }

        ajaxJson.setObj(iPxcertificatetableService.getzhengshuPage(page, kcQ, queryWrapper));
        return ajaxJson;
    }

    @ApiOperation("保存发证")
    @RequestMapping(value = "FZSave", method = RequestMethod.POST)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fzSID", value = "证书ID", required = true),
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true),
            @ApiImplicitParam(name = "imgaddress", value = "图片地址", required = true)
    })
    public AjaxJson FZSave(String fzSID, String stuID, String imgaddress, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Long staffID = loginUser.getStaffID();
        if (StringUtils.isEmpty(fzSID)) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("请先绑定课程对应证书！");
            return ajaxJson;
        }

        Long FzID = null;
        List<Pxfazhengtable> faList = iPxfazhengtableService.getstufzList(Long.valueOf(stuID), Long.valueOf(fzSID), qiyeID);
        if (faList.size() == 0) {
            Pxfazhengtable fz = new Pxfazhengtable();
            fz.setFZdate(new Date());
            fz.setFZImage(imgaddress);
            fz.setFZstaff(staffID);
            fz.setQiyeID(qiyeID);
            fz.setStuid(Long.valueOf(stuID));
            fz.setZSid(Long.valueOf(fzSID));
            iPxfazhengtableService.save(fz);
            FzID = fz.getId();
        } else {
            Pxfazhengtable fzup = faList.get(0);
            fzup.setFZdate(new Date());
            fzup.setFZstaff(staffID);
            fzup.setFZImage(imgaddress);
            iPxfazhengtableService.updateById(fzup);
            FzID = fzup.getId();
        }
        ajaxJson.setObj(FzID);
        return ajaxJson;
    }

    /**
     * @Description: Exportzhengshu方法作用:导出证书
     * @param:[response]
     * @return:void
     * @auter:yyl
     * @data:2020/11/30 16:16
     */
    @ApiOperation("导出证书")
    @ResponseBody
    @RequestMapping(value = "Exportzhengshu", method = RequestMethod.GET)
    public void Exportzhengshu(HttpServletResponse response, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        List<ExportZSVo> exportzhengshu = iPxcertificatetableService.Exportzhengshu(qiyeID);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"学员姓名", "课程", "证书名称", "发证状态", "经办人", "发证时间"},
                exportzhengshu,
                new String[]{"stuName", "kechengName", "zsName", "FZstate", "FZstaff", "FZdate"});

        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "发证.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @Description: addZs方法作用:添加证书
     * @param:[pxcertificatetable]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:16
     */
    @ApiOperation("添加证书")
    @RequestMapping(value = "addZs", method = RequestMethod.POST)
    @ResponseBody()
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addZs(@RequestBody addZsForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();

        List<Pxcertificatetable> cfzs = iPxcertificatetableService.getcfZS(form.getZsName(), qiyeID);
        if (cfzs.size() > 0) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("证书名称重复");
            return ajaxJson;
        }

        Pxcertificatetable zs = new Pxcertificatetable();
        zs.setZsName(form.getZsName());
        zs.setQiyeID(qiyeID);
        ajaxJson.setSuccess(iPxcertificatetableService.save(zs));
        return ajaxJson;
    }


    /**
     * @Description: updateZs方法作用:修改证书·
     * @param:[pxcertificatetable]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:17
     */
    @ApiOperation("修改证书")
    @RequestMapping(value = "updateZsList", method = RequestMethod.POST)
    @ResponseBody()
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "证书ID", required = true),
            @ApiImplicitParam(name = "zsName", value = "证书名称", required = true)
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson updateZs(String id, String zsName, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Pxcertificatetable zs = iPxcertificatetableService.getById(id);
        List<Pxcertificatetable> cfzs = iPxcertificatetableService.getcfZS(zsName, qiyeID);
        if (cfzs.size() > 0) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("证书名称重复");
            return ajaxJson;
        }
        zs.setZsName(zsName);
        ajaxJson.setSuccess(iPxcertificatetableService.updateById(zs));
        return ajaxJson;
    }


    /**
     * @Description: delZs方法作用:删除证书
     * @param:[IDs]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:17
     */
    @RequestMapping(value = "delZs", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除证书")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "IDs", value = "证书IDs", required = true),
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delZs(@RequestBody delzsFrom from) {
        AjaxJson ajaxJson = new AjaxJson();
        String ids = from.getIds();
        if (ids == null) {
            ajaxJson.setMsg("数据错误");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        List<stuIDVo> zsVoList = JSON.parseArray(ids, stuIDVo.class);
        for (stuIDVo item : zsVoList) {
            iPxcertificatetableService.removeById(item.getId());
        }
        return ajaxJson;
    }


    /**
     * @Description: bangDingZs方法作用:证书绑定课程
     * @param:[zsID, kcID]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:17
     */
    @ApiOperation("绑定证书")
    @ResponseBody
    @RequestMapping(value = "bangDingZs", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "zsID", value = "证书ID", required = true),
            @ApiImplicitParam(name = "kcID", value = "课程ID", required = true)
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson bangDingZs(@RequestBody bangDingZsForm form) {
        AjaxJson ajaxJson = new AjaxJson();
        Pxkechengtable kc = iPxkechengtableService.getById(form.getKcID());
        if (kc == null) {
            ajaxJson.setMsg("数据格式有误，请联系客服!");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        kc.setZSid(form.getZsID());
        ajaxJson.setSuccess(iPxkechengtableService.updateById(kc));
        return ajaxJson;
    }


    /**
     * @Description: lookBD方法作用:查看证书绑定课程
     * @param:[zsid]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:17
     */
    @ApiOperation("查看绑定")
    @ResponseBody
    @RequestMapping(value = "lookBD", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "zsid", value = "证书ID", required = true)
    })
    public AjaxJson lookBD(Long current,
                           Long size,
                           HttpServletRequest request,
                           Long zsid) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<zhengshuSTVo> page = new Page<>(current, size);
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        ajaxJson.setObj(iPxkechengtableService.getZSxq(zsid, qiyeID, page));
        return ajaxJson;
    }

    /**
     * @Description: delBD方法作用:解绑课程
     * @param:[kcID]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:17
     */
    @ApiOperation("解除绑定")
    @ResponseBody
    @RequestMapping(value = "delBD", method = RequestMethod.DELETE)
    @ApiImplicitParams(
            @ApiImplicitParam(name = "kcID", value = "课程ID", required = true)
    )
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delBD(Long kcID) {
        AjaxJson ajaxJson = new AjaxJson();
        Pxkechengtable kcTab = iPxkechengtableService.getById(kcID);
        kcTab.setZSid(0L);
        ajaxJson.setSuccess(iPxkechengtableService.updateById(kcTab));
        return ajaxJson;
    }
    //endregion

    //endregion

}
