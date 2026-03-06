package com.xwcloud.cloud.oa.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.OA.Areas;
import com.xwcloud.cloud.oa.service.IAreasService;
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
 * @since 2021-07-23
 */
@Controller
@RequestMapping("/areas")
public class AreasController {


    @Autowired
    private IAreasService iAreasService;


    /**
     * 获取所有的省份信息
     *
     * @return
     */
    @RequestMapping(value = "/getAllProvinceInfo", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllProvinceInfo() {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper<Areas> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parentid", "0");
        List<Areas> areasList = iAreasService.list(queryWrapper);
        ajaxJson.setObj(areasList);
        return ajaxJson;
    }

    /**
     * 根据省份获取下级城市 或者 根据城市获取下级区
     * 根据区获取下级街道
     * ...
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getAllAreasSubInfo", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllAreasSubInfo(String id) {
        AjaxJson ajaxJson = new AjaxJson();
        List<Areas> areasList = iAreasService.getAllAreasSubInfo(id);
        ajaxJson.setObj(areasList);
        ajaxJson.setMsg("获取下级城镇信息");
        return ajaxJson;
    }

}
