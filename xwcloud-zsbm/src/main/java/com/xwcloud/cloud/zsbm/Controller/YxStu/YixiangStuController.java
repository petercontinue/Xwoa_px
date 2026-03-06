package com.xwcloud.cloud.zsbm.Controller.YxStu;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.DateUtil;
import com.xwcloud.cloud.common.ExportExcel;
import com.xwcloud.cloud.common.importstuFilesExcel;
import com.xwcloud.cloud.model.Form.YxStuVoForm;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.*;
import com.xwcloud.cloud.zsbm.Controller.savePxLog;
import com.xwcloud.cloud.zsbm.Service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/yxstu/YixiangStu")
@Api(tags = "意向学员")
public class YixiangStuController {

    //region server注入

    @Autowired
    IPxstutableService iPxstutableService;

    @Autowired
    IPxyxgengjintableService iPxyxgengjintableService;

    @Autowired
    IPxyxinvitationtableService iPxyxinvitationtableService;

    @Autowired
    IPxyxinvitedaofangtableService iPxyxinvitedaofangtableService;

    @Autowired
    IPxshitingrecordtableService iPxshitingrecordtableService;

    @Autowired
    IPxkeshistutableService iPxkeshistutableService;

    @Autowired
    IPxbuxikechengtableService iPxbuxikechengtableService;

    @Autowired
    IPxkechengtableService iPxkechengtableService;

    @Autowired
    IPxbuxistyletableService iPxbuxistyletableService;

    @Autowired
    IPxstuclasstableService iPxstuclasstableService;

    @Autowired
    IPxclasstableService iPxclasstableService;

    @Autowired
    IPxpaiketableService iPxpaiketableService;

    @Autowired
    IPxpaiketeachertableService iPxpaiketeachertableService;

    @Autowired
    IPxliuyantableService iPxliuyantableService;

    @Autowired
    IPxyxqiandantableService iPxyxqiandantableService;

    @Autowired
    IPxzhaoshenmubiaocampustableService iPxzhaoshenmubiaocampustableService;

    @Autowired
    IPxzhaoshenmubiaostafftableService iPxzhaoshenmubiaostafftableService;

    @Autowired
    IPxxuanketableService iPxxuanketableService;

    @Autowired
    IPxclassroomtableService iPxclassroomtableService;

    @Autowired
    IPxstafftableService iPxstafftableService;

    @Autowired
    IPxyxtelfromtableService pxyxtelfromtableService;

    @Autowired
    IPxyxtelleveltableService pxyxtelleveltableService;

    @Autowired
    IPxdropdownoptionstableService pxdropdownoptionstableService;

    @Autowired
    IPxstuparamvaluetableService pxstuparamvaluetableService;

    @Autowired
    IPxsysparamdefaulttableService pxsysparamdefaulttableService;

    @Autowired
    IPxsysparamvaluetableService pxsysparamvaluetableService;

    @Autowired
    IPxliushuizhangtableService pxliushuizhangtableService;

    @Autowired
    IPxpowertableService iPxpowertableService;

    @Autowired
    IPxsysparamvaluetableService iPxsysparamvaluetableService;

    @Autowired
    IPxstuparamtypetableService iPxstuparamtypetableService;

    @Autowired
    IPxcampustableService iPxcampustableService;

    @Autowired
    IPxstugradetableService iPxstugradetableService;

    @Autowired
    IPxyxtelfromtableService iPxyxtelfromtableService;

    @Autowired
    IPxsubjecttableService iPxsubjecttableService;

    @Autowired
    IPxstuparamvaluetableService iPxstuparamvaluetableService;

    @Autowired
    savePxLog savePxLog;

    @Autowired
    IPxliushuizhangtableService iPxliushuizhangtableService;
    //endregion

    //region 页面查询的一些方法例如获取所有来源途径等


    /**
     * 查询所有来源途径
     */
    @GetMapping("/getYxSearchtelFrom")
    @ResponseBody
    @ApiOperation("查询所有来源途径")
    public AjaxJson getYxSearchtelFrom(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        List<searchVO> list = pxyxtelfromtableService.getYxSearchtelFrom(qiyeID);
        ajaxJson.setObj(list);
        ajaxJson.setCode("success");
        return ajaxJson;
    }

    /**
     * 查询所有意向等级
     */
    @GetMapping("/getYxSearchTelLevel")
    @ResponseBody
    @ApiOperation("查询所有意向等级")
    public AjaxJson getYxSearchTelLevel(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        List<searchVO> list = pxyxtelleveltableService.getYxSearchTelLevelList(qiyeID);
        ajaxJson.setObj(list);
        ajaxJson.setCode("success");
        return ajaxJson;
    }

    /**
     * 查询所有自定义下拉框的Value
     */
    @GetMapping("/getYxDropdownOption")
    @ResponseBody
    @ApiOperation("查询所有自定义下拉框的Value")
    public AjaxJson getYxDropdownOption(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        List<Pxdropdownoptionstable> list = pxdropdownoptionstableService.list(new QueryWrapper<Pxdropdownoptionstable>().eq("qiyeID", qiyeID));
        ajaxJson.setObj(list);
        ajaxJson.setCode("success");
        return ajaxJson;
    }


    //endregion

    //region 意向学员


    /**
     * 分页查询意向学员信息
     */
    @RequestMapping(value = "/GetYixiangStuByPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("分页查询意向学员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "stuName", value = "学生姓名", required = false),
            @ApiImplicitParam(name = "parentTel", value = "联系电话", required = false),
            @ApiImplicitParam(name = "stuGradeID", value = "学生年级", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区名称", required = false),
            @ApiImplicitParam(name = "telFromID", value = "意向学员意向来源", required = false),
            @ApiImplicitParam(name = "yxLeveID", value = "意向程度", required = false),
            @ApiImplicitParam(name = "dengjiStaffName", value = "登记人", required = false),
            @ApiImplicitParam(name = "shichangStaffName", value = "市场人", required = false),
    })
    public AjaxJson GetYixiangStuByPages(HttpServletRequest request,
                                         long size,
                                         long current,
                                         String stuName,
                                         String parentTel,
                                         Integer stuGradeID,
                                         Integer campusID,
                                         Integer telFromID,
                                         Integer yxLeveID,
                                         String dengjiStaffName,
                                         String shichangStaffName,
                                         String genjinDate,
                                         Integer daofang,
                                         Integer shiting,
                                         String fuzeStaffName,
                                         String dengjiTime,
                                         Integer type
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Page<yixiangStuVo> page = new Page<>(current, size);
        QueryWrapper<yixiangStuVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("a.stuName", stuName);
        }
        if (StringUtils.isNotBlank(parentTel)) {
            queryWrapper.like("a.parentTel", parentTel);
        }
        if (!ObjectUtils.isEmpty(stuGradeID)) {
            queryWrapper.eq("a.stuGradeID", stuGradeID);
        }
        if (!ObjectUtils.isEmpty(campusID)) {
            queryWrapper.eq("a.campusID", campusID);
        }
        if (!ObjectUtils.isEmpty(telFromID)) {
            queryWrapper.eq("a.yxFromID", telFromID);
        }
        if (!ObjectUtils.isEmpty(yxLeveID)) {
            queryWrapper.eq("a.yxLevelID", yxLeveID);
        }
        if (StringUtils.isNotBlank(dengjiStaffName)) {
            queryWrapper.like("a.dengjiStaffName", dengjiStaffName);
        }
        if (StringUtils.isNotBlank(shichangStaffName)) {
            queryWrapper.like("a.shichangStaffName", shichangStaffName);
        }
        if (!ObjectUtils.isEmpty(genjinDate)) {
            String[] split = genjinDate.split("_");
            queryWrapper.between("a.nextGenjinTime", split[0], split[1]);
        }
        if (!ObjectUtils.isEmpty(daofang)) {
            if (daofang == 1) {
                queryWrapper.ne("a.daofangSum", 0);
            } else {
                queryWrapper.eq("a.daofangSum", 0);
            }
        }
        if (!ObjectUtils.isEmpty(shiting)) {
            if (shiting == 1) {
                queryWrapper.ne("a.shitingSum", 0);
            } else {
                queryWrapper.eq("a.shitingSum", 0);
            }
        }
        if (!ObjectUtils.isEmpty(fuzeStaffName)) {
            queryWrapper.like("a.fuzeStaffName", fuzeStaffName);
        }
        if (!ObjectUtils.isEmpty(dengjiTime)) {
            String[] split = dengjiTime.split("_");
            queryWrapper.between("a.dengjiTime", split[0], split[1]);
        }
        if (!ObjectUtils.isEmpty(type)) {
            LocalDate now = LocalDate.now();
            LocalDateTime todayDatetime_e = LocalDateTime.of(now, LocalTime.of(23, 59, 59));
            LocalDate toMonthDate_s = now.with(TemporalAdjusters.firstDayOfMonth());
            LocalDate toMonthDate_e = now.with(TemporalAdjusters.lastDayOfMonth());
            LocalDateTime toMonthDatetime_e = LocalDateTime.of(toMonthDate_e, LocalTime.of(23, 59, 59));
            switch (type) {
                case 2:
//                    今日登记
                    queryWrapper.between("a.dengjiTime", now, todayDatetime_e);
                    break;
                case 3:
//                    本月登记
                    queryWrapper.between("a.dengjiTime", toMonthDate_s, toMonthDatetime_e);
                    break;
                case 4:
//                    今日跟进
                    queryWrapper.between("a.gengjinTime", now, todayDatetime_e);
                    break;
                case 5:
//                    今日未跟进
                    queryWrapper.between("a.nextGenjinTime", now, todayDatetime_e);
                    queryWrapper.notBetween("a.gengjinTime", now, todayDatetime_e);
                    break;
                case 6:
//                    本月跟进
                    queryWrapper.between("a.gengjinTime", toMonthDate_s, toMonthDatetime_e);
                    break;
                case 7:
//                    未跟进
                    queryWrapper.isNull("a.gengjinTime");
                    break;
                case 8:
//                    未分配
                    queryWrapper.isNull("a.yxfenpeistaffID");
                    break;
                default:
                    break;
            }
        }
