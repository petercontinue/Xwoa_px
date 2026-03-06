package com.xwcloud.cloud.oa.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.OA.Vo.YewuLiushuiVo;
import com.xwcloud.cloud.oa.service.IOaLiushuiYewuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-13
 */
@Controller
@RequestMapping("/oaLiushuiYewu")
public class OaLiushuiYewuController {

    @Autowired
    private IOaLiushuiYewuService iOaLiushuiYewuService;

    /**
     * 分页查询所有的业务流水信息
     * liushuiType:  0.全部  1培训流水，2不续费流水，3代金券流水，4下次付款时间流水，5加校区流水，6停用服用流水
     *
     * @param liushuiType
     * @return
     */
    @RequestMapping(value = "/getAllYewuLiushuiInfo", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllYewuLiushuiInfo(@RequestParam(value = "size", defaultValue = "10") long size,
                                          @RequestParam(value = "current", defaultValue = "1") long current,
                                          @RequestParam(value = "liushuiType", defaultValue = "0") Integer liushuiType,
                                          Long id, String kehucompanyname, String staffName, /*String qiyeID,*/
                                          String searchDateStart, String searchDateEnd) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<YewuLiushuiVo> page = new Page<>(current, size);
        QueryWrapper<Object> queryWrapper = new QueryWrapper<>();
//        if (liushuiType != null && liushuiType != 0 && id != null && kehucompanyname != null &&
//                staffName != null && searchDateStart != null && searchDateEnd != null) {
//            queryWrapper.eq("yewuliushui.liushuiType", liushuiType);
//        } else {
//            queryWrapper = null;
//        }
        queryWrapper.eq("yewuliushui.liushuiType", liushuiType);
//        if (qiyeID != null) {
//            queryWrapper.eq("yewuliushui.qiyeID", qiyeID);
//        }
        if (id != null) {
            queryWrapper.like("yewuliushui.id", id);
        }
        if (kehucompanyname != null) {
            queryWrapper.like("kehu.kehucompanyname", kehucompanyname);
        }
        if (staffName != null) {
            queryWrapper.like("staff.staffName", staffName);
        }
        if (searchDateStart != null && searchDateEnd != null) {
            queryWrapper.between("yewuliushui.addTime", searchDateStart, searchDateEnd);
        }
        IPage<YewuLiushuiVo> iPage = iOaLiushuiYewuService.getAllYewuLiushuiInfo(page, queryWrapper);
        ajaxJson.setObj(iPage);
        ajaxJson.setMsg("分页查询所有的业务流水信息成功");

        return ajaxJson;
    }


}
