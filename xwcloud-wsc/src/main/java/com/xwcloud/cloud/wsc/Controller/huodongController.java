package com.xwcloud.cloud.wsc.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.*;
import com.xwcloud.cloud.wsc.Service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/samllprogram/huodong")
@Api(tags = "微信小程序端商城活动信息")
public class huodongController {
    @Autowired
    IWscGoodsService iWscGoodsService;
    @Autowired
    IWscHuodongService iWscHuodongService;

    @Autowired
    IWscPingtuanHuodongService iWscPingtuanHuodongService;

    @Autowired
    IWscPingtuanFaqirecordService iWscPingtuanFaqirecordService;

    @Autowired
    IWscPingtuanJoinrecordService iWscPingtuanJoinrecordService;

    @Autowired
    IWscKanjiaHuodonginfoService iWscKanjiaHuodonginfoService;

    @Autowired
    IWscKanjiaFaqirecordService iWscKanjiaFaqirecordService;

    @Autowired
    IWscKanjiaBangkanrecordService iWscKanjiaBangkanrecordService;

    @Autowired
    IWscGoodsshuxinglistpriceService iWscGoodsshuxinglistService;

    @Autowired
    IWscMiaoshaHuodonginfoService iWscMiaoshaHuodonginfoService;

    @Autowired
    IWscOrderService iWscOrderService;

    @Autowired
    IWhdJizanHuodongService iWhdJizanHuodongService;

    @Autowired
    IWhdJizanFaqimyjizanService iWhdJizanFaqimyjizanService;

    @Autowired
    IWhdJizanHelpjizanService iWhdJizanHelpjizanService;

    @Autowired
    IWhdChoujiangHuodongService iWhdChoujiangHuodongService;

    @Autowired
    IWhdToupiaoHuodongService iWhdToupiaoHuodongService;

    @Autowired
    IWscHuodongOthersService iWscHuodongOthersService;

    @Autowired
    IWhdH5HuodongfabuService iWhdH5HuodongfabuService;

    @Autowired
    private IOaKehuService iOaKehuService;

    @Autowired
    IPxsysparamvaluetableService iPxsysparamvaluetableService;
    @Autowired
    IPxstutableService iPxstutableService;

    @Autowired
    IWhdH5MbschoolJigoujianjieService iWhdH5MbschoolJigoujianjieService;
    @Autowired
    IHuodongJubaoService iHuodongJubaoService;


