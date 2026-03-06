package com.xwcloud.cloud.wsc.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.Vo.dongtaiVO;
import com.xwcloud.cloud.model.Vo.fensiguanzhuVO;
import com.xwcloud.cloud.model.Vo.messageVO;
import com.xwcloud.cloud.model.Vo.yongjinVO;
import com.xwcloud.cloud.model.entity.*;
import com.xwcloud.cloud.wsc.Service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-26
 */
@Controller
@RequestMapping("/samllprogram/wode")
@Api(tags = "微信小程序端个人中心")
public class WodeController {
    @Autowired
    IWscUserguanzhuService iWscUserguanzhuService;

    @Autowired
    IWscDongtaiinfoService iWscDongtaiinfoService;

    @Autowired
    IDongtaiDianzangService iDongtaiDianzangService;

    @Autowired
    IDongtaiPinglunService iDongtaiPinglunService;

    @Autowired
    IWscUserService iWscUserService;

    @Autowired
    IPxtuisongtableService iPxtuisongtableService;

    @Autowired
    IMinganwordsService iMinganwordsService;

    @Autowired
    IWscTuikeBuyService iWscTuikeBuyService;

    @Autowired
    IWscTuikelevelService iWscTuikelevelService;

    @Autowired
    IWscTuikeYongjinService iWscTuikeYongjinService;


    @ResponseBody
    @ApiOperation(value = "分页查询粉丝信息")
    @RequestMapping(value = "/GetAllFensiPages", method = RequestMethod.GET)
    public AjaxJson GetAllFensiPages(HttpServletRequest request, long size, long current) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<fensiguanzhuVO> page = new Page(current, size);
        page = (Page<fensiguanzhuVO>) iWscUserguanzhuService.GetAllfensiPages(page, loginUser.getWscUserID());
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @ResponseBody
    @ApiOperation(value = "分页查询关注的信息")
    @RequestMapping(value = "/GetAllGuanzhuPages", method = RequestMethod.GET)
    public AjaxJson GetAllGuanzhuPages(HttpServletRequest request, long size, long current) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<fensiguanzhuVO> page = new Page(current, size);
        page = (Page<fensiguanzhuVO>) iWscUserguanzhuService.GetAllGuanzhuPages(page, loginUser.getWscUserID());
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @ResponseBody
    @ApiOperation(value = "分页查询我的动态信息")
    @RequestMapping(value = "/GetAllMyDongtai", method = RequestMethod.GET)
    public AjaxJson GetAllMyDongtai(HttpServletRequest request, long size, long current) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<dongtaiVO> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("u.qiyeID", loginUser.getQiyeID());
        queryWrapper.orderByAsc("dongtai.iszhiding");
        page = (Page<dongtaiVO>) iWscDongtaiinfoService.GetMyDongtaiInfo(page, loginUser.getWscUserID(), queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @ResponseBody
    @ApiOperation(value = "操作动态置顶取消置顶")
    @RequestMapping(value = "/caozuoDongtaiZhiding", method = RequestMethod.GET)
    public AjaxJson caozuoDongtaiZhiding(HttpServletRequest request, long dongtaiID) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        WscDongtaiinfo wscDongtaiinfo = iWscDongtaiinfoService.getById(dongtaiID);
        if (wscDongtaiinfo.getIszhiding() == 1) {
            wscDongtaiinfo.setIszhiding(2);
        } else {
            wscDongtaiinfo.setIszhiding(1);
        }
        ajaxJson.setSuccess(iWscDongtaiinfoService.updateById(wscDongtaiinfo));
        return ajaxJson;
    }

    @ResponseBody
    @ApiOperation(value = "删除动态信息")
    @RequestMapping(value = "/DeleteDongtaiInfo", method = RequestMethod.GET)
    public AjaxJson DeleteDongtaiInfo(HttpServletRequest request, long dongtaiID) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("dongtaiID", dongtaiID);
            iDongtaiDianzangService.remove(queryWrapper);
            iDongtaiPinglunService.remove(queryWrapper);
            iWscDongtaiinfoService.removeById(dongtaiID);
            ajaxJson.setSuccess(true);
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
        }
        return ajaxJson;
    }

    /**
     * 保存发布的动态信息
     *
     * @param request
     * @param content
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "保存动态信息")
    @RequestMapping(value = "/saveDongtaiInfo", method = RequestMethod.POST)
    public AjaxJson saveDongtaiInfo(HttpServletRequest request, String content) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("isYouxiao", 1);
        List<Minganwords> minganwordsList = iMinganwordsService.list(queryWrapper);

        for (Minganwords item : minganwordsList) {
            content = content.replace(item.getWord(), "****");
        }

        WscDongtaiinfo wscDongtaiinfo = new WscDongtaiinfo();
        wscDongtaiinfo.setIszhiding(2);
        wscDongtaiinfo.setYueduTimes(0);
        wscDongtaiinfo.setDongtaiTitle("添加动态");
        wscDongtaiinfo.setDongtaiContent(content);
        wscDongtaiinfo.setAddtime(new Date());
        wscDongtaiinfo.setWscuserID(loginUser.getWscUserID());
        wscDongtaiinfo.setQiyeID(Long.valueOf(loginUser.getQiyeID()));
        wscDongtaiinfo.setIsShow(0);
        ajaxJson.setSuccess(iWscDongtaiinfoService.save(wscDongtaiinfo));
        return ajaxJson;
    }


    /**
     * 查询当前登录用户的分销信息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "查询当前登录用户的分销信息")
    @RequestMapping(value = "/getLoginuserFenxiaoInfo", method = RequestMethod.GET)
    public AjaxJson getLoginuserFenxiaoInfo(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setObj(iWscUserService.getloginuserFenxiaoInfo(loginUser.getWscUserID()));
        return ajaxJson;
    }

    /**
     * 分页查询直属或团队用户信息
     *
     * @param request
     * @param size
     * @param current
     * @param type
     * @return
     */

    @ResponseBody
    @ApiOperation(value = "分页查询直属或团队用户信息")
    @RequestMapping(value = "/GetTuiguangYonghu", method = RequestMethod.GET)
    public AjaxJson GetTuiguangYonghu(HttpServletRequest request, long size, long current, int type) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");

        List<WscTuikeBuy> tkbuy = iWscTuikeBuyService.list(new QueryWrapper<WscTuikeBuy>()
                .eq("wsc_user_id", loginUser.getWscUserID())
                .eq("qiyeID", loginUser.getQiyeID())
        );


        if (tkbuy.size() < 0) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("成为推客后即可参与推客分销功能");
            return ajaxJson;
        }

        Page<fensiguanzhuVO> page = new Page(current, size);
        if (type == 1) {
            page = (Page<fensiguanzhuVO>) iWscUserService.GetzhishuUserPages(page, loginUser.getWscUserID());
            ajaxJson.setObj(page);
        } else {
            page = (Page<fensiguanzhuVO>) iWscUserService.GetjianjietuijianPages(page, loginUser.getWscUserID());
            ajaxJson.setObj(page);
        }
        return ajaxJson;
    }

    @ApiOperation("获取全部推客套餐")
    @ResponseBody
    @GetMapping("getAllbuytuikelevel")
    public AjaxJson getAllbuytuikelevel(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        List<WscTuikelevel> buylist = iWscTuikelevelService.list(new QueryWrapper<WscTuikelevel>()
                .eq("isShow", 1)
                .eq("qiyeID", loginUser.getQiyeID())
        );

        ajaxJson.setObj(buylist);
        return ajaxJson;
    }

