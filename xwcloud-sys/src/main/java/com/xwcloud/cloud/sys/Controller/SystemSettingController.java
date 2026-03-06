package com.xwcloud.cloud.sys.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.Form.*;
import com.xwcloud.cloud.model.Form.sys.*;
import com.xwcloud.cloud.model.OA.OaKehu;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.Vo.classroomVo;
import com.xwcloud.cloud.model.Vo.daohangVo;
import com.xwcloud.cloud.model.Vo.minimumchargeVo;
import com.xwcloud.cloud.model.Vo.staffpostVo;
import com.xwcloud.cloud.model.entity.*;
import com.xwcloud.cloud.sys.Service.*;
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
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/sys/SystemSetting")
@Api(tags = "系统设置")
public class SystemSettingController {
    @Autowired
    IPxstaffposttableService iPxstaffposttableService;

    @Autowired
    IPxstugradetableService iPxstugradetableService;

    @Autowired
    IPxbuxistyletableService iPxbuxistyletableService;

    @Autowired
    IPxclasstimestyletableService iPxclasstimestyletableService;

    @Autowired
    IPxclassroomtableService iPxclassroomtableService;

    @Autowired
    IPxstuparamtypetableService iPxstuparamtypetableService;

    @Autowired
    IPxyxtelfromtableService iPxyxtelfromtableService;

    @Autowired
    IPxyxtelleveltableService iPxyxtelleveltableService;

    @Autowired
    IPxpaymoneystyletableService iPxpaymoneystyletableService;

    @Autowired
    IPxminimumchargetableService iPxminimumchargetableService;

    @Autowired
    IPxshouzhistyletableService iPxshouzhistyletableService;

    @Autowired
    IPxcampustableService iPxcampustableService;

    @Autowired
    IPxdropdownoptionstableService iPxdropdownoptionstableService;

    @Autowired
    IPxsysparamdefaulttableService iPxsysparamdefaulttableService;

    @Autowired
    IPxtskaiguandefaulttableService iPxtskaiguandefaulttableService;

    @Autowired
    IPxsysparamvaluetableService iPxsysparamvaluetableService;

    @Autowired
    IPxtesttypetableService iPxtesttypetableService;

    @Autowired
    IPxstafftableService iPxstafftableService;

    @Autowired
    IPxqiandanothermoneytableService iPxqiandanothermoneytableService;

    @Autowired
    IPxtskaiguanvaluetableService iPxtskaiguanvaluetableService;

    @Autowired
    IOaKehuService iOaKehuService;

    @Autowired
    IPxstutableService iPxstutableService;
    @Autowired
    IPxmanyidutableService iPxmanyidutableService;
    @Autowired
    IPxpowertableService iPxpowertableService;
    @Autowired
    IPxtousutableService iPxtousutableService;
    @Autowired
    IPxdaibantableService iPxdaibantableService;
    @Autowired
    IPxdaibantypetableService iPxdaibantypetableService;
    @Autowired
    IPxqiandaninfotableService iPxqiandaninfotableService;
    @Autowired
    IPxczhuodongtableService iPxczhuodongtableService;
    @Autowired
    IWscGoodsService iWscGoodsService;
    @Autowired
    IWhdH5HuodongfabuService iWhdH5HuodongfabuService;
    @Autowired
    IWscOrderService iWscOrderService;
    @Autowired
    IWscOrdertuifeiService iWscOrdertuifeiService;
    @Autowired
    IWscOrdergoodsService iWscOrdergoodsService;
    @Autowired
    IPxbuxikechengtableService iPxbuxikechengtableService;
    @Autowired
    IPxpaiketableService iPxpaiketableService;
    @Autowired
    IPxevaluationtableService iPxevaluationtableService;
    @Autowired
    IPxqingjiatableService iPxqingjiatableService;
    @Autowired
    IPxyuekestufaqitableService iPxyuekestufaqitableService;
    @Autowired
    IPxstucardtableService iPxstucardtableService;
    @Autowired
    IPxclasstableService iPxclasstableService;
    @Autowired
    IPxkeshistutableService iPxkeshistutableService;
    @Autowired
    IPxsubjecttableService iPxsubjecttableService;
    @Autowired
    IPxyichangkaoqintableService iPxyichangkaoqintableService;
    @Autowired
    IPxworkweekrecordtableService iPxworkweekrecordtableService;
    @Autowired
    IPxworkdayrecordtableService iPxworkdayrecordtableService;
    @Autowired
    IPxbooksborrowtableService iPxbooksborrowtableService;
    @Autowired
    IPxdaohangstatableService iPxdaohangstatableService;
    @Autowired
    IPxdaohangtableService iPxdaohangtableService;
    @Autowired
    IEvaluationpingfenService iEvaluationpingfenService;

    @Autowired
    IWscTuikeBuyService iWscTuikeBuyService;

    @Autowired
    IWscTuikelevelService iWscTuikelevelService;

    @Autowired
    IWscTuikeYongjinService iWscTuikeYongjinService;

    @GetMapping("gettuikelevelPage")
    @ResponseBody
    @ApiOperation("获取推客等级设置")
    public AjaxJson gettuikelevelPage(HttpServletRequest request, long size, long current) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<WscTuikelevel> page = new Page(current, size);
        QueryWrapper<WscTuikelevel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());

