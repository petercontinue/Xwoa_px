package com.xwcloud.cloud.oa.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.OA.OaHehuoren;
import com.xwcloud.cloud.model.OA.Vo.HehuorenVo;
import com.xwcloud.cloud.oa.service.IOaHehuorenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/oaHehuoren")
public class OaHehuorenController {

    @Autowired
    private IOaHehuorenService iOaHehuorenService;

    /**
     * 添加合伙人的信息
     *
     * @param oaHehuoren
     * @return
     */
    @RequestMapping(value = "/addHehuoren", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addHehuoren(@RequestBody OaHehuoren oaHehuoren) {
        AjaxJson ajaxJson = new AjaxJson();
        oaHehuoren.setAddTime(new Date());
        boolean save = iOaHehuorenService.save(oaHehuoren);
        if (save) {
            ajaxJson.setMsg("合伙人添加成功");
        } else {
            ajaxJson.setMsg("合伙人添加失败");
            ajaxJson.setCode("N");
            ajaxJson.setSuccess(false);
        }
        return ajaxJson;
    }

    /**
     * 修改合伙人的信息
     *
     * @param oaHehuoren
     * @return
     */
    @RequestMapping(value = "/editHehuoren", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson editHehuoren(@RequestBody OaHehuoren oaHehuoren) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean update = iOaHehuorenService.updateById(oaHehuoren);
        if (update) {
            ajaxJson.setMsg("合伙人修改成功");
        } else {
            ajaxJson.setMsg("合伙人修改失败");
            ajaxJson.setCode("N");
            ajaxJson.setSuccess(false);
        }
        return ajaxJson;
    }

    /**
     * 根据id批量删除合伙人信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delHehuoren", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delHehuoren(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] idsArr = ids.split(",");
        List<String> idsList = Arrays.asList(idsArr);
        boolean remove = iOaHehuorenService.removeByIds(idsList);
        if (remove) {
            ajaxJson.setMsg("合伙人删除成功");
        } else {
            ajaxJson.setMsg("合伙人删除失败");
            ajaxJson.setCode("N");
            ajaxJson.setSuccess(false);
        }
        return ajaxJson;
    }

    /**
     * 分页查询合伙人的信息
     *
     * @param size
     * @param current
     * @return
     */
    @RequestMapping(value = "/getAllHehuoren", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllHehuoren(@RequestParam(value = "size", defaultValue = "10") long size,
                                   @RequestParam(value = "current", defaultValue = "1") long current,
                                   String id, String realName, String phone, Long hehuoLevel, String hehuoType, Long prinvince,
                                   String levelStartTime1, String levelStartTime2, String levelEndTime1, String levelEndTime2,
                                   String firstQiandanTimeStart, String firstQiandanTimeEnd) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<HehuorenVo> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        if (id == null && realName == null && phone == null && hehuoLevel == null && hehuoType == null && prinvince == null &&
                levelStartTime1 == null && levelStartTime2 == null && levelEndTime1 == null && levelEndTime2 == null &&
                firstQiandanTimeStart == null && firstQiandanTimeEnd == null) {
            queryWrapper = null;
        }
        if (id != null) {
            queryWrapper.like("hehuoren.id", id);
        }
        if (realName != null) {
            queryWrapper.like("hehuoren.realName", realName);
        }
        if (prinvince != null) {
            queryWrapper.eq("hehuoren.prinvinceID", prinvince);
        }
        if (phone != null) {
            queryWrapper.like("hehuoren.phone", phone);
        }
        if (hehuoLevel != null) {
            queryWrapper.eq("hehuoren.hehuoLevel", hehuoLevel);
        }
        if (hehuoType != null) {
            queryWrapper.like("hehuoren.hehuoType", hehuoType);
        }
        if (levelStartTime1 != null && levelStartTime2 != null) {
            queryWrapper.between("hehuoren.levelStartTime", levelStartTime1, levelStartTime2);
        }
        if (levelEndTime1 != null && levelEndTime2 != null) {
            queryWrapper.between("hehuoren.levelEndTime", levelEndTime1, levelEndTime2);
        }
        if (firstQiandanTimeStart != null && firstQiandanTimeEnd != null) {
            queryWrapper.between("hehuoren.firstQiandanTime", firstQiandanTimeStart, firstQiandanTimeEnd);
        }
        IPage<HehuorenVo> iPage = iOaHehuorenService.getAllHehuorenInfo(page, queryWrapper);
//        List<HehuorenVo> oaHehuorenList = iPage.getRecords();

        ajaxJson.setObj(iPage);
        return ajaxJson;
    }

    /**
     * 根据id获取一个合伙人信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getOneHehuorenById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getOneHehuorenById(@PathVariable("id") Long id) {
        AjaxJson ajaxJson = new AjaxJson();
        HehuorenVo hehuorenVo = iOaHehuorenService.getOneHehuorenById(id);
        ajaxJson.setMsg("根据id获取一个合伙人信息");
        ajaxJson.setObj(hehuorenVo);
        return ajaxJson;
    }

}
