package com.xwcloud.cloud.oa.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.OA.*;
import com.xwcloud.cloud.model.OA.Vo.QiandanInfoVo;
import com.xwcloud.cloud.model.OA.Vo.QiandanVo;
import com.xwcloud.cloud.model.OA.Vo.QiandanXufeiVo;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.entity.*;
import com.xwcloud.cloud.oa.service.*;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-29
 */
@Controller
@RequestMapping("/oaQiandan")
public class OaQiandanController {

    @Autowired
    private IOaQiandanService iOaQiandanService;

    @Autowired
    private IOaKehuService iOaKehuService;

    @Autowired
    private IOaLiushuiService iOaLiushuiService;

    @Autowired
    private IOaTuifeirecordService iOaTuifeirecordService;

    @Autowired
    private IOaDaijinquanService iOaDaijinquanService;

    @Autowired
    private IOaLogService iOaLogService;

    @Autowired
    private IOaQiandanSpService iOaQiandanSpService;

    @Autowired
    private IOaParameterService iOaParameterService;

    @Autowired
    private IPxcampustableService iPxcampustableService;

    @Autowired
    IPxbuxistyletableService iPxbuxistyletableService;

    @Autowired
    IPxclasstimestyletableService iPxclasstimestyletableService;

    @Autowired
    IPxstaffposttableService iPxstaffposttableService;

    @Autowired
    IPxpaymoneystyletableService iPxpaymoneystyletableService;

    @Autowired
    IPxpowertableService iPxpowertableService;

    @Autowired
    IPxstafftableService iPxstafftableService;

    @Autowired
    IPxshouzhistyletableService iPxshouzhistyletableService;

    @Autowired
    IAreasService iAreasService;

    @Autowired
    IOaQiandanXufeiSpService iOaQiandanXufeiSpService;


    @DeleteMapping("delqiandan")
    @ResponseBody
    @ApiOperation("删除签单")
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delqiandan(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -1); //去到昨天


