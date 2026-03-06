package com.xwcloud.cloud.wsc.Controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.Form.addscordersForm;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.Vo.collectVo;
import com.xwcloud.cloud.model.Vo.guigeshuxingVo;
import com.xwcloud.cloud.model.Vo.ordergoodsVO;
import com.xwcloud.cloud.model.entity.*;
import com.xwcloud.cloud.wsc.Service.*;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 小程序商城部分功能
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-10
 */
@Controller
@RequestMapping("/wsc/wmallcontroller")
public class WmallController {
    @Autowired
    IWscUserService iWscUserService;
    @Autowired
    IWscOrderService iWscOrderService;
    @Autowired
    IWscGoodsService iWscGoodsService;
    @Autowired
    IWscGoodstypeService iWscGoodstypeService;
    @Autowired
    IWscGoodsguigeService iWscGoodsguigeService;
    @Autowired
    IWscGoodsshuxinglistService iWscGoodsshuxinglistService;
    @Autowired
    IWscGoodsshuxinglistpriceService iWscGoodsshuxinglistpriceService;
    @Autowired
    IWscShoppingcatService iWscShoppingcatService;
    @Autowired
    IWscUserAddressService iWscUserAddressService;
    @Autowired
    IWscAddresstypeService iWscAddresstypeService;
    @Autowired
    IWhdCouponsService iWhdCouponsService;
    @Autowired
    IWhdUsercouponsService iWhdUsercouponsService;
    @Autowired
    IWscCollectService iWscCollectService;
    @Autowired
    IWscUserjiaoyiService iWscUserjiaoyiService;
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
    IPxqiandaninfotableService iPxqiandaninfotableService;
    @Autowired
    IPxqiandanstafftableService iPxqiandanstafftableService;
    @Autowired
    IPxqiandaninfo2tableService iPxqiandaninfo2tableService;
    @Autowired
    IPxkechengtableService iPxkechengtableService;
    @Autowired
    IPxsubjecttableService iPxsubjecttableService;
    @Autowired
    IPxclasstableService iPxclasstableService;
    @Autowired
    IPxkeshizengsongtableService iPxkeshizengsongtableService;
    @Autowired
    IPxpaiketableService iPxpaiketableService;
    @Autowired
    IPxxuanketableService iPxxuanketableService;
    @Autowired
    IPxstuclasstableService iPxstuclasstableService;
    @Autowired
    IPxstutableService iPxstutableService;
    @Autowired
    IPxbuxikechengtableService iPxbuxikechengtableService;
    @Autowired
    IPxqiandansubjecttableService iPxqiandansubjecttableService;
    @Autowired
    IPxqiandansuppliesService iPxqiandansuppliesService;
    @Autowired
    IPxteachingsuppliesouttableService iPxteachingsuppliesouttableService;
    @Autowired
    IPxteachingsuppliestableService iPxteachingsuppliestableService;
    @Autowired
    IPxdaijinquantableService iPxdaijinquantableService;
    @Autowired
    IPxliushuizhangtableService iPxliushuizhangtableService;
    @Autowired
    IPxqiandanpaymoneyService iPxqiandanpaymoneyService;
    @Autowired
    IWscUserBindService iWscUserBindService;
    @Autowired
    IWscOrdergoodsService iWscOrdergoodsService;


    //region 商品

    @ApiOperation("获取商品评价")
    @ResponseBody
    @GetMapping(value = "getgoodSpingjia")
    public AjaxJson getgoodSpingjia(HttpServletRequest request, String goodsID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        List<HashMap<String, Object>> getonegoodpingjia = iWscOrdergoodsService.getonegoodpingjia(
                new QueryWrapper<HashMap<String, Object>>()
                        .eq("a.qiyeID", loginUser.getQiyeID())
                        .eq("a.goodsID", goodsID)
        );
        ajaxJson.setObj(getonegoodpingjia);
        return ajaxJson;
    }

    @ApiOperation("获取同类别的商品")
    @ResponseBody
    @GetMapping(value = "getthistypegood")
    public AjaxJson getthistypegood(HttpServletRequest request, String goodsTypeID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");

        ajaxJson.setObj(iWscGoodsService.list(new QueryWrapper<WscGoods>()
                .eq("qiyeID", loginUser.getQiyeID())
                .eq("goodsTypeID", goodsTypeID)
        ));
        return ajaxJson;
    }


    /**
     * @Description: getAllgoodType()方法作用:获取商品类别
     * @param:[request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/5/27 15:57
     */
    @ApiOperation("获取商品类别")
    @ResponseBody
    @GetMapping(value = "getAllgoodType")
    public AjaxJson getAllgoodType(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setObj(iWscGoodstypeService.list(new QueryWrapper<WscGoodstype>()
                .eq("isshow", 1)
                .eq("qiyeID", loginUser.getQiyeID())
        ));
        return ajaxJson;
    }


    /**
     * @Description: getGoodguige()方法作用:获取某个商品的所有规格
     * @param:[request, goodID]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/5/27 15:57
     */
    @ApiOperation("获取某个商品的所有规格")
    @ResponseBody
    @GetMapping(value = "getgoodGuige")
    public AjaxJson getGoodguige(HttpServletRequest request, String goodID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        List<WscGoodsguige> goodslist = iWscGoodsguigeService.list(
                new QueryWrapper<WscGoodsguige>()
                        .eq("qiyeID", loginUser.getQiyeID())
                        .eq("goodsID", goodID)
                        .orderByAsc("id"));
        ajaxJson.setObj(goodslist);
        return ajaxJson;
    }


