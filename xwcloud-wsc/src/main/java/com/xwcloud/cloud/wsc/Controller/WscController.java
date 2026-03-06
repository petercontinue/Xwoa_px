package com.xwcloud.cloud.wsc.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.ExportExcel;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.*;
import com.xwcloud.cloud.wsc.Service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@Controller
@RequestMapping("/wsc/wsc")
@Api(tags = "微商城")
public class WscController {

    //region server注入

    @Autowired
    private IWscGoodsguigeService wscGoodsguigeService;
    @Autowired
    private IWscGoodsService wscGoodsService;
    @Autowired
    private IWscGoodsshuxinglistpricePingtuanService wscGoodsshuxinglistpricePingtuanService;
    @Autowired
    private IWscGoodsshuxinglistpriceService wscGoodsshuxinglistpriceService;
    @Autowired
    private IWscGoodsshuxinglistService wscGoodsshuxinglistService;
    @Autowired
    private IWscGoodstypeService wscGoodstypeService;
    @Autowired
    private IWscHuodongService wscHuodongService;
    @Autowired
    private IWscHuodongValueService wscHuodongValueService;
    @Autowired
    private IWscKanjiaBangkanrecordService wscKanjiaBangkanrecordService;
    @Autowired
    private IWscKanjiaFaqirecordService wscKanjiaFaqirecordService;
    @Autowired
    private IWscOrdergoodsService wscOrdergoodsService;
    @Autowired
    private IWscOrderService wscOrderService;
    @Autowired
    private IWscOrdertuifeiService wscOrdertuifeiService;
    @Autowired
    private IWscPingtuanFaqirecordService wscPingtuanFaqirecordService;
    @Autowired
    private IWscPingtuanJoinrecordService wscPingtuanJoinrecordService;
    @Autowired
    private IWscUserService wscUserService;
    @Autowired
    private IWscYongjinbiliService wscYongjinbiliService;
    @Autowired
    private IWscYongjinService wscYongjinService;
    @Autowired
    private IWscShoppingcatService wscShoppingcatService;
    @Autowired
    private IWscUserjiaoyiService wscUserjiaoyiService;

    @Autowired
    IPxstafftableService iPxstafftableService;

    @Autowired
    IWscHuodongValueService iWscHuodongValueService;
    @Autowired
    IWscHuodongOthersService iWscHuodongOthersService;
    @Autowired
    IWscPingtuanHuodongService iWscPingtuanHuodongService;
    @Autowired
    IWscKanjiaHuodonginfoService iWscKanjiaHuodonginfoService;
    @Autowired
    IWscMiaoshaHuodonginfoService iWscMiaoshaHuodonginfoService;
    @Autowired
    IWhdJizanHuodongService iWhdJizanHuodongService;
    @Autowired
    IWscUserService iWscUserService;
    @Autowired
    IWscDongtaiinfoService iWscDongtaiinfoService;
    @Autowired
    IPxpowertableService iPxpowertableService;

    @Autowired
    IWscTuikelevelService iWscTuikelevelService;
    @Autowired
    IWscTuikeBuyService iWscTuikeBuyService;
    @Autowired
    IWscTixianService iWscTixianService;


    //endregion