//根据数据权限范围查询返回数据
        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", 131);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            QueryWrapper searchsysparam = new QueryWrapper<>().eq("qiyeID", qiyeID).eq("stuParamTypeID", 85);
            Pxsysparamvaluetable pxsysparamvaluetable = iPxsysparamvaluetableService.getOne(searchsysparam);
            if (pxsysparamvaluetable != null) {
                if (pxsysparamvaluetable.getModifyValue().equals(1)) {//个人数据权限为登记人
                    queryWrapper.eq(" a.dengjiTeacherID", loginUser.getStaffID());
                } else if (pxsysparamvaluetable.getModifyValue().equals(2)) {//个人数据权限为市场人
                    queryWrapper.eq("a.yxshichangTeacherID", loginUser.getStaffID());
                } else if (pxsysparamvaluetable.getModifyValue().equals(3)) {//个人数据权限为负责人
                    queryWrapper.eq("a.yxfenpeistaffID", loginUser.getStaffID());
                } else {//未分配个人权限范围，返回没有权限数据
                    queryWrapper.eq("a.campusID", 0);
                }
            } else {//未分配个人权限范围，返回没有权限数据
                queryWrapper.eq("a.campusID", 0);
            }
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }

        queryWrapper.orderByDesc("a.id");
        Page<yixiangStuVo> yixiangstuPages = iPxstutableService.getYixiangstuPages(page, queryWrapper);
        ajaxJson.setObj(yixiangstuPages);

        return ajaxJson;
    }

    /**
     * 根据学生ID查询自定义字段的信息
     */
    @GetMapping("/getDiyParamValue/{stuID}")
    @ResponseBody
    @ApiOperation("根据学生ID查询自定义字段的信息")
    public AjaxJson getDiyParamValue(HttpServletRequest request, @PathVariable long stuID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<Pxstuparamvaluetable> wrapper = new QueryWrapper<>();
        wrapper
                .eq("stuID", stuID)
                .eq("qiyeID", qiyeID);
        List<Pxstuparamvaluetable> list = pxstuparamvaluetableService.list(wrapper);
        ajaxJson.setObj(list);
        ajaxJson.setSuccess(true);
        return ajaxJson;
    }

    /**
     * 添加or修改意向学员
     */
    @PostMapping("/addYixiangStuInfo")
    @ResponseBody
    @ApiOperation("添加or修改意向学员信息")
    public AjaxJson addYixiangStuInfo(HttpServletRequest request, @RequestBody YxStuVoForm yxStuVoForm) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<Pxstutable> wrapper = new QueryWrapper<>();
        wrapper.eq("qiyeID", qiyeID);
        Pxsysparamvaluetable one = iPxsysparamvaluetableService.getOne(
                new QueryWrapper<Pxsysparamvaluetable>()
                        .eq("qiyeID", qiyeID)
                        .eq("sysparamTypeID", 84L)
        );

        String logtext = "";

        if (one != null) {
            String yxstulrType = one.getModifyValue(); //y意向学员录入限制
            if (yxstulrType.equals("1")) {
                //名字相同：相同名字不能录入
                wrapper.eq("stuName", yxStuVoForm.getStuName());
            } else if (yxstulrType.equals("2")) {
                //电话相同：相同电话不能录入
                wrapper.eq("parentTel", yxStuVoForm.getParentTel());
            } else if (yxstulrType.equals("3")) {
                //名字和电话都相同：名字和电话都相同不能录入
                wrapper
                        .eq("stuName", yxStuVoForm.getStuName())
                        .eq("parentTel", yxStuVoForm.getParentTel());
            }
        } else {
            //使用默认-->//名字和电话都相同：名字和电话都相同不能录入
            wrapper
                    .eq("stuName", yxStuVoForm.getStuName())
                    .eq("parentTel", yxStuVoForm.getParentTel());
        }

        if (ObjectUtils.isEmpty(yxStuVoForm.getId())) {
            int count = iPxstutableService.count(wrapper);
            if (count > 0) {
                ajaxJson.setMsg("已存在的意向学员");
                ajaxJson.setSuccess(false);
                return ajaxJson;
            }
            if (ObjectUtils.isEmpty(yxStuVoForm.getStuName())) {
                yxStuVoForm.setStuName("");
            }
            if (ObjectUtils.isEmpty(yxStuVoForm.getParentTelRelation())) {
                yxStuVoForm.setParentTelRelation("9");
            }
            if (ObjectUtils.isEmpty(yxStuVoForm.getStuGradeID())) {
                yxStuVoForm.setStuGradeID(0L);
            }
            if (ObjectUtils.isEmpty(yxStuVoForm.getCampusID())) {
                yxStuVoForm.setCampusID(0L);
            }

            yxStuVoForm
                    .setRemainXuefei(BigDecimal.valueOf(0))
                    .setJifenNum(BigDecimal.valueOf(0))
                    .setPasswd(DigestUtils.md5DigestAsHex("123456".getBytes()))
                    .setActivity(2)
                    .setBuxiStateID(1)
                    .setQiyeID(qiyeID)
                    .setLuruType(1)
                    .setDengjiTeacherID(loginUser.getStaffID())
                    .setDengjiTime(new Date())
                    .insert();

            logtext += "添加意向学员:" + yxStuVoForm.getStuName();
            if (!ObjectUtils.isEmpty(yxStuVoForm.getDiyParam())) {
                yxStuVoForm.getDiyParam().forEach(item -> {
                    if (!ObjectUtils.isEmpty(item.getStuParamTypeID()) && !ObjectUtils.isEmpty(item.getParamValue())) {
                        item
                                .setStuID(yxStuVoForm.getId())
                                .setQiyeID(qiyeID)
                                .insert();
                    }
                });
            }
            ajaxJson.setSuccess(true);

        } else {
            //修改
            wrapper.ne("id", yxStuVoForm.getId());
            int count = iPxstutableService.count(wrapper);
            if (count > 0) {
                ajaxJson.setMsg("已存在的意向学员");
                ajaxJson.setSuccess(false);
                return ajaxJson;
            }

            yxStuVoForm.updateById();
            logtext += "修改意向学员:" + yxStuVoForm.getStuName();

            if (!ObjectUtils.isEmpty(yxStuVoForm.getDiyParam())) {
                yxStuVoForm.getDiyParam().forEach(item -> {
                    if (!ObjectUtils.isEmpty(item.getId())) {
                        item.updateById();
                    }
                });
            }
            ajaxJson.setSuccess(true);

            savePxLog.savepxlog(logtext, "xwcloud-zsbm/yxstu/YixiangStu/addYixiangStuInfo", loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());


        }
        return ajaxJson;
    }

    /**
     * 删除意向学员
     */
    @DeleteMapping(value = "/deleteYixiangStu/{ids}")
    @ResponseBody
    @ApiOperation(value = "批量删除意向学员")
    @Transactional(rollbackFor = Exception.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "删除意向学员ID", required = true),
    })
    public AjaxJson deleteYixiangStu(HttpServletRequest request, @PathVariable List<Long> ids) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
            long qiyeID = loginUser.getQiyeID();

            String logtext = "批量删除意向学员,学员：";
            for (Long stuID : ids) {
                Pxstutable delstu = iPxstutableService.getById(stuID);
                logtext += delstu.getStuName() + ",";

                iPxyxgengjintableService.remove(
                        new QueryWrapper<Pxyxgengjintable>()
                                .eq("stuID", stuID)
                                .eq("qiyeID", qiyeID));
                iPxyxinvitationtableService.remove(
                        new QueryWrapper<Pxyxinvitationtable>()
                                .eq("stuID", stuID)
                                .eq("qiyeID", qiyeID));
//                删除试听相关记录
                List<Pxshitingrecordtable> yxshitingList = iPxshitingrecordtableService.list(
                        new QueryWrapper<Pxshitingrecordtable>()
                                .eq("yxStuID", stuID)
                                .eq("qiyeID", qiyeID));
                if (yxshitingList.size() > 0) {
                    List<Pxkeshistutable> kehaoList = iPxkeshistutableService.list(
                            new QueryWrapper<Pxkeshistutable>()
                                    .eq("stuID", stuID)
                                    .eq("qiyeID", qiyeID));
                    if (kehaoList.size() > 0) {
                        ajaxJson.setMsg("该意向学员有试听的课耗，不能删除");
                        ajaxJson.setSuccess(false);
                        return ajaxJson;
                    }
                    List<Pxbuxikechengtable> yxbuxikechengsList = iPxbuxikechengtableService.list(
                            new QueryWrapper<Pxbuxikechengtable>()
                                    .eq("stuID", stuID)
                                    .eq("qiyeID", qiyeID));
                    if (yxbuxikechengsList.size() > 0) {
                        for (Pxbuxikechengtable yxbuxi : yxbuxikechengsList) {
                            Pxkechengtable kecheng = iPxkechengtableService.getById(yxbuxi.getKechengID());
                            String buxiStyleName = iPxbuxistyletableService.getById(kecheng.getBuxiStyleID()).getBuxiStyleName();
                            if (buxiStyleName == "一对一") {
                                List<Pxstuclasstable> stuClassList = iPxstuclasstableService.list(
                                        new QueryWrapper<Pxstuclasstable>()
                                                .eq("buxiID", yxbuxi.getId())
                                                .eq("qiyeID", qiyeID));
                                if (stuClassList.size() > 0) {
                                    for (Pxstuclasstable stuClass : stuClassList) {
                                        Pxclasstable cla = iPxclasstableService.getById(stuClass.getClassID());
                                        if (cla != null) {
//                                            删除班级之前，把这个班的排课全删除掉
                                            List<Pxpaiketable> paikeList = iPxpaiketableService.list(
                                                    new QueryWrapper<Pxpaiketable>()
                                                            .eq("classID", stuClass.getClassID())
                                                            .eq("qiyeID", qiyeID));
                                            if (paikeList.size() > 0) {
                                                for (Pxpaiketable paike : paikeList) {
                                                    iPxpaiketeachertableService.remove(
                                                            new QueryWrapper<Pxpaiketeachertable>()
                                                                    .eq("paikeID", paike.getId())
                                                                    .eq("qiyeID", qiyeID));
                                                }
                                                iPxpaiketableService.remove(
                                                        new QueryWrapper<Pxpaiketable>()
                                                                .eq("classID", stuClass.getClassID())
                                                                .eq("qiyeID", qiyeID));
                                            }
                                            cla.deleteById();
                                        }
                                    }
                                }
                            }
                            iPxstuclasstableService.remove(
                                    new QueryWrapper<Pxstuclasstable>()
                                            .eq("buxiID", yxbuxi.getId())
                                            .eq("qiyeID", qiyeID));
                            yxbuxi.deleteById();
                        }
                    }
                    iPxshitingrecordtableService.remove(
                            new QueryWrapper<Pxshitingrecordtable>()
                                    .eq("yxstuID", stuID)
                                    .eq("qiyeID", qiyeID));
                }

//                删除自定义字段
                pxstuparamvaluetableService.remove(
                        new QueryWrapper<Pxstuparamvaluetable>()
                                .eq("stuID", stuID)
                                .eq("qiyeID", qiyeID));
            }
            iPxstutableService.removeByIds(ids);

            savePxLog.savepxlog(logtext, "xwcloud-zsbm/yxstu/YixiangStu/deleteYixiangStu", loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());

            ajaxJson.setMsg("信息删除成功");
            ajaxJson.setSuccess(true);
            return ajaxJson;
        } catch (Exception e) {
            ajaxJson.setMsg("删除信息出错了");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
    }

    /**
     * 分页查询学生跟进记录信息
     */
    @GetMapping("/getStuGenjinRecordsPages")
    @ResponseBody
    @ApiOperation(value = "分页查询学生跟进记录信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "stuID", value = "学生ID", required = true)
    })
    public AjaxJson getStuGenjinRecordsPages(HttpServletRequest request, long size, long current, long stuID) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<genjinInfoVo> page = new Page(current, size);
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<genjinInfoVo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("a.stuID", stuID)
                .eq("a.qiyeID", loginUser.getQiyeID())
                .orderByDesc("a.addTime");
        Page<genjinInfoVo> gjList = iPxyxgengjintableService.GetgenjinInfoPages(page, queryWrapper);
        ajaxJson.setObj(gjList);
        return ajaxJson;
    }

    /**
     * 查询下次跟进是否必填
     */
    @GetMapping("/getNextGenjingRequired")
    @ResponseBody
    @ApiOperation(value = "查询下次跟进是否必填")
    public AjaxJson getNextGenjingRequired(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Pxsysparamdefaulttable defaultParam = pxsysparamdefaulttableService.getById(86);
        List<Pxsysparamvaluetable> list = pxsysparamvaluetableService.list(
                new QueryWrapper<Pxsysparamvaluetable>()
                        .eq("sysparamTypeID", defaultParam.getId())
                        .eq("qiyeID", qiyeID));
        if (list.size() > 0) {
            Pxsysparamvaluetable valueParam = list.get(0);
            if (valueParam.getModifyValue().equals("2")) {
                ajaxJson.setObj(true);
            } else {
                ajaxJson.setObj(false);
            }
            ajaxJson.setSuccess(true);
        } else {
            if (defaultParam.getDefaultValue().equals("2")) {
                ajaxJson.setObj(true);
            } else {
                ajaxJson.setObj(false);
            }
            ajaxJson.setSuccess(true);
        }
        return ajaxJson;
    }

    /**
     * 新增/修改意向学员跟进信息
     */
    @PostMapping("/saveAndUpdateStuGenjinInfo")
    @ResponseBody
    @ApiOperation("新增/修改意向学员跟进信息")
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson saveAndUpdateStuGenjinInfo(HttpServletRequest request, @RequestBody GenjingVo genjingVo) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
            long qiyeID = loginUser.getQiyeID();
            String logtext = "";
            Pxstutable byId = iPxstutableService.getById(genjingVo.getStuID());
            if (ObjectUtils.isEmpty(genjingVo.getId())) {


                ajaxJson.setSuccess(
                        genjingVo
                                .setIsRead(false)
                                .setAddTime(new Date())
                                .setAdduser(loginUser.getStaffID())
                                .setQiyeID(qiyeID)
                                .insert());

                logtext += "添加意向学员：" + byId.getStuName() + "的下次跟进信息";
                if (!ObjectUtils.isEmpty(genjingVo.getNextGenjinTime())) {
                    byId.setNextGenjinTime(genjingVo.getNextGenjinTime());
                }
                if (!ObjectUtils.isEmpty(genjingVo.getYxLevelID())) {
                    byId.setYxLevelID(genjingVo.getYxLevelID());
                }
                byId.setLastFollowDate(genjingVo.getGengjinTime());
                byId.updateById();
            } else {
                if (ObjectUtils.isEmpty(genjingVo.getId())) {
                    ajaxJson.setMsg("出错了，请刷新重试。");
                    ajaxJson.setSuccess(false);
                    return ajaxJson;
                }
                logtext += "修改意向学员：" + byId.getStuName() + "的下次跟进信息";
                ajaxJson.setMsg("操作成功");
                ajaxJson.setSuccess(
                        genjingVo.updateById()
                );
            }

            savePxLog.savepxlog(logtext, "xwcloud-zsbm/yxstu/YixiangStu/saveAndUpdateStuGenjinInfo", loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());

            return ajaxJson;
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("出错了，请刷新重试。.");
            return ajaxJson;
        }
    }

    /**
     * 删除跟进记录
     */
    @DeleteMapping("/deletegenjinRecords/{Id}")
    @ResponseBody
    @ApiOperation("删除跟进记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Id", value = "删除数据ID", required = true)
    })
    public AjaxJson deletegenjinRecords(@PathVariable Long Id, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Pxyxgengjintable gj = iPxyxgengjintableService.getById(Id);
        Pxstutable stu = iPxstutableService.getById(gj.getStuID());

        List<Pxyxgengjintable> list = iPxyxgengjintableService.list(
                new QueryWrapper<Pxyxgengjintable>()
                        .eq("stuID", gj.getStuID())
                        .eq("qiyeID", loginUser.getQiyeID())
                        .orderByDesc("addTime")
        );
        if (list.size() > 1) {
            if (gj.getAddTime().getTime() < list.get(0).getAddTime().getTime()) {
                //不用删下次跟进时间
            } else {
                iPxstutableService.updateyxstu(gj.getStuID(), loginUser.getQiyeID());
            }
        } else if (list.size() == 1) { //说明只有这一条，删了就没有跟进。把学员下次跟进时间也删除
            iPxstutableService.updateyxstu(gj.getStuID(), loginUser.getQiyeID());
        }


        savePxLog.savepxlog("删除意向学员：" + stu.getStuName() + "的跟进记录。", "xwcloud-zsbm/yxstu/YixiangStu/deletegenjinRecords", loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());

        iPxyxgengjintableService.removeById(gj);
        return ajaxJson;
    }

    /**
     * 分页查询学生邀约记录
     */
    @GetMapping("/getStuInvitationRecordsPages")
    @ResponseBody
    @ApiOperation("分页查询学生邀约记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "stuID", value = "意向学生ID", required = true),
    })
    public AjaxJson getStuInvitationRecordsPages(HttpServletRequest request, long size, long current, long stuID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<yaoyueVo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .orderByDesc("a.addTime")
                .eq("a.qiyeID", qiyeID)
                .eq("a.stuID", stuID);
        ajaxJson.setObj(iPxyxinvitationtableService.GetinvitationByStuIDPages(new Page<>(current, size), queryWrapper));
        return ajaxJson;
    }

    /**
     * 添加/修改意向学员邀约信息
     */
    @PostMapping("/addOrUpdateYxStuInvitationInfo")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("添加/修改意向学员邀约信息")
    public AjaxJson addOrUpdateYxStuInvitationInfo(HttpServletRequest request, @RequestBody Pxyxinvitationtable pxyxinvitationtable) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        String logtext = "";
        Pxstutable stu = iPxstutableService.getById(pxyxinvitationtable.getStuID());
        try {
            if (ObjectUtils.isEmpty(pxyxinvitationtable.getId())) {
                ajaxJson.setSuccess(
                        pxyxinvitationtable
                                .setAddTeacher(loginUser.getStaffID())
                                .setAddTime(new Date())
                                .setQiyeID(qiyeID)
                                .insert()
                );

                logtext += "添加意向学员" + stu.getStuName() + "邀约信息。";
            } else {
                if (ObjectUtils.isEmpty(pxyxinvitationtable.getId())) {
                    ajaxJson.setMsg("出错了，请刷新重试。");
                    ajaxJson.setSuccess(false);
                    return ajaxJson;
                }
                ajaxJson.setSuccess(
                        pxyxinvitationtable
                                .updateById()
                );
                logtext += "修改意向学员" + stu.getStuName() + "邀约信息。";
            }

            savePxLog.savepxlog(logtext, "xwcloud-zsbm/yxstu/YixiangStu/addOrUpdateYxStuInvitationInfo", loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());

            return ajaxJson;
        } catch (Exception e) {
            ajaxJson.setMsg("出错了，请刷新重试。");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
    }

    /**
     * 删除意向学员邀约记录
     */
    @DeleteMapping("/deleteStuInvitation/{Id}")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("删除意向学员邀约记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Id", value = "删除数据ID", required = true)
    })
    public AjaxJson deleteStuInvitation(HttpServletRequest request, @PathVariable Long Id) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Pxyxinvitationtable yy = iPxyxinvitationtableService.getById(Id);
        Pxstutable stu = iPxstutableService.getById(yy.getStuID());

        iPxyxinvitedaofangtableService.remove(
                new QueryWrapper<Pxyxinvitedaofangtable>()
                        .eq("qiyeID", qiyeID)
                        .eq("inviteID", Id));
        ajaxJson.setSuccess(iPxyxinvitationtableService.removeById(Id));

        savePxLog.savepxlog("删除意向学员:" + stu.getStuName() + "的邀约记录。", "xwcloud-zsbm/yxstu/YixiangStu/deleteStuInvitation", loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());

        return ajaxJson;
    }

    /**
     * 分页查询学生到访信息
     */
    @GetMapping("/getStuInvitationDaofangRecordsPages")
    @ResponseBody
    @ApiOperation("分页查询学生到访信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "stuID", value = "学生ID", required = true),
    })
    public AjaxJson getStuInvitationDaofangRecordsPages(HttpServletRequest request, long size, long current, long stuID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<daofangVo> wrapper = new QueryWrapper<>();
        wrapper
                .orderByDesc("t.addTime")
                .eq("t.qiyeID", qiyeID)
                .eq("t.stuID", stuID);
        ajaxJson.setObj(iPxyxinvitedaofangtableService.GetinvitationDaofangByStuIDPages(new Page<daofangVo>(current, size), wrapper));
        return ajaxJson;
    }


    /**
     * 保存、修改意向学员到访信息
     */
    @PostMapping("/saveYxStuDaofangInfo")
    @ApiOperation("保存、修改意向学员到访信息")
    @ResponseBody
    public AjaxJson saveYxStuDaofangInfo(HttpServletRequest request, @RequestBody Daofang2Vo daofang2Vo) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();

        String logtext = "";
        Pxstutable stu = iPxstutableService.getById(daofang2Vo.getStuID());
        try {
            if (ObjectUtils.isEmpty(daofang2Vo.getId())) {
                Pxyxinvitationtable pxyxinvitationtable = new Pxyxinvitationtable();
//                如果没有邀约ID说明是无邀约到访
                if (ObjectUtils.isEmpty(daofang2Vo.getInviteID())) {
                    pxyxinvitationtable
                            .setStuID(daofang2Vo.getStuID())
                            .setAddTime(new Date())
                            .setAddTeacher(loginUser.getStaffID())
                            .setInvitationTime(new Date())
                            .setInvitationZhuangtai("3")
                            .setQiyeID(qiyeID)
                            .insert();
                    daofang2Vo.setInviteID(pxyxinvitationtable.getId());

                    logtext += "添加意向学员：" + stu.getStuName() + "到访记录。";
                } else {
//                    如果有邀约ID则去更新邀约记录的状态
                    pxyxinvitationtable
                            .setId(daofang2Vo.getInviteID())
                            .setInvitationZhuangtai("3")
                            .updateById();
//                    如果有到访记录视为修改
                    List<Pxyxinvitedaofangtable> list = iPxyxinvitedaofangtableService.list(
                            new QueryWrapper<Pxyxinvitedaofangtable>()
                                    .eq("inviteID", daofang2Vo.getInviteID())
                                    .eq("qiyeID", qiyeID));
                    if (list.size() > 0) {
                        daofang2Vo.setId(list.get(0).getId());
                    }
                    logtext += "修改意向学员：" + stu.getStuName() + "到访记录。";
                }
                ajaxJson.setSuccess(
                        daofang2Vo
                                .setAdddanfangren(loginUser.getStaffID())
                                .setAdddaofanftime(new Date())
                                .setQiyeID(qiyeID)
                                .insertOrUpdate()
                );
            } else {
                ajaxJson.setSuccess(daofang2Vo.updateById());
            }

            savePxLog.savepxlog(logtext, "xwcloud-zsbm/yxstu/YixiangStu/saveYxStuDaofangInfo", loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());

            return ajaxJson;
        } catch (Exception e) {
            ajaxJson.setMsg("出错了，请刷新重试。");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
    }

    /**
     * 删除意向学员到访
     */
    @DeleteMapping("/deleteYxStuDaofang/{Id}")
    @ApiOperation("删除意向学员到访")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Id", value = "删除数据ID", required = true)
    })
    public AjaxJson deleteYxStuDaofang(@PathVariable Long Id, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Pxyxinvitedaofangtable byId = iPxyxinvitedaofangtableService.getById(Id);
        Pxyxinvitationtable pxyxinvitationtableById = iPxyxinvitationtableService.getById(byId.getInviteID());
        pxyxinvitationtableById.setInvitationZhuangtai("1").updateById();
        ajaxJson.setSuccess(iPxyxinvitedaofangtableService.removeById(Id));
        Pxstutable stu = iPxstutableService.getById(pxyxinvitationtableById.getStuID());

        savePxLog.savepxlog("删除意向学员：" + stu.getStuName() + "的到访信息。", "xwcloud-zsbm/yxstu/YixiangStu/deleteYxStuDaofang", loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());

        return ajaxJson;
    }

    /**
     * 保存意向学员分配信息
     */
    @PostMapping("/saveFenpeixingxi")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("保存意向学员分配信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "意向学员ID", required = true),
            @ApiImplicitParam(name = "fenpeiStaffID", value = "分配staffid", required = true)
    })
    public AjaxJson saveFenpeixingxi(@RequestBody HashMap<String, Object> map, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        try {
            if (ObjectUtils.isEmpty(map.get("stuIDs")) || ObjectUtils.isEmpty(map.get("fenpeiStaffID"))) {
                ajaxJson.setMsg("出错了，请刷新重试。");
                ajaxJson.setSuccess(false);
                return ajaxJson;
            }
            ArrayList<Long> stuIDs = new ArrayList<>();
            if (map.get("stuIDs") instanceof List<?>) {
                for (Object o : (List<?>) map.get("stuIDs")) {
                    stuIDs.add(Long.parseLong(o.toString()));
                }
            } else {
                ajaxJson.setMsg("出错了，请刷新重试。");
                ajaxJson.setSuccess(false);
                return ajaxJson;
            }
            UpdateWrapper<Pxstutable> wrapper = new UpdateWrapper<>();
            wrapper.lambda()
                    .set(Pxstutable::getYxfenpeistaffID, Long.parseLong(map.get("fenpeiStaffID").toString()))
                    .set(Pxstutable::getYxfenpeiDate, new Date())
                    .in(Pxstutable::getId, stuIDs);
            ajaxJson.setSuccess(
                    iPxstutableService
                            .update(wrapper)
            );

            savePxLog.savepxlog("保存意向学员ID为:" + stuIDs + "的意向学员，意向负责人分配信息。", "xwcloud-zsbm/yxstu/YixiangStu/saveFenpeixingxi", loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());

            return ajaxJson;
        } catch (Exception e) {
            ajaxJson.setMsg("出错了，请刷新重试。");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
    }

    /**
     * 将意向学员设置成未分配
     */
    @PostMapping("/setYxStuWeifenpei")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("将意向学员设置成未分配")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuIDS", value = "学生ID数组", required = true)
    })
    public AjaxJson setYxStuWeifenpei(@RequestBody List<Long> stuIDS, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        try {
            if (ObjectUtils.isEmpty(stuIDS)) {
                ajaxJson.setMsg("出错了，请刷新重试。");
                ajaxJson.setSuccess(false);
                return ajaxJson;
            }
            UpdateWrapper<Pxstutable> wrapper = new UpdateWrapper<>();
            wrapper.lambda()
                    .set(Pxstutable::getYxfenpeiDate, null)
                    .set(Pxstutable::getYxfenpeistaffID, null)
                    .in(Pxstutable::getId, stuIDS);
            ajaxJson.setSuccess(
                    iPxstutableService.update(wrapper)
            );


            savePxLog.savepxlog("保存意向学员ID为:" + stuIDS + "的意向学员设置成未分配。", "xwcloud-zsbm/yxstu/YixiangStu/setYxStuWeifenpei", loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());

            return ajaxJson;
        } catch (Exception e) {
            ajaxJson.setMsg("出错了，请刷新重试。");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
    }

    /**
     * 查询意向学员的试听信息
     */
    @GetMapping("/getYxStuShitingRecords")
    @ResponseBody
    @ApiOperation("查询意向学员的试听信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页码", example = "1", required = true),
            @ApiImplicitParam(name = "stuID", value = "意向学员ID", required = true),
    })
    public AjaxJson getYxStuShitingRecords(HttpServletRequest request, long size, long current, Long stuID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<shitingLiushuiVo> wrapper = new QueryWrapper<>();
        wrapper
                .orderByDesc("a.id")
                .eq("b.id", stuID)
                .eq("a.qiyeID", qiyeID);
        ajaxJson.setObj(
                iPxshitingrecordtableService.GetShitingLiushuiPages(new Page<>(current, size), wrapper)
        );
        return ajaxJson;
    }

    /**
     * 查询所有非一对一班级
     */
    @GetMapping("/getClassListNo1V1")
    @ResponseBody
    @ApiOperation("查询所有非一对一班级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区"),
    })
    public AjaxJson getClassListNo1V1(HttpServletRequest request, Long campusID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<Pxclasstable> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(Pxclasstable::getIsdelete, false)
                .eq(Pxclasstable::getIsShow, 1)
                .eq(Pxclasstable::getIs1v1Class, 0)
                .eq(Pxclasstable::getQiyeID, qiyeID);
        if (!ObjectUtils.isEmpty(campusID) && campusID > 0) {
            wrapper.lambda().eq(Pxclasstable::getCampusID, campusID);
        }
        wrapper.lambda().select(Pxclasstable::getId, Pxclasstable::getClassName);
        ajaxJson.setObj(iPxclasstableService.list(wrapper));
        return ajaxJson;
    }

    /**
     * 根据班级查询排课
     */
    @GetMapping("/getPaikeByClassID")
    @ResponseBody
    @ApiOperation("根据班级查询排课")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classID", value = "班级ID", required = true)
    })
    public AjaxJson getPaikeByClassID(HttpServletRequest request, String classID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        LocalDate now = LocalDate.now().plusDays(-3);
        QueryWrapper<Pxpaiketable> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(Pxpaiketable::getClassID, classID)
                .gt(Pxpaiketable::getHaveClassDate, now)
                .eq(Pxpaiketable::getQiyeID, qiyeID);
        ajaxJson.setObj(iPxpaiketableService.list(wrapper));
        return ajaxJson;
    }

    /**
     * 意向插班班级（一对一）
     */
    @GetMapping("/getYxChabanKc")
    @ResponseBody
    @ApiOperation("意向插班班级（一对一）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID")
    })
    public AjaxJson getYxChabanKc(HttpServletRequest request, Long campusID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<Pxkechengtable> wrapper = new QueryWrapper<>();
        wrapper
                .eq("a.qiyeID", qiyeID);
        if (!ObjectUtils.isEmpty(campusID) && campusID > 0) {
            wrapper.eq("a.qiyeID", campusID);
        }
        ajaxJson.setObj(iPxkechengtableService.getYxChabanKc(wrapper));
        return ajaxJson;
    }

    /**
     * 根据课程查询任课老师
     */
    @GetMapping("/getSubjectTeacher")
    @ResponseBody
    @ApiOperation("根据课程查询任课老师")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "kechengID", value = "课程ID", required = true)
    })
    public AjaxJson getSubjectTeacher(HttpServletRequest request, String kechengID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Pxkechengtable byId = iPxkechengtableService.getById(kechengID);
        ajaxJson.setObj(iPxstafftableService.getSubjectTeacher(byId.getSubjectID(), qiyeID));
        return ajaxJson;
    }

    /**
     * 查询教室
     */
    @GetMapping("/getClassRoom")
    @ResponseBody
    @ApiOperation("查询教室")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID")
    })
    public AjaxJson getClassRoom(HttpServletRequest request, Long campusID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<Pxclassroomtable> wrapper = new QueryWrapper<>();
        wrapper.eq("a.qiyeID", qiyeID);
        if (!ObjectUtils.isEmpty(campusID) && campusID > 0) {
            wrapper.eq("a.campusID", campusID);
        }
        ajaxJson.setObj(iPxclassroomtableService.getClassRoom(wrapper));
        return ajaxJson;
    }

    /**
     * 根据流水ID查支付方式
     */
    @GetMapping("/getPayMoneyStyle")
    @ResponseBody
    @ApiOperation("根据流水ID查支付方式")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "liushuiID", value = "流水ID", required = true)
    )
    public AjaxJson getPayMoneyStyle(HttpServletRequest request, Long liushuiID) {
        AjaxJson ajaxJson = new AjaxJson();
        Pxliushuizhangtable pxliushuizhangtable = pxliushuizhangtableService.getById(liushuiID);
        ajaxJson.setObj(pxliushuizhangtable.getPayMoneyStyle());
        return ajaxJson;
    }

    /**
     * 删除学员试听记录
     */
    @DeleteMapping("/deleteShitingRecords")
    @ResponseBody
    @ApiOperation("删除学员试听记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stID", value = "试听记录ID", required = true),
    })
    public AjaxJson deleteShitingRecords(String stID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Pxshitingrecordtable shiting = iPxshitingrecordtableService.getById(stID);
        Pxstutable stu = iPxstutableService.getById(shiting.getYxStuID());

        ajaxJson.setSuccess(iPxshitingrecordtableService.removeById(stID));

        savePxLog.savepxlog("删除了意向学员:" + stu.getStuName() + "的试听记录。", "xwcloud-zsbm/yxstu/YixiangStu/deleteShitingRecords", loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());

        return ajaxJson;
    }

    /**
     * 检测时间冲突
     */
    public QueryWrapper<Pxpaiketable> paikeTimeJC(Time startTime, Time endTime) {
        QueryWrapper<Pxpaiketable> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .and(wrapperPaikeTimeJC -> {
                    wrapperPaikeTimeJC
                            .or(wrapper -> {
//                                情况一：-> os_____s_____oe
                                wrapper
                                        .le(Pxpaiketable::getStartLessonDateTime, startTime)
                                        .ge(Pxpaiketable::getEndLessonDateTime, startTime);
                            })
                            .or(wrapper -> {
//                                情况二：-> os_____e___oe
                                wrapper
                                        .le(Pxpaiketable::getStartLessonDateTime, endTime)
                                        .ge(Pxpaiketable::getEndLessonDateTime, endTime);
                            })
                            .or(wrapper -> {
//                                情况三：-> s_____os_____oe___e
                                wrapper
                                        .ge(Pxpaiketable::getStartLessonDateTime, startTime)
                                        .le(Pxpaiketable::getEndLessonDateTime, endTime);
                            });
                });
        return queryWrapper;
    }

    /**
     * 保存试听
     */
    @PostMapping("/saveChabanShiting")
    @ResponseBody
    @Transactional
    @ApiOperation("保存插班试听")
    public AjaxJson saveChabanShiting(HttpServletRequest request, @RequestBody ShitingForm shitingForm) {

        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        try {
            //添加还是修改
            String logtext = "";
            Pxstutable pxstutable = iPxstutableService.getById(shitingForm.getStuID());
            Pxpaiketable paike = null;
            Pxkechengtable kecheng;
            if (!ObjectUtils.isEmpty(shitingForm.getPaikeID())) {
                paike = iPxpaiketableService.getById(shitingForm.getPaikeID());
                kecheng = iPxkechengtableService.getById(paike.getKechengID());
            } else {
                kecheng = iPxkechengtableService.getById(shitingForm.getKechengID());
            }
            if (ObjectUtils.isEmpty(shitingForm.getId())) {
                //添加
                logtext += "添加意向学员：" + pxstutable.getStuName() + "的插班试听。";
                QueryWrapper<Pxbuxikechengtable> wrapperBuxikecheng = new QueryWrapper<>();
                wrapperBuxikecheng.lambda()
                        .eq(Pxbuxikechengtable::getStuID, shitingForm.getStuID())
                        .eq(Pxbuxikechengtable::getKechengID, kecheng.getId())
                        .eq(Pxbuxikechengtable::getQiyeID, loginUser.getQiyeID());
                List<Pxbuxikechengtable> pxbuxikechengtableList = iPxbuxikechengtableService.list(wrapperBuxikecheng);
                Long buxiID = 0L;
                //没有补习课程，录入！
                if (pxbuxikechengtableList.isEmpty()) {
                    Pxbuxikechengtable pxbuxikechengtable = new Pxbuxikechengtable();
                    pxbuxikechengtable
                            .setStuID(shitingForm.getStuID())
                            .setKechengID(kecheng.getId())
                            .setKechengprice(BigDecimal.valueOf(0))
                            .setOriginalprice(BigDecimal.valueOf(0))
                            .setRemainkeshi(BigDecimal.valueOf(0))
                            .setKeshiNum(BigDecimal.valueOf(0))
                            .setZongjia(BigDecimal.valueOf(0))
                            .setBuykeshiDateTime(new Date())
                            .setJifeiStyleID(kecheng.getJifeiStyleID())
                            .setIsShow(1)
                            .setType(1)
                            .setQianDanInfoID(0L)
                            .setQianDanSubjectID(0L)
                            .setQiyeID(loginUser.getQiyeID());
                    iPxbuxikechengtableService.save(pxbuxikechengtable);
                    buxiID = pxbuxikechengtable.getId();

                    logtext += "录入补习课程信息，ID：" + buxiID;
                } else {
                    buxiID = pxbuxikechengtableList.get(0).getId();
                    logtext += "补习课程ID：" + buxiID;
                }

                //有排课ID说明是插班试听
                if (!ObjectUtils.isEmpty(shitingForm.getPaikeID())) {

                    //判断是否有相同排课的试听
                    QueryWrapper<Pxshitingrecordtable> pxshitingrecordtableWrapper = new QueryWrapper<>();
                    pxshitingrecordtableWrapper.lambda()
                            .eq(Pxshitingrecordtable::getYxStuID, shitingForm.getStuID())
                            .eq(Pxshitingrecordtable::getPaikeID, shitingForm.getPaikeID())
                            .eq(Pxshitingrecordtable::getQiyeID, loginUser.getQiyeID());
                    int count = iPxshitingrecordtableService.count(pxshitingrecordtableWrapper);
                    if (count > 0) {
                        //该排课已经有试听记录了
                        ajaxJson.setMsg("该排课已经有试听记录了");
                        ajaxJson.setCode("N");
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        return ajaxJson;
                    }

                    //插入选课表
                    Pxxuanketable pxxuanketable = new Pxxuanketable();
                    pxxuanketable
                            .setBuxiID(buxiID)
                            .setPaikeID(shitingForm.getPaikeID())
                            .setRecordDate(new Date())
                            .setType(0) //说明是插进去的学员，不是原班学员
                            .setStuID(shitingForm.getStuID())
                            .setQiyeID(loginUser.getQiyeID())
                            .insert();

                    // 判断是否试听收费
                    logtext += ",插班试听,插入排课的ID：" + shitingForm.getPaikeID();
                    Long liushuiID = null;
                    if (shitingForm.getShitingPrice().compareTo(BigDecimal.valueOf(0)) == 1) {
//                    if (shitingForm.getShitingPrice().compareTo(BigDecimal.valueOf(0)) == 1) {

                        //收费则录流水
                        Pxpaiketable paikeById = iPxpaiketableService.getById(shitingForm.getPaikeID());
                        Pxclassroomtable roomById = iPxclassroomtableService.getById(paikeById.getClassRoomID());
                        Long campusID = pxstutable.getCampusID();
                        if (ObjectUtils.isEmpty(campusID)) {
                            campusID = roomById.getCampusID();
                        }
                        Pxliushuizhangtable pxliushuizhangtable = new Pxliushuizhangtable();
                        pxliushuizhangtable
                                .setLiushuiDateTime(new Date())
                                .setCampusID(campusID)
                                .setLiushuiZaiyao("试听学费")
                                .setPayMoneyStyle(shitingForm.getPayMoneyStyle())
                                .setShouruMoney(shitingForm.getShitingPrice())
                                .setZhichuMoney(BigDecimal.valueOf(0))
                                .setShouzhiStyleID(9L)
                                .setJinbanRen(loginUser.getStaffID())
                                .setStuID(shitingForm.getStuID())
                                .setAddStaffID(loginUser.getStaffID())
                                .setLuruTime(new Date())
                                .setQiyeID(loginUser.getQiyeID())
                                .insert();
                        liushuiID = pxliushuizhangtable.getId();
                        logtext += "。试听收费：" + shitingForm.getShitingPrice() + "元,添加财务流水记录。流水ID为：" + liushuiID;
                    }
                    //插入试听记录
                    Pxshitingrecordtable pxshitingrecordtable = new Pxshitingrecordtable();
                    pxshitingrecordtable
                            .setYxStuID(shitingForm.getStuID())
                            .setChabanOr1v1(1)     //插班试听：1    ，一对一：2
                            .setKechengID(paike.getKechengID())
                            .setClassID(shitingForm.getClassID())
                            .setHaveClassDate(paike.getHaveClassDate())
                            .setStartLessonDateTime(paike.getStartLessonDateTime())
                            .setEndLessonDateTime(paike.getEndLessonDateTime())
                            .setWeekN(paike.getWeekN())
                            .setPaikeID(paike.getId())
                            .setStTeacher(paike.getTeacherIDs())
                            .setSubjectID(kecheng.getSubjectID())
                            .setClassRoomID(paike.getClassRoomID().toString())
                            .setIsAddStuNumToTeacher(shitingForm.getIsKechengStuNum() ? 1 : 0)
                            .setShitingPrice(shitingForm.getShitingPrice())
                            .setAddStaffID(loginUser.getStaffID())
                            .setAddTime(new Date())
                            .setQiyeID(loginUser.getQiyeID())
                            .setLiushuiID(liushuiID)
                            .insert();
                    logtext += "添加插班试听记录完毕。插班试听ID为：" + pxshitingrecordtable.getId() + "。";

                } else {
                    //没有排课ID说明是一对一试听
                    String[] split = shitingForm.getHaveClassTime().split("-");
                    LocalTime st = LocalTime.parse(split[0]);
                    LocalTime et = LocalTime.parse(split[1]);

                    Time startTime = Time.valueOf(st);
                    Time endTime = Time.valueOf(et);

                    Pxclassroomtable classRoom = iPxclassroomtableService.getById(shitingForm.getClassRoomID());
                    Pxstafftable pxstafftableById = iPxstafftableService.getById(shitingForm.getStaffID());
                    // 判断是否检测冲突
                    if (shitingForm.getCtjcSwitch()) {
                        //排课冲突检测

                        //检测教室是否冲突

                        QueryWrapper<Pxpaiketable> wrapperPaikeRoom = paikeTimeJC(startTime, endTime);
                        wrapperPaikeRoom.lambda()
                                .eq(Pxpaiketable::getClassRoomID, shitingForm.getClassRoomID())
                                .eq(Pxpaiketable::getHaveClassDate, shitingForm.getHaveClassDate())
                                .eq(Pxpaiketable::getQiyeID, loginUser.getQiyeID());
                        List<Pxpaiketable> pxpaiketableList = iPxpaiketableService.list(wrapperPaikeRoom);
                        if (!pxpaiketableList.isEmpty()) {
                            //说明教室冲突了

                            //开始查找是否有脏数据
                            for (Pxpaiketable paikeItem : pxpaiketableList) {
                                Pxclasstable pxclasstableByID = iPxclasstableService.getById(paikeItem.getClassID());
                                //班级不存在说明是脏数据
                                if (ObjectUtils.isEmpty(pxclasstableByID)) {
                                    //班级不存在所有学员退班级
                                    iPxstuclasstableService.remove(
                                            new QueryWrapper<Pxstuclasstable>().lambda()
                                                    .eq(Pxstuclasstable::getClassID, paikeItem.getClassID())
                                                    .eq(Pxstuclasstable::getQiyeID, loginUser.getQiyeID())
                                    );
                                    //有排课删除排课
                                    List<Pxpaiketable> delPxpaiketableList = iPxpaiketableService.list(
                                            new LambdaQueryWrapper<Pxpaiketable>()
                                                    .eq(Pxpaiketable::getClassID, paikeItem.getClassID())
                                                    .eq(Pxpaiketable::getQiyeID, loginUser.getQiyeID()));
                                    if (!delPxpaiketableList.isEmpty()) {
                                        List<Long> delPaikeIds = delPxpaiketableList.stream().map(Pxpaiketable::getId).collect(Collectors.toList());
                                        //删除对应排课教师
                                        iPxpaiketeachertableService.remove(
                                                new LambdaQueryWrapper<Pxpaiketeachertable>()
                                                        .in(Pxpaiketeachertable::getPaikeID, delPaikeIds)
                                                        .eq(Pxpaiketeachertable::getQiyeID, loginUser.getQiyeID()));
                                        //删除排课脏数据
                                        iPxpaiketableService.removeByIds(delPaikeIds);
                                    }
                                } else {
                                    String haveClassDate = DateUtil.formatDate(paikeItem.getHaveClassDate(), "yyyy-MM-dd");
                                    String haveClassTimeStart = DateUtil.formatDate(paikeItem.getStartLessonDateTime(), "HH:mm");
                                    String haveClassTimeEnd = DateUtil.formatDate(paikeItem.getEndLessonDateTime(), "HH:mm");
                                    ajaxJson.setMsg("教室冲突：在" + haveClassDate + " " + haveClassTimeStart + "-" + haveClassTimeEnd + "。【" + classRoom.getClassroomname() + "】教室被班级【" + pxclasstableByID.getClassName() + "】占用");
                                    ajaxJson.setSuccess(false);
//                                    回滚
                                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                                    return ajaxJson;
                                }
                            }
                        }
//                        检测和老师的时间是否冲突
                        QueryWrapper<Pxpaiketable> wrapperPaikeTeacher = paikeTimeJC(startTime, endTime);
                        wrapperPaikeTeacher
                                .eq("b.teacherID", shitingForm.getStaffID())
                                .eq("a.qiyeID", loginUser.getQiyeID())
                                .lambda()
                                .eq(Pxpaiketable::getHaveClassDate, shitingForm.getHaveClassDate());
                        List<Pxpaiketable> teacherTimeCT = iPxpaiketableService.getTeacherTimeCT(wrapperPaikeTeacher);
                        if (!teacherTimeCT.isEmpty()) {
//                            说明老师的时间冲突了

//                            开始查找是否有脏数据
                            for (Pxpaiketable paikeItem : teacherTimeCT) {
                                Pxclasstable pxclasstableByID = iPxclasstableService.getById(paikeItem.getClassID());
//                                班级不存在说明是脏数据
                                if (ObjectUtils.isEmpty(pxclasstableByID)) {
//                                    班级不存在所有学员退班级
                                    iPxstuclasstableService.remove(
                                            new QueryWrapper<Pxstuclasstable>().lambda()
                                                    .eq(Pxstuclasstable::getClassID, paikeItem.getClassID())
                                                    .eq(Pxstuclasstable::getQiyeID, loginUser.getQiyeID())
                                    );
//                                    有排课删除排课
                                    List<Pxpaiketable> delPxpaiketableList = iPxpaiketableService.list(
                                            new LambdaQueryWrapper<Pxpaiketable>()
                                                    .eq(Pxpaiketable::getClassID, paikeItem.getClassID())
                                                    .eq(Pxpaiketable::getQiyeID, loginUser.getQiyeID()));
                                    if (!delPxpaiketableList.isEmpty()) {
                                        List<Long> delPaikeIds = delPxpaiketableList.stream().map(Pxpaiketable::getId).collect(Collectors.toList());
//                                        删除对应排课教师
                                        iPxpaiketeachertableService.remove(
                                                new LambdaQueryWrapper<Pxpaiketeachertable>()
                                                        .in(Pxpaiketeachertable::getPaikeID, delPaikeIds)
                                                        .eq(Pxpaiketeachertable::getQiyeID, loginUser.getQiyeID()));
//                                    删除排课脏数据
                                        iPxpaiketableService.removeByIds(delPaikeIds);
                                    }
                                } else {
                                    String haveClassDate = DateUtil.formatDate(paikeItem.getHaveClassDate(), "yyyy-MM-dd");
                                    String haveClassTimeStart = DateUtil.formatDate(paikeItem.getStartLessonDateTime(), "HH:mm");
                                    String haveClassTimeEnd = DateUtil.formatDate(paikeItem.getEndLessonDateTime(), "HH:mm");
                                    ajaxJson.setMsg("教师冲突：在" + haveClassDate + " " + haveClassTimeStart + "-" + haveClassTimeEnd + "。【" + pxstafftableById.getStaffName() + "】老师在班级【" + pxclasstableByID.getClassName() + "】排课");
                                    ajaxJson.setSuccess(false);
//                                    回滚
                                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                                    return ajaxJson;
                                }
                            }
                        }

                    }
                    //程序执行到这里说明:不检测冲突，或者没有冲突，开始排课
                    //建立一对一班级
                    logtext += ",一对一试听,";
                    Long classID;
                    String className = pxstutable.getStuName() + "_" + kecheng.getKechengName() + "_试听一对一";
                    QueryWrapper<Pxclasstable> wrapperClassTable = new QueryWrapper<>();
                    wrapperClassTable.lambda()
                            .eq(Pxclasstable::getClassName, className)
                            .eq(Pxclasstable::getQiyeID, loginUser.getQiyeID());
                    //先查询是否存在
                    List<Pxclasstable> pxclasstableList = iPxclasstableService.list(wrapperClassTable);
                    if (pxclasstableList.isEmpty()) {
                        //不存在则新建班级
                        Pxclasstable pxclasstable = new Pxclasstable();
                        pxclasstable
                                .setAddTime(new Date())
                                .setAddStaffID(loginUser.getStaffID())
                                .setCampusID(kecheng.getCampusID())
                                .setClassName(className)
                                .setIsdelete(false)
                                .setIsShow(1)
                                .setIs1v1Class(1)
                                .setClassState(0)
                                .setQiyeID(loginUser.getQiyeID())
                                .insert();
                        classID = pxclasstable.getId();
                        logtext += "不存在对应一对一班级，创建一对一班级,班级ID为：" + classID + ",班级名称：" + className;
                    } else {
                        //存在则复用
                        classID = pxclasstableList.get(0).getId();
                        Pxclasstable oldclass = iPxclasstableService.getById(classID);
                        logtext += "存在对应一对一班级，班级ID：" + classID + ",班级名称：" + oldclass.getClassName();
                    }


                    //插班
                    QueryWrapper<Pxstuclasstable> wrapperStuClass = new QueryWrapper<>();
                    wrapperStuClass.lambda()
                            .eq(Pxstuclasstable::getClassID, classID)
                            .eq(Pxstuclasstable::getBuxiID, buxiID)
                            .eq(Pxstuclasstable::getQiyeID, loginUser.getQiyeID());
                    List<Pxstuclasstable> pxstuclasstableList = iPxstuclasstableService.list(wrapperStuClass);
                    if (pxstuclasstableList.isEmpty()) {
                        Pxstuclasstable addstuclass = new Pxstuclasstable();
                        addstuclass
                                .setBuxiID(buxiID)
                                .setClassID(classID)
                                .setQiyeID(loginUser.getQiyeID())
                                .insert();
                    }


                    ZoneId zoneId = ZoneId.systemDefault();


                    String[] weekDay = new String[]{"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
                    LocalDate localDate = LocalDate.parse(shitingForm.getHaveClassDate());
                    String weekN = weekDay[localDate.getDayOfWeek().getValue() - 1];

                    //插入排课Date.from(instantStart)
                    paike = new Pxpaiketable();
                    paike
                            .setStartLessonDateTime(startTime)
                            .setEndLessonDateTime(endTime)
                            .setHaveClassDate(Date.from(localDate.atStartOfDay(zoneId).toInstant()))
                            .setClassID(classID)
                            .setClassRoomID(shitingForm.getClassRoomID())
                            .setWeekN(weekN)
                            .setKechengID(shitingForm.getKechengID())
                            .setDakaoqin(false)
                            .setTeacherIDs(shitingForm.getStaffID().toString())
                            .setTeacherNames(pxstafftableById.getStaffName())
                            .setTags(YxStuUtils.getTagByUUId())
                            .setQiyeID(loginUser.getQiyeID())
                            .insert();

                    //插入排课老师
                    Pxpaiketeachertable paikeTeacher = new Pxpaiketeachertable();
                    paikeTeacher
                            .setPaikeID(paike.getId())
                            .setTeacherID(shitingForm.getStaffID())
                            .setQiyeID(loginUser.getQiyeID())
                            .insert();

                    //往选课表里插入一条记录
                    Pxxuanketable pxxuanketable = new Pxxuanketable();
                    pxxuanketable
                            .setBuxiID(buxiID)
                            .setPaikeID(paike.getId())
                            .setRecordDate(new Date())
                            .setType(0)  //说明是插进去的学员，不是原班学员
                            .setStuID(shitingForm.getStuID())
                            .setQiyeID(loginUser.getQiyeID())
                            .insert();

                    logtext += "。非原班级学员。插入" + paike.getHaveClassDate() + "~" + paike.getStartLessonDateTime() + "至" + paike.getEndLessonDateTime() + "的排课，排课ID：" + paike.getId();
                    //判断是否试听收费
                    Long liushuiID = null;
                    if (shitingForm.getShitingPrice().compareTo(BigDecimal.valueOf(0)) == 1) {
                        //收费则录流水
                        Long campusID = pxstutable.getCampusID();
                        if (ObjectUtils.isEmpty(campusID)) {
                            campusID = classRoom.getCampusID();
                        }
                        Pxliushuizhangtable pxliushuizhangtable = new Pxliushuizhangtable();
                        pxliushuizhangtable
                                .setLiushuiDateTime(new Date())
                                .setCampusID(campusID)
                                .setLiushuiZaiyao("试听学费")
                                .setPayMoneyStyle(shitingForm.getPayMoneyStyle())
                                .setShouruMoney(shitingForm.getShitingPrice())
                                .setZhichuMoney(BigDecimal.valueOf(0))
                                .setShouzhiStyleID(9L)
                                .setJinbanRen(loginUser.getStaffID())
                                .setStuID(shitingForm.getStuID())
                                .setAddStaffID(loginUser.getStaffID())
                                .setLuruTime(new Date())
                                .setQiyeID(loginUser.getQiyeID())
                                .insert();
                        liushuiID = pxliushuizhangtable.getId();
                    }
                    logtext += "。试听收费：" + shitingForm.getShitingPrice() + "元,添加财务流水记录。流水ID为：" + liushuiID;
                    //插入试听记录表
                    Pxshitingrecordtable pxshitingrecordtable = new Pxshitingrecordtable();
                    pxshitingrecordtable
                            .setYxStuID(shitingForm.getStuID())
                            .setChabanOr1v1(2)  //插班试听：1    ，一对一：2
                            .setKechengID(shitingForm.getKechengID())
                            .setClassID(classID)
                            .setHaveClassDate(paike.getHaveClassDate())
                            .setStartLessonDateTime(paike.getStartLessonDateTime())
                            .setEndLessonDateTime(paike.getEndLessonDateTime())
                            .setWeekN(weekN)
                            .setPaikeID(paike.getId())
                            .setStTeacher(paike.getTeacherIDs())
                            .setSubjectID(kecheng.getSubjectID())
                            .setClassRoomID(paike.getClassRoomID().toString())
                            .setIsAddStuNumToTeacher(shitingForm.getIsKechengStuNum() ? 1 : 0)
                            .setShitingPrice(shitingForm.getShitingPrice())
                            .setAddTime(new Date())
                            .setAddStaffID(loginUser.getStaffID())
                            .setQiyeID(loginUser.getQiyeID())
                            .setLiushuiID(liushuiID)
                            .insert();
                    logtext += "。添加一对一插班试听完毕，插入试听记录ID：" + pxshitingrecordtable.getId() + "。";
                }


            } else {
                //修改

                Pxshitingrecordtable pxshitingrecordtable = iPxshitingrecordtableService.getById(shitingForm.getId());
                //有排课ID说明是插班试听
                if (!ObjectUtils.isEmpty(shitingForm.getPaikeID())) {

                    logtext += "修改意向学员：" + pxstutable.getStuName() + "的插班试听。";
                    //班课，插班试听
                    QueryWrapper<Pxbuxikechengtable> wrapperBuxiOld = new QueryWrapper<>();
                    wrapperBuxiOld.lambda()
                            .eq(Pxbuxikechengtable::getStuID, pxshitingrecordtable.getYxStuID())
                            .eq(Pxbuxikechengtable::getKechengID, pxshitingrecordtable.getKechengID())
                            .eq(Pxbuxikechengtable::getQiyeID, loginUser.getQiyeID());
                    List<Pxbuxikechengtable> pxbuxikechengtables = iPxbuxikechengtableService.list(wrapperBuxiOld);
                    if (!pxbuxikechengtables.isEmpty()) {
                        pxbuxikechengtables.get(0)
                                .setKechengID(shitingForm.getKechengID())
                                .updateById();
                    }

                    if (!pxshitingrecordtable.getPaikeID().equals(shitingForm.getPaikeID())) {
                        //两次排课不一样，修改选课表
                        QueryWrapper<Pxxuanketable> pxxuanketablewrapper = new QueryWrapper<>();
                        pxxuanketablewrapper.lambda()
                                .eq(Pxxuanketable::getPaikeID, pxshitingrecordtable.getPaikeID())
                                .eq(Pxxuanketable::getStuID, shitingForm.getStuID())
                                .eq(Pxxuanketable::getQiyeID, loginUser.getQiyeID())
                                .eq(Pxxuanketable::getType, 0);
                        List<Pxxuanketable> pxxuanketableList = iPxxuanketableService.list(pxxuanketablewrapper);
                        if (!pxxuanketableList.isEmpty()) {
                            pxxuanketableList.get(0)
                                    .setPaikeID(shitingForm.getPaikeID())
                                    .updateById();
                        }
                    }

                    Pxpaiketable pxpaiketableNew = iPxpaiketableService.getById(shitingForm.getPaikeID());

                    if (shitingForm.getShitingPrice().compareTo(BigDecimal.valueOf(0)) != 0) {
                        //说明修改的新数据有试听费用
                        if (pxshitingrecordtable.getShitingPrice().compareTo(BigDecimal.valueOf(0)) != 0) {
                            //说明老数据也有有试听费用
                            logtext += ",原试听数据也存在试听费用,修改试听费用，修改前：" + pxshitingrecordtable.getShitingPrice() + ",修改后:" + shitingForm.getShitingPrice();

                            pxshitingrecordtable.setShitingPrice(shitingForm.getShitingPrice());
                            iPxshitingrecordtableService.updateById(pxshitingrecordtable);

                            Pxliushuizhangtable ls = iPxliushuizhangtableService.getById(pxshitingrecordtable.getLiushuiID());
                            logtext += "。修改流水记录，流水ID:" + ls.getId() + "，修改前：" + ls.getShouruMoney() + "修改后：" + shitingForm.getShitingPrice();

                            ls.setShouruMoney(shitingForm.getShitingPrice());
                            iPxliushuizhangtableService.updateById(ls);

                        } else {
                            //老数据没有流水，加入
                            Pxclassroomtable classRoom = iPxclassroomtableService.getById(pxpaiketableNew.getClassRoomID());
                            Long campusID = pxstutable.getCampusID();
                            if (ObjectUtils.isEmpty(campusID)) {
                                campusID = classRoom.getCampusID();
                            }
                            Pxliushuizhangtable pxliushuizhangtable = new Pxliushuizhangtable();
                            pxliushuizhangtable
                                    .setLiushuiDateTime(new Date())
                                    .setCampusID(campusID)
                                    .setLiushuiZaiyao("试听学费")
                                    .setPayMoneyStyle(shitingForm.getPayMoneyStyle())
                                    .setShouruMoney(shitingForm.getShitingPrice())
                                    .setZhichuMoney(BigDecimal.valueOf(0))
                                    .setShouzhiStyleID(9L)
                                    .setJinbanRen(loginUser.getStaffID())
                                    .setStuID(shitingForm.getStuID())
                                    .setAddStaffID(loginUser.getStaffID())
                                    .setLuruTime(new Date())
                                    .setQiyeID(loginUser.getQiyeID())
                                    .insert();
                            //设置流水ID
                            pxshitingrecordtable.setLiushuiID(pxliushuizhangtable.getId());
                            logtext += "存在试听费用，添加流水记录，流水ID：" + pxliushuizhangtable.getId();

                        }

                    } else {
                        //说明修改的新数据没有试听费用
                        if (pxshitingrecordtable.getShitingPrice().compareTo(BigDecimal.valueOf(0)) != 0) {
                            //说明老数据有试听费用，删除
                            pxliushuizhangtableService.removeById(pxshitingrecordtable.getLiushuiID());
                            pxshitingrecordtable.setLiushuiID(null);
                            logtext += "，新数据不存在试听费用，删除原有试听费用。删除流水记录。";
                        }
                    }

                    //修改试听记录表
                    pxshitingrecordtable
                            .setKechengID(pxpaiketableNew.getKechengID())
                            .setClassID(pxpaiketableNew.getClassID())
                            .setHaveClassDate(pxpaiketableNew.getHaveClassDate())
                            .setStartLessonDateTime(pxpaiketableNew.getStartLessonDateTime())
                            .setEndLessonDateTime(pxpaiketableNew.getEndLessonDateTime())
                            .setWeekN(pxpaiketableNew.getWeekN())
                            .setPaikeID(pxpaiketableNew.getId())
                            .setStTeacher(pxpaiketableNew.getTeacherIDs())
                            .setIsAddStuNumToTeacher(shitingForm.getIsKechengStuNum() ? 1 : 0)
                            .setShitingPrice(shitingForm.getShitingPrice())
                            .updateById();

                    logtext += "，修改插班试听记录完毕。";

                } else {
                    logtext += "修改意向学员：" + pxstutable.getStuName() + "的一对一插班试听。";
                    //没有排课ID说明是一对一试听
                    String[] split = shitingForm.getHaveClassTime().split("-");
                    LocalTime st = LocalTime.parse(split[0]);
                    LocalTime et = LocalTime.parse(split[1]);
                    Time startTime = Time.valueOf(st);
                    Time endTime = Time.valueOf(et);

                    Pxclassroomtable classRoom = iPxclassroomtableService.getById(shitingForm.getClassRoomID());
                    Pxstafftable pxstafftableById = iPxstafftableService.getById(shitingForm.getStaffID());
                    //判断是否检测冲突
                    if (shitingForm.getCtjcSwitch()) {
                        //排课冲突检测

                        //检测教室是否冲突

                        QueryWrapper<Pxpaiketable> wrapperPaikeRoom = paikeTimeJC(startTime, endTime);
                        wrapperPaikeRoom.lambda()
                                .ne(Pxpaiketable::getId, pxshitingrecordtable.getPaikeID())
                                .eq(Pxpaiketable::getClassRoomID, shitingForm.getClassRoomID())
                                .eq(Pxpaiketable::getHaveClassDate, shitingForm.getHaveClassDate())
                                .eq(Pxpaiketable::getQiyeID, loginUser.getQiyeID());
                        List<Pxpaiketable> pxpaiketableList = iPxpaiketableService.list(wrapperPaikeRoom);
                        if (!pxpaiketableList.isEmpty()) {
//                            说明教室冲突了

//                            开始查找是否有脏数据
                            for (Pxpaiketable paikeItem : pxpaiketableList) {
                                Pxclasstable pxclasstableByID = iPxclasstableService.getById(paikeItem.getClassID());
//                                班级不存在说明是脏数据
                                if (ObjectUtils.isEmpty(pxclasstableByID)) {
//                                    班级不存在所有学员退班级
                                    iPxstuclasstableService.remove(
                                            new QueryWrapper<Pxstuclasstable>().lambda()
                                                    .eq(Pxstuclasstable::getClassID, paikeItem.getClassID())
                                                    .eq(Pxstuclasstable::getQiyeID, loginUser.getQiyeID())
                                    );
//                                    有排课删除排课
                                    List<Pxpaiketable> delPxpaiketableList = iPxpaiketableService.list(
                                            new LambdaQueryWrapper<Pxpaiketable>()
                                                    .eq(Pxpaiketable::getClassID, paikeItem.getClassID())
                                                    .eq(Pxpaiketable::getQiyeID, loginUser.getQiyeID()));
                                    if (!delPxpaiketableList.isEmpty()) {
                                        List<Long> delPaikeIds = delPxpaiketableList.stream().map(Pxpaiketable::getId).collect(Collectors.toList());
//                                        删除对应排课教师
                                        iPxpaiketeachertableService.remove(
                                                new LambdaQueryWrapper<Pxpaiketeachertable>()
                                                        .in(Pxpaiketeachertable::getPaikeID, delPaikeIds)
                                                        .eq(Pxpaiketeachertable::getQiyeID, loginUser.getQiyeID()));
//                                    删除排课脏数据
                                        iPxpaiketableService.removeByIds(delPaikeIds);
                                    }
                                } else {
                                    String haveClassDate = DateUtil.formatDate(paikeItem.getHaveClassDate(), "yyyy-MM-dd");
                                    String haveClassTimeStart = DateUtil.formatDate(paikeItem.getStartLessonDateTime(), "HH:mm");
                                    String haveClassTimeEnd = DateUtil.formatDate(paikeItem.getEndLessonDateTime(), "HH:mm");
                                    ajaxJson.setMsg("教室冲突：在" + haveClassDate + " " + haveClassTimeStart + "-" + haveClassTimeEnd + "。【" + classRoom.getClassroomname() + "】教室被班级【" + pxclasstableByID.getClassName() + "】占用");
                                    ajaxJson.setSuccess(false);
//                                    回滚
                                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                                    return ajaxJson;
                                }
                            }
                        }
//                        检测和老师的时间是否冲突
                        QueryWrapper<Pxpaiketable> wrapperPaikeTeacher = paikeTimeJC(startTime, endTime);
                        wrapperPaikeTeacher
                                .ne("a.id", pxshitingrecordtable.getPaikeID())
                                .eq("b.teacherID", shitingForm.getStaffID())
                                .eq("a.qiyeID", loginUser.getQiyeID())
                                .lambda()
                                .eq(Pxpaiketable::getHaveClassDate, shitingForm.getHaveClassDate());
                        List<Pxpaiketable> teacherTimeCT = iPxpaiketableService.getTeacherTimeCT(wrapperPaikeTeacher);
                        if (!teacherTimeCT.isEmpty()) {
//                            说明老师的时间冲突了

//                            开始查找是否有脏数据
                            for (Pxpaiketable paikeItem : teacherTimeCT) {
                                Pxclasstable pxclasstableByID = iPxclasstableService.getById(paikeItem.getClassID());
//                                班级不存在说明是脏数据
                                if (ObjectUtils.isEmpty(pxclasstableByID)) {
//                                    班级不存在所有学员退班级
                                    iPxstuclasstableService.remove(
                                            new QueryWrapper<Pxstuclasstable>().lambda()
                                                    .eq(Pxstuclasstable::getClassID, paikeItem.getClassID())
                                                    .eq(Pxstuclasstable::getQiyeID, loginUser.getQiyeID())
                                    );
//                                    有排课删除排课
                                    List<Pxpaiketable> delPxpaiketableList = iPxpaiketableService.list(
                                            new LambdaQueryWrapper<Pxpaiketable>()
                                                    .eq(Pxpaiketable::getClassID, paikeItem.getClassID())
                                                    .eq(Pxpaiketable::getQiyeID, loginUser.getQiyeID()));
                                    if (!delPxpaiketableList.isEmpty()) {
                                        List<Long> delPaikeIds = delPxpaiketableList.stream().map(Pxpaiketable::getId).collect(Collectors.toList());
//                                        删除对应排课教师
                                        iPxpaiketeachertableService.remove(
                                                new LambdaQueryWrapper<Pxpaiketeachertable>()
                                                        .in(Pxpaiketeachertable::getPaikeID, delPaikeIds)
                                                        .eq(Pxpaiketeachertable::getQiyeID, loginUser.getQiyeID()));
//                                    删除排课脏数据
                                        iPxpaiketableService.removeByIds(delPaikeIds);
                                    }
                                } else {
                                    String haveClassDate = DateUtil.formatDate(paikeItem.getHaveClassDate(), "yyyy-MM-dd");
                                    String haveClassTimeStart = DateUtil.formatDate(paikeItem.getStartLessonDateTime(), "HH:mm");
                                    String haveClassTimeEnd = DateUtil.formatDate(paikeItem.getEndLessonDateTime(), "HH:mm");
                                    ajaxJson.setMsg("教师冲突：在" + haveClassDate + " " + haveClassTimeStart + "-" + haveClassTimeEnd + "。【" + pxstafftableById.getStaffName() + "】老师在班级【" + pxclasstableByID.getClassName() + "】排课");
                                    ajaxJson.setSuccess(false);
//                                    回滚
                                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                                    return ajaxJson;
                                }
                            }
                        }

                    }

                    QueryWrapper<Pxbuxikechengtable> wrapperBuxiOld = new QueryWrapper<>();
                    wrapperBuxiOld.lambda()
                            .eq(Pxbuxikechengtable::getStuID, pxshitingrecordtable.getYxStuID())
                            .eq(Pxbuxikechengtable::getKechengID, pxshitingrecordtable.getKechengID())
                            .eq(Pxbuxikechengtable::getQiyeID, loginUser.getQiyeID());
                    List<Pxbuxikechengtable> pxbuxikechengtables = iPxbuxikechengtableService.list(wrapperBuxiOld);
                    if (!pxbuxikechengtables.isEmpty()) {
                        pxbuxikechengtables.get(0)
                                .setKechengID(shitingForm.getKechengID())
                                .updateById();
                    }

                    //修改排课
                    ZoneId zoneId = ZoneId.systemDefault();

                    String[] weekDay = new String[]{"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
                    LocalDate localDate = LocalDate.parse(shitingForm.getHaveClassDate());
                    String weekN = weekDay[localDate.getDayOfWeek().getValue() - 1];
                    Pxpaiketable pxpaiketable = iPxpaiketableService.getById(pxshitingrecordtable.getPaikeID());
                    if (!ObjectUtils.isEmpty(pxpaiketable)) {
                        pxpaiketable
                                .setHaveClassDate(Date.from(localDate.atStartOfDay(zoneId).toInstant()))
                                .setStartLessonDateTime(startTime)
                                .setEndLessonDateTime(endTime)
                                .setClassRoomID(shitingForm.getClassRoomID())
                                .setKechengID(shitingForm.getKechengID())
                                .setWeekN(weekN)
                                .setTeacherIDs(shitingForm.getStaffID().toString())
                                .setTeacherNames(pxstafftableById.getStaffName())
//                                .setDakaoqin(false)
                                .updateById();
                    }
                    logtext += ",修改排课，排课ID：" + pxpaiketable.getId();
                    //更新班级名称
                    if (!pxshitingrecordtable.getKechengID().equals(shitingForm.getKechengID())) {
                        String className = pxstutable.getStuName() + "_" + kecheng.getKechengName() + "_试听一对一";
                        Pxclasstable pxclasstable = new Pxclasstable();
                        pxclasstable
                                .setId(pxpaiketable.getClassID())
                                .setClassName(className)
                                .updateById();
                        logtext += ",修改班级名称，新班级名称：" + className;

                    }
                    //修改试听老师
                    QueryWrapper<Pxpaiketeachertable> wrapperPaikeTeacher = new QueryWrapper<>();
                    wrapperPaikeTeacher.lambda()
                            .eq(Pxpaiketeachertable::getPaikeID, pxshitingrecordtable.getPaikeID())
                            .eq(Pxpaiketeachertable::getQiyeID, loginUser.getQiyeID());
                    List<Pxpaiketeachertable> pxpaiketeachertableList = iPxpaiketeachertableService.list(wrapperPaikeTeacher);
                    if (!pxpaiketeachertableList.isEmpty()) {
                        pxpaiketeachertableList.get(0)
                                .setTeacherID(shitingForm.getStaffID())
                                .updateById();
                    }

                    if (shitingForm.getShitingPrice().compareTo(BigDecimal.valueOf(0)) != 0) {
                        //说明修改的新数据有试听费用
                        if (pxshitingrecordtable.getShitingPrice().compareTo(BigDecimal.valueOf(0)) != 0) {
                            //说明老数据也有有试听费用
                            logtext += ",存在试听费用，试听记录费用，修改前：" + pxshitingrecordtable.getShitingPrice() + "，修改后：" + shitingForm.getShitingPrice();
                            pxshitingrecordtable.setShitingPrice(shitingForm.getShitingPrice());
                            iPxshitingrecordtableService.updateById(pxshitingrecordtable);


                            Pxliushuizhangtable ls = iPxliushuizhangtableService.getById(pxshitingrecordtable.getLiushuiID());
                            logtext += ",修改流水记录ID为：" + ls.getId() + "，修改前：" + ls.getShouruMoney() + ",修改后：" + shitingForm.getShitingPrice();
                            ls.setShouruMoney(shitingForm.getShitingPrice());
                            iPxliushuizhangtableService.updateById(ls);

                        } else {
                            //老数据没有流水，加入
                            Long campusID = pxstutable.getCampusID();
                            if (ObjectUtils.isEmpty(campusID)) {
                                campusID = classRoom.getCampusID();
                            }
                            Pxliushuizhangtable pxliushuizhangtable = new Pxliushuizhangtable();
                            pxliushuizhangtable
                                    .setLiushuiDateTime(new Date())
                                    .setCampusID(campusID)
                                    .setLiushuiZaiyao("试听学费")
                                    .setPayMoneyStyle(shitingForm.getPayMoneyStyle())
                                    .setShouruMoney(shitingForm.getShitingPrice())
                                    .setZhichuMoney(BigDecimal.valueOf(0))
                                    .setShouzhiStyleID(9L)
                                    .setJinbanRen(loginUser.getStaffID())
                                    .setStuID(shitingForm.getStuID())
                                    .setAddStaffID(loginUser.getStaffID())
                                    .setLuruTime(new Date())
                                    .setQiyeID(loginUser.getQiyeID())
                                    .insert();
                            //设置流水ID
                            pxshitingrecordtable.setLiushuiID(pxliushuizhangtable.getId());
                            logtext += ",原数据不存在试听费用，添加流水费用：" + shitingForm.getShitingPrice() + "，产生流水ID：" + pxliushuizhangtable.getId();
                        }

                    } else {
                        //说明修改的新数据没有试听费用
                        if (pxshitingrecordtable.getShitingPrice().compareTo(BigDecimal.valueOf(0)) != 0) {
                            //说明老数据有试听费用，删除
                            pxliushuizhangtableService.removeById(pxshitingrecordtable.getLiushuiID());
                            pxshitingrecordtable.setLiushuiID(null);

                            logtext += "，修改后不存在试听费用，删除多余流水数据、清空试听费用。";
                        }
                    }


                    //修改试听记录表
                    pxshitingrecordtable
                            .setKechengID(pxpaiketable.getKechengID())
                            .setHaveClassDate(Date.from(localDate.atStartOfDay(zoneId).toInstant()))
                            .setStartLessonDateTime(startTime)
                            .setEndLessonDateTime(endTime)
                            .setWeekN(weekN)
                            .setStTeacher(shitingForm.getStaffID().toString())
                            .setIsAddStuNumToTeacher(shitingForm.getIsKechengStuNum() ? 1 : 0)
                            .setShitingPrice(shitingForm.getShitingPrice())
                            .updateById();
                    logtext += ",修改试听记录完毕。";
                }
            }


            savePxLog.savepxlog(logtext, "xwcloud-zsbm/yxstu/YixiangStu/saveChabanShiting", loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());


            ajaxJson.setSuccess(true);
            return ajaxJson;
        } catch (Exception e) {
            ajaxJson.setMsg("出错了，请刷新重试！");
            ajaxJson.setSuccess(false);
            // 回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ajaxJson;
        }
    }

    /**
     * 保存试听满意度
     */
    @PostMapping("/saveShitingManyidu")
    @ResponseBody
    @Transactional
    @ApiOperation("保存试听满意度")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "试听记录ID", required = true),
            @ApiImplicitParam(name = "shiTingManyiduID", value = "满意度ID", required = true),
            @ApiImplicitParam(name = "shiTingShuoming", value = "满意度说明", required = true),
    })
    public AjaxJson saveShitingManyidu(String id, String shiTingManyiduID, String shiTingShuoming, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Pxshitingrecordtable st = iPxshitingrecordtableService.getById(id);
        st.setShiTingManyiduID(Long.valueOf(shiTingManyiduID));
        st.setShiTingShuoming(shiTingShuoming);

        Pxstutable stu = iPxstutableService.getById(st.getYxStuID());

        ajaxJson.setSuccess(
                iPxshitingrecordtableService.updateById(st)
        );

        savePxLog.savepxlog("保存意向学员：" + stu.getStuName() + "的试听满意度", "xwcloud-zsbm/yxstu/YixiangStu/saveShitingManyidu", loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());

        return ajaxJson;
    }

    /**
     * 导出意向学员
     *
     * @param response response对象
     */
    @GetMapping("/exportYxStu")
    @ResponseBody
    @ApiOperation("导出意向学员")
    public void exportYxStu(HttpServletResponse response, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<PxStuTableVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.qiyeID", qiyeID);
        List<PxStuTableVo> exportYxStuList = iPxstutableService.getExportYxStuList(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(
                new String[]{"姓名", "性别", "年级", "校区", "学员生日", "来源途径", "意向科目", "意向程度", "家长电话", "家长关系", "登记人", "市场人", "负责人", "登记时间", "最后一次跟进时间"},
                exportYxStuList,
                new String[]{"stuName", "stuSex", "stuGradeName", "campusName", "stubirth-D", "telFromName", "yxSubjects", "telLevelName", "parentTel", "parentTelRelationValue", "dengjiTeacherName", "yxShichangTeacherName", "yxFenpeiName", "dengjiTime-DT", "lastFollowDate-D"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "导出意向学员.xls", 15);

//            savePxLog.savepxlog("导出意向学员信息", "xwcloud-zsbm/yxstu/YixiangStu/exportYxStu", loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //endregion

    //region 跟进提醒

    /**
     * 分页查询意向学员跟进提醒
     */
    @GetMapping("/getYixiangStuGenjinTixing")
    @ResponseBody
    @ApiOperation("分页查询意向学员跟进提醒")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "id", value = "学生ID"),
            @ApiImplicitParam(name = "stuName", value = "学生姓名查询"),
            @ApiImplicitParam(name = "stuGradeID", value = "学生年级查询"),
            @ApiImplicitParam(name = "campusID", value = "校区名字查询"),
            @ApiImplicitParam(name = "staffpostID", value = "负责人岗位查询"),
            @ApiImplicitParam(name = "dengjiTime", value = "登记时间查询"),
            @ApiImplicitParam(name = "nextGenjinTime", value = "下次跟进时间查询"),
            @ApiImplicitParam(name = "type", value = "查询等级"),
    })
    public AjaxJson getYixiangStuGenjinTixing(HttpServletRequest request, GenjinTipSearchObj searchObj) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<genjinTixingVo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .orderByDesc("a.id")
                .eq("a.qiyeID", loginUser.getQiyeID());
        if (!ObjectUtils.isEmpty(searchObj.getId())) {
            queryWrapper.like("a.id", searchObj.getId());
        }
        if (!ObjectUtils.isEmpty(searchObj.getStuName())) {
            queryWrapper.like("a.stuName", searchObj.getStuName());
        }
        if (!ObjectUtils.isEmpty(searchObj.getStuGradeID())) {
            queryWrapper.eq("e.id", searchObj.getStuGradeID());
        }
        if (!ObjectUtils.isEmpty(searchObj.getCampusID())) {
            queryWrapper.eq("d.id", searchObj.getCampusID());
        }
        if (!ObjectUtils.isEmpty(searchObj.getStaffpostID())) {
            queryWrapper.eq("c.id", searchObj.getStaffpostID());
        }
        if (!ObjectUtils.isEmpty(searchObj.getStaffName())) {
            queryWrapper.like("b.staffName", searchObj.getStaffName());
        }
        if (!ObjectUtils.isEmpty(searchObj.getDengjiTime())) {
            String[] split = searchObj.getDengjiTime().split("_");
            queryWrapper.between("a.dengjiTime", split[0], split[1]);
        }
        if (!ObjectUtils.isEmpty(searchObj.getNextGenjinTime())) {
            String[] split = searchObj.getNextGenjinTime().split("_");
            queryWrapper.between("a.nextGenjinTime", split[0], split[1]);
        }
        if (!ObjectUtils.isEmpty(searchObj.getType()) && !searchObj.getType().equals(1)) {
            LocalDate now = LocalDate.now();
            LocalDateTime today59 = LocalDateTime.of(now, LocalTime.of(23, 59, 59));
            switch (searchObj.getType()) {
                case 2:
//                    今日提醒
                    queryWrapper.between("a.nextGenjinTime", now, today59);
                    break;
                case 3:
//                    明日提醒
                    queryWrapper.between("a.nextGenjinTime", now.plusDays(1), today59.plusDays(1));
                    break;
                case 4:
//                    本周提醒
                    int dayOfWeek = now.getDayOfWeek().getValue() - 1;
                    queryWrapper.between("a.nextGenjinTime", now.plusDays(-dayOfWeek), today59.plusDays(-dayOfWeek + 6));
                    break;
                case 5:
//                    本月提醒
                    int dayOfMonth = now.getDayOfMonth() - 1;
                    queryWrapper.between("a.nextGenjinTime", now.with(TemporalAdjusters.firstDayOfMonth()), today59.with(TemporalAdjusters.lastDayOfMonth()));
                    break;
            }
        }
        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", 132);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            QueryWrapper searchsysparam = new QueryWrapper<>().eq("qiyeID", loginUser.getQiyeID()).eq("stuParamTypeID", 85);
            Pxsysparamvaluetable pxsysparamvaluetable = iPxsysparamvaluetableService.getOne(searchsysparam);
            if (pxsysparamvaluetable != null) {
                if (pxsysparamvaluetable.getModifyValue().equals(1)) {//个人数据权限为登记人
                    queryWrapper.eq(" a.dengjiTeacherID", loginUser.getStaffID());
                } else if (pxsysparamvaluetable.getModifyValue().equals(2)) {//个人数据权限为市场人
                    queryWrapper.eq("a.yxshichangTeacherID", loginUser.getStaffID());
                } else if (pxsysparamvaluetable.getModifyValue().equals(3)) {//个人数据权限为负责人
                    queryWrapper.eq("a.yxfenpeistaffID", loginUser.getStaffID());
                } else {//未分配个人权限范围，返回没有权限数据
                    queryWrapper.eq("a.campusID", 0);
                }
            } else {//未分配个人权限范围，返回没有权限数据
                queryWrapper.eq("a.campusID", 0);
            }
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }
        ajaxJson.setObj(
                iPxstutableService.GetYixiangStuTixingPages(
                        new Page<genjinTixingVo>(searchObj.getCurrent(), searchObj.getSize()), queryWrapper)
        );
        ajaxJson.setCode("success");
        return ajaxJson;
    }

    /**
     * 删除跟进信息
     */
    @DeleteMapping(value = "/deleteGenjinTixing/{ids}")
    @ResponseBody
    @Transactional
    @ApiOperation("根据学生ID批量删除跟进信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "删除数据stuID集合", required = true)
    })
    public AjaxJson DeleteGenjinTixing(HttpServletRequest request, @PathVariable List<Long> ids) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<Pxyxgengjintable> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .in(Pxyxgengjintable::getStuID, ids)
                .eq(Pxyxgengjintable::getQiyeID, loginUser.getQiyeID());
        boolean remove = iPxyxgengjintableService.remove(wrapper);

        if (!remove) {
            ajaxJson.setMsg("勾选的学员没有跟进记录");
            ajaxJson.setSuccess(false);
        }


        savePxLog.savepxlog("删除了意向学员，意向学员ID：" + ids, "xwcloud-zsbm/yxstu/YixiangStu/deleteGenjinTixing", loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());

        return ajaxJson;
    }
    //endregion

    //region 微信留言

    /**
     * 分页查询留言信息
     */
    @GetMapping("/getAllLiuyanPages")
    @ResponseBody
    @ApiOperation("分页查询留言信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "userName", value = "用户名", required = false),
            @ApiImplicitParam(name = "tel", value = "联系电话", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区名称", required = false),
    })
    public AjaxJson getAllLiuyanPages(HttpServletRequest request, long size, long current, String userName, String tel, Long campusID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<Pxliuyantable> wrapper = new QueryWrapper<>();
        wrapper.eq("qiyeID", loginUser.getQiyeID());
        if (!ObjectUtils.isEmpty(userName)) {
            wrapper.like("userName", userName);
        }
        if (!ObjectUtils.isEmpty(tel)) {
            wrapper.like("tel", tel);
        }
        if (!ObjectUtils.isEmpty(campusID)) {
            wrapper.eq("campusID", campusID);
        }
        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", 133);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            wrapper.eq("campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            wrapper.eq("campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            wrapper.in("campusID", lookPower);
        }
        ajaxJson.setObj(iPxliuyantableService.page(new Page<>(current, size), wrapper));
        ajaxJson.setCode("success");
        return ajaxJson;
    }

    /**
     * 删除留言信息
     */
    @DeleteMapping("/deleteLiuyanList/{ids}")
    @ResponseBody
    @Transactional
    @ApiOperation("删除留言信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "删除数据ID", required = true)
    })
    public AjaxJson deleteLiuyanList(@PathVariable List<Long> ids) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(
                iPxliuyantableService.removeByIds(ids)
        );
        return ajaxJson;
    }
    //endregion

    //region 跟进流水

    /**
     * 分页查询跟进流水信息
     */
    @GetMapping("/getGenjinliushuiPages")
    @ResponseBody
    @ApiOperation("分页查询跟进流水信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true),
            @ApiImplicitParam(name = "gengjinTime", value = "跟进时间", required = true),
            @ApiImplicitParam(name = "staffName", value = "跟进人", required = true),
    })
    public AjaxJson getGenjinliushuiPages(HttpServletRequest request, GenjinLiushuiSearchObj searchObj) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<genjinliushuiVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (!ObjectUtils.isEmpty(searchObj.getCampusID())) {
            queryWrapper.eq("c.id", searchObj.getCampusID());
        }
        if (!ObjectUtils.isEmpty(searchObj.getStuName())) {
            queryWrapper.like("b.stuName", searchObj.getStuName());
        }
        if (!ObjectUtils.isEmpty(searchObj.getStaffName())) {
            queryWrapper.like("d.staffName", searchObj.getStaffName());
        }
        if (!ObjectUtils.isEmpty(searchObj.getGengjinTime())) {
            String[] s = searchObj.getGengjinTime().split("_");
            queryWrapper.between("a.gengjinTime", s[0], s[1]);
        }
        //根据权限范围加载数据
        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", 135);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("b.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("b.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("b.campusID", lookPower);
        }
        queryWrapper.orderByDesc("a.gengjinTime");
        ajaxJson.setObj(iPxyxgengjintableService.GegenjinLiushuiPages(new Page<>(searchObj.getCurrent(), searchObj.getSize()), queryWrapper));
        ajaxJson.setCode("success");
        return ajaxJson;
    }

    /**
     * 导出跟进流水
     */
    @GetMapping("/exportGenjinliushui")
    @ResponseBody
    @ApiOperation("导出跟进流水")
    public void exportGenjinliushui(HttpServletResponse response, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<genjinliushuiVo> queryWrapper = new QueryWrapper<genjinliushuiVo>();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        List<genjinliushuiVo> genjinliushuiList = iPxyxgengjintableService.GetExportGenjinliushui(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "学生姓名", "跟进内容", "跟进时间", "跟进人", "录入时间", "负责人"},
                genjinliushuiList,
                new String[]{"campusName", "stuName", "gengjinText", "gengjinTime-D", "staffName", "addTime-DT", "fuzeStaffName"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "跟进流水导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion

    //region 邀约到访流水

    /**
     * 分页查询邀约到访信息
     */
    @GetMapping("/getyaoyueDaofangPages")
    @ResponseBody
    @ApiOperation("分页查询邀约到访信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true),
            @ApiImplicitParam(name = "stuName", value = "学生姓名", required = true),
            @ApiImplicitParam(name = "invitationTime", value = "邀约时间", required = true),
            @ApiImplicitParam(name = "invitationZhuangtai", value = "邀约状态", required = true),
            @ApiImplicitParam(name = "staffName", value = "邀约人姓名", required = true),
    })
    public AjaxJson getyaoyueDaofangPages(HttpServletRequest request,
                                          long size, long current,
                                          String campusID, String stuName, String invitationTime, String invitationZhuangtai, String staffName) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<yaoyuedaofangVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (!ObjectUtils.isEmpty(campusID)) {
            queryWrapper.eq("c.id", campusID);
        }
        if (!ObjectUtils.isEmpty(stuName)) {
            queryWrapper.like("b.stuName", stuName);
        }
        if (!ObjectUtils.isEmpty(invitationTime)) {
            String[] s = (invitationTime + " 23:59:59").split("_");
            queryWrapper.between("a.invitationTime", s[0], s[1]);
        }
        if (!ObjectUtils.isEmpty(invitationZhuangtai)) {
            queryWrapper.eq("a.invitationZhuangtai", invitationZhuangtai);
        }
        if (!ObjectUtils.isEmpty(staffName)) {
            queryWrapper.like("d.staffName", staffName);
        }
        //根据权限范围加载数据
        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", 136);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("b.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("b.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("b.campusID", lookPower);
        }
        queryWrapper.orderByDesc("a.invitationTime");
        ajaxJson.setObj(iPxyxinvitationtableService.GetyaoyueDaofangPages(new Page<>(current, size), queryWrapper));
        ajaxJson.setCode("success");
        return ajaxJson;
    }

    /**
     * 导出邀约到访流水
     */
    @GetMapping("/exportYaoyuedaofangliushui")
    @ResponseBody
    @ApiOperation("导出邀约到访流水")
    public void exportYaoyuedaofangliushui(HttpServletResponse response, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<yaoyuedaofangVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        List<yaoyuedaofangVo> yaoyuedaofangVoList = iPxyxinvitationtableService.getyaoyuedaofangList(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "学生姓名", "邀约时间", "邀约状态", "邀约人", "到访时间", "到访人", "情况说明"},
                yaoyuedaofangVoList,
                new String[]{"campusName", "stuName", "invitationTime-DT", "invitationZhuangtai", "staffName", "daofangDate-DT", "daofangStaffName", "shuoMing"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "邀约到访信息导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //endregion

    //region 试听流水记录

    /**
     * 分页查询试听流水
     */
    @GetMapping("/getShitingRecordsPages")
    @ResponseBody
    @ApiOperation("分页查询试听流水")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区名称ID"),
            @ApiImplicitParam(name = "stuName", value = "学生姓名"),
            @ApiImplicitParam(name = "chabanOr1v1", value = "插班试听还是一对一试听"),
            @ApiImplicitParam(name = "className", value = "班级名称"),
            @ApiImplicitParam(name = "haveClassDate", value = "上课时间"),
            @ApiImplicitParam(name = "shiTingManyiduID", value = "试听满意度"),
            @ApiImplicitParam(name = "stTeachers", value = "试听老师"),
    })
    public AjaxJson getShitingRecordsPages(HttpServletRequest request,
                                           long size, long current,
                                           Long campusID, String stuName, Integer chabanOr1v1, String className,
                                           String haveClassDate, Integer shiTingManyiduID, String stTeachers) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<shitingLiushuiVo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("a.qiyeID", loginUser.getQiyeID());
        if (!ObjectUtils.isEmpty(campusID)) {
            queryWrapper.eq("c.id", campusID);
        }
        if (!ObjectUtils.isEmpty(stuName)) {
            queryWrapper.like("b.stuName", stuName);
        }
        if (!ObjectUtils.isEmpty(chabanOr1v1)) {
            queryWrapper.eq("a.chabanOr1v1", chabanOr1v1);
        }
        if (!ObjectUtils.isEmpty(className)) {
            queryWrapper.like("d.className", className);
        }
        if (!ObjectUtils.isEmpty(haveClassDate)) {
            String[] s = (haveClassDate + " 23:59:59").split("_");
            queryWrapper.between("a.haveClassDate", s[0], s[1]);
        }
        if (!ObjectUtils.isEmpty(shiTingManyiduID)) {
            queryWrapper.eq("a.shiTingManyiduID", shiTingManyiduID);
        }
        if (!ObjectUtils.isEmpty(stTeachers)) {
            queryWrapper.like("e.teacherNames", stTeachers);
        }

        //根据权限范围加载数据
        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", 137);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("b.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("b.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("b.campusID", lookPower);
        }
        queryWrapper.orderByDesc("e.haveClassDate");
        ajaxJson.setObj(iPxshitingrecordtableService.GetShitingLiushuiPages(new Page<>(current, size), queryWrapper));
        ajaxJson.setCode("success");
        return ajaxJson;
    }

    /**
     * 导出试听流水信息
     */
    @GetMapping("/exportshitingliushui")
    @ResponseBody
    @ApiOperation("导出试听流水信息")
    public void exportshitingliushui(HttpServletResponse response, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<shitingLiushuiVo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .orderByDesc("a.id")
                .eq("a.qiyeID", loginUser.getQiyeID());
        List<shitingLiushuiVo> shitingLiushuiVoList = iPxshitingrecordtableService.GetShitingLiushuiList(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(
                new String[]{"校区", "学生姓名", "试听方式", "班级名称", "试听日期", "上下课时间", "试听老师", "教室名称", "试听满意度", "满意度说明", "录入人"},
                shitingLiushuiVoList,
                new String[]{"campusName", "stuName", "chabanOr1v1Value", "className", "haveClassDate-D", "haveLessTime", "stTeachers", "classRoomName", "shiTingManyidu", "shiTingShuoming", "stTeachers"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "试听流水信息导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion

    //region 意向签单流水

    /**
     * 分页查询意向签单流水信息
     */
    @GetMapping("/getAllQiandanLiushuiPages")
    @ResponseBody
    @ApiOperation("分页查询意向签单流水信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID"),
            @ApiImplicitParam(name = "stuName", value = "学生姓名"),
            @ApiImplicitParam(name = "isBaomingOrChongzhi", value = "报名签单还是充值"),
            @ApiImplicitParam(name = "scStaffName", value = "市场人姓名"),
            @ApiImplicitParam(name = "fzStaffName", value = "负责人姓名"),
            @ApiImplicitParam(name = "qdStaffName", value = "签单人姓名"),
            @ApiImplicitParam(name = "yxDengjiDateTime", value = "登记时间"),
            @ApiImplicitParam(name = "yxQiandanDateTime", value = "签单时间"),
    })
    public AjaxJson getAllQiandanLiushuiPages(HttpServletRequest request,
                                              long size, long current,
                                              Long campusID, String stuName, String isBaomingOrChongzhi, String scStaffName,
                                              String fzStaffName, String qdStaffName, String yxDengjiDateTime, String yxQiandanDateTime) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<yxqiandanVo> page = new Page<>(current, size);
        QueryWrapper<yxqiandanVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (!ObjectUtils.isEmpty(campusID)) {
            queryWrapper.like("c.id", campusID);
        }
        if (!ObjectUtils.isEmpty(stuName)) {
            queryWrapper.like("b.stuName", stuName);
        }
        if (!ObjectUtils.isEmpty(isBaomingOrChongzhi)) {
            queryWrapper.eq("a.isBaomingOrChongzhi", isBaomingOrChongzhi);
        }
        if (!ObjectUtils.isEmpty(scStaffName)) {
            queryWrapper.like("e.staffName", scStaffName);
        }
        if (!ObjectUtils.isEmpty(fzStaffName)) {
            queryWrapper.like("d.staffName", fzStaffName);
        }
        if (!ObjectUtils.isEmpty(qdStaffName)) {
            queryWrapper.like("f.staffName", qdStaffName);
        }
        if (!ObjectUtils.isEmpty(yxDengjiDateTime)) {
            String[] s = (yxDengjiDateTime + " 23:59:59").split("_");
            queryWrapper.between("a.yxDengjiDateTime", s[0], s[1]);
        }
        if (!ObjectUtils.isEmpty(yxQiandanDateTime)) {
            String[] s = (yxQiandanDateTime + " 23:59:59").split("_");
            queryWrapper.between("a.yxQiandanDateTime", s[0], s[1]);
        }
        //根据权限范围加载数据
        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", 138);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            QueryWrapper searchsysparam = new QueryWrapper<>().eq("qiyeID", loginUser.getQiyeID()).eq("stuParamTypeID", 85);
            Pxsysparamvaluetable pxsysparamvaluetable = iPxsysparamvaluetableService.getOne(searchsysparam);
            if (pxsysparamvaluetable != null) {
                if (pxsysparamvaluetable.getModifyValue().equals(1)) {//个人数据权限为登记人
                    queryWrapper.eq(" b.dengjiTeacherID", loginUser.getStaffID());
                } else if (pxsysparamvaluetable.getModifyValue().equals(2)) {//个人数据权限为市场人
                    queryWrapper.eq("b.yxshichangTeacherID", loginUser.getStaffID());
                } else if (pxsysparamvaluetable.getModifyValue().equals(3)) {//个人数据权限为负责人
                    queryWrapper.eq("b.yxfenpeistaffID", loginUser.getStaffID());
                } else {//未分配个人权限范围，返回没有权限数据
                    queryWrapper.eq("b.campusID", 0);
                }
            } else {//未分配个人权限范围，返回没有权限数据
                queryWrapper.eq("b.campusID", 0);
            }
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("b.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("b.campusID", lookPower);
        }
        queryWrapper.orderByDesc("a.yxQiandanDateTime");
        ajaxJson.setObj(iPxyxqiandantableService.GetAllLiuyanyixiangqiandanPages(page, queryWrapper));
        ajaxJson.setCode("success");
        return ajaxJson;
    }

    /**
     * 导出意向签单流水信息
     */
    @GetMapping("/exportyxqiandanliushui")
    @ResponseBody
    @ApiOperation("导出意向签单流水信息")
    public void exportyxqiandanliushui(HttpServletResponse response, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<yxqiandanVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        List<yxqiandanVo> yxqiandanVoList = iPxyxqiandantableService.GetyixiangqiandanList(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(
                new String[]{"校区", "学员姓名", "签单实收", "签单方式", "市场人 ", "负责人", "签单人", "登记日期", "签单日期"},
                yxqiandanVoList,
                new String[]{"campusName", "stuName", "qianDanMoney", "isBaomingOrChongzhi", "scStaffName", "fzStaffName", "qdStaffName", "yxDengjiDateTime-DT", "yxQiandanDateTime-DT"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "意向签单流水导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion

    //region 招生目标

    /**
     * 分页获取校区招生目标
     */
    @GetMapping("/getcampusMubiaoPages")
    @ResponseBody
    @ApiOperation("分页获取校区招生目标")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区名称"),
            @ApiImplicitParam(name = "yearMonth", value = "目标年月"),
    })
    public AjaxJson getcampusMubiaoPages(HttpServletRequest request, long size, long current, Long campusID, String yearMonth) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<zhaoshengmubiaoVo> page = new Page<>(current, size);
        QueryWrapper<zhaoshengmubiaoVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("a.addTime").eq("a.qiyeID", loginUser.getQiyeID());
        if (!ObjectUtils.isEmpty(campusID)) {
            queryWrapper.eq("b.id", campusID);
        }
        if (!ObjectUtils.isEmpty(yearMonth)) {
            queryWrapper.eq("a.yearMonth", yearMonth);
        }
        //根据权限范围加载数据
        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", 138);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }
        queryWrapper.orderByDesc("a.yearMonth");
        ajaxJson.setObj(iPxzhaoshenmubiaocampustableService.GetZhaoshengmubiaoCampusPages(page, queryWrapper));
        ajaxJson.setCode("success");
        return ajaxJson;
    }

    /**
     * 新增/修改校区招生目标信息
     */
    @PostMapping("/saveCampusMubiao")
    @ResponseBody
    @Transactional
    @ApiOperation("新增/修改校区招生目标信息")
    public AjaxJson saveCampusMubiao(HttpServletRequest request, @RequestBody Pxzhaoshenmubiaocampustable zsmb) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        String logtext = "";
        Pxcampustable cam = iPxcampustableService.getById(zsmb.getCampusID());

        if (ObjectUtils.isEmpty(zsmb.getId())) {
            QueryWrapper<Pxzhaoshenmubiaocampustable> wrapper = new QueryWrapper<>();
            wrapper.lambda()
                    .eq(Pxzhaoshenmubiaocampustable::getQiyeID, loginUser.getQiyeID())
                    .eq(Pxzhaoshenmubiaocampustable::getCampusID, zsmb.getCampusID())
                    .eq(Pxzhaoshenmubiaocampustable::getYearMonth, zsmb.getYearMonth());
            int count = iPxzhaoshenmubiaocampustableService.count(wrapper);
            if (count > 0) {
                ajaxJson.setMsg("该校区已存在该年月的招生目标，不能重复添加");
                ajaxJson.setSuccess(false);
                return ajaxJson;
            }

            ajaxJson.setSuccess(
                    zsmb
                            .setAddStaffID(loginUser.getStaffID())
                            .setAddTime(new Date())
                            .setQiyeID(loginUser.getQiyeID())
                            .insert());
            logtext = "添加校区为：" + cam.getCampusName() + "在" + zsmb.getYearMonth() + "的招生目标";
        } else {
            QueryWrapper<Pxzhaoshenmubiaocampustable> wrapper = new QueryWrapper<>();
            wrapper.lambda()
                    .ne(Pxzhaoshenmubiaocampustable::getId, zsmb.getId())
                    .eq(Pxzhaoshenmubiaocampustable::getQiyeID, loginUser.getQiyeID())
                    .eq(Pxzhaoshenmubiaocampustable::getCampusID, zsmb.getCampusID())
                    .eq(Pxzhaoshenmubiaocampustable::getYearMonth, zsmb.getYearMonth());
            int count = iPxzhaoshenmubiaocampustableService.count(wrapper);
            if (count > 0) {
                ajaxJson.setMsg("该校区已存在该年月的招生目标，不能重复添加");
                ajaxJson.setSuccess(false);
                return ajaxJson;
            }
            logtext = "修改校区为：" + cam.getCampusName() + "在" + zsmb.getYearMonth() + "的招生目标";
            ajaxJson.setSuccess(zsmb.updateById());
        }

        savePxLog.savepxlog(logtext, "xwcloud-zsbm/yxstu/YixiangStu/saveCampusMubiao", loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());
        return ajaxJson;
    }

    /**
     * 删除校区招生目标
     */
    @DeleteMapping("/deleteCampusZhaoshengmubiao/{ids}")
    @ResponseBody
    @Transactional
    @ApiOperation("删除校区招生目标")
    public AjaxJson deleteCampusZhaoshengmubiao(@PathVariable List<Long> ids) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(
                iPxzhaoshenmubiaocampustableService.removeByIds(ids));
        return ajaxJson;
    }

    /**
     * 分页获取员工招生目标
     */
    @GetMapping("/getStaffMubiaoPages")
    @ResponseBody
    @ApiOperation("分页获取员工招生目标")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true),
            @ApiImplicitParam(name = "yearMonth", value = "年月", required = true),
            @ApiImplicitParam(name = "staffName", value = "员工名称"),
    })
    public AjaxJson getStaffMubiaoPages(HttpServletRequest request, long current, long size, long campusID, String yearMonth, String staffName) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<zhaoshengStaffMubaoVo> wrapper = new QueryWrapper<>();
        wrapper
                .eq("b.campusID", campusID)
                .eq("a.yearMonth", yearMonth)
                .eq("a.qiyeID", loginUser.getQiyeID());
        if (!ObjectUtils.isEmpty(staffName)) {
            wrapper.like("b.staffName", staffName);
        }
        ajaxJson.setObj(
                iPxzhaoshenmubiaocampustableService.getStaffZhaoshengmubiaoPages(new Page<>(current, size), wrapper)
        );
        ajaxJson.setCode("success");
        return ajaxJson;
    }

    @PostMapping("/addOrEditStaffMubiao")
    @ResponseBody
    @Transactional
    @ApiOperation("添加修改员工招生目标")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true),
            @ApiImplicitParam(name = "staffID", value = "员工ID", required = true),
            @ApiImplicitParam(name = "id", value = "ID", required = true),
            @ApiImplicitParam(name = "yearMonth", value = "年月", required = true),
            @ApiImplicitParam(name = "monthMoney", value = "月业绩", required = true),
            @ApiImplicitParam(name = "monthSum", value = "月人数", required = true),
    })
    public AjaxJson addOrEditStaffMubiao(HttpServletRequest request, @RequestBody zhaoshengStaffMubaoVo mubiao) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        String logtext = "";
        try {
            QueryWrapper<zhaoshengmubiaoVo> queryWrapper = new QueryWrapper<>();
            queryWrapper
                    .eq("b.id", mubiao.getCampusID())
                    .eq("a.yearMonth", mubiao.getYearMonth())
                    .eq("a.qiyeID", loginUser.getQiyeID());
            List<zhaoshengmubiaoVo> records = iPxzhaoshenmubiaocampustableService.GetZhaoshengmubiaoCampusPages(new Page<>(1, Integer.MAX_VALUE), queryWrapper).getRecords();
            if (records.isEmpty()) {
                ajaxJson.setMsg("改校区没有招生目标，请返回刷新并添加");
                ajaxJson.setSuccess(false);
                ajaxJson.setCode("isct");
                return ajaxJson;
            }
            Pxstafftable staff = iPxstafftableService.getById(mubiao.getStaffID());
            if (ObjectUtils.isEmpty(mubiao.getId())) {
//                添加

                double staffMubiaoOld = ObjectUtils.isEmpty(records.get(0).getYuangong()) ? 0 : Double.parseDouble(records.get(0).getYuangong());
                double campusMubiao = Double.parseDouble(records.get(0).getMonthMoney());
                if (mubiao.getMonthMoney().add(BigDecimal.valueOf(staffMubiaoOld)).compareTo(BigDecimal.valueOf(campusMubiao)) == 1) {
                    ajaxJson.setMsg("员工分配的目标总额超过校区目标总额");
                    ajaxJson.setSuccess(false);
                    ajaxJson.setCode("isct");
                    return ajaxJson;
                }
                QueryWrapper<Pxzhaoshenmubiaostafftable> wrapperCount = new QueryWrapper<>();
                wrapperCount
                        .eq("staffID", mubiao.getStaffID())
                        .eq("qiyeID", loginUser.getQiyeID());
                int count = iPxzhaoshenmubiaostafftableService.count(wrapperCount);
                if (count > 0) {
                    ajaxJson.setMsg("请勿重复添加");
                    ajaxJson.setSuccess(false);
                    ajaxJson.setCode("isct");
                    return ajaxJson;
                }
                ajaxJson.setSuccess(
                        mubiao
                                .setAddStaffID(loginUser.getStaffID())
                                .setAddTime(new Date())
                                .setQiyeID(loginUser.getQiyeID())
                                .insert()
                );


                logtext += "添加员工：" + staff.getStaffName() + ",在" + mubiao.getYearMonth() + "的招生目标";
            } else {
//                修改
                Pxzhaoshenmubiaostafftable byId = iPxzhaoshenmubiaostafftableService.getById(mubiao.getId());
                double staffMubiaoOld = ObjectUtils.isEmpty(records.get(0).getYuangong()) ? 0 : Double.parseDouble(records.get(0).getYuangong());
                double campusMubiao = Double.parseDouble(records.get(0).getMonthMoney());
                if (mubiao.getMonthMoney().add(BigDecimal.valueOf(staffMubiaoOld)).subtract(byId.getMonthMoney()).compareTo(BigDecimal.valueOf(campusMubiao)) == 1) {
                    ajaxJson.setMsg("员工分配的目标总额超过校区目标总额");
                    ajaxJson.setSuccess(false);
                    ajaxJson.setCode("isct");
                    return ajaxJson;
                }
                QueryWrapper<Pxzhaoshenmubiaostafftable> wrapperCount = new QueryWrapper<>();
                wrapperCount
                        .ne("id", mubiao.getId())
                        .eq("staffID", mubiao.getStaffID())
                        .eq("qiyeID", loginUser.getQiyeID());
                int count = iPxzhaoshenmubiaostafftableService.count(wrapperCount);
                if (count > 0) {
                    ajaxJson.setMsg("请勿重复添加");
                    ajaxJson.setSuccess(false);
                    ajaxJson.setCode("isct");
                    return ajaxJson;
                }
                ajaxJson.setSuccess(mubiao.updateById());
                logtext += "修改员工：" + staff.getStaffName() + ",在" + mubiao.getYearMonth() + "的招生目标";
            }


            savePxLog.savepxlog(logtext, "xwcloud-zsbm/yxstu/YixiangStu/addOrEditStaffMubiao", loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());

            return ajaxJson;
        } catch (Exception e) {
            ajaxJson.setMsg("出错了请刷新重试");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ajaxJson;
        }
    }

    @DeleteMapping("/deleteStaffMubiao/{ids}")
    @ResponseBody
    @Transactional
    @ApiOperation("批量删除员工招生目标")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "ids", value = "删除的IDs", required = true)
    )
    public AjaxJson deleteStaffMubiao(@PathVariable List<Long> ids, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setSuccess(
                iPxzhaoshenmubiaostafftableService.removeByIds(ids)
        );

        savePxLog.savepxlog("批量删除员工招生目标", "xwcloud-zsbm/yxstu/YixiangStu/deleteStaffMubiao", loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());
        return ajaxJson;
    }

    //endregion

    //region 意向学员信息导入模板下载

    @ResponseBody
    @RequestMapping(value = "/ExportyixiangstuFilesMuban", method = RequestMethod.GET)
    public void ExportyixiangstuFilesMuban(HttpServletResponse response, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());

        List<String> titleList = new ArrayList<String>(); //合并的列表
        List<String> li = new ArrayList<>(); //自定义字段表头列表

        List<pramaTypeVo> selectstuparamtype = iPxstuparamtypetableService.getOne(qiyeID);
        if (selectstuparamtype.size() > 0) { //添加自定义字段必填项
            for (pramaTypeVo item : selectstuparamtype) {
                li.add(item.getStuParamTypeName() + "（自定义必填项）");
            }
        }

        Pxcampustable cam = iPxcampustableService.getById(loginUser.getCampusID());
        Pxstugradetable grade = iPxstugradetableService.getOne(qiyeID);
        //表头
        String[] headlist = {"学员姓名", "性别", "联系电话（必填）", "年级/年龄段", "校区", "来源途径", " 意向科目（以逗号隔开）", "意向程度", "登记人", "登记时间", "负责人", "数据来源人", "学员电话", "学员生日"};
        //数据标题
        String[] shujulist = {"stuName", "stuSex", "parentTel", "stuGradeName", "campusName", "yixianglaiyuan", "yixiangkemu", "yixiangchengdu", "dengjiTeacher", "dengjiTime", "fuzeTeacher", "laiyuanTeacher", "stuTel", "stuBirthday"};
        List<String> HD = Arrays.asList(headlist);

        //合并表头
        titleList.addAll(HD);
        titleList.addAll(li);
        String[] newArr = titleList.toArray(new String[titleList.size()]);

        importyixiangStuInfoFilesVo One = new importyixiangStuInfoFilesVo();
        One.setStuName("张三");
        One.setStuSex("男");
        One.setParentTel("13666888668");
        One.setCampusName(cam.getCampusName());
        if (grade != null) {
            One.setStuGradeName(grade.getStugradename());
        } else {
            One.setStuGradeName("8岁以上");
        }
        One.setDengjiTeacher("管理员");
        One.setFuzeTeacher("管理员");
        One.setStuSex("男");
        One.setLaiyuanTeacher("管理员");
        One.setDengjiTime("2021-08-08");
        One.setYixiangchengdu("一般");
        One.setYixianglaiyuan("推广");
        One.setYixiangkemu("钢琴,舞蹈,美术");
        One.setStuTel("13888888888");
        One.setStuBirthday("2008-08-08");
        List<importyixiangStuInfoFilesVo> stuInfo = Collections.singletonList(One);
        List<List<Object>> list = ExportExcel.formatDataToList(newArr, stuInfo, shujulist);
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "意向学员导入模板.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //endregion

    //region 导入意向学员信息
    @ApiOperation("导入意向学员信息")
    @ResponseBody
    @RequestMapping(value = "/yixiangstuFilesExcel", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson yixiangstuFilesExcel(@RequestParam(value = "file") MultipartFile file, int jbid, HttpServletRequest request) throws ParseException {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Long staffID = loginUser.getStaffID();

        List<String> typeli = new ArrayList<>(); //自定义字段表头列表

        List<pramaTypeVo> selectstuparamtype = iPxstuparamtypetableService.getOne(qiyeID);
        if (selectstuparamtype.size() > 0) { //添加自定义字段必填项
            for (pramaTypeVo item : selectstuparamtype) {
                typeli.add(item.getStuParamTypeName() + "（自定义必填项）");
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
        //region 基础信息导入

        String logtext = "";
        stuInfo = importstuFilesExcel.readExcel("", importyixiangStuInfoFilesVo.class, file, typeli);

        if (stuInfo.size() > 0) {
            logtext += "导入意向学员信息。名单：";
            List<importyixiangStuInfoFilesVo> li = stuInfo.get("list");
            List zdlist = stuInfo.get("zdlist");
//                    for (importStuInfoFilesVo itemb : li) {
            for (int n = 0; n < li.size(); n++) {
                importyixiangStuInfoFilesVo itemb = li.get(n);
                String stuName = itemb.getStuName();
                logtext += stuName + ",";
                String stuSex = itemb.getStuSex();
                String parentTel = itemb.getParentTel();
                String stuCampusName = itemb.getCampusName();
                String stuGradeName = itemb.getStuGradeName();
                BigDecimal stuIntegar = BigDecimal.valueOf(0);
                String stuBirthDay = itemb.getStuBirthday();
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

                Pxcampustable cam = null;
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("qiyeID", qiyeID);
                queryWrapper.eq("campusName", stuCampusName);
                List<Pxcampustable> oneCampus = iPxcampustableService.list(queryWrapper);
                if (oneCampus.size() == 0) {
                    ajaxJson.setCode("N");
                    ajaxJson.setMsg("第" + (n + 1) + "行，校区不存在");
                    return ajaxJson;
                } else {
                    cam = oneCampus.get(0);
                }
                QueryWrapper<Pxstutable> stuQ = new QueryWrapper<>();
                stuQ
                        .eq("stuName", stuName)
                        .eq("qiyeID", qiyeID)
                        .eq("parentTel", parentTel);
                List<Pxstutable> stuList = iPxstutableService.list(stuQ);
                if (stuList.size() > 0) {
                    ajaxJson.setCode("N");
                    ajaxJson.setMsg("第" + (n + 1) + "行，学生已存在");
                    return ajaxJson;
                } else {

                    //region 不存在数据自动录入
                    Long nianjiid = 0L;//年级id
                    Long laiyuanid = 0L;//来源id
                    //"系统设置-招生途径 不允许为空，至少要有一个招生途径!
                    QueryWrapper queryWrapper1 = new QueryWrapper();
                    queryWrapper1.eq("qiyeID", qiyeID);
                    queryWrapper1.eq("telFromName", itemb.getYixianglaiyuan());
                    List<Pxyxtelfromtable> oneTelfrom = iPxyxtelfromtableService.list(queryWrapper1);
                    if (oneTelfrom.size() == 0) {
                        Pxyxtelfromtable telf = new Pxyxtelfromtable();
                        telf.setQiyeID(qiyeID);
                        telf.setAddStaffID(staffID);
                        telf.setBeizhu("当前系统招生途径未设置，自动添加一条招生途经");
                        telf.setAddTime(new Date());
                        telf.setTelFromName(itemb.getYixianglaiyuan());
                        iPxyxtelfromtableService.save(telf);
                        laiyuanid = telf.getId();
                    } else {
                        laiyuanid = oneTelfrom.get(0).getId();
                    }

                    QueryWrapper<Pxstugradetable> gradeQ = new QueryWrapper<>();
                    gradeQ
                            .eq("qiyeID", qiyeID)
                            .eq("stuGradeName", stuGradeName);
                    List<Pxstugradetable> stuGradetab = iPxstugradetableService.list(gradeQ);
                    if (stuGradetab.size() == 0) {
                        Pxstugradetable grade = new Pxstugradetable();
                        grade.setQiyeID(qiyeID);
                        grade.setStugradename(stuGradeName);
                        iPxstugradetableService.save(grade);
                        nianjiid = grade.getId();
                    } else {
                        nianjiid = stuGradetab.get(0).getId();
                    }
                    String yixiangID = "";
                    for (String it : itemb.getYixiangkemu().split(",")) {
                        QueryWrapper queryWrapper2 = new QueryWrapper();
                        queryWrapper2.eq("qiyeID", qiyeID);
                        queryWrapper2.eq("subjectName", it);
                        List<Pxsubjecttable> pxsubjecttable = iPxsubjecttableService.list(queryWrapper2);
                        if (pxsubjecttable.size() > 0) {
                            yixiangID += pxsubjecttable.get(0).getId() + ",";
                        }
                    }
                    //endregion

                    //region 添加StuTable

                    Pxstutable stu = new Pxstutable();
                    stu.setStuName(stuName);
                    stu.setPasswd(DigestUtils.md5DigestAsHex("123456".getBytes()));
                    stu.setParentTel(parentTel);
                    stu.setActivity(2);
                    stu.setStuSex(stuSex);
                    stu.setBuxiStateID(1);
                    stu.setStuGradeID(nianjiid);
                    stu.setCampusID(cam.getId());
                    stu.setQiyeID(qiyeID);
                    stu.setRemainXuefei(l);
                    if (StringUtils.isNotBlank(itemb.getStuBirthday())) {
                        stu.setStubirth(df.parse(itemb.getStuBirthday()));
                    }
                    stu.setLuruType(2);
                    stu.setDengjiTeacherID(staffID);
                    stu.setDengjiTime(new Date());
                    stu.setYixiangkemu(yixiangID);

                    if (stuIntegar.compareTo(l) == 1) { //>0
                        stu.setJifenNum(stuIntegar);
                    } else {
                        stu.setJifenNum(l);
                    }
                    if (StringUtils.isNoneBlank(itemb.getYixiangchengdu())) {
                        QueryWrapper queryWrapper2 = new QueryWrapper();
                        queryWrapper2.eq("qiyeID", qiyeID);
                        queryWrapper2.eq("telLevelName", itemb.getYixiangchengdu());
                        List<Pxyxtelleveltable> pxyxtelleveltables = pxyxtelleveltableService.list(queryWrapper2);
                        if (pxyxtelleveltables.size() == 0) {
                            Pxyxtelleveltable pxyxtelleveltable = new Pxyxtelleveltable();
                            pxyxtelleveltable.setAddTime(new Date());
                            pxyxtelleveltable.setAddStaffID(loginUser.getStaffID());
                            pxyxtelleveltable.setQiyeID(qiyeID);
                            pxyxtelleveltable.setTelLevelName(itemb.getYixiangchengdu());
                            pxyxtelleveltableService.save(pxyxtelleveltable);
                            stu.setYxLevelID(pxyxtelleveltable.getId());
                        } else {
                            stu.setYxLevelID(pxyxtelleveltables.get(0).getId());
                        }
                    }
                    if (StringUtils.isNotBlank(itemb.getYixianglaiyuan())) {
                        QueryWrapper queryWrapper2 = new QueryWrapper();
                        queryWrapper2.eq("qiyeID", qiyeID);
                        queryWrapper2.eq("telFromName", itemb.getYixianglaiyuan());
                        List<Pxyxtelfromtable> pxyxtelfromtables = pxyxtelfromtableService.list(queryWrapper2);
                        if (pxyxtelfromtables.size() == 0) {
                            Pxyxtelfromtable pxyxtelfromtable = new Pxyxtelfromtable();
                            pxyxtelfromtable.setAddTime(new Date());
                            pxyxtelfromtable.setAddStaffID(loginUser.getStaffID());
                            pxyxtelfromtable.setQiyeID(qiyeID);
                            pxyxtelfromtable.setTelFromName(itemb.getYixianglaiyuan());
                            pxyxtelfromtable.setBeizhu("导入意向学员时添加");
                            pxyxtelfromtableService.save(pxyxtelfromtable);
                            stu.setYxFromID(pxyxtelfromtable.getId());
                        } else {
                            stu.setYxFromID(pxyxtelfromtables.get(0).getId());
                        }
                    }
                    if (StringUtils.isNotBlank(itemb.getDengjiTeacher())) {
                        QueryWrapper queryWrapper2 = new QueryWrapper();
                        queryWrapper2.eq("qiyeID", qiyeID);
                        queryWrapper2.eq("staffName", itemb.getDengjiTeacher());
                        List<Pxstafftable> pxstafftables = iPxstafftableService.list(queryWrapper2);
                        if (pxstafftables.size() == 0) {
                            stu.setDengjiTeacherID(loginUser.getStaffID());
                        } else {
                            stu.setDengjiTeacherID(pxstafftables.get(0).getId());
                        }
                    }
                    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
                    if (StringUtils.isNotBlank(itemb.getDengjiTime())) {
                        stu.setDengjiTime(ft.parse(itemb.getDengjiTime()));
                    }
                    if (StringUtils.isNotBlank(itemb.getFuzeTeacher())) {
                        QueryWrapper queryWrapper2 = new QueryWrapper();
                        queryWrapper2.eq("qiyeID", qiyeID);
                        queryWrapper2.eq("staffName", itemb.getFuzeTeacher());
                        List<Pxstafftable> pxstafftables = iPxstafftableService.list(queryWrapper2);
                        if (pxstafftables.size() == 0) {
                        } else {
                            stu.setYxfenpeistaffID(pxstafftables.get(0).getId());
                        }
                    }
                    if (StringUtils.isNotBlank(itemb.getLaiyuanTeacher())) {
                        QueryWrapper queryWrapper2 = new QueryWrapper();
                        queryWrapper2.eq("qiyeID", qiyeID);
                        queryWrapper2.eq("staffName", itemb.getLaiyuanTeacher());
                        List<Pxstafftable> pxstafftables = iPxstafftableService.list(queryWrapper2);
                        if (pxstafftables.size() == 0) {
                        } else {
                            stu.setYxshichangTeacherID(pxstafftables.get(0).getId());
                        }
                    }
                    if (StringUtils.isNotBlank(itemb.getStuBirthday())) {
                        stu.setStubirth(ft.parse(itemb.getStuBirthday()));
                    }
                    if (StringUtils.isNotBlank(itemb.getStuTel())) {
                        stu.setStuTel(itemb.getStuTel());
                    }
                    stu.setParentTelRelation(9 + "");
                    iPxstutableService.save(stu);

                    //----自定义字段
                    if (zdlist.size() > 0) {
                        for (int m = 14; m < (14 + zdlist.size()); m++) {
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
        } else {
            ajaxJson.setMsg("导入表格为空表");
            ajaxJson.setCode("N");
            return ajaxJson;
        }

        savePxLog.savepxlog(logtext, "xwcloud-zsbm/yxstu/YixiangStu/yixiangstuFilesExcel", loginUser.getStaffID(), loginUser.getStaffName(), 1, loginUser.getQiyeID());

        return ajaxJson;
        //endregion
    }
    //endregion

}
