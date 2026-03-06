package com.xwcloud.cloud.oa.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.OA.OaYingjiantype;
import com.xwcloud.cloud.oa.service.IOaYingjiantypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@Controller
@RequestMapping("/oaYingjiantype")
public class OaYingjiantypeController {

    @Autowired
    private IOaYingjiantypeService iOaYingjiantypeService;

    /**
     * 添加硬件类型记录
     *
     * @param oaYingjiantype
     * @return
     */
    @RequestMapping(value = "/addYingjiantype", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addYingjiantype(@RequestBody OaYingjiantype oaYingjiantype) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean save = iOaYingjiantypeService.save(oaYingjiantype);
        if (save) {
            ajaxJson.setMsg("硬件类型添加成功");
        } else {
            ajaxJson.setMsg("硬件类型添加失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 修改硬件类型记录
     *
     * @param oaYingjiantype
     * @return
     */
    @RequestMapping(value = "/editYingjiantype", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson editYingjiantype(@RequestBody OaYingjiantype oaYingjiantype) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean update = iOaYingjiantypeService.updateById(oaYingjiantype);
        if (update) {
            ajaxJson.setMsg("硬件类型修改成功");
        } else {
            ajaxJson.setMsg("硬件类型修改失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 删除硬件类型记录
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delYingjiantype", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delYingjiantype(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] idsArr = ids.split(",");
        List<String> idsList = Arrays.asList(idsArr);
        boolean remove = iOaYingjiantypeService.removeByIds(idsList);
        if (remove) {
            ajaxJson.setMsg("删除硬件类型记录成功");
        } else {
            ajaxJson.setMsg("删除硬件类型记录失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 分页获取所有的硬件类型记录
     *
     * @param current
     * @param size
     * @return
     */
    @RequestMapping(value = "/getAllYingjiantype", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllYingjiantype(@RequestParam(value = "current", defaultValue = "1") long current,
                                       @RequestParam(value = "size", defaultValue = "10") long size) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<OaYingjiantype> page = new Page<>(current, size);
        IPage<OaYingjiantype> iPage = iOaYingjiantypeService.page(page);
//        List<OaYingjiantype> oaYingjiantypeList = iPage.getRecords();
        ajaxJson.setObj(iPage);
        ajaxJson.setMsg("分页获取所有的硬件类型记录");

        return ajaxJson;
    }

    /**
     * 根据id获取一个硬件类型信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getOneYingjiantypeById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getOneYingjiantypeById(@PathVariable("id") long id) {
        AjaxJson ajaxJson = new AjaxJson();
        OaYingjiantype oaYingjiantype = iOaYingjiantypeService.getById(id);
        ajaxJson.setMsg("根据id获取一个硬件类型信息");
        ajaxJson.setObj(oaYingjiantype);
        return ajaxJson;
    }

}
