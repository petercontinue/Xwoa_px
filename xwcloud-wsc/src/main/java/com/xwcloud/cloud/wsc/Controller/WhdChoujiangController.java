package com.xwcloud.cloud.wsc.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.Sso.LoginUser;

import com.xwcloud.cloud.model.Vo.WhdChoujiangJiangpinVo;
import com.xwcloud.cloud.model.Vo.WhdChoujiangRecordrVo;
import com.xwcloud.cloud.model.Vo.allgailvVo;
import com.xwcloud.cloud.model.entity.WhdChoujiangCjrecord;
import com.xwcloud.cloud.model.entity.WhdChoujiangHuodong;
import com.xwcloud.cloud.model.entity.WhdChoujiangJiangping;
import com.xwcloud.cloud.wsc.Service.IWhdChoujiangCjrecordService;
import com.xwcloud.cloud.wsc.Service.IWhdChoujiangHuodongService;
import com.xwcloud.cloud.wsc.Service.IWhdChoujiangJiangpingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Controller
@RequestMapping("/wsc/whdChoujiang")
@Api(tags = "微活动：抽奖")
public class WhdChoujiangController {

    //region server注入

    @Autowired
    private IWhdChoujiangCjrecordService whdChoujiangCjrecordService;

    @Autowired
    private IWhdChoujiangHuodongService whdChoujiangHuodongService;

    @Autowired
    private IWhdChoujiangJiangpingService whdChoujiangJiangpingService;

    @Autowired
    private  IWhdChoujiangHuodongService iWhdChoujiangHuodongService;

    //endregion

    //region 活动

