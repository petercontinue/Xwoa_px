package com.xwcloud.cloud.oa.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xwcloud.cloud.model.OA.*;
import com.xwcloud.cloud.model.entity.*;
import com.xwcloud.cloud.oa.Dao.IWhdH5HuodongfabuDao;
import com.xwcloud.cloud.oa.Dao.IWscGoodsshuxinglistDao;
import com.xwcloud.cloud.oa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Dokehu {
    @Autowired
    IActivitytableService iActivitytableService;
    @Autowired
    IBirthdaydianzangService iBirthdaydianzangService;
    @Autowired
    IEvaluationpingfenService iEvaluationpingfenService;
    @Autowired
    IHuodongJubaoService iHuodongJubaoService;
    @Autowired
    ILogxiugaijilutableService iLogxiugaijilutableService;
    @Autowired
    ILogxjbtableService iLogxjbtableService;
    @Autowired
    IOaBugAdviceService iOaBugAdviceService;
    @Autowired
    IOaDaijinquanService iOaDaijinquanService;
    @Autowired
    IOaHehuorenService iOaHehuorenService;
    @Autowired
    IOaHehuorenLevelchangeService iOaHehuorenLevelchangeService;
    @Autowired
    IOaHehuorenLevelService iOaHehuorenLevelService;
    @Autowired
    IOaHuifangService iOaHuifangService;
    @Autowired
    IOaKehuService iOaKehuService;
    @Autowired
    IOaLiushuiService iOaLiushuiService;
    @Autowired
    IOaLiushuiStyleService iOaLiushuiStyleService;
    @Autowired
    IOaLiushuiYewuService iOaLiushuiYewuService;
    @Autowired
    IOaLogService iOaLogService;
    @Autowired
    IOaManyiduService iOaManyiduService;
    @Autowired
    IPxassetsaddtableService iPxassetsaddtableService;
    @Autowired
    IPxassetsouttableService iPxassetsouttableService;
    @Autowired
    IPxassetsstyletableService iPxassetsstyletableService;
    @Autowired
    IPxassetstableService iPxassetstableService;
    @Autowired
    IPxautoxiaoketableService iPxautoxiaoketableService;
    @Autowired
    IPxbooksaddtableService iPxbooksaddtableService;
    @Autowired
    IPxbooksborrowtableService iPxbooksborrowtableService;
    @Autowired
    IPxbooksouttableService iPxbooksouttableService;
    @Autowired
    IPxbooksreturntableService iPxbooksreturntableService;
    @Autowired
    IPxbookstableService iPxbookstableService;
    @Autowired
    IPxbuxikechengtableService iPxbuxikechengtableService;
    @Autowired
    IPxbuxistyletableService iPxbuxistyletableService;
    @Autowired
    IPxbxkcchangetableService iPxbxkcchangetableService;
    @Autowired
    IPxcampustableService iPxcampustableService;
    @Autowired
    IPxcertificatetableService iPxcertificatetableService;
    @Autowired
    IPxchongzhipaytableService iPxchongzhipaytableService;
    @Autowired
    IPxchongzhitableService iPxchongzhitableService;
    @Autowired
    IPxclassroomtableService iPxclassroomtableService;
    @Autowired
    IPxclasstableService iPxclasstableService;
    @Autowired
    IPxclasstimestyletableService iPxclasstimestyletableService;
    @Autowired
    IPxczhuodongtableService iPxczhuodongtableService;
    @Autowired
    IPxdaibantableService iPxdaibantableService;
    @Autowired
    IPxdaibantypetableService iPxdaibantypetableService;
    @Autowired
    IPxdaijinquantableService iPxdaijinquantableService;
    @Autowired
    IPxdaofangceshitableService iPxdaofangceshitableService;
    @Autowired
    IPxdaohangstatableService iPxdaohangstatableService;
    @Autowired
    IPxdaohangtableService iPxdaohangtableService;
    @Autowired
    IPxdropdownoptionstableService iPxdropdownoptionstableService;
    @Autowired
    IPxevaluationmubantableService iPxevaluationmubantableService;
    @Autowired
    IPxevaluationtableService iPxevaluationtableService;
    @Autowired
    IPxfazhengtableService iPxfazhengtableService;
    @Autowired
    IPxpowertableService iPxpowertableService;
    @Autowired
    IPxgonggaojiazhangtableService iPxgonggaojiazhangtableService;
    @Autowired
    IPxgonggaostafftableService iPxgonggaostafftableService;
    @Autowired
    IPxgonggaotableService iPxgonggaotableService;
    @Autowired
    IPxgradeupdatetableService iPxgradeupdatetableService;
    @Autowired
    IPxjiekeService iPxjiekeService;
    @Autowired
    IPxjiekevalueService iPxjiekevalueService;
    @Autowired
    IPxjifentableService iPxjifentableService;
    @Autowired
    IPxkaojisqtableService iPxkaojisqtableService;
    @Autowired
    IPxkaojitableService iPxkaojitableService;
    @Autowired
    IPxkechengcontenttableService iPxkechengcontenttableService;
    @Autowired
    IPxkechengtableService iPxkechengtableService;
    @Autowired
    IPxkeshiresettableService iPxkeshiresettableService;
    @Autowired
    IPxkeshistutableService iPxkeshistutableService;
    @Autowired
    IPxkeshiteachertableService iPxkeshiteachertableService;
    @Autowired
    IPxkeshizengsongtableService iPxkeshizengsongtableService;
    @Autowired
    IPxkeshizhuansongtableService iPxkeshizhuansongtableService;
    @Autowired
    IPxliushuizhangtableService iPxliushuizhangtableService;
    @Autowired
    IPxliuyantableService iPxliuyantableService;
    @Autowired
    IPxmanyidutableService iPxmanyidutableService;
    @Autowired
    IPxminimumchargetableService iPxminimumchargetableService;
    @Autowired
    IPxoldschooltableService iPxoldschooltableService;
    @Autowired
    IPxoldschoolteachertableService iPxoldschoolteachertableService;
    @Autowired
    IPxoldstugenjintableService iPxoldstugenjintableService;
    @Autowired
    IPxpaiketableService iPxpaiketableService;
    @Autowired
    IPxpaiketeachertableService iPxpaiketeachertableService;
    @Autowired
    IPxpaymoneystyletableService iPxpaymoneystyletableService;
    @Autowired
    IPxqiandaninfo2tableService iPxqiandaninfo2tableService;
    @Autowired
    IPxqiandaninfotableService iPxqiandaninfotableService;
    @Autowired
    IPxqiandanothermoneytableService iPxqiandanothermoneytableService;
    @Autowired
    IPxqiandanpaymoneyService iPxqiandanpaymoneyService;
    @Autowired
    IPxqiandanstafftableService iPxqiandanstafftableService;
    @Autowired
    IPxqiandansubjecttableService iPxqiandansubjecttableService;
    @Autowired
    IPxqiandansuppliesService iPxqiandansuppliesService;
    @Autowired
    IPxqiandanzhuanjieshaotableService iPxqiandanzhuanjieshaotableService;
    @Autowired
    IPxqiandaoqiantuitableService iPxqiandaoqiantuitableService;
    @Autowired
    IPxqingjiatableService iPxqingjiatableService;
    @Autowired
    IPxroomchecktableService iPxroomchecktableService;
    @Autowired
    IPxroomknowingtableService iPxroomknowingtableService;
    @Autowired
    IPxroomtableService iPxroomtableService;
    @Autowired
    IPxsalarystaffposttableService iPxsalarystaffposttableService;
    @Autowired
    IPxsalarystyletableService iPxsalarystyletableService;
    @Autowired
    IPxsalarytableService iPxsalarytableService;
    @Autowired
    IPxsalaryxiangxitableService iPxsalaryxiangxitableService;
    @Autowired
    IPxscoretableService iPxscoretableService;
    @Autowired
    IPxshitingrecordtableService iPxshitingrecordtableService;
    @Autowired
    IPxshouzhistyletableService iPxshouzhistyletableService;
    @Autowired
    IPxstaffposttableService iPxstaffposttableService;
    @Autowired
    IPxstafftableService iPxstafftableService;
    @Autowired
    IPxstucardtableService iPxstucardtableService;
    @Autowired
    IPxstuclasstableService iPxstuclasstableService;
    @Autowired
    IPxstugradetableService iPxstugradetableService;
    @Autowired
    IPxstuhuifangtableService iPxstuhuifangtableService;
    @Autowired
    IPxstukaoqingtableService iPxstukaoqingtableService;
    @Autowired
    IPxstukaoqingteachertableService iPxstukaoqingteachertableService;
    @Autowired
    IPxstukxqtableService iPxstukxqtableService;
    @Autowired
    IPxstuparamtypetableService iPxstuparamtypetableService;
    @Autowired
    IPxstuparamvaluetableService iPxstuparamvaluetableService;
    @Autowired
    IPxstutableService iPxstutableService;
    @Autowired
    IPxstuzxqrecordtableService iPxstuzxqrecordtableService;
    @Autowired
    IPxsubjecttableService iPxsubjecttableService;
    @Autowired
    IPxsysparamvaluetableService iPxsysparamvaluetableService;
    @Autowired
    IPxteachingsuppliesbuytableService iPxteachingsuppliesbuytableService;
    @Autowired
    IPxteachingsuppliesorderdetailtableService iPxteachingsuppliesorderdetailtableService;
    @Autowired
    IPxteachingsuppliesorderstableService iPxteachingsuppliesorderstableService;
    @Autowired
    IPxteachingsuppliesouttableService iPxteachingsuppliesouttableService;
    @Autowired
    IPxteachingsuppliestableService iPxteachingsuppliestableService;
    @Autowired
    IPxteachingsuppliestypetableService iPxteachingsuppliestypetableService;
    @Autowired
    IPxteachsubjecttableService iPxteachsubjecttableService;
    @Autowired
    IPxtesttypetableService iPxtesttypetableService;
    @Autowired
    IPxtingkeService iPxtingkeService;
    @Autowired
    IPxtousutableService iPxtousutableService;
    @Autowired
    IPxtskaiguanvaluetableService iPxtskaiguanvaluetableService;
    @Autowired
    IPxtuifeikechengtableService iPxtuifeikechengtableService;
    @Autowired
    IPxtuifeitableService iPxtuifeitableService;
    @Autowired
    IPxtuifeiteachsuppliestableService iPxtuifeiteachsuppliestableService;
    @Autowired
    IPxtuisongtableService iPxtuisongtableService;
    @Autowired
    IPxworkdayrecordtableService iPxworkdayrecordtableService;
    @Autowired
    IPxworkweekrecordtableService iPxworkweekrecordtableService;
    @Autowired
    IPxxiangceimagetableService iPxxiangceimagetableService;
    @Autowired
    IPxxiangcetableService iPxxiangcetableService;
    @Autowired
    IPxxuanketableService iPxxuanketableService;
    @Autowired
    IPxyichangkaoqintableService iPxyichangkaoqintableService;
    @Autowired
    IPxyouhuizhengcetableService iPxyouhuizhengcetableService;
    @Autowired
    IPxyuekestufaqistujointableService iPxyuekestufaqistujointableService;
    @Autowired
    IPxyuekestufaqitableService iPxyuekestufaqitableService;
    @Autowired
    IPxyueketeacherfabupriceService iPxyueketeacherfabupriceService;
    @Autowired
    IPxyueketeacherfabustutableService iPxyueketeacherfabustutableService;
    @Autowired
    IPxyueketeacherfabutableService iPxyueketeacherfabutableService;
    @Autowired
    IPxyxgengjintableService iPxyxgengjintableService;
    @Autowired
    IPxyxinvitationtableService iPxyxinvitationtableService;
    @Autowired
    IPxyxinvitedaofangtableService iPxyxinvitedaofangtableService;
    @Autowired
    IPxyxqiandantableService iPxyxqiandantableService;
    @Autowired
    IPxyxtelfromtableService iPxyxtelfromtableService;
    @Autowired
    IPxyxtelleveltableService iPxyxtelleveltableService;
    @Autowired
    IPxzhaoshenmubiaocampustableService iPxzhaoshenmubiaocampustableService;
    @Autowired
    IPxzhaoshenmubiaostafftableService iPxzhaoshenmubiaostafftableService;
    @Autowired
    IPxzuoyepiyuetableService iPxzuoyepiyuetableService;
    @Autowired
    IPxzuoyestujiaotableService iPxzuoyestujiaotableService;
    @Autowired
    IPxzuoyetableService iPxzuoyetableService;
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
    IQiandanapppayZhuanjieshaoService iQiandanapppayZhuanjieshaoService;
    @Autowired
    ITeaevaluationvalueService iTeaevaluationvalueService;
    @Autowired
    ITuichongzhiyuespService iTuichongzhiyuespService;
    @Autowired
    ITuifeishenpiService iTuifeishenpiService;
    @Autowired
    ITuikechenginfoService iTuikechenginfoService;
    @Autowired
    ITuiqiandaninfo2Service iTuiqiandaninfo2Service;
    @Autowired
    ITuisuppliseinfoService iTuisuppliseinfoService;
    @Autowired
    ITuizafeiinfoService iTuizafeiinfoService;
    @Autowired
    IWhdChongzhiRecordService iWhdChongzhiRecordService;
    @Autowired
    IWhdChongzhiPayrecordService iWhdChongzhiPayrecordService;
    @Autowired
    IWhdChoujiangCjrecordService iWhdChoujiangCjrecordService;
    @Autowired
    IWhdChoujiangHuodongService iWhdChoujiangHuodongService;
    @Autowired
    IWhdChoujiangJiangpingService iWhdChoujiangJiangpingService;
    @Autowired
    IWhdCouponsService iWhdCouponsService;
    @Autowired
    IWhdH5HuodongfabuDao iWhdH5HuodongfabuDao;
    @Autowired
    IWhdH5HuodongfabuJigoujianjieService iWhdH5HuodongfabuJigoujianjieService;
    @Autowired
    IWhdH5MbmusicService iWhdH5MbmusicService;
    @Autowired
    IWhdH5MbschoolJigoujianjieService iWhdH5MbschoolJigoujianjieService;
    @Autowired
    IWhdJizanFaqimyjizanService iWhdJizanFaqimyjizanService;
    @Autowired
    IWhdJizanHelpjizanService iWhdJizanHelpjizanService;
    @Autowired
    IWhdJizanHuodongService iWhdJizanHuodongService;
    @Autowired
    IWhdToupiaoCansaistuService iWhdToupiaoCansaistuService;
    @Autowired
    IWhdToupiaoHuodongService iWhdToupiaoHuodongService;
    @Autowired
    IWhdToupiaoTprecordService iWhdToupiaoTprecordService;
    @Autowired
    IWhdUsercouponsService iWhdUsercouponsService;
    @Autowired
    IWscAddresstypeService iWscAddresstypeService;
    @Autowired
    IWscDongtaiinfoService iWscDongtaiinfoService;
    @Autowired
    IWscGoodsService iWscGoodsService;
    @Autowired
    IWscGoodsguigeService iWscGoodsguigeServicel;
    @Autowired
    IWscGoodsshuxinglistDao iWscGoodsshuxinglistDao;
    @Autowired
    IWscGoodsshuxinglistpriceService iWscGoodsshuxinglistpriceService;
    @Autowired
    IWscGoodsshuxinglistpricePingtuanService iWscGoodsshuxinglistpricePingtuanService;
    @Autowired
    IWscGoodstypeService iWscGoodstypeService;
    @Autowired
    IWscHuodongService iWscHuodongService;
    @Autowired
    IWscHuodongOthersService iWscHuodongOthersService;
    @Autowired
    IWscHuodongValueService iWscHuodongValueService;
    @Autowired
    IWscKanjiaBangkanrecordService iWscKanjiaBangkanrecordService;
    @Autowired
    IWscKanjiaFaqirecordService iWscKanjiaFaqirecordService;
    @Autowired
    IWscKanjiaHuodonginfoService iWscKanjiaHuodonginfoService;
    @Autowired
    IWscMiaoshaHuodonginfoService iWscMiaoshaHuodonginfoService;
    @Autowired
    IWscOrderService iWscOrderService;
    @Autowired
    IWscOrdergoodsService iWscOrdergoodsService;
    @Autowired
    IWscOrdertuifeiService iWscOrdertuifeiService;
    @Autowired
    IWscPingtuanFaqirecordService iWscPingtuanFaqirecordService;
    @Autowired
    IWscPingtuanHuodongService iWscPingtuanHuodongService;
    @Autowired
    IWscPingtuanJoinrecordService iWscPingtuanJoinrecordService;
    @Autowired
    IWscShoppingcatService iWscShoppingcatService;
    @Autowired
    IWscTixianService iWscTixianService;
    @Autowired
    IWscTuikeBuyService iWscTuikeBuyService;
    @Autowired
    IWscTuikelevelService iWscTuikelevelService;
    @Autowired
    IWscTuikeYongjinService iWscTuikeYongjinService;
    @Autowired
    IWscUserAddressService iWscUserAddressService;
    @Autowired
    IWscUserBindService iWscUserBindService;
    @Autowired
    IWscUserService iWscUserService;
    @Autowired
    IWscUserjiaoyiService iWscUserjiaoyiService;
    @Autowired
    IOaPeixunrecordService iOaPeixunrecordService;
    @Autowired
    IOaQiandanSpService iOaQiandanSpService;
    @Autowired
    IOaQiandanXufeiSpService iOaQiandanXufeiSpService;
    @Autowired
    IOaSmsBuyrecordsService iOaSmsBuyrecordsService;
    @Autowired
    IOaSmsSendrecordsService iOaSmsSendrecordsService;
    @Autowired
    IOaTuifeirecordService iOaTuifeirecordService;
    @Autowired
    IOaYingjianbuyrecordService iOaYingjianbuyrecordService;
    @Autowired
    IPxkeshistuteachertableService iPxkeshistuteachertableService;
    @Autowired
    IQiandanshenpiService iQiandanshenpiService;
    @Autowired
    IQiandanshenpipaymoneyService iQiandanshenpipaymoneyService;
    @Autowired
    IQiandanshenpisubjectService iQiandanshenpisubjectService;
    @Autowired
    IQiandanshenpisuppliesService iQiandanshenpisuppliesService;
    @Autowired
    IQiandanshenpiyejirenService iQiandanshenpiyejirenService;
    @Autowired
    IQiandanshenpizafeiService iQiandanshenpizafeiService;
    @Autowired
    IQiandanshenpiZhuanjieshaoService iQiandanshenpiZhuanjieshaoService;
    @Autowired
    IWhdH5HuodongfabuService iWhdH5HuodongfabuService;
    @Autowired
    IWscCollectService iWscCollectService;
    @Autowired
    IWscGoodsshuxinglistService iWscGoodsshuxinglistService;
    @Autowired
    IOaQiandanService iOaQiandanService;


    /**
     * 系统清空、删除（意向客户、正式客户）
     *
     * @param ids
     * @param dotype
     * @return
     */
    public String handleOakehu(String ids, int dotype) {

        String[] arrId = ids.split(",");
        for (String item : arrId) {
            OaKehu kehu = iOaKehuService.getById(item);
            OaKehu nowhehu = kehu;
            //客户ID=>qiyeID
            if (dotype == 1) {
                //删除客户
                if (kehu.getKehuType() == 1) {
                    //意向客户全删
                    //已签单的 oA->客户表、签单表、财务流水，不能删除,要对业绩 ?退费、硬件流水
                    iOaKehuService.removeById(kehu);
                }
                iOaQiandanSpService.remove(new QueryWrapper<OaQiandanSp>().eq("qiyeID", nowhehu.getId()));
                iOaQiandanXufeiSpService.remove(new QueryWrapper<OaQiandanXufeiSp>().eq("qiyeID", nowhehu.getId()));
                iOaSmsBuyrecordsService.remove(new QueryWrapper<OaSmsBuyrecords>().eq("qiyeID", nowhehu.getId()));
                iOaSmsSendrecordsService.remove(new QueryWrapper<OaSmsSendrecords>().eq("qiyeID", nowhehu.getId()));
//                iOaTuifeirecordService.remove(new QueryWrapper<OaTuifeirecord>().eq("qiyeID",nowhehu.getId()));
                iOaYingjianbuyrecordService.remove(new QueryWrapper<OaYingjianbuyrecord>().eq("qiyeID", nowhehu.getId()));

                //删除培训系统 ：校区科目、课程、培训方式、课时时长、岗位、员工、任教科目、权限数据
                iPxcampustableService.remove(new QueryWrapper<Pxcampustable>().eq("qiyeID", nowhehu.getId())); //校区
                iPxclasstimestyletableService.remove(new QueryWrapper<Pxclasstimestyletable>().eq("qiyeID", nowhehu.getId()));//课程时长
                iPxbuxistyletableService.remove(new QueryWrapper<Pxbuxistyletable>().eq("qiyeID", nowhehu.getId())); //补习方式
                iPxkechengtableService.remove(new QueryWrapper<Pxkechengtable>().eq("qiyeID", nowhehu.getId())); //课程
                iPxsubjecttableService.remove(new QueryWrapper<Pxsubjecttable>().eq("qiyeID", nowhehu.getId())); //科目
                iPxstaffposttableService.remove(new QueryWrapper<Pxstaffposttable>().eq("qiyeID", nowhehu.getId())); //岗位
                iPxstafftableService.remove(new QueryWrapper<Pxstafftable>().eq("qiyeID", nowhehu.getId())); //员工
                iPxstaffposttableService.remove(new QueryWrapper<Pxstaffposttable>().eq("qiyeID", nowhehu.getId())); //岗位
                iPxteachsubjecttableService.remove(new QueryWrapper<Pxteachsubjecttable>().eq("qiyeID", nowhehu.getId()));//任教科目
                iPxpowertableService.remove(new QueryWrapper<Pxpowertable>().eq("qiyeID", nowhehu.getId())); //权限
                iPxpaymoneystyletableService.remove(new QueryWrapper<Pxpaymoneystyletable>().eq("qiyeID", nowhehu.getId()));
                iPxshouzhistyletableService.remove(new QueryWrapper<Pxshouzhistyletable>().eq("qiyeID", nowhehu.getId()));


            } else if (dotype == 2) {
                //数据清空 校区科目、课程、培训方式、课时时长、岗位、员工、任教科目、权限不清空 + 不涉及oA数据 （无操作即可）
            }


            //这里的是都要清除的数据
            //删其他OA数据
            iOaBugAdviceService.remove(new QueryWrapper<OaBugAdvice>().eq("qiyeID", nowhehu.getId()));
            iOaDaijinquanService.remove(new QueryWrapper<OaDaijinquan>().eq("qiyeID", nowhehu.getId()));
            iOaHehuorenService.remove(new QueryWrapper<OaHehuoren>().eq("qiyeID", nowhehu.getId()));
            iOaHuifangService.remove(new QueryWrapper<OaHuifang>().eq("qiyeID", nowhehu.getId()));
            iOaPeixunrecordService.remove(new QueryWrapper<OaPeixunrecord>().eq("qiyeID", nowhehu.getId()));

            iPxassetsaddtableService.remove(new QueryWrapper<Pxassetsaddtable>().eq("qiyeID", nowhehu.getId()));
            iPxassetsouttableService.remove(new QueryWrapper<Pxassetsouttable>().eq("qiyeID", nowhehu.getId()));
            iPxassetsstyletableService.remove(new QueryWrapper<Pxassetsstyletable>().eq("qiyeID", nowhehu.getId()));
            iPxassetstableService.remove(new QueryWrapper<Pxassetstable>().eq("qiyeID", nowhehu.getId()));
            iPxautoxiaoketableService.remove(new QueryWrapper<Pxautoxiaoketable>().eq("qiyeID", nowhehu.getId()));
            iPxbooksaddtableService.remove(new QueryWrapper<Pxbooksaddtable>().eq("qiyeID", nowhehu.getId()));
            iPxbooksborrowtableService.remove(new QueryWrapper<Pxbooksborrowtable>().eq("qiyeID", nowhehu.getId()));
            iPxbooksouttableService.remove(new QueryWrapper<Pxbooksouttable>().eq("qiyeID", nowhehu.getId()));
            iPxbooksreturntableService.remove(new QueryWrapper<Pxbooksreturntable>().eq("qiyeID", nowhehu.getId()));
            iPxbookstableService.remove(new QueryWrapper<Pxbookstable>().eq("qiyeID", nowhehu.getId()));
            iPxbuxikechengtableService.remove(new QueryWrapper<Pxbuxikechengtable>().eq("qiyeID", nowhehu.getId()));
            iPxbxkcchangetableService.remove(new QueryWrapper<Pxbxkcchangetable>().eq("qiyeID", nowhehu.getId()));
            iPxcertificatetableService.remove(new QueryWrapper<Pxcertificatetable>().eq("qiyeID", nowhehu.getId()));
            iPxchongzhipaytableService.remove(new QueryWrapper<Pxchongzhipaytable>().eq("qiyeID", nowhehu.getId()));
            iPxchongzhitableService.remove(new QueryWrapper<Pxchongzhitable>().eq("qiyeID", nowhehu.getId()));
            iPxclassroomtableService.remove(new QueryWrapper<Pxclassroomtable>().eq("qiyeID", nowhehu.getId()));
            iPxclasstableService.remove(new QueryWrapper<Pxclasstable>().eq("qiyeID", nowhehu.getId()));
            iPxchongzhitableService.remove(new QueryWrapper<Pxchongzhitable>().eq("qiyeID", nowhehu.getId()));
            iPxczhuodongtableService.remove(new QueryWrapper<Pxczhuodongtable>().eq("qiyeID", nowhehu.getId()));
            iPxchongzhitableService.remove(new QueryWrapper<Pxchongzhitable>().eq("qiyeID", nowhehu.getId()));
            iPxdaijinquantableService.remove(new QueryWrapper<Pxdaijinquantable>().eq("qiyeID", nowhehu.getId()));
            iPxdaofangceshitableService.remove(new QueryWrapper<Pxdaofangceshitable>().eq("qiyeID", nowhehu.getId()));
            iPxdaohangstatableService.remove(new QueryWrapper<Pxdaohangstatable>().eq("qiyeID", nowhehu.getId()));
            iPxchongzhitableService.remove(new QueryWrapper<Pxchongzhitable>().eq("qiyeID", nowhehu.getId()));
            iPxdropdownoptionstableService.remove(new QueryWrapper<Pxdropdownoptionstable>().eq("qiyeID", nowhehu.getId()));
            iPxevaluationtableService.remove(new QueryWrapper<Pxevaluationtable>().eq("qiyeID", nowhehu.getId()));
            iPxevaluationmubantableService.remove(new QueryWrapper<Pxevaluationmubantable>().eq("qiyeID", nowhehu.getId()));
            iPxfazhengtableService.remove(new QueryWrapper<Pxfazhengtable>().eq("qiyeID", nowhehu.getId()));
            iPxgonggaojiazhangtableService.remove(new QueryWrapper<Pxgonggaojiazhangtable>().eq("qiyeID", nowhehu.getId()));
            iPxgonggaostafftableService.remove(new QueryWrapper<Pxgonggaostafftable>().eq("qiyeID", nowhehu.getId()));
            iPxgonggaotableService.remove(new QueryWrapper<Pxgonggaotable>().eq("qiyeID", nowhehu.getId()));
            iPxgradeupdatetableService.remove(new QueryWrapper<Pxgradeupdatetable>().eq("qiyeID", nowhehu.getId()));
            iPxjiekeService.remove(new QueryWrapper<Pxjieke>().eq("qiyeID", nowhehu.getId()));
            iPxjifentableService.remove(new QueryWrapper<Pxjifentable>().eq("qiyeID", nowhehu.getId()));
            iPxkaojisqtableService.remove(new QueryWrapper<Pxkaojisqtable>().eq("qiyeID", nowhehu.getId()));
            iPxkaojitableService.remove(new QueryWrapper<Pxkaojitable>().eq("qiyeID", nowhehu.getId()));
            iPxkechengcontenttableService.remove(new QueryWrapper<Pxkechengcontenttable>().eq("qiyeID", nowhehu.getId()));
            iPxkeshiresettableService.remove(new QueryWrapper<Pxkeshiresettable>().eq("qiyeID", nowhehu.getId()));
            iPxkeshistutableService.remove(new QueryWrapper<Pxkeshistutable>().eq("qiyeID", nowhehu.getId()));
            iPxkeshistuteachertableService.remove(new QueryWrapper<Pxkeshistuteachertable>().eq("qiyeID", nowhehu.getId()));
            iPxkeshiteachertableService.remove(new QueryWrapper<Pxkeshiteachertable>().eq("qiyeID", nowhehu.getId()));
            iPxkeshizengsongtableService.remove(new QueryWrapper<Pxkeshizengsongtable>().eq("qiyeID", nowhehu.getId()));
            iPxkeshizhuansongtableService.remove(new QueryWrapper<Pxkeshizhuansongtable>().eq("qiyeID", nowhehu.getId()));
            iPxliushuizhangtableService.remove(new QueryWrapper<Pxliushuizhangtable>().eq("qiyeID", nowhehu.getId()));
            iPxliuyantableService.remove(new QueryWrapper<Pxliuyantable>().eq("qiyeID", nowhehu.getId()));
            iPxmanyidutableService.remove(new QueryWrapper<Pxmanyidutable>().eq("qiyeID", nowhehu.getId()));
            iPxminimumchargetableService.remove(new QueryWrapper<Pxminimumchargetable>().eq("qiyeID", nowhehu.getId()));
            iPxoldschooltableService.remove(new QueryWrapper<Pxoldschooltable>().eq("qiyeID", nowhehu.getId()));
            iPxoldschoolteachertableService.remove(new QueryWrapper<Pxoldschoolteachertable>().eq("qiyeID", nowhehu.getId()));
            iPxoldstugenjintableService.remove(new QueryWrapper<Pxoldstugenjintable>().eq("qiyeID", nowhehu.getId()));
            iPxpaiketableService.remove(new QueryWrapper<Pxpaiketable>().eq("qiyeID", nowhehu.getId()));
            iPxpaiketeachertableService.remove(new QueryWrapper<Pxpaiketeachertable>().eq("qiyeID", nowhehu.getId()));
            iPxqiandaninfo2tableService.remove(new QueryWrapper<Pxqiandaninfo2table>().eq("qiyeID", nowhehu.getId()));
            iPxqiandaninfotableService.remove(new QueryWrapper<Pxqiandaninfotable>().eq("qiyeID", nowhehu.getId()));
            iPxqiandanothermoneytableService.remove(new QueryWrapper<Pxqiandanothermoneytable>().eq("qiyeID", nowhehu.getId()));
            iPxqiandanpaymoneyService.remove(new QueryWrapper<Pxqiandanpaymoney>().eq("qiyeID", nowhehu.getId()));
            iPxqiandanstafftableService.remove(new QueryWrapper<Pxqiandanstafftable>().eq("qiyeID", nowhehu.getId()));
            iPxqiandansubjecttableService.remove(new QueryWrapper<Pxqiandansubjecttable>().eq("qiyeID", nowhehu.getId()));
            iPxqiandansuppliesService.remove(new QueryWrapper<Pxqiandansupplies>().eq("qiyeID", nowhehu.getId()));
            iPxqiandanzhuanjieshaotableService.remove(new QueryWrapper<Pxqiandanzhuanjieshaotable>().eq("qiyeID", nowhehu.getId()));
            iPxqiandaoqiantuitableService.remove(new QueryWrapper<Pxqiandaoqiantuitable>().eq("qiyeID", nowhehu.getId()));
            iPxqingjiatableService.remove(new QueryWrapper<Pxqingjiatable>().eq("qiyeID", nowhehu.getId()));
            iPxroomchecktableService.remove(new QueryWrapper<Pxroomchecktable>().eq("qiyeID", nowhehu.getId()));
            iPxroomknowingtableService.remove(new QueryWrapper<Pxroomknowingtable>().eq("qiyeID", nowhehu.getId()));
            iPxroomtableService.remove(new QueryWrapper<Pxroomtable>().eq("qiyeID", nowhehu.getId()));
            iPxsalarystaffposttableService.remove(new QueryWrapper<Pxsalarystaffposttable>().eq("qiyeID", nowhehu.getId()));
            iPxsalarystyletableService.remove(new QueryWrapper<Pxsalarystyletable>().eq("qiyeID", nowhehu.getId()));
            iPxsalarytableService.remove(new QueryWrapper<Pxsalarytable>().eq("qiyeID", nowhehu.getId()));
            iPxsalaryxiangxitableService.remove(new QueryWrapper<Pxsalaryxiangxitable>().eq("qiyeID", nowhehu.getId()));
            iPxscoretableService.remove(new QueryWrapper<Pxscoretable>().eq("qiyeID", nowhehu.getId()));
            iPxshitingrecordtableService.remove(new QueryWrapper<Pxshitingrecordtable>().eq("qiyeID", nowhehu.getId()));
            iPxstucardtableService.remove(new QueryWrapper<Pxstucardtable>().eq("qiyeID", nowhehu.getId()));
            iPxstuclasstableService.remove(new QueryWrapper<Pxstuclasstable>().eq("qiyeID", nowhehu.getId()));
            iPxstugradetableService.remove(new QueryWrapper<Pxstugradetable>().eq("qiyeID", nowhehu.getId()));
            iPxstuhuifangtableService.remove(new QueryWrapper<Pxstuhuifangtable>().eq("qiyeID", nowhehu.getId()));
            iPxstukaoqingtableService.remove(new QueryWrapper<Pxstukaoqingtable>().eq("qiyeID", nowhehu.getId()));
            iPxstukaoqingteachertableService.remove(new QueryWrapper<Pxstukaoqingteachertable>().eq("qiyeID", nowhehu.getId()));
            iPxstukxqtableService.remove(new QueryWrapper<Pxstukxqtable>().eq("qiyeID", nowhehu.getId()));
            iPxstuparamtypetableService.remove(new QueryWrapper<Pxstuparamtypetable>().eq("qiyeID", nowhehu.getId()));
            iPxstuparamvaluetableService.remove(new QueryWrapper<Pxstuparamvaluetable>().eq("qiyeID", nowhehu.getId()));
            iPxstutableService.remove(new QueryWrapper<Pxstutable>().eq("qiyeID", nowhehu.getId()));
            iPxstuzxqrecordtableService.remove(new QueryWrapper<Pxstuzxqrecordtable>().eq("qiyeID", nowhehu.getId()));
            iPxsubjecttableService.remove(new QueryWrapper<Pxsubjecttable>().eq("qiyeID", nowhehu.getId()));
            iPxsysparamvaluetableService.remove(new QueryWrapper<Pxsysparamvaluetable>().eq("qiyeID", nowhehu.getId()));
            iPxteachingsuppliesbuytableService.remove(new QueryWrapper<Pxteachingsuppliesbuytable>().eq("qiyeID", nowhehu.getId()));
            iPxteachingsuppliesorderdetailtableService.remove(new QueryWrapper<Pxteachingsuppliesorderdetailtable>().eq("qiyeID", nowhehu.getId()));
            iPxteachingsuppliesorderstableService.remove(new QueryWrapper<Pxteachingsuppliesorderstable>().eq("qiyeID", nowhehu.getId()));
            iPxteachingsuppliesouttableService.remove(new QueryWrapper<Pxteachingsuppliesouttable>().eq("qiyeID", nowhehu.getId()));
            iPxteachingsuppliestableService.remove(new QueryWrapper<Pxteachingsuppliestable>().eq("qiyeID", nowhehu.getId()));
            iPxteachingsuppliestypetableService.remove(new QueryWrapper<Pxteachingsuppliestypetable>().eq("qiyeID", nowhehu.getId()));
            iPxtesttypetableService.remove(new QueryWrapper<Pxtesttypetable>().eq("qiyeID", nowhehu.getId()));
            iPxtingkeService.remove(new QueryWrapper<Pxtingke>().eq("qiyeID", nowhehu.getId()));
            iPxtskaiguanvaluetableService.remove(new QueryWrapper<Pxtskaiguanvaluetable>().eq("qiyeID", nowhehu.getId()));
            iPxtuifeikechengtableService.remove(new QueryWrapper<Pxtuifeikechengtable>().eq("qiyeID", nowhehu.getId()));
            iPxtuifeitableService.remove(new QueryWrapper<Pxtuifeitable>().eq("qiyeID", nowhehu.getId()));
            iPxtuifeiteachsuppliestableService.remove(new QueryWrapper<Pxtuifeiteachsuppliestable>().eq("qiyeID", nowhehu.getId()));
            iPxtuisongtableService.remove(new QueryWrapper<Pxtuisongtable>().eq("qiyeID", nowhehu.getId()));
            iPxworkdayrecordtableService.remove(new QueryWrapper<Pxworkdayrecordtable>().eq("qiyeID", nowhehu.getId()));
            iPxworkweekrecordtableService.remove(new QueryWrapper<Pxworkweekrecordtable>().eq("qiyeID", nowhehu.getId()));
            iPxxiangceimagetableService.remove(new QueryWrapper<Pxxiangceimagetable>().eq("qiyeID", nowhehu.getId()));
            iPxxiangcetableService.remove(new QueryWrapper<Pxxiangcetable>().eq("qiyeID", nowhehu.getId()));
            iPxxuanketableService.remove(new QueryWrapper<Pxxuanketable>().eq("qiyeID", nowhehu.getId()));
            iPxyichangkaoqintableService.remove(new QueryWrapper<Pxyichangkaoqintable>().eq("qiyeID", nowhehu.getId()));
            iPxyouhuizhengcetableService.remove(new QueryWrapper<Pxyouhuizhengcetable>().eq("qiyeID", nowhehu.getId()));
            iPxyuekestufaqistujointableService.remove(new QueryWrapper<Pxyuekestufaqistujointable>().eq("qiyeID", nowhehu.getId()));
            iPxyuekestufaqitableService.remove(new QueryWrapper<Pxyuekestufaqitable>().eq("qiyeID", nowhehu.getId()));
            iPxyueketeacherfabupriceService.remove(new QueryWrapper<Pxyueketeacherfabuprice>().eq("qiyeID", nowhehu.getId()));
            iPxyueketeacherfabustutableService.remove(new QueryWrapper<Pxyueketeacherfabustutable>().eq("qiyeID", nowhehu.getId()));
            iPxyueketeacherfabutableService.remove(new QueryWrapper<Pxyueketeacherfabutable>().eq("qiyeID", nowhehu.getId()));
            iPxyxgengjintableService.remove(new QueryWrapper<Pxyxgengjintable>().eq("qiyeID", nowhehu.getId()));
            iPxyxinvitationtableService.remove(new QueryWrapper<Pxyxinvitationtable>().eq("qiyeID", nowhehu.getId()));
            iPxyxinvitedaofangtableService.remove(new QueryWrapper<Pxyxinvitedaofangtable>().eq("qiyeID", nowhehu.getId()));
            iPxyxqiandantableService.remove(new QueryWrapper<Pxyxqiandantable>().eq("qiyeID", nowhehu.getId()));
            iPxyxtelfromtableService.remove(new QueryWrapper<Pxyxtelfromtable>().eq("qiyeID", nowhehu.getId()));
            iPxyxtelleveltableService.remove(new QueryWrapper<Pxyxtelleveltable>().eq("qiyeID", nowhehu.getId()));
            iPxzhaoshenmubiaocampustableService.remove(new QueryWrapper<Pxzhaoshenmubiaocampustable>().eq("qiyeID", nowhehu.getId()));
            iPxzhaoshenmubiaostafftableService.remove(new QueryWrapper<Pxzhaoshenmubiaostafftable>().eq("qiyeID", nowhehu.getId()));
            iPxzuoyepiyuetableService.remove(new QueryWrapper<Pxzuoyepiyuetable>().eq("qiyeID", nowhehu.getId()));
            iPxzuoyestujiaotableService.remove(new QueryWrapper<Pxzuoyestujiaotable>().eq("qiyeID", nowhehu.getId()));
            iPxzuoyetableService.remove(new QueryWrapper<Pxzuoyetable>().eq("qiyeID", nowhehu.getId()));
            iQiandanapppayService.remove(new QueryWrapper<Qiandanapppay>().eq("qiyeID", nowhehu.getId()));
            iQiandanapppaymoneyService.remove(new QueryWrapper<Qiandanapppaymoney>().eq("qiyeID", nowhehu.getId()));
            iQiandanapppaysubjectService.remove(new QueryWrapper<Qiandanapppaysubject>().eq("qiyeID", nowhehu.getId()));
            iQiandanapppaysuppliesService.remove(new QueryWrapper<Qiandanapppaysupplies>().eq("qiyeID", nowhehu.getId()));
            iQiandanapppayyejirenService.remove(new QueryWrapper<Qiandanapppayyejiren>().eq("qiyeID", nowhehu.getId()));
            iQiandanapppayzafeiService.remove(new QueryWrapper<Qiandanapppayzafei>().eq("qiyeID", nowhehu.getId()));
            iQiandanapppayZhuanjieshaoService.remove(new QueryWrapper<QiandanapppayZhuanjieshao>().eq("qiyeID", nowhehu.getId()));
            iQiandanshenpiService.remove(new QueryWrapper<Qiandanshenpi>().eq("qiyeID", nowhehu.getId()));
            iQiandanshenpipaymoneyService.remove(new QueryWrapper<Qiandanshenpipaymoney>().eq("qiyeID", nowhehu.getId()));
            iQiandanshenpisubjectService.remove(new QueryWrapper<Qiandanshenpisubject>().eq("qiyeID", nowhehu.getId()));
            iQiandanshenpisuppliesService.remove(new QueryWrapper<Qiandanshenpisupplies>().eq("qiyeID", nowhehu.getId()));
            iQiandanshenpiyejirenService.remove(new QueryWrapper<Qiandanshenpiyejiren>().eq("qiyeID", nowhehu.getId()));
            iQiandanshenpizafeiService.remove(new QueryWrapper<Qiandanshenpizafei>().eq("qiyeID", nowhehu.getId()));
            iQiandanshenpiZhuanjieshaoService.remove(new QueryWrapper<QiandanshenpiZhuanjieshao>().eq("qiyeID", nowhehu.getId()));
            iTuichongzhiyuespService.remove(new QueryWrapper<Tuichongzhiyuesp>().eq("qiyeID", nowhehu.getId()));
            iTuifeishenpiService.remove(new QueryWrapper<Tuifeishenpi>().eq("qiyeID", nowhehu.getId()));
            iTuikechenginfoService.remove(new QueryWrapper<Tuikechenginfo>().eq("qiyeID", nowhehu.getId()));
            iTuiqiandaninfo2Service.remove(new QueryWrapper<Tuiqiandaninfo2>().eq("qiyeID", nowhehu.getId()));
            iTuisuppliseinfoService.remove(new QueryWrapper<Tuisuppliseinfo>().eq("qiyeID", nowhehu.getId()));
            iTuizafeiinfoService.remove(new QueryWrapper<Tuizafeiinfo>().eq("qiyeID", nowhehu.getId()));
            iWhdChongzhiPayrecordService.remove(new QueryWrapper<WhdChongzhiPayrecord>().eq("qiyeID", nowhehu.getId()));
            iWhdChongzhiRecordService.remove(new QueryWrapper<WhdChongzhiRecord>().eq("qiyeID", nowhehu.getId()));
            iWhdChoujiangCjrecordService.remove(new QueryWrapper<WhdChoujiangCjrecord>().eq("qiyeID", nowhehu.getId()));
            iWhdChoujiangHuodongService.remove(new QueryWrapper<WhdChoujiangHuodong>().eq("qiyeID", nowhehu.getId()));
            iWhdChoujiangJiangpingService.remove(new QueryWrapper<WhdChoujiangJiangping>().eq("qiyeID", nowhehu.getId()));
            iWhdCouponsService.remove(new QueryWrapper<WhdCoupons>().eq("qiyeID", nowhehu.getId()));
            iWhdH5HuodongfabuService.remove(new QueryWrapper<WhdH5Huodongfabu>().eq("qiyeID", nowhehu.getId()));
            iWhdH5HuodongfabuJigoujianjieService.remove(new QueryWrapper<WhdH5HuodongfabuJigoujianjie>().eq("qiyeID", nowhehu.getId()));
            iWhdJizanFaqimyjizanService.remove(new QueryWrapper<WhdJizanFaqimyjizan>().eq("qiyeID", nowhehu.getId()));
            iWhdJizanHelpjizanService.remove(new QueryWrapper<WhdJizanHelpjizan>().eq("qiyeID", nowhehu.getId()));
            iWhdJizanHuodongService.remove(new QueryWrapper<WhdJizanHuodong>().eq("qiyeID", nowhehu.getId()));
            iWhdToupiaoCansaistuService.remove(new QueryWrapper<WhdToupiaoCansaistu>().eq("qiyeID", nowhehu.getId()));
            iWhdToupiaoHuodongService.remove(new QueryWrapper<WhdToupiaoHuodong>().eq("qiyeID", nowhehu.getId()));
            iWhdToupiaoTprecordService.remove(new QueryWrapper<WhdToupiaoTprecord>().eq("qiyeID", nowhehu.getId()));
            iWhdUsercouponsService.remove(new QueryWrapper<WhdUsercoupons>().eq("qiyeID", nowhehu.getId()));
            iWscAddresstypeService.remove(new QueryWrapper<WscAddresstype>().eq("qiyeID", nowhehu.getId()));
            iWscCollectService.remove(new QueryWrapper<WscCollect>().eq("qiyeID", nowhehu.getId()));
            iWscDongtaiinfoService.remove(new QueryWrapper<WscDongtaiinfo>().eq("qiyeID", nowhehu.getId()));
            iWscGoodsService.remove(new QueryWrapper<WscGoods>().eq("qiyeID", nowhehu.getId()));
            iWscGoodsguigeServicel.remove(new QueryWrapper<WscGoodsguige>().eq("qiyeID", nowhehu.getId()));
            iWscGoodsshuxinglistService.remove(new QueryWrapper<WscGoodsshuxinglist>().eq("qiyeID", nowhehu.getId()));
            iWscGoodsshuxinglistpriceService.remove(new QueryWrapper<WscGoodsshuxinglistprice>().eq("qiyeID", nowhehu.getId()));
            iWscGoodsshuxinglistpricePingtuanService.remove(new QueryWrapper<WscGoodsshuxinglistpricePingtuan>().eq("qiyeID", nowhehu.getId()));
            iWscGoodstypeService.remove(new QueryWrapper<WscGoodstype>().eq("qiyeID", nowhehu.getId()));
            iWscHuodongOthersService.remove(new QueryWrapper<WscHuodongOthers>().eq("qiyeID", nowhehu.getId()));
            iWscHuodongValueService.remove(new QueryWrapper<WscHuodongValue>().eq("qiyeID", nowhehu.getId()));
            iWscKanjiaBangkanrecordService.remove(new QueryWrapper<WscKanjiaBangkanrecord>().eq("qiyeID", nowhehu.getId()));
            iWscKanjiaFaqirecordService.remove(new QueryWrapper<WscKanjiaFaqirecord>().eq("qiyeID", nowhehu.getId()));
            iWscKanjiaHuodonginfoService.remove(new QueryWrapper<WscKanjiaHuodonginfo>().eq("qiyeID", nowhehu.getId()));
            iWscMiaoshaHuodonginfoService.remove(new QueryWrapper<WscMiaoshaHuodonginfo>().eq("qiyeID", nowhehu.getId()));
            iWscOrderService.remove(new QueryWrapper<WscOrder>().eq("qiyeID", nowhehu.getId()));
            iWscOrdergoodsService.remove(new QueryWrapper<WscOrdergoods>().eq("qiyeID", nowhehu.getId()));
            iWscOrdertuifeiService.remove(new QueryWrapper<WscOrdertuifei>().eq("qiyeID", nowhehu.getId()));
            iWscPingtuanFaqirecordService.remove(new QueryWrapper<WscPingtuanFaqirecord>().eq("qiyeID", nowhehu.getId()));
            iWscPingtuanHuodongService.remove(new QueryWrapper<WscPingtuanHuodong>().eq("qiyeID", nowhehu.getId()));
            iWscPingtuanJoinrecordService.remove(new QueryWrapper<WscPingtuanJoinrecord>().eq("qiyeID", nowhehu.getId()));
            iWscShoppingcatService.remove(new QueryWrapper<WscShoppingcat>().eq("qiyeID", nowhehu.getId()));
            iWscTixianService.remove(new QueryWrapper<WscTixian>().eq("qiyeID", nowhehu.getId()));
            iWscTuikeBuyService.remove(new QueryWrapper<WscTuikeBuy>().eq("qiyeID", nowhehu.getId()));
            iWscTuikelevelService.remove(new QueryWrapper<WscTuikelevel>().eq("qiyeID", nowhehu.getId()));
            iWscTuikeYongjinService.remove(new QueryWrapper<WscTuikeYongjin>().eq("qiyeID", nowhehu.getId()));
            iWscUserService.remove(new QueryWrapper<WscUser>().eq("qiyeID", nowhehu.getId()));
            iWscUserAddressService.remove(new QueryWrapper<WscUserAddress>().eq("qiyeID", nowhehu.getId()));
            iWscUserBindService.remove(new QueryWrapper<WscUserBind>().eq("qiyeID", nowhehu.getId()));
            iWscUserjiaoyiService.remove(new QueryWrapper<WscUserjiaoyi>().eq("qiyeID", nowhehu.getId()));

        }

        return "操作成功";
    }


    /**
     * 减校区
     *
     * @param campusID
     * @param qiyeID
     * @return
     */
    public String delcampus(String campusID, Long qiyeID) {

        OaKehu kehu = iOaKehuService.getById(qiyeID);

        if (kehu.getKehuType() == 1) {
        } else {
            OaQiandan oneqd = iOaQiandanService.getOne(new QueryWrapper<OaQiandan>()
                    .eq("qiyeID", qiyeID)
                    .orderByDesc("qiandanDatetime")
                    .last(" limit 1"));
            oneqd.setCampusNum(oneqd.getCampusNum() - 1);
            iOaQiandanService.updateById(oneqd);
        }

        iPxassetstableService.remove(new QueryWrapper<Pxassetstable>().eq("qiyeID", qiyeID).eq("campusID", campusID));
        iPxbookstableService.remove(new QueryWrapper<Pxbookstable>().eq("qiyeID", qiyeID).eq("campusID", campusID));
        iPxclassroomtableService.remove(new QueryWrapper<Pxclassroomtable>().eq("qiyeID", qiyeID).eq("campusID", campusID));
        iPxclasstableService.remove(new QueryWrapper<Pxclasstable>().eq("qiyeID", qiyeID).eq("campusID", campusID));
        iPxkechengtableService.remove(new QueryWrapper<Pxkechengtable>().eq("qiyeID", qiyeID).eq("campusID", campusID));
        iPxkeshistutableService.remove(new QueryWrapper<Pxkeshistutable>().eq("qiyeID", qiyeID).eq("campusID", campusID));
        iPxkeshiteachertableService.remove(new QueryWrapper<Pxkeshiteachertable>().eq("qiyeID", qiyeID).eq("campusID", campusID));
        iPxliushuizhangtableService.remove(new QueryWrapper<Pxliushuizhangtable>().eq("qiyeID", qiyeID).eq("campusID", campusID));
        iPxliuyantableService.remove(new QueryWrapper<Pxliuyantable>().eq("qiyeID", qiyeID).eq("campusID", campusID));
        iPxqiandaninfotableService.remove(new QueryWrapper<Pxqiandaninfotable>().eq("qiyeID", qiyeID).eq("campusID", campusID));
        iPxroomtableService.remove(new QueryWrapper<Pxroomtable>().eq("qiyeID", qiyeID).eq("campusID", campusID));
        iPxstaffposttableService.remove(new QueryWrapper<Pxstaffposttable>().eq("qiyeID", qiyeID).eq("campusID", campusID));
        iPxstafftableService.remove(new QueryWrapper<Pxstafftable>().eq("qiyeID", qiyeID).eq("campusID", campusID));
        iPxsubjecttableService.remove(new QueryWrapper<Pxsubjecttable>().eq("qiyeID", qiyeID).eq("campusID", campusID));
        iPxteachingsuppliesbuytableService.remove(new QueryWrapper<Pxteachingsuppliesbuytable>().eq("qiyeID", qiyeID).eq("campusID", campusID));
        iPxteachingsuppliestableService.remove(new QueryWrapper<Pxteachingsuppliestable>().eq("qiyeID", qiyeID).eq("campusID", campusID));
        iPxyouhuizhengcetableService.remove(new QueryWrapper<Pxyouhuizhengcetable>().eq("qiyeID", qiyeID).eq("campusID", campusID));
        iPxyueketeacherfabutableService.remove(new QueryWrapper<Pxyueketeacherfabutable>().eq("qiyeID", qiyeID).eq("campusID", campusID));
        iPxzhaoshenmubiaocampustableService.remove(new QueryWrapper<Pxzhaoshenmubiaocampustable>().eq("qiyeID", qiyeID).eq("campusID", campusID));
        iQiandanapppayService.remove(new QueryWrapper<Qiandanapppay>().eq("qiyeID", qiyeID).eq("campusID", campusID));
        iTuifeishenpiService.remove(new QueryWrapper<Tuifeishenpi>().eq("qiyeID", qiyeID).eq("campusID", campusID));
//        iWscGoodsService.remove(new QueryWrapper<WscGoods>().eq("qiyeID", qiyeID).eq("campusID", campusID));


//        iWscOrderService.remove(new QueryWrapper<WscOrder>().eq("qiyeID", qiyeID).eq("campusID", campusID));
        iQiandanapppayService.remove(new QueryWrapper<Qiandanapppay>().eq("qiyeID", qiyeID).eq("campusID", campusID));

        List<WscOrder> orderlist = iWscOrderService.list(new QueryWrapper<WscOrder>().eq("qiyeID", qiyeID).last(" and  FIND_IN_SET( " + campusID + ", yxCampusIDs ) "));
        if (orderlist.size() > 0) {
            for (WscOrder item : orderlist) {
                iWscOrderService.updateOrdercampus(campusID, item.getId());
            }
        }


        List<WscGoods> goodslist = iWscGoodsService.list(new QueryWrapper<WscGoods>().eq("qiyeID", qiyeID).last(" and  FIND_IN_SET( " + campusID + ", campusIDs ) "));
        if (goodslist.size() > 0) {
            for (WscGoods item : goodslist) {
                iWscGoodsService.updategoodscampus(campusID, item.getId());
            }
        }


        iPxcampustableService.removeById(campusID);
        kehu.setCampusNum(kehu.getCampusNum() - 1);
        iOaKehuService.updateById(kehu);
        return "操作成功";
    }

}
