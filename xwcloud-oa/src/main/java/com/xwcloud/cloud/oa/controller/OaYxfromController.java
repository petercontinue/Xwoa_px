package com.xwcloud.cloud.oa.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.OA.OaYxfrom;
import com.xwcloud.cloud.oa.service.IOaYxfromService;
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
@RequestMapping("/oaYxfrom")
public class OaYxfromController {

    @Autowired
    private IOaYxfromService iOaYxfromService;

    /**
     * 添加意向来源
     *
     * @param oaYxfrom
     * @return
     */
    @RequestMapping(value = "/addYxfrom", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addYxfrom(@RequestBody OaYxfrom oaYxfrom) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean save = iOaYxfromService.save(oaYxfrom);
        if (save) {
            ajaxJson.setMsg("意向来源添加成功");
        } else {
            ajaxJson.setMsg("意向来源添加失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 修改意向来源
     *
     * @param oaYxfrom
     * @return
     */
    @RequestMapping(value = "/editYxfrom", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson editYxfrom(@RequestBody OaYxfrom oaYxfrom) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean update = iOaYxfromService.updateById(oaYxfrom);
        if (update) {
            ajaxJson.setMsg("意向来源修改成功");
        } else {
            ajaxJson.setMsg("意向来源修改失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 批量删除意向来源
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delYxfrom", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delYxfrom(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] idsArr = ids.split(",");
        List<String> idsList = Arrays.asList(idsArr);
        boolean remove = iOaYxfromService.removeByIds(idsList);
        if (remove) {
            ajaxJson.setMsg("删除意向来源记录成功");
        } else {
            ajaxJson.setMsg("删除意向来源记录失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 分页获取所有的意向来源信息
     *
     * @param current
     * @param size
     * @return
     */
    @RequestMapping(value = "/getAllYxfrom", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllYxfrom(@RequestParam(value = "current", defaultValue = "1") long current,
                                 @RequestParam(value = "size", defaultValue = "10") long size) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<OaYxfrom> page = new Page<>(current, size);
        IPage<OaYxfrom> iPage = iOaYxfromService.page(page);
//        List<OaYxfrom> oaYxfromList = iPage.getRecords();
        ajaxJson.setObj(iPage);
        ajaxJson.setMsg("分页获取所有的意向来源记录");

        return ajaxJson;
    }

    /**
     * 根据id获取一个意向来源信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getOneProductById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getOneProductById(@PathVariable("id") long id) {
        AjaxJson ajaxJson = new AjaxJson();
        OaYxfrom oaYxfrom = iOaYxfromService.getById(id);
        ajaxJson.setMsg("根据id获取一个意向来源信息");
        ajaxJson.setObj(oaYxfrom);
        return ajaxJson;
    }

}
