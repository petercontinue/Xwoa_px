package com.xwcloud.cloud.caiwu.Controller.tuifei;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.caiwu.Service.*;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.ExportExcel;
import com.xwcloud.cloud.model.Form.addFrom;
import com.xwcloud.cloud.model.Form.caiwu.*;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
@Controller
@RequestMapping("/caiwu/pxtuifeitable")
@Api(tags = "退费管理")
public class PxtuifeitableController {

    @Autowired
    IPxtuifeitableService iPxtuifeitableService; // 退费表
    @Autowired
    IPxliushuizhangtableService iPxliushuizhangtableService;// 流水表
    @Autowired
    IPxqiandaninfotableService iPxqiandaninfotableService;// 签单表
    @Autowired
    IPxqiandansubjecttableService iPxqiandansubjecttableService;// 签单课程表
    @Autowired
    IPxqiandaninfo2tableService iPxqiandaninfo2tableService;// 签单杂费表
    @Autowired
    IPxqiandansuppliesService iPxqiandansuppliesService;// 签单商品表

    @Autowired
    IPxstutableService iPxstutableService;
    @Autowired
    IPxpowertableService iPxpowertableService;
    @Autowired
    IPxkechengtableService iPxkechengtableService;
    @Autowired
    IPxsysparamdefaulttableService iPxsysparamdefaulttableService;
    @Autowired
    IPxsysparamvaluetableService iPxsysparamvaluetableService;
    @Autowired
    IPxtuifeikechengtableService iPxtuifeikechengtableService;
    @Autowired
    ITuifeishenpiService iTuifeishenpiService;  //退费审批
    @Autowired
    ITuikechenginfoService iTuikechenginfoService; //退课程
    @Autowired
    ITuisuppliseinfoService iTuisuppliseinfoService; //退商品
    @Autowired
    ITuizafeiinfoService iTuizafeiinfoService; //退杂费
    @Autowired
    IPxpaymoneystyletableService iPxpaymoneystyletableService;
    @Autowired
    IPxstafftableService iPxstafftableService;
    @Autowired
    IPxbuxikechengtableService iPxbuxikechengtableService;
    @Autowired
    IPxdaijinquantableService iPxdaijinquantableService;
    @Autowired
    IPxyouhuizhengcetableService iPxyouhuizhengcetableService;
    @Autowired
    IPxteachingsuppliestableService iPxteachingsuppliestableService;
    @Autowired
    IPxtuifeiteachsuppliestableService iPxtuifeiteachsuppliestableService;
    @Autowired
    IPxbuxistyletableService iPxbuxistyletableService;
    @Autowired
    IPxstuclasstableService iPxstuclasstableService;
    @Autowired
    IPxpaiketableService iPxpaiketableService;
    @Autowired
    IPxpaiketeachertableService iPxpaiketeachertableService;
    @Autowired
    IPxxuanketableService iPxxuanketableService;
    @Autowired
    IPxclasstableService iPxclasstableService;
    @Autowired
    IPxkeshistutableService iPxkeshistutableService;
    @Autowired
    IPxchongzhipaytableService iPxchongzhipaytableService;
    @Autowired
    ITuiqiandaninfo2Service iTuiqiandaninfo2Service;

    @Autowired
    IPxcampustableService iPxcampustableService;

    @Autowired
    ITuichongzhiyuespService iTuichongzhiyuespService;
    @Autowired
    IPxchongzhitableService iPxchongzhitableService;


    @ApiOperation("获取退费统计")
    @ResponseBody
    @GetMapping("gettuifeitongji")
    public AjaxJson gettuifeitongji(HttpServletRequest request, String toyear, String campusID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        QueryWrapper<HashMap<String, Object>> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("a.campusID", campusID);
        }
        String Y = null;
        if (StringUtils.isNotBlank(toyear)) {
            Y = toyear;
        } else {
            Y = sdf.format(new Date());
        }
        ajaxJson.setObj(iPxqiandaninfotableService.gettuifeitongji(queryWrapper, Y));

