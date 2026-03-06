package com.xwcloud.cloud.oa.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.OA.OaSmsSendrecords;
import com.xwcloud.cloud.model.OA.Vo.SmsSendrecordsVo;
import com.xwcloud.cloud.oa.service.IOaSmsSendrecordsService;
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
 * @since 2021-07-03
 */
@RestController
@RequestMapping("/oaSmsSendrecords")
public class OaSmsSendrecordsController {

    @Autowired
    private IOaSmsSendrecordsService iOaSmsSendrecordsService;

    /**
     * 添加短信发送记录
     *
     * @param oaSmsSendrecords
     * @return
     */
    @RequestMapping(value = "/addSmsSendrecords", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addSmsSendrecords(@RequestBody OaSmsSendrecords oaSmsSendrecords) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean save = iOaSmsSendrecordsService.save(oaSmsSendrecords);
        if (save) {
            ajaxJson.setMsg("短信发送记录添加成功");
        } else {
            ajaxJson.setMsg("短信发送记录添加失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 修改短信发送记录
     *
     * @param oaSmsSendrecords
     * @return
     */
    @RequestMapping(value = "/editSmsSendrecords", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson editSmsSendrecords(@RequestBody OaSmsSendrecords oaSmsSendrecords) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean update = iOaSmsSendrecordsService.updateById(oaSmsSendrecords);
        if (update) {
            ajaxJson.setMsg("短信发送记录修改成功");
        } else {
            ajaxJson.setMsg("短信发送记录修改失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }
        return ajaxJson;
    }

    /**
     * 批量删除短信发送记录
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delSmsSendrecords", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delSmsSendrecords(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] idsArr = ids.split(",");
        List<String> idsList = Arrays.asList(idsArr);
        boolean remove = iOaSmsSendrecordsService.removeByIds(idsList);
        if (remove) {
            ajaxJson.setMsg("删除短信发送记录成功");
        } else {
            ajaxJson.setMsg("删除短信发送记录失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 分页获取所有短信发送记录
     *
     * @param current
     * @param size
     * @return
     */
    @RequestMapping(value = "/getAllSmsSendrecords", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllSmsSendrecords(@RequestParam(value = "current", defaultValue = "1") long current,
                                         @RequestParam(value = "size", defaultValue = "10") long size,
                                         String kehucompanyname, String smsPhone, String searchDateStart, String searchDateEnd) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<SmsSendrecordsVo> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        if (kehucompanyname == null && smsPhone == null && searchDateStart == null && searchDateEnd == null) {
            queryWrapper = null;
        }
        if (kehucompanyname != null) {
            queryWrapper.like("kehu.kehucompanyname", kehucompanyname);
        }
        if (smsPhone != null) {
            queryWrapper.like("sendrecords.smsPhone", smsPhone);
        }
        if (searchDateStart != null && searchDateEnd != null) {
            queryWrapper.between("sendrecords.sendTime", searchDateStart, searchDateEnd);
        }
        IPage<SmsSendrecordsVo> iPage = iOaSmsSendrecordsService.getAllSmsBuyrecordsVoInfo(page, queryWrapper);
//        List<SmsSendrecordsVo> oaSmsSendrecordsList = iPage.getRecords();
        ajaxJson.setObj(iPage);
        ajaxJson.setMsg("分页获取所有的短信发送记录成功");

        return ajaxJson;
    }

    /**
     * 根据id获取一个短信发送记录
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getOneSmsSendrecordsById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getOneSmsSendrecordsById(@PathVariable("id") long id) {
        AjaxJson ajaxJson = new AjaxJson();
        OaSmsSendrecords oaSmsSendrecords = iOaSmsSendrecordsService.getById(id);
        ajaxJson.setMsg("根据id获取一个短信发送记录");
        ajaxJson.setObj(oaSmsSendrecords);
        return ajaxJson;
    }
}
