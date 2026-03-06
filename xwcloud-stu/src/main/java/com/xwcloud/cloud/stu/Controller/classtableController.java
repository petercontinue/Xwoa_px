package com.xwcloud.cloud.stu.Controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.DateUtil;
import com.xwcloud.cloud.common.ExportExcel;
import com.xwcloud.cloud.model.Form.*;
import com.xwcloud.cloud.model.Form.stu.*;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.*;
import com.xwcloud.cloud.stu.Service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-13
 */
@Controller
@RequestMapping("/stu/stuClass")
@Api(tags = "学员班级")
@Transactional(rollbackFor = Exception.class)
public class classtableController {

    //region 注入服务集
    @Autowired
    IPxclasstableService iPxclasstableService;

    @Autowired
    IPxpaiketableService iPxpaiketableService;

    @Autowired
    IPxpaiketeachertableService iPxpaiketeachertableService;


    @Autowired
    IPxbuxikechengtableService iPxbuxikechengtableService;

    @Autowired
    IPxxuanketableService iPxxuanketableService;

    @Autowired
    IPxstuclasstableService iPxstuclasstableService;

    @Autowired
    IPxsysparamdefaulttableService iPxsysparamdefaulttableService;

    @Autowired
    IPxsysparamvaluetableService iPxsysparamvaluetableService;

    @Autowired
    IPxkechengtableService iPxkechengtableService;

    @Autowired
    IPxkeshistutableService iPxkeshistutableService;

    @Autowired
    IPxstukaoqingtableService iPxstukaoqingtableService;

    @Autowired
    IPxstutableService iPxstutableService;

    @Autowired
    IPxclasstimestyletableService iPxclasstimestyletableService;

    @Autowired
    IPxstukxqtableService iPxstukxqtableService;

    @Autowired
    IPxbuxistyletableService iPxbuxistyletableService;

    @Autowired
    IPxsubjecttableService iPxsubjecttableService;

    @Autowired
    IPxpowertableService iPxpowertableService;

    @Autowired
    IPxkeshiteachertableService iPxkeshiteachertableService;
    //endregion

    //region 学员班级


    /**
     * @Description: getStuClassPage方法作用:分页获取学员班级
     * @param:[current, size, is1v1Class, className, campusName, jingbanren, addTime, isShow]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:35
     */
    @ApiOperation(value = "分页获取学员班级")
    @RequestMapping(value = "getStuClassPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "is1v1Class", value = "班级类型", required = true),
            @ApiImplicitParam(name = "className", value = "班级名称", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区", required = false),
            @ApiImplicitParam(name = "jingbanren", value = "建班人", required = false),
            @ApiImplicitParam(name = "startDate", value = "建班时间", required = false),
            @ApiImplicitParam(name = "endDate", value = "建班时间", required = false),
            @ApiImplicitParam(name = "isShow", value = "班级是否启用：全部：-1 启用：0 不启用：1", required = true),
    })
    public AjaxJson getStuClassPage(
            Long current,
            Long size,
            int is1v1Class,
            String className,
            String campusID,
            String jingbanren,
            String startDate,
            String endDate,
            int isShow,
            HttpServletRequest request
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Page<classTyleVo> page = new Page<>(current, size);
        QueryWrapper<classTyleVo> queryWrapper = new QueryWrapper();
        Long menuID = null;
        if (is1v1Class == 0) {
            menuID = 231L;  //is1v1Class=0是班课
        } else if (is1v1Class == 1) {
            menuID = 232L;  //is1v1Class=1是一对一班级
        }

        //判断是否是1对1班级
        queryWrapper
                .eq("cl.is1v1Class", is1v1Class)
                .eq("cl.qiyeID", qiyeID);
        if (isShow != -1) {
            queryWrapper.eq("cl.isShow", isShow);
        }
        if (StringUtils.isNotBlank(className)) {
            queryWrapper.like("cl.className", className);
        }
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("cl.campusID", campusID);
        }
        if (StringUtils.isNotBlank(jingbanren)) {
            queryWrapper.like("pxstafftable.staffName", jingbanren);
        }
        if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {
            queryWrapper.ge("cl.addTime", startDate).le("cl.addTime", endDate);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", menuID);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("cl.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("cl.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("cl.campusID", lookPower);
        }
        queryWrapper.orderByDesc("cl.addTime");
        Page<classTyleVo> allClass = iPxclasstableService.getStuClass(page, queryWrapper);
        ajaxJson.setObj(allClass);
        return ajaxJson;
    }

    //region 学员班级按钮事件


    /**
     * @Description: addClass方法作用:添加学员班级
     * @param:[pxclasstable]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:35
     */
    @RequestMapping(value = "addClass", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加班级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "className", value = "班级名称", required = true),
            @ApiImplicitParam(name = "zidingyiClassID", value = "自定义班级ID", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区", required = true),
            @ApiImplicitParam(name = "maxStuNum", value = "班级最大人数", required = false),
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addClass(@RequestBody addClassForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());

        if (StringUtils.isNotBlank(form.getZidingyiClassID())) {
            List<Pxclasstable> zdID = iPxclasstableService.getClasszdID(form.getZidingyiClassID(), Long.valueOf(qiyeID));
            if (zdID.size() > 0) {
                ajaxJson.setMsg("自定义班级名称已存在，请跟换重试！");//自定班级ID重复
                ajaxJson.setCode("N");
                return ajaxJson;
            }
        }
        List<Pxclasstable> clName = iPxclasstableService.getClassName(form.getClassName(), Long.valueOf(qiyeID));
        if (clName.size() > 0) {
            ajaxJson.setMsg("班级名称已经存在，请更换重试！"); //班级名字重复
            ajaxJson.setCode("N");
            return ajaxJson;
        }

        int maxStuNumInt = -1;   //班级最大人数，-1表示不限制，默认值-1
        if (StringUtils.isNotBlank(form.getMaxStuNum())) {
            maxStuNumInt = Integer.valueOf(form.getMaxStuNum());
            if (maxStuNumInt <= 0 || maxStuNumInt > 10000) {
                ajaxJson.setMsg("班级最大人数必须是1~10000之间的数值！"); //班级名字重复
                ajaxJson.setCode("N");
                return ajaxJson;
            }
        }

        Pxclasstable add = new Pxclasstable();
        add.setZidingyiClassID(form.getZidingyiClassID());
        add.setClassName(form.getClassName());
        add.setCampusID(Long.valueOf(form.getCampusID()));
        add.setMaxStuNum(maxStuNumInt);   //班级最大人数，-1表示不限制，默认值-1
        add.setIs1v1Class(0);
        add.setIsShow(1);
        add.setClassState(0);
        add.setQiyeID(qiyeID);
        add.setAddStaffID(staffID);
        add.setAddTime(new Date());
        add.setIsdelete(false);
        ajaxJson.setSuccess(iPxclasstableService.save(add));
        return ajaxJson;
    }

