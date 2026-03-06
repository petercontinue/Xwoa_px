package com.xwcloud.cloud.oa.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.OA.OaLiushui;
import com.xwcloud.cloud.model.OA.OaQiandan;
import com.xwcloud.cloud.model.OA.OaYingjianbuyrecord;
import com.xwcloud.cloud.model.OA.Vo.YingjianInfoVo;
import com.xwcloud.cloud.oa.service.IOaLiushuiService;
import com.xwcloud.cloud.oa.service.IOaQiandanService;
import com.xwcloud.cloud.oa.service.IOaYingjianbuyrecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-03
 */
@RestController
@RequestMapping("/oaYingjianbuyrecord")
public class OaYingjianbuyrecordController {


    @Autowired
    private IOaYingjianbuyrecordService iOaYingjianbuyrecordService;

    @Autowired
    private IOaQiandanService iOaQiandanService;

    @Autowired
    private IOaLiushuiService iOaLiushuiService;

    /**
     * 添加硬件下单信息
     *
     * @param oaYingjianbuyrecord
     * @return
     */
    @RequestMapping(value = "/addYingjianOrders", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addYingjianOrders(@RequestBody OaYingjianbuyrecord oaYingjianbuyrecord) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper<OaQiandan> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("qiyeID", oaYingjianbuyrecord.getQiyeID());
        OaQiandan oaQiandan = iOaQiandanService.getOne(queryWrapper);
        //签单表不为空
        //hardwareFahuoState:硬件发货状态：0无硬件，不需要发货，1有硬件-未发货，2有硬件-已发货，默认0
        if (oaQiandan != null) {
            switch (oaQiandan.getHardwareFahuoState()) {
                case 0:
                    ajaxJson.setSuccess(false);
                    ajaxJson.setCode("N");
                    ajaxJson.setMsg("该签单无硬件");
                    break;
                case 1:
                    //oa_yingjianbuyrecord表中插入购买一条记录
                    OaLiushui oaLiushui = new OaLiushui();
                    oaLiushui.setJinbanrenStaffID(oaYingjianbuyrecord.getYingjianBuyUser());
                    oaLiushui.setLiushuiDatetime(new Date());
                    oaLiushui.setLiushuishuoming(oaYingjianbuyrecord.getShuoming());
                    oaLiushui.setLurutime(new Date());
                    oaLiushui.setPaymoneystyleID(1L);
                    oaLiushui.setQiandanID(oaYingjianbuyrecord.getQiandanID());
                    oaLiushui.setShourumoney(oaYingjianbuyrecord.getPrice());
                    oaLiushui.setZhichumoney(oaYingjianbuyrecord.getPrice());

                    boolean result = iOaLiushuiService.save(oaLiushui);
                    if (result) {
                        //插入成功
                        Long id = oaLiushui.getId(); //获取流水ID
                        oaYingjianbuyrecord.setXiadanState(1);
                        oaYingjianbuyrecord.setYingjianLiushuiID(id);
//                        oaYingjianbuyrecord.setYingjianID(1L);
                        //获取购买数量
                        BigDecimal buyNum = oaYingjianbuyrecord.getBuyNum();
                        //获取单价
                        BigDecimal price = oaYingjianbuyrecord.getPrice();
                        //计算总价
                        BigDecimal totalMoney = buyNum.multiply(price);
                        oaYingjianbuyrecord.setTotalMoney(totalMoney);
//                        oaYingjianbuyrecord.setAddUser(1L);
                        oaYingjianbuyrecord.setAddTime(new Date());
                        oaYingjianbuyrecord.setYingjianBuyTime(new Date());

                        boolean save = iOaYingjianbuyrecordService.save(oaYingjianbuyrecord);
                        if (save) {
                            ajaxJson.setMsg("硬件下单成功");
                            ajaxJson.setObj(oaYingjianbuyrecord);
                        }
                    }
                    break;
                case 2:
                    ajaxJson.setSuccess(false);
                    ajaxJson.setCode("N");
                    ajaxJson.setMsg("该签单硬件已发货");
                    break;
            }
        } else {
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
            ajaxJson.setMsg("签单不存在，不能下单");
        }

        return ajaxJson;
    }


