package com.xwcloud.cloud.stu.Controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.DateUtil;
import com.xwcloud.cloud.common.ExportExcel;
import com.xwcloud.cloud.common.importstuFilesExcel;
import com.xwcloud.cloud.model.Form.*;
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
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-05
 */
@Controller
@Api(tags = "学员管理")
@RequestMapping("/stu/stuManagement")
public class stutableController {


    //region 注入有关服务
    @Autowired
    IPxstutableService iPxstutableService;

    @Autowired
    IPxgradeupdatetableService iPxgradeupdatetableService;

    @Autowired
    IPxroomtableService iPxroomtableService;

    @Autowired
    IPxjifentableService iPxjifentableService;

    @Autowired
    IPxstucardtableService iPxstucardtableService;

    @Autowired
    IPxbuxikechengtableService iPxbuxikechengtableService;

    @Autowired
    IPxstuclasstableService iPxstuclasstableService;

    @Autowired
    IPxbuxistyletableService iPxbuxistyletableService;

    @Autowired
    IPxclasstableService iPxclasstableService;

    @Autowired
    IPxpaiketableService iPxpaiketableService;

    @Autowired
    IPxpaiketeachertableService iPxpaiketeachertableService;

    @Autowired
    IPxxuanketableService iPxxuanketableService;

    @Autowired
    IPxchongzhipaytableService iPxchongzhipaytableService;

    @Autowired
    IPxkeshizhuansongtableService iPxkeshizhuansongtableService;

    @Autowired
    IPxkeshistutableService iPxkeshistutableService;

    @Autowired
    IPxkeshiteachertableService iPxkeshiteachertableService;

    @Autowired
    IPxkeshistuteachertableService iPxkeshistuteachertableService;

    @Autowired
    IPxstukaoqingtableService iPxstukaoqingtableService;

    @Autowired
    IPxstukaoqingteachertableService iPxstukaoqingteachertableService;

    @Autowired
    IPxqiandansubjecttableService iPxqiandansubjecttableService;

    @Autowired
    IPxqiandaninfotableService iPxqiandaninfotableService;

    @Autowired
    IPxqiandaninfo2tableService iPxqiandaninfo2tableService;

    @Autowired
    IPxliushuizhangtableService iPxliushuizhangtableService;

    @Autowired
    IPxchongzhitableService iPxchongzhitableService;

    @Autowired
    IPxkeshizengsongtableService iPxkeshizengsongtableService;

    @Autowired
    IPxdaijinquantableService iPxdaijinquantableService;

    @Autowired
    IPxautoxiaoketableService iPxautoxiaoketableService;

    @Autowired
    IPxoldschooltableService iPxoldschooltableService;

    @Autowired
    IPxoldschoolteachertableService iPxoldschoolteachertableService;

    @Autowired
    IPxstuparamvaluetableService iPxstuparamvaluetableService;

    @Autowired
    IPxkechengtableService iPxkechengtableService;

    @Autowired
    IPxcampustableService iPxcampustableService;

    @Autowired
    IPxstuzxqrecordtableService iPxstuzxqrecordtableService;

    @Autowired
    IPxtingkeService iPxtingkeService;

    @Autowired
    IPxjiekeService iPxjiekeService;

    @Autowired
    IPxjiekevalueService iPxjiekevalueService;

    @Autowired
    IPxstafftableService iPxstafftableService;

    @Autowired
    IPxstugradetableService iPxstugradetableService;

    @Autowired
    IPxsubjecttableService iPxsubjecttableService;

    @Autowired
    IPxpaymoneystyletableService iPxpaymoneystyletableService;

    @Autowired
    IPxyxtelfromtableService iPxyxtelfromtableService;

    @Autowired
    IPxclasstimestyletableService iPxclasstimestyletableService;

    @Autowired
    IPxqiandanstafftableService iPxqiandanstafftableService;

    @Autowired
    IPxqiandanpaymoneyService iPxqiandanpaymoneyService;

    @Autowired
    IPxstuparamtypetableService iPxstuparamtypetableService;

    @Autowired
    IPxdropdownoptionstableService iPxdropdownoptionstableService;

    @Autowired
    IPxpowertableService iPxpowertableService;

    @Autowired
    IPxqiandaoqiantuitableService iPxqiandaoqiantuitableService;

    @Autowired
    IPxstukxqtableService iPxstukxqtableService;

    @Autowired
    savePxLog savePxLog;

    //endregion

    //region 学员档案


    /**
     * @Description: LisIfindAll方法作用:获取所有学生
     * @param:[]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:22
     */
    @ApiOperation(value = "获取所有正常学生")
    @ResponseBody
    @RequestMapping(value = "getallstu", method = RequestMethod.GET)
    public AjaxJson getallstu(int menuID, HttpServletRequest request) {
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
        ajaxJson.setObj(iPxstutableService.getallstu(queryWrapper));
        return ajaxJson;
    }


    /**
     * @Description: Getnianji()方法作用:获取所有年级
     * @param:[request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/3/3 10:00
     */
    @ApiOperation(value = "获取所有年级")
    @ResponseBody
    @RequestMapping(value = "Getnianji", method = RequestMethod.GET)
    public AjaxJson Getnianji(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        ajaxJson.setObj(iPxstutableService.Getnianji(qiyeID));
        return ajaxJson;
    }

    @ApiOperation(value = "获取学员自定义字段")
    @ResponseBody
    @RequestMapping(value = "Getstuparamtype", method = RequestMethod.GET)
    public AjaxJson Getstuparamtype(String stuID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        ajaxJson.setObj(iPxstuparamtypetableService.getByqiye(Long.valueOf(stuID), qiyeID));
        return ajaxJson;
    }


    @ApiOperation(value = "获取下拉框数据")
    @ResponseBody
    @RequestMapping(value = "Getselectparamvalue", method = RequestMethod.GET)
    public AjaxJson Getselectparamvalue(String stuParamTypeId, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        ajaxJson.setObj(iPxdropdownoptionstableService.getparamTypeList(Long.valueOf(stuParamTypeId), qiyeID));
        return ajaxJson;
    }


