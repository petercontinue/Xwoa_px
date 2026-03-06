package com.xwcloud.cloud.oa.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.OA.*;
import com.xwcloud.cloud.model.OA.Vo.QiandanSpVo;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.entity.Pxcampustable;
import com.xwcloud.cloud.oa.service.*;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-21
 */
@Controller
@RequestMapping("/oaQiandanSp")
public class OaQiandanSpController {


    @Autowired
    private IOaQiandanSpService iOaQiandanSpService;

    @Autowired
    private IOaQiandanService iOaQiandanService;

    @Autowired
    private IOaLogService iOaLogService;

    @Autowired
    private IOaKehuService iOaKehuService;

    @Autowired
    private IPxcampustableService iPxcampustableService;

    @Autowired
    private IOaStaffService iOaStaffService;

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

    /**
     * 获取所有的签单审批信息(新签)
     *
     * @param size
     * @param current
     * @return
     */
    @RequestMapping(value = "/getAllQiandanSpInfo", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllQiandanSpInfo(@RequestParam(value = "size", defaultValue = "10") long size,
                                        @RequestParam(value = "current", defaultValue = "1") long current,
                                        String shengpiState, String kehucompanyname,
                                        String taocanName, String orderid,
                                        String staffName, String xinqianorxufei, String qdSearchStart, String qdSearchEnd,
                                        String spSearchStart, String spSearchEnd) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<QiandanSpVo> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();

        queryWrapper.eq("1", 1);

        if (StringUtils.isNotBlank(shengpiState)) {
            queryWrapper.eq("qiandansp.shengpiState", shengpiState);
        }
        if (StringUtils.isNotBlank(kehucompanyname)) {
            queryWrapper.like("kehu.kehucompanyname", kehucompanyname);
        }

        if (StringUtils.isNotBlank(taocanName)) {
            queryWrapper.like("taocan.taocanName", taocanName);
        }

        if (StringUtils.isNotBlank(orderid)) {
            queryWrapper.like("qiandansp.orderid", orderid);
        }
        if (StringUtils.isNotBlank(staffName)) {
            queryWrapper.like("staff.staffName", staffName);
        }
//        if (StringUtils.isNotBlank(xinqianorxufei)) {
//            queryWrapper.eq("qiandansp.xinqianorxufei", xinqianorxufei);
//        }
        if (StringUtils.isNotBlank(qdSearchStart) && StringUtils.isNotBlank(qdSearchEnd)) {
            queryWrapper.between("qiandansp.qiandanDatetime", qdSearchStart, qdSearchEnd);
        }
        if (StringUtils.isNotBlank(spSearchStart) && StringUtils.isNotBlank(spSearchEnd)) {
            queryWrapper.between("qiandansp.shengpiDate", spSearchStart, spSearchEnd);
        }
        Page<QiandanSpVo> iPage = iOaQiandanSpService.getAllQiandanSpInfo(page, queryWrapper);
        ajaxJson.setObj(iPage);
        ajaxJson.setMsg("获取所有的签单审批信息");
        return ajaxJson;
    }


