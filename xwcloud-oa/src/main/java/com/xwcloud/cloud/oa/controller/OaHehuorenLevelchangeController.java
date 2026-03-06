package com.xwcloud.cloud.oa.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.OA.OaHehuorenLevelchange;
import com.xwcloud.cloud.oa.service.IOaHehuorenLevelchangeService;
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
 * @since 2021-07-02
 */
@RestController
@RequestMapping("/oaHehuorenLevelchange")
public class OaHehuorenLevelchangeController {

    @Autowired
    private IOaHehuorenLevelchangeService iOaHehuorenLevelchangeService;

    /**
     * 添加合伙人级别变动信息
     *
     * @param oaHehuorenLevelchange
     * @return
     */
    @RequestMapping(value = "/addHehuorenLevelchange", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addHehuorenLevelchange(@RequestBody OaHehuorenLevelchange oaHehuorenLevelchange) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean save = iOaHehuorenLevelchangeService.save(oaHehuorenLevelchange);
        if (save) {
            ajaxJson.setMsg("合伙人级别变动信息添加成功");
        } else {
            ajaxJson.setMsg("合伙人级别变动信息添加失败");
            ajaxJson.setCode("N");
            ajaxJson.setSuccess(false);
        }

        return ajaxJson;
    }

    /**
     * 修改合伙人级别变动信息
     *
     * @param oaHehuorenLevelchange
     * @return
     */
    @RequestMapping(value = "/editHehuorenLevelchange", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson editHehuorenLevelchange(@RequestBody OaHehuorenLevelchange oaHehuorenLevelchange) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean update = iOaHehuorenLevelchangeService.updateById(oaHehuorenLevelchange);
        if (update) {
            ajaxJson.setMsg("合伙人级别变动信息修改成功");
        } else {
            ajaxJson.setMsg("合伙人级别变动信息修改失败");
            ajaxJson.setCode("N");
            ajaxJson.setSuccess(false);
        }

        return ajaxJson;
    }

    /**
     * 批量删除合伙人级别变动信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delHehuorenLevelchange", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delHehuorenLevelchange(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] idsArr = ids.split(",");
        List<String> idsList = Arrays.asList(idsArr);
        boolean remove = iOaHehuorenLevelchangeService.removeByIds(idsList);
        if (remove) {
            ajaxJson.setMsg("合伙人级别变动信息删除成功");
        } else {
            ajaxJson.setMsg("合伙人级别变动信息删除失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 分页获取所有的合伙人级别变动记录
     *
     * @param current
     * @param size
     * @return
     */
    @RequestMapping(value = "/getAllHehuorenLevelchange", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllHehuorenLevelchange(@RequestParam(value = "current", defaultValue = "1") long current,
                                              @RequestParam(value = "size", defaultValue = "10") long size) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<OaHehuorenLevelchange> page = new Page<>(current, size);
        IPage<OaHehuorenLevelchange> iPage = iOaHehuorenLevelchangeService.page(page);
//        List<OaHehuorenLevelchange> oaHehuorenLevelchangeList = iPage.getRecords();
        ajaxJson.setObj(iPage);
        ajaxJson.setMsg("获取所有的合伙人级别变动记录");
        return ajaxJson;
    }

    /**
     * 根据id获取一个合伙人级别变动信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getOneHehuorenLevelchangeById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getOneHehuorenLevelchangeById(@PathVariable("id") long id) {
        AjaxJson ajaxJson = new AjaxJson();
        OaHehuorenLevelchange oaHehuorenLevelchange = iOaHehuorenLevelchangeService.getById(id);
        ajaxJson.setMsg("根据id获取一个合伙人级别变动信息");
        ajaxJson.setObj(oaHehuorenLevelchange);
        return ajaxJson;
    }

}
