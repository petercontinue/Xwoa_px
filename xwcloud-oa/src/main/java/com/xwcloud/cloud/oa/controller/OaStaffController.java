package com.xwcloud.cloud.oa.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.OA.OaStaff;
import com.xwcloud.cloud.model.OA.OaStaffpost;
import com.xwcloud.cloud.model.OA.Vo.StaffVo;
import com.xwcloud.cloud.oa.service.IOaStaffService;
import com.xwcloud.cloud.oa.service.IOaStaffpostService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-29
 */
@Controller
@RequestMapping("/oaStaff")
public class OaStaffController {

    @Autowired
    private IOaStaffService iOaStaffService;

    @Autowired
    private IOaStaffpostService iOaStaffpostService;

    /**
     * 添加职员信息
     *
     * @return
     */
    @RequestMapping(value = "/addStaff", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addStaff(String stafftel,
                             String staffName,
                             Long staffpostID,
                             String addtime,
                             String shuoming,
                             String workName,
                             String worktel
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        OaStaffpost oaStaffpost = iOaStaffpostService.getById(staffpostID);
        OaStaff oaStaff = new OaStaff();
        Date parse = null;
        try {
            parse = sdf.parse(addtime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passHash = encoder.encode("123456");

        oaStaff
                .setStaffpostID(staffpostID)
                .setAddtime(parse)
                .setWorkName(workName)
                .setWorktel(worktel)
                .setShuoming(shuoming)
                .setStafftel(stafftel)
                .setStaffName(staffName)
                .setStaffpostName(oaStaffpost.getStaffpostName())
                .setStaffstate(1)
                .setQiyeID(Long.valueOf(-1))
                .setPasswd(passHash);

        ajaxJson.setSuccess(iOaStaffService.save(oaStaff));
        return ajaxJson;
    }

    /**
     * @param id
     * @param pwd
     * @return
     */
    @ApiOperation("修改密码")
    @ResponseBody
    @PostMapping("updateuserpwd")
    public AjaxJson updateuserpwd(String id, String oldpwd, String pwd) {
        AjaxJson ajaxJson = new AjaxJson();
        OaStaff staff = iOaStaffService.getById(id);


        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!new BCryptPasswordEncoder().matches(oldpwd, staff.getPasswd())) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("密码不正确");
            return ajaxJson;
        }

        String passHash = encoder.encode(pwd);
        staff.setPasswd(passHash);
        ajaxJson.setSuccess(iOaStaffService.updateById(staff));
        return ajaxJson;
    }


    /**
     * 冻结or解冻状态的修改
     *
     * @param id
     * @param staffState
     * @return
     */
    @RequestMapping(value = "/UpdateStaffSate", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson dongjieOrJiedongStaff(String id, Integer staffState) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] ids = id.split(",");
        List<String> idsList = Arrays.asList(ids);
        List<OaStaff> oaStaffList = (List<OaStaff>) iOaStaffService.listByIds(idsList);
        if (oaStaffList != null) {
            for (OaStaff oaStaff : oaStaffList) {
                if (staffState != null && staffState == 1) {
                    oaStaff.setStaffstate(1);//正常状态
                    ajaxJson.setMsg("职员解冻成功");
                } else if (staffState != null && staffState == 2) {
                    oaStaff.setStaffstate(2);//冻结状态
                    ajaxJson.setMsg("职员冻结成功");
                }
                iOaStaffService.updateById(oaStaff);
            }
        }

        return ajaxJson;
    }