    /**
     * 删除签单审批记录
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delQiandanSp", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delQiandanSp(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] idArr = ids.split(",");
        List<String> idList = Arrays.asList(idArr);
        boolean remove = iOaQiandanSpService.removeByIds(idList);
        if (remove) {
            ajaxJson.setMsg("删除签单审批记录成功");
        } else {
            ajaxJson.setMsg("删除签单审批记录失败");
            ajaxJson.setCode("N");
            ajaxJson.setSuccess(false);
        }

        OaLog oaLog = new OaLog();
        oaLog.setAddTime(new Date());
        ids = ids.substring(0, ids.length() - 1);
        oaLog.setFuncName("删除签单审批记录");
        oaLog.setSystemContent("删除签单审批记录,被删除的签单审批记录的id为:\'" + ids + "\'");
        oaLog.setLogType(1);
        iOaLogService.save(oaLog);

        return ajaxJson;
    }

    /**
     * 签单审批通过或不通过
     * spCondition: 1.审批通过  2.审批不通过
     *
     * @param ids
     * @param spCondition
     * @param shenpiNopassReason
     * @return
     */
    @RequestMapping(value = "/shenpiPassOrNoPass", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson shenpiPass(HttpServletRequest request, String ids, Integer spCondition, String shenpiNopassReason) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long staffID = loginUser.getStaffID();

        AjaxJson ajaxJson = new AjaxJson();
        String[] idArr = ids.split(",");
        List<String> idList = Arrays.asList(idArr);
//        QueryWrapper<OaLiushuiYewuSp> queryWrapper = new QueryWrapper<>();
        Collection<OaQiandanSp> oaQiandanSps = iOaQiandanSpService.listByIds(idList);
        for (OaQiandanSp oaQiandanSp : oaQiandanSps) {
            if (oaQiandanSp.getShengpiState() != 1) {
                ajaxJson.setMsg("请选择都是未审批的签单");
                ajaxJson.setSuccess(false);
                ajaxJson.setCode("N");
                return ajaxJson;
            }
            oaQiandanSp.setShengpiDate(new Date());
//            SelfUserEntity userInfo = SecurityUtil.getUserInfo();
//            Long staffID = userInfo.getStaffID();
            OaStaff staff = iOaStaffService.getById(staffID);
            oaQiandanSp.setShengpiStaff(staff.getStaffName());
            //日志记录
            OaLog oaLog = new OaLog();
            oaLog.setAddTime(new Date());
            oaLog.setLogType(1);
            //审批通过
            if (spCondition == 1) {
                oaQiandanSp.setShengpiState(3);
                //如果审批通过 将审批表中的数据插入到签单表中
                OaQiandan oaQiandan = new OaQiandan();
                BeanUtils.copyProperties(oaQiandanSp, oaQiandan);
                long cityid = oaQiandanSp.getArea();
                long provinceid = Long.valueOf(iAreasService.getById(cityid).getParentid());
                oaQiandan.setQiandanstate(1);
                iOaQiandanService.saveOrUpdate(oaQiandan);
                //审批通过后修改客户状态为已购买状态
                Long qiyeID = oaQiandan.getQiyeID();
                OaKehu oaKehu = iOaKehuService.getById(qiyeID);

                Integer oldcampusNum = oaKehu.getCampusNum();  //原有的校区数

                oaKehu.setKehuType(2);
                oaKehu.setManyiduID(3L);
                oaKehu.setPeixuntypeID(oaQiandanSp.getPeixunTypeID());
                oaKehu.setProvinceid(provinceid);
                oaKehu.setCityid(cityid);
                oaKehu.setFirstqiandandatetime(oaQiandanSp.getQiandanDatetime());
                oaKehu.setNextpaydatetime(oaQiandanSp.getNextpayDate());
                oaKehu.setKehuinfobeizhu(oaQiandanSp.getQiandanbeizhu());
                iOaKehuService.updateById(oaKehu);

                Integer campusnum = oaQiandan.getCampusNum(); //现在的校区数

                if (campusnum < oldcampusNum) {
                    for (int i = 0; i < campusnum - oldcampusNum; i++) {
                        Pxcampustable cam = new Pxcampustable();
                        cam
                                .setCampusName("Y" + i)
                                .setIshaveMall(1)
                                .setIshaveZhibo(0)
                                .setIsOpen(1)
                                .setBuyDateTime(oaQiandanSp.getQiandanDatetime())
                                .setNextPayTime(oaQiandanSp.getNextpayDate())
                                .setQiyeID(oaKehu.getId());
                        iPxcampustableService.save(cam);
                    }
                }


                //region 原有
//                Long campusID = 0L;
//                Integer campusNum = oaQiandan.getCampusNum();
//                for (int i = 0; i < campusNum; i++) {
//                    Pxcampustable pxcampustable = new Pxcampustable();
//                    pxcampustable.setCampusName("X"+i);
//                    pxcampustable.setNextPayTime(oaQiandanSp.getNextpayDate());
//                    pxcampustable.setIsOpen(1);
//                    pxcampustable.setIshaveMall(1);
//                    pxcampustable.setIshaveZhibo(0);
//                    pxcampustable.setBuyDateTime(oaQiandanSp.getQiandanDatetime());
//                    pxcampustable.setQiyeID(oaQiandan.getQiyeID());
//                    iPxcampustableService.save(pxcampustable);
//
//                    if (i == 0) {
//                        campusID = pxcampustable.getId();
//                    }
//                }
//
//                //添加一对一补习方式
//                Pxbuxistyletable otoBxstyle = new Pxbuxistyletable();
//                otoBxstyle
//                        .setQiyeID(oaQiandan.getQiyeID())
//                        .setIs1v1(1)
//                        .setBuxiStyleName("一对一");
//                iPxbuxistyletableService.save(otoBxstyle);
//
//
//                //添加课程时长
//                Pxclasstimestyletable onetiems = new Pxclasstimestyletable();
//                Pxclasstimestyletable oneday = new Pxclasstimestyletable();
//                onetiems
//                        .setClasstimestylename("-1")
//                        .setQiyeID(oaQiandan.getQiyeID());
//                oneday
//                        .setClasstimestylename("-2")
//                        .setQiyeID(oaQiandan.getQiyeID());
//
//                iPxclasstimestyletableService.save(onetiems);
//                iPxclasstimestyletableService.save(oneday);
//
//                //添加岗位
//                Pxstaffposttable gw = new Pxstaffposttable();
//                gw
//                        .setCampusID(campusID)
//                        .setQiyeID(oaQiandan.getQiyeID())
//                        .setStaffpostName("超级管理员");
//                iPxstaffposttableService.save(gw);
//
//
//                //添加超级管理员
//                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//                String passHash = encoder.encode("123456");
//
//                Pxstafftable peo = new Pxstafftable();
//                peo
//                        .setStaffName(oaKehu.getKehucontractname())
//                        .setStaffTel(oaKehu.getKehutelphone())
//                        .setPassword(passHash)
//                        .setCampusID(campusID)
//                        .setStaffPostID(gw.getId())
//                        .setRole(2)
//                        .setJoinTime(new Date())
//                        .setShowInApp(false)
//                        .setStaffState(1)
//                        .setQiyeID(oaQiandan.getQiyeID());
//                iPxstafftableService.save(peo);
//
//                //添加支付类别
//                Pxpaymoneystyletable pay = new Pxpaymoneystyletable();
//                pay
//                        .setMoneystyleName("微信")
//                        .setQiyeID(oaQiandan.getQiyeID());
//                iPxpaymoneystyletableService.save(pay);
//
//                Pxpaymoneystyletable pay2 = new Pxpaymoneystyletable();
//                pay2
//                        .setMoneystyleName("支付宝")
//                        .setQiyeID(oaQiandan.getQiyeID());
//                iPxpaymoneystyletableService.save(pay2);
//
//                //添加收支类别
//                for (int i = 0; i < 9; i++) {
//                    Pxshouzhistyletable shouzhi = new Pxshouzhistyletable();
//
//                    switch (i) {
//                        case 0:
//                            shouzhi.setShouzhiStyle("新签");
//                            shouzhi.setBeizhu("不允许修改和删除");
//                            break;
//                        case 1:
//                            shouzhi.setShouzhiStyle("续费");
//                            shouzhi.setBeizhu("不允许修改和删除");
//                            break;
//                        case 2:
//                            shouzhi.setShouzhiStyle("尾款");
//                            shouzhi.setBeizhu("不允许修改和删除");
//                            break;
//                        case 3:
//                            shouzhi.setShouzhiStyle("试听收入");
//                            shouzhi.setBeizhu("试听收入");
//                            break;
//                        case 4:
//                            shouzhi.setShouzhiStyle("商城订单收入");
//                            shouzhi.setBeizhu("商城订单收入");
//                            break;
//                        case 5:
//                            shouzhi.setShouzhiStyle("退费");
//                            shouzhi.setBeizhu("不允许修改和删除");
//                            break;
//                        case 6:
//                            shouzhi.setShouzhiStyle("课程到期自动清零课耗收入");
//                            shouzhi.setBeizhu("课程到期自动清零课耗收入");
//                            break;
//                        case 7:
//                            shouzhi.setShouzhiStyle("充值收入");
//                            shouzhi.setBeizhu("不允许修改和删除");
//                            break;
//                        case 8:
//                            shouzhi.setShouzhiStyle("进销存收入");
//                            shouzhi.setBeizhu("不允许修改和删除");
//                            break;
//                    }
//
//                    if (i == 5) {
//                        shouzhi.setIsshouOrzhichu("2"); //退费
//                    } else if (i == 6) {
//                        shouzhi.setIsshouOrzhichu("3"); //课程到期自动清零课耗收入
//                    } else {
//                        shouzhi.setIsshouOrzhichu("1");
//                    }
//
//                    shouzhi
//                            .setLurudate(new Date())
//                            .setStaffID(peo.getId())
//                            .setQiyeID(oaQiandan.getQiyeID());
//                    iPxshouzhistyletableService.save(shouzhi);
//
//                }
//
//                List<Pxpowertable> power = iPxpowertableService.list(new QueryWrapper<Pxpowertable>()
//                        .eq("qiyeID", 1)
//                        .eq("staffpostID", 1));
//
//                for (Pxpowertable item : power) {
//                    Pxpowertable pone = new Pxpowertable();
//
//                    BeanUtils.copyProperties(item, pone);
//                    pone
//                            .setId(null)
//                            .setQiyeID(oaQiandan.getQiyeID())
//                            .setStaffpostID(gw.getId());
//                    iPxpowertableService.save(pone);
//                }
                //endregion

                oaLog.setFuncName("签单审批通过的记录");
                oaLog.setSystemContent("签单审批通过的记录,审批的id为:\'" + oaQiandanSp.getId() + "\',审批人是:\'" + oaQiandanSp.getShengpiStaff());
            } else if (spCondition == 2) {
                //审批不通过
                oaQiandanSp.setShengpiState(2);
                oaQiandanSp.setShenpiNopassReason(shenpiNopassReason);
                oaLog.setFuncName("签单审批未通过的记录");
                oaLog.setSystemContent("签单审批未通过的记录,未通过审批的id为:\'" + oaQiandanSp.getId() + "\',审批人是:\'" + oaQiandanSp.getShengpiStaff() + "\',审批未通过的原因是:\'" + oaQiandanSp.getShenpiNopassReason() + "\'");
            }

            //向日志表中插入一条记录
            iOaLogService.save(oaLog);
        }
        boolean update = iOaQiandanSpService.updateBatchById(oaQiandanSps);
        if (update) {
            ajaxJson.setMsg("审批成功");
        } else {
            ajaxJson.setMsg("审批失败");
            ajaxJson.setCode("N");
            ajaxJson.setSuccess(false);
        }

        return ajaxJson;
    }


