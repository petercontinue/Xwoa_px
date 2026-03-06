package com.xwcloud.cloud.oa.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.OA.OaHuifang;
import com.xwcloud.cloud.model.OA.Vo.HuifangVo;
import com.xwcloud.cloud.oa.service.IOaHuifangService;
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
@RequestMapping("/oaHuifang")
public class OaHuifangController {

    @Autowired
    private IOaHuifangService iOaHuifangService;

    /**
     * 回访记录的添加
     *
     * @param oaHuifang
     * @return
     */
    @RequestMapping(value = "/addHuifang", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addHuifang(@RequestBody OaHuifang oaHuifang) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean save = iOaHuifangService.save(oaHuifang);
        if (save) {
            ajaxJson.setMsg("回访记录添加成功");
        } else {
            ajaxJson.setMsg("回访记录添加失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }


    /**
     * 对回访记录进行修改
     *
     * @param oaHuifang
     * @return
     */
    @RequestMapping(value = "/editHuifang", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson editHuifang(@RequestBody OaHuifang oaHuifang) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean update = iOaHuifangService.updateById(oaHuifang);
        if (update) {
            ajaxJson.setMsg("回访记录修改成功");
        } else {
            ajaxJson.setMsg("回访记录修改失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 对回访记录进行批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delHuifang", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delHuifang(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] idsArr = ids.split(",");
        List<String> idsList = Arrays.asList(idsArr);
        boolean remove = iOaHuifangService.removeByIds(idsList);
        if (remove) {
            ajaxJson.setMsg("回访记录删除成功");
        } else {
            ajaxJson.setMsg("回访记录删除失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 分页查询所有的回访记录
     *
     * @param current
     * @param size
     * @return
     */
    @RequestMapping(value = "/getAllHuifang", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllHuifang(@RequestParam(value = "current", defaultValue = "1") long current,
                                  @RequestParam(value = "size", defaultValue = "10") long size) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<HuifangVo> page = new Page<>(current, size);
        IPage<HuifangVo> iPage = iOaHuifangService.getAllHuifangInfo(page);
        List<HuifangVo> huifangList = iPage.getRecords();
        ajaxJson.setObj(huifangList);
        ajaxJson.setMsg("获取所有的回访记录");
        return ajaxJson;
    }

    /**
     * 根据id获取一个回访信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getOneHuifangById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getOneHuifangById(@PathVariable("id") long id) {
        AjaxJson ajaxJson = new AjaxJson();
        HuifangVo huifangVo = iOaHuifangService.getOneHuifangById(id);
        ajaxJson.setMsg("根据id获取一个回访信息");
        ajaxJson.setObj(huifangVo);
        return ajaxJson;
    }

}