    /**
     * 修改职员信息
     *
     * @return
     */
    @RequestMapping(value = "/editStaff", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson editStaff(
            String id,
            String stafftel,
            String staffName,
            Long staffpostID,
            String addtime,
            String shuoming,
            String workName,
            String worktel
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        OaStaffpost oaStaffpost = iOaStaffpostService.getById(staffpostID);
        OaStaff staff = iOaStaffService.getById(id);
        Date addT = null;
        try {
            addT = sdf.parse(addtime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        staff
                .setStaffpostID(staffpostID)
                .setAddtime(addT)
                .setWorkName(workName)
                .setWorktel(worktel)
                .setShuoming(shuoming)
                .setStafftel(stafftel)
                .setStaffName(staffName)
                .setStaffpostName(oaStaffpost.getStaffpostName())
                .setStaffstate(1)
                .setQiyeID(Long.valueOf(-1));

        boolean save = iOaStaffService.updateById(staff);
        if (save) {
            ajaxJson.setMsg("职员信息修改成功");
        } else {
            ajaxJson.setMsg("职员信息修改失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }
        return ajaxJson;
    }

    /**
     * 批量删除职员信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delStaff", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delStaff(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] idsArr = ids.split(",");
        List<String> idsList = Arrays.asList(idsArr);
        boolean remove = iOaStaffService.removeByIds(idsList);
        if (remove) {
            ajaxJson.setMsg("删除职员信息成功");
        } else {
            ajaxJson.setMsg("删除职员信息失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }

    /**
     * 分页获取所有的职员信息
     *
     * @param current
     * @param size
     * @return
     */
    @RequestMapping(value = "/getAllStaff", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllStaff(@RequestParam(value = "current", defaultValue = "1") long current,
                                @RequestParam(value = "size", defaultValue = "10") long size,
                                String staffTel,
                                String staffName,
                                Integer staffstate,
                                Integer currentmonthstaff) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<StaffVo> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("staff.stafftel", staffTel);
        queryWrapper.like("staff.staffName", staffName);
        if (staffstate != null && staffstate != 0) {
            queryWrapper.eq("staff.staffstate", staffstate);
        }
        //获取本月新入职的员工
        if (currentmonthstaff != null && currentmonthstaff != 0) {
            //获取本月第一天
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, 0);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            Date firstDate = calendar.getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String firstDay = dateFormat.format(firstDate);
            //获取本月最后一天
            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH, 0);
            Date lastDate = calendar.getTime();
            String lastDay = dateFormat.format(lastDate);
            queryWrapper.between("staff.addtime", firstDay, lastDay);
        }
        queryWrapper.orderByDesc("staff.addtime");
        IPage<StaffVo> iPage = iOaStaffService.getAllStaffInfo(page, queryWrapper);

        ajaxJson.setObj(iPage);
        ajaxJson.setMsg("分页获取所有的职员信息");

        return ajaxJson;
    }

    /**
     * 获取所有职员信息
     *
     * @param current
     * @param size
     * @return
     */
    @RequestMapping(value = "/getAllStaffInfo", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllStaffInfo(@RequestParam(value = "current", defaultValue = "1") long current,
                                    @RequestParam(value = "size", defaultValue = "10") long size) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<OaStaff> page = new Page<>(current, size);
        IPage<OaStaff> iPage = iOaStaffService.page(page);
        ajaxJson.setObj(iPage);
        return ajaxJson;
    }

    /**
     * 根据id获取一个职员信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getOneStaffById", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getOneStaffById(long id) {
        AjaxJson ajaxJson = new AjaxJson();
        OaStaff oaStaff = iOaStaffService.getById(id);
        ajaxJson.setMsg("根据id获取一个职员信息");
        ajaxJson.setObj(oaStaff);
        return ajaxJson;
    }

    @RequestMapping(value = "/UpdateStaffSate", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson UpdateStaffSate(int staffState, String id, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] IDs = id.split(",");
        List<OaStaff> list = iOaStaffService.list(new QueryWrapper<OaStaff>()
                .in("id", IDs)
                .eq("qiyeID", "-1")
        );
        for (OaStaff item : list) {
            item.setStaffstate(staffState);
            iOaStaffService.updateById(item);
        }
        ajaxJson.setSuccess(true);
        return ajaxJson;

    }

    @RequestMapping(value = "/resetStaffPassword", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson ResetTsaffPassword(String id, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] IDs = id.split(",");
        List<OaStaff> list = iOaStaffService.list(new QueryWrapper<OaStaff>()
                .in("id", IDs)
                .eq("qiyeID", "-1")
        );
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passHash = encoder.encode("123456");
        for (OaStaff item : list) {
            item.setPasswd(passHash);
            iOaStaffService.updateById(item);
        }
        ajaxJson.setSuccess(true);
        return ajaxJson;
    }

}
