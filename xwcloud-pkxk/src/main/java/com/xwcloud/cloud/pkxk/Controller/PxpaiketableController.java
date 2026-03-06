package com.xwcloud.cloud.pkxk.Controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.DateUtil;
import com.xwcloud.cloud.common.ExportExcel;
import com.xwcloud.cloud.common.aliyun.AliyunOssUtil;
import com.xwcloud.cloud.model.Form.*;
import com.xwcloud.cloud.model.Form.stu.editAutoxiaokeForm;
import com.xwcloud.cloud.model.Form.stu.rgqiandaoqiantuiForm;
import com.xwcloud.cloud.model.Form.stu.savezdxkForm;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.*;
import com.xwcloud.cloud.pkxk.Dao.IPxstukaoqingteachertableDao;
import com.xwcloud.cloud.pkxk.Service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jodd.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-08
 */

@Controller
@RequestMapping("/paike/paikexiaoke")
@Api(tags = "排课消课")
public class PxpaiketableController {

    //region  注入服务

    @Autowired
    IPxstukaoqingteachertableDao iPxstukaoqingteachertableDao;

    @Autowired
    IPxstukaoqingtableService iPxstukaoqingtableService;

    @Autowired
    IPxstutableService iPxstutableService;

    @Autowired
    IPxqiandaoqiantuitableService iPxqiandaoqiantuitableService;

    @Autowired
    IPxstuclasstableService iPxstuclasstableService;

    @Autowired
    IPxpaiketableService iPxpaiketableService;

    @Autowired
    IPxkechengtableService iPxkechengtableService;

    @Autowired
    IPxbuxistyletableService iPxbuxistyletableService;

    @Autowired
    IPxbuxikechengtableService iPxbuxikechengtableService;

    @Autowired
    IPxsysparamdefaulttableService iPxsysparamdefaulttableService;

    @Autowired
    IPxkeshistutableService iPxkeshistutableService;

    @Autowired
    IPxpaiketeachertableService iPxpaiketeachertableService;

    @Autowired
    IPxstafftableService iPxstafftableService;

    @Autowired
    kechengQiehuan kechengQiehuan;

    @Autowired
    IPxclasstableService iPxclasstableService;

    @Autowired
    IPxkeshistuteachertableService iPxkeshistuteachertableService;

    @Autowired
    IPxjifentableService iPxjifentableService;

    @Autowired
    IPxwxusertableService iPxwxusertableService;

    @Autowired
    IPxkeshiteachertableService iPxkeshiteachertableService;

    @Autowired
    IPxstukaoqingteachertableService iPxstukaoqingteachertableService;

    @Autowired
    IPxcampustableService iPxcampustableService;

    @Autowired
    IPxautoxiaoketableService iPxautoxiaoketableService;

    @Autowired
    IPxstucardtableService iPxstucardtableService;

    @Autowired
    IPxchongzhipaytableService iPxchongzhipaytableService;

    @Autowired
    IPxxuanketableService iPxxuanketableService;

    @Autowired
    IPxstugradetableService iPxstugradetableService;

    @Autowired
    IPxclasstimestyletableService iPxclasstimestyletableService;

    @Autowired
    IPxsubjecttableService iPxsubjecttableService;

    @Autowired
    IPxclassroomtableService iPxclassroomtableService;

    @Autowired
    IPxsysparamvaluetableService iPxsysparamvaluetableService;

    @Autowired
    IPxpowertableService iPxpowertableService;

    @Autowired
    IPxqiandaninfotableService iPxqiandaninfotableService;


    @Autowired
    IPxqingjiatableService iPxqingjiatableService;

    @Autowired
    IPxkechengcontenttableService iPxkechengcontenttableService;

    @Autowired
    IWscUserBindService iWscUserBindService;

    @Autowired
    IWscUserService iWscUserService;

    @Autowired
    IGzhAlluserService iGzhAlluserService;

    @Autowired
    IPxtuisongtableService iPxtuisongtableService;

    @Autowired
    sendMessage sendMessage;

    //endregion

    //region 排课

    /**
     * @Description: getstugradeList()方法作用:获取学员年级
     * @param:[]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/1/4 11:59
     */
    @ResponseBody
    @RequestMapping(value = "getstugradeList", method = RequestMethod.GET)
    @ApiOperation("获取学员年级")
    public AjaxJson getstugradeList(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        ajaxJson.setObj(iPxstugradetableService.getgradeList(qiyeID));
        return ajaxJson;
    }

    /**
     * @Description: getstubuxiList()方法作用:获取补习方式
     * @param:[]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/1/4 12:01
     */
    @ResponseBody
    @RequestMapping(value = "getstubuxiList", method = RequestMethod.GET)
    @ApiOperation("获取补习方式")
    public AjaxJson getstubuxiList(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        ajaxJson.setObj(iPxbuxistyletableService.getbuxiStyleList(qiyeID));
        return ajaxJson;
    }

    /**
     * @Description: getallkechengByCampusId()方法作用:根据校区获取课程
     * @param:[request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/1/13 10:25
     */
    @ResponseBody
    @RequestMapping(value = "getallkechengByCampusId", method = RequestMethod.GET)
    @ApiOperation("根据校区获取课程")
    public AjaxJson getallkechengByCampusId(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long campusID = loginUser.getCampusID();
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        ajaxJson.setObj(iPxkechengtableService.getkcBycampus(campusID, qiyeID));
        return ajaxJson;
    }

    /**
     * @Description: NOpaikegetclass()方法作用:根据所有班级
     * @param:[]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/1/13 10:45
     */
    @ResponseBody
    @RequestMapping(value = "NOpaikegetclass", method = RequestMethod.GET)
    @ApiOperation("根据所有班级")
    public AjaxJson NOpaikegetclass(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", qiyeID);

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 243);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }
        ajaxJson.setObj(iPxclasstableService.NOpaikegetclass(queryWrapper));
        return ajaxJson;
    }

    /**
     * @Description: getallstaff()方法作用:根据所有员工
     * @param:[]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/1/13 11:12
     */
    @ResponseBody
    @RequestMapping(value = "getallstaff", method = RequestMethod.GET)
    @ApiOperation("根据所有员工（在职）")
    public AjaxJson getallstaff(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        ajaxJson.setObj(iPxstafftableService.getallstaff(qiyeID));
        return ajaxJson;
    }

    /**
     * 根据课程查询任教教师信息
     *
     * @param request
     * @param kechengID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/GetRenjiaoTeacher", method = RequestMethod.GET)
    @ApiOperation(value = "根据课程查询任教教师信息")
    public AjaxJson GetRenjiaoTeacher(HttpServletRequest request, long kechengID) {
        AjaxJson ajaxJson = new AjaxJson();
        Pxkechengtable pxkechengtable = iPxkechengtableService.getById(kechengID);
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setObj(iPxkechengtableService.GetRenkeTeacherList(pxkechengtable.getSubjectID(), Long.valueOf(loginUser.getQiyeID())));
        return ajaxJson;
    }

    @ResponseBody
    @RequestMapping(value = "/GetKechengContent", method = RequestMethod.GET)
    @ApiOperation(value = "根据课程ID查询课程内容信息")
    public AjaxJson GetKechengContent(HttpServletRequest request, long kechengID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setObj(iPxkechengcontenttableService.GetKechengContentList(kechengID));
        return ajaxJson;
    }

    /**
     * @Description: GetcampusStuName()方法作用:获取当前校区学员
     * @param:[request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/1/13 16:40
     */
    @ResponseBody
    @RequestMapping(value = "GetcampusStuName", method = RequestMethod.GET)
    @ApiOperation("获取当前校区学员")
    public AjaxJson GetcampusStuName(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long campusID = loginUser.getCampusID();
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        ajaxJson.setObj(iPxstutableService.GetcampusStuName(qiyeID));
        return ajaxJson;
    }

    /**
     * @Description: getxkStu()方法作用:获取学员获取补习课程
     * @param:[stuID]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/1/13 16:39
     */
    @ResponseBody
    @RequestMapping(value = "getxkStu", method = RequestMethod.GET)
    @ApiOperation("按照学员获取补习课程")
    public AjaxJson getxkStu(Long stuID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        ajaxJson.setObj(iPxstutableService.getxkStu(stuID, qiyeID));
        return ajaxJson;
    }

    /**
     * @Description: getKcToYueXiaoKe()方法作用:余额消课获取课程
     * @param:[menuID, request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/1/22 10:44
     */
    @ResponseBody
    @RequestMapping(value = "getKcToYueXiaoKe", method = RequestMethod.GET)
    @ApiOperation("余额消课获取课程")
    public AjaxJson getKcToYueXiaoKe(Long menuID, HttpServletRequest request) {
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
        ajaxJson.setObj(iPxkechengtableService.getKcToYueXiaoKe(queryWrapper));
        return ajaxJson;
    }

    /**
     * @Description: getclassTime()方法作用:获取课程时长
     * @param:[kechengID]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/1/22 10:44
     */
    @ResponseBody
    @RequestMapping(value = "getclassTime", method = RequestMethod.GET)
    @ApiOperation("获取课程时长")
    public AjaxJson getclassTime(Long kechengID) {
        AjaxJson ajaxJson = new AjaxJson();
        Long clID = iPxkechengtableService.getById(kechengID).getClassTimeStyleID();
        String classtimestylename = iPxclasstimestyletableService.getById(clID).getClasstimestylename();
        ajaxJson.setObj(classtimestylename);
        return ajaxJson;
    }

    /**
     * 查询校区教室信息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/GetAllClassRoom", method = RequestMethod.GET)
    @ApiOperation(value = "查询校区教室信息")
    public AjaxJson GetAllClassRoom(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        long campusID = loginUser.getCampusID();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", qiyeID);


        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 146);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("campusID", lookPower);
        }
        ajaxJson.setObj(iPxclassroomtableService.list(queryWrapper));
        return ajaxJson;
    }


    /**
     * 获取教师排课课表
     *
     * @param teacherIDs
     * @param yearMouth
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getpaikeShowList", method = RequestMethod.GET)
    @ApiOperation("获取教师排课课表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teacherIDs", value = "教师ID（多个教师用，隔开）", required = true),
            @ApiImplicitParam(name = "yearMouth", value = "年月", required = false),
    })
    public AjaxJson getpaikeShowList(String teacherIDs, String yearMouth, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        QueryWrapper<paikeShowVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.qiyeID", qiyeID);
        String haveClassDate = "";
        if (StringUtils.isNotBlank(yearMouth)) {
            yearMouth += "-01";
            Date mDate = null;
            try {
                mDate = df.parse(yearMouth);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            haveClassDate = df.format(mDate);
        } else {
            //获取当前日期

            haveClassDate = df.format(new Date());
        }
        if (StringUtils.isNotBlank(teacherIDs)) {
//            String[] TeaList = teacherIDs.split(",");
//            queryWrapper.in("teacherIDs", TeaList);
            queryWrapper.last(" and find_in_set(" + teacherIDs + ", a.teacherIDs)");
        }
        ajaxJson.setObj(iPxpaiketableService.getpaikeShowList(haveClassDate, queryWrapper));
        return ajaxJson;
    }

    /**
     * 查询排课详情
     *
     * @param id
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getpaikeInfo", method = RequestMethod.GET)
    @ApiOperation("查询排课详情")
    public AjaxJson getpaikeInfo(long id, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxpaiketableService.getpaikeListInfo(id));
        return ajaxJson;
    }

    /**
     * @Description: getkongxianTeacher()方法作用:按时间查询空闲老师
     * @param:[current, size, campusID, haveClassDate, starTime, endTime, TeacherName]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/25 17:14
     */
    @ResponseBody
    @RequestMapping(value = "getkongxianTeacher", method = RequestMethod.GET)
    @ApiOperation("按时间查询空闲老师")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页获取的数据量", example = "10", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true),
            @ApiImplicitParam(name = "haveClassDate", value = "上课日期", required = true),
            @ApiImplicitParam(name = "starTime", value = "上课时间", required = true),
            @ApiImplicitParam(name = "endTime", value = "下课时间", required = true),
            @ApiImplicitParam(name = "TeacherName", value = "教师名字", required = false)
    })
    public AjaxJson getkongxianTeacher(Long current, Long size, Long campusID, String haveClassDate, String starTime, String endTime, String TeacherName,
                                       HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());

        Page<haveTimeTeaVo> page = new Page<>(current, size);
        QueryWrapper<haveTimeTeaVo> queryWrapper = new QueryWrapper<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        queryWrapper.eq("a.qiyeID", qiyeID);
        if (campusID != 0) {
            queryWrapper.eq("c.id", campusID);
        }
        if (StringUtils.isNotBlank(TeacherName)) {
            queryWrapper.like("a.staffName", TeacherName);
        }

        Date haveDate = null;
        try {
            haveDate = df.parse(haveClassDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String skriqi = df.format(haveDate);//yyyy-MM-dd

        Date kaishiD = null;
        try {
            kaishiD = sdf.parse(starTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String kaishi = sdf.format(kaishiD);

        Date jiesuD = null;
        try {
            jiesuD = sdf.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String jiesu = sdf.format(jiesuD);

        List<teacherIDVo> teacherIDs = iPxpaiketableService.getTea(skriqi, kaishi, jiesu, qiyeID);

        if (teacherIDs.size() != 0) {
            if (teacherIDs.get(0) != null) {
                String[] TeaList = String.valueOf(teacherIDs.get(0).getTeaID()).split(","); //选中的日期下 有排课的老师
                queryWrapper.notIn("a.id", TeaList); //不在排课老师列表内
            }
        }

        ajaxJson.setObj(iPxstafftableService.getstaff(page, queryWrapper));
        return ajaxJson;
    }


    /**
     * @Description: getkongxianClassRoom()方法作用:按时间查询空闲教室
     * @param:[current, size, campusID, haveClassDate, starTime, endTime, classRoomName]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/28 14:13
     */
    @ResponseBody
    @RequestMapping(value = "getkongxianClassRoom", method = RequestMethod.GET)
    @ApiOperation("按时间查询空闲教室")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页获取的数据量", example = "10", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true),
            @ApiImplicitParam(name = "haveClassDate", value = "上课日期", required = true),
            @ApiImplicitParam(name = "starTime", value = "上课时间", required = true),
            @ApiImplicitParam(name = "endTime", value = "下课时间", required = true),
            @ApiImplicitParam(name = "classRoomName", value = "教室", required = false)
    })
    public AjaxJson getkongxianClassRoom(Long current, Long size, String campusID, String haveClassDate, String starTime, String endTime,
 String classRoomName, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<haveTimeCrVO> page = new Page<>(current, size);
        QueryWrapper<haveTimeTeaVo> queryWrapper = new QueryWrapper<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        queryWrapper.eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("b.id", campusID);
        }
        if (StringUtils.isNotBlank(classRoomName)) {
            queryWrapper.like("a.classRoomName", classRoomName);
        }

        Date haveDate = null;
        try {
            haveDate = df.parse(haveClassDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String skriqi = df.format(haveDate);//yyyy-MM-dd

        Date kaishiD = null;
        try {
            kaishiD = sdf.parse(starTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String kaishi = sdf.format(kaishiD);

        Date jiesuD = null;
        try {
            jiesuD = sdf.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String jiesu = sdf.format(jiesuD);

        List<paikeroomVo> havepaikeroom = iPxpaiketableService.getclassRoomList(skriqi, kaishi, jiesu, qiyeID);

        if (havepaikeroom.size() != 0) {
            if (havepaikeroom.get(0) != null) {
                String[] CRList = havepaikeroom.get(0).getClassRoomID().split(",");
                queryWrapper.notIn("a.id", CRList);
            }
        }
        ajaxJson.setObj(iPxclassroomtableService.gethavetimeclassRoomList(page, queryWrapper));
        return ajaxJson;
    }

    @ResponseBody
    @RequestMapping(value = "/DeletDateTimePaike", method = RequestMethod.DELETE)
    @ApiOperation(value = "按时间段删除排课")
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson DeletDateTimePaike(HttpServletRequest request, String startDate, String startTime, String endDate, String endTime) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
            List<Pxpaiketable> pxpaiketableList = iPxpaiketableService.getPaikelistByDate(startDate, endDate, startTime, endTime, loginUser.getQiyeID());
            for (Pxpaiketable item : pxpaiketableList) {
                List<Pxkeshistutable> pxkeshistutableList = iPxpaiketableService.GetKeshiList(item.getClassID(), item.getHaveClassDate(),
                 item.getStartLessonDateTime(), item.getEndLessonDateTime(), item.getQiyeID());
                if (pxkeshistutableList.size() == 0) {
                    QueryWrapper queryWrapper = new QueryWrapper();
                    queryWrapper.eq("paikeID", item.getId());
                    iPxpaiketeachertableService.remove(queryWrapper);
                    QueryWrapper queryWrapper1 = new QueryWrapper();
                    queryWrapper1.eq("paikeID", item.getId());
                    iPxxuanketableService.remove(queryWrapper1);
                    iPxpaiketableService.removeById(item.getId());
                }
            }
            ajaxJson.setSuccess(true);
            ajaxJson.setCode("Y");
            return ajaxJson;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ajaxJson.setMsg("操作失败");
            ajaxJson.setCode("N");
            return ajaxJson;
        }

    }

    @RequestMapping(value = "/upDateTeacherByTime", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "根据时间段修改上课教师")
    public AjaxJson upDateTeacherByTime(String startDate, String endDate, String startLessonDateTime, String endLessonDateTime, String oldteaTeidt,
     String newteaTeidt, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
//        try {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        List<Pxpaiketable> pxpaiketableList = iPxpaiketableService.GetAllPaikeNeedUpdateTeacher(startDate, endDate, startLessonDateTime, endLessonDateTime,
         loginUser.getQiyeID());
        if (pxpaiketableList.size() > 0) {
            for (Pxpaiketable onedelpk : pxpaiketableList) {
                if (onedelpk.getTeacherIDs().split(",").length > 1) {
                    for (String onetea : onedelpk.getTeacherIDs().split(",")) {
                        if (onetea == oldteaTeidt) {
                            QueryWrapper queryWrapper = new QueryWrapper();
                            queryWrapper.eq("qiyeID", qiyeID);
                            queryWrapper.eq("classID", onedelpk.getClassID());
                            queryWrapper.eq("haveClassDate", onedelpk.getHaveClassDate());
                            queryWrapper.eq("startLessonDateTime", onedelpk.getStartLessonDateTime());
                            queryWrapper.eq("endLessonDateTime", onedelpk.getEndLessonDateTime());
                            List<Pxkeshistutable> pdkqTable = iPxkeshistutableService.list(queryWrapper);
                            if (pdkqTable.size() == 0) {
                                QueryWrapper queryWrapper1 = new QueryWrapper();
                                queryWrapper1.eq("paikeID", onedelpk.getId());
                                queryWrapper1.eq("qiyeID", qiyeID);
                                List<Pxpaiketeachertable> pkTeach = iPxpaiketeachertableService.list(queryWrapper1);
                                if (pkTeach.size() > 0) {
                                    for (Pxpaiketeachertable onepkTeach : pkTeach) {
                                        onepkTeach.setTeacherID(Long.valueOf(newteaTeidt));
                                        iPxpaiketeachertableService.updateById(onepkTeach);
                                    }
                                }
                                String newlist = "";
                                String newlistteacherName = "";
                                for (String editea : onedelpk.getTeacherIDs().split(",")) {
                                    if (editea == oldteaTeidt) {
                                        if (newlist == "") {
                                            newlist = newteaTeidt;
                                            newlistteacherName = iPxstafftableService.getById(newteaTeidt).getStaffName();
                                        } else {
                                            newlist = ',' + newteaTeidt;
                                            newlistteacherName = "," + iPxstafftableService.getById(newteaTeidt).getStaffName();
                                        }
                                    } else {
                                        if (newlist == "") {
                                            newlist = editea;
                                            newlistteacherName = iPxstafftableService.getById(newteaTeidt).getStaffName();
                                        } else {
                                            newlist = ',' + editea;
                                            newlistteacherName = "," + iPxstafftableService.getById(newteaTeidt).getStaffName();
                                        }
                                    }
                                }
                                onedelpk.setTeacherIDs(newlist);
                                onedelpk.setTeacherNames(newlistteacherName);
                                iPxpaiketableService.updateById(onedelpk);
                            }
                        }
                    }
                } else {
                    if (onedelpk.getTeacherIDs().equals(oldteaTeidt)) {
                        QueryWrapper queryWrapper = new QueryWrapper();
                        queryWrapper.eq("qiyeID", qiyeID);
                        queryWrapper.eq("classID", onedelpk.getClassID());
                        queryWrapper.eq("haveClassDate", onedelpk.getHaveClassDate());
                        queryWrapper.eq("startLessonDateTime", onedelpk.getStartLessonDateTime());
                        queryWrapper.eq("endLessonDateTime", onedelpk.getEndLessonDateTime());
                        List<Pxkeshistutable> pdkqTable = iPxkeshistutableService.list(queryWrapper);
                        if (pdkqTable.size() == 0) {
                            QueryWrapper queryWrapper1 = new QueryWrapper();
                            queryWrapper1.eq("paikeID", onedelpk.getId());
                            queryWrapper1.eq("qiyeID", qiyeID);
                            Pxstafftable pxstafftable = iPxstafftableService.getById(newteaTeidt);
                            List<Pxpaiketeachertable> pkTeach = iPxpaiketeachertableService.list(queryWrapper1);
                            if (pkTeach.size() > 0) {
                                for (Pxpaiketeachertable onepkTeach : pkTeach) {
                                    onepkTeach.setTeacherID(Long.valueOf(newteaTeidt));
                                    iPxpaiketeachertableService.updateById(onepkTeach);
                                }
                                onedelpk.setTeacherIDs(newteaTeidt);
                                onedelpk.setTeacherNames(pxstafftable.getStaffName());
                                iPxpaiketableService.updateById(onedelpk);
                            }
                        }
                    }
                }
            }
        }
        ajaxJson.setSuccess(true);
        return ajaxJson;
//        } catch (Exception e) {
//            ajaxJson.setMsg("修改失败");
//            ajaxJson.setCode("N");
//            return ajaxJson;
//        }
    }

    /**
     * 排课（增减学员页面查询学生信息）
     *
     * @param classID
     * @param paikeid
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/GetClassStuList", method = RequestMethod.GET)
    @ApiOperation(value = "查询班级学生信息")
    public AjaxJson GetClassStuList(long classID, long paikeid, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        if (paikeid == 0) {
            ajaxJson.setObj(iPxstuclasstableService.getClassStuInfobyClassID(classID, loginUser.getQiyeID()));
            return ajaxJson;
        } else {
            ajaxJson.setObj(iPxstuclasstableService.getstuInfoBypaikeID(paikeid, loginUser.getQiyeID()));
            return ajaxJson;
        }
    }

    /**
     * 生成排课批次
     *
     * @return
     */
    public static String getTagByUUId() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        String time = format.format(date);
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return time + String.format("%011d", hashCodeV);
    }

    /**
     * 保存排课
     *
     * @param from
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "SavePaike", method = RequestMethod.POST)
    @ApiOperation("保存排课")
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson SavePaike(@RequestBody savepaikeFrom from, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Long staffID = Long.valueOf(loginUser.getStaffID());
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();

        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");

        Long kechengID = from.getKecheng();
        Long classN = from.getClassNum();
        //String tabtype = from.getTabtype();
        Long classRoomID = from.getClassRoomID();
        String tabdata = from.getTabdata();
        String checkPKchongtu = from.getCheckPKchongtu();
        String newStuData = from.getNewStuData();
        //String classID = from.getClassID();
        String showjiaoshi = from.getShowjiaoshi();
        Pxclasstable cla = iPxclasstableService.getById(classN);
        Pxkechengtable kecheng = iPxkechengtableService.getById(kechengID);
        String buxiStyleName = iPxbuxistyletableService.getById(kecheng.getBuxiStyleID()).getBuxiStyleName();
        List<teacherIDVo> teacherList = JSON.parseArray(showjiaoshi, teacherIDVo.class);
        if (buxiStyleName == "一对一" && teacherList.size() > 1) {
            ajaxJson.setMsg("一对一的课程只能选择一个老师");
            return ajaxJson;
        }
        String teacherIDs = "";
        String teacherNames = "";
        for (teacherIDVo itemtea : teacherList) {
            String staffName = iPxstafftableService.getById(itemtea.getTeaID()).getStaffName();
            teacherIDs += itemtea.getTeaID() + ",";
            teacherNames += staffName + ",";
        }
        String[] tList = teacherIDs.split(",");
        //转存到数据库时 以，分割

        Random rd = new Random();
        int sjs = rd.nextInt(89999) + 10000; //随机生成范围10000-99999
        // String pktags = new Date().toString() + sjs;
        // 排课的批次
        String pktags = getTagByUUId();

        List<nopaikestuDateVo> newStuDataList = JSON.parseArray(newStuData, nopaikestuDateVo.class);
        List<tabdataVo> alldata = JSON.parseArray(tabdata, tabdataVo.class);
        String datetext = "";
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        for (tabdataVo oneriqi : alldata) {
            Date shangkeriqi = null;//开始日期
            try {
                shangkeriqi = ft.parse(oneriqi.getHaveClassDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String kechengContent = oneriqi.getKechengContent();
            LocalTime startTime = LocalTime.parse(oneriqi.getStartTime());
            LocalTime endTime = LocalTime.parse(oneriqi.getEndTime());

            Time St = Time.valueOf(startTime);//上课时间
            Time Et = Time.valueOf(endTime);//下课时间


            if (St.getTime() >= Et.getTime()) {
                ajaxJson.setMsg("上课时间错误，结束时间要大于开始时间");
                ajaxJson.setCode("N");
                return ajaxJson;
            }

            //检测教师同一时间重复排课
            QueryWrapper<Pxpaiketable> pkQ = new QueryWrapper<>();
            pkQ
                    .eq("haveClassDate", shangkeriqi)
                    .eq("qiyeID", qiyeID)
                    .and(a -> a
                            .gt("startLessonDateTime", St)
                            .lt("endLessonDateTime", Et)
                            .or(b -> b
                                    .gt("startLessonDateTime", Et)
                                    .lt("endLessonDateTime", Et)).or(c -> c.eq("startLessonDateTime", St).eq("endLessonDateTime", Et)))
                    .in("teacherIDs", tList)
                    .eq("classID", classN);
            List<Pxpaiketable> pkTea = iPxpaiketableService.selectpaike(pkQ);
            if (pkTea.size() > 0) {
                ajaxJson.setMsg("相同班相同时段相同老师不能重复排课（取消冲突检测也将限制）！");
                ajaxJson.setCode("N");
                return ajaxJson;
            }

            if (checkPKchongtu != null && Integer.valueOf(checkPKchongtu) == 1) {
                //为null说明用户不需要检测排课冲突

                //检测教室是否冲突
                Pxclassroomtable classRoom = iPxclassroomtableService.getById(classRoomID);
                if (classRoom.getIschongtu() == true) {
                    QueryWrapper<Pxpaiketable> pkctQ = new QueryWrapper<>();
                    pkctQ
                            .eq("classRoomID", classRoomID)
                            .eq("haveClassDate", shangkeriqi)
                            .eq("qiyeID", qiyeID);
                    List<Pxpaiketable> pk1 = iPxpaiketableService.selectpaike(pkctQ);
                    if (pk1.size() > 0) {
                        for (Pxpaiketable onepk : pk1) {
                            if ((St.getTime() > onepk.getStartLessonDateTime().getTime() && St.getTime() < onepk.getEndLessonDateTime().getTime())
                                    || (Et.getTime() > onepk.getStartLessonDateTime().getTime() && Et.getTime() < onepk.getEndLessonDateTime().getTime())
                                    || (St.getTime() == onepk.getStartLessonDateTime().getTime() && Et.getTime() == onepk.getEndLessonDateTime().getTime())
                                    || (St.getTime() < onepk.getStartLessonDateTime().getTime() && Et.getTime() > onepk.getEndLessonDateTime().getTime())) {

                                Pxclasstable oneclass = iPxclasstableService.getById(onepk.getClassID());
                                if (oneclass == null) {
                                    //班级都不存在，说明这是一条垃圾数据：班级已经被删除了，但这个班的排课数据还在；现在把这个班的排课数据全部删除掉；
                                    QueryWrapper<Pxpaiketable> pkclaQ = new QueryWrapper<>();
                                    pkclaQ.eq("classID", onepk.getClassID()).eq("qiyeID", qiyeID);
                                    List<Pxpaiketable> claPaike = iPxpaiketableService.selectpaike(pkclaQ);
                                    for (Pxpaiketable oneclapk : claPaike) {

                                        QueryWrapper<Pxpaiketeachertable> pkteaQ = new QueryWrapper<>();
                                        pkteaQ.eq("paikeID", oneclapk.getId()).eq("qiyeID", qiyeID);
                                        List<Pxpaiketeachertable> onecpkt = iPxpaiketeachertableService.selectkehaoTeacher(pkteaQ);
                                        if (onecpkt.size() > 0) {
                                            for (Pxpaiketeachertable itemt : onecpkt) {
                                                iPxpaiketeachertableService.removeById(itemt.getId());
                                            }
                                        }
                                        iPxpaiketableService.removeById(oneclapk.getId());
                                    }

                                } else {
                                    ajaxJson.setMsg("chongtu|教室冲突：" + classRoom.getClassroomname() + "," + oneclass.getClassName() + "," + onepk.getStartLessonDateTime().toString().substring(0, 5) + "-" + onepk.getEndLessonDateTime().toString().substring(0, 5));
                                    return ajaxJson;
                                }
                            }
                        }
                    }
                }

                //检测和老师的时间是否冲突
                for (teacherIDVo itemtea : teacherList) {
                    List<Pxpaiketable> pk2 = iPxpaiketableService.getbupkTea(itemtea.getTeaID(), shangkeriqi, qiyeID);
                    if (pk2.size() > 0) {
                        for (Pxpaiketable onepk : pk2) {
                            if ((St.getTime() > onepk.getStartLessonDateTime().getTime() && St.getTime() < onepk.getEndLessonDateTime().getTime())
                                    || (Et.getTime() > onepk.getStartLessonDateTime().getTime() && Et.getTime() < onepk.getEndLessonDateTime().getTime())
                                    || (St.getTime() == onepk.getStartLessonDateTime().getTime() && Et.getTime() == onepk.getEndLessonDateTime().getTime())
                                    || (St.getTime() < onepk.getStartLessonDateTime().getTime() && Et.getTime() > onepk.getEndLessonDateTime().getTime())) {
                                Pxstafftable oneTeacherName = iPxstafftableService.getById(itemtea.getTeaID());
                                Pxclasstable oneclass = iPxclasstableService.getById(onepk.getClassID());
                                if (oneclass != null) {
                                    ajaxJson.setMsg("chongtu|和" + oneTeacherName.getStaffName() + "老师的排课冲突：" + classRoom.getClassroomname() + "," + oneclass.getClassName() + "," + onepk.getStartLessonDateTime().toString().substring(0, 5) + "-" + onepk.getEndLessonDateTime().toString().substring(0, 5));
                                    return ajaxJson;
                                } else {
                                    //班级如果为空，直接把班里的所有学员退班，而且把这个班的所有排课删除掉；
                                    QueryWrapper<Pxstuclasstable> stuclassQ = new QueryWrapper<>();
                                    stuclassQ.eq("classID", onepk.getClassID()).eq("qiyeID", qiyeID);
                                    List<Pxstuclasstable> stucla = iPxstuclasstableService.selectstuclass(stuclassQ);
                                    if (stucla.size() > 0) {
                                        for (Pxstuclasstable itemsc : stucla) {
                                            iPxstuclasstableService.removeById(itemsc.getId());
                                        }
                                    }

                                    //有排课的话，删除排课
                                    QueryWrapper<Pxpaiketable> delpk = new QueryWrapper<>();
                                    delpk
                                            .eq("classID", onepk.getClassRoomID())
                                            .eq("qiyeId", qiyeID);
                                    List<Pxpaiketable> claPaike = iPxpaiketableService.selectpaike(delpk);
                                    if (claPaike.size() > 0) {
                                        for (Pxpaiketable oneclaPaike : claPaike) {
                                            QueryWrapper<Pxpaiketeachertable> pkteaQ = new QueryWrapper<>();
                                            pkteaQ.eq("paikeID", oneclaPaike.getId()).eq("qiyeID", qiyeID);
                                            List<Pxpaiketeachertable> paikeTeachers = iPxpaiketeachertableService.selectkehaoTeacher(pkteaQ);
                                            if (paikeTeachers.size() > 0) {
                                                for (Pxpaiketeachertable itemt : paikeTeachers) {
                                                    iPxpaiketeachertableService.removeById(itemt.getId());
                                                }
                                            }
                                            iPxpaiketableService.removeById(oneclaPaike.getId());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                //检测和班里的学生已有的排课时间是否有冲突
                for (nopaikestuDateVo oneStu : newStuDataList) {
                    Long stuID = oneStu.getID();
                    //关联查询可冲突排课
                    List<Pxpaiketable> stuXuankeALL = iPxpaiketableService.getstuXuankeList(shangkeriqi, stuID, qiyeID);

                    if (stuXuankeALL.size() > 0) {
                        for (Pxpaiketable onepk : stuXuankeALL) {
                            if (St.getTime() == onepk.getStartLessonDateTime().getTime()
                                    || Et.getTime() == onepk.getEndLessonDateTime().getTime()
                                    || (Et.getTime() > onepk.getStartLessonDateTime().getTime() && Et.getTime() < onepk.getEndLessonDateTime().getTime())
                                    || (St.getTime() > onepk.getStartLessonDateTime().getTime() && St.getTime() < onepk.getEndLessonDateTime().getTime())) {
                                Pxstutable stu = iPxstutableService.getById(stuID);
                                Pxkechengtable stukecheng = iPxkechengtableService.getById(onepk.getKechengID());

                                ajaxJson.setMsg("chongtu|和学员" + stu.getStuName() + "的排课冲突:" + stukecheng.getKechengName() + "," + onepk.getStartLessonDateTime().toString().substring(0, 5) + "-" + onepk.getEndLessonDateTime().toString().substring(0, 5));
                                return ajaxJson;
                            }
                        }
                    }
                }
            }
            String weekN = DateUtil.getWeekOfDate(shangkeriqi);

            //添加排课信息
            Pxpaiketable pkT = new Pxpaiketable();
            pkT.setTeacherIDs(teacherIDs);
            pkT.setTeacherNames(teacherNames);
            pkT.setStartLessonDateTime(St);
            pkT.setEndLessonDateTime(Et);
            pkT.setHaveClassDate(shangkeriqi);
            pkT.setClassID(classN);
            pkT.setClassRoomID(classRoomID);
            pkT.setWeekN(weekN);
            pkT.setKechengID(kechengID);//课程
            pkT.setKechengContent(kechengContent);
            pkT.setTags(pktags);//排课的批次
            pkT.setDakaoqin(false);
            pkT.setQiyeID(qiyeID);
            iPxpaiketableService.save(pkT);

            //添加教师排课
            for (teacherIDVo teaID : teacherList) {
                Pxpaiketeachertable pkteaT = new Pxpaiketeachertable();
                pkteaT.setQiyeID(qiyeID);
                pkteaT.setPaikeID(pkT.getId());
                pkteaT.setTeacherID(Long.valueOf(teaID.getTeaID()));
                iPxpaiketeachertableService.save(pkteaT);
            }

            //添加学员选课
            for (nopaikestuDateVo addstu : newStuDataList) {
                Pxxuanketable onexk = new Pxxuanketable();
                onexk.setQiyeID(qiyeID);
                onexk.setStuID(addstu.getID());
                onexk.setBuxiID(addstu.getBxID());
                onexk.setType(Integer.valueOf(addstu.getType()));
                onexk.setRecordDate(new Date());
                onexk.setPaikeID(pkT.getId());
                iPxxuanketableService.save(onexk);
            }

            QueryWrapper<Pxstukaoqingtable> stukqQ = new QueryWrapper<>();
            stukqQ
                    .eq("haveclassDate", pkT.getHaveClassDate())
                    .eq("startClassDateTime", pkT.getStartLessonDateTime())
                    .eq("endClassDateTime", pkT.getEndLessonDateTime())
                    .eq("classID", pkT.getClassID())
                    .eq("teacherIDs", pkT.getTeacherIDs())
                    .eq("qiyeID", qiyeID);
            List<Pxstukaoqingtable> selectstukaoqing = iPxstukaoqingtableService.selectstukaoqing(stukqQ);
            int classStuKaoqingCount = selectstukaoqing.size();
            if (newStuDataList.size() != 0 && newStuDataList.size() <= classStuKaoqingCount && classStuKaoqingCount > 0) {
                pkT.setDakaoqin(true);
                iPxpaiketableService.updateById(pkT);
            }
        }
        //region 推送给家长、推送给老师
        String data = kecheng.getKechengName() + "," + cla.getClassName() + "，" + datetext + "。请注意查看";
        for (nopaikestuDateVo oneStu : newStuDataList) {
            long stuid = Long.valueOf(oneStu.getID());
            Pxtuisongtable pxtuisongtable = new Pxtuisongtable();
            pxtuisongtable.setKechengID(from.getKecheng());
            pxtuisongtable.setAddStaffID(loginUser.getStaffID());
            pxtuisongtable.setAddTime(new Date());
            pxtuisongtable.setAppread(0);
            pxtuisongtable.setNote("增加新的排课：" + data);
            pxtuisongtable.setRole(1);
            pxtuisongtable.setStuID(stuid);
            pxtuisongtable.setTuisongTypeName(13L);
            pxtuisongtable.setQiyeID(loginUser.getQiyeID());
            iPxtuisongtableService.save(pxtuisongtable);
            sendMessage.sendMessage(pxtuisongtable.getId());
        }
        for (teacherIDVo teaID : teacherList) {
            long teacherID = Long.valueOf(teaID.getTeaID());
            Pxtuisongtable pxtuisongtable = new Pxtuisongtable();
            pxtuisongtable.setQiyeID(loginUser.getQiyeID());
            pxtuisongtable.setRole(2);
            pxtuisongtable.setNote("增加新的排课：" + data);
            pxtuisongtable.setAppread(0);
            pxtuisongtable.setKechengID(from.getKecheng());
            pxtuisongtable.setAddTime(new Date());
            pxtuisongtable.setStaffID(teacherID);
            pxtuisongtable.setWxstate(0);
            pxtuisongtable.setTuisongTypeName(13L);
            iPxtuisongtableService.save(pxtuisongtable);
            sendMessage.sendMessage(pxtuisongtable.getId());
        }

        //endregion

        ajaxJson.setMsg("保存成功");
        return ajaxJson;
    }


    /**
     * @Description: editSavePaike()方法作用:修改排课
     * @param:[from]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/25 10:41
     */
    @ResponseBody
    @RequestMapping(value = "editSavePaike", method = RequestMethod.POST)
    @ApiOperation("修改排课")
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson editSavePaike(@RequestBody editpaikeFrom from, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Long staffID = loginUser.getStaffID();
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();

        long paikeID = from.getPaikeid();
        String newStuData = from.getEditStuData();
        Long kechengID = from.getKecheng();
        Long classID = from.getEditclass();
        String showjiaoshi = from.getShowjiaoshi();
        Long classRoomID = from.getEditclassRoomID();
        Date shangkeriqi = from.getEditenddate();

        LocalTime startTime = LocalTime.parse(from.getEditstarttime());
//        LocalDateTime localDateTimeStart = LocalDateTime.of(LocalDate.now(), startTime);
//        ZoneId zoneId = ZoneId.systemDefault();
//        Instant instantStart = localDateTimeStart.atZone(zoneId).toInstant();
        LocalTime endTime = LocalTime.parse(from.getEditendtime());
//        LocalDateTime localDateTimeEnd = LocalDateTime.of(LocalDate.now(), endTime);
//        Instant instantEnd = localDateTimeEnd.atZone(zoneId).toInstant();

        Time St = Time.valueOf(startTime);//上课时间
        Time Et = Time.valueOf(endTime);//下课时间

        String checkPKchongtuEdit = from.getCheckPKchongtuEdit();

        List<teacherIDVo> teacherList = JSON.parseArray(showjiaoshi, teacherIDVo.class);
        List<editpaikestuVo> newStuDataList = JSON.parseArray(newStuData, editpaikestuVo.class);

        String teacherIDs = "";
        String teacherNames = "";
        for (teacherIDVo itemtea : teacherList) {
            String staffName = iPxstafftableService.getById(itemtea.getTeaID()).getStaffName();
            teacherIDs += itemtea.getTeaID() + ",";
            teacherNames += staffName + ",";
        }
        String[] tList = teacherIDs.split(",");
        Pxpaiketable paike = iPxpaiketableService.getById(paikeID);
        QueryWrapper<Pxkeshistutable> khQ = new QueryWrapper<>();
        khQ
                .eq("classID", paike.getClassID())
                .eq("haveClassDate", paike.getHaveClassDate())
                .eq("startLessonDateTime", paike.getStartLessonDateTime())
                .eq("endLessonDateTime", paike.getEndLessonDateTime())
                .eq("qiyeID", qiyeID);
        List<Pxkeshistutable> pdkqTable = iPxkeshistutableService.list(khQ);
        if (pdkqTable.size() > 0) {
            ajaxJson.setMsg("原排课中存在课耗，不能修改排课");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        Pxkechengtable kecheng = iPxkechengtableService.getById(paike.getKechengID());
        String buxiStyleName = iPxbuxistyletableService.getById(kecheng.getBuxiStyleID()).getBuxiStyleName();
        if (buxiStyleName == "一对一" && showjiaoshi.length() > 1) {
            ajaxJson.setMsg("一对一的课程只能选择一个老师");
            return ajaxJson;
        }
        QueryWrapper<Pxpaiketeachertable> pkteaQ = new QueryWrapper<>();
        pkteaQ
                .eq("paikeID", paikeID)
                .eq("qiyeID", qiyeID);
        List<Pxpaiketeachertable> pkttab = iPxpaiketeachertableService.selectkehaoTeacher(pkteaQ);

        QueryWrapper<Pxxuanketable> xkQ = new QueryWrapper<>();
        xkQ
                .eq("paikeID", paikeID)
                .eq("qiyeID", qiyeID);
        List<Pxxuanketable> xkTab = iPxxuanketableService.selectxuanke(xkQ);
        for (Pxxuanketable item : xkTab) {
            iPxxuanketableService.removeById(item);
        }

        Random rd = new Random();
        int sjs = rd.nextInt(89999) + 10000; //随机生成范围10000-99999
        String pktags = new Date().toString() + sjs;//排课的批次

        //检测教师同一时间重复排课
        QueryWrapper<Pxpaiketable> ckPkQ = new QueryWrapper<>();
        ckPkQ
                .eq("haveClassDate", shangkeriqi)
                .and(a -> a
                        .gt("startLessonDateTime", St)
                        .lt("endLessonDateTime", Et)
                        .or(b -> b
                                .gt("startLessonDateTime", Et)
                                .lt("endLessonDateTime", Et))
                        .or(c -> c
                                .eq("startLessonDateTime", St)
                                .eq("endLessonDateTime", Et)))
                .eq("classID", classID)
                .eq("id", paikeID)
                .eq("teacherIDs", tList)
                .eq("qiyeID", qiyeID);
        List<Pxpaiketable> pkTea = iPxpaiketableService.selectpaike(ckPkQ);
        if (pkTea.size() > 0) {
            ajaxJson.setMsg("相同班相同时段相同老师不能重复排课（取消冲突检测也将限制）！");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        if (checkPKchongtuEdit != null && Integer.valueOf(checkPKchongtuEdit) == 1) {
            //为null说明用户不需要检测排课冲突

            //检测教室是否冲突
            Pxclassroomtable classRoom = iPxclassroomtableService.getById(classRoomID);
            if (classRoom.getIschongtu() == true) {
                QueryWrapper<Pxpaiketable> pkctQ = new QueryWrapper<>();
                pkctQ
                        .eq("classRoomID", classRoomID)
                        .eq("haveClassDate", shangkeriqi)
                        .eq("id", paikeID)
                        .eq("qiyeID", qiyeID);
                List<Pxpaiketable> pk1 = iPxpaiketableService.selectpaike(pkctQ);
                if (pk1.size() > 0) {
                    for (Pxpaiketable onepk : pk1) {
                        if ((St.getTime() > onepk.getStartLessonDateTime().getTime() && St.getTime() < onepk.getEndLessonDateTime().getTime())
                                || (Et.getTime() > onepk.getStartLessonDateTime().getTime() && Et.getTime() < onepk.getEndLessonDateTime().getTime())
                                || (St.getTime() == onepk.getStartLessonDateTime().getTime() && Et.getTime() == onepk.getEndLessonDateTime().getTime())
                        ) {

                            Pxclasstable oneclass = iPxclasstableService.getById(onepk.getClassID());
                            if (oneclass == null) {
                                //班级都不存在，说明这是一条垃圾数据：班级已经被删除了，但这个班的排课数据还在；现在把这个班的排课数据全部删除掉；
                                QueryWrapper<Pxpaiketable> pkclaQ = new QueryWrapper<>();
                                pkclaQ
                                        .eq("classID", onepk.getClassID())
                                        .eq("qiyeID", qiyeID);
                                List<Pxpaiketable> claPaike = iPxpaiketableService.selectpaike(pkclaQ);
                                if (claPaike.size() > 0) {
                                    for (Pxpaiketable oneclapk : claPaike) {

                                        QueryWrapper<Pxpaiketeachertable> pkteaQ2 = new QueryWrapper<>();
                                        pkteaQ2
                                                .eq("paikeID", oneclapk.getId())
                                                .eq("qiyeID", qiyeID);
                                        List<Pxpaiketeachertable> onecpkt = iPxpaiketeachertableService.selectkehaoTeacher(pkteaQ2);
                                        if (onecpkt.size() > 0) {
                                            for (Pxpaiketeachertable itemt : onecpkt) {
                                                iPxpaiketeachertableService.removeById(itemt.getId());
                                            }
                                        }
                                        iPxpaiketableService.removeById(oneclapk.getId());
                                    }
                                }


                            } else {
                                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                                ajaxJson.setMsg("chongtu|教室冲突：" + classRoom.getClassroomname() + "," + oneclass.getClassName() + "," + onepk.getStartLessonDateTime().toString().substring(0, 5) + "-" + onepk.getEndLessonDateTime().toString().substring(0, 5));
                                ajaxJson.setCode("N");
                                return ajaxJson;
                            }
                        }
                    }
                }
            }

            //检测和老师的时间是否冲突
            for (teacherIDVo itemtea : teacherList) {
                List<Pxpaiketable> pk2 = iPxpaiketableService.editbupkTea(itemtea.getTeaID(), shangkeriqi, paikeID, qiyeID);
                if (pk2.size() > 0) {
                    for (Pxpaiketable onepk : pk2) {
                        if ((St.getTime() > onepk.getStartLessonDateTime().getTime() && St.getTime() < onepk.getEndLessonDateTime().getTime())
                                || (Et.getTime() > onepk.getStartLessonDateTime().getTime() && Et.getTime() < onepk.getEndLessonDateTime().getTime())
                                || (St.getTime() == onepk.getStartLessonDateTime().getTime() && Et.getTime() == onepk.getEndLessonDateTime().getTime())
                        ) {
                            Pxstafftable oneTeacherName = iPxstafftableService.getById(itemtea.getTeaID());
                            Pxclasstable oneclass = iPxclasstableService.getById(onepk.getClassID());
                            if (oneclass != null) {
                                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                                ajaxJson.setMsg("chongtu|和" + oneTeacherName.getStaffName() + "老师的排课冲突：" + classRoom.getClassroomname() + "," + oneclass.getClassName() + "," + onepk.getStartLessonDateTime().toString().substring(0, 5) + "-" + onepk.getEndLessonDateTime().toString().substring(0, 5));
                                ajaxJson.setCode("N");
                                return ajaxJson;
                            } else {
                                //班级如果为空，直接把班里的所有学员退班，而且把这个班的所有排课删除掉；
                                QueryWrapper<Pxstuclasstable> stuclassQ = new QueryWrapper<>();
                                stuclassQ.eq("classID", onepk.getClassID()).eq("qiyeID", qiyeID);
                                List<Pxstuclasstable> stucla = iPxstuclasstableService.selectstuclass(stuclassQ);
                                if (stucla.size() > 0) {
                                    for (Pxstuclasstable itemsc : stucla) {
                                        iPxstuclasstableService.removeById(itemsc.getId());
                                    }
                                }

                                //有排课的话，删除排课
                                QueryWrapper<Pxpaiketable> delpk = new QueryWrapper<>();
                                delpk
                                        .eq("classID", onepk.getClassID())
                                        .eq("qiyeID", qiyeID);
                                List<Pxpaiketable> claPaike = iPxpaiketableService.selectpaike(delpk);
                                if (claPaike.size() > 0) {
                                    for (Pxpaiketable oneclaPaike : claPaike) {
                                        QueryWrapper<Pxpaiketeachertable> delpkteaQ = new QueryWrapper<>();
                                        delpkteaQ
                                                .eq("paikeID", oneclaPaike.getId())
                                                .eq("qiyeID", qiyeID);
                                        List<Pxpaiketeachertable> paikeTeachers = iPxpaiketeachertableService.selectkehaoTeacher(delpkteaQ);
                                        if (paikeTeachers.size() > 0) {
                                            for (Pxpaiketeachertable itemt : paikeTeachers) {
                                                iPxpaiketeachertableService.removeById(itemt.getId());
                                            }
                                        }
                                        iPxpaiketableService.removeById(oneclaPaike.getId());
                                    }
                                }
                            }
                        }
                    }
                }
            }

            //检测和班里的学生已有的排课时间是否有冲突
            for (editpaikestuVo oneStu : newStuDataList) {
                Long stuID = oneStu.getID();
                //关联查询可冲突排课
                QueryWrapper<Pxbuxikechengtable> bkQ = new QueryWrapper<>();
                bkQ
                        .eq("stuID", stuID)
                        .eq("isShow", 1)
                        .eq("qiyeID", qiyeID);
                List<Pxbuxikechengtable> buxikc = iPxbuxikechengtableService.selectbuxikecheng(bkQ);
                if (buxikc.size() > 0) {
                    for (Pxbuxikechengtable onebuxikc : buxikc) {
                        QueryWrapper<Pxxuanketable> chexkQ = new QueryWrapper<>();
                        chexkQ
                                .eq("id", onebuxikc.getId())
                                .eq("stuID", onebuxikc.getStuID())
                                .eq("qiyeID", qiyeID);
                        List<Pxxuanketable> stuXuankeALL = iPxxuanketableService.selectxuanke(chexkQ);
                        for (Pxxuanketable oneStuXuanke : stuXuankeALL) {
                            QueryWrapper<Pxpaiketable> chePkQ = new QueryWrapper<>();
                            chePkQ
                                    .eq("id", oneStuXuanke.getPaikeID())
                                    .eq("haveClassDate", shangkeriqi)
                                    .ne("id", paikeID)
                                    .eq("qiyeID", qiyeID);
                            List<Pxpaiketable> onepk = iPxpaiketableService.selectpaike(chePkQ);
                            if (onepk.size() > 0) {
                                if (St.getTime() == onepk.get(0).getStartLessonDateTime().getTime()
                                        || Et.getTime() == onepk.get(0).getEndLessonDateTime().getTime()
                                        || (Et.getTime() > onepk.get(0).getStartLessonDateTime().getTime() && Et.getTime() < onepk.get(0).getEndLessonDateTime().getTime())
                                        || (St.getTime() > onepk.get(0).getStartLessonDateTime().getTime() && St.getTime() < onepk.get(0).getEndLessonDateTime().getTime())
                                ) {
                                    Pxstutable stu = iPxstutableService.getById(stuID);
                                    Pxkechengtable stukecheng = iPxkechengtableService.getById(onepk.get(0).getKechengID());

                                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                                    ajaxJson.setMsg("chongtu|和学员" + stu.getStuName() + "的排课冲突:" + stukecheng.getKechengName() + "," + onepk.get(0).getStartLessonDateTime().toString().substring(0, 5) + "-" + onepk.get(0).getEndLessonDateTime().toString().substring(0, 5));
                                    ajaxJson.setCode("N");
                                    return ajaxJson;
                                }
                            }
                        }
                    }
                }
            }
        }
        iPxpaiketableService.removeById(from.getPaikeid());
        String weekN = DateUtil.getWeekOfDate(shangkeriqi);
        //添加排课信息
        Pxpaiketable pkT = new Pxpaiketable();
        pkT.setTeacherIDs(teacherIDs);
        pkT.setTeacherNames(teacherNames);
        pkT.setStartLessonDateTime(St);
        pkT.setEndLessonDateTime(Et);
        pkT.setHaveClassDate(shangkeriqi);
        pkT.setClassID(classID);
        pkT.setClassRoomID(classRoomID);
        pkT.setWeekN(weekN);
        pkT.setKechengID(kechengID);//课程
        pkT.setTags(pktags);//排课的批次
        pkT.setDakaoqin(false);
        pkT.setQiyeID(qiyeID);
        iPxpaiketableService.save(pkT);

        QueryWrapper<Pxpaiketeachertable> delpktQ = new QueryWrapper<>();
        delpktQ
                .eq("paikeID", paikeID)
                .eq("qiyeID", qiyeID);
        List<Pxpaiketeachertable> delpaiketea = iPxpaiketeachertableService.selectkehaoTeacher(delpktQ);
        for (Pxpaiketeachertable itempkt : delpaiketea) {
            iPxpaiketeachertableService.removeById(itempkt.getId());
        }

        //添加教师排课
        for (teacherIDVo teaID : teacherList) {
            Pxpaiketeachertable pkteaT = new Pxpaiketeachertable();
            pkteaT.setQiyeID(qiyeID);
            pkteaT.setPaikeID(pkT.getId());
            pkteaT.setTeacherID(Long.valueOf(teaID.getTeaID()));
            iPxpaiketeachertableService.save(pkteaT);
        }

        //添加学员选课
        for (editpaikestuVo addstu : newStuDataList) {
            Pxxuanketable onexk = new Pxxuanketable();
            onexk.setQiyeID(qiyeID);
            onexk.setStuID(addstu.getID());
            onexk.setBuxiID(addstu.getBxID());
            onexk.setType(Integer.valueOf(addstu.getType()));
            onexk.setRecordDate(new Date());
            onexk.setPaikeID(pkT.getId());
            iPxxuanketableService.save(onexk);
        }

        //region 给任课老师发微信消息-提醒排课更新
        for (teacherIDVo teaID : teacherList) {
            Long teacherID = Long.valueOf(teaID.getTeaID());
            String data = kecheng.getKechengName() + "，" + DateUtil.formatDate2(paike.getHaveClassDate()) + " "
                    + DateUtil.formatDate7(paike.getStartLessonDateTime()) + "~" + DateUtil.formatDate7(paike.getEndLessonDateTime());
            Pxtuisongtable pxtuisongtable = new Pxtuisongtable();
            pxtuisongtable.setTuisongTypeName(13L);
            pxtuisongtable.setWxstate(0);
            pxtuisongtable.setAddTime(new Date());
            pxtuisongtable.setKechengID(kecheng.getId());
            pxtuisongtable.setNote("课表更新-修改：" + data);
            pxtuisongtable.setRole(2);
            pxtuisongtable.setQiyeID(loginUser.getQiyeID());
            pxtuisongtable.setStaffID(teacherID);
            iPxtuisongtableService.save(pxtuisongtable);
            sendMessage.sendMessage(pxtuisongtable.getId());
        }

        //endregion
        ajaxJson.setMsg("保存成功");
        return ajaxJson;
    }

    @ResponseBody
    @RequestMapping(value = "/chongpaiAllPaike", method = RequestMethod.POST)
    @ApiOperation(value = "重排当前批次所有排课")
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson chongpaiAllPaike(@RequestBody chongpaiAllForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        //有排课批次信息（排除有课耗排课）
        List<Pxpaiketable> alldelPaike = iPxpaiketableService.GetChongpaiPiciList(form.getTags(), Long.valueOf(loginUser.getQiyeID()));
        if (alldelPaike.size() > 0) {
            for (Pxpaiketable onedelpk : alldelPaike) {
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("paikeID", onedelpk.getId());
                queryWrapper.eq("qiyeID", qiyeID);
                iPxpaiketeachertableService.remove(queryWrapper);

                iPxxuanketableService.remove(queryWrapper);

                iPxpaiketableService.removeById(onedelpk.getId());
            }
        }
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");

        Long kechengID = form.getKecheng();
        Long classN = form.getClassNum();
        Long classRoomID = form.getClassRoomID();
        String tabdata = form.getTabdata();
        String checkPKchongtu = form.getCheckPKchongtu();
        String newStuData = form.getNewStuData();
        String showjiaoshi = form.getShowjiaoshi();
        Pxclasstable cla = iPxclasstableService.getById(classN);
        Pxkechengtable kecheng = iPxkechengtableService.getById(kechengID);
        String buxiStyleName = iPxbuxistyletableService.getById(kecheng.getBuxiStyleID()).getBuxiStyleName();
        List<teacherIDVo> teacherList = JSON.parseArray(showjiaoshi, teacherIDVo.class);
        if (buxiStyleName == "一对一" && teacherList.size() > 1) {
            ajaxJson.setMsg("一对一的课程只能选择一个老师");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        String teacherIDs = "";
        String teacherNames = "";
        for (teacherIDVo itemtea : teacherList) {
            String staffName = iPxstafftableService.getById(itemtea.getTeaID()).getStaffName();
            teacherIDs += itemtea.getTeaID() + ",";
            teacherNames += staffName + ",";
        }
        String[] tList = teacherIDs.split(",");
        //转存到数据库时 以，分割

        List<nopaikestuDateVo> newStuDataList = JSON.parseArray(newStuData, nopaikestuDateVo.class);
        List<tabdataVo> alldata = JSON.parseArray(tabdata, tabdataVo.class);
        String datetext = "";
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        for (tabdataVo oneriqi : alldata) {
            Date shangkeriqi = null;//开始日期
            try {
                shangkeriqi = ft.parse(oneriqi.getHaveClassDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String kechengContent = oneriqi.getKechengContent();

//            LocalTime startTime = LocalTime.parse(oneriqi.getStartTime());
//            LocalDateTime localDateTimeStart = LocalDateTime.of(LocalDate.now(), startTime);
//            ZoneId zoneId = ZoneId.systemDefault();
//            Instant instantStart = localDateTimeStart.atZone(zoneId).toInstant();
//
//            LocalTime endTime = LocalTime.parse(oneriqi.getEndTime());
//            LocalDateTime localDateTimeEnd = LocalDateTime.of(LocalDate.now(), endTime);
//            Instant instantEnd = localDateTimeEnd.atZone(zoneId).toInstant();

            Time St = Time.valueOf(oneriqi.getStartTime());//上课时间
            Time Et = Time.valueOf(oneriqi.getEndTime());//下课时间

            if (St.getTime() >= Et.getTime()) {
                ajaxJson.setMsg("上课时间错误，结束时间要大于开始时间");
                ajaxJson.setCode("N");
                return ajaxJson;
            }

            //检测教师同一时间重复排课
            QueryWrapper<Pxpaiketable> pkQ = new QueryWrapper<>();
            pkQ
                    .eq("haveClassDate", shangkeriqi)
                    .eq("qiyeID", qiyeID)
                    .and(a -> a
                            .gt("startLessonDateTime", St)
                            .lt("endLessonDateTime", Et)
                            .or(b -> b
                                    .gt("startLessonDateTime", Et)
                                    .lt("endLessonDateTime", Et))
                            .or(c -> c
                                    .eq("startLessonDateTime", St)
                                    .eq("endLessonDateTime", Et)))
                    .in("teacherIDs", tList)
                    .eq("classID", classN);
            List<Pxpaiketable> pkTea = iPxpaiketableService.selectpaike(pkQ);
            if (pkTea.size() > 0) {
                ajaxJson.setMsg("相同班相同时段相同老师不能重复排课（取消冲突检测也将限制）！");
                ajaxJson.setCode("N");
                return ajaxJson;
            }

            if (checkPKchongtu != null && Integer.valueOf(checkPKchongtu) == 1) {
                //为null说明用户不需要检测排课冲突

                //检测教室是否冲突
                Pxclassroomtable classRoom = iPxclassroomtableService.getById(classRoomID);
                if (classRoom.getIschongtu() == true) {
                    QueryWrapper<Pxpaiketable> pkctQ = new QueryWrapper<>();
                    pkctQ
                            .eq("classRoomID", classRoomID)
                            .eq("haveClassDate", shangkeriqi)
                            .eq("qiyeID", qiyeID);
                    List<Pxpaiketable> pk1 = iPxpaiketableService.selectpaike(pkctQ);
                    if (pk1.size() > 0) {
                        for (Pxpaiketable onepk : pk1) {
                            if ((St.getTime() > onepk.getStartLessonDateTime().getTime() && St.getTime() < onepk.getEndLessonDateTime().getTime())
                                    || (Et.getTime() > onepk.getStartLessonDateTime().getTime() && Et.getTime() < onepk.getEndLessonDateTime().getTime())
                                    || (St.getTime() == onepk.getStartLessonDateTime().getTime() && Et.getTime() == onepk.getEndLessonDateTime().getTime())
                                    || (St.getTime() < onepk.getStartLessonDateTime().getTime() && Et.getTime() > onepk.getEndLessonDateTime().getTime())) {

                                Pxclasstable oneclass = iPxclasstableService.getById(onepk.getClassID());
                                if (oneclass == null) {
                                    //班级都不存在，说明这是一条垃圾数据：班级已经被删除了，但这个班的排课数据还在；现在把这个班的排课数据全部删除掉；
                                    QueryWrapper<Pxpaiketable> pkclaQ = new QueryWrapper<>();
                                    pkclaQ
                                            .eq("classID", onepk.getClassID())
                                            .eq("qiyeID", qiyeID);
                                    List<Pxpaiketable> claPaike = iPxpaiketableService.selectpaike(pkclaQ);
                                    for (Pxpaiketable oneclapk : claPaike) {

                                        QueryWrapper<Pxpaiketeachertable> pkteaQ = new QueryWrapper<>();
                                        pkteaQ
                                                .eq("paikeID", oneclapk.getId())
                                                .eq("qiyeID", qiyeID);
                                        List<Pxpaiketeachertable> onecpkt = iPxpaiketeachertableService.selectkehaoTeacher(pkteaQ);
                                        if (onecpkt.size() > 0) {
                                            for (Pxpaiketeachertable itemt : onecpkt) {
                                                iPxpaiketeachertableService.removeById(itemt.getId());
                                            }
                                        }
                                        iPxpaiketableService.removeById(oneclapk.getId());
                                    }

                                } else {
                                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                                    ajaxJson.setMsg("chongtu|教室冲突：" + classRoom.getClassroomname() + "," + oneclass.getClassName() + "," + onepk.getStartLessonDateTime().toString().substring(0, 5) + "-" + onepk.getEndLessonDateTime().toString().substring(0, 5));
                                    ajaxJson.setCode("N");
                                    return ajaxJson;
                                }
                            }
                        }
                    }
                }

                //检测和老师的时间是否冲突
                for (teacherIDVo itemtea : teacherList) {
                    List<Pxpaiketable> pk2 = iPxpaiketableService.getbupkTea(itemtea.getTeaID(), shangkeriqi, qiyeID);
                    if (pk2.size() > 0) {
                        for (Pxpaiketable onepk : pk2) {
                            if ((St.getTime() > onepk.getStartLessonDateTime().getTime() && St.getTime() < onepk.getEndLessonDateTime().getTime())
                                    || (Et.getTime() > onepk.getStartLessonDateTime().getTime() && Et.getTime() < onepk.getEndLessonDateTime().getTime())
                                    || (St.getTime() == onepk.getStartLessonDateTime().getTime() && Et.getTime() == onepk.getEndLessonDateTime().getTime())
                                    || (St.getTime() < onepk.getStartLessonDateTime().getTime() && Et.getTime() > onepk.getEndLessonDateTime().getTime())) {
                                Pxstafftable oneTeacherName = iPxstafftableService.getById(itemtea.getTeaID());
                                Pxclasstable oneclass = iPxclasstableService.getById(onepk.getClassID());
                                if (oneclass != null) {
                                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                                    ajaxJson.setMsg("chongtu|和" + oneTeacherName.getStaffName() + "老师的排课冲突：" + classRoom.getClassroomname() + "," + oneclass.getClassName() + "," + onepk.getStartLessonDateTime().toString().substring(0, 5) + "-" + onepk.getEndLessonDateTime().toString().substring(0, 5));
                                    ajaxJson.setCode("N");
                                    return ajaxJson;
                                } else {
                                    //班级如果为空，直接把班里的所有学员退班，而且把这个班的所有排课删除掉；
                                    QueryWrapper<Pxstuclasstable> stuclassQ = new QueryWrapper<>();
                                    stuclassQ
                                            .eq("classID", onepk.getClassID())
                                            .eq("qiyeID", qiyeID);
                                    List<Pxstuclasstable> stucla = iPxstuclasstableService.selectstuclass(stuclassQ);
                                    if (stucla.size() > 0) {
                                        for (Pxstuclasstable itemsc : stucla) {
                                            iPxstuclasstableService.removeById(itemsc.getId());
                                        }
                                    }

                                    //有排课的话，删除排课
                                    QueryWrapper<Pxpaiketable> delpk = new QueryWrapper<>();
                                    delpk
                                            .eq("classID", onepk.getClassID())
                                            .eq("qiyeId", qiyeID);
                                    List<Pxpaiketable> claPaike = iPxpaiketableService.selectpaike(delpk);
                                    if (claPaike.size() > 0) {
                                        for (Pxpaiketable oneclaPaike : claPaike) {
                                            QueryWrapper<Pxpaiketeachertable> pkteaQ = new QueryWrapper<>();
                                            pkteaQ
                                                    .eq("paikeID", oneclaPaike.getId())
                                                    .eq("qiyeID", qiyeID);
                                            List<Pxpaiketeachertable> paikeTeachers = iPxpaiketeachertableService.selectkehaoTeacher(pkteaQ);
                                            if (paikeTeachers.size() > 0) {
                                                for (Pxpaiketeachertable itemt : paikeTeachers) {
                                                    iPxpaiketeachertableService.removeById(itemt.getId());
                                                }
                                            }
                                            iPxpaiketableService.removeById(oneclaPaike.getId());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                //检测和班里的学生已有的排课时间是否有冲突
                for (nopaikestuDateVo oneStu : newStuDataList) {
                    Long stuID = oneStu.getID();
                    //关联查询可冲突排课
                    List<Pxpaiketable> stuXuankeALL = iPxpaiketableService.getstuXuankeList(shangkeriqi, stuID, qiyeID);

                    if (stuXuankeALL.size() > 0) {
                        for (Pxpaiketable onepk : stuXuankeALL) {
                            if (St.getTime() == onepk.getStartLessonDateTime().getTime()
                                    || Et.getTime() == onepk.getEndLessonDateTime().getTime()
                                    || (Et.getTime() > onepk.getStartLessonDateTime().getTime() && Et.getTime() < onepk.getEndLessonDateTime().getTime())
                                    || (St.getTime() > onepk.getStartLessonDateTime().getTime() && St.getTime() < onepk.getEndLessonDateTime().getTime())) {
                                Pxstutable stu = iPxstutableService.getById(stuID);
                                Pxkechengtable stukecheng = iPxkechengtableService.getById(onepk.getKechengID());

                                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                                ajaxJson.setMsg("chongtu|和学员" + stu.getStuName() + "的排课冲突:" + stukecheng.getKechengName() + "," + onepk.getStartLessonDateTime().toString().substring(0, 5) + "-" + onepk.getEndLessonDateTime().toString().substring(0, 5));
                                ajaxJson.setCode("N");
                                return ajaxJson;
                            }
                        }
                    }
                }
            }
            String weekN = DateUtil.getWeekOfDate(shangkeriqi);

            //添加排课信息
            Pxpaiketable pkT = new Pxpaiketable();
            pkT.setTeacherIDs(teacherIDs);
            pkT.setTeacherNames(teacherNames);
            pkT.setStartLessonDateTime(St);
            pkT.setEndLessonDateTime(Et);
            pkT.setHaveClassDate(shangkeriqi);
            pkT.setClassID(classN);
            pkT.setClassRoomID(classRoomID);
            pkT.setWeekN(weekN);
            pkT.setKechengID(kechengID);//课程
            pkT.setKechengContent(kechengContent);
            pkT.setTags(form.getTags());//排课的批次
            pkT.setDakaoqin(false);
            pkT.setQiyeID(qiyeID);
            iPxpaiketableService.save(pkT);

            //添加教师排课
            for (teacherIDVo teaID : teacherList) {
                Pxpaiketeachertable pkteaT = new Pxpaiketeachertable();
                pkteaT.setQiyeID(qiyeID);
                pkteaT.setPaikeID(pkT.getId());
                pkteaT.setTeacherID(Long.valueOf(teaID.getTeaID()));
                iPxpaiketeachertableService.save(pkteaT);
            }

            //添加学员选课
            for (nopaikestuDateVo addstu : newStuDataList) {
                Pxxuanketable onexk = new Pxxuanketable();
                onexk.setQiyeID(qiyeID);
                onexk.setStuID(addstu.getID());
                onexk.setBuxiID(addstu.getBxID());
                onexk.setType(Integer.valueOf(addstu.getType()));
                onexk.setRecordDate(new Date());
                onexk.setPaikeID(pkT.getId());
                iPxxuanketableService.save(onexk);
            }

            QueryWrapper<Pxstukaoqingtable> stukqQ = new QueryWrapper<>();
            stukqQ
                    .eq("haveclassDate", pkT.getHaveClassDate())
                    .eq("startClassDateTime", pkT.getStartLessonDateTime())
                    .eq("endClassDateTime", pkT.getEndLessonDateTime())
                    .eq("classID", pkT.getClassID())
                    .eq("teacherIDs", pkT.getTeacherIDs())
                    .eq("qiyeID", qiyeID);
            List<Pxstukaoqingtable> selectstukaoqing = iPxstukaoqingtableService.selectstukaoqing(stukqQ);
            int classStuKaoqingCount = selectstukaoqing.size();
            if (newStuDataList.size() != 0 && newStuDataList.size() <= classStuKaoqingCount && classStuKaoqingCount > 0) {
                pkT.setDakaoqin(true);
                iPxpaiketableService.updateById(pkT);
            }
        }


        //region 推送给家长和任课老师
        String data = kecheng.getKechengName() + "," + cla.getClassName() + "，" + datetext + "。请注意查看";
        for (teacherIDVo itemtea : teacherList) {
            long teacherID = Long.valueOf(itemtea.getTeaID());
            Pxtuisongtable pxtuisongtable = new Pxtuisongtable();
            pxtuisongtable.setQiyeID(loginUser.getQiyeID());
            pxtuisongtable.setRole(2);
            pxtuisongtable.setNote("课表更新通知：" + data);
            pxtuisongtable.setKechengID(kecheng.getId());
            pxtuisongtable.setAppread(0);
            pxtuisongtable.setTuisongTypeName(13L);
            pxtuisongtable.setWxstate(0);
            pxtuisongtable.setStaffID(teacherID);
            pxtuisongtable.setAddTime(new Date());
            iPxtuisongtableService.save(pxtuisongtable);
            sendMessage.sendMessage(pxtuisongtable.getId());
        }
        for (nopaikestuDateVo addstu : newStuDataList) {
            long stuID = Long.valueOf(addstu.getID());
            Pxtuisongtable pxtuisongtable = new Pxtuisongtable();
            pxtuisongtable.setQiyeID(loginUser.getQiyeID());
            pxtuisongtable.setWxstate(0);
            pxtuisongtable.setAppread(0);
            pxtuisongtable.setRole(1);
            pxtuisongtable.setKechengID(kecheng.getId());
            pxtuisongtable.setNote("课表更新：" + data);
            pxtuisongtable.setTuisongTypeName(13L);
            pxtuisongtable.setAddTime(new Date());
            iPxtuisongtableService.save(pxtuisongtable);
            sendMessage.sendMessage(pxtuisongtable.getId());
        }
        //endregion

        ajaxJson.setMsg("保存成功");
        return ajaxJson;
    }

    /**
     * @Description: Delpaike()方法作用:删除排课
     * @param:[id]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/25 11:10
     */
    @ResponseBody
    @RequestMapping(value = "Delpaike", method = RequestMethod.DELETE)
    @ApiOperation("删除排课")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "排课ID", required = true)
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson Delpaike(String id, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();

        String text = "";

        Pxpaiketable paike = iPxpaiketableService.getById(id);
        QueryWrapper<Pxxuanketable> xkQ = new QueryWrapper<>();
        xkQ
                .eq("qiyeID", qiyeID)
                .eq("paikeID", paike.getId());
        List<Pxxuanketable> xuankeL = iPxxuanketableService.selectxuanke(xkQ);
        if (paike != null && xuankeL.size() > 0) {
            Pxxuanketable xuanke = xuankeL.get(0);
            QueryWrapper<Pxkeshistutable> kehaQ = new QueryWrapper<>();
            kehaQ
                    .eq("qiyeID", qiyeID)
                    .eq("classID", paike.getClassID())
                    .eq("haveClassDate", paike.getHaveClassDate())
                    .eq("startLessonDateTime", paike.getStartLessonDateTime())
                    .eq("endLessonDateTime", paike.getEndLessonDateTime())
                    .eq("stuID", xuanke.getStuID());
            List<Pxkeshistutable> pdkqTable = iPxkeshistutableService.selectkehao(kehaQ);
            if (pdkqTable.size() > 0) {
                ajaxJson.setMsg("排课中存在课耗，不能删除排课");
                ajaxJson.setCode("N");
                return ajaxJson;
            }
            text += "删除老师:" + paike.getTeacherNames() + paike.getHaveClassDate().toString() + "," + paike.getStartLessonDateTime().toString() + "," + paike.getEndLessonDateTime().toString() + "的排课。";
        }
        iPxpaiketableService.removeById(paike.getId());

        QueryWrapper<Pxpaiketeachertable> pkteaQ = new QueryWrapper<>();
        pkteaQ
                .eq("paikeID", id)
                .eq("qiyeID", qiyeID);
        List<Pxpaiketeachertable> pkt = iPxpaiketeachertableService.selectkehaoTeacher(pkteaQ);
        if (pkt.size() > 0) {
            iPxpaiketeachertableService.remove(pkteaQ);
        }

        QueryWrapper<Pxxuanketable> delxkQ = new QueryWrapper<>();
        delxkQ
                .eq("qiyeID", qiyeID)
                .eq("paikeID", id);
        List<Pxxuanketable> xkt = iPxxuanketableService.selectxuanke(delxkQ);
        if (xkt.size() > 0) {
            iPxxuanketableService.remove(delxkQ);
        }

        if (pkt.size() > 0) {
            //region 给任课老师发微信消息-提醒排课更新
            Pxclasstable pxclasstable = iPxclasstableService.getById(paike.getClassID());
            for (String teacherID : paike.getTeacherIDs().split(",")) {
                Pxtuisongtable pxtuisongtable = new Pxtuisongtable();
                pxtuisongtable.setAddTime(new Date());
                pxtuisongtable.setTuisongTypeName(13L);
                pxtuisongtable.setNote("删除了班级：" + pxclasstable.getClassName() + DateUtil.formatDate2(paike.getHaveClassDate()) + " " + DateUtil.formatDate7(paike.getStartLessonDateTime()) + " "
                        + DateUtil.formatDate7(paike.getEndLessonDateTime()) + ",的排课");
                pxtuisongtable.setKechengID(paike.getKechengID());
                pxtuisongtable.setQiyeID(loginUser.getQiyeID());
                pxtuisongtable.setAppread(0);
                pxtuisongtable.setWxstate(0).setRole(2);
                pxtuisongtable.setStaffID(Long.valueOf(teacherID));
                pxtuisongtable.setAddStaffID(loginUser.getStaffID());
                iPxtuisongtableService.save(pxtuisongtable);
                sendMessage.sendMessage(pxtuisongtable.getId());
            }

            //endregion
        }

        ajaxJson.setMsg("删除成功");
        return ajaxJson;
    }

    /**
     * 删除所有排课
     *
     * @param oldkechengID
     * @param oldclassID
     * @param classRooID
     * @param oldteaID
     * @param tstrDate
     * @param tendDate
     * @param deltype
     * @param tags
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/DelAllpaike", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除所有排课")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldkechengID", value = "排课课程ID", required = true),
            @ApiImplicitParam(name = "oldclassID", value = "排课班级ID", required = true),
            @ApiImplicitParam(name = "classRooID", value = "教室ID", required = true),
            @ApiImplicitParam(name = "oldteaID", value = "老师ID", required = true),
            @ApiImplicitParam(name = "tstrDate", value = "开始上课时间", required = true),
            @ApiImplicitParam(name = "tendDate", value = "结束上课时间", required = true),
            @ApiImplicitParam(name = "deltype", value = "删除排课类型", required = true)
    })
    public AjaxJson DelAllpaike(long oldkechengID, long oldclassID, long classRooID, String oldteaID, String tstrDate, String tendDate, Integer deltype,
                                String tags, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        AjaxJson ajaxJson = new AjaxJson();
        try {
            String tclassName = iPxclasstableService.getById(oldclassID).getClassName();
            String tkechengName = iPxkechengtableService.getById(oldkechengID).getKechengName();
            String tuinote = "删除排课，班级：" + tclassName + ",课程：" + tkechengName + ";";
            String teacherIDs = "";
            if (deltype == 1) {
                //老排课删除
                if (StringUtils.isNotEmpty(tags))    //排课批次为空（老数据）
                {
                    QueryWrapper queryWrapper = new QueryWrapper();
                    queryWrapper.eq("qiyeID", qiyeID);
                    queryWrapper.eq("classID", oldclassID);
                    queryWrapper.eq("startLessonDateTime", tstrDate);
                    queryWrapper.eq("endLessonDateTime", tendDate);
                    List<Pxpaiketable> alldelPaike1 = iPxpaiketableService.list(queryWrapper);
                    if (alldelPaike1.size() > 0) {
                        for (Pxpaiketable onedelpk : alldelPaike1) {
                            QueryWrapper queryWrapper1 = new QueryWrapper();
                            queryWrapper1.eq("qiyeID", qiyeID);
                            queryWrapper1.eq("classID", onedelpk.getClassID());
                            queryWrapper1.eq("haveClassDate", onedelpk.getHaveClassDate());
                            queryWrapper1.eq("startLessonDateTime", onedelpk.getStartLessonDateTime());
                            queryWrapper1.eq("endLessonDateTime", onedelpk.getEndLessonDateTime());
                            List<Pxkeshistutable> pdkqTable = iPxkeshistutableService.list(queryWrapper1);
                            if (pdkqTable.size() == 0) {
                                QueryWrapper queryWrapper2 = new QueryWrapper();
                                queryWrapper2.eq("qiyeID", qiyeID);
                                queryWrapper2.eq("paikeID", onedelpk.getId());
                                List<Pxpaiketeachertable> pkTeach = iPxpaiketeachertableService.list(queryWrapper2);
                                if (pkTeach.size() > 0) {
                                    iPxpaiketeachertableService.remove(queryWrapper2);
                                }
                                QueryWrapper queryWrapper3 = new QueryWrapper();
                                queryWrapper3.eq("qiyeID", qiyeID);
                                queryWrapper3.eq("paikeID", onedelpk.getId());
                                if (pkTeach.size() > 0) {
                                    iPxxuanketableService.remove(queryWrapper3);
                                }
                                iPxpaiketableService.remove(queryWrapper);
                            }
                            tuinote += DateUtil.formatDate2(onedelpk.getHaveClassDate()) + " " + DateUtil.formatDate7(onedelpk.getStartLessonDateTime()) + " "
                                    + DateUtil.formatDate7(onedelpk.getEndLessonDateTime()) + ";";
                            teacherIDs = onedelpk.getTeacherIDs();
                        }
                    }
                } else {     //有排课批次信息
                    QueryWrapper queryWrapper = new QueryWrapper();
                    queryWrapper.eq("qiyeID", qiyeID);
                    queryWrapper.eq("tags", tags);
                    List<Pxpaiketable> alldelPaike2 = iPxpaiketableService.list(queryWrapper);
                    if (alldelPaike2.size() > 0) {
                        for (Pxpaiketable onedelpk : alldelPaike2) {
                            QueryWrapper queryWrapper1 = new QueryWrapper();
                            queryWrapper1.eq("qiyeID", qiyeID);
                            queryWrapper1.eq("classID", onedelpk.getClassID());
                            queryWrapper1.eq("haveClassDate", onedelpk.getHaveClassDate());
                            queryWrapper1.eq("startLessonDateTime", onedelpk.getStartLessonDateTime());
                            queryWrapper1.eq("endLessonDateTime", onedelpk.getEndLessonDateTime());
                            List<Pxkeshistutable> pdkqTable = iPxkeshistutableService.list(queryWrapper1);
                            if (pdkqTable.size() == 0) {
                                QueryWrapper queryWrapper2 = new QueryWrapper();
                                queryWrapper2.eq("qiyeID", qiyeID);
                                queryWrapper2.eq("paikeID", onedelpk.getId());
                                List<Pxpaiketeachertable> pkTeach = iPxpaiketeachertableService.list(queryWrapper2);
                                if (pkTeach.size() > 0) {
                                    iPxpaiketeachertableService.remove(queryWrapper2);
                                }
                                QueryWrapper queryWrapper3 = new QueryWrapper();
                                queryWrapper3.eq("qiyeID", qiyeID);
                                queryWrapper3.eq("paikeID", onedelpk.getId());
                                List<Pxxuanketable> pkXuanke = iPxxuanketableService.list(queryWrapper3);
                                if (pkXuanke.size() > 0) {
                                    iPxxuanketableService.remove(queryWrapper3);
                                }
                                iPxpaiketableService.remove(queryWrapper);
                            }
                            tuinote += DateUtil.formatDate2(onedelpk.getHaveClassDate()) + " " + DateUtil.formatDate7(onedelpk.getStartLessonDateTime()) + " "
                             + DateUtil.formatDate7(onedelpk.getEndLessonDateTime()) + ";";
                            teacherIDs = onedelpk.getTeacherIDs();
                        }
                    }
                }
            } else {
                //2同一班级同一时间段。3同一班级
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("qiyeID", qiyeID);
                if (deltype == 2) {
                    queryWrapper.eq("classID", oldclassID);
                    queryWrapper.eq("startLessonDateTime", tstrDate);
                    queryWrapper.eq("endLessonDateTime", tendDate);
                } else {
                    queryWrapper.eq("classID", oldclassID);
                }
                List<Pxpaiketable> alldelPaike2 = iPxpaiketableService.list(queryWrapper);
                if (alldelPaike2.size() > 0) {
                    for (Pxpaiketable onedelpk : alldelPaike2) {
                        QueryWrapper queryWrapper1 = new QueryWrapper();
                        queryWrapper1.eq("qiyeID", qiyeID);
                        queryWrapper1.eq("classID", onedelpk.getClassID());
                        queryWrapper1.eq("haveClassDate", onedelpk.getHaveClassDate());
                        queryWrapper1.eq("startLessonDateTime", onedelpk.getStartLessonDateTime());
                        queryWrapper1.eq("endLessonDateTime", onedelpk.getEndLessonDateTime());
                        List<Pxkeshistutable> pdkqTable = iPxkeshistutableService.list(queryWrapper1);
                        if (pdkqTable.size() == 0) {
                            QueryWrapper queryWrapper2 = new QueryWrapper();
                            queryWrapper2.eq("qiyeID", qiyeID);
                            queryWrapper2.eq("paikeID", onedelpk.getId());
                            List<Pxpaiketeachertable> pkTeach = iPxpaiketeachertableService.list(queryWrapper2);
                            if (pkTeach.size() > 0) {
                                iPxpaiketeachertableService.remove(queryWrapper2);
                            }
                            QueryWrapper queryWrapper3 = new QueryWrapper();
                            queryWrapper3.eq("qiyeID", qiyeID);
                            queryWrapper3.eq("paikeID", onedelpk.getId());
                            List<Pxxuanketable> pkXuanke = iPxxuanketableService.list(queryWrapper3);
                            if (pkXuanke.size() > 0) {
                                iPxxuanketableService.remove(queryWrapper3);
                            }
                            iPxpaiketableService.removeById(onedelpk.getId());
                        }
                        tuinote += DateUtil.formatDate2(onedelpk.getHaveClassDate()) + " " + DateUtil.formatDate7(onedelpk.getStartLessonDateTime()) + " " + DateUtil.formatDate7(onedelpk.getEndLessonDateTime()) + ";";
                        teacherIDs = onedelpk.getTeacherIDs();
                    }
                }
            }
            //region 推送课表更新通知
            for (String teacherID : teacherIDs.split(",")) {
                if (StringUtils.isNotEmpty(teacherID)) {
                    Pxtuisongtable pxtuisongtable = new Pxtuisongtable();
                    pxtuisongtable.setAddStaffID(loginUser.getStaffID());
                    pxtuisongtable.setWxstate(0);
                    pxtuisongtable.setAppread(0);
                    pxtuisongtable.setQiyeID(loginUser.getQiyeID());
                    pxtuisongtable.setNote(tuinote);
                    pxtuisongtable.setTuisongTypeName(13L);
                    pxtuisongtable.setAddTime(new Date());
                    pxtuisongtable.setRole(2);
                    pxtuisongtable.setStaffID(Long.valueOf(teacherID));
                    iPxtuisongtableService.save(pxtuisongtable);
                    sendMessage.sendMessage(pxtuisongtable.getId());
                }
            }

            //endregion
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("排课信息删除成功");
            return ajaxJson;
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("信息删除失败，请重试！");
            return ajaxJson;
        }
    }

    //endregion

    //region 课表

    //region 科目课表

    /**
     * @Description: getsubjectkebiaoList()方法作用:获取科目课表
     * @param:[subjectID, yearMouth]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/23 12:03
     */
    @ResponseBody
    @RequestMapping(value = "getsubjectkebiaoList", method = RequestMethod.GET)
    @ApiOperation("获取科目课表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "subjectID", value = "科目ID", required = true),
            @ApiImplicitParam(name = "yearMouth", value = "年月", required = false),
    })
    public AjaxJson getsubjectkebiaoList(Long subjectID, String yearMouth, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        String haveClassDate = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isNotBlank(yearMouth)) {
            yearMouth += "-01";
            Date mDate = null;
            try {
                mDate = df.parse(yearMouth);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            haveClassDate = df.format(mDate);
        } else {
            //获取当前日期
            haveClassDate = df.format(new Date());
        }
        ajaxJson.setObj(iPxpaiketableService.getsubjectkebiaoList(haveClassDate, subjectID, qiyeID));
        return ajaxJson;
    }

    @ResponseBody
    @RequestMapping(value = "/GetPrintSubjectKebiao", method = RequestMethod.GET)
    @ApiOperation(value = "查询打印的科目课表信息")
    public AjaxJson GetPrintSubjectKebiao(HttpServletRequest request, String startDate, String endDate, long subjectID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        ajaxJson.setObj(iPxpaiketableService.GetPrintSubjectKebiaoList(qiyeID, startDate, endDate, subjectID));
        return ajaxJson;
    }

    /**
     * @Description: ExportsubjectkebiaoList()方法作用:导出科目课表
     * @param:[response, subjectID, yearMouth]
     * @return:void
     * @auter:yyl
     * @data:2020/12/23 15:00
     */
    @RequestMapping(value = "ExportsubjectkebiaoList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出科目课表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "subjectID", value = "科目ID", required = true),
            @ApiImplicitParam(name = "yearMouth", value = "年月", required = false),
    })
    public void ExportsubjectkebiaoList(HttpServletResponse response, HttpServletRequest request,
                                        @RequestParam(required = false) long subjectID, // 科目ID
                                        @RequestParam(required = false) String yearMouth // 年月
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        String haveClassDate = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isNotBlank(yearMouth)) {
            yearMouth += "-01";
            Date mDate = null;
            try {
                mDate = df.parse(yearMouth);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            haveClassDate = df.format(mDate);
        } else {
            //获取当前日期
            haveClassDate = df.format(new Date());
        }
        List<subjectkebiaoVo> subjectkebiaoVos = iPxpaiketableService.ExportsubjectkebiaoList(haveClassDate, subjectID, qiyeID);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"科目ID", "科目名称", "日期", "星期", "上课时间", "下课时间", "教室", "课程", "班级", "班级人数"},
                subjectkebiaoVos,
                new String[]{"subjectID", "subjectName", "haveClassDate-D", "weekN", "startLessonDateTime-T", "endLessonDateTime-T", "classRoomName",
 "kechengName", "className", "xkstuSum"});

        try {
            String subjectName = iPxsubjecttableService.getById(subjectID).getSubjectName();
            ExportExcel.exportExcel(response, list, "Sheet1", subjectName + "科目课表导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //endregion

    //region 教师课表


    /**
     * @Description: getTeacherkebiaoList()方法作用:获取教师课表
     * @param:[teacherIDs, yearMouth]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/23 12:03
     */
    @ResponseBody
    @RequestMapping(value = "getTeacherkebiaoList", method = RequestMethod.GET)
    @ApiOperation("获取教师课表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teacherIDs", value = "教师ID", required = true),
            @ApiImplicitParam(name = "yearMouth", value = "年月", required = false),
    })
    public AjaxJson getTeacherkebiaoList(String teacherIDs, String yearMouth, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        String haveClassDate = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isNotBlank(yearMouth)) {
            yearMouth += "-01";
            Date mDate = null;
            try {
                mDate = df.parse(yearMouth);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            haveClassDate = df.format(mDate);
        } else {
            //获取当前日期
            haveClassDate = df.format(new Date());
        }
        ajaxJson.setObj(iPxpaiketableService.getTeacherkebiaoList(haveClassDate, teacherIDs, qiyeID));
        return ajaxJson;
    }


    /**
     * @Description: ExportTeacherkebiaoList()方法作用:导出教师课表
     * @param:[response, teacherIDs, yearMouth]
     * @return:void
     * @auter:yyl
     * @data:2020/12/23 15:23
     */
    @RequestMapping(value = "ExportTeacherkebiaoList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出教师课表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teacherIDs", value = "教师ID", required = true),
            @ApiImplicitParam(name = "yearMouth", value = "年月", required = false),
    })
    public void ExportTeacherkebiaoList(HttpServletResponse response, HttpServletRequest request,
                                        @RequestParam(required = false) String teacherIDs, // 教师ID
                                        @RequestParam(required = false) String yearMouth // 年月
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        String haveClassDate = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isNotBlank(yearMouth)) {
            yearMouth += "-01";
            Date mDate = null;
            try {
                mDate = df.parse(yearMouth);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            haveClassDate = df.format(mDate);
        } else {
            //获取当前日期
            haveClassDate = df.format(new Date());
        }

        List<teacherkebiaoVo> teacherkebiaoVos = iPxpaiketableService.ExportTeacherkebiaoList(haveClassDate, teacherIDs, qiyeID);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"工号", "教师姓名", "日期", "星期", "上课时间", "下课时间", "教室", "课程", "班级", "班级人数"},
                teacherkebiaoVos,
                new String[]{"teacherIDs", "teacherNames", "haveClassDate-D", "weekN", "startLessonDateTime-T", "endLessonDateTime-T", "classRoomName",
                "kechengName", "className", "ysSum"});
        try {
            String staffName = iPxstafftableService.getById(teacherIDs).getStaffName();
            ExportExcel.exportExcel(response, list, "Sheet1", staffName + "教师课表导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //endregion

    //region 学员课表

    @ResponseBody
    @RequestMapping(value = "/GetAllStuList", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有学生")
    public AjaxJson GetAllStuList(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", qiyeID);
        queryWrapper.eq("buxiStateID", 2);
        ajaxJson.setObj(iPxstutableService.list(queryWrapper));
        return ajaxJson;
    }

    /**
     * @Description: getstukebiaoList()方法作用:获取学员课表
     * @param:[stuID, yearMouth]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/23 11:19
     */
    @ResponseBody
    @RequestMapping(value = "getstukebiaoList", method = RequestMethod.GET)
    @ApiOperation("获取学员课表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学号", required = true),
            @ApiImplicitParam(name = "yearMouth", value = "年月", required = false),
    })
    public AjaxJson getstukebiaoList(Long stuID, String yearMouth, HttpServletRequest request
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        String haveClassDate = "";
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isNotBlank(yearMouth)) {
            yearMouth += "-01";
            Date mDate = null;
            try {
                mDate = df.parse(yearMouth);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            haveClassDate = df.format(mDate);
        } else {
            //获取当前日期
            haveClassDate = df.format(new Date());
        }
        ajaxJson.setObj(iPxpaiketableService.getstukebiaoList(haveClassDate, stuID, qiyeID));
        return ajaxJson;
    }


    /**
     * @Description: getstupaiketoqjInfo()方法作用:学员请假获取排课信息
     * @param:[request, size, current, stuID, haveClassDate]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/5/9 18:20
     */
    @RequestMapping(value = "getstupaiketoqjInfo", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "学员请假获取排课信息")
    public AjaxJson getstupaiketoqjInfo(HttpServletRequest request, Long size, Long current, String stuID, String haveClassDate) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Long qiyeID = loginUser.getQiyeID();
        Page<qjpaikeVo> page = new Page<>(current, size);
        QueryWrapper<qjpaikeVo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("paike.qiyeID", qiyeID)
                .eq("stu.id", stuID);
        if (StringUtils.isNotBlank(haveClassDate)) {
            if (haveClassDate.equals("全部排课")) {
                String HD = df.format(new Date());
                queryWrapper.ge("paike.haveClassDate", HD);
            } else {
                queryWrapper.eq("paike.haveClassDate", haveClassDate);
            }
        }
        ajaxJson.setObj(iPxpaiketableService.getstupaiketoqjInfo(page, Long.valueOf(stuID), queryWrapper));
        return ajaxJson;
    }

    @RequestMapping(value = "getstuqingjiaInfo", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "学员请假获取排课信息")
    public AjaxJson getstuqingjiaInfo(HttpServletRequest request, Long size, Long current, String stuID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Page<qjInfoVo> page = new Page<>(current, size);
        QueryWrapper<qjInfoVo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("a.qiyeID", qiyeID)
                .eq("a.stuid", stuID);
        ajaxJson.setObj(iPxpaiketableService.getstuqingjiaInfo(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * 判断学生的某次排课是否允许请假
     *
     * @param request
     * @param paikeID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/pdStuisQiangjia", method = RequestMethod.GET)
    public AjaxJson pdStuisQiangjia(HttpServletRequest request, Long paikeID) throws ParseException {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Pxpaiketable pxpaiketable = iPxpaiketableService.getById(paikeID);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("sysparamTypeID", 64);
        long qingjiaHours = 0;
        Pxsysparamvaluetable pxsysparamvaluetable = iPxsysparamvaluetableService.getOne(queryWrapper);
        Pxsysparamdefaulttable pxsysparamdefaulttable = iPxsysparamdefaulttableService.getById(64);
        if (pxsysparamdefaulttable == null) {
            qingjiaHours = Long.valueOf(pxsysparamdefaulttable.getDefaultValue());
        } else {
            qingjiaHours = Long.valueOf(pxsysparamvaluetable.getModifyValue());
        }
        Date nowdate = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String sss = DateUtil.formatDate4(pxpaiketable.getHaveClassDate()) + " " + formatter.format(pxpaiketable.getStartLessonDateTime());
        Date haveclasdate = ft.parse(sss);
        long xiangchaHours = (haveclasdate.getTime() - nowdate.getTime()) / (1000L * 3600L * 24L);
        if (xiangchaHours < qingjiaHours) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("超出请假允许时间，不能请假");
        } else {
            ajaxJson.setCode("Y");
        }
        return ajaxJson;
    }


    /**
     * @Description: savestuqingjia()方法作用:保存学员请假
     * @param:[request, paikeID, stuID, beizhu]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/5/9 18:20
     */
    @RequestMapping(value = "savestuqingjia", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存学员请假")
    public AjaxJson savestuqingjia(HttpServletRequest request, long paikeID, long stuID, String beizhu) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Calendar cal = Calendar.getInstance();
        Pxpaiketable pk = iPxpaiketableService.getById(paikeID);
        Pxkechengtable kc = iPxkechengtableService.getById(pk.getKechengID());

        Pxsysparamvaluetable sysvalue = iPxsysparamvaluetableService.getOne(new QueryWrapper<Pxsysparamvaluetable>()
                .eq("sysparamTypeID", 64L)
                .eq("qiyeID", qiyeID)
        );
        String modifyvalue = "";
        if (sysvalue != null) {
            modifyvalue = sysvalue.getModifyValue();
        } else {
            modifyvalue = iPxsysparamdefaulttableService.getById(64).getDefaultValue();
        }
        cal.setTime(pk.getHaveClassDate());
        cal.add(Calendar.HOUR, (-1) * Integer.valueOf(modifyvalue)); //开课前多少小时
        Date time = cal.getTime();
        if (time.getTime() < new Date().getTime()) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("请假需要在开课：" + modifyvalue + "小时前，进行操作！！！");
            return ajaxJson;
        }

        Pxqingjiatable qj = new Pxqingjiatable();
        qj.setTeacherIDs(pk.getTeacherIDs());
        qj.setTeacherNames(pk.getTeacherNames());
        qj.setKechengID(kc.getId());
        qj.setClassID(pk.getClassID());
        qj.setHaveClassDate(pk.getHaveClassDate());
        qj.setStartLessonDateTime(pk.getStartLessonDateTime());
        qj.setEndLessonDateTime(pk.getEndLessonDateTime());
        qj.setStuid(stuID);
        qj.setBeizhu(beizhu);
        qj.setQingjiaDateTime(new Date());
        qj.setQjState(1);//正常请假
        qj.setShenheState(0);
        qj.setAddDate(new Date());
        qj.setPaikeID(pk.getId());
        qj.setQiyeID(qiyeID);
        iPxqingjiatableService.save(qj);
        return ajaxJson;
    }


    /**
     * @Description: Revokestuqingjia()方法作用:保存学员请假撤销
     * @param:[request, id, cancelQjReason]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/5/9 18:20
     */
    @RequestMapping(value = "Revokestuqingjia", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存学员请假撤销")
    public AjaxJson Revokestuqingjia(HttpServletRequest request, String id, String cancelQjReason) {
        AjaxJson ajaxJson = new AjaxJson();
//        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dftime = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar cal = Calendar.getInstance();

        Pxqingjiatable qj = iPxqingjiatableService.getById(id);
        String HD = df.format(qj.getHaveClassDate());
        String st = dftime.format(qj.getStartLessonDateTime());
        String nDate = sdf.format(new Date());

        Date ND = null;
        try {
            ND = sdf.parse(nDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date HT = null;
        try {
            HT = sdf.parse(HD + " " + st);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Pxsysparamvaluetable sysvalue = iPxsysparamvaluetableService.getOne(new QueryWrapper<Pxsysparamvaluetable>()
                .eq("sysparamTypeID", 64L)
                .eq("qiyeID", qj.getQiyeID())
        );
        String modifyvalue = "";
        if (sysvalue != null) {
            modifyvalue = sysvalue.getModifyValue();
        } else {
            modifyvalue = iPxsysparamdefaulttableService.getById(64).getDefaultValue();
        }
        cal.setTime(HT);
        cal.add(Calendar.HOUR, (-1) * Integer.valueOf(modifyvalue)); //开课前多少小时
        Date time = cal.getTime();
        if (time.getTime() < ND.getTime()) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("撤销请假需要在开课：" + modifyvalue + "小时前，进行操作！！！");
            return ajaxJson;
        }

        qj.setQjState(2);
        qj.setCancelQjDateTime(new Date());
        qj.setCancelQjReason(cancelQjReason);
        ajaxJson.setSuccess(iPxqingjiatableService.updateById(qj));
        return ajaxJson;
    }


    /**
     * @Description: ExportstukebiaoList()方法作用:学员课表
     * @param:[response, stuID, yearMouth]
     * @return:void
     * @auter:yyl
     * @data:2020/12/23 15:40
     */
    @RequestMapping(value = "ExportstukebiaoList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出学员课表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学号", required = true),
            @ApiImplicitParam(name = "yearMouth", value = "年月", required = false),
    })
    public void ExportstukebiaoList(HttpServletResponse response, HttpServletRequest request,
                                    @RequestParam(required = false) long stuID, // 科目ID
                                    @RequestParam(required = false) String yearMouth // 年月
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        String haveClassDate = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isNotBlank(yearMouth)) {
            yearMouth += "-01";
            Date mDate = null;
            try {
                mDate = df.parse(yearMouth);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            haveClassDate = df.format(mDate);
        } else {
            //获取当前日期
            haveClassDate = df.format(new Date());
        }
        List<stukebiaoVO> stukebiaoVOS = iPxpaiketableService.ExportstukebiaoList(haveClassDate, stuID, qiyeID);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"学号", "姓名", "日期", "星期", "上课时间", "下课时间", "教室", "课程", "班级", "任课老师"},
                stukebiaoVOS,
                new String[]{"stuID", "stuName", "haveClassDate-D", "weekN", "startLessonDateTime-T", "endLessonDateTime-T", "classRoomName", "kechengName",
                        "className", "teacherNames"});
        try {
            String stuName = iPxstutableService.getById(stuID).getStuName();
            ExportExcel.exportExcel(response, list, "Sheet1", stuName + "学员课表导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //endregion

    //region 教室课表

    @ResponseBody
    @RequestMapping(value = "/GetAllClassroomList", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有教室信息")
    public AjaxJson GetAllClassroomList(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        long campusID = loginUser.getCampusID();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", qiyeID);
        ajaxJson.setObj(iPxclassroomtableService.list(queryWrapper));
        return ajaxJson;
    }

    /**
     * @Description: getjiaoshikebiaoList()方法作用:getjiaoshikebiaoList
     * @param:[classRoomID, yearMouth]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/23 10:43
     */
    @ResponseBody
    @RequestMapping(value = "getjiaoshikebiaoList", method = RequestMethod.GET)
    @ApiOperation("获取教室课表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classRoomID", value = "教室ID", required = true),
            @ApiImplicitParam(name = "yearMouth", value = "年月", required = false),
    })
    public AjaxJson getjiaoshikebiaoList(long classRoomID, String yearMouth, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        String haveClassDate = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isNotBlank(yearMouth)) {
            yearMouth += "-01";
            Date mDate = null;
            try {
                mDate = df.parse(yearMouth);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            haveClassDate = df.format(mDate);
        } else {
            //获取当前日期
            haveClassDate = df.format(new Date());
        }
        ajaxJson.setObj(iPxpaiketableService.getjiaoshikebiaoList(haveClassDate, classRoomID, qiyeID));
        return ajaxJson;
    }


    /**
     * @Description: ExportjiaoshikebiaoList()方法作用:导出教室课表
     * @param:[response, classRoomID, yearMouth]
     * @return:void
     * @auter:yyl
     * @data:2020/12/23 15:49
     */
    @RequestMapping(value = "ExportjiaoshikebiaoList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出教室课表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classRoomID", value = "学号", required = true),
            @ApiImplicitParam(name = "yearMouth", value = "年月", required = false),
    })
    public void ExportjiaoshikebiaoList(HttpServletResponse response, HttpServletRequest request,
                                        @RequestParam(required = false) long classRoomID,
                                        @RequestParam(required = false) String yearMouth // 年月
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        String haveClassDate = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isNotBlank(yearMouth)) {
            yearMouth += "-01";
            Date mDate = null;
            try {
                mDate = df.parse(yearMouth);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            haveClassDate = df.format(mDate);
        } else {
            //获取当前日期
            haveClassDate = df.format(new Date());
        }
        List<jiaoshikebiaoVO> jiaoshikebiaoVOs = iPxpaiketableService.ExportjiaoshikebiaoList(haveClassDate, classRoomID, qiyeID);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"教室ID", "教室", "日期", "星期", "上课时间", "下课时间", "课程", "班级", "任课老师"},
                jiaoshikebiaoVOs,
                new String[]{"classRoomID", "classRoomName", "haveClassDate-D", "weekN", "startLessonDateTime-T", "endLessonDateTime-T", "kechengName",
                        "className", "teacherNames"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "导出教室课表.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion

    //region 天课表


    /**
     * @Description: getDaykebiaopage()方法作用:分页获取天课表
     * @param:[current, size, campusID, teachName, haveClassDate, className]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/22 18:15
     */
    @ResponseBody
    @RequestMapping(value = "getDaykebiaopage", method = RequestMethod.GET)
    @ApiOperation("分页获取天课表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页获取数据量", example = "10", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "teachName", value = "教师", required = false),
            @ApiImplicitParam(name = "StartDate", value = "开始日期", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束日期", required = false),
            @ApiImplicitParam(name = "className", value = "班级", required = false),
    })
    public AjaxJson getDaykebiaopage(Long current, Long size, String campusID, String teachName, String StartDate, String endDate, String className,
 HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<daykebiaoVo> page = new Page<>(current, size);
        QueryWrapper<daykebiaoVo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("1", 1)
                .eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(StartDate) && StringUtils.isNotBlank(endDate)) {
            queryWrapper.ge("a.haveClassDate", StartDate).le("a.haveClassDate", endDate);
        } else {
            //获取当前日期
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            queryWrapper.eq("a.haveClassDate", df.format(new Date()));
        }
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("c.campusID", campusID);
        }
        if (StringUtils.isNotBlank(teachName)) {
            queryWrapper.like("a.teacherNames", teachName);
        }
        if (StringUtils.isNotBlank(className)) {
            queryWrapper.like("b.className", className);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 242);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("c.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("c.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("c.campusID", lookPower);
        }
        ajaxJson.setObj(iPxpaiketableService.getDaykebiaopage(page, queryWrapper));
        return ajaxJson;
    }


    /**
     * @Description: ExportdaykebiaoList()方法作用:导出天课表
     * @param:[response, campusID, startDate, endDate]
     * @return:void
     * @auter:yyl
     * @data:2020/12/23 16:15
     */
    @RequestMapping(value = "ExportdaykebiaoList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出天课表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "startDate", value = "开始时间", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束时间", required = false),
    })
    public void ExportdaykebiaoList(HttpServletResponse response, HttpServletRequest request,
                                    @RequestParam(required = false) String campusID, // 校区ID
                                    @RequestParam(required = false) String startDate, // 开始日期
                                    @RequestParam(required = false) String endDate // 结束日期
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper<daykebiaoVo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("1", 1)
                .eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("c.campusID", campusID);
        }

        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("a.haveClassDate", startDate);
        }

        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("a.haveClassDate", endDate);
        }
        List<daykebiaoVo> daykebiaoVos = iPxpaiketableService.ExportdaykebiaoList(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "上课日期", "周", "上课时间", "下课时间", "课程名称", "教师", "班级名称", "上课学生", "教室"},
                daykebiaoVos,
                new String[]{"campusName", "haveClassDate", "weekN", "startLessonDateTime-T", "endLessonDateTime-T", "kechengName", "teacherNames",
                "className", "stuName", "classRoomName"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "天课表导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion

    //endregion

    //region 人工消课


    /**
     * @Description: getrengongxiaokePage()方法作用:分页获取人工消课
     * @param:[current, size, campusID, teacherNames, haveClassDate, className]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/18 10:20
     */
    @ResponseBody
    @RequestMapping(value = "getrengongxiaokePage", method = RequestMethod.GET)
    @ApiOperation("分页获取人工消课")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据量", example = "10", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "teacherNames", value = "任课教师", required = false),
            @ApiImplicitParam(name = "startDate", value = "开始上课日期", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束上课日期", required = false),
            @ApiImplicitParam(name = "className", value = "班级名称", required = false),
            @ApiImplicitParam(name = "kaoqing", value = "排课的考勤状态 -1：（默认）全部 1：完成 0：未考勤", example = "-1", required = false),
    })
    public AjaxJson getrengongxiaokePage(Long current,
                                         Long size,
                                         String campusID,
                                         String teacherNames,
                                         String startDate,
                                         String endDate,
                                         String className,
                                         int kaoqing,
                                         HttpServletRequest request
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        AjaxJson ajaxJson = new AjaxJson();
        Page<rengongxiaokeVo> page = new Page<>(current, size);
        QueryWrapper<rengongxiaokeVo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("1", 1)
                .eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {
            queryWrapper.ge("a.haveClassDate", startDate).le("a.haveClassDate", endDate);
        } else {
            //获取当前日期
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            queryWrapper.eq("a.haveClassDate", df.format(new Date()));
        }
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("b.campusID", campusID);
        }
        if (StringUtils.isNotBlank(teacherNames)) {
            queryWrapper.like("a.teacherNames", teacherNames);
        }
        if (StringUtils.isNotBlank(className)) {
            queryWrapper.like("b.className", className);
        }
        if (kaoqing != -1) {
            if (kaoqing == 1) {
                queryWrapper.eq("a.dakaoqin", true);
            } else if (kaoqing == 0) {
                queryWrapper.eq("a.dakaoqin", false);
            }
        }
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 243);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("b.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("b.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("b.campusID", lookPower);
        }

        ajaxJson.setObj(iPxpaiketableService.getrengongxiaokePage(page, queryWrapper));
        return ajaxJson;
    }


    /**
     * @Description: Exportrengongxiaoke()方法作用:导出人工消课
     * @param:[response, campusID, startDate, endDate]
     * @return:void
     * @auter:yyl
     * @data:2020/12/18 11:18
     */
    @RequestMapping(value = "Exportrengongxiaoke", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出人工消课")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "startDate", value = "开始时间", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束时间", required = false),
    })
    public void Exportrengongxiaoke(HttpServletResponse response, HttpServletRequest request,
                                    @RequestParam(required = false) String campusID, // 校区ID
                                    @RequestParam(required = false) String startDate, // 开始日期
                                    @RequestParam(required = false) String endDate // 结束日期
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("1", 1);
        queryWrapper.eq("a.qiyeID", qiyeID);

        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("b.campusID", campusID);
        }
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("a.haveClassDate", startDate);
        }
        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("a.haveClassDate", endDate);
        }
        List<rengongxiaokeVo> exportrengongxiaoke = iPxpaiketableService.Exportrengongxiaoke(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "任课教师", "星期", "上课日期", "上课时间", "下课时间", "班级", "上课教室", "应上人数", "考勤人数", "考勤状态"},
                exportrengongxiaoke,
                new String[]{"campusName", "teacherNames", "weekN", "haveClassDate-D", "startLessonDateTime-T", "endLessonDateTime-T", "className",
                "classRoomName", "yshangcount", "havekaoqingStuNum", "dakaoqin"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "人工消课导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* 去除尾部，符号*/
    public String delsub(String st) {
        String str = "";
        String last = st.substring(st.length() - 1, st.length());
        if (last == ",") {
            str = st.substring(0, st.length() - 1);
        } else {
            str = st;
        }
        return str;
    }

    //IsHavePaike 检测排课
    @ResponseBody
    @RequestMapping(value = "IsHavePaike", method = RequestMethod.GET)
    @ApiOperation("检测排课")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "haveclassDate", value = "消课日期", required = true),
            @ApiImplicitParam(name = "startLessonDateTime", value = "开始时间", required = true),
            @ApiImplicitParam(name = "endLessonDateTime", value = "结束时间", required = true),
            @ApiImplicitParam(name = "classID", value = "班级ID", required = true),
    })
    public AjaxJson IsHavePaike(String teacher,
                                String haveclassDate,
                                String startLessonDateTime,
                                String endLessonDateTime,
                                Long classID,
                                HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        String st = "[";
        String[] tList = teacher.split(",");
        for (String its : tList) {
            st += "{teaID:" + teacher + "},";
        }
        st += "]";
        List<teacherIDVo> teacherIDs = JSON.parseArray(st, teacherIDVo.class);

        String teaIDS = "";
        String msg = "";
        String ret = "";

        String[] dateList = haveclassDate.split(",");
        if (teacherIDs.size() > 1) {  //多个老师
            for (teacherIDVo item : teacherIDs) {
                teaIDS += item.getTeaID() + ",";
            }
            if (dateList.length == 1) {
                QueryWrapper<Pxpaiketable> pkQ = new QueryWrapper<>();
                pkQ
                        .eq("qiyeID", qiyeID)
                        .eq("classid", classID)
                        .eq("haveclassdate", haveclassDate)
                        .ge("startlessondatetime", startLessonDateTime)
                        .le("endlessondatetime", endLessonDateTime)
                        .ne("teacherids", teaIDS);
                List<Pxpaiketable> getpkList = iPxpaiketableService.selectpaike(pkQ);
                if (getpkList.size() > 0) {
                    msg = "have";
                    ret = "当前课程、班级、上课时间、上课日期 在" + haveclassDate.toString() + "已有排课，请选择排正确的排课老师进行不排课消课或者选择按排课消课！";
                    ajaxJson.setMsg(ret);
                } else {
                    msg = "Nohave";
                    ret = "没有冲突";
                    ajaxJson.setMsg(ret);
                }
            } else {
                for (String itemd : dateList) {
                    QueryWrapper<Pxpaiketable> pkmored = new QueryWrapper<>();
                    pkmored
                            .eq("qiyeID", qiyeID)
                            .eq("classid", classID)
                            .eq("haveclassdate", itemd)
                            .ge("startlessondatetime", startLessonDateTime)
                            .le("endlessondatetime", endLessonDateTime)
                            .ne("teacherids", teaIDS);
                    List<Pxpaiketable> getpkList = iPxpaiketableService.selectpaike(pkmored);
                    if (getpkList.size() > 0) {
                        msg = "have";
                        ret = "当前课程、班级、上课时间、上课日期 在" + itemd.toString() + "已有排课，请选择排正确的排课老师进行不排课消课或者选择按排课消课！";
                        ajaxJson.setMsg(ret);
                    } else {
                        msg = "Nohave";
                        ret = "没有冲突";
                        ajaxJson.setMsg(ret);
                    }
                }
            }

        } else {
            long teaID1 = teacherIDs.get(0).getTeaID();
            //单个老师
            if (dateList.length == 1) {  //单个日期
                long teaID = teacherIDs.get(0).getTeaID();
                QueryWrapper<Pxpaiketable> pkQ2 = new QueryWrapper<>();
                pkQ2
                        .eq("qiyeID", qiyeID)
                        .eq("classid", classID)
                        .eq("haveclassdate", haveclassDate)
                        .eq("startlessondatetime", startLessonDateTime)
                        .eq("endlessondatetime", endLessonDateTime)
                        .and(i -> i.ne("teacherids", teaID1).or(b -> b.ne("teacherids", teaID1 + ',')));
                List<Pxpaiketable> getpkList2 = iPxpaiketableService.selectpaike(pkQ2);
                if (getpkList2.size() > 0) {
                    msg = "have";
                    ret = "当前课程、班级、上课时间、上课日期 在" + dateList[0] + "已有排课，请选择排正确的排课老师进行不排课消课或者选择按排课消课！";
                    ajaxJson.setMsg(ret);
                } else {
                    msg = "Nohave";
                    ret = "没有冲突";
                    ajaxJson.setMsg(ret);
                }
            } else {
                //多日期
                for (String itemd : dateList) {
                    QueryWrapper<Pxpaiketable> pkmored = new QueryWrapper<>();
                    pkmored
                            .eq("qiyeID", qiyeID)
                            .eq("classid", classID)
                            .eq("haveclassdate", itemd)
                            .ge("startlessondatetime", startLessonDateTime)
                            .le("endlessondatetime", endLessonDateTime)
                            .and(i -> i.ne("teacherids", teaID1).or(b -> b.ne("teacherids", teaID1 + ',')));
                    List<Pxpaiketable> getpkList = iPxpaiketableService.selectpaike(pkmored);
                    if (getpkList.size() > 0) {
                        msg = "have";
                        ret = "当前课程、班级、上课时间、上课日期 在" + itemd + "已有排课，请选择排正确的排课老师进行不排课消课或者选择按排课消课！";
                        ajaxJson.setMsg(ret);
                    } else {
                        msg = "Nohave";
                        ret = "没有冲突";
                        ajaxJson.setMsg(ret);
                    }
                }
            }
        }

        return ajaxJson;
    }

    /**
     * @Description: StuNoPaike()方法作用:按不排课消课，把班级学生及考勤返回
     * @param:[haveclassDate, startLessonDateTime, endLessonDateTime, classID]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/1/15 11:30
     */
    @ResponseBody
    @RequestMapping(value = "StuNoPaike", method = RequestMethod.GET)
    @ApiOperation("按不排课消课，把班级学生及考勤返回")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "haveclassDate", value = "消课日期", required = true),
            @ApiImplicitParam(name = "startLessonDateTime", value = "开始时间", required = true),
            @ApiImplicitParam(name = "endLessonDateTime", value = "结束时间", required = true),
            @ApiImplicitParam(name = "classID", value = "班级ID", required = true),
    })
    public AjaxJson StuNoPaike(HttpServletRequest request, String haveclassDate, String startLessonDateTime, String endLessonDateTime, Long classID,
 String stuName) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotEmpty(stuName)) {
            queryWrapper.like("f.stuName", stuName);
        }
        ajaxJson.setObj(iPxbuxikechengtableService.getStuNoPaike(haveclassDate, startLessonDateTime, endLessonDateTime, classID, queryWrapper));
        return ajaxJson;
    }

    /**
     * @Description: StuNoPaike()方法作用:获取自由学生（添加的学生）
     * @param:[stuID, buxiID]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/1/14 16:13
     */
    @ResponseBody
    @RequestMapping(value = "getzyStu", method = RequestMethod.GET)
    @ApiOperation("获取自由学生（添加的学生）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true),
            @ApiImplicitParam(name = "buxiID", value = "补习ID", required = true),
    })
    public AjaxJson StuNoPaike(Long stuID, Long buxiID) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxbuxikechengtableService.getaddstuList(stuID, buxiID));
        return ajaxJson;
    }


    /**
     * @Description: savestukehao()方法作用:不排课消课——保存课时消耗
     * @param:[from]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/22 16:50
     */
    @ResponseBody
    @RequestMapping(value = "savestukehao", method = RequestMethod.POST)
    @ApiOperation("不排课消课——保存课时消耗")
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson savestukehao(
            @RequestBody NopaikexiaokeFrom from,
            HttpServletRequest request
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());
        String staffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();

        BigDecimal l = new BigDecimal(0);
        BigDecimal y = new BigDecimal(1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Calendar cal = Calendar.getInstance();


        Long classidStr = from.getClassidStr(); //班级
        String newstu = from.getNewstu();  //学员信息
        Date shangkedate = from.getShangkedate();//上课日期
        String shangketeacher = from.getShangketeacher(); //教师ID,可能有多个教师
        List<teacherIDVo> temp = JSON.parseArray(shangketeacher, teacherIDVo.class);

        String teaIDs = "";  //转存到数据库中
        String teacherNames = "";
        for (teacherIDVo item : temp) {
            if (teaIDs == "") {
                teaIDs = item.getTeaID() + "";
                teacherNames = iPxstafftableService.getById(item.getTeaID()).getStaffName();
            } else {
                teaIDs += item.getTeaID() + ",";
                teacherNames += iPxstafftableService.getById(item.getTeaID()).getStaffName();
            }
        }

        //多个教师的列表
        String[] teacherIDs = teaIDs.split(",");

        boolean remainksCanIsFushu = true;
        //剩余课时可以扣减为负数
        String sysParam63 = iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 63L) == null ?
iPxsysparamdefaulttableService.getById(63).getDefaultValue() : iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 63L).getModifyValue();


        if (sysParam63 != null && sysParam63 == "否") {
            remainksCanIsFushu = false;//剩余课时不可以扣减为负数
        }


        LocalTime st = LocalTime.parse(from.getStartTime());
        LocalTime et = LocalTime.parse(from.getEndTime());

        Time sTime = Time.valueOf(st);
        Time eTime = Time.valueOf(et);

        String zhujiaoteacher = from.getZhujiaoteacher() == null ? "" : from.getZhujiaoteacher();
        Long kechengID = from.getKechengID();
        BigDecimal teacherkeshiNum = from.getTeacherkeshiNum(); //计入教师课时
        String shuoming = from.getShuoming() == null ? "" : from.getShuoming();
        String allriqi = from.getAllriqi();

        String tkaoqinstyle = "正常";


        //转换格式，出去时分秒带来的影响
        Date mintady = null; //上课日期（日期类型）
        try {
            mintady = sdf.parse(sdf.format(shangkedate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date t2 = null; //当前日期
        try {
            t2 = sdf.parse(sdf.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (StringUtils.isEmpty(newstu)) {
            ajaxJson.setMsg("请至少勾选一名学员");
            ajaxJson.setCode("N");
            return ajaxJson;
        }

        String tianshu = iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 41L) == null ?
iPxsysparamdefaulttableService.getById(41).getDefaultValue() : iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 41L).getModifyValue();
        //课时消耗限制（天）


        cal.setTime(t2);
        cal.add(Calendar.DATE, (-1) * Integer.valueOf(tianshu)); //计算课时消耗限制的时间（当前日期之前）
        if (mintady.getTime() < cal.getTime().getTime()) {
            ajaxJson.setMsg("不能录入" + tianshu + "天前的课时消耗");
            ajaxJson.setCode("N");
            return ajaxJson;
        }

        if (mintady.getTime() > t2.getTime()) {
            ajaxJson.setMsg("上课日期不能大于今天");
            ajaxJson.setCode("N");
            return ajaxJson;
        }

        List allnianji = new ArrayList();
        List khids = new ArrayList();
        if (eTime.getTime() <= sTime.getTime()) {
            ajaxJson.setMsg("上课的结束日期要大于开始日期");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        Pxkechengtable kc = iPxkechengtableService.getById(kechengID);
        String buxiStyleName = iPxbuxistyletableService.getById(kc.getBuxiStyleID()).getBuxiStyleName();
        Pxclasstable cla = iPxclasstableService.getById(classidStr);
        String stumessage = "";

        List<NopaikeStuVo> stustr = JSON.parseArray(newstu, NopaikeStuVo.class); //学员信息


        String[] alldate = allriqi.split(",");

        for (String onedata : alldate) {
            Date adddate = null;
            try {
                adddate = sdf.parse(onedata);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String week = DateUtil.getWeekOfDate(adddate); //星期

            QueryWrapper<Pxstukaoqingtable> stukqQ = new QueryWrapper<>();
            stukqQ
                    .eq("qiyeID", qiyeID)
                    .eq("haveclassDate", adddate)
                    .eq("startClassDateTime", sTime)
                    .eq("endClassDateTime", eTime)
                    .in("teacherIDs", teaIDs);
            List<Pxstukaoqingtable> StuKaoqingedCount = iPxstukaoqingtableService.selectstukaoqing(stukqQ);

            //region 不排课消课先查询是否有排课，没有则班级人数为勾选的人数，有则班级人数为勾选的人数加上去重后的选课人数
            List newStuIDList = new ArrayList();
//            for(NopaikeStuVo item:stustr){
//
//            }
            int classStuCount = 0;
            Long classID = classidStr;
            QueryWrapper<Pxpaiketable> pkQ = new QueryWrapper<>();
            pkQ
                    .eq("qiyeID", qiyeID)
                    .eq("classID", classID)
                    .eq("haveClassDate", adddate)
                    .eq("startLessonDateTime", sTime)
                    .eq("endLessonDateTime", eTime)
                    .in("teacherIDs", teaIDs);
            List<Pxpaiketable> selectpaike = iPxpaiketableService.selectpaike(pkQ);
            int stuCount = 0;
            if (selectpaike.size() > 0) {
                Pxpaiketable pdpaikeTable1 = selectpaike.get(0);
                List<teacherIDVo> teacherIDVos = iPxxuanketableService.gexkStuBypkID(pdpaikeTable1.getId());
                long xk1 = teacherIDVos.get(0).getTeaID();


            }
            //endregion

            for (NopaikeStuVo item : stustr) {
                Long stusharebuxiID = null;
                BigDecimal sharekehao = new BigDecimal(0);
                String teacherKSID = "";
                Long stuidss = item.getStuID(); //学号
                String stuKaoqingStyle = item.getKaoqing();//考勤状态
                BigDecimal stukeshiNum = item.getKeshi();//消课课时
                String buxiID = item.getBuxiID();//补习ID --》手动添加进排课的学员才有，其他为0

                Pxstutable stuInfo = iPxstutableService.getById(stuidss); //学员
                String StuNo = stuInfo.getZidingyiStuID() == null ? stuidss.toString() : stuInfo.getZidingyiStuID();
                String xiugaiInfo = "学号：" + StuNo + ",姓名：" + stuInfo.getStuName() + "***后台【不排课消课】，班级：" + cla.getClassName() + ",课程：" + kc.getKechengName() +
",总人数" + classStuCount;

                //region
                Pxbuxikechengtable buxikecheng = buxiID.equals("0") ? iPxbuxikechengtableService.getbuxiBystuAcla(stuidss, classID).get(0) :
iPxbuxikechengtableService.getById(buxiID);

                if (buxikecheng == null) {
                    ajaxJson.setMsg(stuInfo.getStuName() + "，课程找不到（未启用或不存在）");
                    return ajaxJson;
                }

                Integer jfstyle = buxikecheng.getJifeiStyleID(); //计费方式
                Long stuKechengID = buxikecheng.getKechengID();
                Pxkechengtable stukecheng = iPxkechengtableService.getById(stuKechengID);
                xiugaiInfo += ",该学员在这个班里的课程名称：" + stukecheng.getKechengName() + "，计费方式" + jfstyle + "，本次消课时：" + stukeshiNum.toString();

                //看课耗表是否有消耗
                QueryWrapper<Pxkeshistutable> keshiQ = new QueryWrapper<>();
                keshiQ
                        .eq("qiyeID", qiyeID)
                        .eq("stuID", stuidss)
                        .eq("haveClassDate", adddate)
                        .eq("startLessonDateTime", sTime)
                        .eq("endLessonDateTime", eTime)
                        .eq("kechengID", buxikecheng.getKechengID())
                        .eq("classID", classID);
                List<Pxkeshistutable> stukehao = iPxkeshistutableService.selectkehao(keshiQ);
                //如果有消耗 如果有课耗并且是旷课，返回错误 就不管 返回已刷过卡
                if (stukehao.size() > 0) {
                    ajaxJson.setMsg("已有记录");
                    ajaxJson.setCode("N");
                    return ajaxJson;
                } else {
                    //如果无消耗  进行消耗
                    //学员课时消耗 课时、学费扣减
                    //先创建教师课时记录或教师课时记录实上人数加1，然后扣减学员课时
                    stumessage += "姓名：" + stuInfo.getStuName() + ",课时" + stukeshiNum + ",";
                    String nianji = iPxstugradetableService.getById(stuInfo.getStuGradeID()).getStugradename();
                    if (!allnianji.contains(nianji)) {// 没有
                        allnianji.add(nianji);
                    }
                    Pxkechengtable kecheng = iPxkechengtableService.getById(buxikecheng.getKechengID());

                    //请假是否扣课时
                    QueryWrapper<Pxkeshistutable> khQ = new QueryWrapper<>();
                    khQ
                            .eq("qiyeID", qiyeID)
                            .eq("stuID", stuidss)
                            .eq("kechengID", buxikecheng.getKechengID())
                            .eq("stuKaoqingStyle", "2");
                    int stuQinjiaTimes = iPxkeshistutableService.selectkehao(khQ).size();//请假之前次数
                    if (kecheng.getIskoukeshi() == false && stuKaoqingStyle == "2" && (kecheng.getQingjiaTimes() == -1 || stuQinjiaTimes < kecheng.getQingjiaTimes())) {
                        stukeshiNum = l;
                        if (kecheng.getQingjiaTimes() == -1) {
                            xiugaiInfo += "，课程【" + kecheng.getKechengName() + "】设置了请假不扣减课时,且请假次数不限制，学员考勤为请假，课时为：0";
                        } else {
                            xiugaiInfo += "，课程【" + kecheng.getKechengName() + "】，设置了请假不扣减课时,且未超过请假次数限制，学员考勤为请假，课时为：0：";
                        }

                    }
                    if (kecheng.getIskoukeshi() == false && stuKaoqingStyle == "2" && (kecheng.getQingjiaTimes() != -1 && stuQinjiaTimes > kecheng.getQingjiaTimes())) {
                        xiugaiInfo += "，课程【" + kecheng.getKechengName() + "】，学生该课程请假次数已经达到最大值，正常扣除课时：" + stukeshiNum;
                    }

                    //教师课时添加
                    for (teacherIDVo teacherStr : temp) {
                        long teacherID = teacherStr.getTeaID();
                        String tStaffName = iPxstafftableService.getById(teacherID).getStaffName();
                        xiugaiInfo += "***开始记录教师课时：" + tStaffName;

                        QueryWrapper<Pxkeshiteachertable> ksTeaQ = new QueryWrapper<>();
                        ksTeaQ
                                .eq("qiyeID", qiyeID)
                                .eq("haveClassDate", adddate)
                                .eq("startLessonDateTime", sTime)
                                .eq("kechengID", kechengID)
                                .eq("teacherID", teacherID)
                                .eq("endLessonDateTime", eTime);

                        List<Pxkeshiteachertable> pxkeshiteachertables = iPxkeshiteachertableService.selectTeakehao(ksTeaQ);
                        if (pxkeshiteachertables.size() > 0) {
                            Pxkeshiteachertable currentTeacher = pxkeshiteachertables.get(0);
                            xiugaiInfo += "，存在教师的此次课耗记录";
                            if (stuKaoqingStyle == "1" || stuKaoqingStyle == "4" || stuKaoqingStyle == "6") {//1正常，2请假，3旷课，4迟到
                                currentTeacher.setSsStuNum(currentTeacher.getSsStuNum() + 1);
                                xiugaiInfo += "，课耗记录实上人数加1，总数为" + currentTeacher.getSsStuNum().toString();
                            }
                            currentTeacher.setAllstuNianji(currentTeacher.getAllstuNianji() + allnianji);
                            iPxkeshiteachertableService.updateById(currentTeacher);
                            //teacherKSID = currentTeacher.getId();
                            xiugaiInfo += "，教师课时记录完毕";
                        } else {
                            xiugaiInfo += "，不存在教师此次课耗记录";
                            //没有教师的课耗记录，才新增教师的课耗记录；
                            Pxkeshiteachertable onet = new Pxkeshiteachertable();
                            onet.setBuxiStyleID(kc.getBuxiStyleID());
                            onet.setCampusID(cla.getCampusID());
                            onet.setClassID(classID);
                            onet.setClassTimeStyleID(kc.getClassTimeStyleID());
                            onet.setStartLessonDateTime(sTime);
                            onet.setEndLessonDateTime(eTime);
                            onet.setHaveClassDate(adddate);
                            onet.setKechengID(kechengID);
                            onet.setShuoMing("");
                            onet.setQiyeID(qiyeID);
                            xiugaiInfo += "，上课日期：" + adddate.toString();
                            xiugaiInfo += " " + sTime.toString() + "-" + eTime.toString().substring(0, 5);
                            if (stuKaoqingStyle == "1" || stuKaoqingStyle == "4" || stuKaoqingStyle == "6") {//1正常，2请假，3旷课，4迟到
                                onet.setSsStuNum(1);
                                xiugaiInfo += "，课耗记录实上人数为：1";
                            } else {
                                onet.setSsStuNum(0);
                                xiugaiInfo += "实上人数不用增加";
                            }
                            onet.setKeshiNum(teacherkeshiNum);
                            xiugaiInfo += "，课时为：" + teacherkeshiNum.toString();

                            if (buxiStyleName == "一对一") {
                                onet.setStuID(stuidss);
                                xiugaiInfo += "，记录为一对一";
                            } else {
                                onet.setStuID(0L);
                                xiugaiInfo += "，记录为班课";
                            }
                            onet.setTeacherID(Long.valueOf(teacherID));
                            onet.setTeacherkaoqing(tkaoqinstyle);
                            onet.setWeekN(week);
                            onet.setAllstuNianji(allnianji.toString());
                            onet.setYsStuNum(classStuCount);
                            onet.setZhujiao(zhujiaoteacher);
                            iPxkeshiteachertableService.save(onet);
                            //teacherKSID = onet.getId();
                            xiugaiInfo += "，教师课时记录完毕";
                        }
                    }

                    xiugaiInfo += "***添加学员考勤";
                    //添加学员考勤
                    QueryWrapper<Pxstukaoqingtable> astukqQ = new QueryWrapper<>();
                    astukqQ
                            .eq("qiyeID", qiyeID)
                            .eq("stuID", stuidss)
                            .eq("kechengID", stuKechengID)
                            .eq("haveclassDate", adddate)
                            .eq("startClassDateTime", sTime)
                            .eq("endClassDateTime", eTime);
                    List<Pxstukaoqingtable> stukqList = iPxstukaoqingtableService.selectstukaoqing(astukqQ);
                    if (stukqList.size() == 0) {
//                        Pxstukaoqingtable stukq = stukqList.get(0);
                        xiugaiInfo += "，***添加学员考勤";
                        xiugaiInfo += "，考勤类别" + stuKaoqingStyle;
                        Pxstukaoqingtable onek = new Pxstukaoqingtable();
                        onek.setEndclassdatetime(eTime);
                        onek.setStartclassdatetime(sTime);
                        onek.setHaveclassdate(adddate);
                        Integer kqsty = Integer.valueOf(stuKaoqingStyle);
                        String kqstyle = kqsty == 1 ? "正常" : kqsty == 2 ? "请假" : kqsty == 3 ? "旷课" : kqsty == 4 ? "迟到" : kqsty == 5 ? "早退" : "补课";
                        onek.setKaoqingstyle(kqsty);
                        onek.setKaoqingbeizhu(kqstyle);
                        onek.setKechengid(stuKechengID);
                        onek.setClassid(classID);
                        onek.setStuid(stuidss);
                        onek.setTeacherids(teaIDs);
                        onek.setTeachernames(teacherNames);
                        onek.setQiyeid(qiyeID);
                        iPxstukaoqingtableService.save(onek);

                        //如果有排课信息，加入选课表
                        QueryWrapper<Pxpaiketable> pk = new QueryWrapper<>();
                        pk
                                .eq("classid ", classID)
                                .eq("haveClassDate", adddate)
                                .eq("startlessondatetime", sTime)
                                .eq("endLessonDateTime", eTime)
                                .eq("teacherIDs", teacherIDs)
                                .eq("qiyeID", qiyeID);
                        List<Pxpaiketable> pdpaikeList = iPxpaiketableService.selectpaike(pk);
                        if (pdpaikeList.size() > 0) {
                            Pxpaiketable pdpaikeTable = pdpaikeList.get(0);
                            QueryWrapper<Pxxuanketable> xkQ = new QueryWrapper<>();
                            xkQ
                                    .eq("paikeID", pdpaikeTable.getId())
                                    .eq("stuID", stuidss);
                            List<Pxxuanketable> xuankeList = iPxxuanketableService.selectxuanke(xkQ);
                            if (xuankeList.size() > 0) {
                                Pxxuanketable addxk = new Pxxuanketable();
                                addxk.setStuID(stuidss);
                                addxk.setPaikeID(pdpaikeTable.getId());
                                addxk.setRecordDate(new Date());
                                addxk.setType(1);
                                addxk.setBuxiID(buxikecheng.getId());
                                addxk.setQiyeID(qiyeID);
                                iPxxuanketableService.save(addxk);

                                QueryWrapper<Pxxuanketable> xkcou = new QueryWrapper<>();
                                xkcou.eq("paikeID", pdpaikeTable.getId());
                                classStuCount = iPxxuanketableService.selectxuanke(xkcou).size();
                            }
                        }

                        for (teacherIDVo teacherStr : temp) {
                            xiugaiInfo += "，***添加学生考勤教师记录";
                            Pxstukaoqingteachertable stt = new Pxstukaoqingteachertable();
                            stt.setTeacherid(Long.valueOf(teacherStr.getTeaID()));
                            stt.setStukaoqingtabid(onek.getId());
                            stt.setQiyeID(qiyeID);
                            iPxstukaoqingteachertableService.save(stt);
                        }
                    }

                    BigDecimal ksPrice = new BigDecimal("0");
                    if (jfstyle == 1 || jfstyle == 2) {
                        xiugaiInfo += "***扣减学员课时和学费，学员课程计费方式为" + jfstyle;
                        //请假是否扣课时
                        if (stukeshiNum.intValue() > 0) {

                            //如果该课程的剩余课时扣除本次课时后，剩余课时为负数
                            if (buxikecheng.getRemainkeshi().subtract(stukeshiNum).compareTo(l) == -1) {  //剩余课时-扣课时<0
                                xiugaiInfo += "，学员剩余课时：" + buxikecheng.getRemainkeshi().toString() + ",消耗课时：" + stukeshiNum.toString() + ",不够扣！";
                                QueryWrapper<Pxbuxikechengtable> bx2 = new QueryWrapper<>();
                                bx2
                                        .eq("kechengID", stuKechengID)
                                        .eq("stuID", stuidss)
                                        .ge("remainkeshi", stukeshiNum)
                                        .eq("qiyeID", qiyeID);
                                Pxbuxikechengtable buxikecheng2 = iPxbuxikechengtableService.getOne(bx2);
                                if (buxikecheng2 != null) {
                                    xiugaiInfo += "，存在相同补习课程记录";

                                    //如果隐藏的同ID课程的剩余课时扣除本次课的课时还有剩余
                                    xiugaiInfo += "，同课程剩余课时：" + buxikecheng2.getRemainkeshi().toString() + "，够扣！";
                                    Long stuKcID = buxikecheng2.getKechengID();
                                    String kcQiehuan = kechengQiehuan.kechengQiehuan(buxikecheng, buxikecheng2, buxiStyleName, classID, staffID, qiyeID);
                                    xiugaiInfo += "，扣减课时前，剩余课时为：" + buxikecheng2.getRemainkeshi().toString() + "，扣减课时：" + stukeshiNum.toString();
                                    buxikecheng2.setRemainkeshi(buxikecheng2.getRemainkeshi().subtract(stukeshiNum));//扣减课时
                                    xiugaiInfo += "，扣减后的剩余课时为：" + buxikecheng2.getRemainkeshi().toString();
                                    iPxbuxikechengtableService.updateById(buxikecheng2);
                                    if (buxikecheng2.getType() == 2) {
                                        //如果该条记录是赠送课时，则不记单价，也不扣除学员学费
                                        //类型：为空或1买的，2赠送的，3转送得到的，4转送出去的
                                        ksPrice = l;
                                        xiugaiInfo += "，课程记录是赠送的，消课单价置0，不扣减学费";

                                    } else {
                                        xiugaiInfo += "，该补习课程消课单价：" + buxikecheng2.getKechengprice().toString();
                                        ksPrice = buxikecheng2.getKechengprice();

                                        xiugaiInfo += "，扣减前剩余学费：" + stuInfo.getRemainXuefei().toString();
                                        stuInfo.setRemainXuefei(stuInfo.getRemainXuefei().subtract(stukeshiNum.multiply(ksPrice)));
                                        iPxstutableService.updateById(stuInfo);
                                        xiugaiInfo += "，扣减后剩余学费：" + stuInfo.getRemainXuefei().toString();
                                    }

                                } else {
                                    xiugaiInfo += "，不存在相同且够扣的补习课程记录";

                                    if (!buxikecheng.getShareBuxiID().equals("0")) {
                                        //有共享课时
                                        String[] sharebuxi = buxikecheng.getShareBuxiID().split(",");
                                        for (String items : sharebuxi) {
                                            Pxbuxikechengtable onesharebx = iPxbuxikechengtableService.getById(items);
                                            Pxstutable sharestu = iPxstutableService.getById(onesharebx.getStuID());
                                            Pxkechengtable sharekc = iPxkechengtableService.getById(onesharebx.getKechengID());
                                            if (onesharebx.getRemainkeshi().compareTo(stukeshiNum) == 1) { //补习课程剩余课时>扣减课时
                                                xiugaiInfo += "。存在够扣减的共享课时,共享了:" + sharestu.getStuName() + "的" + sharekc.getKechengName() + "课程";

                                                BigDecimal shareMoney = buxikecheng.getKechengprice().multiply(stukeshiNum);
                                                BigDecimal sharekeshi = shareMoney.divide(onesharebx.getKechengprice());

                                                sharekehao = sharekeshi;
                                                onesharebx.setRemainkeshi(onesharebx.getRemainkeshi().subtract(sharekeshi));
                                                iPxbuxikechengtableService.updateById(onesharebx);

                                                sharestu.setRemainXuefei(sharestu.getRemainXuefei().subtract(shareMoney));
                                                iPxstutableService.updateById(sharestu);

                                                stusharebuxiID = onesharebx.getId();
                                                break;  //跳出并停止循环

                                            }
                                        }

                                    } else {
                                        if (remainksCanIsFushu == false) {
                                            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                                            ajaxJson.setCode("N");
                                            ajaxJson.setMsg(stuInfo.getStuName() + ",剩余课时会扣减为负数，系统设置不允许课时扣减为负数!");
                                            return ajaxJson;
                                        }
                                        //可扣减为负，原课时扣
                                        /*减剩余课时*/
                                        xiugaiInfo += "，课时扣减前，剩余课时：" + buxikecheng.getRemainkeshi().toString() + "，要扣减课时" + stukeshiNum.toString();
                                        buxikecheng.setRemainkeshi(buxikecheng.getRemainkeshi().subtract(stukeshiNum));
                                        xiugaiInfo += "，课时扣减后，剩余课时：" + buxikecheng.getRemainkeshi().toString();
                                        if (buxikecheng.getType() == 2) {
                                            //如果该条记录是赠送课时，则不记单价，也不扣除学员学费
                                            //类型：为空或1买的，2赠送的，3转送得到的，4转送出去的
                                            ksPrice = l;
                                            xiugaiInfo += "，课程记录是赠送的，消课单价置0，不扣减学费";
                                        } else {
                                            ksPrice = buxikecheng.getKechengprice();
                                            xiugaiInfo += "，消课单价" + ksPrice.toString();
                                            xiugaiInfo += "，扣减学费前剩余学费" + stuInfo.getRemainXuefei().toString();
                                            stuInfo.setRemainXuefei(stuInfo.getRemainXuefei().subtract(stukeshiNum.multiply(ksPrice)));
                                            iPxstutableService.updateById(stuInfo);
                                            xiugaiInfo += "，扣减学费后剩余学费" + stuInfo.getRemainXuefei().toString();
                                        }
                                    }
                                }

                            } else {
                                xiugaiInfo += "，学员课程是够扣减的";
                                /*减剩余课时*/
                                xiugaiInfo += "，课时扣减前，剩余课时：" + buxikecheng.getRemainkeshi().toString() + "，要扣减课时：" + stukeshiNum.toString();
                                buxikecheng.setRemainkeshi(buxikecheng.getRemainkeshi().subtract(stukeshiNum));
                                iPxbuxikechengtableService.updateById(buxikecheng);
                                xiugaiInfo += "，课时扣减后，剩余课时：" + buxikecheng.getRemainkeshi().toString();
                                if (buxikecheng.getType() == 2) {  //如果该条记录是赠送课时，则不记单价，也不扣除学员学费
                                    //类型：为空或1买的，2赠送的，3转送得到的，4转送出去的
                                    ksPrice = l;
                                    xiugaiInfo += "，该补习课程为赠送课程，消课单价：0，扣减学费：0";
                                } else {
                                    ksPrice = buxikecheng.getKechengprice();
                                    xiugaiInfo += "，消课单价：" + ksPrice.toString();
                                    xiugaiInfo += "，扣减前，剩余学费：" + stuInfo.getRemainXuefei().toString();
                                    stuInfo.setRemainXuefei(stuInfo.getRemainXuefei().subtract(stukeshiNum.multiply(ksPrice)));
                                    iPxstutableService.updateById(stuInfo);

                                    xiugaiInfo += "，扣减后，剩余学费：" + stuInfo.getRemainXuefei().toString();
                                }
                            }
                        }
                    }

                    xiugaiInfo += "，***添加学员课时记录";

                    //学员课时消耗
                    //region 学员课时消耗

                    Pxbuxikechengtable bkByDay = new Pxbuxikechengtable();

                    Pxkeshistutable onestuKS = new Pxkeshistutable();
                    onestuKS.setAddtime(new Date());
                    onestuKS.setAdduser(staffID);
                    onestuKS.setQiyeID(qiyeID);
                    onestuKS.setBuxiStyleID(kc.getBuxiStyleID());
                    onestuKS.setCampusID(cla.getCampusID());
                    onestuKS.setStuGradeID(stuInfo.getStuGradeID());
                    onestuKS.setZhujiao(zhujiaoteacher);
                    onestuKS.setClassID(classID);
                    onestuKS.setClassTimeStyleID(kc.getClassTimeStyleID());
                    onestuKS.setKechengID(stuKechengID);
                    onestuKS.setHaveClassDate(adddate);
                    onestuKS.setWeekN(week);
                    onestuKS.setStartLessonDateTime(sTime);
                    onestuKS.setEndLessonDateTime(eTime);
                    bkByDay = buxikecheng;


                    if (jfstyle == 1 || jfstyle == 2) {
                        if (stusharebuxiID != null && stusharebuxiID != 0) {
                            //有进行共享消课
                            onestuKS.setShareBuxiID(stusharebuxiID);
                            Pxbuxikechengtable one = iPxbuxikechengtableService.getById(stusharebuxiID);
                            onestuKS.setKechengPrice(one.getKechengprice());
                            onestuKS.setKeshiNum(sharekehao);//课时
                            xiugaiInfo += "学员考勤类别" + stuKaoqingStyle + "，课程按课时或课时包计费，单价：" + one.getKechengprice().toString() + ",课时：" + sharekehao.toString();
                        } else {
                            onestuKS.setShareBuxiID(0L);
                            onestuKS.setKechengPrice(ksPrice);
                            onestuKS.setKeshiNum(stukeshiNum);//课时
                            xiugaiInfo += "学员考勤类别" + stuKaoqingStyle + "，课程按课时或课时包计费，单价：" + ksPrice.toString() + ",课时：" + stukeshiNum.toString();
                        }
                    } else {
                        //未在有效时间课程切换
                        if (bkByDay.getEndDate().getTime() < t2.getTime() || bkByDay.getStartDate().getTime() > t2.getTime()) {
                            //查找有效时间内最早的一条记录
                            QueryWrapper<Pxbuxikechengtable> bxkcQ = new QueryWrapper<>();
                            bxkcQ
                                    .le("startDate", t2)
                                    .ge("endDate", t2)
                                    .eq("kechengID", stuKechengID)
                                    .eq("stuID", stuidss)
                                    .eq("isShow", "1")
                                    .eq("qiyeID", qiyeID);
                            List<Pxbuxikechengtable> czList = iPxbuxikechengtableService.selectbuxikecheng(bxkcQ);
                            if (czList.size() > 0) {
                                Pxbuxikechengtable cznewbxTab = czList.get(0);
                                bkByDay = cznewbxTab;
                                //判断插班，插班表（切换班级）
                                QueryWrapper<Pxstuclasstable> stuclaQ = new QueryWrapper<>();
                                stuclaQ
                                        .eq("qiyeID", qiyeID)
                                        .eq("buxiID", buxikecheng.getId());
                                List<Pxstuclasstable> pdstuclassTab = iPxstuclasstableService.selectstuclass(stuclaQ);
                                for (Pxstuclasstable nstuc : pdstuclassTab) {
                                    nstuc.setBuxiID(cznewbxTab.getId());
                                    iPxstuclasstableService.updateById(nstuc);
                                }

                                //判断排课，选课表（切换排课课程）
                                QueryWrapper<Pxxuanketable> xkTabQ = new QueryWrapper<>();
                                xkTabQ
                                        .eq("qiyeID", qiyeID)
                                        .eq("buxiID", buxikecheng.getId());
                                List<Pxxuanketable> pdxuanke = iPxxuanketableService.selectxuanke(xkTabQ);
                                for (Pxxuanketable nxk : pdxuanke) {
                                    Pxpaiketable oneClapk = iPxpaiketableService.getById(nxk.getPaikeID());
                                    //存在课耗选课不替换
                                    QueryWrapper<Pxkeshistutable> pdkhQ = new QueryWrapper<>();
                                    pdkhQ
                                            .eq("kechengID", oneClapk.getKechengID())
                                            .eq("classID", oneClapk.getClassID())
                                            .eq("stuID", buxikecheng.getStuID())
                                            .eq("haveClassDate", oneClapk.getHaveClassDate())
                                            .eq("startLessonDateTime", oneClapk.getStartLessonDateTime())
                                            .eq("endLessonDateTime", oneClapk.getEndLessonDateTime())
                                            .eq("qiyeID", qiyeID);
                                    List<Pxkeshistutable> pdkehao = iPxkeshistutableService.selectkehao(pdkhQ);
                                    if (pdkehao.size() == 0) {
                                        //切换成有效课程
                                        nxk.setBuxiID(cznewbxTab.getId());
                                        iPxxuanketableService.updateById(nxk);
                                    }
                                }
                            } else {
                                onestuKS.setKeshiNum(l);
                                onestuKS.setKechengPrice(l);
                                xiugaiInfo += "找到切换，课单价、课时为0";
                            }
                        }
                    }
                    onestuKS.setStuID(stuidss);
                    onestuKS.setDakaoqingStyle(1);
                    onestuKS.setStuKaoqingStyle(stuKaoqingStyle);
                    onestuKS.setTeacherIDs(teaIDs);
                    onestuKS.setTeacherNames(teacherNames);
                    xiugaiInfo += "，任课教师名字：" + teacherNames;
                    onestuKS.setWeekN(week);
                    onestuKS.setShuoMing(shuoming + ",后台不排课消课");
                    onestuKS.setBuxikechengID(bkByDay.getId());
                    onestuKS.setBanzhurenStaffID(stuInfo.getBanzhurenTeacherID());
                    iPxkeshistutableService.save(onestuKS);

                    xiugaiInfo += "，学员课时保存完毕";
                    khids.add(onestuKS.getId());


                    for (teacherIDVo teacherStr : temp) {
                        Pxkeshistuteachertable kstuteach = new Pxkeshistuteachertable();
                        kstuteach.setTeacherid(Long.valueOf(teacherStr.getTeaID()));
                        kstuteach.setKeshistutableid(onestuKS.getId());
                        kstuteach.setQiyeID(qiyeID);
                        iPxkeshistuteachertableService.save(kstuteach);
                    }

                    Pxstutable stu = iPxstutableService.getById(stuidss);
                    BigDecimal oldJifen = stu.getJifenNum(); //原本积分
                    String jfen = iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 72L) == null ?
                     iPxsysparamdefaulttableService.getById(72).getDefaultValue() :
                      iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 72L).getModifyValue();
                    //消课积分比例设置
                    BigDecimal jfn = new BigDecimal(jfen);
                    if (stukeshiNum.intValue() > 0) {
                        if (jfn.intValue() > 0) {
                            Pxjifentable JF = new Pxjifentable();
                            stu.setJifenNum(stu.getJifenNum().add(jfn.multiply(stukeshiNum)));
                            iPxstutableService.updateById(stu);
                            JF.setStuID(stuidss);
                            JF.setType(1);
                            JF.setOldintegral(oldJifen);
                            JF.setIntegral(jfn.multiply(stukeshiNum));
                            JF.setStaffID(staffID);
                            JF.setCreatetime(t2);
                            JF.setRemark("手动录入课耗信息，产生积分");
                            JF.setQiyeID(qiyeID);
                            iPxjifentableService.save(JF);
                        }
                    }
                    String kechengName = iPxkechengtableService.getById(stuKechengID).getKechengName();
                    //region 向家长推送积分变动提醒
                    Pxtuisongtable pxtuisongtable = new Pxtuisongtable()
                            .setRole(1)
                            .setWxstate(0)
                            .setAppread(0)
                            .setQiyeID(loginUser.getQiyeID())
                            .setAddStaffID(loginUser.getStaffID())
                            .setAddTime(new Date())
                            .setNote("学员：" + stu.getStuName() + "手动录入课耗信息，产生积分：" + jfn.multiply(stukeshiNum))
                            .setKechengID(kecheng.getId())
                            .setStuID(stuidss)
                            .setTuisongTypeName(4L);
                    iPxtuisongtableService.save(pxtuisongtable);
                    sendMessage.sendMessage(pxtuisongtable.getId());
                    //endregion
                    //家长推送孩子的剩余课时
                    // 如果剩余课时低于预警值，给家长发送续费提醒;
                    BigDecimal stuKechengRemainKeshi = l;
                    String classWarning = iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 13L) == null ?
                            iPxsysparamdefaulttableService.getById(13).getDefaultValue() :
                            iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 13L).getModifyValue(); //预警类型
                    if (classWarning == "总课时预警") {
                        stuKechengRemainKeshi = iPxbuxikechengtableService.getSumzongRks(stuidss).get(0).getSumR();
                    } else {
                        stuKechengRemainKeshi = iPxbuxikechengtableService.getSumRks(stuidss, stuKechengID, Long.valueOf(qiyeID)).get(0).getSumR();
                    }
                    //预警值
                    String yujinzhi = iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 11L) == null ?
                            iPxsysparamdefaulttableService.getById(11).getDefaultValue() :
                            iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 11L).getModifyValue();

                    Pxstutable msgstu = iPxstutableService.getById(stuidss);
                    String msgStuName = msgstu.getStuName();
                    String stuCampusName = iPxcampustableService.getById(msgstu.getCampusID()).getCampusName();
                    String noticeType = "";
                    String noticeContent = "";
                    //判断是否显示课程结束日期
                    Pxsysparamvaluetable getvalue = iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 61L);
                    Pxsysparamvaluetable getqinglin = iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 62L);
                    boolean isshowEnddate = getvalue != null ? getvalue.getModifyValue() == "是" ? true : false :
                            iPxsysparamdefaulttableService.getById(61).getDefaultValue() == "是" ? true : false;//是否在（补习）课程起止日期之前消课
                    boolean isshowEnddate1 = getvalue != null ? getqinglin.getModifyValue() == "是" ? true : false :
                            iPxsysparamdefaulttableService.getById(61).getDefaultValue() == "是" ? true : false;//课时自动清零

                    if (stuKechengRemainKeshi.intValue() > Integer.valueOf(yujinzhi)) {
                        noticeType = "温馨提示";
                        if (jfstyle == 3) {
                            noticeContent =
                                    msgStuName + "的课程《" + kechengName + "》" + DateUtil.formatDate2(adddate) + " " + sTime.toString() + "-" + eTime.toString() + "已消课";
                        } else {
                            if (classWarning == "总课时预警") {
                                noticeContent =
                                 msgStuName + "的课程《" + kechengName + "》" + DateUtil.formatDate2(adddate) + " " + sTime.toString() + "-" + eTime.toString() +
                                         "上课消课" + stukeshiNum + "课时，总课时剩余" + stuKechengRemainKeshi + "课时。";
                            } else {
                                noticeContent = msgStuName + "的课程《" + kechengName + "》" + DateUtil.formatDate2(adddate) + " " + sTime.toString().substring(0,
5) + "-" + eTime.toString() + "上课消课" + stukeshiNum + "课时，单科课时剩余" + stuKechengRemainKeshi + "课时。";
                            }
                        }
                    } else {
                        if (jfstyle == 3) {
                            noticeType = "温馨提示";
                            noticeContent =
                                    msgStuName + "的课程[" + kechengName + "]" + DateUtil.formatDate2(adddate) + " " + sTime.toString() + "-" + eTime.toString() + "已消课";
                        } else {
                            if (classWarning == "总课时预警") {
                                noticeType = "续费提醒";
                                noticeContent =
                                 msgStuName + "的课程[" + kechengName + "]" + DateUtil.formatDate2(adddate) + " " + sTime.toString() + "-" + eTime.toString() +
 "上课消课" + stukeshiNum + "课时，总课时剩余" + stuKechengRemainKeshi + "课时，请及时到我校续费。";
                            } else {
                                noticeType = "续费提醒";
                                noticeContent =
                                 msgStuName + "的课程[" + kechengName + "]" + DateUtil.formatDate2(adddate) + " " + sTime.toString() + "-" + eTime.toString() +
                                 "上课消课" + stukeshiNum + "课时，单科课时剩余" + stuKechengRemainKeshi + "课时，请及时到我校续费。";
                            }
                        }
                    }
                    //判断是是否显示课程结束日期
                    if (isshowEnddate == true || isshowEnddate1 == true) {
                        noticeContent += "课程到期时间：" + buxikecheng.getEndDate().toString();
                    }
                    String noticeRemark = stuCampusName;
                    Pxtuisongtable pxtuisongtable1 = new Pxtuisongtable()
                            .setAddStaffID(loginUser.getStaffID())
                            .setAddTime(new Date())
                            .setAppread(0)
                            .setWxstate(0)
                            .setKechengID(kecheng.getId())
                            .setQiyeID(loginUser.getQiyeID())
                            .setRole(1)
                            .setStuID(stuidss)
                            .setNote(noticeContent)
                            .setHaveclassDate(adddate)
                            .setStartLessonDateTime(sTime)
                            .setEndLessonDateTime(eTime)
                            .setTuisongTypeName(5L);
                    iPxtuisongtableService.save(pxtuisongtable1);
                    sendMessage.sendMessage(pxtuisongtable1.getId());
                    //region 推送给家长
                    //endregion
                    //endregion
                }
                //endregion

                //如果打了考勤的人数已经大于或等于班级人数，则改变这次排课的考勤状态
                QueryWrapper<Pxstukaoqingtable> stukqTabQ = new QueryWrapper<>();
                stukqTabQ
                        .eq("qiyeID", qiyeID)
                        .eq("haveclassDate", adddate)
                        .eq("startClassDateTime", sTime)
                        .eq("endClassDateTime", eTime)
                        .eq("classID", classID)
                        .in("teacherIDs", teacherIDs);
                int classStuKaoqingCount = iPxstukaoqingtableService.selectstukaoqing(stukqTabQ).size();
                xiugaiInfo += "，应上总人数" + classStuCount + "，已完成考勤人数" + classStuKaoqingCount;
                if (classStuKaoqingCount >= classStuCount) {
                    QueryWrapper<Pxpaiketable> pkkqQ = new QueryWrapper<>();
                    pkkqQ
                            .eq("qiyeID", qiyeID)
                            .eq("classid", classID)
                            .eq("haveclassdate", adddate)
                            .eq("startlessondatetime", sTime)
                            .eq("endLessonDateTime", eTime)
                            .in("teacherids", teacherIDs);
                    List<Pxpaiketable> paikeList = iPxpaiketableService.selectpaike(pkkqQ);
                    if (paikeList.size() > 0) {
                        Pxpaiketable paike = paikeList.get(0);
                        paike.setDakaoqin(true);
                        iPxpaiketableService.updateById(paike);
                        xiugaiInfo += "，该次排课已完成考勤";
                    }
                }
            }
        }
        ajaxJson.setMsg("排课消课成功，产生课耗ID为：" + khids);
        return ajaxJson;
    }

    //paikeDayShow 排课消课

    /**
     * @Description: getpaikexiaoekestuPage()方法作用:按排课消课，把班级学生及考勤返回
     * @param:[current, size, stuName]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/18 14:14
     */
    @RequestMapping(value = "getpaikexiaoekestuList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "按排课消课，把班级学生及考勤返回")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pkID", value = "排课ID", required = true),
            @ApiImplicitParam(name = "stuName", value = "学员姓名", required = false)
    })
    public AjaxJson getpaikexiaoekestuList(String pkID, String stuName, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("a.paikeID", pkID);
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("c.stuName", stuName);
        }
        ajaxJson.setObj(iPxxuanketableService.getpaikexiaoekestuList(queryWrapper));
        return ajaxJson;
    }

    /**
     * @Description: pdstudingjin()方法作用:判断定金
     * @param:[type, request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/3/30 14:50
     */
    @ResponseBody
    @RequestMapping(value = "pdstudingjin", method = RequestMethod.POST)
    @ApiOperation("判断定金")
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson pdstudingjin(@RequestBody pdDingjinFrom from, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Long staffID = Long.valueOf(loginUser.getStaffID());
        String text = "";
        int index = 0;
        int type = from.getType();

        if (type == 1) {
            String newstu = from.getNewstu();
            String tiankc = from.getTiankc();
            rengongxiaokeVo Jorow = JSON.parseArray(from.getTiankc(), rengongxiaokeVo.class).get(0);
            //newstu 学员ID|考勤状态|扣课时数
            List<stukaoqingVo> stustr = JSON.parseArray(newstu, stukaoqingVo.class);
            Long paikeID = Jorow.getId();
            Long classID = Long.valueOf(Jorow.getClassID());

            for (stukaoqingVo item : stustr) {
                Long stuidss = item.getStuID();
                String stuKaoqingStyle = item.getKaoqing();
                BigDecimal stukeshiNum = item.getKeshi();

                List<Pxxuanketable> xkTab = iPxxuanketableService.list(new QueryWrapper<Pxxuanketable>()
                        .eq("stuid", stuidss)
                        .eq("paikeid", paikeID)
                        .eq("qiyeID", qiyeID));
                List<Pxbuxikechengtable> bxTabList = xkTab.size() == 0 ? iPxbuxikechengtableService.getBxByStuAndClass(stuidss, classID, qiyeID) :
 iPxbuxikechengtableService.list(new QueryWrapper<Pxbuxikechengtable>()
                        .eq("id", xkTab.get(0).getBuxiID())
                        .eq("isshow", 1)
                        .eq("qiyeID", qiyeID));

                BigDecimal dingjin = new BigDecimal(0);
                BigDecimal l = new BigDecimal(0);
                BigDecimal f = new BigDecimal(-1);
                if (bxTabList.size() != 0) {
                    Pxbuxikechengtable bxTab = bxTabList.get(0);
                    if (bxTab.getQianDanInfoID() != null) {
                        //新数据
                        Pxqiandaninfotable qdTab = iPxqiandaninfotableService.getById(bxTab.getQianDanInfoID());
                        if (qdTab != null) {
                            dingjin = qdTab.getShishouTotalMoney();
                        } else {
                            dingjin = f;
                        }
                    } else {
                        dingjin = f;
                    }

                    //老数据暂时不做处理
                    if (dingjin.compareTo(f) != 0) {
                        List<Pxkeshistutable> kehaoList = iPxkeshistutableService.list(new QueryWrapper<Pxkeshistutable>().eq("buxikechengid", bxTab.getId()));
                        BigDecimal yxkehao = kehaoList.size() > 0 ? iPxkeshistutableService.getkehao(bxTab.getId(), qiyeID).getKehao() : l;

                        BigDecimal allkehao = yxkehao.add(stukeshiNum.multiply(bxTab.getKechengprice()));

                        if (dingjin.compareTo(allkehao) == -1) {//dingjin < allkehao
                            if (index < 2) {
                                text += iPxstutableService.getById(stuidss).getStuName() + ',';
                            } else if (index == 2) {
                                text += iPxstutableService.getById(stuidss).getStuName() + "等学员,";
                            }
                            index += 1;
                        }
                    }
                }
            }
        } else {

            Long classidStr = Long.valueOf(from.getClassidStr());
            String newstu = from.getNpnewstu();

            //newstu 学员ID|考勤状态|扣课时数|补习ID
            List<NopaikeStuVo> stustr = JSON.parseArray(newstu, NopaikeStuVo.class); //学员信息

            for (NopaikeStuVo item : stustr) {
                Long stuidss = item.getStuID();
                String stuKaoqingStyle = item.getKaoqing();
                BigDecimal stukeshiNum = item.getKeshi();
                Long buxiID = Long.valueOf(item.getBuxiID());

                List<Pxbuxikechengtable> bxTabList = buxiID == 0 ? iPxbuxikechengtableService.getBxByStuAndClass(stuidss, classidStr, qiyeID) :
                 iPxbuxikechengtableService.list(new QueryWrapper<Pxbuxikechengtable>()
                        .eq("id", buxiID)
                        .eq("isshow", 1)
                        .eq("qiyeID", qiyeID));

                BigDecimal dingjin = new BigDecimal(0);
                BigDecimal l = new BigDecimal(0);
                BigDecimal f = new BigDecimal(-1);

                if (bxTabList.size() != 0) {
                    Pxbuxikechengtable bxTab = bxTabList.get(0);
                    if (bxTab.getQianDanInfoID() != null) {
                        //新数据
                        Pxqiandaninfotable qdTab = iPxqiandaninfotableService.getById(bxTab.getQianDanInfoID());
                        if (qdTab != null) {
                            dingjin = qdTab.getShishouTotalMoney();
                        } else {
                            dingjin = f;
                        }
                    } else {
                        dingjin = f;
                    }

                    //老数据暂时不做处理
                    if (dingjin.compareTo(f) != 0) {  //不相等
                        List<Pxkeshistutable> kehaoList = iPxkeshistutableService.list(new QueryWrapper<Pxkeshistutable>().eq("buxikechengid", bxTab.getId()));
                        BigDecimal yxkehao = kehaoList.size() > 0 ? iPxkeshistutableService.getkehao(bxTab.getId(), qiyeID).getKehao() : l;
                        BigDecimal allkehao = yxkehao.add(stukeshiNum.multiply(bxTab.getKechengprice()));

                        if (dingjin.compareTo(allkehao) == -1) {//dingjin < allkehao
                            if (index < 2) {
                                text += iPxstutableService.getById(stuidss).getStuName() + ',';
                            } else if (index == 2) {
                                text += iPxstutableService.getById(stuidss).getStuName() + "等学员,";
                            }
                            index += 1;
                        }
                    }
                }
            }
        }

        if (text != "") {

            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ajaxJson.setCode("N");
            ajaxJson.setMsg(text);
        } else {

            ajaxJson.setMsg("");
        }
        return ajaxJson;
    }


    @ResponseBody
    @RequestMapping(value = "pdrkeshi", method = RequestMethod.POST)
    @ApiOperation("判断课时")
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson pdrkeshi(HttpServletRequest request, @RequestBody pdrkeshiFrom from) {
        AjaxJson ajaxJson = new AjaxJson();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Long staffID = Long.valueOf(loginUser.getStaffID());

        String sysvalue = iPxsysparamvaluetableService.getOne(
                new QueryWrapper<Pxsysparamvaluetable>()
                        .eq("sysparamtypeid", 63L)
                        .eq("qiyeID", qiyeID)
        ).getModifyValue();

        if (sysvalue != null && sysvalue.equals("是")) {
            //课时可扣减为负 不需要做课时检测
            ajaxJson.setMsg("");
            return ajaxJson;
        } else if (sysvalue != null) {

            List<stupdrkeshiVo> stupdrkeshiVos = JSON.parseArray(from.getNewstu(), stupdrkeshiVo.class);
            List<kcModelVo> kcModelVos = JSON.parseArray(from.getTabdata(), kcModelVo.class);

            int have = 0;
            int num = kcModelVos.size(); //要排几个课时

            Date ST = null;
            try {
                ST = sdf.parse(kcModelVos.get(0).getStartTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Date ET = null;
            try {
                ET = sdf.parse(kcModelVos.get(0).getEndTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            BigDecimal time = BigDecimal.valueOf((ET.getTime() - ST.getTime()) / (1000 * 60));

            String text = "";
            for (stupdrkeshiVo item : stupdrkeshiVos) {
                BigDecimal akeshi = new BigDecimal(0);

                Pxbuxikechengtable buxike = iPxbuxikechengtableService.getById(item.getBxID());
                Pxkechengtable kc = iPxkechengtableService.getById(buxike.getKechengID());

                if (buxike != null && kc != null) {
                    if (buxike.getJifeiStyleID() == 3) {
                        //学生课程所有剩余天数
                        long allrday = 0;
                        allrday = (new Date().getTime() - buxike.getEndDate().getTime()) / (24 * 1000 * 60 * 60) + 1;

                        if (allrday < num) {
                            text += item.getStuName() + ",";
                            have += 1;
                        }

                    } else {
                        Pxclasstimestyletable clsTimetab = iPxclasstimestyletableService.getById(kc.getClassTimeStyleID());
                        BigDecimal classt = new BigDecimal(clsTimetab.getClasstimestylename());

                        if (Integer.parseInt(clsTimetab.getClasstimestylename()) < 0) {
                            akeshi = BigDecimal.valueOf(num);
                        } else {

                            akeshi = time.divide(classt, 2, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(num));
                        }

                        String getbxremainkeshi = iPxbuxikechengtableService.getbxremainkeshi(
                                new QueryWrapper<Pxbuxikechengtable>()
                                        .eq("a.stuid", buxike.getStuID())
                                        .eq("a.kechengid", buxike.getKechengID())
                        );
                        BigDecimal allrkeshi = new BigDecimal(getbxremainkeshi);
                        List<Pxpaiketable> allxuanke = iPxxuanketableService.getNokehaoStutoPk(buxike.getKechengID(), buxike.getStuID(), qiyeID);
                        for (Pxpaiketable itempk : allxuanke) {
                            if (itempk != null) {
                                //计算课时
                                Date etime = itempk.getEndLessonDateTime();
                                Date stime = itempk.getStartLessonDateTime();
                                BigDecimal nowcltime = BigDecimal.valueOf((etime.getTime() - stime.getTime()) / (1000 * 60));
                                akeshi = akeshi.add(nowcltime.divide(classt));
                            }
                        }
                        if (allrkeshi.compareTo(akeshi) == -1) {
                            text += item.getStuName() + ",";
                            have += 1;
                        }


                    }
                } else {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    ajaxJson.setCode("N");
                    ajaxJson.setMsg("找不到课程信息");
                    return ajaxJson;
                }
            }
            if (have > 0) {
                if (text.length() > 20) {
                    text = text.substring(0, 20);
                }
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                ajaxJson.setCode("N");
                ajaxJson.setMsg(text + "等存在课时不足！");
                return ajaxJson;
            }
        }
        return ajaxJson;
    }


    /**
     * @Description: tiansavestukehao()方法作用:学生课时-课时录入——保存课时消耗(按排课消课)
     * @param:[from]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/21 10:11
     */
    @ResponseBody
    @RequestMapping(value = "tiansavestukehao", method = RequestMethod.POST)
    @ApiOperation("学生课时-课时录入——保存课时消耗")
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson tiansavestukehao(@RequestBody paikexiaokeFrom from, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        String tkaoqinstyle = "正常";
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());

        BigDecimal l = new BigDecimal(0);
        BigDecimal y = new BigDecimal(1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        //课时
        BigDecimal teacherkeshiNum = from.getTeacherKeshiNum();//计入教师课时


        //要打考勤的学员信息
        String newstu = from.getStudatamesaage();

        if (StringUtils.isEmpty(newstu)) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("请至少勾选一名学员");
            return ajaxJson;
        }

        List<stukaoqingVo> stukaoqingVos = JSON.parseArray(newstu, stukaoqingVo.class);

        String zhujiao = from.getZhujiao();//助教

        //排课信息（天）
        rengongxiaokeVo tiankc = JSON.parseArray(from.getTiankc(), rengongxiaokeVo.class).get(0);

        Long paikeID = tiankc.getId();//排课ID
        String shangkelaoshi = tiankc.getTeacherIDs();
        if (shangkelaoshi.substring(shangkelaoshi.length() - 1, shangkelaoshi.length()).equals(",")) {
            shangkelaoshi = shangkelaoshi.substring(0, shangkelaoshi.length() - 1);
        }
        String[] teastr = shangkelaoshi.split(",");//上课老师

        String teacherNames = "";
        for (String itemts : teastr) {
            String oneStaffName = iPxstafftableService.getById(itemts).getStaffName();
            teacherNames += oneStaffName + ',';
        }

        String format = sdf.format(new Date());
        Calendar cal = Calendar.getInstance();

        //转换格式，出去时分秒带来的影响
        Date t2 = null;
        try {
            t2 = sdf.parse(sdf.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date haveClassDate = null;
        try {
            haveClassDate = sdf.parse(sdf.format(tiankc.getHaveClassDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Long classID = Long.valueOf(tiankc.getClassID());
        Long kechengID = Long.valueOf(tiankc.getKechengID());
        Time startLessonDateTime = tiankc.getStartLessonDateTime();
        Time endLessonDateTime = tiankc.getEndLessonDateTime();
        //班级总人数
        int classStuCount = iPxbuxikechengtableService.getrengongstuCount(paikeID).get(0).getStuCount();
        boolean remainksCanIsFushu = true;
        //剩余课时可以扣减为负数
        String sysParam63 = iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 63L) == null ?
         iPxsysparamdefaulttableService.getById(63).getDefaultValue() : iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 63L).getModifyValue();
        if (sysParam63 != null && sysParam63 == "否") {
            remainksCanIsFushu = false;//剩余课时不可以扣减为负数
        }

        if (haveClassDate.getTime() > t2.getTime()) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("上课日期不能大于今天");
            return ajaxJson;
        }

        if (endLessonDateTime.getTime() <= startLessonDateTime.getTime()) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("上课的结束时间要大于开始时间");
            return ajaxJson;
        }

        String week = DateUtil.getWeekOfDate(haveClassDate);
        Pxclasstable cla = iPxclasstableService.getById(classID);
        Pxkechengtable kc = iPxkechengtableService.getById(kechengID);
        String buxiStyleName = iPxbuxistyletableService.getById(kc.getBuxiStyleID()).getBuxiStyleName();

        List allnianji = new ArrayList();

        List khids = new ArrayList();

        Long teacherKSID = 0L;
        for (stukaoqingVo item : stukaoqingVos) {
            Long stusharebuxiID = null;
            BigDecimal sharekehao = new BigDecimal(0);
            Long stuidss = item.getStuID();
            String stuKaoqingStyle = item.getKaoqing();
            BigDecimal stukeshiNum = item.getKeshi();

            Pxstutable stuInfo = iPxstutableService.getById(stuidss);
            String stuNo = stuInfo.getZidingyiStuID() == null ? stuInfo.getId().toString() : stuInfo.getZidingyiStuID(); //自定义学号

            String xiugaiInfo = "学号:" + stuNo + ",姓名:" + stuInfo.getStuName() + "，后台【按排课消课】";

            //region

            //看看这个学生在这个班里的课程ID
            QueryWrapper<Pxxuanketable> xkQ = new QueryWrapper<>();
            xkQ.eq("stuID", stuidss).eq("paikeID", paikeID);
            Pxxuanketable xkTab = iPxxuanketableService.selectxuanke(xkQ).get(0);

            QueryWrapper<Pxbuxikechengtable> xkgetQ = new QueryWrapper();
            xkgetQ
                    .eq("id", xkTab.getBuxiID())
                    .eq("isShow", 1)
                    .eq("qiyeID", qiyeID);
            Pxbuxikechengtable buxikecheng = xkTab == null ? iPxbuxikechengtableService.getbxbystuclass(stuidss, classID).get(0) :
 iPxbuxikechengtableService.selectbuxikecheng(xkgetQ).get(0);

            if (buxikecheng == null) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("课程找不到（未启用或不存在）");
                return ajaxJson;
            }
            Pxkechengtable kcxx = iPxkechengtableService.getById(buxikecheng.getKechengID());
            String isKanEndDate = iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 61L) == null ?
             iPxsysparamdefaulttableService.getById(61).getDefaultValue() :
iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 61L).getModifyValue();
            if (isKanEndDate == "是") {
                if (buxikecheng.getEndDate() != null) {
                    if (buxikecheng.getEndDate().getTime() < new Date().getTime()) {
                        ajaxJson.setCode("N");
                        ajaxJson.setMsg("上课的结束日期已经小于今天，即课程已过期");
                        return ajaxJson;
                    }
                } else {
                    ajaxJson.setCode("N");
                    ajaxJson.setMsg("上课的结束日期为空，请完善课程的结束日期后再消课");
                    return ajaxJson;
                }
            }
            Integer jfstyle = buxikecheng.getJifeiStyleID();
            Long stukechengID = buxikecheng.getKechengID();
            Pxkechengtable stuKc = iPxkechengtableService.getById(stukechengID);

            xiugaiInfo += "***学员课程" + stuKc.getKechengName() + "，课程计费方式" + jfstyle.toString();

            //看课耗表是否有消耗
            QueryWrapper<Pxkeshistutable> ksstuQ = new QueryWrapper<>();
            ksstuQ
                    .eq("qiyeID", qiyeID)
                    .eq("stuID", stuidss)
                    .eq("haveClassDate", haveClassDate)
                    .eq("startLessonDateTime", startLessonDateTime)
                    .eq("endLessonDateTime", endLessonDateTime)
                    .eq("kechengID", stukechengID)
                    .eq("classID", classID)
                    .last(" and FIND_IN_SET( " + shangkelaoshi + ", teacherIDs ) ");
//                    .in("teacherIDs", teastr);
            List<Pxkeshistutable> stukehao = iPxkeshistutableService.selectkehao(ksstuQ);
            //如果有消耗 如果有课耗并且是旷课，返回错误 就不管 返回已刷过卡
            if (stukehao.size() > 0) {
                String stuName = stuInfo.getStuName();
                ajaxJson.setCode("N");
                ajaxJson.setMsg(stuName + "同学已经消过课了");
                return ajaxJson;
            } else {
                //如果无消耗  进行消课
                //学员课时消耗 课时、学费扣减
                String stuGradeName = iPxstugradetableService.getById(stuInfo.getStuGradeID()).getStugradename();
                if (!allnianji.contains(stuGradeName)) {// 没有
                    allnianji.add(stuGradeName);
                }
                //请假是否扣课时
                QueryWrapper<Pxkeshistutable> khQ = new QueryWrapper<>();
                khQ
                        .eq("stuID", stuidss)
                        .eq("kechengID", buxikecheng.getKechengID())
                        .eq("stuKaoqingStyle", "2")
                        .eq("qiyeID", qiyeID);
                int stuQinjiaTimes = iPxkeshistutableService.selectkehao(khQ).size();//请假之前次数
                if (stuKc.getIskoukeshi() == false && stuKaoqingStyle.equals("2") && (stuKc.getQingjiaTimes() == -1 || stuQinjiaTimes < stuKc.getQingjiaTimes())) {
                    stukeshiNum = new BigDecimal(0);
                    if (stuKc.getQingjiaTimes() == -1) {
                        xiugaiInfo += "，课程【" + stuKc.getKechengName() + "】设置了请假不扣减课时,且请假次数不限制，学员考勤为请假，课时为：0";
                    } else {
                        xiugaiInfo += "，课程【" + stuKc.getKechengName() + "】，设置了请假不扣减课时,且未超过请假次数限制，学员考勤为请假，课时为：0：";
                    }

                }
                if (stuKc.getIskoukeshi() == false && stuKaoqingStyle == "2" && (stuKc.getQingjiaTimes() != -1 && stuQinjiaTimes > stuKc.getQingjiaTimes())) {
                    xiugaiInfo += "，课程【" + stuKc.getKechengName() + "】，学生该课程请假次数已经达到最大值，正常扣除课时：" + stukeshiNum;
                }

                //教师课时添加
                for (String itema : teastr) {
                    String tStaffName = iPxstafftableService.getById(itema).getStaffName();

                    xiugaiInfo += "***开始记录教师课时，教师：" + tStaffName;
                    QueryWrapper<Pxkeshiteachertable> ksTeaQ = new QueryWrapper<>();
                    ksTeaQ
                            .eq("haveClassDate", haveClassDate)
                            .eq("startLessonDateTime", startLessonDateTime)
                            .eq("kechengID", kechengID)
                            .eq("teacherID", itema)
                            .eq("endLessonDateTime", endLessonDateTime)
                            .eq("qiyeID", qiyeID);

                    List<Pxkeshiteachertable> pxkeshiteachertables = iPxkeshiteachertableService.selectTeakehao(ksTeaQ);

                    if (pxkeshiteachertables.size() > 0) {
                        Pxkeshiteachertable currentTeacher = pxkeshiteachertables.get(0);
                        //如果已经有教师的课耗记录了，只是把实上人数加1
                        xiugaiInfo += "，存在教师的此次课耗记录";
                        if (stuKaoqingStyle == "1" || stuKaoqingStyle == "4" || stuKaoqingStyle == "6") {//1正常，2请假，3旷课，4迟到
                            currentTeacher.setSsStuNum(currentTeacher.getSsStuNum() + 1);
                            xiugaiInfo += "，课耗记录实上人数加1，总数为" + currentTeacher.getSsStuNum().toString();
                        }
                        currentTeacher.setAllstuNianji(currentTeacher.getAllstuNianji() + allnianji);
                        iPxkeshiteachertableService.updateById(currentTeacher);
                        teacherKSID = currentTeacher.getId();
                        xiugaiInfo += "，教师课时记录完毕";
                    } else {
                        xiugaiInfo += "，不存在教师的此次课耗记录";
                        //没有教师的课耗记录，才新增教师的课耗记录；
                        Pxkeshiteachertable onet = new Pxkeshiteachertable();
                        onet.setBuxiStyleID(kc.getBuxiStyleID());
                        onet.setCampusID(cla.getCampusID());
                        onet.setClassID(classID);
                        onet.setStuID(stuidss);
                        onet.setClassTimeStyleID(kc.getClassTimeStyleID());
                        onet.setStartLessonDateTime(startLessonDateTime);
                        onet.setEndLessonDateTime(endLessonDateTime);
                        onet.setHaveClassDate(haveClassDate);
                        onet.setKechengID(kechengID);
                        onet.setKeshiNum(teacherkeshiNum);
                        onet.setShuoMing("");
                        onet.setQiyeID(qiyeID);
                        xiugaiInfo += "，上课日期时间" + haveClassDate.toString() + " " + startLessonDateTime.toString().substring(0, 5) + "-" + endLessonDateTime.toString().substring(0, 5) + ",课时" + teacherkeshiNum.toString();
                        xiugaiInfo += "，消课学员考勤状态" + stuKaoqingStyle;
                        if (Integer.valueOf(stuKaoqingStyle) == 1 || Integer.valueOf(stuKaoqingStyle) == 4 || Integer.valueOf(stuKaoqingStyle) == 6) {//1正常，2
                            // 请假，3旷课，4迟到
                            onet.setSsStuNum(1);
                            xiugaiInfo += "，实上人数加1后为" + onet.getSsStuNum().toString();
                        } else {
                            onet.setSsStuNum(0);
                            xiugaiInfo += "实上人数不用增加";
                        }
                        if (iPxbuxistyletableService.getById(kc.getBuxiStyleID()).getBuxiStyleName() == "一对一") {
                            onet.setStuID(stuidss);
                            xiugaiInfo += "，记录为一对一";
                        } else {
                            onet.setStuID(0L);
                            xiugaiInfo += "，记录为班课";
                        }
                        onet.setTeacherID(Long.valueOf(itema));
                        onet.setTeacherkaoqing(tkaoqinstyle);
                        onet.setWeekN(week);
                        onet.setAllstuNianji(allnianji.toString());
                        onet.setYsStuNum(classStuCount);
                        onet.setZhujiao(zhujiao);
                        iPxkeshiteachertableService.save(onet);
                        teacherKSID = onet.getId();
                        xiugaiInfo += "，教师课时记录完毕";
                    }

                }

                //添加学员考勤
                QueryWrapper<Pxstukaoqingtable> stukqQ = new QueryWrapper<>();
                stukqQ
                        .eq("stuID", stuidss)
                        .eq("kechengID", stukechengID)
                        .eq("haveclassDate", haveClassDate)
                        .eq("startClassDateTime", startLessonDateTime)
                        .eq("endClassDateTime", endLessonDateTime)
                        .eq("qiyeID", qiyeID);
                List<Pxstukaoqingtable> stukqList = iPxstukaoqingtableService.selectstukaoqing(stukqQ);
                if (stukqList.size() == 0) {
                    xiugaiInfo += "，***添加学员考勤";
                    Pxstukaoqingtable onek = new Pxstukaoqingtable();
                    onek.setEndclassdatetime(endLessonDateTime);
                    onek.setStartclassdatetime(startLessonDateTime);
                    onek.setHaveclassdate(haveClassDate);
                    onek.setKaoqingstyle(Integer.valueOf(stuKaoqingStyle));
                    Integer kqsty = Integer.valueOf(stuKaoqingStyle);
                    String kqstyle = kqsty == 1 ? "正常" : kqsty == 2 ? "请假" : kqsty == 3 ? "旷课" : kqsty == 4 ? "迟到" : kqsty == 5 ? "早退" : "补课";
                    onek.setKaoqingbeizhu(kqstyle);
                    onek.setKechengid(stukechengID);
                    onek.setClassid(classID);
                    onek.setStuid(stuidss);
                    onek.setTeacherids(shangkelaoshi);
                    onek.setTeachernames(teacherNames);
                    onek.setQiyeid(qiyeID);
                    iPxstukaoqingtableService.save(onek);

                    for (String itemt : teastr) {
                        xiugaiInfo += "，***添加学生考勤教师记录";
                        Pxstukaoqingteachertable stt = new Pxstukaoqingteachertable();
                        stt.setTeacherid(Long.valueOf(itemt));
                        stt.setStukaoqingtabid(onek.getId());
                        stt.setQiyeID(qiyeID);
                        iPxstukaoqingteachertableService.save(stt);
                    }
                }


                xiugaiInfo += "，***扣减学员课时和学费";
                BigDecimal ksPrice = new BigDecimal("0");
                if (jfstyle == 1 || jfstyle == 2) {
                    xiugaiInfo += "，课程类型为按课时或课时包计费";
                    //如果该课程的剩余课时扣除本次课时后，剩余课时为负数
                    if (buxikecheng.getRemainkeshi().subtract(stukeshiNum).intValue() < 0) {
                        xiugaiInfo += "，学员剩余课时不够扣减";
                        //看看是否有同ID且课时够扣的课程是隐藏的
                        QueryWrapper<Pxbuxikechengtable> bx2 = new QueryWrapper<>();
                        bx2
                                .eq("qiyeID", qiyeID)
                                .eq("kechengID", stukechengID)
                                .eq("stuID", stuidss)
                                .eq("isShow", 0)
                                .ge("remainkeshi", stukeshiNum);
                        Pxbuxikechengtable buxikecheng2 = iPxbuxikechengtableService.selectbuxikecheng(bx2).get(0);
                        if (buxikecheng2 != null) {
                            //新老课程切换
                            String kcQiehuan = kechengQiehuan.kechengQiehuan(buxikecheng, buxikecheng2, buxiStyleName, classID, staffID, qiyeID);
                            xiugaiInfo += kcQiehuan;
                            xiugaiInfo += "，消课前剩余课时" + buxikecheng2.getRemainkeshi().toString() + "，要扣减课时" + stukeshiNum.toString();
                            buxikecheng2.setRemainkeshi(buxikecheng2.getRemainkeshi().subtract(stukeshiNum));//扣减课时
                            iPxbuxikechengtableService.updateById(buxikecheng2);
                            xiugaiInfo += "，消课后剩余课时" + buxikecheng2.getRemainkeshi().toString();

                            if (buxikecheng2.getType() == 2) {
                                //如果该条记录是赠送课时，则不记单价，也不扣除学员学费
                                //类型：为空或1买的，2赠送的，3转送得到的，4转送出去的
                                ksPrice = l;
                                xiugaiInfo += "，课程记录是赠送的，消课单价置0，不扣减学费";

                            } else {
                                ksPrice = buxikecheng2.getKechengprice();
                                xiugaiInfo += "，消课单价" + ksPrice.toString();
                                xiugaiInfo += "，扣减学费前剩余学费" + stuInfo.getRemainXuefei().toString();
                                stuInfo.setRemainXuefei(stuInfo.getRemainXuefei().subtract(stukeshiNum.multiply(ksPrice)));
                                iPxstutableService.updateById(stuInfo);
                                xiugaiInfo += "，扣减学费后剩余学费" + stuInfo.getRemainXuefei().toString();
                            }
                        } else {

                            xiugaiInfo += "，不存在相同且够扣的补习课程记录";
                            if (!buxikecheng.getShareBuxiID().equals("0")) {
                                //有共享课时
                                String[] sharebuxi = buxikecheng.getShareBuxiID().split(",");
                                for (String items : sharebuxi) {
                                    Pxbuxikechengtable onesharebx = iPxbuxikechengtableService.getById(items);
                                    Pxstutable sharestu = iPxstutableService.getById(onesharebx.getStuID());
                                    Pxkechengtable sharekc = iPxkechengtableService.getById(onesharebx.getKechengID());
                                    if (onesharebx.getRemainkeshi().compareTo(stukeshiNum) == 1) { //补习课程剩余课时>扣减课时
                                        xiugaiInfo += "。存在够扣减的共享课时,共享了:" + sharestu.getStuName() + "的" + sharekc.getKechengName() + "课程";

                                        BigDecimal shareMoney = buxikecheng.getKechengprice().multiply(stukeshiNum);
                                        BigDecimal sharekeshi = shareMoney.divide(onesharebx.getKechengprice());
                                        sharekehao = sharekeshi;
                                        onesharebx.setRemainkeshi(onesharebx.getRemainkeshi().subtract(sharekeshi));
                                        iPxbuxikechengtableService.updateById(onesharebx);

                                        sharestu.setRemainXuefei(sharestu.getRemainXuefei().subtract(shareMoney));
                                        iPxstutableService.updateById(sharestu);

                                        stusharebuxiID = onesharebx.getId();
                                        break;  //跳出并停止循环

                                    }
                                }

                            } else {
                                if (remainksCanIsFushu == false) {
                                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                                    ajaxJson.setCode("N");
                                    ajaxJson.setMsg(stuInfo.getStuName() + ",剩余课时会扣减为负数，系统设置不允许课时扣减为负数!");
                                    return ajaxJson;
                                }
                                //可扣减为负，原课时扣
                                /*减剩余课时*/
                                xiugaiInfo += "，课时扣减前，剩余课时：" + buxikecheng.getRemainkeshi().toString() + "，要扣减课时" + stukeshiNum.toString();
                                buxikecheng.setRemainkeshi(buxikecheng.getRemainkeshi().subtract(stukeshiNum));
                                xiugaiInfo += "，课时扣减后，剩余课时：" + buxikecheng.getRemainkeshi().toString();
                                if (buxikecheng.getType() == 2) {
                                    //如果该条记录是赠送课时，则不记单价，也不扣除学员学费
                                    //类型：为空或1买的，2赠送的，3转送得到的，4转送出去的
                                    ksPrice = l;
                                    xiugaiInfo += "，课程记录是赠送的，消课单价置0，不扣减学费";
                                } else {
                                    ksPrice = buxikecheng.getKechengprice();
                                    xiugaiInfo += "，消课单价" + ksPrice.toString();
                                    xiugaiInfo += "，扣减学费前剩余学费" + stuInfo.getRemainXuefei().toString();
                                    stuInfo.setRemainXuefei(stuInfo.getRemainXuefei().subtract(stukeshiNum.multiply(ksPrice)));
                                    iPxstutableService.updateById(stuInfo);
                                    xiugaiInfo += "，扣减学费后剩余学费" + stuInfo.getRemainXuefei().toString();
                                }
                            }
                        }
                    } else {
                        xiugaiInfo += "，学员剩余课时够扣减";
                        /*减剩余课时*/
                        xiugaiInfo += "，课时扣减前，剩余课时" + buxikecheng.getRemainkeshi().toString() + "，要扣减课时" + stukeshiNum.toString();
                        buxikecheng.setRemainkeshi(buxikecheng.getRemainkeshi().subtract(stukeshiNum));
                        iPxbuxikechengtableService.updateById(buxikecheng);
                        xiugaiInfo += "，课时扣减后，剩余课时" + buxikecheng.getRemainkeshi().toString();

                        if (buxikecheng.getType() == 2) {
                            //如果该条记录是赠送课时，则不记单价，也不扣除学员学费
                            //类型：为空或1买的，2赠送的，3转送得到的，4转送出去的
                            ksPrice = l;
                            xiugaiInfo += "，该补习课程为赠送课程，消课单价：0，扣减学费：0";
                        } else {
                            ksPrice = buxikecheng.getKechengprice();
                            xiugaiInfo += "，消课单价" + ksPrice.toString();
                            xiugaiInfo += "，扣减学费前剩余学费" + stuInfo.getRemainXuefei().toString();
                            stuInfo.setRemainXuefei(stuInfo.getRemainXuefei().subtract(stukeshiNum.multiply(ksPrice)));
                            iPxstutableService.updateById(stuInfo);
                            xiugaiInfo += "，扣减学费后剩余学费" + stuInfo.getRemainXuefei().toString();
                        }
                    }
                }
                xiugaiInfo += "，【添加学员课时记录】";
                //学员课时消耗
                Pxbuxikechengtable bkByDay = new Pxbuxikechengtable();
                Pxkechengtable kcTab = iPxkechengtableService.getById(buxikecheng.getKechengID());
                Pxkeshistutable ones = new Pxkeshistutable();
                ones.setAddtime(new Date());
                ones.setAdduser(staffID);
                ones.setQiyeID(qiyeID);
                ones.setBuxiStyleID(kcTab.getBuxiStyleID());
                ones.setCampusID(cla.getCampusID());
                ones.setStuGradeID(stuInfo.getStuGradeID());
                ones.setClassID(classID);
                ones.setClassTimeStyleID(kcTab.getClassTimeStyleID());
                ones.setKechengID(stukechengID);
                ones.setHaveClassDate(haveClassDate);
                ones.setWeekN(week);
                ones.setStartLessonDateTime(startLessonDateTime);
                ones.setEndLessonDateTime(endLessonDateTime);
                bkByDay = buxikecheng;
                if (jfstyle == 1 || jfstyle == 2) {
                    if (stusharebuxiID != null && stusharebuxiID != 0) {
                        //有进行共享消课
                        ones.setShareBuxiID(stusharebuxiID);
                        Pxbuxikechengtable one = iPxbuxikechengtableService.getById(stusharebuxiID);
                        ones.setKechengPrice(one.getKechengprice());
                        ones.setKeshiNum(sharekehao);//课时
                        xiugaiInfo += "学员考勤类别" + stuKaoqingStyle + "，课程按课时或课时包计费，单价：" + one.getKechengprice().toString() + ",课时：" + sharekehao.toString();
                    } else {
                        ones.setShareBuxiID(0L);
                        ones.setKechengPrice(ksPrice);
                        ones.setKeshiNum(stukeshiNum);//课时
                        xiugaiInfo += "学员考勤类别" + stuKaoqingStyle + "，课程按课时或课时包计费，单价：" + ksPrice.toString() + ",课时：" + stukeshiNum.toString();
                    }
                } else {
                    //未在有效时间课程切换
                    if (bkByDay.getEndDate().getTime() < t2.getTime() || bkByDay.getStartDate().getTime() > t2.getTime()) {
                        //查找有效时间内最早的一条记录
                        QueryWrapper<Pxbuxikechengtable> bxkcQ = new QueryWrapper<>();
                        bxkcQ
                                .le("startDate", t2)
                                .ge("endDate", t2)
                                .eq("kechengID", stukechengID)
                                .eq("stuID", stuidss)
                                .eq("isShow", 1)
                                .eq("qiyeID", qiyeID);
                        Pxbuxikechengtable cznewbxTab = iPxbuxikechengtableService.selectbuxikecheng(bxkcQ).get(0);
                        if (cznewbxTab != null) {
                            bkByDay = cznewbxTab;

                            //判断插班，插班表（切换班级）
                            QueryWrapper<Pxstuclasstable> stuclaQ = new QueryWrapper<>();
                            stuclaQ
                                    .eq("buxiID", buxikecheng.getId())
                                    .eq("qiyeID", qiyeID);
                            List<Pxstuclasstable> pdstuclassTab = iPxstuclasstableService.selectstuclass(stuclaQ);
                            for (Pxstuclasstable nstuc : pdstuclassTab) {
                                nstuc.setBuxiID(cznewbxTab.getId());
                                iPxstuclasstableService.updateById(nstuc);
                            }

                            //判断排课，选课表（切换排课课程）
                            QueryWrapper<Pxxuanketable> xkTabQ = new QueryWrapper<>();
                            xkTabQ
                                    .eq("buxiID", buxikecheng.getId())
                                    .eq("qiyeID", qiyeID);
                            List<Pxxuanketable> pdxuanke = iPxxuanketableService.selectxuanke(xkTabQ);
                            for (Pxxuanketable nxk : pdxuanke) {
                                Pxpaiketable oneClapk = iPxpaiketableService.getById(nxk.getPaikeID());
                                //存在课耗选课不替换
                                QueryWrapper<Pxkeshistutable> pdkhQ = new QueryWrapper<>();
                                pdkhQ
                                        .eq("kechengID", oneClapk.getKechengID())
                                        .eq("classID", oneClapk.getClassID())
                                        .eq("stuID", buxikecheng.getStuID())
                                        .eq("haveClassDate", oneClapk.getHaveClassDate())
                                        .eq("startLessonDateTime", oneClapk.getStartLessonDateTime())
                                        .eq("endLessonDateTime", oneClapk.getEndLessonDateTime())
                                        .eq("qiyeID", qiyeID);
                                List<Pxkeshistutable> pdkehao = iPxkeshistutableService.selectkehao(pdkhQ);
                                if (pdkehao.size() == 0) {
                                    nxk.setBuxiID(cznewbxTab.getId());
                                    iPxxuanketableService.updateById(nxk);
                                }
                            }

                        }
                    }

                    QueryWrapper<Pxkeshistutable> pdtodayQ = new QueryWrapper<>();
                    pdtodayQ
                            .eq("kechengID", stukechengID)
                            .eq("stuID", stuidss)
                            .eq("haveClassDate", haveClassDate)
                            .eq("qiyeID", qiyeID);
                    Pxkeshistutable pdtodayxiakeTab = iPxkeshistutableService.selectkehao(pdtodayQ).get(0);
                    Pxkechengtable stuKC = iPxkechengtableService.getById(stukechengID);
                    String classTime = iPxclasstimestyletableService.getById(stuKC.getClassTimeStyleID()).getClasstimestylename();
                    int day = 1;

                    //判断老数据与新数据差别消课
                    if (bkByDay.getKeshiNum().intValue() != 0 && bkByDay.getRemainkeshi().intValue() != 0) {
                        ones.setKechengPrice(bkByDay.getKechengprice());
                        xiugaiInfo += "，按起止日期计费课程，一天的学费" + bkByDay.getKechengprice().toString();

                        Pxstutable dayStu = iPxstutableService.getById(stuidss);
                        xiugaiInfo += "，消课前学员剩余学费" + dayStu.getRemainXuefei().toString();
                        //判断今天是否已经存在消课记录，存在将不再扣课时
                        if (pdtodayxiakeTab != null) {
                            ones.setKeshiNum(l); //课时
                        } else {
                            ones.setKeshiNum(y);
                            bkByDay.setRemainkeshi(bkByDay.getRemainkeshi().subtract(y));
                            dayStu.setRemainXuefei(dayStu.getRemainXuefei().subtract(bkByDay.getKechengprice()));
                            iPxbuxikechengtableService.updateById(bkByDay);
                            iPxstutableService.updateById(dayStu);
                        }
                        xiugaiInfo += "，消课后学员剩余学费" + dayStu.getRemainXuefei().toString();
                    } else {
                        //按起止日期计费的课程
                        long days = (bkByDay.getEndDate().getTime() - bkByDay.getStartDate().getTime()) / (24 * 1000 * 60 * 60); ///1000毫秒*60s*60m*24h
                        BigDecimal D = new BigDecimal(days);
                        //计算一天价钱
                        BigDecimal onedayMoney = bkByDay.getZongjia().divide(D);

                        ones.setKechengPrice(onedayMoney);
                        xiugaiInfo += "，按起止日期计费课程，一天的学费" + onedayMoney.toString();

                        Pxstutable dayStu = iPxstutableService.getById(stuidss);
                        xiugaiInfo += "，消课前学员剩余学费" + dayStu.getRemainXuefei().toString();
                        //判断今天是否已经存在消课记录，存在将不再扣课时
                        if (pdtodayxiakeTab != null) {
                            ones.setKeshiNum(l);
                        } else {
                            ones.setKeshiNum(y);
                            dayStu.setRemainXuefei(dayStu.getRemainXuefei().subtract(onedayMoney));
                            iPxstutableService.updateById(dayStu);
                        }
                        xiugaiInfo += "，消课后学员剩余学费" + dayStu.getRemainXuefei().toString();
                    }

                }
                ones.setStuID(stuidss);
                ones.setStuKaoqingStyle(stuKaoqingStyle);
                ones.setDakaoqingStyle(2);
                ones.setTeacherIDs(shangkelaoshi);
                ones.setTeacherNames(teacherNames);
                xiugaiInfo += "，上课教师姓名" + teacherNames;
                ones.setShuoMing("后台按排课消课");
                ones.setBuxikechengID(bkByDay.getId());
                ones.setBanzhurenStaffID(stuInfo.getBanzhurenTeacherID());
                iPxkeshistutableService.save(ones);
                xiugaiInfo += "，学员课时保存完毕";
                khids.add(ones.getId());
                for (String itema : teastr) {
                    Pxkeshistuteachertable kstuteach = new Pxkeshistuteachertable();
                    kstuteach.setTeacherid(Long.valueOf(itema));
                    kstuteach.setKeshistutableid(ones.getId());
                    kstuteach.setQiyeID(qiyeID);
                    iPxkeshistuteachertableService.save(kstuteach);
                }

                //课耗产生积分
                Pxstutable stu = iPxstutableService.getById(stuidss);
                BigDecimal oldJifen = stu.getJifenNum(); //原本积分
                String jfen = iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 72L) == null ?
                 iPxsysparamdefaulttableService.getById(72).getDefaultValue() :
                  iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 72L).getModifyValue();
                //消课积分比例设置
                BigDecimal jfn = new BigDecimal(jfen);
                if (Integer.valueOf(jfen) > 0) {
                    Pxjifentable JF = new Pxjifentable();
                    stu.setJifenNum(stu.getJifenNum().add(jfn.multiply(stukeshiNum)));//改变学生表积分
                    iPxstutableService.updateById(stu);
                    JF.setStuID(stuidss);
                    JF.setType(1);
                    JF.setOldintegral(oldJifen);
                    JF.setIntegral(jfn.multiply(stukeshiNum));
                    JF.setStaffID(staffID);
                    JF.setCreatetime(t2);
                    JF.setRemark("手动录入课耗信息，产生积分");
                    JF.setQiyeID(qiyeID);
                    iPxjifentableService.save(JF);
                }
                Pxstutable msgstu = iPxstutableService.getById(stuidss);
                String msgStuName = msgstu.getStuName();
                String stuCampusName = iPxcampustableService.getById(msgstu.getCampusID()).getCampusName();
                String kechengName = iPxkechengtableService.getById(stukechengID).getKechengName();
                //模板消息ID
                String templateId = "";

                //region 向家长推送积分变动提醒
                Pxtuisongtable pxtuisongtable = new Pxtuisongtable()
                        .setTuisongTypeName(4L)
                        .setAddTime(new Date())
                        .setQiyeID(loginUser.getQiyeID())
                        .setAddStaffID(loginUser.getStaffID())
                        .setAppread(0)
                        .setWxstate(0)
                        .setKechengID(kechengID)
                        .setNote("按排课消课扣减课时，自动获得：" + stukeshiNum + "个积分！")
                        .setRole(1)
                        .setStuID(msgstu.getId());
                iPxtuisongtableService.save(pxtuisongtable);
                sendMessage.sendMessage(pxtuisongtable.getId());

                //endregion
                //家长推送孩子的剩余课时
                // 如果剩余课时低于预警值，给家长发送续费提醒;
                BigDecimal stuKechengRemainKeshi = l;
                String classWarning = iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 13L) == null ?
                 iPxsysparamdefaulttableService.getById(13).getDefaultValue() :
                  iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 13L).getModifyValue();
                if (classWarning == "总课时预警") {
                    stuKechengRemainKeshi = iPxbuxikechengtableService.getSumzongRks(stuidss).get(0).getSumR();
                } else {
                    stuKechengRemainKeshi = iPxbuxikechengtableService.getSumRks(stuidss, stukechengID, Long.valueOf(qiyeID)).get(0).getSumR();
                }

                String yujinzhi = iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 11L) == null ?
                 iPxsysparamdefaulttableService.getById(11).getDefaultValue() :
                  iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 11L).getModifyValue();
                Pxpaiketable paikeTab = iPxpaiketableService.getById(paikeID);
                String kcnritong = "";
                if (!StringUtils.isEmpty(paikeTab.getKechengContent())) {
                    if (paikeTab.getKechengContent().length() > 20) {
                        kcnritong = ",内容：" + paikeTab.getKechengContent().substring(0, 20) + "。";
                    } else {
                        kcnritong = ",内容：" + paikeTab.getKechengContent() + "。";
                    }

                }


                String noticeType = "";
                String noticeContent = "";
                int type = 29;
                Pxsysparamvaluetable getvalue = iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 61L);
                Pxsysparamvaluetable getqinglin = iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 62L);
                boolean isshowEnddate = getvalue != null ? getvalue.getModifyValue() == "是" ? true : false :
                 iPxsysparamdefaulttableService.getById(61).getDefaultValue() == "是" ? true : false;//是否在（补习）课程起止日期之前消课
                boolean isshowEnddate1 = getvalue != null ? getqinglin.getModifyValue() == "是" ? true : false :
                 iPxsysparamdefaulttableService.getById(61).getDefaultValue() == "是" ? true : false;//课时自动清零

                if (stuKechengRemainKeshi.intValue() > Integer.valueOf(yujinzhi)) {
                    noticeType = "温馨提示";
                    if (jfstyle == 3) {
                        noticeContent =
                                msgStuName + "的课程《" + kechengName + "》" + DateUtil.formatDate2(haveClassDate) + " " + startLessonDateTime.toString().substring(0, 5) + "-" + endLessonDateTime.toString().substring(0, 5) + kcnritong + "已消课";
                    } else {
                        if (classWarning == "总课时预警") {
                            noticeContent =
                                    msgStuName + "的课程《" + kechengName + "》" + DateUtil.formatDate2(haveClassDate) + " " + startLessonDateTime.toString().substring(0
 , 5) + "-" + endLessonDateTime.toString().substring(0, 5) + kcnritong + "上课消课" + stukeshiNum + "课时，总课时剩余" + stuKechengRemainKeshi + "课时。";
                        } else {
                            noticeContent =
                             msgStuName + "的课程《" + kechengName + "》" + DateUtil.formatDate2(haveClassDate) + " " + startLessonDateTime.toString().substring(0
                             , 5) + "-" + endLessonDateTime.toString().substring(0, 5) + kcnritong + "上课消课" + stukeshiNum + "课时，单科课时剩余" + stuKechengRemainKeshi + "课时。";
                        }
                    }
                } else {
                    type = 1;
                    if (jfstyle == 3) {
                        noticeType = "温馨提示";
                        noticeContent =
                         msgStuName + "的课程[" + kechengName + "]" + DateUtil.formatDate2(haveClassDate) + " " + startLessonDateTime.toString().substring(0, 5) + "-" + endLessonDateTime.toString().substring(0, 5) + kcnritong + "已消课";
                    } else {
                        if (classWarning == "总课时预警") {
                            noticeType = "续费提醒";
                            noticeContent =
                            msgStuName + "的课程[" + kechengName + "]" + DateUtil.formatDate2(haveClassDate) + " " + startLessonDateTime.toString().substring(0
                             , 5) + "-" + endLessonDateTime.toString().substring(0, 5) + kcnritong + "上课消课" + stukeshiNum + "课时，总课时剩余" + stuKechengRemainKeshi + "课时，请及时到我校续费。";
                        } else {
                            noticeType = "续费提醒";
                            noticeContent =
                             msgStuName + "的课程[" + kechengName + "]" + DateUtil.formatDate2(haveClassDate) + " " + startLessonDateTime.toString().substring(0
                             , 5) + "-" + endLessonDateTime.toString().substring(0, 5) + kcnritong + "上课消课" + stukeshiNum + "课时，单科课时剩余" + stuKechengRemainKeshi + "课时，请及时到我校续费。";
                        }
                    }
                }

                if (isshowEnddate == true || isshowEnddate1 == true) {
                    noticeContent += "课程到期时间：" + buxikecheng.getEndDate().toString();
                }
                String noticeRemark = stuCampusName;

                //region 推送给家长
                Pxtuisongtable pxtuisongtable1 = new Pxtuisongtable()
                        .setTuisongTypeName(5L)
                        .setNote(noticeContent)
                        .setRole(1)
                        .setStuID(stuidss)
                        .setQiyeID(loginUser.getQiyeID())
                        .setAddStaffID(loginUser.getStaffID())
                        .setAddTime(new Date())
                        .setKechengID(stukechengID)
                        .setAppread(0)
                        .setWxstate(0)
                        .setHaveclassDate(haveClassDate)
                        .setStartLessonDateTime(startLessonDateTime)
                        .setEndLessonDateTime(endLessonDateTime);
                iPxtuisongtableService.save(pxtuisongtable1);
                sendMessage.sendMessage(pxtuisongtable1.getId());
                //endregion
            }

            //endregion

            //如果打了考勤的人数已经大于或等于班级人数，则改变这次排课的考勤状态
            QueryWrapper<Pxstukaoqingtable> stukqTabQ = new QueryWrapper<>();
            stukqTabQ
                    .eq("haveclassDate", haveClassDate)
                    .eq("startClassDateTime", startLessonDateTime)
                    .eq("endClassDateTime", endLessonDateTime)
                    .eq("classID", classID)
//                    .in("teacherIDs", teastr)
                    .eq("qiyeID", qiyeID)
                    .last(" and FIND_IN_SET( " + shangkelaoshi + ", teacherIDs ) ");
            int classStuKaoqingCount = iPxstukaoqingtableService.selectstukaoqing(stukqTabQ).size() + 1;
            xiugaiInfo += "，应上总人数" + classStuCount + ",已完成考勤人数" + classStuKaoqingCount;
            if (classStuKaoqingCount == classStuCount) {

                Pxpaiketable paike = iPxpaiketableService.getById(paikeID);
                if (paike != null) {
                    paike.setDakaoqin(true);
                    iPxpaiketableService.updateById(paike);
                    xiugaiInfo += "，排课考勤状态为已考勤";
                }
            }
        }

        ajaxJson.setMsg("排课消课成功，产生课耗ID为：" + khids);
        return ajaxJson;
    }

    /**
     * @Description: getxiaokedayingPage()方法作用:是否消课后打印
     * @param:[current, size]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/1/15 10:54
     */
    @RequestMapping(value = "getxiaokedaying", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "(人工消课)消课打印详情")
    public AjaxJson getxiaokedaying(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<Pxsysparamvaluetable> q = new QueryWrapper<>();
        q
                .eq("qiyeID", qiyeID)
                .eq("sysparamTypeID", 71);
        List<Pxsysparamvaluetable> qysys = iPxsysparamvaluetableService.selectsysvalue(q);
        String modifyvalue = "";
        if (qysys.size() != 0) {
            modifyvalue = qysys.get(0).getModifyValue();
        } else {
            modifyvalue = iPxsysparamdefaulttableService.getById(71).getDefaultValue();
        }
        ajaxJson.setObj(modifyvalue);
        return ajaxJson;
    }


    /**
     * @Description: getxiaokedayingPage()方法作用:(人工消课)消课打印详情
     * @param:[current, size]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/21 9:45
     */
    @RequestMapping(value = "getxiaokedayingPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "(人工消课)消课打印详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据量", example = "1", required = true),
    })
    public AjaxJson getxiaokedayingPage(Long current, Long size) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<xiaokedayingVo> page = new Page(current, size);
        ajaxJson.setObj(iPxkeshistutableService.getxiaokedayingPage(page));
        return ajaxJson;
    }


    /**
     * @Description: getclasskaoqingPage()方法作用:(人工消课)班级考勤详情
     * @param:[current, size, paikeID, classID]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/18 15:07
     */
    @RequestMapping(value = "getclasskaoqingPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "(人工消课)班级考勤详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据量", example = "1", required = true),
            @ApiImplicitParam(name = "paikeID", value = "排课ID", required = true),
            @ApiImplicitParam(name = "classID", value = "班级ID", required = true),
    })
    public AjaxJson getclasskaoqingPage(Long current, Long size, Long paikeID, Long classID) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<classkaoqingVo> page = new Page(current, size);
        ajaxJson.setObj(iPxbuxikechengtableService.getclasskaoqingPage(page, paikeID, classID));
        return ajaxJson;
    }

    //endregion

    //region 刷卡消课


    /**
     * @Description: getshuakaxiaokePage()方法作用:分页获取刷卡消课
     * @param:[current, size, stuID, cardNumber, campusID, stuName, TeacherID, buxiStyleID]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/16 17:38
     */
    @ResponseBody
    @RequestMapping(value = "getshuakaxiaokePage", method = RequestMethod.GET)
    @ApiOperation("分页获取刷卡消课")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页获取的数据量", example = "10", required = true),
            @ApiImplicitParam(name = "stuID", value = "学号", required = false),
            @ApiImplicitParam(name = "cardNumber", value = "卡号", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "stuName", value = "学员姓名", required = false),
            @ApiImplicitParam(name = "teacherNames", value = "教师", required = false),
            @ApiImplicitParam(name = "buxiStyleID", value = "培训方式", required = false),
    })
    public AjaxJson getshuakaxiaokePage(Long current, Long size, String stuID, String cardNumber, String
            campusID, String stuName, String teacherNames, String buxiStyleID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<shuakaxiaokeVo> page = new Page<>(current, size);
        QueryWrapper<shuakaxiaokeVo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("1", 1)
                .eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(stuID)) {
            queryWrapper
                    .like("b.zidingyiStuID", stuID)
                    .or().eq("a.stuID", stuID);
        }
        if (StringUtils.isNotBlank(cardNumber)) {
            queryWrapper.like("d.cardNumber", cardNumber);
        }
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("b.campusID", campusID);
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("b.stuName", stuName);
        }
        if (StringUtils.isNotBlank(teacherNames)) {
            queryWrapper.like("f.teacherNames", teacherNames);
        }
        if (StringUtils.isNotBlank(buxiStyleID)) {
            queryWrapper.eq("g.buxiStyleID", buxiStyleID);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 244);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("b.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("b.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("b.campusID", lookPower);
        }
        ajaxJson.setObj(iPxqiandaoqiantuitableService.getshuakaxiaokePage(page, queryWrapper));
        return ajaxJson;
    }


    /**
     * @Description: Exportshuakaxiaoke()方法作用:导出刷卡消课
     * @param:[response, campusID, startDate, endDate]
     * @return:void
     * @auter:yyl
     * @data:2020/12/16 10:23
     */
    @RequestMapping(value = "Exportshuakaxiaoke", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出刷卡消课")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "startDate", value = "开始时间", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束时间", required = false),
    })
    public void Exportshuakaxiaoke(HttpServletResponse response, HttpServletRequest request,
                                   @RequestParam(required = false) String campusID, // 校区ID
                                   @RequestParam(required = false) String startDate, // 开始日期
                                   @RequestParam(required = false) String endDate // 结束日期
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper<shuakaxiaokeVo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("1", 1)
                .eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("b.campusID", campusID);
        }
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("a.qianDatetime", startDate);
        }
        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("a.qianDatetime", endDate);
        }
        List<shuakaxiaokeVo> exportshuakaxiaoke = iPxqiandaoqiantuitableService.Exportshuakaxiaoke(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "学号", "卡号", "姓名", "教师", "课时", "培训方式", "上课日期", "上课时间", "下课时间", "刷卡时间"},
                exportshuakaxiaoke,
                new String[]{"campusName", "stuID", "cardNumber", "stuName", "teacherNames", "keshi", "buxiStyleName", "haveClassDate-D",
                        "startLessonDateTime-T", "endLessonDateTime-T", "qianDatetime-D"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "刷卡消课导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @Description: saveShaka()方法作用:刷卡扣课时
     * @param:[cardNumber]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/16 17:38
     */
    @ResponseBody
    @RequestMapping(value = "saveShaka", method = RequestMethod.POST)
    @ApiOperation("保存刷卡扣课时")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cardNumber", value = "学员卡号", required = true)
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson saveShaka(String cardNumber, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        Long stusharebuxiID = null;
        BigDecimal sharekehao = new BigDecimal(0);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();

        BigDecimal l = new BigDecimal(0);
        //qiandao:0失败,1扣除成功吗，2签到成功，3扣除成功且签到成功
        int qiandao = 0;

        if (StringUtils.isEmpty(cardNumber)) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("请输入学生卡号或请刷卡");
            return ajaxJson;
        }
        Pxstucardtable stuCard = null;
        QueryWrapper<Pxstucardtable> skQ = new QueryWrapper<>();
        skQ
                .eq("cardNumber", cardNumber)
                .eq("qiyeID", qiyeID);
        List<Pxstucardtable> selectstucard = iPxstucardtableService.selectstucard(skQ);
        if (selectstucard.size() == 0) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("该学员卡还未分配给任何学员！请前往【学员管理——学员卡管理】进行分配学员卡");
            return ajaxJson;
        } else {
            stuCard = selectstucard.get(0);
        }

        Pxstutable stu = iPxstutableService.getById(stuCard.getStuID());
        String stuNo = stu.getZidingyiStuID() == null ? stu.getId().toString() : stu.getZidingyiStuID();//获取学号（自定义学号）
        Long shuakaStuID = stu.getId();//学员ID

        String xiugaiInfo = "学号：" + stuNo + ",姓名:" + stu.getStuName() + "，【刷卡消课】";
        if (stu.getBuxiStateID() != 2) {
            //不是在读学员
            ajaxJson.setCode("N");
            ajaxJson.setMsg("该学员不是在读状态，先设置学员为在读状态才能刷卡消课");
            return ajaxJson;
        }
        QueryWrapper<Pxqiandaoqiantuitable> qdqtQ = new QueryWrapper<>();
        qdqtQ
                .eq("stuID", stu.getId())
                .eq("qiandaoOrqiantui", 1)
                .eq("qiyeID", qiyeID)
                .orderByDesc("qianDatetime");
        Pxqiandaoqiantuitable pdqiandao = null;
        List<Pxqiandaoqiantuitable> selectqiaodao = iPxqiandaoqiantuitableService.selectqiaodao(qdqtQ);
        if (selectqiaodao.size() > 0) {
            pdqiandao = selectqiaodao.get(0);
        }

        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, -30);
        Date time = cal.getTime();

        if ((pdqiandao != null && time.getTime() > pdqiandao.getQiandatetime().getTime()) || pdqiandao == null) {
            //添加签到记录
            Pxqiandaoqiantuitable one = new Pxqiandaoqiantuitable();
            one.setQiandatetime(new Date());
            one.setAddstaffid(staffID);
            one.setQianstyle(1);//刷卡
            one.setTsstate(false);
            one.setStuid(shuakaStuID);
            one.setQiyeID(qiyeID);
            one.setQiandaoorqiantui(1);
            iPxqiandaoqiantuitableService.save(one);

            List<Pxwxusertable> wxmessage = iPxwxusertableService.getuserList(shuakaStuID, Long.valueOf(qiyeID));

            int tsnum = 0;
            //region 推送给家长签到信息
//            for(Pxwxusertable item:wxmessage){
//
//            }

            //endregion

            if (tsnum > 0) {
                one.setTsstate(true);
                qiandao = 2;
                iPxqiandaoqiantuitableService.updateById(one);
                xiugaiInfo += "刷卡签到成功";
            }
            //计算刷卡时间
            String format = sdf.format(new Date());
            Date zhong = null;  //课中时间
            try {
                zhong = sdf.parse(format);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String kz = sdf.format(zhong); //课中时间

            cal.setTime(zhong);
            cal.add(Calendar.MINUTE, -30);
            String kaishi = sdf.format(cal.getTime());  //课前30分钟

            cal.setTime(zhong);
            cal.add(Calendar.MINUTE, 30);
            String jiesu = sdf.format(cal.getTime());  //课后30分钟

            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            String skDT = sdf2.format(new Date());


            shuakaxkgetPKVo paike = null;
            List<shuakaxkgetPKVo> shuakaxkgetPKVos = iPxpaiketableService.getshuakaxkgetPKList(Long.valueOf(qiyeID), skDT, cardNumber, kaishi, jiesu, kz);

            if (shuakaxkgetPKVos.size() == 0) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("没有查询到相关排课信息，要排了课才能刷卡打课时！");
                return ajaxJson;
            } else {
                paike = shuakaxkgetPKVos.get(0);
            }
            //给签到签退表添加排课信息
            one.setPaikeid(paike.getId());
            iPxqiandaoqiantuitableService.updateById(one);

            Pxkechengtable pkkc = iPxkechengtableService.getById(paike.getKechengID());
            String buxiStyleName = iPxbuxistyletableService.getById(pkkc.getBuxiStyleID()).getBuxiStyleName();

            //班级总人数
            int stuCount = iPxbuxikechengtableService.getstuCount(paike.getId()).get(0).getStuCount();


            ////-----------

            Boolean remainksCanIsFushu = true;   //剩余课时可以扣减为负数
            String defaultValue = iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 63L) == null ?
             iPxsysparamdefaulttableService.getById(63).getDefaultValue() :
              iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 63L).getModifyValue();
            if (defaultValue != null && defaultValue == "否") {
                remainksCanIsFushu = false; //剩余课时不可以扣减为负数
            }
            QueryWrapper<Pxkeshistutable> ksstuQ = new QueryWrapper<>();
            ksstuQ
                    .eq("classID", paike.getClassID())
                    .eq("stuID", shuakaStuID)
                    .eq("haveClassDate", paike.getHaveClassDate())
                    .eq("weekN", paike.getWeekN())
                    .eq("startLessonDateTime", paike.getStartLessonDateTime())
                    .eq("endLessonDateTime", paike.getEndLessonDateTime())
                    .eq("qiyeID", qiyeID);
            List<Pxkeshistutable> ishavekeshi = iPxkeshistutableService.selectkehao(ksstuQ);

            if (ishavekeshi.size() > 0) {
                ksstuQ
                        .eq("qiyeID", qiyeID)
                        .eq("teachernames", paike.getTeacherNames());
                List<Pxkeshistutable> teacher = iPxkeshistutableService.selectkehao(ksstuQ);
                if (teacher.size() > 0) {
                    ajaxJson.setMsg("该学生这个时间的课时已经扣除过了！请不要重复刷卡！");
                    return ajaxJson;
                } else {
                    xiugaiInfo += "，员工编号：" + ishavekeshi.get(0).getTeacherIDs() + "，员工姓名：" + ishavekeshi.get(0).getTeacherNames() + "，在" + ishavekeshi.get(0).getAddtime() + "操作了不排课消课且不排课消课老师不是排课老师，取消了检测当前课程、班级、上课日期、上课时间已有排课，现在学生来进行刷卡消课，即学生在已经扣除课时的基础上再次进行扣课时，可到学员课时复查";
                }
            }


            String teacherIDs = "";
            String teacherNames = "";
            QueryWrapper<Pxpaiketeachertable> pkTeaQ = new QueryWrapper<>();
            pkTeaQ
                    .eq("paikeID", paike.getId())
                    .eq("qiyeID", qiyeID);
            List<Pxpaiketeachertable> paikeTeacher = iPxpaiketeachertableService.selectkehaoTeacher(pkTeaQ);
            for (Pxpaiketeachertable itemts : paikeTeacher) {
                teacherIDs += itemts.getTeacherID() + ",";
                String oneStaffName = iPxstafftableService.getById(teacherIDs).getStaffName();
                teacherNames += oneStaffName + ",";
            }

            Pxbuxikechengtable bk = iPxbuxikechengtableService.getbuxi(shuakaStuID, paike.getId(), Long.valueOf(qiyeID)).get(0);
            //region 该学生这个时间的课程未启用或者未进行排课
            if (bk == null) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("该学生这个时间的课程未启用或者未进行排课");
                return ajaxJson;
            }
            //endregion
            String isKanEndDate = iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 61L) != null ?
             iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 61L).getModifyValue() :
            iPxsysparamdefaulttableService.getById(61).getDefaultValue();//是否只能课程开始和结束日期之间消课

            if (isKanEndDate == "是") {
                if (bk.getEndDate() != null) {
                    long newT = new Date().getTime();
                    if (bk.getEndDate().getTime() < newT) {
                        ajaxJson.setCode("N");
                        ajaxJson.setMsg("上课的结束日期已经小于今天，即课程已过期");
                        return ajaxJson;
                    }
                } else {
                    ajaxJson.setCode("N");
                    ajaxJson.setMsg("上课的结束日期为空，请完善课程的结束日期后再消课");
                    return ajaxJson;
                }
            }

            Integer jfstyle = bk.getJifeiStyleID();
            Long stukechengID = bk.getKechengID();

            long timeL = (paike.getEndLessonDateTime().getTime() - paike.getStartLessonDateTime().getTime()) / (1000 * 60); //排课时间长
            long kcTime = Long.parseLong(paike.getClassTimeStyleName());

            double shichang = 0.0;
            if (jfstyle == 1 || jfstyle == 2) {
                if (kcTime != -1 && kcTime != -2) {
                    shichang = ((double) (Math.round(timeL * 100) / 100.0)) / ((double) (Math.round(kcTime * 100) / 100.0)); //把long类型的转为double类型，保留2位小数
                } else {
                    shichang = 1.0;
                }
            } else {
                shichang = 1.0;
            }

            xiugaiInfo += "，接下来【扣减学员课时和学费】";
            BigDecimal ksPrice = new BigDecimal(0);
            BigDecimal sc = new BigDecimal(shichang);

            if (jfstyle == 1 || jfstyle == 2) {
                if (bk != null) {
                    //学生的当前这个课程记录的剩余课时扣本次课以后就是负数了的话
                    if (bk.getRemainkeshi().subtract(sc).intValue() < 0) {
                        xiugaiInfo += "，学员剩余课时不够扣";
                        QueryWrapper<Pxbuxikechengtable> bx2 = new QueryWrapper<>();
                        bx2
                                .eq("kechengID", stukechengID)
                                .eq("stuID", shuakaStuID)
                                .eq("isShow", 0)
                                .ge("remainkeshi", sc)
                                .eq("qiyeID", qiyeID);
                        List<Pxbuxikechengtable> selecebk2 = iPxbuxikechengtableService.selectbuxikecheng(bx2);
                        if (selecebk2.size() > 0) {
                            Pxbuxikechengtable bk2 = selecebk2.get(0);
                            xiugaiInfo += "，学员有同课程ID的隐藏课程的剩余课时够扣";
                            stukechengID = bk2.getKechengID();

                            //新老课程切换
                            String kcQiehuan = kechengQiehuan.kechengQiehuan(bk, bk2, buxiStyleName, paike.getClassID(), staffID, qiyeID);
                            xiugaiInfo += kcQiehuan;
                            xiugaiInfo += "，消课前剩余课时" + bk2.getRemainkeshi().toString() + "，要扣减课时" + sc.toString();

                            bk2.setRemainkeshi(bk2.getRemainkeshi().subtract(sc));//扣减课时
                            iPxbuxikechengtableService.updateById(bk2);
                            xiugaiInfo += "，消课后剩余课时" + bk2.getRemainkeshi().toString();
                            if (bk2.getType() == 2) {
                                //如果该条记录是赠送课时，则不记单价，也不扣除学员学费
                                //类型：为空或1买的，2赠送的，3转送得到的，4转送出去的
                                ksPrice = l;
                                xiugaiInfo += "，课程记录是赠送的，消课单价置0，不扣减学费";

                            } else {
                                ksPrice = bk2.getKechengprice();
                                xiugaiInfo += "，消课单价" + ksPrice.toString();
                                xiugaiInfo += "，扣减学费前剩余学费" + stu.getRemainXuefei().toString();
                                stu.setRemainXuefei(stu.getRemainXuefei().subtract(sc.multiply(ksPrice)));
                                iPxstutableService.updateById(stu);
                                xiugaiInfo += "，扣减学费后剩余学费" + stu.getRemainXuefei().toString();
                            }
                        } else {


                            xiugaiInfo += "，不存在相同且够扣的补习课程记录";

                            if (!bk.getShareBuxiID().equals("0")) {
                                //有共享课时
                                String[] sharebuxi = bk.getShareBuxiID().split(",");
                                for (String items : sharebuxi) {
                                    Pxbuxikechengtable onesharebx = iPxbuxikechengtableService.getById(items);
                                    Pxstutable sharestu = iPxstutableService.getById(onesharebx.getStuID());
                                    Pxkechengtable sharekc = iPxkechengtableService.getById(onesharebx.getKechengID());
                                    if (onesharebx.getRemainkeshi().compareTo(sc) == 1) { //补习课程剩余课时>扣减课时
                                        xiugaiInfo += "。存在够扣减的共享课时,共享了:" + sharestu.getStuName() + "的" + sharekc.getKechengName() + "课程";

                                        BigDecimal shareMoney = bk.getKechengprice().multiply(sc);
                                        BigDecimal sharekeshi = shareMoney.divide(onesharebx.getKechengprice());
                                        onesharebx.setRemainkeshi(onesharebx.getRemainkeshi().subtract(sharekeshi));
                                        iPxbuxikechengtableService.updateById(onesharebx);
                                        sharekehao = sharekeshi;
                                        sharestu.setRemainXuefei(sharestu.getRemainXuefei().subtract(shareMoney));
                                        iPxstutableService.updateById(sharestu);

                                        stusharebuxiID = onesharebx.getId();
                                        break;  //跳出并停止循环

                                    }
                                }

                            } else {
                                if (remainksCanIsFushu == false) {
                                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                                    ajaxJson.setCode("N");
                                    ajaxJson.setMsg(stu.getStuName() + "的剩余课时会扣减为负数，数据字典中设置的不允许课时扣减为负数!");
                                    return ajaxJson;
                                }

                                xiugaiInfo += "，消课前剩余课时" + bk.getRemainkeshi().toString() + "，要扣减课时" + sc.toString();
                                bk.setRemainkeshi(bk.getRemainkeshi().subtract(sc));
                                iPxbuxikechengtableService.updateById(bk);
                                xiugaiInfo += "，消课后剩余课时" + bk.getRemainkeshi().toString();

                                if (bk.getType() == 2) {
                                    //如果该条记录是赠送课时，则不记单价，也不扣除学员学费
                                    //类型：为空或1买的，2赠送的，3转送得到的，4转送出去的
                                    ksPrice = l;
                                    xiugaiInfo += "，课程记录是赠送的，消课单价置0，不扣减学费";
                                } else {
                                    ksPrice = bk.getKechengprice();
                                    xiugaiInfo += "，消课单价" + ksPrice.toString();
                                    xiugaiInfo += "，扣减学费前剩余学费" + stu.getRemainXuefei().toString();
                                    stu.setRemainXuefei(stu.getRemainXuefei().subtract(sc.multiply(ksPrice)));
                                    iPxstutableService.updateById(stu);
                                    xiugaiInfo += "，扣减学费后剩余学费" + stu.getRemainXuefei().toString();
                                }
                            }
                        }
                    } else {
                        xiugaiInfo += "，学员剩余课时够扣";
                        xiugaiInfo += "，消课前剩余课时" + bk.getRemainkeshi().toString() + "，要扣减课时" + sc.toString();

                        bk.setRemainkeshi(bk.getRemainkeshi().subtract(sc));
                        iPxbuxikechengtableService.updateById(bk);
                        xiugaiInfo += "，消课后剩余课时" + bk.getRemainkeshi().toString();

                        if (bk.getType() == 2) {
                            //如果该条记录是赠送课时，则不记单价，也不扣除学员学费
                            //类型：为空或1买的，2赠送的，3转送得到的，4转送出去的
                            ksPrice = l;
                            xiugaiInfo += "，课程记录是赠送的，消课单价置0，不扣减学费";
                        } else {
                            ksPrice = bk.getKechengprice();
                            xiugaiInfo += "，消课单价" + ksPrice.toString();
                            xiugaiInfo += "，扣减学费前剩余学费" + stu.getRemainXuefei().toString();
                            stu.setRemainXuefei(stu.getRemainXuefei().subtract(sc.multiply(ksPrice)));
                            iPxstutableService.updateById(stu);
                            xiugaiInfo += "，扣减学费后剩余学费" + stu.getRemainXuefei().toString();
                        }
                    }
                }
            } else {
                xiugaiInfo += "，按起止日期计费的课程刷卡消课";

                //按起止日期计费的课程
                long days = (bk.getEndDate().getTime() - bk.getStartDate().getTime()) / (24 * 1000 * 60 * 60); ///1000毫秒*60s*60m*24h
                days += 1;
                if (days == 0) {
                    ajaxJson.setMsg("学员课程的结束日期要大于开始日期，请去学员课程检查");
                    return ajaxJson;
                }
                //计算一天价钱
                BigDecimal day = new BigDecimal(days);
                BigDecimal onedayMoney = bk.getZongjia().divide(day);

                xiugaiInfo += "，一天的学费" + onedayMoney.toString();
                xiugaiInfo += "，消课前学员剩余学费" + stu.getRemainXuefei().toString();
                stu.setRemainXuefei(stu.getRemainXuefei().subtract(onedayMoney));
                iPxstutableService.updateById(stu);
                xiugaiInfo += "，消课后学员剩余学费" + stu.getRemainXuefei().toString();
            }

            xiugaiInfo += "，接下来【添加学员课时记录】";
            //向学生课时表里添加课时消耗记录
            Pxkeshistutable ksStu = new Pxkeshistutable();
            ksStu.setAddtime(new Date());
            ksStu.setQiyeID(qiyeID);
            ksStu.setAdduser(staffID);
            ksStu.setStuID(shuakaStuID);
            ksStu.setClassID(paike.getClassID());
            ksStu.setCampusID(iPxclasstableService.getById(paike.getClassID()).getCampusID());
            ksStu.setKechengID(stukechengID);
            ksStu.setStuGradeID(stu.getStuGradeID());
            ksStu.setHaveClassDate(paike.getHaveClassDate());
            ksStu.setWeekN(paike.getWeekN());
            ksStu.setStartLessonDateTime(paike.getStartLessonDateTime());
            ksStu.setEndLessonDateTime(paike.getEndLessonDateTime());
            ksStu.setBuxiStyleID(paike.getBuxiStyleID());
            ksStu.setClassTimeStyleID(paike.getClassTimeStyleID());

            xiugaiInfo += "，学员课时记录，课程ID" + stukechengID + "，班级ID" + paike.getClassID() + "，上课日期时间" + paike.getHaveClassDate().toString() + " " + paike.getStartLessonDateTime().toString().substring(0, 5) + "-" + paike.getEndLessonDateTime().toString().substring(0, 5);

            BigDecimal y = new BigDecimal(1);
            if (jfstyle == 1 || jfstyle == 2) {


                if (stusharebuxiID != null && stusharebuxiID != 0) {
                    //有进行共享消课
                    ksStu.setShareBuxiID(stusharebuxiID);
                    Pxbuxikechengtable oneshare = iPxbuxikechengtableService.getById(stusharebuxiID);
                    ksStu.setKechengPrice(oneshare.getKechengprice());
                    ksStu.setKeshiNum(sharekehao);//课时
                    xiugaiInfo += "，按课时或课时包计费的课程,单价" + oneshare.getKechengprice().toString() + "，课时" + sharekehao.toString();
                } else {
                    ksStu.setShareBuxiID(0L);
                    ksStu.setKechengPrice(ksPrice);
                    ksStu.setKeshiNum(sc);
                    xiugaiInfo += "，按课时或课时包计费的课程,单价" + ksPrice.toString() + "，课时" + sc.toString();
                }
            } else {
                //判断老数据与新数据差别消课
                if (bk.getKeshiNum().intValue() != 0 && bk.getRemainkeshi().intValue() != 0) {

                    ksStu.setKechengPrice(bk.getKechengprice());
                    ksStu.setKeshiNum(y);
                    bk.setRemainkeshi(bk.getRemainkeshi().subtract(y));
                    iPxbuxikechengtableService.updateById(bk);

                    xiugaiInfo += "，按起止日期计费课程，一天的学费" + bk.getKechengprice().toString();

                    xiugaiInfo += "，消课前学员剩余学费" + stu.getRemainXuefei().toString();
                    stu.setRemainXuefei(stu.getRemainXuefei().subtract(bk.getKechengprice()));
                    xiugaiInfo += "，消课后学员剩余学费" + stu.getRemainXuefei().toString();
                } else {
                    long daytime = (bk.getEndDate().getTime() - bk.getStartDate().getTime()) / (24 * 1000 * 60 * 60); ///1000毫秒*60s*60m*24h
                    BigDecimal dayy = new BigDecimal(daytime);
                    //计算一天价钱
                    BigDecimal onedayMoney = bk.getZongjia().divide(dayy);

                    xiugaiInfo += "，按起止日期计费的课程,一天的价格（消课单价）" + onedayMoney.toString();

                    ksStu.setKechengPrice(onedayMoney);
                    ksStu.setKeshiNum(y);
                }
            }

            ksStu.setStuKaoqingStyle("1");
            ksStu.setShuoMing("签到/签退消课");
            ksStu.setBuxiStyleID(paike.getBuxiID());
            ksStu.setTeacherIDs(teacherIDs);
            ksStu.setTeacherNames(teacherNames);
            ksStu.setDakaoqingStyle(4);
            xiugaiInfo += "，上课老师IDs:" + teacherIDs;
            xiugaiInfo += "，上课老师姓名:" + teacherNames;
            iPxkeshistutableService.save(ksStu);

            xiugaiInfo += "，学员课时记录添加【完毕】,接下来【添加keshiStuTeacherTable】";

            QueryWrapper<Pxpaiketeachertable> pkteaT = new QueryWrapper<>();
            pkteaT
                    .eq("paikeID", paike.getId())
                    .eq("qiyeID", qiyeID);
            List<Pxpaiketeachertable> paikeTeachers = iPxpaiketeachertableService.selectkehaoTeacher(pkteaT);

            if (paikeTeachers.size() > 0) {
                for (Pxpaiketeachertable itemt : paikeTeachers) {
                    xiugaiInfo += "，开始添加keshiStuTeacherTable老师ID" + itemt.getTeacherID().toString();
                    Pxkeshistuteachertable stuksTea = new Pxkeshistuteachertable();
                    stuksTea.setKeshistutableid(ksStu.getId());
                    stuksTea.setTeacherid(itemt.getTeacherID());
                    stuksTea.setQiyeID(qiyeID);
                    iPxkeshistuteachertableService.save(stuksTea);
                    xiugaiInfo += "，已经添加keshiStuTeacherTable老师ID" + itemt.getTeacherID().toString();
                }
            }

            xiugaiInfo += "，添加keshiStuTeacherTable【完毕】";

            //-------------------------------------------------
            xiugaiInfo += "，接下来【添加Pxqiandaoqiantuitable】";
            //签退（之前的刷卡消课表）

            Pxqiandaoqiantuitable qt = new Pxqiandaoqiantuitable();
            qt.setStuid(shuakaStuID);
            qt.setTsstate(false);
            qt.setQiyeID(qiyeID);
            qt.setAddstaffid(staffID);
            qt.setQiandaoorqiantui(2);
            qt.setQianstyle(1);//刷卡签退
            qt.setPaikeid(paike.getId());
            qt.setQiandatetime(paike.getHaveClassDate());
            iPxqiandaoqiantuitableService.save(qt);

            //-------------------------------------------------

            //从数据字典里读出积分比例值
            //如果比例值==0，什么事情都不做；
            //否则，往积分表里插入一条记录，即给学生送积分；积分数= 学生课时数*积分比例值
            Pxstutable student = iPxstutableService.getById(shuakaStuID);//学生表
            BigDecimal oldJifen = student.getJifenNum();//原本积分
            String jfen = iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 72L) == null ?
             iPxsysparamdefaulttableService.getById(72).getDefaultValue() :
                    iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 72L).getModifyValue();

            Pxstutable msgstu = iPxstutableService.getById(shuakaStuID);
            String msgStuName = msgstu.getStuName();
            String stuCampusName = iPxcampustableService.getById(msgstu.getCampusID()).getCampusName();
            String kechengName = iPxkechengtableService.getById(stukechengID).getKechengName();
            //模板消息ID
            String templateId = "";

            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("stuId", msgstu.getId());
            List<WscUserBind> wscUserBinds = iWscUserBindService.list(queryWrapper);

            if (Integer.valueOf(jfen) > 0) {
                BigDecimal jf = new BigDecimal(jfen);
                BigDecimal ksN = sc;
                student.setJifenNum(jf.multiply(ksN));//改变学生表积分
                iPxstutableService.updateById(student);

                //添加积分变动记录
                Pxjifentable JFT = new Pxjifentable();
                JFT.setStuID(shuakaStuID);
                JFT.setType(1);
                JFT.setOldintegral(oldJifen);
                JFT.setIntegral(jf.multiply(ksN));
                JFT.setStaffID(staffID);
                JFT.setCreatetime(new Date());
                JFT.setRemark("签到/签退扣除课时，产生积分");
                JFT.setQiyeID(qiyeID);
                iPxjifentableService.save(JFT);

                //region 向家长推送积分变动提醒
                Pxtuisongtable pxtuisongtable = new Pxtuisongtable().
                        setAddStaffID(loginUser.getStaffID())
                        .setAddTime(new Date())
                        .setAppread(0)
                        .setQiyeID(loginUser.getQiyeID())
                        .setStuID(msgstu.getId())
                        .setTuisongTypeName(4L)
                        .setRole(1)
                        .setNote("按排课消课扣减课时，自动获得：" + jf.multiply(ksN) + "个积分！")
                        .setWxstate(0);
                iPxtuisongtableService.save(pxtuisongtable);
                sendMessage.sendMessage(pxtuisongtable.getId());
                //endregion
            }


            xiugaiInfo += "，接下来【添加教师课时记录】";

            Long teacherKSID = 0L;
            //向老师课时表里添加上课记录
            for (Pxpaiketeachertable itempkt : paikeTeachers) {
                xiugaiInfo += "，开始添加教师课时记录教师ID" + itempkt.getTeacherID().toString();
                Long teacherID = itempkt.getTeacherID();

                QueryWrapper<Pxkeshiteachertable> ksT = new QueryWrapper<>();
                ksT
                        .eq("classID", paike.getClassID())
                        .eq("haveClassDate", paike.getHaveClassDate())
                        .eq("startLessonDateTime", paike.getStartLessonDateTime())
                        .eq("endLessonDateTime", paike.getEndLessonDateTime())
                        .eq("qiyeID", qiyeID);
                Pxkeshiteachertable keshiTeacher = iPxkeshiteachertableService.selectTeakehao(ksT).get(0);
                if (keshiTeacher == null) {
                    xiugaiInfo += "，老师该课程课时段的课时记录还没有";
                    Pxkeshiteachertable addkeshiTeacher = new Pxkeshiteachertable();
                    addkeshiTeacher.setTeacherID(teacherID);
                    addkeshiTeacher.setClassID(paike.getClassID());
                    addkeshiTeacher.setBuxiStyleID(paike.getBuxiStyleID());
                    xiugaiInfo += "，已添加教师课时记录，课程ID" + paike.getKechengID() + "，班级ID" + paike.getClassID() + "上课日期时间" + paike.getHaveClassDate().toString() +
                    " " + paike.getStartLessonDateTime().toString().substring(0, 5) + "-" + paike.getEndLessonDateTime().toString().substring(0, 5) + ",课时" + sc.toString();
                    if (paike.getBuxiStyleID() == 1) {
                        addkeshiTeacher.setStuID(shuakaStuID);
                        xiugaiInfo += "，一对一课程，stuID" + shuakaStuID;
                    } else {
                        addkeshiTeacher.setStuID(0L);
                        xiugaiInfo += "，班课";
                    }
                    addkeshiTeacher.setKechengID(paike.getKechengID());
                    addkeshiTeacher.setClassTimeStyleID(paike.getClassTimeStyleID());
                    addkeshiTeacher.setHaveClassDate(paike.getHaveClassDate());
                    addkeshiTeacher.setWeekN(paike.getWeekN());
                    addkeshiTeacher.setStartLessonDateTime(paike.getStartLessonDateTime());
                    addkeshiTeacher.setEndLessonDateTime(paike.getEndLessonDateTime());
                    addkeshiTeacher.setKeshiNum(sc);
                    addkeshiTeacher.setYsStuNum(stuCount);
                    addkeshiTeacher.setSsStuNum(1);
                    addkeshiTeacher.setCampusID(iPxclasstableService.getById(paike.getClassID()).getCampusID());
                    iPxkeshiteachertableService.save(addkeshiTeacher);

                    teacherKSID = addkeshiTeacher.getId();
                    xiugaiInfo += "，已添加教师课时记录" + itempkt.getTeacherID();
                } else {
                    teacherKSID = keshiTeacher.getId();
                    keshiTeacher.setSsStuNum(keshiTeacher.getSsStuNum() + 1);
                    iPxkeshiteachertableService.updateById(keshiTeacher);
                    xiugaiInfo += "，签到/签退消课，实上人数加1后为" + keshiTeacher.getSsStuNum().toString();
                    xiugaiInfo += "，已添加教师课时记录" + itempkt.getTeacherID();
                }

            }
            xiugaiInfo += "，添加教师课时记录【完毕】";

            xiugaiInfo += "，接下来【添加学员考勤】";
            //添加学员考勤

            QueryWrapper<Pxstukaoqingtable> stukqT = new QueryWrapper<>();
            stukqT
                    .eq("stuID", shuakaStuID)
                    .eq("kechengID", stukechengID)
                    .eq("haveclassDate", paike.getHaveClassDate())
                    .eq("startClassDateTime", paike.getStartLessonDateTime())
                    .eq("endClassDateTime", paike.getEndLessonDateTime())
                    .eq("qiyeID", qiyeID);
            List<Pxstukaoqingtable> stukq = iPxstukaoqingtableService.selectstukaoqing(stukqT);
            if (stukq.size() == 0) {
                xiugaiInfo += "，开始添加学员考勤";
                Pxstukaoqingtable onek = new Pxstukaoqingtable();
                onek.setEndclassdatetime(paike.getEndLessonDateTime());
                onek.setHaveclassdate(paike.getHaveClassDate());
                onek.setStartclassdatetime(paike.getStartLessonDateTime());
                onek.setKaoqingbeizhu("正常");
                onek.setKaoqingstyle(1);
                onek.setKechengid(stukechengID);
                onek.setClassid(paike.getClassID());
                onek.setStuid(shuakaStuID);
                onek.setTeacherids(teacherIDs);
                onek.setTeachernames(teacherNames);
                onek.setQiyeid(qiyeID);
                iPxstukaoqingtableService.save(onek);
                xiugaiInfo += "，添加学员考勤【完毕】，接下来【添加stukaoqingTeacherTable】";
                for (Pxpaiketeachertable items : paikeTeachers) {
                    xiugaiInfo += "，开始添加stukaoqingTeacherTable教师ID" + items.getTeacherID();
                    Pxstukaoqingteachertable stukqt2 = new Pxstukaoqingteachertable();
                    stukqt2.setQiyeID(qiyeID);
                    stukqt2.setStukaoqingtabid(onek.getId());
                    stukqt2.setTeacherid(items.getTeacherID());
                    iPxstukaoqingteachertableService.save(stukqt2);
                    xiugaiInfo += "，已添加stukaoqingTeacherTable教师ID" + items.getTeacherID();
                }

                xiugaiInfo += "，添加stukaoqingTeacherTable【完毕】";
            }

            //家长推送孩子的剩余课时
            // 如果剩余课时低于预警值，给家长发送续费提醒;
            BigDecimal stuKechengRemainKeshi = null;
            Integer yujinzhi = null;
            List<SumbxRemainVo> selectsumRks = iPxbuxikechengtableService.getSumRks(shuakaStuID, stukechengID, Long.valueOf(qiyeID));
            if (selectsumRks.size() > 0) {
                stuKechengRemainKeshi = selectsumRks.get(0).getSumR();
            }
            Pxsysparamvaluetable selectsysvalue = iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 11L);
            if (selectsysvalue != null) {
                yujinzhi = Integer.valueOf(selectsysvalue.getModifyValue());
            } else {
                yujinzhi = Integer.valueOf(iPxsysparamdefaulttableService.getById(11).getDefaultValue());
            }

            Pxstutable msgStu = iPxstutableService.getById(shuakaStuID);
            String noticeType = "";
            String noticeContent = "";
            int type = -1;

            Pxsysparamvaluetable getvalue = iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 61L);
            Pxsysparamvaluetable getqinglin = iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 62L);
            boolean isshowEnddate = getvalue != null ? getvalue.getModifyValue() == "是" ? true : false :
             iPxsysparamdefaulttableService.getById(61).getDefaultValue() == "是" ? true : false;//是否在（补习）课程起止日期之前消课
            boolean isshowEnddate1 = getvalue != null ? getqinglin.getModifyValue() == "是" ? true : false :
             iPxsysparamdefaulttableService.getById(61).getDefaultValue() == "是" ? true : false;//课时自动清零

            if (stuKechengRemainKeshi.intValue() > yujinzhi) {
                noticeType = "温馨提示";
                if (jfstyle == 3) {
                    noticeContent =
msgStuName + "的课程《" + kechengName + "》" + paike.getHaveClassDate().toString() + " " + paike.getStartLessonDateTime().toString().substring(0, 5) + "-" + paike.getEndLessonDateTime().toString().substring(0, 5) + "已消课";
                } else {
                    noticeContent =
                     msgStuName + "的课程《" + kechengName + "》" + paike.getHaveClassDate().toString() + " " + paike.getStartLessonDateTime().toString().substring(0, 5) + "-" + paike.getEndLessonDateTime().toString().substring(0, 5) + "上课消课" + sc.toString() + "课时，剩余" + stuKechengRemainKeshi.toString() + "课时。";
                }
                type = 29;
            } else {
                noticeType = "续费提醒";
                noticeContent =
 msgStuName + "的课程《" + kechengName + "》" + paike.getHaveClassDate().toString() + " " + paike.getStartLessonDateTime().toString().substring(0,
                  5) + "-" + paike.getEndLessonDateTime().toString().substring(0, 5) + "上课消课" + sc.toString() + "课时，剩余" + stuKechengRemainKeshi.toString() +
                  "课时，请及时到我校续费。";
                type = 1;
            }

            if (isshowEnddate == true || isshowEnddate1 == true) {
                noticeContent += "课程到期时间：" + bk.getEndDate().toString();
            }
            String noticeRemark = stuCampusName;

            //region 推送给家长
            Pxtuisongtable pxtuisongtable1 = new Pxtuisongtable()
                    .setAddStaffID(loginUser.getStaffID())
                    .setAddTime(new Date())
                    .setAppread(0)
                    .setQiyeID(loginUser.getQiyeID())
                    .setStuID(msgstu.getId())
                    .setTuisongTypeName(5L)
                    .setRole(1)
                    .setNote(noticeContent)
                    .setWxstate(0)
                    .setHaveclassDate(paike.getHaveClassDate())
                    .setStartLessonDateTime(paike.getStartLessonDateTime())
                    .setEndLessonDateTime(paike.getEndLessonDateTime())
                    .setQiyeID(loginUser.getQiyeID());
            iPxtuisongtableService.save(pxtuisongtable1);
            sendMessage.sendMessage(pxtuisongtable1.getId());


//            templateId = "66c5liowLy0kxCkLXjpVsmp8JpFVEHs_SAZcakX5AgQ";
//            //点击消息跳转小程序链接
//            String url = "";
//            //模板消息内容信息，与模板消息使用一致
//            List<TemplateParam> paras = new ArrayList<>();
//            paras.add(new TemplateParam("first", msgStuName + "同学，你有一个消课通知", "#FF3333"));
//            paras.add(new TemplateParam("keyword1", kechengName, "#0044BB"));
//            paras.add(new TemplateParam("keyword2", stuKechengRemainKeshi + "", "#0044BB"));
//            paras.add(new TemplateParam("keyword3", DateUtil.formatDate2(paike.getHaveClassDate()) + " " + paike.getStartLessonDateTime().toString()
//            .substring(0, 5) + "-" + paike.getEndLessonDateTime().toString().substring(0, 5), "#0044BB"));
//            paras.add(new TemplateParam("keyword4", noticeContent, "#0044BB"));
//            paras.add(new TemplateParam("remark", stuCampusName, "#0044BB"));
//            for (WscUserBind binduser : wscUserBinds) {
//                WscUser wscUser = iWscUserService.getById(binduser.getWscuserid());
//                if (wscUser != null) {
//                    if (wscUser.getUnionid() != "" && wscUser.getUnionid() != null) {
//                        QueryWrapper quer = new QueryWrapper<>();
//                        quer.eq("unionid", wscUser.getUnionid());
//                        GzhAlluser gzhAlluser = iGzhAlluserService.getOne(quer);
//                        WXTmplMsgUtils.sendWXTmplMsg(templateId, gzhAlluser.getOpenid(), url, paras);
//                    }
//                }
//            }

            //endregion


            //如果打了考勤的人数已经大于或等于班级人数，则改变这次排课的考勤状态
            QueryWrapper<Pxstukaoqingtable> stukqQ = new QueryWrapper<>();
            stukqQ
                    .eq("haveclassDate", paike.getHaveClassDate())
                    .eq("startClassDateTime", paike.getStartLessonDateTime())
                    .eq("endClassDateTime", paike.getEndLessonDateTime())
                    .eq("classID", paike.getClassID())
                    .eq("teacherIDs", teacherIDs)
                    .eq("qiyeID", qiyeID);
            List<Pxstukaoqingtable> StuKaoqingCount = iPxstukaoqingtableService.selectstukaoqing(stukqQ);
            xiugaiInfo += "，班级总人数" + stuCount + ",已完成考勤人数" + StuKaoqingCount.size();

            if (StuKaoqingCount.size() >= stuCount) {
                Pxpaiketable paikekq = iPxpaiketableService.getById(paike.getId());
                if (paike != null) {
                    paikekq.setDakaoqin(true);
                    iPxpaiketableService.updateById(paikekq);
                    xiugaiInfo += "，该排课设置为已完成考勤，排课ID" + paikekq.getId();
                }
            }
            ajaxJson.setMsg("课时扣除成功");
        }

        return ajaxJson;
    }

    //endregion

    //region 余额消课


    /**
     * @Description: getyuexiaokePage()方法作用:分页获取余额消课
     * @param:[current, size, campusID, stuName]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/15 15:14
     */
    @ResponseBody
    @RequestMapping(value = "getyuexiaokePage", method = RequestMethod.GET)
    @ApiOperation("分页获取余额消课")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页获取的数据量", example = "10", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "stuName", value = "学员姓名", required = false)
    })
    public AjaxJson getyuexiaokePage(Long current, Long size, String campusID, String stuName, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<yuexiqokeVo> page = new Page<>(current, size);
        QueryWrapper<yuexiqokeVo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("1", 1)
                .eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("b.campusID", campusID);
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("b.stuName", stuName);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 250);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("b.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("b.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("b.campusID", lookPower);
        }
        ajaxJson.setObj(iPxchongzhipaytableService.getyuexiaokePage(page, queryWrapper));
        return ajaxJson;
    }

    @RequestMapping(value = "ExportyuexiaokePage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出余额消课")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "startDate", value = "开始时间", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束时间", required = false),
    })
    public void ExportyuexiaokePage(HttpServletResponse response, HttpServletRequest request,
                                    @RequestParam(required = false) String campusID, // 校区ID
                                    @RequestParam(required = false) String startDate, // 开始日期
                                    @RequestParam(required = false) String endDate // 结束日期
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper<yuexiqokeVo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("1", 1)
                .eq("a.qiyeID", qiyeID);

        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("b.campusID", campusID);
        }
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("a.addTime", startDate);
        }
        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("a.addTime", endDate);
        }
        List<yuexiqokeVo> yuexiqokeVos = iPxchongzhipaytableService.ExportyuexiaokePage(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "学号", "姓名", "金额", "备注", "操作教师", "操作时间"},
                yuexiqokeVos,
                new String[]{"campusName", "stuID", "stuName", "chongzhiPayMoney", "beizhu", "addUser", "addTime-D"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "余额消课导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @Description: getnewkcInfoList()方法作用:获取科目课程
     * @param:[]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/15 17:23
     */
    //获取的结果：启用的课程，开放的校区
    @ResponseBody
    @RequestMapping(value = "getnewkcInfoList", method = RequestMethod.GET)
    @ApiOperation("获取科目课程")
    public AjaxJson getnewkcInfoList(HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxkechengtableService.getnewkcInfoList(qiyeID));
        return ajaxJson;
    }

    @ResponseBody
    @RequestMapping(value = "/GetAllKemuList", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有科目信息")
    public AjaxJson GetAllKemuList(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        ajaxJson.setObj(iPxsubjecttableService.GetAllKemuList(qiyeID));
        return ajaxJson;
    }


    /**
     * @Description: addyuexiaoke()方法作用:添加余额消课
     * @param:[cardNumber, kechengID, kechengName, money]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/15 17:23
     */
    @ResponseBody
    @RequestMapping(value = "addyuexiaoke", method = RequestMethod.POST)
    @ApiOperation("添加余额消课")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cardNumber", value = "学员卡号", required = true),
            @ApiImplicitParam(name = "kechengID", value = "课程ID", required = true),
            @ApiImplicitParam(name = "kechengName", value = "课程名称", required = true),
            @ApiImplicitParam(name = "money", value = "要扣减的金额", required = true),
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addyuexiaoke(String cardNumber, Long kechengID, String kechengName, BigDecimal money, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();
        String campusName = iPxcampustableService.getById(campusID).getCampusName();
        if (StringUtils.isEmpty(cardNumber)) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("请输入学生卡号或请刷卡");
            return ajaxJson;
        }

        QueryWrapper<Pxstucardtable> cardQ = new QueryWrapper<>();
        cardQ
                .eq("cardNumber", cardNumber)
                .eq("qiyeID", qiyeID);
        Pxstucardtable stucard = null;
        List<Pxstucardtable> selectstucard = iPxstucardtableService.selectstucard(cardQ);
        if (selectstucard.size() == 0) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("该学员卡还未分配给任何学员！请前往【学员管理——学员卡管理】进行分配学员卡");
            return ajaxJson;
        } else {
            stucard = selectstucard.get(0);
        }

        Pxstutable stu = iPxstutableService.getById(stucard.getStuID());
        Pxkechengtable kc = iPxkechengtableService.getById(kechengID);

        QueryWrapper<Pxchongzhipaytable> czPayq = new QueryWrapper<>();
        czPayq
                .eq("stuID", stu.getId())
                .eq("qiyeID", qiyeID);
        Pxchongzhipaytable yueTab = iPxchongzhipaytableService.selectChongzhiPay(czPayq).get(0);
        if (stu.getBuxiStateID() != 2) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("学员不是在读状态");
            return ajaxJson;
        }
        if (yueTab == null || yueTab.getChongzhiPayMoney().intValue() < money.intValue()) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("该学员余额不足");
            return ajaxJson;
        }

        String format = sdf.format(new Date());
        Date skDT = null;
        try {
            skDT = sdf.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //添加一条课耗记录
        Pxkeshistutable kh = new Pxkeshistutable();
        kh.setStuID(stu.getId());
        kh.setClassID(0L);//****
        kh.setCampusID(stu.getCampusID());
        kh.setKechengID(kechengID);
        kh.setStuGradeID(stu.getStuGradeID());
        kh.setTeacherIDs("0");//****
        kh.setTeacherNames("0");//****
        kh.setHaveClassDate(new Date());
        kh.setWeekN(DateUtil.getWeekOfDate(new Date()));
        kh.setStartLessonDateTime(Time.valueOf(format));
        kh.setEndLessonDateTime(Time.valueOf(format));
        kh.setKeshiNum(money.divide(kc.getKechengprice()));//计算的上课课时
        kh.setClassTimeStyleID(kc.getClassTimeStyleID());//课程时长
        kh.setKechengPrice(kc.getKechengprice());
        kh.setStuKaoqingStyle("1");//考勤正常 考勤类别：1正常、2请假、3旷课、4迟到、5早退
        kh.setDakaoqingStyle(7);//打考勤方法：0未知，1电脑消课不排课消课，2电脑按排课消课，3微信消课，4刷卡消课，5自动消课，6刷脸消课，7余额消课
        kh.setBuxiStyleID(0L);
        kh.setShuoMing("余额消课,由于没报课，没有固定班级、补习课程、任课老师、计费方式，刷卡从充值的余额里扣减费用");
        kh.setAdduser(staffID);
        kh.setAddtime(new Date());
        kh.setBuxikechengID(0L);//****
        kh.setQiyeID(qiyeID);
        iPxkeshistutableService.save(kh);

        //String text = stu.getStuName() + "在" + new Date() + "余额扣费：" + money.toString() + "。扣费课程ID：" + kc.getId() + " ,课程名称:" + kechengName + "。";

        //接下来添加充值支付记录
        Pxchongzhipaytable one = new Pxchongzhipaytable();
        one.setQiyeID(qiyeID);
        one.setAddStaffID(staffID);
        one.setAddTime(new Date());
        one.setBeizhu("使用余额消课，添加充值支付记录");
        one.setChongzhiPayMoney(money);
        one.setStuID(stu.getId());
        one.setType(3);//消课支出
        one.setKechengID(kechengID);
        one.setXiaokeID(kh.getId());
        one.setKechengName(kechengName);
        iPxchongzhipaytableService.save(one);

        //扣减充值余额
        stu.setRemainChongzhi(stu.getRemainChongzhi().subtract(money));
        iPxstutableService.updateById(stu);

        //region 发送消课通知
//region 推送给家长
        String noticeContent = "余额消课,由于没报课，没有固定班级、补习课程、任课老师、计费方式，刷卡从充值的余额里扣减费用";
        Pxtuisongtable pxtuisongtable1 = new Pxtuisongtable()
                .setAddStaffID(loginUser.getStaffID())
                .setAddTime(new Date())
                .setAppread(0)
                .setQiyeID(loginUser.getQiyeID())
                .setStuID(stu.getId())
                .setTuisongTypeName(5L)
                .setRole(1)
                .setNote(noticeContent)
                .setHaveclassDate(new Date())
                .setStartLessonDateTime(Time.valueOf(format))
                .setEndLessonDateTime(Time.valueOf(format))
                .setWxstate(1);
        iPxtuisongtableService.save(pxtuisongtable1);
        sendMessage.sendMessage(pxtuisongtable1.getId());

//        String templateId = "66c5liowLy0kxCkLXjpVsmp8JpFVEHs_SAZcakX5AgQ";
//        //点击消息跳转小程序链接
//        String url = "";
//
//        //模板消息内容信息，与模板消息使用一致
//        List<TemplateParam> paras = new ArrayList<>();
//        paras.add(new TemplateParam("first", stu.getStuName() + "同学，你有一个消课通知", "#FF3333"));
//        paras.add(new TemplateParam("keyword1", kechengName, "#0044BB"));
//        paras.add(new TemplateParam("keyword2", stu.getRemainChongzhi() + "", "#0044BB"));
//        paras.add(new TemplateParam("keyword3", DateUtil.formatDate2(new Date()) + " " + Time.valueOf(format).toString(), "#0044BB"));
//        paras.add(new TemplateParam("keyword4", noticeContent, "#0044BB"));
//        paras.add(new TemplateParam("remark", campusName, "#0044BB"));
//
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("stuId", stu.getId());
//        List<WscUserBind> wscUserBinds = iWscUserBindService.list(queryWrapper);
//        for (WscUserBind binduser : wscUserBinds) {
//            WscUser wscUser = iWscUserService.getById(binduser.getWscuserid());
//            if (wscUser != null) {
//                if (wscUser.getUnionid() != "" && wscUser.getUnionid() != null) {
//                    QueryWrapper quer = new QueryWrapper<>();
//                    quer.eq("unionid", wscUser.getUnionid());
//                    GzhAlluser gzhAlluser = iGzhAlluserService.getOne(quer);
//                    WXTmplMsgUtils.sendWXTmplMsg(templateId, gzhAlluser.getOpenid(), url, paras);
//                }
//            }
//        }

        //endregion
        //endregion

        ajaxJson.setMsg("操作成功");

        return ajaxJson;
    }


    //endregion

    //region 自动消课


    /**
     * @Description: getAutoPage()方法作用:自动消课
     * @param:[current, size, campusID, renkeTea, banzhuren, stuName]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/14 18:18
     */
    @ApiOperation("分页获取自动消课")
    @ResponseBody
    @RequestMapping(value = "getAutoPage", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页获取数据", example = "10", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "renkeTea", value = "任课老师", required = false),
            @ApiImplicitParam(name = "banzhuren", value = "班主任", required = false),
            @ApiImplicitParam(name = "stuName", value = "学员姓名", required = false),
            @ApiImplicitParam(name = "state", value = "设置自动消课启用 1：关闭  2：开启 -1:全部", example = "-1", required = false),
    })
    public AjaxJson getAutoPage(HttpServletRequest request,
                                Long current,
                                Long size,
                                String campusID,
                                String renkeTea,
                                String banzhuren,
                                String stuName,
                                int state) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper<AutoxiaokeVO> queryWrapper = new QueryWrapper<>();
        Page<AutoxiaokeVO> page = new Page(current, size);
        queryWrapper
                .eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("b.campusID", campusID);
        }
        if (StringUtils.isNotBlank(renkeTea)) {
            queryWrapper.like("f.teaNames", renkeTea);
        }
        if (StringUtils.isNotBlank(banzhuren)) {
            queryWrapper.like("g.staffName", banzhuren);
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("b.stuName", stuName);
        }
        if (state != -1) {
            if (state == 1 || state == 2) {
                queryWrapper.eq("f.state", 1).or(a -> a.eq("f.state", 2));
            } else if (state == 0) {
                queryWrapper.isNull("f.state");
            }
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 247);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("b.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("b.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("b.campusID", lookPower);
        }
        ajaxJson.setObj(iPxbuxikechengtableService.getAutoPage(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * @Description: getallqxclass()方法作用:校区ID检索班级，权限控制
     * @param:[request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/1/12 15:17
     */
    @RequestMapping(value = "getallqxclass", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "校区ID检索班级，权限控制")
    public AjaxJson getallqxclass(HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        //Long staffID = Long.valueOf(loginUser.getStaffID());
        //String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxclasstableService.getclassbycam(campusID, qiyeID));
        return ajaxJson;
    }

    /**
     * @Description: getstaffByCam()方法作用:获取校区员工
     * @param:[request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/1/12 15:35
     */
    @RequestMapping(value = "getstaffByCam", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取校区员工")
    public AjaxJson getstaffByCam(HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long campusID = loginUser.getCampusID();
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxstafftableService.getstaffBycam(campusID, qiyeID));
        return ajaxJson;
    }

    /**
     * @Description: ExportAutoPage()方法作用:导出自动消课
     * @param:[response, campusID]
     * @return:void
     * @auter:yyl
     * @data:2020/12/14 18:32
     */
    @RequestMapping(value = "ExportAutoPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出自动消课")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
    })
    public void ExportAutoPage(HttpServletResponse response, HttpServletRequest request,
                               @RequestParam(required = false) String campusID // 校区ID
//                               @RequestParam(required = false) String startDate, // 开始日期
//                               @RequestParam(required = false) String endDate // 结束日期
    ) {
        QueryWrapper<AutoxiaokeVO> queryWrapper = new QueryWrapper<>();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        queryWrapper
                .eq("1", 1)
                .eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("b.campusID", campusID);
        }
//        if (StringUtils.isNotBlank(startDate)) {
//            queryWrapper.ge("a.qianDatetime", startDate);
//        }
//        if (StringUtils.isNotBlank(endDate)) {
//            queryWrapper.le("a.qianDatetime", endDate);
//        }
        List<AutoxiaokeVO> autoxiaokeVOS = iPxbuxikechengtableService.ExportAutoPage(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "学员姓名", "班主任", "科目", "课程名称", "班级", "任课老师", "消课计入老师课时数", "课程开始时间", "课程结束时间",
         "自动消课状态（已设置/未设置）"},
                autoxiaokeVOS,
                new String[]{"campusName", "stuName", "banzhuren", "subjectName", "kechengName", "className", "teaNames", "keshiNum", "startDate-D", "endDate" +
                        "-D", "state"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "自动消课导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @Description: savezdxk()方法作用:设置自动消课
     * @param:[buxiID, classID, teachID, teachKeshi, zdyesorno]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/15 10:20
     */
    @ResponseBody
    @RequestMapping(value = "savezdxk", method = RequestMethod.POST)
    @ApiOperation("设置自动消课")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "buxiID", value = "补习ID", required = true),
            @ApiImplicitParam(name = "classID", value = "班级ID", required = true),
            @ApiImplicitParam(name = "teachID", value = "教师ID", required = false),
            @ApiImplicitParam(name = "teachKeshi", value = "计入教师课时数", required = false),
            @ApiImplicitParam(name = "zdyesorno", value = "是否立即启用 1：启用  2：关闭", required = true),
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson savezdxk(@RequestBody savezdxkForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        BigDecimal l = new BigDecimal(0);

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();

        String teacherIDs = "";
        String teacherNames = "";

        if (StringUtils.isNotEmpty(form.getTeachID())) {
            //不为空
            String[] showjiaoshi =form.getTeachID().split(",");
            for (String item : showjiaoshi) {
                Pxstafftable tea = iPxstafftableService.getById(item);
                teacherIDs += item + ',';
                teacherNames += tea.getStaffName() + ',';
            }
        }

        QueryWrapper<Pxautoxiaoketable> autoQ = new QueryWrapper<>();
        autoQ
                .eq("buxiID",form.getBuxiID())
                .eq("qiyeID", qiyeID);
        List<Pxautoxiaoketable> pdTabsel = iPxautoxiaoketableService.selectAutoxk(autoQ);
        if (pdTabsel.size() == 0) {

            //没有--添加
            Pxautoxiaoketable one = new Pxautoxiaoketable();
            one.setBuxiid(form.getBuxiID());
            one.setClassid(form.getClassID());
            one.setTeaids(teacherIDs);
            one.setTeanames(teacherNames);
            one.setQiyeid(qiyeID);
            if (form.getTeachKeshi() == 0) {
                one.setKeshinum(l);
            } else {
                BigDecimal Tkeshi = new BigDecimal(form.getTeachKeshi());
                one.setKeshinum(Tkeshi);
            }

            if (form.getZdyesorno()== 1) {
                one.setState(2);
            } else {
                one.setState(1);
            }
            iPxautoxiaoketableService.save(one);

        } else {
            Pxautoxiaoketable pdTab = pdTabsel.get(0);
            //有--修改
            pdTab.setClassid(form.getClassID());
            pdTab.setTeaids(teacherIDs);
            pdTab.setTeanames(teacherNames);
            if (form.getTeachKeshi() == 0) {
                pdTab.setKeshinum(l);
            } else {
                BigDecimal Tkeshi = new BigDecimal(form.getTeachKeshi());
                pdTab.setKeshinum(Tkeshi);
            }

            if (form.getZdyesorno()== 1) {
                pdTab.setState(2);
            } else {
                pdTab.setState(1);
            }
            iPxautoxiaoketableService.updateById(pdTab);
        }

        Pxbuxikechengtable bxkc = iPxbuxikechengtableService.getById(form.getBuxiID());
        Pxstutable stu = iPxstutableService.getById(bxkc.getStuID());
        Pxkechengtable kc = iPxkechengtableService.getById(bxkc.getKechengID());
        Pxstafftable staff = iPxstafftableService.getById(staffID);

        String xiugai =
         staff.getStaffName() + "设置了自动消课：学生姓名：" + stu.getStuName() + "，补习课程ID：" +form.getBuxiID() + ",课程名称：" + kc.getKechengName() + ",班级ID：" +form.getClassID() +
                "," +
                "教师ID" +
                 "：" + teacherIDs + ",教师姓名：" + teacherNames + ",消课课时：" +form.getTeachKeshi();

        ajaxJson.setMsg("操作成功");
        return ajaxJson;
    }

    /**
     * @Description: editAutoxiaoke()方法作用:修改自动消课状态
     * @param:[autoID, type]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/15 9:27
     */
    @ResponseBody
    @RequestMapping(value = "editAutoxiaoke", method = RequestMethod.POST)
    @ApiOperation("修改自动消课状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "autoID", value = "自动消课ID", required = true),
            @ApiImplicitParam(name = "type", value = "要修改的类型 1：关闭 2：开启", required = true)
    })
    public AjaxJson editAutoxiaoke(@RequestBody editAutoxiaokeForm form) {
        AjaxJson ajaxJson = new AjaxJson();
        Pxautoxiaoketable pdTab = iPxautoxiaoketableService.getById(form.getAutoID());
        if (pdTab == null) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("操作失败，请刷新重试");
            return ajaxJson;
        }
        if (form.getType() == 1) {
            pdTab.setState(2);
        } else if (form.getType() == 2) {
            pdTab.setState(1);
        }
        ajaxJson.setSuccess(iPxautoxiaoketableService.updateById(pdTab));
        return ajaxJson;
    }

    //endregion

    //region 刷卡签到签退

    @ResponseBody
    @RequestMapping(value = "getshuakaPage", method = RequestMethod.GET)
    @ApiOperation("获取离校刷卡信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页获取的大小", example = "10", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "stuGradeID", value = "年级ID", required = false),
            @ApiImplicitParam(name = "stuID", value = "学号", required = false),
            @ApiImplicitParam(name = "stuName", value = "班级", required = false),
    })
    public AjaxJson getshuakaPage(Long current, Long size, String campusID, String stuGradeID, String stuID, String
            stuName, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<shuakaVo> page = new Page<>(current, size);
        QueryWrapper<shuakaVo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("1", 1)
                .eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("b.campusID", campusID);
        }
        if (StringUtils.isNotBlank(stuGradeID)) {
            queryWrapper.eq("b.stuGradeID", stuGradeID);
        }
        if (StringUtils.isNotBlank(stuID)) {
            queryWrapper.like("b.zidingyiStuID", stuID).or().eq("a.stuID", stuID);
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("b.stuName", stuName);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 245);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("b.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("b.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("b.campusID", lookPower);
        }
        ajaxJson.setObj(iPxqiandaoqiantuitableService.getshuakaPage(page, queryWrapper));
        return ajaxJson;
    }


    /**
     * @Description: ExportshuakaPage()方法作用:导出刷卡导出
     * @param:[response, campusID, startDate, endDate]
     * @return:void
     * @auter:yyl
     * @data:2020/12/14 17:33
     */
    @RequestMapping(value = "ExportshuakaPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出刷卡签到签退")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "startDate", value = "开始时间", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束时间", required = false),
    })
    public void ExportshuakaPage(HttpServletResponse response, HttpServletRequest request,
                                 @RequestParam(required = false) String campusID, // 校区ID
                                 @RequestParam(required = false) String startDate, // 开始日期
                                 @RequestParam(required = false) String endDate // 结束日期
    ) {
        QueryWrapper<shuakaVo> queryWrapper = new QueryWrapper<>();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        queryWrapper
                .eq("1", 1)
                .eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("b.campusID", campusID);
        }
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("a.qianDatetime", startDate);
        }
        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("a.qianDatetime", endDate);
        }
        List<shuakaVo> shuakaVos = iPxqiandaoqiantuitableService.ExportshuakaPage(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "年级", "学号", "姓名", "离校时间"},
                shuakaVos,
                new String[]{"campusName", "stuGradeName", "stuID", "stuName", "qianDatetime-D"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "刷卡签到签退导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @Description: shuaqiandaoqiantui()方法作用:刷卡签到签退
     * @param:[cardNum]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/15 12:03
     */
    @ResponseBody
    @RequestMapping(value = "shuaqiandaoqiantui", method = RequestMethod.POST)
    @ApiOperation("刷卡签到签退")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cardNum", value = "卡号", required = true)
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson shuaqiandaoqiantui(String cardNum, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();

        if (StringUtils.isEmpty(cardNum)) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("请输入学生卡号或请刷卡");
            return ajaxJson;
        }
        QueryWrapper<Pxstucardtable> cardQ = new QueryWrapper<>();
        cardQ
                .eq("cardNumber", cardNum)
                .eq("qiyeID", qiyeID);
        Pxstucardtable stucard = null;
        List<Pxstucardtable> selectstucard = iPxstucardtableService.selectstucard(cardQ);
        if (selectstucard.size() == 0) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("未查询到学生记录，请确保输入的卡号正确");
            return ajaxJson;
        } else {
            stucard = selectstucard.get(0);
        }

        Long stuID = stucard.getStuID();
        int num = 0;
        String stuCampusName = iPxcampustableService.getById(campusID).getCampusName();
        Pxstutable stuinfo = iPxstutableService.getById(stuID);
        //region 推送给家长

        String noticeContent = "学生已经于" + DateUtil.formatDate2(new Date()) + "在我校离校签退";
        Pxtuisongtable pxtuisongtable1 = new Pxtuisongtable()
                .setAddStaffID(loginUser.getStaffID())
                .setAddTime(new Date())
                .setAppread(0)
                .setQiyeID(loginUser.getQiyeID())
                .setStuID(stuinfo.getId())
                .setTuisongTypeName(3L)
                .setRole(1)
                .setNote(noticeContent)
                .setWxstate(1);
        iPxtuisongtableService.save(pxtuisongtable1);
        sendMessage.sendMessage(pxtuisongtable1.getId());

//        String templateId = "2SvkQxH4NqjmxY-QNaQaToBz7wjB9u5ai8VcbPuvS3M";
//        //点击消息跳转小程序链接
//        String url = "";
//
//        ;
//        //模板消息内容信息，与模板消息使用一致
//        List<TemplateParam> paras = new ArrayList<>();
//        paras.add(new TemplateParam("first", stuinfo.getStuName() + "同学,", "#FF3333"));
//        paras.add(new TemplateParam("keyword1", noticeContent, "#0044BB"));
//        paras.add(new TemplateParam("remark", stuCampusName, "#0044BB"));
//
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("stuId", stuinfo.getId());
//        List<WscUserBind> wscUserBinds = iWscUserBindService.list(queryWrapper);
//        for (WscUserBind binduser : wscUserBinds) {
//            WscUser wscUser = iWscUserService.getById(binduser.getWscuserid());
//            if (wscUser != null) {
//                if (wscUser.getUnionid() != "" && wscUser.getUnionid() != null) {
//                    QueryWrapper quer = new QueryWrapper<>();
//                    quer.eq("unionid", wscUser.getUnionid());
//                    GzhAlluser gzhAlluser = iGzhAlluserService.getOne(quer);
//                    WXTmplMsgUtils.sendWXTmplMsg(templateId, gzhAlluser.getOpenid(), url, paras);
//                }
//            }
//        }

        //endregion

        Pxqiandaoqiantuitable one = new Pxqiandaoqiantuitable();
        one.setQiyeID(qiyeID);
        one.setAddstaffid(staffID);
        one.setQiandaoorqiantui(2);//签退
        one.setStuid(stuID);
        one.setQianstyle(1);//刷卡
        one.setQiandatetime(new Date());
        if (num > 0) {
            one.setTsstate(true);
        } else {
            one.setTsstate(false);
        }
        iPxqiandaoqiantuitableService.save(one);
        ajaxJson.setMsg("刷卡消课成功");
        return ajaxJson;
    }


    //endregion

    //region 人工签到签退


    /**
     * @Description: getPaikeQiandaoPage()方法作用:排课签到签退
     * @param:[current, size, campusID, stuName, kechengName, className, haveclassDate]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/10 9:38
     */
    @ResponseBody
    @RequestMapping(value = "getPaikeQiandaoPage", method = RequestMethod.GET)
    @ApiOperation("排课签到签退")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页获取的大小", example = "10", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "stuName", value = "学员姓名", required = false),
            @ApiImplicitParam(name = "kechengName", value = "课程", required = false),
            @ApiImplicitParam(name = "className", value = "班级", required = false),
//            @ApiImplicitParam(name = "haveclassDate", value = "上课日期", required = false),
            @ApiImplicitParam(name = "startDate", value = "开始时间", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束时间", required = false),
    })
    public AjaxJson getPaikeQiandaoPage(Long current, Long size, String campusID, String stuName, String
            kechengName, String className, String startDate, String endDate, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<ArtificialQiandaoVo> page = new Page<>(current, size);
        QueryWrapper<ArtificialQiandaoVo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("1", 1)
                .eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {
            queryWrapper.ge("c.haveClassDate", startDate).le("c.haveClassDate", endDate);
        } else {
            //获取当前日期
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            queryWrapper.eq("c.haveClassDate", df.format(new Date()));
        }
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("a.campusID", campusID);
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("a.stuName", stuName);
        }
        if (StringUtils.isNotBlank(kechengName)) {
            queryWrapper.like("f.kechengName", kechengName);
        }
        if (StringUtils.isNotBlank(className)) {
            queryWrapper.like("e.className", className);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 246);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }
        ajaxJson.setObj(iPxstutableService.getPaikeQiandaoPage(page, queryWrapper));
        return ajaxJson;
    }


    /**
     * @Description: getqiandaoliushuiPage()方法作用:分页获取签到签退流水
     * @param:[current, size, stuName, type, style]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/10 18:47
     */
    @ResponseBody
    @RequestMapping(value = "getqiandaoliushuiPage", method = RequestMethod.GET)
    @ApiOperation("分页获取签到签退流水")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页获取的大小", example = "10", required = true),
            @ApiImplicitParam(name = "stuName", value = "学员姓名", required = false),
            @ApiImplicitParam(name = "type", value = "签到类型 1:签到 2：签退 -1（默认：全部）", example = "-1", required = false),
            @ApiImplicitParam(name = "style", value = "签到方式 1：刷卡 2：微信 3：人工 -1（默认：全部）", example = "-1", required = false),
    })
    public AjaxJson getqiandaoliushuiPage(Long current, Long size, String stuName, int type, int style, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<qiandaoLiushuiVo> page = new Page<>(current, size);
        QueryWrapper<qiandaoLiushuiVo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("1", 1)
                .eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("d.stuName", stuName);
        }
        if (type != -1) {
            queryWrapper.eq("a.qiandaoOrqiantui", type);
        }
        if (style != -1) {
            queryWrapper.eq("a.qianStyle", style);
        }
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 246);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("d.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("d.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("d.campusID", lookPower);
        }
        ajaxJson.setObj(iPxqiandaoqiantuitableService.getqiandaoliushuiPage(page, queryWrapper));
        return ajaxJson;
    }


    //添加人工 排课签到、签退
    @ResponseBody
    @RequestMapping(value = "rgqiandaoqiantui", method = RequestMethod.POST)
    @ApiOperation("人工签到签退 排课签到签退事件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true),
            @ApiImplicitParam(name = "paikeID", value = "排课ID", required = true),
            @ApiImplicitParam(name = "type", value = "签到类别 1：签到 2：签退", required = true),
            @ApiImplicitParam(name = "iskoukeshi", value = "是否扣课时 1：扣课时 2：不扣课时", required = true),
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson rgqiandaoqiantui(HttpServletRequest request, @RequestBody rgqiandaoqiantuiForm form) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        Long stusharebuxiID = null;
        BigDecimal sharekehao = new BigDecimal(0);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        BigDecimal l = new BigDecimal(0);
        String xiugaiInfo = "";

        Pxstutable msgStu = iPxstutableService.getById(form.getStuID());
        String msgStuName = msgStu.getStuName();
        String stuCampusName = iPxcampustableService.getById(msgStu.getCampusID()).getCampusName();
        String noticeContent = "";

        if (form.getType() == 1) {
            //签到
            QueryWrapper<Pxqiandaoqiantuitable> qd = new QueryWrapper<>();
            qd
                    .eq("qiyeID", qiyeID)
                    .eq("stuID", form.getStuID())
                    .eq("paikeID", form.getPaikeID())
                    .eq("qiandaoOrqiantui", form.getType());
            List<Pxqiandaoqiantuitable> qdTab = iPxqiandaoqiantuitableService.selectqiaodao(qd);
            if (qdTab.size() > 0) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("已经存在签到记录");
                return ajaxJson;
            }
            //region  给家长端发送到校签到消息
            noticeContent = "学生已经于" + DateUtil.formatDate1(new Date()) + "在我校到校签到";

            Pxtuisongtable pxtuisongtable1 = new Pxtuisongtable()
                    .setAddStaffID(loginUser.getStaffID())
                    .setAddTime(new Date())
                    .setAppread(0)
                    .setQiyeID(loginUser.getQiyeID())
                    .setStuID(msgStu.getId())
                    .setTuisongTypeName(2L)
                    .setRole(1)
                    .setNote(noticeContent)
                    .setWxstate(1);
            iPxtuisongtableService.save(pxtuisongtable1);
            sendMessage.sendMessage(pxtuisongtable1.getId());
            //endregion
        } else if (form.getType() == 2) {
            //签退
            QueryWrapper<Pxqiandaoqiantuitable> qt = new QueryWrapper<>();
            qt
                    .eq("qiyeID", qiyeID)
                    .eq("stuID", form.getStuID())
                    .eq("paikeID", form.getPaikeID())
                    .and(a -> a
                            .eq("qiandaoOrqiantui", 2)
                            .or().eq("qiandaoOrqiantui", ""));
            List<Pxqiandaoqiantuitable> qtTab = iPxqiandaoqiantuitableService.selectqiaodao(qt);
            if (qtTab.size() > 0) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("已经存在签退记录");
                return ajaxJson;
            }
            //region  给家长端发送离校签退消息
            noticeContent = "学生已经于" + DateUtil.formatDate1(new Date()) + "在我校离校签退";

            Pxtuisongtable pxtuisongtable1 = new Pxtuisongtable()
                    .setAddStaffID(loginUser.getStaffID())
                    .setAddTime(new Date())
                    .setAppread(0)
                    .setQiyeID(loginUser.getQiyeID())
                    .setStuID(msgStu.getId())
                    .setTuisongTypeName(3L)
                    .setRole(1)
                    .setNote(noticeContent)
                    .setWxstate(1);
            iPxtuisongtableService.save(pxtuisongtable1);
            sendMessage.sendMessage(pxtuisongtable1.getId());


            //endregion
        }

        Pxqiandaoqiantuitable addT = new Pxqiandaoqiantuitable();
        addT.setAddstaffid(staffID);
        addT.setQiyeID(qiyeID);
        addT.setQiandatetime(new Date());
        addT.setPaikeid(form.getPaikeID());
        addT.setQiandaoorqiantui(form.getType());
        addT.setQianstyle(3);
        addT.setStuid(form.getStuID());
        addT.setTsstate(false);
        iPxqiandaoqiantuitableService.save(addT);//保存签到/签退
        //模板消息ID


        if (form.getIskoukeshi() == 1) {

            xiugaiInfo += "人工签到签退成功";

            Pxstutable stu = iPxstutableService.getById(form.getStuID());
            List<paikelistVo> paikeList = iPxpaiketableService.getPaikeList(form.getStuID(), form.getPaikeID(), qiyeID);
            paikelistVo paike = null;
            if (paikeList.size() == 0) {
                // "没有查询到相关排课信息，要排了课才能打扣课时！";
                ajaxJson.setCode("N");
                ajaxJson.setMsg("没有查询到相关排课信息");
                return ajaxJson;
            } else {
                paike = paikeList.get(0);
            }

            Pxkechengtable pkkc = iPxkechengtableService.getById(paike.getKechengID());
            String buxiStyleName = iPxbuxistyletableService.getById(pkkc.getBuxiStyleID()).getBuxiStyleName();

            //班级总人数
            List<Pxbuxikechengtable> pkbuxi = iPxpaiketableService.getclassStuCount(paike.getId(), qiyeID);
            int classStuCount = pkbuxi.size();

            boolean remainksCanIsFushu = true;
            //剩余课时可以扣减为负数
            String sysParam63 = iPxsysparamvaluetableService.getsysvalue(qiyeID, 63L) != null ?
                    iPxsysparamvaluetableService.getsysvalue(qiyeID, 63L).getModifyValue() : iPxsysparamdefaulttableService.getById(63).getDefaultValue();
            if (sysParam63 != null && sysParam63 == "否") {
                remainksCanIsFushu = false;//剩余课时不可以扣减为负数
            }

            QueryWrapper<Pxkeshistutable> stukh = new QueryWrapper<>();
            stukh
                    .eq("qiyeID", qiyeID)
                    .eq("classID", paike.getClassID())
                    .eq("stuID", form.getStuID())
                    .eq("haveClassDate", paike.getHaveClassDate())
                    .eq("weekN", paike.getWeekN())
                    .eq("startLessonDateTime", paike.getStartLessonDateTime())
                    .eq("endLessonDateTime", paike.getEndLessonDateTime());
            List<Pxkeshistutable> ishavekehao = iPxkeshistutableService.selectkehao((stukh));
            if (ishavekehao.size() > 0) {
                stukh.eq("teachernames", paike.getTeacherNames());
                List<Pxkeshistutable> teacher = iPxkeshistutableService.selectkehao(stukh);

                if (teacher.size() > 0) {
                    ajaxJson.setCode("N");
                    ajaxJson.setMsg("该学生已签到签退成功");
                    return ajaxJson;
                } else {
                    xiugaiInfo += "，员工编号：" + ishavekehao.get(0).getTeacherIDs() + "，员工姓名：" + ishavekehao.get(0).getTeacherNames() + "，在" + ishavekehao.get(0).getAddtime() + "操作了不排课消课且不排课消课老师不是排课老师，取消了检测当前课程、班级、上课日期、上课时间已有排课，现在学生来进行签到签退消课，即学生在已经扣除课时的基础上再次进行扣课时，可到学员课时复查";
                }
            }


            String teacherIDs = "";
            String teacherNames = "";

            QueryWrapper<Pxpaiketeachertable> pkTea = new QueryWrapper<>();
            pkTea
                    .eq("paikeID", paike.getId())
                    .eq("qiyeID", qiyeID);
            List<Pxpaiketeachertable> paikeTeacher = iPxpaiketeachertableService.selectkehaoTeacher(pkTea);

            for (Pxpaiketeachertable itemts : paikeTeacher) {
                teacherIDs += itemts.getTeacherID() + ",";
                teacherNames += iPxstafftableService.getById(itemts.getTeacherID()).getStaffName() + ",";
            }
            teacherIDs = teacherIDs.trim();
            teacherNames = teacherNames.trim();

            Pxbuxikechengtable bk = iPxpaiketableService.getbk(form.getStuID(), paike.getId(), qiyeID).get(0);
            if (bk == null) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("该学生这个时间的课程未启用或者未进行排课");
                return ajaxJson;
            }
            String isKanEndDate = iPxsysparamvaluetableService.getsysvalue(qiyeID, 61L) != null ?
             iPxsysparamvaluetableService.getsysvalue(qiyeID, 61L).getModifyValue() : iPxsysparamdefaulttableService.getById(61).getDefaultValue();
            if (isKanEndDate == "是") {
                if (bk.getEndDate() != null) {
                    long time = new Date().getTime();
                    if (bk.getEndDate().getTime() < time) {
                        ajaxJson.setCode("N");
                        ajaxJson.setMsg("上课的结束日期已经小于今天，即课程已过期");
                        return ajaxJson;
                    }
                } else {
                    ajaxJson.setCode("N");
                    ajaxJson.setMsg("上课的结束日期为空，请完善课程的结束日期后再消课");
                    return ajaxJson;
                }
            }

            Integer jfstyle = bk.getJifeiStyleID();
            Long stukechengID = bk.getKechengID();

            long timeL = (paike.getEndLessonDateTime().getTime() - paike.getStartLessonDateTime().getTime()) / (1000 * 60); //排课时间长
            long kcTime = Long.parseLong(paike.getClassTimeStyleName());

            double shichang = 0.0;
            if (jfstyle == 1 || jfstyle == 2) {
                if (kcTime != -1 && kcTime != -2) {
                    shichang = ((double) (Math.round(timeL * 100) / 100.0)) / ((double) (Math.round(kcTime * 100) / 100.0)); //把long类型的转为double类型，保留2位小数
                } else {
                    shichang = 1.0;
                }
            } else {
                shichang = 1.0;
            }

            xiugaiInfo += "，接下来【扣减学员课时和学费】";

            BigDecimal ksPrice = new BigDecimal(0);
            BigDecimal scNum = new BigDecimal(shichang);
            BigDecimal sc = new BigDecimal(shichang);
            if (jfstyle == 1 || jfstyle == 2) {
                if (bk.getRemainkeshi().subtract(scNum).intValue() < 0) {
                    xiugaiInfo += "，学员剩余课时不够扣";
                    QueryWrapper<Pxbuxikechengtable> bx2 = new QueryWrapper<>();
                    bx2
                            .eq("kechengID", stukechengID)
                            .eq("stuID", form.getStuID())
                            .eq("isShow", 0)
                            .ge("remainkeshi", scNum)
                            .eq("qiyeID", qiyeID);
                    Pxbuxikechengtable buxi2 = iPxbuxikechengtableService.selectbuxikecheng(bx2).get(0);
                    if (buxi2 != null) {
                        xiugaiInfo += "，学员有同课程ID的隐藏课程的剩余课时够扣";
                        stukechengID = buxi2.getKechengID();

                        //新老课程切换
                        String kcQiehuan = kechengQiehuan.kechengQiehuan(bk, buxi2, buxiStyleName, paike.getClassID(), staffID, qiyeID);
                        xiugaiInfo += kcQiehuan;

                        xiugaiInfo += "，消课前剩余课时" + buxi2.getRemainkeshi().toString() + "，要扣减课时" + sc.toString();

                        buxi2.setRemainkeshi(buxi2.getRemainkeshi().subtract(sc));//扣减课时
                        iPxbuxikechengtableService.updateById(buxi2);
                        xiugaiInfo += "，消课后剩余课时" + buxi2.getRemainkeshi().toString();

                        if (buxi2.getType() == 2) {
                            //如果该条记录是赠送课时，则不记单价，也不扣除学员学费
                            //类型：为空或1买的，2赠送的，3转送得到的，4转送出去的
                            ksPrice = l;
                            xiugaiInfo += "，课程记录是赠送的，消课单价置0，不扣减学费";

                        } else {
                            ksPrice = buxi2.getKechengprice();
                            xiugaiInfo += "，消课单价" + ksPrice.toString();
                            xiugaiInfo += "，扣减学费前剩余学费" + stu.getRemainXuefei().toString();
                            stu.setRemainXuefei(stu.getRemainXuefei().subtract(sc.multiply(ksPrice)));
                            iPxstutableService.updateById(stu);
                            iPxstutableService.updateById(stu);
                            xiugaiInfo += "，扣减学费后剩余学费" + stu.getRemainXuefei().toString();
                        }

                    } else {
                        xiugaiInfo += "，不存在相同且够扣的补习课程记录";

                        if (!bk.getShareBuxiID().equals("0")) {
                            //有共享课时
                            String[] sharebuxi = bk.getShareBuxiID().split(",");
                            for (String items : sharebuxi) {
                                Pxbuxikechengtable onesharebx = iPxbuxikechengtableService.getById(items);
                                Pxstutable sharestu = iPxstutableService.getById(onesharebx.getStuID());
                                Pxkechengtable sharekc = iPxkechengtableService.getById(onesharebx.getKechengID());
                                if (onesharebx.getRemainkeshi().compareTo(sc) == 1) { //补习课程剩余课时>扣减课时
                                    xiugaiInfo += "。存在够扣减的共享课时,共享了:" + sharestu.getStuName() + "的" + sharekc.getKechengName() + "课程";

                                    BigDecimal shareMoney = bk.getKechengprice().multiply(sc);
                                    BigDecimal sharekeshi = shareMoney.divide(onesharebx.getKechengprice());

                                    sharekehao = sharekeshi;
                                    onesharebx.setRemainkeshi(onesharebx.getRemainkeshi().subtract(sharekeshi));
                                    iPxbuxikechengtableService.updateById(onesharebx);

                                    sharestu.setRemainXuefei(sharestu.getRemainXuefei().subtract(shareMoney));
                                    iPxstutableService.updateById(sharestu);

                                    stusharebuxiID = onesharebx.getId();
                                    break;  //跳出并停止循环

                                }
                            }

                        } else {
                            if (remainksCanIsFushu == false) {
                                ajaxJson.setMsg(stu.getStuName() + "的剩余课时会扣减为负数，数据字典中设置的不允许课时扣减为负数!");
                                return ajaxJson;
                            }

                            xiugaiInfo += "，消课前剩余课时" + bk.getRemainkeshi().toString() + "，要扣减课时" + sc.toString();
                            bk.setRemainkeshi(bk.getRemainkeshi().subtract(sc));
                            iPxbuxikechengtableService.updateById(bk);
                            xiugaiInfo += "，消课后剩余课时" + bk.getRemainkeshi().toString();

                            if (bk.getType() == 2) {
                                //如果该条记录是赠送课时，则不记单价，也不扣除学员学费
                                //类型：为空或1买的，2赠送的，3转送得到的，4转送出去的
                                ksPrice = l;
                                xiugaiInfo += "，课程记录是赠送的，消课单价置0，不扣减学费";
                            } else {
                                ksPrice = bk.getKechengprice();
                                xiugaiInfo += "，消课单价" + ksPrice.toString();
                                xiugaiInfo += "，扣减学费前剩余学费" + stu.getRemainXuefei().toString();
                                stu.setRemainXuefei(stu.getRemainXuefei().subtract(sc.multiply(ksPrice)));
                                iPxstutableService.updateById(stu);
                                xiugaiInfo += "，扣减学费后剩余学费" + stu.getRemainXuefei().toString();
                            }
                        }


                    }
                } else {
                    xiugaiInfo += "，学员剩余课时够扣";
                    xiugaiInfo += "，消课前剩余课时" + bk.getRemainkeshi().toString() + "，要扣减课时" + sc.toString();

                    bk.setRemainkeshi(bk.getRemainkeshi().subtract(sc));
                    iPxbuxikechengtableService.updateById(bk);
                    xiugaiInfo += "，消课后剩余课时" + bk.getRemainkeshi().toString();

                    if (bk.getType() == 2) {
                        //如果该条记录是赠送课时，则不记单价，也不扣除学员学费
                        //类型：为空或1买的，2赠送的，3转送得到的，4转送出去的
                        ksPrice = l;
                        xiugaiInfo += "，课程记录是赠送的，消课单价置0，不扣减学费";
                    } else {
                        ksPrice = bk.getKechengprice();
                        xiugaiInfo += "，消课单价" + ksPrice.toString();
                        xiugaiInfo += "，扣减学费前剩余学费" + stu.getRemainXuefei().toString();
                        stu.setRemainXuefei(stu.getRemainXuefei().subtract(sc.multiply(ksPrice)));
                        iPxstutableService.updateById(stu);
                        xiugaiInfo += "，扣减学费后剩余学费" + stu.getRemainXuefei().toString();
                    }

                }
            } else {
                xiugaiInfo += "，按起止日期计费的课程签到/签退消课";
                //按起止日期计费的课程
                long days = (bk.getEndDate().getTime() - bk.getStartDate().getTime()) / (24 * 1000 * 60 * 60); ///1000毫秒*60s*60m*24h
                days += 1;
                if (days == 0) {
                    ajaxJson.setMsg("学员课程的结束日期要大于开始日期，请去学员课程检查");
                    return ajaxJson;
                }
                BigDecimal day = new BigDecimal(days);
                BigDecimal onedayMoney = bk.getZongjia().divide(day); //计算一天价钱

                xiugaiInfo += "，一天的学费" + onedayMoney.toString();
                xiugaiInfo += "，消课前学员剩余学费" + stu.getRemainXuefei().toString();
                stu.setRemainXuefei(stu.getRemainXuefei().subtract(onedayMoney));
                iPxstutableService.updateById(stu);
                xiugaiInfo += "，消课后学员剩余学费" + stu.getRemainXuefei().toString();
            }

            xiugaiInfo += "，接下来【添加学员课时记录】";

            //向学生课时表里添加课时消耗记录
            Pxkeshistutable ksStu = new Pxkeshistutable();
            ksStu.setAddtime(new Date());
            ksStu.setQiyeID(qiyeID);
            ksStu.setAdduser(staffID);
            ksStu.setStuID(form.getStuID());
            ksStu.setClassID(paike.getClassID());
            ksStu.setCampusID(iPxclasstableService.getById(paike.getClassID()).getCampusID());
            ksStu.setKechengID(stukechengID);
            ksStu.setStuGradeID(stu.getStuGradeID());
            ksStu.setHaveClassDate(paike.getHaveClassDate());
            ksStu.setWeekN(paike.getWeekN());
            ksStu.setStartLessonDateTime(paike.getStartLessonDateTime());
            ksStu.setEndLessonDateTime(paike.getEndLessonDateTime());
            ksStu.setBuxiStyleID(paike.getBuxiStyleID());
            ksStu.setClassTimeStyleID(paike.getClassTimeStyleID());

            xiugaiInfo += "，学员课时记录，课程ID" + stukechengID + "，班级ID" + paike.getClassID() + "，上课日期时间" + paike.getHaveClassDate().toString() + " " + paike.getStartLessonDateTime().toString().substring(0, 5) + "-" + paike.getEndLessonDateTime().toString().substring(0, 5);

            BigDecimal y = new BigDecimal(1);

            if (jfstyle == 1 || jfstyle == 2) {


                if (stusharebuxiID != null && stusharebuxiID != 0) {
                    //有进行共享消课
                    ksStu.setShareBuxiID(stusharebuxiID);
                    Pxbuxikechengtable one = iPxbuxikechengtableService.getById(stusharebuxiID);
                    ksStu.setKechengPrice(one.getKechengprice());
                    ksStu.setKeshiNum(sharekehao);//课时
                    xiugaiInfo += "，按课时或课时包计费的课程,单价" + one.getKechengprice().toString() + "，课时" + sharekehao.toString();

                } else {
                    ksStu.setShareBuxiID(0L);
                    ksStu.setKechengPrice(ksPrice);
                    ksStu.setKeshiNum(sc);
                    xiugaiInfo += "，按课时或课时包计费的课程,单价" + ksPrice.toString() + "，课时" + sc.toString();
                }
            } else {
                //判断老数据与新数据差别消课
                if (bk.getKeshiNum().intValue() != 0 && bk.getRemainkeshi().intValue() != 0) {
                    ksStu.setKechengPrice(bk.getKechengprice());

                    ksStu.setKeshiNum(y);
                    bk.setRemainkeshi(bk.getRemainkeshi().subtract(y));
                    iPxbuxikechengtableService.updateById(bk);

                    xiugaiInfo += "，按起止日期计费课程，一天的学费" + bk.getKechengprice().toString();

                    xiugaiInfo += "，消课前学员剩余学费" + stu.getRemainXuefei().toString();
                    stu.setRemainXuefei(stu.getRemainXuefei().subtract(bk.getKechengprice()));
                    xiugaiInfo += "，消课后学员剩余学费" + stu.getRemainXuefei().toString();
                } else {
                    long time = (bk.getEndDate().getTime() - bk.getStartDate().getTime()) / (24 * 1000 * 60 * 60); ///1000毫秒*60s*60m*24h
                    BigDecimal dayy = new BigDecimal(time);
                    //计算一天价钱
                    BigDecimal onedayMoney = bk.getZongjia().divide(dayy);

                    xiugaiInfo += "，按起止日期计费的课程,一天的价格（消课单价）" + onedayMoney.toString();

                    ksStu.setKechengPrice(onedayMoney);
                    ksStu.setKeshiNum(y);
                }
            }

            ksStu.setStuKaoqingStyle("1");
            ksStu.setDakaoqingStyle(2);
            ksStu.setShuoMing("签到/签退消课");
            ksStu.setBuxikechengID(paike.getBuxiID());
            ksStu.setTeacherIDs(teacherIDs);
            ksStu.setTeacherNames(teacherNames);

            xiugaiInfo += "，上课老师IDs:" + teacherIDs;
            xiugaiInfo += "，上课老师姓名:" + teacherNames;
            iPxkeshistutableService.save(ksStu);

            xiugaiInfo += "，学员课时记录添加【完毕】,接下来【添加keshiStuTeacherTable】";

            QueryWrapper<Pxpaiketeachertable> pkteaT = new QueryWrapper<>();
            pkteaT
                    .eq("paikeID", paike.getId())
                    .eq("qiyeID", qiyeID);
            List<Pxpaiketeachertable> paikeTeachers = iPxpaiketeachertableService.selectkehaoTeacher(pkteaT);

            if (paikeTeachers.size() > 0) {
                for (Pxpaiketeachertable itemt : paikeTeachers) {
                    xiugaiInfo += "，开始添加keshiStuTeacherTable老师ID" + itemt.getTeacherID().toString();
                    Pxkeshistuteachertable stuksTea = new Pxkeshistuteachertable();
                    stuksTea.setKeshistutableid(ksStu.getId());
                    stuksTea.setTeacherid(itemt.getTeacherID());
                    stuksTea.setQiyeID(qiyeID);
                    iPxkeshistuteachertableService.save(stuksTea);
                }
            }

            xiugaiInfo += "，添加keshiStuTeacherTable【完毕】";

            //从数据字典里读出积分比例值
            //如果比例值==0，什么事情都不做；
            //否则，往积分表里插入一条记录，即给学生送积分；积分数= 学生课时数*积分比例值
            Pxstutable student = iPxstutableService.getById(form.getStuID());//学生表
            BigDecimal oldJifen = student.getJifenNum();//原本积分
            String jfen = iPxsysparamvaluetableService.getsysvalue(qiyeID, 72L) == null ? iPxsysparamdefaulttableService.getById(72).getDefaultValue() :
             iPxsysparamvaluetableService.getsysvalue(qiyeID, 72L).getModifyValue();

            String kechengName = iPxkechengtableService.getById(stukechengID).getKechengName();


            if (Integer.valueOf(jfen) > 0) {
                BigDecimal jf = new BigDecimal(jfen);
                BigDecimal ksN = sc;
                student.setJifenNum(jf.multiply(ksN));//改变学生表积分
                iPxstutableService.updateById(student);

                Pxjifentable JFT = new Pxjifentable();
                JFT.setStuID(form.getStuID());
                JFT.setType(1);
                JFT.setOldintegral(oldJifen);
                JFT.setIntegral(jf.multiply(ksN));
                JFT.setStaffID(staffID);
                JFT.setCreatetime(new Date());
                JFT.setRemark("签到/签退扣除课时，产生积分");
                JFT.setQiyeID(qiyeID);
                iPxjifentableService.save(JFT);
                // String stuCampusName=iPxcampustableService.getById(student.getCampusID()).getCampusName();
                //region 向家长推送积分变动提醒
                Pxtuisongtable pxtuisongtable = new Pxtuisongtable()
                        .setAddStaffID(loginUser.getStaffID())
                        .setAddTime(new Date())
                        .setAppread(0)
                        .setQiyeID(loginUser.getQiyeID())
                        .setStuID(form.getStuID())
                        .setTuisongTypeName(4L)
                        .setRole(1)
                        .setNote("按排课消课扣减课时，自动获得：" + jf.multiply(ksN) + "个积分！");
                iPxtuisongtableService.save(pxtuisongtable);
                sendMessage.sendMessage(pxtuisongtable.getId());

//                templateId = "wnStU0KbsNW6adcYSCdLEyY6Vs3bE1x71BRwtKO3PK4";
//                List<TemplateParam> parasjf = new ArrayList<>();
//                parasjf.add(new TemplateParam("first", student.getStuName() + "同学，你有一个积分变动通知", "#FF3333"));
//                parasjf.add(new TemplateParam("keyword1", "积分变动通知", "#0044BB"));
//                parasjf.add(new TemplateParam("keyword2", "按排课消课扣减课时，自动获得：" + jf.multiply(ksN) + "个积分！", "#0044BB"));
//                parasjf.add(new TemplateParam("remark", stuCampusName, "#0044BB"));
//
//
//                for (WscUserBind binduser : wscUserBinds) {
//                    WscUser wscUser = iWscUserService.getById(binduser.getWscuserid());
//                    if (wscUser != null) {
//                        if (wscUser.getUnionid() != "" && wscUser.getUnionid() != null) {
//                            QueryWrapper quer = new QueryWrapper<>();
//                            quer.eq("unionid", wscUser.getUnionid());
//                            GzhAlluser gzhAlluser = iGzhAlluserService.getOne(quer);
//                            boolean tsback = WXTmplMsgUtils.sendWXTmplMsg(templateId, gzhAlluser.getOpenid(), "", parasjf);
//                            pxtuisongtable.setWxstate(tsback ? 1 : 0);
//                        }
//                    }
//                }

                //endregion
            }


            xiugaiInfo += "，接下来【添加教师课时记录】";

            Long teacherKSID = 0L;
            //向老师课时表里添加上课记录
            for (Pxpaiketeachertable itempkt : paikeTeachers) {
                xiugaiInfo += "，开始添加教师课时记录教师ID" + itempkt.getTeacherID().toString();
                Long teacherID = itempkt.getTeacherID();

                QueryWrapper<Pxkeshiteachertable> ksT = new QueryWrapper<>();
                ksT
                        .eq("classID", paike.getClassID())
                        .eq("haveClassDate", paike.getHaveClassDate())
                        .eq("startLessonDateTime", paike.getStartLessonDateTime())
                        .eq("endLessonDateTime", paike.getEndLessonDateTime())
                        .eq("qiyeID", qiyeID);
                List<Pxkeshiteachertable> selectteakehao = iPxkeshiteachertableService.selectTeakehao(ksT);
                if (selectteakehao.size() == 0) {

                    xiugaiInfo += "，老师该课程课时段的课时记录还没有";
                    Pxkeshiteachertable addkeshiTeacher = new Pxkeshiteachertable();
                    addkeshiTeacher.setTeacherID(teacherID);
                    addkeshiTeacher.setClassID(paike.getClassID());
                    addkeshiTeacher.setBuxiStyleID(paike.getBuxiStyleID());
                    xiugaiInfo += "，已添加教师课时记录，课程ID" + paike.getKechengID() + "，班级ID" + paike.getClassID() + "上课日期时间" + paike.getHaveClassDate().toString() +
                    " " + paike.getStartLessonDateTime().toString().substring(0, 5) + "-" + paike.getEndLessonDateTime().toString().substring(0, 5) + ",课时" + sc.toString();
                    if (paike.getBuxiStyleID() == 1) {
                        addkeshiTeacher.setStuID(form.getStuID());
                        xiugaiInfo += "，一对一课程，stuID" + form.getStuID();
                    } else {
                        addkeshiTeacher.setStuID(0L);
                        xiugaiInfo += "，班课";
                    }
                    addkeshiTeacher.setKechengID(paike.getKechengID());
                    addkeshiTeacher.setClassTimeStyleID(paike.getClassTimeStyleID());
                    addkeshiTeacher.setHaveClassDate(paike.getHaveClassDate());
                    addkeshiTeacher.setWeekN(paike.getWeekN());
                    addkeshiTeacher.setStartLessonDateTime(paike.getStartLessonDateTime());
                    addkeshiTeacher.setEndLessonDateTime(paike.getEndLessonDateTime());
                    addkeshiTeacher.setKeshiNum(sc);
                    addkeshiTeacher.setYsStuNum(classStuCount);
                    addkeshiTeacher.setSsStuNum(1);
                    addkeshiTeacher.setQiyeID(qiyeID);
                    addkeshiTeacher.setCampusID(iPxclasstableService.getById(paike.getClassID()).getCampusID());
                    addkeshiTeacher.setTeacherkaoqing(""); ///缺说明
                    addkeshiTeacher.setAllstuNianji(""); ///缺年级
                    iPxkeshiteachertableService.save(addkeshiTeacher);

                    teacherKSID = addkeshiTeacher.getId();
                    xiugaiInfo += "，已添加教师课时记录" + itempkt.getTeacherID();
                } else {
                    Pxkeshiteachertable keshiTeacher = selectteakehao.get(0);
                    teacherKSID = keshiTeacher.getId();
                    keshiTeacher.setSsStuNum(keshiTeacher.getSsStuNum() + 1);
                    iPxkeshiteachertableService.updateById(keshiTeacher);
                    xiugaiInfo += "，签到/签退消课，实上人数加1后为" + keshiTeacher.getSsStuNum().toString();
                    xiugaiInfo += "，已添加教师课时记录" + itempkt.getTeacherID();
                }

            }
            xiugaiInfo += "，添加教师课时记录【完毕】";

            xiugaiInfo += "，接下来【添加学员考勤】";
            //添加学员考勤

            QueryWrapper<Pxstukaoqingtable> stukqT = new QueryWrapper<>();
            stukqT
                    .eq("stuID", form.getStuID())
                    .eq("kechengID", stukechengID)
                    .eq("haveclassDate", paike.getHaveClassDate())
                    .eq("startClassDateTime", paike.getStartLessonDateTime())
                    .eq("endClassDateTime", paike.getEndLessonDateTime())
                    .eq("qiyeID", qiyeID);
            List<Pxstukaoqingtable> stukq = iPxstukaoqingtableService.selectstukaoqing(stukqT);
            if (stukq.size() == 0) {
                xiugaiInfo += "，开始添加学员考勤";
                Pxstukaoqingtable onek = new Pxstukaoqingtable();
                onek.setEndclassdatetime(paike.getEndLessonDateTime());
                onek.setHaveclassdate(paike.getHaveClassDate());
                onek.setStartclassdatetime(paike.getStartLessonDateTime());
                onek.setKaoqingbeizhu("正常");
                onek.setKaoqingstyle(1);
                onek.setKechengid(stukechengID);
                onek.setClassid(paike.getClassID());
                onek.setStuid(form.getStuID());
                onek.setTeacherids(teacherIDs);
                onek.setTeachernames(teacherNames);
                onek.setQiyeid(qiyeID);
                iPxstukaoqingtableService.save(onek);
                xiugaiInfo += "，添加学员考勤【完毕】，接下来【添加stukaoqingTeacherTable】";
                for (Pxpaiketeachertable items : paikeTeachers) {
                    xiugaiInfo += "，开始添加stukaoqingTeacherTable教师ID" + items.getTeacherID();
                    Pxstukaoqingteachertable stukqt2 = new Pxstukaoqingteachertable();
                    stukqt2.setQiyeID(qiyeID);
                    stukqt2.setStukaoqingtabid(onek.getId());
                    stukqt2.setTeacherid(items.getTeacherID());
                    iPxstukaoqingteachertableService.save(stukqt2);
                    xiugaiInfo += "，已添加stukaoqingTeacherTable教师ID" + items.getTeacherID();
                }

                xiugaiInfo += "，添加stukaoqingTeacherTable【完毕】";
            }

            //家长推送孩子的剩余课时
            // 如果剩余课时低于预警值，给家长发送续费提醒;
            BigDecimal stuKechengRemainKeshi = iPxbuxikechengtableService.getSumRks(form.getStuID(), stukechengID, qiyeID).get(0).getSumR();
            Integer yujinzhi = Integer.valueOf(iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 11L) == null ?
 iPxsysparamdefaulttableService.getById(11).getDefaultValue() :
              iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 11L).getModifyValue());


            Pxsysparamvaluetable getvalue = iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 61L);
            Pxsysparamvaluetable getqinglin = iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 62L);
            boolean isshowEnddate = getvalue != null ? getvalue.getModifyValue() == "是" ? true : false :
             iPxsysparamdefaulttableService.getById(61).getDefaultValue() == "是" ? true : false;//是否在（补习）课程起止日期之前消课
            boolean isshowEnddate1 = getvalue != null ? getqinglin.getModifyValue() == "是" ? true : false :
iPxsysparamdefaulttableService.getById(61).getDefaultValue() == "是" ? true : false;//课时自动清零
            if (stuKechengRemainKeshi.intValue() > yujinzhi) {
                if (jfstyle == 3) {
                    noticeContent =
                     msgStuName + "的课程《" + kechengName + "》" + paike.getHaveClassDate().toString() + " " + paike.getStartLessonDateTime().toString().substring(0, 5) + "-" + paike.getEndLessonDateTime().toString().substring(0, 5) + "已消课";
                } else {
                    noticeContent =
                     msgStuName + "的课程《" + kechengName + "》" + paike.getHaveClassDate().toString() + " " + paike.getStartLessonDateTime().toString().substring(0, 5) + "-" + paike.getEndLessonDateTime().toString().substring(0, 5) + "上课消课" + sc.toString() + "课时，剩余" + stuKechengRemainKeshi.toString() + "课时。";
                }
            } else {
                noticeContent =
 msgStuName + "的课程《" + kechengName + "》" + paike.getHaveClassDate().toString() + " " + paike.getStartLessonDateTime().toString().substring(0,
 5) + "-" + paike.getEndLessonDateTime().toString().substring(0, 5) + "上课消课" + sc.toString() + "课时，剩余" + stuKechengRemainKeshi.toString() +
            "课时，请及时到我校续费。";
            }

            if (isshowEnddate == true || isshowEnddate1 == true) {
                noticeContent += "课程到期时间：" + bk.getEndDate().toString();
            }
            String noticeRemark = stuCampusName;


            //region  给家长发送续费提醒
            Pxtuisongtable pxtuisongtable2 = new Pxtuisongtable()
                    .setAddStaffID(loginUser.getStaffID())
                    .setAddTime(new Date())
                    .setAppread(0)
                    .setQiyeID(loginUser.getQiyeID())
                    .setStuID(form.getStuID())
                    .setTuisongTypeName(5L)
                    .setRole(1)
                    .setNote(noticeContent)
                    .setKechengID(stukechengID)
                    .setHaveclassDate(paike.getHaveClassDate())
                    .setStartLessonDateTime(paike.getStartLessonDateTime())
                    .setEndLessonDateTime(paike.getEndLessonDateTime())
                    .setWxstate(1);
            iPxtuisongtableService.save(pxtuisongtable2);
            sendMessage.sendMessage(pxtuisongtable2.getId());

            //endregion

            //如果打了考勤的人数已经大于或等于班级人数，则改变这次排课的考勤状态
            QueryWrapper<Pxstukaoqingtable> stukqQ = new QueryWrapper<>();
            stukqQ
                    .eq("haveclassDate", paike.getHaveClassDate())
                    .eq("startClassDateTime", paike.getStartLessonDateTime())
                    .eq("endClassDateTime", paike.getEndLessonDateTime())
                    .eq("classID", paike.getClassID())
                    .eq("teacherIDs", teacherIDs)
                    .eq("qiyeID", qiyeID);
            List<Pxstukaoqingtable> StuKaoqingCount = iPxstukaoqingtableService.selectstukaoqing(stukqQ);
            xiugaiInfo += "，班级总人数" + classStuCount + ",已完成考勤人数" + StuKaoqingCount.size();

            if (StuKaoqingCount.size() >= classStuCount) {
                Pxpaiketable paikekq = iPxpaiketableService.getById(paike.getId());
                if (paike != null) {
                    paikekq.setDakaoqin(true);
                    iPxpaiketableService.updateById(paikekq);
                    xiugaiInfo += "，该排课设置为已完成考勤，排课ID" + paikekq.getId();
                }
            }
            ajaxJson.setMsg("课时扣除成功");

        } else {
            ajaxJson.setMsg("操作成功");
        }
        return ajaxJson;
    }


    /**
     * @Description: getziyouqiandaoPage()方法作用:分页获取自由签到签退
     * @param:[current, size, campusID, className]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/10 9:38
     */
    @ResponseBody
    @RequestMapping(value = "getziyouqiandaoPage", method = RequestMethod.GET)
    @ApiOperation("分页获取自由签到签退")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页获取的大小", example = "10", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "className", value = "班级名称", required = false),
    })
    public AjaxJson getziyouqiandaoPage(Long current, Long size, String campusID, String className, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Page<ziyouqiandaoVo> page = new Page<>(current, size);
        QueryWrapper<ziyouqiandaoVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("b.campusID", campusID);
        }
        if (StringUtils.isNotBlank(className)) {
            queryWrapper.like("b.className", className);
        }
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 246);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("b.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("b.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("b.campusID", lookPower);
        }
        ajaxJson.setObj(iPxqiandaoqiantuitableService.getziyouqiandaoPage(page, queryWrapper));
        return ajaxJson;
    }


    /**
     * @Description: getziyouqiandaoLookStuPage()方法作用:自由签到班级学员详情
     * @param:[current, size, classID, stuID, stuName]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/10 14:12
     * 班级总人数-详情 点击查询事件
     */
    @ResponseBody
    @RequestMapping(value = "getziyouqiandaoLookStuPage", method = RequestMethod.GET)
    @ApiOperation("自由签到班级学员详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页获取的大小", example = "10", required = true),
            @ApiImplicitParam(name = "classID", value = "班级ID", required = true),
            @ApiImplicitParam(name = "stuID", value = "学号", required = false),
            @ApiImplicitParam(name = "stuName", value = "学员姓名", required = false),
    })
    public AjaxJson getziyouqiandaoLookStuPage(Long current, Long size, String classID, String stuID, String
            stuName, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();

        Page<ziyouqiandaoLookStuVo> page = new Page<>(current, size);
        QueryWrapper<ziyouqiandaoLookStuVo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq(" a.classID", classID)
                .eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(stuID)) {
            queryWrapper.like("c.zidingyiStuID", stuID).or().eq("b.stuID", stuID);
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("c.stuName", stuName);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 246);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("c.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("c.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("c.campusID", lookPower);
        }
        ajaxJson.setObj(iPxstuclasstableService.getziyouqiandaoLookStuPage(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * @Description: SaveqianDaoqianTui()方法作用:班级内学员签到、签退 事件
     * @param:[stuID, Type]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/10 18:47
     */
    @ResponseBody
    @RequestMapping(value = "SaveqianDaoqianTui", method = RequestMethod.POST)
    @ApiOperation("班级内学员签到、签退事件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true),
            @ApiImplicitParam(name = "Type", value = "签到类型  1：签到  2：签退", required = true),
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson SaveqianDaoqianTui(Long stuID, int Type, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();

        //获取30分钟前事件
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, -30);
        Date time = cal.getTime();
        //30分钟之内不能重复签到！

        if (Type == 1) {
            //签到
            //QueryWrapper qd =new QueryWrapper();
            //qd.eq("stuID",stuID);
            //qd.eq("qiandaoOrqiantui",1);//签到
            //qd.gt("qianDatetime",time);//大于30分钟前

            QueryWrapper<Pxqiandaoqiantuitable> qd = new QueryWrapper<>();
            qd
                    .eq("stuID", stuID)
                    .eq("qiandaoOrqiantui", 1)
                    .gt("qianDatetime", time)
                    .eq("qiyeID", qiyeID);
            List<Pxqiandaoqiantuitable> pdqiandao = iPxqiandaoqiantuitableService.selectqiaodao(qd);
            if (pdqiandao.size() > 0) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("请不要重复签到");
                return ajaxJson;
            }
        } else {
            //签退
            //QueryWrapper qt = new QueryWrapper();
            //qt.eq("stuID", stuID);
            //qt.eq("qiandaoOrqiantui", 2);//签退
            //qt.lt("qianDatetime", time);//小于30分钟前

            QueryWrapper<Pxqiandaoqiantuitable> qt = new QueryWrapper<>();
            qt
                    .eq("stuID", stuID)
                    .eq("qiandaoOrqiantui", 2)
                    .gt("qianDatetime", time)
                    .eq("qiyeID", qiyeID);
            List<Pxqiandaoqiantuitable> pdqiandao = iPxqiandaoqiantuitableService.selectqiaodao(qt);
            if (pdqiandao.size() > 0) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("请不要重复签到");
                return ajaxJson;
            }
        }
        Pxqiandaoqiantuitable QD = new Pxqiandaoqiantuitable();
        QD.setAddstaffid(staffID);
        QD.setQiyeID(qiyeID);
        QD.setStuid(stuID);
        QD.setQiandatetime(new Date());
        QD.setQianstyle(3);
        if (Type == 1) {
            QD.setQiandaoorqiantui(1);
        } else {
            QD.setQiandaoorqiantui(2);
        }
        QD.setTsstate(false);//添加时还未给家长推送--为假
        ajaxJson.setSuccess(iPxqiandaoqiantuitableService.save(QD));
        return ajaxJson;
    }


    /**
     * @Description: getziyouqiandaostuNumPage()方法作用:自由签到班级已签到人数详情
     * @param:[current, size, classID]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/10 16:45
     */
    @ResponseBody
    @RequestMapping(value = "getziyouqiandaostuNumPage", method = RequestMethod.GET)
    @ApiOperation("自由签到班级已签到人数详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页获取的大小", example = "10", required = true),
            @ApiImplicitParam(name = "classID", value = "班级ID", required = true),
    })
    public AjaxJson getziyouqiandaostuNumPage(Long current, Long size, Long classID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<ziyouqiandaostuNumVo> page = new Page<>(current, size);
        ajaxJson.setObj(iPxqiandaoqiantuitableService.getziyouqiandaostuNumPage(page, classID, qiyeID));
        return ajaxJson;
    }


    /**
     * @Description: getstuqiaoDaoNumPage()方法作用:自由签到班级已签到学员的签到详情
     * @param:[current, size, stuID]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/10 16:45
     */
    @ResponseBody
    @RequestMapping(value = "getstuqiaoDaoNumPage", method = RequestMethod.GET)
    @ApiOperation("自由签到班级已签到学员的签到详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页获取的大小", example = "10", required = true),
            @ApiImplicitParam(name = "stuID", value = "学号", required = true),
    })
    public AjaxJson getstuqiaoDaoNumPage(Long current, Long size, Long stuID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<stuqiaoDaoNumVo> page = new Page<>(current, size);
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        ajaxJson.setObj(iPxqiandaoqiantuitableService.getstuqiaoDaoNumPage(page, qiyeID, stuID));
        return ajaxJson;
    }


    /**
     * @Description: getziyouqiantuistuNumPage()方法作用:自由签到班级已签退人数详情
     * @param:[current, size, classID]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/10 16:45
     */
    @ResponseBody
    @RequestMapping(value = "getziyouqiantuistuNumPage", method = RequestMethod.GET)
    @ApiOperation("自由签到班级已签退人数详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页获取的大小", example = "10", required = true),
            @ApiImplicitParam(name = "classID", value = "班级ID", required = true),
    })
    public AjaxJson getziyouqiantuistuNumPage(Long current, Long size, Long classID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<ziyouqiandaostuNumVo> page = new Page<>(current, size);
        ajaxJson.setObj(iPxqiandaoqiantuitableService.getziyouqiantuistuNumPage(page, classID, qiyeID));
        return ajaxJson;
    }


    /**
     * @Description: getstuqiaoTuiNumPage()方法作用:自由签到班级已签到学员的签退详情
     * @param:[current, size, stuID]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/10 16:44
     */
    @ResponseBody
    @RequestMapping(value = "getstuqiaoTuiNumPage", method = RequestMethod.GET)
    @ApiOperation("自由签到班级已签到学员的签退详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页获取的大小", example = "10", required = true),
            @ApiImplicitParam(name = "stuID", value = "学号", required = true),
    })
    public AjaxJson getstuqiaoTuiNumPage(Long current, Long size, Long stuID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<stuqiaoDaoNumVo> page = new Page<>(current, size);
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        ajaxJson.setObj(iPxqiandaoqiantuitableService.getstuqiaoTuiNumPage(page, qiyeID, stuID));
        return ajaxJson;
    }


    /**
     * @Description: exportChongzhiInfo()方法作用:导出人工签到签退
     * @param:[response]
     * @return:void
     * @auter:yyl
     * @data:2020/12/9 16:53
     */
    @RequestMapping(value = "ExportArtificialQiandao", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("导出人工签到签退")
    public void exportChongzhiInfo(HttpServletResponse response, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        //人工签到签退统计
        List<ExportQianCountVo> exportQianCountVos = iPxqiandaoqiantuitableService.ExportQianCount(qiyeID);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"学员姓名", "学号", "签到次数", "签退次数"},
                exportQianCountVos,
                new String[]{"stuName", "stuID", "qdNum", "qtNum"});
        ExportExcel.ExcelSource source = new ExportExcel.ExcelSource();
        source.setSheetName("人工签到签退统计");
        source.setTableData(list);
        List<ExportExcel.ExcelSource> sourceList = new ArrayList<>();
        sourceList.add(source);

        //人工签到签退详情
        List<ExportQianInfoVo> exportQianInfoVos = iPxqiandaoqiantuitableService.ExportQianInfo(qiyeID);
        List<List<Object>> DetailedList = ExportExcel.formatDataToList(new String[]{"学员姓名", "签到类型", "签到方式", "时间",
                        "推送状态", "操作时间", "操作人"},
                exportQianInfoVos,
                new String[]{"stuName", "DorT", "qianStyle", "haveClassDate", "tsState", "qianDatetime-D", "addStaffName"});
        ExportExcel.ExcelSource sourcedetaile = new ExportExcel.ExcelSource();
        sourcedetaile.setSheetName("人工签到签退详情");
        sourcedetaile.setTableData(DetailedList);
        sourceList.add(sourcedetaile);

        try {
            // 需要将详细表一起导出
            ExportExcel.exportMultipleSheetExcel(response, sourceList, "人工签到签退导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //endregion

    //region 考勤流水


    /**
     * @Description: getKaoqingliushuiPage()方法作用:分页获取考勤流水
     * @param:[current, size, campusID, stuID, stuName, kechengName, Tearch, haveclassDate]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/8 18:09
     */
    @ResponseBody
    @RequestMapping(value = "getKaoqingliushuiPage", method = RequestMethod.GET)
    @ApiOperation("分页获取考勤流水")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "stuID", value = "学号", required = false),
            @ApiImplicitParam(name = "stuName", value = "学员姓名", required = false),
            @ApiImplicitParam(name = "kechengName", value = "课程", required = false),
            @ApiImplicitParam(name = "tearch", value = "上课教师", required = false),
            @ApiImplicitParam(name = "startDate", value = "开始日期", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束日期", required = false),
    })
    public AjaxJson getKaoqingliushuiPage(Long current, Long size, HttpServletRequest request,
                                          String campusID, String stuID, String stuName,
                                          String kechengName, String tearch, String startDate, String endDate) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        QueryWrapper<KaoqingliushuiVo> queryWrapper = new QueryWrapper<>();
        Page<KaoqingliushuiVo> page = new Page(current, size);
        queryWrapper
                .eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(stuID)) {
            queryWrapper
                    .and(a -> a.isNotNull("e.zidingyiStuID").like("e.zidingyiStuID", stuID))
                    .or(b -> b.isNull("e.zidingyiStuID").eq("a.id", stuID));
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("e.stuName", stuName);
        }
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("e.campusID", campusID);
        }
        if (StringUtils.isNotBlank(kechengName)) {
            queryWrapper.like("d.kechengName", kechengName);
        }
        if (StringUtils.isNotBlank(tearch)) {
            queryWrapper.like("c.staffName", tearch);
        }
        if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {
            queryWrapper.ge("a.haveclassDate", startDate).le("a.haveclassDate", endDate);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 248);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("e.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("e.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("e.campusID", lookPower);
        }
        ajaxJson.setObj(iPxstukaoqingtableService.getKaoqingliushuiPage(page, queryWrapper));
        return ajaxJson;
    }


    /**
     * @Description: ExportKaoqingliushui()方法作用:导出考勤流水
     * @param:[response, campusID, startDate, endDate]
     * @return:void
     * @auter:yyl
     * @data:2020/12/9 11:44
     */
    @RequestMapping(value = "ExportKaoqingliushui", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出考勤流水")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "startDate", value = "开始时间", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束时间", required = false),
    })
    public void ExportKaoqingliushui(HttpServletResponse response, HttpServletRequest request,
                                     @RequestParam(required = false) String campusID, // 校区ID
                                     @RequestParam(required = false) String startDate, // 开始日期
                                     @RequestParam(required = false) String endDate // 结束日期
    ) {
        QueryWrapper queryWrapper = new QueryWrapper();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("e.campusID", campusID);
        }
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("a.haveclassDate", startDate);
        }
        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("a.haveclassDate", endDate);
        }
        List<KaoqingliushuiVo> kaoqingliushuiVos = iPxstukaoqingtableService.ExportKaoqingliushui(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "学号", "姓名", "班主任", "课程", "上课老师", "上课日期", "上课时间", "下课时间", "考勤状态"},
                kaoqingliushuiVos,
                new String[]{"campusName", "stuID", "stuName", "banzhuren", "kechengName", "Tearch", "haveclassDate-D", "startClassDateTime-T",
                "endClassDateTime-T", "kaoqingStyle"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "导出考勤流水.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion

    //region  考勤统计


    /**
     * @Description: getKaoqingCountPage()方法作用:分页获取考勤统计
     * @param:[current, size, campusID, stuID, stuName]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/8 16:47
     */
    @ResponseBody
    @RequestMapping(value = "getKaoqingCountPage", method = RequestMethod.GET)
    @ApiOperation("分页获取考勤统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "stuID", value = "学号", required = false),
            @ApiImplicitParam(name = "stuName", value = "学员姓名", required = false),
    })
    public AjaxJson getKaoqingCountPage(Long current, Long size, String campusID, String stuID, String stuName, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        QueryWrapper<KaoqingCountVo> queryWrapper = new QueryWrapper<>();
        Page<KaoqingCountVo> page = new Page(current, size);
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(stuID)) {
            queryWrapper
                    .and(a -> a.isNotNull("b.zidingyiStuID").like("b.zidingyiStuID", stuID))
                    .or(b -> b.isNull("b.zidingyiStuID").eq("a.id", stuID));
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("b.stuName", stuName);
        }
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("b.campusID", campusID);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 249);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("b.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("b.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("b.campusID", lookPower);
        }
        ajaxJson.setObj(iPxstukaoqingtableService.getKaoqingCountPage(page, queryWrapper));
        return ajaxJson;
    }


    /**
     * @Description: ExportKaoqingCount()方法作用:导出考勤统计
     * @param:[response, campusID, startDate, endDate]
     * @return:void
     * @auter:yyl
     * @data:2020/12/9 11:26
     */
    @RequestMapping(value = "ExportKaoqingCount", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出考勤统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "startDate", value = "开始时间", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束时间", required = false),
    })
    public void ExportKaoqingCount(HttpServletResponse response, HttpServletRequest request,
                                   @RequestParam(required = false) String campusID, // 校区ID
                                   @RequestParam(required = false) String startDate, // 开始日期
                                   @RequestParam(required = false) String endDate // 结束日期
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("b.campusID", campusID);
        }
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("a.haveclassDate", startDate);
        }
        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("a.haveclassDate", endDate);
        }
        List<KaoqingCountVo> kaoqingCountVos = iPxstukaoqingtableService.ExportKaoqingCount(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "学号", "姓名", "班主任", "全部", "正常", "请假", "旷课", "迟到"},
                kaoqingCountVos,
                new String[]{"campusName", "stuID", "stuName", "banzhuren", "AllN", "zhengchang", "qingjia", "kuangke", "chidao"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "导出考勤统计.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion

    //region 试听课信息

    /**
     * 查询所有试听课信息
     *
     * @param request
     * @param current
     * @param size
     * @return
     */
    @RequestMapping(value = "/GetAllshitingkePages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页查询试听课信息")
    public AjaxJson GetAllshitingkePages(HttpServletRequest request, long current, long size, String title, String classroomname,
                                         String kechengName, String Sshangkedate, String Eshangkedate) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(title)) {
            queryWrapper.like("a.huodongTitle", title);
        }
        if (StringUtils.isNotEmpty(classroomname)) {
            queryWrapper.like("c.classRoomName", classroomname);
        }
        if (StringUtils.isNotEmpty(kechengName)) {
            queryWrapper.like("d.kechengName", kechengName);
        }
        if (StringUtils.isNotBlank(Sshangkedate)) {
            queryWrapper.ge("a.haveClassDate", Sshangkedate);
        }
        if (StringUtils.isNotBlank(Eshangkedate)) {
            queryWrapper.le("a.haveClassDate", Eshangkedate);
        }
        queryWrapper.orderByDesc("a.haveClassDate");
        Page<paikeShowVo> page = new Page(current, size);
        ajaxJson.setObj(iPxpaiketableService.GetPaikeShowPages(page, queryWrapper));
        return ajaxJson;
    }

    @ResponseBody
    @RequestMapping(value = "/SaveshitingkechengInfo", method = RequestMethod.POST)
    @ApiOperation(value = "保存试听课信息")
    public AjaxJson SaveshitingkechengInfo(HttpServletRequest request, shitingkeForm form) throws ParseException {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
//        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat ft1 = new SimpleDateFormat("HH:mm");
        String weekN = DateUtil.getWeekOfDate(form.getHaveClassDate());
        Random rd = new Random();
        int sjs = rd.nextInt(89999) + 10000; //随机生成范围10000-99999
        // 排课的批次
        String pktags = getTagByUUId();

        LocalTime startTime = LocalTime.parse(form.getStartLessonDateTime());
        LocalTime endTime = LocalTime.parse(form.getEndLessonDateTime());
        Time St = Time.valueOf(startTime);//上课时间
        Time Et = Time.valueOf(endTime);//下课时间
        //添加排课信息
        Pxpaiketable pkT = new Pxpaiketable();
        pkT.setTeacherIDs(form.getTeacherIDs());
        pkT.setTeacherNames(form.getTeacherNames());
        pkT.setStartLessonDateTime(St);
        pkT.setEndLessonDateTime(Et);
        pkT.setHaveClassDate(form.getHaveClassDate());
        pkT.setClassID(0L);
        pkT.setClassRoomID(form.getClassRoomID());
        pkT.setWeekN(weekN);
        pkT.setKechengID(form.getKechengID());//课程
        pkT.setTags(pktags);//排课的批次
        pkT.setDakaoqin(false);
        pkT.setQiyeID(loginUser.getQiyeID());
        pkT.setHuodongImg(form.getHuodongImg());
        pkT.setHuodongTitle(form.getHuodongTitle());
        pkT.setHuodongshuoming(form.getHuodongshuoming());
        pkT.setZixunphone(form.getZixunphone());
        pkT.setShitingprice(form.getShitingprice());
        pkT.setPaikeType(2);

        ajaxJson.setSuccess(iPxpaiketableService.save(pkT));
        return ajaxJson;
    }

    @RequestMapping(value = "/deletShitingke", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除试听课信息")
    public AjaxJson deletShitingke(HttpServletRequest request, long id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(iPxpaiketableService.removeById(id));
        return ajaxJson;
    }

    @ResponseBody
    @RequestMapping(value = "/GetshitingkeInfoById", method = RequestMethod.GET)
    @ApiOperation(value = "查询排课详情")
    public AjaxJson GetshitingkeInfoById(HttpServletRequest request, long Id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxpaiketableService.getpaikeListInfo(Id));
        return ajaxJson;
    }

    @ResponseBody
    @RequestMapping(value = "/AddliulangTimes", method = RequestMethod.GET)
    @ApiOperation(value = "增加浏览次数或者分享次数")
    public AjaxJson AddliulangTimes(HttpServletRequest request, long Id, Integer type) {
        AjaxJson ajaxJson = new AjaxJson();
        Pxpaiketable pxpaiketable = iPxpaiketableService.getById(Id);
        if (type == 1) {
            pxpaiketable.setLiulangtime(pxpaiketable.getLiulangtime() + 1);
        } else {
            pxpaiketable.setFenxiangtime(pxpaiketable.getFenxiangtime() + 1);
        }
        ajaxJson.setSuccess(iPxpaiketableService.updateById(pxpaiketable));
        return ajaxJson;
    }

    @ResponseBody
    @RequestMapping(value = "/GetJoinShitingInfo", method = RequestMethod.GET)
    @ApiOperation(value = "查询加入试听记录")
    public AjaxJson GetJoinShitingInfo(HttpServletRequest request, long Id) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.paikeID", Id);
        ajaxJson.setObj(iPxpaiketableService.GetAllShitingJoinstuInfo(queryWrapper));
        return ajaxJson;
    }

    //保存加入免费试听课信息
    public AjaxJson saveFreeShiting(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        return ajaxJson;
    }
    //endregion

    @ResponseBody
    @RequestMapping(value = "/GetAllkechengInfoByQuanxian", method = RequestMethod.GET)
    @ApiOperation(value = "根据权限获取可选择的课程信息")
    public AjaxJson GetAllkechengInfoByQuanxian(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper<Pxkechengtable> queryWrapper = new QueryWrapper<>();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("isShow", 1);//启用的课程
        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", 143);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("campusID", -1);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("campusID", lookPower);
        }

        ajaxJson.setObj(iPxkechengtableService.list(queryWrapper));
        return ajaxJson;
    }

    //region 刷脸签到签退
    @ResponseBody
    @RequestMapping(value = "/GetshualianxiaokeInfoPages", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询刷脸消课信息")
    public AjaxJson GetshualianxiaokeInfoPages(HttpServletRequest request, long current, long size, String stuName, long campusID, String teacherName,
                                               long buxiStyleID, String startshangkeDate, String endshangkeDate) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("stu.qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("keshi.dakaoqingStyle", 6);
        if (StringUtils.isNotEmpty(stuName)) {
            queryWrapper.like("stu.stuName", stuName);
        }
        if (campusID != 0) {
            queryWrapper.eq("stu.campusID", campusID);
        }
        if (StringUtils.isNotEmpty(teacherName)) {
            queryWrapper.like("keshi.teacherNames", teacherName);
        }
        if (buxiStyleID != 0) {
            queryWrapper.eq("kecheng.buxiStyleID", buxiStyleID);
        }
        if (StringUtils.isNotEmpty(startshangkeDate)) {
            queryWrapper.ge("keshi.haveClassDate", startshangkeDate);
        }
        if (StringUtils.isNotEmpty(endshangkeDate)) {
            queryWrapper.le("keshi.haveClassDate", endshangkeDate);
        }
        queryWrapper.orderByDesc("keshi.addtime");

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 567);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("campus.id", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("campus.id", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("campus.id", lookPower);
        }
        Page<HashMap<String, Object>> page = new Page(current, size);
        ajaxJson.setObj(iPxqiandaoqiantuitableService.GetshualianxiaokeInfoPages(page, queryWrapper));
        return ajaxJson;
    }

    @ResponseBody
    @RequestMapping(value = "/GetAllStuAndMubanImages", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有学生和采集的模板信息")
    public AjaxJson GetAllStuAndMubanImages(HttpServletRequest request, long current, long size, String stuName, long campusID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("stu.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotEmpty(stuName)) {
            queryWrapper.like("stu.stuName", stuName);
        }
        if (campusID != 0) {
            queryWrapper.eq("stu.campusID", campusID);
        }
        queryWrapper.orderByDesc("stu.dengjiTime");
        Page<HashMap<String, Object>> page = new Page(current, size);
        ajaxJson.setObj(iPxstutableService.GetAllStuInfoAndMubanImages(page, queryWrapper));
        return ajaxJson;
    }

    @ResponseBody
    @RequestMapping(value = "/SaveStuMubanImages", method = RequestMethod.GET)
    @ApiOperation(value = "保存刷脸签到签退模板图片")
    public AjaxJson SaveStuMubanImages(HttpServletRequest request, long StuID, String imageUrl) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("id", StuID);
        Pxstutable pxstutable = iPxstutableService.getOne(queryWrapper);
        if (pxstutable == null) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("未找到该学生信息，请确认！");
        } else {
            pxstutable.setStuxiaokeImage(imageUrl);
            ajaxJson.setSuccess(iPxstutableService.updateById(pxstutable));
        }
        return ajaxJson;
    }

    @ResponseBody
    @RequestMapping(value = "/DeleteStuMubanImage", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除刷脸签到签退模板图片")
    public AjaxJson DeleteStuMubanImage(HttpServletRequest request, long stuID) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        Pxstutable pxstutable = iPxstutableService.getById(stuID);

        String url1 = "http://xjbok-oss.oss-cn-beijing.aliyuncs.com/";
        String key = pxstutable.getStuxiaokeImage().substring(url1.length());

        if (!key.isEmpty()) {
            boolean bol = AliyunOssUtil.deleteOssFile(key);
            if (bol == true) {
                pxstutable.setStuxiaokeImage("");
                ajaxJson.setSuccess(iPxstutableService.updateById(pxstutable));
            } else {
                ajaxJson.setMsg("删除失败");
                ajaxJson.setSuccess(false);
            }
        }
        return ajaxJson;
    }

    @ResponseBody
    @RequestMapping(value = "/compareImgs", method = RequestMethod.POST)
    @ApiOperation(value = "对比图片")
    public AjaxJson compareImgs(HttpServletRequest request, @RequestPart("file") MultipartFile file) throws IOException {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        //String Url = AliyunOssUtil.uploadFile(multipartFile, loginUser.getQiyeID() + "");

        String fileName;
        String uploadFilePath = "H:/javaFace_about/images";
        fileName = UUID.randomUUID().toString() + file.getOriginalFilename() + ".png";
        File packageFile = new File(uploadFilePath);
        if (!packageFile.exists()) {
            packageFile.mkdir();
        }
        File targetFile = new File(uploadFilePath + "/" + fileName);
        file.transferTo(targetFile);
        String Url = targetFile.toString();

        String Urlfwq = StringUtil.replace(Url, "/", "\\");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("buxiStateID", 2);
        queryWrapper.isNotNull("stuxiaokeImage");
        List<Pxstutable> pxstutable = iPxstutableService.list(queryWrapper);
        String shibiestuIDs = "";
        for (Pxstutable item : pxstutable) {
            if (StringUtils.isNotEmpty(item.getStuxiaokeImage())) {
                // tuxiangshibie.detect(Urlfwq, StringUtil.replace(item.getStuxiaokeImage(), "/", "\\"));
                //double compareHist = tuxiangshibie.compare_image(Urlfwq, StringUtil.replace(item.getStuxiaokeImage(), "/", "\\"));
                //  double compareHist = tuxiangshibie.compare_image("H:\\javaFace_about\\images\\tmp02.png", "H:\\javaFace_about\\images\\11.png");
                double compareHist = 0.73;
                if (compareHist > 0.72) {
                    shibiestuIDs += item.getId() + ",";
                }
            }
        }
        String url1 = "http://xjbok-oss.oss-cn-beijing.aliyuncs.com/";
        String key = Url.substring(url1.length());
        AliyunOssUtil.deleteOssFile(key);
        ajaxJson.setMsg(shibiestuIDs);
        return ajaxJson;
    }

    @ResponseBody
    @RequestMapping(value = "/TestcompareImages", method = RequestMethod.GET)
    public AjaxJson TestcompareImages() {
        AjaxJson ajaxJson = new AjaxJson();
        // tuxiangshibie.detect("H:\\javaFace_about\\images\\tmp02.png", "H:\\javaFace_about\\images\\11.png");
        // double compareHist = tuxiangshibie.compare_image("H:\\javaFace_about\\images\\91c14421-21c7-4843-95bf-ea2d2307dd54blob.png",
        // "H:\\javaFace_about\\images\\11.png");
        double compareHist = 0.75;
        if (compareHist > 0.72) {
            ajaxJson.setMsg("识别成功！");
        } else {
            ajaxJson.setMsg("识别失败！");
        }
        return ajaxJson;
    }

    @ResponseBody
    @ApiOperation(value = "上传文件测试")
    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AjaxJson uploadTest(@RequestParam("file") MultipartFile file) throws IOException {
        AjaxJson ajaxJson = new AjaxJson();
        if (file.isEmpty())
            return null;
        String fileName;
        String uploadFilePath = "H:/javaFace_about/images";
        fileName = UUID.randomUUID().toString() + file.getOriginalFilename();
        File packageFile = new File(uploadFilePath);
        if (!packageFile.exists()) {
            packageFile.mkdir();
        }
        File targetFile = new File(uploadFilePath + "/" + fileName);
        file.transferTo(targetFile);
        ajaxJson.setMsg(targetFile.toString());
        return ajaxJson;
    }
    //endregion
}
