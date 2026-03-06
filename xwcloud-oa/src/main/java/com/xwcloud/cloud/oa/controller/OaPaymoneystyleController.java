package com.xwcloud.cloud.oa.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.OA.OaPaymoneystyle;
import com.xwcloud.cloud.oa.service.IOaPaymoneystyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-03
 */
@RestController
@RequestMapping("/oaPaymoneystyle")
public class OaPaymoneystyleController {

    @Autowired
    private IOaPaymoneystyleService iOaPaymoneystyleService;

    /**
     * 添加支付方式
     *
     * @param oaPaymoneystyle
     * @return
     */
    @RequestMapping(value = "/addPaymoneystyle", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addPaymoneystyle(@RequestBody OaPaymoneystyle oaPaymoneystyle) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean save = iOaPaymoneystyleService.save(oaPaymoneystyle);
        if (save) {
            ajaxJson.setMsg("支付方式添加成功");
        } else {
            ajaxJson.setMsg("支付方式添加失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 修改支付方式
     *
     * @param oaPaymoneystyle
     * @return
     */
    @RequestMapping(value = "/editPaymoneystyle", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson editPaymoneystyle(@RequestBody OaPaymoneystyle oaPaymoneystyle) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean update = iOaPaymoneystyleService.updateById(oaPaymoneystyle);
        if (update) {
            ajaxJson.setMsg("支付方式修改成功");
        } else {
            ajaxJson.setMsg("支付方式修改失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 批量删除支付方式记录
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delPaymoneystyle", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delPaymoneystyle(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] idsArr = ids.split(",");
        List<String> idsList = Arrays.asList(idsArr);
        boolean remove = iOaPaymoneystyleService.removeByIds(idsList);
        if (remove) {
            ajaxJson.setMsg("支付方式删除成功");
        } else {
            ajaxJson.setMsg("支付方式删除失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 分页获取所有的支付方式
     *
     * @param current
     * @param size
     * @return
     */
    @RequestMapping(value = "/getAllPaymoneystyle", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllPaymoneystyle(@RequestParam(value = "current", defaultValue = "1") long current,
                                        @RequestParam(value = "size", defaultValue = "10") long size) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<OaPaymoneystyle> page = new Page<>(current, size);
        IPage<OaPaymoneystyle> iPage = iOaPaymoneystyleService.page(page);
//        List<OaPaymoneystyle> oaPaymoneystyleList = iPage.getRecords();
        ajaxJson.setObj(iPage);
        ajaxJson.setMsg("获取所有的支付方式");
        return ajaxJson;
    }

    /**
     * 根据id获取一个支付方式信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getOnePaymoneystyleById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getOnePaymoneystyleById(@PathVariable("id") long id) {
        AjaxJson ajaxJson = new AjaxJson();
        OaPaymoneystyle oaPaymoneystyle = iOaPaymoneystyleService.getById(id);
        ajaxJson.setMsg("根据id获取一个支付方式信息");
        ajaxJson.setObj(oaPaymoneystyle);
        return ajaxJson;
    }

}
