package com.xwcloud.cloud.homeschool.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.ExportExcel;
import com.xwcloud.cloud.homeschool.Service.IPxtousutableService;
import com.xwcloud.cloud.model.Sso.LoginUser;

import com.xwcloud.cloud.model.Vo.PxstuFeedbackVo;
import com.xwcloud.cloud.overall.LogUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/homeschool/pxtousutable")
@Api(tags = "学员反馈")
public class PxstuFeedbacktableController {
    @Autowired
    IPxtousutableService iPxtousutableService;
    @Autowired
    LogUtils logUtils;

    @RequestMapping(value = "/getStuFeedback",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取学员反馈分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name="size",value="分页大小",required=false),
            @ApiImplicitParam(name="current",value="页码",required=false)
    })
    public AjaxJson getStuFeedback(HttpServletRequest request,
                                   @RequestParam(required = false,defaultValue = "10")long size,
                                   @RequestParam(required = false,defaultValue = "1")long current,
                                   @RequestParam(required = false)String stuName,
                                   @RequestParam(required = false)String parentTel
    ) {
        AjaxJson ajaxJson=new AjaxJson();
        LoginUser loginUser=(LoginUser)request.getAttribute("attr");
        Page<PxstuFeedbackVo> page =new Page<>(current,size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("tousu.qiyeID",loginUser.getQiyeID());
        if (StringUtils.isNotBlank(stuName)){
            queryWrapper.like("stu.stuName",stuName);
        }
        if (StringUtils.isNotBlank(parentTel)){
            queryWrapper.like("stu.parentTel",parentTel);
        }
        page=iPxtousutableService.getPage(page,queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/exportStuFeedback",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出学员反馈")
    public void exportStuFeedback(HttpServletRequest request, HttpServletResponse response
                                      ) {
        AjaxJson ajaxJson=new AjaxJson();
        List<PxstuFeedbackVo> pxstuFeedbackVoList =iPxtousutableService.getJoinList(null);

        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区","学员姓名","班主任","年级","家长账号","反馈内容","反馈时间",
                        "查阅状态","查阅时间","查阅人"},
                pxstuFeedbackVoList,
                new String[]{"campusName","stuName","banzhurenName","stuGradeName","parentTel","tousuContent","tousuDate-DT",
                        "chayueState","chayueDate-DT","chayueSatffName"});
        try {
            ExportExcel.exportExcel(response,list,"Sheet1","学员反馈导出.xls",15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
