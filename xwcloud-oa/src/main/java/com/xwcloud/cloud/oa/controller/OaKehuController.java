package com.xwcloud.cloud.oa.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.DateUtil;
import com.xwcloud.cloud.model.OA.*;
import com.xwcloud.cloud.model.OA.Areas;
import com.xwcloud.cloud.model.OA.Vo.KehuVo;
import com.xwcloud.cloud.model.OA.Vo.YiqiandanKehuVo;
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
@RequestMapping("/oaKehu")
public class OaKehuController {

    @Autowired
    private IOaKehuService iOaKehuService;

    @Autowired
    private IOaQiandanService iOaQiandanService;

    @Autowired
    private IOaStaffService iOaStaffService;

    @Autowired
    private IOaPeixuntypeService iOaPeixuntypeService;

    @Autowired
    private IOaLiushuiYewuService iOaLiushuiYewuService;

    @Autowired
    private IOaLogService iOaLogService;

    @Autowired
    private IOaManyiduService iOaManyiduService;

    @Autowired
    IAreasService iAreasService;

    @Autowired
    IPxcampustableService iPxcampustableService;

    @Autowired
    IOaParameterService iOaParameterService;

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

    @ResponseBody
    @GetMapping("getonekehubyID")
    @ApiOperation("获取意向客户")
    public AjaxJson getonekehubyID(String id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iOaKehuService.getById(id));
        return ajaxJson;
    }

    @ResponseBody
    @GetMapping("getareadiquList")
    @ApiOperation("获取地区")
    public AjaxJson getareadiquList(String parentid) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iAreasService.list(new QueryWrapper<Areas>()
                .eq("arealevel", 3)
                .eq("parentid", parentid)
        ));
        return ajaxJson;
    }

    @ResponseBody
    @ApiOperation("开试用账号")
    @PostMapping("openShiyongAccount")
    public AjaxJson openShiyongAccount(
            String kehucompanyname,
            String kehutelphone,
            String yixiangTypeID,
            String kehucontractname,
            String provinceid,
            String cityid,
            String areaid,
            String yxnextgenjindatetime,
            String kehuothertel,
            String yxfromID,
            String hehuorenID,
            int campusNum,
            int maxStuNum,
            String kehuinfobeizhu,
            String weixin,
            HttpServletRequest request
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); //转换成日期格式
        Calendar cal = Calendar.getInstance();

        Date yxnDT = null;
        try {
            yxnDT = df.parse(yxnextgenjindatetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        OaKehu one = new OaKehu();
        String shen = iAreasService.getById(provinceid).getAreaname();
        String shi = iAreasService.getById(cityid).getAreaname();
        String area = "";
        if (StringUtils.isNotBlank(areaid)) {
            area = iAreasService.getById(areaid).getAreaname();
        }
        String areaName = shen + " " + shi + " " + area;
        one
                .setAreaid(Long.valueOf(areaid))
                .setAreaname(areaName)
                .setCityid(Long.valueOf(cityid))
                .setProvinceid(Long.valueOf(provinceid))
                .setCampusNum(campusNum)
                .setDjqRemain(BigDecimal.valueOf(0))
                .setSmsRemain(0)
                .setKehucompanyname(kehucompanyname)
                .setKehucontractname(kehucontractname)
                .setMaxStuNum(0)
                .setKhShowJigouName(kehucompanyname)
                .setKehuinfobeizhu(kehuinfobeizhu)
                .setKehuothertel(kehuothertel)
                .setKehuType(1)
                .setKehuUseState(1)
                .setKehutelphone(kehutelphone)
                .setManyiduID(0L)
                .setWeixin(weixin)
                .setYixiangTypeID(yixiangTypeID)
                .setYxfromID(yxfromID)
                .setAddStaffID(String.valueOf(loginUser.getStaffID()))
                .setYxnextgenjindatetime(yxnDT)
                .setAddTime(new Date())
                .setMaxStuNum(maxStuNum)
                .setHehuorenID(hehuorenID);
        iOaKehuService.save(one);

        OaParameter parameter = iOaParameterService.getById(1);

        String modifyValue = parameter.getModifyValue(); //试用天数

        cal.setTime(new Date());
        cal.add(Calendar.DATE, Integer.parseInt(modifyValue));

        Long campusID = 0L;
        for (int i = 0; i < campusNum; i++) {
            Pxcampustable cam = new Pxcampustable();
            cam.setCampusName("X" + i);
            cam.setNextPayTime(cal.getTime());
            cam.setIsOpen(1);
            cam.setIshaveMall(1);
            cam.setIshaveZhibo(0);
            cam.setBuyDateTime(new Date());
            cam.setQiyeID(one.getId());
            iPxcampustableService.save(cam);

            if (i == 0) {
                campusID = cam.getId();
            }
        }

        //添加一对一补习方式
        Pxbuxistyletable otoBxstyle = new Pxbuxistyletable();
        otoBxstyle
                .setQiyeID(one.getId())
                .setIs1v1(1)
                .setBuxiStyleName("一对一");
        iPxbuxistyletableService.save(otoBxstyle);


        //添加课程时长
        Pxclasstimestyletable onetiems = new Pxclasstimestyletable();
        Pxclasstimestyletable oneday = new Pxclasstimestyletable();
        onetiems
                .setClasstimestylename("-1")
                .setQiyeID(one.getId());
        oneday
                .setClasstimestylename("-2")
                .setQiyeID(one.getId());

        iPxclasstimestyletableService.save(onetiems);
        iPxclasstimestyletableService.save(oneday);

        //添加岗位
        Pxstaffposttable gw = new Pxstaffposttable();
        gw
                .setCampusID(campusID)
                .setQiyeID(one.getId())
                .setStaffpostName("超级管理员");
        iPxstaffposttableService.save(gw);


        //添加超级管理员
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passHash = encoder.encode("123456");

        Pxstafftable peo = new Pxstafftable();
        peo
                .setStaffName(one.getKehucontractname())
                .setStaffTel(one.getKehutelphone())
                .setPassword(passHash)
                .setCampusID(campusID)
                .setStaffPostID(gw.getId())
                .setRole(2)
                .setJoinTime(new Date())
                .setShowInApp(false)
                .setStaffState(1)
                .setQiyeID(one.getId());
        iPxstafftableService.save(peo);


        //添加支付类别
        Pxpaymoneystyletable pay = new Pxpaymoneystyletable();
        pay
                .setMoneystyleName("微信")
                .setQiyeID(one.getId());
        iPxpaymoneystyletableService.save(pay);

        Pxpaymoneystyletable pay2 = new Pxpaymoneystyletable();
        pay2
                .setMoneystyleName("支付宝")
                .setQiyeID(one.getId());
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
                    .setQiyeID(one.getId());
            iPxshouzhistyletableService.save(shouzhi);

        }

        //添加权限
        List<Pxpowertable> power = iPxpowertableService.list(new QueryWrapper<Pxpowertable>()
                .eq("qiyeID", 1)
                .eq("staffpostID", 1));

        for (Pxpowertable item : power) {
            Pxpowertable pone = new Pxpowertable();

            BeanUtils.copyProperties(item, pone);
            pone
                    .setId(null)
                    .setQiyeID(one.getId())
                    .setStaffpostID(gw.getId());
            iPxpowertableService.save(pone);
        }

        return ajaxJson;
    }

    /**
     * 分页查询所有已签单客户的信息
     * queryCondition: 1：查询全部  2：未分配售后  3：无培训  4：一个月内无日志
     * 5：三个月内签单七天内无日志  6：两个月内到期  7：一个月内到期  8：过期两个月内
     *
     * @param size
     * @param current
     * @param queryCondition
     * @return
     */
    @RequestMapping(value = "/getYiqiandanKehuInfo", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getYiqiandanKehuInfo(@RequestParam(value = "size", defaultValue = "10") long size,
                                         @RequestParam(value = "current", defaultValue = "1") long current,
                                         @RequestParam(value = "queryCondition", defaultValue = "1") int queryCondition,
                                         String kehucompanyname, String areaname, String kehucontractname,
                                         Integer manyiduID, String kehutelphone, Integer kehuType,
                                         String searchStart, String searchEnd, String afterstaffName) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<YiqiandanKehuVo> page = new Page<>(current, size);
        QueryWrapper kehuQueryWrapper = new QueryWrapper();
        kehuQueryWrapper.eq("qiandan.qiandanstate", 1);
        if (kehucompanyname != null && kehucompanyname.length() > 0) {
            kehuQueryWrapper.like("kehu.kehucompanyname", kehucompanyname);
        }
        if (areaname != null && areaname.length() > 0) {
            kehuQueryWrapper.like("kehu.areaname", areaname);
        }
        if (kehucontractname != null && kehucontractname.length() > 0) {
            kehuQueryWrapper.like("kehu.kehucontractname", kehucontractname);
        }
        if (manyiduID != null) {
            kehuQueryWrapper.eq("kehu.manyiduID", manyiduID);
        }
        if (kehutelphone != null && kehutelphone.length() > 0) {
            kehuQueryWrapper.like("kehu.kehutelphone", kehutelphone);
        }
        if (kehuType != null) {
            kehuQueryWrapper.eq("kehu.kehuType", kehuType);
        }
        if (searchStart != null && searchEnd != null) {
            kehuQueryWrapper.between("kehu.nextpaydatetime", searchStart, searchEnd);
        }
        if (afterstaffName != null && afterstaffName.length() > 0) {
            kehuQueryWrapper.like("staff2.staffName", afterstaffName);
        }
        switch (queryCondition) {
            case 1:
                ajaxJson.setMsg("已签单客户的信息");
                break;
            case 2:
                //未分配售后的查询条件
                kehuQueryWrapper.isNull("aftersalestaffID");
                ajaxJson.setMsg("未分配售后的客户信息");
                break;
            case 3:
                //无培训的查询条件
                kehuQueryWrapper.isNull("peixuntypeID");
                ajaxJson.setMsg("无培训的客户信息");
                break;
            case 4:
                //一个月内无日志的查询条件

                break;
            case 5:
                //三个月内签单七天内无日志的查询条件
                break;
            case 6:
                //两个月内到期的查询条件
                kehuQueryWrapper.last(" AND DATE_ADD(CURDATE(), INTERVAL 60 DAY) >= date(nextpaydatetime) AND TO_DAYS(CURDATE())<=TO_DAYS(nextpaydatetime)");
                ajaxJson.setMsg("还有两个月内到期的客户信息");
                break;
            case 7:
                //一个月内到期的查询条件
                kehuQueryWrapper.last(" AND DATE_ADD(CURDATE(), INTERVAL 30 DAY) >= date(nextpaydatetime) AND TO_DAYS(CURDATE())<=TO_DAYS(nextpaydatetime)");
                ajaxJson.setMsg("还有一个月内到期的客户信息");
                break;
            case 8:
                //过期两个月内的查询条件
                ajaxJson.setMsg("过期两个月内的客户信息");
                kehuQueryWrapper.last(" AND DATE_SUB(CURDATE(), INTERVAL 60 DAY) <= date(nextpaydatetime) AND nextpaydatetime <= NOW()");
                break;
        }
        kehuQueryWrapper.orderByDesc("kehu.firstqiandandatetime");

        IPage<YiqiandanKehuVo> iPage = iOaKehuService.getAllYiqiandanKehuInfo(page, kehuQueryWrapper);

        List<YiqiandanKehuVo> records = iPage.getRecords();
        //设置客户培训次数
        for (YiqiandanKehuVo yiqiandanKehuVo : records) {
            //查询客户培训次数
            Integer count = iOaKehuService.getKehuPeixunCount(yiqiandanKehuVo.getQiyeID());
            yiqiandanKehuVo.setPeixunCount(count);
        }

        ajaxJson.setObj(iPage);
        return ajaxJson;
    }

    /**
     * 获取所有的客户信息
     *
     * @return
     */
    @RequestMapping(value = "/getAllKehukehucompanyname", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllKehukehucompanyname() {
        AjaxJson ajaxJson = new AjaxJson();
        List<OaKehu> kehuList = iOaKehuService.list();
        ajaxJson.setObj(kehuList);
        ajaxJson.setMsg("获取所有的客户信息");
        return ajaxJson;
    }


    /**
     * 设置客户满意度
     *
     * @param kehuID
     * @param manyiduID
     * @return
     */
    @RequestMapping(value = "/setManyiduID/{kehuID}/{manyiduID}", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson setManyiduID(@PathVariable("kehuID") String kehuID, @PathVariable("manyiduID") Long manyiduID) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] idArr = kehuID.split(",");
        List<OaKehu> kehuList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < idArr.length; i++) {
            //先查询出客户
            OaKehu kehu = iOaKehuService.getById(idArr[i]);
            //设置客户的满意度ID
            kehu.setManyiduID(manyiduID);
            kehuList.add(kehu);
            sb.append(kehu.getKehucompanyname() + "、");
        }
        sb.deleteCharAt(sb.length() - 1);

        //根据kehuID更新满意度ID
        boolean update = iOaKehuService.updateBatchById(kehuList);
        if (update) {
            ajaxJson.setMsg("已签单客户满意度修改成功");
        } else {
            ajaxJson.setMsg("已签单客户满意度修改失败");
            ajaxJson.setCode("N");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        OaManyidu oaManyidu = iOaManyiduService.getById(manyiduID);
        //往日志表中插入一条数据
        OaLog oaLog = new OaLog();
        oaLog.setSystemContent("为\'" + sb.toString() + "\'客户设置了满意度," + "设置的满意度为:\'" + oaManyidu.getManyiduName() + "\'");
        oaLog.setFuncName("设置客户满意度");
        oaLog.setAddTime(new Date());
        oaLog.setLogType(1);
        iOaLogService.save(oaLog);

        return ajaxJson;
    }

    /**
     * 修改已签单客户的客户信息
     *
     * @param oaKehu
     * @return
     */
    @RequestMapping(value = "/editYiQiandanKehuInfo", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson editYiQiandanKehuInfo(@RequestBody OaKehu oaKehu) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean update = iOaKehuService.updateById(oaKehu);
        if (update) {
            ajaxJson.setMsg("已签单客户信息修改成功");
            OaLog oaLog = new OaLog();
            oaLog.setAddTime(new Date());
            oaLog.setLogType(1);
            oaLog.setFuncName("修改已签单客户信息");
            oaLog.setSystemContent("修改了已签单客户的信息,被修改的客户机构名称是:\'" + oaKehu.getKehucompanyname() + "\'");
            iOaLogService.save(oaLog);
        } else {
            ajaxJson.setMsg("已签单客户信息修改失败");
            ajaxJson.setCode("N");
            ajaxJson.setSuccess(false);
        }

        return ajaxJson;
    }

    /**
     * 续费 单位：年
     *
     * @param id
     * @param y
     * @return
     */
    @RequestMapping(value = "/xufei/{id}/{y}", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson xufei(@PathVariable("id") String id, @PathVariable("y") int y) {
        AjaxJson ajaxJson = new AjaxJson();
        iOaKehuService.xufei(Long.parseLong(id), y);
        QueryWrapper<OaQiandan> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("qiyeID", id);
        OaQiandan oaQiandan = iOaQiandanService.getOne(queryWrapper);
        //修改签单的xinqianorxufei为续费状态
        if (oaQiandan.getXinqianorxufei() != 2) {
            oaQiandan.setXinqianorxufei(2);
            iOaQiandanService.updateById(oaQiandan);
        }
        ajaxJson.setMsg("续费成功");
        return ajaxJson;
    }


    /**
     * 根据已签单的kehuID为该客户分配售后
     *
     * @param kehuID
     * @param aftersalestaffID
     * @return
     */
    @RequestMapping(value = "setAftersalestaffID/{kehuID}/{aftersalestaffID}", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson setAftersalestaffID(@PathVariable("kehuID") String kehuID, @PathVariable("aftersalestaffID") Long aftersalestaffID) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] ids = kehuID.split(",");
        List<String> idList = Arrays.asList(ids);
        //先查询出客户
        Collection<OaKehu> oaKehus = iOaKehuService.listByIds(idList);
        boolean update = false;
        StringBuilder sb = new StringBuilder();
        for (OaKehu kehu : oaKehus) {
            //设置客户的售后ID
            kehu.setAftersalestaffID(aftersalestaffID);
            update = iOaKehuService.updateById(kehu);
            sb.append(kehu.getKehucompanyname() + "、");
        }
        OaStaff oaStaff = iOaStaffService.getById(aftersalestaffID);
        sb.deleteCharAt(sb.length() - 1);
        //往日志表中插入一条数据
        OaLog oaLog = new OaLog();
        oaLog.setSystemContent("为\'" + sb.toString() + "\'客户分配了售后," + "分配的售后人是:\'" + oaStaff.getStaffName() + "\'");
        oaLog.setFuncName("为客户分配售后");
        oaLog.setAddTime(new Date());
        oaLog.setLogType(1);
        iOaLogService.save(oaLog);

        if (update) {
            ajaxJson.setMsg("已签单客户分配售后成功");
        } else {
            ajaxJson.setMsg("已签单客户分配售后失败");
            ajaxJson.setCode("N");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }

        return ajaxJson;
    }


    /**
     * 根据企业ID（id）设置签单的下次付款时间（nextpaydatetime）
     * 前台传递的nextpaydatetime格式：yyyy-MM-dd
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/setNextpaydatetime", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson setNextpaydatetime(HttpServletRequest request, @RequestBody Map<String, Object> params) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long staffID = loginUser.getStaffID();

        AjaxJson ajaxJson = new AjaxJson();
        //先根据id查询客户表中该客户是否存在
        QueryWrapper<OaKehu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", params.get("id"));
        OaKehu oaKehu = iOaKehuService.getOne(queryWrapper);
        //如果存在就设置下次付款时间
        if (oaKehu != null) {
            String nextpaydatetime = (String) params.get("nextpaydatetime");
//                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                Date date = format.parse(nextpaydatetime);
            Date date = DateUtil.toDate(nextpaydatetime, "yyyy-MM-dd");
            oaKehu.setNextpaydatetime(date);

            //设置客户下次付款时间
            boolean update = iOaKehuService.updateById(oaKehu);
            if (update) {
                ajaxJson.setMsg("客户下次付款时间设置成功");
                OaLiushuiYewu oaLiushuiYewu = new OaLiushuiYewu();
                //流水类型   4.下次付款时间流水
                oaLiushuiYewu.setLiushuiType(4);
                oaLiushuiYewu.setQiyeID(oaKehu.getId());
                oaLiushuiYewu.setLiushuishuoming("设置下次付款时间流水");
                oaLiushuiYewu.setAddTime(new Date());
                //添加人
//                SelfUserEntity selfUserEntity = SecurityUtil.getUserInfo();
//                Long staffID = selfUserEntity.getStaffID();
                oaLiushuiYewu.setAddUser(staffID);
                iOaLiushuiYewuService.save(oaLiushuiYewu);

            } else {
                ajaxJson.setMsg("客户下次付款时间设置失败");
                ajaxJson.setCode("N");
                ajaxJson.setSuccess(false);
            }
        } else {
            ajaxJson.setMsg("该客户在客户表中不存在，客户下次付款时间设置失败");
            ajaxJson.setCode("N");
            ajaxJson.setSuccess(false);
        }
        return ajaxJson;
    }

    /**
     * 根据id获取一条客户信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getOneKehuById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getOneKehuById(@PathVariable("id") long id) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper<Object> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("kehu.id", id);
        KehuVo kehuVo = iOaKehuService.getOneKehuById(queryWrapper);
        ajaxJson.setMsg("根据id获取一条客户信息");
        ajaxJson.setObj(kehuVo);
        return ajaxJson;
    }

    /**
     * 根据qiyeID获取一个已签单客户信息
     *
     * @param qiyeID
     * @return
     */
    @RequestMapping(value = "/getOneYiqiandanKehuInfo", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getOneYiqiandanKehuInfo(Long qiyeID) {
        AjaxJson ajaxJson = new AjaxJson();
        YiqiandanKehuVo kehuInfo = iOaKehuService.getOneYiqiandanKehuInfo(qiyeID);
        ajaxJson.setObj(kehuInfo);
        ajaxJson.setMsg("根据qiyeID获取一个已签单客户信息成功");
        return ajaxJson;
    }

    /**
     * 根据id获取一个客户信息
     *
     * @param qiyeID
     * @return
     */
    @RequestMapping(value = "/getOneKehuInfo", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getOneKehuInfo(Long qiyeID) {
        AjaxJson ajaxJson = new AjaxJson();
        OaKehu oaKehu = iOaKehuService.getById(qiyeID);
        ajaxJson.setObj(oaKehu);
        ajaxJson.setMsg("根据id获取客户信息");
        return ajaxJson;
    }


    /**
     * 分页查询所有的客户sms
     *
     * @return
     */
    @RequestMapping(value = "/getAllKehusms", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllKehusms(@RequestParam(defaultValue = "10") long size,
                                  @RequestParam(defaultValue = "1") long current,
                                  String kehucompanyname, Integer min, Integer max, String kehutelphone) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<OaKehu> page = new Page<>(current, size);
        QueryWrapper<OaKehu> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("smsRemain");
        if (kehucompanyname != null) {
            queryWrapper.like("kehucompanyname", kehucompanyname);
        }
        if (kehutelphone != null) {
            queryWrapper.like("kehutelphone", kehutelphone);
        }

        if (min != null && max != null) {
            if (min > max) {
                ajaxJson.setMsg("请输入正确的范围值");
                ajaxJson.setCode("N");
                ajaxJson.setSuccess(false);
                return ajaxJson;
            }
            queryWrapper.between("smsRemain", min, max);
        }
        IPage<OaKehu> iPage = iOaKehuService.page(page, queryWrapper);
        ajaxJson.setObj(iPage);
        return ajaxJson;
    }

    /**
     * 查询所有的机构名称
     *
     * @return
     */
    @RequestMapping(value = "/getAllYQDKehukehucompanyname", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllYQDKehukehucompanyname() {
        AjaxJson ajaxJson = new AjaxJson();
        List<OaKehu> kehuList = iOaKehuService.getAllYQDKehukehucompanyname();
        ajaxJson.setMsg("查询所有的已签单的机构名称");
        ajaxJson.setObj(kehuList);
        return ajaxJson;
    }

    /**
     * 根据客户id设置不续费
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "setKehuType/{id}", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson setBuxufei(HttpServletRequest request, @PathVariable("id") String id, Integer kehuType, String shuoming) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long staffID = loginUser.getStaffID();

        AjaxJson ajaxJson = new AjaxJson();
        OaKehu kehu = iOaKehuService.getById(id);
        if (kehu != null) {
            if (kehu.getKehuType() != kehuType) {
                kehu.setKehuType(kehuType);//设置客户类别
                //如果要设置不续费了  客户使用状态要改为已停用状态
                if (kehuType == 4) {
                    kehu.setKehuUseState(3);
                }
            } else {
                ajaxJson.setSuccess(false);
                ajaxJson.setCode("N");
                ajaxJson.setMsg("该客户已处于目标状态，请勿重复操作");
                return ajaxJson;
            }
            boolean update = iOaKehuService.updateById(kehu);
            if (update) {
                ajaxJson.setMsg("设置客户类别信息成功");
                OaLiushuiYewu oaLiushuiYewu = new OaLiushuiYewu();
                //流水类型   4.下次付款时间流水
                oaLiushuiYewu.setLiushuiType(2);
                oaLiushuiYewu.setQiyeID(kehu.getId());
                oaLiushuiYewu.setLiushuishuoming(shuoming);
                oaLiushuiYewu.setAddTime(new Date());
                //添加人
//                SelfUserEntity selfUserEntity = SecurityUtil.getUserInfo();
//                Long staffID = selfUserEntity.getStaffID();
                oaLiushuiYewu.setAddUser(staffID);
                iOaLiushuiYewuService.save(oaLiushuiYewu);
            } else {
                ajaxJson.setMsg("设置客户类别信息失败");
                ajaxJson.setCode("N");
                ajaxJson.setSuccess(false);
            }
            OaLog oaLog = new OaLog();
            oaLog.setSystemContent("客户设置客户类别信息,客户的机构名称是:\'" + kehu.getKehucompanyname() + "\'");
            oaLog.setAddTime(new Date());
            oaLog.setLogType(1);
            oaLog.setFuncName("设置客户类别");
            iOaLogService.save(oaLog);
        }
        return ajaxJson;
    }


    /**
     * 系统停用/复用  flag:1.停用  2.复用  3.系统删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/systemPauseOrReuseOrDel/{id}/{flag}", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson systemPauseOrReuseOrDel(HttpServletRequest request, @PathVariable("id") String id, @PathVariable("flag") Integer flag) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long staffID = loginUser.getStaffID();

        AjaxJson ajaxJson = new AjaxJson();
        String[] ids = id.split(",");
//        List<String> idList = Arrays.asList(ids);
//        Collection<OaKehu> oaKehuList = iOaKehuService.listByIds(idList);
        OaKehu oaKehu = iOaKehuService.getById(ids[0]);
        OaLiushuiYewu oaLiushuiYewu = new OaLiushuiYewu();
        //流水类型   6.系统停用复用
        oaLiushuiYewu.setLiushuiType(6);
//        for (OaKehu oaKehu : oaKehuList) {
        oaLiushuiYewu.setQiyeID(oaKehu.getId());
        oaLiushuiYewu.setAddTime(new Date());
        //添加人
//        SelfUserEntity selfUserEntity = SecurityUtil.getUserInfo();
//        Long staffID = selfUserEntity.getStaffID();
        oaLiushuiYewu.setAddUser(staffID);
        if (oaKehu.getKehuUseState() != 3 && flag == 1) {
            oaLiushuiYewu.setLiushuishuoming("系统停用流水");
            oaKehu.setKehuUseState(3);
            iOaLiushuiYewuService.save(oaLiushuiYewu);
        } else if (oaKehu.getKehuUseState() == 3 && flag == 1) {
            ajaxJson.setMsg("系统已处于停用状态，请勿重复操作!");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
            return ajaxJson;
        } else if (oaKehu.getKehuUseState() != 1 && flag == 2) {
            if (oaKehu.getKehuType() == 4) {
                ajaxJson.setMsg("该客户设置了不续费,系统复用失败");
                ajaxJson.setSuccess(false);
                ajaxJson.setCode("N");
                return ajaxJson;
            }
            oaLiushuiYewu.setLiushuishuoming("系统复用");
            oaKehu.setKehuUseState(1);
            iOaLiushuiYewuService.save(oaLiushuiYewu);
        } else if (oaKehu.getKehuUseState() == 1 && flag == 2) {
            ajaxJson.setMsg("系统已正处于使用状态，请勿重复操作!");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
            return ajaxJson;
        } else if (oaKehu.getKehuUseState() != 4 && flag == 3) {
            oaKehu.setKehuUseState(4);
        } else if (oaKehu.getKehuUseState() == 4 && flag == 3) {
            ajaxJson.setMsg("系统已处于删除状态，请勿重复操作!");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
            return ajaxJson;
        }

        boolean update = iOaKehuService.updateById(oaKehu);
        //往日志表中插入一条数据
        OaLog oaLog = new OaLog();
        if (update) {
            if (flag == 1) {
                ajaxJson.setMsg("系统停用成功");
                oaLog.setSystemContent("停用了企业名为:\'" + oaKehu.getKehucompanyname() + "\'的客户的系统");
                oaLog.setFuncName("系统停用");
            } else if (flag == 2) {
                ajaxJson.setMsg("系统复用成功");
                oaLog.setSystemContent("复用了企业名为:\'" + oaKehu.getKehucompanyname() + "\'的客户的系统");
                oaLog.setFuncName("系统复用");
            } else if (flag == 3) {
                ajaxJson.setMsg("系统删除成功");
                oaLog.setSystemContent("删除了企业名为:\'" + oaKehu.getKehucompanyname() + "\'的客户的系统");
                oaLog.setFuncName("系统删除");
            }
        }
//        }
        oaLog.setAddTime(new Date());
        oaLog.setLogType(1);
        iOaLogService.save(oaLog);

        return ajaxJson;
    }

    /**
     * 分页获取所有客户的代金券剩余金额
     *
     * @param size
     * @param current
     * @return
     */
    @RequestMapping(value = "/getAllDjqRemain", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllDjqRemain(@RequestParam(value = "size", defaultValue = "10") long size,
                                    @RequestParam(value = "current", defaultValue = "1") long current,
                                    Long id, String kehucompanyname) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<OaKehu> page = new Page<>(current, size);
        QueryWrapper<OaKehu> queryWrapper = new QueryWrapper<>();
        if (id != null) {
            queryWrapper.like("id", id);
        }
        if (kehucompanyname != null) {
            queryWrapper.like("kehucompanyname", kehucompanyname);
        }
        Page<OaKehu> kehuPage = iOaKehuService.page(page, queryWrapper);
        ajaxJson.setObj(kehuPage);
        return ajaxJson;
    }

}
