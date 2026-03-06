package com.xwcloud.cloud.sys.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.ExportExcel;
import com.xwcloud.cloud.common.wechatpay.WeChatPayDto;
import com.xwcloud.cloud.common.wechatpay.WeChatPayUtil;
import com.xwcloud.cloud.model.Form.addgonggaoForm;
import com.xwcloud.cloud.model.Form.sys.UpdateteachSubjectForm;
import com.xwcloud.cloud.model.Form.sys.saveteachSubjectForm;
import com.xwcloud.cloud.model.Form.sys.staffForm;
import com.xwcloud.cloud.model.Form.sys.updatestaffForm;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.*;
import com.xwcloud.cloud.sys.Service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@Component
@RequestMapping("/sys/StaffManagement")
@Api(tags = "员工管理")
public class StaffManagementController {
    @Autowired
    IPxstafftableService iPxstafftableService;

    @Autowired
    IPxworkdayrecordtableService iPxworkdayrecordtableService;

    @Autowired
    IPxworkweekrecordtableService iPxworkweekrecordtableService;

    @Autowired
    ILogxjbtableService iLogxjbtableService;

    @Autowired
    IPxcampustableService iPxcampustableService;

    @Autowired
    IPxwxusertableService iPxwxusertableService;

    @Autowired
    IPxstutableService iPxstutableService;

    @Autowired
    IPxteachsubjecttableService iPxteachsubjecttableService;

    @Autowired
    IPxsubjecttableService iPxsubjecttableService;

    @Autowired
    IPxtuisongtableService iPxtuisongtableService;

    @Autowired
    IPxyichangkaoqintableService iPxyichangkaoqintableService;

    @Autowired
    IPxtuisongtypetableService iPxtuisongtypetableService;

    @Autowired
    IPxgonggaotableService iPxgonggaotableService;

    @Autowired
    IPxstaffposttableService iPxstaffposttableService;

    @Autowired
    IPxgonggaostafftableService iPxgonggaostafftableService;

    @Autowired
    IPxpaymoneystyletableService iPxpaymoneystyletableService;

    @Autowired
    IPxpowertableService iPxpowertableService;
    @Autowired
    AmqpTemplate amqpTemplate;

    @Autowired
    private WeChatPayUtil weChatPayUtil;
    @Autowired
    IWscUserService iWscUserService;

    @Autowired
    sendMessage sendMessage;

