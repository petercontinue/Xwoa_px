package com.xwcloud.cloud.wsc.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.Form.zhutiForm;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.Vo.WhdToupiaoCansaiStuVo;
import com.xwcloud.cloud.model.Vo.WhdToupiaoHuodongVo;
import com.xwcloud.cloud.model.Vo.WhdToupiaoRecordVo;
import com.xwcloud.cloud.model.entity.Activitytable;
import com.xwcloud.cloud.model.entity.WhdToupiaoCansaistu;
import com.xwcloud.cloud.model.entity.WhdToupiaoHuodong;
import com.xwcloud.cloud.model.entity.WhdToupiaoTprecord;
import com.xwcloud.cloud.wsc.Service.IActivitytableService;
import com.xwcloud.cloud.wsc.Service.IWhdToupiaoCansaistuService;
import com.xwcloud.cloud.wsc.Service.IWhdToupiaoHuodongService;
import com.xwcloud.cloud.wsc.Service.IWhdToupiaoTprecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/wsc/whdToupiao")
@Api(tags = "微活动：投票")
public class WhdToupiaoController {

    //region server注入

    @Autowired
    private IWhdToupiaoCansaistuService whdToupiaoCansaistuService;
    @Autowired
    private IWhdToupiaoHuodongService whdToupiaoHuodongService;
    @Autowired
    private IWhdToupiaoTprecordService whdToupiaoTprecordService;
    @Autowired
    IActivitytableService iActivitytableService;

    @Autowired
    private IWhdToupiaoHuodongService iWhdToupiaoHuodongService;

    //endregion

    //region 投票活动