    /**
     * @Description: getGoodallguigelist()方法作用:获取某个商品的全部规格数据
     * @param:[request, goodID]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/5/27 15:57
     */
    @ApiOperation("获取某个商品的全部规格数据")
    @ResponseBody
    @GetMapping(value = "getGoodallguigelist")
    public AjaxJson getGoodallguigelist(HttpServletRequest request, String goodID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");

        List<WscGoodsguige> goodslist = iWscGoodsguigeService.list(
                new QueryWrapper<WscGoodsguige>()
                        .eq("qiyeID", loginUser.getQiyeID())
                        .eq("goodsID", goodID)
                        .orderByAsc("id"));
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        if (goodslist.size() > 0) {
            for (WscGoodsguige item : goodslist) {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("id", item.getId());
                jsonObject1.put("name", item.getGuigetypename());
                List<guigeshuxingVo> list = iWscGoodsshuxinglistService.GetGuigeList(
                        new QueryWrapper<guigeshuxingVo>()
                                .eq("qiyeID", loginUser.getQiyeID())
                                .eq("goodsID", goodID)
                                .eq("goodsGuigeTypeID", item.getId())
                );
                String value = JSON.toJSONString(list);
                jsonObject1.put("value", value);
                jsonArray.add(jsonObject1);
                jsonObject.put("sites", jsonArray);
            }
        }
        ajaxJson.setObj(jsonObject);
        return ajaxJson;
    }


    /**
     * @Description: getGoodallguigekucun()方法作用:获取某个商品的各规格库存
     * @param:[request, allguige, goodID]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/5/27 15:57
     */
    @ApiOperation("获取某个商品的各规格库存")
    @ResponseBody
    @GetMapping(value = "getGoodallguigekucun")
    public AjaxJson getGoodallguigekucun(HttpServletRequest request, String allguige, String goodID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<WscGoodsshuxinglistprice> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("goodsid", goodID)
                .eq("qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(allguige)) {
            queryWrapper.eq("goodsshuxinglistall", allguige);
            ajaxJson.setObj(iWscGoodsshuxinglistService.getkucun(queryWrapper));
        } else {
            ajaxJson.setObj(iWscGoodsshuxinglistService.GetAllKucun(queryWrapper));
        }

        return ajaxJson;
    }