        return ajaxJson;
    }

    /**
     * @Description: getallstu()方法作用:获取所有正常学生
     * @param:[menuID, request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/4/8 9:20
     */
    @ApiOperation(value = "获取所有正常学生")
    @ResponseBody
    @RequestMapping(value = "getallstu", method = RequestMethod.GET)

    public AjaxJson getallstu(int menuID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", qiyeID);

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", menuID);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }
        ajaxJson.setObj(iPxstutableService.getallstu(queryWrapper));
        return ajaxJson;
    }

    @ApiOperation(value = "获取所有付费方式")
    @ResponseBody
    @RequestMapping(value = "getpaystyle", method = RequestMethod.GET)
    public AjaxJson getpaystyle(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        ajaxJson.setObj(iPxpaymoneystyletableService.getpaystyle(qiyeID));
        return ajaxJson;
    }


    @ApiOperation(value = "获取所有在职员工")
    @ResponseBody
    @RequestMapping(value = "getallStaff", method = RequestMethod.GET)
    public AjaxJson getallStaff(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper<listVo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("qiyeID", qiyeID)
                .eq("staffState", 1);
        ajaxJson.setObj(iPxstafftableService.getallStaff(queryWrapper));

        return ajaxJson;
    }

    @ApiOperation(value = "获取学员签单")
    @ResponseBody
    @RequestMapping(value = "getStuqiandan", method = RequestMethod.GET)
    public AjaxJson getStuqiandan(String stuID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        ajaxJson.setObj(iPxqiandaninfotableService.getstuQiandan(Long.valueOf(stuID), qiyeID));
        return ajaxJson;
    }


    //region 退费管理

    @RequestMapping(value = "/getTuifeiPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取退费流水数据分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = true),
            @ApiImplicitParam(name = "current", value = "页码", required = true),
            @ApiImplicitParam(name = "liushuiID", value = "流水ID", required = false),
            @ApiImplicitParam(name = "stuName", value = "学员名称", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
    })
    public AjaxJson getTuifeiPage(HttpServletRequest request,
                                  long size, long current,
                                  @RequestParam(required = false) String stuID,
                                  @RequestParam(required = false) String liushuiID,
                                  @RequestParam(required = false) String stuName,
                                  @RequestParam(required = false) String campusID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        QueryWrapper<PxtuifeitableVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(liushuiID)) {
            queryWrapper.like("a.id", liushuiID);
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("b.stuName", stuName);
        }
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.like("a.campusID", campusID);
        }
        if (StringUtils.isNotBlank(stuID)) {
            queryWrapper
                    .and(a -> a.isNotNull("b.zidingyiStuID").like("b.zidingyiStuID", stuID))
                    .or(b -> b.isNull("b.zidingyiStuID").eq("a.stuID", stuID));
        }
        Page<PxtuifeitableVo> page = new Page<>(current, size);


        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 564);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }
        queryWrapper.orderByDesc("a.liushuiDateTime");
        page = iPxtuifeitableService.getPage(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }


    @RequestMapping(value = "/exportRefund", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出退费")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "startRefundDate", value = "退费时间段开始", required = false),
            @ApiImplicitParam(name = "endRefundDate", value = "退费时间段结束", required = false),
    })

    public AjaxJson exportRefund(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(required = false) String campusID,
                                 @RequestParam(required = false) String startRefundDate,// 开始时间
                                 @RequestParam(required = false) String endRefundDate // 结束时间
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("1", 1);
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("a.campusID", campusID);
        }
        if (StringUtils.isNotBlank(startRefundDate)) {
            queryWrapper.ge("a.liushuiDateTime", startRefundDate);
        }
        if (StringUtils.isNotBlank(endRefundDate)) {
            queryWrapper.le("a.liushuiDateTime", endRefundDate);
        }
        List<PxtuifeitableVo> pxbooksborrowtableVos = iPxtuifeitableService.getJoinList(queryWrapper);
        List<List<Object>> borrowlist = ExportExcel.formatDataToList(new String[]{"流水ID", "退费时间", "学号", "学生姓名", "班主任",
                        "校区", "支付方式", "退费金额", "经办人"},
                pxbooksborrowtableVos,
                new String[]{"liushuiID", "liushuiDateTime-DT", "stuID", "stuName", "banzhuren", "campusName", "moneystyleName",
                        "zhichuMoney", "jinbanren"});
        try {
            ExportExcel.exportExcel(response, borrowlist, "退费记录", "退费记录导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ajaxJson;
    }


    @ApiOperation(value = "退费删除")
    @ResponseBody
    @RequestMapping(value = "deletetuifei", method = RequestMethod.DELETE)
    @Transactional(rollbackFor = {Exception.class})
    public AjaxJson deletetuifei(String liushuiID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());

        Pxliushuizhangtable liushui = iPxliushuizhangtableService.getById(liushuiID);
        Pxstutable stu = iPxstutableService.getById(liushui.getStuID());
        Pxqiandaninfotable tuiqd = iPxqiandaninfotableService.getById(liushui.getQiandanID());
        Pxtuifeitable tf = iPxtuifeitableService.getOne(new QueryWrapper<Pxtuifeitable>()
                .eq("qiandanID", tuiqd.getId())
                .eq("qiyeID", qiyeID));
        if (tf.getTuifeiType() == 1 || tf.getTuifeiType() == 3 || tf.getTuifeiType() == 7) {
            //1全科退费 3课程部分退费 7全部退费

            //删除退费产生的签单科目记录
            List<Pxqiandansubjecttable> tsubjectlist = iPxqiandansubjecttableService.list(
                    new QueryWrapper<Pxqiandansubjecttable>()
                            .eq("qianDanInfoID", tf.getQiandanID())
                            .eq("qiyeID", qiyeID)
            );
            if (tsubjectlist.size() > 0) {
                iPxqiandansubjecttableService.remove(
                        new QueryWrapper<Pxqiandansubjecttable>()
                                .eq("qianDanInfoID", tf.getQiandanID())
                                .eq("qiyeID", qiyeID));
            }

            //归回剩余课时
            List<Pxtuifeikechengtable> bxkclist = iPxtuifeikechengtableService.list(
                    new QueryWrapper<Pxtuifeikechengtable>()
                            .eq("tuifeiID", tf.getId())
                            .eq("qiyeID", qiyeID)
            );
            if (bxkclist.size() > 0) {
                for (Pxtuifeikechengtable item : bxkclist) {
                    Pxbuxikechengtable bxkc = iPxbuxikechengtableService.getById(item.getTfBuxiID());
                    bxkc.setRemainkeshi(bxkc.getRemainkeshi().add(item.getTfkeshi()));
                    iPxbuxikechengtableService.updateById(bxkc);
                }
                iPxtuifeikechengtableService.remove(
                        new QueryWrapper<Pxtuifeikechengtable>()
                                .eq("tuifeiID", tf.getId())
                                .eq("qiyeID", qiyeID));
            }


        } else if (tf.getTuifeiType() == 4 || tf.getTuifeiType() == 7) {
            //退杂费 全退
            List<Tuiqiandaninfo2> tzflist = iTuiqiandaninfo2Service.list(
                    new QueryWrapper<Tuiqiandaninfo2>()
                            .eq("tuifeiID", tf.getId())
                            .eq("qiyeID", qiyeID)
            );
            if (tzflist.size() > 0) {
                for (Tuiqiandaninfo2 item : tzflist) {
                    Pxqiandaninfo2table zfone = iPxqiandaninfo2tableService.getById(item.getTuiqianInfo2ID());
                    BigDecimal tmoney = item.getAftertuiMoney().subtract(item.getBeforetuiMoney());
                    zfone.setTuiMoney(zfone.getTuiMoney().subtract(tmoney));
                    iPxqiandaninfo2tableService.updateById(zfone);
                    iTuiqiandaninfo2Service.removeById(item);
                }
            }
        } else if (tf.getTuifeiType() == 5 || tf.getTuifeiType() == 7) {
            //退商品  全退

            List<Pxtuifeiteachsuppliestable> sup = iPxtuifeiteachsuppliestableService.list(
                    new QueryWrapper<Pxtuifeiteachsuppliestable>()
                            .eq("tuifeiID", tf.getId())
                            .eq("qiyeID", qiyeID)
            );
            if (sup.size() > 0) {
                for (Pxtuifeiteachsuppliestable item : sup) {
                    Pxqiandansupplies editsup = iPxqiandansuppliesService.getById(item.getQdsupID());

                    BigDecimal tuimon = item.getTuiNums().multiply(item.getPrice());
                    editsup.setTuiMoney(editsup.getTuiMoney().subtract(tuimon));
                    iPxqiandansuppliesService.updateById(editsup);//已退金额扣减

                    Pxteachingsuppliestable tsup = iPxteachingsuppliestableService.getById(item.getTeachSuppliesID());
                    tsup.setStockNum(tsup.getStockNum().subtract(item.getTuiNums()));
                    iPxteachingsuppliestableService.updateById(tsup);//减去库存
                }
                //删除商品退费记录
                iPxtuifeiteachsuppliestableService.remove(
                        new QueryWrapper<Pxtuifeiteachsuppliestable>()
                                .eq("tuifeiID", tf.getId())
                                .eq("qiyeID", qiyeID)
                );
            }

            //删除签单商品退费记录
            List<Pxqiandansupplies> qdsuplist = iPxqiandansuppliesService.list(
                    new QueryWrapper<Pxqiandansupplies>()
                            .eq("QiandaninfoID", tf.getQiandanID())
                            .eq("qiyeID", qiyeID));

            if (qdsuplist.size() > 0) {
                iPxqiandansuppliesService.remove(
                        new QueryWrapper<Pxqiandansupplies>()
                                .eq("QiandaninfoID", tf.getQiandanID())
                                .eq("qiyeID", qiyeID));
            }
        } else if (tf.getTuifeiType() == 6) {
            //退余额
            stu.setRemainChongzhi(stu.getRemainChongzhi().add(tf.getTuiChongzhiMoney()));
            iPxstutableService.updateById(stu);

            List<Pxchongzhipaytable> czpay = iPxchongzhipaytableService.list(
                    new QueryWrapper<Pxchongzhipaytable>()
                            .eq("tuifeiID", tf.getId())
                            .eq("qiyeID", qiyeID)
            );
            if (czpay.size() > 0) {
                iPxchongzhipaytableService.remove(new QueryWrapper<Pxchongzhipaytable>()
                        .eq("tuifeiID", tf.getId())
                        .eq("qiyeID", qiyeID)
                );
            }
        } else if (tf.getTuifeiType() == 7) {
            //全退结尾
            BigDecimal subtract = tf.getBeforeTFchongzhiRemainMoney().subtract(tf.getAfterTFchongzhiRemainMoney());
            BigDecimal kxuefei = tf.getBeforeTuifeiRemainXuefei().subtract(tf.getAfterTuifeiRemainXuefei());

            stu.setRemainChongzhi(stu.getRemainChongzhi().add(subtract));//加上被扣减的充值金额
            stu.setRemainXuefei(stu.getRemainXuefei().add(kxuefei));//加上被扣减的学费
            stu.setJifenNum(stu.getJifenNum().add(tf.getTuijifen()));
            stu.setBuxiStateID(2);
            iPxstutableService.updateById(stu);
        }

        Tuifeishenpi tfsp = iTuifeishenpiService.getById(tf.getTuifeispID());
        List<Tuikechenginfo> spkcInfo = iTuikechenginfoService.list(
                new QueryWrapper<Tuikechenginfo>()
                        .eq("tuifeispID", tf.getTuifeispID())
                        .eq("qiyeID", qiyeID)
        );
        List<Tuizafeiinfo> spzfInfo = iTuizafeiinfoService.list(
                new QueryWrapper<Tuizafeiinfo>()
                        .eq("tuifeispID", tf.getTuifeispID())
                        .eq("qiyeID", qiyeID)
        );
        List<Tuisuppliseinfo> spsupInfo = iTuisuppliseinfoService.list(
                new QueryWrapper<Tuisuppliseinfo>()
                        .eq("tuifeispID", tf.getTuifeispID())
                        .eq("qiyeID", qiyeID)
        );

        if (spkcInfo.size() > 0) {
            iTuikechenginfoService.remove(
                    new QueryWrapper<Tuikechenginfo>()
                            .eq("tuifeispID", tf.getTuifeispID())
                            .eq("qiyeID", qiyeID)
            );
        }

        if (spzfInfo.size() > 0) {
            iTuizafeiinfoService.remove(
                    new QueryWrapper<Tuizafeiinfo>()
                            .eq("tuifeispID", tf.getTuifeispID())
                            .eq("qiyeID", qiyeID)
            );
        }

        if (spsupInfo.size() > 0) {
            iTuisuppliseinfoService.remove(
                    new QueryWrapper<Tuisuppliseinfo>()
                            .eq("tuifeispID", tf.getTuifeispID())
                            .eq("qiyeID", qiyeID)
            );

        }

        iTuifeishenpiService.removeById(tfsp);
        iPxliushuizhangtableService.removeById(liushui);
        iPxqiandaninfotableService.removeById(tuiqd);
        iPxtuifeitableService.removeById(tf);

//        ajaxJson.setObj(iPxpaymoneystyletableService.getpaystyle(qiyeID));
        return ajaxJson;
    }


    //endregion

    //region 退费统计数据


    @RequestMapping(value = "/getTuifeiDetail", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取退费详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true),
            @ApiImplicitParam(name = "qiandanIDs", value = "签单ID", required = false),
    })
    public AjaxJson getTuifeiDetail(HttpServletRequest request,
                                    String stuID,
                                    String qiandanIDs) {
        AjaxJson ajaxJson = new AjaxJson();
        HashMap<String, Object> objMap = new HashMap<>();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        String qiyeID = String.valueOf(loginUser.getQiyeID());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qdinfo.stuID", stuID);
        String[] qdlist = null;
        if (StringUtils.isNotBlank(qiandanIDs)) {
            qdlist = qiandanIDs.split(",");
            queryWrapper.in("qdinfo.id", qdlist);
        }

        /**
         * 杂费数据
         */
        QueryWrapper queryWrapper3 = new QueryWrapper();
        queryWrapper3.eq("qdinfo.stuID", stuID);
        if (StringUtils.isNotBlank(qiandanIDs)) {
            qdlist = qiandanIDs.split(",");
            queryWrapper3.in("qdinfo.id", qdlist);
        }
        String otherFee = iPxtuifeitableService.getOtherFee(queryWrapper3, qiyeID);
        objMap.put("zafei", otherFee);

        /**
         * 商品数据
         */
        QueryWrapper queryWrapper2 = new QueryWrapper();
        queryWrapper2.eq("qdinfo.stuID", stuID);
        if (StringUtils.isNotBlank(qiandanIDs)) {
            qdlist = qiandanIDs.split(",");
            queryWrapper2.in("qdinfo.id", qdlist);
        }
        String commodity = iPxtuifeitableService.getCommodityFee(queryWrapper2, qiyeID);
        objMap.put("shangpin", commodity);


        /**
         * 获取课程缴纳费用
         */
        HashMap<String, String> kechengMap = new HashMap<>();
        QueryWrapper<Pxqiandaninfotable> queryWrapperqd = new QueryWrapper<>();
        queryWrapperqd
                .eq("stuID", stuID)
                .eq("qiyeID", qiyeID)
                .and(a -> a.eq("moneyStyle", 1).or().eq("moneyStyle", 2).or().eq("moneyStyle", 3));
        if (StringUtils.isNotBlank(qiandanIDs)) {
            qdlist = qiandanIDs.split(",");
            queryWrapperqd.in("id", qdlist);
        }
        List<Pxqiandaninfotable> allqdList = iPxqiandaninfotableService.list(queryWrapperqd);
        BigDecimal kechengjiaonaCost = new BigDecimal(0);

        for (Pxqiandaninfotable item : allqdList) {
            kechengjiaonaCost = kechengjiaonaCost.add(item.getShishouTotalMoney());
        }
        BigDecimal spmoney = new BigDecimal(commodity);
        BigDecimal zfmoney = new BigDecimal(otherFee);
        kechengjiaonaCost = kechengjiaonaCost.subtract(spmoney).subtract(zfmoney);

        // 课时消耗费用
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("keshi.stuID", stuID);
        String ksXiaohao = iPxtuifeitableService.getKeshiXiaohaoSum(queryWrapper1, qiyeID);
        // 接受他人赠送的课时费
        String jieshoukeshifei = iPxtuifeitableService.getJieshouKeshiSum(queryWrapper, stuID, qiyeID);
        // 转送给他人
        String zhuansongkeshifei = iPxtuifeitableService.getZhuansongKeshiSum(queryWrapper, stuID, qiyeID);


        kechengMap.put("jiaona", kechengjiaonaCost.toString());
        kechengMap.put("xiaohao", ksXiaohao);
        kechengMap.put("jieshou", jieshoukeshifei);
        kechengMap.put("zhuansong", zhuansongkeshifei);

        objMap.put("kecheng", kechengMap);

        /**
         *  获取充值费用信息
         */
        HashMap<String, String> chongzhiMap = new HashMap<>();
        // 缴费
        String jiaofei = iPxtuifeitableService.getJiaofei(stuID, qiyeID);
        // 充值赠送
        String czZsong = iPxtuifeitableService.getZengsong(stuID, qiyeID);
        // 消耗金额
        String xiaohao = iPxtuifeitableService.getXiaohaoFee(stuID, qiyeID);
        // 余额
        String yuFee = iPxtuifeitableService.getYuFee(stuID, qiyeID);

        chongzhiMap.put("jiaofei", jiaofei);
        chongzhiMap.put("czZsong", czZsong);
        chongzhiMap.put("xiaohao", xiaohao);
        chongzhiMap.put("yuFee", yuFee);
        objMap.put("chongzhi", chongzhiMap);


        /**
         * 代金券
         */
        QueryWrapper queryWrapper4 = new QueryWrapper();
        queryWrapper4.eq("qdsp.stuID", stuID);
        if (StringUtils.isNotBlank(qiandanIDs)) {
            qdlist = qiandanIDs.split(",");
            queryWrapper4.in("qdinfo.id", qdlist);
        }
        String daijinquan = iPxtuifeitableService.getDaijinquan(queryWrapper4, qiyeID);
        objMap.put("daijinquan", daijinquan);
        /**
         * 优惠政策
         */
        QueryWrapper queryWrapper5 = new QueryWrapper();
        queryWrapper5.eq("qdinfo.stuID", stuID);
        if (StringUtils.isNotBlank(qiandanIDs)) {
            qdlist = qiandanIDs.split(",");
            queryWrapper5.in("qdinfo.id", qdlist);
        }
        String youhui = iPxtuifeitableService.getYouhuiFee(queryWrapper5, qiyeID);
        objMap.put("youhui", youhui);
        /**
         * 剩余金额合计
         */
        BigDecimal kecheng = kechengjiaonaCost;
        BigDecimal chongzhi = new BigDecimal(jiaofei);
        BigDecimal shangping = new BigDecimal(commodity);
        BigDecimal other = new BigDecimal(otherFee);
        BigDecimal daijinquanD = new BigDecimal(daijinquan);
        BigDecimal youhuiD = new BigDecimal(youhui);
        BigDecimal heji = kecheng.add(chongzhi).add(shangping).add(other);
        objMap.put("heji", heji);
        ajaxJson.setObj(objMap);
        return ajaxJson;
    }

    @RequestMapping(value = "/getKechengfeiDetail", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "课程费用详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学生ID"),
            @ApiImplicitParam(name = "qiandanIDs", value = "签单IDs", required = false),
    })
    public AjaxJson getKechengfeiDetail(HttpServletRequest request,
                                        String stuID,
                                        @RequestParam(required = false) String qiandanIDs
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", qiyeID);
        queryWrapper.eq("a.stuID", stuID);
        if (StringUtils.isNotBlank(qiandanIDs)) {
            String[] IDs = qiandanIDs.split(",");
            queryWrapper.in("a.qianDanInfoID", IDs);
        }
        List<HashMap<String, String>> detailList = iPxtuifeitableService.getKechengfeiDetail(queryWrapper);
        ajaxJson.setObj(detailList);
        return ajaxJson;
    }

    @RequestMapping(value = "/getshanpingDetail", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "商品费用详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true),
            @ApiImplicitParam(name = "qiandanIDs", value = "签单IDs", required = false),
    })
    public AjaxJson getshanpingDetail(HttpServletRequest request,
                                      String stuID,
                                      @RequestParam(required = false) String qiandanIDs
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.stuID", stuID);
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(qiandanIDs)) {
            String[] IDs = qiandanIDs.split(",");
            queryWrapper.in("a.QiandaninfoID", IDs);
        }
        List<HashMap<String, String>> detailList = iPxtuifeitableService.getShangpinDetail(queryWrapper);
        ajaxJson.setObj(detailList);
        return ajaxJson;
    }


    @RequestMapping(value = "/getZafeiDetail", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "杂费用详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
            @ApiImplicitParam(name = "qiandanIDs", value = "签单IDs", required = false),
    })
    public AjaxJson getZafeiDetail(HttpServletRequest request,
                                   String stuID,
                                   @RequestParam(required = false) String qiandanIDs
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qdinfo.stuID", stuID);
        queryWrapper.eq("zf.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(qiandanIDs)) {
            String[] IDs = qiandanIDs.split(",");
            queryWrapper.in("zf.qianInfoTabID", IDs);
        }
        List<HashMap<String, String>> detailList = iPxtuifeitableService.getZafeiDetail(queryWrapper);
        ajaxJson.setObj(detailList);
        return ajaxJson;
    }

    //region  未使用

    @RequestMapping(value = "/getChongzhiDetail", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "充值费用详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true)
    })
    public AjaxJson getChongzhiDetail(HttpServletRequest request,
                                      String stuID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("tmp.stuID", stuID);
        List<HashMap<String, String>> detailList = iPxtuifeitableService.getChongzhiDetail(queryWrapper, qiyeID);
        ajaxJson.setObj(detailList);
        return ajaxJson;
    }


    @RequestMapping(value = "/getKeshiDetail", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "课时消耗详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
            @ApiImplicitParam(name = "qiandanIDs", value = "签单IDs", required = false),
    })
    public AjaxJson getKeshiDetail(HttpServletRequest request,
                                   String stuID,
                                   String qiyeID,
                                   @RequestParam(required = false, defaultValue = "") String qiandanIDs
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qdinfo.stuID", stuID);
        queryWrapper.eq("keshi.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(qiandanIDs)) {
            String[] IDs = qiandanIDs.split(",");
            queryWrapper.in("shangpin.QiandaninfoID", IDs);
        }
        List<HashMap<String, String>> detailList = iPxtuifeitableService.getKeshiDetail(queryWrapper);
        ajaxJson.setObj(detailList);
        return ajaxJson;
    }

    @RequestMapping(value = "/getReceiveDetail", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "接受他人赠送的课时费")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
            @ApiImplicitParam(name = "qiandanIDs", value = "签单ID", required = false),
    })
    public AjaxJson getReceiveDetail(HttpServletRequest request,
                                     String stuID,
                                     String qiyeID,
                                     @RequestParam(required = false) String qiandanIDs
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        // 转送人校区,赠送人学号,转送人,赠送课程,送出,送出前后剩余,接收人校区,接收人学号,接收人,课程,收到,收到前后剩余,转送详细(可点击"查看"),转送时间,操作人
        // 转送详情: 备注,课时转送详情
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("zhuansong.songstuID", stuID);
        queryWrapper.eq("zhuansong.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(qiandanIDs)) {
            queryWrapper.eq("qdinfo.id", qiandanIDs);
        }
        List<HashMap<String, String>> list = iPxtuifeitableService.getTransferDetail(queryWrapper);
        ajaxJson.setObj(list);
        return ajaxJson;
    }

    @RequestMapping(value = "/getSendOutDetail", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "转送给他人的课时费")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
            @ApiImplicitParam(name = "qiandanIDs", value = "签单ID", required = false),
    })
    public AjaxJson getSendOutDetail(HttpServletRequest request,
                                     String stuID,
                                     String qiyeID,
                                     @RequestParam(required = false) String qiandanIDs
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        // 转送人校区,赠送人学号,转送人,赠送课程,送出,送出前后剩余,接收人校区,接收人学号,
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("zhuansong.shoustuID", stuID);
        queryWrapper.eq("zhuansong.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(qiandanIDs)) {
            queryWrapper.eq("qdinfo.id", qiandanIDs);
        }
        List<HashMap<String, String>> list = iPxtuifeitableService.getTransferDetail(queryWrapper);
        ajaxJson.setObj(list);
        return ajaxJson;
    }


    @RequestMapping(value = "/getKechengList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取课程列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学生ID", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
            @ApiImplicitParam(name = "qiandanIDs", value = "签单IDs", required = false),
    })
    public AjaxJson getKechengList(HttpServletRequest request,
                                   String stuID,
                                   String qiyeID,
                                   @RequestParam(required = false) String qiandanIDs
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        // 课程名称 单价 剩余课时 剩余学费 计费方式 退费金额
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qdkc.stuID", stuID);
        queryWrapper.eq("buxikecheng.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(qiandanIDs)) {
            String[] strings = qiandanIDs.split(",");
            queryWrapper.in("qdkc.qianDanInfoID", strings);
        }
        List<HashMap<String, String>> list = iPxtuifeitableService.getKechengList(queryWrapper);
        ajaxJson.setObj(list);
        return ajaxJson;
    }

    @RequestMapping(value = "/getCommodityList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取商品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true),
            @ApiImplicitParam(name = "qiandanIDs", value = "签单IDs", required = false),
    })
    public AjaxJson getCommodityList(HttpServletRequest request,
                                     String stuID,
                                     String qiyeID,
                                     @RequestParam(required = false) String qiandanIDs
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", qiyeID);
        queryWrapper.eq("stuID", stuID);
        if (StringUtils.isNotBlank(qiandanIDs)) {
            String[] strings = qiandanIDs.split(",");
            queryWrapper.in("QiandaninfoID", strings);
        }
        List<HashMap<String, String>> hashMapList = iPxtuifeitableService.getCommodityList(queryWrapper);
        ajaxJson.setObj(hashMapList);
        return ajaxJson;
    }

    @RequestMapping(value = "/getZafeiList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取杂费列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true),
            @ApiImplicitParam(name = "qiandanIDs", value = "签单IDs", required = false),
    })
    public AjaxJson getZafeiList(HttpServletRequest request,
                                 String stuID,
                                 String qiyeID,
                                 @RequestParam(required = false) String qiandanIDs
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("zafei.qiyeID", qiyeID);
        queryWrapper.eq("qdinfo.stuID", stuID);
        if (StringUtils.isNotBlank(qiandanIDs)) {
            String[] strings = qiandanIDs.split(",");
            queryWrapper.in("zafei.qianInfoTabID", strings);
        }
        List<HashMap<String, String>> hashMapList = iPxtuifeitableService.getZafeiList(queryWrapper);
        ajaxJson.setObj(hashMapList);
        return ajaxJson;
    }

    //endregion

    //endregion

    @ApiOperation(value = "获取审批开关")
    @ResponseBody
    @RequestMapping(value = "getshenpikaiguan", method = RequestMethod.GET)
    public AjaxJson getshenpikaiguan(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());

        Pxsysparamvaluetable sysvalue = iPxsysparamvaluetableService.getOne(new QueryWrapper<Pxsysparamvaluetable>()
                .eq("sysparamTypeID", 106L)
                .eq("qiyeID", qiyeID)
        );
        ajaxJson.setObj(sysvalue.getModifyValue());
        return ajaxJson;
    }

    //region  退费操作

    @RequestMapping(value = "/Partxuefeioffbufen", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class})
    @ApiOperation(value = "课程部分退费")
    public AjaxJson Partxuefeioffbufen(HttpServletRequest request,
                                       @RequestBody addFrom from
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");


        String qiandanids = from.getQiandanids(); //选中的签单ID
        List<addkcVo> addkcVos = JSON.parseArray(from.getXadata(), addkcVo.class);
        List<tuiMessageVo> tuiMessageVos = JSON.parseArray(from.getTuimessage(), tuiMessageVo.class);
        tuiMessageVo tuiMessage = tuiMessageVos.get(0);

        //退费信息
        String payStyleID = tuiMessage.getPayStyleID();
        String processingTime = tuiMessage.getProcessingTime();
        Date tuiDate = new Date();
        try {
            tuiDate = sdf.parse(processingTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String stuID = tuiMessage.getStuID();
        String yejirenID = tuiMessage.getYejirenID();
        String shuoming = tuiMessage.getShuoming();

        String modifyValue = ""; //判断是否开启退费审批

        String stuinfoLog = "";
        String xiugaiInfo = "";
        String checkqd = "";
        String allqd = "";

        Pxsysparamvaluetable sysvalue = iPxsysparamvaluetableService.getOne(new QueryWrapper<Pxsysparamvaluetable>()
                .eq("sysparamTypeID", 106L)
                .eq("qiyeID", qiyeID));
        modifyValue = sysvalue.getModifyValue();


        Pxstutable stuInfo = iPxstutableService.getById(stuID);
        Pxcampustable cam = iPxcampustableService.getById(stuInfo.getCampusID());
        String stuNo = stuInfo.getZidingyiStuID() == null ? stuID : stuInfo.getZidingyiStuID();
        stuinfoLog += "学员：" + stuInfo.getStuName() + "学号：" + stuNo;

        Long tspID = null;
        if (modifyValue != null && modifyValue.equals("1")) {
            //开启退费审批 -->进入审批流程
            List<Tuifeishenpi> tfsplist = iTuifeishenpiService.list(new QueryWrapper<Tuifeishenpi>().eq("stuID", stuID).eq("qiyeID", qiyeID).eq("spfinish", 1));
            if (tfsplist.size() > 0) {
                ajaxJson.setCode("N");
                ajaxJson.setCode("该学员已有一条未审批退费，请在审批之后再继续操作退费！");
                return ajaxJson;
            }

            xiugaiInfo += "，【后台开启退费审批流程】";

            BigDecimal youhuijine = new BigDecimal(0);
            BigDecimal daijinquan = new BigDecimal(0);
            BigDecimal alltuimoney = new BigDecimal(0);


            for (addkcVo item : addkcVos) {
                alltuimoney = alltuimoney.add(item.getTuimoney());
                Pxbuxikechengtable bx = iPxbuxikechengtableService.getById(item.getId());
                checkqd += bx.getQianDanInfoID() + ",";
            }

            if (StringUtils.isNotBlank(qiandanids)) {
                allqd = qiandanids;
            } else {
                allqd = checkqd;
            }

            //给选中的签单合计一下优惠金额和代金券金额
            String[] qdlist = allqd.split(",");
            for (String item : qdlist) {
                Pxqiandaninfotable xzqd = iPxqiandaninfotableService.getById(item);
                if (xzqd.getYouhuiID() == null) {
                    youhuijine = youhuijine;
                } else {
                    Pxyouhuizhengcetable xzyouhui = iPxyouhuizhengcetableService.getById(xzqd.getYouhuiID());
                    if (xzyouhui != null) {
                        youhuijine = youhuijine.add(xzyouhui.getYouhui());//全部可退费的签单的优惠金额
                    }
                }

                Pxdaijinquantable xzdaijin = iPxdaijinquantableService.getOne(
                        new QueryWrapper<Pxdaijinquantable>()
                                .eq("stuID", stuID)
                                .eq("qiandanID", item)
                                .eq("qiyeID", qiyeID)
                );
                if (xzdaijin == null) {
                    daijinquan = daijinquan;
                } else {
                    daijinquan = daijinquan.add(xzdaijin.getMoney());//全部可退费的签单的代金券金额
                }
            }


            //添加审批记录
            Tuifeishenpi tfSp = new Tuifeishenpi();
            tfSp.setTuiqiandanID(allqd);
            tfSp.setSpqiandanID(allqd);
            tfSp.setCampusID(stuInfo.getCampusID());
            tfSp.setStuID(Long.valueOf(stuID));
            tfSp.setType(3);
            tfSp.setYingtuiMoney(alltuimoney);
            tfSp.setSqtuiMoney(alltuimoney);
            tfSp.setYouhuiMoney(youhuijine);
            tfSp.setDaijinquanMoney(daijinquan);
            tfSp.setTuifeibanlidate(tuiDate);
            tfSp.setTuifeishuoming(shuoming);
            tfSp.setSppayMoneystyle(Long.valueOf(payStyleID));
            tfSp.setYejiren(Long.valueOf(yejirenID));
            tfSp.setSpfinish(1);
            tfSp.setAddTiem(new Date());
            tfSp.setAdduser(staffID);
            tfSp.setQiyeID(qiyeID);
            iTuifeishenpiService.save(tfSp);

            tspID = tfSp.getId();


            for (addkcVo item : addkcVos) {
                BigDecimal tuimoney = item.getTuimoney();
                BigDecimal tuikeshi = item.getTuikeshi();
                Pxbuxikechengtable bxkc = iPxbuxikechengtableService.getById(item.getId());
                //添加商品审批记录
                Tuikechenginfo kc = new Tuikechenginfo();
                kc.setStuID(Long.valueOf(stuID));
                kc.setTuibxkechengID(Long.valueOf(item.getId()));
                kc.setRemainkeshi(tuikeshi);
                kc.setTuikeshi(tuikeshi);
                kc.setTuikechengPrice(bxkc.getKechengprice());
                kc.setAdduser(staffID);
                kc.setAddTime(new Date());
                kc.setTuifeispID(tfSp.getId());
                kc.setTuimoney(bxkc.getRemainkeshi().multiply(bxkc.getKechengprice()));
                kc.setQiyeID(qiyeID);
                iTuikechenginfoService.save(kc);

            }
            xiugaiInfo += "，退费审批、商品退费审批，添加记录完毕，等待审批通过";
            ajaxJson.setMsg(stuinfoLog + xiugaiInfo);
        } else {
            //未开启审批流程  直接退费
            for (addkcVo item : addkcVos) {
                String bxkcID = item.getId();
                BigDecimal ksnum = item.getTuikeshi();
                BigDecimal tuimoney = item.getTuimoney();

                Pxbuxikechengtable buxikc = iPxbuxikechengtableService.getById(bxkcID);
                BigDecimal beforeks = buxikc.getRemainkeshi(); //退费前课时

                BigDecimal beforexf = stuInfo.getRemainXuefei(); //退费前学员学费

                if (buxikc == null) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    ajaxJson.setCode("N");
                    ajaxJson.setMsg("未找到该学生信息的课程信息");
                    return ajaxJson;
                }
                if (Long.valueOf(stuID) != buxikc.getStuID()) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    ajaxJson.setCode("N");
                    ajaxJson.setMsg("错误的匹配信息,请检测网络是否正常");
                    return ajaxJson;
                }

                if (addkcVos.size() == 1 && ((buxikc.getJifeiStyleID() == 3 && buxikc.getZongjia().compareTo(BigDecimal.valueOf(0)) < 1) || (buxikc.getJifeiStyleID() != 3 && buxikc.getRemainkeshi().multiply(buxikc.getKechengprice()).compareTo(BigDecimal.valueOf(0)) < 1))) {
                    //  if (kclist.Length == 1 && ((buxikc.JifeiStyle == 3 && buxikc.zongjia <= 0) || (buxikc.JifeiStyle != 3 && buxikc.remainkeshi * buxikc
                    //  .kechengprice <= 0)))
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    ajaxJson.setCode("N");
                    ajaxJson.setMsg("该课程没有可退学费");
                    return ajaxJson;
                }

                Pxkechengtable tuifeikc = iPxkechengtableService.getById(buxikc.getKechengID());
                xiugaiInfo += "，退费的课程《" + tuifeikc.getKechengName() + "》,课程计费方式:" + tuifeikc.getJifeiStyleID() + "，单价:" + buxikc.getKechengprice();

                if (buxikc.getJifeiStyleID() == 3) {
                    if (buxikc.getZongjia().subtract(tuimoney).compareTo(BigDecimal.valueOf(0)) < 1) {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        ajaxJson.setMsg("单门课剩余学费不足");
                        ajaxJson.setCode("N");
                        return ajaxJson;
                    }

                    long time = (new Date().getTime() - buxikc.getEndDate().getTime()) / (24 * 1000 * 60 * 60);
                    long day = time + 1;
                    if (day > 0L) {
                        BigDecimal days = new BigDecimal(day);
                        if (days.subtract(ksnum).compareTo(BigDecimal.valueOf(0)) == 1) {
                            //剩余天数够扣减

                            int koukeshi = ksnum.intValue();
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(buxikc.getEndDate());
                            cal.add(Calendar.DATE, koukeshi);
                            buxikc.setEndDate(cal.getTime());
                            buxikc.setZongjia(buxikc.getZongjia().subtract(tuimoney));
                        } else {
                            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                            ajaxJson.setCode("N");
                            ajaxJson.setMsg("剩余天数不足，不能退这么多天！");
                            return ajaxJson;
                        }
                    } else {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        ajaxJson.setCode("N");
                        ajaxJson.setMsg("该门课已过期");
                        return ajaxJson;
                    }

                } else {
                    if (buxikc.getRemainkeshi().compareTo(ksnum) == -1) {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        ajaxJson.setCode("N");
                        ajaxJson.setMsg("剩余课时不足");
                        return ajaxJson;
                    }
                    xiugaiInfo += "，单科部分退费【前】剩余课时" + buxikc.getRemainkeshi().toString();
                    xiugaiInfo += "，要退的课时数为" + ksnum.toString();
                    buxikc.setRemainkeshi(buxikc.getRemainkeshi().subtract(ksnum));
                    xiugaiInfo += "，单科部分退费【后】剩余课时" + buxikc.getRemainkeshi().toString();
                    buxikc.setZongjia(buxikc.getZongjia().subtract(ksnum.multiply(buxikc.getKechengprice())));
                    iPxbuxikechengtableService.updateById(buxikc);
                }

                xiugaiInfo += "，单科部分退费【前】学员的总剩余学费" + stuInfo.getRemainXuefei().toString();
                xiugaiInfo += "，要退的学费为" + tuimoney.toString();
                stuInfo.setRemainXuefei(stuInfo.getRemainXuefei().subtract(tuimoney));
                iPxstutableService.updateById(stuInfo);

                BigDecimal tuikcMoney = beforexf.subtract(stuInfo.getRemainXuefei());
                xiugaiInfo += "，单科部分退费【后】学员的总剩余学费" + stuInfo.getRemainXuefei().toString();

                Pxqiandaninfotable tuiqd = new Pxqiandaninfotable();
                tuiqd.setStuID(Long.valueOf(stuID));
                tuiqd.setQiandandate(tuiDate);
                tuiqd.setShishouTotalMoney(tuimoney.negate());
                tuiqd.setHetongMoney(tuimoney.negate());
                tuiqd.setMoneyStyle(3);
                tuiqd.setBeizhu(shuoming);
                tuiqd.setQianDanStaffID(Long.valueOf(yejirenID));
                tuiqd.setRecordInStaffID(staffID);
                tuiqd.setRecordInTime(new Date());
                tuiqd.setPayMoneyStyle(Long.valueOf(payStyleID));
                tuiqd.setCampusID(stuInfo.getCampusID());
                tuiqd.setIsdingjing(1);
                tuiqd.setQiyeID(qiyeID);
                iPxqiandaninfotableService.save(tuiqd);


                Pxqiandansubjecttable qdkc = new Pxqiandansubjecttable();
                qdkc.setStuID(Long.valueOf(stuID));
                qdkc.setQiandandate(tuiDate);
                qdkc.setKechengID(buxikc.getKechengID());
                qdkc.setKechengprice(buxikc.getKechengprice());
                qdkc.setOriginalprice(buxikc.getOriginalprice());
                qdkc.setBuykeshiNum(buxikc.getRemainkeshi().negate());
                qdkc.setQianDanInfoID(tuiqd.getId());
                qdkc.setZongjia(buxikc.getKechengprice().multiply(buxikc.getKeshiNum()).negate());
                qdkc.setStartDate(buxikc.getStartDate());
                qdkc.setEndDate(buxikc.getEndDate());
                qdkc.setKechengStyle(4);
                qdkc.setDiscount(BigDecimal.valueOf(10));
                qdkc.setQiyeID(qiyeID);
                iPxqiandansubjecttableService.save(qdkc);


                //流水记录中添加退费记录
                Pxliushuizhangtable ls = new Pxliushuizhangtable();
                ls.setLiushuiDateTime(tuiDate);
                ls.setCampusID(stuInfo.getCampusID());
                ls.setLiushuiZaiyao("校区：" + cam.getCampusName() + stuinfoLog + ",单科部分退费");
                ls.setPayMoneyStyle(Long.valueOf(payStyleID));
                ls.setShouruMoney(BigDecimal.valueOf(0));
                ls.setZhichuMoney(tuimoney);
                ls.setShouzhiStyleID(6L);
                ls.setJinbanRen(Long.valueOf(yejirenID));
                ls.setStuID(Long.valueOf(stuID));
                ls.setQiandanID(tuiqd.getId());
                ls.setLuruTime(new Date());
                ls.setAddStaffID(staffID);
                ls.setQiyeID(qiyeID);
                iPxliushuizhangtableService.save(ls);
                xiugaiInfo += "，，签单信息、签单科目、流水账，添加记录完毕";


                //添加退费记录--做可逆操作
                Pxtuifeitable tf = new Pxtuifeitable();
                tf.setStuID(Long.valueOf(stuID));
                tf.setTuifeiType(3);
                tf.setQiandanID(tuiqd.getId());

                tf.setBeforeTuifeiRemainXuefei(beforexf);
                tf.setShoudTuifeiTotalMoney(tuikcMoney); //使用系统计算的
                tf.setShijiTuifeiTotalMoney(tuimoney);
                tf.setAfterTuifeiRemainXuefei(stuInfo.getRemainXuefei());

                tf.setBeforeTuifeiJifen(stuInfo.getJifenNum());
                tf.setTuijifen(BigDecimal.valueOf(0));
                tf.setAfterTuifeiJifen(stuInfo.getJifenNum());

                tf.setBeforeTFchongzhiRemainMoney(stuInfo.getRemainChongzhi());
                tf.setTuiChongzhiMoney(BigDecimal.valueOf(0));
                tf.setAfterTFchongzhiRemainMoney(stuInfo.getRemainChongzhi());

                tf.setTuifeiPayStyleID(Long.valueOf(payStyleID));
                tf.setLiushuiID(ls.getId());
                tf.setBeizhu(shuoming);
                tf.setAddStaffID(staffID);
                tf.setAddTime(new Date());
                tf.setQiyeID(qiyeID);
                iPxtuifeitableService.save(tf);

                //退费课程记录
                Pxtuifeikechengtable tbxkc = new Pxtuifeikechengtable();
                tbxkc.setTfBuxiID(Long.valueOf(bxkcID));
                tbxkc.setTfKechengID(buxikc.getKechengID());
                tbxkc.setKechengprice(buxikc.getKechengprice());
                tbxkc.setTfqianRemainkeshi(beforeks);
                tbxkc.setTfkeshi(ksnum);
                tbxkc.setTfhouRemainkeshi(buxikc.getRemainkeshi().subtract(ksnum));
                tbxkc.setBeizhu(shuoming);
                tbxkc.setTuifeiID(tf.getId());
                tbxkc.setQiyeID(qiyeID);
                iPxtuifeikechengtableService.save(tbxkc);

                ajaxJson.setMsg(stuinfoLog + xiugaiInfo);
            }
        }

        return ajaxJson;
    }

    @RequestMapping(value = "/Allxuefeioff", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class})
    @ApiOperation(value = "课程全部退费")
    public AjaxJson Allxuefeioff(HttpServletRequest request,
                                 @RequestBody AllxuefeioffForm form) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        String staffName = loginUser.getStaffName();//经办人
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");

        Pxstutable stu = iPxstutableService.getById(form.getStuID());
        Pxcampustable cam = iPxcampustableService.getById(stu.getCampusID());
        if (stu == null) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("未找到该学员信息");
            return ajaxJson;
        }

        Date tuiDate = new Date();
        try {
            tuiDate = sdf.parse(form.getProcessingTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String stuinfoLog = "学号：" + form.getStuID() + "姓名：" + stu.getStuName();
        String xiugaiInfo = "";
        String modifyValue = null;

        BigDecimal daijinquan = new BigDecimal(0);
        BigDecimal youhuijine = new BigDecimal(0);

        if (StringUtils.isNotBlank(form.getQiandanIDs())) {
            stuinfoLog += ",选择签单下所有课程退费!";
            xiugaiInfo =
                    ",【选择签单全课程退费】:录入的退费金额是" +form.getTuiallkcmoney().toString() + "，退费备注：" +form.getShuoming()+ ",经办人:" + staffName + "，退费时间：" + tuiDate.toString() +
                    "，退费前，学员剩余学费" + stu.getRemainXuefei().toString();
        } else {
            stuinfoLog += ",课程全部退费!";
            xiugaiInfo =
                    ",【全课程退费】:录入的退费金额是" +form.getTuiallkcmoney().toString() + "，退费备注：" + form.getShuoming() + ",经办人:" + staffName + "，退费时间：" + tuiDate.toString() +
            "，退费前，学员剩余学费" + stu.getRemainXuefei().toString();
        }
        Pxsysparamvaluetable sysvalue = iPxsysparamvaluetableService.getOne(new QueryWrapper<Pxsysparamvaluetable>()
                .eq("sysparamTypeID", 106L)
                .eq("qiyeID", qiyeID));
        modifyValue = sysvalue.getModifyValue();


        QueryWrapper<Pxbuxikechengtable> bkq = new QueryWrapper<>();
        bkq
                .eq("stuID",form.getStuID())
                .ge("zongjia", 0)
                .eq("qiyeID", qiyeID);
        if (StringUtils.isNotBlank(form.getQiandanIDs())) {
            bkq.in("qianDanInfoID",form.getQiandanIDs());
        }

        List<Pxbuxikechengtable> buxikc = iPxbuxikechengtableService.list(bkq);


        String alltqdIDs = "";
        if (buxikc.size() == 0) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("该学生没有可退课程!");
            return ajaxJson;
        } else {

        }
        Long tspID = null;
        if (modifyValue != null && modifyValue.equals("1")) {
            //审批流程需要的数据
            for (Pxbuxikechengtable item : buxikc) {
                alltqdIDs += item.getQianDanInfoID() + ",";
                Pxqiandaninfotable qd = iPxqiandaninfotableService.getById(item.getQianDanInfoID());
                if (qd.getYouhuiID() == null) {
                    youhuijine = youhuijine;
                } else {
                    Pxyouhuizhengcetable xzyouhui = iPxyouhuizhengcetableService.getById(qd.getYouhuiID());
                    if (xzyouhui != null) {
                        if (xzyouhui != null) {
                            youhuijine = youhuijine.add(xzyouhui.getYouhui());//全部可退费的签单的优惠金额
                        }
                    }
                }
                Pxdaijinquantable daijin = iPxdaijinquantableService.getOne(
                        new QueryWrapper<Pxdaijinquantable>()
                                .eq("stuID",form.getStuID())
                                .eq("qiandanID", item.getQianDanInfoID())
                                .eq("qiyeID", qiyeID)
                );
                if (daijin == null) {
                    daijinquan = daijinquan;
                } else {
                    daijinquan = daijinquan.add(daijin.getMoney());//全部可退费的签单的代金券金额
                }

            }
            List<Tuifeishenpi> tfsplist = iTuifeishenpiService.list(new QueryWrapper<Tuifeishenpi>().eq("stuID", form.getStuID()).eq("qiyeID", qiyeID).eq(
                    "spfinish",
                    1));
            if (tfsplist.size() > 0) {
                ajaxJson.setCode("N");
                ajaxJson.setCode("该学员已有一条未审批退费，请在审批之后再继续操作退费！");
                return ajaxJson;
            }

            //审批流程
            Tuifeishenpi tfSp = new Tuifeishenpi();
            if (StringUtils.isNotBlank(form.getQiandanIDs())) {
                tfSp.setTuiqiandanID(form.getQiandanIDs());
                tfSp.setSpqiandanID(form.getQiandanIDs());
            } else {
                tfSp.setTuiqiandanID(alltqdIDs);
                tfSp.setSpqiandanID(alltqdIDs);
            }
            tfSp.setCampusID(stu.getCampusID());
            tfSp.setStuID(Long.valueOf(form.getStuID()));
            tfSp.setType(1);
            tfSp.setYingtuiMoney(form.getTuiallkcmoney());
            tfSp.setSqtuiMoney(form.getTuiallkcmoney());
            tfSp.setYouhuiMoney(youhuijine);
            tfSp.setDaijinquanMoney(daijinquan);
            tfSp.setTuifeibanlidate(tuiDate);
            tfSp.setTuifeishuoming(form.getShuoming());
            tfSp.setSppayMoneystyle(Long.valueOf(form.getPayStyleID()));
            tfSp.setYejiren(Long.valueOf(form.getYejirenID()));
            tfSp.setSpfinish(1);
            tfSp.setAddTiem(new Date());
            tfSp.setAdduser(staffID);
            tfSp.setQiyeID(qiyeID);
            iTuifeishenpiService.save(tfSp);

            tspID = tfSp.getId();

            for (Pxbuxikechengtable item : buxikc) {
                //添加课程审批记录
                Tuikechenginfo kc = new Tuikechenginfo();
                kc.setStuID(Long.valueOf(form.getStuID()));
                kc.setTuibxkechengID(item.getId());
                kc.setRemainkeshi(item.getRemainkeshi());
                kc.setTuikeshi(item.getRemainkeshi());
                kc.setTuikechengPrice(item.getKechengprice());
                kc.setAdduser(staffID);
                kc.setAddTime(new Date());
                kc.setTuifeispID(tfSp.getId());
                kc.setTuimoney(item.getRemainkeshi().multiply(item.getKechengprice())); //剩余课时*课单价
                kc.setQiyeID(qiyeID);
                iTuikechenginfoService.save(kc);
            }
            xiugaiInfo += "，退费审批、全部课程退费审批，添加记录完毕，等待审批通过";
            ajaxJson.setMsg(stuinfoLog + xiugaiInfo);

        } else {

            //未开启审批流程  直接退费
            //清空课时 向签单信息表添加记录 同时向签单课程TABLE添加退课记录  最后剩余课时清零
            Pxqiandaninfotable tuiqd = new Pxqiandaninfotable();
            tuiqd.setStuID(Long.valueOf(form.getStuID()));
            tuiqd.setQiandandate(tuiDate);
            tuiqd.setShishouTotalMoney(form.getTuiallkcmoney().negate());
            tuiqd.setHetongMoney(form.getTuiallkcmoney().negate());
            tuiqd.setMoneyStyle(3);
            tuiqd.setBeizhu(form.getShuoming());
            tuiqd.setQianDanStaffID(Long.valueOf(form.getYejirenID()));
            tuiqd.setRecordInStaffID(staffID);
            tuiqd.setRecordInTime(new Date());
            tuiqd.setPayMoneyStyle(Long.valueOf(form.getPayStyleID()));
            tuiqd.setCampusID(stu.getCampusID());
            tuiqd.setIsdingjing(1);
            tuiqd.setQiyeID(qiyeID);
            iPxqiandaninfotableService.save(tuiqd);

            xiugaiInfo += ",签单信息表里添加退费记录";

            //流水记录中添加退费记录
            Pxliushuizhangtable ls = new Pxliushuizhangtable();
            ls.setLiushuiDateTime(tuiDate);
            ls.setCampusID(stu.getCampusID());
            ls.setLiushuiZaiyao("校区：" + cam.getCampusName() + stuinfoLog + ",部分课程费用退费");
            ls.setPayMoneyStyle(Long.valueOf(form.getPayStyleID()));
            ls.setShouruMoney(BigDecimal.valueOf(0));
            ls.setZhichuMoney(form.getTuiallkcmoney());
            ls.setShouzhiStyleID(6L);
            ls.setJinbanRen(Long.valueOf(form.getYejirenID()));
            ls.setStuID(Long.valueOf(form.getStuID()));
            ls.setQiandanID(tuiqd.getId());
            ls.setLuruTime(new Date());
            ls.setAddStaffID(staffID);
            ls.setQiyeID(qiyeID);
            iPxliushuizhangtableService.save(ls);

            xiugaiInfo += "，流水账中添加退费记录";

            //添加退费记录--做可逆操作
            Pxtuifeitable tf = new Pxtuifeitable();
            tf.setStuID(Long.valueOf(form.getStuID()));
            tf.setTuifeiType(1);
            tf.setQiandanID(tuiqd.getId());

            tf.setBeforeTuifeiRemainXuefei(stu.getRemainXuefei());
            tf.setShoudTuifeiTotalMoney(form.getTuiallkcmoney());
            tf.setShijiTuifeiTotalMoney(form.getTuiallkcmoney());
            tf.setAfterTuifeiRemainXuefei(stu.getRemainXuefei().subtract(form.getTuiallkcmoney()));

            tf.setBeforeTuifeiJifen(stu.getJifenNum());
            tf.setTuijifen(BigDecimal.valueOf(0));
            tf.setAfterTuifeiJifen(stu.getJifenNum());

            tf.setBeforeTFchongzhiRemainMoney(stu.getRemainChongzhi());
            tf.setTuiChongzhiMoney(BigDecimal.valueOf(0));
            tf.setAfterTFchongzhiRemainMoney(stu.getRemainChongzhi());

            tf.setTuifeiPayStyleID(Long.valueOf(form.getPayStyleID()));
            tf.setLiushuiID(ls.getId());
            tf.setBeizhu(form.getShuoming());
            tf.setAddStaffID(staffID);
            tf.setAddTime(new Date());
            tf.setQiyeID(qiyeID);
            iPxtuifeitableService.save(tf);

            for (Pxbuxikechengtable item : buxikc) {
                Pxkechengtable TuifeiKc = iPxkechengtableService.getById(item.getKechengID());
                Pxqiandansubjecttable qdkc = new Pxqiandansubjecttable();
                qdkc.setStuID(Long.valueOf(form.getStuID()));
                qdkc.setQiandandate(tuiDate);
                qdkc.setKechengID(item.getKechengID());
                qdkc.setKechengprice(item.getKechengprice());
                qdkc.setOriginalprice(item.getOriginalprice());
                qdkc.setBuykeshiNum(item.getRemainkeshi().negate());
                qdkc.setQianDanInfoID(tuiqd.getId());
                qdkc.setZongjia(item.getKechengprice().multiply(item.getKeshiNum()).negate());
                qdkc.setStartDate(item.getStartDate());
                qdkc.setEndDate(item.getEndDate());
                qdkc.setKechengStyle(4);
                qdkc.setDiscount(BigDecimal.valueOf(10));
                qdkc.setQiyeID(qiyeID);
                iPxqiandansubjecttableService.save(qdkc);

                xiugaiInfo += ",退费课程《" + TuifeiKc.getKechengName() + "》，计费方式" + TuifeiKc.getJifeiStyleID() + "，课程单价" + item.getKechengprice().toString() +
                "退费前剩余课时" + item.getRemainkeshi().toString();
                BigDecimal beforeks = item.getRemainkeshi();

                item.setRemainkeshi(BigDecimal.valueOf(0));   //把buxikechengTable里的学生剩余课时清零；
                xiugaiInfo += "，剩余课时被清零";
                xiugaiInfo += "，退费前的课程剩余费用" + item.getZongjia().toString();
                item.setZongjia(BigDecimal.valueOf(0));
                iPxbuxikechengtableService.updateById(item);


                //退费课程记录
                Pxtuifeikechengtable tbxkc = new Pxtuifeikechengtable();
                tbxkc.setTfBuxiID(item.getId());
                tbxkc.setTfKechengID(item.getKechengID());
                tbxkc.setKechengprice(item.getKechengprice());
                tbxkc.setTfqianRemainkeshi(beforeks);
                tbxkc.setTfkeshi(beforeks); //全退
                tbxkc.setTfhouRemainkeshi(BigDecimal.valueOf(0));
                tbxkc.setBeizhu(form.getShuoming());
                tbxkc.setTuifeiID(tf.getId());
                tbxkc.setQiyeID(qiyeID);
                iPxtuifeikechengtableService.save(tbxkc);

                Pxbuxistyletable bxSytle = iPxbuxistyletableService.getById(TuifeiKc.getBuxiStyleID());
                QueryWrapper<Pxstuclasstable> stuClQ = new QueryWrapper<>();
                stuClQ
                        .eq("buxiID", item.getId())
                        .eq("qiyeID", qiyeID);
                List<Pxstuclasstable> stuOneBuxikcClaALL = iPxstuclasstableService.list(stuClQ);
                if (stuOneBuxikcClaALL.size() > 0) {
                    for (Pxstuclasstable onestucla : stuOneBuxikcClaALL) {
                        Long classID = onestucla.getClassID();
                        //这门课程的班级总人数

                        List<Pxstuclasstable> classStu = iPxstuclasstableService.list(new QueryWrapper<Pxstuclasstable>()
                                .eq("classID", classID)
                                .eq("qiyeID", qiyeID)
                        );


                        //学生退班；
                        List<Pxpaiketable> claPaikeAll = iPxpaiketableService.list(new QueryWrapper<Pxpaiketable>()
                                .eq("classID", classID)
                                .eq("qiyeID", qiyeID));

                        if (claPaikeAll.size() > 0) {
                            for (Pxpaiketable oneClapk : claPaikeAll) {
                                //从选课表里删除之后再退班
                                List<Pxxuanketable> stuXuankeAll = iPxxuanketableService.list(new QueryWrapper<Pxxuanketable>()
                                        .eq("paikeID", oneClapk.getId())
                                        .eq("buxiID", item.getId())
                                        .eq("qiyeID", qiyeID)
                                );

                                if (stuXuankeAll.size() > 0) {
                                    iPxxuanketableService.remove(new QueryWrapper<Pxxuanketable>()
                                            .eq("paikeID", oneClapk.getId())
                                            .eq("buxiID", item.getId())
                                            .eq("qiyeID", qiyeID));
                                }
                            }
                        }
                        iPxstuclasstableService.removeById(onestucla);
                        if (classStu.size() > 0) {
                            //学生所在的班级里的学生人数大于1，即这个班里不只他一个学生，则退班；
                        } else {
                            //如果这个班级里只有他一个学生
                            Pxclasstable cla = iPxclasstableService.getById(classID);
                            if (cla != null) {
                                //如果这个班级有课耗，假删，隐藏
                                List<Pxkeshistutable> kehao = iPxkeshistutableService.list(new QueryWrapper<Pxkeshistutable>()
                                        .eq("classID", classID)
                                        .eq("qiyeID", qiyeID)
                                );

                                if (kehao.size() > 0) {
                                    cla.setIsdelete(true);
                                    cla.setClassState(1);
                                    cla.setIsShow(0);
                                    iPxclasstableService.updateById(cla);
                                } else {
                                    //没有课耗,不管是不是一对一，都把这个班删除掉；
                                    //删除班级之前，把这个班的排课全删除掉
                                    List<Pxpaiketable> claPaike = iPxpaiketableService.list(new QueryWrapper<Pxpaiketable>()
                                            .eq("classID", classID)
                                            .eq("qiyeID", qiyeID));

                                    if (claPaike.size() > 0) {
                                        for (Pxpaiketable onep : claPaike) {
                                            //从选课表里删除之后再退班
                                            List<Pxpaiketeachertable> paikeT = iPxpaiketeachertableService.list(new QueryWrapper<Pxpaiketeachertable>()
                                                    .eq("paikeID", onep.getId())
                                                    .eq("qiyeID", qiyeID)
                                            );

                                            if (paikeT.size() > 0) {
                                                iPxpaiketeachertableService.remove(
                                                        new QueryWrapper<Pxpaiketeachertable>()
                                                                .eq("paikeID", onep.getId())
                                                                .eq("qiyeID", qiyeID));
                                            }
                                        }
                                        iPxpaiketableService.remove(new QueryWrapper<Pxpaiketable>()
                                                .eq("classID", classID)
                                                .eq("qiyeID", qiyeID));
                                    }
                                    iPxclasstableService.removeById(cla);
                                }
                            }
                        }
                    }
                }
            }

            stu.setRemainXuefei(BigDecimal.valueOf(0));
            iPxstutableService.updateById(stu);
            xiugaiInfo += "，剩余学费被清零";

            ajaxJson.setMsg(stuinfoLog + xiugaiInfo);
        }

        return ajaxJson;
    }


    @RequestMapping(value = "/PartWuPingTuiFei", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class})
    @ApiOperation(value = "教学物品选择退费")
    public AjaxJson PartWuPingTuiFei(HttpServletRequest request,
                                     @RequestBody addFrom from
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");

        String qiandanids = from.getQiandanids(); //选中的签单ID
        List<addsupVo> addsupVos = JSON.parseArray(from.getXadata(), addsupVo.class);
        List<tuiMessageVo> tuiMessageVos = JSON.parseArray(from.getTuimessage(), tuiMessageVo.class);
        tuiMessageVo tuiMessage = tuiMessageVos.get(0);


        //退费信息
        String payStyleID = tuiMessage.getPayStyleID();
        String processingTime = tuiMessage.getProcessingTime();
        Date tuiDate = null;
        try {
            tuiDate = sdf.parse(processingTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String stuID = tuiMessage.getStuID();
        String yejirenID = tuiMessage.getYejirenID();
        String shuoming = tuiMessage.getShuoming();

        String modifyValue = ""; //判断是否开启退费审批

        String stuinfoLog = "";
        String xiugaiInfo = "";
        String checkqd = "";
        String allqd = "";

        Pxsysparamvaluetable sysvalue = iPxsysparamvaluetableService.getOne(new QueryWrapper<Pxsysparamvaluetable>()
                .eq("sysparamTypeID", 106L)
                .eq("qiyeID", qiyeID));
        modifyValue = sysvalue.getModifyValue();

        Pxstutable stuInfo = iPxstutableService.getById(stuID);
        Pxcampustable cam = iPxcampustableService.getById(stuInfo.getCampusID());
        String stuNo = stuInfo.getZidingyiStuID() == null ? stuID : stuInfo.getZidingyiStuID();
        stuinfoLog = "学号" + stuNo + "，姓名" + stuInfo.getStuName();

        Long tspID = null;
        if (modifyValue != null && modifyValue.equals("1")) {
            //开启退费审批 -->进入审批流程
            List<Tuifeishenpi> tfsplist = iTuifeishenpiService.list(new QueryWrapper<Tuifeishenpi>().eq("stuID", stuID).eq("qiyeID", qiyeID).eq("spfinish", 1));
            if (tfsplist.size() > 0) {
                ajaxJson.setCode("N");
                ajaxJson.setCode("该学员已有一条未审批退费，请在审批之后再继续操作退费！");
                return ajaxJson;
            }

            xiugaiInfo += "，【后台开启退费审批流程】";

            BigDecimal youhuijine = new BigDecimal(0);
            BigDecimal daijinquan = new BigDecimal(0);
            BigDecimal alltuimoney = new BigDecimal(0);


            for (addsupVo item : addsupVos) {
                alltuimoney = alltuimoney.add(item.getTuimoney());
                Pxqiandaninfo2table tuiqd = iPxqiandaninfo2tableService.getById(item.getId());
                checkqd += tuiqd.getQianInfoTabID() + ",";
            }

            if (StringUtils.isNotBlank(qiandanids)) {
                allqd = qiandanids;
            } else {
                allqd = checkqd;
            }

            //给选中的签单合计一下优惠金额和代金券金额
            String[] qdlist = allqd.split(",");
            for (String item : qdlist) {
                Pxqiandaninfotable xzqd = iPxqiandaninfotableService.getById(item);
                if (xzqd.getYouhuiID() == null) {
                    youhuijine = youhuijine;
                } else {
                    Pxyouhuizhengcetable xzyouhui = iPxyouhuizhengcetableService.getById(xzqd.getYouhuiID());
                    if (xzyouhui != null) {
                        youhuijine = youhuijine.add(xzyouhui.getYouhui());//全部可退费的签单的优惠金额
                    }
                }

                Pxdaijinquantable xzdaijin = iPxdaijinquantableService.getOne(
                        new QueryWrapper<Pxdaijinquantable>()
                                .eq("stuID", stuID)
                                .eq("qiandanID", item)
                                .eq("qiyeID", qiyeID)
                );
                if (xzdaijin == null) {
                    daijinquan = daijinquan;
                } else {
                    daijinquan = daijinquan.add(xzdaijin.getMoney());//全部可退费的签单的代金券金额
                }
            }


            //添加审批记录
            Tuifeishenpi tfSp = new Tuifeishenpi();
            tfSp.setTuiqiandanID(allqd);
            tfSp.setSpqiandanID(allqd);
            tfSp.setCampusID(stuInfo.getCampusID());
            tfSp.setStuID(Long.valueOf(stuID));
            tfSp.setType(5);
            tfSp.setYingtuiMoney(alltuimoney);
            tfSp.setSqtuiMoney(alltuimoney);
            tfSp.setYouhuiMoney(youhuijine);
            tfSp.setDaijinquanMoney(daijinquan);
            tfSp.setTuifeibanlidate(tuiDate);
            tfSp.setTuifeishuoming(shuoming);
            tfSp.setSppayMoneystyle(Long.valueOf(payStyleID));
            tfSp.setYejiren(Long.valueOf(yejirenID));
            tfSp.setSpfinish(1);
            tfSp.setAddTiem(new Date());
            tfSp.setAdduser(staffID);
            tfSp.setQiyeID(qiyeID);
            iTuifeishenpiService.save(tfSp);
            tspID = tfSp.getId();


            //添加完审批添加杂费审批
            for (addsupVo item : addsupVos) {
                BigDecimal tuimoney = item.getTuimoney();
                BigDecimal tuinum = item.getTuinum();

                Pxqiandansupplies qd = iPxqiandansuppliesService.getById(item.getId());
                Pxteachingsuppliestable qdsup = iPxteachingsuppliestableService.getById(qd.getTeachingSuppliesID());

                Tuisuppliseinfo spSp = new Tuisuppliseinfo();
                spSp.setQiadansuppliesTabID(qd.getId());
                spSp.setTuisuppliseID(Long.valueOf(item.getId()));
                spSp.setStuID(Long.valueOf(stuID));
                spSp.setTuiguige(qdsup.getSpecs());
                spSp.setAdduser(staffID);
                spSp.setAddTime(new Date());
                spSp.setTuifeispID(tfSp.getId());
                spSp.setTuimoney(tuimoney);
                spSp.setTuinum(tuinum);
                spSp.setQiyeID(qiyeID);
            }
            xiugaiInfo += "，退费审批、商品退费审批，添加记录完毕，等待审批通过";
            ajaxJson.setMsg(stuinfoLog + xiugaiInfo);
        } else {
            //未开审批 -直接添加数据
            for (addsupVo item : addsupVos) {
                String wpid = item.getId();//物品ID
                BigDecimal tuimoney = item.getTuimoney();
                BigDecimal tuinum = item.getTuinum();

                Pxqiandansupplies teachersupplies = iPxqiandansuppliesService.getById(wpid);
                if (teachersupplies == null) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    ajaxJson.setMsg("未找到签单教学物品信息，请重试！");
                    ajaxJson.setCode("N");
                    return ajaxJson;
                }

                xiugaiInfo = "，【教学物品部分退费】";

                if (tuimoney == null) {
                    tuimoney = BigDecimal.valueOf(0);
                }
                if (tuimoney.compareTo(teachersupplies.getSumMoney()) == 1) {
                    //退费金额>购买金额
                    tuimoney = teachersupplies.getSumMoney();
                }
                teachersupplies.setIsTuiFei(true);
                teachersupplies.setTuiMoney(tuimoney);
                iPxqiandansuppliesService.updateById(teachersupplies);

                Pxqiandaninfotable tuiqd = new Pxqiandaninfotable();
                tuiqd.setStuID(Long.valueOf(stuID));
                tuiqd.setQiandandate(tuiDate);
                tuiqd.setShishouTotalMoney(tuimoney.negate());
                tuiqd.setHetongMoney(tuimoney.negate());
                tuiqd.setMoneyStyle(3);
                tuiqd.setBeizhu(shuoming);
                tuiqd.setQianDanStaffID(Long.valueOf(yejirenID));
                tuiqd.setRecordInStaffID(staffID);
                tuiqd.setRecordInTime(new Date());
                tuiqd.setPayMoneyStyle(Long.valueOf(payStyleID));
                tuiqd.setCampusID(stuInfo.getCampusID());
                tuiqd.setIsdingjing(1);
                tuiqd.setQiyeID(qiyeID);
                iPxqiandaninfotableService.save(tuiqd);

                Pxteachingsuppliestable tsup = iPxteachingsuppliestableService.getById(teachersupplies.getTeachingSuppliesID());

                //在签单物品表中增加退费记录
                Pxqiandansupplies tuisp = new Pxqiandansupplies();
                tuisp.setTeachingSuppliesID(teachersupplies.getTeachingSuppliesID());
                tuisp.setName(tsup.getName());
                tuisp.setBuySum(tuinum.negate());
                tuisp.setQiandaninfoID(tuiqd.getId());
                tuisp.setBuyPrice(teachersupplies.getBuyPrice());
                tuisp.setStuID(Long.valueOf(stuID));
                tuisp.setSumMoney(tuimoney.negate());
                tuisp.setIsTuiFei(false);
                tuisp.setTuiMoney(tuimoney.negate());
                tuisp.setFafangstate(1);
                tuisp.setQiyeID(qiyeID);
                iPxqiandansuppliesService.save(tuisp);

                //教学用品减库存
                tsup.setStockNum(tsup.getStockNum().add(tuinum));
                iPxteachingsuppliestableService.updateById(tsup);

                //流水记录中添加退费记录
                Pxliushuizhangtable ls = new Pxliushuizhangtable();
                ls.setLiushuiDateTime(tuiDate);
                ls.setCampusID(stuInfo.getCampusID());
                ls.setLiushuiZaiyao("校区：" + cam.getCampusName() + stuinfoLog + ",(部分杂费)其他费用退费");
                ls.setPayMoneyStyle(Long.valueOf(payStyleID));
                ls.setShouruMoney(BigDecimal.valueOf(0));
                ls.setZhichuMoney(tuimoney);
                ls.setShouzhiStyleID(6L);
                ls.setJinbanRen(Long.valueOf(yejirenID));
                ls.setStuID(Long.valueOf(stuID));
                ls.setQiandanID(tuiqd.getId());
                ls.setLuruTime(new Date());
                ls.setAddStaffID(staffID);
                ls.setQiyeID(qiyeID);
                iPxliushuizhangtableService.save(ls);


                //添加退费记录--做可逆操作
                Pxtuifeitable tf = new Pxtuifeitable();
                tf.setStuID(Long.valueOf(stuID));
                tf.setTuifeiType(5);
                tf.setQiandanID(tuiqd.getId());

                tf.setBeforeTuifeiRemainXuefei(stuInfo.getRemainXuefei());
                tf.setShoudTuifeiTotalMoney(tuimoney); //使用系统计算的
                tf.setShijiTuifeiTotalMoney(tuimoney);
                tf.setAfterTuifeiRemainXuefei(stuInfo.getRemainXuefei().subtract(tuimoney));

                tf.setBeforeTuifeiJifen(stuInfo.getJifenNum());
                tf.setTuijifen(BigDecimal.valueOf(0));
                tf.setAfterTuifeiJifen(stuInfo.getJifenNum());

                tf.setBeforeTFchongzhiRemainMoney(stuInfo.getRemainChongzhi());
                tf.setTuiChongzhiMoney(BigDecimal.valueOf(0));
                tf.setAfterTFchongzhiRemainMoney(stuInfo.getRemainChongzhi());

                tf.setTuifeiPayStyleID(Long.valueOf(payStyleID));
                tf.setLiushuiID(ls.getId());
                tf.setBeizhu(shuoming);
                tf.setAddStaffID(staffID);
                tf.setAddTime(new Date());
                tf.setQiyeID(qiyeID);
                iPxtuifeitableService.save(tf);


                //添加商品退费记录-做可逆操作
                Pxtuifeiteachsuppliestable tuiteasp = new Pxtuifeiteachsuppliestable();
                tuiteasp.setQdsupID(Long.valueOf(item.getId()));
                tuiteasp.setTeachSuppliesID(teachersupplies.getTeachingSuppliesID());
                tuiteasp.setTeachSuppliesName(teachersupplies.getName());
                tuiteasp.setGuige(tsup.getSpecs());
                tuiteasp.setTfqianRemainNums(tsup.getStockNum());
                tuiteasp.setTuiNums(tuinum);
                tuiteasp.setTfhouRemainNums(tsup.getStockNum().add(tuinum));
                tuiteasp.setDanwei(tsup.getStockUnit());
                tuiteasp.setPrice(teachersupplies.getBuyPrice());
                tuiteasp.setBeizhu(tf.getBeizhu());
                tuiteasp.setTuifeiID(tf.getId());
                tuiteasp.setQiyeID(qiyeID);
                iPxtuifeiteachsuppliestableService.save(tuiteasp);


                xiugaiInfo += "，签单信息、流水账，添加记录完毕,部分商品退费成功";
                ajaxJson.setMsg(stuinfoLog + xiugaiInfo);

            }
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/allPartWuPingTuiFei", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class})
    @ApiOperation(value = "教学物品全部退费")
    public AjaxJson allPartWuPingTuiFei(HttpServletRequest request,
                                        @RequestBody allPartWuPingTuiFeiForm form) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Pxstutable stu = iPxstutableService.getById(form.getStuID());
        Pxcampustable cam = iPxcampustableService.getById(stu.getCampusID());
        String stuinfoLog = "";
        String xiugaiInfo = "";
        BigDecimal daijinquan = new BigDecimal(0);
        BigDecimal youhuijine = new BigDecimal(0);

        if (stu == null) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("未找到该学生信息");
            return ajaxJson;
        }

        stuinfoLog += "学员：" + stu.getStuName();

        Date tuiDate = null;
        try {
            tuiDate = sdf.parse(form.getProcessingTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String modifyValue = ""; //判断是否开启退费审批

        Pxsysparamvaluetable sysvalue = iPxsysparamvaluetableService.getOne(new QueryWrapper<Pxsysparamvaluetable>()
                .eq("sysparamTypeID", 106L)
                .eq("qiyeID", qiyeID));
        modifyValue = sysvalue.getModifyValue();


        QueryWrapper<Pxqiandansupplies> allqdsupQ = new QueryWrapper<>();
        allqdsupQ
                .eq("a.stuID",form.getStuID())
                .and(a -> a.eq("moneyStyle", 1).or(b -> b.eq("moneyStyle", 1)))
                .eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(form.getQiandanIDs())) {
            allqdsupQ.in("a.QiandaninfoID",form.getQiandanIDs());
        }
        List<Pxqiandansupplies> tuisplist = iPxqiandansuppliesService.tuisplist(allqdsupQ);

        String alltqdIDs = "";
        if (tuisplist.size() == 0) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("没有可以退费的商品项");
            return ajaxJson;
        } else {
            //审批流程需要的数据
            for (Pxqiandansupplies item : tuisplist) {
                alltqdIDs += item.getQiandaninfoID() + ",";
                Pxqiandaninfotable qd = iPxqiandaninfotableService.getById(item.getQiandaninfoID());
                if (qd.getYouhuiID() == null) {
                    youhuijine = youhuijine;
                } else {
                    Pxyouhuizhengcetable xzyouhui = iPxyouhuizhengcetableService.getById(qd.getYouhuiID());
                    if (xzyouhui != null) {
                        youhuijine = youhuijine.add(xzyouhui.getYouhui());//全部可退费的签单的优惠金额
                    }
                }
                Pxdaijinquantable daijin = iPxdaijinquantableService.getOne(
                        new QueryWrapper<Pxdaijinquantable>()
                                .eq("stuID",form.getStuID())
                                .eq("qiandanID", item.getQiandaninfoID())
                                .eq("qiyeID", qiyeID)
                );
                if (daijin == null) {
                    daijinquan = daijinquan;
                } else {
                    daijinquan = daijinquan.add(daijin.getMoney());//全部可退费的签单的代金券金额
                }
            }
        }

        Long tspID = null;
        if (modifyValue != null && modifyValue.equals("1")) {
            //开启退费审批 -->进入审批流程
            List<Tuifeishenpi> tfsplist = iTuifeishenpiService.list(new QueryWrapper<Tuifeishenpi>().eq("stuID",form.getStuID()).eq("qiyeID", qiyeID).eq(
                    "spfinish",
                    1));
            if (tfsplist.size() > 0) {
                ajaxJson.setCode("N");
                ajaxJson.setCode("该学员已有一条未审批退费，请在审批之后再继续操作退费！");
                return ajaxJson;
            }

            xiugaiInfo += "，【后台开启退费审批流程】";

            //添加审批记录
            Tuifeishenpi tfSp = new Tuifeishenpi();
            if (StringUtils.isNotBlank(form.getQiandanIDs())) {
                tfSp.setTuiqiandanID(form.getQiandanIDs());
                tfSp.setSpqiandanID(form.getQiandanIDs());
            } else {
                tfSp.setTuiqiandanID(alltqdIDs);
                tfSp.setSpqiandanID(alltqdIDs);
            }
            tfSp.setCampusID(stu.getCampusID());
            tfSp.setStuID(Long.valueOf(form.getStuID()));
            tfSp.setType(5);
            tfSp.setYingtuiMoney(form.getTuiallspmoney());
            tfSp.setSqtuiMoney(form.getTuiallspmoney());
            tfSp.setYouhuiMoney(youhuijine);
            tfSp.setDaijinquanMoney(daijinquan);
            tfSp.setTuifeibanlidate(tuiDate);
            tfSp.setTuifeishuoming(form.getShuoming());
            tfSp.setSppayMoneystyle(Long.valueOf(form.getPayStyleID()));
            tfSp.setYejiren(Long.valueOf(form.getYejirenID()));
            tfSp.setSpfinish(1);
            tfSp.setAddTiem(new Date());
            tfSp.setAdduser(staffID);
            tfSp.setQiyeID(qiyeID);
            iTuifeishenpiService.save(tfSp);
            tspID = tfSp.getId();

            for (Pxqiandansupplies item : tuisplist) {
                //添加商品审批记录
                String tuinum = iPxqiandansuppliesService.getBuysupplies(new QueryWrapper<Pxqiandansupplies>()
                        .eq("QiandaninfoID", item.getQiandaninfoID())
                        .eq("TeachingSuppliesID", item.getTeachingSuppliesID())
                        .eq("qiyeID", qiyeID)
                );
                BigDecimal chanum = new BigDecimal(tuinum); //剩余可退数量
                Pxteachingsuppliestable tsupplies = iPxteachingsuppliestableService.getById(item.getTeachingSuppliesID());
                Tuisuppliseinfo sp = new Tuisuppliseinfo();
                sp.setQiadansuppliesTabID(item.getId());
                sp.setTuisuppliseID(item.getTeachingSuppliesID());
                sp.setStuID(Long.valueOf(form.getStuID()));
                sp.setTuiguige(tsupplies.getSpecs());
                sp.setAdduser(staffID);
                sp.setAddTime(new Date());
                sp.setTuifeispID(tfSp.getId());
                sp.setTuimoney(item.getSumMoney());
                sp.setTuinum(chanum);
                sp.setQiyeID(qiyeID);
                iTuisuppliseinfoService.save(sp);
            }

            xiugaiInfo += "，退费审批、全部退费商品审批，添加记录完毕，等待审批通过";
            ajaxJson.setMsg(stuinfoLog + xiugaiInfo);
        } else {
            //未开审批 -直接添加数据

            Pxqiandaninfotable tuiqd = new Pxqiandaninfotable();
            tuiqd.setStuID(Long.valueOf(form.getStuID()));
            tuiqd.setQiandandate(tuiDate);
            tuiqd.setShishouTotalMoney(form.getTuiallspmoney().negate());
            tuiqd.setHetongMoney(form.getTuiallspmoney().negate());
            tuiqd.setMoneyStyle(3);
            tuiqd.setBeizhu(form.getShuoming());
            tuiqd.setQianDanStaffID(Long.valueOf(form.getYejirenID()));
            tuiqd.setRecordInStaffID(staffID);
            tuiqd.setRecordInTime(new Date());
            tuiqd.setPayMoneyStyle(Long.valueOf(form.getPayStyleID()));
            tuiqd.setCampusID(stu.getCampusID());
            tuiqd.setIsdingjing(1);
            tuiqd.setQiyeID(qiyeID);
            iPxqiandaninfotableService.save(tuiqd);

            Pxliushuizhangtable ls = new Pxliushuizhangtable();
            ls.setLiushuiDateTime(tuiDate);
            ls.setCampusID(stu.getCampusID());
            ls.setLiushuiZaiyao("校区:" + cam.getCampusName() + stuinfoLog + ",全部商品退费");
            ls.setPayMoneyStyle(Long.valueOf(form.getPayStyleID()));
            ls.setShouruMoney(BigDecimal.valueOf(0));
            ls.setZhichuMoney(form.getTuiallspmoney());
            ls.setShouzhiStyleID(6L);
            ls.setJinbanRen(Long.valueOf(form.getYejirenID()));
            ls.setStuID(Long.valueOf(form.getStuID()));
            ls.setQiandanID(tuiqd.getId());
            ls.setLuruTime(new Date());
            ls.setAddStaffID(staffID);
            ls.setQiyeID(qiyeID);
            iPxliushuizhangtableService.save(ls);

            BigDecimal l = BigDecimal.valueOf(0);

            //添加退费记录--做可逆操作
            Pxtuifeitable tf = new Pxtuifeitable();
            tf.setStuID(Long.valueOf(form.getStuID()));
            tf.setTuifeiType(5);
            tf.setQiandanID(tuiqd.getId());

            tf.setBeforeTuifeiRemainXuefei(stu.getRemainXuefei());
            tf.setShoudTuifeiTotalMoney(form.getTuiallspmoney());
            tf.setShijiTuifeiTotalMoney(form.getTuiallspmoney());
            tf.setAfterTuifeiRemainXuefei(stu.getRemainXuefei().subtract(form.getTuiallspmoney()));

            tf.setBeforeTuifeiJifen(stu.getJifenNum());
            tf.setTuijifen(BigDecimal.valueOf(0));
            tf.setAfterTuifeiJifen(stu.getJifenNum());

            tf.setBeforeTFchongzhiRemainMoney(stu.getRemainChongzhi());
            tf.setTuiChongzhiMoney(BigDecimal.valueOf(0));
            tf.setAfterTFchongzhiRemainMoney(BigDecimal.valueOf(0));

            tf.setTuifeiPayStyleID(Long.valueOf(form.getPayStyleID()));
            tf.setLiushuiID(ls.getId());
            tf.setBeizhu(form.getShuoming());
            tf.setAddStaffID(staffID);
            tf.setAddTime(new Date());
            tf.setQiyeID(qiyeID);
            iPxtuifeitableService.save(tf);


            QueryWrapper<Pxqiandansupplies> qdsupQ = new QueryWrapper<>();
            qdsupQ
                    .eq("stuID",form.getStuID())
                    .eq("qiyeID", qiyeID);
            if (StringUtils.isNotBlank(form.getQiandanIDs())) {
                qdsupQ.in("QiandaninfoID", form.getQiandanIDs());
            }
            List<Pxqiandansupplies> suplist = iPxqiandansuppliesService.list(qdsupQ);
            if (suplist.size() > 0) {
                for (Pxqiandansupplies item : suplist) {
                    if (item.getTuiMoney() == null) {
                        item.setTuiMoney(BigDecimal.valueOf(0));
                    }
                    BigDecimal chamoney = item.getSumMoney().subtract(item.getTuiMoney() == null ? BigDecimal.valueOf(0) : item.getTuiMoney());//剩余可退金额
                    String tuinum = iPxqiandansuppliesService.getBuysupplies(new QueryWrapper<Pxqiandansupplies>()
                            .eq("QiandaninfoID", item.getQiandaninfoID())
                            .eq("TeachingSuppliesID", item.getTeachingSuppliesID())
                            .eq("qiyeID", qiyeID)
                    );
                    BigDecimal chanum = new BigDecimal(tuinum); //剩余可退数量

                    item.setIsTuiFei(true);
                    if (item.getTuiMoney() == null) {
                        item.setTuiMoney(BigDecimal.valueOf(0));
                    }
                    item.setTuiMoney(item.getSumMoney());
                    iPxqiandansuppliesService.updateById(item);

                    //教学用品
                    Pxteachingsuppliestable tsup = iPxteachingsuppliestableService.getById(item.getTeachingSuppliesID());

                    //添加商品退费记录-做可逆操作
                    Pxtuifeiteachsuppliestable tuiteasp = new Pxtuifeiteachsuppliestable();
                    tuiteasp.setQdsupID(item.getId());
                    tuiteasp.setTeachSuppliesID(item.getTeachingSuppliesID());
                    tuiteasp.setTeachSuppliesName(item.getName());
                    tuiteasp.setGuige(tsup.getSpecs());
                    tuiteasp.setTfqianRemainNums(tsup.getStockNum());
                    tuiteasp.setTuiNums(chanum);
                    tuiteasp.setTfhouRemainNums(tsup.getStockNum().add(chanum));
                    tuiteasp.setDanwei(tsup.getStockUnit());
                    tuiteasp.setPrice(item.getBuyPrice());
                    tuiteasp.setBeizhu(tf.getBeizhu());
                    tuiteasp.setTuifeiID(tf.getId());
                    tuiteasp.setQiyeID(qiyeID);
                    iPxtuifeiteachsuppliestableService.save(tuiteasp);

                    //教学用品加库存
                    tsup.setStockNum(tsup.getStockNum().add(chanum));
                    iPxteachingsuppliestableService.updateById(tsup);

                    Pxqiandansupplies tuisp = new Pxqiandansupplies();
                    tuisp.setTeachingSuppliesID(item.getTeachingSuppliesID());
                    tuisp.setName(item.getName());
                    tuisp.setBuySum(chanum.negate());
                    tuisp.setQiandaninfoID(tuiqd.getId());
                    tuisp.setBuyPrice(item.getBuyPrice());
                    tuisp.setStuID(Long.valueOf(form.getStuID()));
                    tuisp.setSumMoney(chamoney.negate());
                    tuisp.setIsTuiFei(false);
                    tuisp.setTuiMoney(chamoney.negate());
                    tuisp.setFafangstate(1);
                    tuisp.setQiyeID(qiyeID);
                    iPxqiandansuppliesService.save(tuisp);
                }
            }
            xiugaiInfo += "，签单信息、流水账，添加记录完毕,退费成功";
            ajaxJson.setMsg(stuinfoLog + xiugaiInfo);
        }


        return ajaxJson;
    }

    /**
     * @Description: PartOtherTuiFei()方法作用:(杂费)其他费用部分退费
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/4/12 9:29
     */
    @RequestMapping(value = "/PartOtherTuiFei", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class})
    @ApiOperation(value = "选择杂费退费")
    public AjaxJson PartOtherTuiFei(HttpServletRequest request,
                                    @RequestBody addFrom from
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");

        String qiandanids = from.getQiandanids(); //选中的签单ID
        List<addZfVo> addZfVos = JSON.parseArray(from.getXadata(), addZfVo.class);
        List<tuiMessageVo> tuiMessageVos = JSON.parseArray(from.getTuimessage(), tuiMessageVo.class);
        tuiMessageVo tuiMessage = tuiMessageVos.get(0);

        //退费信息
        String payStyleID = tuiMessage.getPayStyleID();
        String processingTime = tuiMessage.getProcessingTime();
        Date tuiDate = null;
        try {
            tuiDate = sdf.parse(processingTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String stuID = tuiMessage.getStuID();
        String yejirenID = tuiMessage.getYejirenID();
        String shuoming = tuiMessage.getShuoming();

        String modifyValue = ""; //判断是否开启退费审批

        Pxsysparamvaluetable sysvalue = iPxsysparamvaluetableService.getOne(new QueryWrapper<Pxsysparamvaluetable>()
                .eq("sysparamTypeID", 106L)
                .eq("qiyeID", qiyeID));
        modifyValue = sysvalue.getModifyValue();

        String stuinfoLog = "";
        String xiugaiInfo = "";
        String checkqd = "";
        String allqd = "";

        Pxstutable stuInfo = iPxstutableService.getById(stuID);
        Pxcampustable cam = iPxcampustableService.getById(stuInfo.getCampusID());
        String stuNo = stuInfo.getZidingyiStuID() == null ? stuID : stuInfo.getZidingyiStuID();
        stuinfoLog += "学号:" + stuNo + ",姓名" + stuInfo.getStuName();

        Long tspID = null;
        if (modifyValue != null && modifyValue.equals("1")) {
            //开启退费审批 -->进入审批流程
            List<Tuifeishenpi> tfsplist = iTuifeishenpiService.list(new QueryWrapper<Tuifeishenpi>().eq("stuID", stuID).eq("qiyeID", qiyeID).eq("spfinish", 1));
            if (tfsplist.size() > 0) {
                ajaxJson.setCode("N");
                ajaxJson.setCode("该学员已有一条未审批退费，请在审批之后再继续操作退费！");
                return ajaxJson;
            }

            xiugaiInfo += "，【后台开启退费审批流程】";

            BigDecimal youhuijine = new BigDecimal(0);
            BigDecimal daijinquan = new BigDecimal(0);
            BigDecimal alltuimoney = new BigDecimal(0);

            for (addZfVo item : addZfVos) {
                alltuimoney = alltuimoney.add(item.getTuimoney());
                Pxqiandaninfo2table tuiqd = iPxqiandaninfo2tableService.getById(item.getId());
                checkqd += tuiqd.getQianInfoTabID() + ",";
            }


            if (StringUtils.isNotBlank(qiandanids)) {
                allqd = qiandanids;
            } else {
                allqd = checkqd;
            }

            //给选中的签单合计一下优惠金额和代金券金额
            String[] qdlist = allqd.split(",");
            for (String item : qdlist) {
                Pxqiandaninfotable xzqd = iPxqiandaninfotableService.getById(item);
                if (xzqd.getYouhuiID() == null) {
                    youhuijine = youhuijine;
                } else {
                    Pxyouhuizhengcetable xzyouhui = iPxyouhuizhengcetableService.getById(xzqd.getYouhuiID());
                    if (xzyouhui != null) {
                        youhuijine = youhuijine.add(xzyouhui.getYouhui());//全部可退费的签单的优惠金额
                    }
                }

                Pxdaijinquantable xzdaijin = iPxdaijinquantableService.getOne(
                        new QueryWrapper<Pxdaijinquantable>()
                                .eq("stuID", stuID)
                                .eq("qiandanID", item)
                                .eq("qiyeID", qiyeID)
                );
                if (xzdaijin == null) {
                    daijinquan = daijinquan;
                } else {
                    daijinquan = daijinquan.add(xzdaijin.getMoney());//全部可退费的签单的代金券金额
                }
            }


            //添加审批记录
            Tuifeishenpi tfSp = new Tuifeishenpi();

            tfSp.setTuiqiandanID(allqd);
            tfSp.setSpqiandanID(allqd);
            tfSp.setCampusID(stuInfo.getCampusID());
            tfSp.setStuID(Long.valueOf(stuID));
            tfSp.setType(4);
            tfSp.setYingtuiMoney(alltuimoney);
            tfSp.setSqtuiMoney(alltuimoney);
            tfSp.setYouhuiMoney(youhuijine);
            tfSp.setDaijinquanMoney(daijinquan);
            tfSp.setTuifeibanlidate(tuiDate);
            tfSp.setTuifeishuoming(shuoming);
            tfSp.setSppayMoneystyle(Long.valueOf(payStyleID));
            tfSp.setYejiren(Long.valueOf(yejirenID));
            tfSp.setSpfinish(1);
            tfSp.setAddTiem(new Date());
            tfSp.setAdduser(staffID);
            tfSp.setQiyeID(qiyeID);
            iTuifeishenpiService.save(tfSp);
            tspID = tfSp.getId();

            //添加完审批添加杂费审批
            for (addZfVo item : addZfVos) {
                BigDecimal tuimoney = item.getTuimoney();
                Pxqiandaninfo2table tuiqdzf = iPxqiandaninfo2tableService.getById(item.getId());

                Tuizafeiinfo zf = new Tuizafeiinfo();
                zf.setStuID(Long.valueOf(stuID));
                zf.setQiandanInfo2ID(tuiqdzf.getId());
                zf.setQiandanOtherID(tuiqdzf.getQianDanOtherMoneyID());
                zf.setTuizfmoney(tuimoney);
                zf.setAdduser(staffID);
                zf.setAddTime(new Date());
                zf.setTuifeispID(tfSp.getId());
                zf.setQiyeID(qiyeID);
                iTuizafeiinfoService.save(zf);
            }
            xiugaiInfo += "，退费审批、退费杂费审批，添加记录完毕，等待审批通过";
            ajaxJson.setMsg(stuinfoLog + xiugaiInfo);

        } else {
            //未开审批 -直接添加数据
            for (addZfVo item : addZfVos) {
                String otmoneyID = item.getId();
                BigDecimal tuimoney = item.getTuimoney();


                xiugaiInfo += "，【其他费用部分退款】";

                Pxqiandaninfo2table Other = iPxqiandaninfo2tableService.getById(otmoneyID);
                if (Other == null) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    ajaxJson.setCode("N");
                    ajaxJson.setMsg("未找到签单费用信息，请重试！");
                    return ajaxJson;
                }


                Pxqiandaninfotable tuiqd = new Pxqiandaninfotable();
                tuiqd.setStuID(Long.valueOf(stuID));
                tuiqd.setQiandandate(tuiDate);
                tuiqd.setShishouTotalMoney(tuimoney.negate());
                tuiqd.setHetongMoney(tuimoney.negate());
                tuiqd.setMoneyStyle(3);
                tuiqd.setBeizhu(shuoming);
                tuiqd.setQianDanStaffID(Long.valueOf(yejirenID));
                tuiqd.setRecordInStaffID(staffID);
                tuiqd.setRecordInTime(new Date());
                tuiqd.setPayMoneyStyle(Long.valueOf(payStyleID));
                tuiqd.setCampusID(stuInfo.getCampusID());
                tuiqd.setIsdingjing(1);
                tuiqd.setQiyeID(qiyeID);
                iPxqiandaninfotableService.save(tuiqd);

                Pxliushuizhangtable ls = new Pxliushuizhangtable();
                ls.setLiushuiDateTime(tuiDate);
                ls.setCampusID(stuInfo.getCampusID());
                ls.setLiushuiZaiyao("校区：" + cam.getCampusName() + stuinfoLog + ",(部分杂费)其他费用退费");
                ls.setPayMoneyStyle(Long.valueOf(payStyleID));
                ls.setShouruMoney(BigDecimal.valueOf(0));
                ls.setZhichuMoney(tuimoney);
                ls.setShouzhiStyleID(6L);
                ls.setJinbanRen(Long.valueOf(yejirenID));
                ls.setStuID(Long.valueOf(stuID));
                ls.setQiandanID(tuiqd.getId());
                ls.setLuruTime(new Date());
                ls.setAddStaffID(staffID);
                ls.setQiyeID(qiyeID);
                iPxliushuizhangtableService.save(ls);

                //添加退费记录--做可逆操作
                Pxtuifeitable tf = new Pxtuifeitable();
                tf.setStuID(Long.valueOf(stuID));
                tf.setTuifeiType(4);
                tf.setQiandanID(tuiqd.getId());

                tf.setBeforeTuifeiRemainXuefei(stuInfo.getRemainXuefei());
                tf.setShoudTuifeiTotalMoney(tuimoney); //使用系统计算的
                tf.setShijiTuifeiTotalMoney(tuimoney);
                tf.setAfterTuifeiRemainXuefei(stuInfo.getRemainXuefei().subtract(tuimoney));

                tf.setBeforeTuifeiJifen(stuInfo.getJifenNum());
                tf.setTuijifen(BigDecimal.valueOf(0));
                tf.setAfterTuifeiJifen(stuInfo.getJifenNum());

                tf.setBeforeTFchongzhiRemainMoney(stuInfo.getRemainChongzhi());
                tf.setTuiChongzhiMoney(BigDecimal.valueOf(0));
                tf.setAfterTFchongzhiRemainMoney(stuInfo.getRemainChongzhi());

                tf.setTuifeiPayStyleID(Long.valueOf(payStyleID));
                tf.setLiushuiID(ls.getId());
                tf.setBeizhu(shuoming);
                tf.setAddStaffID(staffID);
                tf.setAddTime(new Date());
                tf.setQiyeID(qiyeID);
                iPxtuifeitableService.save(tf);

                //杂费退费可逆
                Tuiqiandaninfo2 tzf = new Tuiqiandaninfo2();
                tzf.setBeforetuiMoney(Other.getTuiMoney());
                tzf.setAftertuiMoney(BigDecimal.valueOf(0));
                tzf.setTuifeiID(tf.getId());
                tzf.setTuiqianInfo2ID(Other.getId());
                tzf.setQiyeID(qiyeID);
                iTuiqiandaninfo2Service.save(tzf);

                if (tuimoney == null) {
                    tuimoney = BigDecimal.valueOf(0);
                }
                if (tuimoney.compareTo(Other.getZongMoney()) == 1) {
                    //退费金额>购买金额
                    tuimoney = Other.getZongMoney();
                }

                Other.setTuiMoney(tuimoney);
                iPxqiandaninfo2tableService.updateById(Other);

                tzf.setAftertuiMoney(tuimoney); //退后金额
                iTuiqiandaninfo2Service.updateById(tzf);

                xiugaiInfo += "，签单信息、流水账，添加记录完毕,退费成功";
                ajaxJson.setMsg(stuinfoLog + xiugaiInfo);
            }
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/allOtherTuiFei", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class})
    @ApiOperation(value = "学杂费全部退费")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true),
            @ApiImplicitParam(name = "payStyleID", value = "支付方式", required = true),
            @ApiImplicitParam(name = "yejirenID", value = "退费业绩人", required = true),
            @ApiImplicitParam(name = "processingTime", value = "退费时间", required = true),
            @ApiImplicitParam(name = "tuiallzfmoney", value = "退费金额", required = true),
            @ApiImplicitParam(name = "shuoming", value = "备注", required = true),
            @ApiImplicitParam(name = "qiandanIDs", value = "签单IDs", required = false)
    })
    public AjaxJson allOtherTuiFei(HttpServletRequest request,
                                   @RequestBody allOtherTuiFeiForm form) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Pxstutable stu = iPxstutableService.getById(form.getStuID());
        Pxcampustable cam = iPxcampustableService.getById(stu.getCampusID());
        String stuinfoLog = "";
        String xiugaiInfo = "";
        BigDecimal daijinquan = new BigDecimal(0);
        BigDecimal youhuijine = new BigDecimal(0);


        if (stu == null) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("未找到该学生信息");
            return ajaxJson;
        }
        stuinfoLog += "学员：" + stu.getStuName();

        Date tuiDate = null;
        try {
            tuiDate = sdf.parse(form.getProcessingTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String modifyValue = ""; //判断是否开启退费审批

        Pxsysparamvaluetable sysvalue = iPxsysparamvaluetableService.getOne(new QueryWrapper<Pxsysparamvaluetable>()
                .eq("sysparamTypeID", 106L)
                .eq("qiyeID", qiyeID));

        modifyValue = sysvalue.getModifyValue();


        //查看是否有可以退费的签单
        QueryWrapper<alltuizfVo> alltuiQ = new QueryWrapper<>();
        alltuiQ
                .ge("a.zongMoney", "a.tuiMoney")
                .eq("b.stuID", form.getStuID())
                .eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(form.getQiandanIDs())) {
            alltuiQ.in("a.qianInfoTabID",form.getQiandanIDs());
        }
        List<alltuizfVo> getalltuizf = iPxtuifeitableService.getalltuizf(alltuiQ);

        String alltqdIDs = "";
        if (getalltuizf.size() == 0) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("没有可以退费的杂费项");
            return ajaxJson;
        } else {
            //审批流程需要的数据
            for (alltuizfVo item : getalltuizf) {
                alltqdIDs += item.getQianInfoTabID() + ",";
                Pxqiandaninfotable qd = iPxqiandaninfotableService.getById(item.getQianInfoTabID());
                if (qd.getYouhuiID() == null) {
                    youhuijine = youhuijine;
                } else {
                    Pxyouhuizhengcetable xzyouhui = iPxyouhuizhengcetableService.getById(qd.getYouhuiID());
                    if (xzyouhui != null) {
                        youhuijine = youhuijine.add(xzyouhui.getYouhui());//全部可退费的签单的优惠金额
                    }
                }
                Pxdaijinquantable daijin = iPxdaijinquantableService.getOne(
                        new QueryWrapper<Pxdaijinquantable>()
                                .eq("stuID",form.getStuID())
                                .eq("qiandanID", item.getQianInfoTabID())
                                .eq("qiyeID", qiyeID)
                );
                if (daijin == null) {
                    daijinquan = daijinquan;
                } else {
                    daijinquan = daijinquan.add(daijin.getMoney());//全部可退费的签单的代金券金额
                }
            }
        }

        Long tspID = null;
        if (modifyValue != null && modifyValue.equals("1")) {
            //开启退费审批 -->进入审批流程
            List<Tuifeishenpi> tfsplist = iTuifeishenpiService.list(new QueryWrapper<Tuifeishenpi>().eq("stuID",form.getStuID()).eq("qiyeID", qiyeID).eq(
                    "spfinish",
                    1));
            if (tfsplist.size() > 0) {
                ajaxJson.setCode("N");
                ajaxJson.setCode("该学员已有一条未审批退费，请在审批之后再继续操作退费！");
                return ajaxJson;
            }
            xiugaiInfo += "，【后台开启退费审批流程】";

            //添加审批记录
            Tuifeishenpi tfSp = new Tuifeishenpi();
            if (StringUtils.isNotBlank(form.getQiandanIDs())) {
                tfSp.setTuiqiandanID(form.getQiandanIDs());
                tfSp.setSpqiandanID(form.getQiandanIDs());
            } else {
                tfSp.setTuiqiandanID(alltqdIDs);
                tfSp.setSpqiandanID(alltqdIDs);
            }
            tfSp.setCampusID(stu.getCampusID());
            tfSp.setStuID(Long.valueOf(form.getStuID()));
            tfSp.setType(4);
            tfSp.setYingtuiMoney(form.getTuiallzfmoney());
            tfSp.setSqtuiMoney(form.getTuiallzfmoney());
            tfSp.setYouhuiMoney(youhuijine);
            tfSp.setDaijinquanMoney(daijinquan);
            tfSp.setTuifeibanlidate(tuiDate);
            tfSp.setTuifeishuoming(form.getShuoming());
            tfSp.setSppayMoneystyle(Long.valueOf(form.getPayStyleID()));
            tfSp.setYejiren(Long.valueOf(form.getYejirenID()));
            tfSp.setSpfinish(1);
            tfSp.setAddTiem(new Date());
            tfSp.setAdduser(staffID);
            tfSp.setQiyeID(qiyeID);
            iTuifeishenpiService.save(tfSp);
            tspID = tfSp.getId();

            //添加进退杂费详情表
            QueryWrapper<Pxqiandaninfo2table> zfQ = new QueryWrapper<>();
            zfQ
                    .eq("b.stuID",form.getStuID())
                    .eq("a.qiyeID", qiyeID);
            if (StringUtils.isNotBlank(form.getQiandanIDs())) {
                zfQ.in("a.qianInfoTabID",form.getQiandanIDs());
            }

            List<Pxqiandaninfo2table> tuiallzf = iPxqiandaninfo2tableService.tuiallzf(zfQ);
            if (tuiallzf.size() > 0) {
                for (Pxqiandaninfo2table item : tuiallzf) {
                    //添加完审批添加杂费审批
                    Tuizafeiinfo zf = new Tuizafeiinfo();
                    zf.setStuID(Long.valueOf(form.getStuID()));
                    zf.setQiandanInfo2ID(item.getId());
                    zf.setQiandanOtherID(item.getQianDanOtherMoneyID());
                    zf.setTuizfmoney(item.getZongMoney());
                    zf.setAdduser(staffID);
                    zf.setAddTime(new Date());
                    zf.setTuifeispID(tfSp.getId());
                    zf.setQiyeID(qiyeID);
                    iTuizafeiinfoService.save(zf);
                }
            }

            xiugaiInfo += "，退费审批、全部退费杂费审批，添加记录完毕，等待审批通过";
            ajaxJson.setMsg(stuinfoLog + xiugaiInfo);
        } else {
            //未开审批 -直接添加数据
            Pxqiandaninfotable tuiqd = new Pxqiandaninfotable();
            tuiqd.setStuID(Long.valueOf(form.getStuID()));
            tuiqd.setQiandandate(tuiDate);
            tuiqd.setShishouTotalMoney(form.getTuiallzfmoney().negate());
            tuiqd.setHetongMoney(form.getTuiallzfmoney().negate());
            tuiqd.setMoneyStyle(3);
            tuiqd.setBeizhu(form.getShuoming());
            tuiqd.setQianDanStaffID(Long.valueOf(form.getYejirenID()));
            tuiqd.setRecordInStaffID(staffID);
            tuiqd.setRecordInTime(new Date());
            tuiqd.setPayMoneyStyle(Long.valueOf(form.getPayStyleID()));
            tuiqd.setCampusID(stu.getCampusID());
            tuiqd.setIsdingjing(1);
            tuiqd.setQiyeID(qiyeID);
            iPxqiandaninfotableService.save(tuiqd);

            Pxliushuizhangtable ls = new Pxliushuizhangtable();
            ls.setLiushuiDateTime(tuiDate);
            ls.setCampusID(stu.getCampusID());
            ls.setLiushuiZaiyao("校区：" + cam.getCampusName() + stuinfoLog + ",全部退费");
            ls.setPayMoneyStyle(Long.valueOf(form.getPayStyleID()));
            ls.setShouruMoney(BigDecimal.valueOf(0));
            ls.setZhichuMoney(form.getTuiallzfmoney());
            ls.setShouzhiStyleID(6L);
            ls.setJinbanRen(Long.valueOf(form.getYejirenID()));
            ls.setStuID(Long.valueOf(form.getStuID()));
            ls.setQiandanID(tuiqd.getId());
            ls.setAddStaffID(staffID);
            ls.setLuruTime(new Date());
            ls.setQiyeID(qiyeID);
            iPxliushuizhangtableService.save(ls);

            //添加退费记录
            Pxtuifeitable tf = new Pxtuifeitable();
            tf.setStuID(Long.valueOf(form.getStuID()));
            tf.setTuifeiType(4);
            tf.setQiandanID(tuiqd.getId());

            tf.setBeforeTuifeiRemainXuefei(stu.getRemainXuefei());
            tf.setShoudTuifeiTotalMoney(form.getTuiallzfmoney());
            tf.setShijiTuifeiTotalMoney(form.getTuiallzfmoney());
            tf.setAfterTuifeiRemainXuefei(stu.getRemainXuefei().subtract(form.getTuiallzfmoney()));

            tf.setBeforeTuifeiJifen(stu.getJifenNum());
            tf.setTuijifen(BigDecimal.valueOf(0));
            tf.setAfterTuifeiJifen(stu.getJifenNum());

            tf.setAfterTFchongzhiRemainMoney(stu.getRemainChongzhi());
            tf.setBeforeTFchongzhiRemainMoney(stu.getRemainChongzhi());
            tf.setTuiChongzhiMoney(BigDecimal.valueOf(0));

            tf.setTuifeiPayStyleID(Long.valueOf(form.getPayStyleID()));
            tf.setLiushuiID(ls.getId());
            tf.setBeizhu(form.getShuoming());
            tf.setAddStaffID(staffID);
            tf.setAddTime(new Date());
            tf.setQiyeID(qiyeID);
            iPxtuifeitableService.save(tf);


            //开始退费
            QueryWrapper<Pxqiandaninfo2table> zfQ = new QueryWrapper<>();
            zfQ
                    .eq("b.stuID",form.getStuID())
                    .eq("a.qiyeID", qiyeID);
            if (StringUtils.isNotBlank(form.getQiandanIDs())) {
                zfQ.in("a.qianInfoTabID",form.getQiandanIDs());
            }
            List<Pxqiandaninfo2table> tuiallzf = iPxqiandaninfo2tableService.tuiallzf(zfQ);
            if (tuiallzf.size() > 0) {
                for (Pxqiandaninfo2table item : tuiallzf) {
                    if (item.getTuiMoney() == null) {
                        item.setTuiMoney(BigDecimal.valueOf(0));
                    }
                    BigDecimal beforetuiMoney = item.getTuiMoney();
                    item.setTuiMoney(item.getZongMoney());
                    iPxqiandaninfo2tableService.updateById(item);

                    //杂费退费可逆
                    Tuiqiandaninfo2 tzf = new Tuiqiandaninfo2();
                    tzf.setBeforetuiMoney(beforetuiMoney);
                    tzf.setAftertuiMoney(item.getZongMoney());
                    tzf.setTuifeiID(tf.getId());
                    tzf.setTuiqianInfo2ID(item.getId());
                    tzf.setQiyeID(qiyeID);
                    iTuiqiandaninfo2Service.save(tzf);

                }
            }
            xiugaiInfo += "，签单信息、流水账，添加记录完毕,退费成功";
            ajaxJson.setMsg(stuinfoLog + xiugaiInfo);
        }


        return ajaxJson;
    }


    @RequestMapping(value = "/Alloff", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class})
    @ApiOperation(value = "全部退费")
    public AjaxJson Alloff(HttpServletRequest request, @RequestBody AlloffForm form
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");

        //操作全部退费  设置学员状态未退费  学员档案不显示学员退费的
        Pxstutable stu = iPxstutableService.getById(form.getStuID());
        Pxcampustable cam = iPxcampustableService.getById(stu.getCampusID());
        String stuinfoLog = "";
        String xiugaiInfo = "";

        if (stu == null) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("未找到该学生信息");
            return ajaxJson;
        }
        if (stu.getBuxiStateID() == 5) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("请不要重复操作");
            return ajaxJson;
        }
        String stuNo = stu.getZidingyiStuID() == null ? form.getStuID() : stu.getZidingyiStuID();
        stuinfoLog = "学号" + stuNo + "，姓名" + stu.getStuName();
        Pxstafftable yejiren = iPxstafftableService.getById(form.getYejirenID());
        Date tuiDate=new Date();
        try {
            tuiDate = sdf.parse(form.getProcessingTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        xiugaiInfo =
         ",【学员全部退费（课程，教学用品，学杂）】:录入的退费金额是" +form.getTuiallmoney().toString() + "，退费备注：" +form.getShuoming()+ ",业绩人:" + yejiren.getStaffName() + "，退费时间：" + tuiDate.toString() + "，退费前，学员剩余学费" + stu.getRemainXuefei().toString();


        BigDecimal daijinquan = new BigDecimal(0);
        BigDecimal youhuijine = new BigDecimal(0);
        String modifyValue = ""; //判断是否开启退费审批

        Pxsysparamvaluetable sysvalue = iPxsysparamvaluetableService.getOne(new QueryWrapper<Pxsysparamvaluetable>()
                .eq("sysparamTypeID", 106L)
                .eq("qiyeID", qiyeID));
        modifyValue = sysvalue.getModifyValue();

        Long tspID = null;
        if (modifyValue != null && modifyValue.equals("1")) {
            List<Tuifeishenpi> tfsplist = iTuifeishenpiService.list(new QueryWrapper<Tuifeishenpi>().eq("stuID",form.getStuID()).eq("qiyeID", qiyeID).eq(
                    "spfinish",
                    1));
            if (tfsplist.size() > 0) {
                ajaxJson.setCode("N");
                ajaxJson.setCode("该学员已有一条未审批退费，请在审批之后再继续操作退费！");
                return ajaxJson;
            }

            xiugaiInfo += "，【后台开启退费审批流程】";

            List<Pxqiandaninfotable> qdlist = iPxqiandaninfotableService.list(
                    new QueryWrapper<Pxqiandaninfotable>()
                            .eq("stuID",form.getStuID())
                            .and(a -> a.eq("moneyStyle", 1).or(b -> b.eq("moneyStyle", 1)))
                            .eq("qiyeID", qiyeID)
            );
            String alltqdIDs = "";
            if (qdlist.size() > 0) {
                for (Pxqiandaninfotable item : qdlist) {
                    alltqdIDs += item.getId() + ",";
                    if (item.getYouhuiID() == null) {
                        youhuijine = youhuijine;
                    } else {
                        Pxyouhuizhengcetable xzyouhui = iPxyouhuizhengcetableService.getById(item.getYouhuiID());
                        if (xzyouhui != null) {
                            youhuijine = youhuijine.add(xzyouhui.getYouhui());//全部可退费的签单的优惠金额
                        }
                    }

                    Pxdaijinquantable daijin = iPxdaijinquantableService.getOne(
                            new QueryWrapper<Pxdaijinquantable>()
                                    .eq("stuID",form.getStuID())
                                    .eq("qiandanID", item.getId())
                                    .eq("qiyeID", qiyeID)
                    );
                    if (daijin == null) {
                        daijinquan = daijinquan;
                    } else {
                        daijinquan = daijinquan.add(daijin.getMoney());//全部可退费的签单的代金券金额
                    }
                }
            }

            //添加审批记录
            Tuifeishenpi tfSp = new Tuifeishenpi();
            if (StringUtils.isNotBlank(alltqdIDs)) {
                tfSp.setTuiqiandanID(alltqdIDs);
                tfSp.setSpqiandanID(alltqdIDs);
            } else {
                tfSp.setTuiqiandanID(alltqdIDs);
                tfSp.setSpqiandanID(alltqdIDs);
            }
            tfSp.setCampusID(stu.getCampusID());
            tfSp.setStuID(Long.valueOf(form.getStuID()));
            tfSp.setType(7);
            tfSp.setYingtuiMoney(form.getTuiallmoney());
            tfSp.setSqtuiMoney(form.getTuiallmoney());
            tfSp.setYouhuiMoney(youhuijine);
            tfSp.setDaijinquanMoney(daijinquan);
            tfSp.setTuifeibanlidate(tuiDate);
            tfSp.setTuifeishuoming(form.getShuoming());
            tfSp.setSppayMoneystyle(Long.valueOf(form.getPayStyleID()));
            tfSp.setYejiren(Long.valueOf(form.getYejirenID()));
            tfSp.setSpfinish(1);
            tfSp.setAddTiem(new Date());
            tfSp.setAdduser(staffID);
            tfSp.setQiyeID(qiyeID);
            iTuifeishenpiService.save(tfSp);
            tspID = tfSp.getId();

            List<Pxbuxikechengtable> buxiList = iPxbuxikechengtableService.list(new QueryWrapper<Pxbuxikechengtable>()
                    .eq("stuID",form.getStuID())
                    .eq("qiyeID", qiyeID));

            if (buxiList.size() > 0) {
                for (Pxbuxikechengtable item : buxiList) {
                    //添加课程审批记录
                    Tuikechenginfo kc = new Tuikechenginfo();
                    kc.setStuID(Long.valueOf(form.getStuID()));
                    kc.setTuibxkechengID(item.getId());
                    kc.setRemainkeshi(item.getRemainkeshi());
                    kc.setTuikeshi(item.getRemainkeshi());
                    kc.setTuikechengPrice(item.getKechengprice());
                    kc.setAdduser(staffID);
                    kc.setAddTime(new Date());
                    kc.setTuifeispID(tfSp.getId());
                    kc.setTuimoney(item.getRemainkeshi().multiply(item.getKechengprice()));
                    kc.setQiyeID(qiyeID);
                    iTuikechenginfoService.save(kc);
                }
            }


            List<Pxqiandansupplies> splist = iPxqiandansuppliesService.list(
                    new QueryWrapper<Pxqiandansupplies>()
                            .eq("stuID",form.getStuID())
                            .eq("qiyeID", qiyeID)
            );
            if (splist.size() > 0) {
                for (Pxqiandansupplies item : splist) {
                    //存在商品退费项

                    //剩余可退金额
                    BigDecimal chamoney = item.getSumMoney().subtract(item.getTuiMoney() == null ? BigDecimal.valueOf(0) : item.getTuiMoney());

                    //剩余可退数量
                    String tuinum = iPxqiandansuppliesService.getBuysupplies(new QueryWrapper<Pxqiandansupplies>()
                            .eq("QiandaninfoID", item.getQiandaninfoID())
                            .eq("TeachingSuppliesID", item.getTeachingSuppliesID())
                            .eq("qiyeID", qiyeID)
                    );
                    BigDecimal chanum = new BigDecimal(tuinum); //剩余可退数量

                    Pxteachingsuppliestable tsupplies = iPxteachingsuppliestableService.getById(item.getTeachingSuppliesID());
                    Tuisuppliseinfo sp = new Tuisuppliseinfo();
                    sp.setQiadansuppliesTabID(item.getId());
                    sp.setTuisuppliseID(item.getTeachingSuppliesID());
                    sp.setStuID(Long.valueOf(form.getStuID()));
                    sp.setTuiguige(tsupplies.getSpecs());
                    sp.setAdduser(staffID);
                    sp.setAddTime(new Date());
                    sp.setTuifeispID(tfSp.getId());
                    sp.setTuimoney(chamoney);
                    sp.setTuinum(chanum);
                    sp.setQiyeID(qiyeID);
                    iTuisuppliseinfoService.save(sp);
                }
            }


            QueryWrapper<Pxqiandaninfo2table> zfQ = new QueryWrapper<>();
            zfQ
                    .eq("b.stuID",form.getStuID())
                    .eq("a.qiyeID", qiyeID)
                    .in("a.qianInfoTabID", alltqdIDs);

            List<Pxqiandaninfo2table> tuiallzf = iPxqiandaninfo2tableService.tuiallzf(zfQ);
            if (tuiallzf.size() > 0) {
                for (Pxqiandaninfo2table item : tuiallzf) {
                    //存在需要退费的杂费项
                    Tuizafeiinfo zf = new Tuizafeiinfo();
                    zf.setStuID(Long.valueOf(form.getStuID()));
                    zf.setQiandanInfo2ID(item.getId());
                    zf.setQiandanOtherID(item.getQianDanOtherMoneyID());
                    zf.setTuizfmoney(item.getZongMoney());
                    zf.setAdduser(staffID);
                    zf.setAddTime(new Date());
                    zf.setTuifeispID(tfSp.getId());
                    zf.setQiyeID(qiyeID);
                    iTuizafeiinfoService.save(zf);
                }
            }


        } else {
            //未开审批 -直接添加数据
            Pxqiandaninfotable tuiqd = new Pxqiandaninfotable();
            tuiqd.setStuID(Long.valueOf(form.getStuID()));
            tuiqd.setQiandandate(tuiDate);
            tuiqd.setShishouTotalMoney(form.getTuiallmoney().negate());
            tuiqd.setHetongMoney(form.getTuiallmoney().negate());
            tuiqd.setMoneyStyle(3);
            tuiqd.setBeizhu(form.getShuoming());
            tuiqd.setQianDanStaffID(Long.valueOf(form.getYejirenID()));
            tuiqd.setRecordInStaffID(staffID);
            tuiqd.setRecordInTime(new Date());
            tuiqd.setPayMoneyStyle(Long.valueOf(form.getPayStyleID()));
            tuiqd.setCampusID(stu.getCampusID());
            tuiqd.setIsdingjing(1);
            tuiqd.setQiyeID(qiyeID);
            iPxqiandaninfotableService.save(tuiqd);

            xiugaiInfo += ",签单信息表里添加退费记录";

            //流水记录中添加退费记录
            Pxliushuizhangtable ls = new Pxliushuizhangtable();
            ls.setLiushuiDateTime(tuiDate);
            ls.setCampusID(stu.getCampusID());
            ls.setLiushuiZaiyao("校区：" + cam.getCampusName() + stuinfoLog + ":全额退费");
            ls.setPayMoneyStyle(Long.valueOf(form.getPayStyleID()));
            ls.setShouruMoney(BigDecimal.valueOf(0));
            ls.setZhichuMoney(form.getTuiallmoney());
            ls.setShouzhiStyleID(6L);
            ls.setJinbanRen(Long.valueOf(form.getYejirenID()));
            ls.setStuID(Long.valueOf(form.getStuID()));
            ls.setQiandanID(tuiqd.getId());
            ls.setLuruTime(new Date());
            ls.setAddStaffID(staffID);
            ls.setQiyeID(qiyeID);
            iPxliushuizhangtableService.save(ls);
            xiugaiInfo += ",流水表里添加流水记录";


            //添加退费记录--做可逆操作
            Pxtuifeitable tf = new Pxtuifeitable();
            tf.setStuID(Long.valueOf(form.getStuID()));
            tf.setTuifeiType(7);
            tf.setQiandanID(tuiqd.getId());

            tf.setBeforeTuifeiRemainXuefei(stu.getRemainXuefei());
            tf.setShoudTuifeiTotalMoney(form.getTuiallmoney()); //使用系统计算的
            tf.setShijiTuifeiTotalMoney(form.getTuiallmoney());
            tf.setAfterTuifeiRemainXuefei(BigDecimal.valueOf(0));

            tf.setBeforeTuifeiJifen(stu.getJifenNum());
            tf.setTuijifen(stu.getJifenNum());
            tf.setAfterTuifeiJifen(BigDecimal.valueOf(0));

            tf.setAfterTFchongzhiRemainMoney(stu.getRemainChongzhi());
            tf.setBeforeTFchongzhiRemainMoney(stu.getRemainChongzhi());
            tf.setTuiChongzhiMoney(BigDecimal.valueOf(0));

            tf.setTuifeiPayStyleID(Long.valueOf(form.getPayStyleID()));
            tf.setLiushuiID(ls.getId());
            tf.setBeizhu(form.getShuoming());
            tf.setAddStaffID(staffID);
            tf.setAddTime(new Date());
            tf.setQiyeID(qiyeID);
            iPxtuifeitableService.save(tf);

            //region 课程全退费
            List<Pxbuxikechengtable> buxikc = iPxbuxikechengtableService.list(new QueryWrapper<Pxbuxikechengtable>()
                    .eq("stuID",form.getStuID())
                    .eq("qiyeID", qiyeID)
            );

            if (buxikc.size() > 0) {
                for (Pxbuxikechengtable item : buxikc) {
                    Pxkechengtable kc = iPxkechengtableService.getById(item.getKechengID());

                    Pxqiandansubjecttable qdkc = new Pxqiandansubjecttable();
                    qdkc.setStuID(Long.valueOf(form.getStuID()));
                    qdkc.setQiandandate(tuiDate);
                    qdkc.setKechengID(item.getKechengID());
                    qdkc.setKechengprice(item.getKechengprice());
                    qdkc.setOriginalprice(item.getOriginalprice());
                    qdkc.setBuykeshiNum(item.getRemainkeshi().negate());
                    qdkc.setQianDanInfoID(tuiqd.getId());
                    qdkc.setZongjia(item.getKechengprice().multiply(item.getKeshiNum()).negate());
                    qdkc.setStartDate(item.getStartDate());
                    qdkc.setEndDate(item.getEndDate());
                    qdkc.setKechengStyle(4);
                    qdkc.setDiscount(BigDecimal.valueOf(10));
                    qdkc.setQiyeID(qiyeID);
                    iPxqiandansubjecttableService.save(qdkc);


                    xiugaiInfo += ",退费课程《" + kc.getKechengName() + "》，计费方式" + kc.getJifeiStyleID().toString() + "，课程单价" + kc.getKechengprice().toString() +
                    "退费前剩余课时" + item.getRemainkeshi().toString();

                    BigDecimal beforeks = item.getRemainkeshi();

                    item.setRemainkeshi(BigDecimal.valueOf(0));   //把buxikechengTable里的学生剩余课时清零；
                    xiugaiInfo += "，剩余课时被清零";
                    xiugaiInfo += "，退费前的课程剩余费用" + item.getZongjia().toString();
                    item.setZongjia(BigDecimal.valueOf(0));
                    iPxbuxikechengtableService.updateById(item);

                    //退费课程记录
                    Pxtuifeikechengtable tbxkc = new Pxtuifeikechengtable();
                    tbxkc.setTfBuxiID(item.getId());
                    tbxkc.setTfKechengID(item.getKechengID());
                    tbxkc.setKechengprice(item.getKechengprice());
                    tbxkc.setTfqianRemainkeshi(beforeks);
                    tbxkc.setTfkeshi(beforeks);
                    tbxkc.setTfhouRemainkeshi(BigDecimal.valueOf(0));
                    tbxkc.setBeizhu(form.getShuoming());
                    tbxkc.setTuifeiID(tf.getId());
                    tbxkc.setQiyeID(qiyeID);
                    iPxtuifeikechengtableService.save(tbxkc);


                    QueryWrapper<Pxstuclasstable> stuClQ = new QueryWrapper<>();
                    stuClQ
                            .eq("buxiID", item.getId())
                            .eq("qiyeID", qiyeID);
                    List<Pxstuclasstable> stuOneBuxikcClaALL = iPxstuclasstableService.list(stuClQ);
                    if (stuOneBuxikcClaALL.size() > 0) {
                        for (Pxstuclasstable onestucla : stuOneBuxikcClaALL) {
                            Long classID = onestucla.getClassID();
                            //这门课程的班级总人数

                            List<Pxstuclasstable> classStu = iPxstuclasstableService.list(new QueryWrapper<Pxstuclasstable>()
                                    .eq("classID", classID)
                                    .eq("qiyeID", qiyeID)
                            );


                            //学生退班；
                            List<Pxpaiketable> claPaikeAll = iPxpaiketableService.list(new QueryWrapper<Pxpaiketable>()
                                    .eq("classID", classID)
                                    .eq("qiyeID", qiyeID));

                            if (claPaikeAll.size() > 0) {
                                for (Pxpaiketable oneClapk : claPaikeAll) {
                                    //从选课表里删除之后再退班
                                    List<Pxxuanketable> stuXuankeAll = iPxxuanketableService.list(new QueryWrapper<Pxxuanketable>()
                                            .eq("paikeID", oneClapk.getId())
                                            .eq("buxiID", item.getId())
                                            .eq("qiyeID", qiyeID)
                                    );

                                    if (stuXuankeAll.size() > 0) {
                                        iPxxuanketableService.remove(new QueryWrapper<Pxxuanketable>()
                                                .eq("paikeID", oneClapk.getId())
                                                .eq("buxiID", item.getId())
                                                .eq("qiyeID", qiyeID));
                                    }
                                }
                            }
                            iPxstuclasstableService.removeById(onestucla);
                            if (classStu.size() > 0) {
                                //学生所在的班级里的学生人数大于1，即这个班里不只他一个学生，则退班；
                            } else {
                                //如果这个班级里只有他一个学生
                                Pxclasstable cla = iPxclasstableService.getById(classID);
                                if (cla != null) {
                                    //如果这个班级有课耗，假删，隐藏
                                    List<Pxkeshistutable> kehao = iPxkeshistutableService.list(new QueryWrapper<Pxkeshistutable>()
                                            .eq("classID", classID)
                                            .eq("qiyeID", qiyeID)
                                    );

                                    if (kehao.size() > 0) {
                                        cla.setIsdelete(true);
                                        cla.setClassState(1);
                                        cla.setIsShow(0);
                                        iPxclasstableService.updateById(cla);
                                    } else {
                                        //没有课耗,不管是不是一对一，都把这个班删除掉；
                                        //删除班级之前，把这个班的排课全删除掉
                                        List<Pxpaiketable> claPaike = iPxpaiketableService.list(new QueryWrapper<Pxpaiketable>()
                                                .eq("classID", classID)
                                                .eq("qiyeID", qiyeID));

                                        if (claPaike.size() > 0) {
                                            for (Pxpaiketable onep : claPaike) {
                                                //从选课表里删除之后再退班
                                                List<Pxpaiketeachertable> paikeT = iPxpaiketeachertableService.list(new QueryWrapper<Pxpaiketeachertable>()
                                                        .eq("paikeID", onep.getId())
                                                        .eq("qiyeID", qiyeID)
                                                );

                                                if (paikeT.size() > 0) {
                                                    iPxpaiketeachertableService.remove(
                                                            new QueryWrapper<Pxpaiketeachertable>()
                                                                    .eq("paikeID", onep.getId())
                                                                    .eq("qiyeID", qiyeID));
                                                }
                                            }
                                            iPxpaiketableService.remove(new QueryWrapper<Pxpaiketable>()
                                                    .eq("classID", classID)
                                                    .eq("qiyeID", qiyeID));
                                        }
                                        cla.setIsdelete(true);
                                        cla.setClassState(1);
                                        cla.setIsShow(0);
                                        iPxclasstableService.updateById(cla);
                                        //iPxclasstableService.removeById(cla);
                                    }
                                }
                            }
                        }
                    }

                }
            }

            //endregion

            //region 教学用品全退
            List<Pxqiandansupplies> jxlist = iPxqiandansuppliesService.list(
                    new QueryWrapper<Pxqiandansupplies>()
                            .eq("stuID",form.getStuID())
                            .eq("qiyeID", qiyeID)
            );
            for (Pxqiandansupplies item : jxlist) {
                //剩余可退金额
                BigDecimal chamoney = item.getSumMoney().subtract(item.getTuiMoney() == null ? BigDecimal.valueOf(0) : item.getTuiMoney());

                //剩余可退数量
                String tuinum = iPxqiandansuppliesService.getBuysupplies(new QueryWrapper<Pxqiandansupplies>()
                        .eq("QiandaninfoID", item.getQiandaninfoID())
                        .eq("TeachingSuppliesID", item.getTeachingSuppliesID())
                        .eq("qiyeID", qiyeID)
                );
                BigDecimal chanum = new BigDecimal(tuinum); //剩余可退数量
                item.setIsTuiFei(true);
                if (item.getTuiMoney() == null) {
                    item.setTuiMoney(BigDecimal.valueOf(0));
                }
                item.setTuiMoney(item.getSumMoney());
                iPxqiandansuppliesService.updateById(item);

                //在签单物品表中增加退费记录

                Pxqiandansupplies tuisp = new Pxqiandansupplies();
                tuisp.setTeachingSuppliesID(item.getTeachingSuppliesID());
                tuisp.setName(item.getName());
                tuisp.setBuySum(chanum.negate());
                tuisp.setQiandaninfoID(tuiqd.getId());
                tuisp.setBuyPrice(item.getBuyPrice());
                tuisp.setStuID(Long.valueOf(form.getStuID()));
                tuisp.setSumMoney(chamoney.negate());
                tuisp.setIsTuiFei(false);
                tuisp.setTuiMoney(chamoney.negate());
                tuisp.setFafangstate(1);
                tuisp.setQiyeID(qiyeID);
                iPxqiandansuppliesService.save(tuisp);

                //教学用品加库存
                Pxteachingsuppliestable tsup = iPxteachingsuppliestableService.getById(item.getTeachingSuppliesID());
                tsup.setStockNum(tsup.getStockNum().add(chanum));
                iPxteachingsuppliestableService.updateById(tsup);


                //添加商品退费记录-做可逆操作
                Pxtuifeiteachsuppliestable tuiteasp = new Pxtuifeiteachsuppliestable();
                tuiteasp.setQdsupID(item.getId());
                tuiteasp.setTeachSuppliesID(item.getTeachingSuppliesID());
                tuiteasp.setTeachSuppliesName(tsup.getName());
                tuiteasp.setGuige(tsup.getSpecs());
                tuiteasp.setTfqianRemainNums(tsup.getStockNum());
                tuiteasp.setTuiNums(chanum);
                tuiteasp.setTfhouRemainNums(tsup.getStockNum().add(chanum));
                tuiteasp.setDanwei(tsup.getStockUnit());
                tuiteasp.setPrice(item.getBuyPrice());
                tuiteasp.setBeizhu(tf.getBeizhu());
                tuiteasp.setTuifeiID(tf.getId());
                tuiteasp.setQiyeID(qiyeID);
                iPxtuifeiteachsuppliestableService.save(tuiteasp);

            }


            //endregion

            //region 学杂费全退
            QueryWrapper<Pxqiandaninfo2table> zfQ = new QueryWrapper<>();
            zfQ
                    .eq("b.stuID",form.getStuID())
                    .eq("a.qiyeID", qiyeID);

            List<Pxqiandaninfo2table> tuiallzf = iPxqiandaninfo2tableService.tuiallzf(zfQ);
            if (tuiallzf.size() > 0) {
                for (Pxqiandaninfo2table item : tuiallzf) {
                    if (item.getTuiMoney() == null) {
                        item.setTuiMoney(BigDecimal.valueOf(0));
                    }
                    BigDecimal beforetuiMoney = item.getTuiMoney();

                    item.setTuiMoney(item.getZongMoney());
                    iPxqiandaninfo2tableService.updateById(item);

                    //杂费退费可逆
                    Tuiqiandaninfo2 tzf = new Tuiqiandaninfo2();
                    tzf.setBeforetuiMoney(beforetuiMoney);
                    tzf.setAftertuiMoney(item.getZongMoney());
                    tzf.setTuifeiID(tf.getId());
                    tzf.setTuiqianInfo2ID(item.getId());
                    tzf.setQiyeID(qiyeID);
                    iTuiqiandaninfo2Service.save(tzf);

                }
            }

            //endregion

            //region 余额全退
            if (stu.getRemainChongzhi().compareTo(BigDecimal.valueOf(0)) == 1) {
                //>0  有余额需要退费
                Pxchongzhipaytable czPay = new Pxchongzhipaytable();

                czPay.setStuID(stu.getId());
                czPay.setChongzhiPayMoney(stu.getRemainChongzhi());
                czPay.setType(1);//退费支出
                czPay.setBeizhu("学员退费余额清空");//备注
                czPay.setTuifeiID(tf.getId());
                czPay.setAddStaffID(staffID);
                czPay.setAddTime(new Date());
                czPay.setQiyeID(qiyeID);
                iPxchongzhipaytableService.save(czPay);
                xiugaiInfo += "余额支付表里添加记录";

                tf.setBeforeTFchongzhiRemainMoney(stu.getRemainChongzhi());
                tf.setTuiChongzhiMoney(stu.getRemainChongzhi());
                tf.setAfterTuifeiRemainXuefei(BigDecimal.valueOf(0));
                stu.setRemainChongzhi(BigDecimal.valueOf(0));
                iPxtuifeitableService.updateById(tf);
                iPxstutableService.updateById(stu);
            }

            //endregion

            //清空学费
            stu.setRemainChongzhi(BigDecimal.valueOf(0));
            stu.setJifenNum(BigDecimal.valueOf(0));
            stu.setRemainXuefei(BigDecimal.valueOf(0));
            stu.setBuxiStateID(5);
            iPxstutableService.updateById(stu);
            xiugaiInfo += "，余额被清零";
            xiugaiInfo += "，剩余积分被清零";
            xiugaiInfo += "，剩余学费被清零";
            xiugaiInfo += "，设置学员状态为退费";

            ajaxJson.setMsg(stuinfoLog + xiugaiInfo);

        }
        return ajaxJson;
    }


    //endregion

    //region 退费审批
    @RequestMapping(value = "/getTuifeishenpiPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取退费审批分页")
    public AjaxJson getTuifeishenpiPage(HttpServletRequest request,
                                        Long size, Long current) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<shenpiVo> page = new Page<>(current, size);
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", qiyeID);

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 565);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("a.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("a.campusID", lookPower);
        }
        queryWrapper.orderByDesc("a.tuifeibanlidate");
        ajaxJson.setObj(iTuifeishenpiService.getshenpiPage(page, queryWrapper));

        return ajaxJson;
    }

    /**
     * @Description: shengpibohui()方法作用:驳回退费申请
     * @param:[spID, spshuoming, request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/4/8 16:05
     */
    @RequestMapping(value = "shengpibohui", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "驳回退费申请")
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson shengpibohui(String spID, String spshuoming, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        Tuifeishenpi sp = iTuifeishenpiService.getById(spID);
        if (sp.getSpfinish() != 1) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("已经处理完的审批！！！");
            return ajaxJson;
        } else {
            sp.setSpfinish(3);
            sp.setSpshuoming(spshuoming);
            sp.setSpUser(staffID);
            sp.setChuliTime(new Date());
            iTuifeishenpiService.updateById(sp);
        }
        return ajaxJson;
    }

    /**
     * @Description: tongguoShenpi()方法作用:通过审批
     * @param:[spID, request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/4/8 16:07
     */
    @RequestMapping(value = "tongguoShenpi", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "通过审批")
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson tongguoShenpi(String spID, String spshuoming, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();

        Tuifeishenpi tfSp = iTuifeishenpiService.getById(spID);


        if (tfSp.getSpfinish() != 1) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("已经处理完的审批！！！");
            return ajaxJson;
        } else {
//            String spqiandanID = tfSp.getSpqiandanID();
//            String[] spqdlist = spqiandanID.split(",");
            tfSp.setSpfinish(2);
            tfSp.setSpshuoming(spshuoming);
            tfSp.setSpUser(staffID);
            tfSp.setChuliTime(new Date());
            iTuifeishenpiService.updateById(tfSp);

            Pxstutable stu = iPxstutableService.getById(tfSp.getStuID());
            Pxcampustable cam = iPxcampustableService.getById(stu.getCampusID());
            String stuNo = stu.getZidingyiStuID() == null ? String.valueOf(stu.getId()) : stu.getZidingyiStuID();
            Long tfstuID = stu.getId();

            Pxqiandaninfotable tuiqd = new Pxqiandaninfotable();
            tuiqd.setStuID(tfstuID);
            tuiqd.setQiandandate(tfSp.getTuifeibanlidate());
            tuiqd.setShishouTotalMoney(tfSp.getYingtuiMoney().negate());
            tuiqd.setHetongMoney(tfSp.getYingtuiMoney().negate());
            tuiqd.setMoneyStyle(3);
            tuiqd.setBeizhu(tfSp.getTuifeishuoming());
            tuiqd.setQianDanStaffID(tfSp.getYejiren());
            tuiqd.setRecordInStaffID(staffID);
            tuiqd.setRecordInTime(new Date());
            tuiqd.setPayMoneyStyle(tfSp.getSppayMoneystyle());
            tuiqd.setCampusID(tfSp.getCampusID());
            tuiqd.setIsdingjing(1);
            tuiqd.setQiyeID(qiyeID);
            iPxqiandaninfotableService.save(tuiqd);

            Pxliushuizhangtable ls = new Pxliushuizhangtable();
            ls.setLiushuiDateTime(tfSp.getTuifeibanlidate());
            ls.setCampusID(tfSp.getCampusID());
            ls.setLiushuiZaiyao("校区：" + cam.getCampusName() + "学号：" + stuNo + "学员：" + stu.getStuName() + "退费审批通过");
            ls.setPayMoneyStyle(tfSp.getSppayMoneystyle());
            ls.setShouruMoney(BigDecimal.valueOf(0));
            ls.setZhichuMoney(tfSp.getYingtuiMoney());
            ls.setShouzhiStyleID(6L);
            ls.setJinbanRen(tfSp.getYejiren());
            ls.setStuID(tfstuID);
            ls.setQiandanID(tuiqd.getId());
            ls.setLuruTime(new Date());
            ls.setAddStaffID(staffID);
            ls.setQiyeID(qiyeID);
            iPxliushuizhangtableService.save(ls);

            BigDecimal l = new BigDecimal(0);
            //添加退费记录--做可逆操作
            Pxtuifeitable tf = new Pxtuifeitable();
            tf.setStuID(tfstuID);
            tf.setTuifeiType(tfSp.getType());
            tf.setTuifeispID(tfSp.getId());
            tf.setQiandanID(tuiqd.getId());

            tf.setBeforeTuifeiRemainXuefei(stu.getRemainXuefei());
            tf.setAfterTuifeiRemainXuefei(stu.getRemainXuefei().subtract(tfSp.getYingtuiMoney()));
            tf.setShoudTuifeiTotalMoney(tfSp.getYingtuiMoney());
            tf.setShijiTuifeiTotalMoney(tfSp.getYingtuiMoney());

            tf.setBeforeTuifeiJifen(stu.getJifenNum());
            tf.setTuijifen(BigDecimal.valueOf(0));
            tf.setAfterTuifeiJifen(stu.getJifenNum());

            tf.setBeforeTFchongzhiRemainMoney(stu.getRemainChongzhi());//假数据 等待下面更新
            tf.setTuiChongzhiMoney(l);//假数据 等待下面更新
            tf.setAfterTFchongzhiRemainMoney(l);//假数据 等待下面更新

            tf.setTuifeiPayStyleID(tfSp.getSppayMoneystyle());
            tf.setLiushuiID(ls.getId());
            tf.setBeizhu(tfSp.getTuifeishuoming());
            tf.setAddStaffID(staffID);
            tf.setAddTime(new Date());
            tf.setQiyeID(qiyeID);
            iPxtuifeitableService.save(tf);

            Tuichongzhiyuesp tuiCZ = iTuichongzhiyuespService.getOne(new QueryWrapper<Tuichongzhiyuesp>()
                    .eq("tfspID", tfSp.getId())
                    .eq("qiyeID", qiyeID)
            );


            if (tfSp.getType() == 1 || tfSp.getType() == 3 || tfSp.getType() == 7) {
                //退课程
                List<Tuikechenginfo> kcInfo = iTuikechenginfoService.list(new QueryWrapper<Tuikechenginfo>()
                        .eq("tuifeispID", spID)
                        .eq("qiyeID", qiyeID)
                );

                if (kcInfo.size() > 0) {
                    for (Tuikechenginfo item : kcInfo) {
                        Pxbuxikechengtable tuibuxikc = iPxbuxikechengtableService.getById(item.getTuibxkechengID());//补习课程
                        Pxkechengtable tkc = iPxkechengtableService.getById(tuibuxikc.getKechengID());

                        Pxqiandansubjecttable qdkc = new Pxqiandansubjecttable();
                        qdkc.setStuID(tfstuID);
                        qdkc.setQiandandate(tfSp.getTuifeibanlidate());
                        qdkc.setKechengID(tkc.getId());
                        qdkc.setKechengprice(item.getTuikechengPrice());
                        qdkc.setOriginalprice(tuibuxikc.getOriginalprice());
                        qdkc.setBuykeshiNum(item.getTuikeshi());//审批里面的退的课时数
                        qdkc.setQianDanInfoID(tuiqd.getId());
                        qdkc.setZongjia(tuibuxikc.getKechengprice().multiply(tuibuxikc.getKeshiNum()).negate());
                        qdkc.setStartDate(tuibuxikc.getStartDate());
                        qdkc.setEndDate(tuibuxikc.getEndDate());
                        qdkc.setKechengStyle(4);
                        qdkc.setDiscount(BigDecimal.valueOf(10));
                        qdkc.setQiyeID(qiyeID);
                        iPxqiandansubjecttableService.save(qdkc);


                        //退费课程记录
                        Pxtuifeikechengtable tbxkc = new Pxtuifeikechengtable();
                        tbxkc.setTfBuxiID(item.getTuibxkechengID());
                        tbxkc.setTfKechengID(tuibuxikc.getKechengID());
                        tbxkc.setKechengprice(tuibuxikc.getKechengprice());
                        tbxkc.setTfqianRemainkeshi(tuibuxikc.getRemainkeshi());
                        tbxkc.setTfkeshi(item.getTuikeshi());
                        tbxkc.setTfhouRemainkeshi(tuibuxikc.getRemainkeshi().subtract(item.getTuikeshi()));
                        tbxkc.setBeizhu(tfSp.getTuifeishuoming());
                        tbxkc.setTuifeiID(tf.getId());
                        tbxkc.setQiyeID(qiyeID);
                        iPxtuifeikechengtableService.save(tbxkc);

                        tuibuxikc.setRemainkeshi(tbxkc.getTfhouRemainkeshi());
                        tuibuxikc.setZongjia(tbxkc.getTfhouRemainkeshi().multiply(tuibuxikc.getKechengprice()));
                        iPxbuxikechengtableService.updateById(tuibuxikc);

                        QueryWrapper<Pxstuclasstable> stuClQ = new QueryWrapper<>();
                        stuClQ
                                .eq("buxiID", item.getId())
                                .eq("qiyeID", qiyeID);
                        List<Pxstuclasstable> stuOneBuxikcClaALL = iPxstuclasstableService.list(stuClQ);
                        if (stuOneBuxikcClaALL.size() > 0) {
                            for (Pxstuclasstable onestucla : stuOneBuxikcClaALL) {
                                Long classID = onestucla.getClassID();
                                //这门课程的班级总人数

                                List<Pxstuclasstable> classStu = iPxstuclasstableService.list(new QueryWrapper<Pxstuclasstable>()
                                        .eq("classID", classID)
                                        .eq("qiyeID", qiyeID)
                                );


                                //学生退班；
                                List<Pxpaiketable> claPaikeAll = iPxpaiketableService.list(new QueryWrapper<Pxpaiketable>()
                                        .eq("classID", classID)
                                        .eq("qiyeID", qiyeID));

                                if (claPaikeAll.size() > 0) {
                                    for (Pxpaiketable oneClapk : claPaikeAll) {
                                        //从选课表里删除之后再退班
                                        List<Pxxuanketable> stuXuankeAll = iPxxuanketableService.list(new QueryWrapper<Pxxuanketable>()
                                                .eq("paikeID", oneClapk.getId())
                                                .eq("buxiID", item.getId())
                                                .eq("qiyeID", qiyeID)
                                        );

                                        if (stuXuankeAll.size() > 0) {
                                            iPxxuanketableService.remove(new QueryWrapper<Pxxuanketable>()
                                                    .eq("paikeID", oneClapk.getId())
                                                    .eq("buxiID", item.getId())
                                                    .eq("qiyeID", qiyeID));
                                        }
                                    }
                                }
                                iPxstuclasstableService.removeById(onestucla);
                                if (classStu.size() > 0) {
                                    //学生所在的班级里的学生人数大于1，即这个班里不只他一个学生，则退班；
                                } else {
                                    //如果这个班级里只有他一个学生
                                    Pxclasstable cla = iPxclasstableService.getById(classID);
                                    if (cla != null) {
                                        //如果这个班级有课耗，假删，隐藏
                                        List<Pxkeshistutable> kehao = iPxkeshistutableService.list(new QueryWrapper<Pxkeshistutable>()
                                                .eq("classID", classID)
                                                .eq("qiyeID", qiyeID)
                                        );

                                        if (kehao.size() > 0) {
                                            cla.setIsdelete(true);
                                            cla.setClassState(1);
                                            cla.setIsShow(0);
                                            iPxclasstableService.updateById(cla);
                                        } else {
                                            //没有课耗,不管是不是一对一，都把这个班删除掉；
                                            //删除班级之前，把这个班的排课全删除掉
                                            List<Pxpaiketable> claPaike = iPxpaiketableService.list(new QueryWrapper<Pxpaiketable>()
                                                    .eq("classID", classID)
                                                    .eq("qiyeID", qiyeID));

                                            if (claPaike.size() > 0) {
                                                for (Pxpaiketable onep : claPaike) {
                                                    //从选课表里删除之后再退班
                                                    List<Pxpaiketeachertable> paikeT = iPxpaiketeachertableService.list(new QueryWrapper<Pxpaiketeachertable>()
                                                            .eq("paikeID", onep.getId())
                                                            .eq("qiyeID", qiyeID)
                                                    );

                                                    if (paikeT.size() > 0) {
                                                        iPxpaiketeachertableService.remove(
                                                                new QueryWrapper<Pxpaiketeachertable>()
                                                                        .eq("paikeID", onep.getId())
                                                                        .eq("qiyeID", qiyeID));
                                                    }
                                                }
                                                iPxpaiketableService.remove(new QueryWrapper<Pxpaiketable>()
                                                        .eq("classID", classID)
                                                        .eq("qiyeID", qiyeID));
                                            }
                                            cla.setIsdelete(true);
                                            cla.setClassState(1);
                                            cla.setIsShow(0);
                                            iPxclasstableService.updateById(cla);
                                            //iPxclasstableService.removeById(cla);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                //全科退费 清空剩余学费
                if (tfSp.getType() == 1) {
                    tf.setBeforeTuifeiRemainXuefei(stu.getRemainXuefei());
                    tf.setAfterTuifeiRemainXuefei(BigDecimal.valueOf(0));
                    iPxtuifeitableService.updateById(tf);

                    stu.setRemainXuefei(BigDecimal.valueOf(0));
                    iPxstutableService.updateById(stu);
                }


            } else if (tfSp.getType() == 4 || tfSp.getType() == 7) {
                //退杂费
                List<Tuizafeiinfo> tuizf = iTuizafeiinfoService.list(new QueryWrapper<Tuizafeiinfo>()
                        .eq("tuifeispID", tfSp.getId())
                        .eq("qiyeID", qiyeID)
                );
                if (tuizf.size() > 0) {
                    for (Tuizafeiinfo item : tuizf) {
                        Pxqiandaninfo2table zfInfo
                                = iPxqiandaninfo2tableService.getById(item.getQiandanInfo2ID());
                        BigDecimal beforetuiMoney = zfInfo.getTuiMoney();
                        zfInfo.setTuiMoney(item.getTuizfmoney());
                        iPxqiandaninfo2tableService.updateById(zfInfo);

                        //杂费退费可逆
                        Tuiqiandaninfo2 tzf = new Tuiqiandaninfo2();
                        tzf.setBeforetuiMoney(beforetuiMoney);
                        tzf.setAftertuiMoney(zfInfo.getTuiMoney());
                        tzf.setTuifeiID(tf.getId());
                        tzf.setTuiqianInfo2ID(zfInfo.getId());
                        tzf.setQiyeID(qiyeID);
                        iTuiqiandaninfo2Service.save(tzf);

                    }
                }
            } else if (tfSp.getType() == 5 || tfSp.getType() == 7) {
                //退商品
                List<Tuisuppliseinfo> tuisupp = iTuisuppliseinfoService.list(new QueryWrapper<Tuisuppliseinfo>()
                        .eq("tuifeispID", tfSp.getId())
                        .eq("qiyeID", qiyeID)
                );

                if (tuisupp.size() > 0) {
                    for (Tuisuppliseinfo item : tuisupp) {
                        Pxqiandansupplies qdsup = iPxqiandansuppliesService.getById(item.getQiadansuppliesTabID());

                        Pxteachingsuppliestable tsup = iPxteachingsuppliestableService.getById(item.getTuisuppliseID());

                        qdsup.setIsTuiFei(true);
                        qdsup.setTuiMoney(item.getTuimoney());
                        iPxqiandansuppliesService.updateById(qdsup);

                        Pxqiandansupplies tuisp = new Pxqiandansupplies();
                        tuisp.setTeachingSuppliesID(item.getTuisuppliseID());
                        tuisp.setName(tsup.getName());
                        tuisp.setBuySum(item.getTuinum().negate());
                        tuisp.setQiandaninfoID(tuiqd.getId());
                        tuisp.setBuyPrice(qdsup.getBuyPrice());
                        tuisp.setStuID(tfstuID);
                        tuisp.setSumMoney(item.getTuimoney().negate());
                        tuisp.setIsTuiFei(false);
                        tuisp.setTuiMoney(item.getTuimoney().negate());
                        tuisp.setFafangstate(1);
                        tuisp.setQiyeID(qiyeID);
                        iPxqiandansuppliesService.save(tuisp);


                        //教学用品加库存
                        tsup.setStockNum(tsup.getStockNum().add(item.getTuinum()));
                        iPxteachingsuppliestableService.updateById(tsup);

                        //添加商品退费记录-做可逆操作
                        Pxtuifeiteachsuppliestable tuiteasp = new Pxtuifeiteachsuppliestable();
                        tuiteasp.setQdsupID(item.getQiadansuppliesTabID());
                        tuiteasp.setTeachSuppliesID(item.getTuisuppliseID());
                        tuiteasp.setTeachSuppliesName(tsup.getName());
                        tuiteasp.setGuige(tsup.getSpecs());
                        tuiteasp.setTfqianRemainNums(tsup.getStockNum());
                        tuiteasp.setTuiNums(item.getTuinum());
                        tuiteasp.setTfhouRemainNums(tsup.getStockNum().add(item.getTuinum()));
                        tuiteasp.setDanwei(tsup.getStockUnit());
                        tuiteasp.setPrice(qdsup.getBuyPrice());
                        tuiteasp.setBeizhu(tf.getBeizhu());
                        tuiteasp.setTuifeiID(tf.getId());
                        tuiteasp.setQiyeID(qiyeID);
                        iPxtuifeiteachsuppliestableService.save(tuiteasp);
                    }
                }
            } else if (tfSp.getType() == 6) {
                //退余额
                Pxchongzhipaytable czPay = new Pxchongzhipaytable();
                czPay.setStuID(tfstuID);
                czPay.setChongzhiPayMoney(tuiCZ.getTuiyue());
                czPay.setType(1);//充值退费支出
                czPay.setBeizhu("学员退余额");//备注
                czPay.setTuifeiID(tf.getId());
                czPay.setAddStaffID(staffID);
                czPay.setAddTime(new Date());
                czPay.setQiyeID(qiyeID);
                iPxchongzhipaytableService.save(czPay);

                tf.setTuiChongzhiMoney(tuiCZ.getTuiyue());
                tf.setAfterTFchongzhiRemainMoney(stu.getRemainChongzhi().subtract(tuiCZ.getTuiyue()));
                iPxtuifeitableService.updateById(tf);

                List<Pxchongzhitable> chongzhilist = iPxchongzhitableService.list(new QueryWrapper<Pxchongzhitable>()
                        .eq("qiyeID", qiyeID)
                        .eq("stuID", tfstuID));
                BigDecimal zmoney = new BigDecimal(0);
                if (chongzhilist.size() > 0) {
                    for (Pxchongzhitable item : chongzhilist) {
                        zmoney = zmoney.add(item.getSongMoney());
                        item.setSongMoney(BigDecimal.valueOf(0));
                        iPxchongzhitableService.updateById(item);
                    }
                }

                stu.setRemainChongzhi(stu.getRemainChongzhi().subtract(tuiCZ.getTuiyue()).subtract(zmoney));
                iPxstutableService.updateById(stu);
            } else if (tfSp.getType() == 7) {
                Pxchongzhipaytable czPay = new Pxchongzhipaytable();
                czPay.setStuID(tfstuID);
                czPay.setChongzhiPayMoney(stu.getRemainChongzhi());
                czPay.setType(1);//充值退费支出
                czPay.setBeizhu("学员退余额");//备注
                czPay.setTuifeiID(tf.getId());
                czPay.setAddStaffID(staffID);
                czPay.setAddTime(new Date());
                czPay.setQiyeID(qiyeID);
                iPxchongzhipaytableService.save(czPay);

                //全部退费清空余额、积分、学费，设置学员状态为退费

                tf.setBeforeTuifeiRemainXuefei(stu.getRemainXuefei());
                tf.setAfterTuifeiRemainXuefei(BigDecimal.valueOf(0));
                tf.setBeforeTFchongzhiRemainMoney(stu.getRemainChongzhi());
                tf.setAfterTFchongzhiRemainMoney(l);
                tf.setBeforeTuifeiJifen(stu.getJifenNum());
                tf.setTuijifen(stu.getJifenNum());
                iPxtuifeitableService.updateById(tf);

                stu.setRemainChongzhi(BigDecimal.valueOf(0));
                stu.setJifenNum(BigDecimal.valueOf(0));
                stu.setRemainXuefei(BigDecimal.valueOf(0));
                stu.setBuxiStateID(5);
                iPxstutableService.updateById(stu);
            }
        }


        return ajaxJson;
    }


    @ResponseBody
    @PostMapping("tuiyueBystu")
    @ApiOperation("退学员余额")
    public AjaxJson tuiyueBystu(
            HttpServletRequest request, @RequestBody tuiyueBystuForm form
            ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");

        Pxstutable stu = iPxstutableService.getById(form.getStuID());
        String stuinfoLog = "";
        String xiugaiInfo = "";

        if (stu == null) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("未找到该学生信息");
            return ajaxJson;
        }

        String stuNo = stu.getZidingyiStuID() == null ?form.getStuID(): stu.getZidingyiStuID();
        stuinfoLog = "学号" + stuNo + "，姓名" + stu.getStuName();
        Pxcampustable cam = iPxcampustableService.getById(stu.getCampusID());
        Pxstafftable yejiren = iPxstafftableService.getById(form.getYejirenID());
        Date tuiDate = new Date();
        try {
            tuiDate = sdf.parse(form.getProcessingTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        xiugaiInfo =
                ",【学员退充值余额】:退充值余额，清空全部充值赠送金额。 录入的退费余额金额是" +form.getTuiyue().toString() + "，退费备注：" +form.getShuoming()+ ",业绩人:" + yejiren.getStaffName() +
            "，退费时间：" + tuiDate.toString() + "，退费前，学员剩余学费" + stu.getRemainXuefei().toString() + "退费前，学员剩余充值余额：" + stu.getRemainChongzhi() + "元。";


        BigDecimal daijinquan = new BigDecimal(0);
        BigDecimal youhuijine = new BigDecimal(0);
        String modifyValue = ""; //判断是否开启退费审批

        Pxsysparamvaluetable sysvalue = iPxsysparamvaluetableService.getOne(new QueryWrapper<Pxsysparamvaluetable>()
                .eq("sysparamTypeID", 106L)
                .eq("qiyeID", qiyeID));
        modifyValue = sysvalue.getModifyValue();


        if (modifyValue != null && modifyValue.equals("1")) {
            //审批流程

            List<Tuifeishenpi> tfsplist = iTuifeishenpiService.list(new QueryWrapper<Tuifeishenpi>().eq("stuID",form.getStuID()).eq("qiyeID", qiyeID).eq(
                    "spfinish",
                    1));
            if (tfsplist.size() > 0) {
                ajaxJson.setCode("N");
                ajaxJson.setCode("该学员已有一条未审批退费，请在审批之后再继续操作退费！");
                return ajaxJson;
            }
            xiugaiInfo += "，【后台开启退费审批流程】";

            List<Pxqiandaninfotable> qdlist = iPxqiandaninfotableService.list(
                    new QueryWrapper<Pxqiandaninfotable>()
                            .eq("stuID",form.getStuID())
                            .and(a -> a.eq("moneyStyle", 1).or(b -> b.eq("moneyStyle", 1)))
                            .eq("qiyeID", qiyeID)

            );
            String alltqdIDs = "";
            if (qdlist.size() > 0) {
                for (Pxqiandaninfotable item : qdlist) {
                    alltqdIDs += item.getId() + ",";
                    if (item.getYouhuiID() == null) {
                        youhuijine = youhuijine;
                    } else {
                        Pxyouhuizhengcetable xzyouhui = iPxyouhuizhengcetableService.getById(item.getYouhuiID());
                        if (xzyouhui != null) {
                            youhuijine = youhuijine.add(xzyouhui.getYouhui());//全部可退费的签单的优惠金额
                        }
                    }

                    Pxdaijinquantable daijin = iPxdaijinquantableService.getOne(
                            new QueryWrapper<Pxdaijinquantable>()
                                    .eq("stuID",form.getStuID())
                                    .eq("qiandanID", item.getId())
                                    .eq("qiyeID", qiyeID)
                    );
                    if (daijin == null) {
                        daijinquan = daijinquan;
                    } else {
                        daijinquan = daijinquan.add(daijin.getMoney());//全部可退费的签单的代金券金额
                    }
                }
            }

            //添加审批记录
            Tuifeishenpi tfSp = new Tuifeishenpi();
            if (StringUtils.isNotBlank(alltqdIDs)) {
                tfSp.setTuiqiandanID(alltqdIDs);
                tfSp.setSpqiandanID(alltqdIDs);
            } else {
                tfSp.setTuiqiandanID(alltqdIDs);
                tfSp.setSpqiandanID(alltqdIDs);
            }
            tfSp.setCampusID(stu.getCampusID());
            tfSp.setStuID(Long.valueOf(form.getStuID()));
            tfSp.setType(6);
            tfSp.setYingtuiMoney(form.getTuiyue());
            tfSp.setSqtuiMoney(form.getTuiyue());
            tfSp.setYouhuiMoney(youhuijine);
            tfSp.setDaijinquanMoney(daijinquan);
            tfSp.setTuifeibanlidate(tuiDate);
            tfSp.setTuifeishuoming(form.getShuoming());
            tfSp.setSppayMoneystyle(Long.valueOf(form.getPayStyleID()));
            tfSp.setYejiren(Long.valueOf(form.getYejirenID()));
            tfSp.setSpfinish(1);
            tfSp.setAddTiem(new Date());
            tfSp.setAdduser(staffID);
            tfSp.setQiyeID(qiyeID);
            iTuifeishenpiService.save(tfSp);

            Tuichongzhiyuesp tyue = new Tuichongzhiyuesp();
            tyue
                    .setAddTime(new Date())
                    .setAddUser(staffID).setBeforetuiMoney(stu.getRemainChongzhi())
                    .setTuiyue(form.getTuiyue()).setAftertuiMoney(stu.getRemainChongzhi().subtract(form.getTuiyue()))
                    .setTfspID(tfSp.getId())
                    .setQiyeID(qiyeID);
            iTuichongzhiyuespService.save(tyue);

            ajaxJson.setMsg("当前已开启审批流程，请到退费审批查看余额退费进程。");
        } else {

            List<Pxchongzhitable> chongzhilist = iPxchongzhitableService.list(new QueryWrapper<Pxchongzhitable>()
                    .eq("qiyeID", qiyeID)
                    .eq("stuID",form.getStuID()));
            BigDecimal zmoney = new BigDecimal(0);
            if (chongzhilist.size() > 0) {
                for (Pxchongzhitable item : chongzhilist) {
                    zmoney = zmoney.add(item.getSongMoney());
                    item.setSongMoney(BigDecimal.valueOf(0));
                    iPxchongzhitableService.updateById(item);
                }
            }

            //未开审批
            Pxqiandaninfotable tuiqd = new Pxqiandaninfotable();
            tuiqd.setStuID(Long.valueOf(form.getStuID()));
            tuiqd.setQiandandate(tuiDate);
            tuiqd.setShishouTotalMoney(form.getTuiyue().negate());
            tuiqd.setHetongMoney(form.getTuiyue().negate());
            tuiqd.setMoneyStyle(3);
            tuiqd.setBeizhu(form.getShuoming());
            tuiqd.setQianDanStaffID(Long.valueOf(form.getYejirenID()));
            tuiqd.setRecordInStaffID(staffID);
            tuiqd.setRecordInTime(new Date());
            tuiqd.setPayMoneyStyle(Long.valueOf(form.getPayStyleID()));
            tuiqd.setCampusID(stu.getCampusID());
            tuiqd.setIsdingjing(1);
            tuiqd.setQiyeID(qiyeID);
            iPxqiandaninfotableService.save(tuiqd);

            //流水记录中添加退费记录
            Pxliushuizhangtable ls = new Pxliushuizhangtable();
            ls.setLiushuiDateTime(tuiDate);
            ls.setCampusID(stu.getCampusID());
            ls.setLiushuiZaiyao(cam.getCampusName() + "," + stuinfoLog + ":全额退费");
            ls.setPayMoneyStyle(Long.valueOf(form.getPayStyleID()));
            ls.setShouruMoney(BigDecimal.valueOf(0));
            ls.setZhichuMoney(form.getTuiyue());
            ls.setShouzhiStyleID(6L);
            ls.setJinbanRen(Long.valueOf(form.getYejirenID()));
            ls.setStuID(Long.valueOf(form.getStuID()));
            ls.setQiandanID(tuiqd.getId());
            ls.setLuruTime(new Date());
            ls.setAddStaffID(staffID);
            ls.setQiyeID(qiyeID);
            iPxliushuizhangtableService.save(ls);
            xiugaiInfo += ",流水表里添加流水记录";


            //添加退费记录--做可逆操作
            Pxtuifeitable tf = new Pxtuifeitable();
            tf.setStuID(Long.valueOf(form.getStuID()));
            tf.setTuifeiType(6);
            tf.setQiandanID(tuiqd.getId());

            tf.setBeforeTuifeiRemainXuefei(stu.getRemainXuefei());
            tf.setAfterTuifeiRemainXuefei(stu.getRemainXuefei());
            tf.setShoudTuifeiTotalMoney(BigDecimal.valueOf(0));
            tf.setShijiTuifeiTotalMoney(BigDecimal.valueOf(0));

            tf.setBeforeTuifeiJifen(stu.getJifenNum());
            tf.setTuijifen(BigDecimal.valueOf(0));
            tf.setAfterTuifeiJifen(stu.getJifenNum());

            tf.setBeforeTFchongzhiRemainMoney(stu.getRemainChongzhi());
            tf.setTuiChongzhiMoney(form.getTuiyue());
            tf.setAfterTFchongzhiRemainMoney(stu.getRemainChongzhi().subtract(form.getTuiyue()));

            tf.setTuifeiPayStyleID(Long.valueOf(form.getPayStyleID()));
            tf.setLiushuiID(ls.getId());
            tf.setBeizhu(form.getShuoming());
            tf.setAddStaffID(staffID);
            tf.setAddTime(new Date());
            tf.setQiyeID(qiyeID);
            iPxtuifeitableService.save(tf);

            Pxchongzhipaytable czpay = new Pxchongzhipaytable();
            czpay
                    .setStuID(stu.getId())
                    .setChongzhiPayMoney(form.getTuiyue())
                    .setType(1)
                    .setBeizhu(form.getShuoming())
                    .setAddStaffID(staffID)
                    .setAddTime(new Date())
                    .setTuifeiID(tf.getId())
                    .setQiyeID(qiyeID);

            iPxchongzhipaytableService.save(czpay);

            stu.setRemainChongzhi(stu.getRemainChongzhi().subtract(form.getTuiyue()).subtract(zmoney));
            iPxstutableService.updateById(stu);

        }
        return ajaxJson;
    }

    //endregion
}