    @GetMapping("getxufeiQiandanSpInfo")
    @ResponseBody
    @ApiOperation("续费签单审批")
    public AjaxJson getxufeiQiandanSpInfo(@RequestParam(value = "size", defaultValue = "10") long size,
                                          @RequestParam(value = "current", defaultValue = "1") long current,
                                          String shengpiState, String kehucompanyname,
                                          String taocanName, String orderid,
                                          String staffName, String xinqianorxufei, String qdSearchStart, String qdSearchEnd,
                                          String spSearchStart, String spSearchEnd) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<HashMap<String, Object>> page = new Page<>(current, size);
        QueryWrapper<HashMap<String, Object>> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("1", 1);

        if (StringUtils.isNotBlank(shengpiState)) {
            queryWrapper.eq("qiandansp.shengpiState", shengpiState);
        }
        if (StringUtils.isNotBlank(kehucompanyname)) {
            queryWrapper.like("kehu.kehucompanyname", kehucompanyname);
        }

        if (StringUtils.isNotBlank(taocanName)) {
            queryWrapper.like("taocan.taocanName", taocanName);
        }

        if (StringUtils.isNotBlank(orderid)) {
            queryWrapper.like("qiandansp.orderid", orderid);
        }
        if (StringUtils.isNotBlank(staffName)) {
            queryWrapper.like("staff.staffName", staffName);
        }
//        if (StringUtils.isNotBlank(xinqianorxufei)) {
//            queryWrapper.eq("qiandansp.xinqianorxufei", xinqianorxufei);
//        }
        if (StringUtils.isNotBlank(qdSearchStart) && StringUtils.isNotBlank(qdSearchEnd)) {
            queryWrapper.between("qiandansp.qiandanDatetime", qdSearchStart, qdSearchEnd);
        }
        if (StringUtils.isNotBlank(spSearchStart) && StringUtils.isNotBlank(spSearchEnd)) {
            queryWrapper.between("qiandansp.shengpiDate", spSearchStart, spSearchEnd);
        }
        Page<HashMap<String, Object>> iPage = iOaQiandanXufeiSpService.getxfQiandanSpInfo(page, queryWrapper);
        ajaxJson.setObj(iPage);
        ajaxJson.setMsg("获取所有的签单审批信息");
        return ajaxJson;
    }


    @DeleteMapping("delxfQiandanSp")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delxfQiandanSp(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] idArr = ids.split(",");
        List<String> idList = Arrays.asList(idArr);
        boolean remove = iOaQiandanXufeiSpService.removeByIds(idList);
        if (remove) {
            ajaxJson.setMsg("删除签单审批记录成功");
        } else {
            ajaxJson.setMsg("删除签单审批记录失败");
            ajaxJson.setCode("N");
            ajaxJson.setSuccess(false);
        }

        OaLog oaLog = new OaLog();
        oaLog.setAddTime(new Date());
        ids = ids.substring(0, ids.length() - 1);
        oaLog.setFuncName("删除签单续费审批记录");
        oaLog.setSystemContent("删除签单续费审批记录,被删除的签单续费审批记录的id为:\'" + ids + "\'");
        oaLog.setLogType(1);
        iOaLogService.save(oaLog);

        return ajaxJson;
    }


    @PostMapping("xfshenpiPassOrNoPass")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson xfshenpiPassOrNoPass(HttpServletRequest request, String ids, Integer spCondition, String shenpiNopassReason) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long staffID = loginUser.getStaffID();

        AjaxJson ajaxJson = new AjaxJson();
        String[] idArr = ids.split(",");
        List<String> idList = Arrays.asList(idArr);

        List<OaQiandanXufeiSp> oaQiandanXufeiSps = iOaQiandanXufeiSpService.listByIds(idList);
        for (OaQiandanXufeiSp oaQiandanSp : oaQiandanXufeiSps) {
            if (oaQiandanSp.getShengpiState() != 1) {
                ajaxJson.setMsg("请选择都是未审批的签单");
                ajaxJson.setSuccess(false);
                ajaxJson.setCode("N");
                return ajaxJson;
            }
            oaQiandanSp.setShengpiDate(new Date());

            OaStaff staff = iOaStaffService.getById(staffID);
            oaQiandanSp.setShengpiStaff(staff.getStaffName());
            //日志记录
            OaLog oaLog = new OaLog();
            oaLog.setAddTime(new Date());
            oaLog.setLogType(1);
            //审批通过
            if (spCondition == 1) {

                //审批通过后修改客户状态为已购买状态
                Long qiyeID = oaQiandanSp.getQiyeID();
                OaKehu oaKehu = iOaKehuService.getById(qiyeID);

                oaQiandanSp.setShengpiState(3);
                oaQiandanSp.setShengpiStaff(loginUser.getStaffID().toString());
                oaQiandanSp.setShengpiDate(new Date());

                iOaQiandanXufeiSpService.updateById(oaQiandanSp);
                //如果审批通过 将审批表中的数据插入到签单表中
                OaQiandan oaQiandan = new OaQiandan();

                oaQiandan
                        .setArea(oaKehu.getProvinceid())
                        .setAreaname(oaKehu.getAreaname())
                        .setBuyhardwarelists(oaQiandanSp.getBuyhardwarelists())
                        .setHardwareFahuoState(oaQiandanSp.getHardwareFahuoState())//有的时候改  没有默认0
                        .setFanyong(BigDecimal.valueOf(0))
                        .setFanyongState(0)
                        .setFanyongOptStaffID(loginUser.getStaffID()) //?
                        .setQiyeID(oaQiandanSp.getQiyeID())
                        .setOrderid(oaQiandanSp.getOrderid())
                        .setQiandanDatetime(oaQiandanSp.getQiandanDatetime())
                        .setNextpayDate(oaQiandanSp.getNextpayDateTime())
                        .setQiandanstate(1)
                        .setQiandanbeizhu(oaQiandanSp.getQiandanbeizhu())
                        .setRuanjianjine(oaQiandanSp.getRuanjianjine())
                        .setYingjianjine(oaQiandanSp.getYingjianjine())
                        .setDaijinquan(oaQiandanSp.getDaijinquan())
                        .setShishoumoney(oaQiandanSp.getShishoumoney())
                        .setSalestaffID(oaQiandanSp.getSalestaffID())
                        .setXinqianorxufei(2)
                        .setCampusNum(oaKehu.getCampusNum() + oaQiandanSp.getAddCampusNum())
                        .setZengsong(oaQiandanSp.getZengsong());

                iOaQiandanService.save(oaQiandan);

                if (oaQiandanSp.getMaxStuNum() == null) {

                } else {
                    oaKehu.setMaxStuNum(oaQiandanSp.getMaxStuNum());
                    oaQiandan.setMaxStuNum(oaQiandanSp.getMaxStuNum());
                }

                if (oaQiandanSp.getNewTaocanTypeID() == null) {

                } else {
                    //说明是改套餐
                    oaQiandan.setTaocanTypeID(oaQiandanSp.getNewTaocanTypeID()); //如果有变更套餐，在存新的套餐ID
                }


                oaKehu.setNextpaydatetime(oaQiandanSp.getNextpayDateTime());
                iOaKehuService.updateById(oaKehu);


                List<Pxcampustable> camList = iPxcampustableService.list(new QueryWrapper<Pxcampustable>().eq("qiyeID", oaKehu.getId()));

                if (camList.size() > 0) {
                    for (Pxcampustable item : camList) {
                        item.setNextPayTime(oaQiandanSp.getNextpayDateTime());
                        iPxcampustableService.updateById(item);
                    }
                }


                if (oaQiandanSp.getAddCampusNum() > 0) {
                    for (int i = 0; i < oaQiandanSp.getAddCampusNum(); i++) {
                        Pxcampustable cam = new Pxcampustable();
                        cam
                                .setCampusName("Y" + i)
                                .setIshaveMall(1)
                                .setIshaveZhibo(0)
                                .setIsOpen(1)
                                .setBuyDateTime(oaQiandanSp.getQiandanDatetime())
                                .setNextPayTime(oaQiandanSp.getNextpayDateTime())
                                .setQiyeID(oaKehu.getId());
                        iPxcampustableService.save(cam);
                    }
                }

                oaLog.setFuncName("签单审批通过的记录");
                oaLog.setSystemContent("签单审批通过的记录,审批的id为:\'" + oaQiandanSp.getId() + "\',审批人是:\'" + oaQiandanSp.getShengpiStaff());
            } else if (spCondition == 2) {
                //审批不通过
                oaQiandanSp.setShengpiState(2);
                oaQiandanSp.setShenpiNopassReason(shenpiNopassReason);
                iOaQiandanXufeiSpService.updateById(oaQiandanSp);

                oaLog.setFuncName("签单审批未通过的记录");
                oaLog.setSystemContent("签单审批未通过的记录,未通过审批的id为:\'" + oaQiandanSp.getId() + "\',审批人是:\'" + oaQiandanSp.getShengpiStaff() + "\',审批未通过的原因是:\'" + oaQiandanSp.getShenpiNopassReason() + "\'");
            }

            //向日志表中插入一条记录
            iOaLogService.save(oaLog);
        }
//        boolean update = iOaQiandanXufeiSpService.updateBatchById(oaQiandanSp);
//        if (update) {
//            ajaxJson.setMsg("审批成功");
//        } else {
//            ajaxJson.setMsg("审批失败");
//            ajaxJson.setCode("N");
//            ajaxJson.setSuccess(false);
//        }

        return ajaxJson;
    }
}
