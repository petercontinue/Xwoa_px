package com.xwcloud.cloud.homeschool.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.DateUtil;
import com.xwcloud.cloud.common.ExportExcel;
import com.xwcloud.cloud.homeschool.Service.IPxpaiketableService;
import com.xwcloud.cloud.homeschool.Service.IPxyueketeacherfabutableService;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.entity.Pxpaiketable;
import com.xwcloud.cloud.model.entity.Pxyueketeacherfabutable;
import com.xwcloud.cloud.model.Vo.PxyueketeacherfabutableVo;
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
import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalTime;
import java.util.*;

@Controller
@RequestMapping("/homeschool/pxyueketeacherfabu")
@Api(tags = "约课(老师发布)")
public class PxyueketeacherfabuController {

    @Autowired
    IPxyueketeacherfabutableService iPxyueketeacherfabutableService;
    @Autowired
    IPxpaiketableService iPxpaiketableService;


    @RequestMapping(value = "/getFabuYuekePage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取教师发布列表分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = true),
            @ApiImplicitParam(name = "current", value = "页码", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "kechengName", value = "课程名称", required = false),
            @ApiImplicitParam(name = "teacherName", value = "教师名称", required = false),
            @ApiImplicitParam(name = "className", value = "班级名称", required = false),
            @ApiImplicitParam(name = "yuekeState", value = "约课状态，1未约满正常可约，2约满了，3过期了", required = false)
    })
    public AjaxJson getFabuYuekePage(HttpServletRequest request,
                                     @RequestParam(required = false, defaultValue = "10") long size,
                                     @RequestParam(required = false, defaultValue = "1") long current,
                                     @RequestParam(required = false) String campusID,
                                     @RequestParam(required = false) String kechengName,
                                     @RequestParam(required = false) String teacherName,
                                     @RequestParam(required = false) String className,
                                     @RequestParam(required = false, defaultValue = "0") int yuekeState
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<PxyueketeacherfabutableVo> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.ne("campus.isOpen", 2);
        queryWrapper.eq("teacherfabu.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.like("teacherfabu.campusID", campusID);
        }
        if (StringUtils.isNotBlank(kechengName)) {
            queryWrapper.like("kecheng.kechengName", kechengName);
        }
        if (StringUtils.isNotBlank(teacherName)) {
            queryWrapper.like("teacherfabu.teacherNames", teacherName);
        }
        if (StringUtils.isNotBlank(className)) {
            queryWrapper.like("class.className", className);
        }
        if (yuekeState != 0) {
            queryWrapper.eq("teacherfabu.yuekeState", yuekeState);
        }
        queryWrapper.orderByDesc("teacherfabu.haveLessonDate");
        page = iPxyueketeacherfabutableService.getPage(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/addFabuYueke", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class})
    @ApiOperation(value = "添加教师发布约课")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true),
            @ApiImplicitParam(name = "teacherID", value = "教师ID", required = true),
            @ApiImplicitParam(name = "teacherName", value = "教师名称", required = true),
            @ApiImplicitParam(name = "kechengID", value = "课程ID", required = true),
            @ApiImplicitParam(name = "classID", value = "班级ID", required = true),
            @ApiImplicitParam(name = "haveLessonDate", value = "上课日期", required = true),
            @ApiImplicitParam(name = "classRoomID", value = "教室ID", required = true),
            @ApiImplicitParam(name = "startingTime", value = "上课时间", required = true),
            @ApiImplicitParam(name = "endTime", value = "下课时间", required = true),
            @ApiImplicitParam(name = "keshi", value = "课时数", required = true),
            @ApiImplicitParam(name = "miniStuNum", value = "最小人数", required = true),
            @ApiImplicitParam(name = "maxStuNum", value = "最大人数", required = true),
            @ApiImplicitParam(name = "isCheck", value = "是否检查排课冲突", required = true),
    })
    public AjaxJson addFabuYueke(HttpServletRequest request, long campusID, long teacherID, String teacherName,
                                 long kechengID, long classID,
                                 String haveLessonDate, long classRoomID, String startingTime, String endTime, BigDecimal keshi,
                                 Integer miniStuNum, Integer maxStuNum, Boolean isCheck
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        //String paikeDate = paikeDates.split(",");
        // 检测排课冲突
        if (isCheck) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.in("haveClassDate", haveLessonDate);
            queryWrapper.eq("startLessonDateTime", startingTime);
            queryWrapper.eq("endLessonDateTime", endTime);
            queryWrapper.like("teacherIDs", teacherID);
            queryWrapper.eq("qiyeID", qiyeID);
            List<Pxpaiketable> pxpaiketables = iPxpaiketableService.list(queryWrapper);
            if (pxpaiketables.size() > 0) {
                String msg = "";
                for (Pxpaiketable paike : pxpaiketables) {
                    msg += DateUtil.formatDate4(paike.getHaveClassDate()) + " ";
                }
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg(msg + "排课有冲突!");
                return ajaxJson;
            }
        }
        // 向排课表添加数据
        String paiketag = DateUtil.formatDate11(new Date());
        Pxpaiketable pxpaiketable = new Pxpaiketable();
