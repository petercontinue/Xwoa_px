package com.xwcloud.cloud.oa.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.OA.OaStaffpost;
import com.xwcloud.cloud.oa.service.IOaStaffpostService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/oaStaffpost")
public class OaStaffpostController {

    @Autowired
    IOaStaffpostService iOaStaffpostService;

    @RequestMapping(value = "/getAllStaffpostPages", method = RequestMethod.GET)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson getAllStaffpostPages(@RequestParam(defaultValue = "10") long size,
                                         @RequestParam(defaultValue = "1") long current,
                                         String staffpostName) {
        AjaxJson ajaxJson = new AjaxJson();
        //LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<OaStaffpost> page = new Page<>(current, size);
        QueryWrapper<OaStaffpost> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("1", 1);
        if (StringUtils.isNotBlank(staffpostName)) {
            queryWrapper.like("staffpostName", staffpostName);
        }

        Page<OaStaffpost> allStaffpostPages = iOaStaffpostService.getAllStaffpostPages(page, queryWrapper);
        ajaxJson.setObj(allStaffpostPages);
        //ajaxJson.setObj(iStaffpostService.getAllStaffpostPages(page, queryWrapper));
        return ajaxJson;
    }

    @RequestMapping(value = "/getOneStaffpostByID", method = RequestMethod.GET)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson getOneStaffpostByID(long id) {
        AjaxJson ajaxJson = new AjaxJson();
        OaStaffpost oneStaffpost = iOaStaffpostService.getOneStaffpostByID(id);
        ajaxJson.setObj(oneStaffpost);
        ajaxJson.setMsg("根据id获取岗位信息");
        return ajaxJson;
    }

    @RequestMapping(value = "/getOneStaffpostByID2/{id}", method = RequestMethod.GET)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson getOneStaffpostByID2(@PathVariable long id) {
        AjaxJson ajaxJson = new AjaxJson();
        OaStaffpost oneStaffpost = iOaStaffpostService.getOneStaffpostByID(id);
        ajaxJson.setObj(oneStaffpost);
        return ajaxJson;
    }

    @RequestMapping(value = "/editStaffpost", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson editStaffpost(@RequestBody OaStaffpost oaStaffpost) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean update = iOaStaffpostService.updateById(oaStaffpost);
        if (update) {
            ajaxJson.setMsg("修改岗位名称成功");
        } else {
            ajaxJson.setMsg("修改岗位名称失败");
            ajaxJson.setCode("N");
            ajaxJson.setSuccess(false);
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/addStaffpost", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addStaffpost(@RequestBody OaStaffpost oaStaffpost) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean save = iOaStaffpostService.save(oaStaffpost);
        if (save) {
            ajaxJson.setMsg("添加岗位名称成功");
        } else {
            ajaxJson.setMsg("添加岗位名称失败");
            ajaxJson.setCode("N");
            ajaxJson.setSuccess(false);
        }
        return ajaxJson;
    }

    @RequestMapping(value = "delStaffpost", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delStaffpost(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] delids = ids.split(",");
        List<String> myids = Arrays.asList(delids);
        iOaStaffpostService.removeByIds(myids);
        ajaxJson.setMsg("删除成功！");
        ajaxJson.setSuccess(true);
        return ajaxJson;
    }

    @ResponseBody
    @RequestMapping(value = "/GetAllstaffpostList", method = RequestMethod.GET)
    public AjaxJson GetAllstaffpostList(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iOaStaffpostService.list());
        return ajaxJson;
    }

}