    /**
     * 修改硬件下单信息
     *
     * @param oaYingjianbuyrecord
     * @return
     */
    @RequestMapping(value = "/editYingjianInfo", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson editYingjianInfo(@RequestBody OaYingjianbuyrecord oaYingjianbuyrecord) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean update = iOaYingjianbuyrecordService.updateById(oaYingjianbuyrecord);
        if (update) {
            ajaxJson.setMsg("硬件下单信息修改成功");
        } else {
            ajaxJson.setMsg("硬件下单信息修改失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 分页获取硬件下单信息
     * xiadanstate:1.全部  2.已下单  3.未下单
     *
     * @param size
     * @param current
     * @param xiadanstate
     * @param kehucompanyname
     * @param yingjianTypeName
     * @param qiandanID
     * @return
     */
    @RequestMapping(value = "/getAllYingjianrecordInfo", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllYingjianrecordInfo(@RequestParam(value = "size", defaultValue = "10") long size,
                                             @RequestParam(value = "current", defaultValue = "1") long current,
                                             @RequestParam(value = "xiadanstate", defaultValue = "1") Integer xiadanstate, String taobaoID,
                                             String kehucompanyname, String yingjianTypeName, String qiandanID, String yingjianLiushuiID,
                                             String staffName, String buyUser, String yjbuyTimeStart, String yjbuyTimeEnd) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<YingjianInfoVo> page = new Page<>(current, size);
        QueryWrapper<Object> queryWrapper = new QueryWrapper<>();
        if (xiadanstate == 1 && kehucompanyname == null && yingjianTypeName == null &&
                qiandanID == null && yingjianLiushuiID == null && staffName == null &&
                buyUser == null && yjbuyTimeStart == null && yjbuyTimeEnd == null) {
            queryWrapper = null;
        }
        if (xiadanstate == 2) {
            queryWrapper.eq("xiadanState", 1);
        } else if (xiadanstate == 3) {
            queryWrapper.eq("xiadanState", 0);
        }
        if (kehucompanyname != null) {
            queryWrapper.like("kehu.kehucompanyname", kehucompanyname);
        }
        if (yingjianTypeName != null) {
            queryWrapper.like("yjtype.yingjianTypeName", yingjianTypeName);
        }
        if (qiandanID != null) {
            queryWrapper.like("yjbuyrecord.qiandanID", qiandanID);
        }
        if (taobaoID != null && taobaoID.length() > 0) {
            queryWrapper.like("yjbuyrecord.taobaoID", taobaoID);
        }
        if (staffName != null) {
            queryWrapper.like("staff2.staffName", staffName);
        }
        if (buyUser != null) {
            queryWrapper.like("staff.staffName", buyUser);
        }
        if (yingjianLiushuiID != null) {
            queryWrapper.like("yjbuyrecord.yingjianLiushuiID", yingjianLiushuiID);
        }
        if (yjbuyTimeStart != null && yjbuyTimeEnd != null) {
            queryWrapper.between("yjbuyrecord.yingjianBuyTime", yjbuyTimeStart, yjbuyTimeEnd);
        }

        IPage<YingjianInfoVo> iPage = iOaYingjianbuyrecordService.getAllYingjianrecordInfo(page, queryWrapper);
        ajaxJson.setObj(iPage);
        ajaxJson.setMsg("分页获取硬件下单信息成功");

        return ajaxJson;
    }

    /**
     * 根据id获取一条硬件下单信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getOneYingjianrecordById", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getOneYingjianrecordById(Long id) {
        AjaxJson ajaxJson = new AjaxJson();
        YingjianInfoVo oneYingjianrecordById = iOaYingjianbuyrecordService.getOneYingjianrecordById(id);
        ajaxJson.setObj(oneYingjianrecordById);
        ajaxJson.setMsg("根据id获取硬件下单信息成功");
        return ajaxJson;
    }


    /**
     * 对硬件进行下单操作
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/yingjianxiadan/{id}", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson yingjianxiadan(@PathVariable("id") String id, String taobaoID) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper<OaYingjianbuyrecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        queryWrapper.ne("xiadanState", 1); //硬件不处于下单状态才能下单
        OaYingjianbuyrecord oaYingjianbuyrecord = iOaYingjianbuyrecordService.getOne(queryWrapper);
        if (oaYingjianbuyrecord == null) {
            ajaxJson.setMsg("该硬件已下单,请勿重复下单");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        //设置下单状态为已下单
        oaYingjianbuyrecord.setXiadanState(1);
        oaYingjianbuyrecord.setTaobaoID(taobaoID);
        boolean update = iOaYingjianbuyrecordService.updateById(oaYingjianbuyrecord);
        if (update) {
            ajaxJson.setMsg("硬件下单成功");
        } else {
            ajaxJson.setMsg("硬件下单失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }
        return ajaxJson;
    }

}
