package com.xwcloud.cloud.oa.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.OA.OaProduct;
import com.xwcloud.cloud.oa.service.IOaProductService;
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
 * @since 2021-06-29
 */
@RestController
@RequestMapping("/oaProduct")
public class OaProductController {

    @Autowired
    private IOaProductService iOaProductService;

    /**
     * 添加产品信息
     *
     * @param oaProduct
     * @return
     */
    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addProduct(@RequestBody OaProduct oaProduct) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean save = iOaProductService.save(oaProduct);
        if (save) {
            ajaxJson.setMsg("产品信息添加成功");
        } else {
            ajaxJson.setMsg("产品信息添加失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 修改产品信息
     *
     * @param oaProduct
     * @return
     */
    @RequestMapping(value = "/editProduct", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson editProduct(@RequestBody OaProduct oaProduct) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean update = iOaProductService.updateById(oaProduct);
        if (update) {
            ajaxJson.setMsg("产品信息修改成功");
        } else {
            ajaxJson.setMsg("产品信息修改失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 批量删除产品信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delProduct", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delProduct(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] idsArr = ids.split(",");
        List<String> idsList = Arrays.asList(idsArr);
        boolean remove = iOaProductService.removeByIds(idsList);
        if (remove) {
            ajaxJson.setMsg("删除产品信息成功");
        } else {
            ajaxJson.setMsg("删除产品信息失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 分页获取所有的产品信息
     *
     * @param current
     * @param size
     * @return
     */
    @RequestMapping(value = "/getAllProduct", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllProduct(@RequestParam(value = "current", defaultValue = "1") long current,
                                  @RequestParam(value = "size", defaultValue = "10") long size) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<OaProduct> page = new Page<>(current, size);
        IPage<OaProduct> iPage = iOaProductService.page(page);
//        List<OaProduct> oaProductList = iPage.getRecords();
        ajaxJson.setObj(iPage);
        ajaxJson.setMsg("分页获取所有的产品信息");

        return ajaxJson;
    }

    /**
     * 根据id获取一个产品信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getOneProductById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getOneProductById(@PathVariable("id") long id) {
        AjaxJson ajaxJson = new AjaxJson();
        OaProduct oaProduct = iOaProductService.getById(id);
        ajaxJson.setMsg("根据id获取一个产品信息");
        ajaxJson.setObj(oaProduct);
        return ajaxJson;
    }

}
