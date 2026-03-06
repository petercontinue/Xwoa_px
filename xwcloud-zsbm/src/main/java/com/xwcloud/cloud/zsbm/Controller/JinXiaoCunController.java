package com.xwcloud.cloud.zsbm.Controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.ExportExcel;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.Form.addcaigoushenpiForm;
import com.xwcloud.cloud.model.Form.enterForm;
import com.xwcloud.cloud.model.Form.outForm;
import com.xwcloud.cloud.model.Form.savewpSaleForm;

import com.xwcloud.cloud.model.entity.*;
import com.xwcloud.cloud.zsbm.Service.*;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/zsbm/JinXiaoCun")
@Api(tags = "进销存")
public class JinXiaoCunController {
    @Autowired
    IPxteachingsuppliestableService iPxteachingsuppliestableService;

    @Autowired
    IPxteachingsuppliesorderstableService iPxteachingsuppliesorderstableService;

    @Autowired
    IPxteachingsuppliesorderdetailtableService iPxteachingsuppliesorderdetailtableService;

    @Autowired
    IPxteachingsuppliesouttableService iPxteachingsuppliesouttableService;

    @Autowired
    IPxstutableService iPxstutableService;

    @Autowired
    IPxchongzhipaytableService iPxchongzhipaytableService;

    @Autowired
    IPxliushuizhangtableService iPxliushuizhangtableService;

    @Autowired
    IPxteachingsuppliestypetableService iPxteachingsuppliestypetableService;

    @Autowired
    IPxteachingsuppliesbuytableService iPxteachingsuppliesbuytableService;

    @Autowired
    IPxpowertableService iPxpowertableService;

    @Autowired
    IPxshouzhistyletableService iPxshouzhistyletableService;

    @Autowired
    savePxLog savePxLog;

