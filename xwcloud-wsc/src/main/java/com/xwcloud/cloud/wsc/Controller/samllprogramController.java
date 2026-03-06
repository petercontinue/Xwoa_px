package com.xwcloud.cloud.wsc.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.DateUtil;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.Pxstafftable;
import com.xwcloud.cloud.model.entity.Pxzuoyestujiaotable;
import com.xwcloud.cloud.wsc.Service.*;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

@Controller
@RequestMapping("/samllprogram")
@Api(tags = "微信小程序端")
public class samllprogramController {
    @Autowired
    IPxpaiketableService iPxpaiketableService;

    @Autowired
    IPxcampustableService iPxcampustableService;

    @Autowired
    IPxstutableService iPxstutableService;

    @Autowired
    IOaKehuService iOaKehuService;

    @Autowired
    IPxzuoyetableService iPxzuoyetableService;

    @Autowired
    IPxkeshistutableService iPxkeshistutableService;

    @Autowired
    IPxqingjiatableService iPxqingjiatableService;

    @Autowired
    IPxkechengtableService iPxkechengtableService;

    @Autowired
    IPxzuoyestujiaotableService iPxzuoyestujiaotableService;

    @Autowired
    IPxevaluationtableService iPxevaluationtableService;
    @Autowired
    IPxstafftableService iPxstafftableService;
    @Autowired
    IPxxiangcetableService iPxxiangcetableService;
    @Autowired
    IPxbuxikechengtableService iPxbuxikechengtableService;


