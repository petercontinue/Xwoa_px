package com.xwcloud.cloud.oa.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.OA.OaLog;
import com.xwcloud.cloud.oa.service.IOaLogService;
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
@RequestMapping("/oaLog")
public class OaLogController {

    @Autowired
    private IOaLogService iOaLogService;

    /**
     * 添加日志记录
     *
     * @param oaLog
     * @return
     */
    @RequestMapping(value = "/addLog", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addLog(@RequestBody OaLog oaLog) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean save = iOaLogService.save(oaLog);
        if (save) {
            ajaxJson.setMsg("日志记录添加成功");
        } else {
            ajaxJson.setMsg("日志记录添加失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }


    /**
     * 批量删除日志记录
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delLog", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delLog(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] idsArr = ids.split(",");
        List<String> idsList = Arrays.asList(idsArr);
        boolean remove = iOaLogService.removeByIds(idsList);
        if (remove) {
            ajaxJson.setMsg("日志记录删除成功");
        } else {
            ajaxJson.setMsg("日志记录删除失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 分页获取所有的日志记录
     *
     * @param current
     * @param size
     * @return
     */
    @RequestMapping(value = "/getAllLog", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllLog(@RequestParam(value = "current", defaultValue = "1") long current,
                              @RequestParam(value = "size", defaultValue = "10") long size,
                              String funcName, Integer logType, String timeSearchStart, String timeSearchEnd) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<OaLog> page = new Page<>(current, size);
        QueryWrapper<OaLog> queryWrapper = new QueryWrapper<>();
        if (funcName != null) {
            queryWrapper.like("funcName", funcName);
        }
        if (logType != null && logType != 0) {
            queryWrapper.like("logType", logType);
        }
        if (timeSearchStart != null && timeSearchEnd != null) {
            queryWrapper.between("addTime", timeSearchStart, timeSearchEnd);
        }
        IPage<OaLog> iPage = iOaLogService.page(page, queryWrapper);
//        List<OaLog> logList = iPage.getRecords();
        ajaxJson.setObj(iPage);
        ajaxJson.setMsg("获取所有的回访记录");
        return ajaxJson;
    }

    /**
     * 根据id获取一个日志信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getOneLogById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getOneLogById(@PathVariable("id") long id) {
        AjaxJson ajaxJson = new AjaxJson();
        OaLog oaLog = iOaLogService.getById(id);
        ajaxJson.setMsg("根据id获取一个日志信息");
        ajaxJson.setObj(oaLog);
        return ajaxJson;
    }

}