    @ApiOperation("修改密码")
    @ResponseBody
    @PostMapping("updateuserpwd")
    public AjaxJson updateuserpwd(String id, String oldpwd, String pwd) {
        AjaxJson ajaxJson = new AjaxJson();
        Pxstafftable staff = iPxstafftableService.getById(id);


        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!new BCryptPasswordEncoder().matches(oldpwd, staff.getPassword())) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("密码不正确");
            return ajaxJson;
        }

        String passHash = encoder.encode(pwd);
        staff.setPassword(passHash);
        ajaxJson.setSuccess(iPxstafftableService.updateById(staff));
        return ajaxJson;
    }

    //region 员工账号管理

    /**
     * 获取登录人信息
     *
     * @return
     */
    @RequestMapping(value = "/GetLoginUserInfo", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取登录人信息")
    public AjaxJson GetLoginUserInfo(HttpServletRequest request) {

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Long staffID = Long.valueOf(loginUser.getStaffID());
        Pxstafftable pxstafftable1 = iPxstafftableService.getById(staffID);
        Long staffpostID = pxstafftable1.getStaffPostID();
        StaffVO staffVO = new StaffVO();
        staffVO.setId(pxstafftable1.getId());
        staffVO.setStaffSex(pxstafftable1.getStaffSex());
        staffVO.setStaffName(pxstafftable1.getStaffName());
        staffVO.setStaffTel(pxstafftable1.getStaffTel());
        staffVO.setStaffPostID(pxstafftable1.getStaffPostID());
        staffVO.setName(iPxstaffposttableService.getById(pxstafftable1.getStaffPostID()).getStaffpostName());
        staffVO.setPhoto(pxstafftable1.getPhoto());
        staffVO.setPermission(iPxstafftableService.GetStaffInfoDetail(qiyeID, staffpostID));
        AjaxJson ajaxJson = new AjaxJson();
        //Pxstafftable pxstafftable = iPxstafftableService.getById("11");
        // ajaxJson.setObj(iPxstafftableService.GetStaffInfoDetail(qiyeID,staffID,staffPostID));
        ajaxJson.setObj(staffVO);
        return ajaxJson;
    }

    /**
     * 获取所有校区
     *
     * @return
     */
    @RequestMapping(value = "/GetAllcampus", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取所有校区")
    public AjaxJson GetAllcampus(HttpServletRequest request, long menuID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper searchdata = new QueryWrapper();
        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", menuID);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        searchdata.eq("qiyeID", loginUser.getQiyeID());
        if (lookPower.equals("0")) {//个人权限
            searchdata.eq("id", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            searchdata.eq("id", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            searchdata.in("id", lookPower);
        }
        ajaxJson.setObj(iPxcampustableService.GetSearchCampusList(searchdata));
        return ajaxJson;
    }


    @GetMapping("getmanagerNum")
    @ResponseBody
    @ApiOperation(value = "获取管理员数量")
    public AjaxJson getmanagerNum(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        List<Pxstafftable> list = iPxstafftableService.list(new QueryWrapper<Pxstafftable>()
                .eq("role", 1)
                .eq("qiyeID", loginUser.getQiyeID())
        );
        ajaxJson.setObj(list.size());
        return ajaxJson;
    }

    /**
     * 添加员工信息
     *
     * @return
     */
    @RequestMapping(value = "/addPxstafftable", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加员工信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "员工ID", required = false),
            @ApiImplicitParam(name = "staffName", value = "员工姓名", required = true),
            @ApiImplicitParam(name = "staffTel", value = "员工联系电话", required = true),
            @ApiImplicitParam(name = "password", value = "账号密码", required = true),
            @ApiImplicitParam(name = "staffSex", value = "员工性别", required = false),
            @ApiImplicitParam(name = "staffBirthday", value = "员工生日", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true),
            @ApiImplicitParam(name = "staffPostID", value = "岗位ID", required = true),
            @ApiImplicitParam(name = "staffState", value = "员工状态1正常，2冻结，3离职，默认值1", required = true),
            @ApiImplicitParam(name = "photo", value = "员工头像", required = false),
            @ApiImplicitParam(name = "qq", value = "员工QQ号", required = false),
            @ApiImplicitParam(name = "email", value = "邮箱地址", required = false),
            @ApiImplicitParam(name = "wx", value = "微信号", required = false),
            @ApiImplicitParam(name = "douyin", value = "抖音号", required = false),
            @ApiImplicitParam(name = "joinTime", value = "入职时间", required = true),
            @ApiImplicitParam(name = "shuoMing", value = "说明信息", required = false),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = false),
    })
    public AjaxJson addPxstafftable(HttpServletRequest request, @RequestBody staffForm form) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Pxstafftable me = iPxstafftableService.getById(loginUser.getStaffID());
        if (me.getRole() < Integer.valueOf(form.getRole())) {
            ajaxJson.setMsg("低级别员工不能操作高级别员工");
            ajaxJson.setCode("N");
            return ajaxJson;
        }

        QueryWrapper queryWrapper = new QueryWrapper();
        QueryWrapper queryWrapper1 = new QueryWrapper();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date JT = null;
        try {
            JT = sdf.parse(form.getJoinTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        queryWrapper.eq("StaffTel", form.getStaffTel());
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        List<Pxstafftable> pxstafftableList = iPxstafftableService.list(queryWrapper);

        queryWrapper1.eq("StaffName", form.getStaffName());
        queryWrapper1.eq("qiyeID", loginUser.getQiyeID());
        List<Pxstafftable> pxstafftableList2 = iPxstafftableService.list(queryWrapper1);

        if (pxstafftableList.size() > 0) {
            ajaxJson.setMsg("已存在该手机号的员工，请不要重复添加");
            ajaxJson.setCode("N");
            return ajaxJson;
        } else if (pxstafftableList2.size() > 0) {
            ajaxJson.setMsg("员工姓名不允许重复！请更换名字");
            ajaxJson.setCode("N");
            return ajaxJson;
        } else {
            Pxstafftable one = new Pxstafftable();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String passHash = encoder.encode(form.getPassword());
            one
                    .setStaffName(form.getStaffName())
                    .setStaffTel(form.getStaffTel())
                    .setPassword(passHash)
                    .setStaffSex(form.getStaffSex())
                    .setCampusID(Long.valueOf(form.getCampusID()))
                    .setStaffPostID(Long.valueOf(form.getStaffPostID()))
                    .setRole(Integer.parseInt(form.getRole()))
                    .setJoinTime(JT)
                    .setShuoMing(form.getShuoMing())
                    .setJiaoxueJingyan(form.getJiaoxueJingyan())
                    .setShowInApp(form.getShowInApp() == 1 ? true : false)
                    .setStaffState(1)
                    .setQiyeID(loginUser.getQiyeID());

            ajaxJson.setSuccess(iPxstafftableService.save(one));
        }
//        LogRabbit logRabbit=new LogRabbit();
//        logRabbit.setXTLogByStaff("添加了员工信息","/sys/StaffManagementaddPxstafftable",String.valueOf(loginUser.getStaffID()),loginUser.getQiyeID());
//        Logxjbtable logxjbtable = new Logxjbtable();
//        logxjbtable.setAddTime(new Date());
//        logxjbtable.setFuncName("/sys/StaffManagementaddPxstafftable");
//        logxjbtable.setLogType(1);
//        logxjbtable.setQiyeID("1");
//        logxjbtable.setStaffID(11);
//        logxjbtable.setSystemContent("添加了员工信息");
//        iLogxjbtableService.save(logxjbtable);
        return ajaxJson;
    }

    /**
     * 按分页获取数据列表
     *
     * @param size    数据大小
     * @param current 当前页
     * @return
     */
    @RequestMapping(value = "/getPxstafftablePage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "按分页获取员工信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据大小", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "staffTel", value = "员工联系电话", required = false),
            @ApiImplicitParam(name = "staffName", value = "员工姓名", required = false),
            @ApiImplicitParam(name = "staffSex", value = "员工性别", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "staffPostID", value = "岗位ID", required = false),
            @ApiImplicitParam(name = "sjoinTime", value = "入职时间（开始时间）", required = false),
            @ApiImplicitParam(name = "ejoinTime", value = "入职时间（结束时间）", required = false),
            @ApiImplicitParam(name = "type", value = "查询类别", example = "1", required = true)
    })
    public AjaxJson getPxstafftablePage(HttpServletRequest request,
                                        long size, long current,
                                        String staffTel, String staffName,
                                        String staffSex, String campusID,
                                        String staffPostID, String sjoinTime,
                                        String ejoinTime, String sbirthDay,
                                        String ebirthDay, Integer type, String role
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<staffinfoVo> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (type == 2) {
            queryWrapper.eq("staffState", 1);
        }
        if (type == 3) {
            queryWrapper.eq("staffState", 3);
        }
        if (type == 4) {
            Calendar cal = Calendar.getInstance();
            int month = cal.get(Calendar.MONTH) + 1;
            //int month = new Date().getMonth() + 1;

            queryWrapper.eq("MOnth(a.joinTime)", month);
        }
        if (StringUtils.isNotBlank(staffTel)) {
            queryWrapper.like("staffTel", staffTel);
        }
        if (StringUtils.isNotBlank(staffName)) {
            queryWrapper.like("staffName", staffName);
        }
        if (StringUtils.isNotBlank(staffSex)) {
            queryWrapper.eq("staffSex", staffSex);
        }
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("a.campusID", campusID);
        }
        if (StringUtils.isNotBlank(staffPostID)) {
            queryWrapper.eq("staffPostID", staffPostID);
        }
        if (StringUtils.isNotBlank(sjoinTime)) {
            queryWrapper.ge("joinTime", sjoinTime);
        }
        if (StringUtils.isNotBlank(ejoinTime)) {
            queryWrapper.le("joinTime", ejoinTime);
        }
        if (StringUtils.isNotBlank(sbirthDay)) {
            queryWrapper.ge("a.staffBirthday", sbirthDay);
        }
        if (StringUtils.isNotBlank(ebirthDay)) {
            queryWrapper.le("a.staffBirthday", ebirthDay);
        }
        if (StringUtils.isNotBlank(role)) {
            queryWrapper.eq("a.role", role);
        }
        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", 531);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            searchpower.eq("a.id", loginUser.getStaffID());
        } else if (lookPower.equals("-1")) {//所在校区权限
            searchpower.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            searchpower.in("a.campusID", lookPower);
        }
        queryWrapper.orderByDesc("a.id");

        page = (Page<staffinfoVo>) iPxstafftableService.GetstaffInfoPages(page, queryWrapper);
        //amqpTemplate.convertAndSend("TestQueue","888888888888");
        ajaxJson.setObj(page);
        return ajaxJson;
    }


    @RequestMapping(value = "/GetAllStaffPostListByCampusID", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询所选校区岗位信息")
    public AjaxJson GetAllStaffPostListByCampusID(String campusID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        List<Pxstaffposttable> list = iPxstaffposttableService.list(
                new QueryWrapper<Pxstaffposttable>()
                        .eq("campusID", campusID)
                        .eq("qiyeID", qiyeID));

        ajaxJson.setObj(list);
        return ajaxJson;
    }

    /**
     * 查询所有员工列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getPxstafftableAllList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询员工列表")
    public AjaxJson getPxstafftableAllList() {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("1", 1);
        queryWrapper.eq("staffState", 1);
        List<Pxstafftable> pxstafftableList = iPxstafftableService.list(queryWrapper);
        ajaxJson.setObj(pxstafftableList);
        return ajaxJson;
    }

    /**
     * 修改数据
     *
     * @return
     */
    @RequestMapping(value = "/editPxstafftable", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("修改员工信息")
    public AjaxJson editPxstafftable(@RequestBody updatestaffForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date JT = null;
        try {
            JT = sdf.parse(form.getJoinTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Pxstafftable me = iPxstafftableService.getById(loginUser.getStaffID());

        Pxstafftable one = iPxstafftableService.getById(form.getId());  //查询原数据

        if (!one.getStaffTel().equals(form.getStaffTel())) {
            QueryWrapper queryWrapper1 = new QueryWrapper();
            queryWrapper1.eq("StaffTel", form.getStaffTel());
            queryWrapper1.eq("qiyeID", loginUser.getQiyeID());
            Pxstafftable one1 = iPxstafftableService.getOne(queryWrapper1);
            if (one1 != null) {
                ajaxJson.setMsg("你修改的手机号重复了！");
                ajaxJson.setCode("N");
                ajaxJson.setSuccess(false);
                return ajaxJson;
            }
        }
        if (!one.getStaffName().equals(form.getStaffName())) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("StaffName", form.getStaffName());
            queryWrapper.eq("qiyeID", loginUser.getQiyeID());
            List<Pxstafftable> stafflist = iPxstafftableService.list(queryWrapper);
            if (stafflist.size() > 0) {
                ajaxJson.setMsg("员工姓名不允许重复！请更换名字");
                ajaxJson.setCode("N");
                ajaxJson.setSuccess(false);
                return ajaxJson;
            }
        }

        if (me.getRole() < one.getRole()) {
            ajaxJson.setMsg("低级别员工不能操作高级别员工");
            ajaxJson.setCode("N");
            return ajaxJson;
        } else {
            one
                    .setStaffName(form.getStaffName())
                    .setStaffTel(form.getStaffTel())
                    .setRole(form.getRole())
                    .setStaffSex(form.getStaffSex())
                    .setCampusID(Long.valueOf(form.getCampusID()))
                    .setStaffPostID(Long.valueOf(form.getStaffPostID()))
                    .setJoinTime(JT)
                    .setShuoMing(form.getShuoMing())
                    .setJiaoxueJingyan(form.getJiaoxueJingyan());
            ajaxJson.setSuccess(iPxstafftableService.updateById(one));
        }
        return ajaxJson;
    }

    /**
     * 删除数据
     *
     * @param Id
     * @return
     */
    @RequestMapping(value = "/delPxstafftable", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除员工信息")
    @ApiImplicitParam(name = "Id", value = "员工ID", required = true)
    public AjaxJson delPxstafftable(String Id, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Pxstafftable me = iPxstafftableService.getById(loginUser.getStaffID());
        Pxstafftable one = iPxstafftableService.getById(Id);
        if (me.getRole() < one.getRole()) {
            ajaxJson.setMsg("低级别员工不能操作高级别员工");
            ajaxJson.setCode("N");
            return ajaxJson;
        } else {
            ajaxJson.setSuccess(iPxstafftableService.removeById(Id));
        }
        return ajaxJson;
    }

    /**
     * 获取指定对象
     *
     * @return
     */
    @RequestMapping(value = "/getPxstafftable", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "根据ID获取对应员工信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pxtafftableId", value = "员工ID", required = true)
    })
    public AjaxJson getPxstafftable(String pxtafftableId) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxstafftableService.getById(pxtafftableId));
        return ajaxJson;
    }

    /**
     * 更新员工状态（1正常，2冻结，3离职）
     *
     * @param staffState
     * @param id
     * @return
     */
    @RequestMapping(value = "/UpdateStaffSate", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "更新员工状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "staffState", value = "员工状态1正常，2冻结，3离职", required = true),
            @ApiImplicitParam(name = "id", value = "员工ID", required = true)
    })
    public AjaxJson UpdateStaffSate(int staffState, String id, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        String[] IDs = id.split(",");
        List<Pxstafftable> list = iPxstafftableService.list(new QueryWrapper<Pxstafftable>()
                .in("id", IDs)
                .eq("qiyeID", loginUser.getQiyeID())
        );
        for (Pxstafftable item : list) {
            Pxstafftable me = iPxstafftableService.getById(loginUser.getStaffID());
            Pxstafftable one = iPxstafftableService.getById(item.getId());
            if (me.getRole() < one.getRole()) {
                ajaxJson.setMsg("低级别员工不能操作高级别员工");
                ajaxJson.setCode("N");
                return ajaxJson;
            } else {
                item.setStaffState(staffState);
                iPxstafftableService.updateById(item);
            }
        }

        return ajaxJson;

    }


    /**
     * 重置用户密码
     *
     * @param Id
     * @return
     */
    @RequestMapping(value = "/ResetTsaffPassword", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "重置用户密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Id", value = "员工ID", required = true)
    })
    public AjaxJson ResetTsaffPassword(String Id, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        String[] IDs = Id.split(",");
        List<Pxstafftable> list = iPxstafftableService.list(new QueryWrapper<Pxstafftable>()
                .in("id", IDs)
                .eq("qiyeID", loginUser.getQiyeID())
        );
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        for (Pxstafftable item : list) {
            Pxstafftable me = iPxstafftableService.getById(loginUser.getStaffID());
            Pxstafftable one = iPxstafftableService.getById(item.getId());
            if (me.getRole() < one.getRole()) {
                ajaxJson.setMsg("低级别员工不能操作高级别员工");
                ajaxJson.setCode("N");
                return ajaxJson;
            } else {
                item.setPassword(encoder.encode("123456"));
                iPxstafftableService.updateById(item);
            }

        }
        return ajaxJson;
    }

    /**
     * 解除员工微信绑定
     *
     * @param staffID
     * @return
     */
    @RequestMapping(value = "/jiechuStaffWeixinBind", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "解除员工微信绑定")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "staffID", value = "员工ID", required = true)
    })
    public AjaxJson jiechuStaffWeixinBind(String staffID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        String[] IDs = staffID.split(",");

        for (String item : IDs) {


            Pxstafftable me = iPxstafftableService.getById(loginUser.getStaffID());
            Pxstafftable one = iPxstafftableService.getById(item);
            if (me.getRole() < one.getRole()) {
                ajaxJson.setMsg("低级别员工不能操作高级别员工");
                ajaxJson.setCode("N");
                return ajaxJson;
            } else {
                WscUser satffuser = iWscUserService.getOne(new QueryWrapper<WscUser>()
                        .eq("qiyeID", loginUser.getQiyeID())
                        .eq("phoneNumber", one.getStaffTel())
                );
                if (satffuser == null) {
                    ajaxJson.setCode("N");
                    ajaxJson.setMsg("未找到该员工的微信绑定信息");
                    return ajaxJson;
                } else {
                    ajaxJson.setSuccess(iWscUserService.removeById(satffuser.getId()));
                }

//                Pxwxusertable staffWeixin = iPxwxusertableService.GetWxuserByStaffID(item);
//                if (staffWeixin != null) {
//                    Pxstutable stu = iPxstutableService.GetstuInfoByParentTel(staffWeixin.getTel());
//                    if (stu != null) {
//                        staffWeixin.setStaffID(null);
//                        ajaxJson.setSuccess(iPxwxusertableService.updateById(staffWeixin));
//                        return ajaxJson;
//                    } else {
//                        ajaxJson.setSuccess(iPxwxusertableService.removeById(staffWeixin.getId()));
//                        return ajaxJson;
//                    }
//                } else {
//                    ajaxJson.setCode("N");
//                    ajaxJson.setMsg("未找到该员工的微信绑定信息");
//                    return ajaxJson;
//                }
            }


        }

        return ajaxJson;

    }

    //region

    /**
     * 分页查询老师任教科目
     *
     * @param size
     * @param current
     * @param subjectName
     * @param teachCampusName
     * @param staffID
     * @return
     */
    @RequestMapping(value = "/GetTeacheSubjectPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页查询老师任教科目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页码", example = "1", required = true),
            @ApiImplicitParam(name = "subjectName", value = "任教科目名称", required = false),
            @ApiImplicitParam(name = "teachCampusName", value = "任教校区", required = false),
            @ApiImplicitParam(name = "staffID", value = "老师ID", required = true),
    })
    public AjaxJson GetTeacheSubjectPages(long size, long current, String subjectId, String teachCampusId, String staffID) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<teachSubjectVo> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("1", 1);
        if (StringUtils.isNotBlank(subjectId)) {
            queryWrapper.eq("b.teachSubjectID", subjectId);
        }
        if (StringUtils.isNotBlank(teachCampusId)) {
            queryWrapper.eq("e.id", teachCampusId);
        }
        page = (Page<teachSubjectVo>) iPxteachsubjecttableService.GetTeacheSubjectPages(page, staffID, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 保存任教科目
     *
     * @param request
     * @param form
     * @return
     */
    @RequestMapping(value = "/saveteachSubject", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存任教科目")
    public AjaxJson saveteachSubject(HttpServletRequest request, @RequestBody saveteachSubjectForm form) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        for (String subjectID : form.getTeachSubjectID().split(",")) {
            long teachsubjectID = Long.valueOf(subjectID);
            Pxteachsubjecttable one = iPxteachsubjecttableService.getOne(new QueryWrapper<Pxteachsubjecttable>()
                    .eq("staffID", form.getStaffID())
                    .eq("teachSubjectID", teachsubjectID)
            );

            if (one != null) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("该老师已任教次科目");
                return ajaxJson;
            }

            Pxteachsubjecttable pxteachsubjecttable = new Pxteachsubjecttable();
            pxteachsubjecttable.setQiyeID(loginUser.getQiyeID());
            pxteachsubjecttable.setShuoming(form.getShuoming());
            pxteachsubjecttable.setTeachSubjectID(teachsubjectID);
            pxteachsubjecttable.setStaffID(form.getStaffID());
            iPxteachsubjecttableService.save(pxteachsubjecttable);
        }
        ajaxJson.setSuccess(true);
        return ajaxJson;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "修改数据ID", required = true),
            @ApiImplicitParam(name = "staffID", value = "员工ID", required = true),
            @ApiImplicitParam(name = "teachSubjectID", value = "任教科目", required = true),
            @ApiImplicitParam(name = "shuoming", value = "说明信息", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    @RequestMapping(value = "/UpdateteachSubject", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改教师任教科目信息")
    public AjaxJson UpdateteachSubject(@RequestBody UpdateteachSubjectForm form) {
        AjaxJson ajaxJson = new AjaxJson();
        Pxteachsubjecttable pxteachsubjecttable = iPxteachsubjecttableService.getById(form.getId());
        pxteachsubjecttable.setShuoming(form.getShuoming());
        pxteachsubjecttable.setTeachSubjectID(Long.valueOf(form.getTeachSubjectID()));
        ajaxJson.setSuccess(iPxteachsubjecttableService.updateById(pxteachsubjecttable));
        return ajaxJson;
    }

    /**
     * 删除教师任教科目
     *
     * @param Id
     * @return
     */
    @RequestMapping(value = "/DeleteTeachSubject", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除老师任教科目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Id", value = "删除数据ID", required = true)
    })
    public AjaxJson DeleteTeachSubject(String Id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(iPxteachsubjecttableService.removeById(Id));
        return ajaxJson;
    }

    /**
     * 查询所有科目信息（用来绑定下拉框）
     *
     * @return
     */
    @RequestMapping(value = "/GetAllSubjectList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询所有科目信息")
    public AjaxJson GetAllSubjectList(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();

        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", 521);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("campusID", -1);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("campusID", lookPower);
        }

        ajaxJson.setObj(iPxsubjecttableService.list());
        return ajaxJson;
    }


    @RequestMapping(value = "/GetAllSubjecbycampusIDtList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询所有科目信息")
    public AjaxJson GetAllSubjecbycampusIDtList(HttpServletRequest request, String campusID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();

        queryWrapper.eq("campusID", campusID);
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        ajaxJson.setObj(iPxsubjecttableService.list(queryWrapper));
        return ajaxJson;
    }

    /**
     * 查询任教科目详情
     *
     * @param Id
     * @return
     */
    @RequestMapping(value = "/GetTeachingSubjectInfo", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询任教科目详情")
    public AjaxJson GetTeachingSubjectInfo(String Id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxteachsubjecttableService.getById(Id));
        return ajaxJson;
    }
    //endregion
    //endregion

    //region 工作日报

    /**
     * 分页查询工作日报信息
     *
     * @param size
     * @param current
     * @return
     */
    @RequestMapping(value = "/GetAllWorkdayRecordPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页查询工作日报信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "StaffName", value = "员工姓名", required = false),
            @ApiImplicitParam(name = "CampusName", value = "校区名称", required = false),
            @ApiImplicitParam(name = "StaffPostName", value = "员工部门名称", required = false),
            @ApiImplicitParam(name = "LogDate", value = "工作日报日期", required = false),
    })
    public AjaxJson GetAllWorkdayRecordPages(HttpServletRequest request, long size, long current, String StaffName, String CampusName, String StaffPostName,
                                             String SLogDate, String ELogDate) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<workdayrecordVo> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(StaffName)) {
            queryWrapper.like("StaffName", StaffName);
        }

        if (StringUtils.isNotBlank(StaffPostName)) {
            queryWrapper.eq("b.staffPostID", StaffPostName);
        }
        if (StringUtils.isNotBlank(SLogDate)) {
            queryWrapper.ge("LogDate", SLogDate);
        }
        if (StringUtils.isNotBlank(ELogDate)) {
            queryWrapper.le("LogDate", ELogDate);
        }
        //根据权限范围加载数据
        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", 154);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.staffID", loginUser.getStaffID());
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("b.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("b.campusID", lookPower);
        }
        if (StringUtils.isNotBlank(CampusName)) {
            queryWrapper.like("c.id", CampusName);
        }
        ajaxJson.setObj(iPxworkdayrecordtableService.Getworkdayrecords(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * 添加工作日报信息
     *
     * @param pxworkdayrecordtable
     * @return
     */
    @RequestMapping(value = "/AddWorkdayRecord", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("添加工作日报信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "日报ID", required = false),
            @ApiImplicitParam(name = "staffID", value = "员工ID", required = true),
            @ApiImplicitParam(name = "logDate", value = "日报日期", required = true),
            @ApiImplicitParam(name = "logContent", value = "日报内容", required = true),
            @ApiImplicitParam(name = "imgsUrl", value = "日报图片路径", required = false),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson AddWorkdayRecord(Pxworkdayrecordtable pxworkdayrecordtable, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());
        pxworkdayrecordtable.setQiyeID(qiyeID);
        pxworkdayrecordtable.setStaffID(staffID);
        pxworkdayrecordtable.setLogDate(new Date());
        ajaxJson.setSuccess(iPxworkdayrecordtableService.save(pxworkdayrecordtable));
        return ajaxJson;
    }

    /**
     * 修改工作日报
     *
     * @param pxworkdayrecordtable
     * @return
     */
    @RequestMapping(value = "/UpdateWorkdayRecord", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改工作日报")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "日报ID", required = true),
            @ApiImplicitParam(name = "staffID", value = "员工ID", required = true),
            @ApiImplicitParam(name = "LogDate", value = "日报日期", required = true),
            @ApiImplicitParam(name = "LogContent", value = "日报内容", required = true),
            @ApiImplicitParam(name = "ImgsUrl", value = "日报图片路径", required = false),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson UpdateWorkdayRecord(Pxworkdayrecordtable pxworkdayrecordtable) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(iPxworkdayrecordtableService.updateById(pxworkdayrecordtable));
        return ajaxJson;
    }

    /**
     * 查询工作日志详情
     *
     * @param Id
     * @return
     */
    @RequestMapping(value = "/GetdailyworkInfobyId", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询工作日志详情")
    public AjaxJson GetdailyworkInfobyId(String Id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxworkdayrecordtableService.getById(Id));
        return ajaxJson;
    }

    /**
     * 删除工作日报信息
     *
     * @param Id
     * @return
     */
    @RequestMapping(value = "/DeleteWorkdayRecord", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除工作日报信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Id", value = "工作日报ID", required = true)
    })
    public AjaxJson DeleteWorkdayRecord(String Id) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] IDs = Id.split(",");
        ajaxJson.setSuccess(iPxworkdayrecordtableService.removeByIds(Arrays.asList(IDs)));
        return ajaxJson;
    }

    @RequestMapping(value = "/exportdailylogInfos", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出工作日志信息")
    public void exportdailylogInfos(HttpServletResponse response, HttpServletRequest request,
                                    @RequestParam(required = false) String campusID, // 校区ID
                                    @RequestParam(required = false) String startDate, // 开始日期
                                    @RequestParam(required = false) String endDate // 结束日期
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("1", 1);
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("c.id", campusID);
        }
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("a.LogDate", startDate);
        }

        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("a.LogDate", endDate);
        }

        List<workdayrecordVo> pxworkdayrecord = iPxworkdayrecordtableService.GetWorkdayrecordsList(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "岗位", "员工姓名", "日志内容", "日期"},
                pxworkdayrecord,
                new String[]{"CampusName", "StaffPostName", "StaffName", "LogContent", "LogDate"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "工作日志.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion

    //region 周工作总结

    /**
     * 查询周工作总结（分页）
     *
     * @param size
     * @param current
     * @return
     */
    @RequestMapping(value = "/GetweekrecordPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询周工作总结")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "StaffName", value = "员工姓名", required = false),
            @ApiImplicitParam(name = "CampusName", value = "校区名称", required = false),
            @ApiImplicitParam(name = "StaffPostName", value = "员工部门名称", required = false),
            @ApiImplicitParam(name = "startDate", value = "周起始日期", required = false),
    })
    public AjaxJson GetweekrecordPages(HttpServletRequest request, long size, long current, String StaffName, String CampusName, String StaffPostName,
                                       String SstartDate, String EstartDate) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<PxworkweekrecordVo> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(StaffName)) {
            queryWrapper.like("b.staffName", StaffName);
        }
        if (StringUtils.isNotBlank(CampusName)) {
            queryWrapper.eq("d.campusName", CampusName);
        }
        if (StringUtils.isNotBlank(StaffPostName)) {
            queryWrapper.eq("b.staffPostID", StaffPostName);
        }
        if (StringUtils.isNotBlank(SstartDate)) {
            queryWrapper.ge("a.startDate", SstartDate);
        }
        if (StringUtils.isNotBlank(EstartDate)) {
            queryWrapper.le("a.startDate", EstartDate);
        }
        //根据权限范围加载数据
        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", 154);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.staffID", loginUser.getStaffID());
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("b.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("b.campusID", lookPower);
        }
        ajaxJson.setObj(iPxworkweekrecordtableService.Getworkweekrecords(page, queryWrapper));
        return ajaxJson;
    }

    @RequestMapping(value = "/getWeekworkrecordInfoById", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询周工作总结详细信息")
    public AjaxJson getWeekworkrecordInfoById(String Id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxworkweekrecordtableService.getById(Id));
        return ajaxJson;
    }

    /**
     * 添加周工作总结
     *
     * @param pxworkweekrecordtable
     * @return
     */
    @RequestMapping(value = "/Addweekrecord", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加周工作总结")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "staffID", value = "员工ID", required = true),
            @ApiImplicitParam(name = "startDate", value = "周起始日期", required = true),
            @ApiImplicitParam(name = "endDate", value = "周结束日期", required = true),
            @ApiImplicitParam(name = "thisWeekRecord", value = "本周工作总结", required = true),
            @ApiImplicitParam(name = "nextWeekRecord", value = "下周工作计划 ", required = true),
            @ApiImplicitParam(name = "imgsUrl", value = "工作总结图片", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson Addweekrecord(Pxworkweekrecordtable pxworkweekrecordtable, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        pxworkweekrecordtable.setQiyeID(loginUser.getQiyeID());
        pxworkweekrecordtable.setStaffID(loginUser.getStaffID());
        pxworkweekrecordtable.setLuruDate(new Date());
        ajaxJson.setSuccess(iPxworkweekrecordtableService.save(pxworkweekrecordtable));
        return ajaxJson;
    }

    /**
     * 修改周工作总结
     *
     * @param pxworkweekrecordtable
     * @return
     */
    @RequestMapping(value = "/Updateweekrecord", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改周工作总结")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "周工作总结ID", required = true),
            @ApiImplicitParam(name = "staffID", value = "员工ID", required = true),
            @ApiImplicitParam(name = "startDate", value = "周起始日期", required = true),
            @ApiImplicitParam(name = "endDate", value = "周结束日期", required = true),
            @ApiImplicitParam(name = "thisWeekRecord", value = "本周工作总结", required = true),
            @ApiImplicitParam(name = "nextWeekRecord", value = "下周工作计划 ", required = true),
            @ApiImplicitParam(name = "luruDate", value = "录入日期", required = true),
            @ApiImplicitParam(name = "imgsUrl", value = "工作总结图片", required = false),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson Updateweekrecord(Pxworkweekrecordtable pxworkweekrecordtable) {
        AjaxJson ajaxJson = new AjaxJson();
        Pxworkweekrecordtable pxworkweek = iPxworkweekrecordtableService.getById(pxworkweekrecordtable.getId());
        ajaxJson.setSuccess(iPxworkweekrecordtableService.updateById(pxworkweekrecordtable));
        return ajaxJson;
    }

    /**
     * @param Id
     * @return * 删除周工作总结
     */
    @RequestMapping(value = "/Deleteweekrecord", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除周工作总结")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Id", value = "周工作总结ID", required = true)
    })
    public AjaxJson Deleteweekrecord(String Id, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        //LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");//日志
        String[] IDs = Id.split(",");
        ajaxJson.setSuccess(iPxworkweekrecordtableService.removeByIds(Arrays.asList(IDs)));
        return ajaxJson;
    }

    /**
     * 导出周工作总结信息
     *
     * @param response
     * @param campusID
     * @param startDate
     * @param endDate
     */
    @RequestMapping(value = "/exportweeklogInfos", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出周工作总结信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "startDate", value = "开始日期", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束日期", required = false),
    })
    public void exportweeklogInfos(HttpServletResponse response,
                                   @RequestParam(required = false) String campusID, // 校区ID
                                   @RequestParam(required = false) String startDate, // 开始日期
                                   @RequestParam(required = false) String endDate // 结束日期
    ) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("1", 1);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("d.id", campusID);
        }
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("a.startDate", startDate);
        }

        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("a.endDate", endDate);
        }

        List<PxworkweekrecordVo> pxworkdayrecord = iPxworkweekrecordtableService.getworkweekrecordsList(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "岗位", "员工姓名", "本周工作总结", "下周工作要点", "周起始日期", "周结束日期", "录入日期"},
                pxworkdayrecord,
                new String[]{"campusName", "staffpostName", "staffName", "thisWeekRecord", "nextWeekRecord", "startDate-DT", "endDate-DT", "luruDate-DT"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "周工作总结.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //endregion

    //region 员工通讯录

    /**
     * 分页查询员工通讯录
     *
     * @param size
     * @param current
     * @param UserName
     * @param staffName
     * @param campusName
     * @param staffPostName
     * @param stafftel
     * @return
     */
    @RequestMapping(value = "/GetAllStaffTelPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("查询员工通讯录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "UserName", value = "用户名", required = false),
            @ApiImplicitParam(name = "staffName", value = "员工姓名", required = false),
            @ApiImplicitParam(name = "campusName", value = "校区名字", required = false),
            @ApiImplicitParam(name = "staffPostName", value = "员工部门名称", required = false),
            @ApiImplicitParam(name = "stafftel", value = "员工电话", required = false),
    })
    public AjaxJson GetAllStaffTelPages(HttpServletRequest request, long size, long current, String UserName, String staffName, String campusName,
                                        String staffPostName, String stafftel) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<stafftelVo> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(UserName)) {
            queryWrapper.eq("a.UserName", UserName);
        }
        if (StringUtils.isNotBlank(staffName)) {
            queryWrapper.eq("a.staffName", staffName);
        }
        if (StringUtils.isNotBlank(campusName)) {
            queryWrapper.eq("c.campusName", campusName);
        }
        if (StringUtils.isNotBlank(staffPostName)) {
            queryWrapper.eq("b.staffPostName", staffPostName);
        }
        if (StringUtils.isNotBlank(stafftel)) {
            queryWrapper.eq("a.stafftel", stafftel);
        }
        //根据权限范围加载数据
        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", 536);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.id", loginUser.getStaffID());
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }
        ajaxJson.setObj(iPxstafftableService.getstaffTel(page, queryWrapper));
        return ajaxJson;

    }

    /**
     * 修改员工电话号码
     *
     * @param staffTel
     * @param id
     * @return
     */
    @RequestMapping(value = "/UpdateStaffTel", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("修改员工电话号码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "staffTel", value = "员工电话号码", required = true),
            @ApiImplicitParam(name = "id", value = "员工ID", required = true)
    })
    public AjaxJson UpdateStaffTel(String staffTel, Long id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxstafftableService.UpdatetaffTel(staffTel, id));
        return ajaxJson;
    }

    /**
     * 导出员工通讯录
     *
     * @param response
     * @param campusID
     * @param staffpostID
     */
    @RequestMapping(value = "/exportstafftelInfos", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出员工通讯录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "staffpostID", value = "岗位ID", required = false)
    })
    public void exportstafftelInfos(HttpServletResponse response,
                                    @RequestParam(required = false) String campusID,
                                    @RequestParam(required = false) String staffpostID
    ) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("1", 1);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("a.campusID ", campusID);
        }
        if (StringUtils.isNotBlank(staffpostID)) {
            queryWrapper.eq("a.staffPostID", staffpostID);
        }
        List<stafftelVo> pxworkdayrecord = iPxstafftableService.GetstafftelList(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "岗位", "员工姓名", "联系电话"},
                pxworkdayrecord,
                new String[]{"campusName", "staffPostName", "staffName", "stafftel"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "员工通讯录导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion

    //region 员工微信群发
    @RequestMapping(value = "/GetWechatMessageStaffPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页查询员工微信信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", defaultValue = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页码", defaultValue = "1", required = true),
            @ApiImplicitParam(name = "staffName", value = "员工姓名", required = false),
            @ApiImplicitParam(name = "campusName", value = "校区名称", required = false),
            @ApiImplicitParam(name = "tuisongType", value = "推送类别", required = false)
    })
    public AjaxJson GetWechatMessageStaffPages(long size, long current,
                                               String staffName,
                                               long campusID, long tuisongType) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<wechatMessageVO> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("1", 1);
        if (StringUtils.isNotBlank(staffName)) {
            queryWrapper.like("b.staffName", staffName);
        }
        if (campusID != 0) {
            queryWrapper.eq("b.campusID", campusID);
        }
        if (tuisongType != 0) {
            queryWrapper.eq("a.tuisongTypeName", tuisongType);
        }
        page = (Page<wechatMessageVO>) iPxtuisongtableService.GetyuangongWechatMessagesPages(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/GetTuisongTyleList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "推送类型")
    public AjaxJson GetTuisongTyleList() {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxtuisongtypetableService.getAllTuisongTypeList());
        return ajaxJson;
    }


    /**
     * 员工微信群发
     *
     * @param request
     * @param teaIDs
     * @param tsType
     * @param note
     * @return
     */
    @ResponseBody
    @PostMapping("wxqunfa")
    @ApiOperation("员工微信群发")
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson wxqunfa(HttpServletRequest request, String teaIDs, int tsType, String note) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");

        String[] IDs = teaIDs.split(",");
        for (String item : IDs) {
            Pxtuisongtable pxtuisongtable = new Pxtuisongtable();
            pxtuisongtable.setQiyeID(loginUser.getQiyeID());
            pxtuisongtable.setRole(2);
            pxtuisongtable.setNote(note);
            pxtuisongtable.setAppread(0);
            pxtuisongtable.setAddTime(new Date());
            pxtuisongtable.setStaffID(Long.valueOf(item));
            pxtuisongtable.setWxstate(0);
            pxtuisongtable.setAddStaffID(loginUser.getStaffID());
            pxtuisongtable.setTuisongTypeName(Long.valueOf(tsType)); //?
            iPxtuisongtableService.save(pxtuisongtable);
            sendMessage.sendMessage(pxtuisongtable.getId());
        }
        return ajaxJson;
    }

    /**
     * 员工校区微信群发
     *
     * @param request
     * @param IDs
     * @param tsType
     * @param note
     * @param type
     * @return
     */
    @ResponseBody
    @PostMapping("campusOrstaffpostqunfa")
    @ApiOperation("员工校区微信群发")
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson campusOrstaffpostqunfa(HttpServletRequest request, String IDs, int tsType, String note, int type) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        String[] ALLIDs = IDs.split(",");

        QueryWrapper<Pxstafftable> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("staffState", 1)
                .eq("qiyeID", loginUser.getQiyeID());
        if (type == 1) {
            //校区群发
            queryWrapper.in("campusID", ALLIDs);
        } else {
            //岗位群发
            queryWrapper.in("staffPostID", ALLIDs);
        }
        List<Pxstafftable> stafflist = iPxstafftableService.list(queryWrapper);

        for (Pxstafftable item : stafflist) {
            Pxtuisongtable pxtuisongtable = new Pxtuisongtable();
            pxtuisongtable.setQiyeID(loginUser.getQiyeID());
            pxtuisongtable.setRole(2);
            pxtuisongtable.setNote(note);
            pxtuisongtable.setAppread(0);
            pxtuisongtable.setAddTime(new Date());
            pxtuisongtable.setStaffID(item.getId());
            pxtuisongtable.setWxstate(0);
            pxtuisongtable.setAddStaffID(loginUser.getStaffID());
            pxtuisongtable.setTuisongTypeName(Long.valueOf(tsType)); //?
            iPxtuisongtableService.save(pxtuisongtable);
            sendMessage.sendMessage(pxtuisongtable.getId());
        }
        return ajaxJson;
    }

    //endregion

    //region 员工异常考勤信息

    /**
     * 分页查询员工异常考勤信息
     *
     * @param request
     * @param size
     * @param current
     * @param campusName
     * @param staffpostName
     * @param staffName
     * @param type
     * @return
     */
    @RequestMapping(value = "/GetyichangkaoqinPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页查询员工异常考勤信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", defaultValue = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页码", defaultValue = "1", required = true),
            @ApiImplicitParam(name = "campusName", value = "校区名称", required = false),
            @ApiImplicitParam(name = "staffpostName", value = "岗位名称", required = false),
            @ApiImplicitParam(name = "staffName", value = "员工姓名", required = false),
            @ApiImplicitParam(name = "type", value = "考勤类别", required = false),
    })
    public AjaxJson GetyichangkaoqinPages(HttpServletRequest request,
                                          long size, long current,
                                          String campusName, String staffpostName,
                                          String staffName, Integer type) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<staffyichangkaoqinVO> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(campusName)) {
            queryWrapper.eq("c.campusName", campusName);
        }
        if (StringUtils.isNotBlank(staffpostName)) {
            queryWrapper.eq("b.staffPostID", staffpostName);
        }
        if (StringUtils.isNotBlank(staffName)) {
            queryWrapper.eq("b.staffName", staffName);
        }
        if (type != 0) {
            queryWrapper.eq("a.type", type);
        }
        //根据权限范围加载数据
        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", 535);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq(" a.staffID", loginUser.getStaffID());
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("b.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("b.campusID", lookPower);
        }
        page = (Page<staffyichangkaoqinVO>) iPxyichangkaoqintableService.GetyichangkaoqinPages(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/Addyuanggongyichangkaoqin", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "新增保存员工异常考勤信息")
    public AjaxJson Addyuanggongyichangkaoqin(Pxyichangkaoqintable pxyichangkaoqintable, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());
        pxyichangkaoqintable.setAddDate(new Date());
        pxyichangkaoqintable.setQiyeID(qiyeID);
        pxyichangkaoqintable.setAddstaffID(staffID);
        ajaxJson.setSuccess(iPxyichangkaoqintableService.save(pxyichangkaoqintable));
        return ajaxJson;
    }

    @RequestMapping(value = "/UpdateYuanggongyichangkaoqin", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改员工异常考勤信息")
    public AjaxJson UpdateYuanggongyichangkaoqin(Pxyichangkaoqintable pxyichangkaoqintable) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(iPxyichangkaoqintableService.updateById(pxyichangkaoqintable));
        return ajaxJson;
    }

    @RequestMapping(value = "/Deleteyichangkaoqin", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除异常考勤")
    public AjaxJson Deleteyichangkaoqin(String Id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(iPxyichangkaoqintableService.removeById(Id));
        return ajaxJson;
    }

    @RequestMapping(value = "/getstaffListByCampusID", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "根据校区ID查询员工信息")
    public AjaxJson getstaffListByCampusID(Long campusID) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxstafftableService.GetStaffListByCampusID(campusID));
        return ajaxJson;
    }

    @RequestMapping(value = "/GetyichangkaoqingInfoById", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询员工异常考勤详细信息")
    public AjaxJson GetyichangkaoqingInfoById(String Id, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();

        ajaxJson.setObj(iPxyichangkaoqintableService.getyichangstaffbandCampusID(new QueryWrapper<HashMap<String, String>>()
                .eq("a.qiyeID", loginUser.getQiyeID())
                .eq("a.id", Id)
        ));
        return ajaxJson;
    }


    @RequestMapping(value = "/exportyichangkaoqinInfos", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出异常考勤信息")
    public void exportyichangkaoqinInfos(HttpServletResponse response, HttpServletRequest request,
                                         @RequestParam(required = false) String campusID, // 校区ID
                                         @RequestParam(required = false) String staffpostID,
                                         @RequestParam(required = false) Integer type,
                                         @RequestParam(required = false) String startDate, // 开始日期
                                         @RequestParam(required = false) String endDate // 结束日期
    ) {
        QueryWrapper queryWrapper = new QueryWrapper();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("b.campusID", campusID);
        }
        if (StringUtils.isNotBlank(staffpostID)) {
            queryWrapper.eq("b.staffPostID", staffpostID);
        }
        if (type != 0) {
            queryWrapper.eq("a.type", type);
        }
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("a.riqi", startDate);
        }

        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("a.riqi", endDate);
        }
        List<staffyichangkaoqinVO> pxworkdayrecord = iPxyichangkaoqintableService.getyichangkaoqingList(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "岗位", "员工姓名", "日期", "类别", "考勤说明", "录入时间", "录入人"},
                pxworkdayrecord,
                new String[]{"campusName", "staffpostName", "staffName", "riqi", "type", "shuoMing", "addDate", "lururen"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "教师异常考勤信息导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion

    //region 员工生日

    /**
     * 分页查询员工生日
     *
     * @param size
     * @param current
     * @param staffName
     * @param campusName
     * @param staffPostName
     * @param Sstaffbirth
     * @param Estaffbirth
     * @return
     */
    @RequestMapping(value = "/GetAllStaffBirthPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页查询员工生日")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "staffName", value = "员工姓名", required = false),
            @ApiImplicitParam(name = "campusName", value = "校区名字", required = false),
            @ApiImplicitParam(name = "staffPostName", value = "员工部门名称", required = false),
            @ApiImplicitParam(name = "Sstaffbirth", value = "生日开始时间", required = false),
            @ApiImplicitParam(name = "Estaffbirth", value = "生日结束时间", required = false),
    })
    public AjaxJson GetAllStaffBirthPages(HttpServletRequest request, long size, long current, String staffName, String campusName, String staffPostName,
                                          String Sstaffbirth, String Estaffbirth) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<staffBirthVo> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(staffName)) {
            queryWrapper.like("a.staffName", staffName);
        }
        if (StringUtils.isNotBlank(campusName)) {
            queryWrapper.eq("c.campusName", campusName);
        }
        if (StringUtils.isNotBlank(staffPostName)) {
            queryWrapper.eq("b.id", staffPostName);
        }
        if (StringUtils.isNotBlank(Sstaffbirth)) {
            queryWrapper.ge("a.staffBirthday", Sstaffbirth);
        }
        if (StringUtils.isNotBlank(Estaffbirth)) {
            queryWrapper.le("a.staffBirthday", Estaffbirth);
        }
        //根据权限范围加载数据
        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", 154);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.id", loginUser.getStaffID());
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }
        ajaxJson.setObj(iPxstafftableService.getStaffBirth(page, queryWrapper));
        return ajaxJson;
    }

    @RequestMapping(value = "/UpdateStaffBirth", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "修改员工生日")
    public AjaxJson UpdateStaffBirth(String staffbirth, Long id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxstafftableService.UpdateStaffBirthday(staffbirth, id));
        return ajaxJson;
    }

    @RequestMapping(value = "/exportstaffBirthInfos", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出员工生日信息")
    public void exportstaffBirthInfos(HttpServletResponse response,
                                      @RequestParam(required = false) String campusID,
                                      @RequestParam(required = false) String staffpostID,
                                      @RequestParam(required = false) String Sbirthday,
                                      @RequestParam(required = false) String Ebirthday
    ) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("1", 1);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("a.campusID ", campusID);
        }
        if (StringUtils.isNotBlank(staffpostID)) {
            queryWrapper.eq("a.staffPostID", staffpostID);
        }
        if (StringUtils.isNotBlank(Sbirthday)) {
            queryWrapper.ge("a.staffBirthday", Sbirthday);
        }
        if (StringUtils.isNotBlank(Ebirthday)) {
            queryWrapper.le("a.staffBirthday", Ebirthday);
        }
        List<staffBirthVo> pxworkdayrecord = iPxstafftableService.GetStaffBirthList(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "岗位", "员工姓名", "员工生日"},
                pxworkdayrecord,
                new String[]{"campusName", "staffPostName", "staffName", "StaffBirthDay-DT"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "员工生日导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion

    //region 公司消息

    /**
     * 分页查询公司消息信息
     *
     * @param size
     * @param current
     * @param gonggaoTitel
     * @param SgonggaoDate
     * @param EgonggaoDate
     * @return
     */
    @RequestMapping(value = "/GetGongsigonggaoPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页查询公司消息信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", defaultValue = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页数", defaultValue = "1", required = true),
            @ApiImplicitParam(name = "gonggaoTitel", value = "消息标题查询关键字", required = false),
            @ApiImplicitParam(name = "SgonggaoDate", value = "消息发送查询开始时间", required = false),
            @ApiImplicitParam(name = "EgonggaoDate", value = "消息发送查询结束日期", required = false),
    })
    public AjaxJson GetGongsigonggaoPages(HttpServletRequest request, long size, long current, String gonggaoTitel, String SgonggaoDate, String EgonggaoDate) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<gonggaoVO> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(gonggaoTitel)) {
            queryWrapper.like("a.gonggaoTitel", gonggaoTitel);
        }
        if (StringUtils.isNotBlank(SgonggaoDate)) {
            queryWrapper.ge("a.gonggaoDate", SgonggaoDate);
        }
        if (StringUtils.isNotBlank(EgonggaoDate)) {
            queryWrapper.le("a.gonggaoDate", EgonggaoDate);
        }
        //根据权限范围加载数据
        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", 538);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.staffID", loginUser.getStaffID());
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("b.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("b.campusID", lookPower);
        }
        ajaxJson.setObj(iPxgonggaotableService.GetgonggaoPages(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * 删除公司消息
     *
     * @param Id
     * @return
     */
    @RequestMapping(value = "/DeleteGongsiGonggao", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除公司消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Id", value = "数据ID", required = true)
    })
    public AjaxJson DeleteGongsiGonggao(String Id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(iPxgonggaotableService.removeById(Id));
        return ajaxJson;
    }

    /**
     * 导出公司消息
     *
     * @param response
     * @param SgonggaoDate
     * @param EgonggaoDate
     */
    @RequestMapping(value = "/exportgongsixiaoxi", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出公司消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "SgonggaoDate", value = "公告日期查询开始时间", required = false),
            @ApiImplicitParam(name = "EgonggaoDate", value = "公告日期查询结束时间", required = false),
    })
    public void exportgongsixiaoxi(HttpServletResponse response,
                                   @RequestParam(required = false) String SgonggaoDate,
                                   @RequestParam(required = false) String EgonggaoDate
    ) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("1", 1);
        if (StringUtils.isNotBlank(SgonggaoDate)) {
            queryWrapper.ge("a.gonggaoDate", SgonggaoDate);
        }
        if (StringUtils.isNotBlank(EgonggaoDate)) {
            queryWrapper.le("a.gonggaoDate", EgonggaoDate);
        }
        List<gonggaoVO> pxworkdayrecord = iPxgonggaotableService.GetgonggaoList(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"公告标题", "公告内容", "发布人", "发布时间"},
                pxworkdayrecord,
                new String[]{"gonggaoTitel", "gonggaoContent", "staffName", "gonggaoDate-DT"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "公告信息导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据校区获取岗位信息
     *
     * @param CampusID
     * @return
     */
    @RequestMapping(value = "/GetStaffPostByCampusID", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "根据校区获取岗位信息")
    public AjaxJson GetStaffPostByCampusID(long CampusID) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxstaffposttableService.GetStaffPostListByCampusID(CampusID));
        return ajaxJson;
    }

    /**
     * 提交保存发送公司消息
     *
     * @param form
     * @return
     */
    @RequestMapping(value = "/savefasonggsXiaoxi", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "提交保存发送公司消息")
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson savefasonggsXiaoxi(addgonggaoForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());
        if (form.getId() == 0L)//添加
        {
            try {
                Pxgonggaotable saveModel = new Pxgonggaotable();
                saveModel.setGonggaocontent(form.getGonggaoText());
                saveModel.setGonggaotitel(form.getGonggaoTitel());
                saveModel.setGonggaodate(new Date());
                saveModel.setStaffid(staffID);
                saveModel.setQiyeID(qiyeID);
                iPxgonggaotableService.save(saveModel);
                if (form.getSendType() == 1) {
                    for (String item : form.getSendCampusId()) {
                        List<Pxstafftable> staff = iPxstafftableService.GetStaffListByCampusID(Long.parseLong(item));
                        for (Pxstafftable pxstafftable : staff) {
                            Pxgonggaostafftable yggg = new Pxgonggaostafftable();
                            yggg.setGonggaoid(saveModel.getId());
                            yggg.setIsread(false);
                            yggg.setJieshoudate(new Date());
                            yggg.setStaffid(pxstafftable.getId());
                            yggg.setQiyeid(loginUser.getQiyeID());
                            yggg.setJieshoudate(new Date());
                            iPxgonggaostafftableService.save(yggg);
                        }
                    }
                } else if (form.getSendType() == 2) {
                    for (String item : form.getSendStaffPostCampusId()) {

                        List<Pxstafftable> staff = iPxstafftableService.GetstaffInfoBycampusidAndstaffpostID(form.getCampusID(), Long.parseLong(item));
                        for (Pxstafftable aa : staff) {
                            Pxgonggaostafftable yggg = new Pxgonggaostafftable();
                            yggg.setGonggaoid(saveModel.getId());
                            yggg.setIsread(false);
                            yggg.setJieshoudate(new Date());
                            yggg.setStaffid(aa.getId());
                            yggg.setQiyeid(loginUser.getQiyeID());
                            yggg.setJieshoudate(new Date());
                            iPxgonggaostafftableService.save(yggg);
                        }
                    }
                } else {
                    for (String y : form.getSendStaffId()) {
                        Pxstafftable staff = iPxstafftableService.getById(y);
                        Pxgonggaostafftable yggg = new Pxgonggaostafftable();
                        yggg.setGonggaoid(saveModel.getId());
                        yggg.setIsread(false);
                        yggg.setJieshoudate(new Date());
                        yggg.setStaffid(staff.getId());
                        yggg.setQiyeid(loginUser.getQiyeID());
                        yggg.setJieshoudate(new Date());
                        iPxgonggaostafftableService.save(yggg);
                    }
                }
                ajaxJson.setSuccess(true);
                return ajaxJson;
            } catch (Exception exception) {
                ajaxJson.setMsg("信息添加失败");
                return ajaxJson;
            }
        } else//修改
        {
            try {
                Pxgonggaotable editModel = iPxgonggaotableService.getById(form.getId());
                editModel.setGonggaotitel(form.getGonggaoTitel());
                editModel.setGonggaocontent(form.getGonggaoText());
                iPxgonggaotableService.updateById(editModel);
                if (form.getSendType() == 1) {
                    for (String item : form.getSendCampusId()) {
                        List<Pxstafftable> staff = iPxstafftableService.GetStaffListByCampusID(Long.parseLong(item));
                        for (Pxstafftable yy : staff) {
                            Pxgonggaostafftable yggg = new Pxgonggaostafftable();
                            yggg.setGonggaoid(form.getId());
                            yggg.setIsread(false);
                            yggg.setJieshoudate(new Date());
                            yggg.setStaffid(yy.getId());
                            yggg.setQiyeid(1L);
                            iPxgonggaostafftableService.save(yggg);
                        }
                    }
                } else if (form.getSendType() == 2) {
                    for (String item : form.getSendStaffPostCampusId()) {
                        List<Pxstafftable> staff = iPxstafftableService.GetstaffInfoBycampusidAndstaffpostID(form.getCampusID(), Long.parseLong(item));
                        for (Pxstafftable aa : staff) {
                            Pxgonggaostafftable yggg = new Pxgonggaostafftable();
                            yggg.setGonggaoid(form.getId());
                            yggg.setIsread(false);
                            yggg.setJieshoudate(new Date());
                            yggg.setStaffid(aa.getId());
                            yggg.setQiyeid(1l);
                            iPxgonggaostafftableService.save(yggg);
                        }
                    }
                } else {
                    for (String y : form.getSendStaffId()) {
                        Pxstafftable staff = iPxstafftableService.getById(y);
                        Pxgonggaostafftable yggg = new Pxgonggaostafftable();
                        yggg.setGonggaoid(form.getId());
                        yggg.setIsread(false);
                        yggg.setJieshoudate(new Date());
                        yggg.setStaffid(staff.getId());
                        yggg.setQiyeid(1l);
                        iPxgonggaostafftableService.save(yggg);
                    }
                }
                ajaxJson.setSuccess(true);
                return ajaxJson;
            } catch (Exception exception) {
                ajaxJson.setMsg("信息修改失败");
                return ajaxJson;
            }
        }
    }
    //endregion

    //region 查询员工（校区_姓名）
    @RequestMapping(value = "/getAllStaffList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询员工（校区_姓名）")
    public AjaxJson getAllStaffList(HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        String dlstaffName = loginUser.getStaffName();
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxstafftableService.GetAllStaffList(qiyeID));
        return ajaxJson;
    }

    /**
     * 查询所有付款方式
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetAllPxPayMoneyStyleList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询所有付款方式")
    public AjaxJson GetAllPxPayMoneyStyleList(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        String dlstaffName = loginUser.getStaffName();
        ajaxJson.setObj(iPxpaymoneystyletableService.GetAllPaymoneystyleList(qiyeID));
        return ajaxJson;
    }

    //endregion
//
//    @RequestMapping(value = "/gettupianyzm",method = RequestMethod.GET)
//    @ResponseBody
//    public verificationCodeUtils.VerificationCode gettupianyzm(){
//        verificationCodeUtils verificationCodeUtils=new verificationCodeUtils();
//        return verificationCodeUtils.generateVerificationCode(80,30);
//    }


    /**
     * 生成预支付订单
     *
     * @param param
     * @return
     * @SneakyThrows
     */
    @PostMapping("/getYuezhifuDingdan")
    @ResponseBody
    @ApiOperation(value = "生成预支付订单")
    public AjaxJson getPrePayInfo(HttpServletRequest request, @RequestBody WeChatPayDto param) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        String payType = param.getPayType();
        if ("11".equals(payType)) {
            Map<String, String> resultMap = null;
            String openId = loginUser.getOpenid();
            try {
                resultMap = weChatPayUtil.getPrePayInfo(param, openId);
            } catch (Exception e) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("生成微信预支付订单失败");
            }
            // 处理公司业务
            ajaxJson.setObj(resultMap);
            return ajaxJson;
        }
        return null;
    }
}
