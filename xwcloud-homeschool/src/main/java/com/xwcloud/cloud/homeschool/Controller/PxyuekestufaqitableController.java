package com.xwcloud.cloud.homeschool.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.DateUtil;
import com.xwcloud.cloud.common.ExportExcel;
import com.xwcloud.cloud.homeschool.Service.IPxpaiketableService;
import com.xwcloud.cloud.homeschool.Service.IPxpaiketeachertableService;
import com.xwcloud.cloud.homeschool.Service.IPxxuanketableService;
import com.xwcloud.cloud.homeschool.Service.IPxyuekestufaqitableService;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.entity.Pxpaiketable;
import com.xwcloud.cloud.model.entity.Pxpaiketeachertable;
import com.xwcloud.cloud.model.entity.Pxxuanketable;
import com.xwcloud.cloud.model.entity.Pxyuekestufaqitable;
import com.xwcloud.cloud.model.Vo.PxyuekestufaqitableVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/homeschool/pxyuekestufaqitable")
@Api(tags = "约课(学员发起)")
public class PxyuekestufaqitableController {

    @Autowired
    IPxyuekestufaqitableService iPxyuekestufaqitableService;
    @Autowired
    IPxpaiketableService iPxpaiketableService;
    @Autowired
    IPxpaiketeachertableService iPxpaiketeachertableService;
    @Autowired
    IPxxuanketableService iPxxuanketableService;

