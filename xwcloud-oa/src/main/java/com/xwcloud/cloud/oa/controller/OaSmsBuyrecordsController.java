package com.xwcloud.cloud.oa.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.OA.OaKehu;
import com.xwcloud.cloud.model.OA.OaSmsBuyrecords;
import com.xwcloud.cloud.model.OA.Vo.SmsBuyrecordsVo;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.oa.service.IOaKehuService;
import com.xwcloud.cloud.oa.service.IOaSmsBuyrecordsService;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-03
 */
@Controller
@RequestMapping("/oaSmsBuyrecords")
public class OaSmsBuyrecordsController {

    @Autowired
    private IOaSmsBuyrecordsService iOaSmsBuyrecordsService;

    @Autowired
    private IOaKehuService iOaKehuService;

    /**
     * 添加短信购买记录
     *
     * @param oaSmsBuyrecords
     * @return
     */
    @RequestMapping(value = "/addSmsBuyrecords/{qiyeIDs}", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addSmsBuyrecords(HttpServletRequest request,@RequestBody OaSmsBuyrecords oaSmsBuyrecords,
                                     @PathVariable("qiyeIDs") String qiyeIDs/*, String shuoming*/) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long staffID = loginUser.getStaffID();

        AjaxJson ajaxJson = new AjaxJson();
        String[] qiyeIDArr = qiyeIDs.split(",");
        List<String> qiyeIDList = Arrays.asList(qiyeIDArr);
        List<OaKehu> oaKehuList = iOaKehuService.listByIds(qiyeIDList);
        for (int i = 0; i < qiyeIDArr.length; i++) {
            oaSmsBuyrecords.setId(null);
            oaSmsBuyrecords.setQiyeID(Long.parseLong(qiyeIDArr[i]));
//            SelfUserEntity userInfo = SecurityUtil.getUserInfo();
//            Long staffID = userInfo.getStaffID();
            oaSmsBuyrecords.setAddUser(staffID);
            BigDecimal bysSum = new BigDecimal(oaSmsBuyrecords.getBuySum());
            //计算短信总金额：  购买短信总金额 = 购买短信单价*购买短信条数
            BigDecimal sumMoney = oaSmsBuyrecords.getPrice().multiply(bysSum);
            oaSmsBuyrecords.setSumMoney(sumMoney);
            oaSmsBuyrecords.setAddTime(new Date());
            //添加短信购买记录
            iOaSmsBuyrecordsService.save(oaSmsBuyrecords);
        }

        for (OaKehu oaKehu : oaKehuList) {
            //设置客户的短信购买条数
            oaKehu.setSmsRemain(oaKehu.getSmsRemain().intValue() + oaSmsBuyrecords.getBuySum());
        }
        boolean update = iOaKehuService.updateBatchById(oaKehuList);
        if (update) {
            ajaxJson.setMsg("添加短信购买记录成功");
        } else {
            ajaxJson.setMsg("添加短信购买记录失败");
            ajaxJson.setCode("N");
            ajaxJson.setSuccess(false);
        }
        return ajaxJson;
    }

    /**
     * 修改短信购买记录
     *
     * @param oaSmsBuyrecords
     * @return
     */
    @RequestMapping(value = "/editSmsBuyrecords", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson editSmsBuyrecords(@RequestBody OaSmsBuyrecords oaSmsBuyrecords) {
        AjaxJson ajaxJson = new AjaxJson();
        BigDecimal bysSum = new BigDecimal(oaSmsBuyrecords.getBuySum());
        //计算短信总金额：  购买短信总金额 = 购买短信单价*购买短信条数
        BigDecimal sumMoney = oaSmsBuyrecords.getPrice().multiply(bysSum);
        oaSmsBuyrecords.setSumMoney(sumMoney);
        boolean update = iOaSmsBuyrecordsService.updateById(oaSmsBuyrecords);
        if (update) {
            ajaxJson.setMsg("修改短信购买记录成功");
        } else {
            ajaxJson.setMsg("修改短信购买记录失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 批量删除短信购买记录
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delSmsBuyrecords", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delSmsBuyrecords(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] idsArr = ids.split(",");
        List<String> idsList = Arrays.asList(idsArr);
        boolean remove = iOaSmsBuyrecordsService.removeByIds(idsList);
        if (remove) {
            ajaxJson.setMsg("删除短信购买记录成功");
        } else {
            ajaxJson.setMsg("删除短信购买记录失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 分页获取所有短信购买记录
     *
     * @param current
     * @param size
     * @return
     */
    @RequestMapping(value = "/getAllSmsBuyrecords", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllSmsBuyrecords(@RequestParam(value = "current", defaultValue = "1") long current,
                                        @RequestParam(value = "size", defaultValue = "10") long size,
                                        String kehucompanyname, String searchDateStart, String searchDateEnd) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<SmsBuyrecordsVo> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        if (kehucompanyname == null && searchDateStart == null && searchDateEnd == null) {
            queryWrapper = null;
        }
        if (kehucompanyname != null) {
            queryWrapper.like("kehu.kehucompanyname", kehucompanyname);
        }
        if (searchDateStart != null && searchDateEnd != null) {
            queryWrapper.between("buyrecords.buyTime", searchDateStart, searchDateEnd);
        }


        IPage<SmsBuyrecordsVo> iPage = iOaSmsBuyrecordsService.getAllSmsBuyrecordsInfo(page, queryWrapper);
//        List<SmsBuyrecordsVo> oaSmsBuyrecordsList = iPage.getRecords();
        ajaxJson.setObj(iPage);
        ajaxJson.setMsg("分页获取所有的短信购买记录成功");

        return ajaxJson;
    }

    /**
     * 根据id获取一个短息购买记录
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getOneSmsBuyrecordsById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getOneSmsBuyrecordsById(@PathVariable("id") long id) {
        AjaxJson ajaxJson = new AjaxJson();
        OaSmsBuyrecords oaSmsBuyrecords = iOaSmsBuyrecordsService.getById(id);
        ajaxJson.setMsg("根据id获取一个短息购买记录");
        ajaxJson.setObj(oaSmsBuyrecords);
        return ajaxJson;
    }

}