    /**
     * 分页查询投票活动
     */
    @GetMapping("/getWhdToupiaoHuodongPage")
    @ResponseBody
    @ApiOperation("分页查询投票活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "huodongName", value = "活动名"),
            @ApiImplicitParam(name = "toupiaoStyle", value = "1.一人只能投一票 2.一人一天一票"),
            @ApiImplicitParam(name = "isUp", value = "是否上架，0不上架，1上架"),
            @ApiImplicitParam(name = "staffName", value = "添加人名"),
    })
    public AjaxJson getWhdToupiaoHuodongPage(HttpServletRequest request, long size, long current, String huodongName, Integer toupiaoStyle, Integer isUp, String staffName, int type) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<WhdToupiaoHuodongVo> wrapper = new QueryWrapper<>();
        wrapper.eq("a.qiyeID", qiyeID);
        if (!ObjectUtils.isEmpty(huodongName)) {
            wrapper.like("a.toupiaoHuodongName", huodongName);
        }
        if (toupiaoStyle != 0) {
            wrapper.eq("a.toupiaoStyle", toupiaoStyle);
        }
        if (isUp != 0) {
            wrapper.eq("a.isUp", isUp);
        }
        if (!ObjectUtils.isEmpty(staffName)) {
            wrapper.like("b.staffName", staffName);
        }
        if (type == 1) {
            //让小程序上显示还没过期的投票活动
            wrapper.ge("a.endtime", new Date());
        }
        Page<WhdToupiaoHuodongVo> page = whdToupiaoHuodongService.getWhdToupiaoHuodongPage(new Page<>(current, size), wrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @ResponseBody
    @ApiOperation("获得参加投票的人数")
    @GetMapping("gettoupaioNum")
    public AjaxJson gettoupaioNum(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("a.isUp", 1);//上架的活动
        queryWrapper.ge("a.endTime", new Date());
        ajaxJson.setObj(whdToupiaoHuodongService.gettoupaioNum(queryWrapper));
        return ajaxJson;
    }

    /**
     * 添加投票活动
     */
    @PostMapping("/addWhdToupiaoHuodong")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @ApiOperation("添加投票活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "toupiaohuodongname", value = "投票活动名称", required = true),
            @ApiImplicitParam(name = "miaoshu", value = "活动描述"),
            @ApiImplicitParam(name = "logo", value = "活动logo", required = true),
            @ApiImplicitParam(name = "toupiaostyle", value = "1.一人只能投一票 2.一人一天一票", required = true),
            @ApiImplicitParam(name = "jiangpin", value = "活动奖品", required = true),
            @ApiImplicitParam(name = "rules", value = "活动规则", required = true),
            @ApiImplicitParam(name = "jigoujianjie", value = "机构简介"),
            @ApiImplicitParam(name = "addtime", value = "添加时间"),
            @ApiImplicitParam(name = "adduser", value = "添加人"),
            @ApiImplicitParam(name = "starttime", value = "开始时间", required = true),
            @ApiImplicitParam(name = "endtime", value = "结束时间", required = true),
            @ApiImplicitParam(name = "isup", value = "是否上架，0不上架，1上架，默认值0"),
            @ApiImplicitParam(name = "qiyeid", value = "企业ID"),
    })
    public AjaxJson addWhdToupiaoHuodong(HttpServletRequest request, String toupiaohuodongname, String miaoshu, String logo, Integer toupiaostyle, String jiangpin,
                                         String rules, String jigoujianjie, String starttime, String endtime) throws ParseException {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        long qiyeID = Long.valueOf(loginUser.getQiyeID());
        WhdToupiaoHuodong whdToupiaoHuodong = new WhdToupiaoHuodong();
        whdToupiaoHuodong.setAddTime(new Date());

        whdToupiaoHuodong.setEndTime(ft.parse(endtime));
        whdToupiaoHuodong.setAddUser(loginUser.getStaffID());
        whdToupiaoHuodong.setJiangpin(jiangpin);
        whdToupiaoHuodong.setJigouJianjie(jigoujianjie);
        whdToupiaoHuodong.setLogo(logo);
        whdToupiaoHuodong.setMiaoshu(miaoshu);
        whdToupiaoHuodong.setQiyeID(qiyeID);
        whdToupiaoHuodong.setRules(rules);
        whdToupiaoHuodong.setStartTime(ft.parse(starttime));
        whdToupiaoHuodong.setToupiaoHuodongName(toupiaohuodongname);
        whdToupiaoHuodong.setToupiaoStyle(toupiaostyle);
        ajaxJson.setSuccess(whdToupiaoHuodongService.save(whdToupiaoHuodong));
        return ajaxJson;
    }

    /**
     * 根据ID更新投票活动
     */
    @PostMapping("/updateWhdToupiaoHuodongByID")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @ApiOperation("根据ID更新投票活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true),
            @ApiImplicitParam(name = "toupiaoHuodongName", value = "投票活动名称"),
            @ApiImplicitParam(name = "miaoshu", value = "活动描述"),
            @ApiImplicitParam(name = "logo", value = "活动logo"),
            @ApiImplicitParam(name = "toupiaoStyle", value = "1.一人只能投一票 2.一人一天一票"),
            @ApiImplicitParam(name = "jiangpin", value = "活动奖品"),
            @ApiImplicitParam(name = "rules", value = "活动规则"),
            @ApiImplicitParam(name = "jigouJianjie", value = "机构简介"),
            @ApiImplicitParam(name = "addTime", value = "添加时间"),
            @ApiImplicitParam(name = "addUser", value = "添加人"),
            @ApiImplicitParam(name = "startTime", value = "开始时间"),
            @ApiImplicitParam(name = "endTime", value = "结束时间"),
            @ApiImplicitParam(name = "isUp", value = "是否上架，0不上架，1上架，默认值0"),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID"),
    })
    public AjaxJson updateWhdToupiaoHuodongByID(HttpServletRequest request, long id, String toupiaohuodongname, String miaoshu, String logo, Integer toupiaostyle, String jiangpin,
                                                String rules, String jigoujianjie, String starttime, String endtime) throws ParseException {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = Long.valueOf(loginUser.getQiyeID());
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        WhdToupiaoHuodong whdToupiaoHuodong = whdToupiaoHuodongService.getById(id);
        whdToupiaoHuodong.setToupiaoHuodongName(toupiaohuodongname);
        whdToupiaoHuodong.setMiaoshu(miaoshu);
        whdToupiaoHuodong.setLogo(logo);
        whdToupiaoHuodong.setToupiaoStyle(toupiaostyle);
        whdToupiaoHuodong.setRules(rules);
        whdToupiaoHuodong.setJigouJianjie(jigoujianjie);
        whdToupiaoHuodong.setStartTime(ft.parse(starttime));
        whdToupiaoHuodong.setEndTime(ft.parse(endtime));
        whdToupiaoHuodong.setAddTime(new Date());
        ajaxJson.setSuccess(whdToupiaoHuodongService.updateById(whdToupiaoHuodong));
        return ajaxJson;
    }

    /**
     * 根据id批量删除投票活动
     */
    @DeleteMapping("/deleteWhdToupiaoHuodongByIDs")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("根据id删除投票活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "投票活动id", required = true),
    })
    public AjaxJson deleteWhdToupiaoHuodongByIDs(HttpServletRequest request, String id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(whdToupiaoHuodongService.removeById(Long.valueOf(id)));
        return ajaxJson;
    }

    /**
     * 更新投票活动的上架状态
     *
     * @param request
     * @param id
     * @param state
     * @return
     */
    @GetMapping("/UpdateToupiaohuodongState")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "要修改的活动ID", required = true),
            @ApiImplicitParam(name = "state", value = "要修改的活动状态：0：未上架；1：上架", required = true),
    })
    public AjaxJson UpdateToupiaohuodongState(HttpServletRequest request, long id, Integer state) {
        AjaxJson ajaxJson = new AjaxJson();
        WhdToupiaoHuodong whdToupiaoHuodong = whdToupiaoHuodongService.getById(id);
        whdToupiaoHuodong.setIsUp(state);
        ajaxJson.setSuccess(whdToupiaoHuodongService.updateById(whdToupiaoHuodong));
        return ajaxJson;
    }

    //endregion

    //region 投票参赛

    /**
     * 分页查询投票参赛
     */
    @GetMapping("/getWhdToupiaoCansaiStuPage")
    @ResponseBody
    @ApiOperation("分页查询投票参赛")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "huodongID", value = "投票活动ID", required = true),
    })
    public AjaxJson getWhdToupiaoCansaiStuPage(HttpServletRequest request, long size, long current, long huodongID, int type) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<WhdToupiaoCansaiStuVo> wrapper = new QueryWrapper<>();
        wrapper
                .eq("toupiaoHuodongID", huodongID)
                .eq("a.qiyeID", qiyeID);
        if (type == 1) {
            wrapper.orderByDesc("a.piaoshu");
            size = 6L;
        }
        Page<WhdToupiaoCansaiStuVo> page = whdToupiaoCansaistuService.getWhdToupiaoCansaiStuPage(new Page(current, size), wrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 添加投票参赛学员
     */
    @PostMapping("/addWhdToupiaoCansaiStu")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @ApiOperation("添加投票参赛学员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuname", value = "参赛学生姓名", required = true),
            @ApiImplicitParam(name = "xuanyan", value = "参赛宣言"),
            @ApiImplicitParam(name = "introduction", value = "个人简介"),
            @ApiImplicitParam(name = "logo", value = "头像", required = true),
            @ApiImplicitParam(name = "lookTimes", value = "查看次数：默认0"),
            @ApiImplicitParam(name = "piaoshu", value = "票数：默认0"),
            @ApiImplicitParam(name = "toupiaohuodongid", value = "活动ID", required = true),
            @ApiImplicitParam(name = "adduser", value = "添加人"),
            @ApiImplicitParam(name = "addtime", value = "添加时间"),
            @ApiImplicitParam(name = "qiyeid", value = "企业ID"),
    })
    public AjaxJson addWhdToupiaoCansaiStu(HttpServletRequest request, WhdToupiaoCansaistu whdToupiaoCansaistu) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        String msg = "";
        if (ObjectUtils.isEmpty(whdToupiaoCansaistu.getStuname())) {
            msg += "学员姓名不可为空。";
        }
        if (ObjectUtils.isEmpty(whdToupiaoCansaistu.getLogo())) {
            msg += "学员头像不可为空。";
        }
        if (ObjectUtils.isEmpty(whdToupiaoCansaistu.getToupiaohuodongid())) {
            msg += "投票活动不可为空。";
        }
        if (!ObjectUtils.isEmpty(msg)) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg(msg);
            return ajaxJson;
        }
        QueryWrapper<WhdToupiaoCansaistu> wrapper = new QueryWrapper<>();
        wrapper
                .eq("toupiaoHuodongID", whdToupiaoCansaistu.getToupiaohuodongid())
                .eq("stuName", whdToupiaoCansaistu.getStuname())
                .eq("qiyeID", qiyeID);
        int count = whdToupiaoCansaistuService.count(wrapper);
        if (count > 0) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("请勿重复添加。");
            return ajaxJson;
        }
        ajaxJson.setSuccess(
                whdToupiaoCansaistu
                        .setAddtime(LocalDateTime.now())
                        .setAdduser(loginUser.getStaffID())
                        .setQiyeID(qiyeID)
                        .insert()
        );
        return ajaxJson;
    }

    /**
     * 根据ID更新投票参赛学员
     */
    @PostMapping("/updateWhdToupiaoCansaiStuByID")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @ApiOperation("根据ID更新投票参赛学员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true),
            @ApiImplicitParam(name = "stuname", value = "参赛学生姓名"),
            @ApiImplicitParam(name = "xuanyan", value = "参赛宣言"),
            @ApiImplicitParam(name = "introduction", value = "个人简介"),
            @ApiImplicitParam(name = "logo", value = "头像"),
            @ApiImplicitParam(name = "lookTimes", value = "查看次数：默认0"),
            @ApiImplicitParam(name = "piaoshu", value = "票数：默认0"),
            @ApiImplicitParam(name = "toupiaohuodongid", value = "活动ID"),
    })
    public AjaxJson updateWhdToupiaoCansaiStuByID(HttpServletRequest request, WhdToupiaoCansaistu whdToupiaoCansaistu) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        String msg = "";
        if (ObjectUtils.isEmpty(whdToupiaoCansaistu.getId())) {
            msg += "粗错鸟。";
        }
        if (!ObjectUtils.isEmpty(msg)) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg(msg);
            return ajaxJson;
        }
        QueryWrapper<WhdToupiaoCansaistu> wrapper = new QueryWrapper<>();
        wrapper
                .ne("id", whdToupiaoCansaistu.getId())
                .eq("qiyeID", qiyeID);
        if (!ObjectUtils.isEmpty(whdToupiaoCansaistu.getToupiaohuodongid()) && !ObjectUtils.isEmpty(whdToupiaoCansaistu.getStuname())) {
            wrapper
                    .eq("toupiaoHuodongID", whdToupiaoCansaistu.getToupiaohuodongid())
                    .eq("stuName", whdToupiaoCansaistu.getStuname());
        } else {
            WhdToupiaoCansaistu byId = whdToupiaoCansaistuService.getById(whdToupiaoCansaistu.getId());
            if (!ObjectUtils.isEmpty(whdToupiaoCansaistu.getToupiaohuodongid())) {
                wrapper
                        .eq("toupiaoHuodongID", whdToupiaoCansaistu.getToupiaohuodongid())
                        .eq("stuName", byId.getStuname());
            } else {
                wrapper
                        .eq("toupiaoHuodongID", byId.getToupiaohuodongid())
                        .eq("stuName", whdToupiaoCansaistu.getStuname());
            }
        }
        int count = whdToupiaoCansaistuService.count(wrapper);
        if (count > 0) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("请勿重复添加。");
            return ajaxJson;
        }
        ajaxJson.setSuccess(
                whdToupiaoCansaistu
                        .setAddtime(null)
                        .setAdduser(null)
                        .setQiyeID(null)
                        .updateById()
        );
        return ajaxJson;
    }

    /**
     * 根据id批量删除投票参赛学员
     */
    @DeleteMapping("/deleteWhdToupiaoCansaiStuByIDs/{ids}")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("根据id批量删除投票参赛学员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "投票参赛学员ids", required = true),
    })
    public AjaxJson deleteWhdToupiaoCansaiStuByIDs(HttpServletRequest request, @PathVariable List<String> ids) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(whdToupiaoCansaistuService.removeByIds(ids));
        return ajaxJson;
    }

    //endregion

    //region 投票记录

    /**
     * 分页查询投票记录
     */
    @GetMapping("/getWhdToupiaoRecordPage")
    @ResponseBody
    @ApiOperation("分页查询投票记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页数", example = "1", required = true),
    })
    public AjaxJson getWhdToupiaoRecordPage(HttpServletRequest request, long size, long current, long huodongID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<WhdToupiaoRecordVo> wrapper = new QueryWrapper<>();
        wrapper.eq("a.qiyeID", qiyeID);
        wrapper.eq("a.toupiaoHuodongID", huodongID);
        wrapper.orderByDesc("toupiaotime");
        Page<WhdToupiaoRecordVo> page = whdToupiaoTprecordService.getWhdToupiaoRecordPage(new Page(current, size), wrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 添加投票记录
     */
    @PostMapping("/addWhdToupiaoRecord")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @ApiOperation("添加投票记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cansaistuid", value = "参赛学员ID，对应投票参赛的id", required = true),
            @ApiImplicitParam(name = "wscuserid", value = "微商城用户ID", required = true),
            @ApiImplicitParam(name = "toupiaotime", value = "投票时间"),
            @ApiImplicitParam(name = "toupiaohuodongid", value = "投票活动ID", required = true),
            @ApiImplicitParam(name = "qiyeid", value = "企业ID"),
    })
    public AjaxJson addWhdToupiaoRecord(HttpServletRequest request, String cansaistuid, String toupiaohuodongid) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String msg = "";
        WhdToupiaoHuodong hd = whdToupiaoHuodongService.getById(toupiaohuodongid);
        Integer toupiaostyle = hd.getToupiaoStyle();//投票方式
        if (toupiaostyle == 1) {
            //一个人只能投一次
            List<WhdToupiaoTprecord> list = whdToupiaoTprecordService.list(new QueryWrapper<WhdToupiaoTprecord>()
                    .eq("qiyeID", loginUser.getQiyeID())
                    .eq("toupiaohuodongid", toupiaohuodongid)
                    .eq("wscuserid", loginUser.getWscUserID()));
            if (list.size() > 0) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("投票活动设置为：一个人只能投一次！！！");
                return ajaxJson;
            }
        } else if (toupiaostyle == 2) {
            //每人每天只能投一次
            List<WhdToupiaoTprecord> list = whdToupiaoTprecordService.list(new QueryWrapper<WhdToupiaoTprecord>()
                    .eq("qiyeID", loginUser.getQiyeID())
                    .eq("toupiaohuodongid", toupiaohuodongid)
                    .eq("wscuserid", loginUser.getWscUserID())
                    .orderByDesc("toupiaotime")
            );
            if (list.size() > 0) {
                WhdToupiaoTprecord one = list.get(0); //取最新一条
                String td = sdf.format(one.getToupiaotime());
                String nD = sdf.format(new Date());
                if (td.equals(nD)) {
                    ajaxJson.setCode("N");
                    ajaxJson.setMsg("投票活动设置为：一个人每天只能投一次！！！");
                    return ajaxJson;
                }
            }
        }

        if (!StringUtils.isNotBlank(cansaistuid)) {
            msg += "参赛学员不可为空。";
        }

        if (!StringUtils.isNotBlank(toupiaohuodongid)) {
            msg += "投票活动不可为空。";
        }
        if (msg != "") {
            ajaxJson.setMsg(msg);
            return ajaxJson;
        }
        WhdToupiaoTprecord tp = new WhdToupiaoTprecord();
        tp.setCansaistuid(Long.valueOf(cansaistuid));
        tp.setWscuserid(loginUser.getWscUserID());
        tp.setToupiaotime(new Date());
        tp.setToupiaohuodongid(Long.valueOf(toupiaohuodongid));
        tp.setQiyeID(Long.valueOf(loginUser.getQiyeID()));
        whdToupiaoTprecordService.save(tp);

        WhdToupiaoCansaistu stu = whdToupiaoCansaistuService.getById(cansaistuid);
        stu.setPiaoshu(stu.getPiaoshu() + 1);
        whdToupiaoCansaistuService.updateById(stu);
        return ajaxJson;
    }

    /**
     * 根据ID更新投票记录
     */
    @PostMapping("/updateWhdToupiaoRecordByID")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @ApiOperation("根据ID更新投票记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true),
            @ApiImplicitParam(name = "cansaistuid", value = "参赛学员ID，对应投票参赛的id"),
            @ApiImplicitParam(name = "wscuserid", value = "微商城用户ID"),
            @ApiImplicitParam(name = "toupiaohuodongid", value = "投票活动ID"),
    })
    public AjaxJson updateWhdToupiaoRecordByID(WhdToupiaoTprecord whdToupiaoTprecord) {
        AjaxJson ajaxJson = new AjaxJson();
        String msg = "";
        if (ObjectUtils.isEmpty(whdToupiaoTprecord.getId())) {
            msg += "粗错鸟。。";
        }
        if (!ObjectUtils.isEmpty(msg)) {
            ajaxJson.setMsg(msg);
            return ajaxJson;
        }
        ajaxJson.setSuccess(
                whdToupiaoTprecord
                        .setToupiaotime(null)
                        .setQiyeID(null)
                        .updateById()
        );
        return ajaxJson;
    }

    /**
     * 根据id批量删除投票记录
     */
    @DeleteMapping("/deleteWhdToupiaoRecordByIDs/{ids}")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("根据id批量删除投票记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "投票记录ids", required = true),
    })
    public AjaxJson deleteWhdToupiaoRecordByIDs(HttpServletRequest request, @PathVariable List<String> ids) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(whdToupiaoTprecordService.removeByIds(ids));
        return ajaxJson;
    }

    //endregion

    //region 主题设置
    @ApiOperation("主题设置")
    @ResponseBody
    @GetMapping("getactivityPages")
    public AjaxJson getactivityPages(HttpServletRequest request, Long size, Long current) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<Activitytable> page = new Page<>(current, size);
        QueryWrapper<Activitytable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        ajaxJson.setObj(iActivitytableService.page(page, queryWrapper));
        return ajaxJson;
    }


    @ApiOperation("删除主题")
    @ResponseBody
    @DeleteMapping("deleteZhuti")
    public AjaxJson deleteZhuti(String id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(iActivitytableService.removeById(id));
        return ajaxJson;
    }


    @ApiOperation("设置启用")
    @ResponseBody
    @PostMapping("zhanshiIsShow")
    public AjaxJson zhanshiIsShow(String id, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Activitytable one = iActivitytableService.getById(id);
        if (one == null) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("数据格式有误，请联系客服!");
            return ajaxJson;
        }
        if (one.getIsUser()) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("所选项已经是使用状态");
            return ajaxJson;
        }
        List<Activitytable> list = iActivitytableService.list(new QueryWrapper<Activitytable>()
                .eq("type", 1)//投票
                .eq("qiyeID", loginUser.getQiyeID()));
        for (Activitytable item : list) {
            if (item.getId() == Long.valueOf(id)) {
                item.setIsUser(true);
            } else {
                item.setIsUser(false);
            }
            iActivitytableService.updateById(item);
        }
        return ajaxJson;
    }

    @ApiOperation("添加或修改主题")
    @ResponseBody
    @PostMapping("addOreditZhuti")
    public AjaxJson addOreditZhuti(@RequestBody zhutiForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        String id = form.getId();
        if (StringUtils.isNotBlank(id)) {
            //修改
            Activitytable one = iActivitytableService.getById(id);
            one
                    .setActivityName(form.getFxtitle())
                    .setActivityDiscription(form.getFxshuoming())
                    .setLogo(form.getLogo())
                    .setImages(form.getLunboimgs())
                    .setSchoolName(form.getJigouname())
                    .setCampusAdress(form.getCampusaddress())
                    .setLianxiTel(form.getZxtel());
            iActivitytableService.updateById(one);
        } else {
            //添加
            List<Activitytable> list = iActivitytableService.list(
                    new QueryWrapper<Activitytable>()
                            .eq("activityName", form.getFxtitle())
                            .eq("type", 1)
                            .eq("qiyeID", loginUser.getQiyeID())
            );
            if (list.size() > 0) {
                ajaxJson.setMsg("已存在该主题");
                ajaxJson.setCode("N");
                return ajaxJson;
            } else {
                Activitytable add = new Activitytable();
                add
                        .setActivityName(form.getFxtitle())
                        .setActivityDiscription(form.getFxshuoming())
                        .setLogo(form.getLogo())
                        .setImages(form.getLunboimgs())
                        .setType(1)
                        .setIsUser(false)
                        .setSchoolName(form.getJigouname())
                        .setCampusAdress(form.getCampusaddress())
                        .setLianxiTel(form.getZxtel())
                        .setQiyeID(loginUser.getQiyeID())
                ;
                iActivitytableService.save(add);
            }

        }
        return ajaxJson;
    }
    //endregion

    /**
     * 根据活动ID查询投票活动详细信息
     * @param request
     * @param toupiaoID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getwehdToupiaoInfoDetail",method = RequestMethod.GET)
    public AjaxJson getwehdToupiaoInfoDetail(HttpServletRequest request,long toupiaoID){
        AjaxJson ajaxJson=new AjaxJson();
        LoginUser loginUser=(LoginUser)request.getAttribute("loginUser");
        ajaxJson.setObj(iWhdToupiaoHuodongService.GetToupiaoHuodongInfoByID(toupiaoID));
        return  ajaxJson;
    }

}