    /**
     * 通过条码查询商品信息
     *
     * @param tiaoma
     * @return
     */
    @RequestMapping(value = "/GetTeachingSuppliesByTiaoma", method = RequestMethod.GET)
    @ApiOperation("通过条码查询商品信息")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tiaoma", value = "商品条码", required = true)
    })
    public AjaxJson GetTeachingSuppliesByTiaoma(String tiaoma) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxteachingsuppliestableService.getTeachingSuppliesByTiaoma(tiaoma));
        return ajaxJson;
    }

    /**
     * 查询进销存销售流水
     *
     * @param size
     * @param current
     * @return
     */
    @RequestMapping(value = "/getXiaoshouLiushuiDays", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("查询进销存销售流水")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
    })
    public AjaxJson getXiaoshouLiushuiDays(long size, long current) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<jinxiaocunXSliushuiVo> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("1", 1);
        ajaxJson.setObj(iPxteachingsuppliesorderstableService.getXiaoshouLiushuiDays(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * 保存进销存收银台结算信息
     *
     * @param form
     * @return
     */
    @RequestMapping(value = "/SaveSaleWupingInfo", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("保存进销存收银台结算信息")
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson SaveSaleWupingInfo(@RequestBody savewpSaleForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        String logtext = "";
        long qiyeID = loginUser.getQiyeID();
        long staffID = loginUser.getStaffID();
        long campusID = loginUser.getCampusID();
        Pxteachingsuppliesorderstable orders = new Pxteachingsuppliesorderstable();
        orders.setCreatDatetime(new Date());
        orders.setCreatStaffID(staffID);
        orders.setOrderMoney(form.getAmountmoney());
        orders.setShijiPayMoney(form.getShijiamount());
        orders.setYouHuiMoney(form.getYouhuijine());
        orders.setOrderNo(getOrderIdByUUId());
        orders.setPayMoneyStyle(form.getPaystyle());
        if (form.getPaystyle().equals("-1")) {
            //选择小程序支付；如果学生不存在，不提交订单
            Pxstutable stu = iPxstutableService.getById(form.getStuID());
            if (stu == null) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("学生不存在");
                return ajaxJson;
            }
            orders.setOrderState(1);
        }
        orders.setQiyeID(qiyeID);
        iPxteachingsuppliesorderstableService.save(orders);
        ajaxJson.setSuccess(true);
        String wpName = "";
        Long stuID = null;
        List<jxcwpData> jxcwpData = JSON.parseArray(form.getWpData(), jxcwpData.class);
        for (jxcwpData jxcwp : jxcwpData) {
            Pxteachingsuppliestable teachingSupplies = iPxteachingsuppliestableService.getById(jxcwp.getId());
            logtext += "卖出：" + teachingSupplies.getName() + "," + jxcwp.getBuySum() + teachingSupplies.getStockUnit();
            Pxteachingsuppliesorderdetailtable orderdetail = new Pxteachingsuppliesorderdetailtable();
            orderdetail.setOrderID(orders.getId());
            orderdetail.setBuyPrice(jxcwp.getSalePrice());
            orderdetail.setBuySum(jxcwp.getBuySum());
            orderdetail.setSuppliesID(jxcwp.getId());
            orderdetail.setSuppliesName(teachingSupplies.getName());
            orderdetail.setQiyeID(qiyeID);
            orderdetail.setSuppliesTiaoma(teachingSupplies.getChangpinTiaoma());
            iPxteachingsuppliesorderdetailtableService.save(orderdetail);
            Pxteachingsuppliesouttable outjl = new Pxteachingsuppliesouttable();
            outjl.setLuruStaffId(staffID);
            outjl.setOutDate(new Date());
            outjl.setOutNum(jxcwp.getBuySum());
            outjl.setOutnumBefore(teachingSupplies.getStockNum());
            outjl.setOutReason("收银台结算购买");
            outjl.setOutStaffId(staffID);
            outjl.setSuppliesId(teachingSupplies.getId());
            outjl.setQiyeID(qiyeID);
            outjl.setType(2);
            iPxteachingsuppliesouttableService.save(outjl);
            teachingSupplies.setStockNum(teachingSupplies.getStockNum().subtract(jxcwp.getBuySum()));
            iPxteachingsuppliestableService.updateById(teachingSupplies);
            wpName += teachingSupplies.getName() + ",";

            Pxstutable stu = iPxstutableService.getById(form.getStuID());
            if (stu == null) {
                logtext += ",给学员:" + stu.getStuName() + ",学号：" + stu.getId();
            }

        }
        if (form.getPaystyle().equals("-2")) {
            logtext += "。学员使用余额支付。";
            Pxstutable stu = iPxstutableService.getById(form.getStuID());
            stuID = stu.getId();
            if (stu == null) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("学生不存在");
                return ajaxJson;
            }
            if (stu.getRemainChongzhi().compareTo(form.getShijiamount()) == -1) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("当前学生余额不够支付!");
                return ajaxJson;
            }
            stu.setRemainChongzhi(stu.getRemainChongzhi().subtract(form.getShijiamount()));
            Pxchongzhipaytable jiaoyi = new Pxchongzhipaytable();
            jiaoyi.setStuID(stu.getId());
            jiaoyi.setChongzhiPayMoney(form.getShijiamount());
            jiaoyi.setType(4);
            jiaoyi.setBeizhu("商品收银台购买商品");
            jiaoyi.setJixiaocunID(orders.getId());
            jiaoyi.setAddStaffID(staffID);
            jiaoyi.setAddTime(new Date());
            jiaoyi.setQiyeID(qiyeID);
            iPxchongzhipaytableService.save(jiaoyi);
        } else {

            if (form.getPaystyle().equals("-1")) {

            } else {
                List<Pxqiandanpaymoney> pxqiandanpaymoneyList = JSON.parseArray(form.getZhifustylemoney(),
                        Pxqiandanpaymoney.class);
                for (Pxqiandanpaymoney pxqiandanpaymoney : pxqiandanpaymoneyList) {
                    if (pxqiandanpaymoney.getPayMoney().compareTo(new BigDecimal(0)) == 1) {
                        Pxliushuizhangtable liushui = new Pxliushuizhangtable();
                        liushui.setCampusID(campusID);
                        liushui.setJinbanRen(staffID);
                        liushui.setLiushuiDateTime(new Date());
                        liushui.setId(Long.parseLong(getOrderIdByUUId()));
                        liushui.setLiushuiZaiyao("商品收银台结算商品,购买商品：" + wpName);
                        liushui.setLuruTime(new Date());
                        liushui.setPayMoneyStyle(pxqiandanpaymoney.getPaymoneyStyleID());
                        liushui.setShouruMoney(pxqiandanpaymoney.getPayMoney());
                        QueryWrapper queryWrapper = new QueryWrapper();
                        queryWrapper.eq("shouzhiStyle", "进销存收入");
                        List<Pxshouzhistyletable> pxshouzhistyletable = iPxshouzhistyletableService.list(queryWrapper);
                        if (pxshouzhistyletable == null) {
                            ajaxJson.setCode("N");
                            ajaxJson.setMsg("请先设置进销存的收支方式");
                            return ajaxJson;
                        }
                        liushui.setShouzhiStyleID(pxshouzhistyletable.get(0).getId());
                        liushui.setStuID(stuID);
                        liushui.setZhichuMoney(new BigDecimal(0));
                        liushui.setAddStaffID(staffID);
                        liushui.setQiyeID(qiyeID);
                        iPxliushuizhangtableService.save(liushui);
                        logtext += "。产生流水ID：" + liushui.getId();
                    }
                }
            }
        }

        savePxLog.savepxlog(logtext, "xwcloud-zsbm/zsbm/JinXiaoCun" +
                        "/SaveSaleWupingInfo", loginUser.getStaffID(), loginUser.getStaffName(), 1,
                loginUser.getQiyeID());

        ajaxJson.setSuccess(true);
        return ajaxJson;
    }

    @RequestMapping(value = "/GetTodayXiaoshouliushui", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询当前天的收银记录")
    public AjaxJson GetTodayXiaoshouliushui(long size, long current, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<jinxiaocunXSliushuiVo> page = new Page(current, size);
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        ajaxJson.setObj(iPxteachingsuppliesorderstableService.GetTodayXiaoshouliushui(page, qiyeID));
        return ajaxJson;
    }

    /**
     * 查询进销存销售流水
     *
     * @param size
     * @param current
     * @param campusID
     * @param OrderNo
     * @param SuppliesName
     * @param staffName
     * @param request
     * @return
     */
    @RequestMapping(value = "/getXiaoshouLiushuiPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("查询进销存销售流水")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区", required = false),
            @ApiImplicitParam(name = "OrderNo", value = "订单编号", required = false),
            @ApiImplicitParam(name = "SuppliesName", value = "物品名称", required = false),
            @ApiImplicitParam(name = "staffName", value = "员工姓名", required = false),
    })
    public AjaxJson getXiaoshouLiushuiPages(long size, long current, long campusID, String OrderNo,
                                            String SuppliesName, String staffName, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<jinxiaocunXSliushuiVo> page = new Page(current, size);
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (campusID != 0) {
            queryWrapper.eq("c.campusID", campusID);
        }
        if (StringUtils.isNotBlank(OrderNo)) {
            queryWrapper.like("b.OrderNo", OrderNo);
        }
        if (StringUtils.isNotBlank(SuppliesName)) {
            queryWrapper.like("SuppliesName", SuppliesName);
        }
        if (StringUtils.isNotBlank(staffName)) {
            queryWrapper.like("staffName", staffName);
        }
        queryWrapper.orderByDesc("b.CreatDatetime");
        ajaxJson.setObj(iPxteachingsuppliesorderstableService.getXiaoshouLiushuiDays(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * 导出商品销售流水
     *
     * @param response
     * @param request
     * @param campusID
     * @param startDate
     * @param endDate
     */
    @RequestMapping(value = "/exportXiaoshouliushui", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出商品销售流水")
    public void exportXiaoshouliushui(HttpServletResponse response, HttpServletRequest request,
                                      @RequestParam(required = false) long campusID,
                                      @RequestParam(required = false) String startDate,
                                      @RequestParam(required = false) String endDate) {
        QueryWrapper queryWrapper = new QueryWrapper();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (campusID != 0) {
            queryWrapper.like(" c.campusID", campusID);
        }
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("a.CreatDatetime", startDate);
        }
        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("a.CreatDatetime", endDate);
        }
        List<jinxiaocunXSliushuiVo> pxqiandanList =
                iPxteachingsuppliesorderstableService.GetAllXiaoshouliushuiList(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "订单号", "商品名称", "出售单价", "出售数量",
                        "合计金额", "操作人", "销售时间"},
                pxqiandanList,
                new String[]{"campusName", "OrderNo", "SuppliesName", "BuyPrice", "BuySum", "OrderMoney", "staffName"
                        , "CreatDatetime"});

        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "商品销售流水导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 分页查询商品信息
     *
     * @param size
     * @param current
     * @param campusID
     * @param name
     * @param typeName
     * @param specs
     * @param changpinTiaoma
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetTeachingSuppliesPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("分页查询商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "campusName", value = "校区名称", required = false),
            @ApiImplicitParam(name = "name", value = "物品名称", required = false),
            @ApiImplicitParam(name = "typeName", value = "物品类别名称", required = false),
            @ApiImplicitParam(name = "specs", value = "物品规格", required = false),
            @ApiImplicitParam(name = "changpinTiaoma", value = "物品编码", required = false),
    })
    public AjaxJson GetTeachingSuppliesPages(long size, long current, long campusID, String name, String typeName,
                                             String specs, String changpinTiaoma, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Page<teachingSuppliesVo> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (campusID != 0) {
            queryWrapper.like("a.campusID", campusID);
        }
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like("name", name);
        }
        if (StringUtils.isNotBlank(typeName)) {
            queryWrapper.like("typeName", typeName);
        }
        if (StringUtils.isNotBlank(specs)) {
            queryWrapper.like("specs", specs);
        }
        if (StringUtils.isNotBlank(changpinTiaoma)) {
            queryWrapper.like("changpinTiaoma", changpinTiaoma);
        }
        //根据权限范围加载数据
        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", 153);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限
        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }
        queryWrapper.orderByDesc("a.addDate");
        ajaxJson.setObj(iPxteachingsuppliestableService.GetTeachingSuppliesPages(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * 生成订单ID
     *
     * @return
     */
    public static String getOrderIdByUUId() {
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

    //region 商品类别管理

    /**
     * 分页查询商品信息
     *
     * @param size
     * @param current
     * @return
     */
    @RequestMapping(value = "/GetAllTypeByPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("分页查询商品类别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
    })
    public AjaxJson GetAllTypeByPages(long size, long current, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", qiyeID);
        Page<Pxteachingsuppliestypetable> page = new Page(current, size);
        ajaxJson.setObj(iPxteachingsuppliestypetableService.page(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * 保存商品类别信息
     *
     * @param typeName
     * @param request
     * @return
     */
    @RequestMapping(value = "/AddTeachingSuppliesType", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("保存商品类别信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = false),
            @ApiImplicitParam(name = "typeName", value = "类别名称", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson AddTeachingSuppliesType(String typeName, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Pxteachingsuppliestypetable pxteachingsuppliestypetable = new Pxteachingsuppliestypetable();
        pxteachingsuppliestypetable.setQiyeID(qiyeID);
        pxteachingsuppliestypetable.setTypeName(typeName);
        ajaxJson.setSuccess(iPxteachingsuppliestypetableService.save(pxteachingsuppliestypetable));

        savePxLog.savepxlog("保存商品类别信息", "xwcloud-zsbm/zsbm/JinXiaoCun" +
                        "/AddTeachingSuppliesType", loginUser.getStaffID(), loginUser.getStaffName(), 1,
                loginUser.getQiyeID());
        return ajaxJson;
    }

    /**
     * 修改商品类别信息
     *
     * @param id
     * @param typeName
     * @param request
     * @return
     */
    @RequestMapping(value = "/UpdateTeachingSuppliesType", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("修改商品类别信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true),
            @ApiImplicitParam(name = "typeName", value = "类别名称", required = true)
    })
    public AjaxJson UpdateTeachingSuppliesType(long id, String typeName, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Pxteachingsuppliestypetable pxteachingsuppliestypetable = iPxteachingsuppliestypetableService.getById(id);
        pxteachingsuppliestypetable.setTypeName(typeName);
        ajaxJson.setSuccess(iPxteachingsuppliestypetableService.updateById(pxteachingsuppliestypetable));
        savePxLog.savepxlog("修改商品类别信息", "xwcloud-zsbm/zsbm/JinXiaoCun" +
                        "/UpdateTeachingSuppliesType", loginUser.getStaffID(), loginUser.getStaffName(), 1,
                loginUser.getQiyeID());
        return ajaxJson;
    }

    /**
     * 删除物品类别信息
     *
     * @param ID
     * @return
     */
    @RequestMapping(value = "/DeleteTeachingSuppliesType", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation("删除物品类别信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID", value = "删除数据ID", required = true)
    })
    public AjaxJson DeleteTeachingSuppliesType(String ID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");

        ajaxJson.setSuccess(iPxteachingsuppliestypetableService.removeById(ID));
        savePxLog.savepxlog("删除物品类别信息", "xwcloud-zsbm/zsbm/JinXiaoCun" +
                        "/DeleteTeachingSuppliesType", loginUser.getStaffID(), loginUser.getStaffName(), 1,
                loginUser.getQiyeID());
        return ajaxJson;
    }

    @ResponseBody
    @ApiOperation(value = "修改教学物品状态")
    @RequestMapping(value = "/UpdateWupingState", method = RequestMethod.GET)
    public AjaxJson UpdateWupingState(String ID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");


        Pxteachingsuppliestable pxteachingsuppliestable = iPxteachingsuppliestableService.getById(ID);
        String logtext = "";
        if (pxteachingsuppliestable.getIsQiYong() == true) {
            logtext = "修改前:启用";
        } else {
            logtext = "修改前:不启用";
        }
        if (pxteachingsuppliestable.getIsQiYong()) {
            pxteachingsuppliestable.setIsQiYong(false);
            logtext = "，修改后：不启用";
        } else {
            pxteachingsuppliestable.setIsQiYong(true);
            logtext = "，修改后：启用";
        }
        ajaxJson.setSuccess(iPxteachingsuppliestableService.updateById(pxteachingsuppliestable));

        savePxLog.savepxlog("修改教学物品状态，" + logtext, "xwcloud-zsbm/zsbm/JinXiaoCun" +
                        "/UpdateWupingState", loginUser.getStaffID(), loginUser.getStaffName(), 1,
                loginUser.getQiyeID());
        return ajaxJson;
    }

    @RequestMapping(value = "/GetAllTeachingSuppliesTypeList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("查询所有物品类别信息")
    public AjaxJson GetAllTeachingSuppliesTypeList(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", qiyeID);
        ajaxJson.setObj(iPxteachingsuppliestypetableService.list(queryWrapper));
        return ajaxJson;
    }

    //endregion

    /**
     * 保存入库信息
     *
     * @param form
     * @return
     */
    @RequestMapping(value = "/Enter_supplies", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("保存入库信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "staffID", value = "录入人ID", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true),
            @ApiImplicitParam(name = "id", value = "数据ID", required = false),
            @ApiImplicitParam(name = "acceptStaffId", value = "审核人", required = true),
            @ApiImplicitParam(name = "typeId", value = "物品类别ID", required = true),
            @ApiImplicitParam(name = "name", value = "入库物品名称", required = true),
            @ApiImplicitParam(name = "specs", value = "入库物品规格", required = true),
            @ApiImplicitParam(name = "stockNum", value = "入库数量", required = true),
            @ApiImplicitParam(name = "stockUnit", value = "入库数量单位", required = true),
            @ApiImplicitParam(name = "addReason", value = "入库说明", required = true),
            @ApiImplicitParam(name = "buyPrice", value = "购买价格", required = true),
            @ApiImplicitParam(name = "salePrice", value = "出售价格", required = true),
            @ApiImplicitParam(name = "wupintiaoma", value = "物品条码", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true)
    })
    public AjaxJson Enter_supplies(@RequestBody enterForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        long staffID = loginUser.getStaffID();
        String logtext = "";
        if (form.getStockNum().compareTo(new BigDecimal(0)) <= 0) {
            ajaxJson.setMsg("入库数量必须大于0");
            return ajaxJson;
        }
        if (form.getId() == 0) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("typeId", form.getTypeId());
            queryWrapper.eq("name", form.getName());
            queryWrapper.eq("campusID", form.getCampusID());
            queryWrapper.eq("specs", form.getSpecs());
            queryWrapper.eq("buyPrice", form.getBuyPrice());
            queryWrapper.eq("salePrice", form.getSalePrice());
            queryWrapper.eq("changpinTiaoma", form.getWupintiaoma());
            Pxteachingsuppliestable pxteachingsuppliestable =
                    iPxteachingsuppliestableService.getTeachingSupplies(queryWrapper);
            Long addsuppliesID = null;
            String supplieName = "";
            BigDecimal OutnumBefore = new BigDecimal(0);
            if (pxteachingsuppliestable != null) {
                OutnumBefore = pxteachingsuppliestable.getStockNum();
                pxteachingsuppliestable.setStockNum(pxteachingsuppliestable.getStockNum().add(form.getStockNum()));
                iPxteachingsuppliestableService.updateById(pxteachingsuppliestable);
                addsuppliesID = pxteachingsuppliestable.getId();
                supplieName = pxteachingsuppliestable.getName();
            } else {
                Pxteachingsuppliestable addsu = new Pxteachingsuppliestable();
                addsu.setCampusID(form.getCampusID());
                addsu.setName(form.getName());
                addsu.setSpecs(form.getSpecs());
                addsu.setStockNum(form.getStockNum());
                addsu.setStockUnit(form.getStockUnit());
                addsu.setTypeId(Long.parseLong(form.getTypeId()));
                addsu.setAddDate(new Date());
                addsu.setYanshouStaffId(Long.parseLong(form.getAcceptStaffId()));
                addsu.setRukuShuoming(form.getAddReason());
                addsu.setBuyPrice(form.getBuyPrice());
                addsu.setSalePrice(form.getSalePrice());
                addsu.setIsQiYong(true);
                addsu.setQiyeID(qiyeID);
                addsu.setChangpinTiaoma(form.getWupintiaoma());
                iPxteachingsuppliestableService.save(addsu);
                addsuppliesID = addsu.getId();
                supplieName = addsu.getName();
            }
            Pxteachingsuppliesouttable pxteachingsuppliesouttable = new Pxteachingsuppliesouttable();
            pxteachingsuppliesouttable.setSuppliesId(addsuppliesID);
            pxteachingsuppliesouttable.setOutStaffId(staffID);
            pxteachingsuppliesouttable.setOutReason(form.getAddReason());
            pxteachingsuppliesouttable.setLuruStaffId(staffID);
            pxteachingsuppliesouttable.setOutDate(new Date());
            pxteachingsuppliesouttable.setOutNum(form.getStockNum());
            pxteachingsuppliesouttable.setOutnumBefore(OutnumBefore);
            pxteachingsuppliesouttable.setType(1);
            pxteachingsuppliesouttable.setQiyeID(qiyeID);
            iPxteachingsuppliesouttableService.save(pxteachingsuppliesouttable);


            logtext += "添加商品:" + form.getName() + "入库记录";
        } else {
            Pxteachingsuppliestable sutab = iPxteachingsuppliestableService.getById(form.getId());
            sutab.setCampusID(form.getCampusID());
            sutab.setName(form.getName());
            sutab.setSpecs(form.getSpecs());
            sutab.setStockNum(form.getStockNum());
            sutab.setStockUnit(form.getStockUnit());
            sutab.setTypeId(Long.parseLong(form.getTypeId()));
            sutab.setYanshouStaffId(Long.parseLong(form.getAcceptStaffId()));
            sutab.setBuyPrice(form.getBuyPrice());
            sutab.setSalePrice(form.getSalePrice());
            sutab.setRukuShuoming(form.getAddReason());
            sutab.setQiyeID(qiyeID);
            iPxteachingsuppliestableService.updateById(sutab);
            logtext += "修改原入库记录";
        }
        ajaxJson.setSuccess(true);

        savePxLog.savepxlog(logtext, "xwcloud-zsbm/zsbm/JinXiaoCun" +
                        "/Enter_supplies", loginUser.getStaffID(), loginUser.getStaffName(), 1,
                loginUser.getQiyeID());
        return ajaxJson;
    }

    /**
     * 删除入库商品信息
     *
     * @param Id
     * @param request
     * @return
     */
    @RequestMapping(value = "/DeleteTeachingSupplies", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除入库商品信息")
    public AjaxJson DeleteTeachingSupplies(long Id, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", qiyeID);
        queryWrapper.eq("suppliesId", Id);
        queryWrapper.eq("type", 2);
        List<Pxteachingsuppliesouttable> pxteachingsuppliesouttableList =
                iPxteachingsuppliesouttableService.list(queryWrapper);
        if (pxteachingsuppliesouttableList.size() > 0) {
            ajaxJson.setMsg("存在出库记录.不能删除");
            ajaxJson.setCode("N");
            return ajaxJson;
        } else {
            iPxteachingsuppliesouttableService.remove(new QueryWrapper<Pxteachingsuppliesouttable>()
                    .eq("qiyeID", qiyeID)
                    .eq("type", 1)
                    .eq("suppliesId", Id)
            );
            Pxteachingsuppliestable byId = iPxteachingsuppliestableService.getById(Id);

            savePxLog.savepxlog("删除商品:" + byId.getName() + "的入库商品信息", "xwcloud-zsbm/zsbm/JinXiaoCun/Enter_supplies",
                    loginUser.getStaffID(), loginUser.getStaffName(), 1,
                    loginUser.getQiyeID());
            ajaxJson.setSuccess(iPxteachingsuppliestableService.removeById(Id));

        }
        return ajaxJson;
    }

    /**
     * 保存出库
     *
     * @param form
     * @return
     */
    @RequestMapping(value = "/Out_supplie", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("出库")
    public AjaxJson Out_supplie(@RequestBody outForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        long staffID = loginUser.getStaffID();
        if (form.getOutNum().compareTo(new BigDecimal(0)) <= 0) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("出库数量必须大于0");
            return ajaxJson;
        }
        try {
            Pxteachingsuppliestable ruku = iPxteachingsuppliestableService.getById(form.getId());
            if (ruku == null) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("该教学用品不存在");
                return ajaxJson;
            }
            if (ruku.getStockNum().compareTo(form.getOutNum()) == -1) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("库存不足");
                return ajaxJson;
            }
            Pxteachingsuppliesouttable chuku = new Pxteachingsuppliesouttable();
            chuku.setSuppliesId(form.getId());
            chuku.setOutNum(form.getOutNum());
            chuku.setOutnumBefore(ruku.getStockNum());
            chuku.setOutReason(form.getOutReason());
            chuku.setOutStaffId(form.getLingyongStaffId());
            chuku.setLuruStaffId(form.getJibanStaffId());
            chuku.setOutDate(new Date());
            chuku.setType(2);
            chuku.setQiyeID(qiyeID);
            iPxteachingsuppliesouttableService.save(chuku);
            ruku.setStockNum(ruku.getStockNum().subtract(form.getOutNum()));
            iPxteachingsuppliestableService.updateById(ruku);
            ajaxJson.setMsg("出库成功");

            savePxLog.savepxlog("商品:" + ruku.getName() + "出库，数量：" + form.getOutNum() + ruku.getStockUnit(), "xwcloud-zsbm/zsbm/JinXiaoCun/Out_supplie",
                    loginUser.getStaffID(), loginUser.getStaffName(), 1,
                    loginUser.getQiyeID());

        } catch (Exception e) {
            ajaxJson.setMsg("操作失败");
        }
        return ajaxJson;
    }

    /**
     * 出库记录
     *
     * @param size
     * @param current
     * @param suppliesID
     * @return
     */
    @RequestMapping(value = "/outTeachingSuppliesJilu", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("出库记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "suppliesID", value = "教学物品ID", required = true)
    })
    public AjaxJson outTeachingSuppliesJilu(long size, long current, long suppliesID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Page<outjiluVo> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (suppliesID != 0) {
            queryWrapper.eq("a.suppliesId", suppliesID);
        }
        queryWrapper.eq("a.type", 2);
        //根据权限范围加载数据
        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", 153);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("b.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("b.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("b.campusID", lookPower);
        }
        ajaxJson.setObj(iPxteachingsuppliesouttableService.GetTeachingSuppliesOutPages(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * 入库记录
     *
     * @param size
     * @param current
     * @param suppliesID
     * @return
     */
    @RequestMapping(value = "/EnterteachingSupplieJilu", method = RequestMethod.GET)
    @ApiOperation("入库记录")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "suppliesID", value = "教学物品ID", required = true),
    })
    public AjaxJson EnterteachingSupplieJilu(long size, long current, long suppliesID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Page<outjiluVo> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (suppliesID != 0) {
            queryWrapper.eq("a.suppliesID", suppliesID);
        }
        queryWrapper.eq("a.type", 1);
        //根据权限范围加载数据
        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", 153);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("b.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("b.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("b.campusID", lookPower);
        }
        ajaxJson.setObj(iPxteachingsuppliesouttableService.GetTeachingSuppliesOutPages(page, queryWrapper));
        return ajaxJson;
    }

    @RequestMapping(value = "/delOutputjili", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation("删除出库记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID", value = "删除数据ID", required = true)
    })
    public AjaxJson delOutputjili(String ID, long qiyeID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long outStaffId = null;
        try {
            Pxteachingsuppliesouttable delData = iPxteachingsuppliesouttableService.getById(ID);
            Pxteachingsuppliestable ts = iPxteachingsuppliestableService.getById(delData.getSuppliesId());
            if (ts != null) {
                Pxteachingsuppliesouttable ruku = new Pxteachingsuppliesouttable();
                ruku.setSuppliesId(ts.getId());
                ruku.setOutNum(delData.getOutNum());
                ruku.setOutnumBefore(ts.getStockNum());
                ruku.setOutReason("删除出库记录，还回库存");
                ruku.setOutStaffId(outStaffId);
                ruku.setLuruStaffId(outStaffId);
                ruku.setOutDate(new Date());
                ruku.setType(1);
                ruku.setQiyeID(qiyeID);
                iPxteachingsuppliesouttableService.save(ruku);
                ts.setStockNum(ts.getStockNum().add(delData.getOutNum()));
                iPxteachingsuppliestableService.updateById(ts);
            } else {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("无法删除,有教学用品不存在");
                return ajaxJson;
            }
            ajaxJson.setSuccess(iPxteachingsuppliesouttableService.removeById(ID));

            savePxLog.savepxlog("删除商品：" + ts.getName() + "出库记录", "xwcloud-zsbm/zsbm/JinXiaoCun/delOutputjili", loginUser.getStaffID(),
                    loginUser.getStaffName(), 1,
                    loginUser.getQiyeID());

            return ajaxJson;
        } catch (Exception e) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("数据格式错误，请联系客服");
            return ajaxJson;
        }
    }

    /**
     * 导出教学物品信息（出库记录入库记录物品信息）
     *
     * @param response
     * @param campusID
     * @param type
     */
    @RequestMapping(value = "/exportteachingSupplies", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("导出教学物品信息（出库记录入库记录物品信息）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "type", value = "导出数据类型：1出库记录信息导出；2入库记录信息导出；3教学物品信息导出", required = true),
    })
    public void exportteachingSupplies(HttpServletResponse response, @RequestParam(required = false) long campusID,
                                       @RequestParam(required = true) Integer type, HttpServletRequest request) {
        QueryWrapper queryWrapper = new QueryWrapper();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (type == 1) {//出库记录信息导出
            queryWrapper.eq("type", 1);
            if (campusID != 0) {
                queryWrapper.eq("b.campusID", campusID);
            }
            List<outjiluVo> pxoutjlList = iPxteachingsuppliesouttableService.GetTeachingSuppliesOutList(queryWrapper);
            List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "名称 ", " 类别 ", " 规格 ", " 入库前数量 "
                            , " 入库数量 ", " 入库后数量 ", " 数量单位 ", " 入库说明 ", " 入库时间 ", " 采购人 ", " 验收入库人"},
                    pxoutjlList,
                    new String[]{"campusName", "name", "typeName", "specs", "stockNum", "outNum", "rukuafterNum",
                            "StockUnit", "outReason", "outDate", "staffName", "ystaffName"});

            try {
                ExportExcel.exportExcel(response, list, "Sheet1", "物品出库记录导出.xls", 15);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type == 2) {//入库记录信息导出
            queryWrapper.eq("type", 2);
            if (campusID != 0) {
                queryWrapper.eq("b.campusID", campusID);
            }
            List<outjiluVo> pxoutjlList = iPxteachingsuppliesouttableService.GetTeachingSuppliesOutList(queryWrapper);
            List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "名称", "类别", "规格", "出库前数量",
                            "出库数量", "出库后数量", "数量单位", "出库说明", "出库时间", "领用人", "出库经办人"},
                    pxoutjlList,
                    new String[]{"campusName", "name", "typeName", "specs", "stockNum", "outNum", "outafterNum",
                            "StockUnit", "outReason", "outDate", "staffName", "ystaffName"});

            try {
                ExportExcel.exportExcel(response, list, "Sheet1", "物品入库记录导出.xls", 15);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type == 3) {//教学物品信息导出
            if (campusID != 0) {
                queryWrapper.eq("a.campusID", campusID);
            }
            List<teachingSuppliesVo> teachingsuppliesList =
                    iPxteachingsuppliestableService.GetTeachingSuppliesList(queryWrapper);
            List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "名称 ", " 类别 ", " 规格 ", "商品条码",
                            " 库存数量 ", " 数量单位 ", "进货单价", "市场指导价（指导销售价）", " 添加时间 "},
                    teachingsuppliesList,
                    new String[]{"campusName", "name", "typeName", "specs", "changpinTiaoma", "StockNum", "StockUnit"
                            , "buyPrice", "salePrice", "addDate"});

            try {
                ExportExcel.exportExcel(response, list, "Sheet1", "物品信息导出.xls", 15);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    //region 采购申请审批

    /**
     * 采购申请审批列表（分页）
     *
     * @param size
     * @param current
     * @param campusID
     * @return
     */
    @RequestMapping(value = "/GetTeachingSuppliesbuyPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("采购申请审批列表（分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "shangpingType", value = "商品类别ID", required = false),
            @ApiImplicitParam(name = "guige", value = "规格ID", required = false),
            @ApiImplicitParam(name = "addStaffID", value = "添加人ID", required = false),
    })
    public AjaxJson GetTeachingSuppliesbuyPages(long size, long current, long campusID, String shangpingType,
                                                String shangpingName, String staffname, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Page<caigoushenqingVo> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (campusID != 0) {
            queryWrapper.eq("a.campusID", campusID);
        }
        if (StringUtils.isNotBlank(shangpingType)) {
            queryWrapper.like("b.typeName", shangpingType);
        }
        if (StringUtils.isNotBlank(shangpingName)) {
            queryWrapper.like("a.shangpingName", shangpingName);
        }
        if (StringUtils.isNotBlank(staffname)) {
            queryWrapper.eq("s.staffName", staffname);
        }

        //根据权限范围加载数据
        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", 154);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }
        queryWrapper.orderByDesc("a.addDate");
        ajaxJson.setObj(iPxteachingsuppliesbuytableService.GetTeachingSuppliesbuyPages(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * 添加/修改采购申请
     *
     * @param form
     * @return
     */
    @RequestMapping(value = "/AddTeachingSuppliesInfo", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("添加/修改采购申请")
    public AjaxJson AddTeachingSuppliesInfo(@RequestBody addcaigoushenpiForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        long staffID = loginUser.getStaffID();
        if (form.getBuyNum().compareTo(new BigDecimal(0)) <= 0) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("采购数量必须大于0");
            return ajaxJson;
        }
        String logtext = "";

        if (form.getBuyID() == 0) {
            Pxteachingsuppliesbuytable caigou = new Pxteachingsuppliesbuytable();
            caigou.setAddDate(new Date());
            caigou.setAddStaffID(staffID);
            caigou.setShangpingName(form.getName());
            caigou.setGuigeID(form.getSpecs());
            caigou.setBuyNum(form.getBuyNum());
            caigou.setDanwei(form.getStockUnit());
            caigou.setShangpingTypeID(form.getTypeId());
            caigou.setCampusID(form.getCampusID());
            caigou.setBeizhu(form.getReason());
            caigou.setQiyeID(qiyeID);
            ajaxJson.setSuccess(iPxteachingsuppliesbuytableService.save(caigou));
            logtext = "添加商品：" + form.getName() + "采购记录；";
        } else {
            Pxteachingsuppliesbuytable caigou = iPxteachingsuppliesbuytableService.getById(form.getBuyID());

            if (caigou.getIsShenhe() != 0) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("已审批过的数据不可在修改");
                return ajaxJson;
            }
            logtext = "修改商品：" + caigou.getShangpingName() + "采购记录；修改后名称：" + form.getName() + ",修改前数量：" + caigou.getBuyNum() + ",修改后：" + form.getBuyNum();
            caigou.setAddDate(new Date());
            caigou.setAddStaffID(staffID);
            caigou.setShangpingName(form.getName());
            caigou.setGuigeID(form.getSpecs());
            caigou.setBuyNum(form.getBuyNum());
            caigou.setDanwei(form.getStockUnit());
            caigou.setShangpingTypeID(form.getTypeId());
            caigou.setCampusID(form.getCampusID());
            caigou.setBeizhu(form.getReason());
            caigou.setQiyeID(qiyeID);
            ajaxJson.setSuccess(iPxteachingsuppliesbuytableService.updateById(caigou));

        }


        savePxLog.savepxlog(logtext, "xwcloud-zsbm/zsbm/JinXiaoCun/AddTeachingSuppliesInfo", loginUser.getStaffID(),
                loginUser.getStaffName(), 1,
                loginUser.getQiyeID());
        return ajaxJson;
    }

    /**
     * 采购申请审核
     *
     * @param Id
     * @param type
     * @return
     */
    @RequestMapping(value = "/caigoushenhe", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("采购申请审核")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Id", name = "审核数据ID", required = true),
            @ApiImplicitParam(value = "type", name = "审核状态，0未审核，1已审核通过，2已审核不通过", required = true),
    })
    public AjaxJson caigoushenhe(long Id, Integer type) {
        AjaxJson ajaxJson = new AjaxJson();
        Pxteachingsuppliesbuytable pxteachingsuppliesbuytable = iPxteachingsuppliesbuytableService.getById(Id);
        if (pxteachingsuppliesbuytable == null) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("未找到该记录，请重试");
            return ajaxJson;
        } else {
            if (pxteachingsuppliesbuytable.getIsShenhe() != 0) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("已审核过的记录不可二次操作！");
                return ajaxJson;
            } else {
                pxteachingsuppliesbuytable.setIsShenhe(type);
                ajaxJson.setSuccess(iPxteachingsuppliesbuytableService.updateById(pxteachingsuppliesbuytable));
            }

            return ajaxJson;
        }
    }

    /**
     * 删除采购申请
     *
     * @param Id
     * @return
     */
    @RequestMapping(value = "/delcaigou", method = RequestMethod.DELETE)
    @ApiOperation("删除采购申请")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Id", value = "删除数据ID", required = true)
    })
    public AjaxJson delcaigou(long Id) {
        AjaxJson ajaxJson = new AjaxJson();
        Pxteachingsuppliesbuytable caigou = iPxteachingsuppliesbuytableService.getById(Id);
        if (caigou == null) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("该记录不存在");
            return ajaxJson;
        }
        if (caigou.getIsShenhe() == 1) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("该申请已通过，暂不能删除");
            return ajaxJson;
        }
        ajaxJson.setSuccess(iPxteachingsuppliesbuytableService.removeById(Id));
        return ajaxJson;
    }
    //endregion
}
