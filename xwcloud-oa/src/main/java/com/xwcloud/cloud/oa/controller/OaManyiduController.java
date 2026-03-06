package com.xwcloud.cloud.oa.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.OA.OaManyidu;
import com.xwcloud.cloud.oa.service.IOaManyiduService;
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
 * @since 2021-07-08
 */
@Controller
@RequestMapping("/oaManyidu")
public class OaManyiduController {

    @Autowired
    IOaManyiduService iOaManyiduService;

    /**
     * 添加满意度
     *
     * @param oaManyidu
     * @return
     */
    @RequestMapping(value = "/addManyidu", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addManyidu(@RequestBody OaManyidu oaManyidu) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean save = iOaManyiduService.save(oaManyidu);
        if (save) {
            ajaxJson.setMsg("添加满意度成功");
        } else {
            ajaxJson.setMsg("添加满意度失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 修改满意度
     *
     * @return
     */
    @RequestMapping(value = "/editManyidu", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson editManyidu(@RequestBody OaManyidu oaManyidu) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean update = iOaManyiduService.updateById(oaManyidu);
        if (update) {
            ajaxJson.setMsg("修改满意度成功");
        } else {
            ajaxJson.setMsg("修改满意度失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 批量删除满意度
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delManyidu", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delManyidu(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] idsArr = ids.split(",");
        List<String> idsList = Arrays.asList(idsArr);
        boolean remove = iOaManyiduService.removeByIds(idsList);
        if (remove) {
            ajaxJson.setMsg("删除满意度记录成功");
        } else {
            ajaxJson.setMsg("删除满意度记录失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 分页获取所有的满意度信息
     *
     * @param current
     * @param size
     * @return
     */
    @RequestMapping(value = "/getAllManyidu", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllManyidu(@RequestParam(value = "current", defaultValue = "1") long current,
                                  @RequestParam(value = "size", defaultValue = "10") long size) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<OaManyidu> page = new Page<>(current, size);
        IPage<OaManyidu> iPage = iOaManyiduService.page(page);
        ajaxJson.setObj(iPage);
        return ajaxJson;
    }

}
