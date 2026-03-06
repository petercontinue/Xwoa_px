package com.xwcloud.cloud.wsc.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.Sso.LoginUser;

import com.xwcloud.cloud.model.entity.WhdChongzhiPayrecord;
import com.xwcloud.cloud.model.entity.WhdChongzhiRecord;
import com.xwcloud.cloud.model.entity.WscUserjiaoyi;
import com.xwcloud.cloud.model.Vo.WhdChongzhiPayrecordVo;
import com.xwcloud.cloud.model.Vo.WhdChongzhiRecordVo;
import com.xwcloud.cloud.wsc.Service.IWhdChongzhiPayrecordService;
import com.xwcloud.cloud.wsc.Service.IWhdChongzhiRecordService;
import com.xwcloud.cloud.wsc.Service.IWscUserjiaoyiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/wsc/whdChongzhi")
@Api(tags = "微活动：充值")
public class WhdChongzhiController {

    //region server注入

    @Autowired
    private IWhdChongzhiPayrecordService whdChongzhiPayrecordService;
    @Autowired
    private IWhdChongzhiRecordService whdChongzhiRecordService;
    @Autowired
    private IWscUserjiaoyiService wscUserjiaoyiService;

    //endregion

    //region 充值支付记录
    @RequestMapping(value = "/GetWechatChongzhiPages",method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "userName",value = "用户微信昵称",required = false),
            @ApiImplicitParam(name = "orderNo",value = "订单号查询",required = false),
            @ApiImplicitParam(name = "startDate",value = "开始时间",required = false),
            @ApiImplicitParam(name = "endDate",value = "结束时间",required = false),
    })
    public AjaxJson GetWechatChongzhiPages(HttpServletRequest request, long current, long size, String userName, String orderNo, String startDate, String endDate) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(userName)) {
            queryWrapper.like("b.nickName", userName);
        }
        if (StringUtils.isNotBlank(orderNo)) {
            queryWrapper.like("a.orderNumber", orderNo);
        }
        if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {
            queryWrapper.ge("a.addDate", startDate);
            queryWrapper.le("a.addDate", endDate);
        }
        Page<WscUserjiaoyi> page = new Page<>(current, size);
        ajaxJson.setObj(wscUserjiaoyiService.GetwechatChongziPages(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * 分页查询充值活动的支付记录
     */
    @GetMapping("/getWhdChongzhiPayRecordPage")
    @ResponseBody
    @ApiOperation("分页查询充值活动的支付记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "userName", value = "用户名"),
    })
    public AjaxJson getWhdChongzhiPayRecordPage(HttpServletRequest request, long size, long current, String userName) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<WhdChongzhiPayrecordVo> wrapper = new QueryWrapper<>();
        wrapper.eq("a.qiyeID", qiyeID);
        if (!ObjectUtils.isEmpty(userName)) {
            wrapper.like("b.userName", userName);
        }
        Page<WhdChongzhiPayrecordVo> page = whdChongzhiPayrecordService.getWhdChongzhiPayRecordPage(new Page<WhdChongzhiPayrecordVo>(current, size), wrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 添加充值支付记录
     */
    @PostMapping("/addWhdChongzhiPayRecord")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @ApiOperation("添加充值支付记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "wxuserid", value = "商城用户ID", required = true),
            @ApiImplicitParam(name = "paymoney", value = "支付金额", required = true),
            @ApiImplicitParam(name = "shuoming", value = "说明"),
    })
    public AjaxJson addWhdChongzhiPayRecord(HttpServletRequest request, WhdChongzhiPayrecord whdChongzhiPayrecord) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        String msg = "";
        if (ObjectUtils.isEmpty(whdChongzhiPayrecord.getWxuserid())) {
            msg += "微商城用户不可为空。";
        }
        if (ObjectUtils.isEmpty(whdChongzhiPayrecord.getPaymoney())) {
            msg += "支付金额不可为空。";
        }
        if (!ObjectUtils.isEmpty(msg)) {
            ajaxJson.setMsg(msg);
            return ajaxJson;
        }
        ajaxJson.setSuccess(
                whdChongzhiPayrecord
                        .setPaytime(LocalDateTime.now())
                        .setQiyeID(qiyeID)
                        .insert()
        );
        return ajaxJson;
    }

    /**
     * 根据ID更新充值支付记录
     */
    @PostMapping("/updateWhdChongzhiPayRecordByID")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @ApiOperation("根据ID更新充值支付记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true),
            @ApiImplicitParam(name = "wxuserid", value = "商城用户ID"),
            @ApiImplicitParam(name = "paymoney", value = "支付金额"),
            @ApiImplicitParam(name = "shuoming", value = "说明"),
    })
    public AjaxJson updateWhdChongzhiPayRecordByID(WhdChongzhiPayrecord whdChongzhiPayrecord) {
        AjaxJson ajaxJson = new AjaxJson();
        String msg = "";
        if (ObjectUtils.isEmpty(whdChongzhiPayrecord.getId())) {
            msg += "粗错鸟。";
        }
        if (!ObjectUtils.isEmpty(msg)) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg(msg);
            return ajaxJson;
        }
        ajaxJson.setSuccess(
                whdChongzhiPayrecord
                        .setPaytime(null)
                        .setQiyeID(null)
                        .updateById()
        );
        return ajaxJson;
    }

    /**
     * 根据id批量删除充值支付记录
     */
    @DeleteMapping("/deleteWhdChongzhiPayRecordByIDs/{ids}")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("根据id批量删除充值支付记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "充值支付记录ids", required = true),
    })
    public AjaxJson deleteWhdChongzhiPayRecordByIDs(@PathVariable List<String> ids) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(whdChongzhiPayrecordService.removeByIds(ids));
        return ajaxJson;
    }

    //endregion

    //region 充值记录

    /**
     * 分页查询充值记录
     */
    @GetMapping("/getWhdChongzhiRecordPage")
    @ResponseBody
    @ApiOperation("分页查询充值记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "userName", value = "用户名"),
    })
    public AjaxJson getWhdChongzhiRecordPage(HttpServletRequest request, long size, long current, String userName) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<WhdChongzhiRecordVo> wrapper = new QueryWrapper<>();
        wrapper.eq("a.qiyeID", qiyeID);
        if (!ObjectUtils.isEmpty(userName)) {
            wrapper.eq("b.userName", userName);
        }
        Page<WhdChongzhiRecordVo> page = whdChongzhiRecordService.getWhdChongzhiRecordPage(new Page(current, size), wrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 添加充值记录
     */
    @PostMapping("/addWhdChongzhiRecord")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @ApiOperation("添加充值记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "wscuserid", value = "商城用户ID", required = true),
            @ApiImplicitParam(name = "czhuodongid", value = "充值活动ID", required = true),
            @ApiImplicitParam(name = "chongzhimoney", value = "充值金额", required = true),
            @ApiImplicitParam(name = "songmoney", value = "赠送金额", required = true),
            @ApiImplicitParam(name = "shidemoney", value = "实得金额", required = true),
            @ApiImplicitParam(name = "chongzhishuoming", value = "充值说明"),
    })
    public AjaxJson addWhdChongzhiRecord(HttpServletRequest request, WhdChongzhiRecord whdChongzhiRecord) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        String msg = "";
        if (ObjectUtils.isEmpty(whdChongzhiRecord.getWscuserid())) {
            msg += "商城用户不可为空。";
        }
        if (ObjectUtils.isEmpty(whdChongzhiRecord.getCzhuodongid())) {
            msg += "充值活动不可为空。";
        }
        if (ObjectUtils.isEmpty(whdChongzhiRecord.getChongzhimoney())) {
            msg += "充值金额不可为空。";
        }
        if (ObjectUtils.isEmpty(whdChongzhiRecord.getSongmoney())) {
            msg += "赠送金额不可为空。";
        }
        if (ObjectUtils.isEmpty(whdChongzhiRecord.getShidemoney())) {
            msg += "实得金额不可为空。";
        }
        if (!ObjectUtils.isEmpty(msg)) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg(msg);
            return ajaxJson;
        }
        ajaxJson.setSuccess(
                whdChongzhiRecord
                        .setChongzhitime(LocalDateTime.now())
                        .setQiyeID(qiyeID)
                        .insert()
        );
        return ajaxJson;
    }

    /**
     * 根据ID更新充值记录
     */
    @PostMapping("/updateWhdChongzhiRecordByID")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @ApiOperation("根据ID更新充值记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true),
            @ApiImplicitParam(name = "wscuserid", value = "商城用户ID"),
            @ApiImplicitParam(name = "czhuodongid", value = "充值活动ID"),
            @ApiImplicitParam(name = "chongzhimoney", value = "充值金额"),
            @ApiImplicitParam(name = "songmoney", value = "赠送金额"),
            @ApiImplicitParam(name = "shidemoney", value = "实得金额"),
            @ApiImplicitParam(name = "chongzhishuoming", value = "充值说明"),
    })
    public AjaxJson updateWhdChongzhiRecordByID(WhdChongzhiRecord whdChongzhiRecord) {
        AjaxJson ajaxJson = new AjaxJson();
        String msg = "";
        if (ObjectUtils.isEmpty(whdChongzhiRecord.getId())) {
            msg += "粗错鸟。";
        }
        if (!ObjectUtils.isEmpty(msg)) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg(msg);
            return ajaxJson;
        }
        ajaxJson.setSuccess(
                whdChongzhiRecord
                        .setChongzhitime(null)
                        .setQiyeID(null)
                        .updateById()
        );
        return ajaxJson;
    }

    /**
     * 根据id批量删除充值记录
     */
    @DeleteMapping("/deleteWhdChongzhiRecordByIDs/{ids}")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("根据id批量删除充值记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "充值记录ids", required = true),
    })
    public AjaxJson deleteWhdChongzhiRecordByIDs(@PathVariable List<String> ids) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(whdChongzhiRecordService.removeByIds(ids));
        return ajaxJson;
    }

    //endregion

}