    /**
     * @Description: getupdateClass()方法作用:修改班级获取选中班级
     * @param:[id]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/2/3 17:41
     */
    @RequestMapping(value = "getupdateClass", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "修改班级获取选中班级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "班级ID", required = true),
    })
    public AjaxJson getupdateClass(String id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxclasstableService.getById(id));
        return ajaxJson;
    }

    /**
     * @Description: updateClass方法作用:修改班级名称
     * @param:[pxclasstable]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:36
     */
    @RequestMapping(value = "updateClass", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改班级")
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson updateClass(@RequestBody updateClassForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Long staffID = Long.valueOf(loginUser.getStaffID());
        if (StringUtils.isNotBlank(form.getZidingyiClassID())) {
            List<Pxclasstable> zdID = iPxclasstableService.getupdateClasszdID(form.getZidingyiClassID(), qiyeID, Long.valueOf(form.getId()));
            if (zdID.size() > 0) {
                ajaxJson.setMsg("自定义班级名称已存在，请跟换重试！");//自定班级ID重复
                ajaxJson.setCode("N");
                return ajaxJson;
            }
        }
        List<Pxclasstable> clName = iPxclasstableService.getupdateClassName(form.getClassName(), qiyeID, Long.valueOf(form.getId()));
        if (clName.size() > 0) {
            ajaxJson.setMsg("班级名称已经存在，请更换重试！"); //班级名字重复
            ajaxJson.setCode("N");
            return ajaxJson;
        }

        Pxclasstable editclass = iPxclasstableService.getById(form.getId());
        //如果修改前后的校区不同，说明改校区了，要做一些判断和处理才行  2021-6-26 贺金平 添加
        if (!editclass.getCampusID().equals(form.getCampusID())) {
            //如果班级里有学员，班级是不可以更改校区的
            List<Pxstuclasstable> getstuclass = iPxstuclasstableService.getstuclass(Long.valueOf(form.getId()), qiyeID);
            if (getstuclass.size() > 0) {
                //即使是一对一班级不可以直接换校区，班级换到新校区，学员还在老校区，那就出错了
                ajaxJson.setMsg("班级里有学员，班级是不可以更改校区的！");
                ajaxJson.setCode("N");
                return ajaxJson;
            }
            //如果班级有课耗记录，不允许改校区!
            List<Pxkeshistutable> pxkeshistutables = iPxclasstableService.getkeHao(Long.valueOf(form.getId()), qiyeID);
            if (pxkeshistutables.size() > 0) {
                ajaxJson.setMsg("该班级有课耗记录，不允许改校区!");
                ajaxJson.setCode("N");
                return ajaxJson;
            }

            //班级里虽然没有学员，但如果该班级有排课，也要把该班级的排课全部删除，才能改校区
            //删除排课 -获取到的排课ID-转列表
            paikeVo paiKe = iPxclasstableService.getPaiKe(Long.valueOf(form.getId()), qiyeID);
            if (paiKe != null) {
                String[] PIDS = paiKe.getIDS().split(",");
                for (String onepk : PIDS) {
                    //删除对应的Pxpaiketeachertable表里的数据
                    //获取paiketeacherID
                    paiketeacherVo ptID = iPxpaiketeachertableService.getPtID(Long.valueOf(onepk), qiyeID);
                    String pptID = ptID.getIDTS();
                    String[] PtIDList = pptID.split(",");
                    if (PtIDList.length > 0) {
                        iPxpaiketeachertableService.removeByIds(Arrays.asList(PtIDList));//删除pxpaiketeacher表数据
                    }
                    //删除对应的Pxxuanketable选课表里的数据
                    List<Pxxuanketable> pxxuanketables = iPxxuanketableService.allxuankebypkID(Long.valueOf(onepk), qiyeID);
                    if (pxxuanketables.size() > 0) {
                        iPxxuanketableService.removeByIds(pxxuanketables);
                    }

                }
                iPxpaiketableService.removeByIds(Arrays.asList(PIDS));//删除排课表数据
            }
        }
        //这里才开始修改班级
        editclass.setZidingyiClassID(form.getZidingyiClassID());
        editclass.setClassName(form.getClassName());
        editclass.setCampusID(Long.valueOf(form.getCampusID()));
        editclass.setMaxStuNum(form.getMaxStuNum());
        ajaxJson.setSuccess(iPxclasstableService.updateById(editclass));
        return ajaxJson;
    }


    /**
     * @Description: delClass方法作用:删除班级
     * @param:[classID]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:36
     */
    @ApiOperation("删除学员班级")
    @ResponseBody
    @RequestMapping(value = "delClass", method = RequestMethod.DELETE)
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delClass(String ids, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        BigDecimal l = new BigDecimal(0);
//        List<stuIDVo> VoList = JSON.parseArray(ids, stuIDVo.class);

        for (String item : ids.split(",")) {
            String className = "";
            long Id = Long.valueOf(item);
            Pxclasstable cla = iPxclasstableService.getById(Id);

            //班级有课耗记录，不允许被删除!
            List<Pxkeshistutable> pxkeshistutables = iPxclasstableService.getkeHao(Long.valueOf(Id), qiyeID);
            if (pxkeshistutables.size() > 0) {
                ajaxJson.setMsg(cla.getClassName() + "-该班级有课耗记录，不允许被删除!");//有课耗，不可删除
                ajaxJson.setCode("N");
                return ajaxJson;
            }

            //不管是一对一还是班级，删除班级都必须删除该班级的排课（还要删除对应的Pxpaiketeachertable表里的数据和对应的Pxxuanketable选课表里的数据）
            //删除某个班级的所有排课
            List<Pxpaiketable> oneClassAllpaike = iPxpaiketableService.getpkBYClassID(Id, qiyeID);
            if (oneClassAllpaike.size() > 0) {
                for (Pxpaiketable onepk : oneClassAllpaike) {
                    paiketeacherVo ptID = iPxpaiketeachertableService.getPtID(Long.valueOf(onepk.getId()), qiyeID);
                    String pptID = ptID.getIDTS();
                    String[] PtIDList = pptID.split(",");
                    if (PtIDList.length > 0) {
                        iPxpaiketeachertableService.removeByIds(Arrays.asList(PtIDList));//删除pxpaiketeacher表数据
                    }
                    //删除对应的Pxxuanketable选课表里的数据
                    List<Pxxuanketable> pxxuanketables = iPxxuanketableService.allxuankebypkID(Long.valueOf(onepk.getId()), qiyeID);
                    if (pxxuanketables.size() > 0) {
                        iPxxuanketableService.removeByIds(pxxuanketables);
                    }
                }
                //删除该班所有排课
                iPxpaiketableService.removeByIds(oneClassAllpaike);
            }

//            paikeVo paiKe = iPxclasstableService.getPaiKe(item.getId(), qiyeID);
//            if (paiKe != null) {
//                String[] PIDS = paiKe.getIDS().split(",");
//                for (String onepk : PIDS) {
//                    //删除对应的Pxpaiketeachertable表里的数据
//                    //获取paiketeacherID
//                    paiketeacherVo ptID = iPxpaiketeachertableService.getPtID(Long.valueOf(onepk), qiyeID);
//                    String pptID = ptID.getIDTS();
//                    String[] PtIDList = pptID.split(",");
//                    if (PtIDList.length > 0) {
//                        iPxpaiketeachertableService.removeByIds(Arrays.asList(PtIDList));//删除pxpaiketeacher表数据
//                    }
//                    //删除对应的Pxxuanketable选课表里的数据
//                    List<Pxxuanketable> pxxuanketables = iPxxuanketableService.allxuankebypkID(Long.valueOf(onepk), qiyeID);
//                    if(pxxuanketables.size()>0){
//                        iPxxuanketableService.removeByIds(pxxuanketables);
//                    }
//                }
//                iPxpaiketableService.removeByIds(Arrays.asList(PIDS));//删除排课表数据
//            }

            //如果是一对一班级，没有剩余课时才能删除，删除一对一班级的同时，会自动把对应的一对一课程也删除掉；删除班级前，还要自动操作学员退班；
            //如果不是一对一班级，班里的所有学员自动退班，然后删除该班级；
            if (cla.getIs1v1Class() == 1) {
                //如果是一对一的班，删除的前提：没有课耗，且没有剩余课时； 删除班级的同时，会把这个学生的这门课程也删除掉；
                List<Pxstuclasstable> stuclatab = iPxstuclasstableService.getstuclass(Id, qiyeID);
                if (stuclatab.size() > 0) {
                    //有人在班级里面
                    for (Pxstuclasstable stubxkc : stuclatab) {
                        Pxbuxikechengtable bxkc = iPxbuxikechengtableService.getById(stubxkc.getBuxiID());
                        if (bxkc != null) {
                            if (!(bxkc.getRemainkeshi().compareTo(l) == 0)) { //不等于0
                                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                                ajaxJson.setCode("N");
                                ajaxJson.setMsg("学员一对一课程的班级有剩余课时，该班级不允许被删除!");
                                return ajaxJson;
                            }
                        } else {
                            //补习课程表为空，则删除stuclassTable对应的记录
                            //从选课表里删除之后再退班
                            List<Pxxuanketable> stuXuankeAll = iPxxuanketableService.getBybxID(stubxkc.getBuxiID(), qiyeID);
                            if (stuXuankeAll.size() > 0) {
                                for (Pxxuanketable dxk : stuXuankeAll) {
                                    iPxxuanketableService.removeById(dxk.getId());
                                }
                            }
                            iPxstuclasstableService.removeById(stubxkc.getId());  //退班
                        }
                    }

                    className += cla.getClassName();
                    iPxclasstableService.removeById(Id);
                } else {
                    //没有人在班级内
                    className += cla.getClassName();
                    iPxclasstableService.removeById(Id);
                }
            } else {
                //如果不是一对一的班级
                //如果班级里有学员，先把班里的学员全部自动退班
                List<Pxstuclasstable> stuclatab = iPxstuclasstableService.getstuclass(Id, qiyeID);
                if (stuclatab.size() > 0) {
                    for (Pxstuclasstable delsl : stuclatab) {
                        iPxstuclasstableService.removeById(delsl.getId());   //学员退班
                    }
                }
                className += cla.getClassName();
                iPxclasstableService.removeById(Id);
            }
        }
        ajaxJson.setSuccess(true);
        ajaxJson.setMsg("删除成功");
        return ajaxJson;
    }


    /**
     * @Description: getClassToStu方法作用:查询学员班级
     * @param:[current, size, stuID]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:36
     */
    @ApiOperation("查询学员班级")
    @ResponseBody
    @RequestMapping(value = "getClassToStu", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true),
    })
    public AjaxJson getClassToStu(Long current, Long size, String stuID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<stuClassVo> page = new Page(current, size);
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        QueryWrapper<stuClassVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bx.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(stuID)) {
            queryWrapper.eq("bx.stuID", stuID);
        }
        ajaxJson.setObj(iPxclasstableService.getClassToStu(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * @Description: getstumingdan()方法作用:查看班级学员名单
     * @param:[current, size, classID, request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/2/4 15:16
     */
    @ApiOperation("查看班级学员名单")
    @ResponseBody
    @RequestMapping(value = "getstumingdan", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "classID", value = "班级ID", required = true)
    })
    public AjaxJson getstumingdan(Long current, Long size, String classID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<classgetstuVo> page = new Page(current, size);
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        ajaxJson.setObj(iPxbuxikechengtableService.getstumingdan(page, Long.valueOf(classID), qiyeID));
        return ajaxJson;
    }

    /**
     * @Description: UpdatestuClass方法作用:修改1v1学员班级名称
     * @param:[id, className]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:36
     */
    @ApiOperation(value = "修改1v1学员班级名称")
    @ResponseBody
    @RequestMapping(value = "UpdatestuClassbyOne", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "班级ID", required = true),
            @ApiImplicitParam(name = "className", value = "一对一班级名称", required = true),
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson UpdatestuClassbyOne(@RequestBody UpdatestuClassbyOneForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        if (!form.getClassName().contains("一对一")) {
            ajaxJson.setMsg("名称必须包含‘一对一’");//没带一对一
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        if (iPxclasstableService.getOtOName(Long.valueOf(form.getId()), form.getClassName(), qiyeID).size() > 0) {
            ajaxJson.setMsg("班级名称已经存在，请更换重试");//现有的名字
            ajaxJson.setCode("N");
            return ajaxJson;
        }
        Pxclasstable pxclasstable = new Pxclasstable();
        pxclasstable.setClassName(form.getClassName());
        UpdateWrapper<Pxclasstable> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", form.getId()).eq("qiyeID", qiyeID);
        ajaxJson.setSuccess(iPxclasstableService.update(pxclasstable, updateWrapper));
        return ajaxJson;
    }
    //endgion


    /**
     * @Description: UpdateOtOIsShow方法作用:班级启用/不启用
     * @param:[id, isShow, clType]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2020/11/30 16:37
     */
    @ApiOperation(value = "班级启用/不启用")
    @ResponseBody
    @RequestMapping(value = "UpdateOtOIsShow", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson UpdateOtOIsShow(@RequestBody stuclassFrom from) {
        AjaxJson ajaxJson = new AjaxJson();

//        QueryWrapper<Pxclasstable> queryWrapper = new QueryWrapper<>();
        String IDs = from.getIds();
        int isShow = from.getIsShow();
        List<stuIDVo> VoList = JSON.parseArray(IDs, stuIDVo.class);

        for (stuIDVo item : VoList) {
            Pxclasstable oneclass = iPxclasstableService.getById(item.getId());
            if (oneclass.getIsShow() == isShow) {
                ajaxJson.setMsg("班级：" + oneclass.getClassName() + "已经是目标状态"); //说明是要修改的状态
                ajaxJson.setCode("N");
                return ajaxJson;
            }
            if (isShow == 0) {   //2021-6-21 贺金平 添加
                //如果是要把班级设置为不启用：
                //  1 当前时间以后的该班级的排课:如果有该班级已经提前就消了课的排课，提示“现在以后的时间，有没完成的消课，不能设置为不启用”
                //  2 当前时间以后的该班级的排课:如果没有消了课的排课，全部删除这些排课，
                String nowTime = DateUtil.formatDate3(new Date());     //转换成了 yyyy-MM-dd hh:mm:ss
                QueryWrapper<Pxpaiketable> pxpaikeQueryWrapper = new QueryWrapper<Pxpaiketable>();
                pxpaikeQueryWrapper.ge("haveClassDate", nowTime);
                List<Pxpaiketable> listpk = iPxpaiketableService.list(pxpaikeQueryWrapper);
                //listpk:该班级当前时间以后的排课
                if (listpk.size() > 0) {
                    for (Pxpaiketable onepk : listpk) {
                        QueryWrapper<Pxstukaoqingtable> pxstukaoqingtableQueryWrapper = new QueryWrapper<>();
                        pxstukaoqingtableQueryWrapper.eq("classID", onepk.getClassID());
                        pxstukaoqingtableQueryWrapper.eq("haveclassDate", onepk.getHaveClassDate());
                        pxstukaoqingtableQueryWrapper.eq("startClassDateTime", onepk.getStartLessonDateTime());
                        pxstukaoqingtableQueryWrapper.eq("endClassDateTime", onepk.getEndLessonDateTime());
                        pxstukaoqingtableQueryWrapper.eq("qiyeID", onepk.getQiyeID());
                        List<Pxstukaoqingtable> listkq = iPxstukaoqingtableService.list(pxstukaoqingtableQueryWrapper);
                        //如果有该班级已经提前就消了课的排课，提示“现在以后的时间，有没完成的消课，不能设置为不启用”; 否则删除这些排课
                        if (listkq.size() > 0) {
                            ajaxJson.setCode("N");
                            ajaxJson.setMsg("现在以后的时间，该班级有没完成的消课，不能设置为不启用");
                            return ajaxJson;
                        }
                    }
                    //程序执行到这里，说明没有正在消课中的排课
                    //删除这些排课
                    for (Pxpaiketable onepk : listpk) {
                        paiketeacherVo ptID = iPxpaiketeachertableService.getPtID(Long.valueOf(onepk.getId()), Long.valueOf(oneclass.getQiyeID()));
                        if (ptID != null) {
                            String pptID = ptID.getIDTS();
                            String[] PtIDList = pptID.split(",");
                            if (PtIDList.length > 0) {
                                iPxpaiketeachertableService.removeByIds(Arrays.asList(PtIDList));//删除pxpaiketeacher表数据
                            }
                            //删除对应的Pxxuanketable选课表里的数据
                            List<Pxxuanketable> pxxuanketables = iPxxuanketableService.allxuankebypkID(Long.valueOf(onepk.getId()),
                                    Long.valueOf(oneclass.getQiyeID()));
                            if (pxxuanketables.size() > 0) {
                                iPxxuanketableService.removeByIds(pxxuanketables);
                            }
                            //删除该班所有排课
                            iPxpaiketableService.removeById(onepk.getId());
                        }
                    }
                }
            }

            oneclass.setIsShow(isShow);
            iPxclasstableService.updateById(oneclass);
        }
        return ajaxJson;
    }


    /**
     * @Description: exportClassOtO方法作用:导出学员班级
     * @param:[clType, classIDs, response]
     * @return:void
     * @auter:yyl
     * @data:2020/11/30 16:37
     */
    @ApiOperation(value = "导出学员班级")
    @ResponseBody
    @RequestMapping(value = "exportClassOtO", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "clType", value = "班级类型 0：普通班级，1：1v1班级", required = true), //clType：0 正常班级 | clType=1:一对一班级
            @ApiImplicitParam(name = "classIDs", value = "选中数据ID", required = false)
    })
    public void exportClassOtO(int clType, String classIDs, HttpServletResponse response, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        String[] pClassID = null;

        QueryWrapper<ExportClassVo> queryWrapper = new QueryWrapper<>(); //班级管理
        QueryWrapper<exportclassstuVo> clstu = new QueryWrapper<>(); //班级学员

        queryWrapper.eq("cl.qiyeID", qiyeID);
        clstu.eq("a.qiyeID", qiyeID);

        if (clType == 0) {
            queryWrapper.eq("cl.is1v1Class", 0);
            clstu.eq(" d.is1v1Class", 0);
        } else if (clType == 1) {
            queryWrapper.eq("cl.is1v1Class", 1);
            clstu.eq(" d.is1v1Class", 1);
        }

        if (pClassID != null) {
            clstu.in("a.classID", pClassID);
        }

        if (StringUtils.isNotBlank(classIDs)) {
            //导出选中
            //获取选中数据
            pClassID = classIDs.split(",");
            queryWrapper.in("cl.id", pClassID);
        }


        List<exportclassstuVo> classStu = iPxstuclasstableService.getClassStu(clstu);
        List<ExportClassVo> pxclasstable = iPxclasstableService.getClassInfoOtO(queryWrapper);

        if (clType == 1) {
            //一对一班级导出
            List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"启用状态", "班级编号", "校区", "班级名称", "学员课程", "建班人", "建班时间"},
                    pxclasstable,
                    new String[]{"isShow", "id", "campusName", "className", "Kc1v1Name", "jingbanren", "addTime-D"});

            ExportExcel.ExcelSource sourcea = new ExportExcel.ExcelSource();
            sourcea.setSheetName("班级管理");
            sourcea.setTableData(list);
            List<ExportExcel.ExcelSource> sourceList = new ArrayList<>();

            sourceList.add(sourcea);
            List<List<Object>> DetailedList = ExportExcel.formatDataToList(new String[]{"校区", "班级编号", "班级名称", "课程名称", "学员ID", "学员名字", "联系电话", "应收", "实收",
                            "课程学费", "剩余学费", "剩余课时", "业绩人"},
                    classStu,
                    new String[]{"campusName", "classID", "className", "kechengName", "stuID", "stuName", "parentTel", "yingshou", "shishou", "jiaofeiNum",
                            "remainXuefei", "remainkeshi", "yejiren"});
            ExportExcel.ExcelSource sourcedetaile = new ExportExcel.ExcelSource();
            sourcedetaile.setSheetName("人工签到签退详情");
            sourcedetaile.setTableData(DetailedList);
            sourceList.add(sourcedetaile);

            try {
                // 需要将详细表一起导出
                ExportExcel.exportMultipleSheetExcel(response, sourceList, "班级管理导出.xls", 15);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (clType == 0) {
            //班课导出
            List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"启用状态", "班级编号", "校区", "班级名称", "班级人数", "学员名单", "建班人", "建班时间"},
                    pxclasstable,
                    new String[]{"isShow", "id", "campusName", "className", "mingdan", "mingdanstu", "jingbanren", "addTime-D"});

            ExportExcel.ExcelSource source = new ExportExcel.ExcelSource();
            source.setSheetName("班级管理");
            source.setTableData(list);
            List<ExportExcel.ExcelSource> sourceList = new ArrayList<>();
            sourceList.add(source);

            List<List<Object>> DetailedList = ExportExcel.formatDataToList(new String[]{"校区", "班级编号", "班级名称", "课程名称", "学员ID", "学员名字", "联系电话", "应收", "实收",
                            "课程学费", "剩余学费", "剩余课时", "业绩人"},
                    classStu,
                    new String[]{"campusName", "classID", "className", "kechengName", "stuID", "stuName", "parentTel", "yingshou", "shishou", "jiaofeiNum",
                            "remainXuefei", "remainkeshi", "yejiren"});
            ExportExcel.ExcelSource sourcedetaile = new ExportExcel.ExcelSource();
            sourcedetaile.setSheetName("人工签到签退详情");
            sourcedetaile.setTableData(DetailedList);
            sourceList.add(sourcedetaile);

            try {
                // 需要将详细表一起导出
                ExportExcel.exportMultipleSheetExcel(response, sourceList, "班级管理导出.xls", 15);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //插班/转班/退班

    @ApiOperation("按班级查找排课信息")
    @GetMapping("getPaikeByclassIDList")
    @ResponseBody
    public AjaxJson getPaikeByclassIDList(String classID, String startDate, String endDate, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper<Pxpaiketable> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("classID", classID)
                .eq("qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("haveClassDate", startDate);
        }
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.le("haveClassDate", endDate);
        }
        queryWrapper.orderByAsc("haveClassDate");
        List<Pxpaiketable> list = iPxpaiketableService.list(queryWrapper);
        ajaxJson.setObj(list);
        return ajaxJson;
    }


    @ApiOperation("按班级和补习ID查找已加入的排课")
    @GetMapping("getInPaikeByClassandBuxi")
    @ResponseBody
    public AjaxJson getInPaikeByClassandBuxi(String classID, String buxiID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        List<zbInPaikeVo> list = iPxxuanketableService.getInPaikeByClassandBuxi(buxiID, classID, loginUser.getQiyeID());
        ajaxJson.setObj(list);
        return ajaxJson;
    }


    @ApiOperation(value = "分页获取学员转班插班")
    @RequestMapping(value = "getzhuangbanchaban", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区", required = false),
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = false),
            @ApiImplicitParam(name = "stuName", value = "学员姓名", required = false),
            @ApiImplicitParam(name = "stuGradeID", value = "年级", required = false),
            @ApiImplicitParam(name = "kechengName", value = "课程名称", required = false),
            @ApiImplicitParam(name = "isShow", value = "课程是否启用：全部：-1 启用：0 不启用：1", example = "-1", required = false),
            @ApiImplicitParam(name = "className", value = "班级", required = false),
            @ApiImplicitParam(name = "type", value = "是否插班：全部：-1,未插班：1,插班：2", example = "-1", required = false),

    })
    public AjaxJson getzhuangbanchaban(Long current, Long size, String campusID, String stuID, String stuName, String stuGradeID, String kechengName,
                                       int isShow, String className, int type, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Page<ZbCbVo> page = new Page<>(current, size);
        QueryWrapper<ZbCbVo> queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("b.campusID", campusID);
        }
        if (StringUtils.isNotBlank(stuID)) {
            queryWrapper
                    .and(a -> a.isNotNull("b.zidingyiStuID").like("b.zidingyiStuID", stuID))
                    .or(b -> b.isNull("b.zidingyiStuID").eq("b.id", stuID));
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("b.stuName", stuName);
        }
        if (StringUtils.isNotBlank(stuGradeID)) {
            queryWrapper.eq("b.stuGradeID", stuGradeID);
        }
        if (StringUtils.isNotBlank(kechengName)) {
            queryWrapper.like("e.kechengName", kechengName);
        }
        if (isShow != -1) {
            queryWrapper.eq("a.isShow", isShow);
        }
        if (StringUtils.isNotBlank(className)) {
            queryWrapper.like("(SELECT GROUP_CONCAT(DISTINCT className) FROM pxstuclasstable i LEFT JOIN pxclasstable j on i.classID=j.id where buxiID=a.id)"
                    , className);
        }
        if (type != -1) {
            if (type == 1) { //快捷查询条件：未插班
                queryWrapper.le("(SELECT (case WHEN COUNT(id)>0 THEN COUNT(id) ELSE 0 END) from pxstuclasstable where buxiID=a.id)", 0);
            } else {         //快捷查询条件：已插班
                queryWrapper.ge("(SELECT (case WHEN COUNT(id)>0 THEN COUNT(id) ELSE 0 END) from pxstuclasstable where buxiID=a.id)", 1);
            }
        }
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("qiyeID", qiyeID);
        queryWrapper1.eq("staffpostID", loginUser.getStaffPostID());
        queryWrapper1.eq("menuID", 146);
        String lookPower = iPxpowertableService.getOne(queryWrapper1).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            queryWrapper.eq("b.campusID", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            queryWrapper.eq("b.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            queryWrapper.in("b.campusID", lookPower);
        }

        queryWrapper.orderByDesc("b.id");
        ajaxJson.setObj(iPxbuxikechengtableService.getzhuangbanchaban(page, queryWrapper));
        return ajaxJson;
    }


    /**
     * @Description: addclass()方法作用:保存建班
     * @param:[campusID, className, zidiyingClassID, request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/2/5 17:06
     */
    @ApiOperation("保存建班")
    @ResponseBody
    @RequestMapping(value = "addclass", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true),
            @ApiImplicitParam(name = "className", value = "班级名称", required = true),
            @ApiImplicitParam(name = "zidiyingClassID", value = "班级编号", required = false),
            @ApiImplicitParam(name = "maxStuNum", value = "班级最大人数，-1表示不限制，默认值-1", required = false)
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addclass(@RequestBody newclassForm form
            , HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long staffID = loginUser.getStaffID();
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        List<Pxclasstable> cfclass = iPxclasstableService.FkClassName(form.getClassName(), qiyeID);
        if (cfclass.size() > 0) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("班级名称已存在");
            return ajaxJson;
        }

        if (StringUtils.isNotBlank(form.getZidingyiClassID())) {
            List<Pxclasstable> cfzdID = iPxclasstableService.getCfzdID(form.getZidingyiClassID(), qiyeID);
            if (cfzdID.size() > 0) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("班级编号已存在");
                return ajaxJson;
            }
        }

        Pxclasstable cla = new Pxclasstable();
        cla.setIsShow(1);
        cla.setCampusID(Long.valueOf(form.getCampusID()));
        cla.setClassName(form.getClassName());
        cla.setZidingyiClassID(form.getZidingyiClassID());
        cla.setIs1v1Class(0);
        cla.setMaxStuNum(form.getMaxStuNum()); //班级最大人数，-1表示不限制，默认值-1
        cla.setIsdelete(false);
        cla.setAddTime(new Date());
        cla.setAddStaffID(staffID);
        cla.setClassState(0);
        cla.setQiyeID(qiyeID);
        cla.setIsShow(1);
        ajaxJson.setSuccess(iPxclasstableService.save(cla));

        return ajaxJson;
    }


    /**
     * @Description: getallbanke()方法作用:获取去全部可用班级（班课）
     * @param:[request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/2/5 17:06
     */
    @ApiOperation("获取全部可用班级")
    @ResponseBody
    @RequestMapping(value = "getallbanke", method = RequestMethod.GET)
    public AjaxJson getallbanke(String campusID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        ajaxJson.setObj(iPxclasstableService.getallbanke(qiyeID, campusID));
        return ajaxJson;
    }


    /**
     * @Description: StuCharu()方法作用:插班保存
     * @param:classID,要插入的班级id
     * @param:ids 是PxbuxikechengTable表的buxiID
     * @param:CBckb: checkbox选中状态，是否插入该班级现有排课
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/2/5 17:06
     */
    @ApiOperation("插班")
    @ResponseBody
    @RequestMapping(value = "StuCharu", method = RequestMethod.POST)
    public AjaxJson StuCharu(@RequestBody StuCharuForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Long staffID = loginUser.getStaffID();
        Pxclasstable stuclass = iPxclasstableService.getById(form.getClassid());//班级
        List<Pxstuclasstable> list = iPxstuclasstableService.list(new QueryWrapper<Pxstuclasstable>()
                .eq("classID", form.getClassid())
                .eq("qiyeID", qiyeID)
        );
        if (stuclass.getMaxStuNum() != -1 && list.size() >= stuclass.getMaxStuNum()) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("当前要插入的班级已达到最大值,已满班！");
            return ajaxJson;
        }

        Pxbuxikechengtable buxi = iPxbuxikechengtableService.getById(form.getIds());
        if (buxi != null && buxi.getIsShow() == 0) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("未启用课程不允许插班！");
            return ajaxJson;
        }
        List<Pxbuxikechengtable> allBuxi = iPxbuxikechengtableService.getByStuID(buxi.getStuID(), qiyeID);

        Boolean flag = false;   //是不是已启用的课程已经插在这个班里了
        Boolean flag2 = false;  //是不是有未启用的课程在这个班里

        for (Pxbuxikechengtable bx : allBuxi) {
            List<Pxstuclasstable> ishave = iPxstuclasstableService.getBybxAndclassID(bx.getId(), Long.valueOf(form.getClassid()), qiyeID);
            if (ishave.size() > 0) {
                if (bx.getIsShow() == 1) {
                    flag = true;
                    break;
                } else {
                    //课程未启用，即有未启用的课程在这个班里,（说明是垃圾数据）把这个删除掉
                    for (Pxstuclasstable del : ishave) {
                        iPxstuclasstableService.removeById(del.getId());
                    }
                    flag2 = true;
                }
            }
        }

        if (flag == true) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ajaxJson.setMsg("已经有课程在这个班了，一个学员不可以重复插入一个班级");
            ajaxJson.setCode("N");
            return ajaxJson;
        }

        //是否勾选了“插入该班级现有排课”
        if (form.getCbckb()) {


            Pxpaiketable checkPK = iPxpaiketableService.getById(form.getPkid()); //选中的排课
            List<Pxpaiketable> paikenews = iPxpaiketableService.list(new QueryWrapper<Pxpaiketable>()
                    .eq("classID", checkPK.getClassID())
                    .ge("haveClassDate", checkPK.getHaveClassDate())
                    .ge("startLessonDateTime", checkPK.getStartLessonDateTime())
                    .eq("qiyeID", loginUser.getQiyeID())
                    .orderByAsc("haveClassDate")
            );

            if (checkPK.getDakaoqin() == true) {
                checkPK.setDakaoqin(false);
                iPxpaiketableService.updateById(checkPK); //（如果排课是打了考勤的）改变排课状态
            }

            Pxsysparamvaluetable getsysvalue = iPxsysparamvaluetableService.getsysvalue(qiyeID, 63L);
            String pdsysTab = getsysvalue == null ? iPxsysparamdefaulttableService.getById(63L).getDefaultValue() : getsysvalue.getModifyValue();
            if (pdsysTab.equals("0")) { //0:不可扣减为负  1：可扣减为负
                //region 判断剩余课时
                int num = paikenews.size();
                BigDecimal akeshi = new BigDecimal(0);
                Pxkechengtable kecTab = iPxkechengtableService.getById(buxi.getKechengID());
                if (buxi.getJifeiStyleID() == 3) {
                    //学生课程所有剩余天数。
                    BigDecimal allrday = new BigDecimal(0);
                    Date nowdate = null;
                    try {
                        nowdate = df.parse(df.format(new Date()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (buxi.getEndDate() != null) {
                        Date Sdate = buxi.getEndDate();
                        long days = (Sdate.getTime() - nowdate.getTime()) / (24 * 1000 * 60 * 60); ///1000毫秒*60s*60m*24h
                        allrday = BigDecimal.valueOf(days);   //**得到剩余天数，（结束日期到今天**
                    }
                    akeshi = BigDecimal.valueOf(num); //**排课课时数**
                    if (allrday.compareTo(akeshi) == -1) { //allrday<akeshi =>-1  起止日期计费剩余天数不足当前班级所有排课，则只将剩余天数插班
                        int countday = 0;//**实际加排课课时**
                        //剩余天数不足
                        for (Pxpaiketable item : paikenews) {
                            if (BigDecimal.valueOf(countday).compareTo(allrday) == -1) {
                                List<Pxxuanketable> xuankeTab2 = iPxxuanketableService.xuankebypkstu(item.getId(), buxi.getStuID(), qiyeID);
                                if (xuankeTab2.size() == 0) {
                                    Pxxuanketable xk = new Pxxuanketable();
                                    xk.setQiyeID(qiyeID);
                                    xk.setBuxiID(Long.valueOf(form.getIds()));
                                    xk.setPaikeID(item.getId());
                                    xk.setRecordDate(new Date());
                                    xk.setStuID(buxi.getStuID());
                                    xk.setType(1);
                                    iPxxuanketableService.save(xk);
                                }
                                countday++;
                            }
                        }
                    } else {
                        //剩余天数足够，则直接插入排课
                        for (Pxpaiketable item1 : paikenews) {
                            List<Pxxuanketable> xuankeTab = iPxxuanketableService.xuankebypkstu(item1.getId(), buxi.getStuID(), qiyeID);
                            if (xuankeTab.size() == 0) {
                                Pxxuanketable xk = new Pxxuanketable();
                                xk.setQiyeID(qiyeID);
                                xk.setBuxiID(Long.valueOf(form.getIds()));
                                xk.setPaikeID(item1.getId());
                                xk.setRecordDate(new Date());
                                xk.setStuID(buxi.getStuID());
                                xk.setType(1);
                                iPxxuanketableService.save(xk);
                            }
                        }
                    }

                } else {
                    Pxclasstimestyletable clsTimetab = iPxclasstimestyletableService.getById(kecTab.getClassTimeStyleID());
                    if (Integer.valueOf(clsTimetab.getClasstimestylename()) < 0) {
                        akeshi = BigDecimal.valueOf(num);
                    } else {
                        if (paikenews.size() > 0) {
                            long time = (checkPK.getEndLessonDateTime().getTime() - checkPK.getStartLessonDateTime().getTime()) / (1000 * 60);
                            akeshi = BigDecimal.valueOf((time / Integer.valueOf(clsTimetab.getClasstimestylename()) * num)); //排课课时
                        }
                    }
                    //学生同课程所有剩余课时。
                    List<allrkeshiVo> getallrkeshi = iPxbuxikechengtableService.getallrkeshi(buxi.getKechengID(), buxi.getStuID(), qiyeID);

                    allrkeshiVo allrkeshione = null;
                    if (getallrkeshi.size() > 0) {
                        allrkeshione = getallrkeshi.get(0);
                    }


                    List<allxuankeVo> allxuanke = iPxxuanketableService.getallxuanke(buxi.getId(), qiyeID);
                    for (allxuankeVo it : allxuanke) {
                        long time = (it.getEndLessonDateTime().getTime() - it.getStartLessonDateTime().getTime()) / (1000 * 60);
                        akeshi.add(BigDecimal.valueOf(time / Integer.valueOf(clsTimetab.getClasstimestylename())));  //加上已选课为打考勤的排课课时
                    }
                    BigDecimal allrkeshi = allrkeshione.getAllrkeshi(); //同课程的全部剩余课时

                    if (allrkeshi.compareTo(akeshi) == -1) {  // a<b => -1
                        BigDecimal xkkeshinum = new BigDecimal(0);  //加入排课课时
                        //剩余课时不足
                        for (Pxpaiketable item1 : paikenews) {
                            if (xkkeshinum.compareTo(allrkeshi) == -1) { //排课次数
                                Pxxuanketable xuankeTab = iPxxuanketableService.getOne(new QueryWrapper<Pxxuanketable>()
                                        .eq("paikeID", item1.getId())
                                        .eq("stuID", buxi.getStuID())
                                        .eq("qiyeID", loginUser.getQiyeID())
                                );

                                if (xuankeTab == null) {
                                    Pxxuanketable xuanke = new Pxxuanketable();
                                    xuanke.setBuxiID(Long.valueOf(form.getIds()));
                                    xuanke.setRecordDate(new Date());
                                    xuanke.setPaikeID(item1.getId());
                                    xuanke.setStuID(buxi.getStuID());
                                    xuanke.setType(1);
                                    xuanke.setQiyeID(qiyeID);
                                    iPxxuanketableService.save(xuanke);

                                    long time = (item1.getEndLessonDateTime().getTime() - item1.getStartLessonDateTime().getTime()) / (1000 * 60);
                                    xkkeshinum.add(BigDecimal.valueOf(time / Integer.valueOf(clsTimetab.getClasstimestylename())));  //实际加入排课的排课课时
                                }
                            }
                        }
                    } else {
                        for (Pxpaiketable item1 : paikenews) {
                            Pxxuanketable xuankeTab = iPxxuanketableService.getOne(new QueryWrapper<Pxxuanketable>()
                                    .eq("paikeID", item1.getId())
                                    .eq("stuID", buxi.getStuID())
                                    .eq("qiyeID", loginUser.getQiyeID())
                            );
                            if (xuankeTab == null) {
                                Pxxuanketable xuanke2 = new Pxxuanketable();
                                xuanke2.setBuxiID(Long.valueOf(form.getIds()));
                                xuanke2.setRecordDate(new Date());
                                xuanke2.setPaikeID(item1.getId());
                                xuanke2.setStuID(buxi.getStuID());
                                xuanke2.setType(1);
                                xuanke2.setQiyeID(qiyeID);
                                iPxxuanketableService.save(xuanke2);
                            }
                        }
                    }
                }
                //endregion


            } else {
                //如果新加入的班级原有排课了，再把学生加在选课表里面去
                for (Pxpaiketable item1 : paikenews) {
                    List<Pxxuanketable> xuankeTab = iPxxuanketableService.xuankebypkstu(item1.getId(), buxi.getStuID(), qiyeID);
                    if (xuankeTab.size() == 0) {
                        Pxxuanketable xk = new Pxxuanketable();
                        xk.setQiyeID(qiyeID);
                        xk.setBuxiID(Long.valueOf(form.getIds()));
                        xk.setPaikeID(item1.getId());
                        xk.setRecordDate(new Date());
                        xk.setStuID(buxi.getStuID());
                        xk.setType(1);
                        iPxxuanketableService.save(xk);
                    }
                }
            }
            Pxstuclasstable add = new Pxstuclasstable();
            add.setQiyeID(qiyeID);
            add.setBuxiID(Long.valueOf(form.getIds()));
            add.setClassID(Long.valueOf(form.getClassid()));
            iPxstuclasstableService.save(add);
        } else {
            Pxstuclasstable add = new Pxstuclasstable();
            add.setQiyeID(qiyeID);
            add.setBuxiID(Long.valueOf(form.getIds()));
            add.setClassID(Long.valueOf(form.getClassid()));
            iPxstuclasstableService.save(add);
        }
        return ajaxJson;
    }


    /**
     * @Description: getallbanke()方法作用:获取补习课程目前已插入的班级
     * @param:[request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/2/5 17:07
     */
    @ApiOperation("获取补习课程目前已插入的班级")
    @ResponseBody
    @RequestMapping(value = "getOldClass", method = RequestMethod.GET)
    public AjaxJson getOldClass(String buxiID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        ajaxJson.setObj(iPxbuxikechengtableService.getoldclass(Long.valueOf(buxiID), qiyeID));
        return ajaxJson;
    }


    /**
     * @Description: saveChangeClass()方法作用:转班保存
     * @param:[buxiID, oldClassID, newClassID, request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/2/6 10:00
     */
    @ApiOperation("转班保存")
    @ResponseBody
    @RequestMapping(value = "saveChangeClass", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson saveChangeClass(@RequestBody saveChangeClassForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        String staffName = loginUser.getStaffName();
        Long qiyeID = loginUser.getQiyeID();

        Pxbuxikechengtable buxi = iPxbuxikechengtableService.getById(form.getBuxiID());
        Pxstutable stu = iPxstutableService.getById(buxi.getStuID());

        Pxclasstable stuclass = iPxclasstableService.getById(form.getNewClassID());//班级
        List<Pxstuclasstable> list = iPxstuclasstableService.list(new QueryWrapper<Pxstuclasstable>()
                .eq("classID", form.getNewClassID())
                .eq("qiyeID", qiyeID)
        );
        if (stuclass.getMaxStuNum() != -1 && list.size() > stuclass.getMaxStuNum()) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("当前选择转到的班级人数已达到最大值！！！");
            return ajaxJson;
        }


        //原班级退班 退排课
        if (StringUtils.isNotBlank(form.getOldpkid())) {
            //pkid不为空
            Pxpaiketable oldpk = iPxpaiketableService.getById(form.getOldpkid());//从本次排课开始退出
            //要退的排课
            List<tuixiankeVo> tuipkList = iPxxuanketableService.tuixuankePaike(new QueryWrapper<tuixiankeVo>()
                    .eq("a.buxiID", form.getBuxiID())
                    .eq("b.classID", form.getOldClassID())
                    .ge("b.haveClassDate", oldpk.getHaveClassDate())
                    .eq("a.qiyeID", loginUser.getQiyeID())
            );


            for (tuixiankeVo item : tuipkList) {
                //原班级退出选择的排课
                Pxpaiketable onePk = iPxpaiketableService.getById(item.getPaikeID());
                if (onePk.getDakaoqin()) {
                    //或者 跳过本次排课，退下一个
                } else {

                    Pxkeshistutable stukq = iPxkeshistutableService.getOne(new QueryWrapper<Pxkeshistutable>()
                            .eq("haveClassDate", onePk.getHaveClassDate())
                            .eq("startLessonDateTime", onePk.getStartLessonDateTime())
                            .eq("endLessonDateTime", onePk.getEndLessonDateTime())
                            .eq("classID", onePk.getClassID())
                            .eq("teacherIDs", onePk.getTeacherIDs())
                            .eq("qiyeID", loginUser.getQiyeID())
                            .eq("stuID", stu.getId())
                    );

                    if (stukq == null) {
                        //未考勤--退
                        int xknum = iPxxuanketableService.list(new QueryWrapper<Pxxuanketable>()
                                .eq("paikeID", item.getPaikeID())
                                .eq("qiyeID", qiyeID)
                        ).size();
                        int kqnum = iPxkeshistutableService.list(new QueryWrapper<Pxkeshistutable>()
                                .eq("haveClassDate", onePk.getHaveClassDate())
                                .eq("startLessonDateTime", onePk.getStartLessonDateTime())
                                .eq("endLessonDateTime", onePk.getEndLessonDateTime())
                                .eq("classID", onePk.getClassID())
                                .eq("teacherIDs", onePk.getTeacherIDs())
                                .eq("qiyeID", loginUser.getQiyeID())

                        ).size();
                        if (xknum - 1 == kqnum) {
                            //说明退了该学员就完成考勤
                            //修改状态
                            onePk.setDakaoqin(true);
                            iPxpaiketableService.updateById(onePk);
                        }
                        //退排课
                        iPxxuanketableService.removeById(item.getId());

                    }
                }
            }
            //原班级退班级
            iPxstuclasstableService.remove(new QueryWrapper<Pxstuclasstable>()
                    .eq("buxiID", form.getBuxiID())
                    .eq("classID", form.getOldClassID())
                    .eq("qiyeID", loginUser.getQiyeID())
            );

        } else {
            //没有排课  直接退
            iPxstuclasstableService.remove(new QueryWrapper<Pxstuclasstable>()
                    .eq("buxiID", form.getBuxiID())
                    .eq("classID", form.getOldClassID())
                    .eq("qiyeID", loginUser.getQiyeID())
            );
        }

        //新班级插班 加排课
        if (buxi != null && buxi.getIsShow() == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ajaxJson.setCode("N");
            ajaxJson.setMsg("未启用课程不允许插班！");
            return ajaxJson;
        }

        List<Pxbuxikechengtable> allBuxi = iPxbuxikechengtableService.getByStuID(buxi.getStuID(), qiyeID);
        Boolean flag = false;   //是不是已启用的课程已经插在这个班里了
        Boolean flag2 = false;  //是不是有未启用的课程在这个班里

        for (Pxbuxikechengtable bx : allBuxi) {
            List<Pxstuclasstable> ishave = iPxstuclasstableService.getBybxAndclassID(bx.getId(), Long.valueOf(form.getNewClassID()), qiyeID);
            if (ishave.size() > 0) {
                if (bx.getIsShow() == 1) {
                    flag = true;
                    break;
                } else {
                    //课程未启用，即有未启用的课程在这个班里,（说明是垃圾数据）把这个删除掉
                    for (Pxstuclasstable del : ishave) {
                        iPxstuclasstableService.removeById(del.getId());
                    }
                    flag2 = true;
                }
            }
        }

        if (flag == true) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ajaxJson.setMsg("已经有课程在这个班了，一个学员不可以重复插入一个班级");
            ajaxJson.setCode("N");
            return ajaxJson;
        }

        if (StringUtils.isNotEmpty(form.getPkid())) {
            Pxpaiketable checkPK = iPxpaiketableService.getById(form.getPkid()); //选中的排课
            List<Pxpaiketable> paikenews = iPxpaiketableService.list(new QueryWrapper<Pxpaiketable>()
                    .eq("classID", checkPK.getClassID())
                    .ge("haveClassDate", checkPK.getHaveClassDate())
                    .eq("qiyeID", loginUser.getQiyeID())
                    .orderByAsc("haveClassDate")
            );

            if (checkPK.getDakaoqin() == true) {
                checkPK.setDakaoqin(false);
                iPxpaiketableService.updateById(checkPK); //（如果排课是打了考勤的）改变排课状态
            }

            Pxsysparamvaluetable getsysvalue = iPxsysparamvaluetableService.getsysvalue(qiyeID, 63L);
            String pdsysTab = getsysvalue == null ? iPxsysparamdefaulttableService.getById(63L).getDefaultValue() : getsysvalue.getModifyValue();
            if (pdsysTab.equals("0")) { //0:不可扣减为负  1：可扣减为负
                //region 判断剩余课时
                int num = paikenews.size();
                BigDecimal akeshi = new BigDecimal(0);
                Pxkechengtable kecTab = iPxkechengtableService.getById(buxi.getKechengID());
                if (buxi.getJifeiStyleID() == 3) {
                    //学生课程所有剩余天数。
                    BigDecimal allrday = new BigDecimal(0);
                    Date nowdate = null;
                    try {
                        nowdate = df.parse(df.format(new Date()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (buxi.getEndDate() != null) {
                        Date Sdate = buxi.getEndDate();
                        long days = (Sdate.getTime() - nowdate.getTime()) / (24 * 1000 * 60 * 60); ///1000毫秒*60s*60m*24h
                        allrday = BigDecimal.valueOf(days);   //**得到剩余天数，（结束日期到今天**
                    }
                    akeshi = BigDecimal.valueOf(num); //**排课课时数**
                    if (allrday.compareTo(akeshi) == -1) { //allrday<akeshi =>-1  起止日期计费剩余天数不足当前班级所有排课，则只将剩余天数插班
                        int countday = 0;//**实际加排课课时**
                        //剩余天数不足
                        for (Pxpaiketable item : paikenews) {
                            if (BigDecimal.valueOf(countday).compareTo(allrday) == -1) {
                                List<Pxxuanketable> xuankeTab2 = iPxxuanketableService.xuankebypkstu(item.getId(), buxi.getStuID(), qiyeID);
                                if (xuankeTab2.size() == 0) {
                                    Pxxuanketable xk = new Pxxuanketable();
                                    xk.setQiyeID(qiyeID);
                                    xk.setBuxiID(buxi.getId());
                                    xk.setPaikeID(item.getId());
                                    xk.setRecordDate(new Date());
                                    xk.setStuID(buxi.getStuID());
                                    xk.setType(1);
                                    iPxxuanketableService.save(xk);
                                }
                                countday++;

                                Pxpaiketable addpk = iPxpaiketableService.getById(item);
                                if (addpk.getDakaoqin()) {
                                    //消过课的修改排课状态
                                    addpk.setDakaoqin(false);
                                    iPxpaiketableService.updateById(addpk);

                                    //排课有几个老师，消课时产生几条教师课耗记录
                                    List<Pxkeshiteachertable> ksteaList = iPxkeshiteachertableService.list(new QueryWrapper<Pxkeshiteachertable>()
                                            .eq("haveClassDate", addpk.getHaveClassDate())
                                            .eq("startLessonDateTime", addpk.getStartLessonDateTime())
                                            .eq("startLessonDateTime", addpk.getEndLessonDateTime())
                                            .eq("classID", addpk.getClassID())
                                            .eq("qiyeID", qiyeID)
                                    );
                                    if (ksteaList.size() > 0) {
                                        for (Pxkeshiteachertable itemt : ksteaList) {
                                            Pxkeshiteachertable onetea = iPxkeshiteachertableService.getById(itemt);
                                            onetea.setYsStuNum(onetea.getYsStuNum() + 1);
                                            iPxkeshiteachertableService.updateById(onetea);
                                        }
                                    }
                                }

                            }
                        }
                    } else {
                        //剩余天数足够，则直接插入排课
                        for (Pxpaiketable item1 : paikenews) {
                            List<Pxxuanketable> xuankeTab = iPxxuanketableService.xuankebypkstu(item1.getId(), buxi.getStuID(), qiyeID);
                            if (xuankeTab.size() == 0) {
                                Pxxuanketable xk = new Pxxuanketable();
                                xk.setQiyeID(qiyeID);
                                xk.setBuxiID(buxi.getId());
                                xk.setPaikeID(item1.getId());
                                xk.setRecordDate(new Date());
                                xk.setStuID(buxi.getStuID());
                                xk.setType(1);
                                iPxxuanketableService.save(xk);
                            }

                            Pxpaiketable addpk = iPxpaiketableService.getById(item1);
                            if (addpk.getDakaoqin()) {
                                //消过课的修改排课状态
                                addpk.setDakaoqin(false);
                                iPxpaiketableService.updateById(addpk);

                                //排课有几个老师，消课时产生几条教师课耗记录
                                List<Pxkeshiteachertable> ksteaList = iPxkeshiteachertableService.list(new QueryWrapper<Pxkeshiteachertable>()
                                        .eq("haveClassDate", addpk.getHaveClassDate())
                                        .eq("startLessonDateTime", addpk.getStartLessonDateTime())
                                        .eq("startLessonDateTime", addpk.getEndLessonDateTime())
                                        .eq("classID", addpk.getClassID())
                                        .eq("qiyeID", qiyeID)
                                );
                                if (ksteaList.size() > 0) {
                                    for (Pxkeshiteachertable itemt : ksteaList) {
                                        Pxkeshiteachertable onetea = iPxkeshiteachertableService.getById(itemt);
                                        onetea.setYsStuNum(onetea.getYsStuNum() + 1);
                                        iPxkeshiteachertableService.updateById(onetea);
                                    }
                                }
                            }
                        }
                    }

                } else {
                    Pxclasstimestyletable clsTimetab = iPxclasstimestyletableService.getById(kecTab.getClassTimeStyleID());
                    if (Integer.valueOf(clsTimetab.getClasstimestylename()) < 0) {
                        akeshi = BigDecimal.valueOf(num);
                    } else {
                        if (paikenews.size() > 0) {
                            long time = (checkPK.getEndLessonDateTime().getTime() - checkPK.getStartLessonDateTime().getTime()) / (1000 * 60);
                            akeshi = BigDecimal.valueOf((time / Integer.valueOf(clsTimetab.getClasstimestylename()) * num)); //排课课时
                        }
                    }
                    //学生同课程所有剩余课时。
                    List<allrkeshiVo> getallrkeshi = iPxbuxikechengtableService.getallrkeshi(buxi.getKechengID(), buxi.getStuID(), qiyeID);

                    allrkeshiVo allrkeshione = null;
                    if (getallrkeshi.size() > 0) {
                        allrkeshione = getallrkeshi.get(0);
                    }


                    List<allxuankeVo> allxuanke = iPxxuanketableService.getallxuanke(buxi.getId(), qiyeID);
                    for (allxuankeVo it : allxuanke) {
                        long time = (it.getEndLessonDateTime().getTime() - it.getStartLessonDateTime().getTime()) / (1000 * 60);
                        akeshi.add(BigDecimal.valueOf(time / Integer.valueOf(clsTimetab.getClasstimestylename())));  //加上已选课为打考勤的排课课时
                    }
                    BigDecimal allrkeshi = allrkeshione.getAllrkeshi(); //同课程的全部剩余课时

                    if (allrkeshi.compareTo(akeshi) == -1) {  // a<b => -1
                        BigDecimal xkkeshinum = new BigDecimal(0);  //加入排课课时
                        //剩余课时不足
                        for (Pxpaiketable item1 : paikenews) {
                            if (xkkeshinum.compareTo(allrkeshi) == -1) { //排课次数
                                Pxxuanketable xuankeTab = iPxxuanketableService.getOne(new QueryWrapper<Pxxuanketable>()
                                        .eq("paikeID", item1.getId())
                                        .eq("stuID", buxi.getStuID())
                                        .eq("qiyeID", loginUser.getQiyeID())
                                );
                                //加排课
                                if (xuankeTab == null) {
                                    Pxxuanketable xuanke = new Pxxuanketable();
                                    xuanke.setBuxiID(buxi.getId());
                                    xuanke.setRecordDate(new Date());
                                    xuanke.setPaikeID(item1.getId());
                                    xuanke.setStuID(buxi.getStuID());
                                    xuanke.setType(1);
                                    xuanke.setQiyeID(qiyeID);
                                    iPxxuanketableService.save(xuanke);

                                    long time = (item1.getEndLessonDateTime().getTime() - item1.getStartLessonDateTime().getTime()) / (1000 * 60);
                                    xkkeshinum.add(BigDecimal.valueOf(time / Integer.valueOf(clsTimetab.getClasstimestylename())));  //实际加入排课的排课课时
                                }

                                Pxpaiketable addpk = iPxpaiketableService.getById(item1);
                                if (addpk.getDakaoqin()) {
                                    //消过课的修改排课状态
                                    addpk.setDakaoqin(false);
                                    iPxpaiketableService.updateById(addpk);

                                    //排课有几个老师，消课时产生几条教师课耗记录
                                    List<Pxkeshiteachertable> ksteaList = iPxkeshiteachertableService.list(new QueryWrapper<Pxkeshiteachertable>()
                                            .eq("haveClassDate", addpk.getHaveClassDate())
                                            .eq("startLessonDateTime", addpk.getStartLessonDateTime())
                                            .eq("startLessonDateTime", addpk.getEndLessonDateTime())
                                            .eq("classID", addpk.getClassID())
                                            .eq("qiyeID", qiyeID)
                                    );
                                    if (ksteaList.size() > 0) {
                                        for (Pxkeshiteachertable itemt : ksteaList) {
                                            Pxkeshiteachertable onetea = iPxkeshiteachertableService.getById(itemt);
                                            onetea.setYsStuNum(onetea.getYsStuNum() + 1);
                                            iPxkeshiteachertableService.updateById(onetea);
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        for (Pxpaiketable item1 : paikenews) {
                            Pxxuanketable xuankeTab = iPxxuanketableService.getOne(new QueryWrapper<Pxxuanketable>()
                                    .eq("paikeID", item1.getId())
                                    .eq("stuID", buxi.getStuID())
                                    .eq("qiyeID", loginUser.getQiyeID())
                            );
                            if (xuankeTab == null) {
                                Pxxuanketable xuanke2 = new Pxxuanketable();
                                xuanke2.setBuxiID(buxi.getId());
                                xuanke2.setRecordDate(new Date());
                                xuanke2.setPaikeID(item1.getId());
                                xuanke2.setStuID(buxi.getStuID());
                                xuanke2.setType(1);
                                xuanke2.setQiyeID(qiyeID);
                                iPxxuanketableService.save(xuanke2);
                            }

                            Pxpaiketable addpk = iPxpaiketableService.getById(item1);
                            if (addpk.getDakaoqin()) {
                                //消过课的修改排课状态
                                addpk.setDakaoqin(false);
                                iPxpaiketableService.updateById(addpk);

                                //排课有几个老师，消课时产生几条教师课耗记录
                                List<Pxkeshiteachertable> ksteaList = iPxkeshiteachertableService.list(new QueryWrapper<Pxkeshiteachertable>()
                                        .eq("haveClassDate", addpk.getHaveClassDate())
                                        .eq("startLessonDateTime", addpk.getStartLessonDateTime())
                                        .eq("startLessonDateTime", addpk.getEndLessonDateTime())
                                        .eq("classID", addpk.getClassID())
                                        .eq("qiyeID", qiyeID)
                                );
                                if (ksteaList.size() > 0) {
                                    for (Pxkeshiteachertable itemt : ksteaList) {
                                        Pxkeshiteachertable onetea = iPxkeshiteachertableService.getById(itemt);
                                        onetea.setYsStuNum(onetea.getYsStuNum() + 1);
                                        iPxkeshiteachertableService.updateById(onetea);
                                    }
                                }
                            }
                        }
                    }
                }
                //endregion
            } else {
                //如果新加入的班级原有排课了，再把学生加在选课表里面去
                for (Pxpaiketable item1 : paikenews) {
                    List<Pxxuanketable> xuankeTab = iPxxuanketableService.xuankebypkstu(item1.getId(), buxi.getStuID(), qiyeID);
                    if (xuankeTab.size() == 0) {
                        Pxxuanketable xk = new Pxxuanketable();
                        xk.setQiyeID(qiyeID);
                        xk.setBuxiID(Long.valueOf(form.getBuxiID()));
                        xk.setPaikeID(item1.getId());
                        xk.setRecordDate(new Date());
                        xk.setStuID(buxi.getStuID());
                        xk.setType(1);
                        iPxxuanketableService.save(xk);
                    }

                    Pxpaiketable addpk = iPxpaiketableService.getById(item1);
                    if (addpk.getDakaoqin()) {
                        //消过课的修改排课状态
                        addpk.setDakaoqin(false);
                        iPxpaiketableService.updateById(addpk);

                        //排课有几个老师，消课时产生几条教师课耗记录
                        List<Pxkeshiteachertable> ksteaList = iPxkeshiteachertableService.list(new QueryWrapper<Pxkeshiteachertable>()
                                .eq("haveClassDate", addpk.getHaveClassDate())
                                .eq("startLessonDateTime", addpk.getStartLessonDateTime())
                                .eq("startLessonDateTime", addpk.getEndLessonDateTime())
                                .eq("classID", addpk.getClassID())
                                .eq("qiyeID", qiyeID)
                        );
                        if (ksteaList.size() > 0) {
                            for (Pxkeshiteachertable itemt : ksteaList) {
                                Pxkeshiteachertable onetea = iPxkeshiteachertableService.getById(itemt);
                                onetea.setYsStuNum(onetea.getYsStuNum() + 1);
                                iPxkeshiteachertableService.updateById(onetea);
                            }
                        }
                    }
                }
            }
        }

        //插班
        Pxstuclasstable add = new Pxstuclasstable();
        add.setQiyeID(qiyeID);
        add.setBuxiID(Long.valueOf(form.getBuxiID()));
        add.setClassID(Long.valueOf(form.getNewClassID()));
        iPxstuclasstableService.save(add);


        String Info =
                staffName + "把学员" + stu.getStuName() + "转班：从" + iPxclasstableService.getById(form.getOldClassID()).getClassName() + ",转班到：" + iPxclasstableService.getById(form.getNewClassID()).getClassName();
        ajaxJson.setMsg(Info);
        return ajaxJson;
    }


    /**
     * @Description: allnoStuCharu()方法作用:批量插班
     * @param:[from, request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/2/6 18:58
     */
    @ApiOperation("批量插班")
    @ResponseBody
    @RequestMapping(value = "allnoStuCharu", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson allnoStuCharu(@RequestBody allChaRuFrom from, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        int Snum = 0;
        Long classid = Long.valueOf(from.getClassid());
        String ids = from.getIds();
        Boolean cbckb = from.getCbckb();
        String pkid = from.getPkid();

        if (ids != null) {
            //判断学生是否有重复插班的情况
            List<stuIDVo> buxilist = JSON.parseArray(ids, stuIDVo.class);
            Pxclasstable stuclass = iPxclasstableService.getById(classid);
            List<Pxstuclasstable> list = iPxstuclasstableService.list(
                    new QueryWrapper<Pxstuclasstable>()
                            .eq("classID", classid)
                            .eq("qiyeID", qiyeID)
            );
            if (stuclass.getMaxStuNum() != -1 && (list.size() > stuclass.getMaxStuNum() || list.size() + buxilist.size() > stuclass.getMaxStuNum())) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("要插入的班级人数已满或者批量插入人数与现有班级人数之和大于班级最大人数");
                return ajaxJson;
            }


            for (stuIDVo buxiIDstr : buxilist) {
                Long stuID = iPxbuxikechengtableService.getById(buxiIDstr.getId()).getStuID();
                List<Pxbuxikechengtable> stuALLbuxiKC = iPxbuxikechengtableService.getstuALLbuxiKC(stuID, buxiIDstr.getId(), qiyeID);
                if (stuALLbuxiKC.size() > 0) {
                    for (Pxbuxikechengtable stuOneBuxiKC : stuALLbuxiKC) {
                        List<Pxstuclasstable> stuIsInClass = iPxstuclasstableService.getBybxAndclassID(stuOneBuxiKC.getId(), Long.valueOf(classid), qiyeID);
                        if (stuIsInClass.size() > 0) {
                            Pxstutable stu = iPxstutableService.getById(stuID);
                            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                            ajaxJson.setMsg(stu.getStuName() + "的其他课程已经在这个班了，不能重复插班！");
                            ajaxJson.setCode("N");
                            return ajaxJson;
                        }
                    }
                }
            }
            for (stuIDVo item : buxilist) {
                Pxbuxikechengtable buxi = iPxbuxikechengtableService.getById(item.getId());
                List<Pxbuxikechengtable> allBuxi = iPxbuxikechengtableService.getbuxikc(buxi.getStuID(), qiyeID);
                Boolean flag = false;
                if (allBuxi.size() > 0) {
                    for (Pxbuxikechengtable bx : allBuxi) {
                        List<Pxstuclasstable> ishave = iPxstuclasstableService.getBybxAndclassID(bx.getId(), classid, qiyeID);
                        if (ishave.size() > 0) {
                            flag = true;
                            break;
                        }
                    }
                }
                if (flag) {
                    Snum++;
                } else {
                    if (cbckb) {
                        //如果新加入的班级原有排课了，再把学生加在选课表里面去
                        // List<Pxpaiketable> paikenews = iPxpaiketableService.getBykq(classid, qiyeID);


                        Pxpaiketable checkPK = iPxpaiketableService.getById(pkid); //选中的排课
                        List<Pxpaiketable> paikenews = iPxpaiketableService.list(new QueryWrapper<Pxpaiketable>()
                                .eq("classID", checkPK.getClassID())
                                .ge("haveClassDate", checkPK.getHaveClassDate())
                                .eq("qiyeID", loginUser.getQiyeID())
                                .orderByAsc("haveClassDate")
                        );
                        if (checkPK.getDakaoqin() == true) {
                            checkPK.setDakaoqin(false);
                            iPxpaiketableService.updateById(checkPK); //（如果排课是打了考勤的）改变排课状态
                        }

                        Pxsysparamvaluetable getsysvalue = iPxsysparamvaluetableService.getsysvalue(qiyeID, 63L);
                        String pdsysTab = getsysvalue == null ? iPxsysparamdefaulttableService.getById(63L).getDefaultValue() : getsysvalue.getModifyValue();

                        if (pdsysTab.equals("0")) { //0:不可扣减为负  1：可扣减为负
                            //region 判断剩余课时
                            int num = paikenews.size();
                            BigDecimal akeshi = new BigDecimal(0);
                            Pxkechengtable kecTab = iPxkechengtableService.getById(buxi.getKechengID());
                            if (buxi.getJifeiStyleID() == 3) {
                                //学生课程所有剩余天数。
                                BigDecimal allrday = new BigDecimal(0);
                                Date nowdate = null;
                                try {
                                    nowdate = df.parse(df.format(new Date()));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                if (buxi.getEndDate() != null) {
                                    Date Sdate = buxi.getEndDate();
                                    long days = (Sdate.getTime() - nowdate.getTime()) / (24 * 1000 * 60 * 60); ///1000毫秒*60s*60m*24h
                                    allrday = BigDecimal.valueOf(days);   //**得到剩余天数，（结束日期到今天**
                                }
                                akeshi = BigDecimal.valueOf(num); //**排课课时数**
                                if (allrday.compareTo(akeshi) == -1) { //allrday<akeshi =>-1  起止日期计费剩余天数不足当前班级所有排课，则只将剩余天数插班
                                    int countday = 0;//**实际加排课课时**
                                    //剩余天数不足
                                    for (Pxpaiketable itempk : paikenews) {
                                        if (BigDecimal.valueOf(countday).compareTo(allrday) == -1) {
                                            List<Pxxuanketable> xuankeTab2 = iPxxuanketableService.xuankebypkstu(itempk.getId(), buxi.getStuID(), qiyeID);
                                            if (xuankeTab2.size() == 0) {
                                                Pxxuanketable xk = new Pxxuanketable();
                                                xk.setQiyeID(qiyeID);
                                                xk.setBuxiID(item.getId());
                                                xk.setPaikeID(itempk.getId());
                                                xk.setRecordDate(new Date());
                                                xk.setStuID(buxi.getStuID());
                                                xk.setType(1);
                                                iPxxuanketableService.save(xk);
                                            }
                                            countday++;

                                            Pxpaiketable addpk = iPxpaiketableService.getById(itempk);
                                            if (addpk.getDakaoqin()) {
                                                //消过课的修改排课状态
                                                addpk.setDakaoqin(false);
                                                iPxpaiketableService.updateById(addpk);

                                                //排课有几个老师，消课时产生几条教师课耗记录
                                                List<Pxkeshiteachertable> ksteaList = iPxkeshiteachertableService.list(new QueryWrapper<Pxkeshiteachertable>()
                                                        .eq("haveClassDate", addpk.getHaveClassDate())
                                                        .eq("startLessonDateTime", addpk.getStartLessonDateTime())
                                                        .eq("startLessonDateTime", addpk.getEndLessonDateTime())
                                                        .eq("classID", addpk.getClassID())
                                                        .eq("qiyeID", qiyeID)
                                                );
                                                if (ksteaList.size() > 0) {
                                                    for (Pxkeshiteachertable itemt : ksteaList) {
                                                        Pxkeshiteachertable onetea = iPxkeshiteachertableService.getById(itemt);
                                                        onetea.setYsStuNum(onetea.getYsStuNum() + 1);
                                                        iPxkeshiteachertableService.updateById(onetea);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    //剩余天数足够，则直接插入排课
                                    for (Pxpaiketable item1 : paikenews) {
                                        List<Pxxuanketable> xuankeTab = iPxxuanketableService.xuankebypkstu(item1.getId(), buxi.getStuID(), qiyeID);
                                        if (xuankeTab.size() == 0) {
                                            Pxxuanketable xk = new Pxxuanketable();
                                            xk.setQiyeID(qiyeID);
                                            xk.setBuxiID(item.getId());
                                            xk.setPaikeID(item1.getId());
                                            xk.setRecordDate(new Date());
                                            xk.setStuID(buxi.getStuID());
                                            xk.setType(1);
                                            iPxxuanketableService.save(xk);
                                        }

                                        Pxpaiketable addpk = iPxpaiketableService.getById(item1);
                                        if (addpk.getDakaoqin()) {
                                            //消过课的修改排课状态
                                            addpk.setDakaoqin(false);
                                            iPxpaiketableService.updateById(addpk);

                                            //排课有几个老师，消课时产生几条教师课耗记录
                                            List<Pxkeshiteachertable> ksteaList = iPxkeshiteachertableService.list(new QueryWrapper<Pxkeshiteachertable>()
                                                    .eq("haveClassDate", addpk.getHaveClassDate())
                                                    .eq("startLessonDateTime", addpk.getStartLessonDateTime())
                                                    .eq("startLessonDateTime", addpk.getEndLessonDateTime())
                                                    .eq("classID", addpk.getClassID())
                                                    .eq("qiyeID", qiyeID)
                                            );
                                            if (ksteaList.size() > 0) {
                                                for (Pxkeshiteachertable itemt : ksteaList) {
                                                    Pxkeshiteachertable onetea = iPxkeshiteachertableService.getById(itemt);
                                                    onetea.setYsStuNum(onetea.getYsStuNum() + 1);
                                                    iPxkeshiteachertableService.updateById(onetea);
                                                }
                                            }
                                        }
                                    }
                                }

                            } else {
                                Pxclasstimestyletable clsTimetab = iPxclasstimestyletableService.getById(kecTab.getClassTimeStyleID());
                                if (Integer.valueOf(clsTimetab.getClasstimestylename()) < 0) {
                                    akeshi = BigDecimal.valueOf(num);
                                } else {
                                    if (paikenews.size() > 0) {
                                        long time = (checkPK.getEndLessonDateTime().getTime() - checkPK.getStartLessonDateTime().getTime()) / (1000 * 60);
                                        akeshi = BigDecimal.valueOf((time / Integer.valueOf(clsTimetab.getClasstimestylename()) * num)); //排课课时
                                    }
                                }
                                //学生同课程所有剩余课时。
                                List<allrkeshiVo> getallrkeshi = iPxbuxikechengtableService.getallrkeshi(buxi.getKechengID(), buxi.getStuID(), qiyeID);

                                allrkeshiVo allrkeshione = null;
                                if (getallrkeshi.size() > 0) {
                                    allrkeshione = getallrkeshi.get(0);
                                }


                                List<allxuankeVo> allxuanke = iPxxuanketableService.getallxuanke(buxi.getId(), qiyeID);
                                for (allxuankeVo it : allxuanke) {
                                    long time = (it.getEndLessonDateTime().getTime() - it.getStartLessonDateTime().getTime()) / (1000 * 60);
                                    akeshi.add(BigDecimal.valueOf(time / Integer.valueOf(clsTimetab.getClasstimestylename())));  //加上已选课为打考勤的排课课时
                                }
                                BigDecimal allrkeshi = allrkeshione.getAllrkeshi(); //同课程的全部剩余课时

                                if (allrkeshi.compareTo(akeshi) == -1) {  // a<b => -1
                                    BigDecimal xkkeshinum = new BigDecimal(0);  //加入排课课时
                                    //剩余课时不足
                                    for (Pxpaiketable item1 : paikenews) {
                                        if (xkkeshinum.compareTo(allrkeshi) == -1) { //排课次数
                                            Pxxuanketable xuankeTab = iPxxuanketableService.getOne(new QueryWrapper<Pxxuanketable>()
                                                    .eq("paikeID", item1.getId())
                                                    .eq("stuID", buxi.getStuID())
                                                    .eq("qiyeID", loginUser.getQiyeID())
                                            );

                                            if (xuankeTab == null) {
                                                Pxxuanketable xuanke = new Pxxuanketable();
                                                xuanke.setBuxiID(item.getId());
                                                xuanke.setRecordDate(new Date());
                                                xuanke.setPaikeID(item1.getId());
                                                xuanke.setStuID(buxi.getStuID());
                                                xuanke.setType(1);
                                                xuanke.setQiyeID(qiyeID);
                                                iPxxuanketableService.save(xuanke);

                                                long time = (item1.getEndLessonDateTime().getTime() - item1.getStartLessonDateTime().getTime()) / (1000 * 60);
                                                xkkeshinum.add(BigDecimal.valueOf(time / Integer.valueOf(clsTimetab.getClasstimestylename())));  //实际加入排课的排课课时
                                            }

                                            Pxpaiketable addpk = iPxpaiketableService.getById(item1);
                                            if (addpk.getDakaoqin()) {
                                                //消过课的修改排课状态
                                                addpk.setDakaoqin(false);
                                                iPxpaiketableService.updateById(addpk);

                                                //排课有几个老师，消课时产生几条教师课耗记录
                                                List<Pxkeshiteachertable> ksteaList = iPxkeshiteachertableService.list(new QueryWrapper<Pxkeshiteachertable>()
                                                        .eq("haveClassDate", addpk.getHaveClassDate())
                                                        .eq("startLessonDateTime", addpk.getStartLessonDateTime())
                                                        .eq("startLessonDateTime", addpk.getEndLessonDateTime())
                                                        .eq("classID", addpk.getClassID())
                                                        .eq("qiyeID", qiyeID)
                                                );
                                                if (ksteaList.size() > 0) {
                                                    for (Pxkeshiteachertable itemt : ksteaList) {
                                                        Pxkeshiteachertable onetea = iPxkeshiteachertableService.getById(itemt);
                                                        onetea.setYsStuNum(onetea.getYsStuNum() + 1);
                                                        iPxkeshiteachertableService.updateById(onetea);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    for (Pxpaiketable item1 : paikenews) {
                                        Pxxuanketable xuankeTab = iPxxuanketableService.getOne(new QueryWrapper<Pxxuanketable>()
                                                .eq("paikeID", item1.getId())
                                                .eq("stuID", buxi.getStuID())
                                                .eq("qiyeID", loginUser.getQiyeID())
                                        );
                                        if (xuankeTab == null) {
                                            Pxxuanketable xuanke2 = new Pxxuanketable();
                                            xuanke2.setBuxiID(item.getId());
                                            xuanke2.setRecordDate(new Date());
                                            xuanke2.setPaikeID(item1.getId());
                                            xuanke2.setStuID(buxi.getStuID());
                                            xuanke2.setType(1);
                                            xuanke2.setQiyeID(qiyeID);
                                            iPxxuanketableService.save(xuanke2);
                                        }

                                        Pxpaiketable addpk = iPxpaiketableService.getById(item1);
                                        if (addpk.getDakaoqin()) {
                                            //消过课的修改排课状态
                                            addpk.setDakaoqin(false);
                                            iPxpaiketableService.updateById(addpk);

                                            //排课有几个老师，消课时产生几条教师课耗记录
                                            List<Pxkeshiteachertable> ksteaList = iPxkeshiteachertableService.list(new QueryWrapper<Pxkeshiteachertable>()
                                                    .eq("haveClassDate", addpk.getHaveClassDate())
                                                    .eq("startLessonDateTime", addpk.getStartLessonDateTime())
                                                    .eq("startLessonDateTime", addpk.getEndLessonDateTime())
                                                    .eq("classID", addpk.getClassID())
                                                    .eq("qiyeID", qiyeID)
                                            );
                                            if (ksteaList.size() > 0) {
                                                for (Pxkeshiteachertable itemt : ksteaList) {
                                                    Pxkeshiteachertable onetea = iPxkeshiteachertableService.getById(itemt);
                                                    onetea.setYsStuNum(onetea.getYsStuNum() + 1);
                                                    iPxkeshiteachertableService.updateById(onetea);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            //endregion
                        } else {
                            //如果新加入的班级原有排课了，再把学生加在选课表里面去
                            for (Pxpaiketable item1 : paikenews) {
                                List<Pxxuanketable> xuankeTab = iPxxuanketableService.xuankebypkstu(item1.getId(), buxi.getStuID(), qiyeID);
                                if (xuankeTab.size() == 0) {
                                    Pxxuanketable xk = new Pxxuanketable();
                                    xk.setQiyeID(qiyeID);
                                    xk.setBuxiID(item.getId());
                                    xk.setPaikeID(item1.getId());
                                    xk.setRecordDate(new Date());
                                    xk.setStuID(buxi.getStuID());
                                    xk.setType(1);
                                    iPxxuanketableService.save(xk);
                                }
                            }
                        }
                        Pxstuclasstable add = new Pxstuclasstable();
                        add.setQiyeID(qiyeID);
                        add.setBuxiID(item.getId());
                        add.setClassID(classid);
                        iPxstuclasstableService.save(add);
                    } else {
                        Pxstuclasstable add = new Pxstuclasstable();
                        add.setQiyeID(qiyeID);
                        add.setBuxiID(item.getId());
                        add.setClassID(classid);
                        iPxstuclasstableService.save(add);
                    }

                }
            }

        }
        return ajaxJson;
    }

    /**
     * @Description: getHaveClass()方法作用:已插班级
     * @param:[buxiID, request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/2/6 10:00
     */
    @ApiOperation("已插班级")
    @ResponseBody
    @RequestMapping(value = "getHaveClass", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "buxiID", value = "补习ID", required = true),
    })
    public AjaxJson getHaveClass(Long current, Long size, String buxiID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<havaclassVo> page = new Page<>(current, size);
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        ajaxJson.setObj(iPxbuxikechengtableService.getHaveClass(page, Long.valueOf(buxiID), qiyeID));
        return ajaxJson;
    }


    /**
     * @Description: tuichuClassOnlyOne()方法作用:只有1个班级的退班
     * @param:[buxiID, request]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/2/6 10:57
     */
    @ApiOperation("退班")
    @ResponseBody
    @RequestMapping(value = "tuichuClass", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson tuichuClass(@RequestBody tuiclassFrom from, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        String staffName = loginUser.getStaffName();
        String xiugaiInfo = staffName + "操作退班: 把学员:";

        List<stuIDVo> buxiVoList = JSON.parseArray(from.getIds(), stuIDVo.class);//buxiID
        String today = df.format(new Date());
        String allStuName = "";
        String oldClassName = "";
        for (stuIDVo oneID : buxiVoList) { //补习ID
            Pxbuxikechengtable buxikc = iPxbuxikechengtableService.getById(oneID.getId());
            Pxstutable stu = iPxstutableService.getById(buxikc.getStuID());
            allStuName += stu.getStuName() + ",";
            if (from.getClassids() != null) { //班级ID  ---某个学员退班时 有多个班级的情况
                List<stuIDVo> classVoList = JSON.parseArray(from.getClassids(), stuIDVo.class);

                for (stuIDVo oldClassID : classVoList) {
                    Pxclasstable oldClass = iPxclasstableService.getById(oldClassID.getId());
                    oldClassName += oldClass.getClassName() + ",";
                    List<tuixiankeVo> stuAllxuanke = null;
                    if (classVoList.size() > 1) {
                        //退多个班级
                        stuAllxuanke = iPxxuanketableService.tuixuankePaike(new QueryWrapper<tuixiankeVo>()
                                .eq("a.buxiID", oneID.getId())
                                .eq("a.qyeID", qiyeID)
                                .eq("b.classID", oldClassID.getId())
                                .ge("b.haveClassDate", today)
                        );
                    } else if (classVoList.size() == 1) {
                        //退单个班级及选择排课
                        if (StringUtils.isNotBlank(from.getPkid())) { //有排课ID
                            Pxpaiketable checkPk = iPxpaiketableService.getById(from.getPkid());
                            stuAllxuanke = iPxxuanketableService.tuixuankePaike(new QueryWrapper<tuixiankeVo>()
                                    .eq("a.buxiID", oneID.getId())
                                    .eq("a.qiyeID", qiyeID)
                                    .eq("b.classID", oldClassID.getId())
                                    .ge("b.haveClassDate", checkPk.getHaveClassDate())
                            );
                        }
                    }
                    //是否有排课，是否有选课，有则需要把这个学生从选课表中剔除
                    if (stuAllxuanke != null) {
                        for (tuixiankeVo itemxk : stuAllxuanke) {
                            Pxpaiketable onetpk = iPxpaiketableService.getById(itemxk.getPaikeID());
                            if (!onetpk.getDakaoqin()) {
                                //没打考勤的才退，打了考勤的跳过
                                Pxkeshistutable stukq = iPxkeshistutableService.getOne(new QueryWrapper<Pxkeshistutable>()
                                        .eq("haveClassDate", onetpk.getHaveClassDate())
                                        .eq("startLessonDateTime", onetpk.getStartLessonDateTime())
                                        .eq("endLessonDateTime", onetpk.getEndLessonDateTime())
                                        .eq("classID", onetpk.getClassID())
                                        .eq("teacherIDs", onetpk.getTeacherIDs())
                                        .eq("qiyeID", loginUser.getQiyeID())
                                        .eq("stuID", stu.getId())
                                );

                                if (stukq == null) {
                                    //未考勤--退
                                    int xknum = iPxxuanketableService.list(new QueryWrapper<Pxxuanketable>()
                                            .eq("paikeID", itemxk.getPaikeID())
                                            .eq("qiyeID", qiyeID)
                                    ).size();
                                    int kqnum = iPxkeshistutableService.list(new QueryWrapper<Pxkeshistutable>()
                                            .eq("haveClassDate", onetpk.getHaveClassDate())
                                            .eq("startLessonDateTime", onetpk.getStartLessonDateTime())
                                            .eq("endLessonDateTime", onetpk.getEndLessonDateTime())
                                            .eq("classID", onetpk.getClassID())
                                            .eq("teacherIDs", onetpk.getTeacherIDs())
                                            .eq("qiyeID", loginUser.getQiyeID())

                                    ).size();
                                    if (xknum - 1 == kqnum) {
                                        //说明退了该学员就完成考勤
                                        //修改状态
                                        onetpk.setDakaoqin(true);
                                        iPxpaiketableService.updateById(onetpk);
                                    }
                                    //退排课
                                    iPxxuanketableService.removeById(itemxk.getId());
                                }
                            }
                        }
                    }
                    //退班
                    iPxstuclasstableService.remove(new QueryWrapper<Pxstuclasstable>()
                            .eq("buxiID", buxikc.getId())
                            .eq("classID", oldClassID.getId())
                            .eq("qiyeID", qiyeID)
                    );
                }
            } else {
                //批量退班操作时
                List<Pxstuclasstable> stucla = iPxstuclasstableService.getBybxID(oneID.getId(), qiyeID);
                if (stucla.size() > 0) {
                    for (Pxstuclasstable itema : stucla) {
                        oldClassName += iPxclasstableService.getById(itema.getClassID()).getClassName() + ',';
                        //获取学员们的全部今天及之后的全部排课
                        List<tuixiankeVo> stuAllxuanke = iPxxuanketableService.tuixuankePaike(new QueryWrapper<tuixiankeVo>()
                                .eq("a.buxiID", oneID.getId())
                                .eq("a.qyeID", qiyeID)
                                .ge("b.haveClassDate", today)
                        );

                        //是否有排课，是否有选课，有则需要把这个学生从选课表中剔除
                        if (stuAllxuanke.size() > 0) {
                            for (tuixiankeVo itemxk : stuAllxuanke) {
                                Pxpaiketable onetpk = iPxpaiketableService.getById(itemxk.getPaikeID());
                                if (!onetpk.getDakaoqin()) {
                                    //没打考勤的才退，打了考勤的跳过
                                    Pxkeshistutable stukq = iPxkeshistutableService.getOne(new QueryWrapper<Pxkeshistutable>()
                                            .eq("haveClassDate", onetpk.getHaveClassDate())
                                            .eq("startLessonDateTime", onetpk.getStartLessonDateTime())
                                            .eq("endLessonDateTime", onetpk.getEndLessonDateTime())
                                            .eq("classID", onetpk.getClassID())
                                            .eq("teacherIDs", onetpk.getTeacherIDs())
                                            .eq("qiyeID", loginUser.getQiyeID())
                                            .eq("stuID", stu.getId())
                                    );

                                    if (stukq == null) {
                                        //未考勤--退
                                        int xknum = iPxxuanketableService.list(new QueryWrapper<Pxxuanketable>()
                                                .eq("paikeID", itemxk.getPaikeID())
                                                .eq("qiyeID", qiyeID)
                                        ).size();
                                        int kqnum = iPxkeshistutableService.list(new QueryWrapper<Pxkeshistutable>()
                                                .eq("haveClassDate", onetpk.getHaveClassDate())
                                                .eq("startLessonDateTime", onetpk.getStartLessonDateTime())
                                                .eq("endLessonDateTime", onetpk.getEndLessonDateTime())
                                                .eq("classID", onetpk.getClassID())
                                                .eq("teacherIDs", onetpk.getTeacherIDs())
                                                .eq("qiyeID", loginUser.getQiyeID())

                                        ).size();
                                        if (xknum - 1 == kqnum) {
                                            //说明退了该学员就完成考勤
                                            //修改状态
                                            onetpk.setDakaoqin(true);
                                            iPxpaiketableService.updateById(onetpk);
                                        }
                                        //退排课
                                        iPxxuanketableService.removeById(itemxk.getId());
                                    }
                                }
                            }
                        }
                        //退班
                        iPxstuclasstableService.removeById(itema.getId());
                    }
                }

            }
        }
        xiugaiInfo += allStuName + "，从" + oldClassName + "，退班";
        ajaxJson.setMsg(xiugaiInfo);
        return ajaxJson;
    }


    @ApiOperation("批量转班")
    @ResponseBody
    @RequestMapping(value = "saveplChangeClass", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson saveplChangeClass(@RequestBody AllzhuanbanFrom from, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        String staffName = loginUser.getStaffName();

        String IDs = from.getIds();
        String newClassID = from.getClassid();
        Long pkid = Long.valueOf(from.getPkid());
        List<stuIDVo> buxiList = JSON.parseArray(IDs, stuIDVo.class);

        Pxclasstable stuclass = iPxclasstableService.getById(newClassID);
        List<Pxstuclasstable> list = iPxstuclasstableService.list(
                new QueryWrapper<Pxstuclasstable>()
                        .eq("classID", newClassID)
                        .eq("qiyeID", qiyeID)
        );
        if (stuclass.getMaxStuNum() != -1 && (list.size() > stuclass.getMaxStuNum() || list.size() + buxiList.size() > stuclass.getMaxStuNum())) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("要转入的班级人数已满，或者批量转班人数与现有班级人数之和大于班级最大人数");
            return ajaxJson;
        }

        String today = df.format(new Date());
        String Info = staffName + "把";
        String oldClassName = "";
        String allStuName = "";
        for (stuIDVo item : buxiList) {

            //学员对应补习课程的插入的班级
            List<Pxstuclasstable> stucla = iPxstuclasstableService.getBybxID(item.getId(), qiyeID);
            Pxbuxikechengtable buxikc = iPxbuxikechengtableService.getById(item.getId());
            Pxstutable stu = iPxstutableService.getById(buxikc.getStuID());
            allStuName += stu.getStuName() + ",";

            /**
             * 原全部班级退班退排课
             */
            if (stucla.size() > 0) {
                for (Pxstuclasstable itema : stucla) {
                    oldClassName += iPxclasstableService.getById(itema.getClassID()).getClassName() + ',';
                    //获取学员们的全部今天及之后的全部排课
                    List<tuixiankeVo> stuAllxuanke = iPxxuanketableService.tuixuankePaike(new QueryWrapper<tuixiankeVo>()
                            .eq("a.buxiID", item.getId())
                            .eq("a.qyeID", qiyeID)
                            .ge("b.haveClassDate", today)
                    );

                    //是否有排课，是否有选课，有则需要把这个学生从选课表中剔除
                    if (stuAllxuanke.size() > 0) {
                        for (tuixiankeVo itemxk : stuAllxuanke) {
                            Pxpaiketable onetpk = iPxpaiketableService.getById(itemxk.getPaikeID());
                            if (!onetpk.getDakaoqin()) {
                                //没打考勤的才退，打了考勤的跳过
                                Pxkeshistutable stukq = iPxkeshistutableService.getOne(new QueryWrapper<Pxkeshistutable>()
                                        .eq("haveClassDate", onetpk.getHaveClassDate())
                                        .eq("startLessonDateTime", onetpk.getStartLessonDateTime())
                                        .eq("endLessonDateTime", onetpk.getEndLessonDateTime())
                                        .eq("classID", onetpk.getClassID())
                                        .eq("teacherIDs", onetpk.getTeacherIDs())
                                        .eq("qiyeID", loginUser.getQiyeID())
                                        .eq("stuID", stu.getId())
                                );

                                if (stukq == null) {
                                    //未考勤--退
                                    int xknum = iPxxuanketableService.list(new QueryWrapper<Pxxuanketable>()
                                            .eq("paikeID", itemxk.getPaikeID())
                                            .eq("qiyeID", qiyeID)
                                    ).size();
                                    int kqnum = iPxkeshistutableService.list(new QueryWrapper<Pxkeshistutable>()
                                            .eq("haveClassDate", onetpk.getHaveClassDate())
                                            .eq("startLessonDateTime", onetpk.getStartLessonDateTime())
                                            .eq("endLessonDateTime", onetpk.getEndLessonDateTime())
                                            .eq("classID", onetpk.getClassID())
                                            .eq("teacherIDs", onetpk.getTeacherIDs())
                                            .eq("qiyeID", loginUser.getQiyeID())

                                    ).size();
                                    if (xknum - 1 == kqnum) {
                                        //说明退了该学员就完成考勤
                                        //修改状态
                                        onetpk.setDakaoqin(true);
                                        iPxpaiketableService.updateById(onetpk);
                                    }
                                    //退排课
                                    iPxxuanketableService.removeById(itemxk.getId());
                                }
                            }
                        }
                    }
                    //退班
                    iPxstuclasstableService.removeById(itema.getId());
                }
            }

            /**
             * 新班级插班 加排课
             */
            if (buxikc != null && buxikc.getIsShow() == 0) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                ajaxJson.setCode("N");
                ajaxJson.setMsg("未启用课程不允许插班！");
                return ajaxJson;
            }
            List<Pxbuxikechengtable> allBuxi = iPxbuxikechengtableService.getByStuID(buxikc.getStuID(), qiyeID);
            Boolean flag = false;   //是不是已启用的课程已经插在这个班里了
            Boolean flag2 = false;  //是不是有未启用的课程在这个班里

            for (Pxbuxikechengtable bx : allBuxi) {
                List<Pxstuclasstable> ishave = iPxstuclasstableService.getBybxAndclassID(bx.getId(), Long.valueOf(newClassID), qiyeID);
                if (ishave.size() > 0) {
                    if (bx.getIsShow() == 1) {
                        flag = true;
                        break;
                    } else {
                        //课程未启用，即有未启用的课程在这个班里,（说明是垃圾数据）把这个删除掉
                        for (Pxstuclasstable del : ishave) {
                            iPxstuclasstableService.removeById(del.getId());
                        }
                        flag2 = true;
                    }
                }
            }

            if (flag == true) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                ajaxJson.setMsg("已经有课程在这个班了，一个学员不可以重复插入一个班级");
                ajaxJson.setCode("N");
                return ajaxJson;
            }

            Pxpaiketable checkPK = iPxpaiketableService.getById(pkid); //选中的排课
            List<Pxpaiketable> paikenews = iPxpaiketableService.list(new QueryWrapper<Pxpaiketable>()
                    .eq("classID", checkPK.getClassID())
                    .ge("haveClassDate", checkPK.getHaveClassDate())
                    .eq("qiyeID", loginUser.getQiyeID())
                    .orderByAsc("haveClassDate")
            );

            if (checkPK.getDakaoqin() == true) {
                checkPK.setDakaoqin(false);
                iPxpaiketableService.updateById(checkPK); //（如果排课是打了考勤的）改变排课状态
            }

            Pxsysparamvaluetable getsysvalue = iPxsysparamvaluetableService.getsysvalue(qiyeID, 63L);
            String pdsysTab = getsysvalue == null ? iPxsysparamdefaulttableService.getById(63L).getDefaultValue() : getsysvalue.getModifyValue();
            if (pdsysTab.equals("0")) { //0:不可扣减为负  1：可扣减为负
                //region 判断剩余课时
                int num = paikenews.size();
                BigDecimal akeshi = new BigDecimal(0);
                Pxkechengtable kecTab = iPxkechengtableService.getById(buxikc.getKechengID());
                if (buxikc.getJifeiStyleID() == 3) {
                    //学生课程所有剩余天数。
                    BigDecimal allrday = new BigDecimal(0);
                    Date nowdate = null;
                    try {
                        nowdate = df.parse(df.format(new Date()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (buxikc.getEndDate() != null) {
                        Date Sdate = buxikc.getEndDate();
                        long days = (Sdate.getTime() - nowdate.getTime()) / (24 * 1000 * 60 * 60); ///1000毫秒*60s*60m*24h
                        allrday = BigDecimal.valueOf(days);   //**得到剩余天数，（结束日期到今天**
                    }
                    akeshi = BigDecimal.valueOf(num); //**排课课时数**
                    if (allrday.compareTo(akeshi) == -1) { //allrday<akeshi =>-1  起止日期计费剩余天数不足当前班级所有排课，则只将剩余天数插班
                        int countday = 0;//**实际加排课课时**
                        //剩余天数不足
                        for (Pxpaiketable item1 : paikenews) {
                            if (BigDecimal.valueOf(countday).compareTo(allrday) == -1) {
                                List<Pxxuanketable> xuankeTab2 = iPxxuanketableService.xuankebypkstu(item.getId(), buxikc.getStuID(), qiyeID);
                                if (xuankeTab2.size() == 0) {
                                    Pxxuanketable xk = new Pxxuanketable();
                                    xk.setQiyeID(qiyeID);
                                    xk.setBuxiID(buxikc.getId());
                                    xk.setPaikeID(item.getId());
                                    xk.setRecordDate(new Date());
                                    xk.setStuID(buxikc.getStuID());
                                    xk.setType(1);
                                    iPxxuanketableService.save(xk);
                                }
                                countday++;

                                Pxpaiketable addpk = iPxpaiketableService.getById(item1);
                                if (addpk.getDakaoqin()) {
                                    //消过课的修改排课状态
                                    addpk.setDakaoqin(false);
                                    iPxpaiketableService.updateById(addpk);

                                    //排课有几个老师，消课时产生几条教师课耗记录
                                    List<Pxkeshiteachertable> ksteaList = iPxkeshiteachertableService.list(new QueryWrapper<Pxkeshiteachertable>()
                                            .eq("haveClassDate", addpk.getHaveClassDate())
                                            .eq("startLessonDateTime", addpk.getStartLessonDateTime())
                                            .eq("startLessonDateTime", addpk.getEndLessonDateTime())
                                            .eq("classID", addpk.getClassID())
                                            .eq("qiyeID", qiyeID)
                                    );
                                    if (ksteaList.size() > 0) {
                                        for (Pxkeshiteachertable itemt : ksteaList) {
                                            Pxkeshiteachertable onetea = iPxkeshiteachertableService.getById(itemt);
                                            onetea.setYsStuNum(onetea.getYsStuNum() + 1);
                                            iPxkeshiteachertableService.updateById(onetea);
                                        }
                                    }
                                }

                            }
                        }
                    } else {
                        //剩余天数足够，则直接插入排课
                        for (Pxpaiketable item1 : paikenews) {
                            List<Pxxuanketable> xuankeTab = iPxxuanketableService.xuankebypkstu(item1.getId(), buxikc.getStuID(), qiyeID);
                            if (xuankeTab.size() == 0) {
                                Pxxuanketable xk = new Pxxuanketable();
                                xk.setQiyeID(qiyeID);
                                xk.setBuxiID(buxikc.getId());
                                xk.setPaikeID(item1.getId());
                                xk.setRecordDate(new Date());
                                xk.setStuID(buxikc.getStuID());
                                xk.setType(1);
                                iPxxuanketableService.save(xk);
                            }

                            Pxpaiketable addpk = iPxpaiketableService.getById(item1);
                            if (addpk.getDakaoqin()) {
                                //消过课的修改排课状态
                                addpk.setDakaoqin(false);
                                iPxpaiketableService.updateById(addpk);

                                //排课有几个老师，消课时产生几条教师课耗记录
                                List<Pxkeshiteachertable> ksteaList = iPxkeshiteachertableService.list(new QueryWrapper<Pxkeshiteachertable>()
                                        .eq("haveClassDate", addpk.getHaveClassDate())
                                        .eq("startLessonDateTime", addpk.getStartLessonDateTime())
                                        .eq("startLessonDateTime", addpk.getEndLessonDateTime())
                                        .eq("classID", addpk.getClassID())
                                        .eq("qiyeID", qiyeID)
                                );
                                if (ksteaList.size() > 0) {
                                    for (Pxkeshiteachertable itemt : ksteaList) {
                                        Pxkeshiteachertable onetea = iPxkeshiteachertableService.getById(itemt);
                                        onetea.setYsStuNum(onetea.getYsStuNum() + 1);
                                        iPxkeshiteachertableService.updateById(onetea);
                                    }
                                }
                            }
                        }
                    }

                } else {
                    Pxclasstimestyletable clsTimetab = iPxclasstimestyletableService.getById(kecTab.getClassTimeStyleID());
                    if (Integer.valueOf(clsTimetab.getClasstimestylename()) < 0) {
                        akeshi = BigDecimal.valueOf(num);
                    } else {
                        if (paikenews.size() > 0) {
                            long time = (checkPK.getEndLessonDateTime().getTime() - checkPK.getStartLessonDateTime().getTime()) / (1000 * 60);
                            akeshi = BigDecimal.valueOf((time / Integer.valueOf(clsTimetab.getClasstimestylename()) * num)); //排课课时
                        }
                    }
                    //学生同课程所有剩余课时。
                    List<allrkeshiVo> getallrkeshi = iPxbuxikechengtableService.getallrkeshi(buxikc.getKechengID(), buxikc.getStuID(), qiyeID);

                    allrkeshiVo allrkeshione = null;
                    if (getallrkeshi.size() > 0) {
                        allrkeshione = getallrkeshi.get(0);
                    }


                    List<allxuankeVo> allxuanke = iPxxuanketableService.getallxuanke(buxikc.getId(), qiyeID);
                    for (allxuankeVo it : allxuanke) {
                        long time = (it.getEndLessonDateTime().getTime() - it.getStartLessonDateTime().getTime()) / (1000 * 60);
                        akeshi.add(BigDecimal.valueOf(time / Integer.valueOf(clsTimetab.getClasstimestylename())));  //加上已选课为打考勤的排课课时
                    }
                    BigDecimal allrkeshi = allrkeshione.getAllrkeshi(); //同课程的全部剩余课时

                    if (allrkeshi.compareTo(akeshi) == -1) {  // a<b => -1
                        BigDecimal xkkeshinum = new BigDecimal(0);  //加入排课课时
                        //剩余课时不足
                        for (Pxpaiketable item1 : paikenews) {
                            if (xkkeshinum.compareTo(allrkeshi) == -1) { //排课次数
                                Pxxuanketable xuankeTab = iPxxuanketableService.getOne(new QueryWrapper<Pxxuanketable>()
                                        .eq("paikeID", item1.getId())
                                        .eq("stuID", buxikc.getStuID())
                                        .eq("qiyeID", loginUser.getQiyeID())
                                );
                                //加排课
                                if (xuankeTab == null) {
                                    Pxxuanketable xuanke = new Pxxuanketable();
                                    xuanke.setBuxiID(buxikc.getId());
                                    xuanke.setRecordDate(new Date());
                                    xuanke.setPaikeID(item1.getId());
                                    xuanke.setStuID(buxikc.getStuID());
                                    xuanke.setType(1);
                                    xuanke.setQiyeID(qiyeID);
                                    iPxxuanketableService.save(xuanke);

                                    long time = (item1.getEndLessonDateTime().getTime() - item1.getStartLessonDateTime().getTime()) / (1000 * 60);
                                    xkkeshinum.add(BigDecimal.valueOf(time / Integer.valueOf(clsTimetab.getClasstimestylename())));  //实际加入排课的排课课时
                                }

                                Pxpaiketable addpk = iPxpaiketableService.getById(item1);
                                if (addpk.getDakaoqin()) {
                                    //消过课的修改排课状态
                                    addpk.setDakaoqin(false);
                                    iPxpaiketableService.updateById(addpk);

                                    //排课有几个老师，消课时产生几条教师课耗记录
                                    List<Pxkeshiteachertable> ksteaList = iPxkeshiteachertableService.list(new QueryWrapper<Pxkeshiteachertable>()
                                            .eq("haveClassDate", addpk.getHaveClassDate())
                                            .eq("startLessonDateTime", addpk.getStartLessonDateTime())
                                            .eq("startLessonDateTime", addpk.getEndLessonDateTime())
                                            .eq("classID", addpk.getClassID())
                                            .eq("qiyeID", qiyeID)
                                    );
                                    if (ksteaList.size() > 0) {
                                        for (Pxkeshiteachertable itemt : ksteaList) {
                                            Pxkeshiteachertable onetea = iPxkeshiteachertableService.getById(itemt);
                                            onetea.setYsStuNum(onetea.getYsStuNum() + 1);
                                            iPxkeshiteachertableService.updateById(onetea);
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        for (Pxpaiketable item1 : paikenews) {
                            Pxxuanketable xuankeTab = iPxxuanketableService.getOne(new QueryWrapper<Pxxuanketable>()
                                    .eq("paikeID", item1.getId())
                                    .eq("stuID", buxikc.getStuID())
                                    .eq("qiyeID", loginUser.getQiyeID())
                            );
                            if (xuankeTab == null) {
                                Pxxuanketable xuanke2 = new Pxxuanketable();
                                xuanke2.setBuxiID(buxikc.getId());
                                xuanke2.setRecordDate(new Date());
                                xuanke2.setPaikeID(item1.getId());
                                xuanke2.setStuID(buxikc.getStuID());
                                xuanke2.setType(1);
                                xuanke2.setQiyeID(qiyeID);
                                iPxxuanketableService.save(xuanke2);
                            }

                            Pxpaiketable addpk = iPxpaiketableService.getById(item1);
                            if (addpk.getDakaoqin()) {
                                //消过课的修改排课状态
                                addpk.setDakaoqin(false);
                                iPxpaiketableService.updateById(addpk);

                                //排课有几个老师，消课时产生几条教师课耗记录
                                List<Pxkeshiteachertable> ksteaList = iPxkeshiteachertableService.list(new QueryWrapper<Pxkeshiteachertable>()
                                        .eq("haveClassDate", addpk.getHaveClassDate())
                                        .eq("startLessonDateTime", addpk.getStartLessonDateTime())
                                        .eq("startLessonDateTime", addpk.getEndLessonDateTime())
                                        .eq("classID", addpk.getClassID())
                                        .eq("qiyeID", qiyeID)
                                );
                                if (ksteaList.size() > 0) {
                                    for (Pxkeshiteachertable itemt : ksteaList) {
                                        Pxkeshiteachertable onetea = iPxkeshiteachertableService.getById(itemt);
                                        onetea.setYsStuNum(onetea.getYsStuNum() + 1);
                                        iPxkeshiteachertableService.updateById(onetea);
                                    }
                                }
                            }
                        }
                    }
                }
                //endregion
            } else {
                //如果新加入的班级原有排课了，再把学生加在选课表里面去
                for (Pxpaiketable item1 : paikenews) {
                    List<Pxxuanketable> xuankeTab = iPxxuanketableService.xuankebypkstu(item1.getId(), buxikc.getStuID(), qiyeID);
                    if (xuankeTab.size() == 0) {
                        Pxxuanketable xk = new Pxxuanketable();
                        xk.setQiyeID(qiyeID);
                        xk.setBuxiID(buxikc.getId());
                        xk.setPaikeID(item1.getId());
                        xk.setRecordDate(new Date());
                        xk.setStuID(buxikc.getStuID());
                        xk.setType(1);
                        iPxxuanketableService.save(xk);
                    }

                    Pxpaiketable addpk = iPxpaiketableService.getById(item1);
                    if (addpk.getDakaoqin()) {
                        //消过课的修改排课状态
                        addpk.setDakaoqin(false);
                        iPxpaiketableService.updateById(addpk);

                        //排课有几个老师，消课时产生几条教师课耗记录
                        List<Pxkeshiteachertable> ksteaList = iPxkeshiteachertableService.list(new QueryWrapper<Pxkeshiteachertable>()
                                .eq("haveClassDate", addpk.getHaveClassDate())
                                .eq("startLessonDateTime", addpk.getStartLessonDateTime())
                                .eq("startLessonDateTime", addpk.getEndLessonDateTime())
                                .eq("classID", addpk.getClassID())
                                .eq("qiyeID", qiyeID)
                        );
                        if (ksteaList.size() > 0) {
                            for (Pxkeshiteachertable itemt : ksteaList) {
                                Pxkeshiteachertable onetea = iPxkeshiteachertableService.getById(itemt);
                                onetea.setYsStuNum(onetea.getYsStuNum() + 1);
                                iPxkeshiteachertableService.updateById(onetea);
                            }
                        }
                    }
                }
            }

            //新班级插班
            Pxstuclasstable add = new Pxstuclasstable();
            add.setQiyeID(qiyeID);
            add.setBuxiID(buxikc.getId());
            add.setClassID(Long.valueOf(newClassID));
            iPxstuclasstableService.save(add);


            Info += "学员：" + allStuName + "【批量转班】：转班到：" + stuclass.getClassName() + "。";
            ajaxJson.setMsg(Info);
        }
        return ajaxJson;
    }


    @RequestMapping(value = "Getkxqstu", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取可跨校区学员")
    public AjaxJson Getkxqstu(HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxstukxqtableService.Getkxqstu(qiyeID));
        return ajaxJson;
    }


    @RequestMapping(value = "Getkxqbxkecheng", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取选中跨校区学员课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true)
    })
    public AjaxJson Getkxqbxkecheng(String stuID, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxstukxqtableService.Getkxqbxkecheng(Long.valueOf(stuID), qiyeID));
        return ajaxJson;
    }


    @RequestMapping(value = "GetKxqCampus", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取学员所在校区以外的校区-不受权限控制")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "buxiID", value = "补习ID", required = true)
    })
    public AjaxJson GetKxqCampus(String buxiID, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxstukxqtableService.GetKxqCampus(Long.valueOf(buxiID), qiyeID));
        return ajaxJson;
    }

    @RequestMapping(value = "getbuxistyle", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取学员补习课程补习方式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "buxiID", value = "补习ID", required = true)
    })
    public AjaxJson getbuxistyle(String buxiID, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        AjaxJson ajaxJson = new AjaxJson();
        Long kechengID = iPxbuxikechengtableService.getById(buxiID).getKechengID();
        Long buxistyleid = iPxkechengtableService.getById(kechengID).getBuxiStyleID();
        ajaxJson.setObj(iPxbuxistyletableService.getById(buxistyleid).getBuxiStyleName());
        return ajaxJson;
    }

    @RequestMapping(value = "getkxqclass", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取去校区班级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true)
    })
    public AjaxJson getkxqclass(String campusID, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxclasstableService.getkxqclass(Long.valueOf(campusID), qiyeID));
        return ajaxJson;
    }

    @ApiOperation("跨校区插班")
    @ResponseBody
    @RequestMapping(value = "savekxqClass", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson savekxqClass(@RequestBody kxqupclassFrom from, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long staffID = loginUser.getStaffID();
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());

        Long stuID = Long.valueOf(from.getStuid());
        Long bxkcID = Long.valueOf(from.getBuxiid());
        Long campusID = Long.valueOf(from.getCampusid());
        Long classID = Long.valueOf(from.getClassid());
        Boolean cbckb = from.getCbckb();
        Pxclasstable stuclass = iPxclasstableService.getById(classID);
        List<Pxstuclasstable> list = iPxstuclasstableService.list(new QueryWrapper<Pxstuclasstable>()
                .eq("classID", classID)
                .eq("qiyeID", qiyeID)
        );
        if (stuclass.getMaxStuNum() != -1 && list.size() >= stuclass.getMaxStuNum()) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("当前要插入的班级已达到最大值,已满班！");
            return ajaxJson;
        }

        if (classID == null) {
            Pxstutable stuTab = iPxstutableService.getById(stuID);
            Pxbuxikechengtable buxiTab = iPxbuxikechengtableService.getById(bxkcID);
            Pxkechengtable kc = iPxkechengtableService.getById(buxiTab.getKechengID());
            Pxsubjecttable sunjTable = iPxsubjecttableService.getById(kc.getSubjectID());
            List<Pxstuclasstable> pdTab = iPxstuclasstableService.getBybxID(bxkcID, qiyeID);
            Boolean flag = false;
            for (Pxstuclasstable item : pdTab) {
                Pxclasstable pdclassTab = iPxclasstableService.getById(item.getClassID());
                if (pdclassTab.getCampusID() == campusID) {
                    flag = true;
                }
            }
            if (flag == true) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                ajaxJson.setCode("N");
                ajaxJson.setMsg("改课程在该校区已经存在班级");
                return ajaxJson;
            } else {
                Long newclassID = 0L;
                Boolean ii = false;
                while (ii = false) {
                    Random rd = new Random();
                    int sjs = rd.nextInt(999);
                    String className = stuTab.getStuName() + "_" + sunjTable.getSubjectName() + "_一对一" + sjs;
                    List<Pxclasstable> pdclass = iPxclasstableService.getClassName(className, qiyeID);
                    if (pdclass.size() == 0) {
                        Pxclasstable cl = new Pxclasstable();
                        cl.setClassName(className);
                        cl.setCampusID(campusID);
                        cl.setIs1v1Class(1);
                        cl.setIsShow(1);
                        cl.setAddStaffID(staffID);
                        cl.setAddTime(new Date());
                        cl.setQiyeID(qiyeID);
                        cl.setClassState(0);
                        iPxclasstableService.save(cl);
                        ii = true;
                    }
                }

                Pxstuclasstable stucl = new Pxstuclasstable();
                stucl.setQiyeID(qiyeID);
                stucl.setClassID(classID);
                stucl.setBuxiID(bxkcID);
                iPxstuclasstableService.save(stucl);
                ajaxJson.setMsg("该校区已生成对应一对一班级");
            }
        } else {
            //判断学生是否有重复插班的情况
            Pxbuxikechengtable buxiTab = iPxbuxikechengtableService.getById(bxkcID);
            Pxstutable stua = iPxstutableService.getById(buxiTab.getStuID());
            Pxkechengtable kc = iPxkechengtableService.getById(buxiTab.getKechengID());
            Pxclasstable Newclass = iPxclasstableService.getById(classID);

            List<Pxbuxikechengtable> stuALLbuxiKC = iPxbuxikechengtableService.getstuALLbuxiKC(buxiTab.getStuID(), bxkcID, qiyeID);
            if (stuALLbuxiKC.size() > 0) {
                for (Pxbuxikechengtable stuOneBuxiKC : stuALLbuxiKC) {
                    List<Pxstuclasstable> stuIsInClass = iPxstuclasstableService.getBybxAndclassID(stuOneBuxiKC.getId(), classID, qiyeID);
                    if (stuIsInClass.size() > 0) {
                        Pxstutable stu = iPxstutableService.getById(stuID);
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        ajaxJson.setMsg(stu.getStuName() + "的其他课程已经在这个班了，不能重复插班！");
                        ajaxJson.setCode("N");
                        return ajaxJson;
                    }
                }
            }
            if (cbckb) {
                //如果新加入的班级原有排课了，再把学生加在选课表里面去
                List<Pxpaiketable> paikenews = iPxpaiketableService.getBykq(classID, qiyeID);
                for (Pxpaiketable item1 : paikenews) {
                    List<Pxxuanketable> xuankeTab = iPxxuanketableService.allxuankebypkID(item1.getId(), qiyeID);
                    List<Pxkeshistutable> iskaoqin = iPxkeshistutableService.otherStuks(item1.getClassID(), item1.getHaveClassDate(),
                            item1.getStartLessonDateTime(), item1.getEndLessonDateTime(), buxiTab.getStuID(), qiyeID);
                    if (iskaoqin.size() == 0) {
                        for (Pxxuanketable xuankeItem : xuankeTab) {
                            int h = iPxbuxikechengtableService.getAny(qiyeID, xuankeItem.getId()).size();
                            if (h == 0) {
                                iPxxuanketableService.removeById(xuankeItem.getId());
                            }
                            if (xuankeItem.getStuID() == buxiTab.getStuID() && h > 0) {
                                Pxstutable stu = iPxstutableService.getById(buxiTab.getStuID());
                                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                                ajaxJson.setCode("N");
                                ajaxJson.setMsg(stu.getStuName() + "有课程在这个班级的排课里，不能插班，请先从排课中移除再插班!");
                                return ajaxJson;
                            }
                        }

                        Pxxuanketable xk = new Pxxuanketable();
                        xk.setQiyeID(qiyeID);
                        xk.setBuxiID(buxiTab.getId());
                        xk.setPaikeID(item1.getId());
                        xk.setRecordDate(new Date());
                        xk.setStuID(buxiTab.getStuID());
                        xk.setType(1);
                        iPxxuanketableService.save(xk);
                    }

                }
            }
            Pxstuclasstable add = new Pxstuclasstable();
            add.setQiyeID(qiyeID);
            add.setBuxiID(bxkcID);
            add.setClassID(classID);
            iPxstuclasstableService.save(add);

            ajaxJson.setMsg("给" + stua.getStuName() + "插入班级操作，课程：" + kc.getKechengName() + ",班级:" + Newclass.getClassName());
        }

        return ajaxJson;
    }


    @ApiOperation("导出插班信息")
    @ResponseBody
    @RequestMapping(value = "DeriveChabanXinxi", method = RequestMethod.GET)
    public void DeriveChabanXinxi(HttpServletResponse response, HttpServletRequest request
    ) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        List<DeriveChabanXinxiVo> deriveChabanXinxiVos = iPxbuxikechengtableService.DeriveChabanXinxi(qiyeID);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "学号", "姓名", "年级", "课程", "课程状态", "班级"},
                deriveChabanXinxiVos,
                new String[]{"campusName", "stuID", "stuName", "stuGradeName", "kechengName", "isShow", "allClassName"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "插班信息导出.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //endregion

    //endregion
}
