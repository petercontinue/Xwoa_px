package com.xwcloud.cloud.pkxk.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.DateUtil;
import com.xwcloud.cloud.common.ExportExcel;
import com.xwcloud.cloud.model.Form.stu.updateRemainKsForm;
import com.xwcloud.cloud.model.Form.stu.updateRemainXFForm;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.*;
import com.xwcloud.cloud.model.pkxk.Vo.stuKehaoVo;
import com.xwcloud.cloud.pkxk.Service.*;
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
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-22
 */
@Controller
@RequestMapping("/kehao/ClassRecord")
@Api(tags = "上课记录")
public class ClassrecordController {

    //region 注入服务
    @Autowired
    IPxkeshistutableService iPxkeshistutableService;

    @Autowired
    IPxbuxikechengtableService iPxbuxikechengtableService;

    @Autowired
    IPxjifentableService iPxjifentableService;

    @Autowired
    IPxkechengtableService iPxkechengtableService;


    @Autowired
    IPxkeshistuteachertableService iPxkeshistuteachertableService;

    @Autowired
    IPxqiandaoqiantuitableService iPxqiandaoqiantuitableService;

    @Autowired
    IPxstukaoqingtableService iPxstukaoqingtableService;

    @Autowired
    IPxstukaoqingteachertableService iPxstukaoqingteachertableService;

    @Autowired
    IPxstutableService iPxstutableService;

    @Autowired
    IPxsysparamdefaulttableService iPxsysparamdefaulttableService;

    @Autowired
    IPxsysparamvaluetableService iPxsysparamvaluetableService;

    @Autowired
    IPxtskaiguandefaulttableService iPxtskaiguandefaulttableService;

    @Autowired
    IPxtuisongtableService iPxtuisongtableService;

    @Autowired
    IPxkeshiteachertableService iPxkeshiteachertableService;

    @Autowired
    IPxpaiketableService iPxpaiketableService;

    @Autowired
    IPxkeshiresettableService iPxkeshiresettableService;

    @Autowired
    IPxpowertableService iPxpowertableService;
    //endregion

    //region 学员上课记录

