package com.xwcloud.cloud.sys.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.ExportExcel;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.Vo.LogxjbVo;
import com.xwcloud.cloud.sys.Service.ILogxjbtableService;
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

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-25
 */
@Controller
@RequestMapping("/sys/SystemLog")
@Api(tags = "系统日志")
public class SystemLogController {
    @Autowired
    ILogxjbtableService iLogxjbtableService;

//    @Autowired
//    private RedisUtil redisUtil;

    //region 操作日志

    /**
     * 分页查询所有操作日志
     *
     * @param size
     * @param current
     * @return
     */
    @RequestMapping(value = "/GetAllLogsPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询所有操作日志")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页码", example = "1", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "staffID", value = "员工ID", required = false),
            @ApiImplicitParam(name = "logType", value = "日志类别", required = false)
    })
    public AjaxJson GetAllLogsPages(long size, long current, @RequestParam(required = false) String campusID, @RequestParam(required = false) String staffID,
                                    @RequestParam(required = false) Integer logType,String SDate,String Edate, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Page<LogxjbVo> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("b.campusID", campusID);
        }
        if (StringUtils.isNotBlank(staffID)) {
            queryWrapper.like("b.staffName", staffID);
        }
        if (logType != 0) {
            queryWrapper.eq("a.logType", logType);
        }
        if(StringUtils.isNotBlank(SDate)){
            queryWrapper.ge("a.addTime",SDate);
        }
        if(StringUtils.isNotBlank(Edate)){
            queryWrapper.le("a.addTime",Edate);
        }
        ajaxJson.setObj(iLogxjbtableService.getLogxjbInfo(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * 导出日志信息
     *
     * @param response
     * @param staffID
     * @param campusID
     * @param startDate
     * @param endDate
     */
    @RequestMapping(value = "/exportlogInfos", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("导出日志信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "staffID", value = "员工ID", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "startDate", value = "开始时间", required = false),
            @ApiImplicitParam(name = "endDate", value = "结束时间", required = false)
    })
    public void exportlogInfos(HttpServletResponse response, String staffID,//员工ID
                               @RequestParam(required = false) String campusID, // 校区ID
                               @RequestParam(required = false) String startDate, // 开始日期
                               @RequestParam(required = false) String endDate ,// 结束日期
                               Integer logType,HttpServletRequest request
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID",qiyeID);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("c.id", campusID);
        }
        if (StringUtils.isNotBlank(staffID)) {
            queryWrapper.eq("a.staffID", staffID);
        }
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("a.addTime", startDate);
        }

        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("a.addTime", endDate);
        }
        if(logType!=0){
            queryWrapper.eq("a.logType",logType);
        }
        List<LogxjbVo> pxlogxjb = iLogxjbtableService.getLogxjbInfolist(queryWrapper);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"日志时间", "校区", "操作人", "操作类型", "操作日志详情"},
                pxlogxjb,
                new String[]{"addTime", "campusName", "staffName", "logType", "systemContent"});

        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "操作日志信息导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //endregion

}
