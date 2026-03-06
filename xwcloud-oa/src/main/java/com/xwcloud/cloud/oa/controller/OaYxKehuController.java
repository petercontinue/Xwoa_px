package com.xwcloud.cloud.oa.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.OA.OaKehu;
import com.xwcloud.cloud.model.OA.OaLog;
import com.xwcloud.cloud.model.OA.OaParameter;
import com.xwcloud.cloud.model.OA.Vo.KehuVo;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.entity.*;
import com.xwcloud.cloud.oa.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/oaYxKehu")
public class OaYxKehuController {

    @Autowired
    private IOaKehuService iOaKehuService;

    @Autowired
    private IOaLogService iOaLogService;

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
    private IOaParameterService iOaParameterService;

    @Autowired
    Dokehu dokehu;

    @Autowired
    IPxstuzxqrecordtableService iPxstuzxqrecordtableService;

    @Autowired
    IPxstukxqtableService iPxstukxqtableService;

    @ResponseBody
    @PostMapping("kehuyanqi")
    @ApiOperation("意向客户延期")
    public AjaxJson kehuyanqi(String ids, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Calendar cal = Calendar.getInstance();
        OaParameter parameter = iOaParameterService.getById(1);
        String modifyValue = parameter.getModifyValue(); //试用天数

        String[] IDs = ids.split(",");
        String newDT = "";
        for (String item : IDs) {
            OaKehu one = iOaKehuService.getById(item);

            List<Pxcampustable> campuslist = iPxcampustableService.list(new QueryWrapper<Pxcampustable>().eq("qiyeID", one.getId()));

            for (Pxcampustable itemc : campuslist) {
                cal.setTime(itemc.getNextPayTime());
                cal.add(Calendar.DATE, Integer.parseInt(modifyValue));

                itemc.setNextPayTime(cal.getTime());
                iPxcampustableService.updateById(itemc);

                newDT = cal.getTime().toString();
            }

        }

        ajaxJson.setMsg("意向客户已延期到：" + newDT);
        return ajaxJson;
    }


