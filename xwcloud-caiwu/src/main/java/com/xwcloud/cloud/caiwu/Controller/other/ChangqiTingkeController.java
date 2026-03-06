package com.xwcloud.cloud.caiwu.Controller.other;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.caiwu.Service.IPxpowertableService;
import com.xwcloud.cloud.caiwu.Service.IPxqiandansubjecttableService;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.ExportExcel;
import com.xwcloud.cloud.model.Sso.LoginUser;
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
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/shujutongji/other")
@Api(tags = "其他统计")
public class ChangqiTingkeController {
    @Autowired
    IPxqiandansubjecttableService iPxqiandansubjecttableService;
    @Autowired
    IPxpowertableService iPxpowertableService;


    @RequestMapping(value = "/getTingkeStu", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "长期停课学员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDate", value = "开始日期", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束日期", required = false)
    })
    public AjaxJson getTingkeStu(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(required = false, defaultValue = "10") long size,
                                 @RequestParam(required = false, defaultValue = "1") long current,
                                 @RequestParam(required = false) Integer tingkeType,
                                 @RequestParam(required = false) String stuName,
                                 @RequestParam(required = false) String xuehao,
                                 @RequestParam(required = false) String gradeID,
                                 @RequestParam(required = false) Long campusID
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        Page<HashMap<String, String>> page = new Page<>(current, size);
        QueryWrapper<HashMap<String, String>> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tt.qiyeID", loginUser.getQiyeID());
        if (tingkeType != null) {
            switch (tingkeType) {
                case 2: // 1~2个月
                    queryWrapper.apply("timestampdiff(MONTH,tt.haveClassDate,NOW())>=0 AND timestampdiff(MONTH,tt.haveClassDate,NOW())<=2");
                    break;
                case 3: // 3~5个月
                    queryWrapper.apply("timestampdiff(MONTH,tt.haveClassDate,NOW())>=3 AND timestampdiff(MONTH,tt.haveClassDate,NOW())<=5");
                    break;
                case 4: // 6个月以上
                    queryWrapper.apply("timestampdiff(MONTH,tt.haveClassDate,NOW())>=6 ");
                    break;
            }
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("tt.stuName", stuName);
        }
        if (StringUtils.isNotBlank(xuehao)) {
            queryWrapper.like("tt.zidingyiStuID", xuehao).or().like("tt.id", xuehao);
        }
        if (StringUtils.isNotBlank(gradeID)) {
            queryWrapper.eq("tt.stuGradeID", gradeID);
        }


        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 442);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("tt.banzhurenTeacherID", loginUser.getStaffID());
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("tt.campusID",loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("tt.campusID",lookPower);
        }
        if (campusID!=0) {
            queryWrapper.eq("tt.campusID", campusID);
        }
        queryWrapper.orderByDesc("tt.tingkeTime");
        page = iPxqiandansubjecttableService.getTingkeStu(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/exportTingkeStu", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出长期停课学员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDate", value = "开始日期", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束日期", required = false),
    })
    public void exportTingkeStu(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(required = false) String gradeID,
                                @RequestParam(required = false) String campusID,
                                @RequestParam(required = false) String startDate,
                                @RequestParam(required = false) String endDate
//                                String qiyeID
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("stu.qiyeID", loginUser.getQiyeID());

        if (StringUtils.isNotBlank(gradeID)) {
            queryWrapper.eq("stugrade.id", gradeID);
        }
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("campus.id", campusID);
        }
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("stu.tingkeTime", startDate);
        }
        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("stu.tingkeTime", endDate);
        }
        List<HashMap<String, Object>> list = iPxqiandansubjecttableService.getTingkeStuList(queryWrapper);
        List<List<Object>> returnlist = ExportExcel.formatHashMapDataToList(
                new String[]{ "学号", "姓名", "年级", "最后一次上课时间", "停课天数", "班主任姓名" },
                list,
                new String[]{"campusName", "stuName", "stuGradeName", "maxhave", "tingke", "staffName"});
        try {
            ExportExcel.exportExcel(response, returnlist, "长期停课预警", "长期停课预警.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
