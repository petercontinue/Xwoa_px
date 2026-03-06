package com.xwcloud.cloud.caiwu.Controller.other;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.caiwu.Service.IPxqiandansubjecttableService;
import com.xwcloud.cloud.caiwu.Service.IPxsubjecttableService;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.Sso.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@RequestMapping("/shujutongji/other")
@Api(tags = "科目报名统计")
public class SubjectBmTongjiController {

    @Autowired
    private IPxsubjecttableService pxsubjecttableService;
    @Autowired
    private IPxqiandansubjecttableService pxqiandansubjecttableService;

    @GetMapping("/getSubjectBmTongji")
    @ResponseBody
    @ApiOperation(value = "科目报名统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID"),
            @ApiImplicitParam(name = "subjectID", value = "科目ID"),
            @ApiImplicitParam(name = "datesoe", value = "签单时间：格式--->start_end"),
    })
    public AjaxJson getSubjectBmTongji(HttpServletRequest request,long size,long current, Long campusID, Long subjectID, String datesoe){
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<HashMap<String, Object>> wrapper = new QueryWrapper<>();
        wrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (!ObjectUtils.isEmpty(campusID)){
            wrapper.eq("a.campusID", campusID);
        }
        if (!ObjectUtils.isEmpty(subjectID)){
            wrapper.eq("a.id", subjectID);
        }
        if (!ObjectUtils.isEmpty(datesoe)){
            String[] s = (datesoe + " 23:59:59").split("_");
            wrapper.between("e.qiandandate", s[0], s[1]);
        }
        Page<HashMap<String, Object>> list = pxsubjecttableService.getSubjectBmTongji(new Page(current, size), wrapper);
        ajaxJson.setObj(list);
        ajaxJson.setCode("success");
        return ajaxJson;
    }

    @GetMapping("/getSubjectBmByCampusAndSubject")
    @ResponseBody
    @ApiOperation(value = "科目报名统计明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true),
            @ApiImplicitParam(name = "subjectID", value = "科目ID", required = true),
    })
    public AjaxJson getSubjectBmByCampusAndSubject(HttpServletRequest request,long size,long current, long campusID, long subjectID){
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<HashMap<String, Object>> wrapper = new QueryWrapper<>();
        wrapper
                .eq("c.subjectID", subjectID)
                .eq("e.campusID", campusID)
                .eq("a.qiyeID", loginUser.getQiyeID());
        Page<HashMap<String, Object>> page = pxqiandansubjecttableService.getSubjectBmByCampusAndSubject(new Page(current, size), wrapper);
        ajaxJson.setObj(page);
        ajaxJson.setCode("success");
        return ajaxJson;
    }

}
