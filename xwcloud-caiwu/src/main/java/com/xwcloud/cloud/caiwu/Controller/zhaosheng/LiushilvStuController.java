package com.xwcloud.cloud.caiwu.Controller.zhaosheng;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.caiwu.Service.IPxkeshistutableService;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.Vo.LiushilvStuVo;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

@Controller()
@RequestMapping("/shujutongji/liushilvStu")
@Api(tags = "学员流失率")
public class LiushilvStuController {

    //region server注入
    @Autowired
    private IPxkeshistutableService pxkeshistutableService;
    //endregion

    //region 教师学员流失率
    @GetMapping("/getTeaStuLiushiPage")
    @ResponseBody
    @ApiOperation("教师学员流失率")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "每页显示条数"),
            @ApiImplicitParam(name = "campusID", value = "校区ID"),
            @ApiImplicitParam(name = "staffName", value = "教师名字"),
            @ApiImplicitParam(name = "datesoe", value = "按上课日期筛选，格式：yyyy-MM-dd_yyyy-MM-dd"),
    })
    public AjaxJson getTeaStuLiushiPage(HttpServletRequest request,
                                        long current, long size,
                                        Long campusID, String staffName, String datesoe, Integer type) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<Object> wrapper = new QueryWrapper<>();
        wrapper
                .eq("a.qiyeID", loginUser.getQiyeID());
        if (!ObjectUtils.isEmpty(campusID)) {
            wrapper.eq("a.campusID", campusID);
        }
        if (!ObjectUtils.isEmpty(staffName)) {
            wrapper.like("a.staffName", staffName);
        }

        QueryWrapper queryWrapper = new QueryWrapper();
        if (!ObjectUtils.isEmpty(type)) {
            LocalDate now = LocalDate.now();
            LocalTime today2359 = LocalTime.of(23, 59, 59);
            LocalDateTime localDateTime = LocalDateTime.of(now, today2359);
            switch (type) {
                case 1:
                    //本月
                    queryWrapper.between("c.addtime", now.with(TemporalAdjusters.firstDayOfMonth()), localDateTime.with(TemporalAdjusters.lastDayOfMonth()));
                    break;
                case 2:
                    //下月
                    queryWrapper.between("c.addtime", now.plusMonths(1).with(TemporalAdjusters.firstDayOfMonth()),
                            localDateTime.plusMonths(1).with(TemporalAdjusters.lastDayOfMonth()));
                    break;
                case 3:
                    //指定时间段
                    if (!ObjectUtils.isEmpty(datesoe)) {
                        String[] s = (datesoe + " 23:59:59").split("_");
                        queryWrapper.between("c.addtime", s[0], s[1]);
                    }
                    break;
            }
        } else {
            queryWrapper.eq("1", "1");
        }
        Page<LiushilvStuVo> page = pxkeshistutableService.getTeaStuLiushiPage(new Page<>(current, size), wrapper, queryWrapper);
        ajaxJson.setObj(page);
        ajaxJson.setCode("success");
        return ajaxJson;
    }
    //endregion

    //region 班主任学员流失率
    @GetMapping("/getBanzhurenStuLiushiPage")
    @ResponseBody
    @ApiOperation("班主任学员流失率")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "每页显示条数"),
            @ApiImplicitParam(name = "campusID", value = "校区ID"),
            @ApiImplicitParam(name = "staffName", value = "教师名字"),
            @ApiImplicitParam(name = "datesoe", value = "按上课日期筛选，格式：yyyy-MM-dd_yyyy-MM-dd"),
    })
    public AjaxJson getBanzhurenStuLiushiPage(HttpServletRequest request,
                                              long current, long size,
                                              Long campusID, String staffName, String datesoe, Integer type) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<Object> wrapper = new QueryWrapper<>();
        wrapper
                .orderByDesc("allstunum - zaistunum")
                .eq("a.qiyeID", loginUser.getQiyeID());
        if (!ObjectUtils.isEmpty(campusID)) {
            wrapper.eq("a.campusID", campusID);
        }
        if (!ObjectUtils.isEmpty(staffName)) {
            wrapper.like("a.staffName", staffName);
        }
        if (!ObjectUtils.isEmpty(type)) {
            LocalDate now = LocalDate.now();
            LocalTime today2359 = LocalTime.of(23, 59, 59);
            LocalDateTime localDateTime = LocalDateTime.of(now, today2359);
            switch (type) {
                case 1:
//                    本月
                    wrapper.between("b.haveClassDate", now.with(TemporalAdjusters.firstDayOfMonth()), localDateTime.with(TemporalAdjusters.lastDayOfMonth()));
                    break;
                case 2:
//                    下月
                    wrapper.between("b.haveClassDate", now.plusMonths(1).with(TemporalAdjusters.firstDayOfMonth()),
                            localDateTime.plusMonths(1).with(TemporalAdjusters.lastDayOfMonth()));
                    break;
                case 3:
//                    指定时间段
                    if (!ObjectUtils.isEmpty(datesoe)) {
                        String[] s = (datesoe + " 23:59:59").split("_");
                        wrapper.between("b.haveClassDate", s[0], s[1]);
                    }
                    break;
            }
        }
        Page<LiushilvStuVo> page = pxkeshistutableService.getBanzhurenStuLiushiPage(new Page<>(current, size), wrapper);
        ajaxJson.setObj(page);
        ajaxJson.setCode("success");
        return ajaxJson;
    }
    //endregion


    //region 校区学员流失率
    @GetMapping("/getCampusStuLiushiPage")
    @ResponseBody
    @ApiOperation("班主任学员流失率")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "每页显示条数"),
            @ApiImplicitParam(name = "campusID", value = "校区ID"),
            @ApiImplicitParam(name = "datesoe", value = "按上课日期筛选，格式：yyyy-MM-dd_yyyy-MM-dd"),
    })
    public AjaxJson getCampusStuLiushiPage(HttpServletRequest request,
                                           long current, long size,
                                           Long campusID, String datesoe, Integer type
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<Object> wrapper = new QueryWrapper<>();
        wrapper
                .eq("a.qiyeID", loginUser.getQiyeID());
        if (!ObjectUtils.isEmpty(campusID)) {
            wrapper.eq("a.campusID", campusID);
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(1, 1);
        if (type == 3) { //0:全部 1：本月 2：下月 3：自定义查询
            String[] s = (datesoe + " 23:59:59").split("_");
            wrapper.between("c.addtime", s[0], s[1]);
        }
        Page<LiushilvStuVo> page = pxkeshistutableService.getCampusStuLiushiPage(new Page<>(current, size), wrapper, type, queryWrapper);


        ajaxJson.setObj(page);
        return ajaxJson;
    }
    //endregion

}