    @GetMapping("/GetAllAppTearcher")
    @ResponseBody
    @ApiOperation("分页获取老师")
    public AjaxJson GetAllAppTearcher(HttpServletRequest request, long size, long current, String teacherIDs) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<Pxstafftable> wrapper = new QueryWrapper<>();
        wrapper
                .eq("qiyeID", qiyeID)
                .eq("staffState", 1)
                .eq("showInApp", true);
        if (StringUtils.isNoneBlank(teacherIDs)) {
            wrapper.in("id", teacherIDs);
        }
        ajaxJson.setObj(iPxstafftableService.page(new Page<>(current, size), wrapper));
        return ajaxJson;
    }


    @GetMapping("/GetStaffInfo")
    @ResponseBody
    @ApiOperation("获取用户详情")
    public AjaxJson GetStaffInfo(HttpServletRequest request, String staffID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper Q = new QueryWrapper();
        Q.eq("a.id", staffID);
        Q.eq("a.qiyeID", qiyeID);
        ajaxJson.setObj(iPxstafftableService.GetStaffInfo(Q));
        return ajaxJson;
    }

    @GetMapping("/GetgerenStaffInfo")
    @ResponseBody
    @ApiOperation("获取个人详情（我的）")
    public AjaxJson GetgerenStaffInfo(HttpServletRequest request, String staffID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");

//        Pxstafftable one = iPxstafftableService.getById(staffID);
//        WscUser wscuser = iWscUserService.getOne(new QueryWrapper<WscUser>()
//                .eq("qiyeID", loginUser.getQiyeID())
//                .eq("phoneNumber", one.getStaffTel()));

        QueryWrapper Q = new QueryWrapper();
        Q.eq("a.qiyeID", loginUser.getQiyeID());
        Q.eq("a.id", staffID);
        ajaxJson.setObj(iPxstafftableService.GetgerenStaffInfo(Q));
        return ajaxJson;
    }


    @GetMapping("/GetTeagerenInfo")
    @ResponseBody
    @ApiOperation("获取个人详情（师资）")
    public AjaxJson GetTeagerenInfo(HttpServletRequest request, String staffID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.id", staffID);
        ajaxJson.setObj(iPxstafftableService.GetgerenStaffInfo(queryWrapper));
        return ajaxJson;
    }


    @GetMapping("/GetgerenStaffInfotoIndexpage")
    @ResponseBody
    @ApiOperation("获取个人详情（首页）")
    public AjaxJson GetgerenStaffInfotoIndexpage(HttpServletRequest request, String id) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        WscDongtaiinfo dt = iWscDongtaiinfoService.getById(id);
        QueryWrapper Q = new QueryWrapper();
        Q.eq("a.qiyeID", loginUser.getQiyeID());
        Q.eq("a.wscuserID", dt.getWscuserID());
        ajaxJson.setObj(iPxstafftableService.GetgerenStaffInfotoIndexpage(Q));
        return ajaxJson;
    }

    @GetMapping("/GetgerenDongtaiInfo")
    @ResponseBody
    @ApiOperation("获取个人动态详情")
    public AjaxJson GetgerenDongtaiInfo(HttpServletRequest request, String staffID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper Q = new QueryWrapper();
        Q.eq("b.id", staffID);
        ajaxJson.setObj(iPxstafftableService.GetgerenDongtaiInfo(Q));
        return ajaxJson;
    }

    @ResponseBody
    @RequestMapping(value = "/GetShitingStaffInfo", method = RequestMethod.GET)
    @ApiOperation(value = "查询试听课教师信息")
    public AjaxJson GetShitingStaffInfo(HttpServletRequest request, String teacherIDs) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        queryWrapper.in("a.id", teacherIDs);
        queryWrapper.eq("a.showInApp", true);
        ajaxJson.setObj(iPxstafftableService.GetStaffInfo(queryWrapper));
        return ajaxJson;
    }


    //region 商品

    /**
     * 分页查询商品
     */
    @GetMapping("/getWscGoodsPage")
    @ResponseBody
    @ApiOperation("分页查询商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "goodsname", value = "商品名"),
            @ApiImplicitParam(name = "goodstypeid", value = "商品类别ID"),
            @ApiImplicitParam(name = "huodongstarttime", value = "活动开始时间（如果开启了活动的话）"),
            @ApiImplicitParam(name = "huodongendtime", value = "活动结束时间（如果开启了活动的话）"),
            @ApiImplicitParam(name = "shangjiastate", value = "上架状态：1,上架,0未上架或已下架"),
            @ApiImplicitParam(name = "style", value = "1 课程商品，2实物商品，3其他虚拟商品"),
            @ApiImplicitParam(name = "isautoqiandan", value = "是否是自动签单。1不自动录签单，2自动签单（新签），3自动签单（续费）"),
            @ApiImplicitParam(name = "fabuto", value = "默认0：全部发布，1：只发布微信，2：只发布抖音"),
    })
    public AjaxJson getWscGoodsPage(HttpServletRequest request, long size, long current, WscGoods wscGoods,
                                    @RequestParam(required = false) String saletype, //小程序端的
                                    @RequestParam(required = false) String priceype, //小程序端的
                                    @RequestParam(required = false) String minp, //小程序端的
                                    @RequestParam(required = false) String maxp  //小程序端的
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<WscGoodsVo> wrapper = new QueryWrapper<>();
        wrapper.eq("a.qiyeID", qiyeID);
        if (!ObjectUtils.isEmpty(wscGoods.getGoodsName())) {
            wrapper.like("a.goodsName", wscGoods.getGoodsName());
        }
        if (wscGoods.getGoodsTypeID() != -1) {
            wrapper.eq("a.goodsTypeID", wscGoods.getGoodsTypeID());
        }
        if (!ObjectUtils.isEmpty(wscGoods.getHuodongStartTime())) {
            wrapper.ge("a.huodongStartTime", wscGoods.getHuodongStartTime());
        }
        if (!ObjectUtils.isEmpty(wscGoods.getHuodongEndTime())) {
            wrapper.le("a.huodongEndTime", wscGoods.getHuodongEndTime());
        }
        if (wscGoods.getShangjiaState() != -1) {
            wrapper.eq("a.shangjiaState", wscGoods.getShangjiaState());
        }
        if (!ObjectUtils.isEmpty(wscGoods.getStyle())) {
            wrapper.eq("a.style", wscGoods.getStyle());
        }
        if (!ObjectUtils.isEmpty(wscGoods.getIsAutoQiandan())) {
            wrapper.eq("a.isAutoQiandan", wscGoods.getIsAutoQiandan());
        }
        if (!ObjectUtils.isEmpty(wscGoods.getFabuTo())) {
            wrapper.eq("a.fabuTo", wscGoods.getFabuTo());
        }
        if (StringUtils.isNotBlank(saletype)) {
            if (saletype.equals("1")) {
                wrapper.orderByAsc("(SELECT (case WHEN COUNT(id)>0 THEN COUNT(id) ELSE 0 END) from wsc_ordergoods where goodsID=a.id)");
            } else if (saletype.equals("2")) {
                wrapper.orderByDesc("(SELECT (case WHEN COUNT(id)>0 THEN COUNT(id) ELSE 0 END) from wsc_ordergoods where goodsID=a.id)");
            }
        }

        if (StringUtils.isNotBlank(priceype)) {
            if (priceype.equals("1")) {
                wrapper.orderByAsc("onlyTimeBuyPrice");
            } else if (priceype.equals("2")) {
                wrapper.orderByDesc("onlyTimeBuyPrice");
            }
        }

        if (StringUtils.isNotBlank(minp)) {
            wrapper.ge("a.basicprice", minp);
        }
        if (StringUtils.isNotBlank(minp)) {
            wrapper.le("a.basicprice", maxp);
        }
        Page<WscGoodsVo> page = wscGoodsService.getWscGoodsPage(new Page<>(current, size), wrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 查询所有的商品类别信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetGoodstypeList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询所有的商品类别")
    public AjaxJson GetGoodstypeList(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        ajaxJson.setObj(wscGoodstypeService.list(queryWrapper));
        return ajaxJson;
    }

    /**
     * 查询所有活动信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetAllHuodongList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询所有活动信息")
    public AjaxJson GetAllHuodongList(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("isShow", 1);
        ajaxJson.setObj(wscHuodongService.list(queryWrapper));
        return ajaxJson;
    }

    /**
     * 添加商品
     */
    @PostMapping("/addWscGoods")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @ApiOperation("添加商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsname", value = "商品名称", required = true),
            @ApiImplicitParam(name = "goodstypeid", value = "商品类别ID", required = true),
            @ApiImplicitParam(name = "basicprice", value = "一口价（没有商品属性的时候用一口价，如果同时还开启活动，这个价就是活动的原价）"),
            @ApiImplicitParam(name = "jifenprice", value = "积分价（不设置商品属性。不参加活动的商品才能用积分），如果设置商品属性"),
            @ApiImplicitParam(name = "huodongid", value = "活动ID，0表示不参加活动，如果不为0，即对应活动表的ID，默认值0"),
            @ApiImplicitParam(name = "huodongstarttime", value = "活动开始时间（如果开启了活动的话）"),
            @ApiImplicitParam(name = "huodongendtime", value = "活动结束时间（如果开启了活动的话）"),
            @ApiImplicitParam(name = "onlytimebuyprice", value = "限时抢购价（没有设置属性的限时抢购价），如果设置了属性"),
            @ApiImplicitParam(name = "kanjiaoniceminnum", value = "砍价一刀最少砍多少"),
            @ApiImplicitParam(name = "kanjiaonicemaxnum", value = "砍价一刀最多砍多少"),
            @ApiImplicitParam(name = "kanjiasuccessprice", value = "砍价成功的价格（没有设置属性时的砍价活动的底价-即砍价成功的价格），如果设置了属性"),
            @ApiImplicitParam(name = "goodinfo", value = "商品详情"),
            @ApiImplicitParam(name = "img1", value = "banner图1，也是商品主图", required = true),
            @ApiImplicitParam(name = "img2"),
            @ApiImplicitParam(name = "img3"),
            @ApiImplicitParam(name = "img4"),
            @ApiImplicitParam(name = "img5"),
            @ApiImplicitParam(name = "adduser", value = "录入人"),
            @ApiImplicitParam(name = "addtime", value = "录入时间"),
            @ApiImplicitParam(name = "shangjiastate", value = "上架状态：1,上架,0未上架或已下架", required = true),
            @ApiImplicitParam(name = "text", value = "商品详情"),
            @ApiImplicitParam(name = "fxnum", value = "分享次数"),
            @ApiImplicitParam(name = "maxnum", value = "最大报名人数，0表示人数不限", required = true),
            @ApiImplicitParam(name = "goodsshuomingone", value = "商品说明1"),
            @ApiImplicitParam(name = "goodsshuomingoneisbold", value = "商品说明1字体是否加粗"),
            @ApiImplicitParam(name = "goodsshuomingonefontcolor", value = "商品说明1字体颜色（十六进制）"),
            @ApiImplicitParam(name = "goodsshuomingtwo", value = "商品说明2"),
            @ApiImplicitParam(name = "goodsshuomingtwoisbold", value = "商品说明2,字体是否加粗"),
            @ApiImplicitParam(name = "goodsshuomingtwofontcolor", value = "商品说明2，字体颜色（十六进制）"),
            @ApiImplicitParam(name = "cartnum", value = "加入购物车量"),
            @ApiImplicitParam(name = "campusids", value = "购买的是课程的话，可选校区ID有哪些，逗号隔开"),
            @ApiImplicitParam(name = "style", value = "1 课程商品，2实物商品，3其他虚拟商品", required = true),
            @ApiImplicitParam(name = "paixu", value = "商品排序"),
            @ApiImplicitParam(name = "qiyeid", value = "企业ID"),
            @ApiImplicitParam(name = "isautoqiandan", value = "是否是自动签单。1不自动录签单，2自动签单（新签），3自动签单（续费）"),
            @ApiImplicitParam(name = "fidfanyongbili", value = "父级返佣比例"),
            @ApiImplicitParam(name = "gfidfanyongbili", value = "祖级返佣比例"),
            @ApiImplicitParam(name = "fabuto", value = "默认0：全部发布，1：只发布微信，2：只发布抖音", required = true),
    })
    public AjaxJson addWscGoods(HttpServletRequest request, WscGoods wscGoods) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        String msg = "";
        if (ObjectUtils.isEmpty(wscGoods.getGoodsName())) {
            msg += "商品名不可为空。";
        }
        if (ObjectUtils.isEmpty(wscGoods.getGoodsTypeID())) {
            msg += "商品类别不可为空。";
        }
        if (ObjectUtils.isEmpty(wscGoods.getImg1())) {
            msg += "商品主图不可为空。";
        }
        if (ObjectUtils.isEmpty(wscGoods.getShangjiaState())) {
            msg += "上架状态不可为空。";
        }
        if (ObjectUtils.isEmpty(wscGoods.getStyle())) {
            msg += "商品风格不可为空。";
        }
        if (!ObjectUtils.isEmpty(msg)) {
            ajaxJson.setMsg(msg);
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        QueryWrapper<WscGoods> wrapper = new QueryWrapper<>();
        wrapper.eq("goodsName", wscGoods.getGoodsName());

        int count = wscGoodsService.count(wrapper);
        if (count > 0) {
            ajaxJson.setMsg("请勿重复添加。");
            return ajaxJson;
        }
        ajaxJson.setSuccess(
                wscGoods
                        .setAddUser(loginUser.getStaffID())
                        .setAddTime(LocalDateTime.now())
                        .setQiyeID(qiyeID)
                        .insert()
        );
        ajaxJson.setMsg(wscGoods.getId() + "");
        return ajaxJson;
    }

    /**
     * 根据ID更新商品
     */
    @PostMapping("/updateWscGoodsByID")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @ApiOperation("根据ID更新商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品ID", required = true),
            @ApiImplicitParam(name = "goodsname", value = "商品名称"),
            @ApiImplicitParam(name = "goodstypeid", value = "商品类别ID"),
            @ApiImplicitParam(name = "basicprice", value = "一口价（没有商品属性的时候用一口价，如果同时还开启活动，这个价就是活动的原价）"),
            @ApiImplicitParam(name = "jifenprice", value = "积分价（不设置商品属性。不参加活动的商品才能用积分），如果设置商品属性"),
            @ApiImplicitParam(name = "huodongid", value = "活动ID，0表示不参加活动，如果不为0，即对应活动表的ID，默认值0"),
            @ApiImplicitParam(name = "huodongstarttime", value = "活动开始时间（如果开启了活动的话）"),
            @ApiImplicitParam(name = "huodongendtime", value = "活动结束时间（如果开启了活动的话）"),
            @ApiImplicitParam(name = "onlytimebuyprice", value = "限时抢购价（没有设置属性的限时抢购价），如果设置了属性"),
            @ApiImplicitParam(name = "kanjiaoniceminnum", value = "砍价一刀最少砍多少"),
            @ApiImplicitParam(name = "kanjiaonicemaxnum", value = "砍价一刀最多砍多少"),
            @ApiImplicitParam(name = "kanjiasuccessprice", value = "砍价成功的价格（没有设置属性时的砍价活动的底价-即砍价成功的价格），如果设置了属性"),
            @ApiImplicitParam(name = "goodinfo", value = "商品详情"),
            @ApiImplicitParam(name = "img1", value = "banner图1，也是商品主图"),
            @ApiImplicitParam(name = "img2"),
            @ApiImplicitParam(name = "img3"),
            @ApiImplicitParam(name = "img4"),
            @ApiImplicitParam(name = "img5"),
            @ApiImplicitParam(name = "shangjiastate", value = "上架状态：1,上架,0未上架或已下架"),
            @ApiImplicitParam(name = "text", value = "商品详情"),
            @ApiImplicitParam(name = "fxnum", value = "分享次数"),
            @ApiImplicitParam(name = "maxnum", value = "最大报名人数，0表示人数不限"),
            @ApiImplicitParam(name = "goodsshuomingone", value = "商品说明1"),
            @ApiImplicitParam(name = "goodsshuomingoneisbold", value = "商品说明1字体是否加粗"),
            @ApiImplicitParam(name = "goodsshuomingonefontcolor", value = "商品说明1字体颜色（十六进制）"),
            @ApiImplicitParam(name = "goodsshuomingtwo", value = "商品说明2"),
            @ApiImplicitParam(name = "goodsshuomingtwoisbold", value = "商品说明2,字体是否加粗"),
            @ApiImplicitParam(name = "goodsshuomingtwofontcolor", value = "商品说明2，字体颜色（十六进制）"),
            @ApiImplicitParam(name = "cartnum", value = "加入购物车量"),
            @ApiImplicitParam(name = "campusids", value = "购买的是课程的话，可选校区ID有哪些，逗号隔开"),
            @ApiImplicitParam(name = "style", value = "1 课程商品，2实物商品，3其他虚拟商品"),
            @ApiImplicitParam(name = "paixu", value = "商品排序"),
            @ApiImplicitParam(name = "isautoqiandan", value = "是否是自动签单。1不自动录签单，2自动签单（新签），3自动签单（续费）"),
            @ApiImplicitParam(name = "fidfanyongbili", value = "父级返佣比例"),
            @ApiImplicitParam(name = "gfidfanyongbili", value = "祖级返佣比例"),
            @ApiImplicitParam(name = "fabuto", value = "默认0：全部发布，1：只发布微信，2：只发布抖音"),
    })
    public AjaxJson updateWscGoodsByID(HttpServletRequest request, WscGoods wscGoods) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        String msg = "";
        if (ObjectUtils.isEmpty(wscGoods.getId())) {
            msg += "粗错了。";
        }
        if (!ObjectUtils.isEmpty(msg)) {
            ajaxJson.setMsg(msg);
            return ajaxJson;
        }
        QueryWrapper<WscGoods> wrapper = new QueryWrapper<>();
        wrapper.eq("goodsName", wscGoods.getGoodsName());
        wrapper.ne("id", wscGoods.getId());
        int count = wscGoodsService.count(wrapper);
        if (count > 0) {
            ajaxJson.setMsg("请勿重复添加。");
            return ajaxJson;
        }
        ajaxJson.setSuccess(
                wscGoods
                        .setAddUser(null)
                        .setAddTime(null)
                        .setQiyeID(null)
                        .updateById()
        );
        return ajaxJson;
    }

    /**
     * 根据id删除商品
     */
    @DeleteMapping("/deleteWscGoodsByIDs")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("根据id删除商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品id", required = true),
    })
    public AjaxJson deleteWscGoodsByIDs(HttpServletRequest request, String id) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("goodsID", id);
        wscGoodsguigeService.remove(queryWrapper);
        wscGoodsshuxinglistService.remove(queryWrapper);
        wscGoodsshuxinglistpriceService.remove(queryWrapper);
        ajaxJson.setSuccess(wscGoodsService.removeById(id));
        return ajaxJson;
    }

    /**
     * 查询商品属性组合
     *
     * @param request
     * @param goodsID
     * @return
     */
    @RequestMapping(value = "/GetGoodsShuxingList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("查询商品属性组合")
    public AjaxJson GetGoodsShuxingList(HttpServletRequest request, long goodsID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        //  ajaxJson.setObj(wscGoodsguigeService.GetgoodsGuigeList(Long.parseLong(loginUser.getQiyeID()), goodsID));
        ajaxJson.setObj(wscGoodsguigeService.SearchAllGuigeList(loginUser.getQiyeID(), goodsID));
        return ajaxJson;
    }

    @RequestMapping(value = "/UpdateWscGoodsStateByID", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "修改商城商品的上架状态")
    public AjaxJson UpdateWscGoodsStateByID(HttpServletRequest request, long goodsID, int shangjiaState) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        WscGoods wscGoods = wscGoodsService.getById(goodsID);
        wscGoods.setShangjiaState(shangjiaState);
        ajaxJson.setSuccess(wscGoodsService.updateById(wscGoods));
        return ajaxJson;

    }

    /**
     * 根据商品ID查询商品详情
     *
     * @param request
     * @param goodsID
     * @return
     */
    @RequestMapping(value = "/GetGoodsInfoDetail", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "根据商品ID查询商品详情")
    public AjaxJson GetGoodsInfoDetail(HttpServletRequest request, long goodsID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setObj(wscGoodsService.getById(goodsID));
        return ajaxJson;
    }
    //endregion

    //region 商品类别

    /**
     * 分页查询商品类别
     *
     * @param size          10
     * @param current       1
     * @param goodsTypeName 商品名字（次数可模糊搜）
     */
    @GetMapping("/getWscGoodsTypePage")
    @ResponseBody
    @ApiOperation("分页查询商品类别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "goodsTypeName", value = "商品类别名称"),
            @ApiImplicitParam(name = "typeLevel", value = "商品类别级别"),
            @ApiImplicitParam(name = "isShow", value = "是否显示", example = "1"),
    })
    public AjaxJson getWscGoodsTypePage(HttpServletRequest request, long size, long current, String goodsTypeName, Integer typeLevel, Integer isShow) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        AjaxJson ajaxJson = new AjaxJson();
        Page<WscGoodstype> page = new Page<>(current, size);
        QueryWrapper<WscGoodstype> wrapper = new QueryWrapper<>();
        wrapper.eq("qiyeID", qiyeID);
        if (StringUtils.isNoneBlank(goodsTypeName)) {
            wrapper.like("goodsType", goodsTypeName);
        }
        if (typeLevel != null) {
            wrapper.eq("typeLevel", typeLevel);
        }
        if (isShow != null) {
            wrapper.eq("isShow", isShow);
        }
        page = wscGoodstypeService.page(page, wrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * @param request
     * @param goodstype
     * @return
     */
    @PostMapping("/addWscGoodsType")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("添加商品类别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodstype", value = "商品类别名", required = true),
            @ApiImplicitParam(name = "typelevel", value = "商品类别级别", required = true),
            @ApiImplicitParam(name = "fid", value = "父类别ID", required = true),
            @ApiImplicitParam(name = "isshow", value = "是否显示", example = "1", required = true),
            @ApiImplicitParam(name = "qiyeid", value = "企业ID"),
    })
    public AjaxJson addWscGoodsType(HttpServletRequest request, String goodstype) {
        AjaxJson ajaxJson = new AjaxJson();
        WscGoodstype wscGoodstype = new WscGoodstype();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        wscGoodstype.setQiyeID(qiyeID);
        wscGoodstype.setFid(0L);
        wscGoodstype.setIsshow(1);
        wscGoodstype.setGoodstype(goodstype);
        wscGoodstype.setTypelevel(1);
        int count = wscGoodstypeService.count(new QueryWrapper<>(wscGoodstype));
        if (count > 0) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("当前系统已存在相同的商品类别信息");
            return ajaxJson;
        } else {
            ajaxJson.setSuccess(wscGoodstypeService.save(wscGoodstype));
        }
        return ajaxJson;
    }

    /**
     * 根据ID更新微商城商品类别
     *
     * @param request
     * @param id
     * @param goodstype
     * @return
     */
    @PostMapping("/updateWscGoodsTypeByID")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("修改商品类别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodstype", value = "商品类别名", required = true),
            @ApiImplicitParam(name = "typelevel", value = "商品类别级别", required = true),
            @ApiImplicitParam(name = "fid", value = "父类别ID", required = true),
            @ApiImplicitParam(name = "isshow", value = "是否显示", example = "1", required = true),
            @ApiImplicitParam(name = "qiyeid", value = "企业ID"),
    })
    public AjaxJson updateWscGoodsTypeByID(HttpServletRequest request, long id, String goodstype) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        WscGoodstype wscGoodstype = wscGoodstypeService.getById(id);
        long qiyeID = loginUser.getQiyeID();
        wscGoodstype.setQiyeID(qiyeID);
        wscGoodstype.setGoodstype(goodstype);
        int count = wscGoodstypeService.count(new QueryWrapper<>(wscGoodstype));
        if (count > 0) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("当前系统已存在相同的商品类别信息");
            return ajaxJson;
        } else {
            ajaxJson.setSuccess(wscGoodstypeService.updateById(wscGoodstype));
        }
        return ajaxJson;
    }

    /**
     * 根据id删除商品类别
     */
    @DeleteMapping("/deleteWscGoodsTypeByIDs")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("根据id删除商品类别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "商品类别id", required = true),
    })
    public AjaxJson deleteWscGoodsTypeByIDs(HttpServletRequest request, long id) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("goodstypeID", id);
        if (wscGoodsService.list(queryWrapper).size() > 0) {
            ajaxJson.setMsg("准备删除的类别中有商品存在");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        } else {
            ajaxJson.setSuccess(wscGoodstypeService.removeById(id));
        }
        return ajaxJson;
    }

    //endregion

    //region 商品规格

    /**
     * 根据商品ID查询规格信息
     *
     * @param guigeTypeName 规格名字可模糊查找
     */
    @GetMapping("/getWscGoodsGuigePageByGoodsID")
    @ResponseBody
    @ApiOperation("根据商品ID查询商品规格")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "每页条目数量", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "goodsID", value = "商品ID", required = true),
            @ApiImplicitParam(name = "guigeTypeName", value = "规格名"),
    })
    public AjaxJson getWscGoodsGuigePageByGoodsID(long size, long current, String goodsID, String guigeTypeName, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<WscGoodsguige> wrapper = new QueryWrapper<>();
        wrapper
                .eq("qiyeID", qiyeID)
                .eq("id", goodsID);
        if (StringUtils.isNoneBlank(guigeTypeName)) {
            wrapper.like("guigeTypeName", guigeTypeName);
        }
        Page<WscGoodsguige> page = wscGoodsguigeService.page(new Page<>(current, size), wrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 添加商品规格
     *
     * @param wscGoodsguige 商品对象
     */
    @PostMapping("/addWscGoodsGuige")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("添加商品规格")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "guigeTypeName", value = "商品规格名称", required = true),
            @ApiImplicitParam(name = "goodsID", value = "商品ID", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson addWscGoodsGuige(HttpServletRequest request, WscGoodsguige wscGoodsguige) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        if (StringUtils.isEmpty(wscGoodsguige.getGuigetypename()) || wscGoodsguige.getGoodsid() == null) {
            String msg = "";
            if (StringUtils.isEmpty(wscGoodsguige.getGuigetypename())) {
                msg += "规格名称不可为空。";
            }
            if (wscGoodsguige.getGoodsid() == null) {
                msg += "商品ID不可为空。";
            }
            ajaxJson.setMsg(msg);
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        wscGoodsguige.setQiyeID(qiyeID);
        int count = wscGoodsguigeService.count(new QueryWrapper<>(wscGoodsguige));
        if (count > 0) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("系统存在同名规格");
            return ajaxJson;
        }
        ajaxJson.setSuccess(wscGoodsguigeService.save(wscGoodsguige));
        return ajaxJson;
    }

    /**
     * 根据ID更新商品规格
     */
    @PostMapping("/updateWscGoodsGuigeByID")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("根据ID更新商品规格")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品规格ID", required = true),
            @ApiImplicitParam(name = "guigeTypeName", value = "商品规格名称", required = true),
            @ApiImplicitParam(name = "goodsID", value = "商品ID", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
    })
    public AjaxJson updateWscGoodsGuigeByID(HttpServletRequest request, WscGoodsguige wscGoodsguige) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        if (ObjectUtils.isEmpty(wscGoodsguige.getGuigetypename()) && ObjectUtils.isEmpty(wscGoodsguige.getGoodsid())) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("请至少修改一项。");
            return ajaxJson;
        }
        wscGoodsguige.setQiyeID(qiyeID);
        //region 判断重复
        QueryWrapper<WscGoodsguige> wrapper = new QueryWrapper<>();
        wrapper
                .ne("id", wscGoodsguige.getId())
                .eq("qiyeID", qiyeID);
        if (!ObjectUtils.isEmpty(wscGoodsguige.getGoodsid()) && !ObjectUtils.isEmpty(wscGoodsguige.getGuigetypename())) {
            //名字商品ID都修改的情况下
            wrapper
                    .eq("guigeTypeName", wscGoodsguige.getGuigetypename())
                    .eq("goodsID", wscGoodsguige.getGoodsid());
        } else {
            //只修改其中之一的情况下
            WscGoodsguige wscGoodsById = wscGoodsguigeService.getById(wscGoodsguige.getId());
            if (!ObjectUtils.isEmpty(wscGoodsguige.getGoodsid())) {
                //改商品ID
                wrapper
                        .eq("guigeTypeName", wscGoodsById.getGuigetypename())
                        .eq("goodsID", wscGoodsguige.getGoodsid());
            } else {
                //改商品名字
                wrapper
                        .eq("guigeTypeName", wscGoodsguige.getGuigetypename())
                        .eq("goodsID", wscGoodsById.getGoodsid());
            }
        }
        //endregion
        int count = wscGoodsguigeService.count(wrapper);
        if (count > 0) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("系统存在同名规格");
            return ajaxJson;
        }
        ajaxJson.setSuccess(wscGoodsguigeService.updateById(wscGoodsguige));
        return ajaxJson;
    }

    /**
     * 根据ID删除商品规格
     */
    @DeleteMapping("/deleteWscGoodsGuigeByIDs")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("根据ID删除商品规格")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "规格ID", required = true),
    })
    public AjaxJson deleteWscGoodsGuigeByIDs(String id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(wscGoodsguigeService.removeById(id));
        return ajaxJson;
    }

    //endregion

    //region 商品属性列表

    /**
     * 查询所有规格信息（下拉）
     *
     * @param request
     * @param goodsID
     * @return
     */
    @RequestMapping(value = "/GetAllGuigeDataList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询所有规格信息（下拉）")
    public AjaxJson GetAllGuigeDataList(HttpServletRequest request, long goodsID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("goodsID", goodsID);
        ajaxJson.setObj(wscGoodsguigeService.list(queryWrapper));
        return ajaxJson;
    }

    /**
     * 查询商品的属性列表
     */
    @GetMapping("/getGoodsAttributePage")
    @ResponseBody
    @ApiOperation("查询商品的属性列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码数", example = "1", required = true),
            @ApiImplicitParam(name = "attributeName", value = "属性名", example = "1", required = true)
    })
    public AjaxJson getGoodsAttributePage(long size, long current, String attributeName, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<WscGoodsAttributeVo> wrapper = new QueryWrapper<>();
        wrapper.eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotEmpty(attributeName)) {
            wrapper.like("shuxingMing", attributeName);
        }
        Page<WscGoodsAttributeVo> page = wscGoodsshuxinglistService.getGoodsAttributePage(new Page<>(current, size), wrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 添加商品属性列表
     */
    @PostMapping("/addGoodsAttributeList")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("添加商品属性列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsguigetypeid", value = "商品规格id", required = true),
            @ApiImplicitParam(name = "shuxingMing", value = "商品属性名", required = true),
            @ApiImplicitParam(name = "shuxingPaixu", value = "商品属性排序", required = true),
            @ApiImplicitParam(name = "isneedchangimg", value = "商品是否需要更图片，1不需要，2需要更换", example = "1", required = true),
            @ApiImplicitParam(name = "addStaffID", value = "添加人ID"),
            @ApiImplicitParam(name = "addTime", value = "添加人时间"),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
    })
    public AjaxJson addGoodsAttributeList(HttpServletRequest request, WscGoodsshuxinglist wscGoodsshuxinglist) {
        AjaxJson ajaxJson = new AjaxJson();
        String msg = "";
        if (
                wscGoodsshuxinglist.getGoodsguigetypeid() == null ||
                        StringUtils.isEmpty(wscGoodsshuxinglist.getShuxingming()) ||
                        wscGoodsshuxinglist.getIsneedchangimg() == null ||
                        wscGoodsshuxinglist.getShuxingpaixu() == null
        ) {
            if (wscGoodsshuxinglist.getGoodsguigetypeid() == null) {
                msg += "商品规格不可为空。";
            }
            if (StringUtils.isEmpty(wscGoodsshuxinglist.getShuxingming())) {
                msg += "商品属性名不可为空。";
            }
            if (wscGoodsshuxinglist.getIsneedchangimg() == null) {
                msg += "是否需要图片不可为空。";
            }
            if (wscGoodsshuxinglist.getShuxingpaixu() == null) {
                msg += "商品属性排序不可为空。";
            }
            ajaxJson.setMsg(msg);
            return ajaxJson;
        }
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        wscGoodsshuxinglist.setQiyeID(qiyeID);
        int count = wscGoodsshuxinglistService.count(new QueryWrapper<>(wscGoodsshuxinglist));
        if (count > 0) {
            ajaxJson.setMsg("请勿重复添加");
            return ajaxJson;
        }
        wscGoodsshuxinglist.setAddstaffid(staffID);
        wscGoodsshuxinglist.setAddtime(LocalDateTime.now());
        ajaxJson.setSuccess(wscGoodsshuxinglistService.save(wscGoodsshuxinglist));
        return ajaxJson;
    }

    /**
     * 根据ID更新商品属性列表
     */
    @PostMapping("/updateWscGoodsAttributeListByID")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("根据ID更新商品属性列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsguigetypeid", value = "商品规格id", required = true),
            @ApiImplicitParam(name = "shuxingMing", value = "商品属性名", required = true),
            @ApiImplicitParam(name = "shuxingPaixu", value = "商品属性排序", required = true),
            @ApiImplicitParam(name = "isneedchangimg", value = "商品是否需要更图片，1不需要，2需要更换", example = "1", required = true),
            @ApiImplicitParam(name = "addStaffID", value = "添加人ID"),
            @ApiImplicitParam(name = "addTime", value = "添加人时间"),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
    })
    public AjaxJson updateWscGoodsAttributeListByID(HttpServletRequest request, WscGoodsshuxinglist wscGoodsshuxinglist) {
        AjaxJson ajaxJson = new AjaxJson();
        String msg = "";
        if (
                wscGoodsshuxinglist.getGoodsguigetypeid() == null &&
                        StringUtils.isEmpty(wscGoodsshuxinglist.getShuxingming()) &&
                        wscGoodsshuxinglist.getIsneedchangimg() == null &&
                        wscGoodsshuxinglist.getShuxingpaixu() == null
        ) {
            ajaxJson.setMsg("请至少修改一项");
            return ajaxJson;
        }
//        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
//        String qiyeID = loginUser.getQiyeID();
//        wscGoodsshuxinglist.setQiyeid(Long.parseLong(qiyeID));
//        QueryWrapper<WscGoodsshuxinglist> wrapper = new QueryWrapper<>();
//        wrapper
//                .ne("id", wscGoodsshuxinglist.getId())
//                .eq("qiyeID", qiyeID);
//        int count = wscGoodsshuxinglistService.count(new QueryWrapper<>(wscGoodsshuxinglist));
//        if (count > 0){
//            ajaxJson.setMsg("系统已存在相同记录");
//            return  ajaxJson;
//        }
        ajaxJson.setSuccess(wscGoodsshuxinglistService.updateById(wscGoodsshuxinglist));
        return ajaxJson;
    }

    /**
     * 根据ID删除商品属性列表
     */
    @DeleteMapping("/deleteWscGoodsAttributeListByIDs")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("根据ID删除商品属性列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品属性id", required = true),
    })
    public AjaxJson deleteWscGoodsAttributeListByIDs(String id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(wscGoodsshuxinglistService.removeById(id));
        return ajaxJson;
    }

    //endregion

    //region 商品价格属性列表

    /**
     * 分页查询商品价格属性列表
     */
    @GetMapping("/getWscGoodsAttrPriceListPage")
    @ResponseBody
    @ApiOperation("分页查询商品价格属性列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "goodsID", value = "商品ID", required = true),
    })
    public AjaxJson getWscGoodsAttrPriceListPage(HttpServletRequest request, long size, long current, long goodsID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<WscGoodsshuxinglistprice> wrapper = new QueryWrapper<>();
        wrapper.eq("qiyeID", qiyeID);
        wrapper.eq("goodsID", goodsID);
        Page<WscGoodsshuxinglistprice> page = wscGoodsshuxinglistpriceService.page(new Page<>(current, size), wrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 添加商品价格属性列表
     */
    @PostMapping("/addWscGoodsAttrPriceList")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("添加商品价格属性列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsid", value = "商品id", required = true),
            @ApiImplicitParam(name = "goodsshuxinglistall", value = "商品的属性组合", required = true),
            @ApiImplicitParam(name = "originalprice", value = "商品原价(设置了属性的商品原价)", required = true),
            @ApiImplicitParam(name = "price", value = "普通售价（没有活动时的售价）", required = true),
            @ApiImplicitParam(name = "jifenprice", value = "积分价（设置商品属性。不参加活动的商品才能用积分）", required = true),
            @ApiImplicitParam(name = "onlytimebuyprice", value = "限时抢购价", required = true),
            @ApiImplicitParam(name = "kanjiasuccessprice", value = "砍价成功的价格", required = true)
    })
    public AjaxJson addWscGoodsAttrPriceList(HttpServletRequest request, long goodsid, String shuxing, BigDecimal originalprice,
                                             BigDecimal price, BigDecimal jifenprice, BigDecimal onlytimebuyprice, BigDecimal kanjiasuccessprice) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        String shuxingzuhe = "";
        for (String item : shuxing.split(",")) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("goodsID", goodsid);
            queryWrapper.eq("qiyeID", loginUser.getQiyeID());
            queryWrapper.eq("shuxingMing", item);
            shuxingzuhe += wscGoodsshuxinglistService.getOne(queryWrapper).getId() + ",";
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("goodsID", goodsid);
        queryWrapper.eq("goodsShuxingListAll", shuxingzuhe);
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        if (wscGoodsshuxinglistpriceService.list(queryWrapper).size() > 0) {
            WscGoodsshuxinglistprice wscGoodsshuxinglistprice = wscGoodsshuxinglistpriceService.getOne(queryWrapper);
            wscGoodsshuxinglistprice.setGoodsShuxingListAll(shuxingzuhe);
            wscGoodsshuxinglistprice.setGoodsID(goodsid);
            wscGoodsshuxinglistprice.setPrice(price);
            wscGoodsshuxinglistprice.setQiyeID(loginUser.getQiyeID());
            wscGoodsshuxinglistprice.setJifenPrice(jifenprice);
            wscGoodsshuxinglistprice.setKanjiaSuccessPrice(kanjiasuccessprice);
            wscGoodsshuxinglistprice.setOnlyTimeBuyPrice(onlytimebuyprice);
            wscGoodsshuxinglistprice.setOriginalPrice(originalprice);
            ajaxJson.setSuccess(wscGoodsshuxinglistpriceService.updateById(wscGoodsshuxinglistprice));
            return ajaxJson;
        } else {
            WscGoodsshuxinglistprice wscGoodsshuxinglistprice = new WscGoodsshuxinglistprice();
            wscGoodsshuxinglistprice.setGoodsShuxingListAll(shuxingzuhe);
            wscGoodsshuxinglistprice.setGoodsID(goodsid);
            wscGoodsshuxinglistprice.setPrice(price);
            wscGoodsshuxinglistprice.setQiyeID(loginUser.getQiyeID());
            wscGoodsshuxinglistprice.setJifenPrice(jifenprice);
            wscGoodsshuxinglistprice.setKanjiaSuccessPrice(kanjiasuccessprice);
            wscGoodsshuxinglistprice.setOnlyTimeBuyPrice(onlytimebuyprice);
            wscGoodsshuxinglistprice.setOriginalPrice(originalprice);
            ajaxJson.setSuccess(wscGoodsshuxinglistpriceService.save(wscGoodsshuxinglistprice));
            return ajaxJson;
        }
    }

    @RequestMapping(value = "/GetShuxingzuhePrice", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询商品属性组合的价格信息")
    public AjaxJson GetShuxingzuhePrice(HttpServletRequest request, String shuxing, long goodsid) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        String shuxingzuhe = "";
        for (String item : shuxing.split(",")) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("goodsID", goodsid);
            queryWrapper.eq("qiyeID", loginUser.getQiyeID());
            queryWrapper.eq("shuxingMing", item);
            shuxingzuhe += wscGoodsshuxinglistService.getOne(queryWrapper).getId() + ",";
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("goodsID", goodsid);
        queryWrapper.eq("goodsShuxingListAll", shuxingzuhe);
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        WscGoodsshuxinglistprice wscGoodsshuxinglistprice = wscGoodsshuxinglistpriceService.getOne(queryWrapper);
        if (wscGoodsshuxinglistprice != null) {
            ajaxJson.setObj(wscGoodsshuxinglistprice);
        } else {
            ajaxJson.setCode("N");
        }
        return ajaxJson;
    }


    @RequestMapping(value = "/GetAllShuxingValueList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取商品属性价格")
    public AjaxJson GetAllShuxingValueList(HttpServletRequest request, long goodsID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("goodsID", goodsID);
        queryWrapper.eq("qiyeID", qiyeID);
        List<WscGoodsguige> wscGoodsguiges = wscGoodsguigeService.list(queryWrapper);
        List<List<String>> arry1 = new ArrayList<>();
        for (WscGoodsguige item : wscGoodsguiges) {
            QueryWrapper queryWrapper1 = new QueryWrapper();
            queryWrapper1.eq("goodsID", goodsID);
            queryWrapper1.eq("qiyeID", qiyeID);
            queryWrapper1.eq("goodsguigetypeid", item.getId());
            List<WscGoodsshuxinglist> wscGoodsshuxinglists = wscGoodsshuxinglistService.list(queryWrapper1);
            List<String> shuxing = new ArrayList<>();
            for (WscGoodsshuxinglist item1 : wscGoodsshuxinglists) {
                shuxing.add(item1.getShuxingming());
            }
            arry1.add(shuxing);
        }
        ajaxJson.setObj(arry1);
        return ajaxJson;
    }

    /**
     * 根据id修改商品价格属性列表
     */
    @PostMapping("/updateWscGoodsAttrPriceList")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("根据id修改商品价格属性列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsid", value = "商品id", required = true),
            @ApiImplicitParam(name = "goodsshuxinglistall", value = "商品的属性组合", required = true),
            @ApiImplicitParam(name = "originalprice", value = "商品原价(设置了属性的商品原价)", required = true),
            @ApiImplicitParam(name = "price", value = "普通售价（没有活动时的售价）", required = true),
            @ApiImplicitParam(name = "jifenprice", value = "积分价（设置商品属性。不参加活动的商品才能用积分）", required = true),
            @ApiImplicitParam(name = "onlytimebuyprice", value = "限时抢购价", required = true),
            @ApiImplicitParam(name = "kanjiasuccessprice", value = "砍价成功的价格", required = true),
            @ApiImplicitParam(name = "qiyeid", value = "企业ID", required = true),
    })
    public AjaxJson updateWscGoodsAttrPriceList(HttpServletRequest request, WscGoodsshuxinglistprice wscGoodsshuxinglistprice) {
        AjaxJson ajaxJson = new AjaxJson();
        if (wscGoodsshuxinglistprice.getGoodsID() == null || StringUtils.isEmpty(wscGoodsshuxinglistprice.getGoodsShuxingListAll())) {
            String msg = "";
            if (wscGoodsshuxinglistprice.getGoodsID() == null) {
                msg += "商品ID不可为空。";
            }
            if (StringUtils.isEmpty(wscGoodsshuxinglistprice.getGoodsShuxingListAll())) {
                msg += "商品属性组合不可为空。";
            }
            ajaxJson.setCode("N");
            ajaxJson.setMsg(msg);
            return ajaxJson;
        }
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<WscGoodsshuxinglistprice> wrapper = new QueryWrapper<>();
        if (!ObjectUtils.isEmpty(wscGoodsshuxinglistprice.getGoodsID())) {
            wrapper
                    .ne("id", wscGoodsshuxinglistprice.getId())
                    .eq("goodsID", wscGoodsshuxinglistprice.getGoodsID())
                    .eq("qiyeID", qiyeID);
            Integer count = wscGoodsshuxinglistprice.selectCount(wrapper);
            if (count > 0) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("系统已存在相同记录");
                return ajaxJson;
            }
        }
        ajaxJson.setSuccess(
                wscGoodsshuxinglistprice
                        .setQiyeID(qiyeID)
                        .updateById()
        );

        return ajaxJson;
    }

    /**
     * 根据id批量删除商品价格属性列表
     * 请求方式：/path/1,2,3
     */
    @DeleteMapping("/deleteWscGoodsAttrPriceList/{ids}")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("根据id批量删除商品价格属性列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "商品类别ids", required = true),
    })
    public AjaxJson deleteWscGoodsAttrPriceList(@PathVariable List<String> ids) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(wscGoodsshuxinglistpriceService.removeByIds(ids));
        return ajaxJson;
    }


    //endregion

    //region 商品价格属性列表->拼团

    /**
     * 根据商品价格属性列表ID，分页查询商品价格属性列表->拼团
     */
    @GetMapping("/getWscGoodsAttrListPricePTPage")
    @ResponseBody
    @ApiOperation("根据商品价格属性列表ID，分页查询商品价格属性列表->拼团")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "goodsShuxingListPriceID", value = "商品价格属性列表ID", required = true),
    })
    public AjaxJson getWscGoodsAttrListPricePTPage(long size, long current, String goodsShuxingListPriceID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<WscGoodsshuxinglistpricePingtuan> wrapper = new QueryWrapper<>();
        wrapper
                .eq("qiyeID", qiyeID)
                .eq("goodsShuxingListPriceID", goodsShuxingListPriceID);
        ajaxJson.setObj(wscGoodsshuxinglistpricePingtuanService.page(new Page<>(current, size), wrapper));
        return ajaxJson;
    }

    /**
     * 添加商品价格属性列表->拼团
     */
    @PostMapping("/addWscGoodsAttrListPricePT")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("添加商品价格属性列表->拼团")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsshuxinglistpriceid", value = "商品价格属性列表ID", required = true),
            @ApiImplicitParam(name = "pingtuanrenshu", value = "拼团价格，即达到多少人是什么价", required = true),
            @ApiImplicitParam(name = "pingtuanprice", value = "拼团人数，即达到多少人是什么价", required = true),
            @ApiImplicitParam(name = "shuoming", value = "说明"),
            @ApiImplicitParam(name = "qiyeid", value = "企业ID"),
    })
    public AjaxJson addWscGoodsAttrListPricePT(HttpServletRequest request, WscGoodsshuxinglistpricePingtuan wscGoodsshuxinglistpricePingtuan) {
        AjaxJson ajaxJson = new AjaxJson();
        if (
                wscGoodsshuxinglistpricePingtuan.getGoodsShuxingListPriceID() == null ||
                        StringUtils.isEmpty(wscGoodsshuxinglistpricePingtuan.getPingtuanRenshu()) ||
                        wscGoodsshuxinglistpricePingtuan.getPingtuanPrice() == null
        ) {
            String msg = "";
            if (wscGoodsshuxinglistpricePingtuan.getGoodsShuxingListPriceID() == null) {
                msg += "商品价格属性列表ID不可为空。";
            }
            if (StringUtils.isEmpty(wscGoodsshuxinglistpricePingtuan.getPingtuanRenshu())) {
                msg += "拼团人数不可为空。";
            }
            if (wscGoodsshuxinglistpricePingtuan.getPingtuanPrice() == null) {
                msg += "拼团价格不可为空。";
            }
            ajaxJson.setCode("N");
            ajaxJson.setMsg(msg);
            return ajaxJson;
        }
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        wscGoodsshuxinglistpricePingtuan.setQiyeID(qiyeID);
        //排除说明
        String shuoming = wscGoodsshuxinglistpricePingtuan.getShuoming();
        wscGoodsshuxinglistpricePingtuan.setShuoming(null);
        int count = wscGoodsshuxinglistpricePingtuanService.count(new QueryWrapper<>(wscGoodsshuxinglistpricePingtuan));
        if (count > 0) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("请勿重复添加");
            return ajaxJson;
        }
        wscGoodsshuxinglistpricePingtuan.setShuoming(shuoming);
        ajaxJson.setSuccess(wscGoodsshuxinglistpricePingtuanService.save(wscGoodsshuxinglistpricePingtuan));
        return ajaxJson;
    }

    /**
     * 根据ID更新商品价格属性列表->拼团
     */
    @PostMapping("/updateWscGoodsAttrListPricePTByID")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("根据ID更新商品价格属性列表->拼团")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsshuxinglistpriceid", value = "商品价格属性列表ID", required = true),
            @ApiImplicitParam(name = "pingtuanrenshu", value = "拼团价格，即达到多少人是什么价", required = true),
            @ApiImplicitParam(name = "pingtuanprice", value = "拼团人数，即达到多少人是什么价", required = true),
            @ApiImplicitParam(name = "shuoming", value = "说明"),
            @ApiImplicitParam(name = "qiyeid", value = "企业ID"),
    })
    public AjaxJson updateWscGoodsAttrListPricePTByID(HttpServletRequest request, WscGoodsshuxinglistpricePingtuan wscGoodsshuxinglistpricePingtuan) {
        AjaxJson ajaxJson = new AjaxJson();
        if (
                wscGoodsshuxinglistpricePingtuan.getGoodsShuxingListPriceID() == null ||
                        StringUtils.isEmpty(wscGoodsshuxinglistpricePingtuan.getPingtuanRenshu()) ||
                        wscGoodsshuxinglistpricePingtuan.getPingtuanPrice() == null
        ) {
            String msg = "";
            if (wscGoodsshuxinglistpricePingtuan.getGoodsShuxingListPriceID() == null) {
                msg += "商品价格属性列表ID不可为空。";
            }
            if (StringUtils.isEmpty(wscGoodsshuxinglistpricePingtuan.getPingtuanRenshu())) {
                msg += "拼团人数不可为空。";
            }
            if (wscGoodsshuxinglistpricePingtuan.getPingtuanPrice() == null) {
                msg += "拼团价格不可为空。";
            }
            ajaxJson.setCode("N");
            ajaxJson.setMsg(msg);
            return ajaxJson;
        }
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        wscGoodsshuxinglistpricePingtuan.setQiyeID(qiyeID);
        //排除说明
        String shuoming = wscGoodsshuxinglistpricePingtuan.getShuoming();
        wscGoodsshuxinglistpricePingtuan.setShuoming(null);
        int count = wscGoodsshuxinglistpricePingtuanService.count(new QueryWrapper<>(wscGoodsshuxinglistpricePingtuan));
        if (count > 0) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("已存在相同数据");
            return ajaxJson;
        }
        wscGoodsshuxinglistpricePingtuan.setShuoming(shuoming);
        ajaxJson.setSuccess(wscGoodsshuxinglistpricePingtuanService.updateById(wscGoodsshuxinglistpricePingtuan));
        return ajaxJson;
    }

    /**
     * 根据ID删除商品价格属性列表->拼团
     * 请求方式：/path/1,2,3
     */
    @DeleteMapping("/deleteWscGoodsAttrListPricePTByIDs/{ids}")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("根据ID删除商品价格属性列表->拼团")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "商品属性列表ids", required = true),
    })
    public AjaxJson deleteWscGoodsAttrListPricePTByIDs(@PathVariable List<String> ids) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(wscGoodsshuxinglistpricePingtuanService.removeByIds(ids));
        return ajaxJson;
    }

    //endregion

    //region 活动

    /**
     * 分页查询活动
     */
    @GetMapping("/getWscHuodongPage")
    @ResponseBody
    @ApiOperation("分页查询活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "一页条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "huodongName", value = "活动名"),
            @ApiImplicitParam(name = "isShow", value = "是否显示"),
    })
    public AjaxJson getWscHuodongPage(long size, long current, String huodongName, Integer isShow) {
        AjaxJson ajaxJson = new AjaxJson();
//        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
//        String qiyeID = loginUser.getQiyeID();
        QueryWrapper<WscHuodong> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(huodongName)) {
            wrapper.like("huodongName", huodongName);
        }
        if (isShow != null) {
            wrapper.eq("isShow", isShow);
        }
        Page<WscHuodong> page = wscHuodongService.page(new Page<>(current, size), wrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 添加微商城活动
     */
    @PostMapping("/addWscHuodong")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("添加微商城活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "huodongname", value = "活动名", required = true),
            @ApiImplicitParam(name = "isshow", value = "是否显示 1显示，0不显示", example = "1", required = true),
    })
    public AjaxJson addWscHuodong(WscHuodong wscHuodong) {
        AjaxJson ajaxJson = new AjaxJson();
//        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
//        String qiyeID = loginUser.getQiyeID();
        if (StringUtils.isEmpty(wscHuodong.getHuodongName())) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("活动名称不可为空");
            return ajaxJson;
        }
        if (wscHuodong.getIsShow() == null) {
            wscHuodong.setIsShow(1);
        }
        QueryWrapper<WscHuodong> wrapper = new QueryWrapper<>();
        wrapper.eq("huodongName", wscHuodong.getHuodongName());
        Integer count = wscHuodong.selectCount(wrapper);
        if (count > 0) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("请勿重复添加");
            return ajaxJson;
        }
        ajaxJson.setSuccess(wscHuodong.insert());
        return ajaxJson;
    }

    /**
     * 修改微商城活动
     */
    @PostMapping("/updateWscHuodongByID")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("修改微商城活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "huodongname", value = "活动名", required = true),
            @ApiImplicitParam(name = "isshow", value = "是否显示 1显示，0不显示", example = "1", required = true),
    })
    public AjaxJson updateWscHuodongByID(WscHuodong wscHuodong) {
        AjaxJson ajaxJson = new AjaxJson();
        if (StringUtils.isEmpty(wscHuodong.getHuodongName())) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("活动名称不可为空");
            return ajaxJson;
        }
        if (wscHuodong.getIsShow() == null) {
            wscHuodong.setIsShow(1);
        }
        if (!ObjectUtils.isEmpty(wscHuodong.getHuodongName())) {
            QueryWrapper<WscHuodong> wrapper = new QueryWrapper<>();
            wrapper.eq("huodongName", wscHuodong.getHuodongName());
            wrapper.ne("id", wscHuodong.getId());
            Integer count = wscHuodong.selectCount(wrapper);
            if (count > 0) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("已存在同名活动");
                return ajaxJson;
            }
        }
        ajaxJson.setSuccess(wscHuodong.updateById());
        return ajaxJson;
    }

    /**
     * 根据id批量删除微商城活动
     * 请求方式：/path/1,2,3
     */
    @DeleteMapping("/deleteWscHuodongByIDs/{ids}")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("根据id批量删除微商城活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "活动ids", required = true),
    })
    public AjaxJson deleteWscHuodongByIDs(@PathVariable List<String> ids) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(wscHuodongService.removeByIds(ids));
        return ajaxJson;
    }

    //endregion

    //region 企业活动

    /**
     * 分页查询企业活动
     */
    @GetMapping("/getWscHuodongValPage")
    @ResponseBody
    @ApiOperation("分页查询企业活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "isShow", value = "是否显示", example = "1"),
    })
    public AjaxJson getWscHuodongValPage(HttpServletRequest request, long size, long current, Integer isShow) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<WscHuodongValueVo> wrapper = new QueryWrapper<>();
        wrapper.eq("a.qiyeID", qiyeID);
        if (isShow != null) {
            wrapper.eq("b.isShow", isShow);
        }
        wrapper.orderByAsc("a.paixu");
        Page<WscHuodongValueVo> page = wscHuodongValueService.getWscHuodongValPage(new Page<>(current, size), wrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 添加企业活动
     */
    @PostMapping("/addWscHuodongVal")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("添加企业活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "huodongid", value = "活动ID", required = true),
            @ApiImplicitParam(name = "paixu", value = "排序"),
            @ApiImplicitParam(name = "isshow", value = "是否显示 1显示0否", example = "1"),
            @ApiImplicitParam(name = "qiyeid", value = "企业ID"),
    })
    public AjaxJson addWscHuodongVal(HttpServletRequest request,
                                     int huodongID,
                                     int isShow, String huodongtitle,
                                     String huodongImg,
                                     String huodongshuoming,
                                     String startDatetime,
                                     String endDatetime) throws ParseException {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        WscHuodongValue wscHuodongValue = new WscHuodongValue();
        wscHuodongValue.setQiyeID(qiyeID);
        wscHuodongValue.setHuodongID(Long.valueOf(huodongID));
        wscHuodongValue.setIsShow(isShow);

        if (wscHuodongValue.getIsShow() == null) {
            wscHuodongValue.setIsShow(1);
        }
        if (wscHuodongValue.getHuodongID() == null) {
            ajaxJson.setMsg("活动ID不可为空");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("huodongid", wscHuodongValue.getHuodongID());
        if (wscHuodongValueService.list(queryWrapper).size() > 0) {
            ajaxJson.setMsg("已添加该活动");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        iWscHuodongValueService.save(wscHuodongValue);
        if (huodongID == 8 || huodongID == 9 || huodongID == 10 || huodongID == 11 || huodongID == 12 || huodongID == 13) {
            WscHuodongOthers wscHuodongOthers = new WscHuodongOthers();
            wscHuodongOthers.setHuodongID(huodongID);
            wscHuodongOthers.setHuodongImg(huodongImg);
            wscHuodongOthers.setHuodongState(1);
            wscHuodongOthers.setHuodongtitle(huodongtitle);
            wscHuodongOthers.setHuodongshuoming(huodongshuoming);
            wscHuodongOthers.setEndDatetime(ft.parse(endDatetime));
            wscHuodongOthers.setFenxiangtimes(0);
            wscHuodongOthers.setLiulantimes(0);
            wscHuodongOthers.setStartDatetime(ft.parse(startDatetime));
            wscHuodongOthers.setQiyeID(Long.valueOf(loginUser.getQiyeID()));
            iWscHuodongOthersService.save(wscHuodongOthers);
        } else if (huodongID == 1) {
            //拼团
            WscPingtuanHuodong wscPingtuanHuodong = new WscPingtuanHuodong();
            wscPingtuanHuodong.setHuodongImg(huodongImg);
            wscPingtuanHuodong.setHuodongTitle(huodongtitle);
            wscPingtuanHuodong.setHuodongshuoming(huodongshuoming);
            wscPingtuanHuodong.setFenxiangTimes(0);
            wscPingtuanHuodong.setLiulangTimes(0);
            wscPingtuanHuodong.setQiyeID(Long.valueOf(loginUser.getQiyeID()));
            iWscPingtuanHuodongService.save(wscPingtuanHuodong);
        } else if (huodongID == 2) {
            //砍价
            WscKanjiaHuodonginfo wscKanjiaHuodonginfo = new WscKanjiaHuodonginfo();
            wscKanjiaHuodonginfo.setHuodongImg(huodongImg);
            wscKanjiaHuodonginfo.setHuodongTitle(huodongtitle);
            wscKanjiaHuodonginfo.setHuodongshuoming(huodongshuoming);
            wscKanjiaHuodonginfo.setLiulanTimes(0);
            wscKanjiaHuodonginfo.setFenxiangTimes(0);
            wscKanjiaHuodonginfo.setQiyeID(Long.valueOf(loginUser.getQiyeID()));
            iWscKanjiaHuodonginfoService.save(wscKanjiaHuodonginfo);
        } else if (huodongID == 3) {
            //秒杀
            WscMiaoshaHuodonginfo wscMiaoshaHuodonginfo = new WscMiaoshaHuodonginfo();
            wscMiaoshaHuodonginfo.setHuodongImg(huodongImg);
            wscMiaoshaHuodonginfo.setHuodongtitle(huodongtitle);
            wscMiaoshaHuodonginfo.setHuodongshuoming(huodongshuoming);
            wscMiaoshaHuodonginfo.setLiulanTimes(0);
            wscMiaoshaHuodonginfo.setFenxiangTimes(0);
            wscMiaoshaHuodonginfo.setQiyeID(Long.valueOf(loginUser.getQiyeID()));
            iWscMiaoshaHuodonginfoService.save(wscMiaoshaHuodonginfo);
        } else if (huodongID == 7) {
            //集赞
            WhdJizanHuodong whdJizanHuodong = new WhdJizanHuodong();
            whdJizanHuodong.setJizanHuodongName(huodongtitle);
            whdJizanHuodong.setJizanShuoming(huodongshuoming);
            whdJizanHuodong.setLiulantimes(0);
            whdJizanHuodong.setFenxiangtimes(0);
            whdJizanHuodong.setAddUser(loginUser.getStaffID() + "");
            whdJizanHuodong.setQiyeID(Long.valueOf(loginUser.getQiyeID()));
            whdJizanHuodong.setStartTime(ft.parse(startDatetime));
            whdJizanHuodong.setEndTime(ft.parse(endDatetime));
            whdJizanHuodong.setJizanLogoUrl(huodongImg);
            whdJizanHuodong.setAddTime(new Date());
            whdJizanHuodong.setIsOpen(1);
            iWhdJizanHuodongService.save(whdJizanHuodong);
        }
        ajaxJson.setSuccess(true);
        return ajaxJson;
    }

    /**
     * 根据ID修改企业活动
     */
    @PostMapping("/updateWscHuodongValByID")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("根据ID修改企业活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "huodongid", value = "活动ID", required = true),
            @ApiImplicitParam(name = "paixu", value = "排序"),
            @ApiImplicitParam(name = "isshow", value = "是否显示 1显示0否", example = "1"),
            @ApiImplicitParam(name = "qiyeid", value = "企业ID"),
    })
    public AjaxJson updateWscHuodongValByID(HttpServletRequest request, long id, int huodongid,
                                            int isshow, String huodongtitle,
                                            String huodongImg,
                                            String huodongshuoming,
                                            String startDatetime,
                                            String endDatetime) throws ParseException {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        WscHuodongValue wscHuodongValue = iWscHuodongValueService.getById(id);
        if (wscHuodongValue == null) {
            ajaxJson.setMsg("未找到该活动信息，请先添加活动");
            return ajaxJson;
        }
        wscHuodongValue.setHuodongID(Long.valueOf(huodongid));
        wscHuodongValue.setQiyeID(Long.valueOf(loginUser.getQiyeID()));
        wscHuodongValue.setIsShow(isshow);
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        iWscHuodongValueService.updateById(wscHuodongValue);
        if (huodongid == 8 || huodongid == 9 || huodongid == 10 || huodongid == 11 || huodongid == 12 || huodongid == 13) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("huodongID", huodongid);
            queryWrapper.eq("qiyeID", loginUser.getQiyeID());
            WscHuodongOthers wscHuodongOthers = iWscHuodongOthersService.getOne(queryWrapper);
            if (wscHuodongOthers == null) {
                wscHuodongOthers.setQiyeID(Long.valueOf(loginUser.getQiyeID()));
                wscHuodongOthers.setHuodongID(huodongid);
                wscHuodongOthers.setHuodongImg(huodongImg);
                wscHuodongOthers.setHuodongState(1);
                wscHuodongOthers.setHuodongshuoming(huodongshuoming);
                wscHuodongOthers.setHuodongtitle(huodongtitle);
                wscHuodongOthers.setStartDatetime(ft.parse(startDatetime));
                wscHuodongOthers.setEndDatetime(ft.parse(endDatetime));
                wscHuodongOthers.setLiulantimes(0);
                wscHuodongOthers.setFenxiangtimes(0);
                ajaxJson.setSuccess(iWscHuodongOthersService.save(wscHuodongOthers));
            } else {
                wscHuodongOthers.setQiyeID(Long.valueOf(loginUser.getQiyeID()));
                wscHuodongOthers.setHuodongID(huodongid);
                wscHuodongOthers.setHuodongImg(huodongImg);
                wscHuodongOthers.setHuodongState(1);
                wscHuodongOthers.setHuodongshuoming(huodongshuoming);
                wscHuodongOthers.setHuodongtitle(huodongtitle);
                wscHuodongOthers.setStartDatetime(ft.parse(startDatetime));
                wscHuodongOthers.setEndDatetime(ft.parse(endDatetime));
                ajaxJson.setSuccess(iWscHuodongOthersService.updateById(wscHuodongOthers));
            }
        } else if (huodongid == 1) {//拼团
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("qiyeID", Long.valueOf(loginUser.getQiyeID()));
            WscPingtuanHuodong wscPingtuanHuodong = iWscPingtuanHuodongService.getOne(queryWrapper);
            if (wscPingtuanHuodong == null) {
                wscPingtuanHuodong.setHuodongImg(huodongImg);
                wscPingtuanHuodong.setHuodongTitle(huodongtitle);
                wscPingtuanHuodong.setHuodongshuoming(huodongshuoming);
                wscPingtuanHuodong.setQiyeID(Long.valueOf(loginUser.getQiyeID()));
                wscPingtuanHuodong.setLiulangTimes(0);
                wscPingtuanHuodong.setFenxiangTimes(0);
                ajaxJson.setSuccess(iWscPingtuanHuodongService.save(wscPingtuanHuodong));
            } else {
                wscPingtuanHuodong.setHuodongImg(huodongImg);
                wscPingtuanHuodong.setHuodongTitle(huodongtitle);
                wscPingtuanHuodong.setHuodongshuoming(huodongshuoming);
                wscPingtuanHuodong.setQiyeID(Long.valueOf(loginUser.getQiyeID()));
                ajaxJson.setSuccess(iWscPingtuanHuodongService.updateById(wscPingtuanHuodong));
            }
        } else if (huodongid == 2) {//砍价
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("qiyeID", Long.valueOf(loginUser.getQiyeID()));
            WscKanjiaHuodonginfo wscKanjiaHuodonginfo = iWscKanjiaHuodonginfoService.getOne(queryWrapper);
            if (wscKanjiaHuodonginfo == null) {
                wscKanjiaHuodonginfo.setHuodongImg(huodongImg);
                wscKanjiaHuodonginfo.setHuodongTitle(huodongtitle);
                wscKanjiaHuodonginfo.setHuodongshuoming(huodongshuoming);
                wscKanjiaHuodonginfo.setQiyeID(Long.valueOf(loginUser.getQiyeID()));
                wscKanjiaHuodonginfo.setFenxiangTimes(0);
                wscKanjiaHuodonginfo.setLiulanTimes(0);
                ajaxJson.setSuccess(iWscKanjiaHuodonginfoService.save(wscKanjiaHuodonginfo));
            } else {
                wscKanjiaHuodonginfo.setHuodongImg(huodongImg);
                wscKanjiaHuodonginfo.setHuodongTitle(huodongtitle);
                wscKanjiaHuodonginfo.setHuodongshuoming(huodongshuoming);
                wscKanjiaHuodonginfo.setQiyeID(Long.valueOf(loginUser.getQiyeID()));
                ajaxJson.setSuccess(iWscKanjiaHuodonginfoService.updateById(wscKanjiaHuodonginfo));
            }
        } else if (huodongid == 3) {
            //秒杀
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("qiyeID", Long.valueOf(loginUser.getQiyeID()));
            WscMiaoshaHuodonginfo wscMiaoshaHuodonginfo = iWscMiaoshaHuodonginfoService.getOne(queryWrapper);
            if (wscMiaoshaHuodonginfo == null) {
                wscMiaoshaHuodonginfo.setHuodongImg(huodongImg);
                wscMiaoshaHuodonginfo.setHuodongtitle(huodongtitle);
                wscMiaoshaHuodonginfo.setHuodongshuoming(huodongshuoming);
                wscMiaoshaHuodonginfo.setQiyeID(Long.valueOf(loginUser.getQiyeID()));
                wscMiaoshaHuodonginfo.setFenxiangTimes(0);
                wscMiaoshaHuodonginfo.setLiulanTimes(0);
                ajaxJson.setSuccess(iWscMiaoshaHuodonginfoService.save(wscMiaoshaHuodonginfo));
            } else {
                wscMiaoshaHuodonginfo.setHuodongImg(huodongImg);
                wscMiaoshaHuodonginfo.setHuodongtitle(huodongtitle);
                wscMiaoshaHuodonginfo.setHuodongshuoming(huodongshuoming);
                wscMiaoshaHuodonginfo.setQiyeID(Long.valueOf(loginUser.getQiyeID()));
                ajaxJson.setSuccess(iWscMiaoshaHuodonginfoService.updateById(wscMiaoshaHuodonginfo));
            }
        } else if (huodongid == 7) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("qiyeID", loginUser.getQiyeID());
            WhdJizanHuodong whdJizanHuodong = iWhdJizanHuodongService.getOne(queryWrapper);
            if (whdJizanHuodong == null) {
                whdJizanHuodong.setJizanHuodongName(huodongtitle);
                whdJizanHuodong.setAddTime(new Date());
                whdJizanHuodong.setAddUser(loginUser.getStaffID() + "");
                whdJizanHuodong.setEndTime(ft.parse(endDatetime));
                whdJizanHuodong.setFenxiangtimes(0);
                whdJizanHuodong.setJizanLogoUrl(huodongImg);
                whdJizanHuodong.setJizanShuoming(huodongshuoming);
                whdJizanHuodong.setLiulantimes(0);
                whdJizanHuodong.setStartTime(ft.parse(startDatetime));
                whdJizanHuodong.setQiyeID(Long.valueOf(loginUser.getQiyeID()));
                ajaxJson.setSuccess(iWhdJizanHuodongService.save(whdJizanHuodong));
            } else {
                whdJizanHuodong.setJizanHuodongName(huodongtitle);
                whdJizanHuodong.setAddUser(loginUser.getStaffID() + "");
                whdJizanHuodong.setEndTime(ft.parse(endDatetime));
                whdJizanHuodong.setJizanLogoUrl(huodongImg);
                whdJizanHuodong.setJizanShuoming(huodongshuoming);
                whdJizanHuodong.setStartTime(ft.parse(startDatetime));
                whdJizanHuodong.setQiyeID(Long.valueOf(loginUser.getQiyeID()));
                ajaxJson.setSuccess(iWhdJizanHuodongService.save(whdJizanHuodong));
            }
        }
        return ajaxJson;
    }

    @ResponseBody
    @RequestMapping(value = "/getwscHuodongInfoByID", method = RequestMethod.GET)
    @ApiOperation(value = "查询活动信息")
    public AjaxJson getwscHuodongInfoByID(HttpServletRequest request, long huodongID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        huodonginfoVO huodonginfoVO = new huodonginfoVO();
        if (huodongID == 8 || huodongID == 9 || huodongID == 10 || huodongID == 11 || huodongID == 12 || huodongID == 13) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("qiyeID", loginUser.getQiyeID());
            queryWrapper.eq("huodongID", huodongID);
            WscHuodongOthers wscHuodongOthers = iWscHuodongOthersService.getOne(queryWrapper);
            huodonginfoVO.setHuodongImg(wscHuodongOthers.getHuodongImg());
            huodonginfoVO.setHuodongtitle(wscHuodongOthers.getHuodongtitle());
            huodonginfoVO.setHuodongshuoming(wscHuodongOthers.getHuodongshuoming());
            huodonginfoVO.setStartDatetime(wscHuodongOthers.getStartDatetime());
            huodonginfoVO.setEndDatetime(wscHuodongOthers.getEndDatetime());
        } else if (huodongID == 1) {
            //拼团
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("qiyeID", loginUser.getQiyeID());
            WscPingtuanHuodong wscPingtuanHuodong = iWscPingtuanHuodongService.getOne(queryWrapper);
            huodonginfoVO.setHuodongImg(wscPingtuanHuodong.getHuodongImg());
            huodonginfoVO.setHuodongtitle(wscPingtuanHuodong.getHuodongTitle());
            huodonginfoVO.setHuodongshuoming(wscPingtuanHuodong.getHuodongshuoming());
        } else if (huodongID == 2) {
            //砍价
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("qiyeID", loginUser.getQiyeID());
            WscKanjiaHuodonginfo wscKanjiaHuodonginfo = iWscKanjiaHuodonginfoService.getOne(queryWrapper);
            huodonginfoVO.setHuodongImg(wscKanjiaHuodonginfo.getHuodongImg());
            huodonginfoVO.setHuodongtitle(wscKanjiaHuodonginfo.getHuodongTitle());
            huodonginfoVO.setHuodongshuoming(wscKanjiaHuodonginfo.getHuodongshuoming());
        } else if (huodongID == 3) {
            //秒杀
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("qiyeID", loginUser.getQiyeID());
            WscMiaoshaHuodonginfo wscMiaoshaHuodonginfo = iWscMiaoshaHuodonginfoService.getOne(queryWrapper);
            huodonginfoVO.setHuodongImg(wscMiaoshaHuodonginfo.getHuodongImg());
            huodonginfoVO.setHuodongtitle(wscMiaoshaHuodonginfo.getHuodongtitle());
            huodonginfoVO.setHuodongshuoming(wscMiaoshaHuodonginfo.getHuodongshuoming());
        } else if (huodongID == 7) {
            //集赞
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("qiyeID", loginUser.getQiyeID());
            WhdJizanHuodong whdJizanHuodong = iWhdJizanHuodongService.getOne(queryWrapper);
            huodonginfoVO.setHuodongImg(whdJizanHuodong.getJizanLogoUrl());
            huodonginfoVO.setHuodongtitle(whdJizanHuodong.getJizanHuodongName());
            huodonginfoVO.setHuodongshuoming(whdJizanHuodong.getJizanShuoming());
            huodonginfoVO.setStartDatetime(whdJizanHuodong.getStartTime());
            huodonginfoVO.setEndDatetime(whdJizanHuodong.getEndTime());
        }
        ajaxJson.setObj(huodonginfoVO);
        return ajaxJson;
    }

    /**
     * 根据id批量删除商品类别
     * 请求方式：/path/1,2,3
     */
    @DeleteMapping("/deleteWscHuodongValByIDs/{ids}")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("根据id批量删除商品类别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "企业活动ids", required = true),
    })
    public AjaxJson deleteWscHuodongValByIDs(@PathVariable List<String> ids) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(wscHuodongValueService.removeByIds(ids));
        return ajaxJson;
    }

    @RequestMapping(value = "/GetAllShowHuodongList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取所有的可用活动信息")

    public AjaxJson GetAllShowHuodongList(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("isShow", 1);
        ajaxJson.setObj(wscHuodongService.list(queryWrapper));
        return ajaxJson;
    }
    //endregion

    //region 订单

    /**
     * 分页查询订单
     */
    @GetMapping("/getWscOrderPage")
    @ResponseBody
    @ApiOperation("分页查询订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "ordernumber", value = "订单号"),
            @ApiImplicitParam(name = "type", value = "类型，0送货上门，1自取，2虚拟课程(商品)"),
            @ApiImplicitParam(name = "receivename", value = "收货人。type=2为联系人"),
            @ApiImplicitParam(name = "receivedizhi", value = "收货地址"),
            @ApiImplicitParam(name = "lianxitel", value = "收货电话(联系电话)"),
            @ApiImplicitParam(name = "paystyle", value = "0.余额支付 1.微信支付 2.积分支付"),
            @ApiImplicitParam(name = "orderfrom", value = "订单来源，1微信端，2抖音"),
            @ApiImplicitParam(name = "orderstate", value = "订单状态：1下单未支付、2下单已支付（未发货），3已发货（待收货），4已完成，5退款，6已关闭的订单"),
            @ApiImplicitParam(name = "orderUserName", value = "下单用户名"),
    })
    public AjaxJson getWscOrderPage(HttpServletRequest request, long size, long current, WscOrderVo wscOrderVo) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<WscOrderVo> wrapper = new QueryWrapper<>();
        wrapper.eq("a.qiyeID", qiyeID);
        if (!ObjectUtils.isEmpty(wscOrderVo.getOrderNumber())) {
            wrapper.like("a.orderNumber", wscOrderVo.getOrderNumber());
        }
        if (!ObjectUtils.isEmpty(wscOrderVo.getType())) {
            wrapper.eq("a.type", wscOrderVo.getType());
        }
        if (!ObjectUtils.isEmpty(wscOrderVo.getReceiveName())) {
            wrapper.like("a.receiveName", wscOrderVo.getReceiveName());
        }
        if (!ObjectUtils.isEmpty(wscOrderVo.getReceiveDizhi())) {
            wrapper.like("a.receiveDizhi", wscOrderVo.getReceiveDizhi());
        }
        if (!ObjectUtils.isEmpty(wscOrderVo.getLianxiTel())) {
            wrapper.like("a.lianxiTel", wscOrderVo.getLianxiTel());
        }
        if (!ObjectUtils.isEmpty(wscOrderVo.getPayStyle())) {
            wrapper.eq("a.payStyle", wscOrderVo.getPayStyle());
        }
        if (!ObjectUtils.isEmpty(wscOrderVo.getOrderFrom())) {
            wrapper.eq("a.orderFrom", wscOrderVo.getOrderFrom());
        }
        if (!ObjectUtils.isEmpty(wscOrderVo.getOrderUserName())) {
            wrapper.like("b.nickName", wscOrderVo.getOrderUserName());
        }
        if (!ObjectUtils.isEmpty(wscOrderVo.getOrderState())) {
            wrapper.eq("a.orderState", wscOrderVo.getOrderState());
        }
        if (!ObjectUtils.isEmpty(wscOrderVo.getDatesoe())) {
            String[] s = (wscOrderVo.getDatesoe() + " 23:59:59").split("_");
            wrapper.between("a.orderDateTime", s[0], s[1]);
        }
        if (!ObjectUtils.isEmpty(wscOrderVo.getSearchType())) {
            LocalDate now = LocalDate.now();
            switch (wscOrderVo.getSearchType()) {
                case 2: {
//                    今日
                    wrapper.between("a.orderDateTime", now, now.toString() + " 23:59:59");
                    break;
                }
                case 3: {
//                    本周
                    int dayOfWeek = now.getDayOfWeek().getValue();
                    LocalDate start = now.plusDays(1 - dayOfWeek);
                    LocalDate end = now.plusDays(7 - dayOfWeek);
                    wrapper.between("a.orderDateTime", start, end.toString() + " 23:59:59");
                    break;
                }
                case 4: {
//                    本月
                    LocalDate start = now.with(TemporalAdjusters.firstDayOfMonth());
                    LocalDate end = now.with(TemporalAdjusters.lastDayOfMonth());
                    wrapper.between("a.orderDateTime", start, end.toString() + " 23:59:59");
                    break;
                }
            }
        }
        wrapper.orderByDesc("orderDateTime");
        Page<WscOrderVo> page = wscOrderService.getWscOrderPage(new Page<>(current, size), wrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 分页查询指定订单的订单商品
     */
    @GetMapping("/getOrderGoodsDetailByOrderNumberPage")
    @ResponseBody
    @ApiOperation("分页查询指定订单的订单商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "orderNumber", value = "订单号", required = true),
    })
    public AjaxJson getOrderGoodsDetailByOrderNumberPage(HttpServletRequest request, long size, long current, long orderNumber) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<HashMap<String, Object>> wrapper = new QueryWrapper<>();
        wrapper
                .eq("aa.qiyeID", loginUser.getQiyeID())
                .eq("aa.orderNumber", orderNumber);
        Page<HashMap<String, Object>> page = wscOrdergoodsService.getOrderGoodsDetailByOrderNumberPage(new Page(current, size), wrapper);
        ajaxJson.setObj(page);
        ajaxJson.setCode("success");
        return ajaxJson;
    }

    /**
     * 批量发货
     */
    @PostMapping("/orderFahuo")
    @ResponseBody
    @Transactional
    @ApiOperation("批量发货")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "订单ID", required = true),
    })
    public AjaxJson orderFahuo(HttpServletRequest request, @RequestBody List<Long> ids) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        try {
            QueryWrapper<WscOrder> wrapperQ = new QueryWrapper<>();
            wrapperQ.lambda()
                    .ne(WscOrder::getOrderState, 2)
                    .in(WscOrder::getId, ids)
                    .eq(WscOrder::getQiyeID, loginUser.getQiyeID());
            int count = wscOrderService.count(wrapperQ);
            if (count > 0) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("订单状态已经改变");
                return ajaxJson;
            }
            UpdateWrapper<WscOrder> wrapperU = new UpdateWrapper<>();
            wrapperU.lambda()
                    .in(WscOrder::getId, ids)
                    .eq(WscOrder::getQiyeID, loginUser.getQiyeID())
                    .set(WscOrder::getOrderState, 3);
            wscOrderService.update(wrapperU);
            ajaxJson.setCode("success");
            return ajaxJson;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ajaxJson.setCode("N");
            ajaxJson.setMsg("操作失败了，请刷新重试");
            return ajaxJson;
        }
    }

    /**
     * 完成订单
     */
    @PostMapping("/orderWancheng")
    @ResponseBody
    @Transactional
    @ApiOperation("完成订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderID", value = "订单ID", required = true),
    })
    public AjaxJson orderWancheng(HttpServletRequest request, @RequestBody HashMap<String, Object> postData) {
        AjaxJson ajaxJson = new AjaxJson();
        if (ObjectUtils.isEmpty(postData) || postData.isEmpty()) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("操作失败了，请刷新重试");
            return ajaxJson;
        }
        Long orderID = Long.parseLong(postData.get("orderID").toString());
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        try {
            WscOrder wscOrderById = wscOrderService.getById(orderID);
            if (wscOrderById.getOrderState() != 3) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("订单状态已经改变");
                return ajaxJson;
            }
            WscOrder wscOrder = new WscOrder();
            wscOrder
                    .setId(orderID)
                    .setOrderState(4)
                    .updateById();
            //返佣