    /**
     * 分页查询活动
     */
    @GetMapping("/getWhdChoujiangHuodongPage")
    @ResponseBody
    @ApiOperation("分页查询活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "choujianghuodongname", value = "活动名"),
            @ApiImplicitParam(name = "starttime", value = "开始时间"),
            @ApiImplicitParam(name = "endtime", value = "结束时间"),
            @ApiImplicitParam(name = "addtime", value = "添加时间"),
            @ApiImplicitParam(name = "adduser", value = "添加人"),
            @ApiImplicitParam(name = "isup", example = "-1", value = "是否上架，1上架，2不上架"),
    })
    public AjaxJson getWhdChoujiangHuodongPage(HttpServletRequest request, Long size, Long current, String choujianghuodongname, String starttime, String endtime, String addtime, String adduser, int isup) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<WhdChoujiangHuodong> wrapper = new QueryWrapper<>();
        wrapper.eq("a.qiyeID", qiyeID);
        if (!ObjectUtils.isEmpty(choujianghuodongname)) {
            wrapper.like("a.choujiangHuodongName", choujianghuodongname);
        }
        if (!ObjectUtils.isEmpty(starttime)) {
            wrapper.ge("a.startTime", starttime);
        }
        if (!ObjectUtils.isEmpty(endtime)) {
            wrapper.le("a.endTime", endtime);
        }
        if (!ObjectUtils.isEmpty(addtime)) {
            wrapper.eq("a.addTime", addtime);
        }
        if (!ObjectUtils.isEmpty(adduser)) {
//            wrapper.select("(select staffName )");
            wrapper.eq("b.staffName", adduser);
        }
        if (isup != -1) {
            wrapper.eq("a.isUp", isup);
        }
        Page<HashMap<String, Object>> page = whdChoujiangHuodongService.getcjhuodongPages(new Page<>(current, size), wrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 添加抽奖活动
     */
    @PostMapping("/addWhdChoujiangHuodong")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @ApiOperation("添加抽奖活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "choujianghuodongname", value = "活动名", required = true),
            @ApiImplicitParam(name = "shuoming", value = "说明"),
            @ApiImplicitParam(name = "starttime", value = "开始时间", required = true),
            @ApiImplicitParam(name = "endtime", value = "结束时间", required = true),
            @ApiImplicitParam(name = "choujiangstyle", value = "1 总共能抽多少次，2每天能抽多少次", required = true),
            @ApiImplicitParam(name = "cishu", value = "抽奖次数,默认值0", required = true),
    })
    public AjaxJson addWhdChoujiangHuodong(HttpServletRequest request,
                                           String choujianghuodongname,
                                           String shuoming,
                                           String starttime,
                                           String endtime,
                                           int choujiangstyle,
                                           int cishu) {
        AjaxJson ajaxJson = new AjaxJson();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Long staffID = loginUser.getStaffID();
        String msg = "";
        if (ObjectUtils.isEmpty(choujianghuodongname)) {
            msg += "活动名称不可为空。";
        }
        if (ObjectUtils.isEmpty(starttime)) {
            msg += "开始时间不可为空。";
        }
        if (ObjectUtils.isEmpty(endtime)) {
            msg += "结束时间不可为空。";
        }
        if (!ObjectUtils.isEmpty(msg)) {
            ajaxJson.setMsg(msg);
            ajaxJson.setCode("N");
            return ajaxJson;
        }

//        LocalDateTime ST = LocalDateTime.parse(starttime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        LocalDateTime ET = LocalDateTime.parse(endtime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Date ST = null;
        try {
            ST = sdf.parse(starttime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date ET = null;
        try {
            ET = sdf.parse(endtime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (ST.getTime() > ET.getTime()) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("开始时间必须小于结束时间。");
            return ajaxJson;
        }

        WhdChoujiangHuodong nhd = new WhdChoujiangHuodong();
        nhd.setChoujiangHuodongName(choujianghuodongname);
        nhd.setShuoming(shuoming);
        nhd.setStartTime(ST.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        nhd.setEndTime(ET.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        nhd.setAddTime(LocalDateTime.now());
        nhd.setAddUser(staffID);
        nhd.setChoujiangStyle(choujiangstyle);
        nhd.setCishu(cishu);
        nhd.setIsUp(2);
        nhd.setQiyeID(qiyeID);
        whdChoujiangHuodongService.save(nhd);
        return ajaxJson;
    }

    /**
     * 根据ID更新抽奖活动
     */
    @PostMapping("/updateWhdChoujiangHuodongByID")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @ApiOperation("根据ID更新抽奖活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true),
            @ApiImplicitParam(name = "choujianghuodongname", value = "活动名"),
            @ApiImplicitParam(name = "shuoming", value = "说明"),
            @ApiImplicitParam(name = "jigoujianjie", value = "机构简介"),
            @ApiImplicitParam(name = "starttime", value = "开始时间"),
            @ApiImplicitParam(name = "endtime", value = "结束时间"),
            @ApiImplicitParam(name = "addtime", value = "添加时间"),
            @ApiImplicitParam(name = "adduser", value = "添加人"),
            @ApiImplicitParam(name = "choujiangstyle", value = "1 总共能抽多少次，2每天能抽多少次"),
            @ApiImplicitParam(name = "cishu", value = "抽奖次数,默认值0"),
            @ApiImplicitParam(name = "isup", value = "是否上架，1上架，2不上架"),
            @ApiImplicitParam(name = "qiyeid", value = "企业ID"),
    })
    public AjaxJson updateWhdChoujiangHuodongByID(HttpServletRequest request, WhdChoujiangHuodong whdChoujiangHuodong) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long qiyeID = loginUser.getQiyeID();
        String msg = "";
        if (ObjectUtils.isEmpty(whdChoujiangHuodong.getId())) {
            msg += "粗错鸟。";
        }
        if (!ObjectUtils.isEmpty(msg)) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg(msg);
            return ajaxJson;
        }
        if (!ObjectUtils.isEmpty(whdChoujiangHuodong.getStartTime()) && !ObjectUtils.isEmpty(whdChoujiangHuodong.getEndTime())) {
            Date choujiangStartTime = Date.from(whdChoujiangHuodong.getStartTime().atZone(ZoneId.systemDefault()).toInstant());
            Date choujiangEndTime = Date.from(whdChoujiangHuodong.getEndTime().atZone(ZoneId.systemDefault()).toInstant());
            if (choujiangStartTime.getTime() > choujiangEndTime.getTime()) {
                ajaxJson.setMsg("开始时间必须小于结束时间。");
                return ajaxJson;
            }
        }
        WhdChoujiangHuodong whdChoujiangHuodongById = whdChoujiangHuodongService.getById(whdChoujiangHuodong.getId());
        if (!ObjectUtils.isEmpty(whdChoujiangHuodong.getStartTime()) && ObjectUtils.isEmpty(whdChoujiangHuodong.getEndTime())) {
            Date choujiangStartTime = Date.from(whdChoujiangHuodong.getStartTime().atZone(ZoneId.systemDefault()).toInstant());
            Date choujiangEndTime = Date.from(whdChoujiangHuodongById.getEndTime().atZone(ZoneId.systemDefault()).toInstant());
            if (choujiangStartTime.getTime() > choujiangEndTime.getTime()) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("开始时间必须小于结束时间。");
                return ajaxJson;
            }
        }
        if (!ObjectUtils.isEmpty(whdChoujiangHuodong.getEndTime()) && ObjectUtils.isEmpty(whdChoujiangHuodong.getStartTime())) {
            Date choujiangStartTime = Date.from(whdChoujiangHuodongById.getStartTime().atZone(ZoneId.systemDefault()).toInstant());
            Date choujiangEndTime = Date.from(whdChoujiangHuodong.getEndTime().atZone(ZoneId.systemDefault()).toInstant());
            if (choujiangStartTime.getTime() > choujiangEndTime.getTime()) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("开始时间必须小于结束时间。");
                return ajaxJson;
            }
        }
        if (!ObjectUtils.isEmpty(whdChoujiangHuodong.getChoujiangHuodongName())) {
            QueryWrapper<WhdChoujiangHuodong> wrapper = new QueryWrapper<>();
            wrapper
                    .ne("id", whdChoujiangHuodong.getId())
                    .eq("choujiangHuodongName", whdChoujiangHuodong.getChoujiangHuodongName())
                    .eq("qiyeID", qiyeID);
            int count = whdChoujiangHuodongService.count(wrapper);
            if (count > 0) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("系统已存在同名活动。");
                return ajaxJson;
            }
        }
        ajaxJson.setSuccess(
                whdChoujiangHuodong
                        .setAddTime(null)
                        .setAddUser(null)
                        .setQiyeID(null)
                        .updateById()
        );
        return ajaxJson;
    }

    /**
     * 根据id删除抽奖活动
     */
    @DeleteMapping("/deleteWhdChoujiangHuodongByID")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("根据id删除抽奖活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "抽奖活动id", required = true),
    })
    public AjaxJson deleteWhdChoujiangHuodongByIDs(String id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(whdChoujiangHuodongService.removeById(id));
        return ajaxJson;
    }

    /**
     * @Description: updateUpStyle()方法作用:根据id修改活动上架类型
     * @param:[id, isup]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/4/2 11:19
     */
    @PostMapping("/updateUpStyle")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("根据id修改活动上架类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品id", required = true),
            @ApiImplicitParam(name = "isup", value = "上架类型", required = true),
    })
    public AjaxJson updateUpStyle(HttpServletRequest request, String id, int isup) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        WhdChoujiangHuodong edit = whdChoujiangHuodongService.getById(id);
        if (edit.getIsUp() == isup) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("已是目标状态");
            return ajaxJson;
        }
        if (isup == 1) {
            //上架
            WhdChoujiangHuodong one = whdChoujiangHuodongService.getOne(new QueryWrapper<WhdChoujiangHuodong>()
                    .eq("isup", 2)
                    .eq("qiyeID", loginUser.getQiyeID())
            );
            if (one != null) {
                one.setIsUp(2); //把上架的活动下架
                whdChoujiangHuodongService.save(one);
            }
        }
        edit.setIsUp(isup);
        whdChoujiangHuodongService.updateById(edit);
        return ajaxJson;
    }

    //endregion

    //region 奖品

    /**
     * 分页查询活动奖品
     */
    @GetMapping("/getWhdChoujiangJiangpinPage")
    @ResponseBody
    @ApiOperation("分页查询活动奖品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "huodongID", value = "活动ID", required = true),
    })
    public AjaxJson getWhdChoujiangJiangpinPage(HttpServletRequest request, Long size, Long current, String huodongID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        Long staffID = loginUser.getStaffID();
        List<WhdChoujiangJiangping> haveJP = whdChoujiangJiangpingService.list(new QueryWrapper<WhdChoujiangJiangping>()
                .eq("choujianghuodongid", huodongID)
                .eq("qiyeID", qiyeID));
        if (haveJP.size() < 8) {  //给活动添加默认奖品
            for (int i = 0; i < 8 - haveJP.size(); i++) {
                WhdChoujiangJiangping jp = new WhdChoujiangJiangping();
                jp.setJiangpinglevel("参与奖");
                jp.setJiangpingname("谢谢参与");
                jp.setChoujianghuodongid(Long.valueOf(huodongID));
                jp.setZhongjianggailv("0.1");
                jp.setJiangpingtotalnum(i + 1);
                jp.setType(0);
                jp.setQiyeID(qiyeID);
                jp.setAdduser(staffID);
                jp.setAddtime(new Date());
                whdChoujiangJiangpingService.save(jp);
            }
        }

        allgailvVo Allgl = whdChoujiangJiangpingService.getallgailv(Long.valueOf(huodongID), qiyeID);
        BigDecimal nowsum = Allgl.getAlllv();
        if (nowsum.compareTo(BigDecimal.valueOf(1)) == 1) {//如果全部概率大于1
            //确保是添加够8个商品的
            List<WhdChoujiangJiangping> glJP = whdChoujiangJiangpingService.list(new QueryWrapper<WhdChoujiangJiangping>()
                    .eq("choujianghuodongid", huodongID)
                    .eq("qiyeID", qiyeID));
            for (WhdChoujiangJiangping item : glJP) {
                item.setZhongjianggailv("0");
                whdChoujiangJiangpingService.updateById(item);
            }
        }

        QueryWrapper<WhdChoujiangJiangpinVo> wrapper = new QueryWrapper<>();
        wrapper
                .eq("a.choujiangHuodongID", huodongID)
                .eq("a.qiyeID", qiyeID)
                .orderByAsc("id");
        Page<WhdChoujiangJiangpinVo> page = whdChoujiangJiangpingService.getWhdChoujiangJiangpinPage(new Page<>(current, size), wrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 添加抽奖奖品
     */
    @PostMapping("/addWhdChoujiangJiangpin")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @ApiOperation("添加抽奖奖品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jiangpinglevel", value = "奖品等级", required = true),
            @ApiImplicitParam(name = "jiangpingname", value = "奖品名称", required = true),
            @ApiImplicitParam(name = "jiangpingimg", value = "奖品图片"),
            @ApiImplicitParam(name = "choujianghuodongid", value = "抽奖活动ID", required = true),
            @ApiImplicitParam(name = "zhongjianggailv", value = "中奖概率", required = true),
            @ApiImplicitParam(name = "jiangpingtotalnum", value = "奖品数量", required = true),
            @ApiImplicitParam(name = "type", value = "0：不是奖品，1：是奖品", required = true),
            @ApiImplicitParam(name = "qiyeid"),
            @ApiImplicitParam(name = "adduser", value = "添加人"),
            @ApiImplicitParam(name = "addtime", value = "添加时间"),
    })
    public AjaxJson addWhdChoujiangJiangpin(HttpServletRequest request, WhdChoujiangJiangping whdChoujiangJiangping) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        String msg = "";
        if (ObjectUtils.isEmpty(whdChoujiangJiangping.getJiangpinglevel())) {
            msg += "奖品等级不可为空。";
        }
        if (ObjectUtils.isEmpty(whdChoujiangJiangping.getJiangpingname())) {
            msg += "奖品名称不可为空。";
        }
        if (ObjectUtils.isEmpty(whdChoujiangJiangping.getChoujianghuodongid())) {
            msg += "抽奖活动不可为空。";
        }
        if (ObjectUtils.isEmpty(whdChoujiangJiangping.getZhongjianggailv())) {
            msg += "中奖概率不可为空。";
        }
        if (ObjectUtils.isEmpty(whdChoujiangJiangping.getJiangpingtotalnum())) {
            msg += "奖品数量不可为空。";
        }
        if (ObjectUtils.isEmpty(whdChoujiangJiangping.getType())) {
            msg += "奖品类型不可为空。";
        }
        if (!ObjectUtils.isEmpty(msg)) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg(msg);
            return ajaxJson;
        }
        QueryWrapper<WhdChoujiangJiangping> wrapper = new QueryWrapper<>();
        wrapper
                .eq("jiangpingLevel", whdChoujiangJiangping.getJiangpinglevel())
                .eq("jiangpingName", whdChoujiangJiangping.getJiangpingname())
                .eq("qiyeID", qiyeID);
        int count = whdChoujiangJiangpingService.count(wrapper);
        if (count > 0) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("请勿重复添加。");
            return ajaxJson;
        }
        ajaxJson.setSuccess(
                whdChoujiangJiangping
                        .setAddtime(new Date())
                        .setAdduser(loginUser.getStaffID())
                        .setQiyeID(qiyeID)
                        .insert()
        );
        return ajaxJson;
    }

    /**
     * 根据ID更新抽奖奖品
     */
    @PostMapping("/updateWhdChoujiangJiangpinByID")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @ApiOperation("根据ID更新抽奖奖品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true),
            @ApiImplicitParam(name = "jiangpinglevel", value = "奖品等级"),
            @ApiImplicitParam(name = "jiangpingname", value = "奖品名称"),
            @ApiImplicitParam(name = "jiangpingimg", value = "奖品图片"),
            @ApiImplicitParam(name = "choujianghuodongid", value = "抽奖活动ID"),
            @ApiImplicitParam(name = "zhongjianggailv", value = "中奖概率"),
            @ApiImplicitParam(name = "jiangpingtotalnum", value = "奖品数量"),
            @ApiImplicitParam(name = "type", value = "0：不是奖品，1：是奖品"),
            @ApiImplicitParam(name = "qiyeid"),
            @ApiImplicitParam(name = "adduser", value = "添加人"),
            @ApiImplicitParam(name = "addtime", value = "添加时间"),
    })
    public AjaxJson updateWhdChoujiangJiangpinByID(HttpServletRequest request, WhdChoujiangJiangping whdChoujiangJiangping) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        String msg = "";
        if (ObjectUtils.isEmpty(whdChoujiangJiangping.getId())) {
            msg += "粗错鸟。";
        }
        if (!ObjectUtils.isEmpty(msg)) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg(msg);
            return ajaxJson;
        }
        QueryWrapper<WhdChoujiangJiangping> wrapper = new QueryWrapper<>();
        wrapper
                .ne("id", whdChoujiangJiangping.getId())
                .eq("qiyeID", qiyeID);
        if (!ObjectUtils.isEmpty(whdChoujiangJiangping.getJiangpinglevel()) && !ObjectUtils.isEmpty(whdChoujiangJiangping.getJiangpingname())) {
            wrapper
                    .eq("jiangpingLevel", whdChoujiangJiangping.getJiangpinglevel())
                    .eq("jiangpingName", whdChoujiangJiangping.getJiangpingname());
        } else {
            WhdChoujiangJiangping byId = whdChoujiangJiangpingService.getById(whdChoujiangJiangping.getId());
            if (!ObjectUtils.isEmpty(whdChoujiangJiangping.getJiangpinglevel())) {
                wrapper
                        .eq("jiangpingLevel", whdChoujiangJiangping.getJiangpinglevel())
                        .eq("jiangpingName", byId.getJiangpingname());
            } else {
                wrapper
                        .eq("jiangpingLevel", byId.getJiangpinglevel())
                        .eq("jiangpingName", whdChoujiangJiangping.getJiangpingname());
            }
        }
        int count = whdChoujiangJiangpingService.count(wrapper);
        if (count > 0) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("系统已存在相同记录。");
            return ajaxJson;
        }
        ajaxJson.setSuccess(
                whdChoujiangJiangping
                        .setAddtime(null)
                        .setAdduser(null)
                        .setQiyeID(null)
                        .updateById()
        );
        return ajaxJson;
    }

    @DeleteMapping("/deleteWhdChoujiangJiangpinByIDs")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("根据id批量删除抽奖奖品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "商品类别ids", required = true),
    })
    public AjaxJson deleteWhdChoujiangJiangpinByIDs(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] Plist = ids.split(",");
        ajaxJson.setSuccess(whdChoujiangJiangpingService.removeByIds(Arrays.asList(Plist)));
        return ajaxJson;
    }


    //endregion

    //region 抽奖记录

    /**
     * 分页查询抽奖记录
     */
    @GetMapping("/getWhdChoujiangRecordPage")
    @ResponseBody
    @ApiOperation("分页查询抽奖记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "huodongID", value = "活动ID"),

            @ApiImplicitParam(name = "huodongName", value = "活动名称"),
            @ApiImplicitParam(name = "userName", value = "用户名"),
    })
    public AjaxJson getWhdChoujiangRecordPage(HttpServletRequest request,
                                              long size,
                                              long current,
                                              String huodongID,
                                              String huodongName,
                                              String userName,
                                              @RequestParam(required = false) int type
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper<WhdChoujiangRecordrVo> wrapper = new QueryWrapper<>();
        wrapper.eq("a.qiyeID", qiyeID);

        if (StringUtils.isNotBlank(huodongID)) {
            wrapper.eq("a.choujiangHuodongID", huodongID);
        }
        if (!ObjectUtils.isEmpty(huodongName)) {
            wrapper.like("b.choujiangHuodongName", huodongName);
        }
        if (!ObjectUtils.isEmpty(userName)) {
            wrapper.like("c.userName", userName);
        }
        if (type == 1) {
            //小程序
            wrapper.eq("d.type", 1);
        }
        Page<HashMap<String, Object>> page = whdChoujiangCjrecordService.getWhdChoujiangRecordPage(new Page(current, size), wrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 添加抽奖记录
     */
    @PostMapping("/addWhdChoujiangRecord")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @ApiOperation("添加抽奖记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "choujianghuodongid", value = "抽奖活动", required = true),
            @ApiImplicitParam(name = "choujiangwxuserid", value = "抽奖用户", required = true),
            @ApiImplicitParam(name = "jiangpingid", value = "抽奖奖品", required = true),
            @ApiImplicitParam(name = "jiangpingfafangstate", value = "奖品发放状态，0未放，1已发放", required = true),
            @ApiImplicitParam(name = "choujiangtime", value = "抽奖时间"),
            @ApiImplicitParam(name = "shuoming", value = "说明"),
            @ApiImplicitParam(name = "qiyeid", value = "企业ID"),
    })
    public AjaxJson addWhdChoujiangRecord(HttpServletRequest request, WhdChoujiangCjrecord whdChoujiangCjrecord) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        String msg = "";
        if (ObjectUtils.isEmpty(whdChoujiangCjrecord.getChoujianghuodongid())) {
            msg += "抽奖活动不可为空。";
        }
        if (ObjectUtils.isEmpty(whdChoujiangCjrecord.getChoujiangwxuserid())) {
            msg += "抽奖用户不可为空。";
        }
        if (ObjectUtils.isEmpty(whdChoujiangCjrecord.getJiangpingid())) {
            msg += "抽奖奖品不可为空。";
        }
        if (ObjectUtils.isEmpty(whdChoujiangCjrecord.getJiangpingfafangstate())) {
            msg += "奖品发放状态不可为空。";
        }
        if (!ObjectUtils.isEmpty(msg)) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg(msg);
            return ajaxJson;
        }
        ajaxJson.setSuccess(
                whdChoujiangCjrecord
                        .setChoujiangtime(new Date())
                        .setQiyeID(qiyeID)
                        .insert()
        );
        return ajaxJson;
    }

    /**
     * 根据ID更新抽奖记录
     */
    @PostMapping("/updateWhdChoujiangRecordByID")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @ApiOperation("根据ID更新抽奖记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true),
            @ApiImplicitParam(name = "choujianghuodongid", value = "抽奖活动"),
            @ApiImplicitParam(name = "choujiangwxuserid", value = "抽奖用户"),
            @ApiImplicitParam(name = "jiangpingid", value = "抽奖奖品"),
            @ApiImplicitParam(name = "jiangpingfafangstate", value = "奖品发放状态，0未放，1已发放"),
            @ApiImplicitParam(name = "shuoming", value = "说明"),
    })
    public AjaxJson updateWhdChoujiangRecordByID(WhdChoujiangCjrecord whdChoujiangCjrecord) {
        AjaxJson ajaxJson = new AjaxJson();
        String msg = "";
        if (ObjectUtils.isEmpty(whdChoujiangCjrecord.getId())) {
            msg += "粗错鸟。";
        }
        if (!ObjectUtils.isEmpty(msg)) {
            ajaxJson.setMsg(msg);
            return ajaxJson;
        }
        ajaxJson.setSuccess(
                whdChoujiangCjrecord
                        .setChoujiangtime(null)
                        .setQiyeID(null)
                        .updateById()
        );
        return ajaxJson;
    }

    /**
     * 根据id批量删除抽奖记录
     */
    @DeleteMapping("/deleteWhdChoujiangRecordByIDs/{ids}")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("根据id批量删除抽奖记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "抽奖记录ids", required = true),
    })
    public AjaxJson deleteWhdChoujiangRecordByIDs(@PathVariable List<String> ids) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(whdChoujiangCjrecordService.removeByIds(ids));
        return ajaxJson;
    }

    /**
     * @Description: getchoujiangInfo()方法作用:获取抽奖结果(抽奖)
     * @param:[request, huodongID]
     * @return:com.xwcloud.cloud.common.AjaxJson
     * @auter:yyl
     * @data:2021/5/25 14:19
     */
    @GetMapping("getchoujiangInfo")
    @ResponseBody
    @ApiOperation("获取抽奖结果")
    public AjaxJson getchoujiangInfo(HttpServletRequest request, String huodongID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        WhdChoujiangHuodong hd = whdChoujiangHuodongService.getById(huodongID);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(new Date());
        Date parse = null;
        try {
            parse = sdf.parse(today);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Integer isup = hd.getIsUp();//是否上架
        if (isup != 1) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("抽奖活动不是上架活动");
            return ajaxJson;
        }

        Date endtime =  Date.from(hd.getEndTime().atZone(ZoneId.systemDefault()).toInstant());
        Date starttime = Date.from(hd.getStartTime().atZone(ZoneId.systemDefault()).toInstant());
        if (parse.getTime() > endtime.getTime() || parse.getTime() < starttime.getTime()) {
            ajaxJson.setMsg("该抽奖活动未开始或已结束！！！");
            ajaxJson.setCode("N");
            return ajaxJson;
        }

        Integer cishu = hd.getCishu();
        if (hd.getChoujiangStyle() == 1) {
            //总共可以抽奖多少次
            List<WhdChoujiangCjrecord> cjInfo = whdChoujiangCjrecordService.list(
                    new QueryWrapper<WhdChoujiangCjrecord>()
                            .eq("choujianghuodongid", huodongID)
                            .eq("choujiangwxuserid", loginUser.getWscUserID())
                            .eq("qiyeID", loginUser.getQiyeID())
            );
            if (cjInfo.size() >= cishu.intValue()) {  //正常下都是==
                ajaxJson.setCode("N");
                ajaxJson.setMsg("抽奖次数已经用完啦！");
                return ajaxJson;
            }

        } else if (hd.getChoujiangStyle() == 2) {
            //每天可以抽多少次
            List<WhdChoujiangCjrecord> cjInfo = whdChoujiangCjrecordService.list(
                    new QueryWrapper<WhdChoujiangCjrecord>()
                            .eq("choujianghuodongid", huodongID)
                            .eq("choujiangwxuserid", loginUser.getWscUserID())
                            .eq("qiyeID", loginUser.getQiyeID())
                            .eq("choujiangtime", today)
            );
            if (cjInfo.size() >= cishu.intValue()) {  //正常下都是==
                ajaxJson.setCode("N");
                ajaxJson.setMsg("今天抽奖次数已经用完啦！");
                return ajaxJson;
            }
        }
        List<Double> orignalRates = new ArrayList<>();
        List<WhdChoujiangJiangping> CJlist = whdChoujiangJiangpingService.list(new QueryWrapper<WhdChoujiangJiangping>()
                .eq("choujiangHuodongID", huodongID)
                .eq("qiyeID", loginUser.getQiyeID())
                .orderByAsc("id")
        );

        for (WhdChoujiangJiangping item : CJlist) {
            if (item.getJiangpingtotalnum() <= 0) {
                //某个奖品没了 需要排除掉
            } else {
                Double gl = Double.valueOf(item.getZhongjianggailv());
                orignalRates.add(gl);
            }
        }
        int size = orignalRates.size();

        // 计算总概率，这样可以保证不一定总概率是1
        double sumRate = 0d;
        for (double rate : orignalRates) {
            sumRate += rate;
        }

        // 计算每个物品在总概率的基础下的概率情况
        List<Double> sortOrignalRates = new ArrayList<>(size);
        Double tempSumRate = 0d;
        for (double rate : orignalRates) {
            tempSumRate += rate;
            sortOrignalRates.add(tempSumRate / sumRate);
        }

        // 根据区块值来获取抽取到的物品索引
        double nextDouble = Math.random();
        sortOrignalRates.add(nextDouble);
        Collections.sort(sortOrignalRates);

        ajaxJson.setObj(sortOrignalRates.indexOf(nextDouble));
        int i = sortOrignalRates.indexOf(nextDouble);

        //添加抽奖记录
        WhdChoujiangCjrecord cj = new WhdChoujiangCjrecord();
        cj.setChoujianghuodongid(Long.valueOf(huodongID));
        cj.setChoujiangwxuserid(loginUser.getWscUserID());
        cj.setJiangpingid(CJlist.get(i).getId());
        cj.setJiangpingfafangstate(0); //未发放
        cj.setChoujiangtime(new Date());
        cj.setQiyeID(loginUser.getQiyeID());
        whdChoujiangCjrecordService.save(cj);

        return ajaxJson;
    }


    @ApiOperation("奖品发放")
    @PostMapping("fafangjiangpin")
    @ResponseBody
    public AjaxJson fafangjiangpin(HttpServletRequest request, String ids, String beizhu) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        String[] IDlist = ids.split(",");
        int okNum = 0;
        for (String item : IDlist) {
            WhdChoujiangCjrecord cj = whdChoujiangCjrecordService.getById(item);
            if (cj.getJiangpingfafangstate() == 1) {
                cj.setShuoming(beizhu);
            } else {
                cj.setJiangpingfafangstate(1);
                cj.setJiangpingFFTime(new Date());
                cj.setFFstaffID(loginUser.getStaffID());
                cj.setShuoming(beizhu);
                okNum += 1;
            }
            whdChoujiangCjrecordService.updateById(cj);
        }
        ajaxJson.setMsg("成功发放：" + okNum + "条奖品！");
        return ajaxJson;
    }
    //endregion

    /**
     * 查询抽奖活动详细信息
     * @param request
     * @param huodongID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getChoujiangHuodongDetail",method = RequestMethod.GET)
    public  AjaxJson getChoujiangHuodongDetail(HttpServletRequest request,long huodongID){
        AjaxJson ajaxJson=new AjaxJson();
        LoginUser loginUser=(LoginUser)request.getAttribute("loginUser");
        ajaxJson.setObj(iWhdChoujiangHuodongService.getById(huodongID));
        return ajaxJson;
    }

}
