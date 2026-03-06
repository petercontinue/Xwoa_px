package com.xwcloud.cloud.oa.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.DateUtil;
import com.xwcloud.cloud.model.OA.OaLiushuiYewu;
import com.xwcloud.cloud.model.OA.OaLog;
import com.xwcloud.cloud.model.OA.OaPeixunrecord;
import com.xwcloud.cloud.model.OA.Vo.PeixunrecordVo;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.oa.service.IOaLiushuiYewuService;
import com.xwcloud.cloud.oa.service.IOaLogService;
import com.xwcloud.cloud.oa.service.IOaPeixunrecordService;
import com.xwcloud.cloud.oa.service.IOaQiandanService;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
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
@RequestMapping("/oaPeixunrecord")
public class OaPeixunrecordController {

    @Autowired
    private IOaPeixunrecordService iOaPeixunrecordService;

    @Autowired
    private IOaQiandanService iOaQiandanService;

    @Autowired
    private IOaLiushuiYewuService iOaLiushuiYewuService;

    @Autowired
    private IOaLogService iOaLogService;

    /**
     * 根据qiyeID添加培训记录
     *
     * @param oaPeixunrecord
     * @return
     */
    @RequestMapping(value = "/addPeixunRecord", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addPeixunRecord(HttpServletRequest request,@RequestBody OaPeixunrecord oaPeixunrecord) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long staffID = loginUser.getStaffID();


        AjaxJson ajaxJson = new AjaxJson();
        //根据qiyeID查询是否有是否已签单 如果已签单才对该客户进行培训
        Long qiyeID = oaPeixunrecord.getQiyeID();
        if (oaPeixunrecord.getAddTime() == null) {
            oaPeixunrecord.setAddTime(new Date());
        }
//        SelfUserEntity userInfo = SecurityUtil.getUserInfo();
//        Long staffID = userInfo.getStaffID();
        oaPeixunrecord.setAddstaffID(staffID);
        boolean result = iOaQiandanService.checkKehuYiqiandan(qiyeID);
        if (result) {
            boolean insert = iOaPeixunrecordService.save(oaPeixunrecord);
            if (insert) {
                ajaxJson.setMsg("培训记录添加成功");
                OaLiushuiYewu oaLiushuiYewu = new OaLiushuiYewu();
                oaLiushuiYewu.setQiyeID(oaPeixunrecord.getQiyeID());
                //流水类别：1培训流水，2不续费流水，3代金券流水，4下次付款时间流水，5加校区流水，6停用服用流水
                oaLiushuiYewu.setLiushuiType(1);
                oaLiushuiYewu.setAddUser(oaPeixunrecord.getAddstaffID());
                oaLiushuiYewu.setLiushuishuoming("培训流水");
                oaLiushuiYewu.setAddTime(new Date());
                iOaLiushuiYewuService.save(oaLiushuiYewu);
            }
        } else {
            ajaxJson.setMsg("该用户还没有进行签单，请进行签单后再进行培训");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
            return ajaxJson;
        }

        //往日志表中插入一条数据
        OaLog oaLog = new OaLog();
        oaLog.setSystemContent("为qiyeID为:\'" + oaPeixunrecord.getQiyeID() + "\'的客户添加了一条培训记录," + "设置的该客户的培训时间为:\'" + DateUtil.formatDate4(oaPeixunrecord.getPxDate()) + "\'");
        oaLog.setFuncName("为客户添加一条培训记录");
        oaLog.setAddTime(new Date());
        oaLog.setLogType(1);
        iOaLogService.save(oaLog);
        return ajaxJson;
    }

    /**
     * 根据qiyeID删除一条培训记录
     *
     * @param qiyeID
     * @return
     */
    @RequestMapping(value = "/delPeixunRecord/{qiyeID}", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delPeixunRecord(@PathVariable("qiyeID") Long qiyeID) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper<OaPeixunrecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("qiyeID", qiyeID);
        boolean delete = iOaPeixunrecordService.remove(queryWrapper);
        if (delete) {
            ajaxJson.setMsg("培训记录删除成功");
        } else {
            ajaxJson.setMsg("培训记录删除失败");
            ajaxJson.setCode("N");
            ajaxJson.setSuccess(false);
        }
        return ajaxJson;
    }