//        pxpaiketable.setStartLessonDateTime(DateUtil.toDate(startingTime, "HH:mm:ss")); // 上课时间
//        pxpaiketable.setEndLessonDateTime(DateUtil.toDate(endTime, "HH:mm:ss")); // 下课时间
        LocalTime ST = LocalTime.parse(startingTime);
        LocalTime ET = LocalTime.parse(endTime);


        Time St = Time.valueOf(ST);//上课时间
        Time Et = Time.valueOf(ET);//下课时间
        pxpaiketable.setStartLessonDateTime(St); // 上课时间
        pxpaiketable.setEndLessonDateTime(Et); // 下课时间
        pxpaiketable.setHaveClassDate(DateUtil.toDate(haveLessonDate, "yyyy-MM-dd")); // 上课日期
        pxpaiketable.setTeacherNames(teacherName); // 排课老师名称
        pxpaiketable.setTeacherIDs(String.valueOf(teacherID)); // 排课老师ID
        pxpaiketable.setClassID(classID); // 班级ID
        pxpaiketable.setClassRoomID(classRoomID); // 教室ID
        String weekn = DateUtil.getWeekOfDate(DateUtil.toDate(haveLessonDate, "yyyy-MM-dd"));
        pxpaiketable.setWeekN(weekn); // 星期几
        pxpaiketable.setMaxStuNum(maxStuNum); // 最大学员人数
        pxpaiketable.setKechengID(kechengID); // 课程ID
        pxpaiketable.setKechengContent(null); // 课程内容
        pxpaiketable.setDakaoqin(false); // 是否已完成打考勤
        String tags = DateUtil.formatDate11(new Date());
        pxpaiketable.setTags(tags);// 排课批次
        pxpaiketable.setCanqingjiaBeforeHours(0); // 允许开课前几小时内请假
        pxpaiketable.setShuakaTimeArea(1);// 刷卡或刷脸消课时间段，1课前，2课中，3课后，默认1
        pxpaiketable.setQiyeID(qiyeID);
        iPxpaiketableService.save(pxpaiketable);
        // 向约课表添加数据
        Pxyueketeacherfabutable pxyueketeacherfabutable = new Pxyueketeacherfabutable();
        pxyueketeacherfabutable.setTeacherIDs(String.valueOf(teacherID));
        pxyueketeacherfabutable.setTeacherNames(teacherName);
        pxyueketeacherfabutable.setClassID(classID);
        pxyueketeacherfabutable.setHaveLessonDate(DateUtil.toDate(haveLessonDate, "yyyy-MM-dd"));
        pxyueketeacherfabutable.setStartLessonTime(St);
        pxyueketeacherfabutable.setEndLessonTime(Et);
        pxyueketeacherfabutable.setKeshiNum(keshi);
        pxyueketeacherfabutable.setKechengID(kechengID);
        pxyueketeacherfabutable.setClassroomID(classRoomID);
        pxyueketeacherfabutable.setMinSuccessYuekeStuNum(miniStuNum);
        pxyueketeacherfabutable.setMaxStuNum(maxStuNum);
        pxyueketeacherfabutable.setPaikeID(pxpaiketable.getId());
        pxyueketeacherfabutable.setYuekeState(1);
        pxyueketeacherfabutable.setCampusID(campusID);
        pxyueketeacherfabutable.setAddTime(new Date());
        pxyueketeacherfabutable.setAddStaffID(loginUser.getStaffID());
        pxyueketeacherfabutable.setQiyeID(loginUser.getQiyeID());
        iPxyueketeacherfabutableService.save(pxyueketeacherfabutable);
        return ajaxJson;
    }

    @RequestMapping(value = "/editFabuYueke", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class})
    @ApiOperation(value = "编辑教师发布约课")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true),
            @ApiImplicitParam(name = "teacherID", value = "教师ID", required = true),
            @ApiImplicitParam(name = "teacherName", value = "教师名称", required = true),
            @ApiImplicitParam(name = "kechengID", value = "课程ID", required = true),
            @ApiImplicitParam(name = "classID", value = "班级ID", required = true),
            @ApiImplicitParam(name = "haveLessonDate", value = "上课日期", required = true),
            @ApiImplicitParam(name = "classRoomID", value = "教室ID", required = true),
            @ApiImplicitParam(name = "startingTime", value = "上课时间", required = true),
            @ApiImplicitParam(name = "endTime", value = "下课时间", required = true),
            @ApiImplicitParam(name = "keshi", value = "课时数", required = true),
            @ApiImplicitParam(name = "miniStuNum", value = "最小人数", required = true),
            @ApiImplicitParam(name = "maxStuNum", value = "最大人数", required = true),
            @ApiImplicitParam(name = "isCheck", value = "是否检查排课冲突", required = true),
    })
    public AjaxJson editFabuYueke(HttpServletRequest request, Long yuekeID, Long campusID, Long teacherID, String teacherName,
                                  Long kechengID, Long classID, String haveLessonDate, Long classRoomID, String startingTime, String endTime, BigDecimal keshi,
                                  int miniStuNum, int maxStuNum, Boolean isCheck) {
        AjaxJson ajaxJson = new AjaxJson();
        Pxyueketeacherfabutable pxyueketeacherfabutable = iPxyueketeacherfabutableService.getById(yuekeID);
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        // 检测排课冲突
        if (isCheck) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.in("haveClassDate", haveLessonDate);
            queryWrapper.eq("startLessonDateTime", startingTime);
            queryWrapper.eq("endLessonDateTime", endTime);
            queryWrapper.like("teacherIDs", teacherID);
            queryWrapper.ne("id", pxyueketeacherfabutable.getPaikeID());
            List<Pxpaiketable> pxpaiketables = iPxpaiketableService.list(queryWrapper);
            if (pxpaiketables.size() > 0) {
                String msg = "";
                for (Pxpaiketable paike : pxpaiketables) {
                    msg += DateUtil.formatDate4(paike.getHaveClassDate()) + " ";
                }
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg(msg + "排课有冲突!");
                return ajaxJson;
            }
        }
        // 修改约课

        pxyueketeacherfabutable.setTeacherIDs(String.valueOf(teacherID));
        pxyueketeacherfabutable.setTeacherNames(teacherName);
        pxyueketeacherfabutable.setClassID(classID);
        pxyueketeacherfabutable.setHaveLessonDate(DateUtil.toDate(haveLessonDate, "yyyy-MM-dd"));
        pxyueketeacherfabutable.setStartLessonTime(DateUtil.toDate(startingTime, "HH:mm:ss"));
        pxyueketeacherfabutable.setEndLessonTime(DateUtil.toDate(endTime, "HH:mm:ss"));
        pxyueketeacherfabutable.setKeshiNum(keshi);
        pxyueketeacherfabutable.setKechengID(kechengID);
        pxyueketeacherfabutable.setClassroomID(classRoomID);
        pxyueketeacherfabutable.setMinSuccessYuekeStuNum(miniStuNum);
        pxyueketeacherfabutable.setMaxStuNum(maxStuNum);
        pxyueketeacherfabutable.setYuekeState(1);
        pxyueketeacherfabutable.setCampusID(campusID);
        iPxyueketeacherfabutableService.updateById(pxyueketeacherfabutable);
        // 修改排课

        Pxpaiketable pxpaiketable = iPxpaiketableService.getById(pxyueketeacherfabutable.getPaikeID());
