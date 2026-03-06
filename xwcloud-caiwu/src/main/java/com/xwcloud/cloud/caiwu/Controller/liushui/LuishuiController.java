package com.xwcloud.cloud.caiwu.Controller.liushui;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.caiwu.Dao.IPxliushuizhangtableDao;
import com.xwcloud.cloud.caiwu.Service.IPxliushuizhangtableService;
import com.xwcloud.cloud.caiwu.Service.IPxpaymoneystyletableService;
import com.xwcloud.cloud.caiwu.Service.IPxpowertableService;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.DateUtil;
import com.xwcloud.cloud.common.ExportExcel;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.entity.Pxliushuizhangtable;
import com.xwcloud.cloud.model.entity.Pxpaymoneystyletable;
import com.xwcloud.cloud.model.entity.Pxsalarystyletable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/caiwu/liushui")
@Api(tags = "财务流水")
public class LuishuiController {

    @Autowired
    IPxliushuizhangtableService iPxliushuizhangtableService;

    @Autowired
    IPxpaymoneystyletableService iPxpaymoneystyletableService;

    @Autowired
    IPxpowertableService iPxpowertableService;

    @Autowired
    IPxliushuizhangtableDao iPxliushuizhangtableDao;


    @ResponseBody
    @GetMapping("getpayMoneyList")
    @ApiOperation(value = "获取支付方式")
    public AjaxJson getpayMoneyList(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        List<Pxpaymoneystyletable> list = iPxpaymoneystyletableService.list(
                new QueryWrapper<Pxpaymoneystyletable>()
                        .eq("qiyeID", loginUser.getQiyeID())
        );
        ajaxJson.setObj(list);
        return ajaxJson;
    }