    @RequestMapping(value = "/getYuekePage",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取学生约课列表分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name="size",value="分页大小",required=true),
            @ApiImplicitParam(name="current",value="页码",required=true),
            @ApiImplicitParam(name="kechengName",value="课程名称",required=false),
            @ApiImplicitParam(name="stuName",value="学生名称",required=false),
            @ApiImplicitParam(name="teacherName",value="教师名称",required=false),
            @ApiImplicitParam(name="status",value="状态",required=false)
    })
    public AjaxJson getYuekePage(HttpServletRequest request,
                                 @RequestParam(required = false,defaultValue = "10")long size,
                                 @RequestParam(required = false,defaultValue = "1")long current,
                                 @RequestParam(required = false)String kechengName,
                                 @RequestParam(required = false)String stuName,
                                 @RequestParam(required = false)String teacherName,
                                 @RequestParam(required = false)String status
    ) {
        AjaxJson ajaxJson=new AjaxJson();
        LoginUser loginUser=(LoginUser)request.getAttribute("loginUser");
        Page<PxyuekestufaqitableVo> page = new Page(current, size);
        QueryWrapper queryWrapper =new QueryWrapper();
        queryWrapper.eq("stuyueke.qiyeID",loginUser.getQiyeID());
        if (StringUtils.isNotBlank(kechengName)){
            queryWrapper.like("kecheng.kechengName",kechengName);
        }
        if (StringUtils.isNotBlank(stuName)){
            queryWrapper.like("stu.stuName",stuName);
        }
        if (StringUtils.isNotBlank(teacherName)){
            queryWrapper.like("teacher.staffName",teacherName);
        }
        if (StringUtils.isNotBlank(status)){
            queryWrapper.eq("stuyueke.yuekeShenheState",status);
        }
        page= iPxyuekestufaqitableService.getPage(page,queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/auditNotPassed",method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class})
    @ApiOperation(value = "审核不通过")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="约课ID",required=true),
            @ApiImplicitParam(name="yuekeShenheState",value="约课审核状态，1未审核，2已审核通过，3审核未通过,默认值1",required=true),
            @ApiImplicitParam(name="yuekeShenheDafu",value="约课审核答复,审核通过的答复内容，或审核未通过原因",required=true)
    })
    public AjaxJson auditNotPassed(HttpServletRequest request,String id,
                                                  @RequestParam(required = false)String yuekeShenheDafu
    ) {
        AjaxJson ajaxJson=new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID",loginUser.getQiyeID());
        queryWrapper.eq("id",id);
        Pxyuekestufaqitable pxyuekestufaqitable = iPxyuekestufaqitableService.getOne(queryWrapper);
        ajaxJson.setSuccess(false);
        if (pxyuekestufaqitable!=null){
            if (pxyuekestufaqitable.getYuekeShenheState()!=null&&pxyuekestufaqitable.getYuekeShenheState()!=1){
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg("已经审核过了!");
                return  ajaxJson;
            }
            pxyuekestufaqitable.setYuekeShenheState(3);
            if (StringUtils.isNotBlank(yuekeShenheDafu)){
                pxyuekestufaqitable.setYuekeShenheDafu(yuekeShenheDafu);
            }
            pxyuekestufaqitable.setShenheDatetime(new Date());
            pxyuekestufaqitable.setShenheStaffID(loginUser.getStaffID());
            ajaxJson.setSuccess(iPxyuekestufaqitableService.updateById(pxyuekestufaqitable));
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/examinationPassed",method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class})
    @ApiOperation(value = "审核通过")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="约课ID",required=true),
            @ApiImplicitParam(name="classroomID",value="教室ID",required=true)
    })
    public AjaxJson examinationPassed(HttpServletRequest request,
                                      long id,
                                      long classroomID
    ) {
        AjaxJson ajaxJson=new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("stuyueke.qiyeID",loginUser.getQiyeID());
        queryWrapper.eq("stuyueke.id",id);
        PxyuekestufaqitableVo pxyuekestufaqitable = iPxyuekestufaqitableService.getJoinList(queryWrapper).get(0);
        if (pxyuekestufaqitable.getYuekeShenheState()!=null&&pxyuekestufaqitable.getYuekeShenheState()!=1){
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("已经审核过了!");
            return  ajaxJson;
        }
        pxyuekestufaqitable.setYuekeShenheState(2);
        pxyuekestufaqitable.setShenheStaffID(loginUser.getStaffID());
        pxyuekestufaqitable.setShenheDatetime(new Date());
        // 添加排课
        Pxpaiketable pxpaiketable = new Pxpaiketable();
        pxpaiketable.setStartLessonDateTime(pxyuekestufaqitable.getHaveLessonStartTime()); // 上课时间
        pxpaiketable.setEndLessonDateTime(pxyuekestufaqitable.getHaveLessonEndTime()); // 下课时间
        pxpaiketable.setHaveClassDate(pxyuekestufaqitable.getHaveClassDate()); // 上课日期
        pxpaiketable.setTeacherNames(pxyuekestufaqitable.getTeacherName()); // 排课老师名称
        pxpaiketable.setTeacherIDs(String.valueOf(pxyuekestufaqitable.getTeacherID())); // 排课老师ID
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("buxikecheng.id",pxyuekestufaqitable.getBuxikechengID());
        HashMap<String,String> classMap= iPxpaiketableService.getClassID(queryWrapper1).get(0);
        pxpaiketable.setClassID(Long.valueOf(classMap.get("classID"))); // 班级ID
        pxpaiketable.setClassRoomID(classroomID); // 教室ID
        String weekn = DateUtil.getWeekOfDate(pxyuekestufaqitable.getHaveClassDate());
        pxpaiketable.setWeekN(weekn); // 星期几
        pxpaiketable.setMaxStuNum(-1); // 最大学员人数 -1表示人数不限
        pxpaiketable.setKechengID(pxyuekestufaqitable.getKechengID()); // 课程ID
        pxpaiketable.setKechengContent(null); // 课程内容
        pxpaiketable.setDakaoqin(false); // 是否已完成打考勤
        String tags = DateUtil.formatDate11(new Date());
        pxpaiketable.setTags(tags);// 排课批次
        pxpaiketable.setCanqingjiaBeforeHours(0); // 允许开课前几小时内请假
        pxpaiketable.setShuakaTimeArea(1);// 刷卡或刷脸消课时间段，1课前，2课中，3课后，默认1
        iPxpaiketableService.save(pxpaiketable);
        // 添加排课老师表,约课的那个老师
        Pxpaiketeachertable pxpaiketeachertable = new Pxpaiketeachertable();
        pxpaiketeachertable.setQiyeID(pxyuekestufaqitable.getQiyeID());
        pxpaiketeachertable.setPaikeID(pxpaiketable.getId());
        pxpaiketeachertable.setTeacherID(pxyuekestufaqitable.getTeacherID());
        iPxpaiketeachertableService.save(pxpaiketeachertable);
        // 添加选课
        Pxxuanketable pxxuanketable= new Pxxuanketable();
        pxxuanketable.setPaikeID(pxpaiketable.getId());
        pxxuanketable.setRecordDate(new Date());
        pxxuanketable.setStuID(Long.valueOf(classMap.get("stuID")));
        pxxuanketable.setType(0);
        pxxuanketable.setBuxiID(pxyuekestufaqitable.getBuxikechengID());
        pxxuanketable.setQiyeID(pxyuekestufaqitable.getQiyeID());
        // 修改约课状态
        iPxyuekestufaqitableService.updateById(pxyuekestufaqitable);
        return ajaxJson;
    }

    @RequestMapping(value = "/getClassRoomList",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取教室列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="campusID",value="校区ID",required=true),
            @ApiImplicitParam(name="qiyeID",value="企业ID",required=true),
    })
    public AjaxJson getClassRoomList(HttpServletRequest request,@RequestParam(required = false)String campusID

    ) {
        AjaxJson ajaxJson=new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID",loginUser.getQiyeID());
        if (StringUtils.isNotBlank(campusID)){
            queryWrapper.eq("campusID",campusID);
        }
         List<HashMap<String,String>> list = iPxyuekestufaqitableService.getClassRoomList(queryWrapper);
        ajaxJson.setObj(list);
        return ajaxJson;
    }

    @RequestMapping(value = "/exportStuyueke",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出学生约课")
    @ApiImplicitParams({
            //@ApiImplicitParam(name="Ids",value="班级ID数组,以英文逗号','分割",required=true),
    })
    public void exportZuoye(HttpServletRequest request,HttpServletResponse response) {
        AjaxJson ajaxJson=new AjaxJson();

        List<PxyuekestufaqitableVo>  pxyuekestufaqitableVos = iPxyuekestufaqitableService.getJoinList(null);
        HashMap<String, Map> caseHash= new HashMap<>();
        Map<Object,String> buxiStylemap =new HashMap<>();
        buxiStylemap.put(1,"一对一");
        buxiStylemap.put(2,"班课");
        caseHash.put("buxiStylemap",buxiStylemap);
        Map<Object,String> ShenheStatemap =new HashMap<>();
        ShenheStatemap.put(1,"未审核");
        ShenheStatemap.put(2,"已审核通过");
        ShenheStatemap.put(3,"审核未通过");
        caseHash.put("ShenheStatemap",ShenheStatemap);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"课程名称","发起学生","班主任","教师","上课日期",
                        "开始时间","结束时间","类型","状态","发起时间"},
                pxyuekestufaqitableVos,
                new String[]{"kechengName","stuName","banzhurenName","teacherName","haveClassDate-D","haveLessonStartTime-T",
                        "haveLessonEndTime","buxiStyle-Case-buxiStylemap","yuekeShenheState-Case-ShenheStatemap","addTime-DT"},
                caseHash
                );
        try {
            ExportExcel.exportExcel(response,list,"Sheet1","导出学生约课.xls",15);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