    @ApiOperation("获取客户有的校区")
    @ResponseBody
    @GetMapping("getkehuAllcampus")
    public AjaxJson getkehuAllcampus(String id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxcampustableService.list(new QueryWrapper<Pxcampustable>()
                .eq("qiyeID", id)
                .ne("isOpen", 2))
        );
        return ajaxJson;
    }

    @ResponseBody
    @PostMapping("addOrdelCampus")
    @ApiOperation("加校区或者减校区")
    public AjaxJson addOrdelCampus(int type, int addnum, String campusids, String qiyeID) {
        AjaxJson ajaxJson = new AjaxJson();


        OaKehu kehu = iOaKehuService.getById(qiyeID);
        if (type == 1) {

            Pxcampustable havecam = iPxcampustableService.getOne(new QueryWrapper<Pxcampustable>()
                    .eq("qiyeID", kehu.getId())
                    .last(" limit 1 "));

            //加校区
            for (int i = 0; i < addnum; i++) {
                Pxcampustable cam = new Pxcampustable();
                cam.setCampusName("Y" + i);
                cam.setNextPayTime(havecam.getNextPayTime());
                cam.setIsOpen(1);
                cam.setIshaveMall(1);
                cam.setIshaveZhibo(0);
                cam.setBuyDateTime(new Date());
                cam.setQiyeID(kehu.getId());
                iPxcampustableService.save(cam);
            }
            kehu.setCampusNum(kehu.getCampusNum() + addnum);
            iOaKehuService.updateById(kehu);
        } else if (type == 2) {
            //减校区

            List<Pxcampustable> camlist = iPxcampustableService.list(new QueryWrapper<Pxcampustable>()
                    .eq("qiyeID", kehu.getId())
            );
            if (camlist.size() > 1) {
                String[] IDs = campusids.split(",");

                if (IDs.length == camlist.size()) {
                    ajaxJson.setCode("N");
                    ajaxJson.setMsg("最少需要一个校区，不可全部删除");
                    return ajaxJson;
                }

                for (String item : IDs) {
                    List<Pxstukxqtable> kxqlist = iPxstukxqtableService.list(new QueryWrapper<Pxstukxqtable>()
                            .eq("qiyeID", kehu.getId())
                            .eq("kxqCampusID", item)
                    );

                    List<Pxstuzxqrecordtable> zxqlist = iPxstuzxqrecordtableService.list(new QueryWrapper<Pxstuzxqrecordtable>()
                            .eq("qiyeID", kehu.getId())
                            .eq("newCampusID", kehu.getId())
                    );

                    if (kxqlist.size() > 0 || zxqlist.size() > 0) {
                        ajaxJson.setCode("N");
                        ajaxJson.setMsg("要删除的校区存在跨校区或转校区记录，不可减校区！，请联系客服");
                        return ajaxJson;
                    }

                    //开始减校区
                    String delcampus = dokehu.delcampus(item, kehu.getId());
                    ajaxJson.setMsg(delcampus);

                }
            } else {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("只有一个校区，不允许删除！");
                return ajaxJson;
            }


        }
        return ajaxJson;
    }


    /**
     * 添加客户或意向客户
     * 电话和机构名称，都不能和原表中的数据重复
     *
     * @param oaKehu
     * @return
     */
    @RequestMapping(value = "/addYixiangKehu", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addYixiangKehu(HttpServletRequest request, @RequestBody OaKehu oaKehu) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long staffID = loginUser.getStaffID();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); //转换成日期格式
        Calendar cal = Calendar.getInstance();

        AjaxJson ajaxJson = new AjaxJson();
        oaKehu.setDjqRemain(new BigDecimal(0));
        oaKehu.setSmsRemain(0);
        oaKehu.setKehuType(1);    //客户类别：1意向客户，2已购买客户(仍有效)，3已退费客户，4没续费了
        oaKehu.setKehuUseState(1);
        oaKehu.setCampusNum(oaKehu.getCampusNum());
        oaKehu.setAddTime(new Date());
