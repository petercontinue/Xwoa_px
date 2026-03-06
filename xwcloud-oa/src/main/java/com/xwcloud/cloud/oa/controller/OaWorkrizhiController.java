package com.xwcloud.cloud.oa.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.OA.OaWorkrizhi;
import com.xwcloud.cloud.model.OA.Vo.WorkrizhiVo;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.oa.service.IOaWorkrizhiService;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
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
@RequestMapping("/oaWorkrizhi")
public class OaWorkrizhiController {

    @Autowired
    private IOaWorkrizhiService iOaWorkrizhiService;

    /**
     * 添加工作日志记录
     *
     * @param oaWorkrizhi
     * @return
     */
    @RequestMapping(value = "/addWorkrizhi", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addWorkrizhi(HttpServletRequest request,@RequestBody OaWorkrizhi oaWorkrizhi) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long staffID = loginUser.getStaffID();


        AjaxJson ajaxJson = new AjaxJson();
//        SelfUserEntity userInfo = SecurityUtil.getUserInfo();
//        Long staffID = userInfo.getStaffID();
        oaWorkrizhi.setAddStaffID(staffID);
        oaWorkrizhi.setReadState(false);
        if (oaWorkrizhi.getType() == null || oaWorkrizhi.getType() == 0) {
            oaWorkrizhi.setType(1);
        }
        oaWorkrizhi.setAddTime(new Date());
        boolean save = iOaWorkrizhiService.save(oaWorkrizhi);
        if (save) {
            ajaxJson.setMsg("工作日志添加成功");
        } else {
            ajaxJson.setMsg("工作日志添加失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }
        return ajaxJson;
    }

    /**
     * 修改工作日志记录
     *
     * @param oaWorkrizhi
     * @return
     */
    @RequestMapping(value = "/editWorkrizhi", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson editWorkrizhi(@RequestBody OaWorkrizhi oaWorkrizhi) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean update = iOaWorkrizhiService.updateById(oaWorkrizhi);
        if (update) {
            ajaxJson.setMsg("工作日志修改成功");
        } else {
            ajaxJson.setMsg("工作日志修改失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }
        return ajaxJson;
    }

    /**
     * 批量删除工作日志记录
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delWorkrizhi", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delWorkrizhi(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] idsArr = ids.split(",");
        List<String> idsList = Arrays.asList(idsArr);
        boolean remove = iOaWorkrizhiService.removeByIds(idsList);
        if (remove) {
            ajaxJson.setMsg("删除工作日志记录成功");
        } else {
            ajaxJson.setMsg("删除工作日志记录失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 分页获取所有的工作日志记录
     *
     * @param current
     * @param size
     * @return
     */
    @RequestMapping(value = "/getAllWorkrizhi", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllWorkrizhi(@RequestParam(value = "current", defaultValue = "1") long current,
                                    @RequestParam(value = "size", defaultValue = "10") long size,
                                    String staffName, Integer readState, Integer type, String addTimeStart, String addTimeEnd) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<WorkrizhiVo> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        if (staffName == null && readState == null && type == null && addTimeStart == null && addTimeEnd == null) {
            queryWrapper = null;
        }
        if (staffName != null) {
            queryWrapper.like("staff.staffName", staffName);
        }
        if (readState != null) {
            queryWrapper.eq("workrizhi.readState", readState);
        }
        if (type != null) {
            queryWrapper.eq("workrizhi.type", type);
        }
        if (addTimeStart != null && addTimeEnd != null) {
            queryWrapper.between("workrizhi.addTime", addTimeStart, addTimeEnd);
        }
        IPage<WorkrizhiVo> iPage = iOaWorkrizhiService.getAllWorkrizhiInfo(page, queryWrapper);
//        List<WorkrizhiVo> workrizhiVoList = iPage.getRecords();
        ajaxJson.setObj(iPage);
        ajaxJson.setMsg("分页获取所有的工作日志记录");

        return ajaxJson;
    }

    /**
     * 根据id获取一个工作日志信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getOneWorkrizhiById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getOneWorkrizhiById(@PathVariable("id") long id) {
        AjaxJson ajaxJson = new AjaxJson();
        WorkrizhiVo workrizhiVo = iOaWorkrizhiService.getOneWorkrizhiById(id);
        ajaxJson.setMsg("根据id获取一个工作日志信息");
        ajaxJson.setObj(workrizhiVo);
        return ajaxJson;
    }

    /**
     * 查看工作日志
     *
     * @param ids
     * @return
     */
    @PutMapping("/lookWorkrizhi")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson lookWorkrizhi(HttpServletRequest request,String ids) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long staffID = loginUser.getStaffID();

        AjaxJson ajaxJson = new AjaxJson();
        String[] idArr = ids.split(",");
        List<String> idList = Arrays.asList(idArr);
//        SelfUserEntity userInfo = SecurityUtil.getUserInfo();
//        Long staffID = userInfo.getStaffID();
        List<OaWorkrizhi> workrizhiList = iOaWorkrizhiService.listByIds(idList);
        for (OaWorkrizhi oaWorkrizhi : workrizhiList) {
            if (oaWorkrizhi.getHuibaoToStaffID() != staffID) {
                ajaxJson.setMsg("只能查看向自己汇报的信息");
                ajaxJson.setCode("N");
                ajaxJson.setSuccess(false);
                return ajaxJson;
            }
            if (oaWorkrizhi.getReadState() == true) {
                ajaxJson.setMsg("请选择都是没有阅读的汇报信息");
                ajaxJson.setCode("N");
                ajaxJson.setSuccess(false);
                return ajaxJson;
            }
            oaWorkrizhi.setReadState(true);
        }
        boolean update = iOaWorkrizhiService.updateBatchById(workrizhiList);
        if (update) {
            ajaxJson.setMsg("工作日志查看成功");
        } else {
            ajaxJson.setMsg("工作日志查看失败");
            ajaxJson.setCode("N");
            ajaxJson.setSuccess(false);
        }

        return ajaxJson;
    }

}