//            WscUser wscUserById = wscUserService.getById(wscOrderById.getOrderUserID());
//            if (!ObjectUtils.isEmpty(wscUserById) && !ObjectUtils.isEmpty(wscUserById.getFid()) && wscUserById.getFid() != 0) {
//                WscUser user_fuji = wscUserService.getById(wscUserById.getFid());
//                if (!ObjectUtils.isEmpty(user_fuji) && !ObjectUtils.isEmpty(user_fuji.getFid()) && user_fuji.getFid() != 0) {
//                    WscUser user_zuji = wscUserService.getById(user_fuji.getFid());
//                    BigDecimal yongjin_zuji = BigDecimal.valueOf(0);
//                    if (!ObjectUtils.isEmpty(user_zuji)) {
//                        //祖级佣金增加
//                        WscYongjinbili wscYongjinbili = wscYongjinbiliService.getById(1);
//                        BigDecimal bili = wscYongjinbili.getBili().divide(BigDecimal.valueOf(100));
//                        BigDecimal yongjin = wscOrderById.getPayMoney().multiply(bili);
//                        user_zuji.setScRemainMoney(user_zuji.getScRemainMoney().add(yongjin));
//                        user_zuji.setScYijieYongjin(user_zuji.getScYijieYongjin().add(yongjin));
//                        yongjin_zuji = yongjin;
//                    }
//                    //父级佣金增加
//                    WscYongjinbili wscYongjinbili = wscYongjinbiliService.getById(2);
//                    BigDecimal bili = wscYongjinbili.getBili().divide(BigDecimal.valueOf(100));
//                    BigDecimal yongjin = wscOrderById.getPayMoney().multiply(bili);
//                    user_fuji.setScRemainMoney(user_fuji.getScRemainMoney().add(yongjin));
//                    user_fuji.setScYijieYongjin(user_fuji.getScYijieYongjin().add(yongjin));
////                    佣金记录添加
//                    WscYongjin wscyongjin = new WscYongjin();
//                    wscyongjin
//                            .setOrderNumber(Long.valueOf(wscOrderById.getOrderNumber()))
//                            .setMoney(wscOrderById.getPayMoney())
//                            .setFid(user_fuji.getId())
//                            .setFidMoney(yongjin)
//                            .setFidJiesuanDateTime(LocalDateTime.now())
//                            .setTopfid(user_fuji.getFid())
//                            .setTopfidMoney(yongjin_zuji)
//                            .setTopfidJiesuanDateTime(LocalDateTime.now())
//                            .setQiyeID(loginUser.getQiyeID())
//                            .insert();
//                }
//            }
            ajaxJson.setCode("success");
            return ajaxJson;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ajaxJson.setMsg("操作失败了，请刷新重试");
            return ajaxJson;
        }
    }

    /**
     * 导出订单
     */
    @GetMapping("/exportOrder")
    @ResponseBody
    @ApiOperation("导出订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "datesoe", value = "起止日期、格式：start_end"),
    })
    public void exportOrder(HttpServletRequest request, HttpServletResponse response, String datesoe) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper<HashMap<String, Object>> wrapper = new QueryWrapper<>();
        wrapper.eq("a.qiyeID", qiyeID);
        if (!ObjectUtils.isEmpty(datesoe)) {
            String[] s = (datesoe + " 23:59:59").split("_");
            wrapper.between("a.orderDateTime", s[0], s[1]);
        }
        List<HashMap<String, Object>> wscOrderList = wscOrderService.getWscOrderList(wrapper);
        List<List<Object>> list = ExportExcel.formatHashMapDataToList(
                new String[]{"订单号", "订单状态", "商品详情", "支付金额", "支付积分", "支付方式", "订单备注", "下单时间", "下单客户", "联系人", "联系电话", "意向校区", "物流地址", "收货方式"},
                wscOrderList,
                new String[]{"orderNumber", "orderState", "goodsName", "payMoney", "payJifen", "payStyle", "beizhu", "orderDateTime-DT", "userName", "receiveName", "lianxiTel", "receiveDizhi", "receiveDizhi1", "type"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "订单信息.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//
//    /**
//     * 添加一条订单（此方法添加的统一为未支付的）
//     */
//    @PostMapping("/addWscOrder")
//    @ResponseBody
//    @Transactional(rollbackFor = Exception.class)
//    @ApiOperation("添加一条订单（此方法添加的统一为未支付的）")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "paymoney", value = "支付金额", required = true),
//            @ApiImplicitParam(name = "payjifen", value = "支付积分", required = true),
//            @ApiImplicitParam(name = "couponsid", value = "优惠券ID"),
//            @ApiImplicitParam(name = "type", value = "类型，0送货上门，1自取，2虚拟课程(商品)", required = true),
//            @ApiImplicitParam(name = "receivename", value = "收货人。type=2为联系人", required = true),
//            @ApiImplicitParam(name = "receivedizhi", value = "收货地址", required = true),
//            @ApiImplicitParam(name = "lianxitel", value = "收货电话(联系电话)", required = true),
//            @ApiImplicitParam(name = "yxcampusids", value = "意向校区，多个意向用逗号分隔；当type=2时,才设置意向校区"),
//            @ApiImplicitParam(name = "beizhu", value = "订单备注"),
//            @ApiImplicitParam(name = "paystyle", value = "0.余额支付 1.微信支付 2.积分支付", required = true),
//            @ApiImplicitParam(name = "huodongid", value = "活动ID，0表示普通订单，其他值对应活动id"),
//            @ApiImplicitParam(name = "orderfrom", value = "订单来源，1微信端，2抖音"),
//            @ApiImplicitParam(name = "orderuserid", value = "下单用户", required = true),
//    })
//    public AjaxJson addWscOrder(HttpServletRequest request, WscOrder wscOrder) {
//        AjaxJson ajaxJson = new AjaxJson();
//        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
//        String qiyeID = loginUser.getQiyeID();
//        if (
//                ObjectUtils.isEmpty(wscOrder.getPaymoney()) ||
//                        ObjectUtils.isEmpty(wscOrder.getPayjifen()) ||
//                        ObjectUtils.isEmpty(wscOrder.getType()) ||
//                        ObjectUtils.isEmpty(wscOrder.getReceivename()) ||
//                        ObjectUtils.isEmpty(wscOrder.getReceivedizhi()) ||
//                        ObjectUtils.isEmpty(wscOrder.getLianxitel()) ||
//                        ObjectUtils.isEmpty(wscOrder.getPaystyle()) ||
//                        ObjectUtils.isEmpty(wscOrder.getOrderuserid())
//        ) {
//            String msg = "";
//            if (ObjectUtils.isEmpty(wscOrder.getPaymoney())) {
//                msg += "支付金额不可为空。";
//            }
//            if (ObjectUtils.isEmpty(wscOrder.getPayjifen())) {
//                msg += "支付积分不可为空。";
//            }
//            if (ObjectUtils.isEmpty(wscOrder.getType())) {
//                msg += "类型不可为空。";
//            }
//            if (ObjectUtils.isEmpty(wscOrder.getReceivename())) {
//                msg += "联系人不可为空。";
//            }
//            if (ObjectUtils.isEmpty(wscOrder.getReceivedizhi())) {
//                msg += "联系地址不可为空。";
//            }
//            if (ObjectUtils.isEmpty(wscOrder.getLianxitel())) {
//                msg += "联系电话不可为空。";
//            }
//            if (ObjectUtils.isEmpty(wscOrder.getPaystyle())) {
//                msg += "支付方式不可为空。";
//            }
//            if (ObjectUtils.isEmpty(wscOrder.getOrderuserid())) {
//                msg += "下单用户不可为空。";
//            }
//            ajaxJson.setMsg(msg);
//            return ajaxJson;
//        }
//
//        if (ObjectUtils.isEmpty(wscOrder.getOrderfrom())) {
//            wscOrder.setOrderfrom(1);
//        }
//        int first = new Random(10).nextInt(8) + 1;
//        int hashCodeV = UUID.randomUUID().toString().hashCode();
//        if (hashCodeV < 0) {//有可能是负数
//            hashCodeV = -hashCodeV;
//        }
//        String OrderNumber = first + String.format("%015d", hashCodeV);
//        ajaxJson.setSuccess(wscOrder
//                .setOrdernumber(Long.parseLong(OrderNumber))
//                .setOrderdatetime(LocalDateTime.now())
//                .setPaydatetime(LocalDateTime.now())
//                .setOrderstate(1)
//                .setQiyeid(Long.parseLong(qiyeID))
//                .insert());
//        return ajaxJson;
//    }
//
//    /**
//     * 根据ID更新一条订单
//     */
//    @PostMapping("/updateWscOrderByID")
//    @ResponseBody
//    @Transactional(rollbackFor = Exception.class)
//    @ApiOperation("根据ID更新一条订单")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "paymoney", value = "支付金额"),
//            @ApiImplicitParam(name = "payjifen", value = "支付积分"),
//            @ApiImplicitParam(name = "couponsid", value = "优惠券ID"),
//            @ApiImplicitParam(name = "type", value = "类型，0送货上门，1自取，2虚拟课程(商品)"),
//            @ApiImplicitParam(name = "receivename", value = "收货人。type=2为联系人"),
//            @ApiImplicitParam(name = "receivedizhi", value = "收货地址"),
//            @ApiImplicitParam(name = "lianxitel", value = "收货电话(联系电话)"),
//            @ApiImplicitParam(name = "yxcampusids", value = "意向校区，多个意向用逗号分隔；当type=2时,才设置意向校区"),
//            @ApiImplicitParam(name = "beizhu", value = "订单备注"),
//            @ApiImplicitParam(name = "paystyle", value = "0.余额支付 1.微信支付 2.积分支付"),
//            @ApiImplicitParam(name = "paydatetime", value = "支付时间"),
//            @ApiImplicitParam(name = "huodongid", value = "活动ID，0表示普通订单，其他值对应活动id"),
//            @ApiImplicitParam(name = "orderfrom", value = "订单来源，1微信端，2抖音"),
//            @ApiImplicitParam(name = "orderstate", value = "订单状态：1下单未支付、2下单已支付（未发货），3已发货（待收货），4已完成，5退款，6已关闭的订单"),
//            @ApiImplicitParam(name = "orderuserid", value = "下单用户"),
//    })
//    public AjaxJson updateWscOrderByID(WscOrder wscOrder) {
//        AjaxJson ajaxJson = new AjaxJson();
//        if (ObjectUtils.isEmpty(wscOrder.getId())) {
//            ajaxJson.setMsg("订单ID不可为空");
//        } else {
//            ajaxJson.setSuccess(
//                    wscOrder
//                            .setOrdernumber(null)
//                            .setOrderdatetime(null)
//                            .setQiyeid(null)
//                            .updateById()
//            );
//        }
//        return ajaxJson;
//    }

    //endregion

    //region 购物车

    @GetMapping("/getShoppingCatPage")
    @ResponseBody
    @ApiOperation("分页查询购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "goodsName", value = "商品名字"),
            @ApiImplicitParam(name = "guige", value = "规格"),
            @ApiImplicitParam(name = "addUser", value = "购物车所属用户"),
            @ApiImplicitParam(name = "datesoe", value = "添加时间-格式：start_end"),
    })
    public AjaxJson getShoppingCatPage(HttpServletRequest request,
                                       long size,
                                       long current,
                                       String goodsName,
                                       String guige,
                                       String addUser,
                                       String datesoe,
                                       Integer searchType,
                                       @RequestParam(required = false) String addUserID  //小程序的
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<HashMap<String, Object>> wrapper = new QueryWrapper<>();
        wrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (!ObjectUtils.isEmpty(goodsName)) {
            wrapper.like("a.goodsName", goodsName);
        }
        if (!ObjectUtils.isEmpty(guige)) {
            wrapper.like("a.guige", guige);
        }
        if (!ObjectUtils.isEmpty(addUser)) {
            wrapper.like("e.staffName", addUser);
        }
        if (!ObjectUtils.isEmpty(addUserID)) { //小程序
            wrapper.like("a.addUser", addUserID);
        }
        if (!ObjectUtils.isEmpty(datesoe)) {
            String[] s = (datesoe + " 23:59:59").split("_");
            wrapper.between("a.addDateTime", s[0], s[1]);
        }
        if (!ObjectUtils.isEmpty(searchType)) {
            LocalDate now = LocalDate.now();
            switch (searchType) {
                case 2: {
//                    今日
                    wrapper.between("a.addDateTime", now, now.toString() + " 23:59:59");
                    break;
                }
                case 3: {
//                    本周
                    int dayOfWeek = now.getDayOfWeek().getValue();
                    LocalDate start = now.plusDays(1 - dayOfWeek);
                    LocalDate end = now.plusDays(7 - dayOfWeek);
                    wrapper.between("a.addDateTime", start, end.toString() + " 23:59:59");
                    break;
                }
                case 4: {
//                    本月
                    LocalDate start = now.with(TemporalAdjusters.firstDayOfMonth());
                    LocalDate end = now.with(TemporalAdjusters.lastDayOfMonth());
                    wrapper.between("a.addDateTime", start, end.toString() + " 23:59:59");
                    break;
                }
            }
        }
        Page<HashMap<String, Object>> page = wscShoppingcatService.getShoppingCatPage(new Page<>(current, size), wrapper);
        ajaxJson.setObj(page);
        ajaxJson.setCode("success");
        return ajaxJson;
    }

    //endregion

    //region 退款退货管理

    /**
     * 分页获取退货退款记录
     */
    @GetMapping("/getTuihuokuanPage")
    @ResponseBody
    @ApiOperation("分页获取退货退款记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "orderNumber", value = "订单号"),
            @ApiImplicitParam(name = "receiveName", value = "联系人"),
            @ApiImplicitParam(name = "userName", value = "下单用户"),
            @ApiImplicitParam(name = "datesoe", value = "申请时间-格式：start_end"),
            @ApiImplicitParam(name = "tuikuanState", value = "退款状态"),
            @ApiImplicitParam(name = "tuihuoState", value = "退货状态"),
    })
    public AjaxJson getTuihuokuanPage(HttpServletRequest request,
                                      long size, long current,
                                      Long orderNumber, String receiveName, String lianxiTel, String userName,
                                      String datesoe, Integer tuikuanState, Integer tuihuoState, Integer searchType) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<HashMap<String, Object>> wrapper = new QueryWrapper<>();
        wrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (!ObjectUtils.isEmpty(orderNumber)) {
            wrapper.eq("a.orderNumber", orderNumber);
        }
        if (!ObjectUtils.isEmpty(receiveName)) {
            wrapper.like("b.receiveName", receiveName);
        }
        if (!ObjectUtils.isEmpty(lianxiTel)) {
            wrapper.like("b.lianxiTel", lianxiTel);
        }
        if (!ObjectUtils.isEmpty(userName)) {
            wrapper.like("d.nickName", userName);
        }
        if (!ObjectUtils.isEmpty(datesoe)) {
            String[] s = (datesoe + " 23:59:59").split("_");
            wrapper.between("a.tuikuanTime", s[0], s[1]);
        }
        if (!ObjectUtils.isEmpty(tuikuanState)) {
            wrapper.eq("a.tuikuanState", tuikuanState);
        }
        if (!ObjectUtils.isEmpty(tuihuoState)) {
            wrapper.eq("a.tuihuoState", tuihuoState);
        }
        if (!ObjectUtils.isEmpty(searchType)) {
            LocalDate now = LocalDate.now();
            switch (searchType) {
                case 2: {
//                    今日
                    wrapper.between("a.tuikuanTime", now, now.toString() + " 23:59:59");
                    break;
                }
                case 3: {
//                    本周
                    int dayOfWeek = now.getDayOfWeek().getValue();
                    LocalDate start = now.plusDays(1 - dayOfWeek);
                    LocalDate end = now.plusDays(7 - dayOfWeek);
                    wrapper.between("a.tuikuanTime", start, end.toString() + " 23:59:59");
                    break;
                }
                case 4: {
//                    本月
                    LocalDate start = now.with(TemporalAdjusters.firstDayOfMonth());
                    LocalDate end = now.with(TemporalAdjusters.lastDayOfMonth());
                    wrapper.between("a.tuikuanTime", start, end.toString() + " 23:59:59");
                    break;
                }
            }
        }
        Page<HashMap<String, Object>> page = wscOrdertuifeiService.getTuihuokuanPage(new Page<>(current, size), wrapper);
        ajaxJson.setObj(page);
        ajaxJson.setCode("success");
        return ajaxJson;
    }

    /**
     * 同意退款
     */
    @PostMapping("/agreeTuikuan")
    @ResponseBody
    @Transactional
    @ApiOperation("同意退款")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单退费表ID", required = true),
            @ApiImplicitParam(name = "orderNumber", value = "订单号", required = true),
            @ApiImplicitParam(name = "shuoming", value = "同意退费的说明", required = true),
    })
    public AjaxJson agreeTuikuan(HttpServletRequest request, @RequestBody AgreeTuikuanVo agreeTuikuanVo) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        try {
            WscOrdertuifei wscOrdertuifei = wscOrdertuifeiService.getById(agreeTuikuanVo.getId());
            //0.待处理，1.退款中，2拒绝退款，3退款成功，4退款失败，

            if (Arrays.stream(new int[]{1, 2, 3, 4}).anyMatch(item -> item == wscOrdertuifei.getTuikuanState())) {
                if (wscOrdertuifei.getTuikuanState() == 2) {
                    ajaxJson.setMsg("退款申请已被拒绝，请重新申请退款!");
                } else if (wscOrdertuifei.getTuikuanState() == 3) {
                    ajaxJson.setMsg("该退款申请已经同意，请勿重新操作!");
                } else {
                    ajaxJson.setMsg("该申请已经退款!");
                }
                ajaxJson.setCode("N");
                return ajaxJson;
            }
            QueryWrapper<WscOrder> wrapper = new QueryWrapper<>();
            wrapper.lambda()
                    .eq(WscOrder::getQiyeID, loginUser.getQiyeID())
                    .eq(WscOrder::getOrderNumber, agreeTuikuanVo.getOrderNumber())
                    //订单状态：1下单未支付、2下单已支付（未发货），3已发货（待收货），4已完成，5退款，6已关闭的订单
                    .eq(WscOrder::getOrderState, 5);
            WscOrder wscOrder = wscOrderService.getOne(wrapper);
            if (ObjectUtils.isEmpty(wscOrder)) {
                //订单查找不到，操作失败
                return ajaxJson;
            }
            if (wscOrder.getPayStyle() == 1) {
//                微信退款

                wscOrdertuifei
                        .setTuikuanState(3)
                        .setChuliTuikuanMsg("");//应由微信退款返回值决定
                wscOrder.setOrderState(5).updateById();
                ajaxJson.setCode("success");
            } else if (wscOrder.getPayStyle() == 2) {
//                退积分
                WscUser wscUser = wscUserService.getById(wscOrder.getOrderUserID());
                wscUser
                        .setScJifen(wscUser.getScJifen().add(wscOrder.getPayJifen()))
                        .updateById();
//                添加商城交易记录表
                WscUserjiaoyi wscUserjiaoyi = new WscUserjiaoyi();
                wscUserjiaoyi
                        .setOrderNumber(Long.valueOf(wscOrder.getOrderNumber()))
                        .setWscUserID(wscUser.getId())
                        .setPayMoney(wscOrder.getPayJifen())
                        .setGiveMoney(BigDecimal.valueOf(0))
                        .setTotalMoney(wscOrder.getPayJifen())
                        .setStyle(2)
                        .setType(2)
                        .setAddDate(new Date())
                        .setOkDate(new Date())
                        .setState(true)
                        .setQiyeID(loginUser.getQiyeID())
                        .insert();
//                修改退费状态
                wscOrdertuifei
                        .setTuikuanState(3)
                        .setChuliTuikuanMsg("退还积分");
                ajaxJson.setCode("success");
            } else {
//                退余额
                WscUser wscUser = wscUserService.getById(wscOrder.getOrderUserID());
                wscUser
                        .setScRemainMoney(wscUser.getScRemainMoney().add(wscOrder.getPayMoney()))
                        .updateById();
//                添加商城交易记录表
                WscUserjiaoyi wscUserjiaoyi = new WscUserjiaoyi();
                wscUserjiaoyi
                        .setOrderNumber(Long.valueOf(wscOrder.getOrderNumber()))
                        .setWscUserID(wscUser.getId())
                        .setPayMoney(wscOrder.getPayMoney())
                        .setGiveMoney(BigDecimal.valueOf(0))
                        .setTotalMoney(wscOrder.getPayJifen())
                        .setStyle(1)
                        .setType(2)
                        .setAddDate(new Date())
                        .setOkDate(new Date())
                        .setState(true)
                        .setQiyeID(loginUser.getQiyeID())
                        .insert();
//                修改退费状态
                wscOrdertuifei
                        .setTuikuanState(3)
                        .setChuliTuikuanMsg("退还余额");
                ajaxJson.setCode("success");
            }
            wscOrdertuifei
                    .setChuliTuikuanRen(loginUser.getStaffID())
                    .setChuliTuikuanShuoming(agreeTuikuanVo.getShuoming())
                    .setChuliTuikuanTime(LocalDateTime.now())
                    .updateById();

            return ajaxJson;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ajaxJson.setCode("N");
            ajaxJson.setMsg("出错了，请刷新重试");
            return ajaxJson;
        }
    }

    /**
     * 拒绝退款
     */
    @PostMapping("/refuseTuikuan")
    @ResponseBody
    @Transactional
    @ApiOperation("拒绝退款")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单退费表ID", required = true),
            @ApiImplicitParam(name = "shuoming", value = "拒绝说明", required = true),
    })
    public AjaxJson refuseTuikuan(HttpServletRequest request, @RequestBody RefuseTuikuanVo refuseTuikuanVo) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        try {
            WscOrdertuifei wscOrdertuifei = wscOrdertuifeiService.getById(refuseTuikuanVo.getId());
            //0.待处理，1.退款中，2拒绝退款，3退款成功，4退款失败，
            if (Arrays.stream(new int[]{1, 2, 3, 4}).anyMatch(item -> item == wscOrdertuifei.getTuikuanState())) {
                if (wscOrdertuifei.getTuikuanState() == 2) {
                    ajaxJson.setMsg("退款申请已被拒绝，请重新申请退款!");
                } else if (wscOrdertuifei.getTuikuanState() == 3) {
                    ajaxJson.setMsg("该退款申请已经同意，请勿重新操作!");
                } else {
                    ajaxJson.setMsg("该申请已经退款!");
                }
                return ajaxJson;
            }
            wscOrdertuifei
                    .setTuikuanState(2)
                    .setChuliTuikuanTime(LocalDateTime.now())
                    .setChuliTuikuanShuoming(refuseTuikuanVo.getShuoming())
                    .setChuliTuikuanRen(loginUser.getStaffID())
                    .setChuliTuikuanMsg("拒绝退款")
                    .updateById();
            ajaxJson.setCode("success");
            return ajaxJson;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println(e);
            ajaxJson.setMsg("出错了，请刷新重试");
            return ajaxJson;
        }
    }

    /**
     * 充值订单退款
     */
    @PostMapping("/rechargeOrderTuikuan")
    @ResponseBody
    @Transactional
    @ApiOperation("充值订单退款")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderNumber", value = "订单号", required = true),
    })
    public AjaxJson rechargeOrderTuikuan(HttpServletRequest request, @RequestBody HashMap<String, Object> map) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        try {
            String orderNumber = map.get("orderNumber").toString();
            QueryWrapper<WscUserjiaoyi> wrapper = new QueryWrapper<>();
            wrapper.lambda()
                    .eq(WscUserjiaoyi::getQiyeID, loginUser.getQiyeID())
                    .eq(WscUserjiaoyi::getOrderNumber, orderNumber);
            WscUserjiaoyi wscUserjiaoyi = wscUserjiaoyiService.getOne(wrapper);
            if (ObjectUtils.isEmpty(wscUserjiaoyi)) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("出错了，请刷新重试");
                return ajaxJson;
            }
            WscUser wscUser = wscUserService.getById(wscUserjiaoyi.getWscUserID());
            if (wscUser.getScRemainMoney().compareTo(wscUserjiaoyi.getTotalMoney()) < 0) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("该用户商城余额不足");
                return ajaxJson;
            }