    /**
     * @Description: getStuFilesPage方法作用:分页获取学员档案
     * @param:[current, size, stuID, campusID, stuName, parentTel, banzhuren, stuGradeID, buxiStateID]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:22
     */
    @ApiOperation(value = "分页获取学员档案")
    @RequestMapping(value = "getStuFilesPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "stuName", value = "学员名称", required = false),
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = false),
            @ApiImplicitParam(name = "parentTel", value = "学员家长电话", required = false),
            @ApiImplicitParam(name = "banzhuren", value = "班主任", required = false),
            @ApiImplicitParam(name = "stuGradeID", value = "年级ID", required = false),
            @ApiImplicitParam(name = "buxiStateID", value = "学员状态", required = false),
    })
    public AjaxJson getStuFilesPage(HttpServletRequest request, Long current,
                                    Long size,
                                    Long stuID,
                                    Long campusID,
                                    String stuName,
                                    String parentTel,
                                    String banzhuren,
                                    Long stuGradeID,
                                    Long buxiStateID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<stuCampusVo> page = new Page(current, size);
        QueryWrapper<stuCampusVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (stuID != -1) {
            queryWrapper
                    .and(a -> a.isNotNull("a.zidingyiStuID").like("a.zidingyiStuID", stuID))
                    .or(b -> b.isNull("a.zidingyiStuID").eq("a.id", stuID));
        }
        if (campusID != -1) {
            queryWrapper.eq("a.campusID", campusID);
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("a.stuName", stuName);
        }
        if (StringUtils.isNotBlank(parentTel)) {
            queryWrapper.likeRight("a.parentTel", parentTel);
        }
        if (StringUtils.isNotBlank(banzhuren)) {
            queryWrapper.like("d.staffName", banzhuren);
        }
        if (stuGradeID != -1) {
            queryWrapper.eq("a.stuGradeID", stuGradeID);
        }
        if (buxiStateID == -1) {
            queryWrapper
                    .ne("a.buxiStateID", 1)
                    .ne("a.buxiStateID", 7);
            //去掉意向学员与新签待审批学员
        } else {
            queryWrapper.eq("a.buxiStateID", buxiStateID);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 211);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }
        queryWrapper.orderByDesc("a.id");
        ajaxJson.setObj(iPxstutableService.getStuCampusList(page, queryWrapper));
        return ajaxJson;
    }

    //region 学语言档案按钮集

    /**
     * 获取学员补习状态
     *
     * @param type
     * @return
     */
    public String getstubuxiState(int type) {
        String stustate = "";
        if (type == 1) {
            stustate = "意向";
        } else if (type == 2) {
            stustate = "在读";
        } else if (type == 3) {
            stustate = "停课";
        } else if (type == 4) {
            stustate = "结课";
        } else if (type == 5) {
            stustate = "退费";
        } else if (type == 6) {
            stustate = "休眠";
        }
        return stustate;
    }

    /**
     * @Description: UpdateStu方法作用:修改学员信息
     * @param:[pxstutable, shuxingValue]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:22
     */
    @RequestMapping(value = "UpdateStu", method = RequestMethod.POST)
    @ApiOperation(value = "修改学员信息")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson UpdateStu(
            @RequestBody stuValueFrom from,
            HttpServletRequest request
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();

        Long stuID = Long.valueOf(from.getStuID());
        String zidingyiStuID = from.getZidingyiStuID();
        String stuName = from.getStuName();
        String parentTel = from.getParentTel();
        Long campusID = Long.valueOf(from.getCampusID());
        String stuSex = from.getStuSex();
        String parentTelRelation = from.getParentTelRelation();
        Long stuGradeID = Long.valueOf(from.getStuGradeID());
        String stubirth = from.getStubirth();
        String stuTel = from.getStuTel();
        String oldSchool = from.getOldSchool();
        String oldSchoolTeacher = from.getOldSchoolTeacher();
        Long dengjiTeacherID = Long.valueOf(from.getDengjiTeacherID());
        String stuXuexi = from.getStuXuexi();
        String oldTeacherTel = from.getOldTeacherTel();

        Pxstutable stu = iPxstutableService.getById(stuID);
        Pxcampustable oldcam = iPxcampustableService.getById(stu.getCampusID());
        Pxstugradetable oldgrade = iPxstugradetableService.getById(stu.getStuGradeID());

        String logtext =
                "【修改学员信息】，学号：" + stuID + ",姓名：" + stu.getStuName() + ",修改前,校区：" + oldcam.getCampusName() + ",年级：" + oldgrade.getStugradename() + ",家长联系电话：" + stu.getParentTel() + ",学员状态:" + getstubuxiState(stu.getBuxiStateID()) + "。";
        String oldStuName = stu.getStuName();

        Date BirthDay = null;
        try {
            BirthDay = sdf.parse(stubirth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        stu.setZidingyiStuID(zidingyiStuID);
        stu.setStuName(stuName);
        stu.setParentTel(parentTel);
        stu.setCampusID(campusID);
        stu.setStuSex(stuSex);
        stu.setParentTelRelation(parentTelRelation);
        stu.setStuGradeID(stuGradeID);
        stu.setStubirth(BirthDay);
        stu.setStuTel(stuTel);
        stu.setStuXuexi(stuXuexi);
//        stu.setStubirth(banzhurenTeacherID);

        stu.setDengjiTeacherID(dengjiTeacherID);
        stu.setDengjiTime(new Date());

        if (!oldStuName.equals(stuName)) { //学员修改名字是修改一对一班级名称
            QueryWrapper<Pxbuxikechengtable> stuclByono = new QueryWrapper();
            stuclByono
                    .eq("stuID", stuID)
                    .eq("qiyeID", qiyeID);
            List<Pxbuxikechengtable> selectbxkc = iPxbuxikechengtableService.selectbxkc(stuclByono);
            if (selectbxkc.size() > 0) {
                for (Pxbuxikechengtable item : selectbxkc) {
                    QueryWrapper<Pxstuclasstable> stucl = new QueryWrapper<>();
                    stucl
                            .eq("buxiID", item.getId())
                            .eq("qiyeID", qiyeID);
                    List<Pxstuclasstable> selectstuclass = iPxstuclasstableService.selectstuclass(stucl);
                    if (selectstuclass.size() > 0) {
                        for (Pxstuclasstable itema : selectstuclass) {
                            Pxclasstable stuclass = iPxclasstableService.getById(itema.getClassID());
                            if (stuclass.getIs1v1Class() == 1) {
                                iPxclasstableService.updatestuOyOclass(oldStuName, stuName, Long.valueOf(qiyeID));
                            }
                        }
                    }
                }
            }
        }


        if (StringUtils.isNotEmpty(oldSchool)) {
            //按照学校名称找记录
            List<Pxoldschooltable> school = iPxoldschooltableService.getoldschool(oldSchool, Long.valueOf(qiyeID));
            //如果存在原就读学院
            if (school.size() > 0) {
                stu.setOldSchool(school.get(0).getOldSchoolID());

                if (StringUtils.isNotEmpty(oldSchool)) {
                    //获取单前原就读老师
                    List<Pxoldschoolteachertable> teacher = iPxoldschoolteachertableService.getoldTeacher(oldSchoolTeacher, school.get(0).getOldSchoolID(),
                            Long.valueOf(qiyeID));
                    if (teacher.size() > 0) {
                        stu.setOldSchoolTeacher(teacher.get(0).getOldSchoolTeacherID());
                    } else {
                        Pxoldschoolteachertable tea = new Pxoldschoolteachertable();
                        tea.setOldSchoolID(school.get(0).getOldSchoolID());
                        tea.setOldSchoolTeacherName(oldSchoolTeacher);
                        tea.setOldSchoolTeacherTel(oldTeacherTel);
                        tea.setQiyeID(qiyeID);
                        iPxoldschoolteachertableService.save(tea);
                        stu.setOldSchoolTeacher(tea.getOldSchoolTeacherID());
                    }
                }
                iPxoldschooltableService.updateById(school.get(0));
            } else {

                //添加原学校
                Pxoldschooltable sch = new Pxoldschooltable();
                sch.setOldSchoolName(oldSchool);
                sch.setQiyeID(qiyeID);
                iPxoldschooltableService.save(sch);
                stu.setOldSchool(sch.getOldSchoolID());
//                schid = sch.getOldSchoolID();

                if (StringUtils.isNotEmpty(oldSchoolTeacher)) {
                    //添加原学校老师
                    Pxoldschoolteachertable tea = new Pxoldschoolteachertable();
                    tea.setOldSchoolID(sch.getOldSchoolID());
                    tea.setOldSchoolTeacherName(oldSchoolTeacher);
                    tea.setOldSchoolTeacherTel(oldTeacherTel);
                    tea.setQiyeID(qiyeID);
                    iPxoldschoolteachertableService.save(tea);
                    stu.setOldSchoolTeacher(tea.getOldSchoolTeacherID());
                }
            }
        }

        //学生自定义属性修改
        String alldata = from.getAlldata();
        if (alldata != null) {
            List<pramaTypeVo> pramaTypeVos = JSON.parseArray(alldata, pramaTypeVo.class);
            for (pramaTypeVo items : pramaTypeVos) {
                List<Pxstuparamvaluetable> pdPar = iPxstuparamvaluetableService.getstuParam(Long.valueOf(items.getId()), stuID, Long.valueOf(qiyeID));
                if (pdPar.size() == 0) {
                    if (items.getPvalue() != null) {
                        Pxstuparamvaluetable stuPartab = new Pxstuparamvaluetable();
                        stuPartab.setStuID(stuID);
                        stuPartab.setStuParamTypeID(Long.valueOf(items.getId()));
                        stuPartab.setParamValue(items.getPvalue());
                        stuPartab.setQiyeID(qiyeID);
                        iPxstuparamvaluetableService.save(stuPartab);
                    }
                } else {
                    pdPar.get(0).setStuParamTypeID(Long.valueOf(items.getId()));
                    pdPar.get(0).setParamValue(items.getPvalue());
                    iPxstuparamvaluetableService.updateById(pdPar.get(0));
                }
            }
        }


        ajaxJson.setSuccess(iPxstutableService.updateById(stu));
        Pxcampustable nowcam = iPxcampustableService.getById(campusID);
        Pxstugradetable nowgrade = iPxstugradetableService.getById(stuGradeID);
        logtext += "；修改后，姓名：" + stu.getStuName() + ",校区：" + nowcam.getCampusName() + ",年级：" + nowgrade.getStugradename() + ",家长电话：" + stu.getParentTel() + "," +
                "学员状态:" + getstubuxiState(stu.getBuxiStateID()) + "。";

        savePxLog.savepxlog(logtext, "xwcloud-stu/stu/stuManagement/UpdateStu", loginUser.getStaffID(),
                loginUser.getStaffName(), 1,
                loginUser.getQiyeID());
        return ajaxJson;
    }


    /**
     * @Description: delFeedback方法作用:删除学员(批量)
     * @param:[IDs]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:23
     */
    @RequestMapping(value = "delStu", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除学员")
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delStu(String ids,HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());

        String logtext = "删除学员档案，被删除学员为：";

        for (String item : ids.split(",")) {

            Pxstutable stu = iPxstutableService.getById(item);
            logtext += stu.getStuName() + ",";

            //有课时转送记录的话，不允许删除，不论是收到转送还是转送给别人；
            List<Pxkeshizhuansongtable> ishavezhuansong = iPxkeshizhuansongtableService.getksZhuangSong(qiyeID,Long.valueOf(item));
            if (ishavezhuansong.size() > 0) {
                ajaxJson.setMsg("该学员有课时转送记录，先删除转送记录以后才可以删除学员!");
                ajaxJson.setCode("N");
                return ajaxJson;
            }

            //删除学员课时
            List<Pxkeshistutable> allkeshi = iPxkeshistutableService.getkeshistu(Long.valueOf(item), qiyeID);
            if (allkeshi.size() > 0) {
                for (Pxkeshistutable oneks : allkeshi) {
                    Long classID = oneks.getClassID();
                    Date haveClassDate = oneks.getHaveClassDate();
                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");

                    Time startLessonDateTime = oneks.getStartLessonDateTime();
                    Time endLessonDateTime = oneks.getEndLessonDateTime();

                    //Time startLessonDateTime = sdf.parse(oneks.getStartLessonDateTime().toString());

//                    try {
//
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }

                    Long stuID = oneks.getStuID();

                    List<Pxkeshistutable> otherStuks = iPxkeshistutableService.otherStuks(classID, haveClassDate, startLessonDateTime, endLessonDateTime,
                            stuID, qiyeID);
                    if (otherStuks.size() == 0) {
                        List<Pxkeshiteachertable> teachks = iPxkeshiteachertableService.Teachks(classID, haveClassDate, startLessonDateTime,
                                endLessonDateTime, qiyeID);
                        if (teachks.size() > 0) {
                            for (Pxkeshiteachertable tkeshi : teachks) {
                                iPxkeshiteachertableService.removeById(tkeshi.getId());
                            }
                        }
                        List<Pxkeshistuteachertable> ksStuTeachs = iPxkeshistuteachertableService.getksStuTeachs(oneks.getId(), qiyeID);
                        if (ksStuTeachs.size() > 0) {
                            for (Pxkeshistuteachertable ksStuTeachID : ksStuTeachs) {
                                iPxkeshistuteachertableService.removeById(ksStuTeachID.getId());
                            }
                        }
                    }
                    iPxkeshistutableService.removeById(oneks.getId());
                }
            }

            //删除考勤信息
            List<Pxstukaoqingtable> allStukaoqing = iPxstukaoqingtableService.allStukaoqing(Long.valueOf(item), qiyeID);
            if (allStukaoqing.size() > 0) {
                for (Pxstukaoqingtable onekq : allStukaoqing) {
                    List<Pxstukaoqingteachertable> kqTeach = iPxstukaoqingteachertableService.getkqTeach(onekq.getId(), qiyeID);
                    if (kqTeach.size() > 0) {
                        for (Pxstukaoqingteachertable stukqTeah : kqTeach) {
                            iPxstukaoqingteachertableService.removeById(stukqTeah.getId());
                        }
                    }
                    iPxstukaoqingtableService.removeById(onekq.getId());
                }
            }

            //删除学员课程、班级
            List<Pxbuxikechengtable> allStubxkc = iPxbuxikechengtableService.getbuxikc(Long.valueOf(item), qiyeID);
            if (allStubxkc.size() > 0) {
                for (Pxbuxikechengtable onebxkc : allStubxkc) {
                    List<Pxkechengtable> onekc = iPxbuxikechengtableService.getKc(onebxkc.getKechengID(), qiyeID);
                    Pxbuxistyletable buxisty = iPxbuxistyletableService.getById(onekc.get(0).getBuxiStyleID());
                    String buxiStyleName = buxisty.getBuxiStyleName();//补习方式
                    Integer is1v1 = buxisty.getIs1v1();//是否是一对一补习方式
                    List<Pxstuclasstable> stuclas = iPxstuclasstableService.getBybxID(onebxkc.getId(), qiyeID);
                    if (stuclas.size() > 0) {
                        for (Pxstuclasstable oneStucla : stuclas) {
                            Pxclasstable cla = iPxclasstableService.getById(oneStucla.getClassID());
                            if (cla != null) {
                                if (buxiStyleName == "一对一" && is1v1 == 1) {
                                    List<Pxpaiketable> claPaikes = iPxpaiketableService.getpkBYClassID(oneStucla.getClassID(), qiyeID);
                                    if (claPaikes.size() > 0) {
                                        for (Pxpaiketable onepk : claPaikes) {
                                            //删除教师排课
                                            paiketeacherVo clapaikeTeach = iPxpaiketeachertableService.getTkTpaike(onepk.getId(), qiyeID);
                                            if (clapaikeTeach != null) {
                                                String[] pkTeacherIDs = clapaikeTeach.getIDTS().split(",");
                                                iPxpaiketeachertableService.removeByIds(Arrays.asList(pkTeacherIDs));
                                            }
                                            //删除选课
                                            List<Pxxuanketable> allxuanke = iPxxuanketableService.allxuankebypkID(onepk.getId(), qiyeID);
                                            List<Pxxuanketable> xuankeTab = iPxxuanketableService.xuankebypkstu(onepk.getId(), Long.valueOf(item),
                                                    qiyeID);
                                            //考勤状态判断
                                            countykqVo ykqCount = iPxstukaoqingtableService.ykqCount(onepk.getHaveClassDate(), onepk.getStartLessonDateTime()
                                                    , onepk.getEndLessonDateTime(), onepk.getClassID(), onepk.getTeacherIDs(), qiyeID);
                                            if (ykqCount.getYkqCount() != 0 && ykqCount.getYkqCount() >= allxuanke.size()) {
                                                onepk.setDakaoqin(true);
                                                iPxpaiketableService.updateById(onepk);
                                            }
                                            if (xuankeTab.size() > 0) {
                                                for (Pxxuanketable dxk : xuankeTab) {
                                                    iPxxuanketableService.removeById(dxk.getId());
                                                }
                                            }
                                            iPxpaiketableService.removeById(onepk.getId());
                                        }
                                    }
                                    iPxclasstableService.removeById(cla.getId());
                                } else {
                                    //如果有排课信息，删选课信息，判断排课考勤状态；
                                    List<Pxpaiketable> claPaikes = iPxpaiketableService.getpkBYClassID(oneStucla.getClassID(), qiyeID);
                                    if (claPaikes.size() > 0) {
                                        for (Pxpaiketable onepk : claPaikes) {
                                            List<Pxxuanketable> allxuanke = iPxxuanketableService.allxuankebypkID(onepk.getId(), qiyeID);
                                            List<Pxxuanketable> xuankeTab = iPxxuanketableService.xuankebypkstu(onepk.getId(), Long.valueOf(item),
                                                    qiyeID);
                                            countykqVo ykqCount = iPxstukaoqingtableService.ykqCount(onepk.getHaveClassDate(), onepk.getStartLessonDateTime()
                                                    , onepk.getEndLessonDateTime(), onepk.getClassID(), onepk.getTeacherIDs(), qiyeID);
                                            if (ykqCount.getYkqCount() != 0 && ykqCount.getYkqCount() >= allxuanke.size() - 1) {
                                                onepk.setDakaoqin(true);
                                                iPxpaiketableService.updateById(onepk);
                                            }
                                            if (xuankeTab.size() > 0) {
                                                for (Pxxuanketable xk : xuankeTab) {
                                                    iPxxuanketableService.removeById(xk.getId());
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            //学员退班
                            iPxstuclasstableService.removeById(oneStucla.getId());
                        }
                    }
                    //删除自动消课
                    if (onebxkc.getJifeiStyleID() == 3) {
                        List<Pxautoxiaoketable> autoxk = iPxautoxiaoketableService.getbyauto(onebxkc.getId(), qiyeID);
                        if (autoxk.size() > 0) {
                            for (Pxautoxiaoketable autox : autoxk) {
                                iPxautoxiaoketableService.removeById(autox.getId());
                            }
                        }
                    }
                    //删除培训课程
                    iPxbuxikechengtableService.removeById(onebxkc.getId());
                }
            }

            //删除签单科目qianDanSubjectTable
            List<Pxqiandansubjecttable> stuqianDanSubs = iPxqiandansubjecttableService.getqdSubject(Long.valueOf(item), qiyeID);
            if (stuqianDanSubs.size() > 0) {
                iPxqiandansubjecttableService.remove(
                        new QueryWrapper<Pxqiandansubjecttable>()
                                .eq("stuID", item)
                                .eq("qiyeID", qiyeID));
            }

            //删除签单信息qianDanInfoTable
            List<Pxqiandaninfotable> stuqianInfos = iPxqiandaninfotableService.getQD(Long.valueOf(item), qiyeID);
            if (stuqianInfos.size() > 0) {
                for (Pxqiandaninfotable qd : stuqianInfos) {
                    List<Pxqiandaninfo2table> qd2 = iPxqiandaninfo2tableService.getqd2(qd.getId(), qiyeID);
                    if (qd2.size() > 0) {
                        iPxqiandaninfo2tableService.remove(
                                new QueryWrapper<Pxqiandaninfo2table>()
                                        .eq("stuID", item)
                                        .eq("qiyeID", qiyeID));
                    }
                    iPxqiandaninfotableService.removeById(qd.getId());
                }

            }

            //删除财务流水
            List<Pxliushuizhangtable> stuLiushuis = iPxliushuizhangtableService.getstuliushui(Long.valueOf(item), qiyeID);
            if (stuLiushuis.size() > 0) {
                iPxliushuizhangtableService.remove(
                        new QueryWrapper<Pxliushuizhangtable>()
                                .eq("stuID", item)
                                .eq("qiyeID", qiyeID));
            }

            //删除余额记录（充值记录）
            List<Pxchongzhitable> getstucz = iPxchongzhitableService.getstucz(Long.valueOf( item), qiyeID);
            if (getstucz.size() > 0) {
                iPxchongzhitableService.remove(
                        new QueryWrapper<Pxchongzhitable>()
                                .eq("stuID", item)
                                .eq("qiyeID", qiyeID));
            }

            //删除课时赠送
            List<Pxkeshizengsongtable> stuKsZengsong = iPxkeshizengsongtableService.getksZs(Long.valueOf(item), qiyeID);
            if (stuKsZengsong.size() > 0) {
                iPxkeshizengsongtableService.remove(
                        new QueryWrapper<Pxkeshizengsongtable>()
                                .eq("stuID", item)
                                .eq("qiyeID", qiyeID));
            }

            //删除积分
            List<Pxjifentable> stujifen = iPxjifentableService.getJF(Long.valueOf(item), qiyeID);
            if (stujifen.size() > 0) {
                iPxjifentableService.remove(
                        new QueryWrapper<Pxjifentable>()
                                .eq("stuID", item)
                                .eq("qiyeID", qiyeID));
            }

            //删除代金券
            List<Pxdaijinquantable> studjq = iPxdaijinquantableService.getstudjq(Long.valueOf(item), qiyeID);
            if (studjq.size() > 0) {
                iPxdaijinquantableService.remove(
                        new QueryWrapper<Pxdaijinquantable>()
                                .eq("stuID", item)
                                .eq("qiyeID", qiyeID));
            }

            //删除学员卡
            List<Pxstucardtable> stuCard = iPxstucardtableService.addUpdateCard(Long.valueOf(item), qiyeID);
            if (stuCard.size() > 0) {
                iPxstucardtableService.remove(
                        new QueryWrapper<Pxstucardtable>()
                                .eq("stuID", item)
                                .eq("qiyeID", qiyeID));
            }

            //删除学员的自定义字段
            QueryWrapper<Pxstuparamvaluetable> stupQ = new QueryWrapper<>();
            stupQ
                    .eq("stuID", item)
                    .eq("qiyeID", qiyeID);
            List<Pxstuparamvaluetable> stuparam = iPxstuparamvaluetableService.list(stupQ);
            if (stuparam.size() > 0) {
                iPxstuparamvaluetableService.remove(stupQ);
            }

            //删除学员的签到签退信息
            QueryWrapper<Pxqiandaoqiantuitable> qdqtQ = new QueryWrapper<>();
            qdqtQ
                    .eq("stuID", item)
                    .eq("qiyeID", qiyeID);
            List<Pxqiandaoqiantuitable> QdQtlist = iPxqiandaoqiantuitableService.list(qdqtQ);
            if (QdQtlist.size() > 0) {
                iPxqiandaoqiantuitableService.remove(qdqtQ);
            }

            //删除学员跨校区信息
            QueryWrapper<Pxstukxqtable> kxqQ = new QueryWrapper<>();
            kxqQ
                    .eq("stuID", item)
                    .eq("qiyeID", qiyeID);
            List<Pxstukxqtable> kxqlist = iPxstukxqtableService.list(kxqQ);
            if (kxqlist.size() > 0) {
                iPxstukxqtableService.remove(kxqQ);
            }
            iPxstutableService.removeById(item);
        }

        savePxLog.savepxlog(logtext, "xwcloud-stu/stu/stuManagement/delStu", loginUser.getStaffID(),
                loginUser.getStaffName(), 1,
                loginUser.getQiyeID());

        return ajaxJson;
    }


    /**
     * @Description: PauseStudy方法作用:停课
     * @param:[stuIDs]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:23
     */
    @ApiOperation("停课")
    @ResponseBody
    @RequestMapping(value = "PauseStudy", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson PauseStudy(@RequestBody delstuFrom from, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Long staffID = loginUser.getStaffID();

        String IDs = from.getIds();
        List<stuIDVo> stuIDVoList = JSON.parseArray(IDs, stuIDVo.class);
        String logtext = "给学员停课，停课学员为：";
        for (stuIDVo item : stuIDVoList) {
            Pxstutable stu = iPxstutableService.getById(item.getId());
            logtext += stu.getStuName() + ",";
            if (stu.getBuxiStateID() != 2) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("学员：" + stu.getStuName() + "不是可以设置停课的学员状态");
                return ajaxJson;
            }
            List<Pxbuxikechengtable> buxikc = iPxbuxikechengtableService.getbuxikc(stu.getId(), qiyeID);
            if (buxikc.size() > 0) {
                for (int j = 0; j < buxikc.size(); j++) {
                    Pxbuxikechengtable itema = buxikc.get(j);
                    List<Pxkechengtable> kc = iPxbuxikechengtableService.getKc(itema.getKechengID(), qiyeID); //课程
                    Pxbuxistyletable buxi = iPxbuxistyletableService.getById(kc.get(0).getSubjectID());//获取补习方式表有关记录
                    String buxiStyleName = buxi.getBuxiStyleName();//补习方式
                    Integer is1v1 = buxi.getIs1v1();//是否是一对一补习方式

                    List<Pxstuclasstable> stucla = iPxstuclasstableService.getBybxID(itema.getId(), qiyeID);//学员班级
                    List<Pxxuanketable> xuanke = iPxxuanketableService.getBybxID(itema.getId(), qiyeID);//获取选课

                    if (buxiStyleName == "一对一" && is1v1 == 1) {
                        //一对一班级隐藏、把现在以后的排课全部删除
                        if (stucla.size() > 0) {
                            for (int k = 0; k < stucla.size(); k++) {
                                Pxstuclasstable itemb = stucla.get(k);
                                Pxclasstable cla = iPxclasstableService.getById(itemb.getId());
                                if (cla != null) {
                                    cla.setIsShow(0);//隐藏一对一班级
                                    iPxclasstableService.updateById(cla);
                                }
                                //删除现在之后的排课
                                List<Pxpaiketable> paike = iPxpaiketableService.getTk(itemb.getClassID(), new Date(), qiyeID);
                                if (paike.size() > 0) {
                                    for (Pxpaiketable pk : paike) {
                                        paiketeacherVo paikeTeach = iPxpaiketeachertableService.getTkTpaike(pk.getId(), qiyeID);
                                        if (paikeTeach != null) {
                                            String[] pkTeacherIDs = paikeTeach.getIDTS().split(",");
                                            iPxpaiketeachertableService.removeByIds(Arrays.asList(pkTeacherIDs));
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (stucla.size() > 0) {
                            for (Pxstuclasstable stuclass : stucla) {
                                //删除学员班级
                                iPxstuclasstableService.removeById(stuclass.getId());
                            }

                            if (xuanke != null) {
                                for (Pxxuanketable xks : xuanke) {
                                    //删除选课
                                    iPxxuanketableService.removeById(xks.getId());
                                }
                            }
                        }
                    }
                }
            }

            Pxtingke tk = new Pxtingke();
            tk.setType(1);
            tk.setStuID(item.getId());
            tk.setTingkeUser(staffID);
            tk.setTingkeTime(new Date());
            tk.setTingkeshuoming(from.getMassage());
            tk.setQiyeID(qiyeID);
            iPxtingkeService.save(tk);

            //学员表修改学员状态
            stu.setBuxiStateID(3);
            stu.setTingkeTime(new Date());
            ajaxJson.setSuccess(iPxstutableService.updateById(stu));
        }

        savePxLog.savepxlog(logtext, "xwcloud-stu/stu/stuManagement/PauseStudy", loginUser.getStaffID(),
                loginUser.getStaffName(), 1,
                loginUser.getQiyeID());
        return ajaxJson;
    }


    /**
     * @Description: longPauseStudy方法作用:休眠
     * @param:[stuIDs]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:23
     */
    @ApiOperation("休眠")
    @ResponseBody
    @RequestMapping(value = "longPauseStudy", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson longPauseStudy(@RequestBody delstuFrom from, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long staffID = loginUser.getStaffID();
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());

        String IDs = from.getIds();
        List<stuIDVo> stuIDVoList = JSON.parseArray(IDs, stuIDVo.class);
        String logtext = "给学员休眠，休眠学员为：";
        for (stuIDVo item : stuIDVoList) {
            Pxstutable stu = iPxstutableService.getById(item.getId());
            logtext += stu.getStuName() + ",";
            if (stu.getBuxiStateID() != 2) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("学员：" + stu.getStuName() + "不是可以设置休眠的学员状态");
                return ajaxJson;
            }
            if (stu.getBuxiStateID() == 2) {
                List<Pxbuxikechengtable> buxikc = iPxbuxikechengtableService.getbuxikc(stu.getId(), qiyeID);
                if (buxikc.size() > 0) {
                    for (int j = 0; j < buxikc.size(); j++) {
                        Pxbuxikechengtable itema = buxikc.get(j);//补习
                        List<Pxkechengtable> kc = iPxbuxikechengtableService.getKc(itema.getKechengID(), qiyeID);//课程
                        Pxbuxistyletable buxi = iPxbuxistyletableService.getById(kc.get(0).getBuxiStyleID());
                        String buxiStyleName = buxi.getBuxiStyleName();//补习方式
                        Integer is1v1 = buxi.getIs1v1();//是否是一对一
                        List<Pxstuclasstable> stucla = iPxstuclasstableService.getBybxID(itema.getId(), qiyeID);
                        List<Pxxuanketable> nokehaoStu = iPxxuanketableService.getNokehaoStu(itema.getId(), qiyeID);

                        if (buxiStyleName == "一对一" && is1v1 == 1) {
                            //一对一班级隐藏、把现在以后的排课全部删除
                            if (stucla.size() > 0) {
                                for (int n = 0; n < stucla.size(); n++) {
                                    Pxstuclasstable itemb = stucla.get(n);
                                    Pxclasstable cla = iPxclasstableService.getById(itemb.getId());
                                    if (cla != null) {
                                        cla.setIsShow(0);
                                        iPxclasstableService.updateById(cla);
                                    }
                                    List<Pxpaiketable> paike = iPxpaiketableService.getTk(itemb.getClassID(), new Date(), qiyeID);//获取排课
                                    if (paike.size() > 0) {
                                        for (int m = 0; m < paike.size(); m++) {
                                            Pxpaiketable itemt = paike.get(m);
                                            paiketeacherVo paikeTeach = iPxpaiketeachertableService.getTkTpaike(itemt.getId(), qiyeID);
                                            if (paikeTeach != null) {
                                                String[] pkTeacherIDs = paikeTeach.getIDTS().split(",");
                                                iPxpaiketeachertableService.removeByIds(Arrays.asList(pkTeacherIDs));//删除教师排课
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            if (stucla.size() > 0) {
                                for (Pxstuclasstable itemsl : stucla) {
                                    iPxstuclasstableService.removeById(itemsl.getId());//删除学员班级
                                }
                            }

                            //非一对一退出排课
                            if (nokehaoStu.size() > 0) {
                                for (Pxxuanketable itemx : nokehaoStu) {
                                    iPxxuanketableService.removeById(itemx);
                                }
                            }
                        }
                    }
                }
            }

            Pxtingke xm = new Pxtingke();
            xm.setType(2);
            xm.setStuID(item.getId());
            xm.setTingkeUser(staffID);
            xm.setTingkeTime(new Date());
            xm.setTingkeshuoming(from.getMassage());
            xm.setQiyeID(qiyeID);
            iPxtingkeService.save(xm);

            stu.setBuxiStateID(6);
            ajaxJson.setSuccess(iPxstutableService.updateById(stu));
        }

        savePxLog.savepxlog(logtext, "xwcloud-stu/stu/stuManagement/longPauseStudy", loginUser.getStaffID(),
                loginUser.getStaffName(), 1,
                loginUser.getQiyeID());
        return ajaxJson;
    }


    /**
     * @Description: stuJieke方法作用:结课
     * @param:[stuIDs]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:23
     */
    @ApiOperation("结课")
    @ResponseBody
    @RequestMapping(value = "stuJieke", method = RequestMethod.POST)
    @ApiImplicitParams(
            @ApiImplicitParam(name = "stuIDs", value = "学员IDs", required = true)
    )
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson stuJieke(@RequestBody delstuFrom from, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Long staffID = Long.valueOf(loginUser.getStaffID());
        BigDecimal n1 = new BigDecimal("0");
        String IDs = from.getIds();
        List<stuIDVo> stuIDVoList = JSON.parseArray(IDs, stuIDVo.class);
        String logtext = "给学员结课，结课学员为：";
        for (stuIDVo item : stuIDVoList) {
            Pxstutable stu = iPxstutableService.getById(item.getId());
            logtext += stu.getStuName() + ",";
            List<Pxbuxikechengtable> buxikc = iPxbuxikechengtableService.getbuxikc(stu.getId(), qiyeID);

            //添加结课记录
            Pxjieke jk = new Pxjieke();
            jk.setStuID(item.getId());
            jk.setJkaddUser(staffID);
            jk.setJiekeTime(new Date());
            jk.setJkshuoming(from.getMassage());
            jk.setJiekeremainxf(stu.getRemainXuefei());
            jk.setJiekeyue(stu.getRemainChongzhi());
            jk.setQiyeID(qiyeID);
            iPxjiekeService.save(jk);

            if (buxikc.size() > 0) {
                for (Pxbuxikechengtable itema : buxikc) {
                    //有几条补习课程添加几条记录(结课属性表)
                    Pxjiekevalue jkva = new Pxjiekevalue();
                    jkva.setJiekeID(jk.getId());
                    jkva.setStuID(item.getId());
                    jkva.setBuxiID(itema.getId());
                    jkva.setRemainkeshi(itema.getRemainkeshi());
                    jkva.setKechengID(itema.getKechengID());
                    jkva.setKechengPrice(itema.getKechengprice());
                    jkva.setAddTime(new Date());
                    jkva.setAddUser(staffID);
                    jkva.setQiyeID(qiyeID);
                    iPxjiekevalueService.save(jkva);

                    List<Pxkechengtable> kc = iPxbuxikechengtableService.getKc(itema.getKechengID(), qiyeID);//课程
                    Pxbuxistyletable buxi = iPxbuxistyletableService.getById(kc.get(0).getBuxiStyleID());
                    String buxiStyleName = buxi.getBuxiStyleName();
                    Integer is1v1 = buxi.getIs1v1();
                    List<Pxstuclasstable> stucla = iPxstuclasstableService.getBybxID(itema.getId(), qiyeID);

                    List<Pxxuanketable> nokehaoStu = iPxxuanketableService.getNokehaoStu(itema.getId(), qiyeID);

                    if (buxiStyleName == "一对一" && is1v1 == 1) {
                        //一对一班级隐藏、把现在以后的排课全部删除
                        if (stucla.size() > 0) {
                            for (int n = 0; n < stucla.size(); n++) {
                                Pxstuclasstable itemb = stucla.get(n);
                                Pxclasstable cla = iPxclasstableService.getById(itemb.getId());
                                if (cla != null) {
                                    cla.setIsShow(0);
                                    iPxclasstableService.updateById(cla);
                                }
                                List<Pxpaiketable> paike = iPxpaiketableService.getTk(itemb.getClassID(), new Date(), qiyeID);//获取排课
                                if (paike.size() > 0) {
                                    for (int m = 0; m < paike.size(); m++) {
                                        Pxpaiketable itemt = paike.get(m);
                                        paiketeacherVo paikeTeach = iPxpaiketeachertableService.getTkTpaike(itemt.getId(), qiyeID);
                                        if (paikeTeach != null) {
                                            String[] pkTeacherIDs = paikeTeach.getIDTS().split(",");
                                            iPxpaiketeachertableService.removeByIds(Arrays.asList(pkTeacherIDs));//删除教师排课
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        //非一对一课程退班
                        if (stucla.size() > 0) {
                            for (int k = 0; k < stucla.size(); k++) {
                                Pxstuclasstable itemsl = stucla.get(k);
                                Long slids = itemsl.getId();
                                iPxstuclasstableService.removeById(slids);//删除学员班级
                            }
                        }

                        //非一对一退出排课
                        if (nokehaoStu.size() > 0) {
                            for (Pxxuanketable itemx : nokehaoStu) {
                                iPxxuanketableService.removeById(itemx);
                            }
                        }
                    }

                    itema.setRemainkeshi(BigDecimal.valueOf(0));
                    iPxbuxikechengtableService.updateById(itema);
                }
            }


            //余额
            BigDecimal Money = stu.getRemainChongzhi();
            if (Money.compareTo(BigDecimal.valueOf(0)) == 1) {  //a>b
                Pxchongzhipaytable pxchongzhipaytable = new Pxchongzhipaytable();

                pxchongzhipaytable.setStuID(stu.getId());
                pxchongzhipaytable.setChongzhiPayMoney(Money);
                pxchongzhipaytable.setType(5);//结课删除
                pxchongzhipaytable.setBeizhu("学员结课");//备注
                pxchongzhipaytable.setAddStaffID(staffID);//获取当前登录者
                pxchongzhipaytable.setAddTime(new Date());
                pxchongzhipaytable.setQiyeID(qiyeID);
                iPxchongzhipaytableService.save(pxchongzhipaytable);

                jk.setChongzhipayID(pxchongzhipaytable.getId());
                iPxjiekeService.updateById(jk);
            }

            stu.setBuxiStateID(4);
            stu.setRemainXuefei(n1);
            ajaxJson.setSuccess(iPxstutableService.updateById(stu));
        }

        savePxLog.savepxlog(logtext, "xwcloud-stu/stu/stuManagement/stuJieke", loginUser.getStaffID(),
                loginUser.getStaffName(), 1,
                loginUser.getQiyeID());
        return ajaxJson;
    }


    /**
     * @Description: huifuStudy方法作用:复课
     * @param:[stuIDs]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:24
     */
    @ApiOperation("复课")
    @ResponseBody
    @RequestMapping(value = "huifuStudy", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson huifuStudy(@RequestBody delstuFrom from, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Long staffID = Long.valueOf(loginUser.getStaffID());
        Long campusID = loginUser.getCampusID();

        String IDs = from.getIds();
        List<stuIDVo> stuIDVoList = JSON.parseArray(IDs, stuIDVo.class);
        String logtext = "给学员复课，复课学员为：";
        for (stuIDVo item : stuIDVoList) {
            Pxstutable stu = iPxstutableService.getById(item.getId());
            logtext += stu.getStuName() + ",";

            Integer buxiStateID = stu.getBuxiStateID();
            String sta = getstubuxiState(buxiStateID);
            String xiugaiInfo = "";

            if (buxiStateID == 3 || buxiStateID == 4 || buxiStateID == 6) {
                xiugaiInfo += "学员复课";
                if (buxiStateID == 4) {
                    List<Pxjieke> jiekelist = iPxjiekeService.list(
                            new QueryWrapper<Pxjieke>()
                                    .eq("stuID", item.getId())
                                    .eq("qiyeID", qiyeID)
                                    .orderByDesc("jiekeTime")
                    );
                    if (jiekelist.size() > 0) {
                        Pxjieke pxjieke = jiekelist.get(0);
                        pxjieke
                                .setFukeTime(new Date())
                                .setFkaddUser(loginUser.getStaffID())
                                .setFkshuoming(from.getMassage());
                        iPxjiekeService.updateById(pxjieke);

                        stu
                                .setRemainXuefei(pxjieke.getJiekeremainxf())
                                .setRemainChongzhi(pxjieke.getJiekeyue());
                        iPxstutableService.updateById(stu);

                        if (pxjieke.getChongzhipayID() != null) {
                            iPxchongzhipaytableService.removeById(pxjieke.getChongzhipayID());
                        }

                        List<Pxjiekevalue> jkvaluelist = iPxjiekevalueService.list(
                                new QueryWrapper<Pxjiekevalue>()
                                        .eq("jiekeID", pxjieke.getId())
                                        .eq("qiyeID", qiyeID)
                        );

                        for (Pxjiekevalue jkv : jkvaluelist) {
                            Pxbuxikechengtable buxk = iPxbuxikechengtableService.getById(jkv.getBuxiID());
                            buxk.setRemainkeshi(jkv.getRemainkeshi());
                            iPxbuxikechengtableService.updateById(buxk);
                        }
                    } else {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        ajaxJson.setMsg("未找到：" + stu.getStuName() + "学员的" + sta + "记录");
                        ajaxJson.setCode("N");
                        return ajaxJson;
                    }
                } else {
                    List<Pxtingke> tkList = iPxtingkeService.getstuTk(item.getId(), qiyeID);
                    if (tkList.size() > 0) {
                        Pxtingke tkstu = tkList.get(0);
                        tkstu.setFukeshuoming(from.getMassage());
                        tkstu.setFukeTime(new Date());
                        tkstu.setFukeUser(staffID);
                        iPxtingkeService.updateById(tkstu);
                    } else {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        ajaxJson.setMsg("未找到：" + stu.getStuName() + "学员的" + sta + "记录");
                        ajaxJson.setCode("N");
                        return ajaxJson;
                    }
                }

            } else {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("学员：" + stu.getStuName() + "不是可复课的学员！");
                return ajaxJson;
            }


            List<Pxbuxikechengtable> buxikc = iPxbuxikechengtableService.getbuxikc(stu.getId(), Long.valueOf(qiyeID));
            BigDecimal YE = stu.getRemainChongzhi();
            if (buxikc.size() > 0) {
                for (int j = 0; j < buxikc.size(); j++) {
                    Pxbuxikechengtable itema = buxikc.get(j);
                    List<Pxkechengtable> kc = iPxbuxikechengtableService.getKc(itema.getKechengID(), Long.valueOf(qiyeID));
                    Pxbuxistyletable buxi = iPxbuxistyletableService.getById(kc.get(0).getBuxiStyleID());
                    String buxiStyleName = buxi.getBuxiStyleName();
                    Integer is1v1 = buxi.getIs1v1();
                    List<Pxstuclasstable> stucla = iPxstuclasstableService.getBybxID(itema.getId(), Long.valueOf(qiyeID));
                    if (buxiStyleName == "一对一" && is1v1 == 1) {
                        //一对一班级显示
                        if (stucla.size() > 0) {
                            for (Pxstuclasstable itemb : stucla) {
                                Pxclasstable cla = iPxclasstableService.getById(itemb.getId());
                                if (cla != null) {
                                    cla.setIsShow(1);
                                    iPxclasstableService.updateById(cla);
                                }
                            }
                        } else {
                            Random rd = new Random();
                            int i1 = rd.nextInt(999);
                            String className = stu.getStuName() + "_" + kc.get(0).getKechengName() + "_一对一" + i1;
                            List<Pxclasstable> pdclass = iPxclasstableService.FkClassName(className, qiyeID);
                            if (pdclass.size() == 0) {
                                Pxclasstable pxclasstable = new Pxclasstable();
                                pxclasstable.setClassName(className);
                                pxclasstable.setCampusID(stu.getCampusID());
                                pxclasstable.setIs1v1Class(1);
                                pxclasstable.setIsShow(1);
                                pxclasstable.setAddStaffID(staffID);//获取当前登录人
                                pxclasstable.setAddTime(new Date());
                                pxclasstable.setClassState(0);
                                iPxclasstableService.save(pxclasstable);
                                Long classID = pxclasstable.getId();//获取当前pxclasstable的ID

                                Pxstuclasstable pxstuclasstable = new Pxstuclasstable();
                                pxstuclasstable.setBuxiID(itema.getId());
                                pxstuclasstable.setClassID(classID);
                                pxstuclasstable.setQiyeID(qiyeID);
                                iPxstuclasstableService.save(pxstuclasstable);
                            }
                        }
                    }
                }
            }
            stu.setBuxiStateID(2);
            ajaxJson.setSuccess(iPxstutableService.updateById(stu));
        }

        savePxLog.savepxlog(logtext, "xwcloud-stu/stu/stuManagement/huifuStudy", loginUser.getStaffID(),
                loginUser.getStaffName(), 1,
                loginUser.getQiyeID());

        return ajaxJson;
    }

    /**
     * @Description: getzxqCampus()方法作用:获取学员可以转的校区
     * @param:[stuID, request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/1/28 11:18
     */
    @ApiOperation("获取学员可以转的校区")
    @ResponseBody
    @RequestMapping(value = "getzxqCampus", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true)
    })
    public AjaxJson getzxqCampus(Long stuID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        ajaxJson.setObj(iPxstutableService.getzxqcampusList(stuID, qiyeID));
        return ajaxJson;
    }


    /**
     * @Description: getzxqbuxiList()方法作用:获取学员时补习课程信息
     * @param:[stuID, request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/1/28 11:44
     */
    @ApiOperation("获取学员时补习课程信息")
    @ResponseBody
    @RequestMapping(value = "getzxqbuxiList", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true)
    })
    public AjaxJson getzxqbuxiList(Long stuID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        ajaxJson.setObj(iPxstutableService.getzxqbuxiList(stuID, qiyeID));
        return ajaxJson;
    }

    @ApiOperation("获取跨校区课程")
    @ResponseBody
    @RequestMapping(value = "getallJFkcshuList", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true),
            @ApiImplicitParam(name = "JifeiStyle", value = "计费方式", required = true),
            @ApiImplicitParam(name = "buxiStyleName", value = "补习名称", required = true)
    })
    public AjaxJson getallJFkcshuList(Long campusID, int JifeiStyle, String buxiStyleName, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
//        QueryWrapper<listVo> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("a.qiyeID",qiyeID).eq("c.campusID",campusID).and(a->a.eq("3",JifeiStyle).eq("a.jifeiStyleID",3).or(b->b.ne("3",JifeiStyle).and(c->c
//        .eq("a.jifeiStyleID",1).or(d->d.eq("a.jifeiStyleID",2)))));
        ajaxJson.setObj(iPxstutableService.getallJFkcshuList(buxiStyleName, JifeiStyle, campusID, qiyeID));
        return ajaxJson;
    }

    @ApiOperation("获取跨校区课程")
    @ResponseBody
    @RequestMapping(value = "getxinbxkc", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newkcID", value = "新课程ID", required = true),
    })
    public AjaxJson getxinbxkc(Long newkcID) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxkechengtableService.getById(newkcID));
        return ajaxJson;
    }

    /**
     * @Description: zhuanxiaoqu方法作用:转校区
     * @param:[parmValue, newcampus, stuid]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:24
     */
    @ApiOperation("转校区")
    @RequestMapping(value = "zhuanxiaoqu", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson zhuanxiaoqu(@RequestBody zxqFrom from, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();
        String parmValue = from.getParmValue();
        Long newcampus = from.getNewcampus();
        if (parmValue != null) {
            List<zxqJSVo> zxqJSVos = JSON.parseArray(parmValue, zxqJSVo.class);
            for (zxqJSVo zxqVo : zxqJSVos) {
                Long oldbxID = zxqVo.getBxID();//原补习ID
                Long nowkcID = zxqVo.getNkcID();//新的课程ID

                //老校区补习课程
                Pxbuxikechengtable oldbxTab = iPxbuxikechengtableService.getById(oldbxID);
                //新校区课程(非补习课程)
                Pxkechengtable nowkcTab = iPxkechengtableService.getById(nowkcID);
                Pxbuxistyletable bxstTab = iPxbuxistyletableService.getById(nowkcTab.getBuxiStyleID());

                if (nowkcTab.getJifeiStyleID() == 2) {
                    oldbxTab.setKechengID(nowkcTab.getId());
                    oldbxTab.setKechengprice(nowkcTab.getKechengprice());
                    oldbxTab.setJifeiStyleID(nowkcTab.getJifeiStyleID());
                    oldbxTab.setRemainkeshi(oldbxTab.getKechengprice().multiply(oldbxTab.getRemainkeshi()).divide(nowkcTab.getKechengprice()));
                    //原课程价格*剩余课时  /现课程单价
                } else {
                    oldbxTab.setKechengID(nowkcTab.getId());
                }
                iPxbuxikechengtableService.updateById(oldbxTab);

                if (bxstTab.getBuxiStyleName() == "一对一" && bxstTab.getIs1v1() == 1) {
                    List<Pxstuclasstable> stuClsTab = iPxstuclasstableService.getBybxID(oldbxID, Long.valueOf(qiyeID));
                    Pxclasstable classTab = iPxclasstableService.getById(stuClsTab.get(0).getId());
                    classTab.setCampusID(newcampus);//班级切换校区
                    iPxclasstableService.updateById(classTab);
                } else {
                    List<Pxstuclasstable> stuClsTab = iPxstuclasstableService.getBybxID(oldbxID, Long.valueOf(qiyeID));
                    if (stuClsTab.size() > 0) {
                        for (Pxstuclasstable stuc : stuClsTab) {
                            iPxstuclasstableService.removeById(stuc.getId()); //原校区退班
                        }
                    }
                }
            }
        }
        Pxstutable stu = iPxstutableService.getById(from.getStuid());
        //String oldcampusName = iPxcampustableService.getById(stu.getCampusID()).getCampusName();
        Long oldCampusID = stu.getCampusID();

        stu.setBanzhurenTeacherID(0L);
        stu.setCampusID(newcampus);
        iPxstutableService.updateById(stu);

        //String newcampusName = iPxcampustableService.getById(newcampus).getCampusName();
        Pxstuzxqrecordtable one = new Pxstuzxqrecordtable();
        one.setStuID(Long.valueOf(from.getStuid()));
        one.setOldCampusID(oldCampusID);
        one.setNewCampusID(newcampus);
        one.setKcChangInfo("");
        one.setAddStaffID(staffID);
        one.setAddTime(new Date());
        one.setQiyeID(qiyeID);
        ajaxJson.setSuccess(iPxstuzxqrecordtableService.save(one));
        return ajaxJson;
    }

    @ApiOperation("获取所有开放班级")
    @ResponseBody
    @RequestMapping(value = "getAllClass", method = RequestMethod.GET)
    public AjaxJson getAllClass(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper<listVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.qiyeID", qiyeID);
        ajaxJson.setObj(iPxclasstableService.getAllClass(null));
        return ajaxJson;
    }

    /**
     * @Description: AsClassTOStuPage方法作用:分页获取按班级查询的学员
     * @param:[current, size, classID]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:24
     */
    @ApiOperation("分页获取按班级查询学员")
    @ResponseBody
    @RequestMapping(value = "AsClassTOStuPage", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "classID", value = "班级ID", required = true)
    })
    public AjaxJson AsClassTOStuPage(Long current, Long size, String classID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<AsClassTOStuVo> page = new Page<>(current, size);
        QueryWrapper<AsClassTOStuVo> queryWrapper = new QueryWrapper();
        queryWrapper.eq("stucl.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(classID)) {
            queryWrapper.eq("stucl.classID", classID);
        }
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 211);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("pxstutable.campusID.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("pxstutable.campusID.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("pxstutable.campusID.campusID", lookPower);
        }
        ajaxJson.setObj(iPxstuclasstableService.AsClassTOStuPage(page, queryWrapper));
        return ajaxJson;
    }


    /**
     * @Description: getstuIntegraInfoPage()方法作用:学员积分使用记录
     * @param:[current, size, stuID, request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/1/24 15:48
     */
    @ApiOperation("学员积分使用记录")
    @ResponseBody
    @RequestMapping(value = "getstuIntegraInfoPage", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true)
    })
    public AjaxJson getstuIntegraInfoPage(Long current, Long size, String stuID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<stuIntegerVo> page = new Page<>(current, size);
        QueryWrapper<stuIntegerVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(stuID)) {
            queryWrapper
                    .and(a -> a.isNotNull("b.zidingyiStuID").like("b.zidingyiStuID", stuID))
                    .or(b -> b.isNull("b.zidingyiStuID").eq("a.stuID", stuID));
        }
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 211);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("b.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("b.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("b.campusID", lookPower);
        }
        queryWrapper.orderByDesc("b.id");
        ajaxJson.setObj(iPxstutableService.getstuIntegraInfoPage(page, queryWrapper));
        return ajaxJson;
    }

    @ApiOperation("学员代金券使用记录")
    @ResponseBody
    @RequestMapping(value = "getdaijinquanInfoPage", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true)
    })
    public AjaxJson getdaijinquanInfoPage(Long current, Long size, String stuID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<daijinquanVo> page = new Page<>(current, size);
        QueryWrapper<daijinquanVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(stuID)) {
            queryWrapper.eq("a.stuID", stuID);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 211);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("b.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("b.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("b.campusID", lookPower);
        }
        ajaxJson.setObj(iPxstutableService.getdaijinquanInfoPage(page, queryWrapper));
        return ajaxJson;
    }

    @ApiOperation("学员课耗记录")
    @ResponseBody
    @RequestMapping(value = "getkehaoInfoPage", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true)
    })
    public AjaxJson getkehaoInfoPage(Long current, Long size, String stuID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<kehaoInfoVo> page = new Page<>(current, size);
        QueryWrapper<kehaoInfoVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(stuID)) {
            queryWrapper.eq("a.stuID", stuID);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 211);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("b.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("b.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("b.campusID", lookPower);
        }
        ajaxJson.setObj(iPxstutableService.getkehaoInfoPage(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * @Description: getstuskInfoPage()方法作用:学员信息
     * @param:[current, size, stuID, request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/1/24 17:43
     */
    @ApiOperation("学员详情")
    @ResponseBody
    @RequestMapping(value = "getstuInfoPage", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true)
    })
    public AjaxJson getstuInfoPage(Long stuID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        List<stuInfoVo> stuInfo = iPxstutableService.getstuInfoPage(stuID, qiyeID);
        int age = 0;
        if (stuInfo.get(0).getStubirth() != null) {
            try {
                age = DateUtil.getAgeByBirth(stuInfo.get(0).getStubirth());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        stuInfo.get(0).setNianling(age);
        System.out.println(stuInfo);
        ajaxJson.setObj(stuInfo);
        return ajaxJson;
    }

    /**
     * @Description: getstuskInfoPage()方法作用:学员上课信息
     * @param:[current, size, stuID, request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/1/25 11:13
     */
    @ApiOperation("学员上课详情")
    @ResponseBody
    @RequestMapping(value = "getstuskInfoPage", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true)
    })
    public AjaxJson getstuskInfoPage(Long current, Long size, Long stuID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<stuskInfoVo> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("stuID", stuID);
        queryWrapper.eq("a.qiyeID", qiyeID);

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 211);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("b.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("b.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("b.campusID", lookPower);
        }
        ajaxJson.setObj(iPxstutableService.getstuskInfoPage(page, stuID, qiyeID, queryWrapper));
        return ajaxJson;
    }

    /**
     * @Description: getstuInfoqiandan()方法作用:学员详情的签单信息
     * @param:[stuID, request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/2/2 14:47
     */
    @ApiOperation("学员详情的签单信息")
    @ResponseBody
    @RequestMapping(value = "getstuInfoqiandan", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true)
    })
    public AjaxJson getstuInfoqiandan(Long stuID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.stuID", stuID);
        queryWrapper.eq("a.qiyeID", qiyeID);

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 211);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }
        ajaxJson.setObj(iPxstutableService.getstuInfoqiandan(queryWrapper));
        return ajaxJson;
    }

    /**
     * @Description: getstuQiandanInfoPage()方法作用:学员签单详情
     * @param:[current, size, stuID, request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/1/25 16:47
     */
    @ApiOperation("学员签单详情")
    @ResponseBody
    @RequestMapping(value = "getstuQiandanInfoPage", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true)
    })
    public AjaxJson getstuQiandanInfoPage(Long current, Long size, Long stuID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<stuskInfoVo> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.id", stuID);
        queryWrapper.eq("a.qiyeID", qiyeID);
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 211);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }
        ajaxJson.setObj(iPxstutableService.getstuQiandanInfoPage(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * @Description: getkechengInfoPage()方法作用:学员课程详情
     * @param:[current, size, stuID, qdID, request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/1/26 11:20
     */
    @ApiOperation("学员课程详情")
    @ResponseBody
    @RequestMapping(value = "getkechengInfoPage", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true),
            @ApiImplicitParam(name = "qdID", value = "签单ID", required = true)
    })
    public AjaxJson getkechengInfoPage(Long current, Long size, Long stuID, Long qdID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<kcInfoLookVo> page = new Page<>(current, size);
        ajaxJson.setObj(iPxstutableService.getkechengInfoPage(page, stuID, qdID, qiyeID));
        return ajaxJson;
    }

    /**
     * @Description: getzafeiInfoPage()方法作用:学员杂费详情
     * @param:[current, size, qdID, request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/2/2 14:47
     */
    @ApiOperation("学员杂费详情")
    @ResponseBody
    @RequestMapping(value = "getzafeiInfoPage", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "qdID", value = "签单ID", required = true)
    })
    public AjaxJson getzafeiInfoPage(Long current, Long size, Long qdID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<zafeiInfoVo> page = new Page<>(current, size);
        ajaxJson.setObj(iPxstutableService.getzafeiInfoPage(page, qdID, qiyeID));
        return ajaxJson;
    }

    //导出学员档案


    @ApiOperation(value = "导出学员班级")
    @ResponseBody
    @RequestMapping(value = "exportAllStuFiles", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "stugradeID", value = "年级ID", required = false),
            @ApiImplicitParam(name = "classID", value = "班级ID", required = false),
            @ApiImplicitParam(name = "starDate", value = "开始时间", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束时间", required = false)
    })
    public void exportAllStuFiles(String campusID, String stugradeID, String classID, String starDate, String endDate, HttpServletResponse response,
                                  HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        List<ExportExcel.ExcelSource> sourceList = new ArrayList<>();

        /**条件*/
        QueryWrapper<stufilesVo> stuInfoQ = new QueryWrapper<>();
        QueryWrapper<exportstuQdVo> stuqdQ = new QueryWrapper<>();
        QueryWrapper<ExportKcVo> stukcQ = new QueryWrapper<>();
        QueryWrapper<ExportstuClassVo> classStuQ = new QueryWrapper<>();
        QueryWrapper<ClassMesVo> classMesQ = new QueryWrapper<>();
        QueryWrapper<remainkeshiVo> keshiQ = new QueryWrapper<>();

        int type = 2;//默认不查询班级
        stuInfoQ.eq("a.qiyeID", qiyeID);
        stuqdQ.eq("a.qiyeID", qiyeID);
        stukcQ.eq("a.qiyeID", qiyeID);
        classStuQ.eq("a.qiyeID", qiyeID);
        classMesQ.eq("a.qiyeID", qiyeID);
        keshiQ.eq("a.qiyeID", qiyeID);

        if (StringUtils.isNotBlank(campusID)) {
            stuInfoQ.eq("a.campusID", campusID);
            stuqdQ.eq("b.campusID", campusID);
            stukcQ.eq("b.campusID", campusID);
            classStuQ.eq("a.campusID", campusID);
            classMesQ.eq("c.campusID", campusID);
            keshiQ.eq("b.campusID", campusID);
        }
        if (StringUtils.isNotBlank(stugradeID)) {
            stuInfoQ.eq("a.stuGradeID", stugradeID);
            stuqdQ.eq("b.stuGradeID", stugradeID);
            stukcQ.eq("b.stuGradeID", stugradeID);
            classMesQ.eq("c.stuGradeID", stugradeID);
            keshiQ.eq("b.stuGradeID", stugradeID);
        }
        if (StringUtils.isNotBlank(starDate) && StringUtils.isNotBlank(endDate)) {
            stuInfoQ.ge("a.dengjiTime", starDate).le("a.dengjiTime", endDate);
            stuqdQ.ge("a.qiandandate", starDate).le("a.qiandandate", endDate);
            stukcQ.ge("a.startDate", starDate).le("a.endDate", endDate);
            classStuQ.ge("a.addTime", starDate).le("a.addTime", endDate);
            classMesQ.ge("e.addTime", starDate).le("e.addTime", endDate);
            keshiQ.ge("a.startDate", starDate).le("a.endDate", endDate);
        }
        if (StringUtils.isNotBlank(classID)) {
            type = 1;
            classStuQ.eq("a.id", classID);
            classMesQ.eq("d.classID", classID);
        }

        /**数据*/
        List<stufilesVo> stufilesVos = iPxstutableService.ExportstuFiles(type, classID, stuInfoQ);
        List<exportstuQdVo> stuQDlist = iPxstutableService.ExportstuQD(stuqdQ);
        List<ExportKcVo> stuKCList = iPxstutableService.Exportstukc(stukcQ);
        List<ExportstuClassVo> allClassList = iPxstutableService.ExportstuClass(classStuQ);
        List<ClassMesVo> classMesList = iPxstutableService.ExportClassMes(classMesQ);
        List<remainkeshiVo> remainkeshiList = iPxstutableService.ExportRekeshi(keshiQ);

        /**构造导出*/
        List<List<Object>> stuInfolist = ExportExcel.formatDataToList(new String[]{"校区", "学号", "姓名", "年级", "班主任", "学员状态", "录入人", "录入时间", "原就读学校", "合作老师",
                        "学员生日", "年龄", "学员电话", "家长电话", "说明"},
                stufilesVos,
                new String[]{"campusName", "stuID", "stuName", "stuGradeName", "banzhuren", "stustate", "staffName", "dengjiTime-D", "parentTel",
                        "oldschoolName", "oldTeacher", "stubirth-D", "age", "stuTel", "beizhu"});

        ExportExcel.ExcelSource stuInfo = new ExportExcel.ExcelSource();
        stuInfo.setSheetName("学员档案");
        stuInfo.setTableData(stuInfolist);
        sourceList.add(stuInfo);


        List<List<Object>> qdList = ExportExcel.formatDataToList(new String[]{"学员校区", "学号", "姓名", "年级", "家长电话", "性别", "备注", "是否住校", "学员电话", "合同金额", "实收金额",
                        "代金券", "学杂费", "办理时间", "经办人", "转介绍", "费用类型", "付费方式"},
                stuQDlist,
                new String[]{"campusName", "stuID", "stuName", "stuGradeName", "parentTel", "stuSex", "beizhu", "roomids", "stuTel", "HetongMoney",
                        "shishouTotalMoney", "money", "zaiMoney", "qiandandate-D", "staffName", "zhuanjieshao", "moneyStyle", "moneystyleName"});
        ExportExcel.ExcelSource stuqd = new ExportExcel.ExcelSource();
        stuqd.setSheetName("交费签单表");
        stuqd.setTableData(qdList);
        sourceList.add(stuqd);

        List<List<Object>> stukcList = ExportExcel.formatDataToList(new String[]{"学员校区", "学号", "姓名", "学员状态", "科目", "培训方式", "课程", "课程时长", "计费方式", "开始时间",
                        "结束时间", "原课程单价", "现单价", "经办人"},
                stuKCList,
                new String[]{"campusName", "stuID", "stuName", "stustate", "subjectName", "buxiStyleName", "kechengName", "classTimeStyleName", "jifeiStyle",
                        "startDate-D", "endDate-D", "originalprice", "kechengprice", "staffName"});
        ExportExcel.ExcelSource stukc = new ExportExcel.ExcelSource();
        stukc.setSheetName("报名课程表");
        stukc.setTableData(stukcList);
        sourceList.add(stukc);


        List<List<Object>> classList = ExportExcel.formatDataToList(new String[]{"班级校区", "班级编号", "班级名称", "班级人数", "建班时间", "操作人", "是否一对一", "是否启用"},
                allClassList,
                new String[]{"campusName", "classID", "className", "numbs", "addTime-D", "staffName", "is1v1Class", "isSHow"});
        ExportExcel.ExcelSource stuclass = new ExportExcel.ExcelSource();
        stuclass.setSheetName("班级信息表");
        stuclass.setTableData(classList);
        sourceList.add(stuclass);


        List<List<Object>> classStuList = ExportExcel.formatDataToList(new String[]{"学员校区", "学号", "姓名", "联系电话", "年级", "课程", "班级校区", "班级编号", "班级名称", "班级人数",
                        "操作人", "建班时间"},
                classMesList,
                new String[]{"stucampusName", "stuID", "stuName", "parentTel", "stuGradeName", "kechengName", "classCampusName", "classID", "className",
                        "numbs", "jinbanren", "addTime-D"});
        ExportExcel.ExcelSource classStu = new ExportExcel.ExcelSource();
        classStu.setSheetName("班级名单表");
        classStu.setTableData(classStuList);
        sourceList.add(classStu);


        List<List<Object>> keshiList = ExportExcel.formatDataToList(new String[]{"学员校区", "学号", "姓名", "科目", "培训方式", "课程", "课程时长", "计费方式", "开始日期", "结束日期",
                        "原课程单价", "现单价", "购买课时数", "剩余课时"},
                remainkeshiList,
                new String[]{"campusName", "stuID", "stuName", "subjectName", "buxiStyleName", "kechengName", "classTimeStyleName", "startDate-D", "endDate-D"
                        , "originalprice", "kechengprice", "keshiNum", "remainkeshi"});
        ExportExcel.ExcelSource stukeshi = new ExportExcel.ExcelSource();
        stukeshi.setSheetName("剩余课时表");
        stukeshi.setTableData(keshiList);
        sourceList.add(stukeshi);

        try {
            // 需要将详细表一起导出
            ExportExcel.exportMultipleSheetExcel(response, sourceList, "学员档案.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @Description: ExportstuFilesMuban()方法作用:0 学员档案下载模板
     * @param:[response, request, jbid]
     * @return:void
     * @auter:yyl
     * @data:2021/3/3 14:49
     */
    @ResponseBody
    @RequestMapping(value = "ExportstuFilesMuban", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jbid", value = "下载类型", required = true)
    })
    public void ExportstuFilesMuban(HttpServletResponse response, HttpServletRequest request, int jbid) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());

        List<String> titleList = new ArrayList<String>(); //合并的列表
        List<String> li = new ArrayList<>(); //自定义字段表头列表

        List<pramaTypeVo> selectstuparamtype = iPxstuparamtypetableService.getOne(qiyeID);
        if (selectstuparamtype.size() > 0) { //添加自定义字段必填项
            for (pramaTypeVo item : selectstuparamtype) {
                if (item.getIsBiTian() == true) {
                    li.add(item.getStuParamTypeName() + "（自定义必填项）");
                } else {
                    li.add(item.getStuParamTypeName());
                }
            }
        }

        Pxcampustable cam = iPxcampustableService.getById(loginUser.getCampusID());
        Pxstugradetable grade = iPxstugradetableService.getOne(qiyeID);
        if (jbid == 1) {
            //表头
            String[] headlist = {"学员姓名", "性别", "联系电话（必填）", "校区（必填）", "年级（必填）", "积分", "备注", "生日", "联系电话关系", "学员积极性", "学员性格", "老师要求", "入学成绩"};
            //数据标题
            String[] shujulist = {"stuName", "stuSex", "parentTel", "stuCampusName", "stuGradeName", "stuIntegar", "beizhu", "stuBirthDay", "parentRelation",
                    "jijixing", "xingge", "laoshiyaoqiu", "ruxuechengji"};
            List<String> HD = Arrays.asList(headlist);

            //合并表头
            titleList.addAll(HD);
            titleList.addAll(li);
            String[] newArr = titleList.toArray(new String[titleList.size()]);

            importStuInfoFilesVo One = new importStuInfoFilesVo();
            One.setStuName("张三");
            One.setStuSex("男");
            One.setParentTel("13666888668");
            One.setStuCampusName(cam.getCampusName());
            if (grade != null) {
                One.setStuGradeName(grade.getStugradename());
            } else {
                One.setStuGradeName("8岁以上");
            }
            One.setStuIntegar(BigDecimal.valueOf(0));
            One.setBeizhu("备注信息");
            One.setStuBirthDay("2021/01/01");
            One.setParentRelation("父母");
            One.setJijixing("");
            One.setXingge("");
            One.setLaoshiyaoqiu("");
            One.setRuxuechengji("");
            List<importStuInfoFilesVo> stuInfo = Collections.singletonList(One);
            List<List<Object>> list = ExportExcel.formatDataToList(newArr, stuInfo, shujulist);
            try {
                ExportExcel.exportExcel(response, list, "Sheet1", "基本信息导入模板.xls", 15);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (jbid == 2) {
            //表头
            String[] headlist = {"学员姓名", "性别", "联系电话（必填）", "校区（必填）", "年级（必填）", "积分", "新签或续费", "培训科目", "培训课程", "培训方式", "课程时长", "班级名称", "计费方式", "开始时间", "结束时间",
                    "课程原单价", "课程实收单价", "签单课时数", "签单单课程总价", "代金券金额", "实收金额", "签单时间", "额外赠送课时", "支付方式", "备注", "出生日期", "联系电话关系"};
            //数据标题
            String[] shujulist = {"stuName", "stuSex", "parentTel", "stuCampusName", "stuGradeName", "stuIntegar", "moneyStyle", "kemuName", "kechengName",
                    "buxiStyle", "classTimeStyle", "className", "jifeiStyle", "startDate", "endDate", "kechengOriginalPrice", "kechengprice", "keshiNum",
                    "HetongMoney", "daijinquanMoney", "shishouTotalMoney", "qiandandate", "zengsongkeshi", "PayMoneyStyle", "stuXuexi", "stubirthday",
                    "parentRelation"};
            List<String> HD = Arrays.asList(headlist);

            //合并表头
            titleList.addAll(HD);
            titleList.addAll(li);
            String[] newArr = titleList.toArray(new String[titleList.size()]);

            Pxsubjecttable sub = null;
            Pxkechengtable bysubject = iPxkechengtableService.getBysubject(qiyeID);
            if (bysubject != null) {
                sub = iPxsubjecttableService.getById(bysubject.getSubjectID());
            }
            Pxbuxistyletable bxstyle = iPxbuxistyletableService.getOne(qiyeID);

            importStuQiandanFilesVo Two = new importStuQiandanFilesVo();
            Two.setStuName("张三");
            Two.setStuSex("男");
            Two.setParentTel("13666888668");
            Two.setStuCampusName(cam.getCampusName());
            if (grade != null) {
                Two.setStuGradeName(grade.getStugradename());
            } else {
                Two.setStuGradeName("8岁以上");
            }
            Two.setStuIntegar(BigDecimal.valueOf(0));
            Two.setMoneyStyle("新签");
            if (sub != null) {
                Two.setKemuName(sub.getSubjectName());
            } else {
                Two.setKemuName("语文");
            }
            if (bysubject != null) {
                Two.setKechengName(bysubject.getKechengName());
            } else {
                Two.setKechengName("语文xx");
            }
            if (bxstyle != null) {
                Two.setBuxiStyle(bxstyle.getBuxiStyleName());
            } else {
                Two.setBuxiStyle("民族舞小班(90分钟)");
            }
            Two.setClassTimeStyle("90");
            Two.setClassName("民族舞(90分钟)小班");
            Two.setJifeiStyle("按课时计费");
            Two.setStartDate("2021/01/01");
            Two.setEndDate("2022/01/01");
            Two.setKechengOriginalPrice(BigDecimal.valueOf(120));
            Two.setKechengprice(BigDecimal.valueOf(100));
            Two.setKeshiNum(BigDecimal.valueOf(10));
            Two.setHetongMoney(BigDecimal.valueOf(1000));
            Two.setDaijinquanMoney(BigDecimal.valueOf(100));
            Two.setShishouTotalMoney(BigDecimal.valueOf(900));
            Two.setQiandandate("2021/01/01");
            Two.setZengsongkeshi(BigDecimal.valueOf(5));
            Two.setPayMoneyStyle("支付宝");
            Two.setStuXuexi("备注信息");
            Two.setStubirthday("2011/01/01");
            Two.setParentRelation("父母");
            List<importStuQiandanFilesVo> stuQdInfo = Collections.singletonList(Two);
            List<List<Object>> list = ExportExcel.formatDataToList(newArr, stuQdInfo, shujulist);
            try {
                ExportExcel.exportExcel(response, list, "Sheet1", "带签单信息导入模板.xls", 15);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    //导入学员
    @ApiOperation("导入学员档案")
    @ResponseBody
    @RequestMapping(value = "stuFilesExcel", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson stuFilesExcel(@RequestParam(value = "file") MultipartFile file, int jbid, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Long staffID = loginUser.getStaffID();

        List<String> typeli = new ArrayList<>(); //自定义字段表头列表

        List<pramaTypeVo> selectstuparamtype = iPxstuparamtypetableService.getOne(qiyeID);

        if (selectstuparamtype.size() > 0) { //添加自定义字段必填项
            for (pramaTypeVo item : selectstuparamtype) {
                if (item.getIsBiTian() == true) {
                    typeli.add(item.getStuParamTypeName() + "（自定义必填项）");
                } else {
                    typeli.add(item.getStuParamTypeName());
                }
            }
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        BigDecimal l = new BigDecimal(0);
        BigDecimal ci = new BigDecimal(-1);
        BigDecimal day = new BigDecimal(-2);
        BigDecimal shi = new BigDecimal(10);

        long t1 = System.currentTimeMillis();
        Map<String, List> stuQDlist = null; ///导入的签单信息
        Map<String, List> stuInfo = null;  ///导入的基础信息
        if (jbid == 1) {
            //region 带签单信息导入
            stuQDlist = importstuFilesExcel.readExcel("", importStuQiandanFilesVo.class, file, typeli);
            if (stuQDlist != null) {
//                for (int i = 0; i < stuQDlist.size(); i++) {
                if (stuQDlist.size() > 0) {
                    List<importStuQiandanFilesVo> li = stuQDlist.get("list");
                    List zdlist = stuQDlist.get("zdlist");
//                    importStuQiandanFilesVo item = stuQDlist.get(i);
//                    for (importStuQiandanFilesVo item : li) {
                    for (int i = 0; i < li.size(); i++) {
                        importStuQiandanFilesVo item = li.get(i);
                        /**
                         * 导入的数据
                         * */
                        String stuName = item.getStuName();
                        String stuSex = item.getStuSex();
                        String parentTel = item.getParentTel();
                        String stuCampusName = item.getStuCampusName();
                        String stuGradeName = item.getStuGradeName();
                        BigDecimal stuIntegar = item.getStuIntegar();
                        String moneyStyle = item.getMoneyStyle();
                        String kemuName = item.getKemuName();
                        String kechengName = item.getKechengName();
                        String buxiStyle = item.getBuxiStyle();
                        String classTimeStyle = item.getClassTimeStyle();
                        String className = item.getClassName();
                        String jifeiStyle = item.getJifeiStyle();
                        BigDecimal kczongjia = item.getHetongMoney();
                        BigDecimal olddangjia = item.getKechengOriginalPrice();
                        BigDecimal xiandanjia = item.getKechengprice();
                        BigDecimal keshiNum = item.getKeshiNum();
                        BigDecimal daijinquanMoney = item.getDaijinquanMoney();
                        BigDecimal shishouTotalMoney = item.getShishouTotalMoney();
                        BigDecimal zengsongkeshi = item.getZengsongkeshi();
                        String payMoneyStyle = item.getPayMoneyStyle();
                        String beizu = item.getStuXuexi();
                        String parentRelation = item.getParentRelation();
                        String startDate = item.getStartDate();
                        String endDate = item.getEndDate();


                        if (stuName == "" || stuName == null) {
                            ajaxJson.setCode("N");
                            ajaxJson.setMsg("第" + (i + 1) + "行，学员姓名为空");
                            return ajaxJson;
                        }
                        if (parentTel == "" || parentTel == null) {
                            ajaxJson.setCode("N");
                            ajaxJson.setMsg("第" + (i + 1) + "行，联系电话为空");
                            return ajaxJson;
                        }
                        if (stuCampusName == "" || stuCampusName == null) {
                            ajaxJson.setCode("N");
                            ajaxJson.setMsg("第" + (i + 1) + "行，学员校区为空");
                            return ajaxJson;
                        }
                        if (stuGradeName == "" || stuGradeName == null) {
                            ajaxJson.setCode("N");
                            ajaxJson.setMsg("第" + (i + 1) + "行，学员年级为空");
                            return ajaxJson;
                        }
                        if (moneyStyle == "" || moneyStyle == null) {
                            ajaxJson.setCode("N");
                            ajaxJson.setMsg("第" + (i + 1) + "行，学员新签或续费为空");
                            return ajaxJson;
                        } else if (!moneyStyle.equals("新签") && !moneyStyle.equals("续费")) {
                            ajaxJson.setCode("N");
                            ajaxJson.setMsg("第" + (i + 1) + "行，新签或续费应该为新签或续费");
                            return ajaxJson;
                        }
                        if (kemuName == "" || kemuName == null) {
                            ajaxJson.setCode("N");
                            ajaxJson.setMsg("第" + (i + 1) + "行，科目为空");
                            return ajaxJson;
                        }

                        //Boolean pattern = classTimeStyle.matches("-?[0-9]+.？[0-9]*");
                        if (classTimeStyle == "" || classTimeStyle == null) {
                            ajaxJson.setCode("N");
                            ajaxJson.setMsg("第" + (i + 1) + "行，课程时长为空");
                            return ajaxJson;
                        }
                        //else if (!pattern && classTimeStyle.equals("1次") && classTimeStyle.equals("1天")) {
                        //    ajaxJson.setCode("N");
                        //    ajaxJson.setMsg("第" + (i+1) + "行，课时时长只能为1次，1天，或者纯数值");
                        //    return ajaxJson;
                        //}

                        if (jifeiStyle == "" || jifeiStyle == null) {
                            ajaxJson.setCode("N");
                            ajaxJson.setMsg("第" + (i + 1) + "行，计费方式为空");
                            return ajaxJson;
                        } else if (!jifeiStyle.equals("按课时计费") && !jifeiStyle.equals("按课时包计费") && !jifeiStyle.equals("按起止日期计费")) {
                            ajaxJson.setCode("N");
                            ajaxJson.setMsg("第" + (i + 1) + "行，计费方式填写有误");
                            return ajaxJson;
                        }
                        if (jifeiStyle.equals("按起止日期计费") && (item.getStartDate() == null || item.getEndDate() == null || item.getStartDate() == "" || item.getEndDate() == "")) {
                            ajaxJson.setCode("N");
                            ajaxJson.setMsg("第" + (i + 1) + "行，学员姓名为空");
                            return ajaxJson;
                        }
                        if (item.getQiandandate() == "" || item.getQiandandate() == null) {
                            ajaxJson.setCode("N");
                            ajaxJson.setMsg("第" + (i + 1) + "行，签单日期为空");
                            return ajaxJson;
                        }
                        if (payMoneyStyle == "" || payMoneyStyle == null) {
                            ajaxJson.setCode("N");
                            ajaxJson.setMsg("第" + (i + 1) + "行，支付方式为空");
                            return ajaxJson;
                        }
                        if (parentRelation == "" || parentRelation == null) {
                            ajaxJson.setCode("N");
                            ajaxJson.setMsg("第" + (i + 1) + "行，联系电话关系为空");
                            return ajaxJson;
                        }
                        if (!jifeiStyle.equals("按起止日期计费") && xiandanjia.compareTo(l) == -1) {
                            ajaxJson.setCode("N");
                            ajaxJson.setMsg("第" + (i + 1) + "行，按课时或课时包计费的课程，课程现单价填写错误");
                            return ajaxJson;
                        }

                        Pxcampustable cam = null;
                        List<Pxcampustable> oneCampus = iPxcampustableService.getOneCampus(qiyeID, stuCampusName);
                        if (oneCampus.size() == 0) {
                            ajaxJson.setCode("N");
                            ajaxJson.setMsg("第" + (i + 1) + "行，校区不存在");
                            return ajaxJson;
                        } else {
                            cam = oneCampus.get(0);
                        }

                        Date Stime = null;
                        try {
                            Stime = sdf.parse(startDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }


                        Date Etime = null;
                        try {
                            Etime = sdf.parse(endDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (Stime.getTime() > Etime.getTime()) {
                            ajaxJson.setCode("N");
                            ajaxJson.setMsg("第" + (i + 1) + "行，开始时间大于结束时间");
                            return ajaxJson;
                        }

                        Date Qdtime = null;
                        try {
                            Qdtime = sdf.parse(item.getQiandandate());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        Date birthday = null;
                        try {
                            birthday = sdf.parse(item.getStubirthday());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        //去除导入时长多出来的文字信息 90分钟->90
                        classTimeStyle = classTimeStyle.trim();
                        String ctStyle = "";
                        if ((classTimeStyle != null || !"".equals(classTimeStyle)) && !classTimeStyle.equals("一天") && !classTimeStyle.equals("一次")) {
                            for (int k = 0; k < classTimeStyle.length(); k++) {
                                if (classTimeStyle.charAt(k) >= 48 && classTimeStyle.charAt(k) <= 57) {
                                    ctStyle += classTimeStyle.charAt(k);
                                }
                            }
                        } else if (classTimeStyle.equals("一天")) {
                            ctStyle = "-2";
                        } else if (classTimeStyle.equals("一次")) {
                            ctStyle = "-1";
                        }

                        if ((xiandanjia.multiply(keshiNum).compareTo(kczongjia.subtract(BigDecimal.valueOf(5))) == -1) ||
                                (xiandanjia.multiply(keshiNum).compareTo(kczongjia.add(BigDecimal.valueOf(5))) == 1) &&
                                        jifeiStyle.equals("按起止日期计费")) {
                            //条件： 小于是-1  大于是1
                            //为了防止小数位引起的正常误差，课程总价在正负5之间都算是正常
                            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                            ajaxJson.setCode("N");
                            ajaxJson.setMsg("第" + (i + 1) + "行，课程总价计算验证错误");
                            return ajaxJson;
                        } else if (kczongjia.subtract(daijinquanMoney).compareTo(shishouTotalMoney) != 0 && jifeiStyle.equals("按起止日期计费")) {
                            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                            ajaxJson.setCode("N");
                            ajaxJson.setMsg("第" + (i + 1) + "行，实收总金额计算验证错误");
                            return ajaxJson;
                        }

//                    QueryWrapper<Pxsubjecttable> subq = new QueryWrapper<>();
//                    subq.eq("qiyeID", qiyeID).like("subjectName", kemuName);
//                    List<Pxsubjecttable> subjectList = iPxsubjecttableService.selectsub(subq);
//                    if (subjectList.size()==0){
//                        ajaxJson.setCode("N");
//                        ajaxJson.setMsg("第" + (i+1) + "行，校区不存在");
//                        return ajaxJson;
//                    }

                        //region 正常数据导入
                        QueryWrapper<Pxstutable> stuQ = new QueryWrapper<>();
                        stuQ
                                .eq("stuName", stuName)
                                .eq("qiyeID", qiyeID);
                        List<Pxstutable> stuList = iPxstutableService.selectstu(stuQ);

                        if (stuList.size() > 0) {
                            Pxstutable stu = stuList.get(0);
                            //region 学员存在

                            //添加签单信息
                            int qiandanstyle = 0;
                            Long zhifufangshi = 0L;

                            Long nianjiid = 0L;//年级id
                            Long laiyuanid = 0L;//来源id
                            Long kemuid = 0L;//科目id
                            Long buxifangshiid = 0L;//补习方式id
                            Long shijianzhiid = 0L;//时间制id
                            Long kechengid = 0L;//课程id
                            Long banjiid = 0L;//班级id
                            int jfstyle = 0;

                            if (moneyStyle.equals("新签")) {
                                qiandanstyle = 1;
                            } else if (moneyStyle.equals("续费")) {
                                qiandanstyle = 2;
                            }
                            List<Pxpaymoneystyletable> onePay = iPxpaymoneystyletableService.getOnePay(qiyeID, payMoneyStyle);
                            if (onePay.size() > 0) {
                                zhifufangshi = onePay.get(0).getId();
                            } else {
                                Pxpaymoneystyletable pay = new Pxpaymoneystyletable();
                                pay.setMoneystyleName(payMoneyStyle);
                                pay.setQiyeID(qiyeID);
                                iPxpaymoneystyletableService.save(pay);
                                zhifufangshi = pay.getId();
                            }
                            //region 不存在的数据录入
                            //"系统设置-招生途径 不允许为空，至少要有一个招生途径!
                            List<Pxyxtelfromtable> oneTelfrom = iPxyxtelfromtableService.getOneTelfrom(qiyeID);
                            if (oneTelfrom.size() == 0) {
                                Pxyxtelfromtable telf = new Pxyxtelfromtable();
                                telf.setQiyeID(qiyeID);
                                telf.setAddStaffID(staffID);
                                telf.setBeizhu("当前系统招生途径未设置，自动添加一条招生途经");
                                telf.setAddTime(new Date());
                                telf.setTelFromName("市场");
                                iPxyxtelfromtableService.save(telf);
                                laiyuanid = telf.getId();
                            } else {
                                laiyuanid = oneTelfrom.get(0).getId();
                            }
                            //检测存在年级、科目、补习方式、课程时长、课程、班级
                            QueryWrapper<Pxstugradetable> gradeQ = new QueryWrapper<>();
                            gradeQ
                                    .eq("qiyeID", qiyeID)
                                    .eq("stuGradeName", stuGradeName);
                            List<Pxstugradetable> stuGradetab = iPxstugradetableService.selectstuGrade(gradeQ);

                            QueryWrapper<Pxsubjecttable> subq = new QueryWrapper<>();
                            subq
                                    .eq("qiyeID", qiyeID)
                                    .eq("subjectName", kemuName);
                            List<Pxsubjecttable> subjecttab = iPxsubjecttableService.selectsub(subq);

                            QueryWrapper<Pxbuxistyletable> bxstyQ = new QueryWrapper<>();
                            bxstyQ
                                    .eq("qiyeID", qiyeID)
                                    .eq("buxiStyleName", buxiStyle);
                            List<Pxbuxistyletable> buxiStyletab = iPxbuxistyletableService.selectbxstyle(bxstyQ);

                            QueryWrapper<Pxclasstimestyletable> classtimeQ = new QueryWrapper<>();
                            classtimeQ
                                    .eq("qiyeID", qiyeID)
                                    .eq("classtimestylename", ctStyle);
                            List<Pxclasstimestyletable> classTimeStyletab = iPxclasstimestyletableService.selectclassTime(classtimeQ);

                            QueryWrapper<Pxkechengtable> keQ = new QueryWrapper<>();
                            keQ
                                    .eq("qiyeid", qiyeID)
                                    .eq("kechengname", kechengName);
                            List<Pxkechengtable> kechengtab = iPxkechengtableService.selectkc(keQ);


                            List<Pxclasstable> classtab = null;
                            if (className != "" || className != null) {
                                QueryWrapper<Pxclasstable> classQ = new QueryWrapper<>();
                                classQ
                                        .eq("qiyeID", qiyeID)
                                        .eq("", className);
                                classtab = iPxclasstableService.selectclass(classQ);
                            }

                            if (jifeiStyle.equals("按课时计费")) {
                                jfstyle = 1;
                            } else if (jifeiStyle.equals("按课时包计费")) {
                                jfstyle = 2;
                            } else if (jifeiStyle.equals("按起止日期计费")) {
                                jfstyle = 3;
                            }

                            if (stuGradetab.size() == 0) {
                                Pxstugradetable grade = new Pxstugradetable();
                                grade.setQiyeID(qiyeID);
                                grade.setStugradename(stuGradeName);
                                iPxstugradetableService.save(grade);
                                nianjiid = grade.getId();
                            } else {
                                nianjiid = stuGradetab.get(0).getId();
                            }

                            if (subjecttab.size() == 0) {
                                Pxsubjecttable sub = new Pxsubjecttable();
                                sub.setCampusID(cam.getId());
                                sub.setQiyeID(qiyeID);
                                sub.setSubjectName(kemuName);
                                iPxsubjecttableService.save(sub);
                                kemuid = sub.getId();
                            } else {
                                kemuid = subjecttab.get(0).getId();
                            }

                            if (buxiStyletab.size() == 0) {
                                Pxbuxistyletable bxSt = new Pxbuxistyletable();
                                bxSt.setBuxiStyleName(buxiStyle);
                                bxSt.setQiyeID(qiyeID);
                                bxSt.setIs1v1(0);
                                iPxbuxistyletableService.save(bxSt);
                                buxifangshiid = bxSt.getId();
                            } else {
                                buxifangshiid = buxiStyletab.get(0).getId();
                            }

                            if (classTimeStyletab.size() == 0) {
                                Pxclasstimestyletable classT = new Pxclasstimestyletable();
                                classT.setClasstimestylename(ctStyle);
                                classT.setQiyeID(qiyeID);
                                iPxclasstimestyletableService.save(classT);
                                shijianzhiid = classT.getId();
                            } else {
                                shijianzhiid = classTimeStyletab.get(0).getId();
                            }

                            if (kechengtab.size() == 0) {
                                Pxkechengtable addkc = new Pxkechengtable();
                                addkc.setKechengName(kechengName);
                                addkc.setSubjectID(kemuid);
                                addkc.setBuxiStyleID(buxifangshiid);
                                if (buxiStyle.equals("一对一")) {
                                    addkc.setIs1v1KC(1);
                                } else {
                                    addkc.setIs1v1KC(0);
                                }

                                addkc.setClassTimeStyleID(shijianzhiid);
                                addkc.setKechengprice(xiandanjia);
                                addkc.setKeshiNum(keshiNum);
                                addkc.setBuyZonjia(kczongjia);
                                addkc.setIsShow(1);
                                addkc.setJifeiStyleID(jfstyle);
                                addkc.setCampusID(cam.getId());
                                addkc.setQiyeID(qiyeID);
                                addkc.setPerdaysqj(0);
                                addkc.setPerkeshiqj(l);
                                addkc.setQingjiaTimes(-1);
                                addkc.setIskoukeshi(false);
                                iPxkechengtableService.save(addkc);
                                kechengid = addkc.getId();
                            } else {
                                kechengid = kechengtab.get(0).getId();
                            }

                            if (classtab == null && !buxiStyle.equals("一对一")) {
                                Pxclasstable newclass = new Pxclasstable();
//                                if (buxiStyle.equals("一对一")) {
//                                    if (className.contains("一对一")) {
//                                        Random rd = new Random();
//                                        int sjs = rd.nextInt(999);
//                                        String newclassName = stuName + "_" + kemuName + "_一对一" + sjs;
//                                        newclass.setClassName(newclassName);
//                                        newclass.setIs1v1Class(1);
//                                    }
//                                } else {
//                                    newclass.setClassName(className);
//                                    newclass.setIs1v1Class(0);
//                                }
                                newclass.setClassName(className);
                                newclass.setIs1v1Class(0);
                                newclass.setCampusID(cam.getId());
                                newclass.setIsShow(1);
                                newclass.setAddStaffID(staffID);
                                newclass.setAddTime(new Date());
                                newclass.setQiyeID(qiyeID);
                                newclass.setClassState(0);
                                iPxclasstableService.save(newclass);
                                banjiid = newclass.getId();
                            } else {
                                banjiid = classtab.get(0).getId();
                            }

                            Pxqiandaninfotable qd = new Pxqiandaninfotable();
                            qd.setStuID(stu.getId());
                            qd.setQiandandate(Qdtime);
                            qd.setShishouTotalMoney(shishouTotalMoney);
                            qd.setHetongMoney(kczongjia);
                            qd.setMoneyStyle(qiandanstyle);
                            qd.setPayMoneyStyle(zhifufangshi);
                            qd.setQianDanStaffID(staffID);
                            qd.setRecordInStaffID(staffID);
                            qd.setRecordInTime(new Date());
                            qd.setCampusID(cam.getId());
                            qd.setQiandanType(2);
                            qd.setQiyeID(qiyeID);
                            qd.setBeizhu(beizu);
                            qd.setIsdingjing(1);
                            iPxqiandaninfotableService.save(qd);

                            stu.setRemainXuefei(stu.getRemainXuefei().add(kczongjia));
                            iPxstutableService.updateById(stu);

                            Pxqiandanstafftable qdst = new Pxqiandanstafftable();
                            qdst.setQiandanID(qd.getId());
                            qdst.setStaffID(staffID);
                            qdst.setYejiMoney(shishouTotalMoney);
                            qdst.setYejidateTime(Qdtime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                            qdst.setIsWeikuan(0);
                            qdst.setQiyeID(qiyeID);
                            iPxqiandanstafftableService.save(qdst);


                            Pxqiandansubjecttable qdSub = new Pxqiandansubjecttable();
                            qdSub.setStuID(stu.getId());
                            qdSub.setQiandandate(Qdtime);
                            qdSub.setKechengID(kechengid);//赞时替代--还未添加课程
                            qdSub.setKechengprice(xiandanjia);
                            qdSub.setOriginalprice(olddangjia);
                            qdSub.setBuykeshiNum(keshiNum);
                            qdSub.setZongjia(kczongjia);
                            qdSub.setStartDate(Stime);
                            qdSub.setEndDate(Etime);
                            qdSub.setQianDanInfoID(qd.getId());
                            qdSub.setKechengStyle(1);
                            qdSub.setDiscount(shi);
                            qdSub.setQiyeID(qiyeID);
                            ///通过BigDecimal的divide方法进行除法时当不整除，出现无限循环小数时，就会抛异常, 取小数后2位
                            qdSub.setDiscount(xiandanjia.divide(olddangjia, 2, BigDecimal.ROUND_HALF_EVEN).multiply(shi));
                            iPxqiandansubjecttableService.save(qdSub);

                            //课程存在判断
                            QueryWrapper<Pxbuxikechengtable> bxQ = new QueryWrapper<>();
                            bxQ
                                    .eq("stuID", stu.getId())
                                    .eq("kechengID", kechengid)
                                    .eq("qiyeID", qiyeID);
                            List<Pxbuxikechengtable> pdbuxikecheng = iPxbuxikechengtableService.selectbxkc(bxQ);

                            if (pdbuxikecheng.size() != 0 && pdbuxikecheng.get(0).getKechengprice().compareTo(xiandanjia) == 0) {
                                Pxbuxikechengtable oldbuxi = pdbuxikecheng.get(0);
                                if (qiandanstyle == 1) {
                                    Pxkechengtable kc = iPxkechengtableService.getById(kechengid);
                                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                                    ajaxJson.setCode("N");
                                    ajaxJson.setMsg("第" + (i + 1) + "行，新签！" + stuName + "的补习课程" + kechengName + "已存在");
                                    return ajaxJson;
                                }

                                if (jfstyle == 3) {
                                    oldbuxi.setStartDate(Stime);
                                    oldbuxi.setEndDate(Etime);
                                    oldbuxi.setOriginalprice(olddangjia);
                                    oldbuxi.setKechengprice(xiandanjia);
                                    oldbuxi.setZongjia(kczongjia);
                                } else {
                                    oldbuxi.setKeshiNum(keshiNum);
                                    oldbuxi.setZongjia(oldbuxi.getZongjia().add(kczongjia));
                                    oldbuxi.setRemainkeshi(oldbuxi.getRemainkeshi().add(keshiNum));
                                }
                                iPxbuxikechengtableService.updateById(oldbuxi);

                                if (buxiStyletab.size() != 0 && !buxiStyletab.get(0).getBuxiStyleName().equals("一对一")) {
                                    Pxstuclasstable stuclass = new Pxstuclasstable();
                                    stuclass.setBuxiID(oldbuxi.getId());
                                    stuclass.setClassID(banjiid);
                                    stuclass.setQiyeID(qiyeID);

                                    List<Pxstuclasstable> have = iPxstuclasstableService.getBybxAndclassID(oldbuxi.getId(), banjiid, qiyeID);
                                    if (have.size() == 0) {
                                        iPxstuclasstableService.save(stuclass);
                                    }
                                    //查询班级的未打考勤的所有排课
                                    List<Pxpaiketable> paike = iPxpaiketableService.getBykq(banjiid, qiyeID);
                                    for (Pxpaiketable paikeItem : paike) {
                                        //没有选课数据插入
                                        List<Pxxuanketable> xuankeTable = iPxxuanketableService.xxkbypkbx(paikeItem.getId(), oldbuxi.getId(), qiyeID);
                                        if (xuankeTable.size() == 0) {
                                            Pxxuanketable xk = new Pxxuanketable();
                                            xk.setQiyeID(qiyeID);
                                            xk.setStuID(stu.getId());
                                            xk.setPaikeID(paikeItem.getId());
                                            xk.setType(1);
                                            xk.setBuxiID(oldbuxi.getId());
                                            xk.setRecordDate(new Date());
                                            iPxxuanketableService.save(xk);
                                        }
                                    }

                                }
                            } else {
                                //添加补习课程信息
                                Pxbuxikechengtable bxTab = new Pxbuxikechengtable();
                                bxTab.setStuID(stu.getId());
                                bxTab.setKechengID(kechengid);
                                if (jfstyle == 3) {
                                    bxTab.setKechengprice(l);
                                    bxTab.setOriginalprice(l);
                                    bxTab.setRemainkeshi(l);
                                    bxTab.setKeshiNum(l);
                                } else {
                                    bxTab.setKechengprice(xiandanjia);
                                    bxTab.setOriginalprice(olddangjia);
                                    bxTab.setRemainkeshi(keshiNum);
                                    bxTab.setKeshiNum(keshiNum);
                                }
                                bxTab.setStartDate(Stime);
                                bxTab.setEndDate(Etime);
                                bxTab.setZongjia(kczongjia);
                                bxTab.setBuykeshiDateTime(new Date());
                                bxTab.setJifeiStyleID(jfstyle);
                                if (pdbuxikecheng.size() != 0 && pdbuxikecheng.get(0).getKechengprice().compareTo(xiandanjia) != 0) {
                                    bxTab.setIsShow(1);
                                } else {
                                    bxTab.setIsShow(0);
                                }
                                bxTab.setType(1);
                                bxTab.setQianDanInfoID(qd.getId());
                                bxTab.setQianDanSubjectID(qdSub.getId());
                                bxTab.setQiyeID(qiyeID);
                                iPxbuxikechengtableService.save(bxTab);

                                //如果是一对一，则建班,插班
                                Long ClassID = 0L;
                                if (buxiStyletab.size() != 0 && buxiStyletab.get(0).getBuxiStyleName() == "一对一") {
                                    Boolean ii = false;
                                    while (ii == false) {
                                        Random rd = new Random();
                                        int sjs = rd.nextInt(999);
                                        String newclassName = stuName + "_" + kemuName + "_一对一" + sjs;
                                        List<Pxclasstable> pdclass = iPxclasstableService.FkClassName(newclassName, qiyeID);
                                        if (pdclass.size() == 0) {
                                            Pxclasstable cl = new Pxclasstable();
                                            cl.setClassName(newclassName);
                                            cl.setCampusID(stu.getCampusID());
                                            cl.setIs1v1Class(1);
                                            cl.setIsShow(1);
                                            cl.setAddTime(new Date());
                                            cl.setAddStaffID(staffID);
                                            cl.setClassState(0);
                                            cl.setIsdelete(false);
                                            iPxclasstableService.save(cl);
                                            ClassID = cl.getId();
                                            ii = true;
                                        }
                                    }

                                    //一对一班级插班
                                    Pxstuclasstable pxstuclass = new Pxstuclasstable();
                                    pxstuclass.setQiyeID(qiyeID);
                                    pxstuclass.setClassID(ClassID);
                                    pxstuclass.setBuxiID(bxTab.getId());
                                    iPxstuclasstableService.save(pxstuclass);
                                } else {
                                    Pxstuclasstable stuclass = new Pxstuclasstable();
                                    stuclass.setBuxiID(bxTab.getId());
                                    stuclass.setClassID(banjiid);
                                    stuclass.setQiyeID(qiyeID);
                                    iPxstuclasstableService.save(stuclass);
                                    //查询班级的未打考勤的所有排课

                                    List<Pxpaiketable> paike = iPxpaiketableService.getBykq(stuclass.getClassID(), qiyeID);
                                    for (Pxpaiketable paikeItem : paike) {
                                        //没有选课数据插入
                                        List<Pxxuanketable> xuankeTable = iPxxuanketableService.xxkbypkbx(paikeItem.getId(), bxTab.getId(), qiyeID);
                                        if (xuankeTable.size() == 0) {
                                            Pxxuanketable xk = new Pxxuanketable();
                                            xk.setQiyeID(qiyeID);
                                            xk.setStuID(stu.getId());
                                            xk.setPaikeID(paikeItem.getId());
                                            xk.setType(1);
                                            xk.setBuxiID(bxTab.getId());
                                            xk.setRecordDate(new Date());
                                            iPxxuanketableService.save(xk);
                                        }
                                    }

                                }
                            }

                            if (zengsongkeshi.compareTo(l) == 1) {
                                //添加增送补习课程信息
                                Pxbuxikechengtable zsbx = new Pxbuxikechengtable();
                                zsbx.setStuID(stu.getId());
                                zsbx.setKechengID(kechengid);
                                zsbx.setKechengprice(xiandanjia);
                                zsbx.setOriginalprice(olddangjia);
                                zsbx.setRemainkeshi(zengsongkeshi);
                                zsbx.setKeshiNum(zengsongkeshi);
                                zsbx.setStartDate(Stime);
                                zsbx.setEndDate(Etime);
                                zsbx.setZongjia(l);
                                zsbx.setBuykeshiDateTime(new Date());
                                zsbx.setJifeiStyleID(jfstyle);
                                zsbx.setIsShow(0);
                                zsbx.setType(2);
                                zsbx.setQiyeID(qiyeID);
                                iPxbuxikechengtableService.save(zsbx);

                                Pxkeshizengsongtable kszs = new Pxkeshizengsongtable();
                                kszs.setStuID(stu.getId());
                                kszs.setKechengID(kechengid);
                                kszs.setKeshiShu(zengsongkeshi);
                                kszs.setKechengPrice(xiandanjia);
                                kszs.setKeshiShu(keshiNum);
                                kszs.setCaozuoStaffId(staffID);
                                kszs.setAddDate(new Date());
                                kszs.setSongYangyin("签单课时赠送");
                                kszs.setJifeiStyle(jfstyle);
                                kszs.setQiandanInfoID(qd.getId());
                                kszs.setQiyeID(qiyeID);
                                iPxkeshizengsongtableService.save(kszs);

                                Pxkechengtable kcTab = iPxkechengtableService.getById(kechengid);
                                Pxbuxistyletable bxStyle = iPxbuxistyletableService.getById(kcTab.getBuxiStyleID());
                                if (bxStyle.getBuxiStyleName().equals("一对一")) {
                                    Pxsubjecttable subject = iPxsubjecttableService.getById(kcTab.getSubjectID());
                                    Long NewclassID = 0L;
                                    //建班插班
                                    Boolean ii = false;
                                    while (ii == false) {
                                        Random rd = new Random();
                                        int sjs = rd.nextInt(999);
                                        String newclassName = "(赠送)" + stuName + "_" + subject.getSubjectName() + "_一对一" + sjs;
                                        List<Pxclasstable> pdclass = iPxclasstableService.FkClassName(newclassName, qiyeID);
                                        if (pdclass.size() == 0) {
                                            Pxclasstable cl = new Pxclasstable();
                                            cl.setClassName(newclassName);
                                            cl.setCampusID(stu.getCampusID());
                                            cl.setIs1v1Class(1);
                                            cl.setIsShow(1);
                                            cl.setAddTime(new Date());
                                            cl.setAddStaffID(staffID);
                                            cl.setClassState(0);
                                            cl.setIsdelete(false);
                                            iPxclasstableService.save(cl);
                                            NewclassID = cl.getId();
                                            ii = true;
                                        }
                                    }

                                    //一对一班级插班
                                    Pxstuclasstable stucla = new Pxstuclasstable();
                                    stucla.setQiyeID(qiyeID);
                                    stucla.setClassID(NewclassID);
                                    stucla.setBuxiID(zsbx.getId());
                                    iPxstuclasstableService.save(stucla);
                                }
                            }

                            //添加流水账
                            Pxliushuizhangtable liushui = new Pxliushuizhangtable();
                            liushui.setLiushuiDateTime(new Date());
                            liushui.setCampusID(cam.getId());
                            liushui.setLiushuiZaiyao(cam.getCampusName() + "," + stu.getStuName() + "同学：" + moneyStyle + "：全款交费");
                            liushui.setPayMoneyStyle(zhifufangshi);
                            liushui.setShouruMoney(shishouTotalMoney);
                            liushui.setZhichuMoney(l);
                            liushui.setJinbanRen(staffID);
                            liushui.setShouzhiStyleID(1L);
                            liushui.setStuID(stu.getId());
                            liushui.setAddStaffID(staffID);
                            liushui.setLuruTime(new Date());
                            liushui.setQiandanID(qd.getId());
                            liushui.setQiyeID(qiyeID);
                            iPxliushuizhangtableService.save(liushui);

                            //添加代金券
                            if (daijinquanMoney.compareTo(l) == 1) { //代金券金额>0
                                Pxdaijinquantable dj = new Pxdaijinquantable();
                                dj.setStuID(stu.getId());
                                dj.setQiandanID(qd.getId());
                                dj.setMoney(daijinquanMoney);
                                dj.setCreatTime(Qdtime);
                                dj.setStaffID(qd.getQianDanStaffID());
                                dj.setQiyeID(qiyeID);
                                iPxdaijinquantableService.save(dj);

                                //填充签单表的优惠信息
                                qd.setYouhuiID(dj.getId());
                                qd.setYouhuijine(daijinquanMoney.toString());
                                iPxqiandaninfotableService.updateById(qd);
                            }
                            if (stuIntegar.compareTo(l) == 1) {
                                Pxjifentable jf = new Pxjifentable();
                                jf.setStuID(stu.getId());
                                jf.setType(1);
                                jf.setOldintegral(l);
                                jf.setIntegral(stuIntegar);
                                jf.setStaffID(staffID);
                                jf.setCreatetime(new Date());
                                jf.setRemark("一键导入");
                                jf.setQiyeID(qiyeID);
                                iPxjifentableService.save(jf);
                            }
                            //endregion
                            //endregion
                        } else {
                            //region 学员不存在、添加信息
                            Long nianjiid = 0L;//年级id
                            Long laiyuanid = 0L;//来源id
                            Long kemuid = 0L;//科目id
                            Long buxifangshiid = 0L;//补习方式id
                            String buxiname = "";//补习方式id
                            Long shijianzhiid = 0L;//时间制id
                            Long kechengid = 0L;//课程id
                            Long banjiid = 0L;//班级id
                            Long zhifufangshi = 0L;
                            int jfstyle = 0;

                            //"系统设置-招生途径 不允许为空，至少要有一个招生途径!
                            List<Pxyxtelfromtable> oneTelfrom = iPxyxtelfromtableService.getOneTelfrom(qiyeID);
                            if (oneTelfrom.size() == 0) {
                                Pxyxtelfromtable telf = new Pxyxtelfromtable();
                                telf.setQiyeID(qiyeID);
                                telf.setAddStaffID(staffID);
                                telf.setBeizhu("当前系统招生途径未设置，自动添加一条招生途经");
                                telf.setAddTime(new Date());
                                telf.setTelFromName("市场");
                                iPxyxtelfromtableService.save(telf);
                                laiyuanid = telf.getId();
                            } else {
                                laiyuanid = oneTelfrom.get(0).getId();
                            }
                            QueryWrapper<Pxstugradetable> gradeQ = new QueryWrapper<>();
                            gradeQ
                                    .eq("qiyeID", qiyeID)
                                    .eq("stuGradeName", stuGradeName);
                            List<Pxstugradetable> stuGradetab = iPxstugradetableService.selectstuGrade(gradeQ);

                            QueryWrapper<Pxsubjecttable> subq = new QueryWrapper<>();
                            subq
                                    .eq("qiyeID", qiyeID)
                                    .eq("subjectName", kemuName);
                            List<Pxsubjecttable> subjecttab = iPxsubjecttableService.selectsub(subq);

                            QueryWrapper<Pxbuxistyletable> bxstyQ = new QueryWrapper<>();
                            bxstyQ
                                    .eq("qiyeID", qiyeID)
                                    .eq("buxiStyleName", buxiStyle);
                            List<Pxbuxistyletable> buxiStyletab = iPxbuxistyletableService.selectbxstyle(bxstyQ);

                            QueryWrapper<Pxclasstimestyletable> classtimeQ = new QueryWrapper<>();
                            classtimeQ
                                    .eq("qiyeID", qiyeID)
                                    .eq("classtimestylename", ctStyle);
                            List<Pxclasstimestyletable> classTimeStyletab = iPxclasstimestyletableService.selectclassTime(classtimeQ);

                            List<Pxkechengtable> kechengtab = null;
                            if (subjecttab.size() != 0) {
                                QueryWrapper<Pxkechengtable> kcQ = new QueryWrapper<>();
                                kcQ
                                        .eq("kechengname", kechengName)
                                        .eq("qiyeid", qiyeID)
                                        .eq("campusid", cam.getId())
                                        .eq("subjectid", subjecttab.get(0).getId());
                                kechengtab = iPxkechengtableService.selectkc(kcQ);
                            } else {
                                kechengtab = null;
                            }

                            QueryWrapper<Pxclasstable> clQ = new QueryWrapper<>();
                            clQ
                                    .eq("className", className)
                                    .eq("campusID", cam.getId())
                                    .eq("qiyeID", qiyeID);
                            List<Pxclasstable> classtab = iPxclasstableService.selectclass(clQ);

                            if (jifeiStyle.equals("按课时计费")) {
                                jfstyle = 1;
                            } else if (jifeiStyle.equals("按课时包计费")) {
                                jfstyle = 2;
                            } else if (jifeiStyle.equals("按起止日期计费")) {
                                jfstyle = 3;
                            }
                            if (stuGradetab.size() == 0) {
                                Pxstugradetable stugra = new Pxstugradetable();
                                stugra.setStugradename(stuGradeName);
                                stugra.setQiyeID(qiyeID);
                                iPxstugradetableService.save(stugra);
                                nianjiid = stugra.getId();
                            } else {
                                nianjiid = stuGradetab.get(0).getId();
                            }

                            if (subjecttab.size() == 0) {
                                Pxsubjecttable sub = new Pxsubjecttable();
                                sub.setCampusID(cam.getId());
                                sub.setQiyeID(qiyeID);
                                sub.setSubjectName(kemuName);
                                iPxsubjecttableService.save(sub);
                                kemuid = sub.getId();
                            } else {
                                kemuid = subjecttab.get(0).getId();
                            }

                            if (buxiStyletab.size() == 0) {
                                Pxbuxistyletable bxSt = new Pxbuxistyletable();
                                bxSt.setBuxiStyleName(buxiStyle);
                                bxSt.setQiyeID(qiyeID);
                                bxSt.setIs1v1(0);
                                iPxbuxistyletableService.save(bxSt);
                                buxifangshiid = bxSt.getId();
                            } else {
                                buxifangshiid = buxiStyletab.get(0).getId();
                            }

                            if (classTimeStyletab.size() == 0) {
                                Pxclasstimestyletable classT = new Pxclasstimestyletable();
                                classT.setClasstimestylename(ctStyle);
                                classT.setQiyeID(qiyeID);
                                iPxclasstimestyletableService.save(classT);
                                shijianzhiid = classT.getId();
                            } else {
                                shijianzhiid = classTimeStyletab.get(0).getId();
                            }

                            if (kechengtab.size() == 0) {

                                Pxkechengtable addkc = new Pxkechengtable();
                                addkc.setKechengName(kechengName);
                                addkc.setSubjectID(kemuid);
                                addkc.setBuxiStyleID(buxifangshiid);
                                if (buxiStyle.equals("一对一")) {
                                    addkc.setIs1v1KC(1);
                                } else {
                                    addkc.setIs1v1KC(0);
                                }

                                addkc.setClassTimeStyleID(shijianzhiid);
                                addkc.setKechengprice(xiandanjia);
                                addkc.setKeshiNum(keshiNum);
                                addkc.setBuyZonjia(kczongjia);
                                addkc.setIsShow(1);
                                addkc.setJifeiStyleID(jfstyle);
                                addkc.setCampusID(cam.getId());

                                addkc.setQiyeID(qiyeID);
                                addkc.setPerdaysqj(0);
                                addkc.setPerkeshiqj(l);
                                addkc.setQingjiaTimes(-1);
                                addkc.setIskoukeshi(false);
                                iPxkechengtableService.save(addkc);
                                kechengid = addkc.getId();

                            } else {
                                kechengid = kechengtab.get(0).getId();
                            }

                            if (classtab.size() == 0 && !buxiStyle.equals("一对一")) {
                                Pxclasstable newclass = new Pxclasstable();
//                                if (buxiStyle.equals("一对一")) {
//                                    if (className.contains("一对一")) {
//                                        Random rd = new Random();
//                                        int sjs = rd.nextInt(999);
//                                        String newclassName = stuName + "_" + kemuName + "_一对一" + sjs;
//                                        newclass.setClassName(newclassName);
//                                        newclass.setIs1v1Class(1);
//                                    }
//                                } else {
//                                    newclass.setClassName(className);
//                                    newclass.setIs1v1Class(0);
//                                }
                                newclass.setClassName(className);
                                newclass.setIs1v1Class(0);
                                newclass.setCampusID(cam.getId());
                                newclass.setIsShow(1);
                                newclass.setAddStaffID(staffID);
                                newclass.setAddTime(new Date());
                                newclass.setQiyeID(qiyeID);
                                newclass.setClassState(0);
                                iPxclasstableService.save(newclass);
                                banjiid = newclass.getId();
                            } else {
                                banjiid = classtab.get(0).getId();
                            }

                            //region 添加信息
                            //添加学员
                            Pxstutable stu = new Pxstutable();
                            stu.setStuName(stuName);
                            stu.setPasswd(DigestUtils.md5DigestAsHex("123456".getBytes()));
                            stu.setParentTel(parentTel);
                            stu.setActivity(2);
                            stu.setStuSex(stuSex);
                            String PR = "0";
                            if (parentRelation.equals("本人")) {
                                PR = "1";
                            } else if (parentRelation.equals("爸爸")) {
                                PR = "2";
                            } else if (parentRelation.equals("妈妈")) {
                                PR = "3";
                            } else if (parentRelation.equals("爷爷")) {
                                PR = "4";
                            } else if (parentRelation.equals("奶奶")) {
                                PR = "5";
                            } else if (parentRelation.equals("外公")) {
                                PR = "6";
                            } else if (parentRelation.equals("外婆")) {
                                PR = "7";
                            } else if (parentRelation.equals("保姆")) {
                                PR = "8";
                            } else {
                                PR = "9";
                            }
                            stu.setParentTelRelation(PR);
                            stu.setBuxiStateID(2);
                            stu.setStuGradeID(nianjiid);
                            stu.setCampusID(cam.getId());
                            stu.setQiyeID(qiyeID);
                            stu.setRemainXuefei(kczongjia);
                            stu.setStubirth(birthday);
                            stu.setLuruType(2);
                            stu.setDengjiTeacherID(staffID);
                            stu.setDengjiTime(new Date());

                            if (stuIntegar.compareTo(l) == 1) {
                                stu.setJifenNum(stuIntegar);
                            } else {
                                stu.setJifenNum(l);
                            }
                            stu.setStuXuexi(beizu);
                            iPxstutableService.save(stu);


                            //----自定义字段
                            if (zdlist.size() > 0) {
                                for (int m = 27; m < (27 + zdlist.size()); m++) {
                                    String zdy = (String) ((HashMap<String, Object>) zdlist.get(i)).get(m);
                                    String[] zdStr = zdy.split(",");
                                    String pvalue = zdStr[0];
                                    String pname = zdStr[1];

                                    int g = pname.indexOf("（自定义必填项）"); //去除（多余字符）
                                    String typename = pname.substring(0, g);
                                    QueryWrapper<Pxstuparamtypetable> stupQ = new QueryWrapper<>();
                                    stupQ
                                            .eq("stuParamTypeName", typename)
                                            .eq("qiyeID", qiyeID);
                                    List<Pxstuparamtypetable> selectp = iPxstuparamtypetableService.selectstuparamtype(stupQ);
                                    if (selectp.size() != 0) {
                                        Pxstuparamvaluetable spV = new Pxstuparamvaluetable();
                                        spV.setQiyeID(qiyeID);
                                        spV.setParamValue(pvalue);
                                        spV.setStuParamTypeID(selectp.get(0).getId());
                                        spV.setStuID(stu.getId());
                                        iPxstuparamvaluetableService.save(spV);
                                    }
                                }
                            }
                            //region 把该学员信息添加到服务端
                            //endregion

                            int qiandanstyle = 0;
                            if (moneyStyle.equals("新签")) {
                                qiandanstyle = 1;
                            } else if (moneyStyle.equals("续费")) {
                                qiandanstyle = 2;
                            }

                            List<Pxpaymoneystyletable> onePay = iPxpaymoneystyletableService.getOnePay(qiyeID, payMoneyStyle);
                            if (onePay.size() > 0) {
                                zhifufangshi = onePay.get(0).getId();
                            } else {
                                Pxpaymoneystyletable pay = new Pxpaymoneystyletable();
                                pay.setMoneystyleName(payMoneyStyle);
                                pay.setQiyeID(qiyeID);
                                iPxpaymoneystyletableService.save(pay);
                                zhifufangshi = pay.getId();
                            }

                            Pxqiandaninfotable qd = new Pxqiandaninfotable();
                            qd.setStuID(stu.getId());
                            qd.setQiandandate(Qdtime);
                            qd.setShishouTotalMoney(shishouTotalMoney);
                            qd.setHetongMoney(kczongjia);
                            qd.setMoneyStyle(qiandanstyle);
                            qd.setPayMoneyStyle(zhifufangshi);
                            qd.setQianDanStaffID(staffID);
                            qd.setRecordInStaffID(staffID);
                            qd.setRecordInTime(new Date());
                            qd.setCampusID(cam.getId());
                            qd.setQiandanType(2);
                            qd.setQiyeID(qiyeID);
                            qd.setBeizhu(beizu);
                            qd.setIsdingjing(1);
                            iPxqiandaninfotableService.save(qd);

                            //添加签单业绩人、支付方式
                            Pxqiandanstafftable qdst = new Pxqiandanstafftable();
                            qdst.setQiandanID(qd.getId());
                            qdst.setStaffID(staffID);
                            qdst.setYejiMoney(shishouTotalMoney);
                            qdst.setYejidateTime(Qdtime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                            qdst.setIsWeikuan(0);
                            qdst.setQiyeID(qiyeID);
                            iPxqiandanstafftableService.save(qdst);

                            Pxqiandanpaymoney qdPay = new Pxqiandanpaymoney();
                            qdPay
                                    .setQiandanID(qd.getId())
                                    .setPaymoneyStyleID(zhifufangshi)
                                    .setPayMoney(shishouTotalMoney)
                                    .setQianDanDate(Qdtime)
                                    .setIsWeikuan(0)
                                    .setQiyeID(qiyeID);
                            iPxqiandanpaymoneyService.save(qdPay);


                            Pxqiandansubjecttable qdSub = new Pxqiandansubjecttable();
                            qdSub.setStuID(stu.getId());
                            qdSub.setQiandandate(Qdtime);
                            qdSub.setKechengID(kechengid);//赞时替代--还未添加课程
                            qdSub.setKechengprice(xiandanjia);
                            qdSub.setOriginalprice(olddangjia);
                            qdSub.setBuykeshiNum(keshiNum);
                            qdSub.setZongjia(kczongjia);
                            qdSub.setStartDate(Stime);
                            qdSub.setEndDate(Etime);
                            qdSub.setQianDanInfoID(qd.getId());
                            qdSub.setKechengStyle(1);
                            ///通过BigDecimal的divide方法进行除法时当不整除，出现无限循环小数时，就会抛异常, 取小数后2位
                            qdSub.setDiscount(xiandanjia.divide(olddangjia, 2, BigDecimal.ROUND_HALF_EVEN).multiply(shi));
                            qdSub.setQiyeID(qiyeID);
                            iPxqiandansubjecttableService.save(qdSub);

                            //添加补习课程信息
                            Pxbuxikechengtable bxTab = new Pxbuxikechengtable();
                            bxTab.setStuID(stu.getId());
                            bxTab.setKechengID(kechengid);
                            bxTab.setKechengprice(xiandanjia);
                            bxTab.setOriginalprice(olddangjia);
                            bxTab.setRemainkeshi(keshiNum);
                            bxTab.setKeshiNum(keshiNum);
                            bxTab.setStartDate(Stime);
                            bxTab.setEndDate(Etime);
                            bxTab.setZongjia(kczongjia);
                            bxTab.setBuykeshiDateTime(new Date());
                            bxTab.setJifeiStyleID(jfstyle);
                            bxTab.setIsShow(1);
                            bxTab.setType(1);
                            bxTab.setQianDanInfoID(qd.getId());
                            bxTab.setQianDanSubjectID(qdSub.getId());
                            bxTab.setQiyeID(qiyeID);
                            iPxbuxikechengtableService.save(bxTab);

                            //如果是一对一，则建班,插班
                            Long ClassID = 0L;

                            if (buxiStyletab.size() != 0 && buxiStyletab.get(0).getBuxiStyleName() == "一对一") {
                                Boolean ii = false;
                                while (ii == false) {
                                    Random rd = new Random();
                                    int sjs = rd.nextInt(999);
                                    String newclassName = stuName + "_" + kemuName + "_一对一" + sjs;
                                    List<Pxclasstable> pdclass = iPxclasstableService.FkClassName(newclassName, qiyeID);
                                    if (pdclass.size() == 0) {
                                        Pxclasstable cl = new Pxclasstable();
                                        cl.setClassName(newclassName);
                                        cl.setCampusID(stu.getCampusID());
                                        cl.setIs1v1Class(1);
                                        cl.setIsShow(1);
                                        cl.setAddTime(new Date());
                                        cl.setAddStaffID(staffID);
                                        cl.setClassState(0);
                                        cl.setIsdelete(false);
                                        iPxclasstableService.save(cl);
                                        ClassID = cl.getId();
                                        ii = true;
                                    }
                                }

                                //一对一班级插班
                                Pxstuclasstable pxstuclass = new Pxstuclasstable();
                                pxstuclass.setQiyeID(qiyeID);
                                pxstuclass.setClassID(ClassID);
                                pxstuclass.setBuxiID(bxTab.getId());
                                iPxstuclasstableService.save(pxstuclass);
                            } else {
                                Pxstuclasstable stuclass = new Pxstuclasstable();
                                stuclass.setBuxiID(bxTab.getId());
                                stuclass.setClassID(banjiid);
                                stuclass.setQiyeID(qiyeID);
                                iPxstuclasstableService.save(stuclass);

                                //如果新加入的班级原有排课了、考勤状态是未完成状态，再把学生加在选课表里面去
                                List<Pxpaiketable> paikenews = iPxpaiketableService.getBykq(stuclass.getClassID(), qiyeID);
                                for (Pxpaiketable paikeItem : paikenews) {
                                    //没有选课数据插入
                                    List<Pxxuanketable> xuankeTable = iPxxuanketableService.xuankebypkstu(paikeItem.getId(), stu.getId(), qiyeID);
                                    if (xuankeTable.size() == 0) {
                                        Pxxuanketable xk = new Pxxuanketable();
                                        xk.setQiyeID(qiyeID);
                                        xk.setStuID(stu.getId());
                                        xk.setPaikeID(paikeItem.getId());
                                        xk.setType(1);
                                        xk.setBuxiID(bxTab.getId());
                                        xk.setRecordDate(new Date());
                                        iPxxuanketableService.save(xk);
                                    }
                                }

                            }

                            if (zengsongkeshi.compareTo(l) == 1) {
                                //添加增送补习课程信息
                                Pxbuxikechengtable zsbx = new Pxbuxikechengtable();
                                zsbx.setStuID(stu.getId());
                                zsbx.setKechengID(kechengid);
                                zsbx.setKechengprice(xiandanjia);
                                zsbx.setOriginalprice(olddangjia);
                                zsbx.setRemainkeshi(zengsongkeshi);
                                zsbx.setKeshiNum(zengsongkeshi);
                                zsbx.setStartDate(Stime);
                                zsbx.setEndDate(Etime);
                                zsbx.setZongjia(l);
                                zsbx.setBuykeshiDateTime(new Date());
                                zsbx.setJifeiStyleID(jfstyle);
                                zsbx.setIsShow(0);
                                zsbx.setType(2);
                                zsbx.setQiyeID(qiyeID);
                                iPxbuxikechengtableService.save(zsbx);

                                Pxkeshizengsongtable kszs = new Pxkeshizengsongtable();
                                kszs.setStuID(stu.getId());
                                kszs.setKechengID(kechengid);
                                kszs.setKeshiShu(zengsongkeshi);
                                kszs.setKechengPrice(xiandanjia);
                                kszs.setKeshiShu(keshiNum);
                                kszs.setCaozuoStaffId(staffID);
                                kszs.setAddDate(new Date());
                                kszs.setSongYangyin("签单课时赠送");
                                kszs.setJifeiStyle(jfstyle);
                                kszs.setQiandanInfoID(qd.getId());
                                kszs.setQiyeID(qiyeID);
                                iPxkeshizengsongtableService.save(kszs);

                                Pxkechengtable kcTab = iPxkechengtableService.getById(kechengid);
                                Pxbuxistyletable bxStyle = iPxbuxistyletableService.getById(kcTab.getBuxiStyleID());
                                if (bxStyle.getBuxiStyleName().equals("一对一")) {
                                    Pxsubjecttable subject = iPxsubjecttableService.getById(kcTab.getSubjectID());
                                    Long NewclassID = 0L;
                                    //建班插班
                                    Boolean ii = false;
                                    while (ii == false) {
                                        Random rd = new Random();
                                        int sjs = rd.nextInt(999);
                                        String newclassName = "(赠送)" + stuName + "_" + subject.getSubjectName() + "_一对一" + sjs;
                                        List<Pxclasstable> pdclass = iPxclasstableService.FkClassName(newclassName, qiyeID);
                                        if (pdclass.size() == 0) {
                                            Pxclasstable cl = new Pxclasstable();
                                            cl.setClassName(newclassName);
                                            cl.setCampusID(stu.getCampusID());
                                            cl.setIs1v1Class(1);
                                            cl.setIsShow(1);
                                            cl.setAddTime(new Date());
                                            cl.setAddStaffID(staffID);
                                            cl.setClassState(0);
                                            cl.setIsdelete(false);
                                            iPxclasstableService.save(cl);
                                            NewclassID = cl.getId();
                                            ii = true;
                                        }
                                    }

                                    //一对一班级插班
                                    Pxstuclasstable stucla = new Pxstuclasstable();
                                    stucla.setQiyeID(qiyeID);
                                    stucla.setClassID(NewclassID);
                                    stucla.setBuxiID(zsbx.getId());
                                    iPxstuclasstableService.save(stucla);
                                }
                            }

                            //添加流水账
                            Pxliushuizhangtable liushui = new Pxliushuizhangtable();
                            liushui.setLiushuiDateTime(new Date());
                            liushui.setCampusID(cam.getId());
                            liushui.setLiushuiZaiyao(cam.getCampusName() + "," + stu.getStuName() + "同学：" + moneyStyle + "：全款交费");
                            liushui.setPayMoneyStyle(zhifufangshi);
                            liushui.setShouruMoney(shishouTotalMoney);
                            liushui.setZhichuMoney(l);
                            liushui.setJinbanRen(staffID);
                            liushui.setShouzhiStyleID(1L);
                            liushui.setStuID(stu.getId());
                            liushui.setAddStaffID(staffID);
                            liushui.setLuruTime(new Date());
                            liushui.setQiandanID(qd.getId());
                            liushui.setQiyeID(qiyeID);
                            iPxliushuizhangtableService.save(liushui);

                            if (daijinquanMoney.compareTo(l) == 1) { //代金券金额>0
                                Pxdaijinquantable dj = new Pxdaijinquantable();
                                dj.setStuID(stu.getId());
                                dj.setQiandanID(qd.getId());
                                dj.setMoney(daijinquanMoney);
                                dj.setCreatTime(Qdtime);
                                dj.setStaffID(qd.getQianDanStaffID());
                                dj.setQiyeID(qiyeID);
                                iPxdaijinquantableService.save(dj);

                                //填充签单表的优惠信息
                                qd.setYouhuiID(dj.getId());
                                qd.setYouhuijine(daijinquanMoney.toString());
                                iPxqiandaninfotableService.updateById(qd);
                            }
                            if (stuIntegar.compareTo(l) == 1) {
                                Pxjifentable jf = new Pxjifentable();
                                jf.setStuID(stu.getId());
                                jf.setType(1);
                                jf.setOldintegral(l);
                                jf.setIntegral(stuIntegar);
                                jf.setStaffID(staffID);
                                jf.setCreatetime(new Date());
                                jf.setRemark("一键导入");
                                jf.setQiyeID(qiyeID);
                                iPxjifentableService.save(jf);
                            }

                            //endregion
                            //endregion
                        }
                        //endregion
                    }


                }
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                ajaxJson.setMsg("导入表格为空表");
                ajaxJson.setCode("N");
                return ajaxJson;
            }
            //endregion
        } else {
            //region 基础信息导入
            stuInfo = importstuFilesExcel.readExcel("", importStuInfoFilesVo.class, file, typeli);
            if (stuInfo.size() > 0) {
                if (stuInfo.size() > 0) {
                    List<importStuInfoFilesVo> li = stuInfo.get("list");
                    List zdlist = stuInfo.get("zdlist");
//                    for (importStuInfoFilesVo itemb : li) {
                    for (int n = 0; n < li.size(); n++) {
                        importStuInfoFilesVo itemb = li.get(n);
                        String stuName = itemb.getStuName();
                        String stuSex = itemb.getStuSex();
                        String parentTel = itemb.getParentTel();
                        String stuCampusName = itemb.getStuCampusName();
                        String stuGradeName = itemb.getStuGradeName();
                        BigDecimal stuIntegar = itemb.getStuIntegar();
                        String beizhu = itemb.getBeizhu();
                        String stuBirthDay = itemb.getStuBirthDay();
                        String parentRelation = itemb.getParentRelation();
                        String jijixing = itemb.getJijixing();
                        String xingge = itemb.getXingge();
                        String laoshiyaoqiu = itemb.getLaoshiyaoqiu();
                        String ruxuechengji = itemb.getRuxuechengji();

                        if (stuName == "" || stuName == null) {
                            ajaxJson.setCode("N");
                            ajaxJson.setMsg("第" + (n + 1) + "行，学员姓名为空");
                            return ajaxJson;
                        }
                        if (parentTel == "" || parentTel == null) {
                            ajaxJson.setCode("N");
                            ajaxJson.setMsg("第" + (n + 1) + "行，联系电话为空");
                            return ajaxJson;
                        }
                        if (stuCampusName == "" || stuCampusName == null) {
                            ajaxJson.setCode("N");
                            ajaxJson.setMsg("第" + (n + 1) + "行，学员校区为空");
                            return ajaxJson;
                        }
                        if (stuGradeName == "" || stuGradeName == null) {
                            ajaxJson.setCode("N");
                            ajaxJson.setMsg("第" + (n + 1) + "行，学员年级为空");
                            return ajaxJson;
                        }

                        if (parentRelation == "" || parentRelation == null) {
                            ajaxJson.setCode("N");
                            ajaxJson.setMsg("第" + (n + 1) + "行，联系电话关系为空");
                            return ajaxJson;
                        }

                        Pxcampustable cam = null;
                        List<Pxcampustable> oneCampus = iPxcampustableService.getOneCampus(qiyeID, stuCampusName);
                        if (oneCampus.size() == 0) {
                            ajaxJson.setCode("N");
                            ajaxJson.setMsg("第" + (n + 1) + "行，校区不存在");
                            return ajaxJson;
                        } else {
                            cam = oneCampus.get(0);
                        }

                        Date birthday = null;
                        try {
                            birthday = sdf.parse(stuBirthDay);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        QueryWrapper<Pxstutable> stuQ = new QueryWrapper<>();
                        stuQ
                                .eq("stuName", stuName)
                                .eq("qiyeID", qiyeID)
                                .eq("parentTel", parentTel);
                        List<Pxstutable> stuList = iPxstutableService.selectstu(stuQ);
                        if (stuList.size() > 0) {
                            ajaxJson.setCode("N");
                            ajaxJson.setMsg("第" + (n + 1) + "行，学生已存在");
                            return ajaxJson;
                        } else {

                            //region 不存在数据自动录入
                            Long nianjiid = 0L;//年级id
                            Long laiyuanid = 0L;//来源id
                            //"系统设置-招生途径 不允许为空，至少要有一个招生途径!
                            List<Pxyxtelfromtable> oneTelfrom = iPxyxtelfromtableService.getOneTelfrom(qiyeID);
                            if (oneTelfrom.size() == 0) {
                                Pxyxtelfromtable telf = new Pxyxtelfromtable();
                                telf.setQiyeID(qiyeID);
                                telf.setAddStaffID(staffID);
                                telf.setBeizhu("当前系统招生途径未设置，自动添加一条招生途经");
                                telf.setAddTime(new Date());
                                telf.setTelFromName("市场");
                                iPxyxtelfromtableService.save(telf);
                                laiyuanid = telf.getId();
                            } else {
                                laiyuanid = oneTelfrom.get(0).getId();
                            }

                            QueryWrapper<Pxstugradetable> gradeQ = new QueryWrapper<>();
                            gradeQ
                                    .eq("qiyeID", qiyeID)
                                    .eq("stuGradeName", stuGradeName);
                            List<Pxstugradetable> stuGradetab = iPxstugradetableService.selectstuGrade(gradeQ);
                            if (stuGradetab.size() == 0) {
                                Pxstugradetable grade = new Pxstugradetable();
                                grade.setQiyeID(qiyeID);
                                grade.setStugradename(stuGradeName);
                                iPxstugradetableService.save(grade);
                                nianjiid = grade.getId();
                            } else {
                                nianjiid = stuGradetab.get(0).getId();
                            }
                            //endregion
                            //region 添加StuTable，qianDanInfoTable~~~~

                            Pxstutable stu = new Pxstutable();
                            stu.setStuName(stuName);
                            stu.setPasswd(DigestUtils.md5DigestAsHex("123456".getBytes()));
                            stu.setParentTel(parentTel);
                            stu.setActivity(2);
                            stu.setStuSex(stuSex);
                            String PR = "0";
                            if (parentRelation.equals("本人")) {
                                PR = "1";
                            } else if (parentRelation.equals("爸爸")) {
                                PR = "2";
                            } else if (parentRelation.equals("妈妈")) {
                                PR = "3";
                            } else if (parentRelation.equals("爷爷")) {
                                PR = "4";
                            } else if (parentRelation.equals("奶奶")) {
                                PR = "5";
                            } else if (parentRelation.equals("外公")) {
                                PR = "6";
                            } else if (parentRelation.equals("外婆")) {
                                PR = "7";
                            } else if (parentRelation.equals("保姆")) {
                                PR = "8";
                            } else {
                                PR = "9";
                            }
                            stu.setParentTelRelation(PR);
                            stu.setBuxiStateID(2);
                            stu.setStuGradeID(nianjiid);
                            stu.setCampusID(cam.getId());
                            stu.setQiyeID(qiyeID);
                            stu.setRemainXuefei(l);
                            if (birthday != null) {
                                stu.setStubirth(birthday);
                            }
                            stu.setLuruType(2);
                            stu.setDengjiTeacherID(staffID);
                            stu.setDengjiTime(new Date());

                            if (stuIntegar.compareTo(l) == 1) { //>0
                                stu.setJifenNum(stuIntegar);
                            } else {
                                stu.setJifenNum(l);
                            }
                            stu.setStuXuexi(beizhu);
                            stu.setJijixing(jijixing);
                            stu.setXingge(xingge);
                            stu.setLaoshiyaoqiu(laoshiyaoqiu);
                            stu.setRuxuechengji(ruxuechengji);
                            iPxstutableService.save(stu);

                            if (stuIntegar.compareTo(l) == 1) {
                                Pxjifentable jf = new Pxjifentable();
                                jf.setStuID(stu.getId());
                                jf.setType(1);
                                jf.setOldintegral(l);
                                jf.setIntegral(stuIntegar);
                                jf.setStaffID(staffID);
                                jf.setCreatetime(new Date());
                                jf.setRemark("一键导入");
                                jf.setQiyeID(qiyeID);
                                iPxjifentableService.save(jf);
                            }

                            //----自定义字段
                            if (zdlist.size() > 0) {
                                for (int m = 13; m < (13 + typeli.size()); m++) {
                                    String zdy = (String) ((HashMap<String, Object>) zdlist.get(n)).get(m);
                                    String[] zdStr = zdy.split(",");
                                    String pvalue = zdStr[0];
                                    String pname = zdStr[1];

                                    int g = pname.indexOf("（自定义必填项）"); //去除（多余字符）
                                    String typename = pname.substring(0, g);
                                    QueryWrapper<Pxstuparamtypetable> stupQ = new QueryWrapper<>();
                                    stupQ
                                            .eq("stuParamTypeName", typename)
                                            .eq("qiyeID", qiyeID);
                                    List<Pxstuparamtypetable> selectp = iPxstuparamtypetableService.selectstuparamtype(stupQ);
                                    if (selectp.size() != 0) {
                                        Pxstuparamvaluetable spV = new Pxstuparamvaluetable();
                                        spV.setQiyeID(qiyeID);
                                        spV.setParamValue(pvalue);
                                        spV.setStuParamTypeID(selectp.get(0).getId());
                                        spV.setStuID(stu.getId());
                                        iPxstuparamvaluetableService.save(spV);
                                    }
                                }
                            }
                            //把该学员信息添加到服务端

                            //endregion
                        }
                    }
//                    importStuInfoFilesVo itemb = stuInfo.get(j);
                }
            } else {
                ajaxJson.setMsg("导入表格为空表");
                ajaxJson.setCode("N");
                return ajaxJson;
            }

            //endregion
        }
        return ajaxJson;
    }

    //endregion


    //endregion

    //region 分配班主任


    /**
     * @Description: getStuTearchPage方法作用:分页获取学员-分配班主任
     * @param:[current, size, id, Banzhuren, campusID, stuName]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:25
     */
    @ApiOperation(value = "分页获取分配班主任")
    @RequestMapping(value = "getStuTearchPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "Banzhuren", value = "班主任", required = false),
            @ApiImplicitParam(name = "stuName", value = "学员名称", required = false),
            @ApiImplicitParam(name = "id", value = "学员ID", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
    })
    public AjaxJson getStuTearchPage(
            Long current,
            Long size,
            Long id,
            String Banzhuren,
            Long campusID,
            String stuName,
            HttpServletRequest request
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<stuTearchVo> page = new Page(current, size);
        QueryWrapper<stuTearchVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (id != 0) {
            queryWrapper
                    .and(a -> a.isNotNull("a.zidingyiStuID").like("a.zidingyiStuID", id))
                    .or(b -> b.isNull("a.zidingyiStuID").eq("a.id", id));
        }
        if (StringUtils.isNotBlank(Banzhuren)) {
            if (Banzhuren.equals("NoHave5")) {
                queryWrapper.isNull("a.banzhurenTeacherID");
            } else {
                queryWrapper.like("pxstafftable.staffName", Banzhuren);
            }
        }
        if (campusID != 0) {
            queryWrapper.eq("a.campusID", campusID);
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("stuName", stuName);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 212);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }
        queryWrapper.orderByDesc("a.id");
        ajaxJson.setObj(iPxstutableService.getStuTearch(page, queryWrapper));
        return ajaxJson;
    }

    //region 分配班主任按钮集

    /**
     * @Description: GetBanzhurenFenpei()方法作用:获取在职的所有员工
     * @param:[request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/1/29 15:23
     */
    @ApiOperation(value = "获取班主任")
    @RequestMapping(value = "GetBanzhurenFenpei", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson GetBanzhurenFenpei(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", qiyeID);
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 212);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }
        ajaxJson.setObj(iPxstafftableService.GetBanzhurenFenpei(queryWrapper));
        return ajaxJson;
    }

    /**
     * @Description: getstuclassPage()方法作用:班级详情（分配班主任）
     * @param:[current, size, stuID, request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/1/29 15:23
     */
    @ApiOperation(value = "班级详情（分配班主任）")
    @RequestMapping(value = "getstuclassPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = false),
    })
    public AjaxJson getstuclassPage(Long current, Long size, Long stuID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<LookstuClassVo> page = new Page<>(current, size);
        ajaxJson.setObj(iPxclasstableService.getstuclassPage(page, stuID, qiyeID));
        return ajaxJson;
    }


    /**
     * @Description: updateTeacher方法作用:给学员分配班主任
     * @param:[stuIDs, teacher]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:25
     */
    @ApiOperation("分配班主任")
    @ResponseBody
    @RequestMapping(value = "FenPingTeacher", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson updateTeacher(@RequestBody stuTeaFrom from, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Long staffID = loginUser.getStaffID();
        String IDs = from.getIds();
        List<stuIDVo> stuIDVoList = JSON.parseArray(IDs, stuIDVo.class);
        for (stuIDVo item : stuIDVoList) {
            Pxstutable stu = iPxstutableService.getById(item.getId());
            stu.setBanzhurenTeacherID(from.getTeacher());
            stu.setYxfenpeiDate(new Date());
            stu.setYxfenpeistaffID(staffID);
            iPxstutableService.updateById(stu);
        }
        ajaxJson.setMsg("保存成功");
        return ajaxJson;
    }


    /**
     * @Description: deleteTeacher方法作用:清除班主任
     * @param:[stuIDs, teacher]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:25
     */
    @ApiOperation("清除班主任")
    @ResponseBody
    @RequestMapping(value = "deleteTeacher", method = RequestMethod.POST)
    public AjaxJson deleteTeacher(@RequestBody stuTeaFrom from, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        String IDs = from.getIds();
        List<stuIDVo> stuIDVoList = JSON.parseArray(IDs, stuIDVo.class);
        for (stuIDVo item : stuIDVoList) {
            Pxstutable stu = iPxstutableService.getById(item.getId());
            if (stu.getBanzhurenTeacherID() == null) {
                ajaxJson.setMsg("学员" + stu.getStuName() + "没有分配班主任");
                ajaxJson.setCode("N");
                return ajaxJson;
            }
            stu.setBanzhurenTeacherID(0L);

            stu.setYxfenpeistaffID(0L);
            iPxstutableService.updateById(stu);
        }
        return ajaxJson;
    }


    /**
     * @Description: exportFeedback方法作用:导出班主任
     * @param:[response, campusID]
     * @return:void
     * @auter:yyl
     * @data:2020/11/30 16:25
     */
    @ApiOperation("导出班主任")
    @ResponseBody
    @RequestMapping(value = "ExportstuTeacher", method = RequestMethod.GET)
    @ApiImplicitParam(name = "campusID", value = "校区ID", required = false)
    public void ExportstuTeacher(HttpServletResponse response, HttpServletRequest request,
                                 @RequestParam(required = false) String campusID // 校区ID
    ) {
        QueryWrapper<stuTearchVo> queryWrapper = new QueryWrapper<>();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("a.campusID", campusID);
        }
        List<stuTearchVo> stuTearchVos = iPxstutableService.ExportstuTeacher(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "学号", "姓名", "班主任", "分配人", "分配日期"},
                stuTearchVos,
                new String[]{"campusName", "stuID", "stuName", "Banzhuren", "yxfenpeistaffID", "yxfenpeiDate-D"});

        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "班主任导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion

    //endregion

    //region 学员卡


    /**
     * @Description: getStuCardPage方法作用:分页获取学员卡
     * @param:[current, size, stuID, stuGradeID, campusID, stuName, cardID]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:25
     */
    @ApiOperation(value = "分页获取获取学员卡")
    @RequestMapping(value = "getStuCardPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "stuName", value = "学员名称", required = false),
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "cardID", value = "学员卡ID", required = false),
            @ApiImplicitParam(name = "stuGradeID", value = "年级ID", required = false),
    })
    public AjaxJson getStuCardPage(
            Long current,
            Long size,
            Long stuID,
            Long stuGradeID,
            Long campusID,
            String stuName,
            String cardID,
            HttpServletRequest request
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<stuCardVo> page = new Page(current, size);
        QueryWrapper<stuCardVo> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("a.qiyeID", qiyeID);
        if (stuID != 0) {
            queryWrapper
                    .and(a -> a.isNotNull("a.zidingyiStuID").like("a.zidingyiStuID", stuID))
                    .or(b -> b.isNull("a.zidingyiStuID").eq("a.id", stuID));
        }
        if (StringUtils.isNotBlank(cardID)) {
            queryWrapper.like("pxstucardtable.cardNumber", cardID);
        }
        if (stuGradeID != 0) {
            queryWrapper.eq("a.stuGradeID", stuGradeID);
        }
        if (campusID != 0) {
            queryWrapper.eq("a.campusID", campusID);
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("a.stuName", stuName);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 213);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }
        queryWrapper.orderByDesc("a.id");
        ajaxJson.setObj(iPxstutableService.getStuCard(page, queryWrapper));
        return ajaxJson;
    }

    //region 学员卡按钮集


    /**
     * @Description: ExportStuCard方法作用:导出学员卡
     * @param:[response, campusID, stuGradeID]
     * @return:void
     * @auter:yyl
     * @data:2020/11/30 16:26
     */
    @RequestMapping(value = "ExportStuCard", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出学员卡")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "stuGradeID", value = "年级ID", required = false),
    })
    public void ExportStuCard(HttpServletResponse response, HttpServletRequest request,
                              @RequestParam(required = false) String campusID, // 校区ID
                              @RequestParam(required = false) String stuGradeID // 年级ID
    ) {
        QueryWrapper<stuCardVo> queryWrapper = new QueryWrapper<>();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("a.campusID", campusID);
        }
        if (StringUtils.isNotBlank(stuGradeID)) {
            queryWrapper.eq("a.stuGradeID", stuGradeID);
        }
        List<stuCardVo> stuCardVos = iPxstutableService.ExportStuCard(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"学员姓名", "学员学号", "学员卡号", "所属校区", "年级"},
                stuCardVos,
                new String[]{"stuName", "stuID", "cardID", "campusName", "stuGradeName"});

        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "学员卡导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @Description: updateCardID方法作用:绑定|修改 学员卡
     * @param:[stuID, cardNum]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:26
     */
    @ApiOperation(value = "修改与绑定学员卡")
    @ResponseBody
    @RequestMapping(value = "updateCardID", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true),
            @ApiImplicitParam(name = "cardNum", value = "学员卡号", required = true)
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson updateCardID(Long stuID, String cardNum, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();

        QueryWrapper<Pxstucardtable> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .ne("stuID", stuID)
                .eq("cardNumber", cardNum)
                .eq("qiyeID", qiyeID);
//        List<Pxstucardtable> card = iPxstucardtableService.getCard(stuID, cardNum);
        List<Pxstucardtable> card = iPxstucardtableService.getcfCard(queryWrapper);

        if (card.size() != 0) {
            ajaxJson.setMsg("该卡号已经被绑定了");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        List<Pxstucardtable> stu = iPxstucardtableService.addUpdateCard(stuID, Long.valueOf(qiyeID));

        if (stu.size() == 0) {
            //没有--添加
            Pxstucardtable pxstucardtable = new Pxstucardtable();
            pxstucardtable.setStuID(stuID);
            pxstucardtable.setAdddate(new Date());
            pxstucardtable.setCardnumber(cardNum);
            pxstucardtable.setAddStaffID(staffID);//获取当前登录用户
            pxstucardtable.setQiyeID(qiyeID);
            iPxstucardtableService.save(pxstucardtable);

        } else if (stu != null) {
            //有--修改
            Pxstucardtable cd = stu.get(0);
            //String oldcardNumber = pxstucardtable.getCardNumber();//原来的学员卡ID
            cd.setCardnumber(cardNum);
            iPxstucardtableService.updateById(cd);
        }
        return ajaxJson;
    }


    /**
     * @Description: delstuCard方法作用:删除学员卡绑定
     * @param:[stuID]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:26
     */
    @ApiOperation("删除学员卡绑定")
    @ResponseBody
    @RequestMapping(value = "delstuCard", method = RequestMethod.DELETE)
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delstuCard(@RequestBody delstuFrom from, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Long staffID = loginUser.getStaffID();
        String IDs = from.getIds();
        List<stuIDVo> stuIDVoList = JSON.parseArray(IDs, stuIDVo.class);
        for (stuIDVo item : stuIDVoList) {
            Pxstutable stu = iPxstutableService.getById(item.getId());
            if (stu == null) {
                ajaxJson.setMsg("没有这个学员");
                ajaxJson.setCode("N");
                return ajaxJson;
            }
            List<Pxstucardtable> stucard = iPxstucardtableService.addUpdateCard(item.getId(), qiyeID);
            if (stucard.size() == 0) {
                ajaxJson.setMsg("学员:" + stu.getStuName() + ",没有绑定学生卡");
                ajaxJson.setCode("N");
                return ajaxJson;
            } else {
                Long id = stucard.get(0).getId();
                iPxstucardtableService.removeById(id);
            }

        }
        ajaxJson.setMsg("操作完成");
        return ajaxJson;
    }
    //endregion

    //endregion

    //region 学员积分


    /**
     * @Description: getStuIntegralPage方法作用:分页获取学员积分
     * @param:[current, size, stuID, campusID, stuName, Integral, jingbanStaff, stuGradeID, type, remark]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:26
     */
    @ApiOperation(value = "分页获取学员积分")
    @RequestMapping(value = "getStuIntegralPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "stuName", value = "学员名称", required = false),
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = false),
            @ApiImplicitParam(name = "jingbanStaff", value = "经办人", required = false),
            @ApiImplicitParam(name = "stuGradeID", value = "年级", required = false),
            @ApiImplicitParam(name = "starDate", value = "开始时间", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束时间", required = false),
            @ApiImplicitParam(name = "type", value = "积分变动类型", example = "-1", required = false),
    })
    public AjaxJson getStuIntegralPage(
            Long current,
            Long size,
            String stuID,
            String campusID,
            String stuName,
            String starDate,
            String endDate,
            String jingbanStaff,
            String stuGradeID,
            int type,
            HttpServletRequest request
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<stuIntegralVo> page = new Page(current, size);
        QueryWrapper<stuIntegralVo> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("evalua.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(stuID)) {
            queryWrapper
                    .and(a -> a.isNotNull("pxstutable.zidingyiStuID").like("pxstutable.zidingyiStuID", stuID))
                    .or(b -> b.isNull("pxstutable.zidingyiStuID").eq("pxstutable.id", stuID));
        }
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("pxstutable.campusID", campusID);
        }
        if (StringUtils.isNotBlank(starDate) && StringUtils.isNotBlank(endDate)) {
            queryWrapper.ge("evalua.createTime", starDate).le("evalua.createTime", endDate);
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("stuName", stuName);
        }
        if (StringUtils.isNotBlank(jingbanStaff)) {
            queryWrapper.like("pxstafftable.staffName", jingbanStaff);
        }
        if (StringUtils.isNotBlank(stuGradeID)) {
            queryWrapper.eq("pxstutable.stuGradeID", stuGradeID);
        }
        if (type != -1) {
            queryWrapper.eq("type", type);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 214);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("pxstutable.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("pxstutable.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("pxstutable.campusID", lookPower);
        }
        queryWrapper.orderByDesc("evalua.createTime");
        ajaxJson.setObj(iPxstutableService.getStuIntegral(page, queryWrapper));
        return ajaxJson;
    }


    //region 学员积分按钮集


    /**
     * @Description: addJiFen方法作用:增加积分记录
     * @param:[pxjifentable, Notice]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:29
     */
    @ApiOperation(value = "添加积分")
    @ResponseBody()
    @RequestMapping(value = "addJiFen", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true),
            @ApiImplicitParam(name = "type", value = "类型：1增加 2扣减", required = true),
            @ApiImplicitParam(name = "integral", value = "变动积分数", required = true),
            @ApiImplicitParam(name = "remark", value = "备注说明", required = false),
            @ApiImplicitParam(name = "Notice", value = "是否推送给家长", required = false),
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addJiFen(String stuID, int type, BigDecimal integral, String remark,
                             @RequestBody(required = false) Boolean Notice, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();


        //获取学员信息
        Pxstutable stu = iPxstutableService.getById(stuID);
        //当前积分
        BigDecimal oldjifenNum = stu.getJifenNum();
        Pxjifentable JF = new Pxjifentable();

        if (type == 1) {
            //添加积分
            stu.setJifenNum(oldjifenNum.add(integral));//加上积分

            JF.setType(1);
        } else if (type == 2) {
            //扣减积分
            BigDecimal num1 = new BigDecimal("-1");
            JF.setIntegral(integral.multiply(num1));
            stu.setJifenNum(oldjifenNum.subtract(integral));//扣减
            JF.setType(2);
        }
        //保存学员表
        iPxstutableService.updateById(stu);
        //填充数据
        JF.setIntegral(integral);
        JF.setStuID(Long.valueOf(stuID));
        JF.setQiyeID(qiyeID);//获取当前登录者的企业ID
        JF.setStaffID(staffID);//需要改为获取去当前登录者
        JF.setCreatetime(new Date());
        JF.setOldintegral(oldjifenNum);//原来的积分
        JF.setRemark(remark);
        //添加积分表记录
        ajaxJson.setSuccess(iPxjifentableService.save(JF));
        return ajaxJson;
    }


    /**
     * @Description: delJiFen方法作用:删除积分记录
     * @param:[jfIDs]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:29
     */
    @RequestMapping(value = "delJiFen", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除积分")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jfIDs", value = "积分记录IDs", required = true),
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delJiFen(@RequestBody deljifenFrom from, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        String jfIDs = from.getIds();
        List<delJFVo> PJfIDs = JSON.parseArray(jfIDs, delJFVo.class);
        for (delJFVo item : PJfIDs) {
            Pxjifentable jifen = iPxjifentableService.getById(item.getJfID());
            if (jifen != null) {
                BigDecimal oldIntegral = jifen.getOldintegral();//原来的积分
                //学员表退回原来的积分数
                Pxstutable stu = iPxstutableService.getById(jifen.getStuID());
                stu.setJifenNum(oldIntegral);
                iPxstutableService.updateById(stu);
            }
            iPxjifentableService.removeById(item.getJfID());
        }
        ajaxJson.setMsg("操作完成");
        return ajaxJson;
    }

    @RequestMapping(value = "getJiFenByID", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "修改获取积分信息")
    @ApiImplicitParams(@ApiImplicitParam(name = "jfID", value = "积分ID", required = true))
    public AjaxJson getJiFenByID(String jfID) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxjifentableService.getById(jfID));
        return ajaxJson;
    }

    /**
     * @Description: editJiFen方法作用:修改积分记录
     * @param:[jfID, newtype, integral, remark, Notice]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:29
     */
    @RequestMapping(value = "editJiFen", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改积分")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jfID", value = "积分ID", required = true),
            @ApiImplicitParam(name = "type", value = "类型：1增加 2扣减", required = true),
            @ApiImplicitParam(name = "integral", value = "变动积分数", required = true),
            @ApiImplicitParam(name = "remark", value = "备注说明", required = false),
            @ApiImplicitParam(name = "Notice", value = "是否推送给家长", required = false),
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson editJiFen(String jfID, int type, BigDecimal integral, String remark, int Notice,
                              HttpServletRequest request) {

        BigDecimal num1 = new BigDecimal("-1");
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();

        Pxjifentable JF = iPxjifentableService.getById(jfID);
        //获取原有类型
        //获取记录有关的学员信息
        Pxstutable stu = iPxstutableService.getById(JF.getStuID());
        if (type == 1) {
            //原：添加类型
            if (type == 1) {
                //学员积分加上：原有积分-变动记录积分
                stu.setJifenNum(stu.getJifenNum().add(integral.subtract(JF.getIntegral())));
                //变动记录修改积分数
                JF.setIntegral(integral);
            } else if (type == 2) {
                stu.setJifenNum(stu.getJifenNum().subtract(integral.add(JF.getIntegral())));
                JF.setIntegral(integral.multiply(num1));//扣减值为负
            }
        } else if (type == 2) {
            //原：扣减类型
            if (type == 1) {
                stu.setJifenNum(stu.getJifenNum().add(integral.subtract(JF.getIntegral())));
                JF.setIntegral(integral);
            } else if (type == 2) {
                stu.setJifenNum(stu.getJifenNum().subtract(integral.subtract(JF.getIntegral())));
                JF.setIntegral(integral.multiply(num1));
            }
        }
        //修改数据
        JF.setType(type);
        JF.setRemark(remark);
        JF.setCreatetime(new Date());
        JF.setStaffID(staffID);//改为当前登录人
        JF.setQiyeID(qiyeID);
        iPxstutableService.updateById(stu);
        ajaxJson.setSuccess(iPxjifentableService.updateById(JF));
        return ajaxJson;
    }


    /**
     * @Description: exportJiFen方法作用:导出
     * @param:[response, campusID, stuGradeID]
     * @return:void
     * @auter:yyl
     * @data:2020/11/30 16:29
     */
    @RequestMapping(value = "exportJiFen", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出学员积分")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "type", value = "类型", required = false),
    })
    public void exportJiFen(HttpServletResponse response, HttpServletRequest request,
                            @RequestParam(required = false) String campusID, // 校区ID
                            @RequestParam(required = false) int type

    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper<stuIntegralVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("evalua.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("pxstutable.campusID", campusID);
        }
        if (type != -1) {
            queryWrapper.eq("evalua.type", type);
        }
        List<stuIntegralVo> stuIntegralVos = iPxjifentableService.ExportIntegral(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "学号", "姓名", "年级", "变动积分", "变动时间", "经办人", "变动类型", "变动说明"},
                stuIntegralVos,
                new String[]{"campusName", "stuID", "stuName", "stuGradeName", "integral", "createTime-D", "jingbanStaff",
                        "type", "remark"});
        //这里的integral是计算好的变动积分数
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "学员积分导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @Description: getJFpaimingPage方法作用:分页获取积分排名
     * @param:[current, size]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:29
     */
    @ApiOperation("分页获取获取积分排名")
    @ResponseBody
    @RequestMapping(value = "getJFpaimingPage", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
    })
    public AjaxJson getJFpaimingPage(int current, int size, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        QueryWrapper<JFRankVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pxstutable.qiyeID", qiyeID);
        Page<JFRankVo> page = new Page(current, size);
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 214);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("pxstutable.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("pxstutable.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("pxstutable.campusID", lookPower);
        }
        ajaxJson.setObj(iPxstutableService.getJFpaiming(page, queryWrapper));
        return ajaxJson;
    }

    //endregion
    //endregion

    //region 年级/年龄段升级


    /**
     * @Description: getStuGradePages方法作用:获取学员年级|年龄段
     * @param:[current, size, stuID, campusID, stuName, stuGradeName]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:30
     */
    @ApiOperation(value = "分页获取学员年级|年龄段")
    @RequestMapping(value = "/getStuGragePage", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "stuName", value = "学员名称", required = false),
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = false),
            @ApiImplicitParam(name = "stuGradeID", value = "年级", required = false),
    })
    public AjaxJson getStuGradePages(
            Long current,
            Long size,
            String stuID,
            String campusID,
            String stuName,
            String stuGradeID,
            HttpServletRequest request
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<stuGradeVo> page = new Page(current, size);
        QueryWrapper<stuGradeVo> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("evalua.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(stuID)) {
            queryWrapper
                    .and(a -> a.isNotNull("evalua.zidingyiStuID").like("evalua.zidingyiStuID", stuID))
                    .or(b -> b.isNull("evalua.zidingyiStuID").eq("evalua.id", stuID));
        }
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("evalua.campusID", campusID);
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("stuName", stuName);
        }
        if (StringUtils.isNotBlank(stuGradeID)) {
            queryWrapper.eq("evalua.stuGradeID", stuGradeID);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 215);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("evalua.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("evalua.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("evalua.campusID", lookPower);
        }
        queryWrapper.orderByDesc("evalua.id");
        ajaxJson.setObj(iPxstutableService.getStuGradeList(page, queryWrapper));
        return ajaxJson;
    }

    //region 年级/年龄段按钮集


    /**
     * @Description: updateStuGrade方法作用:调级
     * @param:[pxstutable]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:30
     */
    @ApiOperation(value = "调级")
    @ResponseBody
    @RequestMapping(value = "updateStuGrade", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson updateStuGrade(@RequestBody stugradeFrom from, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());

        String IDs = from.getIds();
        List<stuIDVo> stuIDVoList = JSON.parseArray(IDs, stuIDVo.class);
        Long stuGradeID = from.getStuGradeID();
        for (stuIDVo item : stuIDVoList) {

            //获取原数据
            Pxstutable stu = iPxstutableService.getById(item.getId());
            Long oldGradeID = stu.getStuGradeID();
            if (oldGradeID == stuGradeID) {
                ajaxJson.setMsg("同学" + stu.getStuName() + "已是目标年级的学员");
                ajaxJson.setCode("N");
                return ajaxJson;
            }

            //添加调级记录
            Pxgradeupdatetable pxgradeupdatetable = new Pxgradeupdatetable();
            pxgradeupdatetable.setStuID(item.getId());//学号
            pxgradeupdatetable.setOldgrade(oldGradeID);//原年级
            pxgradeupdatetable.setNowgrade(stuGradeID);//调整后年级
            pxgradeupdatetable.setAddDate(new Date());//添加时间
            pxgradeupdatetable.setAddStaffID(staffID);
            pxgradeupdatetable.setQiyeID(qiyeID);
            iPxgradeupdatetableService.save(pxgradeupdatetable);
            //修改
            stu.setStuGradeID(stuGradeID);
            iPxstutableService.updateById(stu);
        }

        return ajaxJson;
    }

    //region 调级记录


    /**
     * @Description: getGradeJiLuPage方法作用:分页获取调级记录
     * @param:[current, size, stuID, campusID, stuName]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:30
     */
    @ApiOperation(value = "分页获取调级记录")
    @RequestMapping(value = "getGradeJiLuPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "stuName", value = "学员名称", required = false),
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = false),
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson getGradeJiLuPage(Long current,
                                     Long size,
                                     String stuID,
                                     String campusID,
                                     String stuName, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<gradeNotesVo> page = new Page(current, size);
        QueryWrapper<gradeNotesVo> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("jl.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(stuID)) {
            queryWrapper
                    .and(a -> a.isNotNull("pxstutable.zidingyiStuID").like("pxstutable.zidingyiStuID", stuID))
                    .or(b -> b.isNull("pxstutable.zidingyiStuID").eq("pxstutable.id", stuID));
        }
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("pxstutable.campusID", campusID);
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("pxstutable.stuName", stuName);
        }
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 215);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("pxstutable.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("pxstutable.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("pxstutable.campusID", lookPower);
        }
        queryWrapper.orderByDesc("jl.addDate");
        ajaxJson.setObj(iPxstutableService.getGradeJiLu(page, queryWrapper));
        return ajaxJson;
    }


    /**
     * @Description: editGrade方法作用:撤销升级
     * @param:[pxgradeupdatetable]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:30
     */
    @RequestMapping(value = "editGrade", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "撤销升级")
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson editGrade(@RequestBody updategradeFrom from) {
        AjaxJson ajaxJson = new AjaxJson();
        String IDs = from.getIds();
        List<stuIDVo> stuIDVoList = JSON.parseArray(IDs, stuIDVo.class);//调级ID

        for (stuIDVo item : stuIDVoList) {
            //按照调级记录ID获取有关数据
            Pxgradeupdatetable byId = iPxgradeupdatetableService.getById(item.getId());
            Pxstutable stu = iPxstutableService.getById(byId.getStuID());
            //比较年级是否正确
            if (!stu.getStuGradeID().equals(byId.getNowgrade())) {
                ajaxJson.setMsg("记录年级与现在的年级不符!撤销升级失败");
                ajaxJson.setCode("N");
                return ajaxJson;
            }
            //保存撤销升级，删除调级记录
            stu.setStuGradeID(byId.getOldgrade());
            iPxstutableService.updateById(stu);
            iPxgradeupdatetableService.removeById(item.getId());
        }
        return ajaxJson;
    }
    //endregion

    //endregion

    //endregion

    //region 学员生日


    /**
     * @Description: getStuBirthPage方法作用:分页获取学员生日
     * @param:[current, size, stuID, campusID, stuName, stuSex, banzhuren, stuGradeID]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:30
     */
    @ApiOperation(value = "分页获取学员生日")
    @RequestMapping(value = "getStuBirthPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "stuName", value = "学员名称", required = false),
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = false),
            @ApiImplicitParam(name = "stuGradeID", value = "年级ID", required = false),
            @ApiImplicitParam(name = "month", value = "月", example = "-1", required = true),
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson getStuBirthPage(
            Long current,
            Long size,
            String stuID,
            String campusID,
            String stuName,
            String stuGradeID,
            int month,
            HttpServletRequest request
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<stuBirthVo> page = new Page(current, size);
        QueryWrapper<stuBirthVo> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("a.qiyeID", qiyeID);

        if (StringUtils.isNotBlank(stuID)) {
            queryWrapper
                    .and(a -> a.isNotNull("a.zidingyiStuID").like("a.zidingyiStuID", stuID))
                    .or(b -> b.isNull("a.zidingyiStuID").eq("a.id", stuID));
        }
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("a.campusID", campusID);
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("stuName", stuName);
        }
        if (StringUtils.isNotBlank(stuGradeID)) {
            queryWrapper.eq("a.stuGradeID", stuGradeID);
        }
        if (month != -1) {
            queryWrapper.eq("month(a.stubirth)", month);
//                    ge("a.stubirth", startDate).le("a.stubirth", endDate);
        }
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 216);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }
        queryWrapper.orderByDesc("a.id");
        ajaxJson.setObj(iPxstutableService.getStuBirth(page, queryWrapper));
        return ajaxJson;
    }

    //region 学员生日按钮集


    /**
     * @Description: updateBirth方法作用:修改学员生日
     * @param:[pxstutable]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:31
     */
    @ApiOperation(value = "修改学员生日")
    @ResponseBody
    @RequestMapping(value = "updateBirth", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "学员ID", required = true),
            @ApiImplicitParam(name = "stubirth", value = "生日", required = true)
    })
    public AjaxJson updateBirth(String id, String stubirth) {
        AjaxJson ajaxJson = new AjaxJson();
        Pxstutable stu = iPxstutableService.getById(id);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); //转换成日期格式
        Date birth = new Date();
        try {
            birth = df.parse(stubirth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        stu.setStubirth(birth);
        ajaxJson.setSuccess(iPxstutableService.updateById(stu));
        return ajaxJson;
    }


    /**
     * @Description: ExportStuBirth方法作用:导出学员生日
     * @param:[response, campusID, stuGradeID, startDate, endDate]
     * @return:void
     * @auter:yyl
     * @data:2020/11/30 16:31
     */
    @RequestMapping(value = "ExportStuBirth", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出学员生日")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuGradeID", value = "年级ID", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "startDate", value = "开始时间", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束时间", required = false),
    })
    public void ExportStuBirth(HttpServletResponse response, HttpServletRequest request,
                               @RequestParam(required = false) String campusID, // 校区ID
                               @RequestParam(required = false) String stuGradeID, // 校区ID
                               @RequestParam(required = false) String startDate, // 开始日期
                               @RequestParam(required = false) String endDate // 结束日期
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper<stuBirthVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("a.campusID", campusID);
        }
        if (StringUtils.isNotBlank(stuGradeID)) {
            queryWrapper.eq("a.stuGradeID", stuGradeID);
        }
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("a.stubirth", startDate);
        }

        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("a.stubirth", endDate);
        }
        List<stuBirthVo> stuBirthVos = iPxstutableService.ExportStuBirth(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"学号", "自定义学号", "校区", "年级", "姓名", "生日"},
                stuBirthVos,
                new String[]{"stuID", "zidingyiStuID", "campusName", "stuGradeName", "stuName", "stubirth"});

        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "学员生日导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion

    //endregion

    //region 住宿


    /**
     * @Description: getStuStayPage方法作用:获取学员住宿
     * @param:[current, size, stuID, campusID, stuName, stuGradeName, roomName, banzhuren]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:31
     */
    @ApiOperation(value = "分页获取学员住宿")
    @RequestMapping(value = "getStuStayPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "stuName", value = "学员名称", required = false),
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = false),
            @ApiImplicitParam(name = "stuGradeID", value = "年级", required = false),
            @ApiImplicitParam(name = "roomName", value = "宿舍号", required = false),

    })
    public AjaxJson getStuStayPage(
            Long current,
            Long size,
            String stuID,
            String campusID,
            String stuName,
            String stuGradeID,
            String roomName,
            HttpServletRequest request
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<stuStayVo> page = new Page(current, size);
        QueryWrapper<stuStayVo> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("evalua.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(stuID)) {
            queryWrapper
                    .and(a -> a.isNotNull("evalua.zidingyiStuID").like("evalua.zidingyiStuID", stuID))
                    .or(b -> b.isNull("evalua.zidingyiStuID").eq("evalua.id", stuID));
        }
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("evalua.campusID", campusID);
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("stuName", stuName);
        }
        if (StringUtils.isNotBlank(stuGradeID)) {
            queryWrapper.eq("pxstugradetable.id", stuGradeID);
        }
        if (StringUtils.isNotBlank(roomName)) {
            queryWrapper.like("pxroomtable.number", roomName);
        }
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 217);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("evalua.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("evalua.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("evalua.campusID", lookPower);
        }
        queryWrapper.orderByDesc("evalua.id");
        ajaxJson.setObj(iPxstutableService.getStuStay(page, queryWrapper));
        return ajaxJson;
    }

    //region 住宿的按钮

    /**
     * @Description: getOkList()方法作用:获取学员宿舍
     * @param:[request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/2/2 19:06
     */
    @ApiOperation(value = "获取学员宿舍")
    @ResponseBody
    @RequestMapping(value = "getOkList", method = RequestMethod.GET)
    public AjaxJson getOkList(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        ajaxJson.setObj(iPxroomtableService.getOkList(qiyeID));
        return ajaxJson;
    }

    /**
     * @Description: updateRoom方法作用:修改学员住宿--分配宿舍号
     * @param:[stuIDs, roomID]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:31
     */
    @ApiOperation(value = "分配学员宿舍")
    @ResponseBody
    @RequestMapping(value = "updateRoom", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson updateRoom(@RequestBody stustayFrom from, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        String IDs = from.getIds();
        Long roomID = Long.valueOf(from.getRoomID());
        List<stuIDVo> stuIDVoList = JSON.parseArray(IDs, stuIDVo.class);

        Integer renshu = iPxroomtableService.getById(roomID).getRenshu();//获取到宿舍最大人数
        int newNum = iPxstutableService.getnewNum(roomID, qiyeID).size();
        if (newNum + stuIDVoList.size() > renshu) {
            ajaxJson.setMsg("!!!最多可入住" + renshu + "人,目前已入住：" + newNum + "人,现要添加" + stuIDVoList.size() + "人，请重新设置");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        for (stuIDVo item : stuIDVoList) {
            Pxstutable stu = iPxstutableService.getById(item.getId());
            stu.setRoomid(roomID);
            iPxstutableService.updateById(stu);
        }
//        Pxstutable pxstutable = new Pxstutable();
//        pxstutable.setRoomid(roomID);
//        UpdateWrapper<Pxstutable> updateWrapper = new UpdateWrapper<>();
//        updateWrapper.in("id", stuList);
//        ajaxJson.setSuccess(iPxstutableService.update(pxstutable, updateWrapper));
        return ajaxJson;
    }


    /**
     * @Description: delStuStay方法作用:删除学员住宿
     * @param:[stIDs]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:31
     */
    @ApiOperation(value = "删除学员住宿")
    @RequestMapping(value = "delStuStay", method = RequestMethod.POST)
    @ResponseBody()
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delStuStay(@RequestBody stustayFrom from, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        AjaxJson ajaxJson = new AjaxJson();
        String IDs = from.getIds();
        List<stuIDVo> stuIDVoList = JSON.parseArray(IDs, stuIDVo.class);
        for (stuIDVo item : stuIDVoList) {
            Pxstutable stu = iPxstutableService.getById(item.getId());
            if (stu.getRoomid() == null || stu.getRoomid().toString().equals("")) {
                ajaxJson.setMsg("学员：" + stu.getStuName() + "没有分配宿舍");
                ajaxJson.setCode("N");
                return ajaxJson;
            }
            stu.setRoomid(0L);
            iPxstutableService.updateById(stu);
        }

        return ajaxJson;
    }


    /**
     * @Description: exportStuRoom方法作用:导出学员住宿
     * @param:[campusID, stuGradeName, response]
     * @return:void
     * @auter:yyl
     * @data:2020/11/30 16:32
     */
    @ApiOperation("导出学员住宿")
    @ResponseBody
    @RequestMapping(value = "exportStuRoom", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "stuGradeName", value = "年级", required = false),
    })
    public void exportStuRoom(HttpServletRequest request,
                              @RequestParam(required = false) String campusID,
                              @RequestParam(required = false) String stuGradeName,
                              HttpServletResponse response) {
        QueryWrapper queryWrapper = new QueryWrapper();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        queryWrapper.eq("stu.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("pxcampustable.id", campusID);
        }
        if (StringUtils.isNotBlank(stuGradeName)) {
            queryWrapper.eq("pxstugradetable.stuGradeName", stuGradeName);
        }
        List<ExportStuRoomVo> exportStuRoomVos = iPxroomtableService.exportRoom(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "学号", "学员", "年级", "宿舍号", "班主任"},
                exportStuRoomVos,
                new String[]{"campusName", "stuID", "stuName", "stuGradeName", "RoomNum", "banzhuren"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "学员住宿导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //region 宿舍管理按钮


    /**
     * @Description: getStuClassPage方法作用:分页获取宿舍管理
     * @param:[current, size, campusID, RoomNumber]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:32
     */
    @ApiOperation(value = "分页获取宿舍管理")
    @RequestMapping(value = "roomManage/getRmManagePage", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "RoomNumber", value = "宿舍号", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区名称", required = false),
    })
    public AjaxJson getRmManagePage(
            Long current,
            Long size,
            String campusID,
            String RoomNumber,
            HttpServletRequest request
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<RoomManaVo> page = new Page<>(current, size);
        QueryWrapper<RoomManaVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("rm.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(RoomNumber)) {
            queryWrapper.eq("rm.number", RoomNumber);
        }
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("rm.campusID", campusID);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 217);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("rm.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限l
            queryWrapper.eq("rm.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("rm.campusID", lookPower);
        }
        queryWrapper.orderByDesc("rm.id");
        ajaxJson.setObj(iPxroomtableService.getRmManagePage(page, queryWrapper));
        return ajaxJson;
    }


    /**
     * @Description: addStay方法作用:添加宿舍
     * @param:[pxroomtable]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:32
     */
    @ApiOperation(value = "宿舍管理/添加宿舍")
    @ResponseBody
    @RequestMapping(value = "roomManage/addStay", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true),
            @ApiImplicitParam(name = "RoomNumber", value = "宿舍号", required = true),
            @ApiImplicitParam(name = "renshu", value = "最大人数", required = true),
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addStay(long campusID, String RoomNumber, int renshu, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Long staffID = Long.valueOf(loginUser.getStaffID());

        if (iPxroomtableService.getAdd(Long.valueOf(campusID), RoomNumber, qiyeID).size() > 0) {
            ajaxJson.setMsg("在选中校区已存在同名宿舍号!");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        Pxroomtable room = new Pxroomtable();
        room.setQiyeID(qiyeID);
        room.setAddStaffID(staffID);
        room.setAddTime(new Date());
        room.setCampusID(campusID);
        room.setNumber(RoomNumber);
        room.setRenshu(renshu);
        ajaxJson.setSuccess(iPxroomtableService.save(room));
        return ajaxJson;
    }

    @ApiOperation(value = "宿舍管理/修改宿舍获取宿舍信息")
    @ResponseBody
    @RequestMapping(value = "roomManage/geteditStay", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "宿舍ID", required = true),
    })
    public AjaxJson geteditStay(String id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxroomtableService.getById(id));
        return ajaxJson;
    }

    /**
     * @Description: updateStay方法作用:修改宿舍
     * @param:[pxroomtable]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:32
     */
    @ApiOperation(value = "宿舍管理/修改宿舍")
    @ResponseBody
    @RequestMapping(value = "roomManage/updateStay", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "宿舍ID", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true),
            @ApiImplicitParam(name = "RoomNumber", value = "宿舍号", required = true),
            @ApiImplicitParam(name = "renshu", value = "最大人数", required = true),
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson updateStay(Long id, Long campusID, String RoomNumber, int renshu, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Long staffID = Long.valueOf(loginUser.getStaffID());
        String dlstaffName = loginUser.getStaffName();

        List<Pxroomtable> roomUp = iPxroomtableService.getRoomUp(campusID, RoomNumber, id, qiyeID);
        if (roomUp.size() > 0) {
            ajaxJson.setMsg("在选中校区已存在同名宿舍号！");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        Pxroomtable room = iPxroomtableService.getById(id);
        room.setAddTime(new Date());
        room.setAddStaffID(staffID);
        room.setCampusID(campusID);
        room.setNumber(RoomNumber);
        room.setQiyeID(qiyeID);
        room.setRenshu(renshu);
        ajaxJson.setSuccess(iPxroomtableService.updateById(room));
        return ajaxJson;
    }


    /**
     * @Description: delStay方法作用:删除学员住宿
     * @param:[roomID]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:33
     */
    @ApiOperation(value = "宿舍管理/删除学员宿舍")
    @RequestMapping(value = "roomManage/delStay", method = RequestMethod.DELETE)
    @ResponseBody()
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delStay(@RequestBody stayFrom from, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());

        String IDs = from.getIds();
        List<stuIDVo> stayVoList = JSON.parseArray(IDs, stuIDVo.class);

        for (stuIDVo item : stayVoList) {
            List<Pxstutable> havestu = iPxstutableService.getnewNum(item.getId(), qiyeID);
            Pxroomtable room = iPxroomtableService.getById(item.getId());
            if (havestu.size() > 0) {
                ajaxJson.setMsg("删除失败，" + room.getNumber() + "已被关联使用，不允许删除");
                ajaxJson.setCode("N");
                return ajaxJson;
            }
            iPxroomtableService.removeById(item.getId());
        }
        return ajaxJson;
    }


    //endregion

    //endregion

    //endregion


}
