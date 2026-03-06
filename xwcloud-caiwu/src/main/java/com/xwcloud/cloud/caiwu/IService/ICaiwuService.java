package com.xwcloud.cloud.caiwu.IService;

import com.xwcloud.cloud.common.AjaxJson;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@FeignClient(value = "xwcloud-caiwu")
public interface ICaiwuService {

    // 科目课耗统计
    @RequestMapping(value = "/caiwu/tongji/kemushoufei/getKemukehaoPage", method = RequestMethod.GET)
    @ResponseBody
    AjaxJson getKemukehaoPage(@RequestParam("size") long size,
                              @RequestParam("current") long current,
                              @RequestParam("campusID") String campusID,
                              @RequestParam("kemuName") String kemuName,
                              @RequestParam("startDate") String startDate,
                              @RequestParam("endDate") String endDate,
                              @RequestParam("qiyeID") long qiyeID
    );

    // 班级收费统计
    @RequestMapping(value = "/caiwu/tongji/banjishoufei/getBanjishoufeiPage", method = RequestMethod.GET)
    @ResponseBody
    AjaxJson getBanjishoufeiPage(@RequestParam("size") long size,
                                 @RequestParam("current") long current,
                                 @RequestParam("campusID") long campusID,
                                 @RequestParam("banjiName") String banjiName,
                                 @RequestParam("qiyeID") long qiyeID
    );

    // 班级收费详细
    @RequestMapping(value = "/caiwu/tongji/banjishoufei/getShoufeiDetail", method = RequestMethod.GET)
    @ResponseBody
    AjaxJson getShoufeiDetail(@RequestParam("size") long size,
                              @RequestParam("current") long current,
                              @RequestParam("campusID") long campusID,
                              @RequestParam("classID") long classID,
                              @RequestParam("qiyeID") long qiyeID
    );

    // 科目收费统计
    @RequestMapping(value = "/caiwu/tongji/kemushoufei/getKemushoufeiPage", method = RequestMethod.GET)
    @ResponseBody
    AjaxJson getKemushoufeiPage(@RequestParam("size") long size,
                                @RequestParam("current") long current,
                                @RequestParam("campusID") long campusID,
                                @RequestParam("kemuName") String kemuName,
                                @RequestParam("qiyeID") long qiyeID
    );

    // 科目余额统计
    @RequestMapping(value = "/caiwu/tongji/kemushoufei/getKemuyufeePage", method = RequestMethod.GET)
    @ResponseBody
    AjaxJson getKemuyufeePage(@RequestParam("size") long size,
                              @RequestParam("current") long current,
                              @RequestParam("campusID") long campusID,
                              @RequestParam("kemuName") String kemuName,
                              @RequestParam("qiyeID") long qiyeID
    );

    // 导出科目收费
    @RequestMapping(value = "/caiwu/tongji/kemushoufei/exportKemushoufei", method = RequestMethod.GET)
    @ResponseBody
    void exportKemushoufei(
            @RequestParam("campusID") long campusID,
            @RequestParam("kemuName") String kemuName,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("qiyeID") long qiyeID
    );

    // 导出科目课耗
    @RequestMapping(value = "/caiwu/tongji/kemushoufei/exportKemukehao", method = RequestMethod.GET)
    @ResponseBody
    void exportKemukehao(
            @RequestParam("campusID") long campusID,
            @RequestParam("kemuName") String kemuName,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("qiyeID") long qiyeID
    );

    // 导出科目余额
    @RequestMapping(value = "/caiwu/tongji/kemushoufei/exportKemuyufee", method = RequestMethod.GET)
    @ResponseBody
    void exportKemuyufee(
            @RequestParam("campusID") long campusID,
            @RequestParam("kemuName") String kemuName,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("qiyeID") long qiyeID
    );
}