//        SelfUserEntity userInfo = SecurityUtil.getUserInfo();
//        Long staffID = userInfo.getStaffID();
        oaKehu.setAddStaffID(staffID.toString());
        oaKehu.setKhShowJigouName(oaKehu.getKehucompanyname());
        //客户类别：1意向客户，2已购买客户(仍有效)，3已退费客户，4没续费了
        //电话和机构名称，都不能重复
        String kehutelphone = oaKehu.getKehutelphone();//客户联系电话
        String kehucompanyname = oaKehu.getKehucompanyname();//客户机构名称
        boolean check = iOaKehuService.checkTelPhoneOrCompanyname(kehutelphone, kehucompanyname);
        //添加前先查询修改后客户的联系电话和机构名称是否有何原表中有一样的数据
        if (!check) {
            ajaxJson.setCode("N");
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("电话号或机构名称重复，添加失败");
            return ajaxJson;
        }
        iOaKehuService.save(oaKehu);

        ajaxJson.setMsg("意向客户添加成功");
        OaLog oaLog = new OaLog();
        oaLog.setAddTime(new Date());
        oaLog.setLogType(1);
        oaLog.setFuncName("添加意向客户");
        oaLog.setSystemContent("添加了一条意向客户信息,添加客户的机构名称为:\'" + kehucompanyname + "\',客户购买的套餐类型ID为:\'" + oaKehu.getTaocanID() + "\',客户的联系电话为:\'" + kehutelphone + "\'");
        iOaLogService.save(oaLog);

        //region 数据添加（未使用）
        OaParameter parameter = iOaParameterService.getById(1);

        String modifyValue = parameter.getModifyValue(); //试用天数

        cal.setTime(new Date());
        cal.add(Calendar.DATE, Integer.parseInt(modifyValue));

        Long campusID = 0L;
        for (int i = 0; i < oaKehu.getCampusNum(); i++) {
            Pxcampustable cam = new Pxcampustable();
            cam.setCampusName("X" + i);
            cam.setNextPayTime(cal.getTime());
            cam.setIsOpen(1);
            cam.setIshaveMall(1);
            cam.setIshaveZhibo(0);
            cam.setBuyDateTime(new Date());
            cam.setQiyeID(oaKehu.getId());
            iPxcampustableService.save(cam);

            if (i == 0) {
                campusID = cam.getId();
            }
        }

        //添加一对一补习方式
        Pxbuxistyletable otoBxstyle = new Pxbuxistyletable();
        otoBxstyle
                .setQiyeID(oaKehu.getId())
                .setIs1v1(1)
                .setBuxiStyleName("一对一");
        iPxbuxistyletableService.save(otoBxstyle);


        //添加课程时长
        Pxclasstimestyletable onetiems = new Pxclasstimestyletable();
        Pxclasstimestyletable oneday = new Pxclasstimestyletable();
        onetiems
                .setClasstimestylename("-1")
                .setQiyeID(oaKehu.getId());
        oneday
                .setClasstimestylename("-2")
                .setQiyeID(oaKehu.getId());

        iPxclasstimestyletableService.save(onetiems);
        iPxclasstimestyletableService.save(oneday);

        //添加岗位
        Pxstaffposttable gw = new Pxstaffposttable();
        gw
                .setCampusID(campusID)
                .setQiyeID(oaKehu.getId())
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
                .setQiyeID(oaKehu.getId());
        iPxstafftableService.save(peo);


        //添加支付类别
        Pxpaymoneystyletable pay = new Pxpaymoneystyletable();
        pay
                .setMoneystyleName("微信")
                .setQiyeID(oaKehu.getId());
        iPxpaymoneystyletableService.save(pay);

        Pxpaymoneystyletable pay2 = new Pxpaymoneystyletable();
        pay2
                .setMoneystyleName("支付宝")
                .setQiyeID(oaKehu.getId());
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
                    .setQiyeID(oaKehu.getId());
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
                    .setQiyeID(oaKehu.getId())
                    .setStaffpostID(gw.getId());
            iPxpowertableService.save(pone);
        }

        //endregion

        ajaxJson.setObj(oaKehu.getId()); //客户ID
        return ajaxJson;
    }

    /**
     * 根据id批量删除意向客户
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delYixiangKehu", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delYixiangKehu(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String shuoming = dokehu.handleOakehu(ids, 1);
        ajaxJson.setMsg(shuoming);
        return ajaxJson;
    }


    /**
     * 分页查询所有的意向客户的信息
     *
     * @param size
     * @param current
     * @return
     */
    @RequestMapping(value = "/getAllYxKehuInfo", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllKehuInfo(@RequestParam(value = "size", defaultValue = "10") long size,
                                   @RequestParam(value = "current", defaultValue = "1") long current,
                                   String kehutelphone, String kehucompanyname, Long yixiangTypeID,
                                   Long yxfromID, String staffName, String kehucontractname, Long manyiduID, String realName,
                                   String dateSearchStart, String dateSearchEnd) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<KehuVo> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("kehu.kehuType", 1);
        if (kehutelphone != null) {
            queryWrapper.like("kehu.kehutelphone", kehutelphone);
        }
        if (kehucompanyname != null) {
            queryWrapper.like("kehu.kehucompanyname", kehucompanyname);
        }

        if (yixiangTypeID != null) {
            queryWrapper.eq("kehu.yixiangTypeID", yixiangTypeID);
        }
        if (yxfromID != null) {
            queryWrapper.eq("kehu.yxfromID", yxfromID);
        }
        if (kehucontractname != null) {
            queryWrapper.like("kehu.kehucontractname", kehucontractname);
        }
        if (manyiduID != null) {
            queryWrapper.eq("kehu.manyiduID", manyiduID);
        }
        if (realName != null) {
            queryWrapper.like("hehuoren.realName", realName);
        }
        if (dateSearchStart != null && dateSearchEnd != null) {
            queryWrapper.between("kehu.addTime", dateSearchStart, dateSearchEnd);
        }
        if (staffName != null) {
            queryWrapper.like("staff1.staffName", staffName);
        }
        queryWrapper.eq("kehu.isShow", 1);
        queryWrapper.orderByDesc("kehu.addTime");
        IPage<KehuVo> iPage = iOaKehuService.getAllKehuInfo(page, queryWrapper);
//        List<KehuVo> kehuVoList = iPage.getRecords();
        ajaxJson.setObj(iPage);
        return ajaxJson;
    }

    /**
     * 获取一个意向客户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getOneYxKehuById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getOneKehuById(@PathVariable("id") String id) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper<Object> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("kehu.id", id);
        queryWrapper.eq("kehu.kehuType", 1);
        KehuVo kehuVo = iOaKehuService.getOneKehuById(queryWrapper);
        ajaxJson.setMsg("根据id获取一条客户信息");
        ajaxJson.setObj(kehuVo);
        return ajaxJson;
    }


    /**
     * 根据id查询意向客户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getYixiangKehuById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getYixiangKehuById(@PathVariable("id") String id) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] ids = id.split(",");
        Long kehuId = Long.parseLong(ids[0]);
        QueryWrapper<OaKehu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", kehuId);
        //kehuType：客户类别：1意向客户，2已购买客户(仍有效)，3已退费客户，4没续费了
        queryWrapper.eq("kehuType", 1);
        OaKehu yixiangKehu = iOaKehuService.getOne(queryWrapper);
        ajaxJson.setMsg("根据id查询意向客户成功");
        ajaxJson.setObj(yixiangKehu);

        return ajaxJson;
    }

    /**
     * 修改意向客户
     * 电话和机构名称，都不能和原表中的数据重复
     *
     * @param oaKehu
     * @return
     */
    @RequestMapping(value = "/editYixiangKehu", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson editYixiangKehu(@RequestBody OaKehu oaKehu) {
        AjaxJson ajaxJson = new AjaxJson();
//        oaKehu.setKehucompanyname(oaKehu.getKhShowJigouName());
        oaKehu.setDjqRemain(new BigDecimal(0));
        oaKehu.setSmsRemain(0);
        oaKehu.setKehuType(1);
        oaKehu.setKehuUseState(1);
        oaKehu.setAddTime(new Date());
        //电话和机构名称，都不能重复
        String kehutelphone = oaKehu.getKehutelphone();//客户联系电话
        String kehucompanyname = oaKehu.getKehucompanyname();//客户机构名称
        //检测客户手机号或机构名称是否存在，存在则不修改
        boolean check = iOaKehuService.checkEditTelPhoneOrCompanyname(kehutelphone, kehucompanyname);
        //修改前先查询修改后客户的联系电话和机构名称是否有何原表中有一样的数据
        if (!check) {
            ajaxJson.setCode("N");
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("电话号或机构名称重复，修改失败");
            return ajaxJson;
        }
        QueryWrapper<OaKehu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", oaKehu.getId());
        boolean result = iOaKehuService.update(oaKehu, queryWrapper);
        if (result) {
            ajaxJson.setMsg("意向客户信息修改成功");
            OaLog oaLog = new OaLog();
            oaLog.setAddTime(new Date());
            oaLog.setLogType(1);
            oaLog.setFuncName("修改意向客户");
            oaLog.setSystemContent("修改了意向客户信息,修改后的意向客户的机构名称为:\'" + kehucompanyname + "\',修改后的客户的联系电话为:\'" + kehutelphone + "\'");
            iOaLogService.save(oaLog);
            ajaxJson.setObj(oaKehu);
        } else {
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
            ajaxJson.setMsg("意向客户信息修改失败");
        }
        return ajaxJson;
    }


}