//        QueryWrapper queryWrapper1 = new QueryWrapper();
//        queryWrapper1.eq("qiyeID", loginUser.getQiyeID());
//        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
//        queryWrapper1.eq("menuID", 211);
//        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
//        if (lookPower.equals("0")) {//个人权限
//            queryWrapper.eq("a.campusID", 0);
//        } else if (lookPower.equals("-1")) {//所在校区权限
//            queryWrapper.eq("a.campusID", loginUser.getCampusID());
//        } else if (lookPower.equals("-2")) {//所有校区权限
//
//        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
//            queryWrapper.in("a.campusID", lookPower);
//        }
        queryWrapper.orderByDesc("id");
        ajaxJson.setObj(iWscTuikelevelService.getpage(page, queryWrapper));
        return ajaxJson;
    }

    @ResponseBody
    @GetMapping("geteditlevelbyID")
    @ApiOperation("获取修改的推客等级信息")
    public AjaxJson geteditlevelbyID(String id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iWscTuikelevelService.getById(id));
        return ajaxJson;
    }

    @ResponseBody
    @PostMapping("settklevel")
    @ApiOperation("设置推客等级启用/不启用")
    public AjaxJson settklevel(HttpServletRequest request, String ids, int isshow) {
        AjaxJson aja = new AjaxJson();
//        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        String[] IDs = ids.split(",");
        for (String item : IDs) {
            WscTuikelevel one = iWscTuikelevelService.getById(item);
            one.setIsShow(isshow);
            iWscTuikelevelService.updateById(one);
        }
        return aja;
    }

    @ResponseBody
    @DeleteMapping("deltklevel")
    @ApiOperation("删除推客等级")
    public AjaxJson deltklevel(HttpServletRequest request, String ids) {
        AjaxJson aja = new AjaxJson();
//        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        String[] IDs = ids.split(",");
        aja.setSuccess(iWscTuikelevelService.removeByIds(Arrays.asList(IDs)));
        return aja;
    }


    ///添加/修改

    @ResponseBody
    @PostMapping("savetklevel")
    @ApiOperation("添加/修改推客等级")
    public AjaxJson savetklevel(HttpServletRequest request,
                                String id,
                                String tuikeLevelName,
                                BigDecimal tiaojianMoney,
                                BigDecimal fjFanyongbi1,
                                BigDecimal zjFanyongbi1,
                                BigDecimal fjFanyongbi2,
                                BigDecimal zjFanyongbi2,
                                String shuoming
    ) {
        AjaxJson aja = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        if (StringUtils.isNotBlank(id)) {
            //修改
            WscTuikelevel one = iWscTuikelevelService.getById(id);
            one
                    .setTuikeLevelName(tuikeLevelName)
                    .setTiaojianMoney(tiaojianMoney)
                    .setFjFanyongbi1(fjFanyongbi1)
                    .setZjFanyongbi1(zjFanyongbi1)
                    .setFjFanyongbi2(fjFanyongbi2)
                    .setZjFanyongbi2(zjFanyongbi2)
                    .setShuoming(shuoming);

            iWscTuikelevelService.updateById(one);
        } else {
            //添加
            WscTuikelevel two = new WscTuikelevel();
            two
                    .setQiyeID(loginUser.getQiyeID())
                    .setIsShow(1)
                    .setTuikeLevelName(tuikeLevelName)
                    .setTiaojianMoney(tiaojianMoney)
                    .setFjFanyongbi1(fjFanyongbi1)
                    .setZjFanyongbi1(zjFanyongbi1)
                    .setFjFanyongbi2(fjFanyongbi2)
                    .setZjFanyongbi2(zjFanyongbi2)
                    .setShuoming(shuoming);

            iWscTuikelevelService.save(two);
        }

        return aja;
    }


    @GetMapping("getxitongremainDay")
    @ResponseBody
    @ApiOperation("获取系统剩余天数")
    public AjaxJson getxitongremainDay(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");

        Pxstafftable staff = iPxstafftableService.getById(loginUser.getStaffID());
        Pxcampustable cam = iPxcampustableService.getById(staff.getCampusID());
        long days = (cam.getNextPayTime().getTime() - (new Date()).getTime()) / (24 * 1000 * 60 * 60); ///1000毫秒*60s*60m*24h
        ajaxJson.setObj(days);
        return ajaxJson;
    }


    //region 代办提醒
    @GetMapping("getdaibanTixin")
    @ResponseBody
    @ApiOperation("获取代办提醒")
    public AjaxJson getdaibanTixin(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        List<Pxdaibantable> daibanList = iPxdaibantableService.list(
                new QueryWrapper<Pxdaibantable>()
                        .eq("isShow", 1));
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat mydf = new SimpleDateFormat("MM-dd");
        String nowday = sdf.format(new Date());

        JSONObject allObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (Pxdaibantable item : daibanList) {
            JSONObject jsonObject1 = new JSONObject();
            if (item.getId() == 1) {
                //意向跟进
                List<Pxstutable> yxlist = iPxstutableService.list(new QueryWrapper<Pxstutable>()
                        .eq("qiyeiD", qiyeID)
                        .eq("buxiStateID", 1)//意向学员
                        .isNotNull("nextGenjinTime")
                        .and(a -> a
                                .eq("(DATE_FORMAT(nextGenjinTime,'%Y-%m-%d'))", nowday)
                                .eq("yxfenpeistaffID", loginUser.getStaffID())
                                .eq("(select count(id) from pxyxgengjintable where (DATE_FORMAT(gengjinTime,'%Y-%m-%d'))=" + nowday + " and stuId= pxstutable" +
                                        ".id)", 0)
                        )
                );
                if (yxlist.size() > 0) {
                    jsonObject1.put("msg", "今天有" + yxlist.size() + "个意向跟进");
                    jsonObject1.put("url", "genjintixing");
                    jsonArray.add(jsonObject1);
                    allObject.put("tixin", jsonArray);
                }
            } else if (item.getId() == 2) {
                //报名尾款
                List<Pxqiandaninfotable> wkList = iPxqiandaninfotableService.list(new QueryWrapper<Pxqiandaninfotable>()
                        .eq("qiyeID", loginUser.getQiyeID())
                        .last(" and HetongMoney >(shishouTotalMoney+youhuijine+ (select ( CASE WHEN sum(b.money) IS NOT NULL THEN b.money ELSE 0 END ) from " +
                                "pxdaijinquantable b where pxqiandaninfotable.id=b.qiandanID))")
                );
                if (wkList.size() > 0) {
                    jsonObject1.put("msg", "今天有" + wkList.size() + "个报名尾款");
                    jsonObject1.put("url", "xinqian");
                    jsonArray.add(jsonObject1);
                    allObject.put("tixin", jsonArray);
                }
            } else if (item.getId() == 3) {
                //充值报课
            } else if (item.getId() == 4) {
                //充值优惠政策
                List<Pxczhuodongtable> czyhzc = iPxczhuodongtableService.list(new QueryWrapper<Pxczhuodongtable>()
                        .eq("qiyeID", qiyeID)
                        .lt("Edate", nowday)
                );
                if (czyhzc.size() > 0) {
                    jsonObject1.put("msg", "今天有" + czyhzc.size() + "个充值优惠政策过期");
                    jsonObject1.put("url", "chongzhiyhzc");
                    jsonArray.add(jsonObject1);
                    allObject.put("tixin", jsonArray);
                }

            } else if (item.getId() == 5) {
                //意向留言 x
            } else if (item.getId() == 6) {
                //商品低库存
                List<HashMap<String, Object>> spdkc = iWscGoodsService.getnoKCGoods(new QueryWrapper<WscGoods>().
                        eq("qiyeID", qiyeID)
                );
                if (spdkc.size() > 0) {
                    jsonObject1.put("msg", "有" + spdkc.size() + "个商城商品低库存");
                    jsonObject1.put("url", "shangpinguanli");
                    jsonArray.add(jsonObject1);
                    allObject.put("tixin", jsonArray);
                }
            } else if (item.getId() == 7) {
                //H5招生活动
                List<WhdH5Huodongfabu> H5List = iWhdH5HuodongfabuService.list(new QueryWrapper<WhdH5Huodongfabu>()
                        .eq("qiyeID", qiyeID)
                        .lt("huodongEndDateTime", df.format(new Date())));
                if (H5List.size() > 0) {
                    jsonObject1.put("msg", "有" + H5List.size() + "个H5招生活动过期");
                    jsonObject1.put("url", "zhaoxuexiu");
                    jsonArray.add(jsonObject1);
                    allObject.put("tixin", jsonArray);
                }
            } else if (item.getId() == 8) {
                //商城新订单
                List<WscOrder> nofahuoList = iWscOrderService.list(new QueryWrapper<WscOrder>().eq("orderState", 2));
                if (nofahuoList.size() > 0) {
                    jsonObject1.put("msg", "有" + nofahuoList.size() + "个商城新订单(未发货)");
                    jsonObject1.put("url", "ordersManage");
                    jsonArray.add(jsonObject1);
                    allObject.put("tixin", jsonArray);
                }
            } else if (item.getId() == 9) {
                //商城订单退费
                List<WscOrdertuifei> wctuiList = iWscOrdertuifeiService.list(new QueryWrapper<WscOrdertuifei>().eq("qiyeID", qiyeID).eq("tuikuanState", 0));
                if (wctuiList.size() > 0) {
                    jsonObject1.put("msg", "有" + wctuiList.size() + "个商城订单退费(未处理)");
                    jsonObject1.put("url", "returnGoodsManage");
                    jsonArray.add(jsonObject1);
                    allObject.put("tixin", jsonArray);
                }
            } else if (item.getId() == 10) {
                //商城评价
                List<WscOrdergoods> pjList = iWscOrdergoodsService.list(new QueryWrapper<WscOrdergoods>().eq("qiyeID", qiyeID).ge("pingjiaDate", nowday));
                if (pjList.size() > 0) {
                    jsonObject1.put("msg", "有" + pjList.size() + "个商城评价");
                    jsonObject1.put("url", "evaluationManage");
                    jsonArray.add(jsonObject1);
                    allObject.put("tixin", jsonArray);
                }
            } else if (item.getId() == 201) {
                //学员生日
                List<Pxstutable> birthList = iPxstutableService.list(new QueryWrapper<Pxstutable>().eq("qiyeID", qiyeID)
                        .eq("(DATE_FORMAT(stubirth,'%m-%d'))", mydf.format(new Date()))
                );
                if (birthList.size() > 0) {
                    jsonObject1.put("msg", "今天有" + birthList.size() + "个学员生日");
                    jsonObject1.put("url", "stuBirthDay");
                    jsonArray.add(jsonObject1);
                    allObject.put("tixin", jsonArray);
                }
            } else if (item.getId() == 202) {
                //剩余课时为负数
                List<Pxbuxikechengtable> fukeshiList = iPxbuxikechengtableService.list(new QueryWrapper<Pxbuxikechengtable>()
                        .eq("qiyeID", qiyeID)
                        .lt("remainkeshi", 0)
                        .last(" group by stuID")
                );
                if (fukeshiList.size() > 0) {
                    jsonObject1.put("msg", "有" + fukeshiList.size() + "个学生剩余课时为负数");
                    jsonObject1.put("url", "stukecheng");
                    jsonArray.add(jsonObject1);
                    allObject.put("tixin", jsonArray);
                }
            } else if (item.getId() == 203) {
                //课程过期
                List<Pxbuxikechengtable> guoqiList = iPxbuxikechengtableService.list(new QueryWrapper<Pxbuxikechengtable>()
                        .eq("qiyeID", qiyeID)
                        .lt("endDate", nowday)
                        .last(" group by stuID")
                );
                if (guoqiList.size() > 0) {
                    jsonObject1.put("msg", "有" + guoqiList.size() + "个学生课程过期");
                    jsonObject1.put("url", "stukecheng");
                    jsonArray.add(jsonObject1);
                    allObject.put("tixin", jsonArray);
                }
            } else if (item.getId() == 204) {
                //排课未消课
                List<Pxpaiketable> noxiakeList = iPxpaiketableService.list(
                        new QueryWrapper<Pxpaiketable>()
                                .eq("qiyeID", qiyeID)
                                .ne("dakaoqin", true)
                                .lt("haveClassDate", nowday));

                if (noxiakeList.size() > 0) {
                    jsonObject1.put("msg", "有" + noxiakeList.size() + "个排课未消课");
                    jsonObject1.put("url", "xiaokeqiandao");
                    jsonArray.add(jsonObject1);
                    allObject.put("tixin", jsonArray);
                }
            } else if (item.getId() == 205) {
                //学员评价老师
                List<Pxevaluationtable> stuToTeaList = iPxevaluationtableService.list(new QueryWrapper<Pxevaluationtable>()
                        .eq("qiyeID", qiyeID)
                        .eq("type", 2)
                        .ge("addtime", nowday));
                if (stuToTeaList.size() > 0) {
                    jsonObject1.put("msg", "有" + stuToTeaList.size() + "个学员评价老师");
                    jsonObject1.put("url", "stupjteacher");
                    jsonArray.add(jsonObject1);
                    allObject.put("tixin", jsonArray);
                }
            } else if (item.getId() == 206) {
                //请假审批
                List<Pxqingjiatable> sjshList = iPxqingjiatableService.list(new QueryWrapper<Pxqingjiatable>()
                        .eq("qiyeID", qiyeID)
                        .eq("shenheRen", 0));
                if (sjshList.size() > 0) {
                    jsonObject1.put("msg", "有" + sjshList.size() + "个请假审批");
                    jsonObject1.put("url", "weiqingjia");
                    jsonArray.add(jsonObject1);
                    allObject.put("tixin", jsonArray);
                }
            } else if (item.getId() == 207) {
                //约课未处理 stu
                List<Pxyuekestufaqitable> stuyklist = iPxyuekestufaqitableService.list(new QueryWrapper<Pxyuekestufaqitable>()
                        .eq("qiyeID", qiyeID)
                        .eq("yuekeShenheState", 1)
                );
                if (stuyklist.size() > 0) {
                    jsonObject1.put("msg", "有" + stuyklist.size() + "个约课未处理（学生）");
                    jsonObject1.put("url", "xuesheyueke");
                    jsonArray.add(jsonObject1);
                    allObject.put("tixin", jsonArray);
                }
            } else if (item.getId() == 208) {
                //约课未处理 tea

            } else if (item.getId() == 209) {
                //图书借阅超期
                String noback = iPxbooksborrowtableService.getnobreakList(new QueryWrapper<HashMap<String, String>>()
                        .eq("a.qiyeID", qiyeID)
                );
                if (!noback.equals("0")) {
                    jsonObject1.put("msg", "有" + noback + "个图书借阅超期");
                    jsonObject1.put("url", "jieshujilu");
                    jsonArray.add(jsonObject1);
                    allObject.put("tixin", jsonArray);
                }

            } else if (item.getId() == 210) {
                //学员待分配班主任
                List<Pxstutable> noteaStulist = iPxstutableService.list(new QueryWrapper<Pxstutable>()
                        .eq("qiyeID", qiyeID)
                        .and(a -> a.eq("banzhurenTeacherID", 0).isNotNull("banzhurenTeacherID")
                                .and(b -> b.eq("buxiStateID", 1).or(c -> c.eq("buxiStateID", 2)))
                        )
                );
                if (noteaStulist.size() > 0) {
                    jsonObject1.put("msg", "有" + noteaStulist.size() + "个学员待分配班主任");
                    jsonObject1.put("url", "stuTearch");
                    jsonArray.add(jsonObject1);
                    allObject.put("tixin", jsonArray);
                }
            } else if (item.getId() == 211) {
                //学员未发卡
                List<Pxstucardtable> nostuCardlist = iPxstucardtableService.getNostuCardlist(new QueryWrapper<Pxstucardtable>()
                        .eq("a.qiyeID", qiyeID)
                );
                if (nostuCardlist.size() > 0) {
                    jsonObject1.put("msg", "有" + nostuCardlist.size() + "个学员未发卡");
                    jsonObject1.put("url", "stuCard");
                    jsonArray.add(jsonObject1);
                    allObject.put("tixin", jsonArray);
                }
            } else if (item.getId() == 212) {
                //课程未插班
                List<Pxbuxikechengtable> noClasskcList = iPxbuxikechengtableService.getNOClasskc(new QueryWrapper<Pxbuxikechengtable>()
                        .eq("a.qiyeID", qiyeID)
                );
                if (noClasskcList.size() > 0) {
                    jsonObject1.put("msg", "有" + noClasskcList.size() + "个学生课程未插班");
                    jsonObject1.put("url", "zhuanbanchaban");
                    jsonArray.add(jsonObject1);
                    allObject.put("tixin", jsonArray);
                }
            } else if (item.getId() == 213) {
                //班级未排课
                List<Pxclasstable> nopkClassList = iPxclasstableService.getNopkClass(qiyeID);
                if (nopkClassList.size() > 0) {
                    jsonObject1.put("msg", "有" + nopkClassList.size() + "个班级未排课");
                    jsonObject1.put("url", "zhuanbanchaban");
                    jsonArray.add(jsonObject1);
                    allObject.put("tixin", jsonArray);
                }

            } else if (item.getId() == 214) {
                //长期无课耗
                List<HashMap<String, String>> longtimenokeshiStuList = iPxstutableService.getlongtimenokeshiStu(qiyeID);
                if (longtimenokeshiStuList.size() > 0) {
                    jsonObject1.put("msg", "有" + longtimenokeshiStuList.size() + "个学生超过三个月无课耗");
                    jsonObject1.put("url", "stukehao");
                    jsonArray.add(jsonObject1);
                    allObject.put("tixin", jsonArray);
                }
            } else if (item.getId() == 215) {
                //班级未上课
                List<HashMap<String, String>> nokeshiclassList = iPxclasstableService.getOnemonthnokeshiClass(qiyeID);
                if (nokeshiclassList.size() > 0) {
                    jsonObject1.put("msg", "有" + nokeshiclassList.size() + "个班级超过一个月未上课");
                    jsonObject1.put("url", "stukehao");
                    jsonArray.add(jsonObject1);
                    allObject.put("tixin", jsonArray);
                }
            } else if (item.getId() == 216) {
                //学员考勤异常
                List<Pxkeshistutable> exkaoqingstuList = iPxkeshistutableService.list(new QueryWrapper<Pxkeshistutable>()
                        .eq("qiyeID", qiyeID)
                        .last(" and haveclassDate=(DATE_FORMAT(NOW(),'%Y-%m-%d'))  and stuKaoqingStyle!=1 ")
                );
                if (exkaoqingstuList.size() > 0) {
                    jsonObject1.put("msg", "今天有" + exkaoqingstuList.size() + "个学员考勤异常");
                    jsonObject1.put("url", "stukehao");
                    jsonArray.add(jsonObject1);
                    allObject.put("tixin", jsonArray);
                }
            } else if (item.getId() == 217) {
                //家长反馈
                List<Pxtousutable> tousulist = iPxtousutableService.list(new QueryWrapper<Pxtousutable>()
                        .eq("qiyeID", qiyeID)
                        .eq("chayueState", false)
                );
                if (tousulist.size() > 0) {
                    jsonObject1.put("msg", "有" + tousulist.size() + "个家长反馈");
                    jsonObject1.put("url", "stukehao");
                    jsonArray.add(jsonObject1);
                    allObject.put("tixin", jsonArray);
                }
            } else if (item.getId() == 218) {
                //满意度评价
                List<Pxmanyidutable> mydList = iPxmanyidutableService.list(new QueryWrapper<Pxmanyidutable>()
                        .eq("qiyeID", qiyeID)
                        .eq("pingjiaDate", nowday)
                );
                if (mydList.size() > 0) {
                    jsonObject1.put("msg", "有" + mydList.size() + "条满意度评价");
                    jsonObject1.put("url", "manyidupingjia");
                    jsonArray.add(jsonObject1);
                    allObject.put("tixin", jsonArray);
                }
            } else if (item.getId() == 219) {
                //学员未完成微信绑定
                List<HashMap<String, String>> nowxUserList = iPxstutableService.getwxNoUserList(new QueryWrapper<HashMap<String, String>>()
                        .eq("a.qiyeID", qiyeID)
                        .isNull("b.id")
                );
                if (nowxUserList.size() > 0) {
                    jsonObject1.put("msg", "有" + nowxUserList.size() + "个学员未完成微信绑定");
                    jsonObject1.put("url", "xueyuanweixinzhanhao");
                    jsonArray.add(jsonObject1);
                    allObject.put("tixin", jsonArray);
                }
            } else if (item.getId() == 220) {
                //科目未设置任课老师
                List<Pxsubjecttable> noTeakemuList = iPxsubjecttableService.getNoTeakemu(new QueryWrapper<Pxsubjecttable>().eq("a.qiyeID", qiyeID));
                if (noTeakemuList.size() > 0) {
                    jsonObject1.put("msg", "有" + noTeakemuList.size() + "个科目未设置任课老师");
                    jsonObject1.put("url", "staffinfo");
                    jsonArray.add(jsonObject1);
                    allObject.put("tixin", jsonArray);
                }
            } else if (item.getId() == 401) {
                //员工异常考勤
                List<Pxyichangkaoqintable> ygkqlist = iPxyichangkaoqintableService.list(new QueryWrapper<Pxyichangkaoqintable>()
                        .eq("qiyeID", qiyeID)
                        .eq("addDate", nowday));
                if (ygkqlist.size() > 0) {
                    jsonObject1.put("msg", "有" + ygkqlist.size() + "个员工异常考勤");
                    jsonObject1.put("url", "staffinfo");
                    jsonArray.add(jsonObject1);
                    allObject.put("tixin", jsonArray);
                }
            } else if (item.getId() == 402) {
                //工作日报
                List<Pxworkdayrecordtable> logList = iPxworkdayrecordtableService.list(new QueryWrapper<Pxworkdayrecordtable>()
                        .eq("qiyeID", qiyeID)
                        .eq("DATE_FORMAT(LogDate,'%Y-%m-%d')", nowday));
                if (logList.size() > 0) {
                    jsonObject1.put("msg", "有" + logList.size() + "个工作日报");
                    jsonObject1.put("url", "Dailywork");
                    jsonArray.add(jsonObject1);
                    allObject.put("tixin", jsonArray);
                }

            } else if (item.getId() == 403) {
                //周工作总结
                List<Pxworkweekrecordtable> workList = iPxworkweekrecordtableService.list(new QueryWrapper<Pxworkweekrecordtable>()
                        .eq("qiyeID", qiyeID)
                        .eq("DATE_FORMAT(luruDate,'%Y-%m-%d')", nowday)
                );
                if (workList.size() > 0) {
                    jsonObject1.put("msg", "有" + workList.size() + "个周工作总结");
                    jsonObject1.put("url", "Weekwork");
                    jsonArray.add(jsonObject1);
                    allObject.put("tixin", jsonArray);
                }
            } else if (item.getId() == 404) {
                //员工生日
            } else if (item.getId() == 405) {
                //老师未微信绑定
                List<HashMap<String, String>> wxteaList = iPxstafftableService.getnowxTeaList(new QueryWrapper<Pxstafftable>().eq("a.qiyeID", qiyeID));
                if (wxteaList.size() > 0) {
                    jsonObject1.put("msg", "有" + wxteaList.size() + "个老师未微信绑定");
                    jsonObject1.put("url", "staffinfo");
                    jsonArray.add(jsonObject1);
                    allObject.put("tixin", jsonArray);
                }
            }

        }
        ajaxJson.setObj(allObject);
        return ajaxJson;
    }
    //endregion

    //region 快捷入口

    @GetMapping("getdaohangtoStaff")
    @ResponseBody
    @ApiOperation("获取用户导航")
    public AjaxJson getdaohangtoStaff(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        List<HashMap<String, Object>> staffdaohanglist = iPxdaohangstatableService.getstaffdaohang(new QueryWrapper<HashMap<String, Object>>()
                .eq("a.qiyeID", qiyeID)
                .eq("a.staffID", staffID)
        );
        ajaxJson.setObj(staffdaohanglist);
        return ajaxJson;
    }


    @RequestMapping(value = "saveUserdaohang", method = RequestMethod.POST)
    @ApiOperation(value = "保存用户导航")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson saveUserdaohang(
            @RequestBody daohangFrom from,
            HttpServletRequest request
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        String dhmessage = from.getDhmessage();
        if (StringUtils.isNotBlank(dhmessage)) {
            List<daohangVo> daohangVos = JSON.parseArray(dhmessage, daohangVo.class);


            String inIDs = "";
            for (daohangVo item : daohangVos) {
                if (StringUtils.isNotBlank(item.getId()) && !item.getId().equals("0")) {
                    //有
                    inIDs += item.getId() + ",";
                } else {
                    Pxdaohangtable DH = iPxdaohangtableService.getById(item.getDhID());
                    Pxdaohangstatable one = new Pxdaohangstatable();
                    one
                            .setStaffID(loginUser.getStaffID())
                            .setDhID(DH.getId())
                            .setPaixu(1)
                            .setIsShow(1)
                            .setQiyeID(loginUser.getQiyeID());
                    iPxdaohangstatableService.save(one);
                    inIDs += one.getId() + ",";
                }
            }
            String[] st = inIDs.split(",");

            List<Pxdaohangstatable> list = iPxdaohangstatableService.list(new QueryWrapper<Pxdaohangstatable>()
                    .eq("qiyeID", loginUser.getQiyeID())
                    .eq("staffID", loginUser.getStaffID())
                    .notIn("id", st)
            );

            if (list.size() > 0) {
                iPxdaohangstatableService.remove(new QueryWrapper<Pxdaohangstatable>()
                        .eq("qiyeID", loginUser.getQiyeID())
                        .eq("staffID", loginUser.getStaffID())
                        .notIn("id", st)
                );
            }


        }
        return ajaxJson;
    }


    //endregion

    //region 点评项目设置
    @GetMapping("getTeaRateitemList")
    @ResponseBody
    @ApiOperation("点评项目设置")
    public AjaxJson getTeaRateitemList(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        List<Evaluationpingfen> pfList = iEvaluationpingfenService.list(new QueryWrapper<Evaluationpingfen>()
                .eq("qiyeID", loginUser.getQiyeID())
        );
        ajaxJson.setObj(pfList);
        return ajaxJson;
    }

    @ApiOperation("添加修改老师评分项目")
    @ResponseBody
    @PostMapping("addRate")

    public AjaxJson addRate(HttpServletRequest request, @RequestBody addRateForm form) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");

        if (StringUtils.isNotBlank(form.getId())) {
            //修改
            List<Evaluationpingfen> haveone = iEvaluationpingfenService.list(new QueryWrapper<Evaluationpingfen>()
                    .eq("qiyeID", loginUser.getQiyeID())
                    .eq("pfName", form.getRatritem())
            );
            if (haveone.size() > 0) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("请勿设置重复项！");
                return ajaxJson;
            } else {
                Evaluationpingfen editone = iEvaluationpingfenService.getById(form.getId());
                editone.setPfName(form.getRatritem());
                iEvaluationpingfenService.updateById(editone);
            }

        } else {
            //添加
            List<Evaluationpingfen> pfList = iEvaluationpingfenService.list(new QueryWrapper<Evaluationpingfen>()
                    .eq("qiyeID", loginUser.getQiyeID())
            );
            if (pfList.size() > 5) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("最多设置5个评分项！");
                return ajaxJson;
            }
            if (StringUtils.isNotBlank(form.getRatritem())) {
                List<Evaluationpingfen> haveone = iEvaluationpingfenService.list(new QueryWrapper<Evaluationpingfen>()
                        .eq("qiyeID", loginUser.getQiyeID())
                        .eq("pfName",form.getRatritem())
                );
                if (haveone.size() > 0) {
                    ajaxJson.setCode("N");
                    ajaxJson.setMsg("请勿设置重复项！");
                    return ajaxJson;
                } else {
                    Evaluationpingfen one = new Evaluationpingfen();
                    one
                            .setPfName(form.getRatritem())
                            .setQiyeID(loginUser.getQiyeID());
                    iEvaluationpingfenService.save(one);
                }
            }
        }

        return ajaxJson;
    }


    @DeleteMapping("delTeaRate")
    @ResponseBody
    @ApiOperation("删除教师评分项目")
    public AjaxJson delTeaRate(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] IDs = ids.split(",");
        ajaxJson.setSuccess(iEvaluationpingfenService.removeByIds(Arrays.asList(IDs)));
        return ajaxJson;
    }

    //endregion

    //region 岗位设置


    @GetMapping("getqiyeName")
    @ResponseBody
    @ApiOperation("获取机构名称")
    public AjaxJson getqiyeName(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Pxstafftable staff = iPxstafftableService.getById(loginUser.getStaffID());
        QueryWrapper<HashMap<String, Object>> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (staff.getRole() < 1) {
            queryWrapper.eq("a.id", staff.getCampusID());
        }
        List<HashMap<String, Object>> getjigou = iPxcampustableService.getjigou(queryWrapper);
        ajaxJson.setObj(getjigou);

        return ajaxJson;
    }

    @GetMapping("getjigouqjandykTimes")
    @ResponseBody
    @ApiOperation("获取撤销请假、约课时间限制")
    public AjaxJson getjigouqjandykTimes(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        JSONObject jsonObject = new JSONObject();
        String qjvalue = "";
        String ykvalue = "";
        Pxsysparamvaluetable qj = iPxsysparamvaluetableService.getOne(new QueryWrapper<Pxsysparamvaluetable>()
                .eq("sysparamTypeID", 64L)
                .eq("qiyeID", loginUser.getQiyeID())
        );
        if (qj == null) {
            qjvalue = iPxsysparamdefaulttableService.getById(64).getDefaultValue();
        } else {
            qjvalue = qj.getModifyValue();
        }

        Pxsysparamvaluetable yk = iPxsysparamvaluetableService.getOne(new QueryWrapper<Pxsysparamvaluetable>()
                .eq("sysparamTypeID", 65L)
                .eq("qiyeID", loginUser.getQiyeID())
        );
        if (yk == null) {
            ykvalue = iPxsysparamdefaulttableService.getById(65).getDefaultValue();
        } else {
            ykvalue = yk.getModifyValue();
        }
        jsonObject.put("qjValue", qjvalue);
        jsonObject.put("ykValue", ykvalue);

        ajaxJson.setObj(jsonObject);
        return ajaxJson;

    }

    @ApiOperation("修改请假、撤销请假、撤销约课时间限制")
    @ResponseBody
    @PostMapping("editTimexianzhi")
    public AjaxJson editTimexianzhi(HttpServletRequest request, Long type, int times) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Pxsysparamvaluetable item = iPxsysparamvaluetableService.getOne(new QueryWrapper<Pxsysparamvaluetable>()
                .eq("sysparamTypeID", type)
                .eq("qiyeID", loginUser.getQiyeID())
        );
        if (item == null) {
            Pxsysparamvaluetable v = new Pxsysparamvaluetable();
            v
                    .setQiyeID(loginUser.getQiyeID())
                    .setSysparamTypeID(type)
                    .setModifyValue(String.valueOf(times));
            iPxsysparamvaluetableService.save(v);
        } else {
            item.setModifyValue(String.valueOf(times));
            iPxsysparamvaluetableService.updateById(item);
        }
        return ajaxJson;
    }


    /**
     * 查询所有岗位
     *
     * @param size
     * @param current
     * @param campusID
     * @param staffpostName
     * @return
     */
    @RequestMapping(value = "/getAllstaffPostPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询所有岗位信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "staffpostName", value = "岗位名称", required = false)
    })
    public AjaxJson getAllstaffPostPages(HttpServletRequest request,
                                         long size, long current, Long campusID, String staffpostName) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Page<staffpostVo> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (campusID != -1) {
            queryWrapper.eq("a.campusID", campusID);
        }
        if (StringUtils.isNotBlank(staffpostName)) {
            queryWrapper.like("staffpostName", staffpostName);
        }
        queryWrapper.orderByDesc("a.id");
        ajaxJson.setObj(iPxstaffposttableService.getStaffpostList(page, queryWrapper));
        return ajaxJson;
    }

    @RequestMapping(value = "/getstaffpostNameByID", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "通过岗位ID查询岗位名称")
    public AjaxJson getstaffpostNameByID(HttpServletRequest request, long staffpostID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        ajaxJson.setObj(iPxstaffposttableService.getById(staffpostID));
        return ajaxJson;
    }

    /**
     * 查询所有岗位信息
     *
     * @return
     */
    @RequestMapping(value = "/GetAllStaffPostList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询所有岗位信息")
    public AjaxJson GetAllStaffPostList(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        ajaxJson.setObj(iPxstaffposttableService.GetSearchStaffPostList(qiyeID));
        return ajaxJson;
    }

    /**
     * 新增岗位信息
     *
     * @param staffpostName
     * @param campusID
     * @param request
     * @return
     */
    @RequestMapping(value = "/addStaffpost", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "新增岗位信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "staffpostName", value = "岗位名称", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true),

    })
    public AjaxJson AddStaffPost(String staffpostName, long campusID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("StaffpostName", staffpostName);
        queryWrapper.eq("QiyeID", qiyeID);
        queryWrapper.eq("CampusID", campusID);
        List<Pxstaffposttable> pxstaffposttableList = iPxstaffposttableService.list(queryWrapper);
        if (pxstaffposttableList.size() > 0) {
            ajaxJson.setMsg("已存在这个岗位");
            ajaxJson.setCode("N");
        } else {
            Pxstaffposttable pxstaffpostTable = new Pxstaffposttable();
            pxstaffpostTable.setCampusID(campusID);
            pxstaffpostTable.setQiyeID(qiyeID);
            pxstaffpostTable.setStaffpostName(staffpostName);
            ajaxJson.setSuccess(iPxstaffposttableService.save(pxstaffpostTable));
        }
        return ajaxJson;
    }

    /**
     * 修改岗位信息
     *
     * @param id
     * @param staffpostName
     * @param campusID
     * @param request
     * @return
     */
    @RequestMapping(value = "/updatestaffpost", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改岗位信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "修改的岗位ID", required = true),
            @ApiImplicitParam(name = "staffpostName", value = "岗位名称", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true)
    })
    public AjaxJson UpdateStaffpost(long id, String staffpostName, long campusID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", qiyeID);
        queryWrapper.eq("StaffpostName", staffpostName);
        queryWrapper.eq("CampusID", campusID);
        if (iPxstaffposttableService.list(queryWrapper).size() > 0) {
            ajaxJson.setMsg("该校区已存在这个岗位");
            ajaxJson.setCode("N");
        } else {
            Pxstaffposttable pxstaffposttable = iPxstaffposttableService.getById(id);
            pxstaffposttable.setStaffpostName(staffpostName);
            pxstaffposttable.setCampusID(campusID);
            ajaxJson.setSuccess(iPxstaffposttableService.updateById(pxstaffposttable));
        }
        return ajaxJson;
    }

    /**
     * 删除岗位信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/deletestaffpost", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除岗位信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "删除岗位ID", required = true),
    })
    public AjaxJson DeleteStaffPost(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] delIDs = ids.split(",");
        // 使用Arrays.asList 转换
        List<String> idsList = Arrays.asList(delIDs);

        //判断岗位上是否有员工，如果该岗位没有员工，才可以删除该岗位
        for (String staffpostID : idsList) {
            QueryWrapper<Pxstafftable> pxstafftableQueryWrapper = new QueryWrapper<Pxstafftable>();
            pxstafftableQueryWrapper.eq("staffPostID", staffpostID);
            Pxstafftable onestaff = iPxstafftableService.getOne(pxstafftableQueryWrapper);
            Pxstaffposttable oneStaffpost = iPxstaffposttableService.getById(staffpostID);
            //如果该岗位没有员工，才可以删除该岗位
            if (onestaff != null) {
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg(oneStaffpost.getStaffpostName() + "-该岗位有员工数据，先删除员工数据后才能删除该岗位！");
                return ajaxJson;
            }
        }

        //删除该岗位的权限数据
        for (String staffpostID : idsList) {
            QueryWrapper<Pxpowertable> pxpowertableQueryWrapper = new QueryWrapper<Pxpowertable>();
            pxpowertableQueryWrapper.eq("staffpostID", staffpostID);
            List<Pxpowertable> listpower = iPxpowertableService.list(pxpowertableQueryWrapper);
            iPxpowertableService.removeByIds(listpower);
        }

        //删除岗位
        ajaxJson.setSuccess(iPxstaffposttableService.removeByIds(idsList));
        return ajaxJson;
    }
    //endregion

    //region 年级/年龄段设置

    /**
     * 分页查询年级信息
     *
     * @param size
     * @param current
     * @return
     */
    @RequestMapping(value = "/getStuGradePage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页查询年级信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "stuGradeName", value = "年级/年龄段", required = false),
    })
    public AjaxJson getStuGradePage(long size, long current, String stuGradeName) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<Pxstugradetable> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.isNotBlank(stuGradeName)) {
            queryWrapper.eq("stuGradeName", stuGradeName);
        }
        page = (Page<Pxstugradetable>) iPxstugradetableService.page(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 新增年级信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/addStuGrade", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "新增年级信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = false),
            @ApiImplicitParam(name = "stuGradeName", value = "年级/年龄段名称", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson addStuGrade(String stuGradeName, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();

        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.isNotEmpty(stuGradeName)) {
            queryWrapper.eq("QiyeID", qiyeID);
            queryWrapper.eq("StuGradeName", stuGradeName);
        }
        List<Pxstugradetable> pxstugradetableList = iPxstugradetableService.list(queryWrapper);
        if (pxstugradetableList.size() > 0) {
            ajaxJson.setMsg("已存在该年级，不能重复添加");
        } else {
            Pxstugradetable pxstugradetable = new Pxstugradetable();
            pxstugradetable.setQiyeID(qiyeID);
            pxstugradetable.setStugradename(stuGradeName);
            ajaxJson.setSuccess(iPxstugradetableService.save(pxstugradetable));
        }
        return ajaxJson;
    }

    /**
     * 修改年级信息
     *
     * @return
     */
    @RequestMapping(value = "/updateStugrade", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改年级信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true),
            @ApiImplicitParam(name = "stuGradeName", value = "年级/年龄段名称", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson updateStugrade(String stuGradeName, String id, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        List<Pxstugradetable> pxstugradetables = iPxstugradetableService.GetStuGradeListByqiyeIDAndGrade(qiyeID, stuGradeName);
        if (pxstugradetables.size() > 0) {
            ajaxJson.setMsg("当前系统已存在该名称的年级，请重新修改");
            return ajaxJson;
        } else {
            Pxstugradetable one = iPxstugradetableService.getById(id);
            one.setStugradename(stuGradeName);

            ajaxJson.setSuccess(iPxstugradetableService.updateById(one));
            return ajaxJson;
        }
    }

    /**
     * 删除年级信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/deleteStuGrage", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除年级信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "删除年级ID", required = true),
    })
    public AjaxJson deleteStuGrage(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] delIDs = ids.split(",");
        // 使用Arrays.asList 转换
        List<String> idsList = Arrays.asList(delIDs);

        //判断该年级下有没有学员
        for (String stuGradeID : idsList) {
            //iPxstutableService
            QueryWrapper<Pxstutable> pxstutableQueryWrapper = new QueryWrapper<>();
            pxstutableQueryWrapper.eq("stuGradeID", stuGradeID);
            Pxstutable oneStu = iPxstutableService.getOne(pxstutableQueryWrapper);
            Pxstugradetable oneStuGrade = iPxstugradetableService.getById(stuGradeID);
            if (oneStu != null) {
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg(oneStuGrade.getStugradename() + "-该年级有学员数据，先删除学员数据后才能删除该年级！");
                return ajaxJson;
            }
        }
        //该年级没有学员才可以删除该年级
        ajaxJson.setSuccess(iPxstugradetableService.removeByIds(idsList));
        return ajaxJson;
    }
    //endregion

    //region 培训方式设置

    /**
     * 查询所有的补习方式
     *
     * @param size
     * @param current
     * @param buxiStyleName
     * @return
     */
    @RequestMapping(value = "/getAllbuxistylePage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询所有补习方式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页码", example = "1", required = true),
            @ApiImplicitParam(name = "buxiStyleName", value = "补习方式名称", required = false)
    })
    public AjaxJson getAllbuxistylePage(long size, long current, String buxiStyleName, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();

        QueryWrapper queryWrapper = new QueryWrapper();
        Page<Pxbuxistyletable> page = new Page(current, size);
        if (StringUtils.isNotBlank(buxiStyleName)) {
            queryWrapper.eq("BuxiStyleName", buxiStyleName);
        }
        if (qiyeID != 0) {
            queryWrapper.eq("qiyeID", qiyeID);
        }
        page = (Page<Pxbuxistyletable>) iPxbuxistyletableService.page(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 新增补习方式
     *
     * @param buxiStyleName
     * @return
     */
    @RequestMapping(value = "/addbuxistyle", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "新增补习方式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "buxiStyleName", value = "补习方式名称", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = false),
    })
    public AjaxJson Addbuxistyle(String buxiStyleName, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();

        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.isNotEmpty((buxiStyleName))) {
            queryWrapper.eq("BuxiStyleName", buxiStyleName);
            queryWrapper.eq("QiyeID", qiyeID);
        }
        List<Pxbuxistyletable> pxbuxistyletableList = iPxbuxistyletableService.list(queryWrapper);
        if (pxbuxistyletableList.size() > 0) {
            ajaxJson.setMsg("当前系统已存在该补习方式，不允许重复添加");
            ajaxJson.setSuccess(false);
        } else {
            Pxbuxistyletable pxbuxistyletable = new Pxbuxistyletable();
            pxbuxistyletable.setQiyeID(qiyeID);
            pxbuxistyletable.setBuxiStyleName(buxiStyleName);
            ajaxJson.setSuccess(iPxbuxistyletableService.save(pxbuxistyletable));
        }
        return ajaxJson;
    }

    /**
     * 修改补习方式信息
     *
     * @param pxbuxistyletable
     * @return
     */
    @RequestMapping(value = "/updatebuxistyle", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改补习方式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "修改数据ID", required = true),
            @ApiImplicitParam(name = "buxiStyleName", value = "补习方式名称", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson Updatebuxistyle(Pxbuxistyletable pxbuxistyletable, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        String dlstaffName = loginUser.getStaffName();

        if (pxbuxistyletable.getBuxiStyleName().trim() == "一对一") {
            ajaxJson.setMsg("【一对一】培训方式不允许修改！");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("buxiStyleName", pxbuxistyletable.getBuxiStyleName());
        queryWrapper.eq("qiyeID", qiyeID);
        List<Pxbuxistyletable> pxbuxistyletableList = iPxbuxistyletableService.list(queryWrapper);
        if (pxbuxistyletableList.size() > 0) {
            ajaxJson.setMsg("当前系统已存在这个名称的补习方式");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        } else {
            ajaxJson.setSuccess(iPxbuxistyletableService.updateById(pxbuxistyletable));
            return ajaxJson;
        }

    }

    /**
     * 删除补习方式信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/deletebuxistyle", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除补习方式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "删除补习方式ID", required = true)
    })
    public AjaxJson deletebuxistyle(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] delIDs = ids.split(",");
        // 使用Arrays.asList 转换
        List<String> idsList = Arrays.asList(delIDs);

        //判断该年级下有没有学员
        for (String id : idsList) {
            List<Pxkechengtable> pxkechengtableList = iPxbuxistyletableService.GetKechengByBuxistyleID(id);
            if (pxkechengtableList.size() > 0) {
                ajaxJson.setMsg("您要删除的培训方式下有培训课程，不允许删除");
                ajaxJson.setSuccess(false);
                return ajaxJson;
            }
            Pxbuxistyletable pxbuxistyletable = iPxbuxistyletableService.getById(id);
            if (pxbuxistyletable.getBuxiStyleName().trim() == "一对一") {
                ajaxJson.setMsg("【一对一】培训方式不允许删除");
                ajaxJson.setSuccess(false);
                return ajaxJson;
            }
        }
        ajaxJson.setSuccess(iPxbuxistyletableService.removeByIds(idsList));
        return ajaxJson;
    }
    //endregion

    //region 课程时长设置

    /**
     * 分页查询课程时长信息
     *
     * @param size
     * @param current
     * @return
     */
    @RequestMapping(value = "/getALlclasstimestyle", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页查询课程时长信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码数", example = "1", required = true)
    })
    public AjaxJson getALlclasstimestylePage(long size, long current, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", qiyeID);
        Page<Pxclasstimestyletable> page = new Page(current, size);
        page = (Page<Pxclasstimestyletable>) iPxclasstimestyletableService.page(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 新增课程时长
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/addclasstimestyle", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "新增课程时长")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classTimeStyleName", value = "课程时长", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true)
    })
    public AjaxJson addclasstimestyle(String ClassTimeStyleName, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();
        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.isNotEmpty((ClassTimeStyleName))) {
            queryWrapper.eq("ClassTimeStyleName", ClassTimeStyleName);
            queryWrapper.eq("QiyeID", qiyeID);
        }
        Pxclasstimestyletable pxclasstimestyletable = new Pxclasstimestyletable();
        pxclasstimestyletable.setQiyeID(qiyeID);
        pxclasstimestyletable.setClasstimestylename(ClassTimeStyleName);
        List<Pxclasstimestyletable> pxclasstimestyletableList = iPxclasstimestyletableService.list(queryWrapper);
        if (pxclasstimestyletableList.size() > 0) {
            ajaxJson.setMsg("当前系统已存在相同的课程时长信息");
        } else {
            ajaxJson.setSuccess(iPxclasstimestyletableService.save(pxclasstimestyletable));
        }
        return ajaxJson;
    }

    /**
     * 修改课程时长信息
     *
     * @return
     */
    @RequestMapping(value = "/updateClasstimestyle", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改课程时长信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "修改数据ID", required = true),
            @ApiImplicitParam(name = "classTimeStyleName", value = "课程时长", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true)
    })
    public AjaxJson updateClasstimestyle(String id, String classTimeStyleName, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        //课程时长里有课程，则该课程时长不能修改和删除
        List<Pxkechengtable> pxkc = iPxclasstimestyletableService.GetkechengByKechengshichang(id);
        if (pxkc.size() > 0) {
            ajaxJson.setMsg("该课程时长有培训课程，不允许修改！");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }

        //课程时长不允许重复
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", qiyeID);
        queryWrapper.eq("ClassTimeStyleName", classTimeStyleName);
        List<Pxclasstimestyletable> pxclasstimestyletableList = iPxclasstimestyletableService.list(queryWrapper);
        if (pxclasstimestyletableList.size() > 0) {
            ajaxJson.setMsg("已存在你要的课程时长，不能修改为重复的课程时长值！");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        } else {
            Pxclasstimestyletable one = iPxclasstimestyletableService.getById(id);
            one.setClasstimestylename(classTimeStyleName);
            ajaxJson.setSuccess(iPxclasstimestyletableService.updateById(one));
        }
        return ajaxJson;
    }

    /**
     * 删除课程时长
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/deleteClasstimeStyle", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除课程时长")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Id", value = "删除数据ID", required = true)
    })
    public AjaxJson deleteClasstimeStyle(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] delIDs = ids.split(",");
        // 使用Arrays.asList 转换
        List<String> idsList = Arrays.asList(delIDs);

        //判断该课程时长有没有课程，有课程的话不允许删除
        for (String id : idsList) {
            //课程时长里有课程，则该课程时长不能修改和删除
            List<Pxkechengtable> pxkc = iPxclasstimestyletableService.GetkechengByKechengshichang(id);
            if (pxkc.size() > 0) {
                ajaxJson.setMsg("该课程时长有培训课程，不允许删除！");
                ajaxJson.setSuccess(false);
                return ajaxJson;
            }
        }
        iPxclasstimestyletableService.removeByIds(idsList);
        ajaxJson.setMsg("删除课程时长成功");
        return ajaxJson;
    }
    //endregion

    //region 教室设置

    /**
     * 查询教室信息
     *
     * @param size
     * @param current
     * @return
     */
    @RequestMapping(value = "/getPagesClassRoomList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页查询教室列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "classRoomName", value = "教室名称", required = false)
    })
    public AjaxJson getPagesClassRoomPage(long size, long current, long campusID, String classRoomName, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        String dlstaffName = loginUser.getStaffName();
        Page<classroomVo> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(classRoomName)) {
            queryWrapper.like("classRoomName", classRoomName);
        }
        if (campusID != -1) {
            queryWrapper.eq("a.campusID", campusID);
        }
        ajaxJson.setObj(iPxclassroomtableService.GetClassRoomPage(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * 添加教室
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/addClassRoom", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "新增教室")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classRoomName", value = "教室名称", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true),
            @ApiImplicitParam(name = "ischongtu", value = "是否检测冲突,1检测冲突，2不检测冲突", required = true),
    })
    public AjaxJson addClassRoom(String classRoomName, Long campusID, long ischongtu, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        String dlstaffName = loginUser.getStaffName();


        if (StringUtils.isNotEmpty(classRoomName)) {
            queryWrapper.eq("ClassRoomName", classRoomName);
            queryWrapper.eq("CampusID", campusID);
            queryWrapper.eq("QiyeID", qiyeID);
        }
        List<Pxclassroomtable> pxclassroomtableList = iPxclassroomtableService.list(queryWrapper);
        if (pxclassroomtableList.size() > 0) {
            ajaxJson.setMsg("教室名称重名了，请更换名称重新添加！");
            ajaxJson.setSuccess(false);
        } else {
            Pxclassroomtable pxclassroomtable = new Pxclassroomtable();
            pxclassroomtable.setClassroomname(classRoomName);
            pxclassroomtable.setQiyeID(qiyeID);
            pxclassroomtable.setCampusID(campusID);
            if (ischongtu == 1) {
                pxclassroomtable.setIschongtu(true);
            } else {
                pxclassroomtable.setIschongtu(false);
            }
            pxclassroomtable.setRecordinstaffid(staffID);
            pxclassroomtable.setRecordintime(new Date());
            ajaxJson.setSuccess(iPxclassroomtableService.save(pxclassroomtable));
        }
        return ajaxJson;
    }

    /**
     * 编辑教室信息
     *
     * @param id
     * @param classRoomName
     * @param campusID
     * @param ischongtu
     * @return
     */
    @RequestMapping(value = "/editClassRoom", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "编辑教室信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "教室ID", required = true),
            @ApiImplicitParam(name = "classRoomName", value = "教室名称", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true),
            @ApiImplicitParam(name = "ischongtu", value = "是否检测冲突", required = true),
//            @ApiImplicitParam(name = "recordInStaffID", value = "录入人ID", required = true),
//            @ApiImplicitParam(name = "recordInTime", value = "录入时间", required = true),
//            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true)
    })
    public AjaxJson editClassRoom(long id, String classRoomName, Long campusID, long ischongtu, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        String dlstaffName = loginUser.getStaffName();

        QueryWrapper queryWrapper = new QueryWrapper();
        List<Pxclassroomtable> classroomList = iPxclassroomtableService.GetClassRoomListByqiyeID_CampusID_classRoomName(qiyeID, campusID, classRoomName);
        if (classroomList.size() > 0) {
            ajaxJson.setMsg("当前系统已存在该名称的年级，请重新修改");
        } else {
            Pxclassroomtable classroomtable = iPxclassroomtableService.getById(id);
            if (classroomtable != null) {
                classroomtable.setClassroomname(classRoomName);
                if (ischongtu == 1) {
                    classroomtable.setIschongtu(true);
                } else {
                    classroomtable.setIschongtu(false);
                }
                classroomtable.setCampusID(campusID);

                ajaxJson.setSuccess(iPxclassroomtableService.updateById(classroomtable));
                ajaxJson.setMsg("教室修改成功！");
            } else {


                ajaxJson.setMsg("要修改的教室数据不存在！");
                ajaxJson.setSuccess(false);
            }
        }

        return ajaxJson;
    }

    @RequestMapping(value = "/GetclassroomInfo", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询教室详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Id", value = "数据ID", required = true)
    })
    public AjaxJson GetclassroomInfo(String Id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxclassroomtableService.getById(Id));
        return ajaxJson;
    }

    /**
     * 删除教室信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/deleteClassRoom", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除教室信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Id", value = "删除数据ID", required = true)
    })
    public AjaxJson deleteClassRoom(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] delIDs = ids.split(",");
        // 使用Arrays.asList 转换
        List<String> idsList = Arrays.asList(delIDs);

        for (String id : idsList) {
            List<Pxpaiketable> pxpaiketableList = iPxclassroomtableService.GetpaikeByclassRoomid(id);
            if (pxpaiketableList.size() > 0) {
                ajaxJson.setMsg("该教室已有排课，不能删除！");
                ajaxJson.setSuccess(false);
                return ajaxJson;
            }
        }
        ajaxJson.setSuccess(iPxclassroomtableService.removeByIds(idsList));
        return ajaxJson;
    }
    //endregion

    //region 报名附加字段设置

    /**
     * 分页查询报名附加字段设置
     *
     * @param size
     * @param current
     * @return
     */
    @RequestMapping(value = "/GetStuParamtypePages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页查询报名附加字段设置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true)
    })
    public AjaxJson GetStuParamtypePages(long size, long current, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        String dlstaffName = loginUser.getStaffName();

        Page<Pxstuparamtypetable> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", qiyeID);
        page = (Page<Pxstuparamtypetable>) iPxstuparamtypetableService.page(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 添加报名附加字段
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/AddStuParamTypeInfo", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加报名附加字段")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuParamTypeName", value = "学员自定义字段名称", required = true),
            @ApiImplicitParam(name = "IsBiTian", value = "是否必填", required = true),
            @ApiImplicitParam(name = "widthType", value = "0 短框 1.长框 2.下拉框", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = false),
    })
    public AjaxJson AddStuParamTypeInfo(String StuParamTypeName, boolean IsBiTian, Integer widthType, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        String dlstaffName = loginUser.getStaffName();

        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.isNotBlank(StuParamTypeName)) {
            queryWrapper.eq("StuParamTypeName", StuParamTypeName);
        }
        queryWrapper.eq("QiyeID", qiyeID);
        List<Pxbuxistyletable> pxbuxistyletableList = iPxstuparamtypetableService.list(queryWrapper);
        if (pxbuxistyletableList.size() > 0) {
            ajaxJson.setMsg("当前系统已存在该信息，不能重复添加");
        } else {
            Pxstuparamtypetable pxstuparamtypetable = new Pxstuparamtypetable();
            pxstuparamtypetable.setStuParamTypeName(StuParamTypeName);
            pxstuparamtypetable.setQiyeID(qiyeID);
            pxstuparamtypetable.setWidthType(widthType);
            pxstuparamtypetable.setIsBiTian(IsBiTian);
            ajaxJson.setSuccess(iPxstuparamtypetableService.save(pxstuparamtypetable));
        }
        return ajaxJson;
    }

    /**
     * 更新报名附加字段信息
     *
     * @param pxstuparamtypetable
     * @return
     */
    @RequestMapping(value = "/UpdateStuparamTypeInfo", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "更新报名附加字段信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "修改数据ID", required = true),
            @ApiImplicitParam(name = "stuParamTypeName", value = "学员自定义字段名称", required = true),
            @ApiImplicitParam(name = "IsBiTian", value = "是否必填", required = true),
            @ApiImplicitParam(name = "widthType", value = "0 短框 1.长框 2.下拉框", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = false),
    })
    public AjaxJson UpdateStuparamTypeInfo(Pxstuparamtypetable pxstuparamtypetable) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(iPxstuparamtypetableService.updateById(pxstuparamtypetable));
        return ajaxJson;
    }

    /**
     * 删除报名自定义字段信息
     *
     * @param Id
     * @return
     */
    @RequestMapping(value = "/DeleteStuParamType", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除报名自定义字段信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Id", value = "删除数据ID", required = true),
    })
    public AjaxJson DeleteStuParamType(String Id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(iPxstuparamtypetableService.removeById(Id));
        return ajaxJson;
    }

    /**
     * 加载对应自定义字段下拉框的下拉选项
     *
     * @param stuParamTypeId
     * @param size
     * @param current
     * @return
     */
    @RequestMapping(value = "/GetOptionsById", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页查询自定义字段的下拉选项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuParamTypeId", value = "下拉框的ID", required = true),
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true)
    })
    public AjaxJson GetOptionsByIdPages(String stuParamTypeId, long size, long current) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<Pxdropdownoptionstable> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("stuParamTypeId", stuParamTypeId);
        page = (Page<Pxdropdownoptionstable>) iPxdropdownoptionstableService.page(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 新增自定义下拉框的下拉选项
     *
     * @param request
     * @param stuParamTypeId
     * @param DropDownOptions
     * @return
     */
    @RequestMapping(value = "/AddOptions", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加自定义下拉框下拉选项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuParamTypeId", value = "学生自定义属性类型ID", required = true),
            @ApiImplicitParam(name = "DropDownOptions", value = "下拉选项名称", required = true),
    })
    public AjaxJson AddOptions(HttpServletRequest request, long stuParamTypeId, String DropDownOptions) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("DropDownOptions", DropDownOptions);
        queryWrapper.eq("StuParamTypeId", stuParamTypeId);
        List<Pxdropdownoptionstable> pxdropdownoptionstableList = iPxdropdownoptionstableService.list(queryWrapper);
        if (pxdropdownoptionstableList.size() > 0) {
            ajaxJson.setMsg("已存在该信息");
        } else {
            Pxdropdownoptionstable pxdropdownoptionstable = new Pxdropdownoptionstable();
            pxdropdownoptionstable.setQiyeID(loginUser.getQiyeID());
            pxdropdownoptionstable.setDropDownOptions(DropDownOptions);
            pxdropdownoptionstable.setStuParamTypeId(stuParamTypeId);
            pxdropdownoptionstable.setIsShow(1);
            ajaxJson.setSuccess(iPxdropdownoptionstableService.save(pxdropdownoptionstable));
        }
        return ajaxJson;
    }

    /**
     * 修改自定义下拉款的下拉选项
     *
     * @param request
     * @param id
     * @param DropDownOptions
     * @return
     */
    @RequestMapping(value = "/EditOptions", method = RequestMethod.POST)
    @ApiOperation(value = "修改自定义下拉框的下拉选项")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "修改数据对应ID", required = true),
            @ApiImplicitParam(name = "stuParamTypeId", value = "学生自定义属性类型ID", required = true),
            @ApiImplicitParam(name = "DropDownOptions", value = "下拉选项名称", required = true),
    })
    public AjaxJson EditOptions(HttpServletRequest request, long id, String DropDownOptions) {
        AjaxJson ajaxJson = new AjaxJson();
        Pxdropdownoptionstable pxdropdownoptionstable = iPxdropdownoptionstableService.getById(id);
        if (pxdropdownoptionstable == null) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("未找到需要修改的数据");
        } else {
            pxdropdownoptionstable.setDropDownOptions(DropDownOptions);
            ajaxJson.setSuccess(iPxdropdownoptionstableService.updateById(pxdropdownoptionstable));
        }
        return ajaxJson;
    }

    /**
     * 删除自定义下拉款的下拉选线
     *
     * @param optionID
     * @return
     */
    @RequestMapping(value = "/DeleteOptions", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除自定义下拉款的下拉选项")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "optionID", value = "删除数据ID", required = true),
    })
    public AjaxJson DeleteOptions(String optionID) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(iPxdropdownoptionstableService.removeById(optionID));
        return ajaxJson;
    }
    //endregion

    //region 学员招生途径设置

    /**
     * 查询学员招生途径
     *
     * @param size
     * @param current
     * @return
     */
    @RequestMapping(value = "/GetPagesyxtelfromPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询学员招生途径")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
    })
    public AjaxJson GetPagesyxtelfromPages(long size, long current, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        String dlstaffName = loginUser.getStaffName();
        Page<Pxyxtelfromtable> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", qiyeID);
        page = (Page<Pxyxtelfromtable>) iPxyxtelfromtableService.page(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 添加学员招生途径
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/Addyxtelfrom", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加学员招生途径")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "telFromName", value = "招生途径名称", required = true),
            @ApiImplicitParam(name = "beizhu", value = "备注信息", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = false),
    })
    public AjaxJson Addyxtelfrom(HttpServletRequest request, @RequestBody yxtelFrom form) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = (loginUser.getStaffID());
        String dlstaffName = loginUser.getStaffName();

        queryWrapper.eq("TelFromName", form.getTelFromName());
        queryWrapper.eq("QiyeID", qiyeID);
        List<Pxyxtelfromtable> pxbuxistyletableList = iPxyxtelfromtableService.list(queryWrapper);
        if (pxbuxistyletableList.size() > 0) {
            ajaxJson.setMsg("当前系统已存在该信息，不能重复添加");
        } else {
            Pxyxtelfromtable pxyxtelfromtable = new Pxyxtelfromtable();
            pxyxtelfromtable.setAddTime(new Date());
            pxyxtelfromtable.setBeizhu(form.getBeizhu());
            pxyxtelfromtable.setAddStaffID(staffID);
            pxyxtelfromtable.setQiyeID(qiyeID);
            pxyxtelfromtable.setTelFromName(form.getTelFromName());
            ajaxJson.setSuccess(iPxyxtelfromtableService.save(pxyxtelfromtable));
        }
        return ajaxJson;
    }

    /**
     * 更新学员招生途径
     *
     * @return
     */
    @RequestMapping(value = "/Updateyxtelfrom", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "更新学员招生途径")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "数据ID", required = true),
            @ApiImplicitParam(name = "telFromName", value = "招生途径名称", required = true),
            @ApiImplicitParam(name = "beizhu", value = "备注信息", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = false),
    })
    public AjaxJson Updateyxtelfrom(HttpServletRequest request, @RequestBody yxtelFrom form) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        List<Pxyxtelfromtable> list = iPxyxtelfromtableService.list(new QueryWrapper<Pxyxtelfromtable>()
                .eq("telFromName", form.getTelFromName())
                .eq("qiyeID", loginUser.getQiyeID())
                .ne("id", form.getId())
        );
        if (list.size() > 0) {
            ajaxJson.setMsg("已有该招生途径");
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        Pxyxtelfromtable one = iPxyxtelfromtableService.getById(form.getId());
        one
                .setBeizhu(form.getBeizhu())
                .setTelFromName(form.getTelFromName());
        ajaxJson.setSuccess(iPxyxtelfromtableService.updateById(one));
        return ajaxJson;
    }

    /**
     * 删除学员招生途径
     *
     * @param Id
     * @return
     */
    @RequestMapping(value = "/Deleteyxtelfrom", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除学员招生途径")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Id", value = "删除数据ID", required = true),
    })
    public AjaxJson Deleteyxtelfrom(String Id) {
        AjaxJson ajaxJson = new AjaxJson();
        List<Pxstutable> pxstutableList = iPxyxtelfromtableService.GetstuBytelFromID(Id);
        if (pxstutableList.size() > 0) {
            ajaxJson.setMsg("该数据已被关联使用，不能删除");
        } else {
            ajaxJson.setSuccess(iPxyxtelfromtableService.removeById(Id));

        }
        return ajaxJson;
    }
    //endregion

    //region 意向程度设置

    /**
     * 意向程度
     *
     * @return
     */
    @RequestMapping(value = "/GetAllYixiangchengduPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页查询意向程度")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
    })
    public AjaxJson GetAllYixiangchengduPages(long size, long current, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<Pxyxtelleveltable> page = new Page(current, size);
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        String dlstaffName = loginUser.getStaffName();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", qiyeID);
        page = (Page<Pxyxtelleveltable>) iPxyxtelleveltableService.page(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 新增意向程度
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/AddYixiangchengdu", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "新增意向程度")
    public AjaxJson AddYixiangchengdu(@RequestBody yixianchengduForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = (loginUser.getStaffID());
        String dlstaffName = loginUser.getStaffName();

        queryWrapper.eq("telLevelName", form.getTelLevelName());
        queryWrapper.eq("qiyeID", qiyeID);
        List<Pxyxtelleveltable> pxyxtelleveltableList = iPxyxtelleveltableService.list(queryWrapper);
        if (pxyxtelleveltableList.size() > 0) {
            ajaxJson.setMsg("当前系统已存在该信息，不能重复添加");
        } else {
            Pxyxtelleveltable pxyxtelleveltable = new Pxyxtelleveltable();
            pxyxtelleveltable.setAddStaffID(staffID);
            pxyxtelleveltable.setAddTime(new Date());
            pxyxtelleveltable.setQiyeID(qiyeID);
            pxyxtelleveltable.setBeizhu(form.getBeizhu());
            pxyxtelleveltable.setTelLevelName(form.getTelLevelName());
            ajaxJson.setSuccess(iPxyxtelleveltableService.save(pxyxtelleveltable));
        }
        return ajaxJson;
    }

    /**
     * 更新意向程度
     *
     * @param pxyxtelleveltable
     * @return
     */
    @RequestMapping(value = "/UpdateYixiangchengdu", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "更新意向程度")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "修改数据ID", required = true),
            @ApiImplicitParam(name = "telLevelName", value = "意向程度", required = true),
            @ApiImplicitParam(name = "beizhu", value = "备注信息", required = true),
            @ApiImplicitParam(name = "addStaffID", value = "添加人", required = false),
            @ApiImplicitParam(name = "addTime", value = "添加时间", required = false),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = false),
    })
    public AjaxJson UpdateYixiangchengdu(@RequestBody Pxyxtelleveltable pxyxtelleveltable) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(iPxyxtelleveltableService.updateById(pxyxtelleveltable));
        return ajaxJson;
    }

    /**
     * 删除意向程度
     *
     * @param Id
     * @return
     */
    @RequestMapping(value = "/DeleteYixiangchengdu", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除意向程度")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Id", value = "删除数据ID", required = true)
    })
    public AjaxJson DeleteYixiangchengdu(String Id) {
        AjaxJson ajaxJson = new AjaxJson();
        List<Pxstutable> pxstutableList = iPxyxtelfromtableService.GetstuBytelFromID(Id);
        if (pxstutableList.size() > 0) {
            ajaxJson.setMsg("该数据已存在关联，不能删除！");
        } else {
            iPxyxtelleveltableService.removeById(Id);
            ajaxJson.setMsg("意向程度删除成功！");
        }
        return ajaxJson;
    }
    //endregion

    //region 收费方式设置

    /**
     * 设置支付方式
     *
     * @param size
     * @param current
     * @return
     */
    @RequestMapping(value = "/getAllPaymoneyStylePages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询所有支付方式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true)
    })
    public AjaxJson getAllPaymoneyStylePages(long size, long current, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", qiyeID);
        Page<Pxpaymoneystyletable> page = new Page(current, size);
        page = (Page<Pxpaymoneystyletable>) iPxpaymoneystyletableService.page(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 新增支付方式
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/addPayMoneyStyle", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "新增支付方式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "moneystyleName", value = "支付方式名称", required = true),
    })
    public AjaxJson addPayMoneyStyle(@RequestBody addPayMoneyStyleForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("moneystyleName",form.getMoneystyleName());
        queryWrapper.eq("qiyeID", qiyeID);
        List<Pxpaymoneystyletable> pxpaymoneystyletableList = iPxpaymoneystyletableService.list(queryWrapper);
        if (pxpaymoneystyletableList.size() > 0) {
            ajaxJson.setMsg("系统已存在该收费方式");
        } else {
            Pxpaymoneystyletable pxpaymoneystyletable = new Pxpaymoneystyletable();
            pxpaymoneystyletable.setQiyeID(qiyeID);
            pxpaymoneystyletable.setMoneystyleName(form.getMoneystyleName());
            ajaxJson.setSuccess(iPxpaymoneystyletableService.save(pxpaymoneystyletable));
        }
        return ajaxJson;
    }

    /**
     * 修改支付方式
     *
     * @param pxpaymoneystyletable
     * @return
     */
    @RequestMapping(value = "/editPayMoneyStyle", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改支付方式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "修改数据ID", required = true),
            @ApiImplicitParam(name = "moneystyleName", value = "支付方式名称", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson editPayMoneyStyle(@RequestBody Pxpaymoneystyletable pxpaymoneystyletable) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(iPxpaymoneystyletableService.updateById(pxpaymoneystyletable));
        return ajaxJson;
    }

    /**
     * 删除支付方式
     *
     * @param Id
     * @return
     */
    @RequestMapping(value = "/deletePayMoneyStyle", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除支付方式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Id", value = "删除数据ID", required = true)
    })
    public AjaxJson deletePayMoneyStyle(String Id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(iPxpaymoneystyletableService.removeById(Id));
        return ajaxJson;
    }
    //endregion

    //region 最低收费标准设置

    /**
     * 分页查询收费标准信息
     *
     * @param size
     * @param current
     * @return
     */
    @RequestMapping(value = "/GetAllShoufeibiaozhunPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页查询收费标准信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuGradeName", value = "年级", required = false),
            @ApiImplicitParam(name = "buxiStyleName", value = "补习方式", required = false),
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true)
    })
    public AjaxJson GetAllShoufeibiaozhunPages(String buxiStyleName, String stuGradeName, long size, long current, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Page<minimumchargeVo> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", qiyeID);
        if (StringUtils.isNotBlank(buxiStyleName)) {
            queryWrapper.eq("buxiStyleName", buxiStyleName);
        }
        if (StringUtils.isNotBlank(stuGradeName)) {
            queryWrapper.eq("stuGradeName", stuGradeName);
        }
        ajaxJson.setObj(iPxminimumchargetableService.GetShoufeiBiaozhunPages(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * 新增收费标准
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/addShoufeiniaozhun", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "新增收费标准")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "buxiStyleId", value = "补习方式ID", required = true),
            @ApiImplicitParam(name = "stuGradeId", value = "年级/年龄段ID", required = true),
            @ApiImplicitParam(name = "minimumCharge", value = "最低课时费用", required = true),
    })
    public AjaxJson addShoufeiniaozhun(@RequestBody addShoufeiniaozhunForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = (Long) loginUser.getStaffID();
        Pxminimumchargetable pxminimumchargetable = new Pxminimumchargetable();
        pxminimumchargetable.setAddStaffID(staffID);
        pxminimumchargetable.setAddTime(new Date());
        pxminimumchargetable.setQiyeID(qiyeID);
        pxminimumchargetable.setBuxiStyleId(form.getBuxiStyleId());
        pxminimumchargetable.setMinimumCharge(form.getMinimumCharge());
        pxminimumchargetable.setStuGradeId(form.getStuGradeId());
        ajaxJson.setSuccess(iPxminimumchargetableService.save(pxminimumchargetable));
        return ajaxJson;
    }

    /**
     * 修改最低收费标准
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/updateShoufeibiaozhun", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改最低收费标准")
    public AjaxJson updateShoufeibiaozhun(@RequestBody shoufeibiaozhunForm form,
                                          HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Pxminimumchargetable one = iPxminimumchargetableService.getById(form.getId());
        one.setBuxiStyleId(Long.valueOf(form.getBuxiStyleId()));
        one.setStuGradeId(Long.valueOf(form.getStuGradeId()));
        one.setMinimumCharge(form.getMinimumCharge());
        ajaxJson.setSuccess(iPxminimumchargetableService.updateById(one));
        return ajaxJson;


    }

    /**
     * 查询对应的最低收费标准信息
     *
     * @param Id
     * @return
     */
    @RequestMapping(value = "/GetShouFeibiaozhunByID", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询对应的最低收费标准信息")
    public AjaxJson GetShouFeibiaozhunByID(long Id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxminimumchargetableService.getById(Id));
        return ajaxJson;
    }

    /**
     * 删除最低收费标准
     *
     * @param Id
     * @return
     */
    @RequestMapping(value = "/deleteShoufeibiaozhun", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除最低收费标准")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Id", name = "删除数据ID", required = true)
    })
    public AjaxJson deleteShoufeibiaozhun(long Id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(iPxminimumchargetableService.removeById(Id));
        return ajaxJson;
    }

    @RequestMapping(value = "/GetAllStuGradeList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询所有的年级年龄段")
    public AjaxJson GetAllStuGradeList(HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", qiyeID);
        List<Pxstugradetable> pxstugradetableList = iPxstugradetableService.list(queryWrapper);
        ajaxJson.setObj(pxstugradetableList);
        return ajaxJson;
    }

    @RequestMapping(value = "/GetAllbuxistyleList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询所有的培训方式")
    public AjaxJson GetAllbuxistyleList(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", qiyeID);
        List<Pxbuxistyletable> pxbuxistyletableList = iPxbuxistyletableService.list(queryWrapper);
        ajaxJson.setObj(pxbuxistyletableList);
        return ajaxJson;

    }
    //endregion

    //region 财务流水类别设置

    /**
     * 查询所有流水类别
     *
     * @param size
     * @param current
     * @return
     */
    @RequestMapping(value = "/getAllShouzhistylePages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取所有流水类别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true)
    })
    public AjaxJson getAllShouzhistylePages(long size, long current, String leibie, int type, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();

        Page<Pxshouzhistyletable> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", qiyeID);

        if (StringUtils.isNotBlank(leibie)) {
            queryWrapper.like("shouzhiStyle", leibie);
        }
        if (type != -1) {
            queryWrapper.eq("isshouOrzhichu", type);
        }
        page = (Page<Pxshouzhistyletable>) iPxshouzhistyletableService.page(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 添加流水类别信息
     * @param request
     * @param form
     * @return
     */
    @RequestMapping(value = "/addShouzhiStyle", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加流水类别信息")
    public AjaxJson addShouzhiStyle(HttpServletRequest request, @RequestBody addShouzhiStyleForm form) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        long staffID = loginUser.getStaffID();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("ShouzhiStyle",form.getShouzhiStyle());
        queryWrapper.eq("QiyeID", qiyeID);
        List<Pxpaymoneystyletable> pxpaymoneystyletableList = iPxshouzhistyletableService.list(queryWrapper);
        if (pxpaymoneystyletableList.size() > 0) {
            ajaxJson.setMsg("系统已存在该流水类别");
        } else {
            Pxshouzhistyletable pxshouzhistyletable = new Pxshouzhistyletable();
            pxshouzhistyletable.setShouzhiStyle(form.getShouzhiStyle());
            pxshouzhistyletable.setQiyeID(qiyeID);
            pxshouzhistyletable.setBeizhu(form.getBeizhu());
            pxshouzhistyletable.setLurudate(new Date());
            pxshouzhistyletable.setStaffID(staffID);
            pxshouzhistyletable.setIsshouOrzhichu(form.getIsshouOrzhichu());
            ajaxJson.setSuccess(iPxshouzhistyletableService.save(pxshouzhistyletable));
        }
        return ajaxJson;
    }

    /**
     * 修改流水类别
     *
     * @return
     */
    @RequestMapping(value = "/editShouzhistyle", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改流水类别")
    public AjaxJson editShouzhistyle(@RequestBody editShouzhistyleForm form) {
        AjaxJson ajaxJson = new AjaxJson();
        Pxshouzhistyletable one = iPxshouzhistyletableService.getById(form.getId());
        one.
                setShouzhiStyle(form.getShouzhiStyle())
                .setIsshouOrzhichu(form.getIsshouOrzhichu())
                .setBeizhu(form.getBeizhu());
        iPxshouzhistyletableService.updateById(one);

        return ajaxJson;
    }

    /**
     * 删除流水类别
     *
     * @param Id
     * @return
     */
    @RequestMapping(value = "/deleteShouzhistyle", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除流水类别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Id", value = "删除数据的ID", required = true)
    })
    public AjaxJson deleteShouzhistyle(String Id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(iPxshouzhistyletableService.removeById(Id));
        return ajaxJson;
    }

    @RequestMapping(value = "/getshouzhistyleById", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询收支方式详情")
    public AjaxJson getshouzhistyleById(long Id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxshouzhistyletableService.getById(Id));
        return ajaxJson;
    }
    //endregion

    //region 校区信息

    /**
     * 分页查询校区信息
     *
     * @param size
     * @param current
     * @return
     */
    @RequestMapping(value = "/GetAllCampusInfoPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("分页查询校区信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true)
    })
    public AjaxJson GetAllCampusInfoPages(long size, long current, String campusName, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        String dlstaffName = loginUser.getStaffName();

        Page<Pxcampustable> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", qiyeID);
        if (StringUtils.isNotBlank(campusName)) {
            queryWrapper.eq("campusName", campusName);
        }
        page = (Page<Pxcampustable>) iPxcampustableService.page(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 保存修改校区信息
     *
     * @param pxcampustable
     * @return
     */
    @RequestMapping(value = "/EditCampusInfo", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("保存修改校区信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "校区ID", required = true),
            @ApiImplicitParam(name = "campusName", value = "校区名字", required = true),
            @ApiImplicitParam(name = "campusAddress", value = "校区地址", required = true),
            @ApiImplicitParam(name = "campusTel", value = "校区联系电话", required = true),
            @ApiImplicitParam(name = "QRcodePrint", value = "小票上打印显示的二维码", required = true),
            @ApiImplicitParam(name = "QRcodeWx", value = "微信公众号二维码", required = true),
            @ApiImplicitParam(name = "wxjiazhangADimg", value = "微信家长端广告图banner", required = true),
            @ApiImplicitParam(name = "wxjiazhangIsShowShoplink", value = "微信学员端是否显示商城链接", required = true),
            @ApiImplicitParam(name = "wxjiazhangShoplinkImg", value = "学员微信端商城链接图片", required = true),
    })
    public AjaxJson EditCampusInfo(Pxcampustable pxcampustable) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(iPxcampustableService.updateById(pxcampustable));
        return ajaxJson;
    }

    @RequestMapping(value = "/EditCampusgongzhonghaoSetting", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "编辑校区公众号配置信息")
    public AjaxJson EditCampusgongzhonghaoSetting(String campusID, String AppID, String AppSecret) {
        AjaxJson ajaxJson = new AjaxJson();
        Pxcampustable pxcampustable = iPxcampustableService.getById(campusID);
        pxcampustable.setAppID(AppID);
        pxcampustable.setAppSecret(AppSecret);
        ajaxJson.setSuccess(iPxcampustableService.updateById(pxcampustable));
        return ajaxJson;
    }

    @RequestMapping(value = "/EditCampusShanghuInfo", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改校区商户号信息")
    public AjaxJson EditCampusShanghuInfo(String campusID, String wxShanghuID, String wxShanghuKey) {
        AjaxJson ajaxJson = new AjaxJson();
        Pxcampustable pxcampustable = iPxcampustableService.getById(campusID);
        pxcampustable.setWxShanghuID(wxShanghuID);
        pxcampustable.setWxShanghuKey(wxShanghuKey);
        ajaxJson.setSuccess(iPxcampustableService.updateById(pxcampustable));
        return ajaxJson;
    }

    @RequestMapping(value = "/updateCampusNameByCampusID", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "修改校区名称")
    public AjaxJson updateCampusNameByCampusID(String campusID, String campusName) {
        AjaxJson ajaxJson = new AjaxJson();
        Pxcampustable pxcampustable = iPxcampustableService.getById(campusID);
        pxcampustable.setCampusName(campusName);
        ajaxJson.setSuccess(iPxcampustableService.updateById(pxcampustable));
        return ajaxJson;
    }

    @RequestMapping(value = "/GetCampusInfoByCampusID", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询校区详细信息")
    public AjaxJson GetCampusInfoByCampusID(String CampusID) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxcampustableService.getById(CampusID));
        return ajaxJson;
    }
    //endregion

    //region 更改系统设置（更改配置值）

    /**
     * 修改配置信息
     * @param form
     * @param request
     * @return
     */
    @RequestMapping(value = "/UpdateSysParamValue", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改配置信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "修改数据的ID", required = true),
            @ApiImplicitParam(name = "value", value = "配置值", required = true)
    })
    public AjaxJson UpdateSysParamValue(@RequestBody SysParamValueForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
            Long qiyeID = loginUser.getQiyeID();
            Long staffID = loginUser.getStaffID();
            String dlstaffName = loginUser.getStaffName();
            Long campusID = loginUser.getCampusID();
            Pxsysparamdefaulttable pxsysparamdefaulttable = iPxsysparamdefaulttableService.getById(form.getId());
            Pxsysparamvaluetable pxsysparamvaluetable = iPxsysparamvaluetableService.GetPxsysparamvalueByQiyeIDAndValueID(qiyeID,
                    pxsysparamdefaulttable.getId());
            if (pxsysparamvaluetable != null) {
                pxsysparamvaluetable.setModifyValue(form.getValue());
                ajaxJson.setSuccess(iPxsysparamvaluetableService.updateById(pxsysparamvaluetable));
            } else {
                Pxsysparamvaluetable newvalue = new Pxsysparamvaluetable();
                newvalue.setQiyeID(qiyeID);
                newvalue.setModifyValue(form.getValue());
                newvalue.setSysparamTypeID(pxsysparamdefaulttable.getId());
                ajaxJson.setSuccess(iPxsysparamvaluetableService.save(newvalue));
            }
            return ajaxJson;
        } catch (Exception e) {
            ajaxJson.setMsg("设置失败");
            return ajaxJson;
        }
    }

    @RequestMapping(value = "/GetSystemParamsById", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "根据ID获取配置值")
    public AjaxJson GetSystemParamsById(Long paramsID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        JSONObject jsonObject = new JSONObject();
        Pxsysparamvaluetable pxsysparamvaluetable = iPxsysparamvaluetableService.GetPxsysparamvalueByQiyeIDAndValueID(qiyeID, paramsID);
        if (pxsysparamvaluetable == null) {
            Pxsysparamdefaulttable defaultvalue = iPxsysparamdefaulttableService.getById(paramsID);
            jsonObject.put("modifyValue", defaultvalue.getDefaultValue());
        } else {
            jsonObject.put("modifyValue", pxsysparamvaluetable.getModifyValue());
        }

        ajaxJson.setObj(jsonObject);
        return ajaxJson;
    }

    @RequestMapping(value = "/GetSanjiFanyongInfo", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询三级返佣信息")
    public AjaxJson GetSanjiFanyongInfo(HttpServletRequest request, Long paramsID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Pxsysparamvaluetable pxsysparamvaluetable = iPxsysparamvaluetableService.GetPxsysparamvalueByQiyeIDAndValueID(qiyeID, paramsID);
        if (pxsysparamvaluetable == null) {
            ajaxJson.setObj(iPxsysparamdefaulttableService.getById(110));
        } else {
            ajaxJson.setObj(pxsysparamvaluetable);
        }
        return ajaxJson;

    }

    /**
     * 查询所有的微信推送开关信息
     *
     * @return
     */
    @RequestMapping(value = "/GetAllTsKaiGuanList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询所有的微信推送开关信息")
    public AjaxJson GetAllTsKaiGuanList() {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        List<Pxtskaiguandefaulttable> pxtskaiguandefaulttableList = iPxtskaiguandefaulttableService.list(queryWrapper);
        ajaxJson.setObj(pxtskaiguandefaulttableList);
        return ajaxJson;
    }

    /**
     * 修改微信推送开关
     *
     * @param value
     * @param Id
     * @return
     */
    @RequestMapping(value = "/UpdateTuisongKaiGuan", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改微信推送开关")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "value", value = "修改的值", required = true),
            @ApiImplicitParam(name = "Id", value = "修改数据ID", required = true)
    })
    public AjaxJson UpdateTuisongKaiGuan(String value, Long Id, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        String dlstaffName = loginUser.getStaffName();
        Long campusID = loginUser.getCampusID();
        Pxtskaiguanvaluetable pxtskaiguanvaluetable1 = iPxtskaiguanvaluetableService.GetPxtsKaiguanvalueById(Id, qiyeID);
        if (pxtskaiguanvaluetable1 != null) {
            pxtskaiguanvaluetable1.setValue(value);
            ajaxJson.setSuccess(iPxtskaiguanvaluetableService.updateById(pxtskaiguanvaluetable1));
            return ajaxJson;
        } else {
            Pxtskaiguanvaluetable pxtskaiguanvaluetable = new Pxtskaiguanvaluetable();
            pxtskaiguanvaluetable.setQiyeID(qiyeID);
            pxtskaiguanvaluetable.setTSTypeID(Id);
            pxtskaiguanvaluetable.setValue(value);
            ajaxJson.setSuccess(iPxtskaiguanvaluetableService.save(pxtskaiguanvaluetable));
            return ajaxJson;
        }
    }

    @RequestMapping(value = "/GetAlltsKaiguangValue", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询所有微信推送开关的值")
    public AjaxJson GetAlltsKaiguangValue(HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", qiyeID);
        List<Pxtskaiguanvaluetable> pxtskaiguanvaluetableList = iPxtskaiguanvaluetableService.list(queryWrapper);
        ajaxJson.setObj(pxtskaiguanvaluetableList);
        return ajaxJson;
    }
    //endregion

    //region 考试类别设置
    @RequestMapping(value = "/getKaoshiLeibie", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页查询考试类别")
    public AjaxJson getKaoshiLeibie(long size, long current, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());
        AjaxJson ajaxJson = new AjaxJson();
        Page<Pxtesttypetable> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", qiyeID);
        page = (Page<Pxtesttypetable>) iPxtesttypetableService.page(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/SaveKaoshileibie", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加保存考试类别")
    public AjaxJson SaveKaoshileibie(@RequestBody SaveKaoshileibieForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());
        if (StringUtils.isNotBlank(form.getId())) {
            Pxtesttypetable one = iPxtesttypetableService.getById(form.getId());
            one.setTestType(form.getTesttype());
            iPxtesttypetableService.updateById(one);
        } else {
            Pxtesttypetable pxtesttypetable = new Pxtesttypetable();
            pxtesttypetable.setQiyeID(qiyeID);
            pxtesttypetable.setTestType(form.getTesttype());
            ajaxJson.setSuccess(iPxtesttypetableService.save(pxtesttypetable));
        }

        return ajaxJson;
    }

    @RequestMapping(value = "/DeleteKaoshileibie", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除考试类别")
    public AjaxJson DeleteKaoshileibie(Long Id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(iPxtesttypetableService.removeById(Id));
        return ajaxJson;
    }
    //endregion

    @RequestMapping(value = "/GetAllStaffList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询所有员工信息")
    public AjaxJson GetAllStaffList() {
        AjaxJson ajaxJson = new AjaxJson();
        List<Pxstafftable> pxstafftableList = iPxstafftableService.list();
        ajaxJson.setObj(pxstafftableList);
        return ajaxJson;
    }

    //region 杂费自定义字段
    @RequestMapping(value = "/getAllqiandanotherMoneyPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页获取杂费自定义字段")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true)
    })
    public AjaxJson getAllqiandanotherMoneyPages(long size, long current, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Page<Pxqiandanothermoneytable> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", qiyeID);
        page = (Page<Pxqiandanothermoneytable>) iPxqiandanothermoneytableService.page(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/addqiandanotherMoney", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加签单其他费用信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "othermoneyname", value = "费用名称", required = true),
    })
    public AjaxJson addqiandanotherMoney(@RequestBody addqiandanotherMoneyForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("othermoneyname",form.getOthermoneyname());
        queryWrapper.eq("QiyeID", qiyeID);
        List<Pxqiandanothermoneytable> pxqiandanothermoneytableList = iPxqiandanothermoneytableService.list(queryWrapper);
        if (pxqiandanothermoneytableList.size() > 0) {
            ajaxJson.setMsg("系统已存在收费类别");
        } else {
            Pxqiandanothermoneytable pxqiandanothermoneytable = new Pxqiandanothermoneytable();
            pxqiandanothermoneytable.setQiyeID(qiyeID);
            pxqiandanothermoneytable.setOthermoneyname(form.getOthermoneyname());
            ajaxJson.setSuccess(iPxqiandanothermoneytableService.save(pxqiandanothermoneytable));
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/editQiandanOtherMoney", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改签单其他费用信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "修改数据ID", required = true),
            @ApiImplicitParam(name = "othermoneyname", value = "费用名称", required = true),
    })
    public AjaxJson editQiandanOtherMoney(@RequestBody Pxqiandanothermoneytable pxqiandanothermoneytable) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(iPxqiandanothermoneytableService.updateById(pxqiandanothermoneytable));
        return ajaxJson;
    }

    @RequestMapping(value = "/deleteQiandanOtherMoney", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除签单其他费用信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Id", value = "删除数据的ID", required = true)
    })
    public AjaxJson deleteQiandanOtherMoney(String Id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(iPxqiandanothermoneytableService.removeById(Id));
        return ajaxJson;
    }

    //endregion

    //region 设置机构简介

    @ResponseBody
    @RequestMapping(value = "/savejigoujianjie", method = RequestMethod.POST)
    @ApiOperation(value = "保存机构简介信息")
    public AjaxJson savejigoujianjie(HttpServletRequest request, @RequestBody jianjieForm form) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        OaKehu oaKehu = iOaKehuService.getById(loginUser.getQiyeID());
        oaKehu.setSitejigouintroduce(form.getJianjiecontent());
        ajaxJson.setSuccess(iOaKehuService.updateById(oaKehu));
        return ajaxJson;
    }
    //endregion
}
