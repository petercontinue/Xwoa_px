package com.xwcloud.cloud.oa.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.OA.OaTaocantype;
import com.xwcloud.cloud.oa.service.IOaTaocantypeService;
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
 * @since 2021-06-29
 */
@Controller
@RequestMapping("/oaTaocantype")
public class OaTaocantypeController {

    @Autowired
    private IOaTaocantypeService iOaTaocantypeService;

    /**
     * 添加套餐类型信息
     *
     * @param oaTaocantype
     * @return
     */
    @RequestMapping(value = "/addTaocantype", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addTaocantype(@RequestBody OaTaocantype oaTaocantype) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean save = iOaTaocantypeService.save(oaTaocantype);
        if (save) {
            ajaxJson.setMsg("套餐类型添加成功");
        } else {
            ajaxJson.setMsg("套餐类型添加失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }
        return ajaxJson;
    }

    /**
     * 修改套餐类型信息
     *
     * @param oaTaocantype
     * @return
     */
    @RequestMapping(value = "/editTaocantype", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson editTaocantype(@RequestBody OaTaocantype oaTaocantype) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean update = iOaTaocantypeService.updateById(oaTaocantype);
        if (update) {
            ajaxJson.setMsg("套餐类型修改成功");
        } else {
            ajaxJson.setMsg("套餐类型修改失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }
        return ajaxJson;
    }

    /**
     * 批量删除套餐类型信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delTaocantype", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delTaocantype(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] idsArr = ids.split(",");
        List<String> idsList = Arrays.asList(idsArr);
        boolean remove = iOaTaocantypeService.removeByIds(idsList);
        if (remove) {
            ajaxJson.setMsg("删除套餐类型记录成功");
        } else {
            ajaxJson.setMsg("删除套餐类型记录失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 分页获取所有的套餐类型信息
     *
     * @param current
     * @param size
     * @return
     */
    @RequestMapping(value = "/getAllTaocantype", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllTaocantype(@RequestParam(value = "current", defaultValue = "1") long current,
                                     @RequestParam(value = "size", defaultValue = "10") long size) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<OaTaocantype> page = new Page<>(current, size);
        IPage<OaTaocantype> iPage = iOaTaocantypeService.page(page);
//        List<OaTaocantype> oaTaocantypeList = iPage.getRecords();
        ajaxJson.setObj(iPage);
        ajaxJson.setMsg("分页获取所有的套餐类型信息");

        return ajaxJson;
    }

    /**
     * 分页获取所有的套餐类型信息
     */
    @RequestMapping(value = "/getAlltaocanTypeNoPage", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAlltaocanTypeNoPage() {
        AjaxJson ajaxJson = new AjaxJson();
        List<OaTaocantype> list = iOaTaocantypeService.list();
        ajaxJson.setObj(list);
        ajaxJson.setMsg("获取所有的套餐类型信息");
        return ajaxJson;
    }

    /**
     * 根据id获取一个套餐类型信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getOneTaocantypeById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getOneTaocantypeById(@PathVariable("id") long id) {
        AjaxJson ajaxJson = new AjaxJson();
        OaTaocantype oaTaocantype = iOaTaocantypeService.getById(id);
        ajaxJson.setMsg("根据id获取一个套餐类型信息");
        ajaxJson.setObj(oaTaocantype);
        return ajaxJson;
    }

}
