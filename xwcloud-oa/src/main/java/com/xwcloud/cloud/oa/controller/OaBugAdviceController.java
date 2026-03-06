package com.xwcloud.cloud.oa.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.OA.OaBugAdvice;
import com.xwcloud.cloud.model.OA.Vo.BugOrAdviceInfo;
import com.xwcloud.cloud.oa.service.IOaBugAdviceService;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2021-07-12
 */
@RestController
@RequestMapping("/oaBugAdvice")
public class OaBugAdviceController {

    @Autowired
    private IOaBugAdviceService iOaBugAdviceService;

    /**
     * 反馈bug或功能建议
     *
     * @param oaBugAdvice
     * @return
     */
    @RequestMapping(value = "/addBugOrAdvice", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addBugOrAdvice(@RequestBody OaBugAdvice oaBugAdvice) {
        AjaxJson ajaxJson = new AjaxJson();
        oaBugAdvice.setAddTime(new Date());
        if (oaBugAdvice.getKehufankuiDateTime() == null) {
            oaBugAdvice.setKehufankuiDateTime(new Date());
        }
        if (oaBugAdvice.getQiyeID() == null || oaBugAdvice.getQiyeID() == 0) {
            oaBugAdvice.setQiyeID(-1L);
        }
        boolean save = iOaBugAdviceService.save(oaBugAdvice);
        if (save) {
            ajaxJson.setMsg("bug或功能建议添加成功");
        } else {
            ajaxJson.setMsg("bug或功能建议添加失败");
            ajaxJson.setCode("N");
            ajaxJson.setSuccess(false);
        }

        return ajaxJson;
    }

    /**
     * bug或功能建议修改
     *
     * @param oaBugAdvice
     * @return
     */
    @RequestMapping(value = "/editBugOrAdvice", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson editBugOrAdvice(@RequestBody OaBugAdvice oaBugAdvice) {
        AjaxJson ajaxJson = new AjaxJson();
        oaBugAdvice.setAddTime(new Date());
        if (oaBugAdvice.getKehufankuiDateTime() == null) {
            oaBugAdvice.setKehufankuiDateTime(new Date());
        }
        if (oaBugAdvice.getQiyeID() == null || oaBugAdvice.getQiyeID() == 0) {
            oaBugAdvice.setQiyeID(-1L);
        }
        boolean update = iOaBugAdviceService.updateById(oaBugAdvice);
        if (update) {
            ajaxJson.setMsg("bug或功能建议修改成功");
        } else {
            ajaxJson.setMsg("bug或功能建议修改失败");
            ajaxJson.setCode("N");
            ajaxJson.setSuccess(false);
        }

        return ajaxJson;
    }


    /**
     * 分页查询所有的bug或功能建议信息
     *
     * @param size
     * @param current
     * @return
     */
    @RequestMapping(value = "/getAllBugOrAdviceInfo", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllBugOrAdviceInfo(@RequestParam(value = "size", defaultValue = "10") long size,
                                          @RequestParam(value = "current", defaultValue = "1") long current,
                                          String kehucompanyname, String staffName, Integer isBugOrAdvice,
                                          String fankuiDateStart, String fankuiDateEnd, String addTimeStart, String addTimeEnd) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<BugOrAdviceInfo> page = new Page<>(current, size);
        QueryWrapper<Object> queryWrapper = new QueryWrapper<>();
        if (kehucompanyname == null && staffName == null && isBugOrAdvice == null
                && fankuiDateStart == null && fankuiDateEnd == null && addTimeStart == null && addTimeEnd == null) {
            queryWrapper = null;
        }
        if (kehucompanyname != null && kehucompanyname.length() > 0) {
            queryWrapper.like("kehu.kehucompanyname", kehucompanyname);
        }
        if (staffName != null) {
            queryWrapper.like("staff.staffName", staffName);
        }
        if (isBugOrAdvice != null) {
            queryWrapper.eq("bug_advice.isBugOrAdvice", isBugOrAdvice);
        }

        if (fankuiDateStart != null && fankuiDateEnd != null) {
            queryWrapper.between("bug_advice.kehufankuiDateTime", fankuiDateStart, fankuiDateEnd);
        }
        if (addTimeStart != null && addTimeEnd != null) {
            queryWrapper.between("bug_advice.addTime", addTimeStart, addTimeEnd);
        }
        IPage<BugOrAdviceInfo> iPage = iOaBugAdviceService.getAllBugOrAdviceInfo(page, queryWrapper);
        ajaxJson.setObj(iPage);
        return ajaxJson;
    }

    /**
     * 根据id获取bug或功能建议
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getOneBugOrAdviceInfo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getOneBugOrAdviceInfo(@PathVariable("id") String id) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] ids = id.split(",");
        BugOrAdviceInfo bugOrAdviceInfo = iOaBugAdviceService.getOneBugOrAdviceInfo(Long.parseLong(ids[0]));
        ajaxJson.setObj(bugOrAdviceInfo);
        ajaxJson.setMsg("根据id获取bug或功能建议成功");
        return ajaxJson;
    }


    /**
     * 根据id批量删除bug或功能建议信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delBugOrAdvice/{id}", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delBugOrAdvice(@PathVariable("id") String id) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] ids = id.split(",");
        List<String> idList = Arrays.asList(ids);
        boolean remove = iOaBugAdviceService.removeByIds(idList);
        if (remove) {
            ajaxJson.setMsg("根据id批量删除bug或功能建议信息成功");
        } else {
            ajaxJson.setMsg("根据id批量删除bug或功能建议信息失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }


        return ajaxJson;
    }

}
