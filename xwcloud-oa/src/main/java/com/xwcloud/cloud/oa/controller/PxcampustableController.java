package com.xwcloud.cloud.oa.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.DateUtil;
import com.xwcloud.cloud.model.OA.*;
import com.xwcloud.cloud.model.OA.Vo.PxcampusVo;
import com.xwcloud.cloud.model.OA.Vo.PxcampustableVo;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.entity.Pxcampustable;
import com.xwcloud.cloud.oa.service.*;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-05
 */
@Controller
@RequestMapping("/pxcampustable")
public class PxcampustableController {

    @Autowired
    private IPxcampustableService iPxcampustableService;

    @Autowired
    private IOaLiushuiYewuService iOaLiushuiYewuService;

    @Autowired
    private IOaKehuService iOaKehuService;

    @Autowired
    private IOaParameterService iOaParameterService;



    @Autowired
    private IOaLogService iOaLogService;

    /**
     * 添加校区信息
     *
     * @param pxcampustable
     * @return
     */
    @RequestMapping(value = "/addPxcampus", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addPxcampus(HttpServletRequest request,@RequestBody PxcampustableVo pxcampustable) {

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long staffID = loginUser.getStaffID();

        AjaxJson ajaxJson = new AjaxJson();
//        boolean save = iPxcampustableService.save(pxcampustable);
//        if (save) {

        OaLiushuiYewu oaLiushuiYewu = new OaLiushuiYewu();
        oaLiushuiYewu.setQiyeID(pxcampustable.getQiyeID());
        oaLiushuiYewu.setAddTime(new Date());
        oaLiushuiYewu.setLiushuishuoming(pxcampustable.getLiushuishuoming());
        //流水类别：1培训流水，2不续费流水，3代金券流水，4下次付款时间流水，5加校区流水，6停用服用流水
        oaLiushuiYewu.setLiushuiType(5);
//            SelfUserEntity selfUserEntity = SecurityUtil.getUserInfo();
//            Long staffID = selfUserEntity.getStaffID();
        oaLiushuiYewu.setAddUser(staffID);
        iOaLiushuiYewuService.save(oaLiushuiYewu);
        Long qiyeID = pxcampustable.getQiyeID();
        OaKehu oaKehu = iOaKehuService.getById(qiyeID);
        QueryWrapper<Pxcampustable> queryWrapper = new QueryWrapper<Pxcampustable>();
        queryWrapper.eq("qiyeID", qiyeID);
        List<Pxcampustable> pxcampusList = iPxcampustableService.list(queryWrapper);
        //如果这个客户只有一个校区 则该客户的下次付款时间就是校区的下次付款时间
        if (pxcampusList.size() == 1) {
            oaKehu.setNextpaydatetime(pxcampustable.getNextPayTime());
            oaKehu.setFirstqiandandatetime(pxcampustable.getBuyDateTime());
        } else if (pxcampusList.size() > 1) {
            //如果这个客户有多个校区 oa_kehu的下次付款时间应该是存校区中最近的一次下次付款时间
            ArrayList<Date> dateArrayList = new ArrayList<>();
            ArrayList<Date> firstDateArrayList = new ArrayList<>();
            for (Pxcampustable pxcampus : pxcampusList) {
                //将该客户所有校区的下次付款时间存起来
                dateArrayList.add(pxcampus.getNextPayTime());
                firstDateArrayList.add(pxcampus.getBuyDateTime());
            }
            Date min = Collections.min(dateArrayList);
            Date firstBuyDate = Collections.min(firstDateArrayList);
            oaKehu.setFirstqiandandatetime(firstBuyDate);
            oaKehu.setNextpaydatetime(min);
        }
        iOaKehuService.updateById(oaKehu);

        OaLog oaLog = new OaLog();
        oaLog.setSystemContent("为企业ID为:\'" + pxcampustable.getQiyeID() + "\'的客户添加了一个校区,添加的校区名为:\'" + pxcampustable.getCampusName() + "\',校区的下次付款时间为:" + pxcampustable.getNextPayTime() + "\'");
        oaLog.setFuncName("为客户添加校区信息");
        oaLog.setAddTime(new Date());
        oaLog.setLogType(1);
        iOaLogService.save(oaLog);
        ajaxJson.setMsg("校区信息添加成功");

        return ajaxJson;
    }


    /**
     * 修改校区信息
     *
     * @param pxcampustable
     * @return
     */
    @RequestMapping(value = "/editPxcampus", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson editPxcampus(@RequestBody Pxcampustable pxcampustable) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean update = iPxcampustableService.updateById(pxcampustable);
        if (update) {
            ajaxJson.setMsg("校区信息修改成功");
        } else {
            ajaxJson.setMsg("校区信息修改失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        OaLog oaLog = new OaLog();
        oaLog.setSystemContent("为qiyeID为:\'" + pxcampustable.getQiyeID() + "\'的客户修改了校区的名称,修改后的校区名称为:\'" + pxcampustable.getCampusName() + "\'");
        oaLog.setFuncName("为客户修改校区信息");
        oaLog.setAddTime(new Date());
        oaLog.setLogType(1);
        iOaLogService.save(oaLog);

        return ajaxJson;
    }

    /**
     * 批量删除校区信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delPxcampus", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delPxcampus(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] arrId = ids.split(",");
        List<String> listId = Arrays.asList(arrId);
        boolean remove = iPxcampustableService.removeByIds(listId);
        if (remove) {
            ajaxJson.setMsg("删除校区信息成功");
        } else {
            ajaxJson.setMsg("删除校区信息失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }
        ids = ids.substring(0, ids.length() - 1);
        OaLog oaLog = new OaLog();
        oaLog.setSystemContent("删除了ID为:\'" + ids + "\'的校区信息" + "\'");
        oaLog.setFuncName("为客户删除校区信息");
        oaLog.setAddTime(new Date());
        oaLog.setLogType(1);
        iOaLogService.save(oaLog);

        return ajaxJson;
    }

    /**
     * 分页获取所有的校区信息
     *
     * @param size
     * @param current
     * @param qiyeID
     * @param id
     * @param campusName
     * @param buyDateTimeStart
     * @param buyDateTimeEnd
     * @param nextPayTimeStart
     * @param nextPayTimeEnd
     * @return
     */
    @RequestMapping(value = "/getAllPxcampus", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllPxcampus(@RequestParam(value = "size", defaultValue = "10") long size,
                                   @RequestParam(value = "current", defaultValue = "1") long current,
                                   Long qiyeID, Long id, String campusName,
                                   String buyDateTimeStart, String buyDateTimeEnd,
                                   String nextPayTimeStart, String nextPayTimeEnd) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<PxcampusVo> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("pxcampustable.qiyeID", qiyeID);
        if (id != null) {
            queryWrapper.like("pxcampustable.id", id);
        }
        if (campusName != null) {
            queryWrapper.like("pxcampustable.campusName", campusName);
        }
        if (buyDateTimeStart != null && buyDateTimeEnd != null) {
            queryWrapper.between("pxcampustable.buyDateTime", buyDateTimeStart, buyDateTimeEnd);
        }
        if (nextPayTimeStart != null && nextPayTimeEnd != null) {
            queryWrapper.between("pxcampustable.nextPayTime", nextPayTimeStart, nextPayTimeEnd);
        }
        IPage<PxcampusVo> iPage = iPxcampustableService.getAllPxcampusInfo(page, queryWrapper);
//        List<PxcampusVo> pxcampusVoList = iPage.getRecords();
        ajaxJson.setObj(iPage);
        ajaxJson.setMsg("分页查询所有培训校区");
        return ajaxJson;
    }

    /**
     * 获取所有客户校区的下次付款时间(设置下次付款时间的页面查询数据)
     *
     * @param size
     * @param current
     * @param id
     * @param campusName
     * @param kehucompanyname
     * @param buyDateTimeStart
     * @param buyDateTimeEnd
     * @param nextPayTimeStart
     * @param nextPayTimeEnd
     * @return
     */
    @RequestMapping(value = "/getAllPxcampusNextPayTime", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllPxcampusNextPayTime(@RequestParam(value = "size", defaultValue = "10") long size,
                                              @RequestParam(value = "current", defaultValue = "1") long current,
                                              Long id, String campusName, String kehucompanyname,
                                              String buyDateTimeStart, String buyDateTimeEnd,
                                              String nextPayTimeStart, String nextPayTimeEnd) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<PxcampusVo> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        if (id == null && campusName == null && kehucompanyname == null &&
                buyDateTimeStart == null && buyDateTimeEnd == null &&
                nextPayTimeStart == null && nextPayTimeEnd == null) {
            queryWrapper = null;
        }
        if (id != null) {
            queryWrapper.like("pxcampustable.id", id);
        }
        if (kehucompanyname != null) {
            queryWrapper.like("kehu.kehucompanyname", kehucompanyname);
        }
        if (campusName != null) {
            queryWrapper.like("pxcampustable.campusName", campusName);
        }
        if (buyDateTimeStart != null && buyDateTimeEnd != null) {
            queryWrapper.between("pxcampustable.buyDateTime", buyDateTimeStart, buyDateTimeEnd);
        }
        if (nextPayTimeStart != null && nextPayTimeEnd != null) {
            queryWrapper.between("pxcampustable.nextPayTime", nextPayTimeStart, nextPayTimeEnd);
        }

        IPage<PxcampusVo> iPage = iPxcampustableService.getAllPxcampusInfo(page, queryWrapper);
        ajaxJson.setObj(iPage);
        ajaxJson.setMsg("获取所有客户校区的下次付款时间成功");
        return ajaxJson;
    }

    /**
     * 设置校区的下次付款时间
     *
     * @return
     */
    @RequestMapping(value = "/setNextPayTime", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson setNextPayTime(HttpServletRequest request,@RequestBody Map<String, Object> params) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long staffID = loginUser.getStaffID();

//        SelfUserEntity userInfo = SecurityUtil.getUserInfo();
//        Long staffID = userInfo.getStaffID();
        AjaxJson ajaxJson = new AjaxJson();
        String kehuID = (String) params.get("qiyeID");
        String[] kehuIdArr = kehuID.split(",");
        Set<String> set = new HashSet<>();
        for (String id : kehuIdArr) {
            set.add(id);
        }
        if (set.size() > 1) {
            ajaxJson.setCode("N");
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("多选设置下次付款时间需要是同一个客户的校区,请您重新再试");
            return ajaxJson;
        }
        String nextPayTime = (String) params.get("nextPayTime");
        Date date = DateUtil.toDate(nextPayTime, "yyyy-MM-dd");
        String ids = (String) params.get("id");
        String[] campusIdArr = ids.split(",");
        String qiyeID = set.iterator().next();

        String shuoming = (String) params.get("shuoming");

        for (int i = 0; i < campusIdArr.length; i++) {
            OaLiushuiYewu oaLiushuiYewu = new OaLiushuiYewu();
            //流水类型   4.下次付款时间流水
            oaLiushuiYewu.setLiushuiType(4);
            oaLiushuiYewu.setQiyeID(Long.parseLong(qiyeID));
            oaLiushuiYewu.setLiushuishuoming(shuoming);
            oaLiushuiYewu.setAddTime(new Date());
            //添加人
            oaLiushuiYewu.setAddUser(staffID);
            Pxcampustable pxcampustable = iPxcampustableService.getById(Long.parseLong(campusIdArr[i]));
            pxcampustable.setNextPayTime(date);
            boolean update = iPxcampustableService.updateById(pxcampustable);
            OaKehu oaKehu = iOaKehuService.getById(qiyeID);
            if (campusIdArr.length == 1) {
                oaKehu.setNextpaydatetime(date);
            } else if (campusIdArr.length > 1) {
                //获取该客户的所有校区信息
                QueryWrapper<Pxcampustable> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("qiyeID", qiyeID);
                List<Pxcampustable> pxcampusList = iPxcampustableService.list(queryWrapper);
                //如果这个客户有多个校区 oa_kehu的下次付款时间应该是存校区中最近的一次下次付款时间
                ArrayList<Date> dateArrayList = new ArrayList<>();
                for (Pxcampustable campus : pxcampusList) {
                    //将该客户所有校区的下次付款时间存起来
                    dateArrayList.add(campus.getNextPayTime());
                }
                Date min = Collections.min(dateArrayList);
                oaKehu.setNextpaydatetime(min);
            }
            //修改客户的下次付款时间是校区最近的一次下次付款时间
            iOaKehuService.updateById(oaKehu);
            if (update) {
                //往日志表中插入一条数据
                OaLog oaLog = new OaLog();
                oaLog.setSystemContent("为\'" + pxcampustable.getCampusName() + "\'校区设置了下次付款时间," + "设置的下次付款时间为:\'" + nextPayTime + "\',该校区所属企业:\'" + oaKehu.getKehucompanyname() + "\'");
                oaLog.setFuncName("为校区设置下次付款时间");
                oaLog.setAddTime(new Date());
                oaLog.setLogType(1);
                iOaLogService.save(oaLog);
            }
            iOaLiushuiYewuService.save(oaLiushuiYewu);
        }

        //--------
//        QueryWrapper<Pxcampustable> queryWrapper = new QueryWrapper<>();
//        queryWrapper.in("id", campusIdArr);
//        List<Pxcampustable> pxcampustableList = iPxcampustableService.list(queryWrapper);
//        OaKehu oaKehu = iOaKehuService.getById(qiyeID);
//        for (int i = 0; i < pxcampustableList.size(); i++) {
//            Pxcampustable pxcampus = pxcampustableList.get(i);
//            pxcampus.setNextPayTime(date);
//            boolean update = iPxcampustableService.updateById(pxcampus);
//            if (update) {
//                //往日志表中插入一条数据
//                OaLog oaLog = new OaLog();
//                oaLog.setSystemContent("为\'" + pxcampus.getCampusName() + "\'校区设置了下次付款时间," + "设置的下次付款时间为:\'" + nextPayTime + "\',该校区所属企业:\'" + oaKehu.getKehucompanyname() + "\'");
//                oaLog.setFuncName("为校区设置下次付款时间");
//                oaLog.setAddTime(new Date());
//                oaLog.setLogType(1);
//                iOaLogService.save(oaLog);
//            }
//            if (i < pxcampustableList.size() - 1) {
//                continue;
//            }
//            //获取该客户的所有校区信息
//            queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("qiyeID", qiyeID);
//            List<Pxcampustable> pxcampusList = iPxcampustableService.list(queryWrapper);
//            //如果这个客户只有一个校区 则该客户的下次付款时间就是校区的下次付款时间
//            if (pxcampusList.size() == 1) {
//                oaKehu.setNextpaydatetime(date);
//            } else if (pxcampusList.size() > 1) {
//                //如果这个客户有多个校区 oa_kehu的下次付款时间应该是存校区中最近的一次下次付款时间
//                ArrayList<Date> dateArrayList = new ArrayList<>();
//                for (Pxcampustable pxcampustable : pxcampusList) {
//                    //将该客户所有校区的下次付款时间存起来
//                    dateArrayList.add(pxcampustable.getNextPayTime());
//                }
//                Date min = Collections.min(dateArrayList);
//                oaKehu.setNextpaydatetime(min);
//            }
//            boolean update1 = iOaKehuService.updateById(oaKehu);
//            if (update1) {
//                ajaxJson.setMsg("设置下次付款时间成功");
//
//            } else {
//                ajaxJson.setMsg("设置下次付款时间失败");
//                ajaxJson.setSuccess(false);
//                ajaxJson.setCode("N");
//            }
//        }

        return ajaxJson;
    }

    /**
     * 根据id查询一条校区信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getOnePxcampusById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getOnePxcampusById(@PathVariable("id") Long id) {
        AjaxJson ajaxJson = new AjaxJson();
        PxcampusVo pxcampusInfo = iPxcampustableService.getOnePxcampusById(id);
        ajaxJson.setMsg("根据id查询一条校区信息");
        ajaxJson.setObj(pxcampusInfo);
        return ajaxJson;
    }


    /**
     * 校区启用或停用
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/editCampusIsOpenState", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson editCampusIsOpenState(Long id) {
        AjaxJson ajaxJson = new AjaxJson();
        Pxcampustable pxcampus = iPxcampustableService.getById(id);
        //往日志表中插入一条数据
        OaLog oaLog = new OaLog();
        //启用状态
        if (pxcampus.getIsOpen() == 1) {
            pxcampus.setIsOpen(2); //改为停用状态
            oaLog.setSystemContent("停用了校区名为:\'" + pxcampus.getCampusName() + "\'的校区");
        } else {
            pxcampus.setIsOpen(1);
            oaLog.setSystemContent("启用了校区名为:\'" + pxcampus.getCampusName() + "\'的校区");
        }
        boolean update = iPxcampustableService.updateById(pxcampus);
        if (update) {
            ajaxJson.setMsg("校区状态修改成功");
        } else {
            ajaxJson.setMsg("校区状态修改失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        oaLog.setFuncName("校区启用停用");
        oaLog.setAddTime(new Date());
        oaLog.setLogType(1);
        iOaLogService.save(oaLog);

        return ajaxJson;
    }

}
