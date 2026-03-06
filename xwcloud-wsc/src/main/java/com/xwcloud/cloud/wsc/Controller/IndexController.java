package com.xwcloud.cloud.wsc.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.wsc.Service.*;
import com.xwcloud.cloud.common.AjaxJson;

import com.xwcloud.cloud.model.OA.OaKehu;
import com.xwcloud.cloud.model.Vo.yuekeTeacherVO;
import com.xwcloud.cloud.model.entity.*;
import com.xwcloud.cloud.model.Sso.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/samllprogram/index")
@Api(tags = "微信小程序端首页信息")
public class IndexController {
    @Autowired
    IOaKehuService iOaKehuService;

    @Autowired
    IPxyueketeacherfabutableService iPxyueketeacherfabutableService;

    @Autowired
    IPxyueketeacherfabupriceService iPxyueketeacherfabupriceService;

    @Autowired
    IPxyueketeacherfabustutableService iPxyueketeacherfabustutableService;

    @Autowired
    IPxstutableService iPxstutableService;

    @Autowired
    IBirthdayzhufuService iBirthdayzhufuService;

    @Autowired
    IWscDongtaiinfoService iWscDongtaiinfoService;

    @Autowired
    IDongtaiDianzangService iDongtaiDianzangService;

    @Autowired
    IDongtaiPinglunService iDongtaiPinglunService;

    @Autowired
    IWscUserguanzhuService iWscUserguanzhuService;

    @Autowired
    IWhdH5MbschoolJigoujianjieService iWhdH5MbschoolJigoujianjieService;

    @Autowired
    IPxkechengtableService iPxkechengtableService;

