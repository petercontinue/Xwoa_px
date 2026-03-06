package com.xwcloud.cloud.oa.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.OA.OaTuifeirecord;
import com.xwcloud.cloud.model.OA.Vo.TuifeirecordVo;
import com.xwcloud.cloud.oa.service.IOaTuifeirecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-01
 */
@Controller
@RequestMapping("/oaTuifeirecord")
public class OaTuifeirecordController {

    @Autowired
    private IOaTuifeirecordService iOaTuifeirecordService;

    /**
     * 添加一条退费记录
     *
     * @param oaTuifeirecord
     * @return
     */
    @RequestMapping(value = "/addTuifeiRecord", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addTuifeiRecord(@RequestBody OaTuifeirecord oaTuifeirecord) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean save = iOaTuifeirecordService.save(oaTuifeirecord);
        if (save) {
            ajaxJson.setMsg("退费记录添加成功");
        } else {
            ajaxJson.setMsg("退费记录添加失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }
        return ajaxJson;
    }

    /**
     * 修改一条退费记录
     *
     * @param oaTuifeirecord
     * @return
     */
    @RequestMapping(value = "/editTuifeiRecord", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson editTuifeiRecord(@RequestBody OaTuifeirecord oaTuifeirecord) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean update = iOaTuifeirecordService.updateById(oaTuifeirecord);
        if (update) {
            ajaxJson.setMsg("退费记录修改成功");
        } else {
            ajaxJson.setMsg("退费记录修改失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }
        return ajaxJson;
    }

    /**
     * 批量删除退费记录
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delTuifeirecord", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson delTuifeirecord(String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] idsArr = ids.split(",");
        List<String> idsList = Arrays.asList(idsArr);
        boolean remove = iOaTuifeirecordService.removeByIds(idsList);
        if (remove) {
            ajaxJson.setMsg("删除退费记录成功");
        } else {
            ajaxJson.setMsg("删除退费记录失败");
            ajaxJson.setSuccess(false);
            ajaxJson.setCode("N");
        }

        return ajaxJson;
    }


    /**
     * 分页获取所有的退费记录
     *
     * @param current
     * @param size
     * @return
     */
    @RequestMapping(value = "/getAllTuifeiRecord", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAllTaocantype(@RequestParam(value = "current", defaultValue = "1") long current,
                                     @RequestParam(value = "size", defaultValue = "10") long size,
                                     String kehucompanyname, String qiandanID, String staffName,
                                     String operatetuifeiStart, String operatetuifeiEnd,
                                     String tuifeiDateStart, String tuifeiDateEnd) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<TuifeirecordVo> page = new Page<>(current, size);
        QueryWrapper<Object> queryWrapper = new QueryWrapper<>();
        if (kehucompanyname == null && qiandanID == null && staffName == null
                && operatetuifeiStart == null && operatetuifeiEnd == null &&
                tuifeiDateStart == null && tuifeiDateEnd == null) {
            queryWrapper = null;
        }
        if (kehucompanyname != null) {
            queryWrapper.like("kehu.kehucompanyname", kehucompanyname);
        }
        if (qiandanID != null) {
            queryWrapper.like("tuifeirecord.qiandanID", qiandanID);
        }
        if (staffName != null) {
            queryWrapper.like("staff.staffName", staffName);
        }
        if (operatetuifeiStart != null && operatetuifeiEnd != null) {
            queryWrapper.between("tuifeirecord.operatetuifeiDatetime", operatetuifeiStart, operatetuifeiEnd);
        }
        if (tuifeiDateStart != null && tuifeiDateEnd != null) {
            queryWrapper.between("tuifeirecord.tuifeiDate", tuifeiDateStart, tuifeiDateEnd);
        }
        IPage<TuifeirecordVo> iPage = iOaTuifeirecordService.getAllTuifeirecordInfo(page, queryWrapper);
//        List<TuifeirecordVo> tuifeirecordVoList = iPage.getRecords();
        ajaxJson.setObj(iPage);
        ajaxJson.setMsg("分页获取所有的退费记录");

        return ajaxJson;
    }

    /**
     * 根据id获取一个退费记录
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getOneTuifeiRecordById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getOneTuifeiRecordById(@PathVariable("id") long id) {
        AjaxJson ajaxJson = new AjaxJson();
        TuifeirecordVo tuifeirecordVo = iOaTuifeirecordService.getOneTuifeirecordById(id);
        ajaxJson.setMsg("根据id获取一个退费记录");
        ajaxJson.setObj(tuifeirecordVo);
        return ajaxJson;
    }

}