    @RequestMapping(value = "/getLiushuiPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取流水分页数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "liushuiID", value = "流水ID", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "paystyleID", value = "支付方式ID", required = false),
            @ApiImplicitParam(name = "shouzhiStyleID", value = "收支类别ID", required = false),
            @ApiImplicitParam(name = "jinbanrenName", value = "经办人", required = false),
            @ApiImplicitParam(name = "startLiushuiDate", value = "开始流水时间", required = false),
            @ApiImplicitParam(name = "endLiushuiDate", value = "结束流水时间", required = false),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
    })
    public AjaxJson getLiushuiPage(HttpServletResponse respons, HttpServletRequest request,
                                   @RequestParam(required = false, defaultValue = "10") long size,
                                   @RequestParam(required = false, defaultValue = "1") long current,
                                   @RequestParam(required = false) String liushuiID,
                                   @RequestParam(required = false) String campusID,
                                   @RequestParam(required = false) String paystyleID,
                                   @RequestParam(required = false) String liushuizhaiyao,
                                   @RequestParam(required = false) String shouzhiStyleID,
                                   @RequestParam(required = false) String jinbanrenName,
                                   @RequestParam(required = false) String startLiushuiDate,
                                   @RequestParam(required = false) String endLiushuiDate
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<HashMap<String, String>> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("liushui.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(liushuiID)) {
            queryWrapper.like("liushui.id", liushuiID);
        }
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("liushui.campusID", campusID);
        }
        if (StringUtils.isNotBlank(shouzhiStyleID)) {
            queryWrapper.eq("liushui.shouzhiStyleID", shouzhiStyleID);
        }
        if (StringUtils.isNotBlank(paystyleID)) {
            queryWrapper.eq("liushui.payMoneyStyle", paystyleID);
        }
        if (StringUtils.isNotBlank(liushuizhaiyao)) {
            queryWrapper.like("liushui.liushuiZaiyao", liushuizhaiyao);
        }

        if (StringUtils.isNotBlank(jinbanrenName)) {
            queryWrapper.like("jinbanrenstaff.staffName", jinbanrenName);
        }
        if (StringUtils.isNotBlank(startLiushuiDate)) {
            queryWrapper.ge("liushui.liushuiDateTime", startLiushuiDate);
        }
        if (StringUtils.isNotBlank(endLiushuiDate)) {
            queryWrapper.ge("liushui.liushuiDateTime", endLiushuiDate);
        }
        //根据权限范围加载数据
        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", 341);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("liushui.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("liushui.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("liushui.campusID", lookPower);
        }
        queryWrapper.orderByDesc("liushui.liushuiDateTime");
        page = iPxliushuizhangtableService.getLiushuiPage(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/addLiushui", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class})
    @ApiOperation(value = "添加流水项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id"),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
    })
    public AjaxJson addLiushui(HttpServletRequest request,
                               String campusID,
                               String shouzhiStyleID,
                               String payMoneyStyle,
                               String shouruMoney,
                               String zhichuMoney,
                               String liushuiDateTime,
                               String jinbanRen,
                               String liushuiZaiyao,
                               String stuID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        if (StringUtils.isBlank(campusID) || StringUtils.isBlank(shouzhiStyleID) || StringUtils.isBlank(payMoneyStyle) ||
                StringUtils.isBlank(liushuiDateTime) ||
                StringUtils.isBlank(jinbanRen) || StringUtils.isBlank(liushuiZaiyao)) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("参数不完整!");
            return ajaxJson;
        }
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Pxliushuizhangtable pxliushuizhangtable = new Pxliushuizhangtable();
        pxliushuizhangtable.setCampusID(Long.valueOf(campusID));
        pxliushuizhangtable.setShouzhiStyleID(Long.valueOf(shouzhiStyleID));
        pxliushuizhangtable.setPayMoneyStyle(Long.valueOf(payMoneyStyle));
        pxliushuizhangtable.setLiushuiDateTime(DateUtil.toDate(liushuiDateTime, "yyyy-MM-dd HH:mm:ss"));
        pxliushuizhangtable.setJinbanRen(Long.valueOf(jinbanRen));
        pxliushuizhangtable.setLiushuiZaiyao(liushuiZaiyao);
        pxliushuizhangtable.setLuruTime(new Date());
        if(StringUtils.isNotBlank(stuID)){
            pxliushuizhangtable.setStuID(Long.valueOf(stuID));
        }
        pxliushuizhangtable.setAddStaffID(loginUser.getStaffID());
        if (StringUtils.isNotBlank(shouruMoney)) {
            pxliushuizhangtable.setShouruMoney(new BigDecimal(shouruMoney));
        } else {
            pxliushuizhangtable.setShouruMoney(new BigDecimal(0));
        }
        if (StringUtils.isNotBlank(zhichuMoney)) {
            pxliushuizhangtable.setZhichuMoney(new BigDecimal(zhichuMoney));
        } else {
            pxliushuizhangtable.setZhichuMoney(new BigDecimal(0));
        }
        pxliushuizhangtable.setQiyeID(loginUser.getQiyeID());
        ajaxJson.setSuccess(iPxliushuizhangtableService.save(pxliushuizhangtable));
        return ajaxJson;
    }

    @RequestMapping(value = "/editLiushui", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class})
    @ApiOperation(value = "修改流水项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id"),
            @ApiImplicitParam(name = "liushuiDateTime", value = "流水时间"),
            @ApiImplicitParam(name = "campusID", value = "校区ID"),
            @ApiImplicitParam(name = "liushuiZaiyao", value = "流水摘要"),
            @ApiImplicitParam(name = "payMoneyStyle", value = "付款方式ID"),
            @ApiImplicitParam(name = "shouruMoney", value = "收入金额"),
            @ApiImplicitParam(name = "zhichuMoney", value = "支出金额"),
            @ApiImplicitParam(name = "shouzhiStyleID", value = "收支类别ID"),
            @ApiImplicitParam(name = "jinbanRen", value = "经办人"),
            @ApiImplicitParam(name = "qiandanID", value = "签单ID"),
            @ApiImplicitParam(name = "addStaffID", value = "录入人"),
            @ApiImplicitParam(name = "luruTime", value = "录入时间"),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
            @ApiImplicitParam(name = "stuID", value = "学员ID"),
    })
    public AjaxJson editLiushui(HttpServletRequest request,
                                Long id,
                                String campusID,
                                String shouzhiStyleID,
                                String payMoneyStyle,
                                String shouruMoney,
                                String zhichuMoney,
                                String liushuiDateTime,
                                String jinbanRen,
                                String liushuiZaiyao

    ) {
        AjaxJson ajaxJson = new AjaxJson();
        if (StringUtils.isBlank(campusID) || StringUtils.isBlank(shouzhiStyleID) || StringUtils.isBlank(payMoneyStyle) ||
                StringUtils.isBlank(liushuiDateTime) ||
                StringUtils.isBlank(jinbanRen) || StringUtils.isBlank(liushuiZaiyao)) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("参数不完整!");
            return ajaxJson;
        }
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Pxliushuizhangtable pxliushuizhangtable = new Pxliushuizhangtable();
        pxliushuizhangtable.setId(id);
        pxliushuizhangtable.setCampusID(Long.valueOf(campusID));
        pxliushuizhangtable.setShouzhiStyleID(Long.valueOf(shouzhiStyleID));
        pxliushuizhangtable.setPayMoneyStyle(Long.valueOf(payMoneyStyle));
        pxliushuizhangtable.setLiushuiDateTime(DateUtil.toDate(liushuiDateTime, "yyyy-MM-dd HH:mm:ss"));
        pxliushuizhangtable.setJinbanRen(Long.valueOf(jinbanRen));
        pxliushuizhangtable.setLiushuiZaiyao(liushuiZaiyao);
        pxliushuizhangtable.setLuruTime(new Date());
        pxliushuizhangtable.setAddStaffID(loginUser.getStaffID());
        if (StringUtils.isNotBlank(shouruMoney)) {
            pxliushuizhangtable.setShouruMoney(new BigDecimal(shouruMoney));
        } else {
            pxliushuizhangtable.setShouruMoney(new BigDecimal(0));
        }
        if (StringUtils.isNotBlank(zhichuMoney)) {
            pxliushuizhangtable.setZhichuMoney(new BigDecimal(zhichuMoney));
        } else {
            pxliushuizhangtable.setZhichuMoney(new BigDecimal(0));
        }
        pxliushuizhangtable.setQiyeID(loginUser.getQiyeID());
        ajaxJson.setSuccess(iPxliushuizhangtableService.updateById(pxliushuizhangtable));
        return ajaxJson;
    }

    @RequestMapping(value = "/getLiushui", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取流水项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "流水ID"),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
    })
    public AjaxJson getLiushui(HttpServletResponse respons,
                               String id, HttpServletRequest request

    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        HashMap<String, String> obj = iPxliushuizhangtableService.getLiushui(id, loginUser.getQiyeID());
        ajaxJson.setObj(obj);
        return ajaxJson;
    }

    @RequestMapping(value = "/delLiushui", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class})
    @ApiOperation(value = "删除流水项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "IDs", value = "IDs"),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
    })
    public AjaxJson delLiushui(HttpServletResponse respons,
                               String IDs, HttpServletRequest request
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        String[] strings = IDs.split(",");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper.in("id", strings);
        List<Pxsalarystyletable> list = iPxliushuizhangtableService.list(queryWrapper);
        if (list == null || list.size() <= 0) {
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        ajaxJson.setSuccess(iPxliushuizhangtableService.removeByIds(Arrays.asList(strings)));
        return ajaxJson;
    }

    @RequestMapping(value = "/getLiushuiDay", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "日统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Ym", value = "年月份"),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
    })
    public AjaxJson getLiushuiDay(HttpServletResponse respons,
                                  String Ym, HttpServletRequest request
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");

        List<HashMap<String, String>> list = iPxliushuizhangtableService.getLiushuiDay(Ym, loginUser.getQiyeID());
        ajaxJson.setObj(list);
        return ajaxJson;
    }

    @RequestMapping(value = "/getShouruDay", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "收支统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Ym", value = "年月份"),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
    })
    public AjaxJson getShouruDay(HttpServletResponse respons,
                                 String Ym, HttpServletRequest request
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");

        List<HashMap<String, String>> list = iPxliushuizhangtableService.getShouruDay(Ym, loginUser.getQiyeID());
        ajaxJson.setObj(list);
        return ajaxJson;
    }

    @ResponseBody
    @RequestMapping(value = "/GetRishouRuDuizhangTableKey",method = RequestMethod.GET)
    public AjaxJson GetRishouRuDuizhangTableKey(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        List<Pxpaymoneystyletable> paystyleList = iPxliushuizhangtableDao.getPaystyleList(queryWrapper);
        for (Integer i = 0; i < 2; i++) {
            Pxpaymoneystyletable pxpaymoneystyletable = new Pxpaymoneystyletable();
            if (i == 0) {
                pxpaymoneystyletable.setMoneystyleName("余额支付");
                pxpaymoneystyletable.setId(Long.valueOf(-2));
            }
            if (i == 1) {
                pxpaymoneystyletable.setMoneystyleName("小程序支付");
                pxpaymoneystyletable.setId(Long.valueOf(-1));
            }
            paystyleList.add(pxpaymoneystyletable);
        }
        ajaxJson.setObj(paystyleList);
        return ajaxJson;
    }

    @RequestMapping(value = "/GetRishouruDuizhangList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "日收入对账")
    public AjaxJson GetRishouruDuizhangList(HttpServletRequest request, String Ym) throws ParseException {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date dt = null;
        try {
            dt = sdf.parse(Ym);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.MONTH, 1);
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        List<Pxpaymoneystyletable> paystyleList = iPxliushuizhangtableDao.getPaystyleList(queryWrapper);
        for (Integer i = 0; i < 2; i++) {
            Pxpaymoneystyletable pxpaymoneystyletable = new Pxpaymoneystyletable();
            if (i == 0) {
                pxpaymoneystyletable.setMoneystyleName("余额支付");
                pxpaymoneystyletable.setId(Long.valueOf(-2));
            }
            if (i == 1) {
                pxpaymoneystyletable.setMoneystyleName("小程序支付");
                pxpaymoneystyletable.setId(Long.valueOf(-1));
            }
            paystyleList.add(pxpaymoneystyletable);
        }

        List<HashMap<String, String>> backData = new ArrayList<>();
        String riqi = Ym + "-01";
        SimpleDateFormat sdfX = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdfX.parse(riqi);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Integer monthDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int xx = 1; xx <= monthDay; xx++) {
            HashMap<String, String> backList = new HashMap<>();
            String mm = Ym.split("-")[1];
            String dd = "";
            if (xx < 10) {
                dd = "0" + xx;
            } else {
                dd = xx + "";
            }
            backList.put("Date", Ym.split("-")[0] + "-" + mm + "-" + dd);
            HashMap<String, BigDecimal> stringArray = new HashMap<>();
            for (Pxpaymoneystyletable item : paystyleList) {
                QueryWrapper queryWrapper1 = new QueryWrapper();
                queryWrapper1.eq("qiyeID", loginUser.getQiyeID());
                queryWrapper1.eq("liushuiDateTime", Ym.split("-")[0] + "-" + mm + "-" + dd);
                queryWrapper1.eq("payMoneyStyle", item.getId());
                BigDecimal summoney = iPxliushuizhangtableService.GetLiushuizhangMoney(queryWrapper1);
                stringArray.put(item.getMoneystyleName(), summoney);
                backList.put(item.getMoneystyleName(), summoney + "");
            }
            backData.add(backList);
        }
        ajaxJson.setObj(backData);
        return ajaxJson;
    }

    @RequestMapping(value = "/getShouzhiStyleList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "收支类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Ym", value = "年月份"),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
    })
    public AjaxJson getShouzhiStyleList(HttpServletRequest request,
                                        String shouzhistyle
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        List<HashMap<String, String>> list = iPxliushuizhangtableService.getShouzhiStyleList(loginUser.getQiyeID(), shouzhistyle);
        ajaxJson.setObj(list);
        return ajaxJson;
    }

    @RequestMapping(value = "/getPaymoneystyleList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "支付类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
    })
    public AjaxJson getPaymoneystyleList(HttpServletRequest request
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        List<Pxpaymoneystyletable> list = iPxliushuizhangtableService.getPayMoneyStyleList(loginUser.getQiyeID());
        ajaxJson.setObj(list);
        return ajaxJson;
    }

    @ResponseBody
    @RequestMapping(value = "/Getshouzhistyle", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有收支方式")
    public AjaxJson Getshouzhistyle(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setObj(iPxliushuizhangtableService.GetAllSearchshouzhiStyleList(loginUser.getQiyeID()));
        return ajaxJson;
    }

    @RequestMapping(value = "/exportLiushuiList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出流水")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "liushuiID", value = "流水ID", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "paystyleID", value = "支付方式ID", required = false),
            @ApiImplicitParam(name = "shouzhiStyleID", value = "收支类别ID", required = false),
            @ApiImplicitParam(name = "jinbanrenName", value = "经办人", required = false),
            @ApiImplicitParam(name = "startLiushuiDate", value = "开始流水时间", required = false),
            @ApiImplicitParam(name = "endLiushuiDate", value = "结束流水时间", required = false),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
    })
    public void exportLiushuiList(HttpServletResponse response,
                                  @RequestParam(required = false) String liushuiID,
                                  @RequestParam(required = false) String campusID,
                                  @RequestParam(required = false) String month,
                                  @RequestParam(required = false) String startLiushuiDate,
                                  @RequestParam(required = false) String endLiushuiDate,
                                  String qiyeID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("liushui.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(liushuiID)) {
            queryWrapper.eq("liushui.id", liushuiID);
        }
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("liushui.campusID", campusID);
        }
        if (StringUtils.isNotBlank(startLiushuiDate)) {
            queryWrapper.ge("liushui.liushuiDateTime", startLiushuiDate);
        }
        if (StringUtils.isNotBlank(endLiushuiDate)) {
            queryWrapper.le("liushui.liushuiDateTime", endLiushuiDate);
        }
        if (StringUtils.isNotBlank(month)) {
            queryWrapper.eq("DATE_FORMAT(liushui.liushuiDateTime,'%Y-%m')", month);
        }

        queryWrapper.orderByDesc("liushui.liushuiDateTime");
        List<HashMap<String, Object>> list = iPxliushuizhangtableService.getLiushuiList(queryWrapper);
        List<List<Object>> returnlist = ExportExcel.formatHashMapDataToList(new String[]{"流水账号", "流水时间", "校区", "摘要", "收支方式",
                        "借方(收入)", "贷方(支出)", "收入支出分类", "经办人", "录入时间"},
                list,
                new String[]{"id", "liushuiDateTime", "campusName", "liushuiZaiyao", "moneystyleName", "shouruMoney", "zhichuMoney",
                        "shouzhiStyle", "jinbanrenName", "luruTime"});
        try {
            ExportExcel.exportExcel(response, returnlist, "流水", "流水导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