//    @PostMapping("buytuke")
//    @ResponseBody
//    @ApiOperation("小程序升级为推客")
//    public AjaxJson buytuke(String wscUserID, String realName,
//                            String phone,
//                            String buyTuikeLevelID,
//                            String shuoming,
//                            HttpServletRequest request) {
//        AjaxJson ajaxJson = new AjaxJson();
//        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
//        WscUser wuser = iWscUserService.getById(wscUserID);
//        WscTuikelevel butlv = iWscTuikelevelService.getById(buyTuikeLevelID);
//        WscTuikeBuy one = new WscTuikeBuy();
//        one.setWscUserId(wuser.getId())
//                .setRealName(realName)
//                .setPhone(phone)
//                .setOldTuikeLevelID(0L)
//                .setBuyTuikeLevelID(butlv.getId())
//                .setPaymoney(butlv.getTiaojianMoney())
//                .setBuyTime(new Date())
//                .setShuoming(shuoming).setQiyeID(loginUser.getQiyeID());
//        iWscTuikeBuyService.save(one);
//
//        wuser.setTuikeLevelID(Long.valueOf(buyTuikeLevelID));
//        iWscUserService.updateById(wuser);
//        return ajaxJson;
//    }


    /**
     * 分页查询当前用户分销订单信息
     *
     * @param request
     * @param size
     * @param current
     * @param type
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "查询当前用户的分销佣金信息")
    @RequestMapping(value = "/getfenxiaoyongjinInfoPages", method = RequestMethod.GET)
    public AjaxJson getfenxiaoyongjinInfoPages(HttpServletRequest request, long size, long current, int type) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<yongjinVO> page = new Page(current, size);
        if (type == 1) {
            page = (Page<yongjinVO>) iWscUserService.GetyiwanchengYongjinInfo(page, loginUser.getWscUserID());
            ajaxJson.setObj(page);
        } else {
            page = (Page<yongjinVO>) iWscUserService.GetWeiwanchengYongjinInfoPages(page, loginUser.getWscUserID());
            ajaxJson.setObj(page);
        }
        return ajaxJson;
    }

    //region 消息中心

    /**
     * 分页查询当前登录用户的消息信息
     *
     * @param request
     * @param size
     * @param current
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "分页查询当前用户收到的消息信息")
    @RequestMapping(value = "/GetAllMessagePages", method = RequestMethod.GET)
    public AjaxJson GetAllMessagePages(HttpServletRequest request, long size, long current) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<messageVO> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("a.role", loginUser.getLoginType());
        if (loginUser.getLoginType() == 1) {
            queryWrapper.eq("a.stuID", loginUser.getStaffID());
            ajaxJson.setObj(iPxtuisongtableService.GetAllMessagePages(page, queryWrapper));
        } else {
            queryWrapper.eq("a.stuID", loginUser.getStaffID());
            ajaxJson.setObj(iPxtuisongtableService.GetAllMessageStaffPages(page, queryWrapper));
        }
        return ajaxJson;
    }
    //endregion

    //region 加载个人信息
    @ResponseBody
    @ApiOperation(value = "加载登录用户信息")
    @RequestMapping(value = "/GetUserInfo", method = RequestMethod.GET)
    public AjaxJson GetUserInfo(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setObj(iWscUserService.getById(loginUser.getWscUserID()));
        return ajaxJson;
    }

    @ResponseBody
    @ApiOperation(value = "修改头像")
    @RequestMapping(value = "/editUserInfoImg", method = RequestMethod.POST)
    public AjaxJson editUserInfoImg(HttpServletRequest request, String imgUrl) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        WscUser one = iWscUserService.getById(loginUser.getWscUserID());
        one.setHeadImage(imgUrl);
        ajaxJson.setSuccess(iWscUserService.updateById(one));
        return ajaxJson;
    }

    //endregion

}
