package com.xwcloud.cloud.oa.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.OA.OaYixiangtype;
import com.xwcloud.cloud.oa.service.IOaYixiangtypeService;
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
@RequestMapping("/oaYixiangtype")
public class OaYixiangtypeController {

    @Autowired
    private IOaYixiangtypeService iOaYixiangtypeService;

    /**
     * 添加意向信息
     *
     * @param oaYixiangtype
     * @return
     */
    @RequestMapping(value = "/addYixiangtype", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addYixiangtype(@RequestBody OaYixiangtype oaYixiangtype) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean save = iOaYixiangtypeService.save(oaYixiangtype);
        if (save) {
            ajaxJson.setMsg("意向信息添加成功");
        } else {
            ajaxJson.setMsg("意向信息添加失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 修改意向信息
     *
     * @param oaYixiangtype
     * @return
     */
    @RequestMapping(value = "/editYixiangtype", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson editYixiangtype(@RequestBody OaYixiangtype oaYixiangtype) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean update = iOaYixiangtypeService.updateById(oaYixiangtype);
        if (update) {
            ajaxJson.setMsg("意向信息修改成功");
        } else {
            ajaxJson.setMsg("意向信息修改失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 批量删除意向信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delYixiangtype", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delYixiangtype(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] idsArr = ids.split(",");
        List<String> idsList = Arrays.asList(idsArr);
        boolean remove = iOaYixiangtypeService.removeByIds(idsList);
        if (remove) {
            ajaxJson.setMsg("删除意向类型记录成功");
        } else {
            ajaxJson.setMsg("删除意向类型记录失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }


    /**
     * 分页获取所有的意向信息
     *
     * @param current
     * @param size
     * @return
     */
    @RequestMapping(value = "/getAllYixiangtype", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllYixiangtype(@RequestParam(value = "current", defaultValue = "1") long current,
                                      @RequestParam(value = "size", defaultValue = "10") long size) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<OaYixiangtype> page = new Page<>(current, size);
        IPage<OaYixiangtype> iPage = iOaYixiangtypeService.page(page);
//        List<OaYixiangtype> oaYixiangtypeList = iPage.getRecords();
        ajaxJson.setObj(iPage);
        ajaxJson.setMsg("分页获取所有的意向记录");

        return ajaxJson;
    }

    /**
     * 根据id获取一个意向类型信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getOneYixiangtypeById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getOneYixiangtypeById(@PathVariable("id") long id) {
        AjaxJson ajaxJson = new AjaxJson();
        OaYixiangtype oaYixiangtype = iOaYixiangtypeService.getById(id);
        ajaxJson.setMsg("根据id获取一个意向类型信息");
        ajaxJson.setObj(oaYixiangtype);
        return ajaxJson;
    }

}
