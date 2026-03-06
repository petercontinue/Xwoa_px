package com.xwcloud.cloud.homeschool.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.DateUtil;
import com.xwcloud.cloud.common.ExportExcel;
import com.xwcloud.cloud.homeschool.Service.IPxqingjiatableService;
import com.xwcloud.cloud.homeschool.Service.IPxstafftableService;
import com.xwcloud.cloud.homeschool.Service.IPxtuisongtableService;
import com.xwcloud.cloud.model.Sso.LoginUser;

import com.xwcloud.cloud.model.Vo.PxqingjiatableVo;
import com.xwcloud.cloud.model.entity.Pxqingjiatable;
import com.xwcloud.cloud.model.entity.Pxstafftable;
import com.xwcloud.cloud.model.entity.Pxtuisongtable;
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
import java.util.*;

@Controller
@RequestMapping("/homeschool/pxqingjiatable")
@Api(tags = "微请假")
public class PxqingjiatableController {

    @Autowired
    IPxqingjiatableService iPxqingjiatableService;

    @Autowired
    IPxstafftableService iPxstafftableService;

    @Autowired
    IPxtuisongtableService iPxtuisongtableService;

    @Autowired
    SendMessagehomeschool sendMessagehomeschool;

    @RequestMapping(value = "/delQingjia", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除请假")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Ids", value = "班级ID数组,以英文逗号','分割", required = true),
    })
    public AjaxJson delqingjia(HttpServletRequest request, String Ids) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        // 获取推送学生列表
        String[] IDs = Ids.split(",");
        ajaxJson.setSuccess(iPxqingjiatableService.removeByIds(Arrays.asList(IDs)));
        return ajaxJson;
    }

    @RequestMapping(value = "/getQingjiaPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取请假分页列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = true),
            @ApiImplicitParam(name = "current", value = "页码", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区Id", required = false),
            @ApiImplicitParam(name = "stuName", value = "学生名称", required = false),
            @ApiImplicitParam(name = "teacherName", value = "教师名称", required = false),
            @ApiImplicitParam(name = "startDate", value = "开始日期", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束日期", required = false),
    })
    public AjaxJson getQingjiaPage(HttpServletRequest request,
            @RequestParam(required = false, defaultValue = "10") long size,
            @RequestParam(required = false, defaultValue = "1") long current,
            @RequestParam(required = false) String campusID,
            @RequestParam(required = false) String stuName,
            @RequestParam(required = false) String teacherName,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endtDate
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<PxqingjiatableVo> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qingjia.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("stu.campusID", campusID);
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("stu.stuName", stuName);
        }
        if (StringUtils.isNotBlank(teacherName)) {
            queryWrapper.like("qingjia.teacherNames", teacherName);
        }
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("qingjia.haveClassDate", startDate);
        }
        if (StringUtils.isNotBlank(endtDate)) {
            queryWrapper.le("qingjia.haveClassDate", endtDate);
        }
        page = iPxqingjiatableService.getPage(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/changeReviewStatus", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "更改审核状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Ids", value = "需要更改的请假ID,以逗号分隔", required = true),
            @ApiImplicitParam(name = "status", value = "审核状态，0未审核，1已审核通过，2已审核未通过", required = true),
            @ApiImplicitParam(name = "shenheNopassReason", value = "未通过原因", required = false),
    })
    public AjaxJson changeReviewStatus(HttpServletRequest request,
                                       String Ids, int status,
                                       @RequestParam(required = false) String shenheNopassReason
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser user = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID",user.getQiyeID());
        queryWrapper.in("id", Arrays.asList(Ids.split(",")));
        List<Pxqingjiatable> list = iPxqingjiatableService.list(queryWrapper);
        for (int i = 0; i < list.size(); i++) {
            Pxstafftable pxstafftable= iPxstafftableService.getById(user.getStaffID());
            if (list.get(i).getShenheState()!=null&&list.get(i).getShenheState()!=0){
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg("已经审核过了");
                return ajaxJson;
            }

            list.get(i).setShenheState(status);// 更改审核状态
            list.get(i).setShenheRen(user.getStaffID()); // 审核人
            String note=pxstafftable.getStaffName()+"审批"+(status==1?"通过":"不通过")+"了："+ DateUtil.formatDate2(list.get(i).getHaveClassDate())
                    +" "+DateUtil.formatDate7(list.get(i).getStartLessonDateTime())+"-"+DateUtil.formatDate7(list.get(i).getEndLessonDateTime())+"的请假。";
            if (StringUtils.isNotBlank(shenheNopassReason)) {
                list.get(i).setShenheNopassReason(shenheNopassReason);// 审核未通过原因
                note+="；原因："+shenheNopassReason;
            }

            Pxtuisongtable pxtuisongtable=new Pxtuisongtable();
            pxtuisongtable.setTuisongTypeName(8L)
                    .setRole(1)
                    .setWxstate(0)
                    .setQiyeID(user.getQiyeID())
                    .setAddStaffID(user.getStaffID())
                    .setAddTime(new Date())
                    .setHaveclassDate(list.get(i).getHaveClassDate())
                    .setNote(note)
                    .setStuID(list.get(i).getStuid())
                    .setAppread(0);
            iPxtuisongtableService.save(pxtuisongtable);
            sendMessagehomeschool.sendMessage(pxtuisongtable.getId());

        }
        ajaxJson.setSuccess(iPxqingjiatableService.updateBatchById(list));
        return ajaxJson;
    }

    @RequestMapping(value = "/exportQingjia", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出请假")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false)
    })
    public void exportQingjia(HttpServletResponse response,HttpServletRequest request,
                              @RequestParam(required = false) String campusID // 校区ID
    ) {
        QueryWrapper queryWrapper = new QueryWrapper();
        LoginUser loginUser=(LoginUser)request.getAttribute("loginUser");
        queryWrapper.eq("qingjia.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("stu.campusID", campusID);
        }

        List<PxqingjiatableVo> pxqingjiatableVoList = iPxqingjiatableService.getJoinList(queryWrapper);
        HashMap<String, Map> caseHash= new HashMap<>();
        Map<Object,String> map =new HashMap<>();
        map.put(0,"未审核");
        map.put(1,"已审核通过");
        map.put(2,"已审核未通过");
        caseHash.put("shenheState",map);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "学生姓名", "课程名称", "班级", "班主任", "教师", "上课日期",
                        "上课时间", "请假备注", "请假审核状态", "请假审核时间"},
                pxqingjiatableVoList,
                new String[]{"campusName", "stuName", "kechengName", "className", "staffName", "teacherNames", "haveClassDate-D",
                        "startLessonDateTime-T", "beizhu", "shenheState-Case-shenheState", "addDate-DT"},caseHash);

        try {
            ExportExcel.exportExcel(response, list, "sheet1", "微请假.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
