package com.xwcloud.cloud.zsbm.Controller;


import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiniu.common.QiniuException;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.DateUtil;
import com.xwcloud.cloud.common.ExportExcel;
import com.xwcloud.cloud.common.Qiniuutils;
import com.xwcloud.cloud.model.Form.*;
import com.xwcloud.cloud.model.Form.zsbm.AddOptionsForm;
import com.xwcloud.cloud.model.Form.zsbm.UpdateKechengInfoForm;
import com.xwcloud.cloud.model.Form.zsbm.czyhzcForm;
import com.xwcloud.cloud.model.Form.zsbm.qdfeiyongsmForm;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.*;
import com.xwcloud.cloud.model.zsbm.Vo.daijinquanVo;
import com.xwcloud.cloud.zsbm.Service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
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
 * @since 2020-11-10
 */
@Controller
@RequestMapping("/zsbm/BaoMingJiaoFei")
@Api(tags = "报名交费")
public class BaoMingJiaoFeiController {
    @Autowired
    IPxkechengtableService iPxkechengtableService;

    @Autowired
    IPxsubjecttableService iPxsubjecttableService;

    @Autowired
    IPxbuxikechengtableService iPxbuxikechengtableService;

    @Autowired
    IPxkechengcontenttableService iPxkechengcontenttableService;

    @Autowired
    IPxyouhuizhengcetableService iPxyouhuizhengcetableService;

    @Autowired
    IPxqiandaninfotableService iPxqiandaninfotableService;

    @Autowired
    IPxchongzhitableService iPxchongzhitableService;

    @Autowired
    IPxchongzhipaytableService iPxchongzhipaytableService;

    @Autowired
    IPxczhuodongtableService iPxczhuodongtableService;

    @Autowired
    IPxstutableService iPxstutableService;

    @Autowired
    IPxliushuizhangtableService iPxliushuizhangtableService;

    @Autowired
    IPxqiandanstafftableService iPxqiandanstafftableService;

    @Autowired
    IPxqiandaninfo2tableService iPxqiandaninfo2tableService;

    @Autowired
    IPxclasstableService iPxclasstableService;

    @Autowired
    IPxkeshizengsongtableService iPxkeshizengsongtableService;

    @Autowired
    IPxstuclasstableService iPxstuclasstableService;

    @Autowired
    IPxpaiketableService iPxpaiketableService;

    @Autowired
    IPxxuanketableService iPxxuanketableService;

    @Autowired
    IPxqiandansubjecttableService iPxqiandansubjecttableService;

    @Autowired
    IPxteachingsuppliestableService iPxteachingsuppliestableService;

    @Autowired
    IPxqiandansuppliesService iPxqiandansuppliesService;

    @Autowired
    IPxteachingsuppliesouttableService iPxteachingsuppliesouttableService;

    @Autowired
    IPxdaijinquantableService iPxdaijinquantableService;

    @Autowired
    IPxqiandanpaymoneyService iPxqiandanpaymoneyService;

    @Autowired
    IPxsysparamdefaulttableService iPxsysparamdefaulttableService;

    @Autowired
    IPxoldschooltableService iPxoldschooltableService;

    @Autowired
    IPxoldschoolteachertableService iPxoldschoolteachertableService;

    @Autowired
    IPxkeshizhuansongtableService iPxkeshizhuansongtableService;

    @Autowired
    IPxbuxistyletableService iPxbuxistyletableService;

    @Autowired
    IPxpaiketeachertableService iPxpaiketeachertableService;

    @Autowired
    IPxbxkcchangetableService iPxbxkcchangetableService;

    @Autowired
    IPxcampustableService iPxcampustableService;

    @Autowired
    IPxkeshistutableService iPxkeshistutableService;

    @Autowired
    IPxstukaoqingtableService iPxstukaoqingtableService;

    @Autowired
    IPxjifentableService iPxjifentableService;

    @Autowired
    IPxqiandaoqiantuitableService iPxqiandaoqiantuitableService;

    @Autowired
    IPxstucardtableService iPxstucardtableService;

    @Autowired
    IPxstukaoqingteachertableService iPxstukaoqingteachertableService;

    @Autowired
    IPxclasstimestyletableService iPxclasstimestyletableService;

    @Autowired
    IPxyxtelfromtableService iPxyxtelfromtableService;

    @Autowired
    IPxstuparamtypetableService iPxstuparamtypetableService;

    @Autowired
    IPxqiandanothermoneytableService iPxqiandanothermoneytableService;

    @Autowired
    IPxpowertableService iPxpowertableService;

    @Autowired
    IPxstuparamvaluetableService iPxstuparamvaluetableService;

    @Autowired
    IQiandanshenpiService iQiandanshenpiService;

    @Autowired
    IPxsysparamvaluetableService iPxsysparamvaluetableService;

    @Autowired
    IQiandanshenpiyejirenService iQiandanshenpiyejirenService;

    @Autowired
    IQiandanshenpipaymoneyService iQiandanshenpipaymoneyService;

    @Autowired
    IQiandanshenpisubjectService iQiandanshenpisubjectService;

    @Autowired
    IQiandanshenpizafeiService iQiandanshenpizafeiService;

    @Autowired
    IQiandanshenpisuppliesService iQiandanshenpisuppliesService;

    @Autowired
    IPxyxgengjintableService iPxyxgengjintableService;

    @Autowired
    IPxyxinvitedaofangtableService iPxyxinvitedaofangtableService;

    @Autowired
    IPxyxinvitationtableService iPxyxinvitationtableService;

    @Autowired
    IPxyxqiandantableService iPxyxqiandantableService;
    @Autowired
    savePxLog savePxLog;

    @Autowired
    IQiandanapppayService iQiandanapppayService;
    @Autowired
    IQiandanapppaymoneyService iQiandanapppaymoneyService;
    @Autowired
    IQiandanapppaysubjectService iQiandanapppaysubjectService;
    @Autowired
    IQiandanapppaysuppliesService iQiandanapppaysuppliesService;
    @Autowired
    IQiandanapppayyejirenService iQiandanapppayyejirenService;
    @Autowired
    IQiandanapppayzafeiService iQiandanapppayzafeiService;

    @Autowired
    IPxstafftableService iPxstafftableService;

    @Autowired
    IPxpaymoneystyletableService iPxpaymoneystyletableService;

    @Autowired
    IQiandanapppayZhuanjieshaoService iQiandanapppayZhuanjieshaoService;

    @Autowired
    IQiandanshenpiZhuanjieshaoService iQiandanshenpiZhuanjieshaoService;

    @Autowired
    IPxqiandanzhuanjieshaotableService iPxqiandanzhuanjieshaotableService;

    @Autowired
    IWscDongtaiinfoService iWscDongtaiinfoService;

    @Autowired
    IDongtaiDianzangService iDongtaiDianzangService;

    @Autowired
    IDongtaiPinglunService iDongtaiPinglunService;
    //region 课程信息

    @ResponseBody
    @GetMapping("getCampusTostaff")
    @ApiOperation(value = "获取带校区的员工信息")
    public AjaxJson getCampusTostaff(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        List<HashMap<String, Object>> campusTostaff =
                iPxstafftableService.getCampusTostaff(new QueryWrapper<Pxstafftable>()
                        .eq("a.staffState", 1)
                        .ne("b.isOpen", 2)
                        .eq("a.qiyeID", loginUser.getQiyeID())
                );
        ajaxJson.setObj(campusTostaff);
        return ajaxJson;
    }


    @ApiOperation("按班级查找排课信息")
    @GetMapping("xinqianGetPaikeByclassIDList")
    @ResponseBody
    public AjaxJson xinqianGetPaikeByclassIDList(String classID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<Pxpaiketable> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("classID", classID)
                .eq("qiyeID", loginUser.getQiyeID());
        queryWrapper.orderByAsc("haveClassDate");
        List<Pxpaiketable> list = iPxpaiketableService.list(queryWrapper);
        ajaxJson.setObj(list);
        return ajaxJson;
    }


    @ResponseBody
    @GetMapping("getPayStyle")
    @ApiOperation(value = "获取带校区的员工信息")
    public AjaxJson getPayStyle(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");

        List<Pxpaymoneystyletable> list = iPxpaymoneystyletableService.list(new QueryWrapper<Pxpaymoneystyletable>()
                .eq("qiyeID", loginUser.getQiyeID())
        );
        ajaxJson.setObj(list);
        return ajaxJson;
    }


    /**
     * 分页查询课程信息
     *
     * @param size
     * @param current
     * @param campusName
     * @param subjectID
     * @param kechengName
     * @param jifeiStyleID
     * @param buxiStyleName
     * @param classTimeStyleName
     * @return
     */
    @RequestMapping(value = "/getAllKechengPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页查询课程信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "campusName", value = "校区名称", required = false),
            @ApiImplicitParam(name = "subjectName", value = "科目名称", required = false),
            @ApiImplicitParam(name = "kechengName", value = "课程名称", required = false),
            @ApiImplicitParam(name = "buxiStyleName", value = "补习方式名称", required = false),
            @ApiImplicitParam(name = "classTimeStyleName", value = "课程时长", required = false)
    })
    public AjaxJson getAllKechengPages(long size, long current, String campusName, long subjectID,
                                       int isShow,
                                       String kechengName,
                                       Integer jifeiStyleID,
                                       String buxiStyleName,
                                       String classTimeStyleName,
                                       HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();
        Page<kechengListVo> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (subjectID != 0) {
            queryWrapper.eq("a.subjectID", subjectID);
        }
        if (StringUtils.isNotBlank(campusName)) {
            queryWrapper.eq("a.campusID", campusName);
        }
        if (StringUtils.isNotBlank(kechengName)) {
            queryWrapper.like("kechengName", kechengName);
        }
        if (StringUtils.isNotBlank(buxiStyleName)) {
            queryWrapper.like("a.buxiStyleID", buxiStyleName);
        }
        if (StringUtils.isNotBlank(classTimeStyleName)) {
            queryWrapper.like("a.classTimeStyleID", classTimeStyleName);
        }
        if (isShow != -1) {
            queryWrapper.eq("a.isShow", isShow);
        }
        if (jifeiStyleID != 0) {
            queryWrapper.eq("a.jifeiStyleID", jifeiStyleID);
        }
        //小程序端获取课程信息时，不需要按照权限查询
        if (loginUser.getLoginType() != 3) {
            QueryWrapper queryWrapper1 = new QueryWrapper();
            queryWrapper1.eq("qiyeID", qiyeID);
            queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
            queryWrapper1.eq("menuID", 146);
            String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
            if (lookPower.equals("0")) {//个人权限
                queryWrapper.eq("a.campusID", 0);
            } else if (lookPower.equals("-1")) {//所在校区权限
                queryWrapper.eq("a.campusID", loginUser.getCampusID());
            } else if (lookPower.equals("-2")) {//所有校区权限

            } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
                queryWrapper.in("a.campusID", lookPower);
            }
        } else {
            queryWrapper.eq("a.showInApp", 1);
        }
        queryWrapper.orderByDesc("a.id");
        ajaxJson.setObj(iPxkechengtableService.getAllKechengPages(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * 添加课程信息
     *
     * @return
     */
    @RequestMapping(value = "/AddKechengInfo", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加课程信息")
    public AjaxJson AddOptions(@RequestBody AddOptionsForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("CampusID", form.getCampusID());
        queryWrapper.eq("KechengName", form.getKechengName());
        Pxcampustable cam = iPxcampustableService.getById(form.getCampusID());
        List<Pxkechengtable> pxkechengtableList = iPxkechengtableService.list(queryWrapper);
        if (pxkechengtableList.size() > 0) {
            ajaxJson.setMsg("该校区已存在该名称的课程信息");
        } else {
            Pxkechengtable one = new Pxkechengtable();
            one
                    .setKechengName(form.getKechengName())
                    .setSubjectID(Long.valueOf(form.getSubjectID()))
                    .setBuxiStyleID(Long.valueOf(form.getBuxiStyleID()))
                    .setIs1v1KC(form.getIs1v1KC())
                    .setClassTimeStyleID(Long.valueOf(form.getClassTimeStyleID()))
                    .setKechengOriginalPrice(form.getKechengOriginalPrice())
                    .setKechengprice(form.getKechengprice())
                    .setKeshiNum(form.getKeshiNum())
                    .setBuyZonjia(form.getBuyZonjia())
                    .setIsShow(form.getIsShow())
                    .setJifeiStyleID(form.getJifeiStyleID())
                    .setCampusID(Long.valueOf(form.getCampusID()))
                    .setQiyeID(qiyeID)
                    .setBgColor(form.getBgColor())
                    .setPerdaysqj(form.getPerdaysqj())
                    .setPerkeshiqj(form.getPerkeshiqj())
                    .setQingjiaTimes(form.getQingjiaTimes())
                    .setIskoukeshi(form.getIskoukeshi())
                    .setKechengImg(form.getKechengImg())
                    .setKechengbeizhu(form.getKechengbeizhu())
                    .setKechengcontent(form.getKechengcontent())
                    .setShowInApp(Integer.valueOf(form.getShowInApp()))
                    .setShuakaTime(1)
                    .setByMonthOrDay(Integer.valueOf(form.getByMonthOrDay()));


            if (StringUtils.isNotBlank(form.getZSid())) {
                one.setZSid(Long.valueOf(form.getZSid()));

            }

            ajaxJson.setSuccess(iPxkechengtableService.save(one));
            String logtext = "添加了校区：" + cam.getCampusName() + "的新课程：" + one.getKechengName();
            savePxLog.savepxlog(logtext, "xwcloud-zsbm/zsbm/BaoMingJiaoFei/AddKechengInfo", loginUser.getStaffID(),
                    loginUser.getStaffName(), 1, loginUser.getQiyeID());

        }
        return ajaxJson;
    }

    /**
     * 修改课程信息
     *
     * @return
     */
    @RequestMapping(value = "/UpdateKechengInfo", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改课程信息")
    public AjaxJson UpdateKechengInfo(@RequestBody UpdateKechengInfoForm form,
                                      HttpServletRequest request
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        queryWrapper.ne("id",form.getId());
        queryWrapper.eq("CampusID",form.getCampusID());
        queryWrapper.eq("KechengName",form.getKechengName());
        Pxcampustable cam = iPxcampustableService.getById(form.getCampusID());

        List<Pxkechengtable> pxkechengtableList = iPxkechengtableService.list(queryWrapper);
        if (pxkechengtableList.size() > 0) {
            ajaxJson.setMsg("该校区已存在该名称的课程信息");
            ajaxJson.setCode("N");
            return ajaxJson;
        } else {
            Pxkechengtable one = iPxkechengtableService.getById(form.getId());
            String oldkcName = one.getKechengName();
            one
                    .setKechengName(form.getKechengName())
                    .setSubjectID(Long.valueOf(form.getSubjectID()))
                    .setBuxiStyleID(Long.valueOf(form.getBuxiStyleID()))
                    .setIs1v1KC(Integer.valueOf(form.getIs1v1KC()))
                    .setClassTimeStyleID(Long.valueOf(form.getClassTimeStyleID()))
                    .setKechengOriginalPrice(form.getKechengOriginalPrice())
                    .setKechengprice(form.getKechengprice())
                    .setKeshiNum(form.getKeshiNum())
                    .setBuyZonjia(form.getBuyZonjia())
                    .setIsShow(Integer.valueOf(form.getIsShow()))
                    .setJifeiStyleID(Integer.valueOf(form.getJifeiStyleID()))
                    .setCampusID(Long.valueOf(form.getCampusID()))
                    .setBgColor(form.getBgColor())
                    .setPerdaysqj(Integer.valueOf(form.getPerdaysqj()))
                    .setPerkeshiqj(form.getPerkeshiqj())
                    .setQingjiaTimes(Integer.valueOf(form.getQingjiaTimes()))
                    .setIskoukeshi(form.getIskoukeshi())
                    .setKechengImg(form.getKechengImg())
                    .setKechengbeizhu(form.getKechengbeizhu())
                    .setKechengcontent(form.getKechengcontent())
                    .setShowInApp(Integer.valueOf(form.getShowInApp()))
                    .setShuakaTime(1)
                    .setByMonthOrDay(Integer.valueOf(form.getByMonthOrDay()));


            if (StringUtils.isNotBlank(form.getZsid())) {
                one.setZSid(Long.valueOf(form.getZsid()));
            }

            ajaxJson.setSuccess(iPxkechengtableService.updateById(one));
            String logtext = "x修改了校区：" + cam.getCampusName() + "的课程。修改前：" + oldkcName + ",修改后：" + one.getKechengName();
            savePxLog.savepxlog(logtext, "xwcloud-zsbm/zsbm/BaoMingJiaoFei/AddKechengInfo", loginUser.getStaffID(),
                    loginUser.getStaffName(), 1, loginUser.getQiyeID());
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/GetKechengInfoById", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "根据课程Id查询课程信息")
    public AjaxJson GetKechengInfoById(long Id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxkechengtableService.getById(Id));
        return ajaxJson;
    }

    /**
     * 删除课程信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/DeleteKechengInfo", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除课程信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "删除数据Id,多个id逗号隔开", required = true),
    })
    public AjaxJson DeleteKechengInfo(String ids, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        String[] delIDs = ids.split(",");
        String logtext = "删除课程：";
        // 使用Arrays.asList 转换
        List<String> idsList = Arrays.asList(delIDs);

        for (String id : idsList) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("kechengID", id);
            List<Pxbuxikechengtable> pxbuxikechengtableList = iPxbuxikechengtableService.list(queryWrapper);
            if (pxbuxikechengtableList.size() > 0) {
                ajaxJson.setMsg("该课程已有学生报名，不允许删除");
                ajaxJson.setCode("N");
                return ajaxJson;
            }
            Pxkechengtable kc = iPxkechengtableService.getById(id);
            logtext += kc.getKechengName() + ",";
        }

        ajaxJson.setSuccess(iPxkechengtableService.removeByIds(idsList));
        savePxLog.savepxlog(logtext, "xwcloud-zsbm/zsbm/BaoMingJiaoFei/DeleteKechengInfo", loginUser.getStaffID(),
                loginUser.getStaffName(), 1, loginUser.getQiyeID());

        return ajaxJson;
    }

    /**
     * 查询所有的科目信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetAllSubjectList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询所有的科目信息")
    public AjaxJson GetAllSubjectList(HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxsubjecttableService.GetAllSubjectList(qiyeID));
        return ajaxJson;
    }

    /**
     * 小程序端获取所有的科目信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetAllSubjectListnocampus", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询所有的科目信息")
    public AjaxJson GetAllSubjectListnocampus(HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxsubjecttableService.GetAllSubjectListnocampus(qiyeID));
        return ajaxJson;
    }

    /**
     * 查询所有补习方式
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetAllBuxistyleList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询所有补习方式")
    public AjaxJson GetAllBuxistyleList(HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxbuxistyletableService.getbuxistyleList(qiyeID));
        return ajaxJson;
    }

    /**
     * 查询所有的课程时长信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetAllClassTimeStyleList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询所有的课程时长信息")
    public AjaxJson GetAllClassTimeStyleList(HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxclasstimestyletableService.GetAllclasstimestyleList(qiyeID));
        return ajaxJson;
    }

    @RequestMapping(value = "/GetAllSubjectListBycampusID", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "根据校区获取科目信息")
    public AjaxJson GetAllSubjectListBycampusID(Long campusID, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxsubjecttableService.GetAllSubjectByxqIDAndqiyeID(qiyeID, campusID));
        return ajaxJson;
    }

    /**
     * 修改课程启用状态
     *
     * @param Id
     * @param state
     * @return
     */
    @RequestMapping(value = "/UpdateKechengState", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "修改课程启用状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Id", value = "修改课程ID", required = true),
            @ApiImplicitParam(name = "state", value = "课程状态", required = true),
    })
    public AjaxJson UpdateKechengState(String Id, int state, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");

        String[] IDs = Id.split(",");
        String logtext = "修改课程:";

        List<Pxkechengtable> list = iPxkechengtableService.list(new QueryWrapper<Pxkechengtable>()
                .in("id", IDs)
                .eq("qiyeID", loginUser.getQiyeID())
        );
        for (Pxkechengtable item : list) {
            item.setIsShow(state);
            logtext += item.getKechengName() + ",";
            iPxkechengtableService.updateById(item);
        }
        if (state == 1) {
            logtext += "的启用状态为启用";
        } else {
            logtext += "的启用状态为不启用";
        }

        savePxLog.savepxlog(logtext, "xwcloud-zsbm/zsbm/BaoMingJiaoFei/UpdateKechengState", loginUser.getStaffID(),
                loginUser.getStaffName(), 1, loginUser.getQiyeID());
        return ajaxJson;
    }

    /**
     * 课程信息导出
     *
     * @param response
     */
    @RequestMapping(value = "/exportKechengInfo", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("导出课程信息")
    public void exportKechengInfo(HttpServletResponse response) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("1", 1);
        List<kechengListVo> pxkechengList = iPxkechengtableService.getAllKechengList(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "科目", "课程名称", "课程内容", "课程启用状态",
                        "计费方式", "培训方式", "课时时长/课时", "课时", "课程单价（元/课时）", "课程总价(元)"},
                pxkechengList,
                new String[]{"campusName", "subjectName", "kechengName", "kechengcontent", "isShow", "jifeiStyleID",
                        "buxiStyleName", "classTimeStyleName", "keshiNum", "kechengprice", "buyZonjia"});

        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "课程信息导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion

    //region 科目设置
    @RequestMapping(value = "/getAllSubjectPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询科目信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
    })
    public AjaxJson getAllSubjectPages(long size, long current, String campusID, String kemu, long menuID,
                                       HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", qiyeID);
        Page<subjectVO> page = new Page(current, size);

        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("a.campusID", campusID);
        }
        if (StringUtils.isNotBlank(kemu)) {
            queryWrapper.like("a.subjectName", kemu);
        }

        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", menuID);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }
        page = (Page<subjectVO>) iPxsubjecttableService.getAllsubjectPages(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 添加科目信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/AddSubjectInfo", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加科目信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "subjectName", value = "科目名称", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson AddSubjectInfo(String subjectName, Long campusID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Pxcampustable cam = iPxcampustableService.getById(campusID);
        List<Pxsubjecttable> pxsubjecttableList = iPxsubjecttableService.GetsubjectList(campusID, subjectName);

        if (pxsubjecttableList.size() > 0) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("该校区已存在这个名称的科目");
            return ajaxJson;
        } else {
            Pxsubjecttable pxsubjecttable = new Pxsubjecttable();
            pxsubjecttable.setSubjectName(subjectName);
            pxsubjecttable.setCampusID(campusID);
            pxsubjecttable.setQiyeID(qiyeID);
            iPxsubjecttableService.save(pxsubjecttable);
            ajaxJson.setMsg("新增科目保存成功");

        }

        savePxLog.savepxlog("添加校区：" + cam.getCampusName() + "，新科目：" + subjectName, "xwcloud-zsbm/zsbm/BaoMingJiaoFei" +
                "/AddSubjectInfo", loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());
        return ajaxJson;
    }

    /**
     * 修改科目信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/UpdateSubJectInfo", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改科目信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "修改数据ID", required = true),
            @ApiImplicitParam(name = "subjectName", value = "科目名称", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson UpdateSubJectInfo(long id, String subjectName, long campusID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");

        Pxsubjecttable pxsubjecttable = iPxsubjecttableService.GetSubjectById(id);
        String oldname = pxsubjecttable.getSubjectName();
        pxsubjecttable.setSubjectName(subjectName);
        pxsubjecttable.setCampusID(campusID);
        ajaxJson.setSuccess(iPxsubjecttableService.updateById(pxsubjecttable));
        Pxcampustable cam = iPxcampustableService.getById(campusID);
        savePxLog.savepxlog("修改校区：" + cam.getCampusName() + "，原科目：" + oldname + "的科目信息。修改后科目为：" + pxsubjecttable.getSubjectName(), "xwcloud-zsbm/zsbm" +
                "/BaoMingJiaoFei/UpdateSubJectInfo", loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());

        return ajaxJson;
    }

    /**
     * 删除科目信息
     *
     * @param Id
     * @return
     */
    @RequestMapping(value = "/DeleteSubjectbyId", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除科目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Id", value = "删除科目ID", required = true)
    })
    public AjaxJson DeleteSubjectbyId(String Id, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        String[] kmIDlist = Id.split(",");
        String logtext = "删除科目:";

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.in("subjectID", kmIDlist);
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());

        List<Pxkechengtable> pxkechengtableList = iPxkechengtableService.list(queryWrapper);
        if (pxkechengtableList.size() > 0) {
            ajaxJson.setMsg("该科目下存在课程，请先删除课程，再删除科目");
            ajaxJson.setCode("N");
            return ajaxJson;
        } else {
            List<Pxsubjecttable> sublist = iPxsubjecttableService.list(new QueryWrapper<Pxsubjecttable>()
                    .eq("qiyeID", loginUser.getQiyeID())
                    .in("id", kmIDlist));
            for (Pxsubjecttable item : sublist) {
                logtext += item.getSubjectName() + ",";
            }

            ajaxJson.setSuccess(iPxsubjecttableService.removeByIds(Arrays.asList(kmIDlist)));
        }


        savePxLog.savepxlog(logtext, "xwcloud-zsbm/zsbm/BaoMingJiaoFei/DeleteSubjectbyId", loginUser.getStaffID(),
                loginUser.getStaffName(), 1, loginUser.getQiyeID());

        return ajaxJson;
    }
    //endregion

    //region 课程内容

    /**
     * 分页获取课程内容信息
     *
     * @param size
     * @param current
     * @return
     */
    @RequestMapping(value = "/GetAllKechengContent", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("分页获取课程内容信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "kechengID", value = "kechengID", required = true)
    })
    public AjaxJson GetAllKechengContent(long size, long current, long kechengID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Page<kechengContentVo> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", qiyeID);
        queryWrapper.eq("a.kechengID", kechengID);
        ajaxJson.setObj(iPxkechengcontenttableService.getKechengContentPages(page, queryWrapper));
        return ajaxJson;

    }

    @RequestMapping(value = "/PiliangAddKechengContent", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "批量添加课程内容")
    public AjaxJson PiliangAddKechengContent(@RequestBody plkcContentForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        long staffid = loginUser.getStaffID();
        List<plkechengContentVO> plkechengContent = JSON.parseArray(form.getKechengcontentList(),
                plkechengContentVO.class);
        for (plkechengContentVO item : plkechengContent) {
            Pxkechengcontenttable pxkechengcontenttable = new Pxkechengcontenttable();
            pxkechengcontenttable.setAddStaffID(staffid);
            pxkechengcontenttable.setAddTime(new Date());
            pxkechengcontenttable.setKechengContent(item.getValueName());
            pxkechengcontenttable.setContentPaixu(item.getXuhao());
            pxkechengcontenttable.setQiyeID(qiyeID);
            pxkechengcontenttable.setKechengID(form.getKechengID());
            iPxkechengcontenttableService.save(pxkechengcontenttable);
        }
        ajaxJson.setMsg("课程内容信息保存成功");

        savePxLog.savepxlog("批量设置课程内容", "xwcloud-zsbm/zsbm/BaoMingJiaoFei/PiliangAddKechengContent",
                loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());

        return ajaxJson;
    }

    /**
     * 添加一次课程内容
     *
     * @param kechengID
     * @param contentPaixu
     * @param kechengContent
     * @param request
     * @return
     */
    @RequestMapping(value = "/addyiciKechengContent", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加一次课程内容")
    public AjaxJson addyiciKechengContent(long kechengID, Integer contentPaixu, String kechengContent,
                                          HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        long staffID = loginUser.getStaffID();
        Pxkechengtable kc = iPxkechengtableService.getById(kechengID);
        List<Pxkechengcontenttable> pxkechengcontenttableList =
                iPxkechengcontenttableService.getKechengcontentBykcidandpx(kechengID, contentPaixu);
        if (pxkechengcontenttableList.size() > 0) {
            ajaxJson.setMsg("已存在该次序号的课程内容");
            ajaxJson.setCode("N");
            return ajaxJson;
        } else {
            Pxkechengcontenttable pxkechengcontenttable = new Pxkechengcontenttable();
            pxkechengcontenttable.setKechengContent(kechengContent);
            pxkechengcontenttable.setContentPaixu(contentPaixu);
            pxkechengcontenttable.setQiyeID(qiyeID);
            pxkechengcontenttable.setAddTime(new Date());
            pxkechengcontenttable.setAddStaffID(staffID);
            pxkechengcontenttable.setKechengID(kechengID);
            iPxkechengcontenttableService.save(pxkechengcontenttable);
            ajaxJson.setMsg("课程内容保存成功");
        }


        savePxLog.savepxlog("添加了课程：" + kc.getKechengName() + "的一次课程内容。", "xwcloud-zsbm/zsbm/BaoMingJiaoFei" +
                "/addyiciKechengContent", loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());
        return ajaxJson;

    }

    /**
     * 删除课程内容
     *
     * @param Id
     * @return
     */
    @RequestMapping(value = "/DeleteKechengContent", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation("删除课程内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Id", value = "删除数据ID", required = true)
    })
    public AjaxJson DeleteKechengContent(String Id, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");

        Pxkechengcontenttable kcnr = iPxkechengcontenttableService.getById(Id);
        Pxkechengtable kc = iPxkechengtableService.getById(kcnr.getKechengID());

        ajaxJson.setSuccess(iPxkechengcontenttableService.removeById(Id));
        savePxLog.savepxlog("删除了课程：" + kc.getKechengName() + "的课程内容。", "xwcloud-zsbm/zsbm/BaoMingJiaoFei" +
                "/DeleteKechengContent", loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());

        return ajaxJson;
    }

    /**
     * 新增课程内容
     *
     * @param pxkechengcontenttable
     * @return
     */
    @RequestMapping(value = "/AddKechengContent", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("新增课程内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "kechengContent", value = "课程内容", required = true),
            @ApiImplicitParam(name = "contentPaixu", value = "课程内容序号", required = true),
            @ApiImplicitParam(name = "kechengID", value = "课程ID", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
            @ApiImplicitParam(name = "addTime", value = "添加时间", required = true),
            @ApiImplicitParam(name = "addStaffID", value = "添加人", required = true),
    })
    public AjaxJson AddKechengContent(Pxkechengcontenttable pxkechengcontenttable, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setSuccess(iPxkechengcontenttableService.save(pxkechengcontenttable));
        savePxLog.savepxlog("新增课程内容", "xwcloud-zsbm/zsbm/BaoMingJiaoFei/AddKechengContent", loginUser.getStaffID(),
                loginUser.getStaffName(), 1, loginUser.getQiyeID());

        return ajaxJson;
    }

    /**
     * 修改课程内容
     *
     * @param id
     * @param kechengContent
     * @param contentPaixu
     * @return
     */
    @RequestMapping(value = "/UpdateKechengContent", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("修改课程内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "修改数据ID", required = true),
            @ApiImplicitParam(name = "kechengContent", value = "课程内容", required = true),
            @ApiImplicitParam(name = "contentPaixu", value = "课程内容序号", required = true),
    })
    public AjaxJson UpdateKechengContent(long id, String kechengContent, Integer contentPaixu,
                                         HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Pxkechengcontenttable pxkechengcontenttable = iPxkechengcontenttableService.getById(id);
        pxkechengcontenttable.setKechengContent(kechengContent);
        pxkechengcontenttable.setContentPaixu(contentPaixu);
        ajaxJson.setSuccess(iPxkechengcontenttableService.updateById(pxkechengcontenttable));
        savePxLog.savepxlog("删除了课程内容。", "xwcloud-zsbm/zsbm/BaoMingJiaoFei/UpdateKechengContent",
                loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());

        return ajaxJson;
    }
    //endregion

    //region 报名优惠政策

    /***
     * 分页查询所有的优惠政策
     * @param size
     * @param current
     * @return
     */
    @RequestMapping(value = "/GetAllYouhuizhengcePages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("分页查询所有的优惠政策")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
    })
    public AjaxJson GetAllYouhuizhengcePages(long size, long current, int type, int yhType,
                                             HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (type != -1) {
            if (type == 1) {
                queryWrapper.ge("a.endDatetime", new Date());
            } else if (type == 2) {
                queryWrapper.lt("a.endDatetime", new Date());
            }
        }
        if (yhType != -1) {
            queryWrapper.eq("a.youhuiType", yhType);
        }
        queryWrapper.orderByDesc("a.addTime");
        Page<youhuizhengceVO> page = new Page(current, size);
        page = (Page<youhuizhengceVO>) iPxyouhuizhengcetableService.GetYouhuizhengcePages(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 新增优惠政策
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/AddYouhuizhengCe", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("新增优惠政策")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "youhuiType", value = "1：折扣；2：满减", required = true),
            @ApiImplicitParam(name = "xianzhijine", value = "限制金额,即达到多少金额享受优惠", required = true),
            @ApiImplicitParam(name = "youhui", value = "优惠金额", required = true),
            @ApiImplicitParam(name = "startDateTime", value = "开始使用时间", required = true),
            @ApiImplicitParam(name = "endDatetime", value = "结束使用时间", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
            @ApiImplicitParam(name = "stuGradeIDs", value = "哪些年级可以用这个优惠政策。年级ID逗号分隔。为空表示通用（即所有年级均可使用）；", required =
                    true),
            @ApiImplicitParam(name = "useTimes", value = "是指优惠政策可以使用的次数；-1表示不限次数，默认值-1", required = true),
    })
    public AjaxJson AddYouhuizhengCe(@RequestBody addyouhuizcForm form, HttpServletRequest request) throws ParseException {
        AjaxJson ajaxJson = new AjaxJson();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Pxyouhuizhengcetable pxyouhuizhengcetable = new Pxyouhuizhengcetable();
        pxyouhuizhengcetable.setCampusID(form.getCampusID());
        pxyouhuizhengcetable.setEndDatetime(dateFormat.parse(form.getEndDatetime()));
        pxyouhuizhengcetable.setStartDateTime(dateFormat.parse(form.getStartDateTime()));
        pxyouhuizhengcetable.setQiyeID(qiyeID);
        pxyouhuizhengcetable.setYouhui(form.getYouhui());
        pxyouhuizhengcetable.setXianzhijine(form.getXianzhijine());
        pxyouhuizhengcetable.setStuGradeIDs(form.getStuGradeIDs());
        pxyouhuizhengcetable.setUseTimes(form.getUseTimes());
        pxyouhuizhengcetable.setYouhuiType(form.getYouhuiType());
        pxyouhuizhengcetable.setAddTime(new Date());
        ajaxJson.setSuccess(iPxyouhuizhengcetableService.save(pxyouhuizhengcetable));


        savePxLog.savepxlog("添加了优惠政策。ID:" + pxyouhuizhengcetable.getId(), "xwcloud-zsbm/zsbm/BaoMingJiaoFei" +
                "/AddYouhuizhengCe", loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());

        return ajaxJson;
    }

    @RequestMapping(value = "/GetYouhuizhengceInfos", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询优惠政策详情")
    public AjaxJson GetYouhuizhengceInfos(long Id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxyouhuizhengcetableService.getById(Id));
        return ajaxJson;
    }

    /**
     * 修改优惠政策信息
     *
     * @param pxyouhuizhengcetable
     * @return
     */
    @RequestMapping(value = "/UpdateYouhuiZhengce", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("修改优惠政策信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "修改优惠政策ID", required = true),
            @ApiImplicitParam(name = "youhuiType", value = "1：折扣；2：满减", required = true),
            @ApiImplicitParam(name = "xianzhijine", value = "限制金额,即达到多少金额享受优惠", required = true),
            @ApiImplicitParam(name = "youhui", value = "优惠金额", required = true),
            @ApiImplicitParam(name = "startDateTime", value = "开始使用时间", required = true),
            @ApiImplicitParam(name = "endDatetime", value = "结束使用时间", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
            @ApiImplicitParam(name = "stuGradeIDs", value = "哪些年级可以用这个优惠政策。年级ID逗号分隔。为空表示通用（即所有年级均可使用）；", required =
                    true),
            @ApiImplicitParam(name = "useTimes", value = "是指优惠政策可以使用的次数；-1表示不限次数，默认值-1", required = true),
    })
    public AjaxJson UpdateYouhuiZhengce(@RequestBody Pxyouhuizhengcetable pxyouhuizhengcetable, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setSuccess(iPxyouhuizhengcetableService.updateById(pxyouhuizhengcetable));

        savePxLog.savepxlog("修改了优惠政策。ID:" + pxyouhuizhengcetable.getId(), "xwcloud-zsbm/zsbm/BaoMingJiaoFei" +
                "/UpdateYouhuiZhengce", loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());

        return ajaxJson;
    }

    /**
     * 删除优惠政策
     *
     * @param Id
     * @return
     */
    @RequestMapping(value = "/DeleteYouHuizhengce", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation("删除优惠政策")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Id", value = "删除数据Id", required = true),
    })
    public AjaxJson DeleteYouHuizhengce(String Id, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setSuccess(iPxyouhuizhengcetableService.removeById(Id));
        savePxLog.savepxlog("删除了优惠政策。ID为：" + Id, "xwcloud-zsbm/zsbm/BaoMingJiaoFei/UpdateKechengContent",
                loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());

        return ajaxJson;
    }
    //endregion

    //region 签单信息(新签)

    /**
     * 查询签单信息（新签）
     *
     * @param size
     * @param current
     * @param campusID
     * @param zidingyiStuID
     * @param stuName
     * @param stuGradeID
     * @param jinbanStaffName
     * @param Sqiandandate
     * @param Eqiandandate
     * @param yejistaffname
     * @param request
     * @return
     */
    @RequestMapping(value = "/getAllQianDanInfoPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("查询签单信息（新签）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "campusName", value = "校区名称", required = false),
            @ApiImplicitParam(name = "zidingyiStuID", value = "自定义学号", required = false),
            @ApiImplicitParam(name = "stuName", value = "学生姓名", required = false),
            @ApiImplicitParam(name = "stuGradeName", value = "学生年级", required = false),
            @ApiImplicitParam(name = "jinbanStaffName", value = "经办人", required = false),
    })
    public AjaxJson getAllQianDanInfoPages(long size, long current, long campusID, String zidingyiStuID,
                                           String stuName, long stuGradeID, String jinbanStaffName
            , String Sqiandandate, String Eqiandandate, String yejistaffname, long menuID, int isweikuan,
                                           HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Page<qianDanInFoVo> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", qiyeID);
        queryWrapper.eq("a.moneyStyle", 1);
        if (StringUtils.isNotBlank(zidingyiStuID)) {
            queryWrapper.like("b.zidingyiStuID", zidingyiStuID);
        }
        if (campusID != 0) {
            queryWrapper.eq("b.campusID", campusID);
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("b.stuName", stuName);
        }
        if (stuGradeID != 0) {
            queryWrapper.eq(" b.stuGradeID", stuGradeID);
        }
        if (StringUtils.isNotBlank(jinbanStaffName)) {
            queryWrapper.like("jinbanStaffName", jinbanStaffName);
        }
        if (StringUtils.isNotBlank(Sqiandandate)) {
            queryWrapper.ge("a.qiandandate", Sqiandandate);
        }
        if (StringUtils.isNotBlank(Eqiandandate)) {
            queryWrapper.le("a.qiandandate", Eqiandandate);
        }
        if (StringUtils.isNotBlank(yejistaffname)) {
            queryWrapper.like("yejistaffName", yejistaffname);
        }
        if (isweikuan != -1) {
            queryWrapper.gt("a.HetongMoney-a.shishouTotalMoney", 0);
        }

        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", menuID);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.recordInStaffID", loginUser.getStaffID());
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("b.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("b.campusID", lookPower);
        }
        queryWrapper.orderByDesc("a.recordInTime");
        ajaxJson.setObj(iPxqiandaninfotableService.GetQiandanInfoPages(page, queryWrapper));
        return ajaxJson;
    }

    @RequestMapping(value = "/panduanStuName", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "判断当前输入的姓名的学生是否已存在")
    public AjaxJson panduanStuName(HttpServletRequest request, String stuName) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("stuName", stuName);
        queryWrapper.gt("buxiStateID", 1);
        Pxstutable pxstutable = iPxstutableService.getOne(queryWrapper);
        if (pxstutable != null) {
            ajaxJson.setMsg("当前系统已存在这个名字的学生");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        return ajaxJson;
    }

    /**
     * 查询所有的意向学员来源
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetAllTelFromList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询所有的意向学员来源")
    public AjaxJson GetAllTelFromList(HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxyxtelfromtableService.GetAllTelFromList(qiyeID));
        return ajaxJson;
    }

    @RequestMapping(value = "/GetYxStuInfo", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询意向学生信息")
    public AjaxJson GetYxStuInfo(HttpServletRequest request, long yxstuID) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxstutableService.getById(yxstuID));
        return ajaxJson;
    }

    /**
     * 保存新签
     *
     * @param form
     * @return
     */
    @RequestMapping(value = "/AddNewStuAndQianDan", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("保存新签报名")
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson AddNewStuAndQianDan(@RequestBody xinqianForm form, HttpServletRequest request) throws ParseException {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();
        String logtext = "";

        if (StringUtil.isNotBlank(form.getZidingyiStuNO())) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("zidingyiStuID", form.getZidingyiStuNO());
            queryWrapper.eq("qiyeID", loginUser.getQiyeID());
            List<Pxstutable> pxstutableList = iPxstutableService.getStuByZidingyi(queryWrapper);
            if (pxstutableList.size() > 0) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("操作失败！学生自定义学号不能重复！");
                return ajaxJson;
            }
        }

        if (form.getStuid() == 0) {
            QueryWrapper queryWrapper1 = new QueryWrapper();
            queryWrapper1.eq("stuName", form.getStuName());
            queryWrapper1.eq("qiyeID", loginUser.getQiyeID());
            queryWrapper1.gt("buxiStateID", 1);
            List<Pxstutable> pxstutableList1 = iPxstutableService.getStuByZidingyi(queryWrapper1);
            if (pxstutableList1.size() > 0 && form.getYxStuID() == 0) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("学员姓名不能重复，请更换");
                return ajaxJson;
            }
        }


        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Pxstutable newstu = form.getStuid() != 0 ? iPxstutableService.getById(form.getStuid()) :
                form.getYxStuID() == 0 ? new Pxstutable() : iPxstutableService.getById(form.getYxStuID());
        newstu.setStuName(form.getStuName());
        newstu.setParentTel(form.getParentsTel());
        newstu.setCampusID(form.getCampusID());
        newstu.setStuGradeID(form.getStuGradeID());
        newstu.setBuxiStateID(form.getBuxiStateID());
        newstu.setJifenNum(new BigDecimal(0));
        if (StringUtils.isNotBlank(form.getStuBrithday())) {
            try {
                newstu.setStubirth(ft.parse(form.getStuBrithday()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        newstu.setDengjiTime(new Date());
        newstu.setStuTel(form.getParentsTel());
        newstu.setQiyeID(loginUser.getQiyeID());
        newstu.setActivity(2); //1 冻结  2正常
        newstu.setRemainChongzhi(new BigDecimal(0));
        newstu.setPasswd(new BCryptPasswordEncoder().encode("123456"));
        newstu.setParentTelRelation(form.getParentTelRelation());  //家长电话关系：1本人，2爸爸，3妈妈，4爷爷，5奶奶，6外公，7外婆，8保姆，9其他
        if (form.getYxStuID() != 0) {
            newstu.setLuruType(1);  //学员数据录入的方式：1意向录入，2表格导入，3电脑后台报名签单录入，4微信端支付报名录入
        } else {
            newstu.setLuruType(3);  //学员数据录入的方式：1意向录入，2表格导入，3电脑后台报名签单录入，4微信端支付报名录入
        }

        newstu.setDengjiTime(new Date());
        newstu.setDengjiTeacherID(form.getQianDanStaffID());
//        newstu.setOldSchool(form.getOldSname());
//        newstu.setOldSchoolTeacher(form.getOldSchoolTeacherID());

        if (StringUtils.isNotBlank(form.getOldSchoolID())) {
            Pxoldschooltable school = iPxoldschooltableService.GetOldSchoolByName(form.getOldSchoolID());
            if (school != null) {
                newstu.setOldSchool(school.getOldSchoolID());
                if (StringUtils.isNotBlank(form.getOldSchoolTeacherID())) {
                    Pxoldschoolteachertable teacher =
                            iPxoldschoolteachertableService.GetOldschoolteacherBytnameAndsID(form.getOldSchoolTeacherID(),
                                    school.getOldSchoolID());
                    if (teacher != null) {
                        newstu.setOldSchoolTeacher(teacher.getOldSchoolTeacherID());
                    } else {
                        Pxoldschoolteachertable tea = new Pxoldschoolteachertable();
                        tea.setOldSchoolID(school.getOldSchoolID());
                        tea.setOldSchoolTeacherName(form.getOldSchoolTeacherID());
                        tea.setQiyeID(qiyeID);
                        iPxoldschoolteachertableService.save(tea);
                        newstu.setOldSchoolTeacher(tea.getOldSchoolTeacherID());
                    }
                }
            } else {
                Pxoldschooltable sch = new Pxoldschooltable();
                sch.setOldSchoolName(form.getOldSchoolID());
                sch.setQiyeID(qiyeID);
                iPxoldschooltableService.save(sch);
                newstu.setOldSchool(sch.getOldSchoolID());

                Pxoldschoolteachertable tea = new Pxoldschoolteachertable();
                tea.setOldSchoolID(sch.getOldSchoolID());
                tea.setOldSchoolTeacherName(form.getOldSchoolTeacherID());
                tea.setQiyeID(qiyeID);
                iPxoldschoolteachertableService.save(tea);
                newstu.setOldSchoolTeacher(tea.getOldSchoolTeacherID());
            }
        }

        BigDecimal youhuiMoney = new BigDecimal(0);
        Pxyouhuizhengcetable pxyouhuizhengcetable =
                iPxyouhuizhengcetableService.getYouhuizhengceById(form.getYouhuizhengce());
        if (form.getYouhuizhengce() != 0L) {
            BigDecimal zong = form.getAmountKC().add(form.getAmountWp().add(form.getAmountOther()));
            if (pxyouhuizhengcetable == null) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("不存在所选择优惠政策");
                return ajaxJson;
            }
            int a = zong.compareTo(pxyouhuizhengcetable.getXianzhijine());
            if (a == 0 || a == 1) {
                if (pxyouhuizhengcetable.getYouhuiType() == 1) {
                    youhuiMoney =
                            zong.subtract(zong.multiply(pxyouhuizhengcetable.getYouhui()).divide(new BigDecimal(10)));
                } else {
                    youhuiMoney = pxyouhuizhengcetable.getYouhui();
                }
            } else {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("不能使用该优惠政策，请重新选择");
                return ajaxJson;
            }
        }
        //定金金额大于0
        if (form.getDingjin().compareTo(new BigDecimal(0)) == 1) {
            newstu.setRemainXuefei(form.getDingjin());
        } else {
            newstu.setRemainXuefei(form.getAmountKC());
        }
        newstu.setQiyeID(qiyeID);
        newstu.setDengjiTeacherID(staffID);
        newstu.setZidingyiStuID(form.getZidingyiStuNO());
        newstu.setStuSex(form.getStuSex() == "1" ? "男" : "女");
        if (form.getYxStuID() == 0 && form.getStuid() == 0) {
            iPxstutableService.save(newstu);   //保存学员
            logtext += "添加新签学员,新签学员：" + newstu.getStuName() + "。";
        } else {
            iPxstutableService.updateById(newstu);
            logtext += "意向新签学员,意向学员：" + newstu.getStuName() + ",变更为正式学员。";
        }

        List<stuparamtypeVO> zidingyiziduan = JSON.parseArray(form.getStuparams(), stuparamtypeVO.class);
        if (!zidingyiziduan.isEmpty()) {
            for (stuparamtypeVO item : zidingyiziduan) {
                if (StringUtils.isNotEmpty(item.getSavevalue())) {
                    Pxstuparamvaluetable pxstuparamvaluetable = new Pxstuparamvaluetable();
                    pxstuparamvaluetable.setStuID(newstu.getId());
                    pxstuparamvaluetable.setParamValue(item.getSavevalue());
                    pxstuparamvaluetable.setStuParamTypeID(item.getId());
                    pxstuparamvaluetable.setQiyeID(loginUser.getQiyeID());
                    iPxstuparamvaluetableService.save(pxstuparamvaluetable);
                }
            }

            logtext += "保存学员自定义字段。";
        }


        //app流程
        String isappPay = form.getIsappPay();

        if (isappPay.equals("是")) {
            logtext += "小程序支付,进入小程序支付流程。";
            //进小程序支付流程
            Qiandanapppay qdappPay = new Qiandanapppay();
            qdappPay.setStuID(newstu.getId())
                    .setQiandanDate(ft.parse(form.getQiandandate()))
                    .setZafeimoney(form.amountOther)
                    .setWupinmoney(form.amountWp)
                    .setHetongMoney(form.getAmountMoney().subtract(youhuiMoney).subtract(form.getDaijinquan()))
                    .setKechengmoney(form.getAmountKC())
                    .setDingjing(form.getDingjin())
                    .setMoneyStyle(1)
                    .setBeizhu(form.getBeizhu())
                    .setCampusID(form.getCampusID())
                    .setFromType(form.getTelFromID())
                    .setYouhuiID(form.getYouhuizhengce())
                    .setYouhuijine(youhuiMoney)
                    .setYouhuishuoming(youhuiMoney.compareTo(new BigDecimal(0)) == 0 ? "未优惠" :
                            pxyouhuizhengcetable.getYouhuiType() == 1 ?
                                    "金额达到或超过" + pxyouhuizhengcetable.getXianzhijine() + "元，" + pxyouhuizhengcetable.getYouhui() + "折优惠" : "金额达到或者超过" +
                                    pxyouhuizhengcetable.getXianzhijine() + "元，满减优惠" + pxyouhuizhengcetable.getYouhui() + "元")
                    .setDaijinquanmoney(form.getDaijinquan())
                    .setAddstaffID(loginUser.getStaffID())
                    .setQdtype(1)
                    .setBuxiStateID(form.getBuxiStateID())
                    .setPayState(1) //待支付
                    .setQiyeID(loginUser.getQiyeID())
                    .setJiazhangDemand(form.getJiazhangDemand());

            if (form.getDingjin().compareTo(new BigDecimal(0)) == 1) {
                qdappPay.setIsdingjing(3);
            } else {
                qdappPay.setIsdingjing(1);
            }

            if (form.getDingjin().compareTo(new BigDecimal(0)) == -1 || form.getDingjin().compareTo(new BigDecimal(0)) == 1) {
                qdappPay.setShishouTotalMoney(form.getDingjin());
            } else {
                qdappPay.setShishouTotalMoney(form.getAmountMoney().subtract(form.getDaijinquan()).subtract(youhuiMoney));
            }

            //zhuanIntroduce 转介绍ID,如果为0或为空，即不是转介绍,否则对应pxqiandanzhuanjieshaotable表的ID
            if (form.getZhuanIntroduce().equals("0")) {
                qdappPay.setZhuanjieshaoID(0L);
            } else {
                QiandanapppayZhuanjieshao qiandanapppayZhuanjieshao = new QiandanapppayZhuanjieshao();
                qiandanapppayZhuanjieshao.setQiandanapppayID(qdappPay.getId()).setQiyeID(loginUser.getQiyeID()).setBeizhu("")
                        .setZhuanjieshaoFromStaffID(Long.valueOf(form.getZhuanjieshaoTeacherID()))
                        .setZhuanjieshaoFromStuID(Long.valueOf(form.getZhuanjieshaoStuID()));
                qdappPay.setZhuanjieshaoID(Long.valueOf(form.getZhuanIntroduce()));
                iQiandanapppayZhuanjieshaoService.save(qiandanapppayZhuanjieshao);
            }
            iQiandanapppayService.save(qdappPay);
            logtext += "添加小程序待支付记录,";


            //签单业绩人
            List<Pxqiandanstafftable> qdstaff = JSON.parseArray(form.getQiandanstaffinfo(), Pxqiandanstafftable.class);
            if (qdstaff.size() > 0) {
                for (Pxqiandanstafftable pxqiandanstafftable : qdstaff) {
                    Qiandanapppayyejiren qdyejiren = new Qiandanapppayyejiren();
                    qdyejiren.setQiandanAppayID(qdappPay.getId())
                            .setQiandanstaffID(pxqiandanstafftable.getStaffID())
                            .setQiyeID(Long.valueOf(qiyeID))
                            .setYejidate(ft.parse(form.getQiandandate()))
                            .setYejiMoney(pxqiandanstafftable.getYejiMoney());
                    iQiandanapppayyejirenService.save(qdyejiren);
                }

                logtext += "添加小程序待支付签单人信息,";
            }


            //杂费
            List<buyZafeiVo> othermoneyVoList = JSON.parseArray(form.getOthermoneydata(), buyZafeiVo.class);
            if (othermoneyVoList.size() > 0) {
                for (buyZafeiVo othermoneyVo : othermoneyVoList) {
                    Qiandanapppayzafei qdappzf = new Qiandanapppayzafei()
                            .setNums(new BigDecimal(1))
                            .setOnePrice(othermoneyVo.getZafeiZongjia())
                            .setQiyeID(Long.valueOf(qiyeID))
                            .setQianDanOtherMoneyID(othermoneyVo.getZafeiID())
                            .setQiandanAppayID(qdappPay.getId())
                            .setZongMoney(othermoneyVo.getZafeiZongjia());
                    iQiandanapppayzafeiService.save(qdappzf);
                }
                logtext += "添加小程序待支付杂费信息,";
            }


            //课程
            List<buyKechengVo> buykechenglist = JSON.parseArray(form.getKcData(), buyKechengVo.class);
            if (buykechenglist.size() == 0) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("操作失败！您还未添加课程！");
                return ajaxJson;
            } else {
                //region 保存课程信息
                for (buyKechengVo buyKechengVo : buykechenglist) {
                    Qiandanapppaysubject qdappsubject = new Qiandanapppaysubject()
                            .setStuID(newstu.getId())
                            .setDiscount(buyKechengVo.getZK())
                            .setBuykeshiNum(buyKechengVo.getKS())
                            .setZengsongkeshi(buyKechengVo.getZKS())
                            .setKechengID(buyKechengVo.getKechengID())
                            .setKechengprice(buyKechengVo.getDJ())
                            .setEndDate(ft.parse(buyKechengVo.getEndDate()))
                            .setStartDate(ft.parse(buyKechengVo.getStartDate()))
                            .setOriginalprice(buyKechengVo.getYDJ())
                            .setQiandanAppayID(qdappPay.getId())
                            .setQiyeID(Long.valueOf(qiyeID))
                            .setQiandandate(ft.parse(form.getQiandandate()))
                            .setKechengStyle(1)
                            .setClassID(buyKechengVo.getClassID())
                            .setPkid(buyKechengVo.getPkid())
                            .setCharukebiao(buyKechengVo.getCkb())
                            .setZongjia(buyKechengVo.getZJ());
                    iQiandanapppaysubjectService.save(qdappsubject);
                }
                logtext += "添加小程序待支付课程信息,";
            }


            //endregion

            //物品
            List<buyWpVo> wpbuyList = JSON.parseArray(form.getWpData(), buyWpVo.class);
            if (wpbuyList.size() > 0) {
                for (buyWpVo buyWpVo : wpbuyList) {
                    Qiandanapppaysupplies qdsupplies = new Qiandanapppaysupplies()
                            .setBuyPrice(buyWpVo.getWpDanjia())
                            .setBuySum(buyWpVo.getWpShuliang())
                            .setFafangstate(1)
                            .setQiyeID(Long.valueOf(qiyeID))
                            .setName(buyWpVo.getWpName())
                            .setQiandanAppayID(qdappPay.getId())
                            .setTeachingSuppliesID(buyWpVo.getId())
                            .setSumMoney(buyWpVo.getWpZongjia());
                    iQiandanapppaysuppliesService.save(qdsupplies);
                }
                logtext += "添加小程序待支付签单商品信息,";
            }


            //paymoney
            List<Pxqiandanpaymoney> pxqiandanpaymoneyList = JSON.parseArray(form.getPaytyles(),
                    Pxqiandanpaymoney.class);
            if (pxqiandanpaymoneyList.size() > 0) {
                for (Pxqiandanpaymoney pxqiandanpaymoney : pxqiandanpaymoneyList) {
                    Qiandanapppaymoney qdapppay = new Qiandanapppaymoney()
                            .setPaymoney(pxqiandanpaymoney.getPayMoney())
                            .setQiandanAppayID(qdappPay.getId())
                            .setQiyeID(Long.valueOf(qiyeID))
                            .setPaymoneystyleID(pxqiandanpaymoney.getPaymoneyStyleID());
                    iQiandanapppaymoneyService.save(qdapppay);
                }
                logtext += "添加小程序待支付付款信息。";
            }


            savePxLog.savepxlog(logtext, "xwcloud-zsbm/zsbm/BaoMingJiaoFei/AddNewStuAndQianDan",
                    loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());

            ajaxJson.setMsg("签单保存成功，请通知家长小程序支付！！！");
            return ajaxJson;
        }

        //审批
        QueryWrapper queryWrappersp = new QueryWrapper();
        queryWrappersp.eq("sysparamTypeID", 107);
        queryWrappersp.eq("qiyeID", loginUser.getQiyeID());
        Pxsysparamvaluetable pxsysparamvaluetable = iPxsysparamvaluetableService.getOne(queryWrappersp);
        Boolean yyj = pxsysparamvaluetable.getModifyValue().equals("1");
        Boolean tt = pxsysparamvaluetable.getModifyValue() == "1";
        if (pxsysparamvaluetable.getModifyValue().equals("1")) {
            logtext += "系统开启签单审批，进去审批流程。";
            newstu.setBuxiStateID(7);    //补习状态,1意向，2在读，3停课，4结课，5退费，6休眠，7新签待审批
            iPxstutableService.updateById(newstu);
            Qiandanshenpi qiandanshenpi = new Qiandanshenpi();
            qiandanshenpi.setQiyeID(Long.valueOf(loginUser.getQiyeID()))
                    .setDaijinquanmoney(form.getDaijinquan())
                    .setBeizhu(form.getBeizhu())
                    .setCampusID(form.getCampusID())
                    .setFromType(form.getTelFromID())
                    .setHetongMoney(form.getAmountMoney().subtract(youhuiMoney).subtract(form.getDaijinquan()))
                    .setKechengmoney(form.getAmountKC())
                    .setStuID(newstu.getId())
                    .setMoneyStyle(1)
                    .setZafeimoney(form.amountOther)
                    .setWupinmoney(form.amountWp)
                    .setYouhuiID(form.getYouhuizhengce())
                    .setYouhuijine(youhuiMoney)
                    .setBeizhu(form.getBeizhu())
                    .setAddstaffID(loginUser.getStaffID())
                    .setIsXinqianOrXufei(1)    //1新签；2续费
                    .setBuxiStateID(form.getBuxiStateID())
                    .setJiazhangDemand(form.getJiazhangDemand())
                    .setYouhuishuoming(youhuiMoney.compareTo(new BigDecimal(0)) == 0 ? "未优惠" :
                            pxyouhuizhengcetable.getYouhuiType() == 1 ?
                                    "金额达到或超过" + pxyouhuizhengcetable.getXianzhijine() + "元，" + pxyouhuizhengcetable.getYouhui() + "折优惠" : "金额达到或者超过" +
                                    pxyouhuizhengcetable.getXianzhijine() + "元，满减优惠" + pxyouhuizhengcetable.getYouhui() + "元");
            if (form.getYxStuID() != 0) {
                qiandanshenpi.setQiandanType(1);   //1意向学员签单，2直接后台录入签单，3微信端支付自动录单
            } else {
                qiandanshenpi.setQiandanType(2);
            }
            qiandanshenpi.setQiandandate(ft.parse(form.getQiandandate()));
            if (form.getDingjin().compareTo(new BigDecimal(0)) == -1 || form.getDingjin().compareTo(new BigDecimal(0)) == 1) {
                qiandanshenpi.setShishouTotalMoney(form.getDingjin());
            } else {
                qiandanshenpi.setShishouTotalMoney(form.getAmountMoney().subtract(form.getDaijinquan()).subtract(youhuiMoney));
            }
            if (form.getDingjin().compareTo(new BigDecimal(0)) == 1) {
                qiandanshenpi.setIsdingjing(3);
            } else {
                qiandanshenpi.setIsdingjing(1);
            }
            qiandanshenpi.setDingjing(form.getDingjin());
            qiandanshenpi.setShenpiState(0);
            qiandanshenpi.setQiyeID(Long.valueOf(qiyeID));
            iQiandanshenpiService.save(qiandanshenpi);
            logtext += "添加新签审批记录完成，审批ID：" + qiandanshenpi.getId();

            if (form.getZhuanIntroduce().equals("0")) {
                qiandanshenpi.setZhuanjieshaoID(0L);
                iQiandanshenpiService.updateById(qiandanshenpi);
            } else {
                QiandanshenpiZhuanjieshao qiandanshenpiZhuanjieshao = new QiandanshenpiZhuanjieshao();
                qiandanshenpiZhuanjieshao.setQiandanshenpiID(qiandanshenpi.getId())
                        .setBeizhu("")
                        .setQiyeID(loginUser.getQiyeID())
                        .setZhuanjieshaoFromStaffID(Long.valueOf(form.getZhuanjieshaoTeacherID()))
                        .setZhuanjieshaoFromStuID(Long.valueOf(form.getZhuanjieshaoStuID()));


                qiandanshenpi.setZhuanjieshaoID(Long.valueOf(form.getZhuanIntroduce()));
                iQiandanshenpiZhuanjieshaoService.save(qiandanshenpiZhuanjieshao);
                logtext += ",添加请新签审批转介绍记录完成，审批转介绍记录ID：" + qiandanshenpiZhuanjieshao.getId();
            }


            List<Pxqiandanstafftable> qdstaff = JSON.parseArray(form.getQiandanstaffinfo(), Pxqiandanstafftable.class);
            for (Pxqiandanstafftable pxqiandanstafftable : qdstaff) {
                Qiandanshenpiyejiren qiandanshenpiyejiren = new Qiandanshenpiyejiren();
                qiandanshenpiyejiren.setQiandanshenpiID(qiandanshenpi.getId())
                        .setQiandanstaffID(pxqiandanstafftable.getStaffID())
                        .setQiyeID(Long.valueOf(qiyeID))
                        .setYejidate(ft.parse(form.getQiandandate()))
                        .setYejiMoney(pxqiandanstafftable.getYejiMoney());
                iQiandanshenpiyejirenService.save(qiandanshenpiyejiren);

                logtext += ",添加请新签审批业绩人记录完成，审批业绩人记录ID：" + qiandanshenpiyejiren.getId();
            }
            List<buyZafeiVo> othermoneyVoList = JSON.parseArray(form.getOthermoneydata(), buyZafeiVo.class);
            for (buyZafeiVo othermoneyVo : othermoneyVoList) {
                Qiandanshenpizafei qiandanshenpizafei = new Qiandanshenpizafei().setNums(new BigDecimal(1))
                        .setOnePrice(othermoneyVo.getZafeiZongjia())
                        .setQiyeID(Long.valueOf(qiyeID))
                        .setQianDanOtherMoneyID(othermoneyVo.getZafeiID())
                        .setQiandanshenpiID(qiandanshenpi.getId())
                        .setZongMoney(othermoneyVo.getZafeiZongjia());
                iQiandanshenpizafeiService.save(qiandanshenpizafei);
                logtext += ",添加请新签审批杂费记录完成，审批杂费记录ID：" + qiandanshenpizafei.getId();

            }
            List<buyKechengVo> buykechenglist = JSON.parseArray(form.getKcData(), buyKechengVo.class);
            if (buykechenglist == null) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("操作失败！您还未添加课程！");
                return ajaxJson;
            }
            //region 保存课程信息
            for (buyKechengVo buyKechengVo : buykechenglist) {
                Qiandanshenpisubject qiandanshenpisubject = new Qiandanshenpisubject()
                        .setStuID(newstu.getId())
                        .setDiscount(buyKechengVo.getZK())
                        .setBuykeshiNum(buyKechengVo.getKS())
                        .setKechengID(buyKechengVo.getKechengID())
                        .setKechengprice(buyKechengVo.getDJ())
                        .setEndDate(ft.parse(buyKechengVo.getEndDate()))
                        .setStartDate(ft.parse(buyKechengVo.getStartDate()))
                        .setOriginalprice(buyKechengVo.getYDJ())
                        .setQiandanshenpiID(qiandanshenpi.getId())
                        .setQiyeID(Long.valueOf(qiyeID))
                        .setQiandandate(ft.parse(form.getQiandandate()))
                        .setKechengStyle(1)
                        .setZengsongkeshi(buyKechengVo.getZKS())
                        .setClassID(buyKechengVo.getClassID())
                        .setPkid(buyKechengVo.getPkid())
                        .setCharukebiao(buyKechengVo.getCkb())
                        .setZongjia(buyKechengVo.getZJ());
                iQiandanshenpisubjectService.save(qiandanshenpisubject);
                logtext += ",添加请新签审批课程记录完成，审批科目课程记录ID：" + qiandanshenpisubject.getId();
            }
            //endregion
            List<buyWpVo> wpbuyList = JSON.parseArray(form.getWpData(), buyWpVo.class);
            for (buyWpVo buyWpVo : wpbuyList) {
                Qiandanshenpisupplies qiandanshenpisupplies = new Qiandanshenpisupplies()
                        .setBuyPrice(buyWpVo.getWpDanjia())
                        .setBuySum(buyWpVo.getWpShuliang())
                        .setFafangstate(1)
                        .setQiyeID(Long.valueOf(qiyeID))
                        .setName(buyWpVo.getWpName())
                        .setQiandanshenpiID(qiandanshenpi.getId())
                        .setTeachingSuppliesID(buyWpVo.getId())
                        .setSumMoney(buyWpVo.getWpZongjia());
                iQiandanshenpisuppliesService.save(qiandanshenpisupplies);
                logtext += ",添加请新签审批商品记录完成，审批商品记录ID：" + qiandanshenpisupplies.getId();

            }

            List<Pxqiandanpaymoney> pxqiandanpaymoneyList = JSON.parseArray(form.getPaytyles(),
                    Pxqiandanpaymoney.class);
            for (Pxqiandanpaymoney pxqiandanpaymoney : pxqiandanpaymoneyList) {
                Qiandanshenpipaymoney qiandanshenpipaymoney = new Qiandanshenpipaymoney()
                        .setPaymoney(pxqiandanpaymoney.getPayMoney())
                        .setQiandanshenpiID(qiandanshenpi.getId())
                        .setQiyeID(Long.valueOf(qiyeID))
                        .setPaymoneystyleID(pxqiandanpaymoney.getPaymoneyStyleID());
                iQiandanshenpipaymoneyService.save(qiandanshenpipaymoney);
                logtext += ",添加请新签审批支付记录完成，审批支付记录ID：" + qiandanshenpipaymoney.getId();

            }
            ajaxJson.setSuccess(true);
            return ajaxJson;
        } else {
            logtext += "未开启签单审批流程，进入新签流程。时间：" + new Date();
            Pxqiandaninfotable qiandan = new Pxqiandaninfotable();
            qiandan.setStuID(newstu.getId());
            try {
                qiandan.setQiandandate(ft.parse(form.getQiandandate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            qiandan.setZhuanjieshaoID(form.getZhuanIntroduce().equals("0") ? 0 :
                    Long.valueOf(form.getZhuanIntroduce()));
            qiandan.setMoneyStyle(1);
            qiandan.setPayMoneyStyle(form.getPayMoneyStyle());
            qiandan.setBeizhu(form.getBeizhu());
            qiandan.setQianDanStaffID(form.getQianDanStaffID());
            qiandan.setRecordInStaffID(form.getQianDanStaffID());
            qiandan.setRecordInTime(new Date());
            qiandan.setCampusID(form.getCampusID());
            qiandan.setFromType(form.getTelFromID());
            qiandan.setYouhuiID(form.getYouhuizhengce());
            qiandan.setYouhuijine(youhuiMoney.toString());
            qiandan.setYouhuishuoming(youhuiMoney.compareTo(new BigDecimal(0)) == 0 ? "未优惠" :
                    pxyouhuizhengcetable.getYouhuiType() == 1 ?
                            "金额达到或超过" + pxyouhuizhengcetable.getXianzhijine() + "元，" + pxyouhuizhengcetable.getYouhui() + "折优惠" :
                            "金额达到或者超过" + pxyouhuizhengcetable.getXianzhijine() + "元，满减优惠" + pxyouhuizhengcetable.getYouhui() + "元");
            qiandan.setHetongMoney(form.getAmountMoney().subtract(youhuiMoney).subtract(form.getDaijinquan()));
            if (form.getDingjin().compareTo(new BigDecimal(0)) == -1 || form.getDingjin().compareTo(new BigDecimal(0)) == 1) {
                qiandan.setShishouTotalMoney(form.getDingjin());
            } else {
                qiandan.setShishouTotalMoney(form.getAmountMoney().subtract(form.getDaijinquan()).subtract(youhuiMoney));
            }
            if (form.getDingjin().compareTo(new BigDecimal(0)) == 1) {
                qiandan.setIsdingjing(3);
            } else {
                qiandan.setIsdingjing(1);
            }
            qiandan.setQiyeID(qiyeID);
            qiandan.setQianDanStaffID(0L);
            qiandan.setRecordInStaffID(staffID);
            qiandan.setPayMoneyStyle(0L);
            qiandan.setBeizhu(form.getBeizhu());
            qiandan.setJiazhangDemand(form.getJiazhangDemand());
            iPxqiandaninfotableService.save(qiandan);

            long qiandanID = qiandan.getId();
            logtext += ",签单ID：" + qiandanID;

            //Pxstafftable jbstaff = iPxstafftableService.getById(form.getQianDanStaffID());
            logtext += "，经办人" + loginUser.getStaffName();

            if (form.getZhuanIntroduce().equals("0")) {

            } else {
                Pxqiandanzhuanjieshaotable pxqiandanzhuanjieshaotable = new Pxqiandanzhuanjieshaotable();
                pxqiandanzhuanjieshaotable.setBeizhu("")
                        .setQiandanID(qiandan.getId())
                        .setQiyeID(loginUser.getQiyeID())
                        .setZhuanjieshaoFromStaffID(Long.valueOf(form.getZhuanjieshaoTeacherID()))
                        .setZhuanjieshaoFromStuID(Long.valueOf(form.getZhuanjieshaoStuID()));
                iPxqiandanzhuanjieshaotableService.save(pxqiandanzhuanjieshaotable);
            }
            List<Pxqiandanstafftable> qdstaff = JSON.parseArray(form.getQiandanstaffinfo(), Pxqiandanstafftable.class);
            for (Pxqiandanstafftable pxqiandanstafftable : qdstaff) {
                Pxqiandanstafftable newqiandanstaff = new Pxqiandanstafftable();
                newqiandanstaff.setIsWeikuan(0);
                newqiandanstaff.setQiandanID(qiandan.getId());
                newqiandanstaff.setQiyeID(qiyeID);
                newqiandanstaff.setStaffID(pxqiandanstafftable.getStaffID());
                newqiandanstaff.setYejidateTime(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                newqiandanstaff.setYejiMoney(pxqiandanstafftable.getYejiMoney());
                iPxqiandanstafftableService.save(newqiandanstaff);

                if (form.getYxStuID() != 0L) {
                    //删除意向学员信息
                    Pxstutable yxstu = iPxstutableService.getById(form.getYxStuID());
                    if (yxstu != null) {
                        //向意向签单表插入一条记录
                        Pxyxqiandantable yxqiandan = new Pxyxqiandantable();
                        yxqiandan.setStuID(form.getYxStuID());
                        yxqiandan.setQianDanMoney(pxqiandanstafftable.getYejiMoney().toString());
                        yxqiandan.setIsBaomingOrChongzhi(1);   //签单方式：1报名，2充值
                        yxqiandan.setYxShichangRenID(yxstu.getYxshichangTeacherID());
                        yxqiandan.setYxDengjiRenID(yxstu.getDengjiTeacherID());
                        yxqiandan.setYxFenpeiDateTime(yxstu.getDengjiTime());
                        yxqiandan.setYxQiandanRenID(pxqiandanstafftable.getStaffID());
                        yxqiandan.setYxQiandanDateTime(qiandan.getQiandandate());
                        yxqiandan.setQiyeID(loginUser.getQiyeID());
                        iPxyxqiandantableService.save(yxqiandan);
                    }
                }
            }
            List<buyZafeiVo> othermoneyVoList = JSON.parseArray(form.getOthermoneydata(), buyZafeiVo.class);
            for (buyZafeiVo othermoneyVo : othermoneyVoList) {
                Pxqiandaninfo2table qd2Tab = new Pxqiandaninfo2table();
                qd2Tab.setQianInfoTabID(qiandan.getId());
                qd2Tab.setQianDanOtherMoneyID(othermoneyVo.getZafeiID());
                qd2Tab.setZongMoney(othermoneyVo.getZafeiZongjia());
                qd2Tab.setJiaoxueYonpingID(0L);
                qd2Tab.setNums(new BigDecimal(1));
                qd2Tab.setOnePrice(othermoneyVo.getZafeiZongjia());
                qd2Tab.setType(1);
                qd2Tab.setTuiMoney(new BigDecimal(0));
                qd2Tab.setQiyeID(qiyeID);
                iPxqiandaninfo2tableService.save(qd2Tab);
            }
            List<buyKechengVo> buykechenglist = JSON.parseArray(form.getKcData(), buyKechengVo.class);
            if (buykechenglist == null) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                ajaxJson.setCode("N");
                ajaxJson.setMsg("操作失败！您还未添加课程！");
                return ajaxJson;
            }
            //region 保存课程信息
            for (buyKechengVo buyKechengVo : buykechenglist) {
                //region 新增课程
                Long classID = 0L;
                if (buyKechengVo.getPxStyleName() == "一对一") {
                    boolean ii = false;
                    while (ii == false) {
                        Random random = new Random();
                        int max = 999;
                        int min = 100;
                        int sjs = random.nextInt(max) % (max - min + 1) + min;

                        String className = newstu.getStuName() + "_" + buyKechengVo.getKmName() + "_一对一" + sjs;
                        List<Pxclasstable> pxclasstableList = iPxclasstableService.GetClassByClassName(className);
                        if (pxclasstableList.size() == 0) {
                            Pxclasstable classtable = new Pxclasstable();
                            classtable.setClassName(className);
                            classtable.setCampusID(newstu.getCampusID());
                            classtable.setAddStaffID(form.getQianDanStaffID());
                            classtable.setAddTime(new Date());
                            classtable.setIsShow(1);
                            classtable.setIs1v1Class(1);
                            classtable.setQiyeID(qiyeID);
                            classtable.setIsdelete(false);
                            iPxclasstableService.save(classtable);
                            classID = classtable.getId();
                            ii = true;
                        }
                    }
                } else {
                    if (buyKechengVo.getClassID() != null) {
                        classID = buyKechengVo.getClassID();
                    }
                }

                //添加补习课程记录
                Pxbuxikechengtable buxi = new Pxbuxikechengtable();
                buxi.setStuID(newstu.getId());
                buxi.setKechengID(buyKechengVo.getKCID());
                if (buyKechengVo.getJifeistyle() == 3) {
                    buxi.setKechengprice(new BigDecimal(0));
                    buxi.setOriginalprice(new BigDecimal(0));
                    buxi.setKeshiNum(new BigDecimal(0));
                    buxi.setRemainkeshi(new BigDecimal(0));
                } else {

                    buxi.setKechengprice(buyKechengVo.getDJ());
                    buxi.setOriginalprice(buyKechengVo.getYDJ());
                    buxi.setKeshiNum(buyKechengVo.getKS());
                    buxi.setRemainkeshi(buyKechengVo.getKS());
                }
                try {
                    buxi.setBuykeshiDateTime(ft.parse(form.getQiandandate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                buxi.setIsShow(buyKechengVo.getIsShow());
                buxi.setZongjia(buyKechengVo.getZJ());
                buxi.setStartDate(ft.parse(buyKechengVo.getStartDate()));
                buxi.setEndDate(ft.parse(buyKechengVo.getEndDate()));
                buxi.setJifeiStyleID(buyKechengVo.getJifeistyle());
                buxi.setType(1);


                if (buyKechengVo.getZKS().compareTo(new BigDecimal(0)) == 1) {
                    //添加赠送记录
                    Pxkeshizengsongtable keshizengsong = new Pxkeshizengsongtable();
                    keshizengsong.setKechengID(buyKechengVo.getKechengID());
                    keshizengsong.setKechengPrice(buyKechengVo.getDJ());
                    keshizengsong.setKeshiShu(buyKechengVo.getZKS());
                    keshizengsong.setSongYangyin("签单课时赠送");
                    keshizengsong.setJifeiStyle(buyKechengVo.getJifeistyle());
                    keshizengsong.setStuID(newstu.getId());
                    keshizengsong.setAddDate(new Date());
                    keshizengsong.setCaozuoStaffId(staffID);
                    keshizengsong.setQiandanInfoID(qiandan.getId());
                    keshizengsong.setQiyeID(qiyeID);
                    iPxkeshizengsongtableService.save(keshizengsong);

                    QueryWrapper queryWrapper2 = new QueryWrapper();
                    queryWrapper2.eq("stuID", newstu.getId());
                    queryWrapper2.eq("kechengID", buyKechengVo.getKCID());
                    queryWrapper2.eq("kechengprice", buyKechengVo.getDJ());
                    queryWrapper2.eq("type", 2);

                    Pxbuxikechengtable pdbuxikc = iPxbuxikechengtableService.GetZidingYiKecheng(queryWrapper2);
                    if (pdbuxikc != null) {
                        pdbuxikc.setRemainkeshi(pdbuxikc.getRemainkeshi().add(buyKechengVo.getZKS()));
                        pdbuxikc.setKeshiNum(pdbuxikc.getKeshiNum().add(buyKechengVo.getZKS()));
                    } else {
                        //添加赠送补习课程记录
                        Pxbuxikechengtable zsbxTab = new Pxbuxikechengtable();
                        zsbxTab.setStuID(newstu.getId());
                        zsbxTab.setKechengID(buyKechengVo.getKechengID());
                        zsbxTab.setKechengprice(buyKechengVo.getDJ());
                        zsbxTab.setOriginalprice(buyKechengVo.getYDJ());
                        zsbxTab.setRemainkeshi(buyKechengVo.getZKS());
                        zsbxTab.setKeshiNum(buyKechengVo.getZKS());
                        zsbxTab.setZongjia(new BigDecimal(0));
                        zsbxTab.setStartDate(ft.parse(buyKechengVo.getStartDate()));
                        zsbxTab.setEndDate(ft.parse(buyKechengVo.getEndDate()));
                        zsbxTab.setBuykeshiDateTime(new Date());
                        zsbxTab.setIsShow(0);
                        zsbxTab.setJifeiStyleID(buyKechengVo.getJifeistyle());
                        zsbxTab.setType(2);    //赠送的
                        zsbxTab.setQiyeID(qiyeID);
                        iPxbuxikechengtableService.save(zsbxTab);

                        if (buyKechengVo.getJifeistyle() == 1) {
                            Long addclassID = 0L;
                            Pxkechengtable onekcTab =
                                    iPxkechengtableService.GetKechengById(buyKechengVo.getKechengID());
                            String subjectName =
                                    iPxsubjecttableService.GetSubjectById(onekcTab.getSubjectID()).getSubjectName();
                            //建班插班
                            boolean ii = false;
                            while (ii == false) {
                                Random rd = new Random();
                                int max = 999;
                                int min = 100;
                                int sjs = rd.nextInt(max) % (max - min + 1) + min;
                                String className = newstu.getStuName() + "_" + subjectName + "_一对一" + sjs;
                                List<Pxclasstable> pdclass = iPxclasstableService.GetClassByClassName(className);
                                if (pdclass.size() == 0) {
                                    Pxclasstable classtable = new Pxclasstable();
                                    classtable.setClassName(className);
                                    classtable.setCampusID(newstu.getCampusID());
                                    classtable.setAddStaffID(loginUser.getStaffID());
                                    classtable.setAddTime(new Date());
                                    classtable.setIsShow(1);
                                    classtable.setIs1v1Class(1);
                                    classtable.setQiyeID(qiyeID);
                                    classtable.setClassState(0);
                                    classtable.setIsdelete(false);
                                    iPxclasstableService.save(classtable);
                                    addclassID = classtable.getId();
                                    ii = true;
                                }
                            }

                            //如果是一对一，要插班
                            Pxstuclasstable stucla = new Pxstuclasstable();
                            stucla.setBuxiID(zsbxTab.getId());
                            stucla.setClassID(addclassID);
                            stucla.setQiyeID(qiyeID);
                            iPxstuclasstableService.save(stucla);
                        }
                    }
                }


                Pxqiandansubjecttable qdsub = new Pxqiandansubjecttable();
                qdsub.setStuID(newstu.getId());
                try {
                    qdsub.setQiandandate(ft.parse(form.getQiandandate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                qdsub.setKechengID(buyKechengVo.getKechengID());
                qdsub.setKechengprice(buyKechengVo.getDJ());
                qdsub.setOriginalprice(buyKechengVo.getYDJ());
                qdsub.setBuykeshiNum(buyKechengVo.getKS());
                qdsub.setZongjia(buyKechengVo.getZJ());   ///增加了总价的存储
                qdsub.setQianDanInfoID(qiandan.getId());
                qdsub.setKechengStyle(1);   //1买的 2 接受的赠送 3 送出的 4 退费
                qdsub.setDiscount(buyKechengVo.getZK());
                qdsub.setStartDate(ft.parse(buyKechengVo.getStartDate()));
                qdsub.setEndDate(ft.parse(buyKechengVo.getEndDate()));
                qdsub.setQiyeID(qiyeID);
                qdsub.setKechengID(buyKechengVo.getKechengID());
                qdsub.setDiscount(buyKechengVo.getZK());
                iPxqiandansubjecttableService.save(qdsub);
                buxi.setQianDanInfoID(qiandan.getId());
                buxi.setQianDanSubjectID(qdsub.getId());
                buxi.setQiyeID(qiyeID);
                buxi.setKechengID(buyKechengVo.getKechengID());
                iPxbuxikechengtableService.save(buxi);

                //插班
                if (classID != 0L) {
                    boolean ishave = false;
                    List<Pxbuxikechengtable> abuxiTab =
                            iPxbuxikechengtableService.GetBuxikchengByStuID(newstu.getId(), qiyeID);
                    for (Pxbuxikechengtable pxbuxikechengtable : abuxiTab) {
                        Pxstuclasstable psstuclsTab = iPxstuclasstableService.GetStuClassByBxIDAndClassID(classID,
                                pxbuxikechengtable.getId());
                        if (psstuclsTab != null) {
                            ishave = true;
                        }
                    }
                    if (ishave == false) {
                        Pxstuclasstable stuclass = new Pxstuclasstable();
                        stuclass.setBuxiID(buxi.getId());
                        stuclass.setClassID(classID);
                        stuclass.setQiyeID(qiyeID);
                        iPxstuclasstableService.save(stuclass);
                        if (buyKechengVo.getPkid() != null) {
                            //如果新加入的班级原有排课了、考勤状态是未完成状态，再把学生加在选课表里面去

                            Pxpaiketable checkPK = iPxpaiketableService.getById(buyKechengVo.getPkid()); //选中的排课
                            List<Pxpaiketable> paikenews = iPxpaiketableService.list(new QueryWrapper<Pxpaiketable>()
                                    .eq("classID", checkPK.getClassID())
                                    .ge("haveClassDate", checkPK.getHaveClassDate())
                                    .ge("startLessonDateTime", checkPK.getStartLessonDateTime())
                                    .eq("qiyeID", loginUser.getQiyeID())
                                    .orderByAsc("haveClassDate")
                            );

                            for (Pxpaiketable pxpaiketable : paikenews) {
                                List<Pxxuanketable> pdxuankeTab =
                                        iPxxuanketableService.GetXuankeByBuxiIDAndPaikeId(buxi.getStuID(),
                                                pxpaiketable.getId());
                                if (pdxuankeTab.size() == 0) {
                                    Pxxuanketable xuanke = new Pxxuanketable();
                                    xuanke.setBuxiID(buxi.getId());
                                    xuanke.setPaikeID(pxpaiketable.getId());
                                    xuanke.setStuID(buxi.getStuID());
                                    xuanke.setRecordDate(new Date());
                                    xuanke.setType(1);
                                    xuanke.setQiyeID(qiyeID);
                                    iPxxuanketableService.save(xuanke);
                                }
                            }
                        }
                    }
                }
            }
            //endregion
            List<buyWpVo> wpbuyList = JSON.parseArray(form.getWpData(), buyWpVo.class);
            for (buyWpVo buyWpVo : wpbuyList) {
                Pxteachingsuppliestable TeachingSupplies =
                        iPxteachingsuppliestableService.GetTeachingSuppliesByName(buyWpVo.getWpName());
                if (TeachingSupplies.getStockNum().compareTo(buyWpVo.getWpShuliang()) == -1) {
                    ajaxJson.setCode("N");
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    ajaxJson.setMsg("当前所购买的教学物品" + buyWpVo.getWpName() + "数量不足！");
                    return ajaxJson;
                }
                Pxqiandansupplies qdsp = new Pxqiandansupplies();
                qdsp.setStuID(newstu.getId());
                qdsp.setTeachingSuppliesID(TeachingSupplies.getId());
                qdsp.setName(buyWpVo.getWpName());
                qdsp.setQiandaninfoID(qiandan.getId());
                qdsp.setBuyPrice(buyWpVo.getWpDanjia());
                qdsp.setBuySum(buyWpVo.getWpShuliang());
                qdsp.setSumMoney(buyWpVo.getWpZongjia());
                qdsp.setIsTuiFei(false);
                qdsp.setTuiMoney(new BigDecimal(0));
                qdsp.setTuiMoney(new BigDecimal(0));
                qdsp.setQiyeID(qiyeID);
                iPxqiandansuppliesService.save(qdsp);
                Pxteachingsuppliesouttable outjl = new Pxteachingsuppliesouttable();
                outjl.setLuruStaffId(form.getQianDanStaffID());
                outjl.setOutDate(new Date());
                outjl.setOutNum(buyWpVo.getWpShuliang());
                outjl.setOutnumBefore(TeachingSupplies.getStockNum());
                outjl.setOutReason("学生购买");
                outjl.setOutStaffId(form.getQianDanStaffID());
                outjl.setSuppliesId(TeachingSupplies.getId());
                outjl.setType(2);
                outjl.setQiyeID(qiyeID);
                outjl.setLuruStaffId(staffID);
                iPxteachingsuppliesouttableService.save(outjl);
                BigDecimal kucun = TeachingSupplies.getStockNum().subtract(buyWpVo.getWpShuliang());
                iPxteachingsuppliestableService.UpdateteachingsuppliesKucun(TeachingSupplies.getId(), kucun);
            }
            if (form.getDaijinquan().compareTo(new BigDecimal(0)) == 1) {//代金券金额大于0
                Pxdaijinquantable pxdaijinquantable = new Pxdaijinquantable();
                pxdaijinquantable.setStuID(newstu.getId());
                pxdaijinquantable.setQiandanID(qiandan.getId());
                pxdaijinquantable.setMoney(form.getDaijinquan());
                pxdaijinquantable.setCreatTime(new Date());
                pxdaijinquantable.setQiyeID(qiyeID);
                pxdaijinquantable.setStaffID(form.getQianDanStaffID());
                pxdaijinquantable.setStaffID(staffID);
                iPxdaijinquantableService.save(pxdaijinquantable);
            }

            List<Pxqiandanpaymoney> pxqiandanpaymoneyList = JSON.parseArray(form.getPaytyles(),
                    Pxqiandanpaymoney.class);
            for (Pxqiandanpaymoney pxqiandanpaymoney : pxqiandanpaymoneyList) {
                if (pxqiandanpaymoney.getPayMoney().compareTo(new BigDecimal(0)) == 1) {
                    Pxqiandanpaymoney ishavepay =
                            iPxqiandanpaymoneyService.GetQiandanPayMoneyStyleByqdID(qiandan.getId(),
                                    pxqiandanpaymoney.getPaymoneyStyleID());
                    if (ishavepay == null) {
                        Pxqiandanpaymoney qdpm = new Pxqiandanpaymoney();
                        qdpm.setPayMoney(pxqiandanpaymoney.getPayMoney());
                        qdpm.setPaymoneyStyleID(pxqiandanpaymoney.getPaymoneyStyleID());
                        qdpm.setQiandanID(qiandan.getId());
                        qdpm.setIsWeikuan(0);
                        try {
                            qdpm.setQianDanDate(ft.parse(form.getQiandandate()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        qdpm.setQiyeID(qiyeID);
                        iPxqiandanpaymoneyService.save(qdpm);
                    } else {

                    }
                    if (pxqiandanpaymoney.getPaymoneyStyleID() != -2) {
                        Pxliushuizhangtable liushui = new Pxliushuizhangtable();
                        Random random = new Random();
                        liushui.setLiushuiDateTime(qiandan.getQiandandate());
                        liushui.setCampusID(form.getCampusID());
                        liushui.setPayMoneyStyle(pxqiandanpaymoney.getPaymoneyStyleID());
                        liushui.setZhichuMoney(new BigDecimal(0));
                        liushui.setShouzhiStyleID(1L);
                        liushui.setJinbanRen(form.getQianDanStaffID());
                        liushui.setStuID(newstu.getId());
                        liushui.setQiandanID(qiandan.getId());
                        liushui.setLuruTime(new Date());
                        liushui.setQiyeID(qiyeID);
                        liushui.setJinbanRen(staffID);
                        String zyText = "";
                        if (StringUtils.isNotBlank(form.getBeizhu())) {
                            zyText = "说明：" + form.getBeizhu();
                        }
                        if (form.getDingjin().compareTo(new BigDecimal(0)) == 1) {
                            liushui.setShouruMoney(form.getDingjin());
                            liushui.setLiushuiZaiyao(newstu.getStuName() + "同学：定金交费！--" + zyText);
                            logtext += ",定金缴费：" + form.getDingjin().toString();
                        } else {
                            liushui.setShouruMoney(pxqiandanpaymoney.getPayMoney());
                            liushui.setLiushuiZaiyao(newstu.getStuName() + "同学，全款交费！--" + zyText);
                            logtext += ",全款缴费：" + pxqiandanpaymoney.getPayMoney().toString();
                        }
                        liushui.setAddStaffID(staffID);

                        iPxliushuizhangtableService.save(liushui);
                        logtext += ",流水号：" + liushui.getId();
                    } else {
                        if (newstu.getRemainChongzhi().compareTo(pxqiandanpaymoney.getPayMoney()) == -1) {
                            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                            ajaxJson.setCode("100");
                            ajaxJson.setSuccess(false);
                            ajaxJson.setMsg("充值余额不够支付");
                            return ajaxJson;
                        } else {
                            newstu.setRemainChongzhi(newstu.getRemainChongzhi().subtract(pxqiandanpaymoney.getPayMoney()));
                            iPxstutableService.updateById(newstu);
                            Pxchongzhipaytable pxchongzhipaytable = new Pxchongzhipaytable();
                            pxchongzhipaytable.setAddStaffID(loginUser.getStaffID());
                            pxchongzhipaytable.setAddTime(new Date());
                            pxchongzhipaytable.setBeizhu("签单报名使用余额支付");
                            pxchongzhipaytable.setChongzhiPayMoney(pxqiandanpaymoney.getPayMoney());
                            pxchongzhipaytable.setQiandanID(qiandan.getId());
                            pxchongzhipaytable.setQiyeID(loginUser.getQiyeID());
                            pxchongzhipaytable.setStuID(newstu.getId());
                            pxchongzhipaytable.setType(2);
                            iPxchongzhipaytableService.save(pxchongzhipaytable);

                            logtext += "，签单报名使用余额支付。";
                        }
                    }
                }
            }

            ajaxJson.setSuccess(true);

        }

        savePxLog.savepxlog(logtext, "xwcloud-zsbm/zsbm/BaoMingJiaoFei/AddNewStuAndQianDan", loginUser.getStaffID(),
                loginUser.getStaffName(), 1, loginUser.getQiyeID());
        return ajaxJson;
    }

    /**
     * 修改费用说明
     *
     * @param form
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("updateqdbeizhu")
    @ApiOperation("修改费用说明")
    public AjaxJson updateqdbeizhu(@RequestBody qdfeiyongsmForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");

        Pxqiandaninfotable qd = iPxqiandaninfotableService.getById(form.getId());
        qd.setBeizhu(form.getBeizhu());
        ajaxJson.setSuccess(iPxqiandaninfotableService.updateById(qd));

        savePxLog.savepxlog("修改了签单：" + form.getId() + "的费用说明。", "xwcloud-zsbm/zsbm/BaoMingJiaoFei/updateqdbeizhu",
                loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());

        return ajaxJson;
    }


    /**
     * 修改签单
     *
     * @param form
     * @return
     */
    @RequestMapping(value = "/UpdateStuQianDan", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("修改签单")
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson UpdateStuQianDan(@RequestBody updateQiandanForm form, HttpServletRequest request) throws ParseException {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();
        String logtext = "";
        Pxqiandaninfotable qiandan = iPxqiandaninfotableService.getById(form.getQiandanid());
        if (qiandan == null) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("签单不存在，请重试！");
            return ajaxJson;
        }
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Pxsysparamdefaulttable pxsysparamdefaulttable = iPxsysparamdefaulttableService.GetSysParamById(43L);
        Date nowDate = new Date();
        Date qiandanDate = null;
        try {
            qiandanDate = ft.parse(form.getQiandandate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int days = (int) ((nowDate.getTime() - qiandanDate.getTime()) / (1000 * 3600 * 24));
        if (days > Integer.valueOf(pxsysparamdefaulttable.getDefaultValue())) {
            ajaxJson.setMsg("办理时间已超过" + days + "天，无法修改");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        BigDecimal yuanzongjia = new BigDecimal(0);
        BigDecimal yuankeshi = new BigDecimal(0);
        if (StringUtils.isNotBlank(form.getZidingyiStuNO())) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("zidingyiStuID", form.getZidingyiStuNO());
            queryWrapper.ne("id", qiandan.getId());
            List<Pxstutable> pxstutableList = iPxstutableService.getStuByZidingyi(queryWrapper);
            if (pxstutableList.size() > 1) {
                ajaxJson.setMsg("修改失败，学生学号重复！");
                ajaxJson.setCode("N");
                return ajaxJson;
            }
        }
        Pxstutable pxstutable = iPxstutableService.getById(qiandan.getStuID());
        Pxstafftable oldjbr = iPxstafftableService.getById(qiandan.getRecordInStaffID());
//        iPxkechengtableService.getById(qiandan.getk)
        String qdtype = "";
        if (qiandan.getMoneyStyle() == 1) {
            qdtype = "新签";
        } else if (qiandan.getMoneyStyle() == 2) {
            qdtype = "续费";
        } else if (qiandan.getMoneyStyle() == 3) {
            qdtype = "退费";
        } else if (qiandan.getMoneyStyle() == 4) {
            qdtype = "转送";
        } else if (qiandan.getMoneyStyle() == 5) {
            qdtype = "换课换出";
        } else if (qiandan.getMoneyStyle() == 6) {
            qdtype = "换课得到";
        }
        logtext =
                "学号：" + qiandan.getStuID() + ",姓名：" + pxstutable.getStuName() + "【修改签单】，修改前经办人:" + oldjbr.getStaffName() +
                        ",签单时间：" + qiandan.getQiandandate() + ",实收金额：" + qiandan.getShishouTotalMoney() + ",类别：" + qdtype + "。";


        pxstutable.setStuName(form.getStuName());
        pxstutable.setStuSex(form.getStuSex());
        pxstutable.setStuGradeID(form.getStuGradeID());
        pxstutable.setBuxiStateID(form.getBuxiStateID());
        pxstutable.setParentTel(form.getParentsTel());
        pxstutable.setStuTel(form.getParentsTel());
        pxstutable.setDengjiTeacherID(staffID);
        pxstutable.setZidingyiStuID(form.getZidingyiStuNO());
        if (StringUtils.isNotBlank(form.oldSname)) {
            Pxoldschooltable school = iPxoldschooltableService.GetOldSchoolByName(form.getOldSname());
            if (school != null) {
                pxstutable.setOldSchool(school.getOldSchoolID());
                if (StringUtils.isNotBlank(form.getOldTname())) {
                    Pxoldschoolteachertable teacher =
                            iPxoldschoolteachertableService.GetOldschoolteacherBytnameAndsID(form.getOldTname(),
                                    school.getOldSchoolID());
                    if (teacher != null) {
                        pxstutable.setOldSchoolTeacher(teacher.getOldSchoolTeacherID());
                    } else {
                        Pxoldschoolteachertable tea = new Pxoldschoolteachertable();
                        tea.setOldSchoolID(school.getOldSchoolID());
                        tea.setOldSchoolTeacherName(form.getOldTname());
                        tea.setQiyeID(qiyeID);
                        iPxoldschoolteachertableService.save(tea);
                        pxstutable.setOldSchoolTeacher(tea.getOldSchoolTeacherID());
                    }
                }
            } else {
                Pxoldschooltable sch = new Pxoldschooltable();
                sch.setOldSchoolName(form.getOldSname());
                sch.setQiyeID(qiyeID);
                iPxoldschooltableService.save(sch);
                pxstutable.setOldSchool(sch.getOldSchoolID());

                Pxoldschoolteachertable tea = new Pxoldschoolteachertable();
                tea.setOldSchoolID(sch.getOldSchoolID());
                tea.setOldSchoolTeacherName(form.getOldTname());
                tea.setQiyeID(qiyeID);
                iPxoldschoolteachertableService.save(tea);
                pxstutable.setOldSchoolTeacher(tea.getOldSchoolTeacherID());
            }
        }
        if (StringUtils.isNotBlank(form.getStuBrithday())) {
            try {
                pxstutable.setStubirth(ft.parse(form.getStuBrithday()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (StringUtils.isNotBlank(form.getNumberID())) {
            pxstutable.setIDnumber(form.getNumberID());
        }
        if (StringUtils.isNotBlank(form.getBeizhu())) {
            qiandan.setBeizhu(form.getBeizhu());
        }
        if (form.getTelFromID() != 0L) {
            qiandan.setFromType(form.getTelFromID());
        }
        try {
            qiandan.setQiandandate(ft.parse(form.getQiandandate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Pxkeshizhuansongtable> haveZS = iPxkeshizhuansongtableService.GetStuzhuansongInfo(qiandan.getStuID(),
                qiandan.getStuID());
        if (haveZS.size() > 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ajaxJson.setMsg("该学员存在课时转送，不能修改签单");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        List<buyWpVo> wpbuyList = JSON.parseArray(form.getWpData(), buyWpVo.class);
        if (wpbuyList != null) {
            for (buyWpVo buywpvo : wpbuyList) {
                Pxteachingsuppliestable TeachingSupplies =
                        iPxteachingsuppliestableService.GetTeachingSuppliesByName(buywpvo.getWpName());
                if (TeachingSupplies != null) {
                    if (TeachingSupplies.getStockNum().compareTo(buywpvo.getWpShuliang()) == -1) {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        ajaxJson.setMsg("当前所购买的教学物品" + buywpvo.getWpName() + "数量不足！");
                        ajaxJson.setCode("N");
                        return ajaxJson;
                    }
                    Pxqiandansupplies qdsupp =
                            iPxqiandansuppliesService.GetQiandanSuppliesByqdIDandSupID(qiandan.getId(),
                                    TeachingSupplies.getId());
                    BigDecimal oldsuppsum = new BigDecimal(0);
                    if (qdsupp != null) {
                        oldsuppsum = qdsupp.getBuySum();
                        qdsupp.setBuyPrice(buywpvo.getWpChushouJia());
                        qdsupp.setBuySum(buywpvo.getWpShuliang());
                        qdsupp.setSumMoney(buywpvo.getWpZongjia());
                        qdsupp.setQiyeID(qiyeID);
                    } else {
                        Pxqiandansupplies qdsp = new Pxqiandansupplies();
                        qdsp.setStuID(qiandan.getStuID());
                        qdsp.setTeachingSuppliesID(TeachingSupplies.getId());
                        qdsp.setName(buywpvo.getWpName());
                        qdsp.setQiandaninfoID(qiandan.getId());
                        qdsp.setBuyPrice(buywpvo.getWpChushouJia());
                        qdsp.setBuySum(buywpvo.getWpShuliang());
                        qdsp.setSumMoney(buywpvo.getWpZongjia());
                        qdsp.setIsTuiFei(false);
                        qdsp.setTuiMoney(new BigDecimal(0));
                        qdsp.setQiyeID(qiyeID);
                        iPxqiandansuppliesService.save(qdsp);

                        Pxteachingsuppliesouttable outjl = new Pxteachingsuppliesouttable();
                        outjl.setLuruStaffId(staffID);
                        outjl.setOutDate(new Date());
                        outjl.setOutNum(buywpvo.getWpShuliang());
                        outjl.setOutnumBefore(TeachingSupplies.getStockNum());
                        outjl.setOutReason("学生购买");
                        outjl.setOutStaffId(staffID);
                        outjl.setSuppliesId(TeachingSupplies.getId());
                        outjl.setType(2);
                        outjl.setQiyeID(qiyeID);
                        iPxteachingsuppliesouttableService.save(outjl);
                        TeachingSupplies.setStockNum(buywpvo.getWpShuliang());
                    }
                    iPxteachingsuppliestableService.updateById(TeachingSupplies);
                }
            }
        }
        if (StringUtils.isNotBlank(form.getZfData())) {
            List<buyZafeiVo> zafeiList = JSON.parseArray(form.getZfData(), buyZafeiVo.class);
            //签单其他费修改
            if (zafeiList != null) {
                for (buyZafeiVo buyZafeiVo : zafeiList) {
                    Pxqiandaninfo2table qd2 = iPxqiandaninfo2tableService.GetQiandanInfoByQiandanID(qiandan.getId(),
                            buyZafeiVo.getZafeiID());
                    if (qd2 == null) {
                        Pxqiandaninfo2table qd2Tab = new Pxqiandaninfo2table();
                        qd2Tab.setQianInfoTabID(qiandan.getId());
                        qd2Tab.setQianDanOtherMoneyID(buyZafeiVo.getZafeiID());
                        qd2Tab.setZongMoney(buyZafeiVo.getZafeiZongjia());
                        qd2Tab.setJiaoxueYonpingID(0L);
                        qd2Tab.setNums(new BigDecimal(1));
                        qd2Tab.setOnePrice(buyZafeiVo.getZafeiZongjia());
                        qd2Tab.setType(1);
                        qd2Tab.setTuiMoney(new BigDecimal(0));
                        qd2Tab.setQiyeID(qiyeID);
                        iPxqiandaninfo2tableService.save(qd2Tab);
                    } else {
                        qd2.setZongMoney(buyZafeiVo.getZafeiZongjia());
                        iPxqiandaninfo2tableService.updateById(qd2);
                    }
                }
            }
        }
        //保存签单人信息
        List<Pxqiandanstafftable> qdstaff = JSON.parseArray(form.getQiandanstaffinfo(), Pxqiandanstafftable.class);

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiandanID", qiandan.getId());
        iPxqiandanstafftableService.remove(queryWrapper1);
        for (Pxqiandanstafftable pxqiandanstafftable : qdstaff) {
            Pxqiandanstafftable newstaf = new Pxqiandanstafftable();
            newstaf.setQiandanID(qiandan.getId());
            newstaf.setStaffID(pxqiandanstafftable.getStaffID());
            newstaf.setYejiMoney(pxqiandanstafftable.getYejiMoney());
            newstaf.setYejidateTime(qiandan.getQiandandate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            newstaf.setQiyeID(qiyeID);
            iPxqiandanstafftableService.save(newstaf);
        }


        Pxdaijinquantable djq = iPxdaijinquantableService.GetDaijinquanByID(qiandan.getId());//该签单的代金券
        if (form.getDaijinquan().compareTo(new BigDecimal(0)) == 1) {
            //如果现在使用了代金券
            if (djq != null) {
                //之前也使用过代金券
                logtext += ";改前代金券：" + djq.getMoney().toString() + ",改后代金券：" + form.getDaijinquan().toString();
                djq.setMoney(form.getDaijinquan());
                iPxdaijinquantableService.updateById(djq);
            } else {
                //之前没用代金券
                djq = new Pxdaijinquantable();
                try {
                    djq.setCreatTime(ft.parse(form.qiandandate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                djq.setMoney(form.getDaijinquan());
                djq.setQiandanID(qiandan.getId());
                djq.setStaffID(staffID);
                djq.setStuID(qiandan.getStuID());
                djq.setQiyeID(qiyeID);
                iPxdaijinquantableService.save(djq);
                logtext += ";改前无代金券，改后用代金券：" + form.getDaijinquan().toString();
            }
        } else {
            //如果现在没使用了代金券
            if (djq != null) {
                //以前用了代金券，就把它删除掉

                logtext += "；改前用代金券：" + djq.getMoney().toString() + "，改后无代金券";
                iPxdaijinquantableService.removeById(djq);
            }
        }
        boolean isOldDingjin = false;//上次是否使用定金
        boolean isNewDingjin = false;//本次是否使用定金
        BigDecimal oldKCzongjia = new BigDecimal(0);//上次的课程总价
        BigDecimal oldDingjin = new BigDecimal(0);//上次的定金
        //判定上次有没有定金
        oldKCzongjia = iPxqiandansubjecttableService.GetQiandanKechengzongjia(qiandan.getId());
        if (qiandan.getHetongMoney().compareTo(qiandan.getShishouTotalMoney()) == 0) {
            //上次有定金
            isOldDingjin = true;
            oldDingjin = qiandan.getShishouTotalMoney();
        } else {
            //上次全款
            isOldDingjin = false;
            oldDingjin = new BigDecimal(0);
        }

        if (form.getDingjin().compareTo(new BigDecimal(0)) != 0) {
            //本次有定金
            isNewDingjin = true;
        } else {
            //这次没有使用定金
            isNewDingjin = false;
        }

        //region 优惠政策
        BigDecimal youhuijine = new BigDecimal(0);
        Pxyouhuizhengcetable yhzc = iPxyouhuizhengcetableService.getYouhuizhengceById(form.getSelectYouhui());
        if (yhzc != null) {
            BigDecimal qiandanAmount = form.getBuxikechengMoney().add(form.getWpPrice()).add(form.getZfAmount());
            if (yhzc.getYouhuiType() == 2) {//满减
                youhuijine = yhzc.getYouhui();
            } else if (yhzc.getYouhuiType() == 1) {//打折
                youhuijine =
                        qiandanAmount.subtract(qiandanAmount.multiply(yhzc.getYouhui()).divide(new BigDecimal(10)));
            }
        }
        //endregion


        if (!isOldDingjin && !isNewDingjin) {
            //两次都没有使用定金
            BigDecimal nowshishouTotalMoney =
                    form.getBuxikechengMoney().add(form.getZfAmount()).add(form.getWpPrice()).add(form.getZfAmount()).subtract(form.getDaijinquan()).subtract(youhuijine);
            logtext += "，（两次都没有使用定金）改前：实收金额：" + qiandan.getShishouTotalMoney().toString() + ",改后实收金额：" + nowshishouTotalMoney.toString();

            qiandan.setShishouTotalMoney(nowshishouTotalMoney);
        } else if (!isOldDingjin && isNewDingjin) {
            //上次没有使用，这次有用
            logtext += "，（上次没有使用定金，这次使用定金）改前：实收金额：" + qiandan.getShishouTotalMoney().toString() + ",改后实收金额：" + form.getDingjin().toString();
            qiandan.setShishouTotalMoney(form.getDingjin());
        } else if (isOldDingjin && !isNewDingjin) {
            //上次有使用定金，这次没有使用定金
            BigDecimal nowshishouTotalMoney =
                    form.getBuxikechengMoney().add(form.getWpPrice()).add(form.getZfAmount()).subtract(form.getDaijinquan()).subtract(youhuijine);
            logtext += "，（上次有使用定金，这次没有使用定金）改前：实收金额：" + qiandan.getShishouTotalMoney().toString() + ",改后实收金额：" + nowshishouTotalMoney.toString();
            qiandan.setShishouTotalMoney(nowshishouTotalMoney);
        } else {
            //两次都使用了定金
            logtext += "，（两次都使用了定金）改前：实收金额：" + qiandan.getShishouTotalMoney().toString() + ",改后实收金额：" + form.getDingjin().toString();
            qiandan.setShishouTotalMoney(form.getDingjin());
        }


        qiandan.setHetongMoney(form.getBuxikechengMoney().add(form.getWpPrice()).add(form.getZfAmount()).subtract(youhuijine).subtract(form.getDaijinquan()));

        qiandan.setQianDanStaffID(staffID);
        qiandan.setMoneyStyle(qiandan.getMoneyStyle());
        qiandan.setPayMoneyStyle(0L);
        qiandan.setRecordInStaffID(staffID);
        qiandan.setYouhuiID(form.getSelectYouhui());
        qiandan.setYouhuijine(youhuijine.toString());
        qiandan.setQiyeID(qiyeID);
        qiandan.setBeizhu(form.beizhu);
        qiandan.setYouhuishuoming(youhuijine.compareTo(new BigDecimal(0)) == 0 ? "未优惠" : yhzc.getYouhuiType() == 1 ?
                "金额达到或超过" + yhzc.getXianzhijine() + "元，" + yhzc.getYouhui() + "折优惠" :
                "金额达到或者超过" + yhzc.getXianzhijine() +
                        "元，满减优惠" + yhzc.getYouhui() + "元");


        logtext += "；改后：其他费：" + form.getZfAmount();

        iPxqiandaninfotableService.updateById(qiandan);
        /*修改培训课程表和签单Subject表*/
        //updateBxKcInfo(Jarows, qiandan.id, moneyStyle);
        //region 修改qiandanSubjectTable和buxikechengTable
        Long qiandanId = qiandan.getId();
        BigDecimal oldRemainXuefei = pxstutable.getRemainXuefei();


        //把删除掉的课程对应的记录和学费扣减出来
        BigDecimal delmoney = new BigDecimal(0);  //课程删除对应的删除学费
        if (StringUtils.isNotBlank(form.getRemovedKCstr())) {
            for (String buxiID : form.getRemovedKCstr().split(",")) {
                if (StringUtils.isNotBlank(buxiID)) {
                    Pxqiandansubjecttable thisqiandansubject = iPxqiandansubjecttableService.getById(buxiID);
                    //要删除的这门课是否有赠送课时，有赠送的，要先把这门课的赠送删除掉；
                    QueryWrapper queryWrapper2 = new QueryWrapper();
                    queryWrapper2.eq("qiandanInfoID", qiandanId);
                    queryWrapper2.eq("kechengId", thisqiandansubject.getKechengID());
                    queryWrapper2.eq("kechengPrice", thisqiandansubject.getKechengprice());
                    queryWrapper2.eq("stuId", thisqiandansubject.getStuID());
                    List<Pxkeshizengsongtable> qdkczs = iPxkeshizengsongtableService.GetZengsongRecords(queryWrapper2);
                    if (qdkczs.size() > 0) {
                        for (Pxkeshizengsongtable pxkeshizengsongtable : qdkczs) {
                            //把buxikechengTable里的赠送课时记录删除或扣减掉
                            QueryWrapper bxkcquery = new QueryWrapper();
                            bxkcquery.eq("stuID", pxkeshizengsongtable.getStuID());
                            bxkcquery.eq("kechengprice", pxkeshizengsongtable.getKechengPrice());
                            bxkcquery.eq("kechengID", pxkeshizengsongtable.getKechengID());
                            bxkcquery.eq("type", 2);
                            Pxbuxikechengtable bxkczs = iPxbuxikechengtableService.GetZidingYiKecheng(bxkcquery);
                            if (bxkczs != null) {
                                if (bxkczs.getRemainkeshi() == pxkeshizengsongtable.getKeshiShu()) {
                                    //如果buxikechengTable里这条赠送课程的剩余课时和赠送课时表里的赠送课时相等，说明这条记录还没有过课耗，直接删除
                                    iPxbuxikechengtableService.removeById(bxkczs);
                                } else {
                                    //如果buxikechengTable里这条赠送课程的剩余课时和赠送课时表里的赠送课时不相等，说明这条记录有过课耗，只是减去掉赠送
                                    BigDecimal keshiShu =
                                            bxkczs.getRemainkeshi().subtract(pxkeshizengsongtable.getKeshiShu());
                                    bxkczs.setRemainkeshi(keshiShu);
                                    BigDecimal kshiNum =
                                            bxkczs.getKeshiNum().subtract(pxkeshizengsongtable.getKeshiShu());
                                    bxkczs.setKeshiNum(kshiNum);
                                }
                            }
                        }
                    }
                    QueryWrapper queryWrapper3 = new QueryWrapper();
                    queryWrapper3.eq("stuID", thisqiandansubject.getStuID());
                    queryWrapper3.eq("kechengID", thisqiandansubject.getKechengID());
                    queryWrapper3.eq("kechengprice", thisqiandansubject.getKechengprice());
                    queryWrapper3.ne("type", 2);
                    Pxbuxikechengtable buxikc = iPxbuxikechengtableService.GetZidingYiKecheng(queryWrapper3);
                    if (buxikc != null) {
                        QueryWrapper queryWrapper4 = new QueryWrapper();
                        queryWrapper4.eq("qianDanInfoID", qiandanId);
                        queryWrapper4.eq("stuID", qiandan.getStuID());
                        queryWrapper4.eq("kechengID", buxikc.getKechengID());
                        queryWrapper4.eq("kechengprice", buxikc.getKechengprice());

                        Pxqiandansubjecttable qandansubdel =
                                iPxqiandansubjecttableService.GetQiandansubjectZidingyi(queryWrapper4);
                        if (qandansubdel != null) {
                            yuanzongjia = qandansubdel.getZongjia();
                            yuankeshi = qandansubdel.getBuykeshiNum();
                            iPxqiandansubjecttableService.removeById(qandansubdel);
                        }

                        delmoney = delmoney.add((BigDecimal) qandansubdel.getZongjia());
                        //删除
                        if (buxikc.getRemainkeshi().compareTo(qandansubdel.getBuykeshiNum()) == 1) {
                            //说明本课程存在多条签单记录，不能直接删除课程,也不能删除班级
                            buxikc.setRemainkeshi(buxikc.getRemainkeshi().subtract(qandansubdel.getBuykeshiNum()));
                            buxikc.setKeshiNum(buxikc.getKeshiNum().subtract(qandansubdel.getBuykeshiNum()));
                            buxikc.setZongjia(buxikc.getZongjia().subtract(qandansubdel.getZongjia()));
                            iPxbuxikechengtableService.updateById(buxikc);
                        } else {
                            //有课耗情况下是不能删除的，所以在这里不大于 就一定是等于
                            //如果是一对一课程，还要把班级删除掉
                            Pxkechengtable delkecheng = iPxkechengtableService.GetKechengById(buxikc.getKechengID());
                            String buxiStyleName =
                                    iPxbuxistyletableService.getById(delkecheng.getBuxiStyleID()).getBuxiStyleName();
                            if (buxiStyleName == "一对一") {
                                Pxstuclasstable stucla = iPxstuclasstableService.GetStuclassBybuxiID(buxikc.getId());
                                if (stucla != null) {
                                    Pxclasstable oneCla = iPxclasstableService.getById(stucla.getClassID());
                                    if (oneCla != null) {
                                        //删除班级之前，把这个班的排课全删除掉
                                        List<Pxpaiketable> claPaike =
                                                iPxpaiketableService.GetPaikebyClassID(stucla.getClassID());
                                        if (claPaike.size() > 0) {
                                            for (Pxpaiketable pxpaiketable : claPaike) {
                                                iPxpaiketeachertableService.DeletePaikeTeacherByPaikeID(pxpaiketable.getId());
                                                //删除选课表里的班级学员
                                                iPxxuanketableService.deleteXuanKeTable(pxpaiketable.getId());
                                            }
                                        }
                                        iPxclasstableService.removeById(oneCla.getId());
                                    }
                                    iPxstuclasstableService.removeById(stucla.getId());
                                }
                            } else {
                                //删除这门课程的时候要删除选课表
                                //在这里是->相当于这个学生的这门课只有这一条签单的时候，不存在多条签单，有排课得删除
                                QueryWrapper queryWrapperxk = new QueryWrapper();
                                queryWrapperxk.eq("buxiID", buxikc.getId());
                                queryWrapperxk.eq("stuID", buxikc.getStuID());
                                List<Pxxuanketable> pxxuanketableList = iPxxuanketableService.list(queryWrapperxk);
                                if (pxxuanketableList.size() > 0) {
                                    iPxxuanketableService.deleteXuankeByBxIDAndStuID(buxikc.getId(), buxikc.getStuID());
                                }
                            }
                            Pxkechengtable delkc = iPxkechengtableService.getById(buxikc.getKechengID());
                            iPxbuxikechengtableService.removeById(buxikc.getId());

                            logtext += "改签单：删除原签单课程《" + delkc.getKechengName() + "》，原单价" + buxikc.getOriginalprice().toString() + "，现单价：" + buxikc.getKechengprice().toString() + ",课时：" + buxikc.getKeshiNum().toString() + ",课程总价：" + buxikc.getZongjia().toString();
                        }
                    }
                }
            }
        }


        List<buyKechengVo> buykecheng = JSON.parseArray(form.getBxKcData(), buyKechengVo.class);
        if (buykecheng != null) {
            BigDecimal thisKCzongjia = new BigDecimal(0);
            for (buyKechengVo buyKechengVo : buykecheng) {
                thisKCzongjia.add(buyKechengVo.getZJ());
                Pxkechengtable kecheng = iPxkechengtableService.getById(buyKechengVo.getKechengID());
                if (buyKechengVo.getQdsID() == 0L) {
                    //修改时新增加的课程
                    //添加qianDanSubjectTable记录
                    Pxqiandansubjecttable qdsub = new Pxqiandansubjecttable();
                    qdsub.setStuID(qiandan.getStuID());
                    qdsub.setQiandandate(new Date());
                    qdsub.setKechengID(buyKechengVo.getKechengID());
                    qdsub.setKechengprice(buyKechengVo.getDJ());
                    qdsub.setOriginalprice(buyKechengVo.getYDJ());
                    qdsub.setBuykeshiNum(buyKechengVo.getKS());
                    qdsub.setZongjia(buyKechengVo.getZJ());
                    qdsub.setKechengStyle(1);    //1买的 2 接受的赠送 3 送出的 4 退费
                    qdsub.setDiscount(buyKechengVo.getZK());
                    qdsub.setQianDanInfoID(qiandanId);
                    qdsub.setStartDate(ft.parse(buyKechengVo.getStartDate()));
                    qdsub.setEndDate(ft.parse(buyKechengVo.getEndDate()));
                    qdsub.setQiyeID(qiyeID);
                    iPxqiandansubjecttableService.save(qdsub);
                    //如果，修改过程中删除了原来所有课程，又给新加一条原来课程列表里面的一天记录只是课时不同bxkcId还是等于0

                    //添加buxikechengTable记录、一对一的话还要建班
                    Pxbuxikechengtable buxi = new Pxbuxikechengtable();
                    buxi.setStuID(qiandan.getStuID());
                    buxi.setKechengID(buyKechengVo.getKechengID());
                    Long cla = 0L;
                    String buxiStyleName =
                            iPxbuxistyletableService.getById(kecheng.getBuxiStyleID()).getBuxiStyleName();
                    if (buxiStyleName == "一对一") {
                        boolean ii = false;
                        while (ii == false) {
                            Random random = new Random();
                            int max = 999;
                            int min = 100;
                            int sjs = random.nextInt(max) % (max - min + 1) + min;
                            String className = pxstutable.getStuName() + "_" + buyKechengVo.getKmName() + "_一对一" + sjs;
                            List<Pxclasstable> pdclass = iPxclasstableService.GetClassByClassName(className);
                            if (pdclass.size() == 0) {
                                Pxclasstable classtable = new Pxclasstable();
                                classtable.setClassName(className);
                                classtable.setCampusID(pxstutable.getCampusID());
                                classtable.setAddStaffID(staffID);
                                classtable.setAddTime(new Date());
                                classtable.setIsShow(1);
                                classtable.setIs1v1Class(1);
                                classtable.setQiyeID(qiyeID);
                                iPxclasstableService.save(classtable);
                                cla = classtable.getId();
                                ii = true;
                            }
                        }
                    } else {
                        if (StringUtils.isNotBlank(buyKechengVo.getBjName())) {
                            if (!StringUtils.isNotBlank(buyKechengVo.getBjName())) {
                                Pxclasstable classTable =
                                        iPxclasstableService.GetClassByClassNameOne(buyKechengVo.getBjName());
                                if (classTable != null) {
                                    cla = classTable.getId();
                                }
                            }
                        }
                    }
                    buxi.setKechengprice(buyKechengVo.getDJ());
                    buxi.setOriginalprice(buyKechengVo.getYDJ());
                    buxi.setRemainkeshi(buyKechengVo.getKS());
                    buxi.setKeshiNum(buyKechengVo.getKS());
                    buxi.setZongjia(buyKechengVo.getZJ());
                    buxi.setStartDate(ft.parse(buyKechengVo.getStartDate()));
                    try {
                        buxi.setBuykeshiDateTime(ft.parse(form.getQiandandate()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    buxi.setQianDanInfoID(qiandan.getId());
                    buxi.setQianDanSubjectID(qdsub.getId());
                    if (iPxbuxikechengtableService.GetStuBuxikechengList(qiandan.getStuID(), buyKechengVo.getKCID(),
                            qiyeID).size() == 0) {
                        buxi.setIsShow(0);
                    } else {
                        buxi.setIsShow(1);
                    }
                    buxi.setJifeiStyleID(buyKechengVo.getJifeistyle());
                    buxi.setType(1);
                    buxi.setQiyeID(qiyeID);
                    iPxbuxikechengtableService.save(buxi);
                    Pxkechengtable addkecheng = iPxkechengtableService.getById(buxi.getKechengID());

                    logtext += "。修改签单新增课程：" + addkecheng.getKechengName() + ",原单价" + buxi.getOriginalprice().toString() +
                            "," +
                            "现单价" + buxi.getKechengprice().toString() + ",课时" + buxi.getKeshiNum().toString() + "，总价" + buxi.getZongjia().toString();


                    Pxbxkcchangetable bxchange = new Pxbxkcchangetable();
                    bxchange.setAddDate(new Date());
                    bxchange.setAddStaffID(staffID);
                    bxchange.setOldbxkcID(buxi.getId());
                    bxchange.setOldbxkcName(iPxkechengtableService.getById(buxi.getKechengID()).getKechengName());
                    bxchange.setOldendDate(buxi.getEndDate());
                    bxchange.setOldkcID(buxi.getKechengID());
                    bxchange.setOldprice(buxi.getKechengprice());
                    bxchange.setOldqiandanID(qiandan.getId());
                    bxchange.setOldrkeshi(buxi.getRemainkeshi());
                    bxchange.setOldstartDate(buxi.getStartDate());
                    bxchange.setOldStuID(buxi.getStuID());
                    bxchange.setOldzongjia(buxi.getZongjia());
                    bxchange.setType(3);
                    bxchange.setQiyeID(qiyeID);
                    iPxbxkcchangetableService.save(bxchange);


                    if (cla != 0L) {
                        Pxstuclasstable stucla = new Pxstuclasstable();
                        stucla.setBuxiID(buxi.getId());
                        stucla.setClassID(cla);
                        iPxstuclasstableService.save(stucla);

                        if (buyKechengVo.getCkb()) {
                            //如果新加入的班级原有排课了，再把学生加在选课表里面去
                            List<Pxpaiketable> paikenews = iPxpaiketableService.GetPaikebyClassID(cla);
                            for (Pxpaiketable pxpaiketable : paikenews) {
                                List<Pxxuanketable> pdxuankeTab =
                                        iPxxuanketableService.GetXuankeByBuxiIDAndPaikeId(buxi.getStuID(),
                                                pxpaiketable.getId());
                                if (pdxuankeTab.size() == 0) {
                                    Pxxuanketable xuanke = new Pxxuanketable();
                                    xuanke.setBuxiID(buxi.getId());
                                    xuanke.setPaikeID(pxpaiketable.getId());
                                    xuanke.setStuID(buxi.getStuID());
                                    xuanke.setRecordDate(new Date());
                                    xuanke.setType(1);
                                    xuanke.setQiyeID(qiyeID);
                                    iPxxuanketableService.save(xuanke);
                                }
                            }
                        }
                    }

                    if (buyKechengVo.getZKS().compareTo(new BigDecimal(0)) == 1) {
                        //添加赠送记录
                        Pxkeshizengsongtable keshizengsong = new Pxkeshizengsongtable();
                        keshizengsong.setKechengID(buyKechengVo.getKCID());
                        keshizengsong.setKechengPrice(buyKechengVo.getDJ());
                        keshizengsong.setKeshiShu(buyKechengVo.getZKS());
                        keshizengsong.setSongYangyin("签单课时赠送");
                        keshizengsong.setStuID(qiandan.getStuID());
                        keshizengsong.setAddDate(new Date());
                        keshizengsong.setCaozuoStaffId(staffID);
                        keshizengsong.setQiandanInfoID(qiandan.getId());
                        keshizengsong.setJifeiStyle(buyKechengVo.getJifeistyle());
                        keshizengsong.setQiyeID(qiyeID);
                        iPxkeshizengsongtableService.save(keshizengsong);
                        Pxbuxikechengtable pdbuxikc = iPxbuxikechengtableService.GetBuxikecheng(qiandan.getStuID(),
                                buyKechengVo.getKCID(), buyKechengVo.getDJ(), qiyeID);
                        if (pdbuxikc != null) {
                            pdbuxikc.setRemainkeshi(buyKechengVo.getZKS());
                            pdbuxikc.setKeshiNum(buyKechengVo.getZKS());
                            iPxbuxikechengtableService.updateById(pdbuxikc);
                        } else {
                            //添加赠送补习课程记录
                            Pxbuxikechengtable zsbxTab = new Pxbuxikechengtable();
                            zsbxTab.setStuID(qiandan.getStuID());
                            zsbxTab.setKechengID(buyKechengVo.getKCID());
                            zsbxTab.setKechengprice(buyKechengVo.getDJ());
                            zsbxTab.setOriginalprice(buyKechengVo.getYDJ());
                            zsbxTab.setRemainkeshi(buyKechengVo.getZKS());
                            zsbxTab.setKeshiNum(buyKechengVo.getZKS());
                            zsbxTab.setZongjia(new BigDecimal(0));
                            zsbxTab.setStartDate(ft.parse(buyKechengVo.getStartDate()));
                            zsbxTab.setEndDate(ft.parse(buyKechengVo.getEndDate()));
                            zsbxTab.setBuykeshiDateTime(new Date());
                            zsbxTab.setIsShow(0);
                            zsbxTab.setJifeiStyleID(buyKechengVo.getJifeistyle());
                            zsbxTab.setType(2);    //赠送的
                            zsbxTab.setQianDanInfoID(qiandan.getId());
                            zsbxTab.setQianDanSubjectID(qdsub.getId());
                            iPxbuxikechengtableService.save(zsbxTab);
                            Pxbxkcchangetable bxcg = new Pxbxkcchangetable();
                            bxcg.setOldbxkcID(zsbxTab.getId());
                            bxcg.setOldbxkcName(iPxkechengtableService.getById(zsbxTab.getKechengID()).getKechengName());
                            bxcg.setOldendDate(zsbxTab.getEndDate());
                            bxcg.setOldkcID(zsbxTab.getKechengID());
                            bxcg.setOldprice(zsbxTab.getKechengprice());
                            bxcg.setOldqiandanID(qiandan.getId());
                            bxcg.setOldrkeshi(zsbxTab.getRemainkeshi());
                            bxcg.setOldstartDate(zsbxTab.getStartDate());
                            bxcg.setOldStuID(qiandan.getStuID());
                            bxcg.setOldzongjia(zsbxTab.getZongjia());
                            bxcg.setType(9);
                            bxcg.setAddDate(new Date());
                            bxcg.setAddStaffID(staffID);
                            iPxbxkcchangetableService.save(bxcg);

                            Pxkechengtable kechengTemp = iPxkechengtableService.getById(buyKechengVo.getKCID());
                            String buxiStyleNameTemp =
                                    iPxbuxistyletableService.getById(kechengTemp.getBuxiStyleID()).getBuxiStyleName();
                            if (buxiStyleNameTemp == "一对一") {
                                Long classIDTemp = 0L;
                                String subjectNameTemp =
                                        iPxsubjecttableService.getById(kechengTemp.getSubjectID()).getSubjectName();
                                //建班插班
                                boolean ii = false;
                                while (ii == false) {
                                    Random random = new Random();
                                    int max = 999;
                                    int min = 100;
                                    int sjs = random.nextInt(max) % (max - min + 1) + min;
                                    String className = "(赠送)" + pxstutable.getStuName() + "_" + subjectNameTemp + "_" +
                                            "一对一" + sjs;
                                    ;
                                    Pxclasstable pdclass = iPxclasstableService.GetClassByClassNameOne(className);
                                    if (pdclass == null) {
                                        Pxclasstable classtable = new Pxclasstable();
                                        classtable.setClassName(className);
                                        classtable.setCampusID(pxstutable.getCampusID());
                                        classtable.setAddStaffID(staffID);
                                        classtable.setAddTime(new Date());
                                        classtable.setIs1v1Class(1);
                                        classtable.setIsShow(1);
                                        iPxclasstableService.save(classtable);
                                        classIDTemp = classtable.getId();
                                        ii = true;
                                    }
                                }
                                //如果是一对一，要插班
                                Pxstuclasstable stucla = new Pxstuclasstable();
                                stucla.setBuxiID(zsbxTab.getId());
                                stucla.setClassID(classIDTemp);
                                iPxstuclasstableService.save(stucla);
                            }
                        }
                    }
                } else {
                    //原有的课程
                    //先看看有没有课耗，如果有课耗，就不能改！（和前端的控制保持一致，前端也是只要有课耗就不能修改和删除）
                    Pxqiandansubjecttable kehaoqiandanSubject =
                            iPxqiandansubjecttableService.getById(buyKechengVo.getQdsID());
                    Pxbuxikechengtable kehao =
                            iPxbuxikechengtableService.GetBuyBuxikecheng(kehaoqiandanSubject.getStuID(),
                                    kehaoqiandanSubject.getKechengID(), kehaoqiandanSubject.getKechengprice(), qiyeID);
                    if (kehao != null) {
                        //只是没有课耗的才改；
                        Pxbuxikechengtable bxKcOld = kehao;
                        BigDecimal oldkechengprice = bxKcOld.getKechengprice();
                        Pxqiandansubjecttable qdsubOld = iPxqiandansubjecttableService.getById(buyKechengVo.getQdsID());
                        yuankeshi = qdsubOld.getBuykeshiNum();
                        yuanzongjia = qdsubOld.getZongjia();
                        BigDecimal yuankechengprice = qdsubOld.getKechengprice();
                        Pxbxkcchangetable bxkcOldchange = new Pxbxkcchangetable();
                        bxkcOldchange.setAddDate(new Date());
                        bxkcOldchange.setAddStaffID(staffID);
                        bxkcOldchange.setOldbxkcID(bxKcOld.getId());
                        bxkcOldchange.setOldbxkcName(iPxkechengtableService.getById(bxKcOld.getKechengID()).getKechengName());
                        bxkcOldchange.setOldendDate(bxKcOld.getEndDate());
                        bxkcOldchange.setOldkcID(bxKcOld.getKechengID());
                        bxkcOldchange.setOldprice(bxKcOld.getKechengprice());
                        bxkcOldchange.setOldqiandanID(qiandan.getId());
                        bxkcOldchange.setOldrkeshi(bxKcOld.getRemainkeshi());
                        bxkcOldchange.setOldstartDate(bxKcOld.getStartDate());
                        bxkcOldchange.setOldStuID(qiandan.getStuID());
                        bxkcOldchange.setOldzongjia(bxKcOld.getZongjia());
                        bxkcOldchange.setNewStuID(qiandan.getStuID());
                        bxkcOldchange.setNewkcID(bxKcOld.getKechengID());
                        bxkcOldchange.setNewbxkcName(iPxkechengtableService.getById(bxKcOld.getKechengID()).getKechengName());

                        if (bxKcOld.getKechengprice().compareTo(buyKechengVo.getDJ()) == 0)   //原课程修改，判断是否改变了价格
                        {
                            //课程价格没有变
                            bxKcOld.setOriginalprice(buyKechengVo.getYDJ());
                            bxKcOld.setKechengprice(buyKechengVo.getDJ());
                            bxKcOld.setKeshiNum(bxKcOld.getKeshiNum().subtract(yuankeshi).add(buyKechengVo.getKS()));
                            bxKcOld.setRemainkeshi(bxKcOld.getRemainkeshi().subtract(qdsubOld.getBuykeshiNum()).add(buyKechengVo.getKS()));
                            bxKcOld.setZongjia(bxKcOld.getZongjia().subtract(yuanzongjia).add(buyKechengVo.getZJ()));
                            bxKcOld.setStartDate(ft.parse(buyKechengVo.getStartDate()));
                            bxkcOldchange.setNewstartDate(ft.parse(buyKechengVo.getStartDate()));
                            bxKcOld.setEndDate(ft.parse(buyKechengVo.getEndDate()));
                            bxkcOldchange.setNewendDate(ft.parse(buyKechengVo.getEndDate()));
                            bxKcOld.setJifeiStyleID(buyKechengVo.getJifeistyle());
                            //region 处理原签单课程的赠送问题——单价没改的情况下！

                            //处理原签单课程的赠送问题
                            Pxkeshizengsongtable kczsOld =
                                    iPxkeshizengsongtableService.GetZongSongInfo(qiandan.getId(),
                                            kehaoqiandanSubject.getKechengID(), yuankechengprice);
                            if (kczsOld != null) {
                                BigDecimal yuanzsKeshiNum = kczsOld.getKeshiShu();  //原赠送的课时数

                                //原来有赠送课时
                                QueryWrapper queryWrapper2 = new QueryWrapper();
                                queryWrapper2.eq("qiandanInfoID", qiandan.getId());
                                queryWrapper2.eq("kechengId", kehaoqiandanSubject.getKechengID());
                                queryWrapper2.eq("kechengPrice", qdsubOld.getKechengprice());
                                List<Pxkeshizengsongtable> kczsOldAll =
                                        iPxkeshizengsongtableService.GetZengsongRecords(queryWrapper2);
                                if (kczsOldAll.size() > 1) {
                                    //如果原来的签单中，这门课有多条赠送记录，则把其他的赠送记录删除掉
                                    for (Pxkeshizengsongtable pxkeshizengsongtable : kczsOldAll) {
                                        if (pxkeshizengsongtable.getId() != kczsOld.getId()) {
                                            iPxkeshizengsongtableService.removeById(pxkeshizengsongtable.getId());
                                        }
                                    }
                                }
                                if (buyKechengVo.getZKS().compareTo(new BigDecimal(0)) != 0) {
                                    //原来有赠送课时，现在也有赠送课时
                                    //则修改原来的赠送课时记录里的单价和赠送课时数
                                    Pxbuxikechengtable zsbxkc =
                                            iPxbuxikechengtableService.GetBuxikecheng(qiandan.getStuID(),
                                                    kehaoqiandanSubject.getKechengID(), kczsOld.getKechengPrice(),
                                                    qiyeID);
                                    kczsOld.setKechengPrice(buyKechengVo.getDJ());
                                    kczsOld.setKeshiShu(buyKechengVo.getZKS());
                                    if (zsbxkc != null) {
                                        zsbxkc.setOriginalprice(buyKechengVo.getYDJ());
                                        zsbxkc.setKechengprice(buyKechengVo.getDJ());
                                        zsbxkc.setRemainkeshi(zsbxkc.getRemainkeshi().add(buyKechengVo.getZKS().subtract(yuanzsKeshiNum)));
                                        zsbxkc.setRemainkeshi(zsbxkc.getRemainkeshi().add(buyKechengVo.getZKS().subtract(yuanzsKeshiNum)));
                                    }
                                } else {
                                    //原来有赠送课时，现在没有赠送课时,则:把补习课程表里的赠送记录删除掉，还要把原来的赠送课时记录删除掉
                                    Pxbuxikechengtable zsbxkc =
                                            iPxbuxikechengtableService.GetBuxikecheng(qiandan.getStuID(),
                                                    kehaoqiandanSubject.getKechengID(), kczsOld.getKechengPrice(),
                                                    qiyeID);
                                    if (zsbxkc != null) {
                                        //把补习课程表里的赠送记录删除掉
                                        iPxbuxikechengtableService.removeById(zsbxkc.getId());
                                    }
                                    //把原来的赠送课时记录删除掉
                                    iPxkeshizengsongtableService.removeById(kczsOld.getId());
                                }
                            } else {
                                //走到这个分支，说明：原来的签单没有赠送课时
                                if (buyKechengVo.getZKS().compareTo(new BigDecimal(0)) != 0) {
                                    //走这个分支，说明：原来的签单没有赠送课时，但现在有赠送课时,则往赠送课时表里添加一条赠送记录
                                    Pxkeshizengsongtable sks = new Pxkeshizengsongtable();
                                    sks.setStuID(qiandan.getStuID());
                                    sks.setKechengID(kehaoqiandanSubject.getKechengID());
                                    sks.setKechengPrice(buyKechengVo.getDJ());
                                    sks.setKeshiShu(buyKechengVo.getZKS());
                                    sks.setCaozuoStaffId(staffID);
                                    sks.setAddDate(new Date());
                                    sks.setSongYangyin("签单课时赠送");
                                    sks.setJifeiStyle(iPxkechengtableService.getById(kehaoqiandanSubject.getKechengID()).getJifeiStyleID());
                                    sks.setQiandanInfoID(qiandan.getId());
                                    sks.setQiyeID(qiyeID);
                                    iPxkeshizengsongtableService.save(sks);

                                    Pxbuxikechengtable zsbx = new Pxbuxikechengtable();
                                    zsbx.setStuID(qiandan.getStuID());
                                    zsbx.setKechengID(kehaoqiandanSubject.getKechengID());
                                    zsbx.setKechengprice(buyKechengVo.getDJ());
                                    zsbx.setOriginalprice(buyKechengVo.getYDJ());
                                    zsbx.setRemainkeshi(buyKechengVo.getZKS());
                                    zsbx.setKeshiNum(buyKechengVo.getZKS());
                                    zsbx.setZongjia(new BigDecimal(0));
                                    zsbx.setStartDate(kehaoqiandanSubject.getStartDate());
                                    zsbx.setEndDate(kehaoqiandanSubject.getEndDate());
                                    zsbx.setBuykeshiDateTime(kehaoqiandanSubject.getQiandandate());
                                    zsbx.setIsShow(0);
                                    zsbx.setJifeiStyleID(iPxkechengtableService.getById(kehaoqiandanSubject.getKechengID()).getJifeiStyleID());
                                    zsbx.setType(2);
                                    zsbx.setQianDanInfoID(qiandan.getId());
                                    zsbx.setQianDanSubjectID(buyKechengVo.getQdsID());
                                    zsbx.setQiyeID(qiyeID);
                                    iPxbuxikechengtableService.save(zsbx);

                                    Pxbxkcchangetable cgbx = new Pxbxkcchangetable();
                                    cgbx.setAddDate(new Date());
                                    cgbx.setAddStaffID(staffID);
                                    cgbx.setType(9);
                                    cgbx.setOldbxkcID(zsbx.getId());
                                    cgbx.setOldbxkcName(iPxkechengtableService.getById(zsbx.getKechengID()).getKechengName());
                                    cgbx.setOldendDate(zsbx.getEndDate());
                                    cgbx.setOldkcID(zsbx.getKechengID());
                                    cgbx.setOldprice(zsbx.getKechengprice());
                                    cgbx.setOldqiandanID(qiandan.getId());
                                    cgbx.setOldrkeshi(zsbx.getRemainkeshi());
                                    cgbx.setOldstartDate(zsbx.getStartDate());
                                    cgbx.setOldStuID(qiandan.getStuID());
                                    cgbx.setOldzongjia(zsbx.getZongjia());
                                    cgbx.setQiyeID(qiyeID);
                                    iPxbxkcchangetableService.save(cgbx);
                                    Pxkechengtable kechengTemp = iPxkechengtableService.getById(sks.getKechengID());
                                    String buxiStyleNameTemp =
                                            iPxbuxistyletableService.getById(kechengTemp.getBuxiStyleID()).getBuxiStyleName();
                                    if (buxiStyleNameTemp == "一对一") {
                                        Long classIDTemp = 0L;
                                        String subjectNameTemp =
                                                iPxsubjecttableService.getById(kechengTemp.getSubjectID()).getSubjectName();
                                        //建班插班
                                        boolean ii = false;
                                        while (ii == false) {
                                            Random rd = new Random();
                                            int max = 999;
                                            int min = 100;
                                            int sjs = rd.nextInt(max) % (max - min + 1) + min;
                                            String className =
                                                    "(赠送)" + pxstutable.getStuName() + "_" + subjectNameTemp + "_一对一" + sjs;
                                            ;
                                            List<Pxclasstable> pdclass =
                                                    iPxclasstableService.GetClassByClassName(className);
                                            if (pdclass.size() == 0) {
                                                Pxclasstable classtable = new Pxclasstable();
                                                classtable.setClassName(className);
                                                classtable.setCampusID(pxstutable.getCampusID());
                                                classtable.setAddStaffID(staffID);
                                                classtable.setAddTime(new Date());
                                                classtable.setIsShow(1);
                                                classtable.setIs1v1Class(1);
                                                classtable.setQiyeID(qiyeID);
                                                classtable.setIsdelete(false);
                                                iPxclasstableService.save(classtable);
                                                classIDTemp = classtable.getId();
                                                ii = true;
                                            }
                                        }
                                        //如果是一对一，要插班
                                        Pxstuclasstable stucla = new Pxstuclasstable();
                                        stucla.setBuxiID(zsbx.getId());
                                        stucla.setClassID(classIDTemp);
                                        stucla.setQiyeID(qiyeID);
                                        iPxstuclasstableService.save(stucla);
                                    }
                                } else {
                                    //走到这个分支，说明：原来的签单没有赠送课时，现在也没有课时赠送，则什么也不做
                                }
                            }
                            //endregion

                        } else {
                            //修改的该课程,单价变了,那应该把原来的那个价的补习课程表里的数据减课时或删除掉，然后增加在新的单价的补习程表记录里添加课时或增加记录
                            //先看看补习课程表里原单价的补习课程记录课时有没有变化
                            if (bxKcOld.getRemainkeshi().compareTo(qdsubOld.getBuykeshiNum()) == 0) {
                                //补习课程表里的剩余课时数和签单课程表里的购买课时数一样
                                //先看看新价格的补习课程记录表，是否已经有记录了;如果原本就有记录，就直接把课时加上去就可以了。如果buxikechengTable
                                // 里没有新价格的记录，则可以把该记录直接更换成新单价的记录
                                Pxbuxikechengtable newpricebx =
                                        iPxbuxikechengtableService.GetBuyBuxikecheng(kehaoqiandanSubject.getStuID(),
                                                qdsubOld.getKechengID(), buyKechengVo.getDJ(), qiyeID);
                                if (newpricebx != null) {
                                    //原本就有记录，就直接在新价格的buxikechengTable里的剩余课时把购买课时加上去就可以了。并且把原价的补习课程表记录删除掉或课时扣减掉
                                    newpricebx.setRemainkeshi(newpricebx.getRemainkeshi().add(buyKechengVo.getKS()));
                                    newpricebx.setKeshiNum(newpricebx.getKeshiNum().add(buyKechengVo.getKS()));
                                    newpricebx.setZongjia(newpricebx.getZongjia().add(buyKechengVo.getZJ()));
                                    //删除原补习课程表里的对应记录
                                    iPxbuxikechengtableService.removeById(bxKcOld.getId());
                                } else {
                                    //buxikechengTable里没有新价格的记录，则可以把该记录直接更换成新单价的记录
                                    bxKcOld.setOriginalprice(buyKechengVo.getYDJ());
                                    bxKcOld.setKechengprice(buyKechengVo.getDJ());
                                    bxkcOldchange.setNewprice(buyKechengVo.getDJ());
                                    bxKcOld.setKeshiNum(bxKcOld.getKeshiNum().subtract(yuankeshi).add(buyKechengVo.getKS()));
                                    bxKcOld.setRemainkeshi(bxKcOld.getRemainkeshi().subtract(qdsubOld.getBuykeshiNum()).add(buyKechengVo.getKS()));
                                    bxkcOldchange.setNewrkeshi(bxKcOld.getRemainkeshi().subtract(qdsubOld.getBuykeshiNum()).add(buyKechengVo.getKS()));
                                    bxKcOld.setZongjia(bxKcOld.getZongjia().subtract(yuanzongjia).add(buyKechengVo.getZJ()));
                                    bxkcOldchange.setNewzongjia(bxKcOld.getZongjia());
                                    bxKcOld.setStartDate(ft.parse(buyKechengVo.getStartDate()));
                                    bxkcOldchange.setNewstartDate(ft.parse(buyKechengVo.getStartDate()));
                                    bxKcOld.setEndDate(ft.parse(buyKechengVo.getEndDate()));
                                    bxkcOldchange.setNewendDate(ft.parse(buyKechengVo.getEndDate()));
                                    //bxKcOld.isShow = bx_isShow;
                                    bxKcOld.setJifeiStyleID(buyKechengVo.getJifeistyle());
                                }
                            } else {
                                //如果剩余课时不同，则把课时和学费扣除，然后在补习课程表里，看看有没有新价格的补习课程记录，如果有，就在该记录上增加课时，如果没有，就新增一条记录；
                                bxKcOld.setKeshiNum(bxKcOld.getKeshiNum().subtract(qdsubOld.getBuykeshiNum()));
                                bxKcOld.setRemainkeshi(bxKcOld.getRemainkeshi().subtract(qdsubOld.getBuykeshiNum()));
                                bxkcOldchange.setNewrkeshi(bxKcOld.getRemainkeshi().subtract(qdsubOld.getBuykeshiNum()));
                                bxKcOld.setZongjia(bxKcOld.getZongjia().subtract(yuanzongjia));
                                bxkcOldchange.setNewzongjia(bxKcOld.getZongjia().subtract(yuanzongjia));

                                //在补习课程表里，看看有没有新价格的补习课程记录，如果有，就在该记录上增加课时，如果没有，就新增一条记录
                                Pxbuxikechengtable bxKcOld2 =
                                        iPxbuxikechengtableService.GetBuyBuxikecheng(kehaoqiandanSubject.getStuID(),
                                                kehaoqiandanSubject.getKechengID(), buyKechengVo.getDJ(), qiyeID);
                                if (bxKcOld2 != null) {
                                    Pxbxkcchangetable bxkcold2change = new Pxbxkcchangetable();
                                    bxkcold2change.setAddDate(new Date());
                                    bxkcold2change.setAddStaffID(staffID);
                                    bxkcold2change.setOldbxkcID(bxKcOld2.getId());
                                    bxkcold2change.setOldbxkcName(iPxkechengtableService.getById(bxKcOld2.getKechengID()).getKechengName());
                                    bxkcold2change.setOldendDate(bxKcOld2.getEndDate());
                                    bxkcold2change.setOldkcID(bxKcOld2.getKechengID());
                                    bxkcold2change.setOldprice(bxKcOld2.getKechengprice());
                                    bxkcold2change.setOldqiandanID(qiandan.getId());
                                    bxkcold2change.setOldrkeshi(bxKcOld2.getRemainkeshi());
                                    bxkcold2change.setOldstartDate(bxKcOld2.getStartDate());
                                    bxkcold2change.setOldStuID(qiandan.getStuID());
                                    bxkcold2change.setOldzongjia(bxKcOld2.getZongjia());
                                    bxkcold2change.setNewbxkcID(bxKcOld2.getId());
                                    bxkcold2change.setNewbxkcName(iPxkechengtableService.getById(bxKcOld2.getKechengID()).getKechengName());
                                    bxkcold2change.setNewkcID(bxKcOld2.getKechengID());
                                    bxkcold2change.setNewprice(bxKcOld2.getKechengprice());
                                    bxkcold2change.setNewqiandanID(qiandan.getId());

                                    bxKcOld2.setKeshiNum(bxKcOld2.getRemainkeshi().add(buyKechengVo.getKS()));
                                    bxKcOld2.setRemainkeshi(bxKcOld2.getRemainkeshi().add(buyKechengVo.getKS()));
                                    bxkcold2change.setNewrkeshi(bxKcOld2.getRemainkeshi());
                                    bxKcOld2.setZongjia(bxKcOld2.getZongjia().add(buyKechengVo.getZJ()));
                                    bxkcold2change.setNewzongjia(bxKcOld2.getZongjia());
                                    bxKcOld2.setStartDate(ft.parse(buyKechengVo.getStartDate()));
                                    bxkcold2change.setNewstartDate(ft.parse(buyKechengVo.getStartDate()));
                                    bxKcOld2.setEndDate(ft.parse(buyKechengVo.getEndDate()));
                                    bxkcold2change.setNewendDate(bxKcOld2.getEndDate());
                                    bxKcOld2.setJifeiStyleID(buyKechengVo.getJifeistyle());
                                } else {
                                    //新价格的补习课程记录没有，新增一条记录
                                    Pxbuxikechengtable bx2 = new Pxbuxikechengtable();
                                    bx2.setStuID(qiandan.getStuID());
                                    bx2.setKechengID(kehaoqiandanSubject.getKechengID());
                                    bx2.setKechengprice(buyKechengVo.getDJ());
                                    bx2.setOriginalprice(buyKechengVo.getYDJ());
                                    bx2.setRemainkeshi(buyKechengVo.getKS());
                                    bx2.setKeshiNum(buyKechengVo.getKS());
                                    bx2.setZongjia(buyKechengVo.getZJ());
                                    bx2.setStartDate(ft.parse(buyKechengVo.getStartDate()));
                                    bx2.setEndDate(ft.parse(buyKechengVo.getEndDate()));
                                    bx2.setBuykeshiDateTime(kehaoqiandanSubject.getQiandandate());
                                    bx2.setIsShow(1);
                                    bx2.setJifeiStyleID(iPxkechengtableService.getById(kehaoqiandanSubject.getKechengID()).getJifeiStyleID());
                                    bx2.setQianDanInfoID(qiandan.getId());
                                    bx2.setQianDanSubjectID(buyKechengVo.getQdsID());
                                    bx2.setQiyeID(qiyeID);
                                    iPxbuxikechengtableService.save(bx2);
                                    Pxbxkcchangetable bx2change = new Pxbxkcchangetable();
                                    bx2change.setAddDate(new Date());
                                    bx2change.setAddStaffID(staffID);
                                    bx2change.setOldbxkcID(bx2.getId());
                                    bx2change.setOldbxkcName(iPxkechengtableService.getById(bx2.getKechengID()).getKechengName());
                                    bx2change.setOldendDate(bx2.getEndDate());
                                    bx2change.setOldkcID(bx2.getKechengID());
                                    bx2change.setOldprice(bx2.getKechengprice());
                                    bx2change.setOldqiandanID(qiandan.getId());
                                    bx2change.setOldrkeshi(bx2.getRemainkeshi());
                                    bx2change.setOldstartDate(bx2.getStartDate());
                                    bx2change.setOldStuID(qiandan.getStuID());
                                    bx2change.setOldzongjia(bx2.getZongjia());
                                    bx2change.setType(3);
                                    bx2change.setQiyeID(qiyeID);
                                    iPxbxkcchangetableService.save(bx2change);
                                    Pxkechengtable kechengTemp = iPxkechengtableService.getById(bx2.getKechengID());
                                    String buxiStyleNameTemp =
                                            iPxbuxistyletableService.getById(kechengTemp.getBuxiStyleID()).getBuxiStyleName();
                                    if (buxiStyleNameTemp == "一对一") {
                                        Long classIDTemp = 0L;
                                        String subjectNameTemp =
                                                iPxsubjecttableService.getById(kechengTemp.getSubjectID()).getSubjectName();
                                        //建班插班
                                        boolean ii = false;
                                        while (ii == false) {
                                            Random rd = new Random();
                                            int max = 999;
                                            int min = 100;
                                            int sjs = rd.nextInt(max) % (max - min + 1) + min;
                                            String className =
                                                    "(赠送)" + pxstutable.getStuName() + "_" + subjectNameTemp + "_一对一" + sjs;
                                            List<Pxclasstable> pdclass =
                                                    iPxclasstableService.GetClassByClassName(className);
                                            if (pdclass.size() == 0) {
                                                Pxclasstable classtable = new Pxclasstable();
                                                classtable.setClassName(className);
                                                classtable.setCampusID(pxstutable.getCampusID());
                                                classtable.setAddStaffID(staffID);
                                                classtable.setAddTime(new Date());
                                                classtable.setIsShow(0);
                                                classtable.setIs1v1Class(1);
                                                classtable.setQiyeID(qiyeID);
                                                classtable.setIsdelete(false);
                                                iPxclasstableService.save(classtable);
                                                classIDTemp = classtable.getId();
                                                ii = true;
                                            }
                                        }
                                        //如果是一对一，要插班
                                        Pxstuclasstable stucla = new Pxstuclasstable();
                                        stucla.setBuxiID(bx2.getId());
                                        stucla.setClassID(classIDTemp);
                                        stucla.setQiyeID(qiyeID);
                                        iPxstuclasstableService.save(stucla);
                                    }
                                }
                            }
                            //region 处理原签单课程的赠送问题——单价改了！

                            //首先看原单价的课程有没有赠送
                            Pxkeshizengsongtable kczsOld =
                                    iPxkeshizengsongtableService.GetZongSongInfo(qiandan.getId(),
                                            kehaoqiandanSubject.getKechengID(), yuankechengprice);
                            if (kczsOld != null) {
                                //走到这里，说明原单价的课程有赠送
                                //再看看新单价的课程有没有赠送[原来有赠送]
                                if (buyKechengVo.getZKS().compareTo(new BigDecimal(0)) != 0) {
                                    //原来有赠送课时，现在也有赠送课时
                                    Pxbuxikechengtable zsbxkc =
                                            iPxbuxikechengtableService.GetBuxikecheng(qiandan.getStuID(),
                                                    kehaoqiandanSubject.getKechengID(), kczsOld.getKechengPrice(),
                                                    qiyeID);
                                    if (zsbxkc != null) {
                                        if (zsbxkc.getRemainkeshi() == kczsOld.getKeshiShu()) {
                                            //如果补习课程表里的剩余课时和赠送课时表里的课时数一样，看看新价格的赠送记录是否有，如果有，直接把赠送课时加上去。如果没有，就直接在原记录上修改
                                            Pxbuxikechengtable newzsbxkc =
                                                    iPxbuxikechengtableService.GetBuxikecheng(qiandan.getStuID(),
                                                            kehaoqiandanSubject.getKechengID(),
                                                            kczsOld.getKechengPrice(), qiyeID);
                                            if (newzsbxkc != null) {
                                                //新价格的赠送记录本来就有，直接把赠送课时加上去；还要把原来的赠送记录删除掉；
                                                newzsbxkc.setRemainkeshi(buyKechengVo.getZKS());
                                                newzsbxkc.setKeshiNum(newzsbxkc.getKeshiNum().add(buyKechengVo.getZKS()));
                                                //删除原来buxikechengTable里的赠送记录
                                                iPxbuxikechengtableService.removeById(zsbxkc.getId());
                                            } else {
                                                //新价格的赠送记录没有，就直接在原记录上修改
                                                zsbxkc.setOriginalprice(buyKechengVo.getYDJ());
                                                zsbxkc.setKechengprice(buyKechengVo.getDJ());
                                                zsbxkc.setRemainkeshi(buyKechengVo.getZKS());
                                                zsbxkc.setKeshiNum(buyKechengVo.getZKS());
                                            }
                                        } else {
                                            //如果补习课程表里的剩余课时和赠送课时表里的课时数不一样，则在补习课程表里的剩余课时扣减原来赠送的课时；并在buxikechengTable
                                            // 里面新增一条新价格的赠送记录
                                            zsbxkc.setRemainkeshi(zsbxkc.getRemainkeshi().subtract(kczsOld.getKeshiShu()));
                                            zsbxkc.setKeshiNum(zsbxkc.getKeshiNum().subtract(kczsOld.getKeshiShu()));

                                            Pxbuxikechengtable zsbxkc2 =
                                                    iPxbuxikechengtableService.GetBuxikecheng(qiandan.getStuID(),
                                                            kehaoqiandanSubject.getKechengID(), buyKechengVo.getDJ(),
                                                            qiyeID);
                                            if (zsbxkc2 != null) {
                                                //如果新价格的赠送记录本来就有，就直接在上面增加赠送课时就可以了
                                                zsbxkc2.setRemainkeshi(zsbxkc2.getRemainkeshi().add(buyKechengVo.getZKS()));
                                                zsbxkc2.setKeshiNum(zsbxkc2.getKeshiNum().add(buyKechengVo.getZKS()));
                                            } else {
                                                //如果新价格的赠送记录没有，就只能在buxikechengTable里面新增一条新价格的赠送记录
                                                Pxbuxikechengtable zsbx2 = new Pxbuxikechengtable();
                                                zsbx2.setStuID(qiandan.getStuID());
                                                zsbx2.setKechengID(kehaoqiandanSubject.getKechengID());
                                                zsbx2.setKechengprice(buyKechengVo.getDJ());
                                                zsbx2.setOriginalprice(buyKechengVo.getYDJ());
                                                zsbx2.setRemainkeshi(buyKechengVo.getZKS());
                                                zsbx2.setKeshiNum(buyKechengVo.getZKS());
                                                zsbx2.setZongjia(new BigDecimal(0));
                                                zsbx2.setStartDate(kehaoqiandanSubject.getStartDate());
                                                zsbx2.setEndDate(kehaoqiandanSubject.getEndDate());
                                                zsbx2.setBuykeshiDateTime(kehaoqiandanSubject.getQiandandate());
                                                zsbx2.setIsShow(0);
                                                zsbx2.setJifeiStyleID(iPxkechengtableService.getById(kehaoqiandanSubject.getKechengID()).getJifeiStyleID());
                                                zsbx2.setType(2);
                                                zsbx2.setQianDanInfoID(qiandan.getId());
                                                zsbx2.setQianDanSubjectID(buyKechengVo.getQdsID());
                                                zsbx2.setQiyeID(qiyeID);
                                                iPxbuxikechengtableService.save(zsbx2);
                                                Pxbxkcchangetable zsbx2change = new Pxbxkcchangetable();
                                                zsbx2change.setAddDate(new Date());
                                                zsbx2change.setAddStaffID(staffID);
                                                zsbx2change.setOldbxkcID(zsbx2.getId());
                                                zsbx2change.setOldbxkcName(iPxkechengtableService.getById(zsbx2.getKechengID()).getKechengName());
                                                zsbx2change.setOldendDate(zsbx2.getEndDate());
                                                zsbx2change.setOldkcID(zsbx2.getKechengID());
                                                zsbx2change.setOldprice(zsbx2.getOriginalprice());
                                                zsbx2change.setOldqiandanID(qiandan.getId());
                                                zsbx2change.setOldrkeshi(zsbx2.getRemainkeshi());
                                                zsbx2change.setOldstartDate(zsbx2.getStartDate());
                                                zsbx2change.setOldStuID(qiandan.getStuID());
                                                zsbx2change.setOldzongjia(zsbx2.getZongjia());
                                                zsbx2change.setType(9);
                                                zsbx2change.setQiyeID(qiyeID);
                                                iPxbxkcchangetableService.save(zsbx2change);
                                                Pxkechengtable kechengTemp =
                                                        iPxkechengtableService.getById(zsbx2.getKechengID());
                                                String buxiStyleNameTemp =
                                                        iPxbuxistyletableService.getById(kechengTemp.getBuxiStyleID()).getBuxiStyleName();
                                                if (buxiStyleNameTemp == "一对一") {
                                                    Long classIDTemp = 0L;
                                                    String subjectNameTemp =
                                                            iPxsubjecttableService.getById(kechengTemp.getSubjectID()).getSubjectName();
                                                    //建班插班
                                                    boolean ii = false;
                                                    while (ii == false) {
                                                        Random rd = new Random();
                                                        int max = 999;
                                                        int min = 100;
                                                        int sjs = rd.nextInt(max) % (max - min + 1) + min;
                                                        String className =
                                                                "(赠送)" + pxstutable.getStuName() + "_" + subjectNameTemp +
                                                                        "_一对一" + sjs;
                                                        List<Pxclasstable> pdclass =
                                                                iPxclasstableService.GetClassByClassName(className);
                                                        if (pdclass.size() == 0) {
                                                            Pxclasstable classtable = new Pxclasstable();
                                                            classtable.setClassName(className);
                                                            classtable.setCampusID(pxstutable.getCampusID());
                                                            classtable.setAddStaffID(staffID);
                                                            classtable.setAddTime(new Date());
                                                            classtable.setIsShow(1);
                                                            classtable.setIs1v1Class(1);
                                                            classtable.setQiyeID(qiyeID);
                                                            classtable.setIsdelete(false);
                                                            iPxclasstableService.save(classtable);
                                                            classIDTemp = classtable.getId();
                                                            ii = true;
                                                        }
                                                    }
                                                    //如果是一对一，要插班
                                                    Pxstuclasstable stucla = new Pxstuclasstable();
                                                    stucla.setBuxiID(zsbx2.getId());
                                                    stucla.setClassID(classIDTemp);
                                                    stucla.setQiyeID(qiyeID);
                                                    iPxstuclasstableService.save(stucla);
                                                }
                                            }
                                        }
                                    }
                                    //修改赠送课时表里的赠送单价，赠送课时
                                    kczsOld.setKechengPrice(buyKechengVo.getDJ());
                                    kczsOld.setKeshiShu(buyKechengVo.getZKS());
                                } else {
                                    //原来有赠送课时，现在没有赠送课时,则:把补习课程表里的赠送记录删除掉（或扣减赠送的课时数），还要把原来的赠送课时记录删除掉
                                    Pxbuxikechengtable zsbxkc =
                                            iPxbuxikechengtableService.GetBuxikecheng(qiandan.getStuID(),
                                                    kehaoqiandanSubject.getKechengID(), kczsOld.getKechengPrice(),
                                                    qiyeID);
                                    if (zsbxkc != null) {
                                        if (zsbxkc.getRemainkeshi().compareTo(kczsOld.getKeshiShu()) == 0) {
                                            Pxbxkcchangetable zsbxkcchange = new Pxbxkcchangetable();
                                            zsbxkcchange.setAddDate(new Date());
                                            zsbxkcchange.setAddStaffID(staffID);
                                            zsbxkcchange.setOldbxkcID(zsbxkc.getId());
                                            zsbxkcchange.setOldbxkcName(iPxkechengtableService.getById(zsbxkc.getKechengID()).getKechengName());
                                            zsbxkcchange.setOldendDate(zsbxkc.getEndDate());
                                            zsbxkcchange.setOldkcID(zsbxkc.getKechengID());
                                            zsbxkcchange.setOldprice(zsbxkc.getKechengprice());
                                            zsbxkcchange.setOldqiandanID(qiandan.getId());
                                            zsbxkcchange.setOldrkeshi(zsbxkc.getRemainkeshi());
                                            zsbxkcchange.setOldstartDate(zsbxkc.getStartDate());
                                            zsbxkcchange.setOldStuID(qiandan.getStuID());
                                            zsbxkcchange.setOldzongjia(zsbxkc.getZongjia());
                                            zsbxkcchange.setType(3);
                                            zsbxkcchange.setQiyeID(qiyeID);
                                            iPxbxkcchangetableService.save(zsbxkcchange);
                                            //把补习课程表里的赠送记录删除掉
                                            iPxbuxikechengtableService.removeById(zsbxkc.getId());
                                        } else {
                                            //减去赠送的课时数
                                            zsbxkc.setRemainkeshi(zsbxkc.getRemainkeshi().subtract(kczsOld.getKeshiShu()));
                                            zsbxkc.setKeshiNum(zsbxkc.getKeshiNum().subtract(kczsOld.getKeshiShu()));
                                        }
                                    }
                                    //把原来的赠送课时记录删除掉
                                    iPxkeshizengsongtableService.removeById(kczsOld.getId());
                                }
                            } else {
                                //走到这个分支，说明：原来的签单没有赠送课时
                                if (buyKechengVo.getZKS().compareTo(new BigDecimal(0)) != 0) {
                                    //走这个分支，说明：原来的签单没有赠送课时，但现在有赠送课时,则往赠送课时表里添加一条赠送记录
                                    Pxkeshizengsongtable sks = new Pxkeshizengsongtable();
                                    sks.setStuID(qiandan.getStuID());
                                    sks.setKechengID(kehaoqiandanSubject.getKechengID());
                                    sks.setKechengPrice(buyKechengVo.getDJ());
                                    sks.setKeshiShu(buyKechengVo.getZKS());
                                    sks.setCaozuoStaffId(qiandan.getQianDanStaffID());
                                    sks.setAddDate(new Date());
                                    sks.setSongYangyin("签单课时赠送");
                                    sks.setJifeiStyle(iPxkechengtableService.getById(kehaoqiandanSubject.getKechengID()).getJifeiStyleID());
                                    sks.setQiandanInfoID(qiandan.getId());
                                    sks.setQiyeID(qiyeID);
                                    iPxkeshizengsongtableService.save(sks);

                                    Pxbuxikechengtable zsbx = new Pxbuxikechengtable();
                                    zsbx.setStuID(qiandan.getStuID());
                                    zsbx.setKechengID(kehaoqiandanSubject.getKechengID());
                                    zsbx.setKechengprice(buyKechengVo.getDJ());
                                    zsbx.setOriginalprice(buyKechengVo.getYDJ());
                                    zsbx.setRemainkeshi(buyKechengVo.getZKS());
                                    zsbx.setKeshiNum(buyKechengVo.getZKS());
                                    zsbx.setZongjia(new BigDecimal(0));
                                    zsbx.setStartDate(kehaoqiandanSubject.getStartDate());
                                    zsbx.setEndDate(kehaoqiandanSubject.getEndDate());
                                    zsbx.setBuykeshiDateTime(kehaoqiandanSubject.getQiandandate());
                                    zsbx.setIsShow(0);
                                    zsbx.setJifeiStyleID(iPxkechengtableService.getById(kehaoqiandanSubject.getKechengID()).getJifeiStyleID());
                                    zsbx.setType(2);
                                    zsbx.setQianDanInfoID(qiandan.getId());
                                    zsbx.setQiyeID(qiyeID);
                                    zsbx.setQianDanSubjectID(buyKechengVo.getQdsID());
                                    iPxbuxikechengtableService.save(zsbx);
                                    Pxbxkcchangetable zsbxchange = new Pxbxkcchangetable();
                                    zsbxchange.setAddDate(new Date());
                                    zsbxchange.setAddStaffID(staffID);
                                    zsbxchange.setOldbxkcID(zsbx.getId());
                                    zsbxchange.setOldbxkcName(iPxkechengtableService.getById(zsbx.getKechengID()).getKechengName());
                                    zsbxchange.setOldendDate(zsbx.getEndDate());
                                    zsbxchange.setOldkcID(zsbx.getKechengID());
                                    zsbxchange.setOldprice(zsbx.getKechengprice());
                                    zsbxchange.setOldqiandanID(qiandan.getId());
                                    zsbxchange.setOldrkeshi(zsbx.getRemainkeshi());
                                    zsbxchange.setOldstartDate(zsbx.getStartDate());
                                    zsbxchange.setOldStuID(qiandan.getStuID());
                                    zsbxchange.setOldzongjia(zsbx.getZongjia());
                                    zsbxchange.setQiyeID(qiyeID);
                                    iPxbxkcchangetableService.save(zsbxchange);
                                    Pxkechengtable kechengTemp = iPxkechengtableService.getById(zsbx.getKechengID());
                                    String buxiStyleNameTemp =
                                            iPxbuxistyletableService.getById(kechengTemp.getBuxiStyleID()).getBuxiStyleName();
                                    if (buxiStyleNameTemp == "一对一") {
                                        Long classIDTemp = 0L;
                                        String subjectNameTemp =
                                                iPxsubjecttableService.getById(kechengTemp.getSubjectID()).getSubjectName();
                                        //建班插班
                                        boolean ii = false;
                                        while (ii == false) {
                                            Random rd = new Random();
                                            int max = 999;
                                            int min = 100;
                                            int sjs = rd.nextInt(max) % (max - min + 1) + min;
                                            String className =
                                                    "(赠送)" + pxstutable.getStuName() + "_" + subjectNameTemp + "_一对一" + sjs;
                                            List<Pxclasstable> pdclass =
                                                    iPxclasstableService.GetClassByClassName(className);
                                            if (pdclass.size() == 0) {
                                                Pxclasstable classtable = new Pxclasstable();
                                                classtable.setClassName(className);
                                                classtable.setCampusID(pxstutable.getCampusID());
                                                classtable.setAddStaffID(staffID);
                                                classtable.setAddTime(new Date());
                                                classtable.setIsShow(1);
                                                classtable.setIs1v1Class(1);
                                                classtable.setQiyeID(qiyeID);
                                                classtable.setIsdelete(false);
                                                iPxclasstableService.save(classtable);
                                                classIDTemp = classtable.getId();
                                                ii = true;
                                            }
                                        }
                                        //如果是一对一，要插班
                                        Pxstuclasstable stucla = new Pxstuclasstable();
                                        stucla.setBuxiID(zsbx.getId());
                                        stucla.setClassID(classIDTemp);
                                        stucla.setQiyeID(qiyeID);
                                        iPxstuclasstableService.save(stucla);
                                    }
                                } else {
                                    //走到这个分支，说明：原来的签单没有赠送课时，现在也没有课时赠送，则什么也不做
                                }
                            }
                            //endregion

                        }
                        bxkcOldchange.setType(3);
                        bxkcOldchange.setQiyeID(qiyeID);
                        iPxbxkcchangetableService.save(bxkcOldchange);
                        BigDecimal oldxuefei = qdsubOld.getZongjia();
                        qdsubOld.setKechengprice(buyKechengVo.getDJ());
                        qdsubOld.setOriginalprice(buyKechengVo.getYDJ());
                        qdsubOld.setBuykeshiNum(buyKechengVo.getKS());
                        qdsubOld.setZongjia(buyKechengVo.getZJ());
                        qdsubOld.setDiscount(buyKechengVo.getZK());
                        qdsubOld.setStartDate(ft.parse(buyKechengVo.getStartDate()));
                        qdsubOld.setEndDate(ft.parse(buyKechengVo.getEndDate()));
                        qdsubOld.setQiyeID(qiyeID);
                        iPxqiandansubjecttableService.updateById(qdsubOld);
                    }
                }

            }

            //oldKCzongjia 上次的课程总价
            //thisKCzongjia 本次的课程总价
            //dingjin 本次的定金
            //oldDingjin 上次的定金
            if (!isOldDingjin && !isNewDingjin) {
                //两次都没有使用了定金
                pxstutable.setRemainXuefei(pxstutable.getRemainXuefei().add(thisKCzongjia.subtract(oldKCzongjia)));
            } else if (!isOldDingjin && isNewDingjin) {
                //上次没有使用，这次有用
                pxstutable.setRemainXuefei(pxstutable.getRemainXuefei().add(form.getDingjin().subtract(oldKCzongjia)));
            } else if (isOldDingjin && !isNewDingjin) {
                //上次有使用，这次没有使用了
                pxstutable.setRemainXuefei(pxstutable.getRemainXuefei().add(thisKCzongjia.subtract(oldDingjin)));
            } else {
                //两次都使用了定金
                pxstutable.setRemainXuefei(pxstutable.getRemainXuefei().add(form.getDingjin().subtract(oldDingjin)));
            }
            iPxstutableService.updateById(pxstutable);
            logtext += "；修改前，学员剩余学费：" + oldRemainXuefei.toString() + "修改签单后：学员剩余学费：" + pxstutable.getRemainXuefei().toString();
        }
        //endregion

        //region 删除了杂费和物品费用
        if (StringUtils.isNotBlank(form.getRemoveZFstr())) {
            String delzf[] = form.getRemoveZFstr().split(",");
            for (String delzfID : delzf) {
                iPxqiandaninfo2tableService.removeById(delzfID);
            }
        }
        if (StringUtils.isNotBlank(form.getRemoveWPstr())) {
            String delwp[] = form.getRemoveWPstr().split(",");
            for (String delID : delwp) {
                Pxqiandansupplies pxqiandansupplies = iPxqiandansuppliesService.getById(delID);
                Pxteachingsuppliestable wp =
                        iPxteachingsuppliestableService.getById(pxqiandansupplies.getTeachingSuppliesID());
                Pxteachingsuppliesouttable outjl = new Pxteachingsuppliesouttable();
                outjl.setLuruStaffId(staffID);
                outjl.setOutDate(new Date());
                outjl.setOutNum(pxqiandansupplies.getBuySum());
                outjl.setOutnumBefore(wp.getStockNum());
                outjl.setOutReason("学生购买");
                outjl.setOutStaffId(staffID);
                outjl.setSuppliesId(wp.getId());
                outjl.setType(1);
                outjl.setQiyeID(qiyeID);
                iPxteachingsuppliesouttableService.save(outjl);
                wp.setStockNum(wp.getStockNum().add(pxqiandansupplies.getBuySum()));
                iPxteachingsuppliestableService.updateById(wp);
            }
            //endregion
        }
        //保存支付金额及方式&添加流水
        List<Pxqiandanpaymoney> payMoneyStyle = JSON.parseArray(form.getPaymoneystyle(), Pxqiandanpaymoney.class);
        List<Pxqiandanpaymoney> oldpst = iPxqiandanpaymoneyService.getQiandanPayMoneyList(qiandan.getId());
        for (Pxqiandanpaymoney pxqiandanpaymoney : oldpst) {
            if (pxqiandanpaymoney.getPaymoneyStyleID() == 3L) {
                pxstutable.setRemainChongzhi(pxstutable.getRemainChongzhi().add(pxqiandanpaymoney.getPayMoney()));
                iPxstutableService.updateById(pxstutable);
            }
        }
        QueryWrapper queryWrapper2 = new QueryWrapper();
        queryWrapper2.eq("qiandanID", qiandan.getId());
        iPxqiandanpaymoneyService.remove(queryWrapper2);
        iPxliushuizhangtableService.DeleteLiushuiByQiandanID(qiandan.getId(), qiyeID);
        for (Pxqiandanpaymoney pxqiandanpaymoney : payMoneyStyle) {
            Pxqiandanpaymoney qdpm = new Pxqiandanpaymoney();
            qdpm.setPayMoney(pxqiandanpaymoney.getPayMoney());
            qdpm.setPaymoneyStyleID(pxqiandanpaymoney.getPaymoneyStyleID());
            qdpm.setQiandanID(qiandan.getId());
            qdpm.setQiyeID(qiyeID);
            qdpm.setQianDanDate(ft.parse(form.getQiandandate()));
            iPxqiandanpaymoneyService.save(qdpm);

            if (pxqiandanpaymoney.getPaymoneyStyleID() == -2)//支付方式中余额支付，先判断余额是否够支付，如果够，扣减余额，否则返回
            {
                if (pxstutable.getRemainChongzhi().compareTo(pxqiandanpaymoney.getPayMoney()) == -1) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    ajaxJson.setCode("N");
                    ajaxJson.setMsg("当前学生余额不足支付，请更换支付方式或金额后重试！");
                    return ajaxJson;
                } else {
                    pxstutable.setRemainChongzhi(pxstutable.getRemainChongzhi().subtract(pxqiandanpaymoney.getPayMoney()));
                    Pxchongzhipaytable jiaoyitab = new Pxchongzhipaytable();
                    jiaoyitab.setStuID(pxstutable.getId());
                    jiaoyitab.setChongzhiPayMoney(pxqiandanpaymoney.getPayMoney());
                    jiaoyitab.setBeizhu("使用余额支付签单");
                    jiaoyitab.setQiandanID(qiandan.getId());
                    jiaoyitab.setAddStaffID(staffID);
                    jiaoyitab.setAddTime(new Date());
                    jiaoyitab.setQiyeID(qiyeID);
                    iPxchongzhipaytableService.save(jiaoyitab);
                    iPxstutableService.updateById(pxstutable);
                }
            } else {
                Pxliushuizhangtable lsz = iPxliushuizhangtableService.GetLiushuiByQdidAndPst(qiandan.getId(),
                        pxqiandanpaymoney.getPaymoneyStyleID());
                if (lsz == null) {
                    Pxliushuizhangtable liushui = new Pxliushuizhangtable();
                    //liushui.setId(Long.parseLong(DateUtil.formatDate11(new Date())));
                    liushui.setLiushuiDateTime(qiandan.getQiandandate());
                    liushui.setCampusID(pxstutable.getCampusID());
                    liushui.setPayMoneyStyle(pxqiandanpaymoney.getPaymoneyStyleID());
                    liushui.setZhichuMoney(new BigDecimal(0));
                    liushui.setShouzhiStyleID(1L);
                    liushui.setJinbanRen(staffID);
                    liushui.setStuID(qiandan.getStuID());
                    liushui.setQiandanID(qiandan.getId());
                    liushui.setLuruTime(new Date());
                    liushui.setQiyeID(qiyeID);
                    String zyText = "";
                    if (StringUtils.isNotBlank(form.getBeizhu())) {
                        zyText = "说明：" + form.getBeizhu();
                    }
                    liushui.setShouruMoney(pxqiandanpaymoney.getPayMoney());
                    String campusNameStr = iPxcampustableService.getById(pxstutable.getCampusID()).getCampusName();
                    liushui.setLiushuiZaiyao(campusNameStr + "校区" + pxstutable.getStuName() + "同学，定金交费！--" + zyText);
                    liushui.setAddStaffID(staffID);
                    iPxliushuizhangtableService.save(liushui);

                    logtext += ".添加签单," + loginUser.getStaffName() + "添加" + qdtype + "信息,流水号: " + liushui.getId();

                } else {
                    Pxliushuizhangtable liushui = new Pxliushuizhangtable();
                    liushui.setId(Long.parseLong(DateUtil.formatDate11(new Date())));
                    liushui.setLiushuiDateTime(qiandan.getQiandandate());
                    liushui.setCampusID(pxstutable.getCampusID());
                    liushui.setPayMoneyStyle(pxqiandanpaymoney.getPaymoneyStyleID());
                    liushui.setZhichuMoney(new BigDecimal(0));
                    liushui.setShouzhiStyleID(1L);
                    liushui.setJinbanRen(staffID);
                    liushui.setStuID(qiandan.getStuID());
                    liushui.setQiandanID(qiandan.getId());
                    liushui.setLuruTime(new Date());
                    liushui.setQiyeID(qiyeID);
                    String zyText = "";
                    if (StringUtils.isNotBlank(form.getBeizhu())) {
                        zyText = "说明：" + form.getBeizhu();
                    }
                    liushui.setShouruMoney(pxqiandanpaymoney.getPayMoney());
                    String campusNameStr = iPxcampustableService.getById(pxstutable.getCampusID()).getCampusName();
                    liushui.setLiushuiZaiyao(campusNameStr + "校区" + pxstutable.getStuName() + "同学，全款交费！--" + zyText);
                    iPxliushuizhangtableService.save(liushui);
                    logtext += ".修改签单信息," + loginUser.getStaffName() + "添加" + qdtype + "信息,流水号: " + liushui.getId();
                }
            }
        }
        ajaxJson.setSuccess(true);
        savePxLog.savepxlog(logtext, "xwcloud-zsbm/zsbm/BaoMingJiaoFei" +
                "/UpdateStuQianDan", loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());
        return ajaxJson;

    }

    /**
     * 查询签单详情
     *
     * @param qiandanID
     * @return
     */
    @RequestMapping(value = "/GetQiandanInfo", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询签单详情")
    public AjaxJson GetQiandanInfo(long qiandanID) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxqiandaninfotableService.GetQiandanInfoLits(qiandanID));
        return ajaxJson;
    }


    /**
     * 根据签单ID查询签单业绩人信息
     *
     * @param qiandanID
     * @return
     */
    @RequestMapping(value = "/GetQiandanStaffInfo", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "根据签单ID查询签单业绩人信息")
    public AjaxJson GetQiandanStaffInfo(long qiandanID) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxqiandanstafftableService.GetqiandanStaffList(qiandanID));
        return ajaxJson;
    }

    /**
     * 查询签单支付金额信息
     *
     * @param qiandanID
     * @return
     */
    @RequestMapping(value = "/GetQiandanPayMoneyList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询签单支付金额信息")
    public AjaxJson GetQiandanPayMoneyList(long qiandanID) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxqiandanpaymoneyService.getqiandanPayMoneyList(qiandanID));
        return ajaxJson;
    }

    /**
     * 查询签单购买课程信息
     *
     * @param qiandanID
     * @return
     */
    @RequestMapping(value = "/GetQiandanBuyKechengList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询签单购买课程信息")
    public AjaxJson GetQiandanBuyKechengList(long qiandanID) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxqiandansubjecttableService.GetQiandanKechengList(qiandanID));
        return ajaxJson;
    }

    /**
     * 查询签单对应购买物品信息
     *
     * @param qiandanID
     * @return
     */
    @RequestMapping(value = "/GetqiandanSuppliesList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询签单对应购买物品信息")
    public AjaxJson GetqiandanSuppliesList(long qiandanID) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxqiandansuppliesService.GetAllWupingList(qiandanID));
        return ajaxJson;
    }

    /**
     * 查询签单其他物品信息
     *
     * @param qiandanID
     * @return
     */
    @RequestMapping(value = "/GetQiandanOtherMoneyListbyqiandan", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询签单其他物品信息")
    public AjaxJson GetQiandanOtherMoneyListbyqiandan(long qiandanID) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxqiandansuppliesService.GetAllWupingList(qiandanID));
        return ajaxJson;
    }

    /**
     * 删除签单
     *
     * @param qiandanID
     * @return
     */
    @RequestMapping(value = "/DeleteQianDanAndStu", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation("删除签单")
    @Transactional(rollbackFor = Exception.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "qiandanID", value = "要删除签单ID", required = true)
    })
    public AjaxJson DeleteQianDanAndStu(String qiandanID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();
        String logtext = "";

        Pxqiandaninfotable qiandan = iPxqiandaninfotableService.getById(qiandanID);
        if (qiandan == null) {
            ajaxJson.setMsg("没有找到这条签单信息！");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Pxsysparamdefaulttable pxsysparamdefaulttable = iPxsysparamdefaulttableService.GetSysParamById(43L);
        Date nowDate = new Date();
        Date qiandanDate = qiandan.getQiandandate();
        int days = (int) ((nowDate.getTime() - qiandanDate.getTime()) / (1000 * 3600 * 24));
        if (days > Integer.valueOf(pxsysparamdefaulttable.getDefaultValue())) {
            ajaxJson.setMsg("办理时间已超过" + days + "天，不能删除签单！");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        List<Pxkeshizhuansongtable> haeZS = iPxkeshizhuansongtableService.GetStuzhuansongInfo(qiandan.getStuID(),
                qiandan.getStuID());
        if (haeZS.size() > 0) {
            ajaxJson.setMsg("该学员存在课时转送，不能删除签单！");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        Pxstutable pxstutable = iPxstutableService.getById(qiandan.getStuID());
        int qiandanCount = iPxqiandaninfotableService.GetStuQianDanList(qiandan.getStuID(), qiyeID).size();
        int qdCountNotuifei = iPxqiandaninfotableService.GetXqOrXfList(qiandan.getStuID(), qiyeID).size();

        logtext += "学号：" + pxstutable.getId() + ",姓名：" + pxstutable.getStuName();


        if (pxstutable.getBuxiStateID() == 5 || qiandanCount == 1 || qdCountNotuifei == 1) {
            //删除签单为当前学生的唯一签单
            //则查keshiStuTable里，如果他有课时记录，就不能删除，给用户提示“该学生已经有课时记录，不能删除”
            int keshi = iPxkeshistutableService.getAllStuKeshiByStuID(pxstutable.getId()).size();
            if (keshi > 0) {
                ajaxJson.setMsg("该学员已有课耗记录，删除课耗记录后才可删除学员");
                ajaxJson.setCode("N");
                return ajaxJson;
            }
            //如果keshiStuTable里，他没有课时记录，那么：
            else {
                logtext += ",删除签单信息，签单唯一。";
                List<Pxbuxikechengtable> buxi = iPxbuxikechengtableService.GetBuxikchengByStuID(pxstutable.getId(),
                        qiyeID);
                if (buxi.size() != 0) {
                    //学生如果有班，则遍历班级；没有班，则不遍历
                    //学生如果是一对一班级，删除该学生对应的一对一班级！
                    for (Pxbuxikechengtable pxbuxikechengtable : buxi) {
                        Long buxiStyleID =
                                iPxkechengtableService.getById(pxbuxikechengtable.getKechengID()).getBuxiStyleID();
                        String bxstyleName = iPxbuxistyletableService.getById(buxiStyleID).getBuxiStyleName();
                        if (bxstyleName == "一对一") {
                            //删除一对一班级
                            Pxstuclasstable stuclass =
                                    iPxstuclasstableService.GetStuclassBybuxiID(pxbuxikechengtable.getId());
                            if (stuclass != null) {
                                Pxclasstable yiduiyiclassTable = iPxclasstableService.getById(stuclass.getClassID());
                                if (yiduiyiclassTable != null) {
                                    Pxclasstable oneCla = iPxclasstableService.getById(stuclass.getClassID());
                                    if (oneCla != null) {
                                        //删除班级之前，把这个班的排课全删除掉
                                        List<Pxpaiketable> claPaike =
                                                iPxpaiketableService.GetPaikebyClassID(stuclass.getClassID());
                                        if (claPaike.size() > 0) {
                                            for (Pxpaiketable pxpaiketable : claPaike) {
                                                iPxpaiketeachertableService.DeletePaikeTeacherByPaikeID(pxpaiketable.getId());
                                                //9.1删选课表
                                                iPxxuanketableService.deleteXuanKeTable(pxpaiketable.getId());
                                                iPxpaiketableService.removeById(pxpaiketable.getId());
                                            }
                                        }
                                        iPxclasstableService.removeById(oneCla.getId());
                                    }
                                    iPxclasstableService.removeById(yiduiyiclassTable.getId());
                                }
                            }
                        }
                        //不管是不是一对一，都退班
                        List<Pxstuclasstable> stucla =
                                iPxstuclasstableService.GetStuclassBybuxiIDlist(pxbuxikechengtable.getId());
                        if (stucla.size() > 0) {
                            for (Pxstuclasstable pxstuclasstable : stucla) {
                                //有排课的话，删除排课
                                List<Pxpaiketable> claPaike =
                                        iPxpaiketableService.GetAllPaikeByClassID(pxstuclasstable.getClassID());
                                if (claPaike.size() > 0) {
                                    for (Pxpaiketable pxpaiketable : claPaike) {
                                        //9.1删选课表
                                        List<Pxxuanketable> allxuanke =
                                                iPxxuanketableService.getAllXuankeByPaikeID(pxpaiketable.getId());
                                        List<Pxxuanketable> xuankeTab =
                                                iPxxuanketableService.GetAllXuankeBypkIDAndStuIDList(pxpaiketable.getId(),
                                                        pxstutable.getId());
                                        //考勤状态判断
                                        int ykqCount =
                                                iPxstukaoqingtableService.GetStuKaoqing(pxpaiketable.getHaveClassDate().toString(),
                                                 pxpaiketable.getStartLessonDateTime().toString(), pxpaiketable.getEndLessonDateTime().toString(),
                                                  pxpaiketable.getClassID(), pxpaiketable.getTeacherNames()).size();

                                        if (ykqCount >= allxuanke.size() - 1) {
                                            pxpaiketable.setDakaoqin(true);
                                        }
                                        if (xuankeTab.size() > 0) {
                                            iPxxuanketableService.remove(new QueryWrapper<Pxxuanketable>()
                                                    .eq("paikeID", pxpaiketable.getId())
                                                    .eq("stuID", pxstutable.getId())
                                                    .eq("qiyeID", loginUser.getQiyeID())
                                            );
                                        }
                                    }
                                }
                                iPxstuclasstableService.removeById(pxstuclasstable);
                            }

                        }
                    }
                    iPxbuxikechengtableService.deleteBuxikechengByStuID(pxstutable.getId(), qiyeID);
                    logtext += "删除学员补习课程";
                }
                //删除代金券
                List<Pxdaijinquantable> daijinqnuan =
                        iPxdaijinquantableService.GetDaijinquanListByStuID(pxstutable.getId());
                if (daijinqnuan.size() != 0) {
                    iPxdaijinquantableService.DeleteDaijinquanByStuID(pxstutable.getId());
                }

                //删除积分
                iPxjifentableService.deleteJiifenByStuID(pxstutable.getId(), qiyeID);

                //删除离校刷卡
                iPxqiandaoqiantuitableService.remove(
                        new QueryWrapper<Pxqiandaoqiantuitable>()
                                .eq("stuID", pxstutable.getId())
                                .eq("qiyeID", qiyeID)
                );

                //删除学员卡
                iPxstucardtableService.remove(
                        new QueryWrapper<Pxstucardtable>()
                                .eq("stuID", pxstutable.getId())
                                .eq("qiyeID", qiyeID));

                //删除赠送课时
                iPxkeshizengsongtableService.remove(
                        new QueryWrapper<Pxkeshizengsongtable>()
                                .eq("stuID", pxstutable.getId())
                                .eq("qiyeID", qiyeID));

                //删除考勤
                List<Pxstukaoqingtable> stukaoqing = iPxstukaoqingtableService.GetKaoqingByStuID(pxstutable.getId());
                if (stukaoqing.size() != 0) {
                    for (Pxstukaoqingtable pxstukaoqingtable : stukaoqing) {

                        iPxstukaoqingteachertableService.remove(new QueryWrapper<Pxstukaoqingteachertable>()
                                .eq("stukaoqingtabid", pxstukaoqingtable.getId())
                                .eq("qiyeID", qiyeID)
                        );
                    }
                    iPxstukaoqingtableService.remove(
                            new QueryWrapper<Pxstukaoqingtable>()
                                    .eq("stuID", pxstutable.getId())
                                    .eq("qiyeID", qiyeID)
                    );
                }

                //删除签单课程
                iPxqiandansubjecttableService.remove(
                        new QueryWrapper<Pxqiandansubjecttable>()
                                .eq("stuID", pxstutable.getId())
                                .eq("qiyeID", qiyeID));

                logtext += "，删除签单科目信息，";

                //删除签单
                List<Pxqiandaninfotable> qiandanlist =
                        iPxqiandaninfotableService.GetStuQianDanList(pxstutable.getId(), qiyeID);
                if (qiandanlist.size() != 0) {
                    for (Pxqiandaninfotable pxqiandaninfotable : qiandanlist) {
                        iPxqiandaninfo2tableService.remove(new QueryWrapper<Pxqiandaninfo2table>()
                                .eq("qianInfoTabID", pxqiandaninfotable.getId())
                                .eq("qiyeId", qiyeID)
                        );
                    }
                    iPxqiandaninfotableService.deleteQiandanByStuID(pxstutable.getId(), qiyeID);
                    logtext += "，删除签单信息";
                }


                //删除流水
                iPxliushuizhangtableService.remove(new QueryWrapper<Pxliushuizhangtable>()
                        .eq("stuID", pxstutable.getId())
                        .eq("qiyeID", qiyeID));
                //删除学生:不是用余额支付的签单，才删除学生
                iPxstutableService.removeById(pxstutable.getId());
                logtext += "，删除学员的财务流水记录，删除学员";
            }

        } else {
            logtext += "删除签单信息：，（学员有多条签单记录，只是删除其中一条）,";
            //该学生存在多条签单，只删除其中一条签单信息
            logtext += "删除签单前，学员剩余学费：" + pxstutable.getRemainXuefei();
            pxstutable.setRemainXuefei(pxstutable.getRemainXuefei().subtract(qiandan.getShishouTotalMoney()));
            iPxstutableService.updateById(pxstutable);
            logtext += "删除签单后，学员剩余学费：" + pxstutable.getRemainXuefei();
            List<Pxqiandansubjecttable> qiandansubjectinfo =
                    iPxqiandansubjecttableService.GetQiandanSubjectByQianDanID(qiandan.getId());
            for (Pxqiandansubjecttable qdjst : qiandansubjectinfo) {
                //kechengStyle : 1买的，2接受的赠送
                Pxbuxikechengtable buxikecheng = iPxbuxikechengtableService.GetBuxikecheng(qdjst.getStuID(),
                        qdjst.getKechengID(), qdjst.getKechengprice(), qiyeID);
                if (buxikecheng.getRemainkeshi().compareTo(qdjst.getBuykeshiNum()) < 1) {
                    //特殊情况：剩余课时初始就小于购买课时（多签单情况课时问题）,暂时不做过多处理！！！
                    //看看有没有课耗，如果没有课耗，就把补习课程删除掉（还有退班，删除排课）
                    List<Pxkeshistutable> bxkcishavekehao =
                            iPxkeshistutableService.GetKehaostuByBuxikechengID(buxikecheng.getId());
                    if (bxkcishavekehao.size() > 0) {
                        //如果有课耗，该补习课程还不能删除；
                        buxikecheng.setRemainkeshi(new BigDecimal(0));
                    } else {
                        //没有课耗，则删除该补习课程,删除补习课程前,退班，退出排课
                        //退出排课
                        List<Pxxuanketable> xuankes = iPxxuanketableService.GetXuankeByKehcnegID(buxikecheng.getId());
                        if (xuankes.size() > 0) {
                            for (Pxxuanketable pxxuanketable : xuankes) {
                                //如果该排课只有这一个学生，就把该次排课删除掉，如果不只这一个学生，就只退出选课表

                                List<Pxxuanketable> xuankeNums =
                                        iPxxuanketableService.getAllXuankeByPaikeID(pxxuanketable.getPaikeID());
                                if (xuankeNums.size() > 1) {
                                    //说明该排课还有其他同学，则只退出选课表
                                    iPxxuanketableService.removeById(pxxuanketable.getId());
                                } else {
                                    //说明排课当中只有这一个学生，则删除选课和排课
                                    Pxpaiketable xkpaike = iPxpaiketableService.getById(pxxuanketable.getPaikeID());
                                    if (xkpaike != null) {
                                        //删除paikeTeacher表里对应的数据
                                        iPxpaiketeachertableService.DeletePaikeTeacherByPaikeID(xkpaike.getId());
                                        //删除排课
                                        iPxpaiketableService.removeById(xkpaike.getId());
                                    }
                                    //删除选课
                                    iPxxuanketableService.removeById(pxxuanketable.getId());
                                }
                            }
                        }
                        //退班,如果是一对一，把班删除掉
                        List<Pxstuclasstable> stucla =
                                iPxstuclasstableService.GetStuclassBybuxiIDlist(buxikecheng.getId());
                        if (stucla.size() > 0) {
                            for (Pxstuclasstable pxstuclasstable : stucla) {
                                Pxkechengtable kc = iPxkechengtableService.getById(buxikecheng.getKechengID());
                                String buxiStyleName =
                                        iPxbuxistyletableService.getById(kc.getBuxiStyleID()).getBuxiStyleName();
                                if (buxiStyleName == "一对一") {
                                    iPxclasstableService.removeById(pxstuclasstable.getClassID());
                                }
                            }
                            //退班
                            iPxstuclasstableService.DeleteStuclassbybuxiID(buxikecheng.getId());
                        }
                        iPxbuxikechengtableService.removeById(buxikecheng.getId());
                        logtext += "，删除补习课程记录，";
                    }
                } else if (buxikecheng.getRemainkeshi().compareTo(qdjst.getBuykeshiNum()) == 1) {
                    //补习课程剩余课时和签单时购买的课时数不相等,且补习课程里的剩余课时大于买的，则把买的扣减出去
                    buxikecheng.setRemainkeshi(buxikecheng.getRemainkeshi().subtract(qdjst.getBuykeshiNum()));
                    buxikecheng.setKeshiNum(buxikecheng.getKeshiNum().subtract(qdjst.getBuykeshiNum()));
                    buxikecheng.setZongjia(buxikecheng.getZongjia().subtract(qdjst.getZongjia()));
                    iPxbuxikechengtableService.updateById(buxikecheng);

                    logtext += "，扣减补习课程删除签单的课时，";
                }
            }

            //注意：签单的时候，如果有赠送课时，在qianDanSubjectTable里面是没有赠送记录的；只有去看赠送表；
            //删除赠送课时
            List<Pxkeshizengsongtable> keshiZhengsong =
                    iPxkeshizengsongtableService.GetZengsongkeshiByQiandanID(qiandan.getId());
            if (keshiZhengsong.size() > 0) {
                for (Pxkeshizengsongtable pxkeshizengsongtable : keshiZhengsong) {
                    Pxbuxikechengtable buxikc =
                            iPxbuxikechengtableService.GetBuxikecheng(pxkeshizengsongtable.getStuID(),
                                    pxkeshizengsongtable.getKechengID(), pxkeshizengsongtable.getKechengPrice(),
                                    qiyeID); //type=2
                    // 表示是赠送的
                    if (buxikc != null) {
                        if (buxikc.getRemainkeshi().compareTo(pxkeshizengsongtable.getKeshiShu()) == 0) {
                            iPxbuxikechengtableService.removeById(buxikc.getId());
                        } else {
                            buxikc.setRemainkeshi(buxikc.getRemainkeshi().subtract(pxkeshizengsongtable.getKeshiShu()));
                            buxikc.setKeshiNum(buxikc.getKeshiNum().subtract(pxkeshizengsongtable.getKeshiShu()));
                            iPxbuxikechengtableService.updateById(buxikc);
                        }
                    } else {
                        //如果补习课程不存在，这是一个不正常的情况
                    }
                }
                iPxkeshizengsongtableService.DeleteKeshizengsongByqiandanInfoID(qiandan.getId(), qiyeID);
            }
            //删除签单课程
            iPxqiandansubjecttableService.DeleteQiandansubjectByQdID(qiandan.getId(), qiyeID);
            logtext += "，删除签单课程记录，";

            //删除签单信息表2
            iPxqiandaninfo2tableService.deleteQiandanInfo2ByQiandanID(qiandan.getId());
            logtext += "，删除签单信息表2记录，";
            //删除流水账
            iPxliushuizhangtableService.deleteLiushuiByStuIdAndqiandanID(pxstutable.getId(), qiandan.getId(), qiyeID);
            logtext += "，删除签单流水账，";
            //删除签单业绩人
            iPxqiandanstafftableService.dleteQiandanStaffbyQiandanID(qiandan.getId());
            //删除签单支付方式
            List<Pxqiandanpaymoney> pxqiandanpaymoneyList =
                    iPxqiandanpaymoneyService.getQiandanPayMoneyList(qiandan.getId());
            for (Pxqiandanpaymoney pxqiandanpaymoney : pxqiandanpaymoneyList) {
                if (pxqiandanpaymoney.getPaymoneyStyleID() == 3) {
                    pxstutable.setRemainChongzhi(pxstutable.getRemainChongzhi().add(pxqiandanpaymoney.getPayMoney()));
                    iPxstutableService.updateById(pxstutable);
                }
            }
            iPxqiandanpaymoneyService.deleteQiandanPayMoneybyqiandanID(qiandan.getId());
            //删除签单商品信息
            iPxqiandansuppliesService.deleteqiandansuppliesByQiandanID(qiandan.getId());

            //删除签单
            iPxqiandaninfotableService.removeById(qiandan.getId());
            logtext += "，删除签单信息记录。";

            //region 删除有的审批记录
            Qiandanshenpi qdsp = iQiandanshenpiService.getOne(new QueryWrapper<Qiandanshenpi>()
                    .eq("stuID", qiandan.getStuID())
                    .eq("qiandandate", qiandan.getQiandandate())
                    .eq("campusID", qiandan.getCampusID())
                    .eq("qiyeID", qiyeID)
            );
            if (qdsp != null) {
                List<Qiandanshenpipaymoney> paylist =
                        iQiandanshenpipaymoneyService.list(new QueryWrapper<Qiandanshenpipaymoney>()
                                .eq("qiandanshenpiID", qdsp.getId())
                                .eq("qiyeID", loginUser.getQiyeID())
                        );

                List<Qiandanshenpisubject> sublist =
                        iQiandanshenpisubjectService.list(new QueryWrapper<Qiandanshenpisubject>()
                                .eq("qiandanshenpiID", qdsp.getId())
                                .eq("qiyeID", loginUser.getQiyeID())
                        );

                List<Qiandanshenpisupplies> supplieslist =
                        iQiandanshenpisuppliesService.list(new QueryWrapper<Qiandanshenpisupplies>()
                                .eq("qiandanshenpiID", qdsp.getId())
                                .eq("qiyeID", loginUser.getQiyeID())
                        );

                List<Qiandanshenpiyejiren> yejirenlist =
                        iQiandanshenpiyejirenService.list(new QueryWrapper<Qiandanshenpiyejiren>()
                                .eq("qiandanshenpiID", qdsp.getId())
                                .eq("qiyeID", loginUser.getQiyeID())
                        );
                List<Qiandanshenpizafei> zflist = iQiandanshenpizafeiService.list(new QueryWrapper<Qiandanshenpizafei>()
                        .eq("qiandanshenpiID", qdsp.getId())
                        .eq("qiyeID", loginUser.getQiyeID())
                );
                List<QiandanshenpiZhuanjieshao> zjslist =
                        iQiandanshenpiZhuanjieshaoService.list(new QueryWrapper<QiandanshenpiZhuanjieshao>()
                                .eq("qiandanshenpiID", qdsp.getId())
                                .eq("qiyeID", loginUser.getQiyeID())
                        );

                if (paylist.size() > 0) {
                    iQiandanshenpipaymoneyService.remove(new QueryWrapper<Qiandanshenpipaymoney>()
                            .eq("qiandanshenpiID", qdsp.getId())
                            .eq("qiyeID", loginUser.getQiyeID())
                    );
                }

                if (sublist.size() > 0) {
                    iQiandanshenpisubjectService.list(new QueryWrapper<Qiandanshenpisubject>()
                            .eq("qiandanshenpiID", qdsp.getId())
                            .eq("qiyeID", loginUser.getQiyeID())
                    );
                }
                if (supplieslist.size() > 0) {
                    iQiandanshenpisuppliesService.list(new QueryWrapper<Qiandanshenpisupplies>()
                            .eq("qiandanshenpiID", qdsp.getId())
                            .eq("qiyeID", loginUser.getQiyeID())
                    );
                }
                if (yejirenlist.size() > 0) {
                    iQiandanshenpiyejirenService.list(new QueryWrapper<Qiandanshenpiyejiren>()
                            .eq("qiandanshenpiID", qdsp.getId())
                            .eq("qiyeID", loginUser.getQiyeID())
                    );
                }
                if (zflist.size() > 0) {
                    iQiandanshenpizafeiService.list(new QueryWrapper<Qiandanshenpizafei>()
                            .eq("qiandanshenpiID", qdsp.getId())
                            .eq("qiyeID", loginUser.getQiyeID())
                    );
                }
                if (zjslist.size() > 0) {
                    iQiandanshenpiZhuanjieshaoService.list(new QueryWrapper<QiandanshenpiZhuanjieshao>()
                            .eq("qiandanshenpiID", qdsp.getId())
                            .eq("qiyeID", loginUser.getQiyeID())
                    );
                }

                iQiandanshenpiService.removeById(qdsp);

                logtext += "，审批有关数据，";
            }
            //endregion

            //region 删除有的小程序支付记录
            Qiandanapppay qdapp = iQiandanapppayService.getOne(new QueryWrapper<Qiandanapppay>()
                    .eq("stuID", qiandan.getStuID())
                    .eq("qiandanDate", qiandan.getQiandandate())
                    .eq("campusID", qiandan.getCampusID())
                    .eq("qiyeID", qiyeID)
            );
            if (qdapp != null) {
                List<Qiandanapppaymoney> apppaylist =
                        iQiandanapppaymoneyService.list(new QueryWrapper<Qiandanapppaymoney>()
                                .eq("qiandanAppayID", qdapp.getId())
                                .eq("qiyeID", loginUser.getQiyeID())
                        );

                List<Qiandanapppaysubject> appsublist =
                        iQiandanapppaysubjectService.list(new QueryWrapper<Qiandanapppaysubject>()
                                .eq("qiandanAppayID", qdapp.getId())
                                .eq("qiyeID", loginUser.getQiyeID())
                        );

                List<Qiandanapppaysupplies> appsupplieslist =
                        iQiandanapppaysuppliesService.list(new QueryWrapper<Qiandanapppaysupplies>()
                                .eq("qiandanAppayID", qdapp.getId())
                                .eq("qiyeID", loginUser.getQiyeID())
                        );

                List<Qiandanapppayyejiren> appyejirenlist =
                        iQiandanapppayyejirenService.list(new QueryWrapper<Qiandanapppayyejiren>()
                                .eq("qiandanAppayID", qdapp.getId())
                                .eq("qiyeID", loginUser.getQiyeID())
                        );
                List<Qiandanapppayzafei> appzflist =
                        iQiandanapppayzafeiService.list(new QueryWrapper<Qiandanapppayzafei>()
                                .eq("qiandanAppayID", qdapp.getId())
                                .eq("qiyeID", loginUser.getQiyeID())
                        );
                List<QiandanapppayZhuanjieshao> appzjslist =
                        iQiandanapppayZhuanjieshaoService.list(new QueryWrapper<QiandanapppayZhuanjieshao>()
                                .eq("qiandanapppayID", qdapp.getId())
                                .eq("qiyeID", loginUser.getQiyeID())
                        );

                if (apppaylist.size() > 0) {
                    iQiandanapppaymoneyService.list(new QueryWrapper<Qiandanapppaymoney>()
                            .eq("qiandanAppayID", qdapp.getId())
                            .eq("qiyeID", loginUser.getQiyeID())
                    );
                }

                if (appsublist.size() > 0) {
                    iQiandanapppaysubjectService.list(new QueryWrapper<Qiandanapppaysubject>()
                            .eq("qiandanAppayID", qdapp.getId())
                            .eq("qiyeID", loginUser.getQiyeID())
                    );
                }
                if (appsupplieslist.size() > 0) {
                    iQiandanapppaysuppliesService.list(new QueryWrapper<Qiandanapppaysupplies>()
                            .eq("qiandanAppayID", qdapp.getId())
                            .eq("qiyeID", loginUser.getQiyeID())
                    );
                }
                if (appyejirenlist.size() > 0) {
                    iQiandanapppayyejirenService.list(new QueryWrapper<Qiandanapppayyejiren>()
                            .eq("qiandanAppayID", qdapp.getId())
                            .eq("qiyeID", loginUser.getQiyeID())
                    );
                }
                if (appzflist.size() > 0) {
                    iQiandanapppayzafeiService.list(new QueryWrapper<Qiandanapppayzafei>()
                            .eq("qiandanAppayID", qdapp.getId())
                            .eq("qiyeID", loginUser.getQiyeID())
                    );
                }
                if (appzjslist.size() > 0) {
                    iQiandanapppayZhuanjieshaoService.list(new QueryWrapper<QiandanapppayZhuanjieshao>()
                            .eq("qiandanapppayID", qdapp.getId())
                            .eq("qiyeID", loginUser.getQiyeID())
                    );

                }

                iQiandanapppayService.removeById(qdapp);
                logtext += "，小程序支付有关数据。";
            }


            //endregion
        }


        savePxLog.savepxlog(logtext, "xwcloud-zsbm/zsbm/BaoMingJiaoFei" +
                "/DeleteQianDanAndStu", loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());

        ajaxJson.setSuccess(true);
        return ajaxJson;
    }

    /**
     * 导出新签信息
     *
     * @param response
     */
    @RequestMapping(value = "/exportXinqian", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("导出新签信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "startDate", value = "开始日期", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束日期", required = false),
    })
    public void exportXinqian(HttpServletResponse response, @RequestParam(required = false) String campusID,
                              HttpServletRequest request, int type,
                              @RequestParam(required = false) String startDate,
                              @RequestParam(required = false) String endDate) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        List<ExportExcel.ExcelSource> sourceList = new ArrayList<>();

        QueryWrapper<qianDanInFoVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("a.moneyStyle", type);

        QueryWrapper<qdkeshiVo> qdksQ = new QueryWrapper<>();
        qdksQ.eq("c.moneyStyle", type);
        qdksQ.eq("a.qiyeID", loginUser.getQiyeID());

        QueryWrapper<qdCountVo> qdcountQ = new QueryWrapper<>();
        qdcountQ.eq("a.qiyeID", loginUser.getQiyeID());
        qdcountQ.eq("c.moneyStyle", type);

        QueryWrapper<xinqianliushuiVo> lsQ = new QueryWrapper<>();
        lsQ.eq("a.qiyeID", loginUser.getQiyeID());
        lsQ.eq("b.moneyStyle", type);

        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq(" b.campusID", campusID);
            qdksQ.eq(" a.campusID", campusID);
            qdcountQ.eq(" a.campusID", campusID);
            lsQ.eq(" c.campusID", campusID);


        }
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("a.qiandandate", startDate);
            qdksQ.ge("c.qiandandate", startDate);
            qdcountQ.ge("c.qiandandate", startDate);
            lsQ.ge("b.qiandandate", startDate);
        }
        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("a.qiandandate", endDate);
            qdksQ.le("c.qiandandate", endDate);
            qdcountQ.le("c.qiandandate", endDate);
            lsQ.le("b.qiandandate", endDate);
        }

        /**
         * 新签信息表
         */
        List<exportqdInfoVo> pxqiandanList = iPxqiandaninfotableService.GetQiandanInfoList(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "学号", "姓名", "家长电话号码", "年级", "收费金额",
                        "代金券", "课程学费", "综合服务费", "办理时间", "经办人", "转介绍", "费用类型", "付费方式", "费用说明", "业绩人", "市场人"},
                pxqiandanList,
                new String[]{"campusName", "zidingyiStuID", "stuName", "parentTel", "stuGradeName", "HetongMoney",
                        "daijinquanMoney", "kechengMoney", "SumotherMoney", "qiandandate", "jinbanStaffName",
                        "iszhuangjieshao", "qdmoneyStyle", "paystyle", "beizhu", "yejistaffName", "shichangtea"});

        ExportExcel.ExcelSource qdInfo = new ExportExcel.ExcelSource();
        String name = "";
        if (type == 1) {
            name = "新签信息表";
        } else if (type == 2) {
            name = "续费信息表";
        }
        qdInfo.setSheetName(name);
        qdInfo.setTableData(list);
        sourceList.add(qdInfo);


        /**
         * 新签课时详情表
         */
        List<qdkeshiVo> getqiandankeshi = iPxstutableService.getqiandankeshi(qdksQ);
        List<List<Object>> qdkslist = ExportExcel.formatDataToList(new String[]{"学号", "姓名", "课程名称", "补习方式", "年级",
                        "科目", "购买课时数", "课程原单价", "折扣价", "课时改变方式", "签单时间", "业绩人", "校区"},
                getqiandankeshi,
                new String[]{"stuID", "stuName", "kechengName", "buxiStyleName", "stuGradeName", "subjectName",
                        "buykeshiNum", "originalprice", "kechengprice", "keshichangeType", "qiandandate",
                        "yejistaffName",
                        "campusName"});
        ExportExcel.ExcelSource qdksInfo = new ExportExcel.ExcelSource();
        String name1 = "";
        if (type == 1) {
            name1 = "新签课时详情表";
        } else if (type == 2) {
            name1 = "续费课时详情表";
        }
        qdksInfo.setSheetName(name1);
        qdksInfo.setTableData(qdkslist);
        sourceList.add(qdksInfo);


        /**
         * 新签统计表
         */
        List<qdCountVo> qdCountVos = iPxstutableService.getqiandanCountlist(qdcountQ);
        List<List<Object>> qdcountlist = ExportExcel.formatDataToList(new String[]{"学号", "姓名", "校区", "家长电话号码", "年级",
                        "合同金额", "实收金额", "课程学费", "代金券", "其他金额", "尾款", "优惠金额"},
                qdCountVos,
                new String[]{"stuID", "stuName", "campusName", "parentTel", "stuGradeName", "HetongMoney",
                        "shishouTotalMoney", "kechengMoney", "daijinquanMoney", "SumotherMoney", "weikuan",
                        "youhuijine"});
        String name2 = "";
        if (type == 1) {
            name2 = "新签统计表";
        } else if (type == 2) {
            name2 = "续费统计表";
        }
        ExportExcel.ExcelSource qdcountInfo = new ExportExcel.ExcelSource();
        qdcountInfo.setSheetName(name2);
        qdcountInfo.setTableData(qdcountlist);
        sourceList.add(qdcountInfo);

        /**
         * 新签流水表
         */
        List<xinqianliushuiVo> xinqianliushuiVos = iPxliushuizhangtableService.getqdliushuiList(lsQ);
        List<List<Object>> qdliushuilist = ExportExcel.formatDataToList(new String[]{"校区", "学号", "姓名", "流水号", "支付方式",
                        "金额", "流水摘要", "流水时间"},
                xinqianliushuiVos,
                new String[]{"campusName", "stuID", "stuName", "liushuiID", "moneystyleName", "shouruMoney",
                        "liushuiZaiyao", "liushuiDateTime"});
        String name3 = "";
        if (type == 1) {
            name3 = "新签流水表";
        } else if (type == 2) {
            name3 = "续费流水表";
        }
        ExportExcel.ExcelSource qdliushuiInfo = new ExportExcel.ExcelSource();
        qdliushuiInfo.setSheetName(name3);
        qdliushuiInfo.setTableData(qdliushuilist);
        sourceList.add(qdliushuiInfo);

        try {
            ExportExcel.exportMultipleSheetExcel(response, sourceList, "新签信息导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有报名附加字段
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetAllZidingyiParams", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询所有报名附加字段")
    public AjaxJson GetAllZidingyiParams(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();
        ajaxJson.setObj(iPxstuparamtypetableService.GetStuparamtypeList(qiyeID));
        return ajaxJson;
    }

    /**
     * 根据年级查询优惠政策
     *
     * @param stuGradeID
     * @return
     */
    @RequestMapping(value = "/GetAllStuGradeYoouhuizhengce", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "根据年级查询优惠政策")
    public AjaxJson GetAllStuGradeYoouhuizhengce(String stuGradeID, BigDecimal jine, String campusID,
                                                 HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");

        Date date = new Date();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.gt("a.endDatetime", date);
        queryWrapper.eq("a.campusID", campusID);
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        queryWrapper.le("a.xianzhijine", jine);
        ajaxJson.setObj(iPxyouhuizhengcetableService.youhuizhengceListBystuGrade(stuGradeID, queryWrapper));
        return ajaxJson;
    }

    /**
     * 根据校区ID跟计费方式查询课程
     *
     * @param campusID
     * @param jifeiStyleID
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetAllKechengBycampusID", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "根据校区ID跟计费方式查询课程")
    public AjaxJson GetAllKechengBycampusID(long campusID, Integer jifeiStyleID, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxkechengtableService.GetBuxikechengByCampusID(campusID, qiyeID, jifeiStyleID));
        return ajaxJson;
    }

    /**
     * 通过校区查询所有班级
     *
     * @param campusID
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetAllClassByCampusID", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "通过校区查询所有班级")
    public AjaxJson GetAllClassByCampusID(long campusID, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxclasstableService.GetAllClassInfoList(campusID, qiyeID));
        return ajaxJson;
    }

    @RequestMapping(value = "/GetQiandanOtherMoneyList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询签单其他费用信息")
    public AjaxJson GetQiandanOtherMoneyList(HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxqiandanothermoneytableService.GetQiandanOtherMoneyList(qiyeID));
        return ajaxJson;
    }

    @RequestMapping(value = "/GetAllWupingList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询所有商品信息")
    public AjaxJson GetAllWupingList(long campusID, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxteachingsuppliestableService.getAllWupingList(campusID, qiyeID));
        return ajaxJson;
    }

    @RequestMapping(value = "/getKechengbykechengID", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "通过课程ID查询课程信息")
    public AjaxJson getKechengbykechengID(long kechengID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxkechengtableService.getById(kechengID));
        return ajaxJson;
    }
    //endregion

    //region 签单信息（续费）

    /**
     * 查询签单信息（续费）
     *
     * @param size
     * @param current
     * @param campusID
     * @param zidingyiStuID
     * @param stuName
     * @param stuGradeID
     * @param jinbanStaffName
     * @param Sqiandandate
     * @param Eqiandandate
     * @param yejistaffname
     * @param request
     * @return
     */
    @RequestMapping(value = "/getAllXufeiQianDanInfoPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("查询签单信息（续费）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "campusName", value = "校区名称", required = false),
            @ApiImplicitParam(name = "zidingyiStuID", value = "自定义学号", required = false),
            @ApiImplicitParam(name = "stuName", value = "学生姓名", required = false),
            @ApiImplicitParam(name = "stuGradeName", value = "学生年级", required = false),
            @ApiImplicitParam(name = "jinbanStaffName", value = "经办人", required = false),
    })
    public AjaxJson getAllXufeiQianDanInfoPages(long size, long current, long campusID, String zidingyiStuID,
                                                String stuName, long stuGradeID, String jinbanStaffName
            , String Sqiandandate, String Eqiandandate, String yejistaffname, long menuID, int isweikuan,
                                                HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Page<qianDanInFoVo> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", qiyeID);
        queryWrapper.eq("moneyStyle", 2);
        if (StringUtils.isNotBlank(zidingyiStuID)) {
            queryWrapper.like("zidingyiStuID", zidingyiStuID);
        }
        if (campusID != 0) {
            queryWrapper.eq("b.campusID", campusID);
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("stuName", stuName);
        }
        if (stuGradeID != 0) {
            queryWrapper.eq(" b.stuGradeID", stuGradeID);
        }
        if (StringUtils.isNotBlank(jinbanStaffName)) {
            queryWrapper.like("jinbanStaffName", jinbanStaffName);
        }
        if (StringUtils.isNotBlank(Sqiandandate)) {
            queryWrapper.ge("a.qiandandate", Sqiandandate);
        }
        if (StringUtils.isNotBlank(Eqiandandate)) {
            queryWrapper.le("a.qiandandate", Eqiandandate);
        }
        if (StringUtils.isNotBlank(yejistaffname)) {
            queryWrapper.like("yejistaffName", yejistaffname);
        }
        if (isweikuan != -1) {
            queryWrapper.gt("a.HetongMoney-a.shishouTotalMoney", 0);
        }
        queryWrapper.orderByDesc("a.recordInTime");

        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", menuID);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.recordInStaffID", loginUser.getStaffID());
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("b.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("b.campusID", lookPower);
        }
        ajaxJson.setObj(iPxqiandaninfotableService.GetQiandanInfoPages(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * 保存续费签单信息
     *
     * @param form
     * @return
     */
    @RequestMapping(value = "/AddXufeiQiandan", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("添加续费签单")
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson AddXufeiQiandan(@RequestBody xufeiForm form, HttpServletRequest request) throws ParseException {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();
        String logtext = "添加续费信息，经办人：" + dlstaffName + "，时间：" + form.getQiandandate().toString();
        String xiugaiInfo = "";
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Pxsysparamdefaulttable pxsysparamdefaulttable = iPxsysparamdefaulttableService.GetSysParamById(43L);
        Date nowDate = new Date();
        int days = (int) ((nowDate.getTime() - form.getQiandandate().getTime()) / (1000 * 3600 * 24));
        if (days > Integer.valueOf(pxsysparamdefaulttable.getDefaultValue())) {
            ajaxJson.setMsg("不能录入" + days + "天以前的签单！");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        Pxstutable pxstutable = iPxstutableService.getById(form.getStuID());
        if (pxstutable == null) {
            ajaxJson.setMsg("系统未找到该续费学员信息！");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        logtext += "，金额：" + form.getShishouTotalMoney() + ",学号：" + pxstutable.getId() + ",学员：" + pxstutable.getStuName();
        //region 优惠政策
        Pxyouhuizhengcetable yhzc = iPxyouhuizhengcetableService.getYouhuizhengceById(form.getYouhuizhengce());
        BigDecimal zong = form.getAmountKC().add(form.getAmountWp()).add(form.getAmountOther());
        BigDecimal zhehoujine = new BigDecimal(0);
        if (yhzc != null) {
            if (zong.compareTo(yhzc.getXianzhijine()) >= 0) {
                if (yhzc.getYouhuiType() == 1)//打折优惠
                {
                    zhehoujine =
                            zong.subtract(zong.subtract(zong.multiply(yhzc.getYouhui()).divide(new BigDecimal(10))));
                } else {//满减优惠
                    zhehoujine = yhzc.getYouhui();
                }
            }
        }

        //小程序付款
        String isappPay = form.getIsAppPay();
        if (isappPay.equals("是")) {
            //进小程序支付流程
            Qiandanapppay qdappPay = new Qiandanapppay();
            qdappPay.setStuID(pxstutable.getId())
                    .setQiandanDate(form.getQiandandate())
                    .setZafeimoney(form.getAmountOther())
                    .setWupinmoney(form.getAmountWp())
                    .setKechengmoney(form.getAmountKC())
                    .setHetongMoney(zong.subtract(zhehoujine).subtract(form.daijinquan))
                    .setDingjing(form.getDingjin())
                    .setMoneyStyle(2)
                    .setBeizhu(form.getBeizhu())
                    .setCampusID(pxstutable.getCampusID())
                    .setYouhuiID(form.getYouhuizhengce())
                    .setYouhuijine(zhehoujine)
                    .setYouhuishuoming(yhzc != null ? yhzc.getYouhuiType() == 1 ?
                            "金额达到或超过" + yhzc.getXianzhijine() + "元，" + yhzc.getYouhui() + "折优惠" :
                            "金额达到或者超过" + yhzc.getXianzhijine() + "元，满减优惠" + yhzc.getYouhui() + "元" : "未优惠")
                    .setDaijinquanmoney(form.getDaijinquan())
                    .setAddstaffID(loginUser.getStaffID())
                    .setQdtype(1)
                    .setBuxiStateID(pxstutable.getBuxiStateID())
                    .setPayState(1) //待支付
                    .setQiyeID(Long.valueOf(loginUser.getQiyeID()));
            if (form.getDaijinquan().compareTo(new BigDecimal(0)) == 1) {
                qdappPay.setShishouTotalMoney(form.getDingjin());
                qdappPay.setIsdingjing(3);
            } else {
                qdappPay.setShishouTotalMoney(zong.subtract(zhehoujine).subtract(form.getDaijinquan()));
                qdappPay.setIsdingjing(1);
            }
            iQiandanapppayService.save(qdappPay);

            //签单业绩人
            List<Pxqiandanstafftable> qdstaff = JSON.parseArray(form.getQiandanstaffinfo(), Pxqiandanstafftable.class);
            for (Pxqiandanstafftable pxqiandanstafftable : qdstaff) {
                Qiandanapppayyejiren qdyejiren = new Qiandanapppayyejiren();
                qdyejiren.setQiandanAppayID(qdappPay.getId())
                        .setQiandanstaffID(pxqiandanstafftable.getStaffID())
                        .setQiyeID(Long.valueOf(qiyeID))
                        .setYejidate(form.getQiandandate())
                        .setYejiMoney(pxqiandanstafftable.getYejiMoney());
                iQiandanapppayyejirenService.save(qdyejiren);
            }

            //杂费
            List<buyZafeiVo> zafeiList = JSON.parseArray(form.getZafeiData(), buyZafeiVo.class);
            for (buyZafeiVo othermoneyVo : zafeiList) {
                Qiandanapppayzafei qdappzf = new Qiandanapppayzafei()
                        .setNums(new BigDecimal(1))
                        .setOnePrice(othermoneyVo.getZafeiZongjia())
                        .setQiyeID(Long.valueOf(qiyeID))
                        .setQianDanOtherMoneyID(othermoneyVo.getZafeiID())
                        .setQiandanAppayID(qdappPay.getId())
                        .setZongMoney(othermoneyVo.getZafeiZongjia());
                iQiandanapppayzafeiService.save(qdappzf);
            }

            //课程
            List<buyKechengVo> buykechenglist = JSON.parseArray(form.getBxKcData(), buyKechengVo.class);
            if (buykechenglist == null) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                ajaxJson.setMsg("操作失败！您还未添加课程！");
                ajaxJson.setCode("N");
                return ajaxJson;
            }

            //region 保存课程信息
            for (buyKechengVo buyKechengVo : buykechenglist) {
                Qiandanapppaysubject qdappsubject = new Qiandanapppaysubject()
                        .setStuID(pxstutable.getId())
                        .setDiscount(buyKechengVo.getZK())
                        .setBuykeshiNum(buyKechengVo.getKS())
                        .setZengsongkeshi(buyKechengVo.getZKS())
                        .setKechengID(buyKechengVo.getKechengID())
                        .setKechengprice(buyKechengVo.getDJ())
                        .setEndDate(ft.parse(buyKechengVo.getEndDate()))
                        .setStartDate(ft.parse(buyKechengVo.getStartDate()))
                        .setOriginalprice(buyKechengVo.getYDJ())
                        .setQiandanAppayID(qdappPay.getId())
                        .setQiyeID(Long.valueOf(qiyeID))
                        .setQiandandate(form.getQiandandate())
                        .setKechengStyle(1)
                        .setClassID(buyKechengVo.getClassID())
                        .setPkid(buyKechengVo.getPkid())
                        .setCharukebiao(buyKechengVo.getCkb())
                        .setZongjia(buyKechengVo.getZJ());
                iQiandanapppaysubjectService.save(qdappsubject);
            }
            //endregion

            //物品
            List<buyWpVo> wpbuyList = JSON.parseArray(form.getWpData(), buyWpVo.class);
            for (buyWpVo buyWpVo : wpbuyList) {
                Qiandanapppaysupplies qdsupplies = new Qiandanapppaysupplies()
                        .setBuyPrice(buyWpVo.getWpDanjia())
                        .setBuySum(buyWpVo.getWpShuliang())
                        .setFafangstate(1)
                        .setQiyeID(Long.valueOf(qiyeID))
                        .setName(buyWpVo.getWpName())
                        .setQiandanAppayID(qdappPay.getId())
                        .setTeachingSuppliesID(buyWpVo.getId())
                        .setSumMoney(buyWpVo.getWpZongjia());
                iQiandanapppaysuppliesService.save(qdsupplies);
            }

            //paymoney
            List<Pxqiandanpaymoney> pxqiandanpaymoneyList = JSON.parseArray(form.getPaytyles(),
                    Pxqiandanpaymoney.class);
            Qiandanapppaymoney qdapppay = new Qiandanapppaymoney()
                    .setPaymoney(pxqiandanpaymoneyList.get(0).getPayMoney())
                    .setQiandanAppayID(qdappPay.getId())
                    .setQiyeID(loginUser.getQiyeID())
                    .setPaymoneystyleID(-1L);
            iQiandanapppaymoneyService.save(qdapppay);

            logtext += ",小程序支付。";
            ajaxJson.setMsg("请通知家长小程序支付！！！");
            return ajaxJson;
        }


        //审批流程
        QueryWrapper queryWrappersp = new QueryWrapper();
        queryWrappersp.eq("sysparamTypeID", 108);
        queryWrappersp.eq("qiyeID", loginUser.getQiyeID());
        Pxsysparamvaluetable pxsysparamvaluetable = iPxsysparamvaluetableService.getOne(queryWrappersp);
        if (pxsysparamvaluetable.getModifyValue().equals("1")) {
            //保存签单审批信息
            Qiandanshenpi qiandanshenpi = new Qiandanshenpi();
            qiandanshenpi.setStuID(pxstutable.getId())
                    .setQiandandate(form.getQiandandate())
                    .setZafeimoney(form.getAmountOther())
                    .setWupinmoney(form.getAmountWp())
                    .setKechengmoney(form.getAmountKC())
                    .setMoneyStyle(2)
                    .setBeizhu(form.getBeizhu())
                    .setCampusID(pxstutable.getCampusID())
                    .setQiandanType(2)
                    .setYouhuiID(form.getYouhuizhengce())
                    .setYouhuijine(zhehoujine)
                    .setYouhuishuoming(yhzc != null ? yhzc.getYouhuiType() == 1 ?
                            "金额达到或超过" + yhzc.getXianzhijine() + "元，" + yhzc.getYouhui() + "折优惠" :
                            "金额达到或者超过" + yhzc.getXianzhijine() + "元，满减优惠" + yhzc.getYouhui() + "元" : "未优惠")
                    .setDaijinquanmoney(form.getDaijinquan())
                    .setAddstaffID(loginUser.getStaffID())
                    .setQiandanType(2)
                    .setBuxiStateID(pxstutable.getBuxiStateID())
                    .setShenpiState(0)
                    .setQiyeID(Long.valueOf(loginUser.getQiyeID()))
                    .setHetongMoney(zong.subtract(zhehoujine).subtract(form.daijinquan));
            if (form.getDaijinquan().compareTo(new BigDecimal(0)) == 1) {
                qiandanshenpi.setShishouTotalMoney(form.getDingjin());
                qiandanshenpi.setIsdingjing(3);
            } else {
                qiandanshenpi.setShishouTotalMoney(zong.subtract(zhehoujine).subtract(form.getDaijinquan()));
                qiandanshenpi.setIsdingjing(1);
            }
            qiandanshenpi.setDingjing(form.getDingjin());
            iQiandanshenpiService.save(qiandanshenpi);
            //保存签单支付方式信息
            List<Pxqiandanpaymoney> payMoneyStyle = JSON.parseArray(form.getPayMoneyStyle(), Pxqiandanpaymoney.class);
            for (Pxqiandanpaymoney pxqiandanpaymoney : payMoneyStyle) {
                Qiandanshenpipaymoney qiandanshenpipaymoney = new Qiandanshenpipaymoney();
                qiandanshenpipaymoney.setQiandanshenpiID(qiandanshenpi.getId())
                        .setPaymoney(pxqiandanpaymoney.getPayMoney())
                        .setQiyeID(Long.valueOf(loginUser.getQiyeID()))
                        .setPaymoneystyleID(pxqiandanpaymoney.getPaymoneyStyleID());
                iQiandanshenpipaymoneyService.save(qiandanshenpipaymoney);
            }
            List<buyZafeiVo> zafeiList = JSON.parseArray(form.getZafeiData(), buyZafeiVo.class);
            //签单其他费添加
            for (buyZafeiVo zf : zafeiList) {
                Qiandanshenpizafei qiandanshenpizafei = new Qiandanshenpizafei();
                qiandanshenpizafei.setNums(new BigDecimal(1))
                        .setQiyeID(Long.valueOf(loginUser.getQiyeID()))
                        .setOnePrice(zf.getZafeiZongjia())
                        .setZongMoney(zf.getZafeiZongjia())
                        .setQianDanOtherMoneyID(zf.getZafeiID())
                        .setQiandanshenpiID(qiandanshenpi.getId());
                iQiandanshenpizafeiService.save(qiandanshenpizafei);
            }
            //保存签单商品信息
            List<buyWpVo> wpbuyList = JSON.parseArray(form.getWpData(), buyWpVo.class);
            if (wpbuyList != null) {
                for (buyWpVo wp : wpbuyList) {
                    Qiandanshenpisupplies qiandanshenpisupplies = new Qiandanshenpisupplies();
                    qiandanshenpisupplies.setQiandanshenpiID(qiandanshenpi.getId())
                            .setQiyeID(Long.valueOf(loginUser.getQiyeID()))
                            .setBuyPrice(wp.getWpChushouJia())
                            .setBuySum(wp.getWpShuliang())
                            .setName(wp.getWpName())
                            .setSumMoney(wp.getWpZongjia())
                            .setTeachingSuppliesID(wp.getId());
                    iQiandanshenpisuppliesService.save(qiandanshenpisupplies);
                }
            }
            //保存签单业绩人信息n
            List<Pxqiandanstafftable> qdstaff = JSON.parseArray(form.getQiandanstaffinfo(), Pxqiandanstafftable.class);
            for (Pxqiandanstafftable pxqiandanstafftable : qdstaff) {
                Qiandanshenpiyejiren qiandanshenpiyejiren = new Qiandanshenpiyejiren();
                qiandanshenpiyejiren.setQiandanstaffID(pxqiandanstafftable.getStaffID())
                        .setQiyeID(Long.valueOf(loginUser.getQiyeID()))
                        .setYejidate(form.getQiandandate())
                        .setQiandanshenpiID(qiandanshenpi.getId())
                        .setYejiMoney(pxqiandanstafftable.getYejiMoney());
                iQiandanshenpiyejirenService.save(qiandanshenpiyejiren);
            }
            //保存签单购买课程信息
            List<buyKechengVo> bxKcList = JSON.parseArray(form.getBxKcData(), buyKechengVo.class);
            if (bxKcList != null) {
                for (buyKechengVo buyKechengVo : bxKcList) {
                    Qiandanshenpisubject qiandanshenpisubject = new Qiandanshenpisubject();
                    qiandanshenpisubject.setQiandanshenpiID(qiandanshenpi.getId())
                            .setStuID(pxstutable.getId())
                            .setQiandandate(form.getQiandandate())
                            .setKechengID(buyKechengVo.getKechengID())
                            .setKechengprice(buyKechengVo.getDJ())
                            .setOriginalprice(buyKechengVo.getYDJ())
                            .setBuykeshiNum(buyKechengVo.getKS())
                            .setZengsongkeshi(buyKechengVo.getZKS())
                            .setZongjia(buyKechengVo.getZJ())
                            .setStartDate(ft.parse(buyKechengVo.getStartDate()))
                            .setEndDate(ft.parse(buyKechengVo.getEndDate()))
                            .setKechengStyle(1)
                            .setDiscount(buyKechengVo.getZK())
                            .setClassID(buyKechengVo.getClassID())
                            .setCharukebiao(buyKechengVo.getCkb())
                            .setQiyeID(Long.valueOf(loginUser.getQiyeID()));
                    iQiandanshenpisubjectService.save(qiandanshenpisubject);
                }
            }
            ajaxJson.setSuccess(true);
            logtext += "，开启审批流程，添加续费审批完毕，等待审批。";

        } else {
            List<Pxqiandanpaymoney> payMoneyStyle = JSON.parseArray(form.getPaytyles(), Pxqiandanpaymoney.class);
            for (Pxqiandanpaymoney pxqiandanpaymoney : payMoneyStyle) {
                //使用系统余额进行支付，扣除余额
                if (pxqiandanpaymoney.getPaymoneyStyleID() == 3) {
                    if (pxstutable.getRemainChongzhi().compareTo(pxqiandanpaymoney.getPayMoney()) == -1) {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        ajaxJson.setMsg("余额不足");
                        ajaxJson.setCode("N");
                        return ajaxJson;
                    }
                    xiugaiInfo += "，用充值余额支付,支付前的充值余额为" + pxstutable.getRemainChongzhi();
                    pxstutable.setRemainChongzhi(pxstutable.getRemainChongzhi().subtract(pxqiandanpaymoney.getPayMoney()));
                    xiugaiInfo += "，支付后的充值余额为" + pxstutable.getRemainChongzhi();

                    Pxchongzhipaytable jiaoyitab = new Pxchongzhipaytable();
                    jiaoyitab.setStuID(pxstutable.getId());
                    jiaoyitab.setChongzhiPayMoney(pxqiandanpaymoney.getPayMoney());
                    jiaoyitab.setType(2);
                    jiaoyitab.setBeizhu("续费使用余额支出");
                    jiaoyitab.setAddTime(new Date());
                    jiaoyitab.setAddStaffID(staffID);
                    jiaoyitab.setQiyeID(qiyeID);
                    iPxchongzhipaytableService.save(jiaoyitab);
                    xiugaiInfo += "，向充值记录表里添加一条续费支出记录";
                }
            }
            //使用系统余额进行支付，扣除余额
            xiugaiInfo += "，续费前，学员剩余学费：" + pxstutable.getRemainXuefei().toString();
            if (form.getDingjin().compareTo(new BigDecimal(0)) == 1) {
                pxstutable.setRemainXuefei(pxstutable.getRemainXuefei().add(form.getDingjin()));
            } else {
                pxstutable.setRemainXuefei(pxstutable.getRemainXuefei().add(form.getAmountKC()));
            }
            xiugaiInfo += "，续费后，学员剩余学费：" + pxstutable.getRemainXuefei().toString();

            Long fromType = 0L;
            if (form.getMoneyStyle() == 1) {
                //fromType = Convert.ToInt32(telFromID);
            } else {
                Pxqiandaninfotable beforQianDan = iPxqiandaninfotableService.GetXqOrXfinfo(pxstutable.getId(), qiyeID);
                if (beforQianDan != null) {
                    fromType = beforQianDan.getFromType();
                }
            }
            Pxqiandaninfotable qiandan = new Pxqiandaninfotable();
            qiandan.setStuID(pxstutable.getId());
            qiandan.setQiandandate(form.getQiandandate());
            if (form.getDaijinquan().compareTo(new BigDecimal(0)) == 1) {
                qiandan.setShishouTotalMoney(form.getDingjin());
                qiandan.setIsdingjing(3);
            } else {
                qiandan.setShishouTotalMoney(zong.subtract(zhehoujine).subtract(form.getDaijinquan()));
                qiandan.setIsdingjing(1);
            }

            qiandan.setHetongMoney(zong.subtract(zhehoujine).subtract(form.daijinquan));
            qiandan.setMoneyStyle(2);
            qiandan.setPayMoneyStyle(0L);
            qiandan.setBeizhu(form.getBeizhu());
            qiandan.setQianDanStaffID(staffID);
            qiandan.setRecordInStaffID(staffID);
            qiandan.setRecordInTime(new Date());
            qiandan.setCampusID(pxstutable.getCampusID());
            qiandan.setFromType(fromType);
            qiandan.setYouhuiID(form.getYouhuizhengce());
            qiandan.setYouhuijine(zhehoujine.toString());
            qiandan.setYouhuishuoming(yhzc != null ? yhzc.getYouhuiType() == 1 ? "金额达到或超过" + yhzc.getXianzhijine() +
                    "元，" + yhzc.getYouhui() + "折优惠" :
                    "金额达到或者超过" + yhzc.getXianzhijine() + "元，满减优惠" + yhzc.getYouhui() + "元"
                    : "未优惠");
            qiandan.setQiyeID(qiyeID);
            iPxqiandaninfotableService.save(qiandan);
            List<buyZafeiVo> zafeiList = JSON.parseArray(form.getZafeiData(), buyZafeiVo.class);
            //签单其他费添加
            if (zafeiList != null) {
                for (buyZafeiVo zf : zafeiList) {
                    Pxqiandaninfo2table qd2Tab = new Pxqiandaninfo2table();
                    qd2Tab.setQianInfoTabID(qiandan.getId());
                    qd2Tab.setQianDanOtherMoneyID(zf.getZafeiID());
                    qd2Tab.setZongMoney(zf.getZafeiZongjia());
                    qd2Tab.setJiaoxueYonpingID(0L);
                    qd2Tab.setNums(new BigDecimal(1));
                    qd2Tab.setOnePrice(zf.getZafeiZongjia());
                    qd2Tab.setType(1);
                    qd2Tab.setTuiMoney(new BigDecimal(0));
                    iPxqiandaninfo2tableService.save(qd2Tab);
                }
            }
            xiugaiInfo += ",添加签单信息：实收" + form.getShishouTotalMoney().toString() + "，签单日期" + form.getQiandandate();
            List<buyWpVo> wpbuyList = JSON.parseArray(form.getWpData(), buyWpVo.class);
            if (wpbuyList != null) {
                for (buyWpVo wp : wpbuyList) {
                    Pxteachingsuppliestable wpxinxi = iPxteachingsuppliestableService.getById(wp.getId());
                    if (wp.getWpZongjia().compareTo(new BigDecimal(0)) == 1 && wpxinxi != null) {
                        if (wpxinxi.getStockNum().compareTo(wp.getWpShuliang()) == -1) {
                            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                            ajaxJson.setMsg("当前所购买的教学物品:" + wp.getWpName() + "数量不足!");
                            ajaxJson.setCode("N");
                            return ajaxJson;
                        }
                        Pxqiandansupplies qdsp = new Pxqiandansupplies();
                        qdsp.setStuID(pxstutable.getId());
                        qdsp.setTeachingSuppliesID(wpxinxi.getId());
                        qdsp.setName(wpxinxi.getName());
                        qdsp.setQiandaninfoID(qiandan.getId());
                        qdsp.setBuyPrice(wp.getWpDanjia());
                        qdsp.setBuySum(wp.getWpShuliang());
                        qdsp.setSumMoney(wp.getWpZongjia());
                        qdsp.setIsTuiFei(false);
                        qdsp.setTuiMoney(new BigDecimal(0));
                        iPxqiandansuppliesService.save(qdsp);

                        Pxteachingsuppliesouttable outjl = new Pxteachingsuppliesouttable();
                        outjl.setLuruStaffId(staffID);
                        outjl.setOutDate(new Date());
                        outjl.setOutNum(wp.getWpShuliang());
                        outjl.setOutnumBefore(wpxinxi.getStockNum());
                        outjl.setOutReason("学生购买");
                        outjl.setOutStaffId(staffID);
                        outjl.setSuppliesId(wpxinxi.getId());
                        outjl.setType(2);
                        iPxteachingsuppliesouttableService.save(outjl);
                        wpxinxi.setStockNum(wpxinxi.getStockNum().subtract(wp.getWpShuliang()));
                        iPxteachingsuppliestableService.updateById(wpxinxi);
                    }
                }
            }
            //保存签单人信息
            List<Pxqiandanstafftable> qdstaff = JSON.parseArray(form.getQiandanstaffinfo(), Pxqiandanstafftable.class);
            for (Pxqiandanstafftable pxqiandanstafftable : qdstaff) {
                Pxqiandanstafftable newstaf = new Pxqiandanstafftable();
                newstaf.setQiandanID(qiandan.getId());
                newstaf.setStaffID(pxqiandanstafftable.getStaffID());
                newstaf.setYejiMoney(pxqiandanstafftable.getYejiMoney());
                newstaf.setYejidateTime(qiandan.getQiandandate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                newstaf.setIsWeikuan(0);
                newstaf.setQiyeID(qiyeID);
                iPxqiandanstafftableService.save(newstaf);
            }
            //保存支付金额及方式
            List<Pxqiandanpaymoney> pxqiandanpaymoneyList = JSON.parseArray(form.getPaytyles(),
                    Pxqiandanpaymoney.class);
            for (Pxqiandanpaymoney pxqiandanpaymoney : pxqiandanpaymoneyList) {
                Pxqiandanpaymoney qdpm = new Pxqiandanpaymoney();
                qdpm.setPayMoney(pxqiandanpaymoney.getPayMoney());
                qdpm.setPaymoneyStyleID(pxqiandanpaymoney.getPaymoneyStyleID());
                qdpm.setQiandanID(qiandan.getId());
                qdpm.setQiyeID(qiyeID);
                qdpm.setIsWeikuan(0);
                qdpm.setQianDanDate(qiandan.getQiandandate());
                iPxqiandanpaymoneyService.save(qdpm);
            }
            //添加课程
            List<buyKechengVo> bxKcList = JSON.parseArray(form.getBxKcData(), buyKechengVo.class);
            if (bxKcList != null) {
                for (buyKechengVo buyKechengVo : bxKcList) {
                    //region 添加补习课程（新签，续费）
                    Pxbuxikechengtable buxiKc = iPxbuxikechengtableService.getById(buyKechengVo.getBuxiID());
                    if (buyKechengVo.getZJ().compareTo(new BigDecimal(0)) == 1 || buxiKc == null) {
                        Pxqiandansubjecttable qdsub = new Pxqiandansubjecttable();
                        qdsub.setStuID(pxstutable.getId());
                        qdsub.setQiandandate(form.getQiandandate());
                        qdsub.setKechengID(buyKechengVo.getKechengID());
                        qdsub.setKechengprice(buyKechengVo.getDJ());
                        qdsub.setOriginalprice(buyKechengVo.getYDJ());
                        qdsub.setBuykeshiNum(buyKechengVo.getKS());
                        qdsub.setZongjia(buyKechengVo.getZJ());
                        qdsub.setQianDanInfoID(qiandan.getId());
                        qdsub.setKechengStyle(1);
                        qdsub.setDiscount(buyKechengVo.getZK());
                        qdsub.setStartDate(ft.parse(buyKechengVo.getStartDate()));
                        qdsub.setEndDate(ft.parse(buyKechengVo.getEndDate()));
                        qdsub.setQiyeID(qiyeID);
                        iPxqiandansubjecttableService.save(qdsub);
                        //新增课程续费
                        String newkcName =
                                iPxkechengtableService.GetKechengById(buyKechengVo.getKechengID()).getKechengName();
                        xiugaiInfo += "，新增课程续费《" + newkcName + "》";

                        Long classID = 0L;
                        if (buyKechengVo.getPxStyleName() == "一对一") {
                            boolean ii = false;
                            while (ii == false) {
                                Random rd = new Random();
                                int max = 999;
                                int min = 100;
                                int sjs = rd.nextInt(max) % (max - min + 1) + min;

                                String className =
                                        pxstutable.getStuName() + "_" + buyKechengVo.getKmName() + "_一对一" + sjs;
                                List<Pxclasstable> pdclass = iPxclasstableService.GetClassByClassName(className);
                                if (pdclass.size() == 0) {
                                    Pxclasstable classtable = new Pxclasstable();
                                    classtable.setClassName(className);
                                    classtable.setCampusID(pxstutable.getCampusID());
                                    classtable.setIsShow(0);
                                    classtable.setAddStaffID(staffID);
                                    classtable.setAddTime(new Date());
                                    classtable.setIsdelete(false);
                                    classtable.setIs1v1Class(1);
                                    classtable.setAddStaffID(staffID);
                                    classtable.setAddTime(new Date());
                                    classtable.setQiyeID(qiyeID);
                                    iPxclasstableService.save(classtable);
                                    classID = classtable.getId();
                                    ii = true;
                                }
                            }
                        } else {
                            List<Pxbuxikechengtable> stuALLbuxiKC =
                                    iPxbuxikechengtableService.getbuxikechenglist(pxstutable.getId(),
                                            buyKechengVo.getBuxiID(), qiyeID);
                            Pxclasstable classTable =
                                    iPxclasstableService.GetClassByClassNameOne(buyKechengVo.getBjName());
                            boolean ishaveclass = false;
                            Long classidin = classTable == null ? 0L : classTable.getId();
                            if (stuALLbuxiKC.size() > 0) {
                                for (Pxbuxikechengtable stuOneBuxiKC : stuALLbuxiKC) {
                                    Pxstuclasstable stuIsInClass =
                                            iPxstuclasstableService.GetStuClassByBxIDAndClassID(classidin,
                                                    stuOneBuxiKC.getId());
                                    if (stuIsInClass != null) {
                                        ishaveclass = true;
                                    }
                                }
                            }
                            if (classTable != null && ishaveclass == false) {
                                classID = classTable.getId();
                            }
                        }

                        Pxbuxikechengtable buxi = new Pxbuxikechengtable();
                        buxi.setStuID(pxstutable.getId());
                        buxi.setKechengID(buyKechengVo.getKechengID());
                        buxi.setKechengprice(buyKechengVo.getDJ());
                        buxi.setOriginalprice(buyKechengVo.getYDJ());
                        buxi.setKeshiNum(buyKechengVo.getKS());
                        buxi.setRemainkeshi(buyKechengVo.getKS());
                        buxi.setZongjia(buyKechengVo.getZJ());
                        xiugaiInfo += ",新增课程续费详情，原单价" + buyKechengVo.getYDJ().toString() + ",单价" + buyKechengVo.getDJ().toString() + ",课时" + buyKechengVo.getKS().toString() + ",课程总价" + buyKechengVo.getZJ().toString();
                        buxi.setStartDate(ft.parse(buyKechengVo.getStartDate()));
                        buxi.setEndDate(ft.parse(buyKechengVo.getEndDate()));
                        buxi.setBuykeshiDateTime(qiandan.getQiandandate());
                        buxi.setIsShow(0);
                        buxi.setJifeiStyleID(buyKechengVo.getJifeistyle());
                        buxi.setQianDanInfoID(qiandan.getId());
                        buxi.setQianDanSubjectID(qdsub.getId());
                        buxi.setType(1);
                        buxi.setQiyeID(qiyeID);
                        iPxbuxikechengtableService.save(buxi);
                        Pxbxkcchangetable buxichange = new Pxbxkcchangetable();
                        buxichange.setAddDate(new Date());
                        buxichange.setAddStaffID(staffID);
                        buxichange.setOldbxkcID(buxi.getId());
                        buxichange.setOldbxkcName(iPxkechengtableService.getById(buxi.getKechengID()).getKechengName());
                        buxichange.setOldendDate(buxi.getEndDate());
                        buxichange.setOldkcID(buxi.getKechengID());
                        buxichange.setOldprice(buxi.getKechengprice());
                        buxichange.setOldqiandanID(qiandan.getId());
                        buxichange.setOldrkeshi(buxi.getRemainkeshi());
                        buxichange.setOldstartDate(buxi.getStartDate());
                        buxichange.setOldStuID(qiandan.getStuID());
                        buxichange.setOldzongjia(buxi.getZongjia());
                        buxichange.setType(2);
                        buxichange.setQiyeID(qiyeID);
                        iPxbxkcchangetableService.save(buxichange);
                        //插班
                        if (classID != 0L) {
                            Pxstuclasstable stuclass = new Pxstuclasstable();
                            stuclass.setBuxiID(buxi.getId());
                            stuclass.setClassID(classID);
                            iPxstuclasstableService.save(stuclass);
                            //如果新加入的班级原有排课了、考勤状态是未完成状态，再把学生加在选课表里面去

                            if (buyKechengVo.getPkid() != null) {
                                Pxpaiketable checkPK = iPxpaiketableService.getById(buyKechengVo.getPkid()); //选中的排课
                                List<Pxpaiketable> paikenews =
                                        iPxpaiketableService.list(new QueryWrapper<Pxpaiketable>()
                                                .eq("classID", checkPK.getClassID())
                                                .ge("haveClassDate", checkPK.getHaveClassDate())
                                                .ge("startLessonDateTime", checkPK.getStartLessonDateTime())
                                                .eq("qiyeID", loginUser.getQiyeID())
                                                .orderByAsc("haveClassDate")
                                        );
                                for (Pxpaiketable pxpaiketable : paikenews) {
                                    List<Pxxuanketable> pdxuankeTab =
                                            iPxxuanketableService.GetAllXuankeBypkIDAndStuIDList(pxpaiketable.getId(),
                                                    buxi.getStuID());
                                    if (pdxuankeTab.size() == 0) {
                                        Pxxuanketable xuanke = new Pxxuanketable();
                                        xuanke.setBuxiID(buxi.getId());
                                        xuanke.setPaikeID(pxpaiketable.getId());
                                        xuanke.setStuID(buxi.getStuID());
                                        xuanke.setRecordDate(new Date());
                                        xuanke.setType(1);
                                        iPxxuanketableService.save(xuanke);
                                    }
                                }
                            }
                        }
                        //续费课时赠送
                        if (buyKechengVo.getZKS().compareTo(new BigDecimal(0)) == 1) {
                            //添加赠送记录
                            Pxkeshizengsongtable keshizengsong = new Pxkeshizengsongtable();
                            keshizengsong.setKechengID(buyKechengVo.getKCID());
                            keshizengsong.setKechengPrice(buyKechengVo.getDJ());
                            keshizengsong.setKeshiShu(buyKechengVo.getZKS());
                            keshizengsong.setSongYangyin("签单课时赠送");
                            keshizengsong.setJifeiStyle(iPxkechengtableService.getById(buyKechengVo.getKechengID()).getJifeiStyleID());
                            keshizengsong.setStuID(pxstutable.getId());
                            keshizengsong.setAddDate(new Date());
                            keshizengsong.setCaozuoStaffId(staffID);
                            keshizengsong.setQiandanInfoID(qiandan.getId());
                            iPxkeshizengsongtableService.save(keshizengsong);

                            Pxbuxikechengtable pdbuxikc =
                                    iPxbuxikechengtableService.GetBuxikecheng(pxstutable.getId(),
                                            buyKechengVo.getKCID(),
                                            buyKechengVo.getDJ(), qiyeID);
                            if (pdbuxikc != null) {
                                pdbuxikc.setRemainkeshi(pdbuxikc.getRemainkeshi().add(buyKechengVo.getZKS()));
                                pdbuxikc.setKeshiNum(pdbuxikc.getKeshiNum().add(buyKechengVo.getZKS()));
                                iPxbuxikechengtableService.updateById(pdbuxikc);
                            } else {
                                //添加赠送补习课程记录
                                Pxbuxikechengtable zsbxTab = new Pxbuxikechengtable();
                                zsbxTab.setStuID(pxstutable.getId());
                                zsbxTab.setKechengID(buyKechengVo.getKCID());
                                zsbxTab.setKechengprice(buyKechengVo.getDJ());
                                zsbxTab.setOriginalprice(buyKechengVo.getYDJ());
                                zsbxTab.setRemainkeshi(buyKechengVo.getZKS());
                                zsbxTab.setKeshiNum(buyKechengVo.getZKS());
                                zsbxTab.setZongjia(new BigDecimal(0));
                                zsbxTab.setStartDate(ft.parse(buyKechengVo.getStartDate()));
                                zsbxTab.setEndDate(ft.parse(buyKechengVo.getEndDate()));
                                zsbxTab.setBuykeshiDateTime(new Date());
                                zsbxTab.setIsShow(0);
                                zsbxTab.setJifeiStyleID(buyKechengVo.getJifeistyle());
                                zsbxTab.setType(2);    //赠送的
                                zsbxTab.setQianDanInfoID(qiandan.getId());
                                zsbxTab.setQianDanSubjectID(qdsub.getId());
                                iPxbuxikechengtableService.save(zsbxTab);

                                Pxbxkcchangetable bxcg = new Pxbxkcchangetable();
                                bxcg.setOldbxkcID(zsbxTab.getId());
                                bxcg.setOldbxkcName(iPxkechengtableService.getById(zsbxTab.getKechengID()).getKechengName());
                                bxcg.setOldendDate(zsbxTab.getEndDate());
                                bxcg.setOldkcID(zsbxTab.getKechengID());
                                bxcg.setOldprice(zsbxTab.getKechengprice());
                                bxcg.setOldqiandanID(qiandan.getId());
                                bxcg.setOldrkeshi(zsbxTab.getRemainkeshi());
                                bxcg.setOldstartDate(zsbxTab.getStartDate());
                                bxcg.setOldStuID(qiandan.getStuID());
                                bxcg.setOldzongjia(zsbxTab.getZongjia());
                                bxcg.setType(9);
                                bxcg.setAddDate(new Date());
                                bxcg.setAddStaffID(staffID);
                                iPxbxkcchangetableService.save(bxcg);

                                Pxkechengtable kechengTemp = iPxkechengtableService.getById(zsbxTab.getKechengID());
                                String buxiStyleNameTemp =
                                        iPxbuxistyletableService.getById(kechengTemp.getBuxiStyleID()).getBuxiStyleName();
                                if (buxiStyleNameTemp == "一对一") {
                                    Long classIDtemp = 0L;
                                    String subjectName =
                                            iPxsubjecttableService.getById(kechengTemp.getSubjectID()).getSubjectName();
                                    //建班插班
                                    boolean ii = false;
                                    while (ii == false) {
                                        Random rd = new Random();
                                        int max = 999;
                                        int min = 100;
                                        int sjs = rd.nextInt(max) % (max - min + 1) + min;
                                        String className = "(赠送)" + pxstutable.getStuName() + "_" + subjectName + "_" +
                                                "一对一" + sjs;
                                        List<Pxclasstable> pdclass =
                                                iPxclasstableService.GetClassByClassName(className);
                                        if (pdclass.size() == 0) {
                                            Pxclasstable classtable = new Pxclasstable();
                                            classtable.setClassName(className);
                                            classtable.setCampusID(pxstutable.getCampusID());
                                            classtable.setAddStaffID(staffID);
                                            classtable.setAddTime(new Date());
                                            classtable.setIsdelete(false);
                                            classtable.setIsShow(1);
                                            classtable.setQiyeID(qiyeID);
                                            classtable.setIs1v1Class(1);
                                            iPxclasstableService.save(classtable);
                                            classIDtemp = classtable.getId();
                                            ii = true;
                                        }
                                    }
                                    xiugaiInfo += "，是一对一课程，完成建班";

                                    //如果是一对一，要插班
                                    Pxstuclasstable stucla = new Pxstuclasstable();
                                    stucla.setBuxiID(zsbxTab.getId());
                                    stucla.setClassID(classIDtemp);
                                    stucla.setQiyeID(qiyeID);
                                    iPxstuclasstableService.save(stucla);
                                    xiugaiInfo += "，一对一课程，完成插班";
                                }
                            }
                        }
                    }
                    //endregion
                }
            }
            if (form.getDaijinquan().compareTo(new BigDecimal(0)) == 1) {
                Pxdaijinquantable djq = new Pxdaijinquantable();
                djq.setCreatTime(new Date());
                djq.setMoney(form.getDaijinquan());
                djq.setQiandanID(qiandan.getId());
                djq.setStaffID(staffID);
                djq.setStuID(qiandan.getStuID());
                iPxdaijinquantableService.save(djq);
                xiugaiInfo += ",使用代金券：" + form.getDaijinquan().toString();
            }
            String campusName = iPxcampustableService.getById(pxstutable.getCampusID()).getCampusName();
            //不是用余额支付的，才添加流水；
            List<Pxqiandanpaymoney> pst = JSON.parseArray(form.getPaytyles(), Pxqiandanpaymoney.class);
            for (Pxqiandanpaymoney pxqiandanpaymoney : pst) {
                //使用系统余额进行支付，扣除余额
                if (pxqiandanpaymoney.getPaymoneyStyleID() != 3) {
                    List<Pxliushuizhangtable> lsz = iPxliushuizhangtableService.GetQiandanLiushuiList(qiandan.getId());
                    if (form.getDingjin().compareTo(new BigDecimal(0)) == 1 && lsz.size() == 0) {
                        Pxliushuizhangtable liushui = new Pxliushuizhangtable();
                        Random random = new Random();
                        int max = 999;
                        int min = 100;
                        int sjs = random.nextInt(max) % (max - min + 1) + min;
                        liushui.setId(Long.parseLong(DateUtil.formatDate11(new Date()) + pxqiandanpaymoney.getPaymoneyStyleID() + sjs));
                        liushui.setLiushuiDateTime(qiandan.getQiandandate());
                        liushui.setCampusID(pxstutable.getCampusID());
                        liushui.setPayMoneyStyle(pxqiandanpaymoney.getPaymoneyStyleID());
                        liushui.setZhichuMoney(new BigDecimal(0));
                        liushui.setShouzhiStyleID(Long.valueOf(form.getMoneyStyle()));
                        liushui.setJinbanRen(staffID);
                        liushui.setStuID(pxstutable.getId());
                        liushui.setQiandanID(qiandan.getId());
                        liushui.setLuruTime(new Date());
                        String zyText = "";
                        if (StringUtils.isNotBlank(form.getBeizhu())) {
                            zyText = "说明：" + form.getBeizhu();
                        }

                        liushui.setShouruMoney(form.getDingjin());
                        liushui.setLiushuiZaiyao(campusName + "校区" + pxstutable.getStuName() + "同学，定金交费！--" + zyText);
                        xiugaiInfo += ",添加财务流水金额" + form.getDingjin().toString() + ",流水号" + liushui.getId();

                        iPxliushuizhangtableService.save(liushui);
                        logtext += ",学员定金交费，流水ID：" + liushui.getId();
                    } else {
                        Pxliushuizhangtable liushui = new Pxliushuizhangtable();
                        Random random = new Random();
                        liushui.setLiushuiDateTime(qiandan.getQiandandate());
                        liushui.setCampusID(pxstutable.getCampusID());
                        liushui.setPayMoneyStyle(pxqiandanpaymoney.getPaymoneyStyleID());
                        liushui.setZhichuMoney(new BigDecimal(0));
                        liushui.setShouzhiStyleID(Long.valueOf(form.getMoneyStyle()));
                        liushui.setJinbanRen(staffID);
                        liushui.setStuID(pxstutable.getId());
                        liushui.setQiandanID(qiandan.getId());
                        liushui.setLuruTime(new Date());
                        liushui.setQiyeID(qiyeID);

                        String zyText = "";
                        if (StringUtils.isNotBlank(form.getBeizhu())) {
                            zyText = "说明：" + form.getBeizhu();
                        }
                        liushui.setShouruMoney(pxqiandanpaymoney.getPayMoney());
                        liushui.setLiushuiZaiyao(campusName + "校区" + pxstutable.getStuName() + "同学，全款交费！--" + zyText);
                        liushui.setAddStaffID(staffID);
                        xiugaiInfo += ",添加财务流水金额" + pxqiandanpaymoney.getPayMoney().toString() + ",流水号" + liushui.getId();
                        iPxliushuizhangtableService.save(liushui);
                        logtext += ",流水ID：" + liushui.getId();
                    }

                }
            }
            //endregion
            ajaxJson.setSuccess(true);

        }


        savePxLog.savepxlog(logtext, "xwcloud-zsbm/zsbm/BaoMingJiaoFei" +
                "/AddXufeiQiandan", loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());

        return ajaxJson;
    }

    /**
     * 查询所有续费可选择学生信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetAllxufeistuList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询所有续费可选择学生信息")
    public AjaxJson GetAllxufeistuList(HttpServletRequest request, long menuID) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        AjaxJson ajaxJson = new AjaxJson();

        QueryWrapper searchdata = new QueryWrapper();
        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", menuID);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        searchdata.eq("a.qiyeID", loginUser.getQiyeID());
        if (lookPower.equals("0")) {//个人权限
            searchdata.eq("a.campusID ", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            searchdata.eq("a.campusID ", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            searchdata.in("a.campusID ", lookPower);
        }
        searchdata.notIn("a.buxiStateID", "1,7");
        ajaxJson.setObj(iPxstutableService.GetAllXufeistuList(searchdata));
        return ajaxJson;
    }

    /**
     * 查询学生所有购买课程信息
     *
     * @param stuID
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetAllStukechengInfoList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询学生所有购买课程信息")
    public AjaxJson GetAllStukechengInfoList(long stuID, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxbuxikechengtableService.GetAllStukechengInfoList(stuID, qiyeID));
        return ajaxJson;
    }
    //endregion

    //region 补交尾款


    @ApiOperation("补交尾款时获取签单信息")
    @ResponseBody
    @GetMapping(value = "getqiandanMessage")
    public AjaxJson getqiandanMessage(String qdID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        JSONObject jsonObject = new JSONObject();

        List<HashMap<String, Object>> yejiren = iPxqiandanstafftableService.getyejitrentoPayweikuan(
                new QueryWrapper<Pxqiandanstafftable>()
                        .eq("a.qiandanID", qdID)
                        .eq("a.qiyeID", loginUser.getQiyeID())
        );

        List<HashMap<String, Object>> paystyle = iPxqiandanpaymoneyService.getPaystyletoPayweikuan(
                new QueryWrapper<Pxqiandanstafftable>()
                        .eq("a.qiandanID", qdID)
                        .eq("a.qiyeID", loginUser.getQiyeID())
        );
        jsonObject.put("yejiren", yejiren);
        jsonObject.put("paystyle", paystyle);
        ajaxJson.setObj(jsonObject);
        return ajaxJson;
    }

    @ResponseBody
    @PostMapping("setweikuan")
    @ApiOperation("补交尾款")
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson setweikuan(
            @RequestBody weikuanForm form,
            HttpServletRequest request
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String qdid = form.getQdid(); //签单ID
        BigDecimal jine = form.getJine(); //补交金额
        String payMoneyStylemessage = form.getPaymoneystyle();//支付方式
        String yejimessage = form.getYejimessage();
        Date bjDate = null;
        try {
            bjDate = sdf.parse(form.getBujiaodate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Pxqiandaninfotable qd = iPxqiandaninfotableService.getById(qdid);
        if (qd == null) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("找不到签单信息");
            return ajaxJson;
        }
        Pxstutable stu = iPxstutableService.getById(qd.getStuID());
        String stuInfo = "学号: " + qd.getStuID().toString() + "姓名:" + stu.getStuName();
        if (qd.getHetongMoney().compareTo(qd.getShishouTotalMoney().add(jine)) == -1) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("补交的尾款过多");
            return ajaxJson;
        }

        String xiugaiInfo = "【补交尾款】";
        xiugaiInfo += "，补交尾款前，实收总金额为：" + qd.getShishouTotalMoney().toString();
        xiugaiInfo += "，补交尾款金额：" + jine.toString();
        qd.setShishouTotalMoney(qd.getShishouTotalMoney().add(jine));
        iPxqiandaninfotableService.updateById(qd);
        xiugaiInfo += "，补交尾款后，实收总金额为：" + qd.getShishouTotalMoney().toString();

        List<weikuanVo> wkMessage = JSON.parseArray(yejimessage, weikuanVo.class);
        xiugaiInfo += "，补交尾款前，学员剩余学费为：" + stu.getRemainXuefei().toString();

        String zf = iPxqiandaninfo2tableService.getzf(new QueryWrapper<Pxqiandaninfo2table>()
                .eq("a.qianInfoTabID", qd.getId())
                .eq("a.qiyeID", loginUser.getQiyeID())
        );
        BigDecimal qdzf = new BigDecimal(zf);
        String wp = iPxqiandansuppliesService.getWP(new QueryWrapper<Pxqiandansupplies>()
                .eq("a.QiandaninfoID", qdid)
                .eq("a.qiyeID", loginUser.getQiyeID())
        );
        BigDecimal qdWP = new BigDecimal(wp);
        BigDecimal zong = new BigDecimal(0);
        zong = qdzf.add(qdWP);
        if (qd.getShishouTotalMoney() == qd.getHetongMoney()) {
            stu.setRemainXuefei(jine.subtract(zong));
        } else {
            stu.setRemainXuefei(stu.getRemainXuefei().add(jine));
        }
        iPxstutableService.updateById(stu);
        xiugaiInfo += "，补交尾款后，学员剩余学费为：" + stu.getRemainXuefei().toString();
        if (qd.getShishouTotalMoney() == qd.getHetongMoney()) {
            qd.setIsdingjing(2);
        } else {
            qd.setIsdingjing(4);
        }

        //保存签单人信息
        for (weikuanVo item : wkMessage) {
            Pxqiandanstafftable qdstaff = new Pxqiandanstafftable();
            qdstaff.setQiandanID(qd.getId());
            qdstaff.setIsWeikuan(1); //尾款
            qdstaff.setStaffID(Long.valueOf(item.getYejiren()));
            qdstaff.setYejiMoney(item.getYejimoney());
            qdstaff.setYejidateTime(bjDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            qdstaff.setQiyeID(loginUser.getQiyeID());
            iPxqiandanstafftableService.save(qdstaff);
        }

        List<weikuanPayVo> wkPaymessage = JSON.parseArray(payMoneyStylemessage, weikuanPayVo.class);
        for (weikuanPayVo item : wkPaymessage) {
            Pxqiandanpaymoney qdpay = iPxqiandanpaymoneyService.getOne(new QueryWrapper<Pxqiandanpaymoney>()
                    .eq("qiandanID", qd.getId())
                    .eq("paymoneyStyleID", item.getPaystyle())
                    .eq("qiyeID", loginUser.getQiyeID())
            );
            if (qdpay == null) {
                Pxqiandanpaymoney qdpm = new Pxqiandanpaymoney();
                qdpm.setQiandanID(qd.getId());
                qdpm.setPaymoneyStyleID(Long.valueOf(item.getPaystyle()));
                qdpm.setPayMoney(item.getPaymoney());
                qdpm.setQianDanDate(bjDate);
                qdpm.setIsWeikuan(1);
                qdpm.setQiyeID(loginUser.getQiyeID());
                iPxqiandanpaymoneyService.save(qdpm);
            } else {
                qdpay.setPayMoney(qdpay.getPayMoney().add(item.getPaymoney()));
                iPxqiandanpaymoneyService.updateById(qdpay);
            }
            if (item.getPaystyle().equals(4)) {
                List<Pxchongzhipaytable> yue = iPxchongzhipaytableService.list(new QueryWrapper<Pxchongzhipaytable>()
                        .eq("stuID", stu.getId())
                        .eq("qiyeID", loginUser.getQiyeID())
                );
                if (yue.size() == 0) {
                    ajaxJson.setMsg("没有充值余额记录");
                    ajaxJson.setCode("N");
                    return ajaxJson;
                }
                if (stu.getRemainChongzhi().compareTo(item.getPaymoney()) == -1) {
                    //剩余金额<支付金额
                    ajaxJson.setMsg("充值余额不足");
                    ajaxJson.setCode("N");
                    return ajaxJson;
                }

                xiugaiInfo += "，用充值余额支付,支付前的充值余额为" + stu.getRemainChongzhi().toString();
                stu.setRemainChongzhi(stu.getRemainChongzhi().subtract(item.getPaymoney()));
                iPxstutableService.updateById(stu);
                xiugaiInfo += "，支付后的充值余额为" + stu.getRemainChongzhi().toString();

                Pxchongzhipaytable czpay = new Pxchongzhipaytable();
                czpay.setStuID(stu.getId());
                czpay.setChongzhiPayMoney(item.getPaymoney());
                czpay.setType(2);
                czpay.setBeizhu("补交尾款，余额支付支出");
                czpay.setQiandanID(qd.getId());
                czpay.setAddStaffID(loginUser.getStaffID());
                czpay.setAddTime(new Date());
                czpay.setQiyeID(loginUser.getQiyeID());
                iPxchongzhipaytableService.save(czpay);
            } else {
                Long moneyStyle = Long.valueOf(qd.getMoneyStyle());
                String note = "";
                if (moneyStyle == 1) {
                    note = "新签";
                } else if (moneyStyle == 1) {
                    note = "续费";
                }
                Pxliushuizhangtable ls = new Pxliushuizhangtable();
                ls.setLiushuiDateTime(new Date());
                ls.setCampusID(qd.getCampusID());
                ls.setLiushuiZaiyao(note + stu.getStuName() + "补交尾款");
                ls.setPayMoneyStyle(Long.valueOf(item.getPaystyle()));
                ls.setShouruMoney(item.getPaymoney());
                ls.setZhichuMoney(BigDecimal.valueOf(0));
                ls.setShouzhiStyleID(3L); //尾款
                ls.setJinbanRen(qd.getRecordInStaffID());
                ls.setStuID(stu.getId());
                ls.setQiandanID(qd.getId());
                ls.setLiushuiDateTime(new Date());
                ls.setLuruTime(new Date());
                ls.setQiyeID(loginUser.getQiyeID());
                ls.setAddStaffID(loginUser.getStaffID());
                iPxliushuizhangtableService.save(ls);
                xiugaiInfo += "，向流水账号插入记录" + ls.getId();
            }
        }

        ajaxJson.setMsg(stuInfo + "|" + xiugaiInfo);

        savePxLog.savepxlog(stuInfo + xiugaiInfo, "xwcloud-zsbm/zsbm/BaoMingJiaoFei" +
                "/setweikuan", loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());
        return ajaxJson;
    }
    //enregion

    //region 充值信息

    /**
     * 查询学生充值余额信息
     *
     * @param size
     * @param current
     * @param campusID
     * @param stuName
     * @param stuGradeID
     * @param zidingyiStuID
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetStuYuePages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("查询学生充值余额信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "campusName", value = "校区名称", required = false),
            @ApiImplicitParam(name = "stuName", value = "学生姓名", required = false)
    })
    public AjaxJson GetStuYuePages(long size, long current, long campusID, String stuName, long stuGradeID,
                                   String zidingyiStuID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Page<StuYueInfoVo> page = new Page(current, size);
        QueryWrapper<StuYueInfoVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (campusID != 0) {
            queryWrapper.eq("a.campusID", campusID);
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("stuName", stuName);
        }
        if (stuGradeID != 0) {
            queryWrapper.eq("a.stuGradeID", stuGradeID);
        }
        if (StringUtils.isNotBlank(zidingyiStuID)) {
            queryWrapper
                    .eq("a.id", zidingyiStuID)
                    .or(a -> a.like("a.zidingyiStuID", zidingyiStuID));
        }
        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", 143);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }
        ajaxJson.setObj(iPxqiandaninfotableService.GetAllStuYuePages(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * 查询对应用户的充值记录
     *
     * @param size
     * @param current
     * @param stuID
     * @return
     */
    @RequestMapping(value = "/GetStuChongzhiPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("查询用户充值记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "stuID", value = "学生ID", required = false)
    })
    public AjaxJson GetStuChongzhiPages(HttpServletRequest request, long size, long current, String stuID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<chongzhiListVo> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("1", 1);
        queryWrapper.eq("stuID", stuID);
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        ajaxJson.setObj(iPxchongzhitableService.GetUserChongzhiListPages(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * 查询学生充值余额使用记录
     *
     * @param size
     * @param current
     * @param stuID
     * @return
     */
    @RequestMapping(value = "/GetStuChongzhiPayPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("查询学生充值余额使用记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "stuID", value = "学生ID", required = true)
    })
    public AjaxJson GetStuChongzhiPayPages(HttpServletRequest request, long size, long current, String stuID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<chongzhiPayListVo> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("stuID", stuID);
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());

        ajaxJson.setObj(iPxchongzhipaytableService.GetUserChongzhiPayListPages(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * 分页查询充值详情
     *
     * @param size
     * @param current
     * @param stuID
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetUserChongzhixiangqingPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页查询充值详情")
    public AjaxJson GetUserChongzhixiangqingPages(long size, long current, long stuID, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        AjaxJson ajaxJson = new AjaxJson();
        Page<chongzhixiangqingVO> page = new Page(current, size);
        ajaxJson.setObj(iPxchongzhipaytableService.GetUserChongzhixiangqingPages(page, qiyeID, stuID));
        return ajaxJson;
    }
    //region 充值优惠政策

    /**
     * 分页获取充值优惠政策
     *
     * @param size
     * @param current
     * @return
     */
    @RequestMapping(value = "/GetczyouhuizhengcePages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("分页获取充值优惠政策")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
    })
    public AjaxJson GetczyouhuizhengcePages(long size, long current, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", qiyeID);
        Page<Pxczhuodongtable> page = new Page<>(current, size);
        ajaxJson.setObj(iPxczhuodongtableService.page(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * 保存充值优惠政策
     *
     * @param request
     * @param form
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/AddChongzhiyouhuizhengce", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("保存充值优惠政策")
    public AjaxJson AddChongzhiyouhuizhengce(HttpServletRequest request, @RequestBody czyhzcForm form) throws ParseException {
        AjaxJson ajaxJson = new AjaxJson();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        Pxczhuodongtable pxczhuodongtable = new Pxczhuodongtable();
        pxczhuodongtable.setAddStaffID(staffID);
        pxczhuodongtable.setAddTime(new Date());
        pxczhuodongtable.setEdate(ft.parse(form.getEdate()));
        pxczhuodongtable.setSdate(ft.parse(form.getSdate()));
        pxczhuodongtable.setHuodongmoney(form.getHuodongmoney());
        pxczhuodongtable.setQiyeID(qiyeID);
        pxczhuodongtable.setType(1);
        pxczhuodongtable.setZongmoney(form.getZongmoney());
        ajaxJson.setSuccess(iPxczhuodongtableService.save(pxczhuodongtable));

        savePxLog.savepxlog("添加了充值优惠政策,ID:" + pxczhuodongtable.getId(), "xwcloud-zsbm/zsbm/BaoMingJiaoFei" +
                        "/AddChongzhiyouhuizhengce", loginUser.getStaffID(), loginUser.getStaffName(), 1,
                loginUser.getQiyeID());
        return ajaxJson;
    }

    /**
     * 修改充值优惠政策
     *
     * @param form
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/UpdateChongzhiyouhuizhengce", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("修改充值优惠政策")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "修改活动ID", required = true),
            @ApiImplicitParam(name = "zongmoney", value = "总金额", required = true),
            @ApiImplicitParam(name = "huodongmoney", value = "活动金额", required = true),
            @ApiImplicitParam(name = "Sdate", value = "活动开始日期", required = true),
            @ApiImplicitParam(name = "Edate", value = "活动结束日期", required = true)
    })
    public AjaxJson UpdateChongzhiyouhuizhengce(@RequestBody czyhzcForm form, HttpServletRequest request) throws ParseException {
        AjaxJson ajaxJson = new AjaxJson();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        Pxczhuodongtable pxczhuodongtable = iPxczhuodongtableService.getById(form.getId());
        pxczhuodongtable.setZongmoney(form.getZongmoney());
        pxczhuodongtable.setHuodongmoney(form.getHuodongmoney());
        pxczhuodongtable.setSdate(ft.parse(form.getSdate()));
        pxczhuodongtable.setEdate(ft.parse(form.getEdate()));
        ajaxJson.setSuccess(iPxczhuodongtableService.updateById(pxczhuodongtable));

        savePxLog.savepxlog("修改了充值优惠政策，ID：" + pxczhuodongtable.getId(), "xwcloud-zsbm/zsbm/BaoMingJiaoFei" +
                        "/UpdateChongzhiyouhuizhengce", loginUser.getStaffID(), loginUser.getStaffName(), 1,
                loginUser.getQiyeID());
        return ajaxJson;
    }

    /**
     * 删除充值优惠活动信息
     *
     * @param Id
     * @return
     */
    @RequestMapping(value = "/DeleteCzhuodong", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation("删除充值优惠活动信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Id", value = "删除数据ID", required = true)
    })
    public AjaxJson DeleteCzhuodong(String Id, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setSuccess(iPxczhuodongtableService.removeById(Id));

        savePxLog.savepxlog("删除了ID为:" + Id + "的充值优惠政策", "xwcloud-zsbm/zsbm/BaoMingJiaoFei" +
                        "/DeleteCzhuodong", loginUser.getStaffID(), loginUser.getStaffName(), 1,
                loginUser.getQiyeID());
        return ajaxJson;
    }

    @RequestMapping(value = "/exportChongzhiInfo", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("导出充值信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
    })
    public void exportChongzhiInfo(HttpServletResponse response,
                                   @RequestParam(required = false) String campusID // 校区ID
    ) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("1", 1);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("campusID", campusID);
        }
        List<StuyueVo> stuVoList = iPxstutableService.getAllStuyueList();
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "姓名", "家长电话号码", "充值账户余额"},
                stuVoList,
                new String[]{"campusName", "stuName", "parentTel", "remainChongzhi"});
        ExportExcel.ExcelSource source = new ExportExcel.ExcelSource();
        source.setSheetName("充值信息");
        source.setTableData(list);
        List<ExportExcel.ExcelSource> sourceList = new ArrayList<>();
        sourceList.add(source);
        //充值流水
        List<chongzhiListVo> chongzhiList = iPxchongzhitableService.GetUserChongzhiListList(queryWrapper);
        List<List<Object>> DetailedList = ExportExcel.formatDataToList(new String[]{"校区", "姓名", "充值金额", "赠送金额",
                        "实得金额", "经办人", "经办时间"},
                chongzhiList,
                new String[]{"campusName", "stuName", "shijiChongzhiMoney", "songMoney", "shideTotalMoney",
                        "staffName", "chongzhiDatetime-DT"});
        ExportExcel.ExcelSource sourcedetaile = new ExportExcel.ExcelSource();
        sourcedetaile.setSheetName("充值流水");
        sourcedetaile.setTableData(DetailedList);
        sourceList.add(sourcedetaile);
        //充值消费流水
        List<chongzhiPayListVo> chongzhipayList = iPxchongzhipaytableService.GetUserChongzhiPayListList(queryWrapper);
        List<List<Object>> DetailedList1 = ExportExcel.formatDataToList(new String[]{"校区", "姓名", "支付金额", "备注", "经办人",
                        "经办时间"},
                chongzhipayList,
                new String[]{"campusName", "stuName", "chongzhiPayMoney", "beizhu", "staffName", "addTime-DT"});
        ExportExcel.ExcelSource sourcedetaile1 = new ExportExcel.ExcelSource();
        sourcedetaile.setSheetName("充值消费流水");
        sourcedetaile.setTableData(DetailedList1);
        sourceList.add(sourcedetaile1);
        try {
            // 需要将详细表一起导出
            ExportExcel.exportMultipleSheetExcel(response, sourceList, "充值信息.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //endregion

    /**
     * 保存新生充值信息
     *
     * @param form
     * @return
     */
    @RequestMapping(value = "/AddNewStuChongzhi", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("保存新生充值")
    public AjaxJson AddNewStuChongzhi(@RequestBody AddChongzhiForm form, HttpServletRequest request) throws ParseException {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("stuName", form.getStuName());
        queryWrapper.gt("buxiStateID", 1);
        List<Pxstutable> pxstaffposttableList = iPxstutableService.list(queryWrapper);

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();

        String logtext = "【新生充值】";
        if (pxstaffposttableList.size() > 0) {
            ajaxJson.setMsg("已存在该姓名的学生");
            ajaxJson.setCode("N");
            return ajaxJson;
        } else {
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            Pxstutable newstu = new Pxstutable();
            if (form.getYxid() != 0L) {
                newstu = iPxstutableService.GetYixiangStuByID(form.getYxid());
            }
            newstu.setStuName(form.getStuName());
            newstu.setParentTel(form.getStuTel());
            newstu.setCampusID(form.getCampusID());
            newstu.setStuGradeID(form.getStuGradeId());
            newstu.setBuxiStateID(form.getStuState());
            newstu.setJifenNum(new BigDecimal(0));
            newstu.setRemainXuefei(new BigDecimal(0));
            if (StringUtils.isNotBlank(form.getStuBirthday())) {
                try {
                    newstu.setStubirth(ft.parse(form.getStuBirthday()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            newstu.setDengjiTime(new Date());
            if (StringUtils.isNotBlank(form.getStuIdentityNumber())) {
                newstu.setIDnumber(form.getStuIdentityNumber());
            }
            if (StringUtils.isNotBlank(form.getOldSchoolID())) {
                Pxoldschooltable school = iPxoldschooltableService.GetOldSchoolByName(form.getOldSchoolID());
                if (school != null) {
                    newstu.setOldSchool(school.getOldSchoolID());
                    if (StringUtils.isNotBlank(form.getOldSchoolTeacherID())) {
                        Pxoldschoolteachertable teacher =
                                iPxoldschoolteachertableService.GetOldschoolteacherBytnameAndsID(form.getOldSchoolTeacherID(), school.getOldSchoolID());
                        if (teacher != null) {
                            newstu.setOldSchoolTeacher(teacher.getOldSchoolTeacherID());
                        } else {
                            Pxoldschoolteachertable tea = new Pxoldschoolteachertable();
                            tea.setOldSchoolID(school.getOldSchoolID());
                            tea.setOldSchoolTeacherName(form.getOldSchoolTeacherID());
                            tea.setQiyeID(qiyeID);
                            iPxoldschoolteachertableService.save(tea);
                            newstu.setOldSchoolTeacher(tea.getOldSchoolTeacherID());
                        }
                    }
                } else {
                    Pxoldschooltable sch = new Pxoldschooltable();
                    sch.setOldSchoolName(form.getOldSchoolID());
                    sch.setQiyeID(qiyeID);
                    iPxoldschooltableService.save(sch);
                    newstu.setOldSchool(sch.getOldSchoolID());

                    Pxoldschoolteachertable tea = new Pxoldschoolteachertable();
                    tea.setOldSchoolID(sch.getOldSchoolID());
                    tea.setOldSchoolTeacherName(form.getOldSchoolTeacherID());
                    tea.setQiyeID(qiyeID);
                    iPxoldschoolteachertableService.save(tea);
                    newstu.setOldSchoolTeacher(tea.getOldSchoolTeacherID());
                }
            }

            newstu.setStuTel(form.getStuTel());
            newstu.setQiyeID(qiyeID);
            newstu.setActivity(1);
            newstu.setRemainChongzhi(form.getChongzhiMoney().add(form.getZengsongMoney()));
            newstu.setPasswd("123456");
            newstu.setParentTelRelation("1");
            newstu.setLuruType(3);
            newstu.setDengjiTime(new Date());
            newstu.setDengjiTeacherID(form.getYeJiRenId());
            newstu.setQiyeID(qiyeID);
            //存在意向学员，直接修改学员信息，否则新增学员信息
            if (form.getYxid() != 0L) {
                iPxstutableService.updateById(newstu);
            } else {
                iPxstutableService.save(newstu);
            }

            Pxchongzhitable pxchongzhitable = new Pxchongzhitable();
            pxchongzhitable.setStuID(newstu.getId());
            pxchongzhitable.setShijiChongzhiMoney(form.getChongzhiMoney());
            pxchongzhitable.setSongMoney(form.getZengsongMoney());
            pxchongzhitable.setShideTotalMoney(form.getChongzhiMoney().add(form.getZengsongMoney()));
            pxchongzhitable.setShuoming("新学员充值");
            if (form.getPayMoneyStyleId().equals("-1")) {
                pxchongzhitable.setPayMoneyStyle("-1");
                pxchongzhitable.setPayState(1);
            } else {
                pxchongzhitable.setPayMoneyStyle(form.getPayMoneyStyleId());
            }

            pxchongzhitable.setYejiStaffID(form.getYeJiRenId());
            try {
                pxchongzhitable.setChongzhiDatetime(ft.parse(form.getAddDateTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            pxchongzhitable.setAddStaffID(staffID);
            pxchongzhitable.setAddTime(new Date());
            pxchongzhitable.setQiyeID(qiyeID);
            iPxchongzhitableService.save(pxchongzhitable);


            logtext += "，学号：" + newstu.getId() + "，姓名：" + newstu.getStuName() + ",充值金额：" + form.getChongzhiMoney() +
                    "充值赠送：" + form.getZengsongMoney() + ",实际得到：" + pxchongzhitable.getShideTotalMoney();

            if (form.getPayMoneyStyleId().equals("-1")) {

            } else {
                List<Pxqiandanpaymoney> pxqiandanpaymoneyList = JSON.parseArray(form.getPaystylemoney(),
                        Pxqiandanpaymoney.class);
                for (Pxqiandanpaymoney pxqiandanpaymoney : pxqiandanpaymoneyList) {
                    if (pxqiandanpaymoney.getPayMoney().compareTo(new BigDecimal(0)) == 1) {
                        Pxliushuizhangtable pxliushuizhangtable = new Pxliushuizhangtable();
                        try {
                            pxliushuizhangtable.setLiushuiDateTime(ft.parse(form.getAddDateTime()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        pxliushuizhangtable.setCampusID(form.getCampusID());
                        pxliushuizhangtable.setLiushuiZaiyao("学生：" + form.getStuName() + "新生充值");
                        pxliushuizhangtable.setPayMoneyStyle(Long.valueOf(pxqiandanpaymoney.getPaymoneyStyleID()));
                        pxliushuizhangtable.setShouruMoney(pxqiandanpaymoney.getPayMoney());
                        pxliushuizhangtable.setZhichuMoney(new BigDecimal(0));
                        pxliushuizhangtable.setShouzhiStyleID(7L);
                        pxliushuizhangtable.setJinbanRen(staffID);
                        pxliushuizhangtable.setAddStaffID(form.getYeJiRenId());
                        pxliushuizhangtable.setLuruTime(new Date());
                        pxliushuizhangtable.setQiyeID(qiyeID);
                        iPxliushuizhangtableService.save(pxliushuizhangtable);
                        logtext += ",产生流水记录ID：" + pxliushuizhangtable.getId();
                    }
                }
//                for (String pst : form.getPayMoneyStyleId().split(",")) {
//                    if (StringUtils.isNotEmpty(pst)) {
//
//                    }
//                }
            }
            if (form.getYxid() != 0) {
                Pxstutable yxstu = iPxstutableService.getById(form.getYxid());
                if (yxstu != null) {
                    //删除意向学员的跟进记录信息
                    QueryWrapper qdeletegenji = new QueryWrapper();
                    qdeletegenji.eq("stuID", form.getYxid());
                    iPxyxgengjintableService.remove(qdeletegenji);
                    //删除意向学员的邀约到访记录
                    List<Pxyxinvitationtable> pxyxinvitationtables =
                            iPxyxinvitationtableService.list(new QueryWrapper<Pxyxinvitationtable>()
                                    .eq("qiyeID", qiyeID)
                                    .eq("stuID", form.getYxid()));
                    for (Pxyxinvitationtable item : pxyxinvitationtables) {
                        iPxyxinvitedaofangtableService.remove(new QueryWrapper<Pxyxinvitedaofangtable>().eq("qiyeID",
                                qiyeID).eq("inviteID", item.getId()));
                    }
                    iPxyxinvitationtableService.remove(new QueryWrapper<Pxyxinvitationtable>()
                            .eq("qiyeID", qiyeID)
                            .eq("stuID", form.getYxid()));

                    //向意向签单表插入一条记录
                    Pxyxqiandantable yxqiandan = new Pxyxqiandantable();
                    yxqiandan.setStuID(form.getYxid());
                    yxqiandan.setQianDanMoney(form.getChongzhiMoney().toString());
                    yxqiandan.setIsBaomingOrChongzhi(2);   //签单方式：1报名，2充值
                    yxqiandan.setYxShichangRenID(yxstu.getYxshichangTeacherID());
                    yxqiandan.setYxDengjiRenID(yxstu.getDengjiTeacherID());
                    yxqiandan.setYxFenpeiDateTime(yxstu.getDengjiTime());
                    yxqiandan.setYxQiandanRenID(loginUser.getStaffID());
                    yxqiandan.setYxQiandanDateTime(ft.parse(form.getAddDateTime()));
                    yxqiandan.setQiyeID(Long.valueOf(loginUser.getQiyeID()));
                    iPxyxqiandantableService.save(yxqiandan);
                }
            }

            ajaxJson.setSuccess(true);
        }

        savePxLog.savepxlog(logtext, "xwcloud-zsbm/zsbm/BaoMingJiaoFei" +
                        "/AddNewStuChongzhi", loginUser.getStaffID(), loginUser.getStaffName(), 1,
                loginUser.getQiyeID());

        return ajaxJson;
    }

    /**
     * 保存老生充值
     *
     * @param form
     * @return
     */
    @RequestMapping(value = "/AddOldStuChongzhi", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("保存老生充值")
    public AjaxJson AddOldStuChongzhi(@RequestBody oldStuChongzhiForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();

        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Pxstutable pxstutable = iPxstutableService.GetYixiangStuByID(form.getOldStuId());
        BigDecimal chongzhimoney =
                pxstutable.getRemainChongzhi().add(form.getChongzhiMoney().add(form.getZengsongMoney()));
        iPxstutableService.UpdateStuRemainChongzhi(chongzhimoney, form.getOldStuId());
        Pxchongzhitable pxchongzhitable = new Pxchongzhitable();
        pxchongzhitable.setStuID(form.getOldStuId());
        pxchongzhitable.setShijiChongzhiMoney(form.getChongzhiMoney());
        pxchongzhitable.setSongMoney(form.getZengsongMoney());
        pxchongzhitable.setShideTotalMoney(form.getChongzhiMoney().add(form.getZengsongMoney()));
        pxchongzhitable.setShuoming("老学员充值");
        pxchongzhitable.setYejiStaffID(form.getYeJiRenId());
        try {
            pxchongzhitable.setChongzhiDatetime(ft.parse(form.getAddDateTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        pxchongzhitable.setAddStaffID(staffID);
        pxchongzhitable.setQiyeID(qiyeID);
        pxchongzhitable.setAddTime(new Date());
        iPxchongzhitableService.save(pxchongzhitable);
        String logtext =
                "【老学员充值】，学号：" + pxstutable.getId() + ",姓名：" + pxstutable.getStuName() + ",充值：" + form.getChongzhiMoney() + "," +
                        "充值赠送：" + form.getZengsongMoney() + ",实际得到：" + pxchongzhitable.getShideTotalMoney();

        List<Pxqiandanpaymoney> pxqiandanpaymoneyList = JSON.parseArray(form.getPaystylemoney(),
                Pxqiandanpaymoney.class);
        for (Pxqiandanpaymoney pxqiandanpaymoney : pxqiandanpaymoneyList) {
            if (pxqiandanpaymoney.getPayMoney().compareTo(new BigDecimal(0)) == 1) {
                Pxliushuizhangtable pxliushuizhangtable = new Pxliushuizhangtable();
                try {
                    pxliushuizhangtable.setLiushuiDateTime(ft.parse(form.getAddDateTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                pxliushuizhangtable.setCampusID(pxstutable.getCampusID());
                pxliushuizhangtable.setLiushuiZaiyao("学生：" + pxstutable.getStuName() + "老生充值:" + pxqiandanpaymoney.getPayMoney().toString() + "元。");
                pxliushuizhangtable.setPayMoneyStyle(pxqiandanpaymoney.getPaymoneyStyleID());
                pxliushuizhangtable.setShouruMoney(pxqiandanpaymoney.getPayMoney());
                pxliushuizhangtable.setZhichuMoney(new BigDecimal(0));
                pxliushuizhangtable.setShouzhiStyleID(7L);
                pxliushuizhangtable.setJinbanRen(form.getYeJiRenId());
                pxliushuizhangtable.setAddStaffID(form.getYeJiRenId());
                pxliushuizhangtable.setLuruTime(new Date());
                pxliushuizhangtable.setStuID(form.getOldStuId());
                pxliushuizhangtable.setQiyeID(qiyeID);
                iPxliushuizhangtableService.save(pxliushuizhangtable);
                logtext += ",产生流水ID：" + pxliushuizhangtable.getId();
            }
        }

        savePxLog.savepxlog(logtext, "xwcloud-zsbm/zsbm/BaoMingJiaoFei" +
                        "/AddOldStuChongzhi", loginUser.getStaffID(), loginUser.getStaffName(), 1,
                loginUser.getQiyeID());

        ajaxJson.setSuccess(true);
        return ajaxJson;
    }

    /**
     * 导出学生余额信息
     *
     * @param response
     * @param campusID
     */
    @RequestMapping(value = "/exportChongzhi", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("导出学员余额信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区", required = false),
    })
    public void exportChongzhi(HttpServletResponse response, @RequestParam(required = false) String campusID,
                               HttpServletRequest request) {
        QueryWrapper queryWrapper = new QueryWrapper();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("a.campusID", campusID);
        }
        List<StuYueInfoVo> stuYueInfoVoList = iPxstutableService.getAllChongzhiList(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "姓名", "家长电话号码", "充值账户余额"},
                stuYueInfoVoList,
                new String[]{"campusName", "stuName", "parentTel", "remainChongzhi"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "导出学员余额信息.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据充值办理日期查询可用优惠政策
     *
     * @param operateTime
     * @param request
     * @return
     */
    @RequestMapping(value = "/Getkeyongchongzhihuodong", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "根据充值办理日期查询可用优惠政策")
    public AjaxJson Getkeyongchongzhihuodong(String operateTime, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        ajaxJson.setObj(iPxczhuodongtableService.GetChongzhiuhuodongByDate(operateTime, qiyeID));
        return ajaxJson;
    }

    /**
     * 分页查询充值流水信息
     *
     * @param size
     * @param current
     * @param campusID
     * @param stuName
     * @param stugradeID
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetChongzhiLiushuiPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页查询充值流水信息")
    public AjaxJson GetChongzhiLiushuiPages(long size, long current, long campusID, String stuName, long stugradeID,
                                            HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("b.qiyeID", qiyeID);
        if (campusID != 0) {
            queryWrapper.eq("b.campusID", campusID);
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("b.stuName", stuName);
        }
        if (stugradeID != 0) {
            queryWrapper.eq("b.stuGradeID", stugradeID);
        }
        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", 143);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("b.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("b.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("b.campusID", lookPower);
        }
        Page<chongzhiliushuiVO> page = new Page<>(current, size);
        ajaxJson.setObj(iPxchongzhitableService.GetChongzhiliushuiPages(page, queryWrapper));
        return ajaxJson;
    }
    //endregion

    //region 代金券流水

    /**
     * 分页查询代金券流水
     *
     * @param size
     * @param current
     * @param campusID
     * @param zidingyiStuID
     * @param stuName
     * @param stuGradeID
     * @param jinbanStaffName
     * @return
     */
    @RequestMapping(value = "/GetAllDaijinquanLiushuiPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("分页查询代金券流水")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "campusName", value = "校区名称", required = false),
            @ApiImplicitParam(name = "zidingyiStuID", value = "自定义学号", required = false),
            @ApiImplicitParam(name = "stuName", value = "学生姓名", required = false),
            @ApiImplicitParam(name = "stuGradeName", value = "学生年级", required = false),
            @ApiImplicitParam(name = "jinbanStaffName", value = "经办人姓名", required = false),

    })
    public AjaxJson GetAllDaijinquanLiushuiPages(long size, long current, long campusID, String
            zidingyiStuID, String stuName, long stuGradeID, String jinbanStaffName, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Page<daijinquanVo> page = new Page(current, size);
        QueryWrapper<HashMap<String, Object>> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(zidingyiStuID)) {
            queryWrapper.like("b.zidingyiStuID", zidingyiStuID).or().eq("b.id", zidingyiStuID);
        }
        if (campusID != 0) {
            queryWrapper.eq("b.campusID", campusID);
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("b.stuName", stuName);
        }
        if (stuGradeID != 0) {
            queryWrapper.eq("b.stuGradeID", stuGradeID);
        }
        if (StringUtils.isNotBlank(jinbanStaffName)) {
            queryWrapper.like("f.staffName", jinbanStaffName);
        }
        queryWrapper.orderByDesc("a.creatTime");
        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", 145);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }
        ajaxJson.setObj(iPxdaijinquantableService.GetDaijinquanLiushuiPages(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * 导出代金券流水信息
     *
     * @param response
     * @param campusID
     * @param startDate
     * @param endDate
     */
    @RequestMapping(value = "/exportDaijinquanliushui", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("导出代金券流水信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "startDate", value = "开始时间", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束时间", required = false)
    })
    public void exportDaijinquanliushui(HttpServletResponse response, HttpServletRequest request,
                                        @RequestParam(required = false) long campusID, // 校区ID
                                        @RequestParam(required = false) String startDate, // 开始日期
                                        @RequestParam(required = false) String endDate // 结束日期
    ) {
        QueryWrapper queryWrapper = new QueryWrapper();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        queryWrapper.like("a.qiyeID", qiyeID);
        if (campusID != 0) {
            queryWrapper.eq("b.campusID", campusID);
        }
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("a.creatTime", startDate);
        }
        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("a.creatTime", endDate);
        }
        List<daijinquanVo> daijinquanVoList = iPxdaijinquantableService.GetDaijinquanLiushuiList(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"学号", "姓名", "校区", "年级", "代金券金额", "使用时间",
                        "经办人"},
                daijinquanVoList,
                new String[]{"campusName", "stuName", "campusName", "stuGradeName", "money", "createTime", "staffName"
                });
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "导出代金券流水.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //endregion

    //region 签单商品流水

    /**
     * 分页加载签单商品流水
     *
     * @param size
     * @param current
     * @param campusID
     * @param zidingyiStuID
     * @param stuName
     * @param stuGradeID
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetQiandanSuppliesPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("分页加载签单商品流水")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "campusName", value = "校区名称", required = false),
            @ApiImplicitParam(name = "zidingyiStuID", value = "自定义学号", required = false),
            @ApiImplicitParam(name = "stuName", value = "学生姓名", required = false),
            @ApiImplicitParam(name = "stuGradeName", value = "学生年级", required = false),
    })
    public AjaxJson GetQiandanSuppliesPages(long size, long current, long campusID, String
            zidingyiStuID, String stuName, long stuGradeID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Page<shangpinliushuiVo> page = new Page(current, size);
        QueryWrapper<shangpinliushuiVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("c.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(zidingyiStuID)) {
            queryWrapper
                    .and(a -> a.isNotNull("c.zidingyiStuID").like("c.zidingyiStuID", zidingyiStuID))
                    .or(b -> b.isNull("c.zidingyiStuID").eq("c.id", zidingyiStuID));
        }
        if (campusID != 0) {
            queryWrapper.eq("c.campusID", campusID);
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("stuName", stuName);
        }
        if (stuGradeID != 0) {
            queryWrapper.eq("c.stuGradeID", stuGradeID);
        }
        queryWrapper.orderByDesc("b.qiandandate");
        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", 147);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("c.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("c.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("c.campusID", lookPower);
        }
        queryWrapper.orderByDesc("a.id");
        ajaxJson.setObj(iPxqiandansuppliesService.GetQiandanSuppliesPages(page, queryWrapper));
        return ajaxJson;

    }

    @RequestMapping(value = "/exportqiandanshangpinList", method = RequestMethod.GET)
    @ApiOperation("导出签单商品流水")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "startDate", value = "开始时间", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束时间", required = false),
    })
    public void exportqiandanshangpinList(HttpServletResponse response, HttpServletRequest request,
                                          @RequestParam(required = false) String campusID, // 校区ID
                                          @RequestParam(required = false) String startDate, // 开始日期
                                          @RequestParam(required = false) String endDate // 结束日期
    ) {
        QueryWrapper queryWrapper = new QueryWrapper();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        queryWrapper.like("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("campusID", campusID);
        }
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("qiandandate", startDate);
        }
        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("qiandandate", endDate);
        }
        List<shangpinliushuiVo> shangpinliushuiVoList = iPxqiandansuppliesService.GetQiandanSuppliesList(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "学号", "学生姓名", "年级/年龄段", "物品名称",
                        "购买数量", "购买价格", "总价"},
                shangpinliushuiVoList,
                new String[]{"campusName", "zidingyiStuID", "stuName", "stuGradeName", "Name", "BuySum", "BuyPrice",
                        "SumMoney"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "签单商品流水导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion

    //region 杂费流水

    /**
     * 分页查询杂费流水
     *
     * @param size
     * @param current
     * @param campusID
     * @param zidingyiStuID
     * @param stuName
     * @param stuGradeID
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetQiandanOtherMoneyPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("分页查询杂费流水")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "campusName", value = "校区名称", required = false),
            @ApiImplicitParam(name = "zidingyiStuID", value = "自定义学号", required = false),
            @ApiImplicitParam(name = "stuName", value = "学生姓名", required = false),
            @ApiImplicitParam(name = "stuGradeName", value = "学生年级", required = false),
    })
    public AjaxJson GetQiandanOtherMoneyPages(long size, long current, long campusID, String zidingyiStuID,
                                              String stuName, long stuGradeID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Page<zaffeiListVo> page = new Page(current, size);
        QueryWrapper<zaffeiListVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(zidingyiStuID)) {
            queryWrapper
                    .and(a -> a.isNotNull("c.zidingyiStuID").like("c.zidingyiStuID", zidingyiStuID))
                    .or(b -> b.isNull("c.zidingyiStuID").eq("b.stuID", zidingyiStuID));
        }
        if (campusID != 0) {
            queryWrapper.eq("c.campusID", campusID);
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("c.stuName", stuName);
        }
        if (stuGradeID != 0) {
            queryWrapper.eq("c.stuGradeID", stuGradeID);
        }
        queryWrapper.orderByDesc("b.qiandandate");
        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", 148);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("c.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("c.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("c.campusID", lookPower);
        }
        ajaxJson.setObj(iPxqiandaninfo2tableService.GetQiandanOtherMoneyPages(page, queryWrapper));
        return ajaxJson;
    }

    @RequestMapping(value = "/exportzafeiList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("导出杂费流水信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "startDate", value = "开始时间", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束时间", required = false),
    })
    public void exportzafeiList(HttpServletResponse response, HttpServletRequest request,
                                @RequestParam(required = false) long campusID, // 校区ID
                                @RequestParam(required = false) String startDate, // 开始日期
                                @RequestParam(required = false) String endDate // 结束日期
    ) {
        QueryWrapper queryWrapper = new QueryWrapper();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        queryWrapper.like("a.qiyeID", qiyeID);
        if (campusID != 0) {
            queryWrapper.eq("c.campusID", campusID);
        }
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("b.qiandandate", startDate);
        }
        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("b.qiandandate", endDate);
        }
        List<zaffeiListVo> zaffeiListVoList = iPxqiandaninfo2tableService.GetQiandanOtherMoneyList(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "学号", "学生姓名", "年级/年龄段", "费项名称",
                        "总金额", "交费时间"},
                zaffeiListVoList,
                new String[]{"campusName", "zidingyiStuID", "stuName", "stuGradeName", "otherMoneyName", "zongMoney",
                        "qiandandate"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "导出杂费流水.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion

    //region 合同

    /**
     * 分页查询签单信息
     *
     * @param size
     * @param current
     * @param campusID
     * @param zidingyiStuID
     * @param stuName
     * @param stuGradeID
     * @param jinbanStaffName
     * @param Sqiandandate
     * @param Eqiandandate
     * @param yejistaffname
     * @param request
     * @return
     */
    @RequestMapping(value = "/getAllQianDanInfoHetongPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("分页查询签单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "campusName", value = "校区名称", required = false),
            @ApiImplicitParam(name = "zidingyiStuID", value = "自定义学号", required = false),
            @ApiImplicitParam(name = "stuName", value = "学生姓名", required = false),
            @ApiImplicitParam(name = "stuGradeName", value = "学生年级", required = false),
            @ApiImplicitParam(name = "jinbanStaffName", value = "经办人", required = false),
    })
    public AjaxJson getAllQianDanInfoHetongPages(long size, long current, long campusID, String zidingyiStuID,
                                                 String stuName, long stuGradeID, String jinbanStaffName
            , String Sqiandandate, String Eqiandandate, String yejistaffname, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Page<qianDanInFoVo> page = new Page(current, size);
        QueryWrapper<qianDanInFoVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(zidingyiStuID)) {
            queryWrapper.like("zidingyiStuID", zidingyiStuID).or().eq("b.id", zidingyiStuID);
        }
        if (campusID != 0) {
            queryWrapper.eq("b.campusID", campusID);
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("stuName", stuName);
        }
        if (stuGradeID != 0) {
            queryWrapper.eq(" b.stuGradeID", stuGradeID);
        }
        if (StringUtils.isNotBlank(jinbanStaffName)) {
            queryWrapper.like("jinbanStaffName", jinbanStaffName);
        }
        if (StringUtils.isNotBlank(Sqiandandate)) {
            queryWrapper.ge("a.qiandandate", Sqiandandate);
        }
        if (StringUtils.isNotBlank(Eqiandandate)) {
            queryWrapper.le("a.qiandandate", Eqiandandate);
        }
        if (StringUtils.isNotBlank(yejistaffname)) {
            queryWrapper.like("(SELECT GROUP_CONCAT(staff.staffName) FROM pxqiandanstafftable AS qds LEFT JOIN " +
                    "pxstafftable AS staff ON qds.staffID=staff.id WHERE qiandanID = a.ID)", yejistaffname);
        }
        queryWrapper.orderByDesc("a.recordInTime");
        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", 144);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("c.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("c.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("c.campusID", lookPower);
        }
        ajaxJson.setObj(iPxqiandaninfotableService.GetQiandanInfoPages(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * 合同管理查询签单详细信息
     *
     * @param request
     * @param qiandanID
     * @return
     */
    @RequestMapping(value = "/GetQiandanInfoByQiandanID_ht", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "合同管理查询签单详细信息")
    public AjaxJson GetQiandanInfoByQiandanID_ht(HttpServletRequest request, long qiandanID) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxqiandaninfotableService.GetqiandanInfoByqiandanID(qiandanID));
        return ajaxJson;
    }

    /**
     * 保存签单合同信息
     *
     * @param request
     * @param qiandanID
     * @param imgurl
     * @return
     */
    @RequestMapping(value = "/SaveQiandanHetong", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "保存签单合同信息")
    public AjaxJson SaveQiandanHetong(HttpServletRequest request, long qiandanID, String imgurl) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Pxqiandaninfotable pxqiandaninfotable = iPxqiandaninfotableService.getById(qiandanID);
        if (!StringUtils.isNotBlank(pxqiandaninfotable.getHetong())) {
            pxqiandaninfotable.setHetong(imgurl);
        } else {
            pxqiandaninfotable.setHetong(pxqiandaninfotable.getHetong() + "," + imgurl);
        }
        ajaxJson.setSuccess(iPxqiandaninfotableService.updateById(pxqiandaninfotable));

        savePxLog.savepxlog("给签单：" + qiandanID + "设置了合同。", "xwcloud-zsbm/zsbm/BaoMingJiaoFei" +
                        "/SaveQiandanHetong", loginUser.getStaffID(), loginUser.getStaffName(), 1,
                loginUser.getQiyeID());
        return ajaxJson;
    }


    @RequestMapping(value = "/Deletehetong", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除合同")
    public AjaxJson Deletehetong(Long qiandanID, String newhetong, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Pxqiandaninfotable qd = iPxqiandaninfotableService.getById(qiandanID);
        qd.setHetong(newhetong);
        ajaxJson.setSuccess(iPxqiandaninfotableService.updateById(qd));

        savePxLog.savepxlog("给签单：" + qiandanID + "删除了合同。", "xwcloud-zsbm/zsbm/BaoMingJiaoFei" +
                        "/Deletehetong", loginUser.getStaffID(), loginUser.getStaffName(), 1,
                loginUser.getQiyeID());
        return ajaxJson;
    }


    @RequestMapping(value = "/DeleteImgs", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除七牛云文件")
    private AjaxJson DeleteImgs(long qiandanID, String key) throws QiniuException {
        AjaxJson ajaxJson = new AjaxJson();
        Pxqiandaninfotable pxqiandaninfotable = iPxqiandaninfotableService.getById(qiandanID);
        String imgurl = "http://imgs.jxb666.com/" + key;
        String newimgurl = "";
        for (String item : pxqiandaninfotable.getHetong().split(",")) {
            if (item != imgurl) {
                newimgurl += item + ",";
            }
        }
        pxqiandaninfotable.setHetong(newimgurl.trim());
        iPxqiandaninfotableService.updateById(pxqiandaninfotable);
        Qiniuutils.delete(key);
        ajaxJson.setSuccess(true);
        return ajaxJson;
    }
    //endregion

    //region 签单审批

    /**
     * 分页查询签单审批信息
     *
     * @param request
     * @param size
     * @param current
     * @return
     */
    @RequestMapping(value = "/GetAllQiandanshenpiInfoPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页查询签单审批信息")
    public AjaxJson GetAllQiandanshenpiInfoPages(HttpServletRequest request, long size, long current, String stuName,
                                                 long campusID,
                                                 String Sqiandandate,
                                                 String Eqiandandate) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<qiandanshenpiVO> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        queryWrapper.orderByDesc("a.qiandandate");
        if (campusID != 0) {
            queryWrapper.eq("a.campusID", campusID);
        }
        if (StringUtils.isNotEmpty(stuName)) {
            queryWrapper.like("b.stuName", stuName);
        }
        if (StringUtils.isNotEmpty(Sqiandandate)) {
            queryWrapper.ge("a.qiandandate", Sqiandandate);
        }
        if (StringUtils.isNotEmpty(Eqiandandate)) {
            queryWrapper.le("a.qiandandate", Eqiandandate);
        }
        ajaxJson.setObj(iQiandanshenpiService.GetAllQiandanshenpiPages(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * 删除签单审批信息
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/DeleteQiandanshengPiInfo", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson DeleteQiandanshengPiInfo(HttpServletRequest request, long id) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        iQiandanshenpiService.removeById(id);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("qiandanshenpiID", id);
        iQiandanshenpisubjectService.remove(queryWrapper);
        iQiandanshenpipaymoneyService.remove(queryWrapper);
        iQiandanshenpisuppliesService.remove(queryWrapper);
        iQiandanshenpizafeiService.remove(queryWrapper);
        iQiandanshenpiyejirenService.remove(queryWrapper);
        ajaxJson.setSuccess(true);
        savePxLog.savepxlog("删除签单审批ID：" + id + "的所有签单审批信息。", "xwcloud-zsbm/zsbm/BaoMingJiaoFei" +
                        "/DeleteQiandanshengPiInfo", loginUser.getStaffID(), loginUser.getStaffName(), 1,
                loginUser.getQiyeID());
        return ajaxJson;
    }

    /**
     * 审批通过
     *
     * @param request
     * @param shenpiID
     * @return
     */
    @RequestMapping(value = "/PassQiandanShenpi", method = RequestMethod.GET)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson PassQiandanShenpi(HttpServletRequest request, long shenpiID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Qiandanshenpi qiandanshenpi = iQiandanshenpiService.getById(shenpiID);
        Pxstutable pxstutable = iPxstutableService.getById(qiandanshenpi.getStuID());


        Pxqiandaninfotable qiandan = new Pxqiandaninfotable();
        qiandan.setStuID(qiandanshenpi.getStuID());
        qiandan.setQiandandate(qiandanshenpi.getQiandandate());
        qiandan.setZhuanjieshaoID(qiandanshenpi.getZhuanjieshaoID());
        qiandan.setMoneyStyle(qiandanshenpi.getIsXinqianOrXufei());    //1新签；2续费；
        qiandan.setBeizhu(qiandanshenpi.getBeizhu());
        qiandan.setRecordInStaffID(qiandanshenpi.getAddstaffID());
        qiandan.setRecordInTime(new Date());
        qiandan.setCampusID(qiandanshenpi.getCampusID());
        qiandan.setFromType(qiandanshenpi.getFromType());
        qiandan.setYouhuiID(qiandanshenpi.getYouhuiID());
        qiandan.setYouhuijine(qiandanshenpi.getYouhuijine().toString());
        qiandan.setYouhuishuoming(qiandanshenpi.getYouhuishuoming());
        qiandan.setHetongMoney(qiandanshenpi.getHetongMoney());
        qiandan.setShishouTotalMoney(qiandanshenpi.getShishouTotalMoney());
        qiandan.setIsdingjing(qiandanshenpi.getIsdingjing());
        qiandan.setQiyeID(loginUser.getQiyeID());
        qiandan.setRecordInStaffID(qiandanshenpi.getAddstaffID());
        //qiandan.setQianDanStaffID(qiandanshenpi.getAddstaffID());
        qiandan.setQianDanStaffID(0L);  //签单人，这个字段也基本没用了，因为要支持多个签单人，所以，用单独的表在存签单人了
        qiandan.setPayMoneyStyle(0L);  //支付方式，这个字段基本没用，支付方式看pxqiandanpaymoney表
        qiandan.setBeizhu(qiandanshenpi.getBeizhu());
        iPxqiandaninfotableService.save(qiandan);

        QueryWrapper searchzjs = new QueryWrapper();
        searchzjs.eq("qiandanshenpiID", qiandanshenpi.getId());
        searchzjs.eq("qiyeID", loginUser.getQiyeID());
        QiandanshenpiZhuanjieshao qiandanshenpiZhuanjieshao = iQiandanshenpiZhuanjieshaoService.getOne(searchzjs);
        if (qiandanshenpiZhuanjieshao != null) {
            Pxqiandanzhuanjieshaotable pxqiandanzhuanjieshaotable = new Pxqiandanzhuanjieshaotable();
            pxqiandanzhuanjieshaotable.setQiyeID(loginUser.getQiyeID())
                    .setZhuanjieshaoFromStuID(qiandanshenpiZhuanjieshao.getZhuanjieshaoFromStuID())
                    .setZhuanjieshaoFromStaffID(qiandanshenpiZhuanjieshao.getZhuanjieshaoFromStaffID())
                    .setQiandanID(qiandan.getId())
                    .setBeizhu("");
            iPxqiandanzhuanjieshaotableService.save(pxqiandanzhuanjieshaotable);
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiandanshenpiID", qiandanshenpi.getId());
        List<Qiandanshenpiyejiren> qdstaff = iQiandanshenpiyejirenService.list(queryWrapper);
        for (Qiandanshenpiyejiren pxqiandanstafftable : qdstaff) {
            Pxqiandanstafftable newqiandanstaff = new Pxqiandanstafftable();
            newqiandanstaff.setIsWeikuan(0);
            newqiandanstaff.setQiandanID(qiandan.getId());
            newqiandanstaff.setQiyeID(pxqiandanstafftable.getQiyeID());
            newqiandanstaff.setStaffID(pxqiandanstafftable.getQiandanstaffID());
            newqiandanstaff.setYejidateTime(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            newqiandanstaff.setYejiMoney(pxqiandanstafftable.getYejiMoney());
            iPxqiandanstafftableService.save(newqiandanstaff);

            //如果是意向学员报名签单的，要向意向签单表插入一条记录
            if (qiandanshenpi.getQiandanType() == 1) {    //1意向学员签单，2直接后台录入签单，3微信端支付自动录单
                //向意向签单表插入一条记录
                Pxyxqiandantable yxqiandan = new Pxyxqiandantable();
                yxqiandan.setStuID(qiandanshenpi.getStuID());
                yxqiandan.setQianDanMoney(pxqiandanstafftable.getYejiMoney().toString());
                yxqiandan.setIsBaomingOrChongzhi(1);   //签单方式：1报名，2充值
                yxqiandan.setYxShichangRenID(pxstutable.getYxshichangTeacherID());
                yxqiandan.setYxDengjiRenID(pxstutable.getDengjiTeacherID());
                yxqiandan.setYxFenpeiDateTime(pxstutable.getDengjiTime());
                yxqiandan.setYxQiandanRenID(pxqiandanstafftable.getQiandanstaffID());
                yxqiandan.setYxQiandanDateTime(qiandan.getQiandandate());
                yxqiandan.setQiyeID(loginUser.getQiyeID());
                iPxyxqiandantableService.save(yxqiandan);
            }
        }
        List<Qiandanshenpizafei> othermoneyVoList = iQiandanshenpizafeiService.list(queryWrapper);
        for (Qiandanshenpizafei othermoneyVo : othermoneyVoList) {
            Pxqiandaninfo2table qd2Tab = new Pxqiandaninfo2table();
            qd2Tab.setQianInfoTabID(qiandan.getId());
            qd2Tab.setQianDanOtherMoneyID(othermoneyVo.getQianDanOtherMoneyID());
            qd2Tab.setZongMoney(othermoneyVo.getZongMoney());
            qd2Tab.setJiaoxueYonpingID(0L);
            qd2Tab.setNums(new BigDecimal(1));
            qd2Tab.setOnePrice(othermoneyVo.getOnePrice());
            qd2Tab.setType(1);
            qd2Tab.setTuiMoney(new BigDecimal(0));
            qd2Tab.setQiyeID(othermoneyVo.getQiyeID());
            iPxqiandaninfo2tableService.save(qd2Tab);
        }
        List<Qiandanshenpisubject> buykechenglist = iQiandanshenpisubjectService.list(queryWrapper);
        if (buykechenglist == null) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("操作失败！您还未添加课程！");
            return ajaxJson;
        }
        //region 保存课程信息
        for (Qiandanshenpisubject buyKechengVo : buykechenglist) {
            //region 新增课程
            Pxkechengtable pxkechengtable = iPxkechengtableService.getById(buyKechengVo.getKechengID());
            Pxsubjecttable pxsubjecttable = iPxsubjecttableService.getById(pxkechengtable.getSubjectID());
            Long classID = 0L;
            if (pxkechengtable.getIs1v1KC() == 1) {
                boolean ii = false;
                while (ii == false) {
                    Random random = new Random();
                    int max = 999;
                    int min = 100;

                    int sjs = random.nextInt(max) % (max - min + 1) + min;

                    String className = pxstutable.getStuName() + "_" + pxsubjecttable.getSubjectName() + "_一对一" + sjs;
                    List<Pxclasstable> pxclasstableList = iPxclasstableService.GetClassByClassName(className);
                    if (pxclasstableList.size() == 0) {
                        Pxclasstable classtable = new Pxclasstable();
                        classtable.setClassName(className);
                        classtable.setCampusID(qiandanshenpi.getCampusID());
                        classtable.setAddStaffID(qiandanshenpi.getAddstaffID());
                        classtable.setAddTime(new Date());
                        classtable.setIsShow(1);
                        classtable.setIs1v1Class(1);
                        classtable.setQiyeID(qiandanshenpi.getQiyeID());
                        classtable.setClassState(0);
                        iPxclasstableService.save(classtable);
                        classID = classtable.getId();
                        ii = true;
                    }
                }
            } else {

                if (buyKechengVo.getClassID() != null) {
                    classID = buyKechengVo.getClassID();
                }
            }

            //添加补习课程记录
            Pxbuxikechengtable buxi = new Pxbuxikechengtable();
            buxi.setStuID(pxstutable.getId());
            buxi.setKechengID(buyKechengVo.getKechengID());
            buxi.setKechengprice(buyKechengVo.getKechengprice());
            buxi.setOriginalprice(buyKechengVo.getOriginalprice());
            buxi.setKeshiNum(buyKechengVo.getBuykeshiNum());
            buxi.setRemainkeshi(buyKechengVo.getBuykeshiNum());
            buxi.setBuykeshiDateTime(qiandanshenpi.getQiandandate());
            buxi.setIsShow(1);
            buxi.setZongjia(buyKechengVo.getZongjia());
            buxi.setStartDate(buyKechengVo.getStartDate());
            buxi.setEndDate(buyKechengVo.getEndDate());
            buxi.setJifeiStyleID(pxkechengtable.getJifeiStyleID());
            buxi.setType(1);


            if (buyKechengVo.getZengsongkeshi().compareTo(new BigDecimal(0)) == 1) {
                //添加赠送记录
                Pxkeshizengsongtable keshizengsong = new Pxkeshizengsongtable();
                keshizengsong.setKechengID(buyKechengVo.getKechengID());
                keshizengsong.setKechengPrice(buyKechengVo.getKechengprice());
                keshizengsong.setKeshiShu(buyKechengVo.getZengsongkeshi());
                keshizengsong.setSongYangyin("签单课时赠送");
                keshizengsong.setJifeiStyle(pxkechengtable.getJifeiStyleID());
                keshizengsong.setStuID(pxstutable.getId());
                keshizengsong.setAddDate(new Date());
                keshizengsong.setCaozuoStaffId(qiandanshenpi.getAddstaffID());
                keshizengsong.setQiandanInfoID(qiandan.getId());
                keshizengsong.setQiyeID(loginUser.getQiyeID());
                iPxkeshizengsongtableService.save(keshizengsong);

                QueryWrapper queryWrapper2 = new QueryWrapper();
                queryWrapper2.eq("stuID", pxstutable.getId());
                queryWrapper2.eq("kechengID", buyKechengVo.getKechengID());
                queryWrapper2.eq("kechengprice", buyKechengVo.getKechengprice());
                queryWrapper2.eq("type", 2);

                Pxbuxikechengtable pdbuxikc = iPxbuxikechengtableService.GetZidingYiKecheng(queryWrapper2);
                if (pdbuxikc != null) {
                    pdbuxikc.setRemainkeshi(pdbuxikc.getRemainkeshi().add(buyKechengVo.getZengsongkeshi()));
                    pdbuxikc.setKeshiNum(pdbuxikc.getKeshiNum().add(buyKechengVo.getZengsongkeshi()));
                } else {
                    //添加赠送补习课程记录
                    Pxbuxikechengtable zsbxTab = new Pxbuxikechengtable();
                    zsbxTab.setStuID(pxstutable.getId());
                    zsbxTab.setKechengID(buyKechengVo.getKechengID());
                    zsbxTab.setKechengprice(buyKechengVo.getKechengprice());
                    zsbxTab.setOriginalprice(buyKechengVo.getOriginalprice());
                    zsbxTab.setRemainkeshi(buyKechengVo.getZengsongkeshi());
                    zsbxTab.setKeshiNum(buyKechengVo.getZengsongkeshi());
                    zsbxTab.setZongjia(new BigDecimal(0));
                    zsbxTab.setStartDate(buyKechengVo.getStartDate());
                    zsbxTab.setEndDate(buyKechengVo.getEndDate());
                    zsbxTab.setBuykeshiDateTime(new Date());
                    zsbxTab.setIsShow(0);
                    zsbxTab.setJifeiStyleID(pxkechengtable.getJifeiStyleID());
                    zsbxTab.setQiyeID(pxstutable.getQiyeID());
                    zsbxTab.setType(2);    //赠送的
                    iPxbuxikechengtableService.save(zsbxTab);

                    if (pxkechengtable.getJifeiStyleID() == 1) {
                        Long addclassID = 0L;
                        Pxkechengtable onekcTab = iPxkechengtableService.GetKechengById(buyKechengVo.getKechengID());
                        String subjectName =
                                iPxsubjecttableService.GetSubjectById(onekcTab.getSubjectID()).getSubjectName();
                        //建班插班
                        boolean ii = false;
                        while (ii == false) {
                            Random rd = new Random();

                            int max = 999;
                            int min = 100;
                            int sjs = rd.nextInt(max) % (max - min + 1) + min;
                            String className = pxstutable.getStuName() + "_" + subjectName + "_一对一" + sjs;
                            List<Pxclasstable> pdclass = iPxclasstableService.GetClassByClassName(className);
                            if (pdclass.size() == 0) {
                                Pxclasstable classtable = new Pxclasstable();
                                classtable.setClassName(className);
                                classtable.setCampusID(qiandanshenpi.getCampusID());
                                classtable.setAddStaffID(qiandanshenpi.getAddstaffID());
                                classtable.setAddTime(new Date());
                                classtable.setIsShow(1);
                                classtable.setClassState(0);
                                classtable.setIs1v1Class(1);
                                classtable.setQiyeID(loginUser.getQiyeID());
                                classtable.setIsdelete(false);
                                iPxclasstableService.save(classtable);
                                addclassID = classtable.getId();
                                ii = true;
                            }
                        }

                        //如果是一对一，要插班
                        Pxstuclasstable stucla = new Pxstuclasstable();
                        stucla.setBuxiID(zsbxTab.getId());
                        stucla.setClassID(addclassID);
                        stucla.setQiyeID(loginUser.getQiyeID());
                        iPxstuclasstableService.save(stucla);
                    }
                }
            }


            Pxqiandansubjecttable qdsub = new Pxqiandansubjecttable();
            qdsub.setStuID(pxstutable.getId());
            qdsub.setQiandandate(qiandanshenpi.getQiandandate());
            qdsub.setKechengID(buyKechengVo.getKechengID());
            qdsub.setKechengprice(buyKechengVo.getKechengprice());
            qdsub.setOriginalprice(buyKechengVo.getOriginalprice());
            qdsub.setBuykeshiNum(buyKechengVo.getBuykeshiNum());
            qdsub.setZongjia(buyKechengVo.getZongjia());   ///增加了总价的存储
            qdsub.setQianDanInfoID(qiandan.getId());
            qdsub.setKechengStyle(1);   //1买的 2 接受的赠送 3 送出的 4 退费
            qdsub.setDiscount(buyKechengVo.getDiscount());
            qdsub.setStartDate(buyKechengVo.getStartDate());
            qdsub.setEndDate(buyKechengVo.getEndDate());
            qdsub.setQiyeID(loginUser.getQiyeID());
            qdsub.setKechengID(buyKechengVo.getKechengID());
            iPxqiandansubjecttableService.save(qdsub);
            buxi.setQianDanInfoID(qiandan.getId());
            buxi.setQianDanSubjectID(qdsub.getId());
            buxi.setQiyeID(loginUser.getQiyeID());
            buxi.setKechengID(buyKechengVo.getKechengID());
            iPxbuxikechengtableService.save(buxi);


            //插班
            if (classID != 0L) {
                boolean ishave = false;
                List<Pxbuxikechengtable> abuxiTab =
                        iPxbuxikechengtableService.GetBuxikchengByStuID(pxstutable.getId(), loginUser.getQiyeID());
                for (Pxbuxikechengtable pxbuxikechengtable : abuxiTab) {
                    Pxstuclasstable psstuclsTab = iPxstuclasstableService.GetStuClassByBxIDAndClassID(classID,
                            pxbuxikechengtable.getId());
                    if (psstuclsTab != null) {
                        ishave = true;
                    }
                }
                if (ishave == false) {
                    Pxstuclasstable stuclass = new Pxstuclasstable();
                    stuclass.setBuxiID(buxi.getId());
                    stuclass.setClassID(classID);
                    stuclass.setQiyeID(loginUser.getQiyeID());
                    iPxstuclasstableService.save(stuclass);
                    if (buyKechengVo.getCharukebiao() == true) {
                        //如果新加入的班级原有排课了、考勤状态是未完成状态，再把学生加在选课表里面去
//                        List<Pxpaiketable> paikenews = iPxpaiketableService.GetPaikebyClassID(classID);

                        Pxpaiketable checkPK = iPxpaiketableService.getById(buyKechengVo.getPkid()); //选中的排课
                        List<Pxpaiketable> paikenews = iPxpaiketableService.list(new QueryWrapper<Pxpaiketable>()
                                .eq("classID", checkPK.getClassID())
                                .ge("haveClassDate", checkPK.getHaveClassDate())
                                .ge("startLessonDateTime", checkPK.getStartLessonDateTime())
                                .eq("qiyeID", loginUser.getQiyeID())
                                .orderByAsc("haveClassDate")
                        );

                        for (Pxpaiketable pxpaiketable : paikenews) {
                            List<Pxxuanketable> pdxuankeTab =
                                    iPxxuanketableService.GetXuankeByBuxiIDAndPaikeId(buxi.getStuID(),
                                            pxpaiketable.getId());
                            if (pdxuankeTab.size() == 0) {
                                Pxxuanketable xuanke = new Pxxuanketable();
                                xuanke.setBuxiID(buxi.getId());
                                xuanke.setPaikeID(pxpaiketable.getId());
                                xuanke.setStuID(buxi.getStuID());
                                xuanke.setRecordDate(new Date());
                                xuanke.setType(1);
                                xuanke.setQiyeID(loginUser.getQiyeID());
                                iPxxuanketableService.save(xuanke);
                            }
                        }
                    }
                }
            }

        }
        //endregion
        List<Qiandanshenpisupplies> wpbuyList = iQiandanshenpisuppliesService.list(queryWrapper);
        for (Qiandanshenpisupplies buyWpVo : wpbuyList) {
            Pxteachingsuppliestable TeachingSupplies =
                    iPxteachingsuppliestableService.GetTeachingSuppliesByName(buyWpVo.getName());
            if (TeachingSupplies.getStockNum().compareTo(buyWpVo.getBuySum()) == -1) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("当前所购买的教学物品" + buyWpVo.getName() + "数量不足！");
                return ajaxJson;
            }
            Pxqiandansupplies qdsp = new Pxqiandansupplies();
            qdsp.setStuID(pxstutable.getId());
            qdsp.setTeachingSuppliesID(TeachingSupplies.getId());
            qdsp.setName(buyWpVo.getName());
            qdsp.setQiandaninfoID(qiandan.getId());
            qdsp.setBuyPrice(buyWpVo.getBuyPrice());
            qdsp.setBuySum(buyWpVo.getBuySum());
            qdsp.setSumMoney(buyWpVo.getSumMoney());
            qdsp.setIsTuiFei(false);
            qdsp.setTuiMoney(new BigDecimal(0));
            qdsp.setTuiMoney(new BigDecimal(0));
            qdsp.setQiyeID(loginUser.getQiyeID());
            iPxqiandansuppliesService.save(qdsp);
            Pxteachingsuppliesouttable outjl = new Pxteachingsuppliesouttable();
            outjl.setLuruStaffId(qiandanshenpi.getAddstaffID());
            outjl.setOutDate(new Date());
            outjl.setOutNum(buyWpVo.getBuySum());
            outjl.setOutnumBefore(TeachingSupplies.getStockNum());
            outjl.setOutReason("学生购买");
            outjl.setOutStaffId(qiandanshenpi.getAddstaffID());
            outjl.setSuppliesId(TeachingSupplies.getId());
            outjl.setType(2);
            outjl.setQiyeID(loginUser.getQiyeID());
            outjl.setLuruStaffId(qiandanshenpi.getAddstaffID());
            iPxteachingsuppliesouttableService.save(outjl);
            BigDecimal kucun = TeachingSupplies.getStockNum().subtract(buyWpVo.getBuySum());
            iPxteachingsuppliestableService.UpdateteachingsuppliesKucun(TeachingSupplies.getId(), kucun);
        }
        if (qiandanshenpi.getDaijinquanmoney().compareTo(new BigDecimal(0)) == 1) {//代金券金额大于0
            Pxdaijinquantable pxdaijinquantable = new Pxdaijinquantable();
            pxdaijinquantable.setStuID(pxstutable.getId());
            pxdaijinquantable.setQiandanID(qiandan.getId());
            pxdaijinquantable.setMoney(qiandanshenpi.getDaijinquanmoney());
            pxdaijinquantable.setCreatTime(new Date());
            pxdaijinquantable.setQiyeID(loginUser.getQiyeID());
            pxdaijinquantable.setStaffID(qiandanshenpi.getAddstaffID());
            pxdaijinquantable.setStaffID(qiandanshenpi.getAddstaffID());
            iPxdaijinquantableService.save(pxdaijinquantable);
        }

        List<Qiandanshenpipaymoney> pxqiandanpaymoneyList = iQiandanshenpipaymoneyService.list(queryWrapper);
        for (Qiandanshenpipaymoney pxqiandanpaymoney : pxqiandanpaymoneyList) {
            if (pxqiandanpaymoney.getPaymoney().compareTo(new BigDecimal(0)) == 1) {
                Pxqiandanpaymoney ishavepay = iPxqiandanpaymoneyService.GetQiandanPayMoneyStyleByqdID(qiandan.getId()
                        , pxqiandanpaymoney.getPaymoneystyleID());
                if (ishavepay == null) {
                    Pxqiandanpaymoney qdpm = new Pxqiandanpaymoney();
                    qdpm.setPayMoney(pxqiandanpaymoney.getPaymoney());
                    qdpm.setPaymoneyStyleID(pxqiandanpaymoney.getPaymoneystyleID());
                    qdpm.setQiandanID(qiandan.getId());
                    qdpm.setIsWeikuan(0);
                    qdpm.setQianDanDate(qiandanshenpi.getQiandandate());
                    qdpm.setQiyeID(loginUser.getQiyeID());
                    iPxqiandanpaymoneyService.save(qdpm);


                } else {

                }
                if (pxqiandanpaymoney.getPaymoneystyleID() != 3) {
                    Pxliushuizhangtable liushui = new Pxliushuizhangtable();
                    Random random = new Random();
                    liushui.setLiushuiDateTime(qiandan.getQiandandate());
                    liushui.setCampusID(qiandanshenpi.getCampusID());
                    liushui.setPayMoneyStyle(pxqiandanpaymoney.getPaymoneystyleID());
                    liushui.setZhichuMoney(new BigDecimal(0));
                    liushui.setShouzhiStyleID(1L);
                    liushui.setJinbanRen(qiandanshenpi.getAddstaffID());
                    liushui.setStuID(pxstutable.getId());
                    liushui.setQiandanID(qiandan.getId());
                    liushui.setLuruTime(new Date());
                    liushui.setQiyeID(loginUser.getQiyeID());
                    liushui.setJinbanRen(qiandanshenpi.getAddstaffID());
                    String zyText = "";
                    if (StringUtils.isNotBlank(qiandanshenpi.getBeizhu())) {
                        zyText = "说明：" + qiandanshenpi.getBeizhu();
                    }
                    if (qiandanshenpi.getDingjing().compareTo(new BigDecimal(0)) == 1) {
                        liushui.setShouruMoney(qiandanshenpi.getDingjing());
                        liushui.setLiushuiZaiyao(pxstutable.getStuName() + "同学：定金交费！--" + zyText);
                    } else {
                        liushui.setShouruMoney(pxqiandanpaymoney.getPaymoney());
                        liushui.setLiushuiZaiyao(pxstutable.getStuName() + "同学，全款交费！--" + zyText);
                    }
                    liushui.setAddStaffID(qiandanshenpi.getAddstaffID());
                    iPxliushuizhangtableService.save(liushui);
                }
            }
        }
        qiandanshenpi.setShenpiState(2);
        qiandanshenpi.setShenpiDateTime(new Date());
        qiandanshenpi.setShenpistaffID(loginUser.getStaffID());
        qiandanshenpi.setBuxiStateID(qiandanshenpi.getBuxiStateID());
        pxstutable.setBuxiStateID(qiandanshenpi.getBuxiStateID());
        iPxstutableService.updateById(pxstutable);
        ajaxJson.setSuccess(iQiandanshenpiService.updateById(qiandanshenpi));

        savePxLog.savepxlog("通过了学员id：" + pxstutable.getId() + ",姓名：" + pxstutable.getStuName() + "的签单审批。", "xwcloud" +
                        "-zsbm/zsbm" +
                        "/BaoMingJiaoFei" +
                        "/PassQiandanShenpi", loginUser.getStaffID(), loginUser.getStaffName(), 1,
                loginUser.getQiyeID());

        return ajaxJson;
    }

    /**
     * 驳回签单审批
     *
     * @param request
     * @param shenpiID
     * @param shenpiReson
     * @return
     */
    @RequestMapping(value = "/qiandanshenpibohui", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson qiandanshenpibohui(HttpServletRequest request, long shenpiID, String shenpiReson) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Qiandanshenpi qiandanshenpi = iQiandanshenpiService.getById(shenpiID);
        Pxstutable stu = iPxstutableService.getById(qiandanshenpi.getStuID());
        qiandanshenpi.setShenpiState(1);
        qiandanshenpi.setShenpistaffID(loginUser.getStaffID());
        qiandanshenpi.setShenpiDateTime(new Date());
        qiandanshenpi.setShenpishuoming(shenpiReson);
        ajaxJson.setSuccess(iQiandanshenpiService.updateById(qiandanshenpi));

        savePxLog.savepxlog("驳回了学员id：" + stu.getId() + ",姓名：" + stu.getStuName() + "的签单审批。", "xwcloud-zsbm/zsbm" +
                        "/BaoMingJiaoFei" +
                        "/qiandanshenpibohui", loginUser.getStaffID(), loginUser.getStaffName(), 1,
                loginUser.getQiyeID());

        return ajaxJson;
    }
    //endregion


    //region 签单小程序支付未支付

    /**
     * 分页查询小程序支付的签单信息
     *
     * @param request
     * @param current
     * @param size
     * @return
     */
    @RequestMapping(value = "/GetAllDaizhifuqiandanPages", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson GetAllDaizhifuqiandanPages(HttpServletRequest request, Long current, Long size, long campusID,
                                               String zidingyiStuID,
                                               String stuName, Integer moneystyle) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (campusID != 0) {
            queryWrapper.eq("b.campusID", campusID);
        }
        if (StringUtils.isNotEmpty(zidingyiStuID)) {
            queryWrapper.like("b.zidingyiStuID", zidingyiStuID);
        }
        if (StringUtils.isNotEmpty(stuName)) {
            queryWrapper.like("b.stuName", stuName);
        }
        queryWrapper.eq("a.moneyStyle", moneystyle);
        queryWrapper.orderByDesc("a.qiandanDate");
        ajaxJson.setObj(iQiandanapppayService.GetAllQiandanAppPayPages(new Page(current, size), queryWrapper));
        return ajaxJson;
    }
    //endregion

    //region 动态管理

    /**
     * 分页加载动态信息
     *
     * @param request
     * @param current
     * @param size
     * @param dongtaiTitle
     * @param nickName
     * @param startDate
     * @param endDate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllWscUserDongtaiPages", method = RequestMethod.GET)
    public AjaxJson getAllWscUserDongtaiPages(HttpServletRequest request, Long current, Long size,
                                              String dongtaiTitle, String nickName, String startDate, String endDate) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("dongtai.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(dongtaiTitle)) {
            queryWrapper.like("dongtai.dongtaiTitle", dongtaiTitle);
        }
        if (StringUtils.isNotBlank(nickName)) {
            queryWrapper.like("u.nickName", nickName);
        }
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("dongtai.Addtime", startDate);
        }
        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("dongtai.Addtime", endDate);
        }
        queryWrapper.orderByDesc("dongtai.Addtime");
        ajaxJson.setObj(iWscDongtaiinfoService.getAllWscUserDongtaiPages(new Page(current, size), queryWrapper));
        return ajaxJson;
    }

    /**
     * 分页加载点赞信息
     *
     * @param request
     * @param current
     * @param size
     * @param dongtaiID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/GetAlldianzanPages", method = RequestMethod.GET)
    public AjaxJson GetAlldianzanPages(HttpServletRequest request, Long current, Long size, Long dongtaiID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("b.qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("a.dongtaiID", dongtaiID);
        ajaxJson.setObj(iWscDongtaiinfoService.GetPagesDianzanInfo(new Page(current, size), queryWrapper));
        return ajaxJson;
    }

    @ResponseBody
    @RequestMapping(value = "/GetAllPingLunPages", method = RequestMethod.GET)
    public AjaxJson GetAllPingLunPages(HttpServletRequest request, Long current, Long size, Long dongtaiID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("b.qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("a.dongtaiID", dongtaiID);
        ajaxJson.setObj(iWscDongtaiinfoService.GetPagesPinglunInfos(new Page(current, size), queryWrapper));
        return ajaxJson;
    }

    /**
     * 删除动态信息
     *
     * @param request
     * @param dongtaiID
     * @return
     */
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/DeleteDongtaiInfo", method = RequestMethod.DELETE)
    public AjaxJson DeleteDongtaiInfo(HttpServletRequest request, Long dongtaiID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("dongtaiID", dongtaiID);
        iDongtaiDianzangService.remove(queryWrapper);
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("dongtaiID", dongtaiID);
        iDongtaiPinglunService.remove(queryWrapper1);
        iWscDongtaiinfoService.removeById(dongtaiID);
        ajaxJson.setSuccess(true);
        ajaxJson.setMsg("删除动态信息成功");
        return ajaxJson;
    }
    //endregion
}