    /**
     * 修改客户的培训记录
     *
     * @param oaPeixunrecord
     * @return
     */
    @RequestMapping(value = "/editPeixunRecord", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson editPeixunRecord(HttpServletRequest request,@RequestBody OaPeixunrecord oaPeixunrecord) {

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long staffID = loginUser.getStaffID();

        AjaxJson ajaxJson = new AjaxJson();
        //先查询该qiyeID对应的培训记录
        Long qiyeID = oaPeixunrecord.getQiyeID();
        QueryWrapper<OaPeixunrecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("qiyeID", qiyeID);
        OaPeixunrecord peixunrecord = iOaPeixunrecordService.getOne(queryWrapper);
//        SelfUserEntity userInfo = SecurityUtil.getUserInfo();
//        Long staffID = userInfo.getStaffID();
        oaPeixunrecord.setAddstaffID(staffID);
        oaPeixunrecord.setAddTime(new Date());
        //表中存在该客户的培训记录
        if (peixunrecord != null) {
            boolean update = iOaPeixunrecordService.updateById(oaPeixunrecord);
            if (update) {
                ajaxJson.setMsg("该客户的培训记录修改成功");
            }
        } else {
            ajaxJson.setMsg("该客户还没有进行培训，修改培训记录失败");
            ajaxJson.setCode("N");
            ajaxJson.setSuccess(false);
        }

        return ajaxJson;
    }


    /**
     * 根据qiyeID批量删除培训记录
     *
     * @param qiyeIDs
     * @return
     */
    @RequestMapping(value = "/delPeixunRecordByQiyeIds", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delPeixunRecordByQiyeIds(String qiyeIDs) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper<OaPeixunrecord> queryWrapper = new QueryWrapper<>();
        String[] qiyeIDArr = qiyeIDs.split(",");
        List<String> qiyeIdList = Arrays.asList(qiyeIDArr);
        queryWrapper.in("qiyeID", qiyeIdList);
        boolean remove = iOaPeixunrecordService.remove(queryWrapper);
        if (remove) {
            ajaxJson.setMsg("企业培训记录删除成功");
        } else {
            ajaxJson.setMsg("企业培训记录删除失败");
            ajaxJson.setCode("N");
            ajaxJson.setSuccess(false);
        }

        return ajaxJson;
    }

    /**
     * 根据id批量删除培训记录
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delPeixunRecordByIds", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delPeixunRecordByIds(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] idArr = ids.split(",");
        List<String> idList = Arrays.asList(idArr);
        boolean remove = iOaPeixunrecordService.removeByIds(idList);
        if (remove) {
            ajaxJson.setMsg("企业培训记录删除成功");
        } else {
            ajaxJson.setMsg("企业培训记录删除失败");
            ajaxJson.setCode("N");
            ajaxJson.setSuccess(false);
        }

        //往日志表中插入一条数据
        OaLog oaLog = new OaLog();
        ids = ids.substring(0, ids.length() - 1);
        oaLog.setSystemContent("删除了ID为:\'" + ids + "\'客户的培训记录");
        oaLog.setFuncName("删除客户培训记录");
        oaLog.setAddTime(new Date());
        oaLog.setLogType(1);
        iOaLogService.save(oaLog);
        return ajaxJson;
    }

    /**
     * 获取所有的培训信息
     *
     * @param current
     * @param size
     * @param id
     * @param staffName
     * @param pxDateSearchStart
     * @param pxDateSearchEnd
     * @param addTimeSearchStart
     * @param addTimeSearchEnd
     * @return
     */
    @RequestMapping(value = "/getAllPeixunRecord", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllPeixunRecord(@RequestParam(value = "current", defaultValue = "1") long current,
                                       @RequestParam(value = "size", defaultValue = "10") long size,/*String qiyeID,*/
                                       String id, String staffName, String kehucompanyname,
                                       String pxDateSearchStart, String pxDateSearchEnd,
                                       String addTimeSearchStart, String addTimeSearchEnd) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<PeixunrecordVo> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("peixunrecord.qiyeID", qiyeID);
        if (id == null && staffName == null && pxDateSearchStart == null && kehucompanyname == null &&
                pxDateSearchEnd == null && addTimeSearchStart == null && addTimeSearchEnd == null) {
            queryWrapper = null;
        }
        if (id != null) {
            queryWrapper.like("peixunrecord.id", id);
        }
        if (staffName != null) {
            queryWrapper.like("staff.staffName", staffName);
        }
        if (kehucompanyname != null) {
            queryWrapper.like("kehu.kehucompanyname", kehucompanyname);
        }
        if (pxDateSearchStart != null && pxDateSearchEnd != null) {
            queryWrapper.between("peixunrecord.pxDate", pxDateSearchStart, pxDateSearchEnd);
        }
        if (addTimeSearchStart != null && addTimeSearchEnd != null) {
            queryWrapper.between("peixunrecord.addTime", addTimeSearchStart, addTimeSearchEnd);
        }
        IPage<PeixunrecordVo> iPage = iOaPeixunrecordService.getAllPeixunrecordInfo(page, queryWrapper);
//        List<PeixunrecordVo> records = iPage.getRecords();
        ajaxJson.setMsg("查询所有客户的培训记录成功");
        ajaxJson.setObj(iPage);
        return ajaxJson;
    }

    /**
     * 根据id获取一条培训记录信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getOnePeixunRecordById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getOnePeixunRecordById(@PathVariable("id") long id) {
        AjaxJson ajaxJson = new AjaxJson();
        PeixunrecordVo peixunrecordVo = iOaPeixunrecordService.getOnePeixunrecordById(id);
        ajaxJson.setMsg("根据id获取一条培训记录信息");
        ajaxJson.setObj(peixunrecordVo);
        return ajaxJson;
    }

}