    /**
     * 查询机构信息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/GetJigouInfo", method = RequestMethod.GET)
    public AjaxJson GetJigouInfo(HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper<OaKehu> oaKehuQueryWrapper = new QueryWrapper<>();
        oaKehuQueryWrapper.eq("id", loginUser.getQiyeID());

        ajaxJson.setObj(iOaKehuService.getOne(oaKehuQueryWrapper));
        return ajaxJson;
    }

    //region 约课信息

    /**
     * 翻页查询老师发布的约课信息
     *
     * @param request
     * @param current
     * @param size
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/GetAllJIaoshifabuyuekePages", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有教师发布的约课信息")
    public AjaxJson GetAllJIaoshifabuyuekePages(HttpServletRequest request, long current, long size) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<yuekeTeacherVO> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.ne("campus.isOpen", 2);
        queryWrapper.eq("teacherfabu.qiyeID", loginUser.getQiyeID());
        queryWrapper.ne("teacherfabu.yuekeState", 3);
        ajaxJson.setObj(iPxyueketeacherfabutableService.GetYuekTeacherFabuPages(page, queryWrapper));
        return ajaxJson;
    }

    /**
     * 查询约课的详细信息
     *
     * @param request
     * @param yuekeID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/GetYuekeInfoDetails", method = RequestMethod.GET)
    @ApiOperation(value = "查询教师发布的约课详细信息 ")
    public AjaxJson GetYuekeInfoDetails(HttpServletRequest request, long yuekeID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.ne("campus.isOpen", 2);
        queryWrapper.eq("teacherfabu.qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("teacherfabu.id", yuekeID);
        ajaxJson.setObj(iPxyueketeacherfabutableService.GetYuekDetailInfo(queryWrapper));
        return ajaxJson;
    }

    @ResponseBody
    @RequestMapping(value = "/GetYuekePriceList", method = RequestMethod.GET)
    @ApiOperation(value = "查询约课价格信息")
    public AjaxJson GetYuekePriceList(HttpServletRequest request, long yuekeID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setObj(iPxyueketeacherfabupriceService.GetAllYuekepriceByyuekeTeacherId(yuekeID, loginUser.getQiyeID()));
        return ajaxJson;
    }

    @ResponseBody
    @RequestMapping(value = "/getJoinYuekeStuInfo", method = RequestMethod.GET)
    @ApiOperation(value = "查询加入约课的学生信息")
    public AjaxJson getJoinYuekeStuInfo(HttpServletRequest request, long yuekeID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setObj(iPxyueketeacherfabustutableService.getJoinyuekeStuInfos(yuekeID, loginUser.getQiyeID()));
        return ajaxJson;
    }

    @ResponseBody
    @ApiOperation(value = "添加约课信息浏览次数")
    @RequestMapping(value = "/setyuekeliulangTimeAdd", method = RequestMethod.GET)
    public AjaxJson setyuekeliulangTimeAdd(HttpServletRequest request, long yuekeID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Pxyueketeacherfabutable pxyueketeacherfabutable = iPxyueketeacherfabutableService.getById(yuekeID);
        pxyueketeacherfabutable.setLiulanTimes(pxyueketeacherfabutable.getLiulanTimes() + 1);
        ajaxJson.setSuccess(iPxyueketeacherfabutableService.updateById(pxyueketeacherfabutable));
        return ajaxJson;
    }
    //endregion

    //region 生日助手
    @ResponseBody
    @ApiOperation(value = "学生生日助手")
    @RequestMapping(value = "/getAllStushengriInfo", method = RequestMethod.GET)
    public AjaxJson getAllStushengriInfo(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setObj(iPxstutableService.GetAllteacherAndStuBirthday(Long.valueOf(loginUser.getQiyeID())));
        return ajaxJson;
    }

    @ResponseBody
    @ApiOperation(value = "查询生日点赞信息")
    @RequestMapping(value = "/GetALldianzangInfos", method = RequestMethod.GET)
    public AjaxJson GetALldianzangInfos(HttpServletRequest request, long beidianzanUserID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setObj(iBirthdayzhufuService.GetbirthDianzanInfo(beidianzanUserID));
        return ajaxJson;
    }

    @ResponseBody
    @ApiOperation(value = "查询所以祝福信息")
    @RequestMapping(value = "/GetAllzhufuInfos", method = RequestMethod.GET)
    public AjaxJson GetAllzhufuInfos(HttpServletRequest request, long zhufuUserID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setObj(iBirthdayzhufuService.GetAllbirthdayZhufu(zhufuUserID));
        return ajaxJson;
    }

    @ResponseBody
    @ApiOperation(value = "查询学生生日主页的学生信息")
    @RequestMapping(value = "/GetStuBirthStuInfo", method = RequestMethod.GET)
    public AjaxJson GetStuBirthStuInfo(HttpServletRequest request, long stuID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setObj(iBirthdayzhufuService.GetshengriStuInfo(stuID));
        return ajaxJson;
    }

    @ResponseBody
    @ApiOperation(value = "查询动态分享信息")
    @RequestMapping(value = "/GetDongtaiInfo", method = RequestMethod.GET)
    public AjaxJson GetDongtaiInfo(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setObj(iWscDongtaiinfoService.GetIndexDongtaifenxiang(loginUser.getWscUserID()));
        return ajaxJson;
    }

    @ResponseBody
    @ApiOperation(value = "查询动态详情")
    @RequestMapping(value = "/GetDongtaiInfoDetail", method = RequestMethod.GET)
    public AjaxJson GetDongtaiInfoDetail(HttpServletRequest request, long dongtaiID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setObj(iWscDongtaiinfoService.GetDongtaiInfo(dongtaiID, loginUser.getWscUserID()));
        return ajaxJson;
    }

    @ResponseBody
    @ApiOperation(value = "加载动态点赞信息")
    @RequestMapping(value = "/LoadDongtaiDianzangInfo", method = RequestMethod.GET)
    public AjaxJson LoadDongtaiDianzangInfo(HttpServletRequest request, long dongtaiID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setObj(iDongtaiDianzangService.GetAllDongtaiDianzangInfo(dongtaiID));
        return ajaxJson;
    }

    @ResponseBody
    @ApiOperation(value = "保存动态点赞信息")
    @RequestMapping(value = "/saveDianzangAdd", method = RequestMethod.GET)
    public AjaxJson saveDianzangAdd(HttpServletRequest request, long dongtaiID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("dongtaiID", dongtaiID);
        queryWrapper.eq("dianzanUserID", loginUser.getWscUserID());
        List<DongtaiDianzang> dongtaiDianzangs = iDongtaiDianzangService.list(queryWrapper);
        if (dongtaiDianzangs.size() > 0) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("你已经点赞过该动态");
        } else {
            DongtaiDianzang dongtaiDianzang = new DongtaiDianzang();
            dongtaiDianzang.setDongtaiID(dongtaiID);
            dongtaiDianzang.setDianzangDatetime(new Date());
            dongtaiDianzang.setDianzanUserID(loginUser.getWscUserID());
            ajaxJson.setSuccess(iDongtaiDianzangService.save(dongtaiDianzang));
            ajaxJson.setMsg("点赞成功");
        }
        return ajaxJson;
    }

    @ResponseBody
    @ApiOperation(value = "加载动态的评论信息")
    @RequestMapping(value = "/LoadDongtaiPinglunInfo", method = RequestMethod.GET)
    public AjaxJson LoadDongtaiPinglunInfo(HttpServletRequest request, long dongtaiID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setObj(iDongtaiPinglunService.GetDongtaiPinglunInfos(dongtaiID));
        return ajaxJson;
    }

    /**
     * 保存关注信息
     *
     * @param request
     * @param userID
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "保存用户关注信息")
    @RequestMapping(value = "/SetUserGuanzhuInfo", method = RequestMethod.GET)
    public AjaxJson SetUserGuanzhuInfo(HttpServletRequest request, long userID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        WscUserguanzhu wscUserguanzhu = new WscUserguanzhu();
        wscUserguanzhu.setGuanzhuDatetine(new Date());
        wscUserguanzhu.setWscuserID(loginUser.getWscUserID());
        wscUserguanzhu.setBeiguanzhuUserID(userID);
        ajaxJson.setSuccess(iWscUserguanzhuService.save(wscUserguanzhu));
        return ajaxJson;
    }

    @ResponseBody
    @ApiOperation(value = "取消关注")
    @RequestMapping(value = "/SetCancelGuanzhuInfo", method = RequestMethod.GET)
    public AjaxJson SetCancelGuanzhuInfo(HttpServletRequest request, long userID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("beiguanzhuUserID", userID);
        queryWrapper.eq("wscuserID", Long.valueOf(loginUser.getWscUserID()));
        ajaxJson.setSuccess(iWscUserguanzhuService.remove(queryWrapper));
        return ajaxJson;
    }

    @ResponseBody
    @ApiOperation(value = "保存评论信息")
    @RequestMapping(value = "/savepinglunInfo", method = RequestMethod.POST)
    public AjaxJson savepinglunInfo(HttpServletRequest request, long dongtaiID, String plcontent) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        DongtaiPinglun dongtaiPinglun = new DongtaiPinglun();
        dongtaiPinglun.setDongtaiID(dongtaiID);
        dongtaiPinglun.setPinglunDatetime(new Date());
        dongtaiPinglun.setPluserID(loginUser.getWscUserID());
        dongtaiPinglun.setPlcontent(plcontent);
        ajaxJson.setSuccess(iDongtaiPinglunService.save(dongtaiPinglun));
        return ajaxJson;
    }

    @ResponseBody
    @ApiOperation(value = "保存阅读次数信息")
    @RequestMapping(value = "/saveyueducishuInfo", method = RequestMethod.GET)
    public AjaxJson saveyueducishuInfo(HttpServletRequest request, long dongtaiID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        WscDongtaiinfo wscDongtaiinfo = iWscDongtaiinfoService.getById(dongtaiID);
        wscDongtaiinfo.setYueduTimes(wscDongtaiinfo.getYueduTimes() + 1);
        ajaxJson.setSuccess(iWscDongtaiinfoService.updateById(wscDongtaiinfo));
        return ajaxJson;
    }

    /**
     * 查询机构简介信息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "查询机构简介信息")
    @RequestMapping(value = "/GetJigoujianjieInfo", method = RequestMethod.GET)
    public AjaxJson GetJigoujianjieInfo(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        ajaxJson.setObj(iWhdH5MbschoolJigoujianjieService.getOne(queryWrapper));
        return ajaxJson;
    }
    //endregion
}
