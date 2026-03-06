package com.xwcloud.cloud.oa.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.OA.OaLiushuiStyle;
import com.xwcloud.cloud.oa.service.IOaLiushuiStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-15
 */
@Controller
@RequestMapping("/oaLiushuiStyle")
public class OaLiushuiStyleController {

    @Autowired
    private IOaLiushuiStyleService iOaLiushuiStyleService;

    /**
     * 分页获取所有的流水类别信息
     *
     * @param size
     * @param current
     * @return
     */
    @RequestMapping(value = "/getAllLiushuiStylePageInfo", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllLiushuiStylePageInfo(@RequestParam(value = "size", defaultValue = "10") long size,
                                               @RequestParam(value = "current", defaultValue = "1") long current) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<OaLiushuiStyle> page = new Page<>(current, size);
        IPage<OaLiushuiStyle> iPage = iOaLiushuiStyleService.page(page);
        ajaxJson.setObj(iPage);
        return ajaxJson;
    }

    /**
     * 获取所有
     * queryCondition: 0查询所有  1查询收入 2查询支出
     *
     * @param queryCondition
     * @return
     */
    @RequestMapping(value = "/getAllLiushuiStyleInfo", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllLiushuiStyleInfo(@RequestParam(value = "queryCondition", defaultValue = "0") Integer queryCondition) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper<OaLiushuiStyle> queryWrapper = new QueryWrapper<>();
        if (queryCondition == 1) {
            queryWrapper.eq("isShouruOrZhichu", 1);
        } else if (queryCondition == 2) {
            queryWrapper.eq("isShouruOrZhichu", 2);
        } else {
            queryWrapper = null;
        }
        List<OaLiushuiStyle> list = iOaLiushuiStyleService.list(queryWrapper);
        ajaxJson.setObj(list);
        return ajaxJson;
    }

}