    @RequestMapping(value = "/getstuXiangcePage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "学员获取电子相册分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = true),
            @ApiImplicitParam(name = "current", value = "页码", required = true),
            @ApiImplicitParam(name = "type", value = "null,1.学员相册   2.校区相册，3班级相册，....", required = false),
            @ApiImplicitParam(name = "title", value = "相册标题", required = false)
    })
    public AjaxJson getstuXiangcePage(
            HttpServletRequest request,
            @RequestParam(required = false, defaultValue = "10") long size,
            @RequestParam(required = false, defaultValue = "1") long current,
            @RequestParam(required = false, defaultValue = "0") int type,
            @RequestParam(required = false) String title,
            String stuID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<PxxiangcetableVo> page = new Page<>(current, size);
        QueryWrapper<PxxiangcetableVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("xiangce.qiyeID", loginUser.getQiyeID());

        String allClass = iPxbuxikechengtableService.getstuInClass(stuID, loginUser.getQiyeID());

        queryWrapper.and(a->a
                .eq("xiangce.type",1).eq("xiangce.stuID",stuID)
                .or(b->b.eq("xiangce.type",2))
                .or(c->c.eq("xiangce.type",3).in("xiangce.typeparmID", Arrays.asList(allClass.split(","))))
        );
        if (type > 0) {
            queryWrapper.eq("xiangce.type", type);
        }
        if (StringUtils.isNotBlank(title)) {
            queryWrapper.like("xiangce.title", title);
        }
        page = iPxxiangcetableService.getPage(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }


    @RequestMapping(value = "/getTeaInfo", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取小程序老师端老师信息")
    public AjaxJson getTeaInfo(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setObj(iPxstafftableService.getappteaInfo(new QueryWrapper<Pxstafftable>()
                .eq("a.qiyeID", loginUser.getQiyeID())
                .eq("a.staffTel", loginUser.getStaffTel())
        ));
        return ajaxJson;
    }


    @RequestMapping(value = "/getAppFeedbackPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取任课老师课后反馈")
    public AjaxJson getAppFeedbackPage(
            @RequestParam(required = false, defaultValue = "10") long size,
            @RequestParam(required = false, defaultValue = "1") long current,
            int type,
            @RequestParam(required = false) String campusID,
            @RequestParam(required = false) String stuId,
            HttpServletRequest request

    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<HashMap<String, String>> page = new Page(current, size);
        QueryWrapper<HashMap<String, String>> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("evalua.qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("evalua.stuid", stuId);
        queryWrapper.eq("evalua.type", type);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("campus.id", campusID);
        }

        page = iPxevaluationtableService.getPxevaluationtableJoinPage(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }


    /**
     * 分页查询学生课表信息
     *
     * @param request
     * @param size
     * @param current
     * @param stuName
     * @param SearchDate
     * @return
     */
    @RequestMapping(value = "/GetAllStuPaikePages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页查询学生排课信息")
    public AjaxJson GetAllStuPaikePages(HttpServletRequest request, long size, long current, String stuName, String SearchDate) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        Page<stupaikeVO> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("paike.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotEmpty(stuName)) {
            queryWrapper.eq("stu.stuName", stuName);
        }
        if (StringUtils.isNotBlank(SearchDate)) {
            queryWrapper.eq("paike.haveClassDate", SearchDate);
        }
        ajaxJson.setObj(iPxpaiketableService.GetStupaikePages(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * 分页查询教师课表信息
     *
     * @param request
     * @param size
     * @param current
     * @param teacherName
     * @param SearchDate
     * @return
     */
    @RequestMapping(value = "/GetAllPaikeTeacherPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页查询教师课表信息")
    public AjaxJson GetAllPaikeTeacherPages(HttpServletRequest request, long size, long current, String teacherName, String SearchDate) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<teapaikeVO> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("pai.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotEmpty(teacherName)) {
            queryWrapper.like("pai.teacherNames", teacherName);
        }
        if (StringUtils.isNotEmpty(SearchDate)) {
            queryWrapper.eq("pai.haveClassDate", SearchDate);
        }
        ajaxJson.setObj(iPxpaiketableService.GetTeacherPaikePages(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * 分页查询天课表信息
     *
     * @param request
     * @param size
     * @param current
     * @param searchDate
     * @return
     */
    @RequestMapping(value = "/GetDayPaikePages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页查询天课表信息")
    public AjaxJson GetDayPaikePages(HttpServletRequest request, long size, long current, String searchDate) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<daikebiaoVO> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("pai.qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("pai.haveClassDate", searchDate);
        ajaxJson.setObj(iPxpaiketableService.GetDayKebiaoPage(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * 查询消课信息
     *
     * @param request
     * @param size
     * @param current
     * @param teacherName
     * @param className
     * @param campusID
     * @param startDate
     * @param endDate
     * @return
     */
    @RequestMapping(value = "/GetAllXiaokeDataPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询消课信息")
    public AjaxJson GetAllXiaokeDataPages(HttpServletRequest request, long size, long current, String teacherName, String className, long campusID,
                                          String startDate, String endDate) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        Page<xiaokeVO> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("pai.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotEmpty(teacherName)) {
            queryWrapper.like("staff.staffName", teacherName);
        }
        if (StringUtils.isNotEmpty(className)) {
            queryWrapper.like("cla.className", className);
        }
        if (campusID != 0) {
            queryWrapper.eq("cla.campusID", campusID);
        }
        if (StringUtils.isNotEmpty(startDate)) {
            queryWrapper.ge("pai.haveClassDate", startDate);
        }
        if (StringUtils.isNotEmpty(endDate)) {
            queryWrapper.le("pai.haveClassDate", endDate);
        }
        ajaxJson.setObj(iPxpaiketableService.GetAllxiaokeDataPage(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * 查询报名交费课时数
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/TestGetStuInfo", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson TestGetStuInfo(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        return ajaxJson;
    }

    @RequestMapping(value = "/Getjiaofeikeshi", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询报名交费课时数")
    public AjaxJson Getjiaofeikeshi(HttpServletRequest request, String stuID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qdsubject.qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("qiandan.stuID", stuID);
        ajaxJson.setObj(iPxstutableService.GetAllbaomingRecords(queryWrapper));
        return ajaxJson;
    }


    /**
     * 查询充值信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetAllchongzhiInfoList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询充值信息")
    public AjaxJson GetAllchongzhiInfoList(HttpServletRequest request, String stuID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("stu.qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("stu.id", stuID);
        ajaxJson.setObj(iPxstutableService.GetStuChongzhjiList(queryWrapper));
        return ajaxJson;
    }


    /**
     * 查询学生剩余课时
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetSturemainKeshi", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询学生剩余课时")
    public AjaxJson GetSturemainKeshi(HttpServletRequest request, String stuID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("stu.qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("stu.id", stuID);
        ajaxJson.setObj(iPxstutableService.GetStuAllRemainkeshi(queryWrapper));
        return ajaxJson;
    }


    @ResponseBody
    @RequestMapping(value = "/GetAllStuList", method = RequestMethod.GET)
    @ApiOperation(value = "查询当前登录账号绑定的所有学生信息")
    public AjaxJson GetAllStuList(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("a.phoneNumber", loginUser.getStaffTel());
        ajaxJson.setObj(iPxstutableService.GetAllStuListLoginPhone(queryWrapper));
        return ajaxJson;
    }

    @ResponseBody
    @RequestMapping(value = "/getJigouListData", method = RequestMethod.GET)
    @ApiOperation(value = "查询电话对应的机构信息")
    public AjaxJson getJigouListData(String loginTel, Integer type) {
        AjaxJson ajaxJson = new AjaxJson();
        if (type == 1) {
            ajaxJson.setObj(iOaKehuService.GetPhoneIncloudJigouStu(loginTel));
        } else {
            ajaxJson.setObj(iOaKehuService.GetPhoneIncloudjigouStaff(loginTel));
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/GetAllzuoyePages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页查询学生作业")
    public AjaxJson GetAllzuoyePages(HttpServletRequest request, long size, long current, long stuID, int type) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<zuoyeVO> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("zuoye.qiyeID", loginUser.getQiyeID());
        if (stuID != 0) {
            queryWrapper.eq("buxi.stuID", stuID);
        }
        if (type == 1) {
            queryWrapper.ge("zuoye.endDate", new Date());
        } else {
            queryWrapper.le("zuoye.endDate", new Date());
        }
        ajaxJson.setObj(iPxzuoyetableService.GetAllstuZuoyeList(page, queryWrapper));
        return ajaxJson;
    }

    @RequestMapping(value = "/GetZuoyexiangqing", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询作业详情")
    public AjaxJson GetZuoyexiangqing(HttpServletRequest request, long zuoyeID) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("zuoye.qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("zuoye.id", zuoyeID);
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxzuoyetableService.GetZuoyexiangqing(queryWrapper));
        return ajaxJson;

    }

    @RequestMapping(value = "/saveZuoyeInfo", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存提交作业信息")
    public AjaxJson saveZuoyeInfo(HttpServletRequest request, long zuoyeID, String Imgs, String note) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Pxzuoyestujiaotable pxzuoyestujiaotable = new Pxzuoyestujiaotable();
        pxzuoyestujiaotable.setBeizhu(note);
        pxzuoyestujiaotable.setZuoyeID(zuoyeID);
        pxzuoyestujiaotable.setJiaozuoyeDateTime(new Date());
        pxzuoyestujiaotable.setZuoyeImg(Imgs);
        pxzuoyestujiaotable.setStuID(loginUser.getStaffID());
        pxzuoyestujiaotable.setQiyeID(loginUser.getQiyeID());
        ajaxJson.setSuccess(iPxzuoyestujiaotableService.save(pxzuoyestujiaotable));
        return ajaxJson;
    }


    @RequestMapping(value = "/Getstukeshi", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询学员课耗信息")
    public AjaxJson Getstukeshi(HttpServletRequest request,
                                Long size,
                                Long current,
                                Long stuID,
                                @RequestParam(required = false) String YearandMon//年月

    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Page<stuKehaoVo> page = new Page<>(current, size);
        QueryWrapper<stuKehaoVo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("a.qiyeID", loginUser.getQiyeID())
                .eq("b.id", stuID);
        if (StringUtils.isNotBlank(YearandMon)) {
            YearandMon = YearandMon + "-10";

            Date Ym = null;
            try {
                Ym = sdf.parse(YearandMon);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date sym = DateUtil.getTimesMonthmorning(Ym); //月初
            Date eym = DateUtil.getTimesMonthnight(Ym); //月末
            queryWrapper
                    .ge("a.haveClassDate", sym)
                    .le("a.haveClassDate", eym);
        }
        ajaxJson.setObj(iPxkeshistutableService.getkeshiStu(page, queryWrapper));
        return ajaxJson;
    }

    @ApiOperation("学员端积分使用记录")
    @ResponseBody
    @RequestMapping(value = "getIntegraInfoforStuPages", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true)
    })
    public AjaxJson getIntegraInfoforStuPages(Long current, Long size, String stuID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<stuIntegerVo> page = new Page<>(current, size);
        QueryWrapper<stuIntegerVo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("a.qiyeID", qiyeID)
                .eq("a.stuID", stuID);
        ajaxJson.setObj(iPxstutableService.getstuIntegraInfoPage(page, queryWrapper));
        return ajaxJson;
    }


}