//        pxpaiketable.setStartLessonDateTime(DateUtil.toDate(startingTime, "HH:mm:ss")); // 上课时间
//        pxpaiketable.setEndLessonDateTime(DateUtil.toDate(endTime, "HH:mm:ss")); // 下课时间
        LocalTime startTime = LocalTime.parse(startingTime);
        LocalTime endDatetime = LocalTime.parse(endTime);
        Time St = Time.valueOf(startTime);//上课时间
        Time Et = Time.valueOf(endDatetime);//下课时间

        pxpaiketable.setStartLessonDateTime(St); // 上课时间
        pxpaiketable.setEndLessonDateTime(Et); // 下课时间
        pxpaiketable.setHaveClassDate(DateUtil.toDate(haveLessonDate, "yyyy-MM-dd")); // 上课日期
        pxpaiketable.setTeacherNames(teacherName); // 排课老师名称
        pxpaiketable.setTeacherIDs(String.valueOf(teacherID)); // 排课老师ID
        pxpaiketable.setClassID(classID); // 班级ID
        pxpaiketable.setClassRoomID(classRoomID); // 教室ID
        String weekn = DateUtil.getWeekOfDate(DateUtil.toDate(haveLessonDate, "yyyy-MM-dd"));
        pxpaiketable.setWeekN(weekn); // 星期几
        pxpaiketable.setMaxStuNum(maxStuNum); // 最大学员人数
        pxpaiketable.setKechengID(kechengID); // 课程ID
        iPxpaiketableService.updateById(pxpaiketable);
        ajaxJson.setSuccess(true);
        return ajaxJson;
    }

    @RequestMapping(value = "/delFabuYueke", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除教师发布约课")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "老师ID，多个老师逗号隔开", required = true),

    })
    public AjaxJson delFabuYueke(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] strings = ids.split(",");
        ajaxJson.setSuccess(iPxyueketeacherfabutableService.removeByIds(Arrays.asList(strings)));
        return ajaxJson;
    }

    @RequestMapping(value = "/getFabuYueke", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取教师发布约课")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "老师ID", required = true)
    })
    public AjaxJson getFabuYueke(String id, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        ajaxJson.setObj(iPxyueketeacherfabutableService.getById(queryWrapper));
        return ajaxJson;
    }

    @RequestMapping(value = "/getYuekechengList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取教师发布约课")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "约课ID", required = true)
    })
    public AjaxJson getYuekechengList(String id, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        ajaxJson.setObj(iPxyueketeacherfabutableService.getOne(queryWrapper));
        return ajaxJson;
    }

    @RequestMapping(value = "/getKechengList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取课程列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "staffID", value = "教师ID", required = false)
    })
    public AjaxJson getKechengList(String staffID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("tsub.qiyeID", qiyeID);
        queryWrapper.eq("tsub.staffID", staffID);
        queryWrapper.isNotNull("kecheng.id");
        List<HashMap<String, String>> kechengList = iPxyueketeacherfabutableService.getKechengList(queryWrapper);
        if (kechengList == null) {
            ajaxJson.setSuccess(false);
        } else {
            ajaxJson.setSuccess(true);
            ajaxJson.setObj(kechengList);
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/exportFabuYuekeList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出教师发布约课")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true),
            @ApiImplicitParam(name = "startDate", value = "开始日期", required = true),
            @ApiImplicitParam(name = "endDate", value = "结束日期", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public void exportFabuYuekeList(
            HttpServletResponse response,
            @RequestParam(required = false) Long campusID,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate, HttpServletRequest request
    ) {
        QueryWrapper queryWrapper = new QueryWrapper();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        queryWrapper.eq("teacherfabu.qiyeID", qiyeID);
        queryWrapper.ne("campus.isOpen", 2);
        if (campusID!=0) {
            queryWrapper.eq("teacherfabu.campusID", campusID);
        }
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("teacherfabu.haveLessonDate", startDate);
        }

        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("teacherfabu.haveLessonDate", endDate);
        }

        List<PxyueketeacherfabutableVo> pxyueketeacherfabutableVoList = iPxyueketeacherfabutableService.getJoinList(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "课程名称", "教师", "班级", "最少人数", "最多人数", "当前人数",
                        "约课状态", "上课日期", "上课时间", "下课时间", "课时", "培训方式", "发起时间"},
                pxyueketeacherfabutableVoList,
                new String[]{"campusName", "kechengName", "teacherName", "className", "minSuccessYuekeStuNum", "maxStuNum",
                        "current", "state", "haveLessonDate-DT", "startLessonTime-DT", "endLessonTime-DT", "keshiNum", "buxiStyleName", "addTime-DT"});

        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "任课老师课后反馈导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