    /**
     * 根据商品ID查询商品信息
     * @param request
     * @param goodsID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/GetGoodsDetail",method = RequestMethod.GET)
    public  AjaxJson GetGoodsDetail(HttpServletRequest request,String goodsID){
        AjaxJson ajaxJson=new AjaxJson();
        ajaxJson.setObj(iWscGoodsService.GetGoodsDetail(goodsID));
        return ajaxJson;
    }

    //endregion

    //region 购物车

    /**
     * @Description: addShoppingcat()方法作用:添加购物车
     * @param:[request, goodsID, goodsShuxingPriceID, huodongID, buyNum]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/5/27 15:54
     */
    @ApiOperation("添加购物车")
    @ResponseBody
    @PostMapping("addShoppingcat")
    public AjaxJson addShoppingcat(HttpServletRequest request, String goodsID, String goodsShuxingPriceID, String huodongID, Long buyNum) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        WscShoppingcat cat = new WscShoppingcat();
        cat.setGoodsid(Long.valueOf(goodsID));
        cat.setGoodsShuxingPriceID(Long.valueOf(goodsShuxingPriceID));
        if (StringUtils.isNotBlank(huodongID)) {
            cat.setHuodongID(Long.valueOf(huodongID));
        }
        cat.setNum(buyNum);
        cat.setAddUser(loginUser.getWscUserID());
        cat.setAddDateTime(new Date());
        cat.setQiyeID(Long.valueOf(loginUser.getQiyeID()));
        iWscShoppingcatService.save(cat);
        ajaxJson.setMsg("添加成功");
        return ajaxJson;
    }


    /**
     * @Description: deleteshoppingcart()方法作用:删除购物车商品
     * @param:[IDs]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/5/27 15:54
     */
    @ApiOperation("删除购物车商品")
    @ResponseBody
    @DeleteMapping("deleteshoppingcart")
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson deleteshoppingcart(String IDs) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] split = IDs.split(",");
        ajaxJson.setSuccess(iWscShoppingcatService.removeByIds(Arrays.asList(split)));
        return ajaxJson;
    }

    /**
     * @Description: getshoppingcartByApp()方法作用:获取登录人的购物车详情
     * @param:[request, current, size]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/5/27 15:54
     */
    @ApiOperation("获取登录人的购物车详情")
    @ResponseBody
    @GetMapping("getshoppingcartByApp")
    public AjaxJson getshoppingcartByApp(HttpServletRequest request,
                                         Long current,
                                         Long size
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<HashMap<String, Object>> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("a.addUser", loginUser.getWscUserID())
                .eq("a.qiyeID", loginUser.getQiyeID());
        Page<HashMap<String, Object>> page = iWscShoppingcatService.getshoppingcartByApp(new Page<>(current, size), queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }


    /**
     * @Description: GetorderByUser()方法作用:获取登录人的购物车详情
     * @param:[request, current, size, orderState]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/5/27 15:55
     */
    @ApiOperation("获取登录人的购物车详情")
    @ResponseBody
    @GetMapping("GetorderByUser")
    public AjaxJson GetorderByUser(HttpServletRequest request,
                                   Long current,
                                   Long size,
                                   String orderState
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<HashMap<String, Object>> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("a.orderState", orderState)
                .eq("a.orderUserID",loginUser.getWscUserID())
                .eq("a.qiyeID", loginUser.getQiyeID());
        Page<HashMap<String, Object>> page = iWscOrderService.GetorderByUser(new Page<>(current, size), queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    //endregion

    //region 收货地址

    /**
     * @Description: getwscUseraddress()方法作用:获取商城用户的收货地址
     * @param:[request, current, size, type]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/5/27 15:55
     */
    @ApiOperation("获取商城用户的收货地址")
    @ResponseBody
    @GetMapping("getwscUseraddress")
    public AjaxJson getwscUseraddress(HttpServletRequest request,
                                      Long current,
                                      Long size,
                                      int type
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<WscUserAddress> page = new Page<>(current, size);
        QueryWrapper<HashMap<String, Object>> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("a.userID", loginUser.getWscUserID())
                .eq("a.qiyeID", loginUser.getQiyeID());
        if (type != 0) {
            queryWrapper.eq("a.isMoren", true);
        }
        queryWrapper.orderByDesc();

        ajaxJson.setObj(iWscUserAddressService.getpage(page, queryWrapper));
        return ajaxJson;
    }


    /**
     * @Description: getaddtypelist()方法作用:获取商城用户的收货地址标签
     * @param:[request, type]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/5/27 15:55
     */
    @ApiOperation("获取商城用户的收货地址标签")
    @ResponseBody
    @GetMapping("getaddtypelist")
    public AjaxJson getaddtypelist(HttpServletRequest request, int type) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<WscAddresstype> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        if (type == 0) {
            queryWrapper.and(a -> a.eq("wscUserID", loginUser.getWscUserID()).or(b -> b.eq("type", 0)));
        } else {
            queryWrapper.eq("wscUserID", loginUser.getWscUserID());
        }

        ajaxJson.setObj(iWscAddresstypeService.list(queryWrapper));
        return ajaxJson;
    }


    /**
     * @Description: addUserAddress()方法作用:添加新收货地址
     * @param:[request, addUserName, address, addresstype, tel, isMoren]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/5/27 15:55
     */
    @ApiOperation("添加新收货地址")
    @ResponseBody
    @PostMapping("addUserAddress")
    public AjaxJson addUserAddress(HttpServletRequest request, String id, String addUserName, String address, String addresstype, String tel, Boolean isMoren) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");

        if (StringUtils.isNotBlank(id)) {
            //修改
            WscUserAddress one = iWscUserAddressService.getById(id);
            one
                    .setAddUserName(addUserName)
                    .setAddress(address)
                    .setAddresstype(Long.valueOf(addresstype))
                    .setTel(tel);

            if (isMoren == true) {
                //去除其他的默认
                WscUserAddress type = new WscUserAddress();
                type.setIsMoren(false);
                UpdateWrapper<WscUserAddress> userUpdateWrapper = new UpdateWrapper<>();
                userUpdateWrapper
                        .eq("qiyeID", loginUser.getQiyeID())
                        .eq("userID", loginUser.getWscUserID());
                iWscUserAddressService.update(type, userUpdateWrapper);
            }
            one.setIsMoren(isMoren);
            one.setUserID(loginUser.getWscUserID());
            iWscUserAddressService.updateById(one);
            ajaxJson.setMsg("修改成功");
        } else {
            WscUserAddress add = new WscUserAddress();
            add.setAddUserName(addUserName);
            add.setAddress(address);
            add.setAddresstype(Long.valueOf(addresstype));
            add.setTel(tel);

            if (isMoren == true) {
                //去除其他的默认
                WscUserAddress type = new WscUserAddress();
                type.setIsMoren(false);
                UpdateWrapper<WscUserAddress> userUpdateWrapper = new UpdateWrapper<>();
                userUpdateWrapper
                        .eq("qiyeID", loginUser.getQiyeID())
                        .eq("userID", loginUser.getWscUserID());
                iWscUserAddressService.update(type, userUpdateWrapper);
            }
            add.setIsMoren(isMoren);
            add.setUserID(loginUser.getWscUserID());
            add.setQiyeID(Long.valueOf(loginUser.getQiyeID()));
            iWscUserAddressService.save(add);
            ajaxJson.setMsg("添加成功");
        }

        return ajaxJson;
    }


    /**
     * @Description: addAddresstype()方法作用:添加收货地址标签
     * @param:[request, typeName]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/5/27 15:55
     */
    @ApiOperation("添加收货地址标签")
    @ResponseBody
    @PostMapping("addAddresstype")
    public AjaxJson addAddresstype(HttpServletRequest request, String typeName) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        WscAddresstype add = new WscAddresstype();
        add.setAddtype(typeName);
        add.setWscUserID(loginUser.getWscUserID());
        add.setQiyeID(Long.valueOf(loginUser.getQiyeID()));
        add.setType(1);
        iWscAddresstypeService.save(add);
        return ajaxJson;
    }


    /**
     * @Description: deladdtype()方法作用:删除地址标签
     * @param:[id]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/5/27 15:55
     */
    @ApiOperation("删除地址标签")
    @ResponseBody
    @DeleteMapping("deladdtype")
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson deladdtype(String id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(iWscAddresstypeService.removeById(id));
        return ajaxJson;
    }

    @ApiOperation("删除地址")
    @ResponseBody
    @DeleteMapping("deladdress")
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson deladdress(String id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(iWscUserAddressService.removeById(id));
        return ajaxJson;
    }

    //endregion

    //region 优惠券
    @ApiOperation("添加优惠券获取商品类别list")
    @ResponseBody
    @GetMapping("getGoodsTyleList")
    public AjaxJson getGoodsTyleList(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        List<WscGoodstype> list = iWscGoodstypeService.list(
                new QueryWrapper<WscGoodstype>()
                        .eq("isshow", 1)
                        .eq("qiyeid", loginUser.getQiyeID())
        );
        ajaxJson.setObj(list);
        return ajaxJson;
    }

    @ApiOperation("获取优惠券")
    @ResponseBody
    @GetMapping("GetCouponsPages")
    public AjaxJson GetCouponsPages(HttpServletRequest request, Long current, Long size, String couponsName, int giveType, int goodstypeID, String startDate, String endDate) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<HashMap<String, Object>> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("a.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(couponsName)) {
            queryWrapper.like("a.couponsName", couponsName);
        }
        if (giveType != -1) {
            queryWrapper.eq("a.giveType", giveType);
        }
        if (goodstypeID != 0) {
            queryWrapper.eq("a.GoodTypeID", goodstypeID);
        }
        if (StringUtils.isNotBlank(startDate) || StringUtils.isNotBlank(endDate)) {
            queryWrapper.ge("a.addDate", startDate);
            queryWrapper.le("a.addDate", endDate);
        }
        Page<HashMap<String, Object>> page = iWhdCouponsService.GetCouponsPages(new Page<>(current, size), queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }


    /**
     * @Description: AddOreditCoupons()方法作用:添加优惠券
     * @param:[request, id, couponsName, Money, manMoney, GoodTypeID, stratDate, endDate, giveType]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/6/4 9:45
     */
    @ApiOperation("添加或修改优惠券")
    @ResponseBody
    @PostMapping("AddOreditCoupons")
    public AjaxJson AddOreditCoupons(HttpServletRequest request,
                                     String id,
                                     String couponsName,
                                     BigDecimal Money,
                                     BigDecimal manMoney,
                                     String GoodTypeID,
                                     String stratDate,
                                     String endDate,
                                     int giveType
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        WhdCoupons Cou = new WhdCoupons();
        if (StringUtils.isNotBlank(id)) {
            //修改
            Cou = iWhdCouponsService.getById(id);
        }

        Cou
                .setCouponsName(couponsName)
                .setMoney(Money)
                .setManMoney(manMoney)
                .setGiveType(giveType)
                .setAddStaffID(loginUser.getStaffID())
                .setAddDate(new Date())
                .setQiyeID(Long.valueOf(loginUser.getQiyeID()));

        if (StringUtils.isNotBlank(GoodTypeID)) {
            Cou.setGoodTypeID(Long.valueOf(GoodTypeID));
        }

        Date st = null;
        try {
            st = sdf.parse(stratDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date et = null;
        try {
            et = sdf.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Cou.setEndDate(et);
        Cou.setStratDate(st);
        if (StringUtils.isNotBlank(GoodTypeID)) {
            Cou.setType(1);
        } else {
            Cou.setType(2);
        }

        if (StringUtils.isNotBlank(id)) {
            iWhdCouponsService.updateById(Cou);
        } else {
            iWhdCouponsService.save(Cou);
        }

        return ajaxJson;
    }


    /**
     * @Description: delCouponsByID()方法作用:删除优惠券
     * @param:[id]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/6/4 9:49
     */
    @DeleteMapping("delCouponsByID")
    @ResponseBody
    @ApiOperation("删除优惠券")
    public AjaxJson delCouponsByID(String id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(iWhdCouponsService.removeById(id));
        return ajaxJson;
    }


    /**
     * @Description: Getuseryouhuiquan()方法作用:分页获取优惠券记录
     * @param:[request, current, size]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/5/27 15:55
     */
    @GetMapping("Getuseryouhuiquan")
    @ResponseBody
    @ApiOperation("分页获取优惠券记录")
    public AjaxJson Getuseryouhuiquan(HttpServletRequest request,
                                      Long current,
                                      Long size,
                                      int type //1:优惠券使用记录 2：优惠券发放记录 3：小程序获取用户优惠券
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<HashMap<String, Object>> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (type == 1) {
            //使用记录
            queryWrapper.eq("a.isUse", true);
        }
        if (type == 3) {
            queryWrapper.eq("a.userID", loginUser.getWscUserID());
        }
        Page<HashMap<String, Object>> page = iWhdUsercouponsService.GetwscUsercouponsPage(new Page<>(current, size), queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    //endregion

    //region 收藏

    /**
     * @Description: GetwscUserCollect()方法作用:获取用户收藏
     * @param:[request, current, size]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/5/27 15:56
     */
    @GetMapping("GetwscUserCollect")
    @ResponseBody
    @ApiOperation("获取用户收藏")
    public AjaxJson GetwscUserCollect(HttpServletRequest request, Long current, Long size) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<HashMap<String, Object>> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("a.wscUserID", loginUser.getWscUserID())
                .eq("b.shangjiaState", 1) //上架的
                .eq("a.qiyeID", loginUser.getQiyeID());
        Page<collectVo> page = iWscCollectService.GetwscUserCollect(new Page<>(current, size), queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }


    /**
     * @Description: GetgoodsIscollect()方法作用:获取用户收藏
     * @param:[request, goodID]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/5/27 15:56
     */
    @GetMapping("GetgoodsIscollect")
    @ResponseBody
    @ApiOperation("获取用户收藏")
    public AjaxJson GetgoodsIscollect(HttpServletRequest request, String goodID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        WscCollect one = iWscCollectService.getOne(
                new QueryWrapper<WscCollect>()
                        .eq("goodsID", goodID)
                        .eq("wscUserID", loginUser.getWscUserID())
                        .eq("qiyeID", loginUser.getQiyeID())
        );
        ajaxJson.setObj(one);
        return ajaxJson;
    }


    /**
     * @Description: addUserSC()方法作用:添加收藏
     * @param:[request, goodID, collectid]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/5/27 15:56
     */
    @ApiOperation("添加收藏")
    @ResponseBody
    @PostMapping("addUserSC")
    public AjaxJson addUserSC(HttpServletRequest request, String goodID, String collectid) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        if (StringUtils.isNotBlank(collectid)) {
            //取消收藏
            iWscCollectService.remove(
                    new QueryWrapper<WscCollect>()
                            .eq("goodsID", goodID)
                            .eq("wscUserID", loginUser.getWscUserID())
                            .eq("qiyeID", loginUser.getQiyeID())
            );
        } else {
            //添加
            WscCollect sc = new WscCollect();
            sc.setGoodsID(Long.valueOf(goodID));
            sc.setWscUserID(loginUser.getWscUserID());
            sc.setAddDate(new Date());
            sc.setQiyeID(Long.valueOf(loginUser.getQiyeID()));
            iWscCollectService.save(sc);
            ajaxJson.setObj(sc);
        }
        return ajaxJson;
    }


    /**
     * @Description: delcollect()方法作用:删除收藏
     * @param:[id]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/5/27 15:56
     */
    @ApiOperation("删除收藏")
    @ResponseBody
    @DeleteMapping("delcollect")
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delcollect(String id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(iWscCollectService.removeById(id));
        return ajaxJson;
    }

    //endregion

    //region 交易

    /**
     * @Description: getWscUserjiaoyi()方法作用:获取商城用户交易记录
     * @param:[request, type]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/5/27 15:56
     */
    @ApiOperation("获取商城用户交易记录")
    @ResponseBody
    @GetMapping("getWscUserjiaoyi")
    public AjaxJson getWscUserjiaoyi(HttpServletRequest request, int type) {
        AjaxJson ajaxJson = new AjaxJson();
        JSONObject jsonObject = new JSONObject();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        List<WscUserjiaoyi> list = iWscUserjiaoyiService.list(
                new QueryWrapper<WscUserjiaoyi>()
                        .eq("wscUserID", loginUser.getWscUserID())
                        .eq("type", type)
                        .eq("qiyeID", loginUser.getQiyeID())
        );

        WscUser user = iWscUserService.getById(loginUser.getWscUserID());
        jsonObject.put("wscuserRmoney", user.getScRemainMoney());//微商城用户的余额
        jsonObject.put("value", JSON.toJSONString(list)); //收支明细
        ajaxJson.setObj(jsonObject);
        return ajaxJson;
    }

    @ApiOperation("获取商城用户的付款记录信息")
    @ResponseBody
    @GetMapping("GetwscUserPay")
    public AjaxJson GetwscUserPay(HttpServletRequest request,
                                  Long current,
                                  Long size,
                                  int type
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        WscUser user = iWscUserService.getById(loginUser.getWscUserID());
        List<WscUserBind> list = iWscUserBindService.list(new QueryWrapper<WscUserBind>()
                .eq("wscuserid", user.getId())
                .eq("qiyeID", loginUser.getQiyeID())
        );

        String allstu = "";
        if (list.size() <= 0) {
            ajaxJson.setMsg("请先绑定学员后再使用付款功能");
            ajaxJson.setCode("N");
            return ajaxJson;
        } else {
            for (WscUserBind item : list) {
                allstu += item.getStuId() + ",";
            }

        }

        String[] stuIDs = allstu.split(",");
        QueryWrapper<HashMap<String, Object>> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .in("a.stuID", stuIDs)
                .eq("a.payState", type)
                .eq("a.qiyeID", loginUser.getQiyeID());
        Page<HashMap<String, Object>> page = iQiandanapppayService.GetUserpayPage(new Page<>(current, size), queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @ApiOperation("小程序支付签单")
    @ResponseBody
    @PostMapping("AppPayQiandan")
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson AppPayQiandan(HttpServletRequest request, String id) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Qiandanapppay qdPay = iQiandanapppayService.getById(id);
        Pxstutable pxstutable = iPxstutableService.getById(qdPay.getStuID());

        Qiandanapppaymoney paystyle = iQiandanapppaymoneyService.getOne(new QueryWrapper<Qiandanapppaymoney>()
                .eq("qiandanAppayID", id)
                .eq("qiyeID", loginUser.getQiyeID())
        );

        List<Qiandanapppaysubject> subjectlist = iQiandanapppaysubjectService.list(
                new QueryWrapper<Qiandanapppaysubject>()
                        .eq("qiandanAppayID", id)
                        .eq("stuID", qdPay.getStuID())
                        .eq("qiyeID", loginUser.getQiyeID())
        );

        List<Qiandanapppaysupplies> supplieslist = iQiandanapppaysuppliesService.list(new QueryWrapper<Qiandanapppaysupplies>()
                .eq("qiandanAppayID", id)
                .eq("qiyeID", loginUser.getQiyeID())
        );

        List<Qiandanapppayyejiren> qdyjrenlist = iQiandanapppayyejirenService.list(new QueryWrapper<Qiandanapppayyejiren>()
                .eq("qiandanAppayID", id)
                .eq("qiyeID", loginUser.getQiyeID())
        );

        List<Qiandanapppayzafei> qdzflist = iQiandanapppayzafeiService.list(new QueryWrapper<Qiandanapppayzafei>()
                .eq("qiandanAppayID", id)
                .eq("qiyeID", loginUser.getQiyeID())
        );

        //添加签单
        Pxqiandaninfotable qd = new Pxqiandaninfotable();
        qd
                .setStuID(qdPay.getStuID())
                .setQiandandate(qdPay.getQiandanDate())
                .setShishouTotalMoney(qdPay.getShishouTotalMoney())
                .setHetongMoney(qdPay.getHetongMoney())
                .setJiazhangDemand(qdPay.getJiazhangDemand())
                .setZhuanjieshaoID(qdPay.getZhuanjieshaoID())
                .setMoneyStyle(qdPay.getMoneyStyle())
                .setBeizhu(qd.getBeizhu())
                .setQianDanStaffID(qd.getQianDanStaffID())
                .setRecordInStaffID(qdPay.getAddstaffID())
                .setRecordInTime(new Date())
                .setPayMoneyStyle(0L)//存的假数据
                .setZhuanjieshaoID(qdPay.getZhuanjieshaoID())
                .setCampusID(qdPay.getCampusID())
                .setFromType(qdPay.getFromType())
                .setYouhuiID(qdPay.getYouhuiID())
                .setYouhuijine(qdPay.getYouhuijine().toString())
                .setYouhuishuoming(qdPay.getYouhuishuoming())
                .setIsdingjing(qdPay.getIsdingjing())
                .setQiyeID(loginUser.getQiyeID())
        ;
        iPxqiandaninfotableService.save(qd);

        for (Qiandanapppayyejiren yjr : qdyjrenlist) {
            Pxqiandanstafftable newqiandanstaff = new Pxqiandanstafftable();
            newqiandanstaff.setIsWeikuan(0);
            newqiandanstaff.setQiandanID(qd.getId());
            newqiandanstaff.setQiyeID(loginUser.getQiyeID());
            newqiandanstaff.setStaffID(yjr.getQiandanstaffID());
            newqiandanstaff.setYejidateTime(LocalDateTime.now());
            newqiandanstaff.setYejiMoney(yjr.getYejiMoney());
            iPxqiandanstafftableService.save(newqiandanstaff);
        }


        for (Qiandanapppayzafei othermoneyVo : qdzflist) {
            Pxqiandaninfo2table qd2Tab = new Pxqiandaninfo2table();
            qd2Tab.setQianInfoTabID(qd.getId());
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


        if (subjectlist.size() == 0) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("操作失败！您还未添加课程！");
            return ajaxJson;
        }

        //region 保存课程信息
        for (Qiandanapppaysubject buyKechengVo : subjectlist) {
            //region 新增课程
            Pxkechengtable pxkechengtable = iPxkechengtableService.getById(buyKechengVo.getKechengID());
            Pxsubjecttable pxsubjecttable = iPxsubjecttableService.getById(pxkechengtable.getSubjectID());
            Long classID = 0L;
            if (pxkechengtable.getIs1v1KC() == 1) {
                boolean ii = false;
                while (ii == false) {
                    Random random = new Random();
                    String className = pxstutable.getStuName() + "_" + pxsubjecttable.getSubjectName() + "_一对一" + random.ints(100, 999);
                    List<Pxclasstable> pxclasstableList = iPxclasstableService.list(
                            new QueryWrapper<Pxclasstable>()
                                    .eq("className", className)
                                    .eq("qiyeID", loginUser.getQiyeID())
                    );
                    if (pxclasstableList.size() == 0) {
                        Pxclasstable classtable = new Pxclasstable();
                        classtable.setClassName(className);
                        classtable.setCampusID(qdPay.getCampusID());
                        classtable.setAddStaffID(qdPay.getAddstaffID());
                        classtable.setAddTime(new Date());
                        classtable.setIsShow(1);
                        classtable.setIs1v1Class(1);
                        classtable.setQiyeID(qdPay.getQiyeID());
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
            buxi.setBuykeshiDateTime(qdPay.getQiandanDate());
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
                keshizengsong.setCaozuoStaffId(qdPay.getAddstaffID());
                keshizengsong.setQiandanInfoID(qd.getId());
                keshizengsong.setQiyeID(loginUser.getQiyeID());
                iPxkeshizengsongtableService.save(keshizengsong);

                QueryWrapper queryWrapper2 = new QueryWrapper();
                queryWrapper2.eq("stuID", pxstutable.getId());
                queryWrapper2.eq("kechengID", buyKechengVo.getKechengID());
                queryWrapper2.eq("kechengprice", buyKechengVo.getKechengprice());
                queryWrapper2.eq("type", 2);
                queryWrapper2.eq("qiyeID", loginUser.getQiyeID());

                Pxbuxikechengtable pdbuxikc = iPxbuxikechengtableService.getOne(queryWrapper2);
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
                    zsbxTab.setType(2);    //赠送的
                    zsbxTab.setQiyeID(loginUser.getQiyeID());
                    iPxbuxikechengtableService.save(zsbxTab);

                    if (pxkechengtable.getJifeiStyleID() == 1) {
                        Long addclassID = 0L;
                        Pxkechengtable onekcTab = iPxkechengtableService.getById(buyKechengVo.getKechengID());
                        String subjectName = iPxsubjecttableService.getById(onekcTab.getSubjectID()).getSubjectName();
                        //建班插班
                        boolean ii = false;
                        while (ii == false) {
                            Random rd = new Random();
                            String className = pxstutable.getStuName() + "_" + subjectName + "_一对一" + rd.ints(100, 999);
                            List<Pxclasstable> pdclass = iPxclasstableService.list(
                                    new QueryWrapper<Pxclasstable>()
                                            .eq("className", className)
                                            .eq("qiyeID", loginUser.getQiyeID())
                            );
                            if (pdclass.size() == 0) {
                                Pxclasstable classtable = new Pxclasstable();
                                classtable.setClassName(className);
                                classtable.setCampusID(qdPay.getCampusID());
                                classtable.setAddStaffID(qdPay.getAddstaffID());
                                classtable.setAddTime(new Date());
                                classtable.setIsShow(1);
                                classtable.setIs1v1Class(1);
                                classtable.setQiyeID(Long.valueOf(loginUser.getQiyeID()));
                                iPxclasstableService.save(classtable);
                                addclassID = classtable.getId();
                                ii = true;
                            }
                        }

                        //如果是一对一，要插班
                        Pxstuclasstable stucla = new Pxstuclasstable();
                        stucla.setBuxiID(zsbxTab.getId());
                        stucla.setClassID(addclassID);
                        stucla.setQiyeID(Long.valueOf(loginUser.getQiyeID()));
                        iPxstuclasstableService.save(stucla);
                    }
                }
            }


            Pxqiandansubjecttable qdsub = new Pxqiandansubjecttable();
            qdsub.setStuID(pxstutable.getId());
            qdsub.setQiandandate(qdPay.getQiandanDate());
            qdsub.setKechengID(buyKechengVo.getKechengID());
            qdsub.setKechengprice(buyKechengVo.getKechengprice());
            qdsub.setOriginalprice(buyKechengVo.getOriginalprice());
            qdsub.setBuykeshiNum(buyKechengVo.getBuykeshiNum());
            qdsub.setZongjia(buyKechengVo.getZongjia());   ///增加了总价的存储
            qdsub.setQianDanInfoID(qd.getId());
            qdsub.setKechengStyle(1);   //1买的 2 接受的赠送 3 送出的 4 退费
            qdsub.setDiscount(buyKechengVo.getDiscount());
            qdsub.setStartDate(buyKechengVo.getStartDate());
            qdsub.setEndDate(buyKechengVo.getEndDate());
            qdsub.setQiyeID(loginUser.getQiyeID());
            qdsub.setKechengID(buyKechengVo.getKechengID());
            iPxqiandansubjecttableService.save(qdsub);
            buxi.setQianDanInfoID(qd.getId());
            buxi.setQianDanSubjectID(qdsub.getId());
            buxi.setQiyeID(loginUser.getQiyeID());
            buxi.setKechengID(buyKechengVo.getKechengID());
            iPxbuxikechengtableService.save(buxi);

            //插班
            if (classID != 0L) {
                boolean ishave = false;
                List<Pxbuxikechengtable> abuxiTab = iPxbuxikechengtableService.list(
                        new QueryWrapper<Pxbuxikechengtable>()
                                .eq("stuID", pxstutable.getId())
                                .eq("qiyeID", loginUser.getQiyeID())
                                .orderByAsc("id")
                                .last("limit 0,1")
                );
                for (Pxbuxikechengtable pxbuxikechengtable : abuxiTab) {
                    Pxstuclasstable psstuclsTab = iPxstuclasstableService.getOne(
                            new QueryWrapper<Pxstuclasstable>()
                                    .eq("classID", classID)
                                    .eq("buxiID", pxbuxikechengtable.getId())
                                    .eq("qiyeID", loginUser.getQiyeID())
                    );
                    if (psstuclsTab != null) {
                        ishave = true;
                    }
                }
                if (ishave == false) {
                    Pxstuclasstable stuclass = new Pxstuclasstable();
                    stuclass.setBuxiID(buxi.getId());
                    stuclass.setClassID(classID);
                    stuclass.setQiyeID(Long.valueOf(loginUser.getQiyeID()));
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
                            List<Pxxuanketable> pdxuankeTab = iPxxuanketableService.list(
                                    new QueryWrapper<Pxxuanketable>()
                                            .eq("paikeID", pxpaiketable.getId())
                                            .eq("stuID", buxi.getStuID())
                                            .eq("qiyeID", loginUser.getQiyeID())
                            );
                            if (pdxuankeTab.size() == 0) {
                                Pxxuanketable xuanke = new Pxxuanketable();
                                xuanke.setBuxiID(buxi.getId());
                                xuanke.setPaikeID(pxpaiketable.getId());
                                xuanke.setStuID(buxi.getStuID());
                                xuanke.setRecordDate(new Date());
                                xuanke.setType(1);
                                xuanke.setQiyeID(Long.valueOf(loginUser.getQiyeID()));
                                iPxxuanketableService.save(xuanke);
                            }
                        }
                    }
                }
            }
        }

        //endregion

        //商品
        for (Qiandanapppaysupplies buyWpVo : supplieslist) {
            Pxteachingsuppliestable TeachingSupplies = iPxteachingsuppliestableService.getOne(
                    new QueryWrapper<Pxteachingsuppliestable>()
                            .eq("name", buyWpVo.getName())
                            .eq("qiyeID", loginUser.getQiyeID())
            );
            if (TeachingSupplies.getStockNum().compareTo(buyWpVo.getBuySum()) == -1) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("当前所购买的教学物品" + buyWpVo.getName() + "数量不足！");
                return ajaxJson;
            }
            Pxqiandansupplies qdsp = new Pxqiandansupplies();
            qdsp.setStuID(pxstutable.getId());
            qdsp.setTeachingSuppliesID(TeachingSupplies.getId());
            qdsp.setName(buyWpVo.getName());
            qdsp.setQiandaninfoID(qd.getId());
            qdsp.setBuyPrice(buyWpVo.getBuyPrice());
            qdsp.setBuySum(buyWpVo.getBuySum());
            qdsp.setSumMoney(buyWpVo.getSumMoney());
            qdsp.setIsTuiFei(false);
            qdsp.setTuiMoney(new BigDecimal(0));
            qdsp.setTuiMoney(new BigDecimal(0));
            qdsp.setQiyeID(loginUser.getQiyeID());
            iPxqiandansuppliesService.save(qdsp);

            Pxteachingsuppliesouttable outjl = new Pxteachingsuppliesouttable();
            outjl.setLuruStaffId(qdPay.getAddstaffID());
            outjl.setOutDate(new Date());
            outjl.setOutNum(buyWpVo.getBuySum());
            outjl.setOutnumBefore(TeachingSupplies.getStockNum());
            outjl.setOutReason("学生购买");
            outjl.setOutStaffId(qdPay.getAddstaffID());
            outjl.setSuppliesId(TeachingSupplies.getId());
            outjl.setType(2);
            outjl.setQiyeID(loginUser.getQiyeID());
            outjl.setLuruStaffId(qdPay.getAddstaffID());
            iPxteachingsuppliesouttableService.save(outjl);
            BigDecimal kucun = TeachingSupplies.getStockNum().subtract(buyWpVo.getBuySum());
            iPxteachingsuppliestableService.UpdateteachingsuppliesKucun(TeachingSupplies.getId(), kucun, Long.valueOf(loginUser.getQiyeID()));
        }
        if (qdPay.getDaijinquanmoney().compareTo(new BigDecimal(0)) == 1) {//代金券金额大于0
            Pxdaijinquantable pxdaijinquantable = new Pxdaijinquantable();
            pxdaijinquantable.setStuID(pxstutable.getId());
            pxdaijinquantable.setQiandanID(qd.getId());
            pxdaijinquantable.setMoney(qdPay.getDaijinquanmoney());
            pxdaijinquantable.setCreatTime(new Date());
            pxdaijinquantable.setQiyeID(loginUser.getQiyeID());
            pxdaijinquantable.setStaffID(qdPay.getAddstaffID());
            pxdaijinquantable.setStaffID(qdPay.getAddstaffID());
            iPxdaijinquantableService.save(pxdaijinquantable);
        }

        //支付


        if (paystyle.getPaymoney().compareTo(new BigDecimal(0)) == 1) {
            Pxqiandanpaymoney ishavepay = iPxqiandanpaymoneyService.getOne(new QueryWrapper<Pxqiandanpaymoney>()
                    .eq("qiandanID", qd.getId())
                    .eq("paymoneyStyleID", paystyle.getPaymoneystyleID())
                    .eq("qiyeID", loginUser.getQiyeID())
            );
            if (ishavepay == null) {
                Pxqiandanpaymoney qdpm = new Pxqiandanpaymoney();
                qdpm.setPayMoney(paystyle.getPaymoney());
                qdpm.setPaymoneyStyleID(-1L);
                qdpm.setQiandanID(qd.getId());
                qdpm.setIsWeikuan(0);
                qdpm.setQianDanDate(qdPay.getQiandanDate());
                qdpm.setQiyeID(loginUser.getQiyeID());
                iPxqiandanpaymoneyService.save(qdpm);
            }

            if (paystyle.getPaymoneystyleID() != 3) {
                Pxliushuizhangtable liushui = new Pxliushuizhangtable();
                Random random = new Random();
                liushui.setLiushuiDateTime(qd.getQiandandate());
                liushui.setCampusID(qdPay.getCampusID());
                liushui.setPayMoneyStyle(-1L);
                liushui.setZhichuMoney(new BigDecimal(0));
                liushui.setShouzhiStyleID(1L);
                liushui.setJinbanRen(qdPay.getAddstaffID());
                liushui.setStuID(pxstutable.getId());
                liushui.setQiandanID(qd.getId());
                liushui.setLuruTime(new Date());
                liushui.setQiyeID(loginUser.getQiyeID());
                liushui.setJinbanRen(qdPay.getAddstaffID());
                String zyText = "";
                if (StringUtils.isNotBlank(qdPay.getBeizhu())) {
                    zyText = "说明：" + qdPay.getBeizhu();
                }
                if (qdPay.getDingjing().compareTo(new BigDecimal(0)) == 1) {
                    liushui.setShouruMoney(qdPay.getDingjing());
                    liushui.setLiushuiZaiyao(pxstutable.getStuName() + "同学：定金交费！--" + zyText);
                } else {
                    liushui.setShouruMoney(paystyle.getPaymoney());
                    liushui.setLiushuiZaiyao(pxstutable.getStuName() + "同学，全款交费！--" + zyText);
                }
                liushui.setAddStaffID(qdPay.getAddstaffID());
                iPxliushuizhangtableService.save(liushui);
            }
        }

        qdPay.setPayDate(new Date());
        qdPay.setPayState(2);
        qdPay.setPayUser(loginUser.getWscUserID());
        qdPay.setBuxiStateID(qdPay.getBuxiStateID());
        pxstutable.setBuxiStateID(qdPay.getBuxiStateID());
        iPxstutableService.updateById(pxstutable);
        ajaxJson.setSuccess(iQiandanapppayService.updateById(qdPay));


        return ajaxJson;
    }

    //endregion

    //region 订单
    @ApiOperation("提交订单")
    @ResponseBody
    @PostMapping("SaveOrdersInfo")
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson SaveOrdersInfo(HttpServletRequest request, addscordersForm form) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        WscOrder wscOrder = new WscOrder();
        wscOrder.setBeizhu(form.getBeizhu());
        wscOrder.setCouponsID(form.getCouponsID());
        wscOrder.setHuodongID(form.getHuodongID());
        wscOrder.setOrderDateTime(new Date());
        if (form.getType() == 2) {
            WscUserAddress wscUserAddress = iWscUserAddressService.getById(form.getShouhuoID());
            if (wscUserAddress == null) {
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg("收货地址不存在");
                return ajaxJson;
            } else {
                wscOrder.setReceiveDizhi(wscUserAddress.getAddress());
                wscOrder.setReceiveName(wscUserAddress.getAddUserName());
                wscOrder.setLianxiTel(wscUserAddress.getTel());
            }
        }
        String orderNumber = getTagByUUId();
        wscOrder.setOrderNumber(orderNumber);
        wscOrder.setType(form.getType());
        wscOrder.setOrderState(1);
        wscOrder.setPayStyle(1);
        wscOrder.setQiyeID(loginUser.getQiyeID());
        wscOrder.setPayMoney(form.getPayMoney());
        wscOrder.setPayJifen(form.getPayJifen());
        wscOrder.setOrderUserID(loginUser.getWscUserID());
        iWscOrderService.save(wscOrder);
        List<ordergoodsVO> ordergoods = JSON.parseArray(form.getOrderGoods(), ordergoodsVO.class);
        for (ordergoodsVO ordergood : ordergoods) {
            WscOrdergoods wscOrdergoods = new WscOrdergoods();
            wscOrdergoods.setGoodsID(ordergood.getGoodID());
            wscOrdergoods.setGoodsshuxinglistpriceID(ordergood.getGuigeID());
            wscOrdergoods.setOrderNumber(wscOrder.getId());
            wscOrdergoods.setNums(ordergood.getBuynum());
            wscOrdergoods.setPayMoney(ordergood.getPrice());
            wscOrdergoods.setQiyeID(loginUser.getQiyeID());
            wscOrdergoods.setHuodongID(form.getHuodongID());
            iWscOrdergoodsService.save(wscOrdergoods);
        }
        ajaxJson.setMsg(wscOrder.getOrderNumber());
        ajaxJson.setSuccess(true);
        return ajaxJson;
    }

    public static String getTagByUUId() {
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
    //endregion

}
