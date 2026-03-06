package com.xwcloud.cloud.wsc.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.Form.plkcContentForm;
import com.xwcloud.cloud.model.Sso.LoginUser;

import com.xwcloud.cloud.model.entity.WhdH5Huodongfabu;
import com.xwcloud.cloud.model.entity.WhdH5HuodongfabuJigoujianjie;
import com.xwcloud.cloud.model.entity.WhdH5MbschoolJigoujianjie;
import com.xwcloud.cloud.model.entity.WhdH5Moban;
import com.xwcloud.cloud.wsc.Service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/zhaoxuexiu")
@Api(tags = "招学秀")
public class ZhaoxuexiuController {

    @Autowired
    IWhdH5MobanService iWhdH5MobanService;

    @Autowired
    IWscHuodongService wscHuodongService;

    @Autowired
    IWhdH5HuodongfabuService whdH5HuodonfabuService;

    @Autowired
    IWhdH5MbschoolJigoujianjieService whdH5MbschoolJigoujianjieService;

    @Autowired
    IWhdH5MbmusicService whdH5MbmusicService;

    @Autowired
    IWhdH5HuodongfabuJigoujianjieService whdH5HuodongfabuJigoujianjieService;

    /**
     * 分页查询招学秀模板信息
     *
     * @param size
     * @param current
     * @param mbTypeID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/GetAllMubanPages", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页数", example = "1", required = true),
    })
    public AjaxJson GetAllMubanPages(long size, long current, Integer mbTypeID) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<WhdH5Moban> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        if (mbTypeID != 0) {
            queryWrapper.eq("mbTypeID", mbTypeID);
        }
        ajaxJson.setObj(iWhdH5MobanService.page(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * 查询所有模板活动类型
     *
     * @return
     */
    @RequestMapping(value = "/GetAllHuodongTypeList", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson GetAllHuodongTypeList() {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(wscHuodongService.GetAllWscHuodongList());
        return ajaxJson;
    }

    /**
     * 分页查询所有已经发布的模板信息
     *
     * @param request
     * @param size
     * @param current
     * @return
     */
    @RequestMapping(value = "/GetAllMyMobanFabuPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true),
            @ApiImplicitParam(name = "current", value = "当前页数", example = "1", required = true)
    })
    public AjaxJson GetAllMyMobanFabuPages(HttpServletRequest request, long size, long current) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(whdH5HuodonfabuService.GetMyzhaoxuexiuMobanPages(new Page<>(current, size), loginUser.getQiyeID()));
        return ajaxJson;
    }

    /**
     * 查询当前登录用户对应的机构信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetjigouInfoByqiyeID", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson GetjigouInfoByqiyeID(HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", Long.valueOf(loginUser.getQiyeID()));
        ajaxJson.setObj(whdH5MbschoolJigoujianjieService.list(queryWrapper));
        return ajaxJson;
    }

    /**
     * 保存机构信息
     * @param request
     * @param form
     * @return
     */
    @RequestMapping(value = "/SavejigouInfo", method = RequestMethod.POST)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "数据ID"),
            @ApiImplicitParam(name = "mbLianxifangshi", value = "模板联系方式"),
            @ApiImplicitParam(name = "mbLianxifangshiUrl", value = "资讯方式图片路径"),
            @ApiImplicitParam(name = "mbSchoolName", value = "模板机构名称"),
            @ApiImplicitParam(name = "mbschoolTel", value = "模板机构联系电话")
    })
    public AjaxJson SavejigouInfo(HttpServletRequest request, @RequestBody WhdH5MbschoolJigoujianjie form) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        WhdH5MbschoolJigoujianjie whdH5MbschoolJigoujianjie = whdH5MbschoolJigoujianjieService.getById(form.getId());
        whdH5MbschoolJigoujianjie.setMbLianxifangshi(form.getMbLianxifangshi());
        whdH5MbschoolJigoujianjie.setMbLianxifangshiUrl(form.getMbLianxifangshiUrl());
        whdH5MbschoolJigoujianjie.setMbSchoolName(form.getMbSchoolName());
        whdH5MbschoolJigoujianjie.setMbschoolTel(form.getMbschoolTel());
        whdH5MbschoolJigoujianjie.setJigoujianjie(form.getJigoujianjie());
        ajaxJson.setSuccess(whdH5MbschoolJigoujianjieService.updateById(whdH5MbschoolJigoujianjie));
        return ajaxJson;
    }

    /**
     * 发布活动
     *
     * @param hid
     * @param request
     * @return
     */
    @RequestMapping(value = "/setFBhuodong", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "hid", value = "保存活动ID")
    })
    public AjaxJson setFBhuodong(long hid, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        WhdH5Huodongfabu whdH5Huodongfabu = whdH5HuodonfabuService.getById(hid);
        whdH5Huodongfabu.setFabuTime(new Date());
        whdH5Huodongfabu.setIsfabu(true);
        ajaxJson.setSuccess(whdH5HuodonfabuService.updateById(whdH5Huodongfabu));
        return ajaxJson;
    }

    /**
     * 查询活动模板信息
     *
     * @param request
     * @param huodongID
     * @return
     */
    @RequestMapping(value = "/GetH5MobanData", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "huodongID", value = "活动ID")
    })
    public AjaxJson GetH5MobanData(HttpServletRequest request, long huodongID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        WhdH5Huodongfabu whdH5Huodongfabu = whdH5HuodonfabuService.getById(huodongID);
        ajaxJson.setObj(whdH5Huodongfabu);
        return ajaxJson;
    }

    /**
     * 根据模板ID查询模板信息
     *
     * @param request
     * @param mobanID
     * @return
     */
    @RequestMapping(value = "/GetMobanInfoDetail", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobanID", value = "模板ID")
    })
    public AjaxJson GetMobanInfoDetail(HttpServletRequest request, long mobanID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setObj(iWhdH5MobanService.getById(mobanID));
        return ajaxJson;
    }

    /**
     * 查询机构简介信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetJigoujianjieInfo", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson GetJigoujianjieInfo(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        ajaxJson.setObj(whdH5MbschoolJigoujianjieService.list(queryWrapper));
        return ajaxJson;
    }

    /**
     * 查询模板音乐信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getMobanMusicList", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getMobanMusicList(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setObj(whdH5MbmusicService.list());
        return ajaxJson;
    }

    @RequestMapping(value = "/SavezhaoxuexiuHuodongInfo", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存招学秀模板信息")
    public AjaxJson SavezhaoxuexiuHuodongInfo(HttpServletRequest request, String huodongImage, Integer maxStuNum, boolean haveAge, boolean haveBirthday,
                                              boolean haveGrade, boolean haveSchool, boolean haveStuSex, boolean haveYxkecheng, String huodongEndDateTime,String huodongStartDateTime,
                                              String huodongShuoMing, String huodongTitle, String jigouName, String jigouTel, String zixunEwm, Integer musicID
            , long mobanID, long mbTypeID, String jianjieNeirong) throws ParseException {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        WhdH5Huodongfabu whdH5Huodongfabu = new WhdH5Huodongfabu();
        whdH5Huodongfabu.setIsfabu(false)
                .setHuodongImage(huodongImage)
                .setAddTime(new Date())
                .setAddUser(loginUser.getStaffID())
                .setHaveAge(haveAge)
                .setHaveBirthday(haveBirthday)
                .setHaveGrade(haveGrade)
                .setHaveSchool(haveSchool)
                .setHaveStuSex(haveStuSex)
                .setHaveYxkecheng(haveYxkecheng)
                .setHuodongEndDateTime(ft.parse(huodongEndDateTime))
                .setHuodongStartDateTime(ft.parse(huodongStartDateTime))
                .setHuodongShuoMing(huodongShuoMing)
                .setHuodongTitle(huodongTitle)
                .setJigouName(jigouName)
                .setJigouTel(jigouTel)
                .setQiyeID(loginUser.getQiyeID())
                .setZixunEwm(zixunEwm)
                .setMusicID(musicID)
                .setMaxStuNum(maxStuNum)
                .setMbTypeID(mbTypeID)
                .setLookNum(0)
                .setBaomingStuNum(0)
                .setMobanID(mobanID);
        whdH5HuodonfabuService.save(whdH5Huodongfabu);
        WhdH5HuodongfabuJigoujianjie whdH5HuodongfabuJigoujianjie = new WhdH5HuodongfabuJigoujianjie().setWhdH5HuodongfabuId(whdH5Huodongfabu.getId())
                .setJianjieNeirong(jianjieNeirong).setJianjieType(0).setQiyeID(loginUser.getQiyeID());
        whdH5HuodongfabuJigoujianjieService.save(whdH5HuodongfabuJigoujianjie);
        ajaxJson.setSuccess(true);
        return ajaxJson;
    }
}
