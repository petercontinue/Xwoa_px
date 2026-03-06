package com.xwcloud.cloud.oa.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.OA.OaDaijinquan;
import com.xwcloud.cloud.model.OA.OaKehu;
import com.xwcloud.cloud.model.OA.OaLiushuiYewu;
import com.xwcloud.cloud.model.OA.OaLog;
import com.xwcloud.cloud.model.OA.Vo.DaijinquanVo;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.oa.service.IOaDaijinquanService;
import com.xwcloud.cloud.oa.service.IOaKehuService;
import com.xwcloud.cloud.oa.service.IOaLiushuiYewuService;
import com.xwcloud.cloud.oa.service.IOaLogService;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-02
 */
@Controller
@RequestMapping("/oaDaijinquan")
public class OaDaijinquanController {

    @Autowired
    private IOaDaijinquanService iOaDaijinquanService;

    @Autowired
    private IOaLiushuiYewuService iOaLiushuiYewuService;

    @Autowired
    private IOaLogService iOaLogService;

    @Autowired
    private IOaKehuService iOaKehuService;

    /**
     * 添加获得代金券信息
     *
     * @param oaDaijinquan
     * @return
     */
    @RequestMapping(value = "/addDaijinquan", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addDaijinquan(HttpServletRequest request,@RequestBody OaDaijinquan oaDaijinquan) {

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");

        AjaxJson ajaxJson = new AjaxJson();
        if (oaDaijinquan.getAddTime() == null) {
            oaDaijinquan.setAddTime(new Date());
        }
        oaDaijinquan.setUseDjq(BigDecimal.ZERO);
        OaKehu oaKehu = iOaKehuService.getById(oaDaijinquan.getQiyeID());
        boolean save = iOaDaijinquanService.save(oaDaijinquan);
        if (oaDaijinquan.getGetDjq().compareTo(BigDecimal.ZERO) > 0) {
            oaKehu.setDjqRemain(oaKehu.getDjqRemain().add(oaDaijinquan.getGetDjq()));
        }
//        if (oaDaijinquan.getUseDjq().compareTo(BigDecimal.ZERO) > 0 && oaKehu.getDjqRemain().compareTo(oaDaijinquan.getUseDjq()) < 0) {
//            ajaxJson.setMsg("代金券余额不足");
//            ajaxJson.setCode("N");
//            ajaxJson.setSuccess(false);
//            return ajaxJson;
//        }
//        if (oaDaijinquan.getUseDjq().compareTo(BigDecimal.ZERO) > 0 && oaKehu.getDjqRemain().compareTo(oaDaijinquan.getUseDjq()) > 0) {
//            oaKehu.setDjqRemain(oaKehu.getDjqRemain().subtract(oaDaijinquan.getUseDjq()));
//        }
        iOaKehuService.updateById(oaKehu);
        if (save) {
            //往业务流水中插入一条记录
            OaLiushuiYewu oaLiushuiYewu = new OaLiushuiYewu();
            oaLiushuiYewu.setQiyeID(oaDaijinquan.getQiyeID());
            //流水类别：1培训流水，2不续费流水，3代金券流水，4下次付款时间流水，5加校区流水，6停用服用流水
            oaLiushuiYewu.setLiushuiType(3);
            oaLiushuiYewu.setLiushuishuoming(oaDaijinquan.getShuoming());

            Long staffID = loginUser.getStaffID();
            oaLiushuiYewu.setAddUser(staffID);
            oaLiushuiYewu.setAddTime(new Date());
            iOaLiushuiYewuService.save(oaLiushuiYewu);
            ajaxJson.setMsg("代金券添加成功");
        } else {
            ajaxJson.setMsg("代金券添加失败");
            ajaxJson.setCode("N");
            ajaxJson.setSuccess(false);
        }
        OaLog oaLog = new OaLog();
        oaLog.setAddTime(new Date());
        oaLog.setFuncName("代金券添加");
        oaLog.setLogType(1);
        oaLog.setSystemContent("客户添加代金券,添加的代金券金额为:\'" + oaKehu.getDjqRemain() + "\',获得代金券的qiyeID为:\'" + oaDaijinquan.getQiyeID() + "\'");
        iOaLogService.save(oaLog);
        return ajaxJson;
    }

    /**
     * 修改代金券
     *
     * @param oaDaijinquan
     * @return
     */
    @RequestMapping(value = "/editDaijinquan", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson editDaijinquan(HttpServletRequest request,@RequestBody OaDaijinquan oaDaijinquan) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long staffID = loginUser.getStaffID();

        AjaxJson ajaxJson = new AjaxJson();
        boolean update = iOaDaijinquanService.updateById(oaDaijinquan);
        if (update) {
            //往业务流水中插入一条记录
            OaLiushuiYewu oaLiushuiYewu = new OaLiushuiYewu();
            oaLiushuiYewu.setQiyeID(oaDaijinquan.getQiyeID());
            //流水类别：1培训流水，2不续费流水，3代金券流水，4下次付款时间流水，5加校区流水，6停用服用流水
            oaLiushuiYewu.setLiushuiType(3);
            oaLiushuiYewu.setLiushuishuoming(oaDaijinquan.getShuoming());

            oaLiushuiYewu.setAddUser(staffID);
            oaLiushuiYewu.setAddTime(new Date());
            iOaLiushuiYewuService.save(oaLiushuiYewu);
            ajaxJson.setMsg("代金券信息修改成功");
        } else {
            ajaxJson.setMsg("代金券信息修改失败");
            ajaxJson.setCode("N");
            ajaxJson.setSuccess(false);
        }
        OaLog oaLog = new OaLog();
        oaLog.setAddTime(new Date());
        oaLog.setFuncName("代金券修改");
        oaLog.setLogType(1);
        oaLog.setSystemContent("修改客户代金券,修改后的代金券金额为:\'" + oaDaijinquan.getGetDjq() + "\',修改代金券的qiyeID为:\'" + oaDaijinquan.getQiyeID() + "\'");
        iOaLogService.save(oaLog);
        return ajaxJson;
    }

    /**
     * 根据id批量删除代金券
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delDaijinquan", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delDaijinquan(HttpServletRequest request,String ids) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long staffID = loginUser.getStaffID();

        AjaxJson ajaxJson = new AjaxJson();
        String[] idsArr = ids.split(",");
        List<String> idsList = Arrays.asList(idsArr);
        Collection<OaDaijinquan> oaDaijinquans = iOaDaijinquanService.listByIds(idsList);
        for (OaDaijinquan oaDaijinquan : oaDaijinquans) {
            //往业务流水中插入一条记录
            OaLiushuiYewu oaLiushuiYewu = new OaLiushuiYewu();
            oaLiushuiYewu.setQiyeID(oaDaijinquan.getQiyeID());
            //流水类别：1培训流水，2不续费流水，3代金券流水，4下次付款时间流水，5加校区流水，6停用服用流水
            oaLiushuiYewu.setLiushuiType(3);
            oaLiushuiYewu.setLiushuishuoming("删除代金券流水");
//            SelfUserEntity selfUserEntity = SecurityUtil.getUserInfo();
//            Long staffID = selfUserEntity.getStaffID();
            oaLiushuiYewu.setAddUser(staffID);
            oaLiushuiYewu.setAddTime(new Date());
            iOaLiushuiYewuService.save(oaLiushuiYewu);
            //插入一条日志记录
            OaLog oaLog = new OaLog();
            oaLog.setAddTime(new Date());
            oaLog.setFuncName("代金券删除");
            oaLog.setLogType(1);
            oaLog.setSystemContent("删除客户代金券,删除代金券的qiyeID为:\'" + oaDaijinquan.getQiyeID() + "\'");
            iOaLogService.save(oaLog);
        }
        boolean remove = iOaDaijinquanService.removeByIds(idsList);
        if (remove) {
            ajaxJson.setMsg("代金券信息删除成功");
        } else {
            ajaxJson.setMsg("代金券信息删除失败");
            ajaxJson.setCode("N");
            ajaxJson.setSuccess(false);
        }

        return ajaxJson;
    }

    /**
     * 分页查询所有的代金券信息
     * <p>
     * isGetOrUse   1:获得  2:使用
     *
     * @return
     */
    @RequestMapping(value = "/getAllDaijinquan", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllDaijinquan(@RequestParam(value = "size", defaultValue = "10") long size,
                                     @RequestParam(value = "current", defaultValue = "1") long current,/*Long qiyeID,*/
                                     @RequestParam(value = "isGetOrUse", defaultValue = "1") Integer isGetOrUse,
                                     String id, String kehucompanyname, String staffName, String searchDateStart, String searchDateEnd
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<DaijinquanVo> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        if (isGetOrUse == 1) {
            queryWrapper.ne("getDjq", 0);
        } else if (isGetOrUse == 2) {
            queryWrapper.ne("useDjq", 0);
        }
        if (/*qiyeID == null && */kehucompanyname == null && id == null && staffName == null && searchDateStart == null && searchDateEnd == null) {
            queryWrapper = null;
        }
//        if (qiyeID != null) {
//            queryWrapper.eq("daijinquan.qiyeID", qiyeID);
//        }
        if (kehucompanyname != null) {
            queryWrapper.like("kehu.kehucompanyname", kehucompanyname);
        }
        if (id != null) {
            queryWrapper.like("daijinquan.id", id);
        }
        if (staffName != null) {
            queryWrapper.like("staff.staffName", staffName);
        }
        if (searchDateStart != null && searchDateEnd != null) {
            queryWrapper.between("daijinquan.addTime", searchDateStart, searchDateEnd);
        }
        IPage<DaijinquanVo> iPage = iOaDaijinquanService.getAllDaijinquanInfo(page, queryWrapper);
//        List<DaijinquanVo> daijinquanList = iPage.getRecords();

        ajaxJson.setObj(iPage);
        return ajaxJson;
    }

    /**
     * 根据id获取一个代金券信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getOneDaijinquanById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getOneDaijinquanById(@PathVariable("id") long id) {
        AjaxJson ajaxJson = new AjaxJson();
        DaijinquanVo daijinquanVo = iOaDaijinquanService.getOneDaijinquanById(id);
        ajaxJson.setMsg("根据id获取一个代金券信息");
        ajaxJson.setObj(daijinquanVo);
        return ajaxJson;
    }


    /**
     * 根据qiyeID获取该客户所有的代金券信息
     *
     * @param qiyeID
     * @return
     */
    @RequestMapping(value = "/getDaijinquanByQiyeId/{qiyeID}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getDaijinquanByQiyeId(@PathVariable("qiyeID") String qiyeID) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper<OaDaijinquan> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("qiyeID", qiyeID);
        List<OaDaijinquan> daijinquanList = iOaDaijinquanService.list(queryWrapper);
        ajaxJson.setMsg("根据企业ID获取所有的代金券信息");
        ajaxJson.setObj(daijinquanList);
        return ajaxJson;
    }

}