//            退款（微信返还）,退只退实际支付的

//            扣减余额，扣连上赠送的一起扣
            wscUser
                    .setScRemainMoney(wscUser.getScRemainMoney().subtract(wscUserjiaoyi.getTotalMoney()))
                    .updateById();

            QueryWrapper<WscOrder> wrapperOrder = new QueryWrapper<>();
            wrapperOrder.lambda()
                    .eq(WscOrder::getQiyeID, loginUser.getQiyeID())
                    .eq(WscOrder::getOrderNumber, orderNumber);
            WscOrder wscOrder = wscOrderService.getOne(wrapperOrder);
            wscOrder.setOrderState(5).updateById();

            ajaxJson.setCode("success");
            return ajaxJson;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println(e);
            ajaxJson.setCode("N");
            ajaxJson.setMsg("出错了，请刷新重试");
            return ajaxJson;
        }
    }


    //endregion

    //region 订单商品

    /**
     * 分页查询订单商品
     */
    @GetMapping("/getWscOrderGoodsPage")
    @ResponseBody
    @ApiOperation("分页查询订单商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "goodsTypeName", value = "商品类别名称"),
            @ApiImplicitParam(name = "typeLevel", value = "商品类别级别"),
            @ApiImplicitParam(name = "isShow", value = "是否显示", example = "1"),
    })
    public AjaxJson getWscOrderGoodsPage(HttpServletRequest request, long size, long current, long orderNumber) {
        AjaxJson ajaxJson = new AjaxJson();
        if (ObjectUtils.isEmpty(orderNumber)) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("订单号不可为空");
            return ajaxJson;
        }
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<WscOrderGoodsVo> wrapper = new QueryWrapper<>();
        wrapper.eq("a.qiyeID", qiyeID);
        wrapper.eq("a.orderNumber", orderNumber);
        Page<WscOrderGoodsVo> page = wscOrdergoodsService.getWscOrderGoodsPage(new Page<>(current, size), wrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 添加定单商品
     */
    @PostMapping("/addWscOrderGoods")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("添加定单商品")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "ordernumber", value = "订单号", required = true),
            @ApiImplicitParam(name = "goodsid", value = "商品id", required = true),
            @ApiImplicitParam(name = "goodsshuxinglistpriceid", value = "商品属性价格列表id", required = true),
            @ApiImplicitParam(name = "pingtuanfaqirenid", value = "拼团发起人ID"),
            @ApiImplicitParam(name = "huodongid", value = "活动ID,0表示普通商品，未参加活动", required = true),
            @ApiImplicitParam(name = "nums", value = "购买数量", required = true),
            @ApiImplicitParam(name = "paymoney", value = "支付金额", required = true),
            @ApiImplicitParam(name = "pingjia", value = "评价"),
            @ApiImplicitParam(name = "pingjiatype", value = "评价类别：0.非常满意 1.满意 2.一般 3.很糟糕"),
            @ApiImplicitParam(name = "pingjiadate", value = "评价时间"),
            @ApiImplicitParam(name = "qiyeid", value = "企业ID"),
    })
    public AjaxJson addWscOrderGoods(HttpServletRequest request, WscOrdergoods wscOrdergoods) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        if (
                ObjectUtils.isEmpty(wscOrdergoods.getOrderNumber()) ||
                        ObjectUtils.isEmpty(wscOrdergoods.getGoodsID()) ||
                        ObjectUtils.isEmpty(wscOrdergoods.getGoodsshuxinglistpriceID()) ||
                        ObjectUtils.isEmpty(wscOrdergoods.getHuodongID()) ||
                        ObjectUtils.isEmpty(wscOrdergoods.getNums()) ||
                        ObjectUtils.isEmpty(wscOrdergoods.getPayMoney())
        ) {
            String msg = "";
            if (ObjectUtils.isEmpty(wscOrdergoods.getOrderNumber())) {
                msg += "订单号不可为空。";
            }
            if (ObjectUtils.isEmpty(wscOrdergoods.getGoodsID())) {
                msg += "商品ID不可为空。";
            }
            if (ObjectUtils.isEmpty(wscOrdergoods.getGoodsshuxinglistpriceID())) {
                msg += "商品属性价格列表不可为空。";
            }
            if (ObjectUtils.isEmpty(wscOrdergoods.getHuodongID())) {
                msg += "活动ID不可为空。";
            }
            if (ObjectUtils.isEmpty(wscOrdergoods.getNums())) {
                msg += "购买数量不可为空。";
            }
            if (ObjectUtils.isEmpty(wscOrdergoods.getPayMoney())) {
                msg += "支付金额不可为空。";
            }
            ajaxJson.setCode("N");
            ajaxJson.setMsg(msg);
            return ajaxJson;
        }
        QueryWrapper<WscOrdergoods> wrapper = new QueryWrapper<>();
        wrapper.eq("orderNumber", wscOrdergoods.getOrderNumber());
        wrapper.eq("goodsID", wscOrdergoods.getGoodsID());
        int count = wscOrdergoodsService.count(wrapper);
        if (count > 0) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("请勿重复添加");
            return ajaxJson;
        }
        ajaxJson.setSuccess(
                wscOrdergoods
                        .setQiyeID(qiyeID)
                        .insert()
        );
        return ajaxJson;
    }

    /**
     * 修改定单商品
     */
    @PostMapping("/updaeWscOrderGoodsByID")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("修改定单商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ordernumber", value = "订单号", required = true),
            @ApiImplicitParam(name = "goodsid", value = "商品id", required = true),
            @ApiImplicitParam(name = "goodsshuxinglistpriceid", value = "商品属性价格列表id", required = true),
            @ApiImplicitParam(name = "pingtuanfaqirenid", value = "拼团发起人ID"),
            @ApiImplicitParam(name = "huodongid", value = "活动ID,0表示普通商品，未参加活动", required = true),
            @ApiImplicitParam(name = "nums", value = "购买数量", required = true),
            @ApiImplicitParam(name = "paymoney", value = "支付金额", required = true),
            @ApiImplicitParam(name = "pingjia", value = "评价"),
            @ApiImplicitParam(name = "pingjiatype", value = "评价类别：0.非常满意 1.满意 2.一般 3.很糟糕"),
            @ApiImplicitParam(name = "pingjiadate", value = "评价时间"),
            @ApiImplicitParam(name = "qiyeid", value = "企业ID"),
    })
    public AjaxJson updaeWscOrderGoodsByID(HttpServletRequest request, WscOrdergoods wscOrdergoods) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        if (ObjectUtils.isEmpty(wscOrdergoods.getId())) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("ID不可为空");
            return ajaxJson;
        }

        if (!ObjectUtils.isEmpty(wscOrdergoods.getGoodsID())) {
            WscOrdergoods byId = wscOrdergoodsService.getById(wscOrdergoods.getId());
            QueryWrapper<WscOrdergoods> wrapper = new QueryWrapper<>();
            wrapper
                    .eq("orderNumber", byId.getOrderNumber())
                    .eq("goodsID", wscOrdergoods.getGoodsID())
                    .eq("qiyeID", qiyeID)
                    .ne("id", wscOrdergoods.getId());
            int count = wscOrdergoodsService.count(wrapper);
            if (count > 0) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("系统已存在相同记录");
                return ajaxJson;
            }
        }
        ajaxJson.setSuccess(
                wscOrdergoods
                        .setOrderNumber(null) //不允许修改订单号
                        .setQiyeID(qiyeID)
                        .updateById()
        );
        return ajaxJson;
    }

    /**
     * 根据id批量删除订单商品
     * 请求方式：/path/1,2,3
     */
    @DeleteMapping("/deleteWscOrderGoodsByIds/{ids}")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("根据id批量删除订单商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "商品类别ids", required = true),
    })
    public AjaxJson deleteWscOrderGoodsByIds(@PathVariable List<String> ids) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(wscOrdergoodsService.removeByIds(ids));
        return ajaxJson;
    }

    //endregion

    //region 砍价帮砍

    /**
     * 根据砍价发起ID分页查询砍价帮砍记录
     */
    @GetMapping("/getWscKanHelpRecordPage")
    @ResponseBody
    @ApiOperation("根据砍价发起ID分页查询砍价帮砍记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "kanjiaFaqiID", value = "砍价发起ID、", required = true),
            @ApiImplicitParam(name = "userName", value = "帮砍人名"),
    })
    public AjaxJson getWscKanHelpRecordPage(HttpServletRequest request, long size, long current, long kanjiaFaqiID, String userName) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<WscKanHelpRecordVo> wrapper = new QueryWrapper<>();
        if (!ObjectUtils.isEmpty(userName)) {
            wrapper.like("b.userName", userName);
        }
        wrapper
                .eq("a.qiyeID", qiyeID)
                .eq("a.kanjiaFaqiID", kanjiaFaqiID);
        Page<WscKanHelpRecordVo> page = wscKanjiaBangkanrecordService.getWscKanHelpRecordPage(new Page<>(current, size), wrapper);
        return ajaxJson;
    }

    /**
     * wsc添加砍价帮砍记录
     */
    @PostMapping("/addWscKanHelpRecord")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("wsc添加砍价帮砍记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "kanjiafaqiid", value = "砍价发起ID", required = true),
            @ApiImplicitParam(name = "bangkanrenwxuserid", value = "帮砍人ID", required = true),
            @ApiImplicitParam(name = "kanmoney", value = "帮砍价格", required = true),
            @ApiImplicitParam(name = "addtime", value = "添加时间"),
            @ApiImplicitParam(name = "qiyeid", value = "企业ID"),
    })
    public AjaxJson addWscKanHelpRecord(HttpServletRequest request, WscKanjiaBangkanrecord wscKanjiaBangkanrecord) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        String msg = "";
        if (ObjectUtils.isEmpty(wscKanjiaBangkanrecord.getKanjiaFaqiID())) {
            msg += "砍价发起ID不可为空。";
        }
        if (ObjectUtils.isEmpty(wscKanjiaBangkanrecord.getBangkanrenWxUserID())) {
            msg += "帮砍人ID不可为空。";
        }
        if (ObjectUtils.isEmpty(wscKanjiaBangkanrecord.getKanMoney())) {
            msg += "帮砍价格不可为空。";
        }
        if (!ObjectUtils.isEmpty(msg)) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg(msg);
            return ajaxJson;
        }
        ajaxJson.setSuccess(
                wscKanjiaBangkanrecord
                        .setAddTime(new Date())
                        .setQiyeID(qiyeID)
                        .insert()
        );
        return ajaxJson;
    }

    //endregion

    //region 砍价发起记录

    /**
     * 分页查询砍价发起记录
     */
    @GetMapping("/getWscKanjiaFaqiRecordPage")
    @ResponseBody
    @ApiOperation("分页查询砍价发起记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "userName", value = "用户名"),
            @ApiImplicitParam(name = "goodsName", value = "商品名"),
    })
    public AjaxJson getWscKanjiaFaqiRecordPage(HttpServletRequest request, long size, long current, String userName, String goodsName) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<WscKanjiaFaqiRecordVo> wrapper = new QueryWrapper<>();
        if (!ObjectUtils.isEmpty(userName)) {
            wrapper.like("b.userName", userName);
        }
        if (!ObjectUtils.isEmpty(goodsName)) {
            wrapper.like("c.goodsName", goodsName);
        }
        wrapper.eq("a.qiyeID", qiyeID);
        Page<WscKanjiaFaqiRecordVo> page = wscKanjiaFaqirecordService.getWscKanjiaFaqiRecordPage(new Page<>(current, size), wrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 添加砍价发起记录
     */
    @PostMapping("/addWscKanjiaFaqiRecord")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @ApiOperation("添加砍价发起记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "kanjiagoodsid", value = "商品ID", required = true),
            @ApiImplicitParam(name = "goodsshuxinglistpriceid", value = "商品属性ID", required = true),
            @ApiImplicitParam(name = "kanjiafaqirenwxuserid", value = "砍价发起人ID", required = true),
            @ApiImplicitParam(name = "minmoney", value = "砍价低价", required = true),
            @ApiImplicitParam(name = "startmoney", value = "砍价原价", required = true),
            @ApiImplicitParam(name = "currentmoney", value = "当前价"),
            @ApiImplicitParam(name = "addtime", value = "添加时间"),
            @ApiImplicitParam(name = "qiyeid", value = "qiyeID"),
    })
    public AjaxJson addWscKanjiaFaqiRecord(HttpServletRequest request, WscKanjiaFaqirecord wscKanjiaFaqirecord) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        String msg = "";
        if (ObjectUtils.isEmpty(wscKanjiaFaqirecord.getKanjiaGoodsID())) {
            msg += "商品不可为空。";
        }
        if (ObjectUtils.isEmpty(wscKanjiaFaqirecord.getGoodsshuxinglistpriceID())) {
            msg += "商品属性不可为空。";
        }
        if (ObjectUtils.isEmpty(wscKanjiaFaqirecord.getKanjiaFaqiRenWxUserID())) {
            msg += "砍价发起人不可为空。";
        }
        if (ObjectUtils.isEmpty(wscKanjiaFaqirecord.getMinMoney())) {
            msg += "砍价低价不可为空。";
        }
        if (ObjectUtils.isEmpty(wscKanjiaFaqirecord.getStartMoney())) {
            msg += "砍价原价不可为空。";
        }
        if (!ObjectUtils.isEmpty(msg)) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg(msg);
            return ajaxJson;
        }
        if (ObjectUtils.isEmpty(wscKanjiaFaqirecord.getCurrentMoney())) {
            wscKanjiaFaqirecord.setCurrentMoney(wscKanjiaFaqirecord.getStartMoney());
        }
        ajaxJson.setSuccess(
                wscKanjiaFaqirecord
                        .setAddTime(new Date())
                        .setQiyeID(qiyeID)
                        .insert()
        );
        return ajaxJson;
    }

    /**
     * 修改砍价发起记录
     */
    @PostMapping("/updateWscKanjiaFaqiRecordByID")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @ApiOperation("添加砍价发起记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true),
            @ApiImplicitParam(name = "kanjiagoodsid", value = "商品ID"),
            @ApiImplicitParam(name = "goodsshuxinglistpriceid", value = "商品属性ID"),
            @ApiImplicitParam(name = "kanjiafaqirenwxuserid", value = "砍价发起人ID"),
            @ApiImplicitParam(name = "minmoney", value = "砍价低价"),
            @ApiImplicitParam(name = "startmoney", value = "砍价原价"),
            @ApiImplicitParam(name = "currentmoney", value = "当前价"),
    })
    public AjaxJson updateWscKanjiaFaqiRecordByID(HttpServletRequest request, WscKanjiaFaqirecord wscKanjiaFaqirecord) {
        AjaxJson ajaxJson = new AjaxJson();
        if (ObjectUtils.isEmpty(wscKanjiaFaqirecord.getId())) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("修改失败请返回重试。");
            return ajaxJson;
        }
        ajaxJson.setSuccess(
                wscKanjiaFaqirecord
                        .setQiyeID(null)
                        .setAddTime(null)
                        .updateById());
        return ajaxJson;
    }

    /**
     * 根据id批量删除发起的砍价活动
     */
    @DeleteMapping("/deleteWscKanjiaFaqiRecordByIDs/{ids}")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("根据id批量删除发起的砍价活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "发起的砍价活动ids", required = true),
    })
    public AjaxJson deleteWscKanjiaFaqiRecordByIDs(HttpServletRequest request, @PathVariable List<String> ids) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(wscKanjiaFaqirecordService.removeByIds(ids));
        return ajaxJson;
    }

    //endregion

    //region 拼团发起记录

    /**
     * 分页查询拼团发起记录
     */
    @GetMapping("/getWscPingtuanFaqiRecordPage")
    @ResponseBody
    @ApiOperation("分页查询拼团发起记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "userName", value = "用户名"),
            @ApiImplicitParam(name = "goodsName", value = "商品名"),
    })
    public AjaxJson getWscPingtuanFaqiRecordPage(HttpServletRequest request, long size, long current, String userName, String goodsName) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<WscPingtuanFaqiRecordVo> wrapper = new QueryWrapper<>();
        wrapper.eq("a.qiyeID", qiyeID);
        if (!ObjectUtils.isEmpty(userName)) {
            wrapper.like("b.userName", userName);
        }
        if (!ObjectUtils.isEmpty(goodsName)) {
            wrapper.like("c.goodsName", goodsName);
        }
        Page<WscPingtuanFaqiRecordVo> page = wscPingtuanFaqirecordService.getWscPingtuanFaqiPage(new Page<>(current, size), wrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 添加拼团发起记录
     */
    @PostMapping("/addWscPingtuanFaqiRecord")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @ApiOperation("添加拼团发起记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pingtuangoodsid", value = "拼团商品ID", required = true),
            @ApiImplicitParam(name = "goodsshuxinglistpriceid", value = "拼团商品价格属性列表ID", required = true),
            @ApiImplicitParam(name = "pingtuanfaqirenwxuserid", value = "拼团发起人wxUserID", required = true),
            @ApiImplicitParam(name = "faqirenorderid", value = "拼团发起人的订单ID", required = true),
    })
    public AjaxJson addWscPingtuanFaqiRecord(HttpServletRequest request, WscPingtuanFaqirecord wscPingtuanFaqirecord) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        String msg = "";
        if (ObjectUtils.isEmpty(wscPingtuanFaqirecord.getPingtuanGoodsID())) {
            msg += "拼团商品不可为空。";
        }
        if (ObjectUtils.isEmpty(wscPingtuanFaqirecord.getGoodsshuxinglistpriceID())) {
            msg += "拼团商品价格属性列表不可为空。";
        }
        if (ObjectUtils.isEmpty(wscPingtuanFaqirecord.getPingtuanFaqiRenWxUserID())) {
            msg += "拼团发起人不可为空。";
        }
        if (ObjectUtils.isEmpty(wscPingtuanFaqirecord.getFaqiRenOrderID())) {
            msg += "拼团发起人的订单不可为空。";
        }
        if (!ObjectUtils.isEmpty(msg)) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg(msg);
            return ajaxJson;
        }
        QueryWrapper<WscPingtuanFaqirecord> wrapper = new QueryWrapper<>();
        wrapper.eq("faqiRenOrderID", wscPingtuanFaqirecord.getFaqiRenOrderID());
        int count = wscPingtuanFaqirecordService.count(wrapper);
        if (count > 0) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("请勿重复添加。");
            return ajaxJson;
        }
        ajaxJson.setSuccess(
                wscPingtuanFaqirecord
                        .setAddTime(new Date())
                        .setQiyeID(qiyeID)
                        .insert()
        );
        return ajaxJson;
    }

    /**
     * 根据ID更新拼团发起记录
     */
    @PostMapping("/updateWscPingtuanFaqiRecordByID")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @ApiOperation("根据ID更新拼团发起记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pingtuangoodsid", value = "拼团商品ID"),
            @ApiImplicitParam(name = "goodsshuxinglistpriceid", value = "拼团商品价格属性列表ID"),
    })
    public AjaxJson updateWscPingtuanFaqiRecordByID(HttpServletRequest request, WscPingtuanFaqirecord wscPingtuanFaqirecord) {
        AjaxJson ajaxJson = new AjaxJson();
        if (ObjectUtils.isEmpty(wscPingtuanFaqirecord.getId())) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("粗错鸟，请返回重试");
            return ajaxJson;
        }
        ajaxJson.setSuccess(
                wscPingtuanFaqirecord
                        .setAddTime(null)
                        .setQiyeID(null)
                        .setFaqiRenOrderID(null)
                        .setPingtuanFaqiRenWxUserID(null)
                        .updateById()
        );
        return ajaxJson;
    }

    /**
     * 根据id批量删除拼团发起记录
     */
    @DeleteMapping("/deleteWscPingtuanFaqiRecordByIDs/{ids}")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("根据id批量删除拼团发起记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "拼团发起记录ids", required = true),
    })
    public AjaxJson deleteWscPingtuanFaqiRecordByIDs(HttpServletRequest request, @PathVariable List<String> ids) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(wscPingtuanFaqirecordService.removeByIds(ids));
        return ajaxJson;
    }

    //endregion

    //region 拼团参与记录

    /**
     * 分页查询拼团参与记录
     */
    @GetMapping("/getWscPingtuanJoinRecordPage")
    @ResponseBody
    @ApiOperation("分页查询拼团参与记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "userName", value = "用户名"),
            @ApiImplicitParam(name = "goodsName", value = "商品名"),
    })
    public AjaxJson getWscPingtuanJoinRecordPage(HttpServletRequest request, long size, long current, String userName, String goodsName) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<WscPingtuanFaqiRecordVo> wrapper = new QueryWrapper<>();
        wrapper.eq("a.qiyeID", qiyeID);
        if (!ObjectUtils.isEmpty(userName)) {
            wrapper.like("f.userName", userName);
        }
        if (!ObjectUtils.isEmpty(goodsName)) {
            wrapper.like("b.goodsName", goodsName);
        }
        Page<WscPingtuanJoinRecordVo> page = wscPingtuanJoinrecordService.getWscPingtuanJoinRecordPage(new Page<>(current, size), wrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 添加拼团发起记录
     */
    @PostMapping("/addWscPingtuanJoinRecord")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @ApiOperation("添加拼团发起记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pingtuangoodsid", value = "拼团商品ID", required = true),
            @ApiImplicitParam(name = "goodsshuxinglistpriceid", value = "拼团商品价格属性列表ID", required = true),
            @ApiImplicitParam(name = "pingtuanfaqirecordid", value = "拼团发起记录ID", required = true),
            @ApiImplicitParam(name = "joinrenorderid", value = "拼团参与人的订单ID", required = true),
            @ApiImplicitParam(name = "joinrenwxuserid", value = "拼团参与人的wxUserID", required = true),
    })
    public AjaxJson addWscPingtuanJoinRecord(HttpServletRequest request, WscPingtuanJoinrecord wscPingtuanJoinrecord) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        String msg = "";
        if (ObjectUtils.isEmpty(wscPingtuanJoinrecord.getPingtuanGoodsID())) {
            msg += "拼团商品不可为空。";
        }
        if (ObjectUtils.isEmpty(wscPingtuanJoinrecord.getGoodsshuxinglistpriceID())) {
            msg += "拼团商品价格属性列表不可为空。";
        }
        if (ObjectUtils.isEmpty(wscPingtuanJoinrecord.getPingtuanFaqiRecordID())) {
            msg += "拼团发起订单不可为空。";
        }
        if (ObjectUtils.isEmpty(wscPingtuanJoinrecord.getJoinRenOrderID())) {
            msg += "拼团参与人的订单不可为空。";
        }
        if (ObjectUtils.isEmpty(wscPingtuanJoinrecord.getJoinRenWxUserID())) {
            msg += "拼团参与人不可为空。";
        }
        if (!ObjectUtils.isEmpty(msg)) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg(msg);
            return ajaxJson;
        }
        QueryWrapper<WscPingtuanJoinrecord> wrapper = new QueryWrapper<>();
        wrapper
                .eq("joinRenWxUserID", wscPingtuanJoinrecord.getJoinRenWxUserID())
                .eq("pingtuanFaqiRecordID", wscPingtuanJoinrecord.getPingtuanFaqiRecordID());
        int count = wscPingtuanJoinrecordService.count(wrapper);
        if (count > 0) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("请勿重复添加。");
            return ajaxJson;
        }
        ajaxJson.setSuccess(
                wscPingtuanJoinrecord
                        .setAddTime(LocalDateTime.now())
                        .setQiyeID(qiyeID)
                        .insert()
        );
        return ajaxJson;
    }

    /**
     * 根据ID更新拼团发起记录
     */
    @PostMapping("/updateWscPingtuanJoinRecordByID")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @ApiOperation("根据ID更新拼团发起记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pingtuangoodsid", value = "拼团商品ID"),
            @ApiImplicitParam(name = "goodsshuxinglistpriceid", value = "拼团商品价格属性列表ID"),
    })
    public AjaxJson updateWscPingtuanJoinRecordByID(HttpServletRequest request, WscPingtuanJoinrecord wscPingtuanJoinrecord) {
        AjaxJson ajaxJson = new AjaxJson();
        if (ObjectUtils.isEmpty(wscPingtuanJoinrecord.getId())) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("粗错鸟，请返回重试");
            return ajaxJson;
        }
        ajaxJson.setSuccess(
                wscPingtuanJoinrecord
                        .setAddTime(null)
                        .setQiyeID(null)
                        .setJoinRenOrderID(null)
                        .setJoinRenWxUserID(null)
                        .setPingtuanFaqiRecordID(null)
                        .updateById()
        );
        return ajaxJson;
    }

    /**
     * 根据id批量删除拼团参与记录
     */
    @DeleteMapping("/deletePingtuanJoinRecordByIDs/{ids}")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("根据id批量删除拼团参与记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "拼团参与记录ids", required = true),
    })
    public AjaxJson deletePingtuanJoinRecordByIDs(HttpServletRequest request, @PathVariable List<String> ids) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(wscPingtuanJoinrecordService.removeByIds(ids));
        return ajaxJson;
    }

    //endregion

    //region 用户

    @DeleteMapping("/delwscUser")
    @ResponseBody
    @ApiOperation("删除商城用户")
    public AjaxJson delwscUser(String ids, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        String[] IDs = ids.split(",");
        iWscUserService.remove(new QueryWrapper<WscUser>()
                .eq("qiyeID", loginUser.getQiyeID())
                .in("id", IDs)
        );
        return ajaxJson;
    }


    @PostMapping("dongjieOrNotwscUser")
    @ResponseBody
    @ApiOperation("冻结/解冻商城用户")
    public AjaxJson dongjieOrNotwscUser(String ids, int type, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        String[] IDs = ids.split(",");
        List<WscUser> list = iWscUserService.list(new QueryWrapper<WscUser>()
                .eq("qiyeID", loginUser.getQiyeID())
                .in("id", IDs)
        );

        if (list.size() > 0) {
            for (WscUser item : list) {
                if (type == 1) {
                    item.setIsdongjie(1);
                } else {
                    item.setIsdongjie(0);
                }
                iWscUserService.updateById(item);
            }
        }
        return ajaxJson;
    }


    @PostMapping("buytuke")
    @ResponseBody
    @ApiOperation("升级为推客")
    public AjaxJson buytuke(String wscUserID, String realName,
                            String phone,
                            String buyTuikeLevelID,
                            String shuoming,
                            HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        WscUser wuser = iWscUserService.getById(wscUserID);
        WscTuikelevel butlv = iWscTuikelevelService.getById(buyTuikeLevelID);
        WscTuikeBuy one = new WscTuikeBuy();
        one.setWscUserId(wuser.getId())
                .setRealName(realName)
                .setPhone(phone)
                .setOldTuikeLevelID(0L)
                .setBuyTuikeLevelID(butlv.getId())
                .setPaymoney(butlv.getTiaojianMoney())
                .setBuyTime(new Date())
                .setShuoming(shuoming).setQiyeID(loginUser.getQiyeID());
        iWscTuikeBuyService.save(one);

        wuser.setTuikeLevelID(Long.valueOf(buyTuikeLevelID));
        iWscUserService.updateById(wuser);
        return ajaxJson;
    }


    /**
     * 分页查询商城用户
     */
    @GetMapping("/getWscUserPage")
    @ResponseBody
    @ApiOperation("分页查询商城用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "diqu", value = "地区"),
            @ApiImplicitParam(name = "addr", value = "地址"),
            @ApiImplicitParam(name = "userType", value = "用户类型"),
            @ApiImplicitParam(name = "sex", value = "性别"),
            @ApiImplicitParam(name = "isdongjie", value = "是否冻结：0未冻结，1冻结"),
            @ApiImplicitParam(name = "isKcUser", value = "是否是课程用户，1不是，2是"),
            @ApiImplicitParam(name = "userName", value = "课程用户对应的学员姓名"),
            @ApiImplicitParam(name = "userTel", value = "课程用户对应的手机号"),
    })
    public AjaxJson getWscUserPage(HttpServletRequest request, long size, long current, String userName, String userTel, String nickName,
                                   String diqu, String fidName, String Sdate, String Edate) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<WscUser> wrapper = new QueryWrapper<>();
        wrapper.eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(userName)) {
            wrapper.like("a.userName", userName);
        }
        if (StringUtils.isNotBlank(userTel)) {
            wrapper.like("a.userTel", userTel);
        }
        if (StringUtils.isNotBlank(nickName)) {
            wrapper.like("a.nickName", nickName);
        }
        if (StringUtils.isNotBlank(diqu)) {
            wrapper.like("a.diqu", diqu);
        }
        if (StringUtils.isNotBlank(fidName)) {
            wrapper.like("a.fidName", fidName);
        }
        if (StringUtils.isNotBlank(Sdate) && StringUtils.isNotBlank(Edate)) {
            wrapper.ge("a.addTime", Sdate).le("a.addTime", Edate);
        }
        Page<HashMap<String, Object>> page = wscUserService.GetWscUserPages(new Page(current, size), wrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }


    /**
     * 添加商城用户
     */
    @PostMapping("/addWscUser")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @ApiOperation("添加商城用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "微信用户的唯一标识", required = true),
            @ApiImplicitParam(name = "usertype", value = "用户类型，1微信用户，2抖音用户", required = true),
            @ApiImplicitParam(name = "unionid", value = ""),
            @ApiImplicitParam(name = "headImage", value = "头像"),
            @ApiImplicitParam(name = "sex", value = "性别"),
            @ApiImplicitParam(name = "diqu", value = "地区"),
            @ApiImplicitParam(name = "addr", value = "地址"),
            @ApiImplicitParam(name = "isdongjie", value = "是否被冻结；  0未冻结，1冻结，默认0"),
            @ApiImplicitParam(name = "iskcuser", value = "是否是课程用户，1不是，2是，默认1"),
            @ApiImplicitParam(name = "username", value = "课程用户对应的学员姓名"),
            @ApiImplicitParam(name = "usertel", value = "课程用户对应的手机号"),
            @ApiImplicitParam(name = "fid", value = "父ID"),
            @ApiImplicitParam(name = "gfid", value = "祖父ID"),
            @ApiImplicitParam(name = "scremainmoney", value = "商城余额。默认值0"),
            @ApiImplicitParam(name = "scjifen", value = "商城积分。默认值0"),
            @ApiImplicitParam(name = "scremainyongjin", value = "商城剩余佣金。默认值0"),
            @ApiImplicitParam(name = "scweijieyongjin", value = "商城未结佣金。默认值0"),
            @ApiImplicitParam(name = "scyijieyongjin", value = "商城已结佣金。默认值0"),
            @ApiImplicitParam(name = "qiyeid", value = "企业ID"),
    })
    public AjaxJson addWscUser(HttpServletRequest request, WscUser wscUser) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        String msg = "";
        if (ObjectUtils.isEmpty(wscUser.getOpenid())) {
            msg += "openID不可为空。";
        }
        if (ObjectUtils.isEmpty(wscUser.getUserType())) {
            msg += "用户类型不可为空。";
        }
        if (!ObjectUtils.isEmpty(msg)) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg(msg);
            return ajaxJson;
        }
        QueryWrapper<WscUser> wrapper = new QueryWrapper<>();
        wrapper
                .eq("qiyeID", qiyeID)
                .eq("openid", wscUser.getOpenid());
        int count = wscUserService.count(wrapper);
        if (count > 0) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("请勿重复添加。");
            return ajaxJson;
        }
        ajaxJson.setSuccess(
                wscUser
                        .setQiyeID(qiyeID)
                        .setAddTime(new Date())
                        .insert()
        );
        return ajaxJson;
    }

    /**
     * 根据ID更新商城用户
     */
    @PostMapping("/updateWscUserByID")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @ApiOperation("根据ID更新商城用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true),
            @ApiImplicitParam(name = "unionid", value = ""),
            @ApiImplicitParam(name = "headImage", value = "头像"),
            @ApiImplicitParam(name = "sex", value = "性别"),
            @ApiImplicitParam(name = "diqu", value = "地区"),
            @ApiImplicitParam(name = "addr", value = "地址"),
            @ApiImplicitParam(name = "usertype", value = "用户类型，1微信用户，2抖音用户"),
            @ApiImplicitParam(name = "isdongjie", value = "是否被冻结；  0未冻结，1冻结，默认0"),
            @ApiImplicitParam(name = "iskcuser", value = "是否是课程用户，1不是，2是，默认1"),
            @ApiImplicitParam(name = "username", value = "课程用户对应的学员姓名"),
            @ApiImplicitParam(name = "usertel", value = "课程用户对应的手机号"),
            @ApiImplicitParam(name = "scremainmoney", value = "商城余额。"),
            @ApiImplicitParam(name = "scjifen", value = "商城积分。"),
            @ApiImplicitParam(name = "scremainyongjin", value = "商城剩余佣金。"),
            @ApiImplicitParam(name = "scweijieyongjin", value = "商城未结佣金。"),
            @ApiImplicitParam(name = "scyijieyongjin", value = "商城已结佣金。"),
    })
    public AjaxJson updateWscUserByID(WscUser wscUser) {
        AjaxJson ajaxJson = new AjaxJson();
        if (ObjectUtils.isEmpty(wscUser.getId())) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("粗错鸟，请返回重试。");
            return ajaxJson;
        }
        ajaxJson.setSuccess(
                wscUser
                        .setOpenid(null)
                        .setFid(null)
                        .setGfid(null)
                        .setQiyeID(null)
                        .setAddTime(null)
                        .updateById()
        );
        return ajaxJson;
    }

    @DeleteMapping("/deleteWscUserByIDs/{ids}")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("根据id批量删除商城用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "用户ids", required = true),
    })
    public AjaxJson deleteWscUserByIDs(HttpServletRequest request, @PathVariable List<String> ids) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(wscUserService.removeByIds(ids));
        return ajaxJson;
    }

    //endregion

    //region 佣金比例

    /**
     * 分页查询佣金比例
     */
    @GetMapping("/getWscYongjinBiliPage")
    @ResponseBody
    @ApiOperation("分页查询佣金比例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页数", example = "1", required = true),
    })
    public AjaxJson getWscYongjinBiliPage(HttpServletRequest request, long size, long current) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<WscYongjinbili> wrapper = new QueryWrapper<>();
        wrapper.eq("qiyeID", qiyeID);
        Page<WscYongjinbili> page = wscYongjinbiliService.page(new Page<>(current, size), wrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 添加佣金比例
     */
    @PostMapping("/addWscYongjinBili")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @ApiOperation("添加佣金比例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jibei", value = "级别", required = true),
            @ApiImplicitParam(name = "bili", value = "比例", required = true),
            @ApiImplicitParam(name = "qiyeid", value = "企业ID"),
    })
    public AjaxJson addWscYongjinBili(HttpServletRequest request, WscYongjinbili wscYongjinbili) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        String msg = "";
        if (ObjectUtils.isEmpty(wscYongjinbili.getJibei())) {
            msg += "级别不可为空。";
        }
        if (ObjectUtils.isEmpty(wscYongjinbili.getBili())) {
            msg += "比例不可为空。";
        }
        if (!ObjectUtils.isEmpty(msg)) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg(msg);
            return ajaxJson;
        }
        QueryWrapper<WscYongjinbili> wrapper = new QueryWrapper<>();
        wrapper
                .eq("jibei", wscYongjinbili.getJibei())
                .eq("qiyeID", qiyeID);
        int count = wscYongjinbiliService.count(wrapper);
        if (count > 0) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("请勿重复添加。");
            return ajaxJson;
        }
        ajaxJson.setSuccess(
                wscYongjinbili
                        .setQiyeID(qiyeID)
                        .insert()
        );
        return ajaxJson;
    }

    /**
     * 根据ID更新佣金比例
     */
    @PostMapping("/updateWscYongjinBiliByID")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @ApiOperation("根据ID更新佣金比例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jibei", value = "级别"),
            @ApiImplicitParam(name = "bili", value = "比例"),
    })
    public AjaxJson updateWscYongjinBiliByID(HttpServletRequest request, WscYongjinbili wscYongjinbili) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        String msg = "";
        if (ObjectUtils.isEmpty(wscYongjinbili.getId())) {
            msg += "粗错鸟。";
        }
        if (!ObjectUtils.isEmpty(msg)) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg(msg);
            return ajaxJson;
        }
        if (!ObjectUtils.isEmpty(wscYongjinbili.getJibei())) {
            QueryWrapper<WscYongjinbili> wrapper = new QueryWrapper<>();
            wrapper
                    .ne("id", wscYongjinbili.getId())
                    .eq("jibei", wscYongjinbili.getJibei())
                    .eq("qiyeID", qiyeID);
            int count = wscYongjinbiliService.count(wrapper);
            if (count > 0) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("系统已存在相同级别。");
                return ajaxJson;
            }
        }
        ajaxJson.setSuccess(
                wscYongjinbili
                        .setQiyeID(null)
                        .updateById()
        );
        return ajaxJson;
    }

    /**
     * 根据id批量删除佣金比例
     */
    @DeleteMapping("/deleteWscYongjinBiliByIDs/{ids}")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("根据id批量删除佣金比例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "佣金比例ids", required = true),
    })
    public AjaxJson deleteWscYongjinBiliByIDs(HttpServletRequest request, @PathVariable List<String> ids) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(wscYongjinbiliService.removeByIds(ids));
        return ajaxJson;
    }

    //endregion

    //region 商品管理
    //endregion

    //region 订单评价管理

    /**
     * 分页查询订单评价信息
     *
     * @param request
     * @param size
     * @param current
     * @param orderNumber
     * @param pingjiaType
     * @param pingjiaren
     * @return
     */
    @RequestMapping(value = "/getorderpingjiaPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页查询订单评价信息")
    public AjaxJson getorderpingjiaPages(HttpServletRequest request, long size, long current, String orderNumber, long pingjiaType, String pingjiaren
            , String Sdate, String Edate, Integer searchType) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<HashMap<String, Object>> wrapper = new QueryWrapper<>();
        wrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(orderNumber)) {
            wrapper.like("b.orderNumber", orderNumber);
        }
        if (pingjiaType != 0) {
            wrapper.eq("a.pingjiaType", pingjiaType);
        }
        if (StringUtils.isNotBlank(pingjiaren)) {
            wrapper.like("c.nickName", pingjiaren);
        }
        if (StringUtils.isNotBlank(Sdate) && StringUtils.isNotBlank(Edate)) {
            wrapper.ge("a.pingjiaDate", Sdate).le("a.pingjiaDate", Edate);
        }
        if (searchType != 0) {
            LocalDate now = LocalDate.now();
            switch (searchType) {
                case 2: {
//                    今日
                    wrapper.between("a.pingjiaDate", now, now.toString() + " 23:59:59");
                    break;
                }
                case 3: {
//                    本周
                    int dayOfWeek = now.getDayOfWeek().getValue();
                    LocalDate start = now.plusDays(1 - dayOfWeek);
                    LocalDate end = now.plusDays(7 - dayOfWeek);
                    wrapper.between("a.pingjiaDate", start, end.toString() + " 23:59:59");
                    break;
                }
                case 4: {
//                    本月
                    LocalDate start = now.with(TemporalAdjusters.firstDayOfMonth());
                    LocalDate end = now.with(TemporalAdjusters.lastDayOfMonth());
                    wrapper.between("a.pingjiaDate", start, end.toString() + " 23:59:59");
                    break;
                }
            }
        }
        Page<HashMap<String, Object>> page = wscOrdergoodsService.GetAllOrderPingjiaPage(new Page(current, size), wrapper);
        ajaxJson.setObj(page);
        ajaxJson.setCode("success");
        return ajaxJson;
    }
    //endregion

    //region 销售流水
    @RequestMapping(value = "/getSqleLiushuiPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询销售流水信息")
    public AjaxJson getSqleLiushuiPages(HttpServletRequest request, long size, long current, Integer searchType, String Sdate, String Edate) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<HashMap<String, Object>> wrapper = new QueryWrapper<>();
        wrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(Sdate) && StringUtils.isNotBlank(Edate)) {
            wrapper.ge("a.luruTime", Sdate).le("a.luruTime", Edate);
        }
        if (searchType != 0) {
            LocalDate now = LocalDate.now();
            switch (searchType) {
                case 2: {
//                    今日
                    wrapper.between("a.luruTime", now, now.toString() + " 23:59:59");
                    break;
                }
                case 3: {
//                    本周
                    int dayOfWeek = now.getDayOfWeek().getValue();
                    LocalDate start = now.plusDays(1 - dayOfWeek);
                    LocalDate end = now.plusDays(7 - dayOfWeek);
                    wrapper.between("a.luruTime", start, end.toString() + " 23:59:59");
                    break;
                }
                case 4: {
//                    本月
                    LocalDate start = now.with(TemporalAdjusters.firstDayOfMonth());
                    LocalDate end = now.with(TemporalAdjusters.lastDayOfMonth());
                    wrapper.between("a.luruTime", start, end.toString() + " 23:59:59");
                    break;
                }
            }
        }
        Page<HashMap<String, Object>> page = wscOrderService.GetsqleLiushuiPages(new Page(current, size), wrapper);
        ajaxJson.setObj(page);
        ajaxJson.setCode("success");
        return ajaxJson;
    }
    //endregion

    //region 三级返佣


    @ResponseBody
    @GetMapping("gettixianshenhePage")
    @ApiOperation("提现审核")
    public AjaxJson gettixianshenhePage(long current, long size,
                                        int shenghestate,
                                        String nickName,
                                        String userName,
                                        String bankcardID,
                                        String kaihuhang,
                                        String shenpiren,

                                        String sqstartTime,
                                        String sqendTime,

                                        String startTime,
                                        String endTime,
                                        HttpServletRequest request
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<HashMap<String, Object>> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(nickName)) {
            queryWrapper.like("b.nickName", nickName);
        }
        if (StringUtils.isNotBlank(userName)) {
            queryWrapper.like("a.realName", userName);
        }
        if (StringUtils.isNotBlank(bankcardID)) {
            queryWrapper.like("a.bankCard", bankcardID);
        }
        if (StringUtils.isNotBlank(kaihuhang)) {
            queryWrapper.like("a.bankName", kaihuhang);
        }
        if (StringUtils.isNotBlank(shenpiren)) {
            queryWrapper.like("c.staffName", shenpiren);
        }
        if (StringUtils.isNotBlank(sqstartTime) && StringUtils.isNotBlank(sqendTime)) {
            queryWrapper.between("a.shengqingTime", sqstartTime, sqendTime);
        }
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            queryWrapper.between("a.tixianShengpiTime", startTime, endTime);
        }

        if (shenghestate != -1) {
            queryWrapper.eq("a.tixianShengpiState", shenghestate);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 129);
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
        Page<HashMap<String, Object>> page = iWscTixianService.gettixianPage(new Page(current, size), queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }


    @PostMapping("PassOrNoshenhetixian")
    @ApiOperation("审核提现")
    @ResponseBody
    public AjaxJson PassOrNoshenhetixian(HttpServletRequest request, int type, String id, String shengpishuoming) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        WscTixian shenhe = iWscTixianService.getById(id);
        if (shenhe.getTixianShengpiState() != 1) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("不是待审批状态");
            return ajaxJson;
        }

        if (type == 1) {
            //审核通过
            shenhe.setTixianShengpiState(3);

        } else if (type == 2) {
            //审核不通过
            shenhe.setTixianShengpiState(2);
        }
        shenhe.setTixianShengpiTime(new Date());
        shenhe.setTixianShengpiStaffID(loginUser.getStaffID());
        shenhe.setShengpiShuoming(shengpishuoming);
        iWscTixianService.updateById(shenhe);

        return ajaxJson;
    }

    @RequestMapping(value = "/getalltuikelv", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取所有推客级别")
    public AjaxJson getalltuikelv(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setObj(iWscTuikelevelService.getAlltklvList(new QueryWrapper<WscTuikelevel>()
                .eq("qiyeID", loginUser.getQiyeID())
                .eq("isShow", 1))
        );
        return ajaxJson;
    }

    @RequestMapping(value = "/GetFangyongJiluPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页查询返佣信息")
    public AjaxJson GetFangyongJiluPages(HttpServletRequest request, long current, long size) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("list.qiyeID", loginUser.getQiyeID());
//        if (StringUtils.isNotBlank(orderNumber)) {
//            queryWrapper.like("list.orderNumber", orderNumber);
//        }
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());


        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 129);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }

        queryWrapper.orderByDesc("a.addTime");
        Page<HashMap<String, Object>> page = wscYongjinService.getfangyongShowPages(new Page(current, size), queryWrapper);

        ajaxJson.setObj(page);
        return ajaxJson;
    }


    @RequestMapping(value = "/GetbuytuikeinfoPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页查询购买成为推客记录")
    public AjaxJson GetbuytuikeinfoPages(HttpServletRequest request, long current,
                                         long size,
                                         String nickName,
                                         String userName,
                                         String phoneNumber,
                                         String oldtkLv,
                                         String nowtkLv,
                                         String starttime,
                                         String endtime
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();

        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(nickName)) {
            queryWrapper.like("b.nickName", nickName);
        }
        if (StringUtils.isNotBlank(userName)) {
            queryWrapper.like("a.realName", userName);
        }
        if (StringUtils.isNotBlank(phoneNumber)) {
            queryWrapper.like("b.phoneNumber", phoneNumber);
        }
        if (StringUtils.isNotBlank(oldtkLv)) {
            queryWrapper.eq("a.oldTuikeLevelID", oldtkLv);
        }
        if (StringUtils.isNotBlank(nowtkLv)) {
            queryWrapper.eq("a.buyTuikeLevelID", nowtkLv);
        }
        if (StringUtils.isNotBlank(starttime) && StringUtils.isNotBlank(endtime)) {
            queryWrapper.between("a.buyTime", starttime, endtime);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 129);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }

        queryWrapper.orderByDesc("a.buyTime");
        Page<HashMap<String, Object>> page = wscYongjinService.getbuytuikePages(new Page(current, size), queryWrapper);

        ajaxJson.setObj(page);
        return ajaxJson;
    }


    @RequestMapping(value = "/gettkteamPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页查看团队")
    public AjaxJson gettkteamPage(HttpServletRequest request, long current,
                                  long size,
                                  String nickName,
                                  String userName,
                                  String phoneNumber
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();

        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(nickName)) {
            queryWrapper.like("b.nickName", nickName);
        }
        if (StringUtils.isNotBlank(userName)) {
            queryWrapper.like("a.realName", userName);
        }
        if (StringUtils.isNotBlank(phoneNumber)) {
            queryWrapper.like("b.phoneNumber", phoneNumber);
        }


        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 129);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }


        Page<HashMap<String, Object>> page = wscYongjinService.gettkteamPage(new Page(current, size), queryWrapper);

        ajaxJson.setObj(page);
        return ajaxJson;
    }


    @RequestMapping(value = "/getfanyongpaiming", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页查看反佣排名")
    public AjaxJson getfanyongpaiming(HttpServletRequest request, long current,
                                      long size,
                                      String nickName,
                                      String userName
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();

        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(nickName)) {
            queryWrapper.like("b.nickName", nickName);
        }
        if (StringUtils.isNotBlank(userName)) {
            queryWrapper.like("a.realName", userName);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 129);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }

        Page<HashMap<String, Object>> page = wscYongjinService.getfanyongpaiming(new Page(current, size), queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }


    @PostMapping("edittuikelevel")
    @ResponseBody
    @ApiOperation("修改推客级别")
    public AjaxJson edittuikelevel(String wscUserID, String shuoming, String newtk_lv, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        WscUser User = iWscUserService.getById(wscUserID);
        WscTuikeBuy tkbuy = iWscTuikeBuyService.getOne(new QueryWrapper<WscTuikeBuy>()
                .eq("wsc_user_id", wscUserID)
                .eq("qiyeID", loginUser.getQiyeID())
                .orderByDesc("buyTime")
                .last(" LIMIT 1 "));

        if (tkbuy == null) {
            ajaxJson.setMsg("请选择已成为推客的成员");
            ajaxJson.setCode("N");
            return ajaxJson;
        } else {

            if (newtk_lv.equals("0")) {
                //修改为普通用户
                ajaxJson.setSuccess(iWscTuikeBuyService.remove(new QueryWrapper<WscTuikeBuy>()
                        .eq("wscUserId", wscUserID)
                        .eq("qiyeID", loginUser.getQiyeID())
                ));
                WscUser updateuser = new WscUser();
                updateuser.setFid(0L);
                iWscUserService.update(new QueryWrapper<WscUser>()
                        .eq("qiyeID", loginUser.getQiyeID())
                        .eq("fid", User.getId()));

                WscUser updateuser2 = new WscUser();
                updateuser2.setGfid(0L);
                iWscUserService.update(new QueryWrapper<WscUser>()
                        .eq("qiyeID", loginUser.getQiyeID())
                        .eq("gfid", User.getId()));

                User.setTuikeLevelID(0L);
                iWscUserService.updateById(User);
            } else {
                //修改
                Long buyTuikeLevelID = tkbuy.getBuyTuikeLevelID();
                WscTuikelevel oldbuylv = iWscTuikelevelService.getById(buyTuikeLevelID);
                WscTuikelevel oldlv = iWscTuikelevelService.getById(tkbuy.getOldTuikeLevelID());

                WscTuikelevel nowlv = iWscTuikelevelService.getById(newtk_lv);
                tkbuy
                        .setOldTuikeLevelID(buyTuikeLevelID)
                        .setBuyTuikeLevelID(Long.valueOf(newtk_lv))
                        .setShuoming("修改推客级别产生说明：修改前 原购买级别【" + oldbuylv.getTuikeLevelName() + "】,原级别：【" + oldlv.getTuikeLevelName() + "】,修改后 购买级别：【" + nowlv.getTuikeLevelName() + "】。修改说明如下：" + shuoming);
                iWscTuikeBuyService.updateById(tkbuy);


                User.setTuikeLevelID(Long.valueOf(newtk_lv));
                iWscUserService.updateById(User);
            }


        }

        return ajaxJson;
    }

    //endregion
}