    /**
     * @Description: getStuList()方法作用:获取学员(id,name)--在读、意向
     * @param:[]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/1/7 17:26
     */
    @ApiOperation(value = "（搜索时）获取学员列表")
    @ResponseBody
    @RequestMapping(value = "getStuList", method = RequestMethod.GET)
    public AjaxJson getStuList(HttpServletRequest request, String menuID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper<Pxstutable> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("buxiStateID", 1)
                .or(a -> a.eq("buxiStateID", 2));

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", menuID);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("campusid", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("campusid", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("campusid", lookPower);
        }
        ajaxJson.setObj(iPxstutableService.getstu(queryWrapper));
        return ajaxJson;
    }


    /**
     * @Description: getStukehaoPage()方法作用:分页获取学员上课记录
     * @param:[current, size, stuID, stuName, stuGradeID, className, kechengName, banzhureb, buxiStyleName, teacherNames, haveClassDate, campusID, stukaoqing]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/3 15:03
     */
    @ApiOperation(value = "分页获取学员上课记录")
    @ResponseBody
    @RequestMapping(value = "getStukehaoPage", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "stuID", value = "学号", required = false),
            @ApiImplicitParam(name = "stuName", value = "学员名字", required = false),
            @ApiImplicitParam(name = "stuGradeID", value = "年级ID", required = false),
            @ApiImplicitParam(name = "className", value = "班级名称", required = false),
            @ApiImplicitParam(name = "kechengName", value = "课程名称", required = false),
            @ApiImplicitParam(name = "banzhuren", value = "班主任", required = false),
            @ApiImplicitParam(name = "buxiStyleID", value = "培训方式", required = false),
            @ApiImplicitParam(name = "teacherNames", value = "任课老师", required = false),
            @ApiImplicitParam(name = "weekN", value = "任课老师", required = false),
            @ApiImplicitParam(name = "startDate", value = "上课日期", required = false),
            @ApiImplicitParam(name = "endDate", value = "上课日期", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "adduser", value = "校区ID", required = false),
            @ApiImplicitParam(name = "stukaoqing", value = "考勤状态 -1:全部 默认 ", example = "-1", required = true)
    })
    public AjaxJson getStukehaoPage(Long current,
                                    Long size,
                                    @RequestParam(required = false) String stuID,
                                    @RequestParam(required = false) String stuName,
                                    @RequestParam(required = false) String stuGradeID,
                                    @RequestParam(required = false) String className,
                                    @RequestParam(required = false) String kechengName,
                                    @RequestParam(required = false) String banzhuren,
                                    @RequestParam(required = false) String buxiStyleID,
                                    @RequestParam(required = false) String teacherNames,
                                    @RequestParam(required = false) String weekN,
                                    @RequestParam(required = false) String startDate,
                                    @RequestParam(required = false) String endDate,
                                    @RequestParam(required = false) String campusID,
                                    @RequestParam(required = false) String adduser,
                                    String stukaoqing,
                                    HttpServletRequest request
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper<stuKehaoVo> queryWrapper = new QueryWrapper<>();
        Page<stuKehaoVo> page = new Page(current, size);

        queryWrapper.eq("1", 1);
        queryWrapper.eq("a.qiyeID", qiyeID);

        if (StringUtils.isNotBlank(stuID)) {
            queryWrapper
                    .like("b.zidingyiStuID", stuID)
                    .or().eq("b.id", stuID);
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("b.stuName", stuName);
        }
        if (StringUtils.isNotBlank(stuGradeID)) {
            queryWrapper.eq("b.stuGradeID", stuGradeID);
        }
        if (StringUtils.isNotBlank(className)) {
            queryWrapper.like("d.className", className);
        }
        if (StringUtils.isNotBlank(kechengName)) {
            queryWrapper.like("h.kechengName", kechengName);
        }
        if (StringUtils.isNotBlank(banzhuren)) {
            queryWrapper.like("i.staffName", banzhuren);
        }
        if (StringUtils.isNotBlank(buxiStyleID)) {
            queryWrapper.eq("a.buxiStyleID", buxiStyleID);
        }
        if (StringUtils.isNotBlank(teacherNames)) {
            queryWrapper.like("a.teacherNames", teacherNames);
        }
        if (StringUtils.isNotBlank(startDate) || StringUtils.isNotBlank(endDate)) {
            queryWrapper
                    .ge("a.haveClassDate", startDate)
                    .le("a.haveClassDate", endDate);
        }
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("a.campusID", campusID);
        }
        if (StringUtils.isNotBlank(weekN)) {
            queryWrapper.eq("a.weekN", weekN);
        }
        if (StringUtils.isNotBlank(adduser)) {
            queryWrapper.like("addu.staffName", adduser);
        }
        if (Integer.valueOf(stukaoqing) != -1) {
            queryWrapper.eq("a.stuKaoqingStyle", stukaoqing);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 251);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }
        queryWrapper.orderByDesc("a.haveClassDate");
        ajaxJson.setObj(iPxkeshistutableService.getStukehao(page, queryWrapper));
        return ajaxJson;
    }


    /**
     * @Description: ExportStukehao()方法作用:导出学员上课记录
     * @param:[campusID, month, stuID, response]
     * @return:void
     * @auter:yyl
     * @data:2020/12/4 15:08
     */
    @ApiOperation(value = "导出学员上课记录")
    @ResponseBody
    @RequestMapping(value = "ExportStukehao", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区", required = false),
            @ApiImplicitParam(name = "joinTime", value = "年月", required = false),
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = false)
    })
    public void ExportStukehao(@RequestParam(required = false) String campusID,
                               @RequestParam(required = false) String joinTime,
                               @RequestParam(required = false) String stuID,
                               HttpServletResponse response, HttpServletRequest request
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper<stuKehaoVo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("1", 1)
                .eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("a.campusID", campusID);
        }
        if (StringUtils.isNotBlank(stuID)) {
            queryWrapper.eq("b.id", stuID);
        }
        if (StringUtils.isNotBlank(joinTime)) {
            queryWrapper
                    .eq("(YEAR(a.haveClassDate))", joinTime.substring(0, 4)) //年
                    .eq("(MONth(a.haveClassDate))", joinTime.substring(5, 7)); //月
        }
        List<stuKehaoVo> stuKehaoVos = iPxkeshistutableService.ExportStukehao(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"学号", "姓名", "年级", "班级", "课程名称", "班主任", "培训方式", "上课教师", "助教", "上课日期", "周几", "上课", "下课", "课时", "单价", "校区", "学员考勤", "说明"},
                stuKehaoVos,
                new String[]{"stuID", "stuName", "stuGradeName", "className", "kechengName", "banzhuren", "buxiStyleName", "teacherNames", "zhujiao", "haveClassDate-D", "weekN", "startLessonDateTime-T", "endLessonDateTime-T", "keshiNum", "kechengPrice", "campusName", "stukaoqing", "shuoMing"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "学员上课记录导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @Description: delStukehao()方法作用:删除学员上课记录
     * @param:[IDs]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/4 18:11
     */
    @ApiOperation(value = "删除学员上课记录")
    @ResponseBody
    @RequestMapping(value = "delStukehao", method = RequestMethod.DELETE)
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delStukehao(String ids, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();
//        String ids = from.getIds();
        if (ids != null) {
//            List<IDVo> khIDList = JSON.parseArray(ids, IDVo.class);
            String delMsg = "";

            for (String item : ids.split(",")) {
                Pxkeshistutable kehao = iPxkeshistutableService.getById(item);
                Pxstutable stuinfo = iPxstutableService.getById(kehao.getStuID());
                Pxkechengtable kc = iPxkechengtableService.getById(kehao.getKechengID());
                Date Totady = kehao.getHaveClassDate();
                String tianshu = iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 41L) == null ? iPxsysparamdefaulttableService.getById(41).getDefaultValue() : iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 41L).getModifyValue();
                ;

                if (Totady.getTime() < DateUtil.addDays(new Date(), -Integer.parseInt(tianshu)).getTime()) {
                    ajaxJson.setMsg("不能删除 " + tianshu + " 天前的课时消耗");
                    ajaxJson.setCode("N");
                    return ajaxJson;
                }

                QueryWrapper<Pxkeshistutable> khQ = new QueryWrapper<>();
                khQ
                        .eq("qiyeID", qiyeID)
                        .eq("classID", kehao.getClassID())
                        .eq("haveClassDate", kehao.getHaveClassDate())
                        .eq("startLessonDateTime", kehao.getStartLessonDateTime())
                        .eq("endLessonDateTime", kehao.getEndLessonDateTime());
                List<Pxkeshistutable> selectkehao = iPxkeshistutableService.selectkehao(khQ);

                int allxiaohaoCount = selectkehao.size();

                int delstuKqStyle = 1;

                //获取到排课
                Pxpaiketable pk = null;
                QueryWrapper<Pxpaiketable> paikeQ = new QueryWrapper<>();
                paikeQ
                        .eq("qiyeID", qiyeID)
                        .eq("classID", kehao.getClassID())
                        .eq("haveClassDate", kehao.getHaveClassDate())
                        .eq("startLessonDateTime", kehao.getStartLessonDateTime())
                        .eq("endLessonDateTime", kehao.getEndLessonDateTime());
                List<Pxpaiketable> selectpaike = iPxpaiketableService.selectpaike(paikeQ);
                if (selectpaike.size() > 0) {
                    pk = selectpaike.get(0);
                }

                QueryWrapper<Pxstukaoqingtable> Qstukq = new QueryWrapper<>();
                Qstukq
                        .eq("qiyeID", qiyeID)
                        .eq("stuID", kehao.getStuID())
                        .eq("haveclassDate", kehao.getHaveClassDate())
                        .eq("startClassDateTime", kehao.getStartLessonDateTime())
                        .eq("endClassDateTime", kehao.getEndLessonDateTime())
                        .eq("kechengID", kehao.getKechengID());
                List<Pxstukaoqingtable> selectstukaoqing = iPxstukaoqingtableService.selectstukaoqing(Qstukq);
                if (selectstukaoqing.size() > 0) {
                    Pxstukaoqingtable kaoqing = selectstukaoqing.get(0);
                    delstuKqStyle = kaoqing.getKaoqingstyle();
                    iPxstukaoqingtableService.removeById(kaoqing.getId());
                    delMsg += "删除了学员考勤记录";
                    QueryWrapper<Pxstukaoqingteachertable> stukqTea = new QueryWrapper<>();
                    stukqTea
                            .eq("qiyeID", qiyeID)
                            .eq("stukaoqingTabID", kaoqing.getId());
                    List<Pxstukaoqingteachertable> stukqTeachers = iPxstukaoqingteachertableService.selectstukaoteacherqing(stukqTea);
                    if (stukqTeachers.size() > 0) {
//                    for (Pxstukaoqingteachertable kqTea : stukqTeachers) {
//                        iPxstukaoqingteachertableService.removeById(kqTea.getId());
//                        delMsg += "，删除了学员考勤教师记录";
//                    }
                        iPxstukaoqingteachertableService.remove(stukqTea);
                        delMsg += "，删除了学员考勤教师记录";
                    } else {
                        delMsg += ",Pxstukaoqingteachertable表内记录不存在";
                    }
                } else {
                    delMsg += ",pxstukaoqingtable记录不存在";
                }

                QueryWrapper<Pxkeshistuteachertable> StuTeaKehao = new QueryWrapper<>();
                StuTeaKehao.eq("keshistutableid", kehao.getId());
                List<Pxkeshistuteachertable> kehaoTeachers = iPxkeshistuteachertableService.selectstuTeakehao(StuTeaKehao);
                if (kehaoTeachers.size() > 0) {
                    //删除教师课耗
                    //// 如果要删除的这条课耗记录已经是这个班最后一条课耗记录了，就把教师的课耗也删除掉；
                    //否则，实上人数减1
                    for (Pxkeshistuteachertable itemtks : kehaoTeachers) {
                        QueryWrapper<Pxkeshiteachertable> TeaKehao = new QueryWrapper<>();
                        TeaKehao
                                .eq("qiyeID", qiyeID)
                                .eq("classID", kehao.getClassID())
                                .eq("haveClassDate", kehao.getHaveClassDate())
                                .eq("startLessonDateTime", kehao.getStartLessonDateTime())
                                .eq("endLessonDateTime", kehao.getEndLessonDateTime());
                        List<Pxkeshiteachertable> teacherkehao = iPxkeshiteachertableService.selectTeakehao(TeaKehao);
                        if (teacherkehao.size() > 0) {
                            if (allxiaohaoCount == 1) {
                                //删除对应的教师课时记录
                                for (Pxkeshiteachertable kstea : teacherkehao) {
                                    iPxkeshiteachertableService.removeById(kstea.getId());
                                }
                                delMsg += "，删除对应的教师课时记录";
                            } else {
                                //删除的这条学生课时记录，学生的考勤不是请假2，也不是旷课3
                                if (delstuKqStyle != 2 && delstuKqStyle != 3) {
                                    for (Pxkeshiteachertable itemteakehao : teacherkehao) {
                                        itemteakehao.setSsStuNum(itemteakehao.getSsStuNum() - 1);
                                        iPxkeshiteachertableService.updateById(itemteakehao);
                                    }
                                    delMsg += ",keshiTeacherTable对应的教师课时记录实上人数减1";
                                }
                            }
                        }
                        iPxkeshistuteachertableService.removeById(itemtks);
                        delMsg += ",<keshiStuTeacherTable对应的课时学员教师记录删除>";
                    }
                } else {
                    delMsg += "【pxkeshiStuTeacherTable学员课时教师记录为空：对应的教师的课时记录未删除】";
                }

                //删除刷卡记录
                QueryWrapper<Pxqiandaoqiantuitable> shuaka = new QueryWrapper<>();
                shuaka
                        .eq("qiyeID", qiyeID)
                        .eq("stuid", kehao.getStuID())
                        .eq("qiandaoorqiantui", 2)
                        .eq("qianstyle", 1);//刷卡签退
                List<Pxqiandaoqiantuitable> selectqiaodao = iPxqiandaoqiantuitableService.selectqiaodao(shuaka);
                if (selectqiaodao.size() > 0) {
                    Pxqiandaoqiantuitable isShuaka = selectqiaodao.get(0);

                    iPxqiandaoqiantuitableService.removeById(isShuaka);
                    delMsg += ",<shuakakoufeiTable对应的课时刷卡记录删除>";
                }
                delMsg += ",<pxkeshiStutable学员课时记录删除>";

                Integer thiskcJifeiStyle = iPxkechengtableService.getById(kehao.getKechengID()).getJifeiStyleID();
                if (thiskcJifeiStyle != 3) {
                    //不是按起止日期计费，才在删除课时记录的时候退还课时和学费
                    //课时表有buxiID，直接用buxiID（否则必须单价也相同）
                    QueryWrapper<Pxbuxikechengtable> buxiQ = new QueryWrapper<>();
                    buxiQ
                            .eq("qiyeID", qiyeID)
                            .eq("stuid", kehao.getStuID())
                            .eq("kechengid", kehao.getKechengID())
                            .eq("kechengprice", kehao.getKechengPrice());
                    List<Pxbuxikechengtable> selectbuxikecheng = iPxbuxikechengtableService.selectbuxikecheng(buxiQ);
                    if (selectbuxikecheng.size() > 0) {
                        Pxbuxikechengtable buxikecheng = selectbuxikecheng.get(0);
                        if (kehao.getKechengPrice().intValue() == 0) {
                            QueryWrapper<Pxbuxikechengtable> buxiQ2 = new QueryWrapper<>();
                            buxiQ2
                                    .eq("qiyeID", qiyeID)
                                    .eq("stuID", kehao.getStuID())
                                    .eq("kechengID", kehao.getKechengID())
                                    .eq("type", 2);
                            buxikecheng = iPxbuxikechengtableService.selectbuxikecheng(buxiQ2).get(0);
                        }
                        if (buxikecheng != null) {
                            delMsg += "返还前学员剩余课时" + buxikecheng.getRemainkeshi().toString() + "，要返还课时" + kehao.getKeshiNum().toString();
                            buxikecheng.setRemainkeshi(buxikecheng.getRemainkeshi().add(kehao.getKeshiNum()));
                            delMsg += "返还后学员剩余课时" + buxikecheng.getRemainkeshi().toString();
                            delMsg += "<成功返还学员课时:" + kehao.getKeshiNum().toString() + ">";
                            iPxbuxikechengtableService.updateById(buxikecheng);
                        }
                    } else {
                        QueryWrapper<Pxbuxikechengtable> buxiQX = new QueryWrapper<>();
                        buxiQX
                                .eq("qiyeID", qiyeID)
                                .eq("stuid", kehao.getStuID())
                                .eq("kechengid", kehao.getKechengID())
                                .eq("kechengprice", kehao.getKechengPrice());

                        List<Pxbuxikechengtable> selectbuxikechengx = iPxbuxikechengtableService.selectbuxikecheng(buxiQX);
                        if (selectbuxikechengx.size() > 0) {
                            Pxbuxikechengtable buxikechengX = selectbuxikechengx.get(0);
                            //找不到单价也相同的才找相同的课程
                            if (buxikechengX == null) {
                                QueryWrapper<Pxbuxikechengtable> buxiQno = new QueryWrapper<>();
                                buxiQno
                                        .eq("qiyeID", qiyeID)
                                        .eq("stuid", kehao.getStuID())
                                        .eq("kechengid", kehao.getKechengID());
                                buxikechengX = iPxbuxikechengtableService.selectbuxikecheng(buxiQno).get(0);
                                if (buxikechengX == null) {
                                    buxikechengX = iPxbuxikechengtableService.getById(kehao.getId());
                                }
                            }
                            if (buxikechengX != null) {
                                delMsg += ",返还前学员剩余课时" + buxikechengX.getRemainkeshi().toString() + "，要返还课时" + kehao.getKeshiNum().toString();
                                buxikechengX.setRemainkeshi(buxikechengX.getRemainkeshi().add(kehao.getKeshiNum()));
                                delMsg += ",返还后学员剩余课时" + buxikechengX.getRemainkeshi().toString();
                                delMsg += ",<成功返还学员课时:" + kehao.getKeshiNum().toString() + ">";
                                iPxbuxikechengtableService.updateById(buxikechengX);
                            } else {
                                ajaxJson.setMsg("这学生已没有该课程，无法删除本次课耗!");
                            }
                        }

                    }
                }
                else {
                    //判断老数据与新数据差别消课
                    Pxbuxikechengtable bkByDay = iPxbuxikechengtableService.getById(kehao.getBuxikechengID());
                    if (bkByDay.getKeshiNum().intValue() != 0 && bkByDay.getRemainkeshi().intValue() != 0) {
                        delMsg += ",起止日期课程，返还前学员剩余课时" + bkByDay.getRemainkeshi().toString() + "，要返还课时" + kehao.getKeshiNum().toString();
                        bkByDay.setRemainkeshi(bkByDay.getRemainkeshi().add(kehao.getKeshiNum()));
                        delMsg += ",返还后学员剩余课时" + bkByDay.getRemainkeshi().toString();
                        delMsg += ",<成功返还学员课时:" + kehao.getKeshiNum().toString() + ">";
                        iPxbuxikechengtableService.updateById(bkByDay);
                    }
                }

                delMsg += ",返还前学员剩余学费" + stuinfo.getRemainXuefei().toString();
                BigDecimal returnMoney = kehao.getKeshiNum().multiply(kehao.getKechengPrice());
                stuinfo.setRemainXuefei(stuinfo.getRemainXuefei().add(returnMoney));
                delMsg += ",返还后学员剩余学费" + stuinfo.getRemainXuefei().toString();
                delMsg += ",返还课时" + kehao.getKeshiNum().toString() + "，单价" + kehao.getKechengPrice().toString() + "，成功返还学费" + returnMoney.toString() + ">";
                iPxstutableService.updateById(stuinfo);

                //看看有没有送过积分，送过积分的话，还要把积分扣减回来；72
                String jfbiliStr = iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 72L) == null ? iPxsysparamdefaulttableService.getById(72).getDefaultValue() : iPxsysparamvaluetableService.getsysvalue(Long.valueOf(qiyeID), 72L).getModifyValue();

                if (StringUtils.isNotEmpty(jfbiliStr)) {
                    BigDecimal jfbili = new BigDecimal(jfbiliStr);

                    BigDecimal jfV = stuinfo.getJifenNum().subtract(jfbili.multiply(kehao.getKeshiNum()));
                    //学员表中的积分先扣下来；
                    if (jfbili.intValue() > 0) {
                        stuinfo.setJifenNum(jfV);
                        iPxstutableService.updateById(stuinfo);
                    }

                    //积分表中再去添加一条扣减记录；
                    Pxjifentable JF = new Pxjifentable();
                    JF.setType(2);//1增加，2扣减
                    JF.setStuID(kehao.getStuID());
                    JF.setOldintegral(stuinfo.getJifenNum());
                    JF.setIntegral(jfV);
                    JF.setStaffID(staffID);
                    JF.setCreatetime(new Date());
                    JF.setRemark("删除课时操作：把学员消课时赠送的积分扣回来；");
                    JF.setQiyeID(qiyeID);
                    iPxjifentableService.save(JF);

                    //region 向家长推送积分变动信息 （待）
                    //endregion
                }

                //看看有没有排课信息，如果有排课信息，应该把这条排课信息的打考勤状态设置为未打考勤；
                if (pk != null) {
                    pk.setDakaoqin(false);//未打考勤为null,打了考勤为True
                    iPxpaiketableService.updateById(pk);
                }

                iPxkeshistutableService.removeById(kehao.getId());
            }
        }


        ajaxJson.setMsg("删除成功");
        return ajaxJson;
    }

    //endregion

    //region 教师上课记录


    /**
     * @Description: getTeakehaoPage()方法作用:分页获取教师上课记录
     * @param:[current, size, campusID, TeachName, grade, kechengName, buxiStyleName, className, haveClassDate]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/6 14:24
     */
    @ApiOperation(value = "分页获取教师上课记录")
    @ResponseBody
    @RequestMapping(value = "getTeakehaoPage", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页获取数据", example = "10", required = true),
            @ApiImplicitParam(name = "campusID", value = "班级校区", required = false),
            @ApiImplicitParam(name = "TeachName", value = "教师", required = false),
            @ApiImplicitParam(name = "grade", value = "年级", required = false),
            @ApiImplicitParam(name = "kechengName", value = "课程", required = false),
            @ApiImplicitParam(name = "buxiStyleName", value = "培训方式ID", required = false),
            @ApiImplicitParam(name = "className", value = "班级", required = false),
            @ApiImplicitParam(name = "startDate", value = "上课日期", required = false),
            @ApiImplicitParam(name = "endDate", value = "上课日期", required = false),
    })
    public AjaxJson getTeakehaoPage(Long current,
                                    Long size,
                                    String campusID,
                                    String TeachName,
                                    String grade,
                                    String kechengName,
                                    String buxiStyleName,
                                    String className,
                                    String startDate,
                                    String endDate,
                                    HttpServletRequest request
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper<TeaKehaoVo> queryWrapper = new QueryWrapper<>();
        Page<TeaKehaoVo> page = new Page(current, size);

        queryWrapper.eq("1", 1);
        queryWrapper.eq("a.qiyeID", qiyeID);

        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("c.campusID", campusID);
        }
        if (StringUtils.isNotBlank(TeachName)) {
            queryWrapper.like("b.staffName", TeachName);
        }
        if (StringUtils.isNotBlank(grade)) {
            queryWrapper.like("a.allstuNianji", grade);
        }
        if (StringUtils.isNotBlank(kechengName)) {
            queryWrapper.like("d.kechengName", kechengName);
        }
        if (StringUtils.isNotBlank(buxiStyleName)) {
            queryWrapper.eq("d.buxiStyleID", buxiStyleName);
        }
        if (StringUtils.isNotBlank(className)) {
            queryWrapper.like("c.className", className);
        }

        if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {
            queryWrapper
                    .ge("a.haveClassDate", startDate)
                    .le("a.haveClassDate", endDate);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 252);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("c.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("c.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("c.campusID", lookPower);
        }
        queryWrapper.orderByDesc("a.haveClassDate", "a.id");
        ajaxJson.setObj(iPxkeshiteachertableService.getTeakehaoPage(page, queryWrapper));
        return ajaxJson;
    }


    @ResponseBody
    @DeleteMapping("delteakehao")
    @ApiOperation("删除教师上课记录")
    public AjaxJson delteakehao(String id, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Pxkeshiteachertable Tea = iPxkeshiteachertableService.getById(id);
        if (Tea.getStuID() == 0) {
            //说明是班课

            //先删学员的
            List<Pxkeshistutable> allstukehao = iPxkeshistutableService.list(new QueryWrapper<Pxkeshistutable>()
                    .eq("haveClassDate", Tea.getHaveClassDate())
                    .eq("startLessonDateTime", Tea.getStartLessonDateTime())
                    .eq("endLessonDateTime", Tea.getEndLessonDateTime())
                    .eq("classID", Tea.getClassID())
                    .eq("kechengID", Tea.getKechengID())
                    .eq("qiyeID", loginUser.getQiyeID())
            );
            for (Pxkeshistutable item : allstukehao) {
                Pxstutable stu = iPxstutableService.getById(item.getStuID());
                //还学费
                BigDecimal tmoney = item.getKeshiNum().multiply(item.getKechengPrice());
                stu.setRemainXuefei(stu.getRemainXuefei().add(tmoney));
                iPxstutableService.updateById(stu);

                //还课时
                if (item.getShareBuxiID() != 0 || item.getShareBuxiID() != null) {
                    //共享课时
                    Pxbuxikechengtable buxi = iPxbuxikechengtableService.getById(item.getShareBuxiID());
                    buxi.setRemainkeshi(buxi.getRemainkeshi().add(item.getKeshiNum()));
                    iPxbuxikechengtableService.updateById(buxi);
                } else {
                    //非共享课时
                    Pxbuxikechengtable buxi = iPxbuxikechengtableService.getById(item.getBuxikechengID());
                    buxi.setRemainkeshi(buxi.getRemainkeshi().add(item.getKeshiNum()));
                    iPxbuxikechengtableService.updateById(buxi);
                }
                iPxkeshistutableService.removeById(item);//删除学员课耗信息
            }

            //在删老师的(可能有多个老师的情况存在)
            iPxkeshiteachertableService.remove(new QueryWrapper<Pxkeshiteachertable>()
                    .eq("haveClassDate", Tea.getHaveClassDate())
                    .eq("startLessonDateTime", Tea.getStartLessonDateTime())
                    .eq("endLessonDateTime", Tea.getEndLessonDateTime())
                    .eq("classID", Tea.getClassID())
                    .eq("kechengID", Tea.getKechengID())
                    .eq("qiyeID", loginUser.getQiyeID())
            );


        } else {
            //一对一的消课
            Pxstutable Tuistu = iPxstutableService.getById(Tea.getStuID());
            Pxkeshistutable OtOstukehao = iPxkeshistutableService.getOne(new QueryWrapper<Pxkeshistutable>()
                    .eq("stuID", Tea.getStuID())
                    .eq("haveClassDate", Tea.getHaveClassDate())
                    .eq("startLessonDateTime", Tea.getStartLessonDateTime())
                    .eq("endLessonDateTime", Tea.getEndLessonDateTime())
                    .eq("classID", Tea.getClassID())
                    .eq("kechengID", Tea.getKechengID())
                    .eq("qiyeID", loginUser.getQiyeID())
            );
            //还学费
            BigDecimal tmoney = OtOstukehao.getKeshiNum().multiply(OtOstukehao.getKechengPrice());
            Tuistu.setRemainXuefei(Tuistu.getRemainXuefei().add(tmoney));
            iPxstutableService.updateById(Tuistu);

            //还课时
            if (OtOstukehao.getShareBuxiID() != 0 || OtOstukehao.getShareBuxiID() != null) {
                //共享课时
                Pxbuxikechengtable buxi = iPxbuxikechengtableService.getById(OtOstukehao.getShareBuxiID());
                buxi.setRemainkeshi(buxi.getRemainkeshi().add(OtOstukehao.getKeshiNum()));
                iPxbuxikechengtableService.updateById(buxi);
            } else {
                //非共享课时
                Pxbuxikechengtable buxi = iPxbuxikechengtableService.getById(OtOstukehao.getBuxikechengID());
                buxi.setRemainkeshi(buxi.getRemainkeshi().add(OtOstukehao.getKeshiNum()));
                iPxbuxikechengtableService.updateById(buxi);
            }

            //删除课耗信息
            iPxkeshistutableService.removeById(OtOstukehao);
            iPxkeshiteachertableService.removeById(Tea);
        }
        return ajaxJson;
    }


    @ApiOperation(value = "分页获取缺勤学员")
    @ResponseBody
    @RequestMapping(value = "NokaoqingstuPage", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页获取数据", example = "10", required = true),
            @ApiImplicitParam(name = "ID", value = "教师课耗表ID", required = false),
    })
    public AjaxJson NokaoqingstuPage(Long current, Long size, String ID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Pxkeshiteachertable tea = iPxkeshiteachertableService.getById(ID);
        QueryWrapper<nokaoqingStuVo> queryWrapper = new QueryWrapper<>();
        Page<nokaoqingStuVo> page = new Page(current, size);
        queryWrapper
                .eq("a.qiyeID", qiyeID)
                .eq("haveClassDate", tea.getHaveClassDate())
                .eq("classID", tea.getClassID())
                .eq("startClassDateTime", tea.getStartLessonDateTime())
                .eq("endClassDateTime", tea.getEndLessonDateTime());

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 252);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("b.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("b.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("b.campusID", lookPower);
        }
        queryWrapper.orderByDesc("a.haveclassDate", "a.id");
        ajaxJson.setObj(iPxstukaoqingtableService.NokaoqingstuPage(page, queryWrapper));

        return ajaxJson;
    }


    /**
     * @Description: ExportTeakehao()方法作用:导出教师上课记录流水
     * @param:[campusID, response]
     * @return:void
     * @auter:yyl
     * @data:2020/12/7 17:45
     */
    @ApiOperation(value = "导出教师上课记录")
    @ResponseBody
    @RequestMapping(value = "ExportTeakehao", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区", required = true),
            @ApiImplicitParam(name = "yearMouth", value = "年月", required = false),
            @ApiImplicitParam(name = "year", value = "年份", required = false),
            @ApiImplicitParam(name = "type", value = "导出类型 1导出流水 2导出统计", required = true)
    })
    public void ExportTeakehao(@RequestParam(required = true) String campusID,
                               @RequestParam(required = false) String yearMouth,
                               @RequestParam(required = false) String year,
                               @RequestParam(required = true) int type,
                               HttpServletResponse response, HttpServletRequest request
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper<TeaKehaoVo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("1", 1)
                .eq("a.qiyeID", qiyeID);

        if (StringUtils.isNotBlank(campusID)) {
            if (type == 1) {
                queryWrapper.eq("a.campusID", campusID);
            } else if (type == 2) {
                queryWrapper.eq("c.campusID", campusID);
            }
        }
        if (StringUtils.isNotBlank(yearMouth)) {
            queryWrapper
                    .eq("(SELECT YEAR(a.haveClassDate))", yearMouth.substring(0, 4))
                    .and(b -> b.eq("SELECT MONth(a.haveClassDate)", yearMouth.substring(5, 7)));
        }
        if (StringUtils.isNotBlank(year)) {
            queryWrapper.eq("(SELECT YEAR(a.haveClassDate))", year);
        }

        if (type == 1) {
            List<TeaKehaoVo> teaKehaoVos = iPxkeshiteachertableService.ExportTeakehao(queryWrapper);
            List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"班级校区", "教师", "助教", "年级", "课程", "培训方式", "班级", "上课日期", "上课", "下课", "课时数", "应上人数", "实上人数", "说明"},
                    teaKehaoVos,
                    new String[]{"campusName", "TeachName", "zhujiao", "grade", "kechengName", "buxiStyleName", "className", "haveClassDate-D", "startLessonDateTime-T", "endLessonDateTime-T", "keshiNum", "ysStuNum", "ssStuNum", "shuoMing"});
            try {
                ExportExcel.exportExcel(response, list, "Sheet1", "导出教师上课记录流水.xls", 15);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type == 2) {
            List<teakehaoCountVo> teakehaoCountVos = iPxkeshiteachertableService.ExportTeakehaoCount(queryWrapper);
            List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"班级校区", "教师", "助教", "年级", "班级", "课时数", "上课日期", "说明"},
                    teakehaoCountVos,
                    new String[]{"campusName", "staffName", "zhujiao", "allNianji", "className", "keshiNum", "haveClassDate-D", "shuoMing"});
            try {
                ExportExcel.exportExcel(response, list, "Sheet1", "教师课时统计导出.xls", 15);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    @ApiOperation(value = "导出教师课时统计")
    @ResponseBody
    @RequestMapping(value = "ExportTeakehaoCount", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区", required = true),
            @ApiImplicitParam(name = "yearMouth", value = "年月", required = false),
            @ApiImplicitParam(name = "year", value = "年份", required = false)
    })
    public void ExportTeakehaoCount(@RequestParam(required = true) String campusID,
                                    @RequestParam(required = false) String yearMouth,
                                    @RequestParam(required = false) String year,
                                    HttpServletResponse response, HttpServletRequest request
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper<teakehaoCountVo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("1", 1)
                .eq("a.qiyeID", qiyeID);

        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("c.campusID", campusID);
        }
        if (StringUtils.isNotBlank(yearMouth)) {
            queryWrapper
                    .eq("(SELECT YEAR(a.haveClassDate))", yearMouth.substring(0, 4))
                    .and(b -> b.eq("SELECT MONth(a.haveClassDate)", yearMouth.substring(5, 7)));
        }
        if (StringUtils.isNotBlank(year)) {
            queryWrapper.eq("(SELECT YEAR(a.haveClassDate))", year);
        }

        List<teakehaoCountVo> teakehaoCountVos = iPxkeshiteachertableService.ExportTeakehaoCount(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"班级校区", "教师", "助教", "年级", "班级", "课时数", "上课日期", "说明"},
                teakehaoCountVos,
                new String[]{"campusName", "staffName", "zhujiao", "shichang", "allNianji", "className", "keshiNum", "haveClassDate-D", "shuoMing"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "教师课时统计导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //endregion


    //region


    /**
     * @Description: remainkeshishowPage()方法作用:分页获取剩余课时
     * @param:[current, size, campusID, stugrageID, stuID, stuName, banzhuren, buxiStateID]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/7 17:30
     */
    @ApiOperation("分页获取剩余课时")
    @ResponseBody
    @RequestMapping(value = "remainkeshishowPage", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页获取数据", example = "10", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "stugrageID", value = "年级ID", required = false),
            @ApiImplicitParam(name = "stuID", value = "学号", required = false),
            @ApiImplicitParam(name = "stuName", value = "学员姓名", required = false),
            @ApiImplicitParam(name = "banzhuren", value = "班主任", required = false),
            @ApiImplicitParam(name = "buxiStateID", value = "学员状态", required = false),

    })
    public AjaxJson remainkeshishowPage(Long current,
                                        Long size,
                                        String campusID,
                                        String stugrageID,
                                        String stuID,
                                        String stuName,
                                        String banzhuren,
                                        String buxiStateID,
                                        HttpServletRequest request
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper<stuRemainVo> queryWrapper = new QueryWrapper<>();
        Page<stuRemainVo> page = new Page(current, size);
        queryWrapper
                .eq("1", 1)
                .eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("a.campusID", campusID);
        }
        if (StringUtils.isNotBlank(stugrageID)) {
            queryWrapper.eq("a.stuGradeID", stugrageID);
        }
        if (StringUtils.isNotBlank(stuID)) {
            queryWrapper
                    .like("a.zidingyiStuID", stuID)
                    .or().eq("a.id", stuID);
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("a.stuName", stuName);
        }
        if (StringUtils.isNotBlank(banzhuren)) {
            queryWrapper.like("d.staffName", banzhuren);
        }
        if (StringUtils.isNotBlank(buxiStateID)) {
            queryWrapper.eq("a.buxiStateID", buxiStateID);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 253);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }
        queryWrapper.orderByDesc("a.dengjiTime");
        ajaxJson.setObj(iPxstutableService.remainkeshishowPage(page, queryWrapper));
        return ajaxJson;
    }


    /**
     * @Description: ExportReMoneyAndKeshi()方法作用: 按类别导出学员剩余学费|课时
     * @param:[campusID, type, response]
     * @return:void
     * @auter:yyl
     * @data:2020/12/7 17:45
     */
    @ApiOperation(value = "导出学员剩余学费|课时")
    @ResponseBody
    @RequestMapping(value = "ExportReMoneyAndKeshi", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区", required = false),
            @ApiImplicitParam(name = "type", value = "1:剩余学费导出  2:剩余课时导出", required = true)
    })
    public void ExportReMoneyAndKeshi(@RequestParam(required = false) String campusID,
                                      int type, HttpServletRequest request,
                                      HttpServletResponse response
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("1", 1);
        queryWrapper.eq("a.qiyeID", qiyeID);

        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("a.campusID", campusID);
        }
        if (type == 1) {
            List<ExportReMoneyVo> exportReMoneyVos = iPxstutableService.ExportReMoney(queryWrapper);
            List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "年级", "学员姓名", "学员状态", "剩余学费"},
                    exportReMoneyVos,
                    new String[]{"campusName", "stuGradeName", "stuName", "stuState", "remainXuefei"});
            try {
                ExportExcel.exportExcel(response, list, "Sheet1", "导出学员剩余学费.xls", 15);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type == 2) {
            List<ExportReKeshiVo> exportReKeshiVos = iPxbuxikechengtableService.ExportRekeshi(queryWrapper);
            List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "学员姓名", "任课老师", "科目", "课程", "班级", "课程当价", "剩余课时", "显示状态"},
                    exportReKeshiVos,
                    new String[]{"campusName", "stuName", "skTea", "subjectName", "kechengName", "className", "kechengprice", "remainkeshi", "isShow"});
            try {
                ExportExcel.exportExcel(response, list, "Sheet1", "导出学员剩余课时.xls", 15);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * @Description: getRemainkeshiDetailsPage()方法作用:分页获取剩余课时详情
     * @param:[current, size, campusID, className, stuID, stuName, subjectName, kechengName]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/7 17:45
     */
    @ApiOperation("分页获取剩余课时详情")
    @ResponseBody
    @RequestMapping(value = "getRemainkeshiDetailsPage", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页获取数据", example = "10", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "className", value = "班级", required = false),
            @ApiImplicitParam(name = "stuID", value = "学号", required = false),
            @ApiImplicitParam(name = "stuName", value = "学员姓名", required = false),
            @ApiImplicitParam(name = "subjectName", value = "科目", required = false),
            @ApiImplicitParam(name = "kechengName", value = "课程", required = false),

    })
    public AjaxJson getRemainkeshiDetailsPage(Long current,
                                              Long size,
                                              String campusID,
                                              String className,
                                              String stuID,
                                              String stuName,
                                              String subjectName,
                                              String kechengName,
                                              HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper<RemainkeshiDetailsVo> queryWrapper = new QueryWrapper<>();
        Page<RemainkeshiDetailsVo> page = new Page(current, size);
        queryWrapper
                .eq("1", 1)
                .eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("b.campusID", campusID);
        }
        if (StringUtils.isNotBlank(className)) {
            queryWrapper.like("g.className", className);
        }
        if (StringUtils.isNotBlank(stuID)) {
            queryWrapper
                    .like("b.zidingyiStuID", stuID)
                    .or().eq("a.stuID", stuID);
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("b.stuName", stuName);
        }
        if (StringUtils.isNotBlank(subjectName)) {
            queryWrapper.like("d.subjectName", subjectName);
        }
        if (StringUtils.isNotBlank(kechengName)) {
            queryWrapper.like("c.kechengName", kechengName);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 253);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("b.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("b.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("b.campusID", lookPower);
        }

        ajaxJson.setObj(iPxbuxikechengtableService.getRemainkeshiDetailsPage(page, queryWrapper));
        return ajaxJson;
    }


    /**
     * @Description: getRemainDaysPage()方法作用:分页获取剩余天详情
     * @param:[current, size, campusID, className, stuID, stuName, subjectName, kechengName, isShow]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/8 10:20
     */
    @ApiOperation("分页获取剩余天详情")
    @ResponseBody
    @RequestMapping(value = "getRemainDaysPage", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页获取数据", example = "10", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "className", value = "班级", required = false),
            @ApiImplicitParam(name = "stuID", value = "学号", required = false),
            @ApiImplicitParam(name = "stuName", value = "学员姓名", required = false),
            @ApiImplicitParam(name = "subjectName", value = "科目", required = false),
            @ApiImplicitParam(name = "kechengName", value = "课程", required = false),
            @ApiImplicitParam(name = "isShow", value = "显示状态 全显示：-1 1：课程启用  0：课程不启用", example = "-1", required = false),
    })
    public AjaxJson getRemainDaysPage(Long current, HttpServletRequest request,
                                      Long size,
                                      String campusID,
                                      String className,
                                      String stuID,
                                      String stuName,
                                      String subjectName,
                                      String kechengName,
                                      int isShow) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper<RemainDaysVo> queryWrapper = new QueryWrapper<>();
        Page<RemainDaysVo> page = new Page(current, size);
        queryWrapper
                .eq("1", 1)
                .eq("a.qiyeID", qiyeID);
        if (isShow != -1) {
            queryWrapper.eq("a.isShow", isShow);
        }
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("b.campusID", campusID);
        }
        if (StringUtils.isNotBlank(className)) {
            queryWrapper.like("g.className", className);
        }
        if (StringUtils.isNotBlank(stuID)) {
            queryWrapper
                    .like("b.zidingyiStuID", stuID)
                    .or().eq("a.stuID", stuID);
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("b.stuName", stuName);
        }
        if (StringUtils.isNotBlank(subjectName)) {
            queryWrapper.like("d.subjectName", subjectName);
        }
        if (StringUtils.isNotBlank(kechengName)) {
            queryWrapper.like("c.kechengName", kechengName);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 253);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("b.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("b.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("b.campusID", lookPower);
        }
        queryWrapper.orderByDesc("a.buykeshiDateTime", "a.id");
        ajaxJson.setObj(iPxbuxikechengtableService.getRemainDaysPage(page, queryWrapper));
        return ajaxJson;
    }


    /**
     * @Description: UpdatekeshiAndXFPage()方法作用:分页获取修改剩余学费|剩余课时
     * @param:[current, size, stuID]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/8 11:00
     */
    @ApiOperation("分页获取修改剩余学费|剩余课时")
    @ResponseBody
    @RequestMapping(value = "UpdatekeshiAndXFPage", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页获取数据", example = "10", required = true),
            @ApiImplicitParam(name = "stuID", value = "学号", required = true),
    })
    public AjaxJson UpdatekeshiAndXFPage(Long current,
                                         Long size,
                                         String stuID,
                                         HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<UpdatekeshiAndXFVo> page = new Page(current, size);
        QueryWrapper<UpdatekeshiAndXFVo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("a.stuID", stuID)
                .eq("a.qiyeiD", qiyeID);

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 253);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("d.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("d.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("d.campusID", lookPower);
        }

        queryWrapper.orderByDesc("a.buykeshiDateTime", "a.id");
        ajaxJson.setObj(iPxbuxikechengtableService.getUpdatekeshiAndXFPage(page, queryWrapper));
        return ajaxJson;
    }


    /**
     * @Description: updateRemainXf()方法作用:修改剩余学费
     * @param:[stuID, newXF]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/8 11:18
     */
    @ResponseBody
    @RequestMapping(value = "updateRemainXF", method = RequestMethod.POST)
    @ApiOperation("修改剩余学费")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学号", required = true),
            @ApiImplicitParam(name = "newXF", value = "新的学费", example = "-1", required = true),
    })
    public AjaxJson updateRemainXF(@RequestBody updateRemainXFForm form) {
        AjaxJson ajaxJson = new AjaxJson();
        if (StringUtils.isEmpty(form.getStuID()) ||form.getNewXF().intValue() == -1) {
            ajaxJson.setMsg("请完善信息");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        Pxstutable stu = iPxstutableService.getById(form.getStuID());
        stu.setRemainXuefei(form.getNewXF());
        ajaxJson.setSuccess(iPxstutableService.updateById(stu));
        return ajaxJson;
    }


    /**
     * @Description: updateRemainKs()方法作用:修改剩余课时
     * @param:[buxiID, newKS]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/8 11:24
     */
    @ResponseBody
    @RequestMapping(value = "updateRemainKs", method = RequestMethod.POST)
    @ApiOperation("修改剩余课时")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "buxiID", value = "补习ID", required = true),
            @ApiImplicitParam(name = "newKS", value = "新的课时", example = "-1", required = true),
    })
    public AjaxJson updateRemainKs(@RequestBody updateRemainKsForm form) {
        AjaxJson ajaxJson = new AjaxJson();
        if (StringUtils.isEmpty(form.getBuxiID()) || form.getNewKS().intValue() == -1) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("请完善信息");
            return ajaxJson;
        }
        Pxbuxikechengtable buxi = iPxbuxikechengtableService.getById(form.getBuxiID());
        buxi.setRemainkeshi(form.getNewKS());
        ajaxJson.setSuccess(iPxbuxikechengtableService.updateById(buxi));
        return ajaxJson;
    }


    /**
     * @Description: qiandanStuShowPage()方法作用:分页获取学员缴费详情
     * @param:[current, size, stuID]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/8 15:02
     */
    @ResponseBody
    @RequestMapping(value = "qiandanStuShowPage", method = RequestMethod.GET)
    @ApiOperation("分页获取学员缴费详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页获取数据", example = "10", required = true),
            @ApiImplicitParam(name = "stuID", value = "学号", required = true),
    })
    public AjaxJson qiandanStuShowPage(Long current, Long size, String stuID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper<qiandanstuVo> queryWrapper = new QueryWrapper<>();
        Page<qiandanstuVo> page = new Page(current, size);
        queryWrapper
                .eq("a.id", stuID)
                .eq("a.qiyeID", qiyeID)
                .orderByDesc("a.dengjiTime", "a.id")
        ;

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 254);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }


        ajaxJson.setObj(iPxstutableService.getqiandanstuShowPage(page, queryWrapper));
        return ajaxJson;
    }


    /**
     * @Description: getstukehaoShowPage()方法作用:分页获取学员课耗详情
     * @param:[current, size, stuID]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/8 15:26
     */
    @ResponseBody
    @RequestMapping(value = "getstukehaoShowPage", method = RequestMethod.GET)
    @ApiOperation("分页获取学员课耗详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页获取数据", example = "10", required = true),
            @ApiImplicitParam(name = "stuID", value = "学号", required = true),
    })
    public AjaxJson getstukehaoShowPage(Long current, Long size, String stuID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper<stukehaoShowVo> queryWrapper = new QueryWrapper<>();
        Page<stukehaoShowVo> page = new Page(current, size);
        queryWrapper
                .eq("b.id", stuID)
                .eq("a.qiyeID", qiyeID)
                .orderByDesc("a.haveClassDate");

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 254);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("b.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("b.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("b.campusID", lookPower);
        }

        ajaxJson.setObj(iPxkeshistutableService.getstukehaoShowPage(page, queryWrapper));
        return ajaxJson;
    }

    @ResponseBody
    @RequestMapping(value = "getstumessageList", method = RequestMethod.GET)
    @ApiOperation("获取学员详情")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true)
    )
    public AjaxJson getstumessageList(Long stuID) {
        AjaxJson ajaxJson = new AjaxJson();
        Pxstutable stu = iPxstutableService.getById(stuID);
        ajaxJson.setObj(stu);
        return ajaxJson;
    }

    //endregion

    //region 学员课时清空记录


    /**
     * @Description: getClearPage()方法作用:学员课时清空记录
     * @param:[current, size, campusID, stuName, kcName]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/12/8 11:56
     */
    @ResponseBody
    @RequestMapping(value = "getClearPage", method = RequestMethod.GET)
    @ApiOperation("分页获取学员课时清空记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页获取数据", example = "10", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "stuName", value = "学员名字", required = false),
            @ApiImplicitParam(name = "kcName", value = "课程", required = false),
    })
    public AjaxJson getClearPage(HttpServletRequest request, Long current,
                                 Long size,
                                 String campusID,
                                 String stuName,
                                 String kcName) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<SutClearVo> page = new Page<>(current, size);
        QueryWrapper<SutClearVo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("1", 1)
                .eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("b.campusID", campusID);
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.eq("b.stuName", stuName);
        }
        if (StringUtils.isNotBlank(kcName)) {
            queryWrapper.like("a.buxiName", kcName);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 254);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("b.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("b.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("b.campusID", lookPower);
        }
        queryWrapper.orderByDesc("a.addDate");
        ajaxJson.setObj(iPxkeshiresettableService.getClearPage(page, queryWrapper));
        return ajaxJson;
    }

    @ApiOperation(value = "删除课时清零")
    @ResponseBody
    @DeleteMapping("deletekeshiClear")
    public AjaxJson deletekeshiClear(String ids, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        String[] clearIds = ids.split(",");
        for (String item : clearIds) {
            Pxkeshiresettable one = iPxkeshiresettableService.getById(item);
            Pxbuxikechengtable buxi = iPxbuxikechengtableService.getById(one.getBuxiid());
            Pxstutable stu = iPxstutableService.getById(one.getStuid());

            if (stu == null) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("学号为：" + one.getStuid() + "的学员不存在！");
                return ajaxJson;
            }
            if (buxi == null) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("学员：" + stu.getStuName() + "的补习课程ID为" + one.getBuxiid() + "的补习课程不存在!");
                return ajaxJson;
            }
            buxi.setRemainkeshi(buxi.getRemainkeshi().add(one.getKeshinum()));
            iPxbuxikechengtableService.updateById(buxi);

            String AllMoney = iPxkeshiresettableService.getClearkeshiMoney(one.getStuid(), loginUser.getQiyeID());
            BigDecimal reMoney = new BigDecimal(AllMoney);
            stu.setRemainXuefei(reMoney);
            iPxstutableService.updateById(stu);

            iPxkeshiresettableService.removeById(item);
        }
        return ajaxJson;
    }


    /**
     * @Description: ExporestuClear()方法作用:导出学员课时清零
     * @param:[campusID, response]
     * @return:void
     * @auter:yyl
     * @data:2020/12/8 14:06
     */
    @ApiOperation(value = "导出学员课时清零")
    @ResponseBody
    @RequestMapping(value = "ExporestuClear", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区", required = false)
    })
    public void ExporestuClear(@RequestParam(required = false) String campusID,
                               HttpServletResponse response, HttpServletRequest request
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper<SutClearVo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("1", 1)
                .eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("b.campusID", campusID);
        }
        List<SutClearVo> sutClearVos = iPxkeshiresettableService.ExporestuClear(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "学员姓名", "课程", "清零课时", "清空学费", "说明", "清零时间"},
                sutClearVos,
                new String[]{"campusName", "stuName", "buxiName", "keshiNum", "xuefei", "beizhu", "addDate-D", ""});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "导出学员课时清零.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //endregion

}
