package com.xwcloud.cloud.wsc.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.entity.*;
import com.xwcloud.cloud.wsc.Service.*;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import scala.util.Random;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-10
 */
@Controller
@RequestMapping("/chongzhi/pxczhuodongtable")
public class PxczhuodongtableController {

    @Autowired
    IPxczhuodongtableService iPxczhuodongtableService;
    @Autowired
    IPxliushuizhangtableService iPxliushuizhangtableService;
    @Autowired
    IWscUserService iWscUserService;
    @Autowired
    IWscUserjiaoyiService iWscUserjiaoyiService;
    @Autowired
    IWscOrderService iWscOrderService;
    @Autowired
    IPxstutableService iPxstutableService;

    //获取充值优惠政策
    @GetMapping("/GetchongzhiList")
    @ResponseBody
    @Transactional
    @ApiOperation("获取充值优惠政策")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "qiyeID", value = "qiyeID", required = true),
//    })
    public AjaxJson GetchongzhiList(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        String ND = sdf.format(new Date());
        List<Pxczhuodongtable> zchuodong = iPxczhuodongtableService.list(
                new QueryWrapper<Pxczhuodongtable>()
                        .eq("qiyeID", loginUser.getQiyeID())
                        .le("sdate", ND)
                        .ge("edate", ND));
        ajaxJson.setObj(zchuodong);
        return ajaxJson;
    }

    @PostMapping("savechongzhi")
    @ResponseBody
    @ApiOperation("保存测试的假充值")
    public AjaxJson savechongzhi(BigDecimal stuCZmoney, BigDecimal zengMoney, String czyhID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        String ND = sdf.format(new Date());

        WscUser User = iWscUserService.getById(loginUser.getWscUserID());
//                iWscUserService.getOne(
//                new QueryWrapper<WscUser>()
//                        .eq("stuID", loginUser.getStaffID())
//                        .eq("qiyeID", loginUser.getQiyeID())
//        );

        if (User == null) {
            ajaxJson.setMsg("出错了！！！");
            ajaxJson.setCode("N");
            return ajaxJson;
        }

//        Pxstutable stu = iPxstutableService.getById(User.getStuID());

        int first = new Random(10).nextInt(8) + 1;
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        String OrderNumber = first + String.format("%015d", hashCodeV);
        //添加订单
//        WscOrder order = new WscOrder();
//        order.setOrdernumber(Long.valueOf(OrderNumber));
//        order.setPaymoney(stuCZmoney.add(zengMoney));
//        order.setPayjifen(BigDecimal.valueOf(0));
//
        Long youhui = null;
        if (!StringUtils.isNotBlank(czyhID)) {
            //不是点击的  需要获取充值优惠
            List<Pxczhuodongtable> czhuodong = iPxczhuodongtableService.list(
                    new QueryWrapper<Pxczhuodongtable>()
                            .eq("qiyeID", loginUser.getQiyeID())
                            .le("sdate", ND)
                            .ge("edate", ND)
                            .orderByAsc("zongmoney")); //把充值的优惠总金额降序
            for (Pxczhuodongtable item : czhuodong) {
                if (stuCZmoney.compareTo(item.getZongmoney()) > -1) { //a>=b
                    youhui = item.getId();
                    zengMoney = item.getHuodongmoney();
                    break;
                }
            }
            //order.setCouponsid(youhui);
        } else {
            //order.setCouponsid(Long.valueOf(czyhID));
        }
//        order.setType(2);//虚拟
//        order.setReceivename(User.getNickName());
//        order.setReceivedizhi("");

        //用户添加余额
        User.setScRemainMoney(User.getScRemainMoney().add(stuCZmoney).add(zengMoney));
        iWscUserService.updateById(User);

        //添加交易
        WscUserjiaoyi ujy = new WscUserjiaoyi();
        ujy.setOrderNumber(Long.valueOf(OrderNumber));
        ujy.setWscUserID(User.getId());
        ujy.setPayMoney(stuCZmoney);
        ujy.setGiveMoney(zengMoney);
        ujy.setTotalMoney(stuCZmoney.add(zengMoney));
        ujy.setStyle(1);
        ujy.setType(1);
        ujy.setAddDate(new Date());
        ujy.setOkDate(new Date());
        ujy.setState(true);
        ujy.setQiyeID(Long.valueOf(loginUser.getQiyeID()));
        iWscUserjiaoyiService.save(ujy);

        //流水
        Pxliushuizhangtable wscls = new Pxliushuizhangtable();
        wscls.setLiushuiDateTime(new Date());
        wscls.setCampusID(loginUser.getCampusID());
        wscls.setLiushuiZaiyao("小程序充值：" + stuCZmoney + "元，充值赠送：" + zengMoney + "元！");
        wscls.setPayMoneyStyle(1L);//小程序支付
        wscls.setShouruMoney(stuCZmoney.add(zengMoney));
        wscls.setZhichuMoney(BigDecimal.valueOf(0));
        wscls.setShouzhiStyleID(11L);//收入
        wscls.setJinbanRen(0L);
        wscls.setAddStaffID(0L);
        wscls.setLuruTime(new Date());
        wscls.setStuID(loginUser.getStaffID());
        wscls.setQiyeID(loginUser.getQiyeID());
        iPxliushuizhangtableService.save(wscls);
        ajaxJson.setMsg("充值成功！共获得：" + ujy.getTotalMoney() + "");
        return ajaxJson;
    }

    @GetMapping("getchongzhiliu")
    @ResponseBody
    @ApiOperation("小程序充值流水")
    public AjaxJson getchongzhiliu(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");


        List<WscUserjiaoyi> list = iWscUserjiaoyiService.list(
                new QueryWrapper<WscUserjiaoyi>()
                        //.select("(select nickName from wsc_user where id = wscUserID) as wdcUser")
                        .eq("type", 1)
                        .eq("style", 1)
                        .eq("qiyeID", loginUser.getQiyeID())
                        .orderByAsc("okDate")
                        .last("limit 2")
        );

        List<HashMap<String, Object>> getchongzhiliushui = iWscUserjiaoyiService.Getchongzhiliushui(new QueryWrapper<WscUserjiaoyi>()
                //.select("(select nickName from wsc_user where id = wscUserID) as wdcUser")
                .eq("type", 1)
                .eq("style", 1)
                .eq("a.qiyeID", loginUser.getQiyeID())
                .orderByAsc("okDate")
                .last("limit 2"));

        ajaxJson.setObj(getchongzhiliushui);
        return ajaxJson;
    }
}
