package com.xwcloud.cloud.oa.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.OA.OaHehuorenLevel;
import com.xwcloud.cloud.oa.service.IOaHehuorenLevelService;
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
 * @since 2021-07-02
 */
@Controller
@RequestMapping("/oaHehuorenLevel")
public class OaHehuorenLevelController {

    @Autowired
    private IOaHehuorenLevelService iOaHehuorenLevelService;

    /**
     * 添加合伙人级别信息
     *
     * @param oaHehuorenLevel
     * @return
     */
    @RequestMapping(value = "/addHehuorenLevel", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addHehuorenLevel(@RequestBody OaHehuorenLevel oaHehuorenLevel) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean save = iOaHehuorenLevelService.save(oaHehuorenLevel);
        if (save) {
            ajaxJson.setMsg("合伙人级别信息添加成功");
        } else {
            ajaxJson.setMsg("合伙人级别信息添加失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }


    /**
     * 修改合伙人级别信息
     *
     * @param oaHehuorenLevel
     * @return
     */
    @RequestMapping(value = "/editHehuorenLevel", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson editHehuorenLevel(@RequestBody OaHehuorenLevel oaHehuorenLevel) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean update = iOaHehuorenLevelService.updateById(oaHehuorenLevel);
        if (update) {
            ajaxJson.setMsg("合伙人级别信息修改成功");
        } else {
            ajaxJson.setMsg("合伙人级别信息修改失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 批量删除合伙人级别记录
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delHehuorenLevel", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delHehuorenLevel(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] idsArr = ids.split(",");
        List<String> idsList = Arrays.asList(idsArr);
        boolean remove = iOaHehuorenLevelService.removeByIds(idsList);
        if (remove) {
            ajaxJson.setMsg("合伙人级别记录删除成功");
        } else {
            ajaxJson.setMsg("合伙人级别记录删除失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 分页获取所有合伙人级别的记录
     *
     * @param current
     * @param size
     * @return
     */
    @RequestMapping(value = "/getAllHehuorenLevel", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllHehuorenLevel(@RequestParam(value = "current", defaultValue = "1") long current,
                                        @RequestParam(value = "size", defaultValue = "10") long size) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<OaHehuorenLevel> page = new Page<>(current, size);
        IPage<OaHehuorenLevel> iPage = iOaHehuorenLevelService.page(page);
//        List<OaHehuorenLevel> hehuorenLevelList = iPage.getRecords();
        ajaxJson.setObj(iPage);
        ajaxJson.setMsg("获取所有的合伙人级别记录");
        return ajaxJson;
    }

    /**
     * 根据id获取一个合伙人级别信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getOneHehuorenLevelById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getOneHehuorenLevelById(@PathVariable("id") long id) {
        AjaxJson ajaxJson = new AjaxJson();
        OaHehuorenLevel oaHehuorenLevel = iOaHehuorenLevelService.getById(id);
        ajaxJson.setMsg("根据id获取一个合伙人级别信息");
        ajaxJson.setObj(oaHehuorenLevel);
        return ajaxJson;
    }

}
