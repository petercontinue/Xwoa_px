package com.xwcloud.cloud.oa.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.OA.OaLiushui;
import com.xwcloud.cloud.model.OA.Vo.LiushuiInfoVo;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.oa.service.IOaLiushuiService;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2021-06-29
 */
@RestController
@RequestMapping("/oaLiushui")
public class OaLiushuiController {

    @Autowired
    private IOaLiushuiService iOaLiushuiService;

    /**
     * 添加财务流水信息
     *
     * @param oaLiushui
     * @return
     */
    @RequestMapping(value = "/addLiushuiInfo", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addLiushuiInfo(HttpServletRequest request,@RequestBody OaLiushui oaLiushui) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long staffID = loginUser.getStaffID();

        AjaxJson ajaxJson = new AjaxJson();
        oaLiushui.setQiandanID(0L);
        if (oaLiushui.getJinbanrenStaffID() == null || oaLiushui.getJinbanrenStaffID() == 0) {
//            SelfUserEntity userInfo = SecurityUtil.getUserInfo();
//            Long staffID = userInfo.getStaffID();
            oaLiushui.setJinbanrenStaffID(staffID);
        }
        if (oaLiushui.getLurutime() == null) {
            oaLiushui.setLurutime(new Date());
        }
        boolean save = iOaLiushuiService.save(oaLiushui);
        if (save) {
            ajaxJson.setMsg("财务流水信息添加成功");
        } else {
            ajaxJson.setMsg("财务流水信息添加失败");
            ajaxJson.setCode("N");
            ajaxJson.setSuccess(false);
        }

        return ajaxJson;
    }

    /**
     * 修改财务流水信息
     *
     * @param oaLiushui
     * @return
     */
    @RequestMapping(value = "/editLiushuiInfo", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson editLiushuiInfo(HttpServletRequest request,@RequestBody OaLiushui oaLiushui) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long staffID = loginUser.getStaffID();

        AjaxJson ajaxJson = new AjaxJson();
        oaLiushui.setQiandanID(0L);
        if (oaLiushui.getJinbanrenStaffID() == null || oaLiushui.getJinbanrenStaffID() == 0) {
//            SelfUserEntity userInfo = SecurityUtil.getUserInfo();
//            Long staffID = userInfo.getStaffID();
            oaLiushui.setJinbanrenStaffID(staffID);
        }
        if (oaLiushui.getLurutime() == null) {
            oaLiushui.setLurutime(new Date());
        }
        boolean update = iOaLiushuiService.updateById(oaLiushui);
        if (update) {
            ajaxJson.setMsg("财务流水信息修改成功");
        } else {
            ajaxJson.setMsg("财务流水信息修改失败");
            ajaxJson.setCode("N");
            ajaxJson.setSuccess(false);
        }

        return ajaxJson;
    }

    /**
     * 根据id批量删除财务流水信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delLiushuiInfo", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delLiushuiInfo(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] idArr = ids.split(",");
        List<String> idList = Arrays.asList(idArr);
        boolean remove = iOaLiushuiService.removeByIds(idList);
        if (remove) {
            ajaxJson.setMsg("根据id批量删除财务流水信息成功");
        } else {
            ajaxJson.setMsg("根据id批量删除财务流水信息失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 分页获取所有的流水信息
     *
     * @param size
     * @param current
     * @return
     */
    @RequestMapping(value = "/getAllLiushuiInfo", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllLiushuiInfo(@RequestParam(value = "size", defaultValue = "10") long size,
                                      @RequestParam(value = "current", defaultValue = "1") long current,
                                      Long liushuiStyleID, String id, String qiandanID, String staffName, Long paymoneystyleID,
                                      String searchDateStart, String searchDateEnd, Integer isShouruOrZhichu) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<LiushuiInfoVo> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        if (liushuiStyleID == null && id == null && qiandanID == null && staffName == null &&
                searchDateStart == null && searchDateEnd == null && isShouruOrZhichu == null && paymoneystyleID == null) {
            queryWrapper = null;
        }
        if (liushuiStyleID != null) {
            queryWrapper.eq("liushui.liushuiStyleID", liushuiStyleID);
        }
        if (id != null) {
            queryWrapper.like("liushui.id", id);
        }
        if (qiandanID != null) {
            queryWrapper.like("liushui.qiandanID", qiandanID);
        }
        if (staffName != null) {
            queryWrapper.like("staff.staffName", staffName);
        }
        if (searchDateStart != null && searchDateEnd != null) {
            queryWrapper.between("liushui.lurutime", searchDateStart, searchDateEnd);
        }
        if (isShouruOrZhichu != null) {
            queryWrapper.eq("liushui.isShouruOrZhichu", isShouruOrZhichu);
        }
        if (paymoneystyleID != null) {
            queryWrapper.eq("liushui.paymoneystyleID", paymoneystyleID);
        }

        IPage<LiushuiInfoVo> iPage = iOaLiushuiService.getAllLiushuiInfo(page, queryWrapper);
        ajaxJson.setObj(iPage);
        ajaxJson.setMsg("分页获取所有的流水信息成功");

        return ajaxJson;
    }

    /**
     * 根据id获取一个流水信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getOneLiushuiInfoById", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getOneLiushuiInfoById(Long id) {
        AjaxJson ajaxJson = new AjaxJson();
        LiushuiInfoVo liushuiInfoVo = iOaLiushuiService.getOneLiushuiInfoById(id);
        ajaxJson.setObj(liushuiInfoVo);
        ajaxJson.setMsg("根据id获取一条流水信息");

        return ajaxJson;
    }

}