    @PostMapping("savehuodongliuyan")
    @ResponseBody
    @ApiOperation("保存营销活动留言")
    public AjaxJson savehuodongliuyan(HttpServletRequest request, String yxuserName, String tel, String liuyan) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Pxstutable one = new Pxstutable();
        one
                .setStuName(yxuserName)
                .setPasswd(new BCryptPasswordEncoder().encode("123456"))
                .setParentTel(tel)
                .setParentTelRelation("9")
                .setBuxiStateID(1)
                .setStuGradeID(0L)
                .setCampusID(0L)
                .setQiyeID(loginUser.getQiyeID())
                .setRemainXuefei(BigDecimal.valueOf(0))
                .setJifenNum(BigDecimal.valueOf(0))
                .setYxFromID(1L)
                .setLuruType(1)
                .setDengjiTeacherID(0L)
                .setStuXuexi(liuyan)
                .setDengjiTime(new Date());
        ajaxJson.setSuccess(iPxstutableService.save(one));

//        yxkh
//                .setDjqRemain(BigDecimal.valueOf(0))
//                .setSmsRemain(0)
//                .setKehuType(1)
//                .setKehucontractname(yxuserName)
//                .setKehuUseState(2)
//                .setKehutelphone(tel)
//                .setYxfromID("4")
//                .setKehuinfobeizhu(liuyan)
//                .setAddStaffID("0")
//                .setAddTime(LocalDateTime.now());
//
//        iOaKehuService.save(yxkh);
        return ajaxJson;
    }


    @ResponseBody
    @ApiOperation(value = "根据活动ID查询活动商品信息")
    @RequestMapping(value = "/GetHuodoongshangpinInfo", method = RequestMethod.GET)
    public AjaxJson GetHuodoongshangpinInfo(HttpServletRequest request, int huodongID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.huodongID", huodongID);
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        ajaxJson.setObj(iWscGoodsService.GetAllGoodsListByHuodongID(queryWrapper));
        return ajaxJson;
    }

    @ResponseBody
    @ApiOperation(value = "查询活动商品详情")
    @RequestMapping(value = "/GetHuodongshangpinDetail", method = RequestMethod.GET)
    public AjaxJson GetHuodongshangpinDetail(HttpServletRequest request, long goodsID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        pingtuandetailVO pingtuanxiangqing = new pingtuandetailVO();
        pingtuanxiangqing.setPtjieti(iWscGoodsService.getshuxinglistpingtuanPricebygoodsID(goodsID));
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.id", goodsID);
        WscGoodsVo wscgoods = iWscGoodsService.GetAllGoodsListByHuodongID(queryWrapper).get(0);
        pingtuanxiangqing.setGoodsName(wscgoods.getGoodsName());
        pingtuanxiangqing.setGoodImg(wscgoods.getImg1());
        pingtuanxiangqing.setBuyPrice(wscgoods.getBasicPrice());
        pingtuanxiangqing.setShijianchuo(wscgoods.getShijianchuo());
        pingtuanxiangqing.setGoodsTypeID(wscgoods.getGoodsTypeID());

        pingtuanxiangqing.setHuodongCount(iWscPingtuanFaqirecordService.GetFaqipingtuanlist(goodsID).size() + iWscPingtuanJoinrecordService.GetpingtuanJoinList(goodsID).size());
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("pingtuanGoodsID", goodsID);
        pingtuanxiangqing.setKaituanshu(iWscPingtuanFaqirecordService.GetFaqipingtuanlist(goodsID).size());
        QueryWrapper queryWrapper2 = new QueryWrapper();
        queryWrapper2.eq("pingtuanGoodsID", goodsID);
        pingtuanxiangqing.setCantuanrenshu(iWscPingtuanJoinrecordService.GetpingtuanJoinList(goodsID).size());
        pingtuanxiangqing.setFaqilist(iWscPingtuanFaqirecordService.GetFaqipingtuanlist(goodsID));
        pingtuanxiangqing.setJoinlist(iWscPingtuanJoinrecordService.GetpingtuanJoinList(goodsID));
        pingtuanxiangqing.setSuccesslist(iWscPingtuanFaqirecordService.GetFaqipingtuanSuccesslist(goodsID));
        ajaxJson.setObj(pingtuanxiangqing);
        return ajaxJson;
    }

    @ResponseBody
    @ApiOperation(value = "查询活动信息")
    @RequestMapping(value = "/getHuodongInfo", method = RequestMethod.GET)
    public AjaxJson getHuodongInfo(HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        List<wschuodongVO> wschuodongVOS = iWscHuodongService.GetAllhuodongList(Long.valueOf(loginUser.getQiyeID()));
        List<huodongVO> fhhuodong = new ArrayList<>();
        for (wschuodongVO item : wschuodongVOS) {
            huodongVO huodong = new huodongVO();
            huodong.setHuodongID(item.getHuodongID());
            huodong.setHuodongName(item.getHuodongName());
            //1：拼团；2：砍价；3：秒杀
            if (item.getHuodongID() == 1 || item.getHuodongID() == 2 || item.getHuodongID() == 3) {
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
                queryWrapper.eq("a.huodongID", item.getHuodongID());
                List<WscGoodsVo> wscGoodsVos = iWscGoodsService.GetAllGoodsListByHuodongID(queryWrapper);
                if (wscGoodsVos.size() > 0) {
                    huodong.setHuodongImg(wscGoodsVos.get(0).getImg1());
                    huodong.setHuodongTitle(wscGoodsVos.get(0).getGoodsName());
                    huodong.setBuySum(wscGoodsVos.get(0).getSales());
                    huodong.setEndDatetime(Date.from(wscGoodsVos.get(0).getHuodongEndTime().atZone(ZoneId.systemDefault()).toInstant()));
                    huodong.setHuodongPrice(wscGoodsVos.get(0).getOnlyTimeBuyPrice());
                    huodong.setOldPrice(wscGoodsVos.get(0).getBasicPrice());
                    huodong.setId(wscGoodsVos.get(0).getId());
                }
                fhhuodong.add(huodong);
            } else if (item.getHuodongID() == 5) {
                //抽奖
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("qiyeID", loginUser.getQiyeID());
                queryWrapper.eq("isUp", 1);
                WhdChoujiangHuodong whdChoujiangHuodong = iWhdChoujiangHuodongService.getOne(queryWrapper);
                if (whdChoujiangHuodong != null) {
                    huodong.setHuodongImg("http://imgs.jxb666.com/457a2ff62b577f40da4c69e009b0d4a4.jpg");
                    huodong.setHuodongTitle(whdChoujiangHuodong.getChoujiangHuodongName());
                    huodong.setId(whdChoujiangHuodong.getId());
                }
                fhhuodong.add(huodong);
            } else if (item.getHuodongID() == 6) {
                //投票
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("isUp", 1);
                queryWrapper.eq("qiyeID", loginUser.getQiyeID());
                List<WhdToupiaoHuodong> whdToupiaoHuodong = iWhdToupiaoHuodongService.list(queryWrapper);
                if (whdToupiaoHuodong.size() > 0) {
                    huodong.setHuodongImg(whdToupiaoHuodong.get(0).getLogo());
                    huodong.setHuodongTitle(whdToupiaoHuodong.get(0).getToupiaoHuodongName());
                    huodong.setId(whdToupiaoHuodong.get(0).getId());
                }
                fhhuodong.add(huodong);
            } else if (item.getHuodongID() == 7) {
                //集赞
                huodong.setHuodongID(item.getHuodongID());
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("qiyeID", loginUser.getQiyeID());
                queryWrapper.eq("isOpen", 1);
                List<WhdJizanHuodong> whdJizanHuodong = iWhdJizanHuodongService.list(queryWrapper);
                if (whdJizanHuodong.size() > 0) {
                    huodong.setHuodongImg(whdJizanHuodong.get(0).getJizanLogoUrl());
                    huodong.setHuodongTitle(whdJizanHuodong.get(0).getJizanHuodongName());
                    huodong.setEndDatetime(whdJizanHuodong.get(0).getEndTime());
                    huodong.setId(whdJizanHuodong.get(0).getId());
                }
                fhhuodong.add(huodong);
            } else if (item.getHuodongID() == 8 || item.getHuodongID() == 9 || item.getHuodongID() == 11 || item.getHuodongID() == 12 || item.getHuodongID() == 13) {
                //节日、邀请、喜报、招聘、开业、店庆
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("isfabu", true);
                queryWrapper.eq("mbTypeID", item.getHuodongID());
                queryWrapper.eq("qiyeID", loginUser.getQiyeID());
                // List<WscHuodongOthers> wscHuodongOthers = iWscHuodongOthersService.list(queryWrapper);
                List<WhdH5Huodongfabu> whdH5Huodongfabus = iWhdH5HuodongfabuService.list(queryWrapper);
                String huodongName = item.getHuodongID() == 8 ? "节日" : item.getHuodongID() == 9 ? "邀请" : item.getHuodongID() == 11 ? "招聘" : item.getHuodongID() == 12 ? "开业" : "店庆";
                if (whdH5Huodongfabus.size() > 0) {
                    huodong.setHuodongImg(whdH5Huodongfabus.get(0).getHuodongImage());
                    huodong.setHuodongID(whdH5Huodongfabus.get(0).getMbTypeID());
                    huodong.setHuodongName(huodongName);
                    huodong.setEndDatetime(whdH5Huodongfabus.get(0).getHuodongEndDateTime());
                    huodong.setHuodongTitle(whdH5Huodongfabus.get(0).getHuodongTitle());
                    huodong.setId(whdH5Huodongfabus.get(0).getId());
                    fhhuodong.add(huodong);
                }
            }
        }
        ajaxJson.setObj(fhhuodong);
        return ajaxJson;
    }

    @ResponseBody
    @ApiOperation(value = "查询拼团活动信息")
    @RequestMapping(value = "/GetPingtuanHuodong", method = RequestMethod.GET)
    public AjaxJson GetPingtuanHuodong(HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        ajaxJson.setObj(iWscPingtuanHuodongService.list(queryWrapper).get(0));
        return ajaxJson;
    }

    //region 砍价活动信息
    @ResponseBody
    @ApiOperation(value = "查询砍价活动详细信息")
    @RequestMapping(value = "/GetKanjiahuoDongDetail", method = RequestMethod.GET)
    public AjaxJson GetKanjiahuoDongDetail(HttpServletRequest request, long goodsID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        kanjiadetailVO kanjiadetailVO = new kanjiadetailVO();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.id", goodsID);
        WscGoodsVo wscgoods = iWscGoodsService.GetAllGoodsListByHuodongID(queryWrapper).get(0);
        kanjiadetailVO.setEnddateTime(Date.from(wscgoods.getHuodongEndTime().atZone(ZoneId.systemDefault()).toInstant()));
        kanjiadetailVO.setGoodImg(wscgoods.getImg1());
        kanjiadetailVO.setGoodsName(wscgoods.getGoodsName());
        kanjiadetailVO.setBuyPrice(wscgoods.getBasicPrice());
        kanjiadetailVO.setGoodsID(wscgoods.getId());
        kanjiadetailVO.setKanjiaOniceMaxNum(wscgoods.getKanjiaOniceMaxNum());
        kanjiadetailVO.setKanjiaOniceMinNum(wscgoods.getKanjiaOniceMinNum());
        kanjiadetailVO.setKanjiaSuccessPrice(wscgoods.getKanjiaSuccessPriceShuxing());
        kanjiadetailVO.setShijianchuo(wscgoods.getShijianchuo());
        kanjiadetailVO.setGoodsTypeID(wscgoods.getGoodsTypeID());
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", loginUser.getQiyeID());
        WscKanjiaHuodonginfo wscKanjiaHuodonginfo = iWscKanjiaHuodonginfoService.getOne(queryWrapper1);
        kanjiadetailVO.setHuodongImg(wscKanjiaHuodonginfo.getHuodongImg());
        kanjiadetailVO.setHuodongTitle(wscKanjiaHuodonginfo.getHuodongTitle());
        kanjiadetailVO.setHuodongshuoming(wscKanjiaHuodonginfo.getHuodongshuoming());
        kanjiadetailVO.setLiulanTimes(wscKanjiaHuodonginfo.getLiulanTimes());
        kanjiadetailVO.setFenxiangTimes(wscKanjiaHuodonginfo.getFenxiangTimes());
        kanjiadetailVO.setFaqicishu(iWscKanjiaFaqirecordService.GetfaqiKanjiaInfoByGoodsID(goodsID).size());
        kanjiadetailVO.setCanyucishu(iWscKanjiaBangkanrecordService.GetBangkanRecords(goodsID).size());
        kanjiadetailVO.setFaqirecords(iWscKanjiaFaqirecordService.GetfaqiKanjiaInfoByGoodsID(goodsID));
        kanjiadetailVO.setKanjiasuccess(iWscKanjiaFaqirecordService.GetfaqiKanjiaSuccessInfoByGoodsID(goodsID));
        kanjiadetailVO.setBangkanList(iWscKanjiaBangkanrecordService.GetBangkanRecords(goodsID));
        kanjiadetailVO.setKanjiaID(wscKanjiaHuodonginfo.getId());
        ajaxJson.setObj(kanjiadetailVO);
        return ajaxJson;
    }

    @ResponseBody
    @RequestMapping(value = "/PanduanyonghufaqiKanjia", method = RequestMethod.GET)
    @ApiOperation(value = "判断当前砍价活动是否已经发起过")
    public AjaxJson PanduanyonghufaqiKanjia(HttpServletRequest request, long goodsID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setObj(iWscKanjiaFaqirecordService.panduanDangqianweixinyonghu(loginUser.getWscUserID(), goodsID));
        return ajaxJson;
    }

    @ResponseBody
    @RequestMapping(value = "/SaveFaqikanjiaInfo", method = RequestMethod.GET)
    @ApiOperation(value = "保存发起砍价信息")
    public AjaxJson SaveFaqikanjiaInfo(HttpServletRequest request, String allguige, String goodID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<WscGoodsshuxinglistprice> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("goodsid", goodID)
                .eq("qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(allguige)) {
            queryWrapper.eq("goodsshuxinglistall", allguige);
        }
        WscGoodsshuxinglistprice wscGoodsshuxinglistprice = iWscGoodsshuxinglistService.getOne(queryWrapper);
        WscKanjiaFaqirecord wscKanjiaFaqirecord = new WscKanjiaFaqirecord();
        wscKanjiaFaqirecord.setKanjiaGoodsID(Long.valueOf(goodID));
        wscKanjiaFaqirecord.setGoodsshuxinglistpriceID(wscGoodsshuxinglistprice.getId());
        wscKanjiaFaqirecord.setKanjiaFaqiRenWxUserID(loginUser.getWscUserID());
        wscKanjiaFaqirecord.setMinMoney(wscGoodsshuxinglistprice.getKanjiaSuccessPrice());
        wscKanjiaFaqirecord.setStartMoney(wscGoodsshuxinglistprice.getPrice());
        wscKanjiaFaqirecord.setCurrentMoney(wscGoodsshuxinglistprice.getPrice());
        wscKanjiaFaqirecord.setAddTime(new Date());
        wscKanjiaFaqirecord.setState(0);
        wscKanjiaFaqirecord.setQiyeID(Long.valueOf(loginUser.getQiyeID()));
        ajaxJson.setSuccess(iWscKanjiaFaqirecordService.save(wscKanjiaFaqirecord));
        return ajaxJson;
    }
    //endregion

    //region 秒杀
    @ResponseBody
    @ApiOperation(value = "查询秒杀活动信息")
    @RequestMapping(value = "/GetMiaoshahuodongInfo", method = RequestMethod.GET)
    public AjaxJson GetMiaoshahuodongInfo(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        ajaxJson.setObj(iWscMiaoshaHuodonginfoService.list(queryWrapper).get(0));
        return ajaxJson;
    }

    @ResponseBody
    @ApiOperation(value = "查询秒杀活动详情")
    @RequestMapping(value = "/GetmiaoshahuodongDetail", method = RequestMethod.GET)
    public AjaxJson GetmiaoshahuodongDetail(HttpServletRequest request, long goodsID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        miaoshahuodongVO miaoshahuodongVO = new miaoshahuodongVO();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.id", goodsID);
        WscGoodsVo wscgoods = iWscGoodsService.GetAllGoodsListByHuodongID(queryWrapper).get(0);
        miaoshahuodongVO.setShijianchuo(wscgoods.getShijianchuo());
        miaoshahuodongVO.setEnddate(Date.from(wscgoods.getHuodongEndTime().atZone(ZoneId.systemDefault()).toInstant()));
        miaoshahuodongVO.setGoodsName(wscgoods.getGoodsName());
        miaoshahuodongVO.setGoodImg(wscgoods.getImg1());
        miaoshahuodongVO.setBuyPrice(wscgoods.getQiangoujia());
        miaoshahuodongVO.setMiaoshalist(iWscOrderService.GetmiaoshachenggongInfo(loginUser.getQiyeID(), goodsID));
        ajaxJson.setObj(miaoshahuodongVO);
        return ajaxJson;
    }
    //endregion

    //region 集赞活动信息
    @ResponseBody
    @ApiOperation(value = "集赞活动列表信息")
    @RequestMapping(value = "/GetjizanhuodongList", method = RequestMethod.GET)
    public AjaxJson GetjizanhuodongList(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("mbTypeID", 7);
        //ajaxJson.setObj(iWhdJizanHuodongService.GetAlljizanList(queryWrapper));
        ajaxJson.setObj(iWhdH5HuodongfabuService.list(queryWrapper));
        return ajaxJson;
    }

    @ResponseBody
    @ApiOperation(value = "查询集赞活动信息")
    @RequestMapping(value = "/GetJizanHuodongDetail", method = RequestMethod.GET)
    public AjaxJson GetJizanHuodongDetail(HttpServletRequest request, long jizanID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        jizandetailVO jizandetailVO = new jizandetailVO();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", jizanID);
        //WhdJizanHuodong whdJizanHuodong = iWhdJizanHuodongService.getById(jizanID);
        WhdH5Huodongfabu whdH5Huodongfabu = iWhdH5HuodongfabuService.getById(jizanID);
        jizandetailVO.setJizanHuodongName(whdH5Huodongfabu.getHuodongTitle());
        jizandetailVO.setEndTime(whdH5Huodongfabu.getHuodongEndDateTime());
        jizandetailVO.setJizanShuoming(whdH5Huodongfabu.getHuodongShuoMing());
        jizandetailVO.setJizanLogoUrl(whdH5Huodongfabu.getHuodongImage());
        jizandetailVO.setAddTime(whdH5Huodongfabu.getAddTime());
        jizandetailVO.setStartTime(whdH5Huodongfabu.getHuodongStartDateTime());
        jizandetailVO.setFaqilist(iWhdJizanFaqimyjizanService.GetjizanFaqiList(jizanID));
        jizandetailVO.setHelpzan(iWhdJizanHelpjizanService.GetHelpdianzanRecordsList(jizanID));
        ajaxJson.setObj(jizandetailVO);
        return ajaxJson;
    }

    /**
     * 查询活动已经发起的集赞信息
     *
     * @param request
     * @param jizanID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/GetFaqiJizanlist", method = RequestMethod.GET)
    public AjaxJson GetFaqiJizanlist(HttpServletRequest request, long jizanID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setObj(iWhdJizanFaqimyjizanService.GetjizanFaqiList(jizanID));
        return ajaxJson;
    }

    /**
     * 查询帮忙点赞信息
     *
     * @param request
     * @param jizanID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/GetHelpJizanList", method = RequestMethod.GET)
    public AjaxJson GetHelpJizanList(HttpServletRequest request, long jizanID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setObj(iWhdJizanHelpjizanService.GetHelpdianzanRecordsList(jizanID));
        return ajaxJson;
    }

    @ResponseBody
    @RequestMapping(value = "/GetjizanghuodongInfo", method = RequestMethod.GET)
    public AjaxJson GetjizanghuodongInfo(HttpServletRequest request, long jizanID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", jizanID);
        ajaxJson.setObj(iWhdH5HuodongfabuService.GetMubanHuodongDetail(queryWrapper));
        return ajaxJson;
    }

    @ResponseBody
    @ApiOperation(value = "查询活动发起人信息")
    @RequestMapping(value = "/SearchfaqirenInfo", method = RequestMethod.GET)
    public AjaxJson SearchfaqirenInfo(HttpServletRequest request, long huodongID, long faqirenID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        if (faqirenID == 0) {
            ajaxJson.setObj(iWhdJizanHuodongService.GetjizanfaqiInfo(huodongID, loginUser.getWscUserID()));
        } else {
            ajaxJson.setObj(iWhdJizanHuodongService.GetjizanfaqiInfo(huodongID, faqirenID));
        }
        return ajaxJson;
    }

    @ResponseBody
    @ApiOperation(value = "保存帮忙集赞信息")
    @RequestMapping(value = "/SavejizanInfo", method = RequestMethod.GET)
    public AjaxJson SavejizanInfo(HttpServletRequest request, long jizanfaqiID, String liuyan) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("whd_jizan_faqimyjizan_id", jizanfaqiID);
        queryWrapper.eq("helpjizanWxUserID", loginUser.getWscUserID());
        List<WhdJizanHelpjizan> helpjizan = iWhdJizanHelpjizanService.list(queryWrapper);
        if (helpjizan.size() == 0) {
            WhdJizanHelpjizan whdJizanHelpjizan = new WhdJizanHelpjizan();
            whdJizanHelpjizan.setHelpjizanTime(new Date());
            whdJizanHelpjizan.setHelpjizanWxUserID(loginUser.getWscUserID());
            whdJizanHelpjizan.setWhdJizanFaqimyjizanId(jizanfaqiID);
            whdJizanHelpjizan.setQiyeID(Long.valueOf(loginUser.getQiyeID()));
            whdJizanHelpjizan.setHelpjizanLiuyan(liuyan);
            ajaxJson.setSuccess(iWhdJizanHelpjizanService.save(whdJizanHelpjizan));
            WhdJizanFaqimyjizan whdJizanFaqimyjizan = iWhdJizanFaqimyjizanService.getById(jizanfaqiID);
            int jzceshu = whdJizanFaqimyjizan.getDianzantimes() + 1;
            whdJizanFaqimyjizan.setDianzantimes(jzceshu);
            iWhdJizanFaqimyjizanService.updateById(whdJizanFaqimyjizan);
        } else {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("您已经帮他集赞过");
        }
        return ajaxJson;
    }

    @ResponseBody
    @ApiOperation(value = "保存发起集赞信息")
    @RequestMapping(value = "/SavefaqiJizanInfo", method = RequestMethod.GET)
    public AjaxJson SavefaqiJizanInfo(HttpServletRequest request, long huodongID, String xuanyan) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("whd_jizan_huodong_id", huodongID);
        queryWrapper.eq("wxUserIDFaqiren", loginUser.getWscUserID());
        List<WhdJizanFaqimyjizan> faqi = iWhdJizanFaqimyjizanService.list(queryWrapper);
        if (faqi.size() == 0) {
            WhdJizanFaqimyjizan whdJizanFaqimyjizan = new WhdJizanFaqimyjizan();
            whdJizanFaqimyjizan.setWhdJizanHuodongId(huodongID);
            whdJizanFaqimyjizan.setDianzantimes(0);
            whdJizanFaqimyjizan.setJizanxuanyan(xuanyan);
            whdJizanFaqimyjizan.setAddTime(new Date());
            whdJizanFaqimyjizan.setQiyeID(Long.valueOf(loginUser.getQiyeID()));
            whdJizanFaqimyjizan.setWxUserIDFaqiren(loginUser.getWscUserID());
            ajaxJson.setSuccess(iWhdJizanFaqimyjizanService.save(whdJizanFaqimyjizan));
        } else {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("您已经发起过该集赞活动");
        }
        return ajaxJson;
    }

    /**
     * 保存集赞活动的浏览次数
     *
     * @param request
     * @param huodongID
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "保存集赞活动的浏览次数")
    @RequestMapping(value = "/SaveAddjizanLiulanTimes", method = RequestMethod.GET)
    public AjaxJson SaveAddjizanLiulanTimes(HttpServletRequest request, long huodongID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        WhdH5Huodongfabu whdH5Huodongfabu = iWhdH5HuodongfabuService.getById(huodongID);
        int liulanTimes = whdH5Huodongfabu.getLookNum() + 1;
        whdH5Huodongfabu.setLookNum(liulanTimes);
        ajaxJson.setObj(iWhdH5HuodongfabuService.updateById(whdH5Huodongfabu));
        return ajaxJson;
    }
    //endregion

    //region 其他活动信息
    @ResponseBody
    @ApiOperation(value = "查询活动信息")
    @RequestMapping(value = "/GetOtherHuodongInfoList", method = RequestMethod.GET)
    public AjaxJson GetOtherHuodongInfoList(HttpServletRequest request, long huodongID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("mbTypeID", huodongID);
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        //queryWrapper.eq("huodongState",1);
        //ajaxJson.setObj(iWscHuodongOthersService.list(queryWrapper));
        ajaxJson.setObj(iWhdH5HuodongfabuService.list(queryWrapper));
        return ajaxJson;
    }

    @ResponseBody
    @ApiOperation(value = "查询活动详情")
    @RequestMapping(value = "/GetHuodongInfo", method = RequestMethod.GET)
    public AjaxJson GetHuodongInfo(HttpServletRequest request, long id) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setObj(iWhdH5HuodongfabuService.getHuodongDetailInfo(loginUser.getQiyeID(), id));
//        ajaxJson.setObj(iWhdH5HuodongfabuService.getById(id));
        return ajaxJson;
    }

    @ResponseBody
    @ApiOperation(value = "保存增加浏览次数信息")
    @RequestMapping(value = "/saveAddliulanTimes", method = RequestMethod.GET)
    public AjaxJson saveAddliulanTimes(HttpServletRequest request, long huodongID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        WscHuodongOthers wscHuodongOthers = iWscHuodongOthersService.getById(huodongID);
        if (wscHuodongOthers != null) {
            wscHuodongOthers.setLiulantimes(wscHuodongOthers.getLiulantimes() + 1);
            ajaxJson.setSuccess(iWscHuodongOthersService.updateById(wscHuodongOthers));
        }
        return ajaxJson;
    }
    //endregion

    @ResponseBody
    @RequestMapping(value = "/GetUserJigouName", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户所属机构名称")
    public AjaxJson GetUserJigouName(HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iOaKehuService.getById(loginUser.getQiyeID()));
        return ajaxJson;
    }

    /**
     * 查询首页或者商城首页轮播图片信息
     *
     * @param request
     * @param typeID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/GetIndexbannerInfo", method = RequestMethod.GET)
    @ApiOperation(value = "查询首页或者商城首页的轮播图信息")
    public AjaxJson GetIndexbannerInfo(HttpServletRequest request, String typeID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("sysparamTypeID", typeID);
        ajaxJson.setObj(iPxsysparamvaluetableService.getOne(queryWrapper));
        return ajaxJson;
    }

    /**
     * 查询活动的机构够简介信息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getjigoujianjie", method = RequestMethod.GET)
    public AjaxJson getjigoujianjie(HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        ajaxJson.setObj(iWhdH5MbschoolJigoujianjieService.getOne(queryWrapper));
        return ajaxJson;
    }

    /**
     * 更新拼团活动的浏览次数
     *
     * @param request
     * @param huodongID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/UpdatepthuodongLiulangTimes", method = RequestMethod.GET)
    public AjaxJson UpdatepthuodongLiulangTimes(HttpServletRequest request, long huodongID) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        WscPingtuanHuodong wscPingtuanHuodong = iWscPingtuanHuodongService.getById(huodongID);
        wscPingtuanHuodong.setLiulangTimes(wscPingtuanHuodong.getLiulangTimes() + 1);
        ajaxJson.setSuccess(iWscPingtuanHuodongService.updateById(wscPingtuanHuodong));
        return ajaxJson;
    }

    /**
     * 保存活动的举报信息
     *
     * @param request
     * @param huodongID
     * @param huodongTitle
     * @param huodongType
     * @param shuoming
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/savejubaoInfo", method = RequestMethod.POST)
    public AjaxJson savejubaoInfo(HttpServletRequest request, long huodongID, String huodongTitle, String huodongType, String shuoming) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("huodongID", huodongID);
        queryWrapper.eq("addUserID", loginUser.getWscUserID());
        List<HuodongJubao> huodongJubaos = iHuodongJubaoService.list(queryWrapper);
        if (huodongJubaos.size() > 0) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("你已经对该活动进行过举报，不需要重复操作！");
        } else {
            HuodongJubao jubao = new HuodongJubao();
            jubao.setAddTime(new Date());
            jubao.setAddUserID(loginUser.getWscUserID());
            jubao.setQiyeID(loginUser.getQiyeID());
            jubao.setIschuli(1);
            jubao.setHuodongID(huodongID);
            jubao.setHuodongtitle(huodongTitle);
            jubao.setHuodongType(huodongType);
            jubao.setShuoming(shuoming);
            ajaxJson.setSuccess(iHuodongJubaoService.save(jubao));
        }
        return ajaxJson;
    }

    /**
     * 更新砍价活动的浏览次数
     *
     * @param request
     * @param kanjiaID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateKanjiaHuodongllTimes", method = RequestMethod.GET)
    public AjaxJson updateKanjiaHuodongllTimes(HttpServletRequest request, long kanjiaID) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        WscKanjiaHuodonginfo wscKanjiaHuodonginfo = iWscKanjiaHuodonginfoService.getById(kanjiaID);
        int cishu = wscKanjiaHuodonginfo.getLiulanTimes() + 1;
        wscKanjiaHuodonginfo.setLiulanTimes(cishu);
        ajaxJson.setSuccess(iWscKanjiaHuodonginfoService.updateById(wscKanjiaHuodonginfo));
        return ajaxJson;
    }

    /**
     * 更新秒杀活动的浏览次数
     *
     * @param request
     * @param miaoshaID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updatemiaoshallTimes", method = RequestMethod.GET)
    public AjaxJson updatemiaoshallTimes(HttpServletRequest request, long miaoshaID) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        WscMiaoshaHuodonginfo wscMiaoshaHuodonginfo = iWscMiaoshaHuodonginfoService.getById(miaoshaID);
        int cishu = wscMiaoshaHuodonginfo.getLiulanTimes() + 1;
        wscMiaoshaHuodonginfo.setLiulanTimes(cishu);
        ajaxJson.setSuccess(iWscMiaoshaHuodonginfoService.updateById(wscMiaoshaHuodonginfo));
        return ajaxJson;
    }
}
