package com.xwcloud.cloud.caiwu.Controller.gongzi;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.caiwu.Service.*;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.DateUtil;
import com.xwcloud.cloud.common.ExportExcel;
import com.xwcloud.cloud.common.importstuFilesExcel;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.Vo.GongziImportVo;
import com.xwcloud.cloud.model.Vo.PxsalarytableVo;
import com.xwcloud.cloud.model.Vo.PxsalaryxiangxitableVo;
import com.xwcloud.cloud.model.entity.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/caiwu/pxgongzitable/gongzimanage")
@Api(tags = "工资管理")
public class GongziManageController {

    @Autowired
    IPxsalarytableService iPxsalarytableService;
    @Autowired
    IPxsalaryxiangxitableService iPxsalaryxiangxitableService;
    @Autowired
    IPxstafftableService iPxstafftableService;
    @Autowired
    IPxstaffposttableService iPxstaffposttableService;
    @Autowired
    IPxcampustableService iPxcampustableService;
    @Autowired
    IPxsalarystyletableService iPxsalarystyletableService;
    @Autowired
     IPxpowertableService iPxpowertableService;


    @RequestMapping(value = "/getGongziPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取工资分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = true),
            @ApiImplicitParam(name = "current", value = "页码", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "staffPost", value = "岗位名称", required = false),
            @ApiImplicitParam(name = "staffName", value = "员工名称", required = false),
            @ApiImplicitParam(name = "startDate", value = "开始年月", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束年月", required = false),
            @ApiImplicitParam(name = "lururen", value = "录入人名称", required = false),
            @ApiImplicitParam(name = "startluruDate", value = "录入开始年月", required = false),
            @ApiImplicitParam(name = "endluruDate", value = "录入结束年月", required = false),
            @ApiImplicitParam(name = "status", value = "审核状态", required = false),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
    })
    public AjaxJson getGongziPage(HttpServletRequest request,
                                  @RequestParam(required = false, defaultValue = "10") long size,
                                  @RequestParam(required = false, defaultValue = "1") long current,
                                  @RequestParam(required = false) String campusID, // 校区ID
                                  @RequestParam(required = false) String staffPost, // 岗位
                                  @RequestParam(required = false) String staffName, // 员工名称
                                  @RequestParam(required = false) String startDate, // 开始年月
                                  @RequestParam(required = false) String endDate, // 结束年月
                                  @RequestParam(required = false) String lururen, // 录入人名称
                                  @RequestParam(required = false) String startluruDate, // 录入开始年月
                                  @RequestParam(required = false) String endluruDate, // 录入结束年月
                                  @RequestParam(required = false) String status // 审核状态
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Page<PxsalarytableVo> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("salary.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("campus.id", campusID);
        }
        if (StringUtils.isNotBlank(staffPost)) {
            queryWrapper.like("staffpost.staffpostName", staffPost);
        }
        if (StringUtils.isNotBlank(staffName)) {
            queryWrapper.like("staff.staffName", staffName);
        }
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("salary.salaryDate", staffName);
        }
        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("salary.salaryDate", endDate);
        }
        if (StringUtils.isNotBlank(lururen)) {
            queryWrapper.like("salary.lururen", lururen);
        }
        if (StringUtils.isNotBlank(startluruDate)) {
            queryWrapper.le("salary.lurudatetime", startluruDate);
        }
        if (StringUtils.isNotBlank(endluruDate)) {
            queryWrapper.ge("salary.lurudatetime", endluruDate);
        }
        if (StringUtils.isNotBlank(status)) {
            queryWrapper.eq("salary.shenheState", status);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 321);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("salary.lururen", loginUser.getStaffID());
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("campus.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("campus.campusID", lookPower);
        }

        page = iPxsalarytableService.getPage(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/getGongzi", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取工资")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "salaryID", value = "工资ID", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson getGongzi(HttpServletRequest request,
                              String salaryID,
                              String qiyeID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("salary.id", salaryID);
        queryWrapper.eq("salary.qiyeID", qiyeID);
        PxsalarytableVo salary = iPxsalarytableService.getGongzi(queryWrapper);
        ajaxJson.setObj(salary);
        return ajaxJson;
    }

    @RequestMapping(value = "/getGongziPro", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取工资项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "staffID", value = "员工ID", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson getGongziPro(HttpServletRequest request,
                                 String staffID,
                                 String qiyeID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("staff.id", staffID);
        queryWrapper.eq("salarystyle.qiyeID", qiyeID);
        List<Pxsalarystyletable> list = iPxsalarytableService.getGongziPro(queryWrapper);
        ajaxJson.setObj(list);
        return ajaxJson;
    }

    @RequestMapping(value = "/addGongzi", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class})
    @ApiOperation(value = "添加工资")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "staffID", value = "员工ID", required = true),
            @ApiImplicitParam(name = "month", value = "月份", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
            @ApiImplicitParam(name = "gongziPro", value = "工资项目", required = true),
    })
    public AjaxJson addGongzi(HttpServletRequest request,
                              String staffID,
                              String month,
                              @RequestBody Map<String, String> gongziPro
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        // 写入工资表
        Pxsalarytable salary = new Pxsalarytable();
        BigDecimal decimal = new BigDecimal("0"); // 初始化金额为0

        for (String value : gongziPro.values()) {
            BigDecimal tmp = new BigDecimal(value);
            decimal = decimal.add(tmp); // 对金额进行增加
        }

        salary.setSalaryMoney(decimal.toString());// 工资金额
        salary.setShenheState(0); // 审核状态
        salary.setStaffID(Long.valueOf(staffID)); // 员工ID
        salary.setSalaryDate(DateUtil.toDate(month, "yyyy-MM"));// 发工资的日期
        salary.setLururen(loginUser.getStaffID()); // 录入人
        salary.setLurudatetime(new Date()); // 录入时间
        salary.setFafangzhuangtai(0);// 发放状态
        salary.setQiyeID(loginUser.getQiyeID()); // 企业ID

        if (!iPxsalarytableService.save(salary)) {
            throw new RuntimeException();
        }
        // 写入工资明细表
        List<Pxsalaryxiangxitable> xiangxiList = new ArrayList<>();
        for (Map.Entry<String, String> entry : gongziPro.entrySet()) {
            //System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
            Pxsalaryxiangxitable xiangxi = new Pxsalaryxiangxitable();
            xiangxi.setSalarystyleID(entry.getKey());// 工资类别ID
            xiangxi.setSalarymoney(new BigDecimal(entry.getValue())); // 工资金额
            xiangxi.setSalaryID(salary.getId()); // 工资ID
            xiangxi.setAddTime(new Date());// 添加时间
            xiangxi.setAddStaffID(loginUser.getStaffID());
            xiangxi.setQiyeID(loginUser.getQiyeID());
            xiangxiList.add(xiangxi);
        }
        if (!iPxsalaryxiangxitableService.saveBatch(xiangxiList)) {
            throw new RuntimeException();
        }
        ajaxJson.setSuccess(true);
        // 写入日志


        return ajaxJson;
    }

    @RequestMapping(value = "/getGongziminxiList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取工资明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "salaryID", value = "工资ID", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson getGongziminxiList(HttpServletRequest request,
                                       String salaryID,
                                       String qiyeID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("xiangxi.salaryID", salaryID);
        queryWrapper.eq("xiangxi.qiyeID", qiyeID);
        List<PxsalaryxiangxitableVo> list = iPxsalaryxiangxitableService.getxiangxiList(queryWrapper);
        ajaxJson.setObj(list);
        return ajaxJson;
    }


    @RequestMapping(value = "/editGongzi", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class})
    @ApiOperation(value = "修改工资")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "salaryID", value = "工资IDs", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
            @ApiImplicitParam(name = "gongziPro", value = "工资项目", required = true),
    })
    public AjaxJson editGongzi(HttpServletRequest request,
                               String salaryID,
                               String qiyeID,
                               @RequestBody Map<String, String> gongziPro
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        // 获取工资数据
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", qiyeID);
        queryWrapper.eq("id", salaryID);
        Pxsalarytable salary = iPxsalarytableService.getOne(queryWrapper);
        // 获取工资详细数据
        QueryWrapper xiangxiQW = new QueryWrapper();
        xiangxiQW.eq("salaryID", salaryID);
        xiangxiQW.eq("qiyeID", qiyeID);
        List<Pxsalaryxiangxitable> list = iPxsalaryxiangxitableService.list(xiangxiQW);
        // 修改工资详细数据
        for (int i = 0; i < list.size(); i++) {
            for (Map.Entry<String, String> entry : gongziPro.entrySet()) {
                //System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
                if (list.get(i).getSalarystyleID().equals(entry.getKey())) {
                    list.get(i).setSalarymoney(new BigDecimal(entry.getValue()));
                }
            }
        }
        // 重新统计工资金额
        BigDecimal bigDecimal = new BigDecimal("0");
        for (int i = 0; i < list.size(); i++) {
            Pxsalarystyletable pxsalarystyletable = iPxsalarystyletableService.getById(list.get(i).getSalarystyleID());
            if (pxsalarystyletable.getIsJiaOrJianOrQiuhe() == 1) {
                bigDecimal = bigDecimal.add(list.get(i).getSalarymoney());
            } else {
                bigDecimal = bigDecimal.subtract(list.get(i).getSalarymoney());
            }

        }
        salary.setSalaryMoney(bigDecimal.toString());
        // 修改明细
        iPxsalaryxiangxitableService.updateBatchById(list);
        // 修改工资
        ajaxJson.setSuccess(iPxsalarytableService.updateById(salary));
        return ajaxJson;
    }

    @RequestMapping(value = "/delGongzi", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class})
    @ApiOperation(value = "删除工资")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "salaryIDs", value = "工资IDs", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson delGongzi(HttpServletRequest request,
                              String salaryIDs
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        String[] strings = salaryIDs.split(",");
        for (int i = 0; i < strings.length; i++) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("id", strings[i]);
            queryWrapper.eq("qiyeID", loginUser.getQiyeID());


            iPxsalarytableService.remove(queryWrapper);
            iPxsalaryxiangxitableService.remove(new QueryWrapper<Pxsalaryxiangxitable>()
                    .eq("salaryID", strings[i])
                    .eq("qiyeID", loginUser.getQiyeID())
            );

//            if (!iPxsalaryxiangxitableService.remove(queryWrapper)) {
//                throw new RuntimeException();
//            }
//            if (!iPxsalarytableService.removeById(strings[i])) {
//                throw new RuntimeException();
//            }
            ajaxJson.setSuccess(true);
        }

        return ajaxJson;
    }

    @RequestMapping(value = "/changeVerify", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class})
    @ApiOperation(value = "审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "salaryID", value = "工资ID", required = true),
            @ApiImplicitParam(name = "shenheState", value = "审核状态", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson changeVerify(HttpServletRequest request,
                                 String salaryID,
                                 int shenheState
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("id", salaryID);
        Pxsalarytable salary = iPxsalarytableService.getOne(queryWrapper);
        if (salary.getShenheState() != 0) {
            ajaxJson.setMsg("已经审核过了!");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        salary.setShenheState(shenheState);
        salary.setShengheren(loginUser.getStaffID());
        ajaxJson.setSuccess(iPxsalarytableService.updateById(salary));
        return ajaxJson;
    }


    @ApiOperation("导入工资下载模板")
    @ResponseBody
    @RequestMapping(value = "ExportGongziMuban", method = RequestMethod.GET)
    public void ExportGongziMuban(HttpServletResponse response, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Pxstafftable staff = iPxstafftableService.getById(loginUser.getStaffID());
        Pxstaffposttable staffpost = iPxstaffposttableService.getById(staff.getStaffPostID());
        Pxcampustable cam = iPxcampustableService.getById(staff.getCampusID());
        GongziImportVo GZImportVo = new GongziImportVo();
        GZImportVo.setStaffName(staff.getStaffName());
        GZImportVo.setCampusName(cam.getCampusName());
        GZImportVo.setStaffPostName(staffpost.getStaffpostName());
        GZImportVo.setGongziDate("2021-05-10");
        List<GongziImportVo> gongzi = Collections.singletonList(GZImportVo);

        List<Pxsalarystyletable> gzstyle = iPxsalarystyletableService.list(new QueryWrapper<Pxsalarystyletable>()
                .eq("qiyeID", loginUser.getQiyeID()));

        String[] headlist = {"校区", "员工名称", "岗位", "工资开始时间", "工资结束时间", "底薪（加项）", "课时费（加项）", "全勤奖（加项）", "迟到（减项）", "工资总额（合计）"};
        List<String> HD = Arrays.asList(headlist);
        List<String> titleList = new ArrayList<String>(); //合并的列表

        String[] shujulist = {"campusName", "staffName", "staffPostName", "GongziDate", "GongziDate"};

//        List<String> li = new ArrayList<>(); //自定义表头列表
//        if (gzstyle.size() >= 0) {
//            for (Pxsalarystyletable item : gzstyle) {
//                li.add(item.getSalaryStyle());
//            }
//        }
        //合并表头
        titleList.addAll(HD);
        //titleList.addAll(li);

        String[] newArr = titleList.toArray(new String[titleList.size()]);

        List<List<Object>> list = ExportExcel.formatDataToList(newArr, gongzi, shujulist);
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "工资导入模板.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/importGongzi", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class})
    @ApiOperation(value = "导入工资")
    public AjaxJson importGongzi(HttpServletRequest request,
                                 @RequestParam(value = "file") MultipartFile file
    ) throws ParseException {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        String errList = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfgzdate = new SimpleDateFormat("yyyy/MM/dd");
        int okCount = 0;

        long t1 = System.currentTimeMillis();
        List<String> li = new ArrayList<>(); //动态表头列表
        List<Pxsalarystyletable> gzstyle = iPxsalarystyletableService.list(
                new QueryWrapper<Pxsalarystyletable>()
                        .eq("qiyeID", loginUser.getQiyeID()));
        if (gzstyle.size() >= 0) {
            for (Pxsalarystyletable item : gzstyle) {
                li.add(item.getSalaryStyle());
            }
        }

        //List<GongziImportVo> list = importExcel.readExcel("", GongziImportVo.class, file);
        Map<String, List> gzlist = null; ///导入信息

        gzlist = importstuFilesExcel.readExcel("", GongziImportVo.class, file, li);
        List zdlist = gzlist.get("zdlist"); //动态表头
        List<GongziImportVo> list = gzlist.get("list");

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                GongziImportVo item = list.get(i);
                if (item.getCampusName() == null || item.getCampusName() == "") {
                    errList += "第" + (i + 1) + "行:校区为空为空,";
                } else if (item.getStaffName() == null || item.getStaffName() == "") {
                    errList += "第" + (i + 1) + "行:名称为空,";
                } else if (item.getStaffPostName() == null || item.getStaffPostName() == "") {
                    errList += "第" + (i + 1) + "行:岗位为空,";
                } else if (item.getGongziDate() == null || item.getGongziDate() == "") {
                    errList += "第" + (i + 1) + "行:工资开始时间为空,";
                } else if (item.getGongziEndDate() == null || item.getGongziEndDate() == "") {
                    errList += "第" + (i + 1) + "行：工资结束时间为空";
                } else {
                    String campusName = item.getCampusName();
                    String staffName = item.getStaffName();
                    String staffPostName = item.getStaffPostName();
                    String gongziDate = item.getGongziDate();
                    String gongziEndDate = item.getGongziEndDate();

                    Date gzDate = sdf.parse(gongziDate);
                    Date gzEdnDate = sdf.parse(gongziEndDate);
                    //gzDate = sdfgzdate.parse(gongziDate);
                    Pxcampustable cam = iPxcampustableService.getOne(new QueryWrapper<Pxcampustable>()
                            .eq("campusName", campusName)
                            .eq("qiyeID", loginUser.getQiyeID())
                    );
                    if (cam == null) {
                        errList += "第" + (i + 1) + "行,系统找不到此校区，";
                    }
                    Pxstafftable staff = iPxstafftableService.getOne(
                            new QueryWrapper<Pxstafftable>()
                                    .eq("staffName", staffName)
                                    .eq("qiyeID", loginUser.getQiyeID())
                    );
                    if (staff == null) {
                        errList += "第" + (i + 1) + "行,系统找不到此员工，";
                    }
                    Pxstaffposttable stPost = iPxstaffposttableService.getOne(new QueryWrapper<Pxstaffposttable>()
                            .eq("staffpostName", staffPostName)
                            .eq("campusID", cam.getId())
                            .eq("qiyeID", loginUser.getQiyeID())
                    );
                    if (stPost == null) {
                        errList += "第" + (i + 1) + "行,系统找不到此岗位，";
                    }
                    if (stPost.getId() != staff.getStaffPostID()) {
                        errList += "第" + (i + 1) + "行,员工岗位与系统不符，";
                    } else {
                        //正常数据录入


                        HashMap<String, Object> ss = (HashMap<String, Object>) zdlist.get(0);
                        if (ss.size() >= 0) {
                            BigDecimal allmoney = new BigDecimal(0);
                            BigDecimal hejimoney = new BigDecimal(0);
                            for (int m = 5; m < (5 + ss.size()); m++) {
                                String zdy = (String) ((HashMap<String, Object>) zdlist.get(i)).get(m);
                                String[] zdStr = zdy.split(",");
                                String pvalue = zdStr[0];
                                String pname = zdStr[1];
                                BigDecimal money = new BigDecimal(pvalue);

                                if (pname.split("（")[1].split("）")[0].equals("合计")) {
                                    BigDecimal hj = new BigDecimal(zdStr[0]);
                                    hejimoney = hj;
                                } else {
                                    int jiaorjian = pname.split("（")[1].split("）")[0].equals("加项") ? 1 : 2;
                                    if (jiaorjian == 1) {
                                        allmoney = allmoney.add(money);
                                    } else {
                                        allmoney = allmoney.subtract(money);
                                    }
                                }
                            }
                            if (allmoney.compareTo(hejimoney) != 0) {
                                ajaxJson.setCode("N");
                                ajaxJson.setMsg("合计金额验证失败");
                                return ajaxJson;
                            }

                            Pxsalarytable sala = new Pxsalarytable();
                            sala.setSalaryMoney("0");
                            sala.setShenheState(1);
                            sala.setStaffID(staff.getId());
                            sala.setShengHeTime(gzDate);
                            sala.setSalaryDate(gzDate);
                            sala.setSalaryEndDate(gzEdnDate);
                            sala.setLururen(loginUser.getStaffID());
                            sala.setLurudatetime(new Date());
                            Pxstafftable gly = iPxstafftableService.getOne(new QueryWrapper<Pxstafftable>()
                                    .eq("staffPostID", 1L)
                                    .eq("qiyeID", loginUser.getQiyeID())
                                    .last("limit 1")
                            );
                            sala.setShengheren(gly.getId());
                            sala.setBeizhu("导入工资，自动审核通过，审核人默认管理员");
                            sala.setQiyeID(loginUser.getQiyeID());
                            iPxsalarytableService.save(sala);

                            for (int m = 5; m < (5 + ss.size()); m++) {
                                okCount++;
                                String zdy = (String) ((HashMap<String, Object>) zdlist.get(i)).get(m);
                                String[] zdStr = zdy.split(",");
                                String pvalue = zdStr[0];
                                String pname = zdStr[1];
                                BigDecimal money = new BigDecimal(pvalue);
                                if (pname.split("（")[1].split("）")[0].equals("合计")) {
                                    sala.setSalaryMoney(zdStr[0]);
                                    iPxsalarytableService.updateById(sala);
                                } else {
                                    boolean jiajian = pname.split("（")[1].split("）")[0].equals("加项");
                                    int jiaorjian = pname.split("（")[1].split("）")[0].equals("加项") ? 1 : 2;
                                    if (jiaorjian == 1) {
                                        allmoney = allmoney.add(money);
                                    } else {
                                        allmoney = allmoney.subtract(money);
                                    }
                                    Pxsalarystyletable salatype = iPxsalarystyletableService.getOne(new QueryWrapper<Pxsalarystyletable>()
                                            .eq("salaryStyle", pname.split("（")[0])
                                            .eq("qiyeID", loginUser.getQiyeID())
                                    );
                                    Pxsalarystyletable pxsalarystyletable = new Pxsalarystyletable();
                                    if (salatype == null) {
                                        pxsalarystyletable.setIsJiaOrJianOrQiuhe(jiaorjian);
                                        pxsalarystyletable.setSalaryStyle(pname.split("（")[0]);
                                        pxsalarystyletable.setStaffID(loginUser.getStaffID());
                                        pxsalarystyletable.setLurudate(new Date());
                                        pxsalarystyletable.setQiyeID(loginUser.getQiyeID());
                                        iPxsalarystyletableService.save(pxsalarystyletable);
                                    }
                                    long salarysID = salatype == null ? pxsalarystyletable.getId() : salatype.getId();
                                    Pxsalaryxiangxitable salaxx = new PxsalaryxiangxitableVo();
                                    salaxx.setSalaryID(sala.getId());
                                    salaxx.setSalarymoney(money);
                                    salaxx.setSalarystyleID(salarysID + "");
                                    salaxx.setShuoming("导入添加数据");
                                    salaxx.setAddStaffID(staff.getId());
                                    salaxx.setAddTime(new Date());
                                    salaxx.setQiyeID(loginUser.getQiyeID());
                                    iPxsalaryxiangxitableService.save(salaxx);
                                }
                            }

                        }

                    }
                }
            }
        } else {
            ajaxJson.setMsg("空数据表！");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        ajaxJson.setMsg(errList);
        return ajaxJson;
    }

    @RequestMapping(value = "/exportGongziList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("导出工资信息")
    public void exportGongziList(HttpServletResponse response, HttpServletRequest request, @RequestParam(required = false) String campusID, // 校区ID
                                 @RequestParam(required = false) String staffPost, // 岗位
                                 @RequestParam(required = false) String staffID, // 员工名称
                                 @RequestParam(required = false) String startDate, // 开始年月
                                 @RequestParam(required = false) String endDate, // 结束年月
                                 @RequestParam(required = false) String lururen, // 录入人名称
                                 @RequestParam(required = false) String startluruDate, // 录入开始年月
                                 @RequestParam(required = false) String endluruDate, // 录入结束年月
                                 @RequestParam(required = false) String status) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("salary.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("campus.id", campusID);
        }
        if (StringUtils.isNotBlank(staffPost)) {
            queryWrapper.like("staffpost.staffpostName", staffPost);
        }
        if (StringUtils.isNotBlank(staffID)) {
            queryWrapper.like("staff.id", staffID);
        }
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("salary.salaryDate", startDate);
        }
        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("salary.salaryDate", endDate);
        }
        if (StringUtils.isNotBlank(lururen)) {
            queryWrapper.like("salary.lururen", lururen);
        }
        if (StringUtils.isNotBlank(startluruDate)) {
            queryWrapper.le("salary.lurudatetime", startluruDate);
        }
        if (StringUtils.isNotBlank(endluruDate)) {
            queryWrapper.ge("salary.lurudatetime", endluruDate);
        }
        if (StringUtils.isNotBlank(status)) {
            queryWrapper.eq("salary.shenheState", status);
        }
        List<PxsalarytableVo> pxsalarytableVoList = iPxsalarytableService.GetSalaryList(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "岗位", "员工", "工资总额", "开始日期", "结束日期", "录入人", "录入时间", "审核状态", "审核人"},
                pxsalarytableVoList,
                new String[]{"campusName", "postName", "staffName", "salaryMoney", "salaryDate-DT", "salaryEndDate-DT", "lururenName", "lurudatetime-DT", "shenheState", "shengherenName"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "导出工资信息.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
