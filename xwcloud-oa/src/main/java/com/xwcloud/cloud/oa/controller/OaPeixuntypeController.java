package com.xwcloud.cloud.oa.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.OA.OaLog;
import com.xwcloud.cloud.model.OA.OaPeixuntype;
import com.xwcloud.cloud.oa.service.IOaLogService;
import com.xwcloud.cloud.oa.service.IOaPeixuntypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
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
@RequestMapping("/oaPeixuntype")
public class OaPeixuntypeController {

    @Autowired
    private IOaPeixuntypeService iOaPeixuntypeService;

    @Autowired
    private IOaLogService iOaLogService;

    /**
     * 培训类型的添加
     *
     * @param oaPeixuntype
     * @return
     */
    @RequestMapping(value = "/addPeixunType", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addPeixunType(@RequestBody OaPeixuntype oaPeixuntype) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean save = iOaPeixuntypeService.save(oaPeixuntype);
        if (save) {
            ajaxJson.setMsg("培训类别添加成功");
        } else {
            ajaxJson.setMsg("培训类别添加失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }
        //往日志表中插入一条数据
        OaLog oaLog = new OaLog();
        oaLog.setSystemContent("添加一条名为\'" + oaPeixuntype.getHangyetypename() + "\'的培训类别信息" + "\'");
        oaLog.setFuncName("添加培训类别");
        oaLog.setAddTime(new Date());
        oaLog.setLogType(1);
        iOaLogService.save(oaLog);

        return ajaxJson;
    }

    /**
     * 培训类型的修改
     *
     * @param oaPeixuntype
     * @return
     */
    @RequestMapping(value = "/editPeixunType", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson editPeixunType(@RequestBody OaPeixuntype oaPeixuntype) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean update = iOaPeixuntypeService.updateById(oaPeixuntype);
        if (update) {
            ajaxJson.setMsg("培训类别修改成功");
        } else {
            ajaxJson.setMsg("培训类别修改失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }
        //往日志表中插入一条数据
        OaLog oaLog = new OaLog();
        oaLog.setSystemContent("修改了一条id为:\'" + oaPeixuntype.getId() + "\'的培训类别信息," + "修改后的培训类别名称为:\'" + oaPeixuntype.getHangyetypename() + "\'");
        oaLog.setFuncName("修改培训类别");
        oaLog.setAddTime(new Date());
        oaLog.setLogType(1);
        iOaLogService.save(oaLog);
        return ajaxJson;
    }

    /**
     * 批量删除培训类型
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delPeixunType", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delPeixunType(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] idsArr = ids.split(",");
        List<String> idsList = Arrays.asList(idsArr);
        boolean remove = iOaPeixuntypeService.removeByIds(idsList);
        if (remove) {
            ajaxJson.setMsg("培训类别删除成功");
        } else {
            ajaxJson.setMsg("培训类别删除失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }
        ids = ids.substring(0, ids.length() - 1);
        //往日志表中插入一条数据
        OaLog oaLog = new OaLog();
        oaLog.setSystemContent("删除了id为:\'" + ids + "\'的培训类别信息");
        oaLog.setFuncName("删除培训类别");
        oaLog.setAddTime(new Date());
        oaLog.setLogType(1);
        iOaLogService.save(oaLog);
        return ajaxJson;
    }

    /**
     * 分页获取所有培训类别
     *
     * @return
     */
    @RequestMapping(value = "/getAllPeixunType", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllPeixunType(@RequestParam(value = "size", defaultValue = "10") long size,
                                     @RequestParam(value = "current", defaultValue = "1") long current,
                                     String hangyetypename, String id) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<OaPeixuntype> page = new Page<>(current, size);
        QueryWrapper<OaPeixuntype> queryWrapper = new QueryWrapper<>();
        if (id != null) {
            queryWrapper.like("id", id);
        }
        if (hangyetypename != null) {
            queryWrapper.like("hangyetypename", hangyetypename);
        }
        IPage<OaPeixuntype> iPage = iOaPeixuntypeService.page(page, queryWrapper);
        ajaxJson.setObj(iPage);
        ajaxJson.setMsg("分页获取所有培训类别");
        return ajaxJson;
    }

    /**
     * 分页获取所有的培训类别
     */
    @RequestMapping(value = "/getAllpeixunTypeNoPage", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllpeixunTypeNoPage() {
        AjaxJson ajaxJson = new AjaxJson();
        List<OaPeixuntype> list = iOaPeixuntypeService.list();
        ajaxJson.setObj(list);
        ajaxJson.setMsg("所有培训类别信息");
        return ajaxJson;
    }

    /**
     * 根据id获取一个培训类型信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getOnePeixunTypeById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getOnePeixunTypeById(@PathVariable("id") long id) {
        AjaxJson ajaxJson = new AjaxJson();
        OaPeixuntype oaPeixuntype = iOaPeixuntypeService.getById(id);
        ajaxJson.setMsg("根据id获取一个培训类别信息");
        ajaxJson.setObj(oaPeixuntype);
        return ajaxJson;
    }

}