        String[] delIDlist = ids.split(",");
        for (String item : delIDlist) {
            OaQiandan qiandan = iOaQiandanService.getById(item);
            OaKehu kehu = iOaKehuService.getById(qiandan.getQiyeID());
            List<OaQiandan> qdList = iOaQiandanService.list(new QueryWrapper<OaQiandan>().eq("qiyeID", qiandan.getQiyeID()));
            if (qdList.size() == 1) {
                //修改客户状态为意向客户(只有一条签单记录时，需要设置为意向客户)
                kehu.setKehuType(1);
                iOaKehuService.updateById(kehu);

                List<Pxcampustable> campusList = iPxcampustableService.list(new QueryWrapper<Pxcampustable>().eq("qiyeID", kehu.getId()));
                for (Pxcampustable itema : campusList) {
                    itema.setNextPayTime(cal.getTime()); //设置校区过期<=>设置系统过期
                    iPxcampustableService.updateById(itema);
                }

            }
            //删除签单
            iOaQiandanService.removeById(item);

            //删除签单流水
            iOaLiushuiService.remove(new QueryWrapper<OaLiushui>().eq("qiandanID", item));


        }
        return ajaxJson;
    }

    /**
     * 添加入一个签单信息
     *
     * @param qiandanVo
     * @return
     */
    @RequestMapping(value = "/addQiandan", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public AjaxJson addQiandan(HttpServletRequest request, @RequestBody QiandanVo qiandanVo) {

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long staffID = loginUser.getStaffID();

        AjaxJson ajaxJson = new AjaxJson();
//        long provice = qiandanVo.getProvinceid();
//        long city = qiandanVo.getCityid();
        String proviceName = iAreasService.getById(qiandanVo.getProvinceid()).getAreaname();
        String cityName = iAreasService.getById(qiandanVo.getCityid()).getAreaname();
        String areaName = proviceName + "-" + cityName;

        //插入签单之前，先查询客户表中没有有对应的这个客户，如果有 就可以直接插入 没有则先添加客户信息再插入签单数据
//        Long qiyeID = qiandanVo.getQiyeID();
//        QueryWrapper<OaKehu> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("id", qiyeID);
//        OaKehu kehu = iOaKehuService.getOne(queryWrapper);
        OaKehu oaKehu = new OaKehu();

        //如果客户表中没有客户信息
//        if (kehu == null) {
        //如果没有 先添加客户信息 再添加签单信息
        BeanUtils.copyProperties(qiandanVo, oaKehu);
        oaKehu.setKehucompanyname(qiandanVo.getKehucompanyname());
        oaKehu.setKhShowJigouName(qiandanVo.getKehucompanyname());
        oaKehu.setKehucontractname(qiandanVo.getKehucompanyname());
        oaKehu.setAreaname(areaName);
//        oaKehu.setAddStaffID(qiandanVo.getAddStaffID());
        oaKehu.setTaocanID(qiandanVo.getTaocanTypeID());
        oaKehu.setSmsRemain(0);
        oaKehu.setKehuUseState(1);
        oaKehu.setYixiangTypeID("1");
        oaKehu.setYxfromID("1");
        oaKehu.setManyiduID(1L);
        oaKehu.setAddTime(new Date());
//        SelfUserEntity userInfo = SecurityUtil.getUserInfo();
//        Long staffID = userInfo.getStaffID();
        oaKehu.setAddStaffID(staffID.toString());
        //插入客户对象并返回kehuID
        //修改客户状态为已购买状态  客户类别：1意向客户，2已购买客户(仍有效)，3已退费客户，4没续费了
        oaKehu.setKehuType(1);
        oaKehu.setDjqRemain(new BigDecimal(0));
        iOaKehuService.save(oaKehu);
//        }

        QueryWrapper<OaParameter> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("id", 2);
        OaParameter parameter = iOaParameterService.getOne(queryWrapper1);
        OaQiandanSp oaQiandanSp = new OaQiandanSp();
        OaQiandan oaQiandan = new OaQiandan();
        if (parameter.getModifyValue().equals("要审批")) {
            BeanUtils.copyProperties(qiandanVo, oaQiandanSp);
            oaQiandanSp.setTaocanTypeID(qiandanVo.getTaocanTypeID());
            oaQiandanSp.setXufeiType(1);
            oaQiandanSp.setHardwareFahuoState(0);
            oaQiandanSp.setQiandanDatetime(new Date());
            oaQiandanSp.setXinqianorxufei(1);
            oaQiandanSp.setAreaname(areaName);
//            if (kehu != null) {
//                oaQiandan.setQiyeID(kehu.getId());
//            } else {
            oaQiandanSp.setQiyeID(oaKehu.getId());
//            }
            //插入签单信息
            oaQiandan = null;
            iOaQiandanSpService.save(oaQiandanSp);
        } else {
            BeanUtils.copyProperties(qiandanVo, oaQiandan);
            oaQiandan.setQiyeID(oaKehu.getId());
            oaQiandan.setXufeiType(1);
            oaQiandan.setQiandanstate(1);
            oaQiandan.setHardwareFahuoState(0);
//            oaQiandan.setQiandanDatetime(new Date());
            oaQiandan.setXinqianorxufei(1);
            oaQiandan.setArea(1L);
            oaQiandanSp = null;
            iOaQiandanService.save(oaQiandan);

            //region 不使用的添加数据
            Long campusID = 0L;
            Integer campusNum = oaQiandan.getCampusNum();
            for (int i = 0; i < campusNum; i++) {
                Pxcampustable pxcampustable = new Pxcampustable();
                pxcampustable.setCampusName("X" + i);
                pxcampustable.setNextPayTime(oaQiandanSp.getNextpayDate());
                pxcampustable.setIsOpen(1);
                pxcampustable.setIshaveMall(1);
                pxcampustable.setIshaveZhibo(0);
                pxcampustable.setBuyDateTime(oaQiandan.getQiandanDatetime());
                pxcampustable.setQiyeID(oaQiandan.getQiyeID());
                iPxcampustableService.save(pxcampustable);

                if (i == 0) {
                    campusID = pxcampustable.getId();
                }
            }

            //添加一对一补习方式
            Pxbuxistyletable otoBxstyle = new Pxbuxistyletable();
            otoBxstyle
                    .setQiyeID(oaQiandan.getQiyeID())
                    .setIs1v1(1)
                    .setBuxiStyleName("一对一");
            iPxbuxistyletableService.save(otoBxstyle);


            //添加课程时长
            Pxclasstimestyletable onetiems = new Pxclasstimestyletable();
            Pxclasstimestyletable oneday = new Pxclasstimestyletable();
            onetiems
                    .setClasstimestylename("-1")
                    .setQiyeID(oaQiandan.getQiyeID());
            oneday
                    .setClasstimestylename("-2")
                    .setQiyeID(oaQiandan.getQiyeID());

            iPxclasstimestyletableService.save(onetiems);
            iPxclasstimestyletableService.save(oneday);

            //添加岗位
            Pxstaffposttable gw = new Pxstaffposttable();
            gw
                    .setCampusID(campusID)
                    .setQiyeID(oaQiandan.getQiyeID())
                    .setStaffpostName("超级管理员");
            iPxstaffposttableService.save(gw);


            //添加超级管理员
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String passHash = encoder.encode("123456");

            Pxstafftable peo = new Pxstafftable();
            peo
                    .setStaffName(oaKehu.getKehucontractname())
                    .setStaffTel(oaKehu.getKehutelphone())
                    .setPassword(passHash)
                    .setCampusID(campusID)
                    .setStaffPostID(gw.getId())
                    .setRole(2)
                    .setJoinTime(new Date())
                    .setShowInApp(false)
                    .setStaffState(1)
                    .setQiyeID(oaQiandan.getQiyeID());
            iPxstafftableService.save(peo);


            //添加支付类别
            Pxpaymoneystyletable pay = new Pxpaymoneystyletable();
            pay
                    .setMoneystyleName("微信")
                    .setQiyeID(oaQiandan.getQiyeID());
            iPxpaymoneystyletableService.save(pay);

            Pxpaymoneystyletable pay2 = new Pxpaymoneystyletable();
            pay2
                    .setMoneystyleName("支付宝")
                    .setQiyeID(oaQiandan.getQiyeID());
            iPxpaymoneystyletableService.save(pay2);


            //添加收支类别
            for (int i = 0; i < 9; i++) {
                Pxshouzhistyletable shouzhi = new Pxshouzhistyletable();

                switch (i) {
                    case 0:
                        shouzhi.setShouzhiStyle("新签");
                        shouzhi.setBeizhu("不允许修改和删除");
                        break;
                    case 1:
                        shouzhi.setShouzhiStyle("续费");
                        shouzhi.setBeizhu("不允许修改和删除");
                        break;
                    case 2:
                        shouzhi.setShouzhiStyle("尾款");
                        shouzhi.setBeizhu("不允许修改和删除");
                        break;
                    case 3:
                        shouzhi.setShouzhiStyle("试听收入");
                        shouzhi.setBeizhu("试听收入");
                        break;
                    case 4:
                        shouzhi.setShouzhiStyle("商城订单收入");
                        shouzhi.setBeizhu("商城订单收入");
                        break;
                    case 5:
                        shouzhi.setShouzhiStyle("退费");
                        shouzhi.setBeizhu("不允许修改和删除");
                        break;
                    case 6:
                        shouzhi.setShouzhiStyle("课程到期自动清零课耗收入");
                        shouzhi.setBeizhu("课程到期自动清零课耗收入");
                        break;
                    case 7:
                        shouzhi.setShouzhiStyle("充值收入");
                        shouzhi.setBeizhu("不允许修改和删除");
                        break;
                    case 8:
                        shouzhi.setShouzhiStyle("进销存收入");
                        shouzhi.setBeizhu("不允许修改和删除");
                        break;
                }

                if (i == 5) {
                    shouzhi.setIsshouOrzhichu("2"); //退费
                } else if (i == 6) {
                    shouzhi.setIsshouOrzhichu("3"); //课程到期自动清零课耗收入
                } else {
                    shouzhi.setIsshouOrzhichu("1");
                }

                shouzhi
                        .setLurudate(new Date())
                        .setStaffID(peo.getId())
                        .setQiyeID(oaQiandan.getQiyeID());
                iPxshouzhistyletableService.save(shouzhi);

            }

            List<Pxpowertable> power = iPxpowertableService.list(new QueryWrapper<Pxpowertable>()
                    .eq("qiyeID", 1)
                    .eq("staffpostID", 1));

            for (Pxpowertable item : power) {
                Pxpowertable pone = new Pxpowertable();

                BeanUtils.copyProperties(item, pone);
                pone
                        .setId(null)
                        .setQiyeID(oaQiandan.getQiyeID())
                        .setStaffpostID(gw.getId());
                iPxpowertableService.save(pone);
            }
            //endregion

        }

        //查询该客户有没有代金券可使用
//        QueryWrapper<OaDaijinquan> daijinquanQueryWrapper = new QueryWrapper<>();
//        daijinquanQueryWrapper.eq("qiyeID", qiandanVo.getQiyeID());
//        OaDaijinquan daijinquan = iOaDaijinquanService.getOne(daijinquanQueryWrapper);
//        if (daijinquan != null) {
//            oaQiandan.setShishoumoney((oaQiandan.getRuanjianjine().add(oaQiandan.getYingjianjine())).subtract(daijinquan.getUseDjq()));
//        } else {
//            oaQiandan.setShishoumoney(oaQiandan.getRuanjianjine().add(oaQiandan.getYingjianjine()));
//        }
//        OaDaijinquan oaDaijinquan = new OaDaijinquan();
//        BeanUtils.copyProperties(qiandanVo, oaDaijinquan);
//        SelfUserEntity userInfo = SecurityUtil.getUserInfo();
//        Long staffID = userInfo.getStaffID();
//        oaDaijinquan.setAddUser(staffID);
        //代金券不为零  才能使用代金券
//        if (oaDaijinquan.getGetDjq().compareTo(BigDecimal.ZERO) != 0) {
//            oaDaijinquan.setUseDjq(oaDaijinquan.getGetDjq());
//            qiandanVo.setShishoumoney((qiandanVo.getRuanjianjine().add(qiandanVo.getYingjianjine())).subtract(oaDaijinquan.getUseDjq()));
//            oaDaijinquan.setShuoming("使用了代金券");
//            oaDaijinquan.setAddTime(new Date());
//            //使用了代金券 向代金券的表中插入一条数据
//            iOaDaijinquanService.save(oaDaijinquan);
//        }

        //往流水表插入一条记录
        OaLiushui oaLiushui = new OaLiushui();
        oaLiushui.setLiushuiDatetime(new Date());
        oaLiushui.setLiushuishuoming("添加签单信息");
        oaLiushui.setJinbanrenStaffID(qiandanVo.getSalestaffID());
        oaLiushui.setLurutime(new Date());
        oaLiushui.setPaymoneystyleID(1L);
        oaLiushui.setIsShouruOrZhichu(1);
        oaLiushui.setLiushuiStyleID(1L);
        if (oaQiandanSp != null) {
            oaLiushui.setQiandanID(oaQiandanSp.getId());
        } else {
            oaLiushui.setQiandanID(oaQiandan.getId());
        }
        oaLiushui.setShourumoney(qiandanVo.getRuanjianjine());
        oaLiushui.setZhichumoney(new BigDecimal(0));

        OaProduct oaProduct = new OaProduct();
        BeanUtils.copyProperties(qiandanVo, oaProduct);

        boolean save = iOaLiushuiService.save(oaLiushui);
        if (save) {
            ajaxJson.setObj(oaLiushui.getId()); //返回流水id
            ajaxJson.setMsg("添加签单成功");
        }

        return ajaxJson;
    }


    /**
     * 添加意向签单
     *
     * @return
     */
    @RequestMapping(value = "/addYxQiandan", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addYxQiandan(@RequestBody QiandanVo qiandanVo) {
        AjaxJson ajaxJson = new AjaxJson();
        String proviceName = iAreasService.getById(qiandanVo.getProvinceid()).getAreaname();
        String cityName = iAreasService.getById(qiandanVo.getCityid()).getAreaname();
        String areaName = proviceName + "-" + cityName;

        long peixunTypeID = qiandanVo.getPeixunTypeID();

        long qiyeID = qiandanVo.getQiyeID();
        OaKehu oaKehu = iOaKehuService.getById(qiyeID);

        OaParameter oaParameter = iOaParameterService.getById(2);
        OaQiandanSp oaQiandanSp = new OaQiandanSp();
        OaQiandan oaQiandan = new OaQiandan();
        if (oaParameter.getModifyValue().equals("要审批")) {
            BeanUtils.copyProperties(qiandanVo, oaQiandanSp);
            oaQiandanSp.setTaocanTypeID(qiandanVo.getTaocanTypeID());

            oaQiandanSp.setPeixunTypeID(peixunTypeID);
            //long peixunTypeID2 = oaQiandanSp.getPeixunTypeID();
            oaQiandanSp.setXufeiType(1);
            oaQiandanSp.setHardwareFahuoState(qiandanVo.getHardwareFahuoState());
//            oaQiandanSp.setQiandanDatetime(new Date());
            oaQiandanSp.setXinqianorxufei(1);
            oaQiandanSp.setAreaname(areaName);
            oaQiandanSp.setArea(qiandanVo.getCityid());
            oaQiandan = null;
            iOaQiandanSpService.save(oaQiandanSp);

            oaKehu.setIsShow(0);    //客户处于待审批状态时，让它看不见
            iOaKehuService.updateById(oaKehu);
            ajaxJson.setMsg("意向签单录入成功，请等待审批");
        } else {
            Integer oldcuapusNum = oaKehu.getCampusNum();
            BeanUtils.copyProperties(qiandanVo, oaKehu);
            oaKehu.setCampusNum(qiandanVo.getCampusNum());
            oaKehu.setKehuType(2);
            oaKehu.setTaocanID(qiandanVo.getTaocanTypeID());
            oaKehu.setPeixuntypeID(qiandanVo.getPeixunTypeID());
            oaKehu.setManyiduID(3L);   //3是满意度未知

            oaKehu.setFirstqiandandatetime(qiandanVo.getQiandanDatetime());
            oaKehu.setNextpaydatetime(qiandanVo.getNextpayDate());
            oaKehu.setKehuinfobeizhu(qiandanVo.getQiandanbeizhu());
            //更新客户信息
            iOaKehuService.updateById(oaKehu);

            BeanUtils.copyProperties(qiandanVo, oaQiandan);
            oaQiandan.setXufeiType(5);
            oaQiandan.setQiandanstate(1);
            oaQiandan.setHardwareFahuoState(0);
//            oaQiandan.setQiandanDatetime(new Date());
            oaQiandan.setXinqianorxufei(1);
            oaQiandan.setArea(1L);
            oaQiandan.setAreaname(areaName);
            oaQiandanSp = null;
            iOaQiandanService.save(oaQiandan);


            //添加多的校区
            Integer campusnum = oaKehu.getCampusNum();
            if (campusnum < oldcuapusNum) {
                for (int i = 0; i < campusnum - oldcuapusNum; i++) {

                    Pxcampustable cam = new Pxcampustable();
                    cam
                            .setCampusName("Y" + i)
                            .setIshaveMall(1)
                            .setIshaveZhibo(0)
                            .setIsOpen(1)
                            .setBuyDateTime(qiandanVo.getQiandanDatetime())
                            .setNextPayTime(qiandanVo.getNextpayDate())
                            .setQiyeID(oaKehu.getId());
                    iPxcampustableService.save(cam);
                }

            }


//            //添加一对一补习方式
//            Pxbuxistyletable otoBxstyle = new Pxbuxistyletable();
//            otoBxstyle
//                    .setQiyeID(qiandanVo.getQiyeID())
//                    .setIs1v1(1)
//                    .setBuxiStyleName("一对一");
//            iPxbuxistyletableService.save(otoBxstyle);
//
//            //添加课程时长
//            Pxclasstimestyletable onetiems = new Pxclasstimestyletable();
//            Pxclasstimestyletable oneday = new Pxclasstimestyletable();
//            onetiems
//                    .setClasstimestylename("-1")
//                    .setQiyeID(qiandanVo.getQiyeID());
//            oneday
//                    .setClasstimestylename("-2")
//                    .setQiyeID(qiandanVo.getQiyeID());
//
//            iPxclasstimestyletableService.save(onetiems);
//            iPxclasstimestyletableService.save(oneday);
//
//            //添加岗位
//            Pxstaffposttable gw = new Pxstaffposttable();
//            gw
//                    .setCampusID(campusID)
//                    .setQiyeID(qiandanVo.getQiyeID())
//                    .setStaffpostName("超级管理员");
//            iPxstaffposttableService.save(gw);
//
//
//            //添加超级管理员
//            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//            String passHash = encoder.encode("123456");
//
//            Pxstafftable peo = new Pxstafftable();
//            peo
//                    .setStaffName(qiandanVo.getKehucontractname())
//                    .setStaffTel(qiandanVo.getKehutelphone())
//                    .setPassword(passHash)
//                    .setCampusID(campusID)
//                    .setStaffPostID(gw.getId())
//                    .setRole(3)
//                    .setJoinTime(new Date())
//                    .setShowInApp(false)
//                    .setStaffState(1)
//                    .setQiyeID(qiandanVo.getQiyeID());
//            iPxstafftableService.save(peo);
//
//            //添加支付类别
//            Pxpaymoneystyletable pay = new Pxpaymoneystyletable();
//            pay
//                    .setMoneystyleName("微信")
//                    .setQiyeID(qiandanVo.getQiyeID());
//            iPxpaymoneystyletableService.save(pay);
//
//            Pxpaymoneystyletable pay2 = new Pxpaymoneystyletable();
//            pay2
//                    .setMoneystyleName("支付宝")
//                    .setQiyeID(qiandanVo.getQiyeID());
//            iPxpaymoneystyletableService.save(pay2);
//
//            //添加收支类别
//            for (int i = 0; i < 9; i++) {
//                Pxshouzhistyletable shouzhi = new Pxshouzhistyletable();
//
//                switch (i) {
//                    case 0:
//                        shouzhi.setShouzhiStyle("新签");
//                        shouzhi.setBeizhu("不允许修改和删除");
//                        break;
//                    case 1:
//                        shouzhi.setShouzhiStyle("续费");
//                        shouzhi.setBeizhu("不允许修改和删除");
//                        break;
//                    case 2:
//                        shouzhi.setShouzhiStyle("尾款");
//                        shouzhi.setBeizhu("不允许修改和删除");
//                        break;
//                    case 3:
//                        shouzhi.setShouzhiStyle("试听收入");
//                        shouzhi.setBeizhu("试听收入");
//                        break;
//                    case 4:
//                        shouzhi.setShouzhiStyle("商城订单收入");
//                        shouzhi.setBeizhu("商城订单收入");
//                        break;
//                    case 5:
//                        shouzhi.setShouzhiStyle("退费");
//                        shouzhi.setBeizhu("不允许修改和删除");
//                        break;
//                    case 6:
//                        shouzhi.setShouzhiStyle("课程到期自动清零课耗收入");
//                        shouzhi.setBeizhu("课程到期自动清零课耗收入");
//                        break;
//                    case 7:
//                        shouzhi.setShouzhiStyle("充值收入");
//                        shouzhi.setBeizhu("不允许修改和删除");
//                        break;
//                    case 8:
//                        shouzhi.setShouzhiStyle("进销存收入");
//                        shouzhi.setBeizhu("不允许修改和删除");
//                        break;
//                }
//
//                if (i == 5) {
//                    shouzhi.setIsshouOrzhichu("2"); //退费
//                } else if (i == 6) {
//                    shouzhi.setIsshouOrzhichu("3"); //课程到期自动清零课耗收入
//                } else {
//                    shouzhi.setIsshouOrzhichu("1");
//                }
//
//                shouzhi
//                        .setLurudate(new Date())
//                        .setStaffID(peo.getId())
//                        .setQiyeID(qiandanVo.getQiyeID());
//                iPxshouzhistyletableService.save(shouzhi);
//
//
//            }
//
//            List<Pxpowertable> power = iPxpowertableService.list(new QueryWrapper<Pxpowertable>()
//                    .eq("qiyeID", 1)
//                    .eq("staffpostID", 1));
//
//            for (Pxpowertable item : power) {
//                Pxpowertable pone = new Pxpowertable();
//
//                BeanUtils.copyProperties(item, pone);
//                pone
//                        .setId(null)
//                        .setQiyeID(qiandanVo.getQiyeID())
//                        .setStaffpostID(gw.getId());
//                iPxpowertableService.save(pone);
//            }


            //往流水表插入一条记录
            OaLiushui oaLiushui = new OaLiushui();
            oaLiushui.setLiushuiDatetime(new Date());
            oaLiushui.setLiushuishuoming(qiandanVo.getKehucompanyname() + ",新签收费");
            oaLiushui.setJinbanrenStaffID(qiandanVo.getSalestaffID());
            oaLiushui.setLurutime(new Date());
            oaLiushui.setPaymoneystyleID(1L);
            oaLiushui.setIsShouruOrZhichu(1);
            oaLiushui.setLiushuiStyleID(1L);
            if (oaQiandanSp != null) {
                oaLiushui.setQiandanID(oaQiandanSp.getId());
            } else {
                oaLiushui.setQiandanID(oaQiandan.getId());
            }
            oaLiushui.setShourumoney(qiandanVo.getRuanjianjine());
            oaLiushui.setZhichumoney(new BigDecimal(0));

            OaProduct oaProduct = new OaProduct();
            BeanUtils.copyProperties(qiandanVo, oaProduct);

            boolean save = iOaLiushuiService.save(oaLiushui);
            if (save) {
                ajaxJson.setObj(oaLiushui.getId()); //返回流水id
                ajaxJson.setMsg("添加意向签单成功");
            }

        }

//---        OaDaijinquan oaDaijinquan = new OaDaijinquan();
//        BeanUtils.copyProperties(qiandanVo, oaDaijinquan);
//        oaDaijinquan.setAddUser(Long.parseLong(qiandanVo.getAddStaffID()));
//        //代金券不为零  才能使用代金券
//        if (oaDaijinquan.getGetDjq().compareTo(BigDecimal.ZERO) != 0) {
//            oaDaijinquan.setUseDjq(oaDaijinquan.getGetDjq());
//            qiandanVo.setShishoumoney((qiandanVo.getRuanjianjine().add(qiandanVo.getYingjianjine())).subtract(oaDaijinquan.getUseDjq()));
//            oaDaijinquan.setShuoming("使用了代金券");
//            oaDaijinquan.setAddTime(new Date());
//            //使用了代金券 向代金券的表中插入一条数据
//            iOaDaijinquanService.save(oaDaijinquan);
//--        }


        return ajaxJson;
    }

    /**
     * 修改签单信息
     *
     * @param oaQiandan
     * @return
     */
    @RequestMapping(value = "editQiandan", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public AjaxJson editQiandan(@RequestBody OaQiandan oaQiandan) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean update = iOaQiandanService.updateById(oaQiandan);
        if (update) {
            ajaxJson.setMsg("签单修改成功");
            ajaxJson.setObj(oaQiandan.getId());//返回签单ID
        } else {
            ajaxJson.setMsg("签单修改失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 分页查询所有的签单信息
     * 根据传入的hetong的值，查询不同的签单,默认查询所有的签单
     * 0：查询所有签单
     * 1：查询有签合同的签单
     * 2：查询没有签合同的签单
     *
     * @param size
     * @param current
     * @return
     */
    @RequestMapping(value = "/getAllQiandan", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllQiandan(@RequestParam(value = "size", defaultValue = "10") long size,
                                  @RequestParam(value = "current", defaultValue = "1") long current,
                                  @RequestParam(value = "hetong", defaultValue = "0") int hetong) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<OaQiandan> page = new Page<>(current, size);
        QueryWrapper<OaQiandan> queryWrapper = new QueryWrapper<>();
        if (hetong == 1) {
            queryWrapper.isNotNull("hetong");
        } else if (hetong == 2) {
            queryWrapper.isNull("hetong");
        }
        IPage<OaQiandan> oaQiandanIPage = iOaQiandanService.page(page, queryWrapper);
        ajaxJson.setObj(oaQiandanIPage);
        return ajaxJson;
    }

    /**
     * 根据签单ID对签单进行续费
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/xinqianOrXufei/{id}", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public AjaxJson xinqianOrXufei(@PathVariable("id") Long id) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper<OaQiandan> queryWrapper = new QueryWrapper<>();
        //查询签单表中是否有这条数据  有则修改xinqianorxufei的状态
        queryWrapper.eq("id", id);
        //当前签单不处于续费状态才可以修改状态为续费状态
        queryWrapper.ne("xinqianorxufei", 2);
        OaQiandan oaQiandan = iOaQiandanService.getOne(queryWrapper);
        //获取企业ID
        Long qiyeID = oaQiandan.getQiyeID();
        //根据qiyeID查询客户是否存在
        OaKehu oaKehu = iOaKehuService.getById(qiyeID);
        //如果签单信息不为空 则修改签单信息为续费
        if (oaQiandan != null && oaKehu != null) {
            //新签还是续费，1新签，2续费
            //修改xinqianorxufei字段的值为2
            oaQiandan.setXinqianorxufei(2);
            boolean update = iOaQiandanService.updateById(oaQiandan);
            if (update) {
                ajaxJson.setMsg("签单续费成功");
                //修改客户类型  kehuType为 已购买状态   客户类别：1意向客户，2已购买客户(仍有效)，3已退费客户，4没续费了
                oaKehu.setKehuType(2);
                iOaKehuService.updateById(oaKehu);
            } else {
                ajaxJson.setMsg("签单续费失败");
                ajaxJson.setCode("N");
                ajaxJson.setSuccess(false);
                return ajaxJson;
            }
            //往流水表插入一条记录
            OaLiushui oaLiushui = new OaLiushui();
            oaLiushui.setLiushuiDatetime(new Date());
            oaLiushui.setLiushuishuoming("客户签单已进行续费");
            oaLiushui.setJinbanrenStaffID(oaQiandan.getSalestaffID());
            oaLiushui.setLurutime(new Date());
            oaLiushui.setPaymoneystyleID(1L);
            oaLiushui.setQiandanID(oaQiandan.getId());
            oaLiushui.setShourumoney(oaQiandan.getRuanjianjine());
            oaLiushui.setZhichumoney(oaQiandan.getRuanjianjine());
            iOaLiushuiService.save(oaLiushui);

        }

        return ajaxJson;
    }

    /**
     * 根据签单的id 对签单进行退费
     *
     * @return
     */
    @RequestMapping(value = "/qiandanTuifei", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public AjaxJson qiandanTuifei(@RequestBody Map params) {
        AjaxJson ajaxJson = new AjaxJson();
        String qiandanId = (String) params.get("qiandanId");
        //退费的时候，退费状态的值不能为 已退费状态（qiandanstate != 2）
//        queryWrapper.ne("qiandanstate", 2);
        OaQiandan oaQiandan = iOaQiandanService.getById(qiandanId);
        //获取企业ID
        Long qiyeID = oaQiandan.getQiyeID();
        //根据qiyeID查询客户是否存在
        OaKehu oaKehu = iOaKehuService.getById(qiyeID);
        if (oaQiandan != null && oaKehu != null) {
            //修改签单的退费状态   签单状态：1正常，2已退费，默认值1
            if (oaQiandan.getQiandanstate() == 2) {
                ajaxJson.setMsg("选中的签单中存在已处于目标状态的签单，请勿重复操作");
                ajaxJson.setCode("N");
                ajaxJson.setSuccess(false);
                return ajaxJson;
            }
            oaQiandan.setQiandanstate(2);
            boolean update = iOaQiandanService.updateById(oaQiandan);
            if (update) {
                ajaxJson.setMsg("签单退费成功");
                //修改客户的类型为已退费  kehuType:客户类别：1意向客户，2已购买客户(仍有效)，3已退费客户，4没续费了
                oaKehu.setKehuType(3);
                iOaKehuService.updateById(oaKehu);
            } else {
                ajaxJson.setMsg("签单退费失败");
                ajaxJson.setCode("N");
                ajaxJson.setSuccess(false);
                return ajaxJson;
            }
            //往流水表插入一条退费记录
            OaLiushui oaLiushui = new OaLiushui();
            oaLiushui.setLiushuiDatetime(new Date());
            oaLiushui.setLiushuishuoming("客户签单已进行退费");
            String jinbanrenStaffID = (String) params.get("jinbanrenStaffID");
            oaLiushui.setJinbanrenStaffID(Long.parseLong(jinbanrenStaffID));
            oaLiushui.setLurutime(new Date());
            oaLiushui.setPaymoneystyleID(1L);
            oaLiushui.setQiandanID(oaQiandan.getId());
            oaLiushui.setShourumoney(oaQiandan.getShishoumoney());
            BigDecimal tuifeiMoney = new BigDecimal((String) params.get("tuifeiMoney"));
            if (tuifeiMoney.compareTo(oaQiandan.getShishoumoney()) == 1) {
                ajaxJson.setSuccess(false);
                ajaxJson.setCode("N");
                ajaxJson.setMsg("退款失败，退款金额不得大于产品的金额");
                return ajaxJson;
            }
            oaLiushui.setZhichumoney(tuifeiMoney);
            oaLiushui.setLiushuiStyleID(32L);
            oaLiushui.setIsShouruOrZhichu(2);
            iOaLiushuiService.save(oaLiushui);
            //往退费记录表里插入一条记录
            OaTuifeirecord oaTuifeirecord = new OaTuifeirecord();
            String tuifeiDate = (String) params.get("tuifeiDate");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = df.parse(tuifeiDate);
                oaTuifeirecord.setTuifeiDate(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (oaTuifeirecord.getOperatetuifeiDatetime() == null) {
                oaTuifeirecord.setOperatetuifeiDatetime(new Date());
            }
//            if (oaTuifeirecord.getTuifeiDate() == null) {
//                oaTuifeirecord.setTuifeiDate(new Date());
//            }
            oaTuifeirecord.setQiyeID(qiyeID);
            oaTuifeirecord.setQiandanID(Long.parseLong(qiandanId));
            oaTuifeirecord.setSalestaffID(oaQiandan.getSalestaffID());
            oaTuifeirecord.setTuifeiMoney(new BigDecimal((String) params.get("tuifeiMoney")));
            oaTuifeirecord.setTuifeiReason((String) params.get("tuifeiReason"));
            iOaTuifeirecordService.save(oaTuifeirecord);

            OaLog oaLog = new OaLog();
            oaLog.setSystemContent("签单退费,退费的签单id是:\'" + qiandanId + "\',机构名称是:\'" + oaKehu.getKehucompanyname() + "\',签单的实收金额是:\'" + oaQiandan.getShishoumoney() + "\',签单的退费金额是:\'" + tuifeiMoney + "\'");
            oaLog.setFuncName("客户签单退费");
            oaLog.setStaffID(Long.parseLong(jinbanrenStaffID));
            oaLog.setLogType(1);
            oaLog.setAddTime(new Date());
            iOaLogService.save(oaLog);

            iOaLogService.save(oaLog);
        }

        return ajaxJson;
    }


    /**
     * 分页获取所有的签单信息
     * 根据传入的hetong的值，查询不同的签单,默认查询所有的签单
     * 0：查询所有签单
     * 1：查询有签合同的签单
     * 2：查询没有签合同的签单
     *
     * @param current
     * @param size
     * @return
     */
    @RequestMapping(value = "/getAllQiandanInfo", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllQiandanInfo(@RequestParam(value = "current", defaultValue = "1") long current,
                                      @RequestParam(value = "size", defaultValue = "10") long size,
                                      @RequestParam(value = "hetong", defaultValue = "0") int hetong,
                                      String areaname, String orderid, String kehucompanyname, String taocanName, String staffName,
                                      Integer hardwareFahuoState, Integer kehuType, Integer qiandanstate, Integer xinqianorxufei,
                                      String searchStart, String searchEnd, String qdID) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<QiandanInfoVo> page = new Page<>(current, size);
        QueryWrapper<Object> queryWrapper = new QueryWrapper<>();
        if (hetong == 0 && areaname == null && kehucompanyname == null &&
                taocanName == null && hardwareFahuoState == null &&
                kehuType == null && orderid == null && qiandanstate == null && staffName == null &&
                xinqianorxufei == null && searchStart == null && searchEnd == null) {
            queryWrapper = null;
        }
        if (areaname != null && areaname.length() > 0) {
            queryWrapper.like("qiandan.areaname", areaname);
        }
        if (orderid != null && orderid.length() > 0) {
            queryWrapper.like("qiandan.orderid", orderid);
        }
        if (staffName != null && staffName.length() > 0) {
            queryWrapper.like("oa_staff.staffName", staffName);
        }
        if (kehucompanyname != null && kehucompanyname.length() > 0) {
            queryWrapper.like("kehu.kehucompanyname", kehucompanyname);
        }
        if (taocanName != null) {
            queryWrapper.like("taocan.taocanName", taocanName);
        }
        if (hardwareFahuoState != null) {
            queryWrapper.eq("qiandan.hardwareFahuoState", hardwareFahuoState);
        }

        if (xinqianorxufei != null) {
            queryWrapper.eq("qiandan.xinqianorxufei", xinqianorxufei);
        }
        if (kehuType != null) {
            queryWrapper.eq("kehu.kehuType", kehuType);
        }
        if (qiandanstate != null) {
            queryWrapper.eq("qiandan.qiandanstate", qiandanstate);
        }

        if (searchStart != null && searchEnd != null) {
            queryWrapper.between("qiandan.qiandanDatetime", searchStart, searchEnd);
        }
        if (qdID != null) {
            queryWrapper.eq("qiandan.id", qdID);
        }

        if (hetong == 1) {
            queryWrapper.isNotNull("qiandan.hetong").eq("qiandan.hetong", "已签");
        } else if (hetong == 2) {
            queryWrapper.isNull("qiandan.hetong").or().eq("qiandan.hetong", "未签");
        }
        //queryWrapper.orderByDesc("qiandan.qiandanDatetime");

        IPage<QiandanInfoVo> iPage = iOaQiandanService.getAllQiandanInfo(page, queryWrapper);
//        List<QiandanInfoVo> qiandanInfoVoList = iPage.getRecords();
        ajaxJson.setObj(iPage);
        ajaxJson.setMsg("分页获取所有的签单信息");
        return ajaxJson;
    }

    /**
     * 获取一条签单信息
     *
     * @return
     */
    @RequestMapping(value = "/getOneQiandanInfo", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getOneQiandanInfo(Long id) {
        AjaxJson ajaxJson = new AjaxJson();
        QiandanInfoVo qiandanInfo = iOaQiandanService.getOneQiandanInfo(id);
        ajaxJson.setObj(qiandanInfo);
        ajaxJson.setMsg("根据id获取一条签单信息");
        return ajaxJson;
    }

    /**
     * 设置合同
     *
     * @return
     */
    @RequestMapping(value = "/sethetong", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson sethetong(String ids, String hetong) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] idsArr = ids.split(",");
        List<String> idList = Arrays.asList(idsArr);
        List<OaQiandan> oaQiandanList = iOaQiandanService.listByIds(idList);
        if (oaQiandanList.size() > 0) {
            for (OaQiandan oaQiandan : oaQiandanList) {
                oaQiandan.setHetong(hetong);
            }
            boolean update = iOaQiandanService.updateBatchById(oaQiandanList);
            if (update) {
                ajaxJson.setMsg("设置合同成功");
            } else {
                ajaxJson.setMsg("设置合同失败");
                ajaxJson.setCode("N");
                ajaxJson.setSuccess(false);
            }
        }
        return ajaxJson;
    }


    /**
     * 对签单进行续费
     *
     * @param qiandanXufeiVo
     * @return
     */
    @RequestMapping(value = "/qiandanxufei", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson qiandanxufei(@RequestBody QiandanXufeiVo qiandanXufeiVo, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //获取签单信息
        OaQiandan oaQiandan = iOaQiandanService.getById(qiandanXufeiVo.getId());
        QueryWrapper<OaParameter> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("id", 3);
        OaParameter parameter = iOaParameterService.getOne(queryWrapper1);
        OaKehu kehu = iOaKehuService.getById(qiandanXufeiVo.getQiyeID());

        if (parameter.getModifyValue().equals("要审批")) {
            OaQiandanXufeiSp qdxfsp = new OaQiandanXufeiSp();


            qdxfsp
                    .setQiyeID(qiandanXufeiVo.getQiyeID())
                    .setOldCampusNum(kehu.getCampusNum())
                    .setAddCampusNum(qiandanXufeiVo.getAddCampusNum())
                    .setNextpayDateTime(qiandanXufeiVo.getNextpayDate())
                    .setQiandanDatetime(qiandanXufeiVo.getQiandanDatetime())
                    .setOldTaocanTypeID(oaQiandan.getTaocanTypeID())
                    .setNewTaocanTypeID(qiandanXufeiVo.getNewtaocanID())
                    .setHardwareFahuoState(qiandanXufeiVo.getHardwareFahuoState())
                    .setOrderid(qiandanXufeiVo.getOrderid())
                    .setRuanjianjine(qiandanXufeiVo.getRuanjianjine())
                    .setYingjianjine(qiandanXufeiVo.getYingjianjine())
                    .setDaijinquan(qiandanXufeiVo.getDaijinquan())
                    .setShishoumoney(qiandanXufeiVo.getShishoumoney())
                    .setZengsong(qiandanXufeiVo.getZengsong())
                    .setBuyhardwarelists(qiandanXufeiVo.getBuyhardwarelists())
                    .setQiandanbeizhu(qiandanXufeiVo.getQiandanbeizhu())
                    .setAddUser(loginUser.getStaffID())
                    .setAddTime(new Date())
                    .setSalestaffID(qiandanXufeiVo.getSalestaffID())
                    .setShengpiState(1);
            iOaQiandanXufeiSpService.save(qdxfsp);
            if (StringUtils.isNotBlank(qiandanXufeiVo.getAddCampusNextpayDate())) {
                qdxfsp.setAddCampusNextpayDateTime(qiandanXufeiVo.getAddCampusNextpayDate());
            }

            if (qiandanXufeiVo.getMaxStuNum() >= 0) {
                qdxfsp.setMaxStuNum(qiandanXufeiVo.getMaxStuNum());
            }

            iOaQiandanXufeiSpService.updateById(qdxfsp);
            ajaxJson.setMsg("添加成功，请等待审批结果");
        } else {
            OaQiandan qd = new OaQiandan();
            qd
                    .setArea(kehu.getAreaid())
                    .setAreaname(kehu.getAreaname())
                    .setBuyhardwarelists(qiandanXufeiVo.getBuyhardwarelists())
                    .setHardwareFahuoState(qiandanXufeiVo.getHardwareFahuoState())//有的时候改  没有默认0
                    .setFanyong(BigDecimal.valueOf(0))
                    .setFanyongState(0)
                    .setFanyongOptStaffID(loginUser.getStaffID()) //?
                    .setQiyeID(qiandanXufeiVo.getQiyeID())
                    .setOrderid(qiandanXufeiVo.getOrderid())
                    .setQiandanDatetime(qiandanXufeiVo.getQiandanDatetime())
                    .setNextpayDate(qiandanXufeiVo.getNextpayDate())
                    .setQiandanstate(1)
                    .setQiandanbeizhu(qiandanXufeiVo.getQiandanbeizhu())
//                    .setTaocanTypeID(qiandanXufeiVo.getNewtaocanID()) //如果有变更套餐，在存新的套餐ID
                    .setRuanjianjine(qiandanXufeiVo.getRuanjianjine())
                    .setYingjianjine(qiandanXufeiVo.getYingjianjine())
                    .setDaijinquan(qiandanXufeiVo.getDaijinquan())
                    .setShishoumoney(qiandanXufeiVo.getShishoumoney())
                    .setSalestaffID(qiandanXufeiVo.getSalestaffID())
                    .setXinqianorxufei(2)
                    .setCampusNum(kehu.getCampusNum() + qiandanXufeiVo.getAddCampusNum())
                    .setZengsong(qiandanXufeiVo.getZengsong());

            iOaQiandanService.save(qd);

            if (qiandanXufeiVo.getMaxStuNum() != 0) {
                kehu.setMaxStuNum(qiandanXufeiVo.getMaxStuNum()); //?
                qd.setMaxStuNum(qiandanXufeiVo.getMaxStuNum());
            }


            if (qiandanXufeiVo.getNewtaocanID() != 0) {
                //说明是改套餐
                qd.setTaocanTypeID(qiandanXufeiVo.getNewtaocanID()); //如果有变更套餐，在存新的套餐ID

            }


            if (qiandanXufeiVo.getAddCampusNum() > 0) {
                //说明是要加校区
                for (int i = 0; i < qiandanXufeiVo.getAddCampusNum(); i++) {
                    Pxcampustable cam = new Pxcampustable();
                    cam.setCampusName("Y" + i);
                    cam.setNextPayTime(qiandanXufeiVo.getNextpayDate());
                    cam.setIsOpen(1);
                    cam.setIshaveMall(1);
                    cam.setIshaveZhibo(0);
                    cam.setBuyDateTime(new Date());
                    cam.setQiyeID(kehu.getId());
                    iPxcampustableService.save(cam);
                }
            }
        }

        return ajaxJson;
    }


}
