package com.xwcloud.cloud.sys.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.ExportExcel;
import com.xwcloud.cloud.common.importExcel;
import com.xwcloud.cloud.model.Form.sys.AssetInfoForm;
import com.xwcloud.cloud.model.Form.sys.beofeiForm;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.Vo.assetAddVO;
import com.xwcloud.cloud.model.Vo.assetsVO;
import com.xwcloud.cloud.model.Vo.dengjiassetsVO;
import com.xwcloud.cloud.model.Vo.zichanImportVo;
import com.xwcloud.cloud.model.entity.*;
import com.xwcloud.cloud.sys.Service.*;
import io.micrometer.core.instrument.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-22
 */
@Controller
@RequestMapping("/sys/pxassets")
@Api(tags = "固定资产管理")
public class AssetManagementController {
    @Autowired
    IPxassetsstyletableService iPxassetsstyletableService;

    @Autowired
    IPxassetstableService iPxassetstableService;

    @Autowired
    IPxassetsaddtableService iPxassetsaddtableService;

    @Autowired
    IPxassetsouttableService iPxassetsouttableService;

    @Autowired
    IPxpowertableService iPxpowertableService;
    @Autowired
    IPxcampustableService iPxcampustableService;
    @Autowired
    IPxstafftableService iPxstafftableService;

    //region 固定资产类别信息


    /**
     * 查询固定资产类别信息
     *
     * @param size
     * @param current
     * @return
     */
    @RequestMapping(value = "/getAllAssetStyle", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有固定资产类别")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true, dataTypeClass = Long.class, dataType = "Long"),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true, dataTypeClass = Long.class, dataType = "Long")
    })
    public AjaxJson getAllAssetStyle(long size, long current, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        String dlstaffName = loginUser.getStaffName();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", qiyeID);
        Page<Pxassetsstyletable> page = new Page(current, size);
        page = (Page<Pxassetsstyletable>) iPxassetsstyletableService.page(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 查询所有固定资产类别
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetAllAssetsStyleList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询所有固定资产类别")
    public AjaxJson GetAllAssetsStyleList(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        String dlstaffName = loginUser.getStaffName();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.qiyeID", qiyeID);
        ajaxJson.setObj(iPxassetstableService.getAllasstestyleList(queryWrapper));
        return ajaxJson;
    }

    /**
     * 添加固定资产类别信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/addAssetStyle", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加固定资产类别信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "assetsName", value = "固定资产类别名称", required = true),
    })
    public AjaxJson addAssetStyle(String assetsName, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        String dlstaffName = loginUser.getStaffName();
        Pxassetsstyletable pxassetsstyletable = new Pxassetsstyletable();
        pxassetsstyletable.setQiyeID(qiyeID);
        pxassetsstyletable.setAssetsName(assetsName);
        ajaxJson.setSuccess(iPxassetsstyletableService.save(pxassetsstyletable));
        return ajaxJson;

    }

    /**
     * 修改固定资产类别信息
     *
     * @param pxassetsstyletable
     * @return
     */
    @RequestMapping(value = "/updateAssetStyle", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改固定资产类别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "修改类别ID", required = true, dataTypeClass = Long.class, dataType = "Long"),
            @ApiImplicitParam(name = "assetsName", value = "固定资产类别名称", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = false),
    })
    public AjaxJson updateAssetStyle(Pxassetsstyletable pxassetsstyletable) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(iPxassetsstyletableService.updateById(pxassetsstyletable));
        return ajaxJson;
    }

    /**
     * 删除固定资产类别
     *
     * @param Id
     * @return
     */
    @RequestMapping(value = "deleteAssetStyle", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除固定资产类别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Id", value = "固定资产类别ID", required = true, dataTypeClass = Long.class, dataType = "Long")
    })

    public AjaxJson deleteAssetStyle(long Id) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", Id);
        List<Pxassetstable> pxassetstableList = iPxassetstableService.list(queryWrapper);
        if (pxassetstableList.size() > 0) {
            ajaxJson.setMsg("该类别已被关联使用，不能删除");
        } else {
            ajaxJson.setSuccess(iPxassetsstyletableService.removeById(Id));
        }
        return ajaxJson;
    }
    //endregion

    //region 固定资产信息

    /**
     * 分页查询固定资产信息
     *
     * @param size
     * @param current
     * @return
     */
    @RequestMapping(value = "/getPagesAssets", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页查询固定资产信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true, dataTypeClass = Long.class, dataType = "Long"),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true, dataTypeClass = Long.class, dataType = "Long"),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false, dataTypeClass = Long.class, dataType = "Long"),
            @ApiImplicitParam(name = "assetsName", value = "固定资产名称", required = false),
            @ApiImplicitParam(name = "leibieID", value = "固定资产类别", required = false)
    })
    public AjaxJson getPagesAssets(long size, long current, long campusID, String assetsName, long leibieID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        Page<assetsVO> page = new Page(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        String dlstaffName = loginUser.getStaffName();
        queryWrapper.eq("a.qiyeID", qiyeID);
        if (campusID != 0) {
            queryWrapper.eq("a.campusID", campusID);
        }
        if (StringUtils.isNotBlank(assetsName)) {
            queryWrapper.like("a.assetsName", assetsName);
        }
        if (leibieID != 0) {
            queryWrapper.eq("a.leibie", leibieID);
        }
        QueryWrapper searchpower = new QueryWrapper();
        searchpower.eq("qiyeID", loginUser.getQiyeID());
        searchpower.eq("staffpostID", loginUser.getStaffPostID());
        searchpower.eq("menuID", 551);
        String lookPower = iPxpowertableService.getOne(searchpower).getDataFanwei();
        if (lookPower.equals("0")) {//个人权限
            searchpower.eq("a.campusID ", 0);
        } else if (lookPower.equals("-1")) {//所在校区权限
            searchpower.eq("a.campusID", loginUser.getCampusID());
        } else if (lookPower.equals("-2")) {//所有校区权限

        } else if (lookPower != "0" && lookPower != "-1" && lookPower != "-2" && lookPower != "") {//指定校区权限
            searchpower.in("a.campusID", lookPower);
        }
        page = (Page<assetsVO>) iPxassetstableService.getassetsPages(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 添加固定资产信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/addAssetInfo", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加固定资产信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true, dataTypeClass = Long.class, dataType = "Long"),
            @ApiImplicitParam(name = "assetsName", value = "固定资产名称", required = true),
            @ApiImplicitParam(name = "leibie", value = "固定资产类别ID", required = true),
            @ApiImplicitParam(name = "guige", value = "固定资产规格", required = true),
            @ApiImplicitParam(name = "num", value = "数量", required = true),
            @ApiImplicitParam(name = "danwei", value = "固定资产数量单位", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true, dataTypeClass = Long.class, dataType = "Long"),
    })
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson addAssetInfo(@RequestBody AssetInfoForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        String dlstaffName = loginUser.getStaffName();
        Pxassetstable pxassetstable = new Pxassetstable();
        pxassetstable.setAssetsName(form.getAssetsName());
        pxassetstable.setCampusID(Long.parseLong(form.getCampusID()));
        pxassetstable.setDanwei(form.getDanwei());
        pxassetstable.setGuige(form.getGuige());
        pxassetstable.setLeibie(form.getLeibie());
        pxassetstable.setNum(form.getNum());
        pxassetstable.setQiyeID(qiyeID);
        iPxassetstableService.save(pxassetstable);
        Pxassetsaddtable pxassetsaddtable = new Pxassetsaddtable();
        pxassetsaddtable.setNum(form.getNum());
        pxassetsaddtable.setAddStaffID(staffID);
        pxassetsaddtable.setQiyeID(qiyeID);
        pxassetsaddtable.setAddTime(new Date());
        pxassetsaddtable.setAssetsID(pxassetstable.getId());
        pxassetsaddtable.setBeizhu(form.getBeizhu());
        iPxassetsaddtableService.save(pxassetsaddtable);
        ajaxJson.setSuccess(true);
        return ajaxJson;
    }

    /**
     * 修改固定资产信息
     *
     * @param pxassetstable
     * @return
     */
    @RequestMapping(value = "/updateAssetInfo", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改固定资产信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Id", value = "固定资产ID", required = true, dataTypeClass = Long.class, dataType = "Long"),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true, dataTypeClass = Long.class, dataType = "Long"),
            @ApiImplicitParam(name = "assetsName", value = "固定资产名称", required = true),
            @ApiImplicitParam(name = "leibie", value = "固定资产类别ID", required = true),
            @ApiImplicitParam(name = "guige", value = "固定资产规格", required = true),
            @ApiImplicitParam(name = "num", value = "数量", required = true),
            @ApiImplicitParam(name = "danwei", value = "固定资产数量单位", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true, dataTypeClass = Long.class, dataType = "Long"),
    })
    public AjaxJson updateAssetInfo(@RequestBody Pxassetstable pxassetstable, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        pxassetstable.setQiyeID(loginUser.getQiyeID());
        ajaxJson.setSuccess(iPxassetstableService.updateById(pxassetstable));
        return ajaxJson;
    }

    /**
     * 删除固定资产信息
     *
     * @param Id
     * @return
     */
    @RequestMapping(value = "/deleteAssetInfo", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除固定资产信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Id", value = "固定资产ID", required = true, dataTypeClass = Long.class, dataType = "Long")
    })
    public AjaxJson deleteAssetInfo(String Id) {
        AjaxJson ajaxJson = new AjaxJson();
        String[] IDs = Id.split(",");
        ajaxJson.setSuccess(iPxassetstableService.removeByIds(Arrays.asList(IDs)));
        return ajaxJson;
    }

    /**
     * 固定资产入库记录
     *
     * @param size
     * @param current
     * @return
     */
    @RequestMapping(value = "/getAssetAddRecords", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "固定资产入库记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true, dataTypeClass = Long.class, dataType = "Long"),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true, dataTypeClass = Long.class, dataType = "Long")
    })
    public AjaxJson getAssetAddRecords(long size, long current, String ID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("b.qiyeID", qiyeID);
        queryWrapper.eq("a.assetsID", ID);
        Page<assetAddVO> page = new Page(current, size);
        page = (Page<assetAddVO>) iPxassetsaddtableService.GetassetsAddPages(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    /**
     * 固定资产出库记录
     *
     * @param size
     * @param current
     * @return
     */
    @RequestMapping(value = "/getAssetOutRecords", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "固定资产出库记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数据条数", example = "10", required = true, dataTypeClass = Long.class, dataType = "Long"),
            @ApiImplicitParam(name = "current", value = "页码", example = "1", required = true, dataTypeClass = Long.class, dataType = "Long")
    })
    public AjaxJson getAssetOutRecords(long size, long current, String ID, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        QueryWrapper queryWrapper = new QueryWrapper("b.qiyeID", qiyeID.toString());
        queryWrapper.eq("a.assetsID", ID);
        Page<assetAddVO> page = new Page(current, size);
        page = (Page<assetAddVO>) iPxassetsouttableService.GetassetsOutPages(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @ApiOperation("导入资产管理下载模板")
    @ResponseBody
    @RequestMapping(value = "ExportzichanMuban", method = RequestMethod.GET)
    public void ExportzichanMuban(HttpServletResponse response, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = Long.valueOf(loginUser.getQiyeID());
        zichanImportVo ImportVo = new zichanImportVo();
        Pxcampustable cam = iPxcampustableService.getOne(new QueryWrapper<Pxcampustable>()
                .eq("qiyeID", loginUser.getQiyeID())
                .last(" limit 1")
        );
        ImportVo.setCampusName(cam.getCampusName());
        ImportVo.setArticleName("联想电脑");
        ImportVo.setLeibie("教学用品");
        ImportVo.setGuige("台式");
        ImportVo.setRukunum(String.valueOf(100));
        ImportVo.setDanwei("台");
        ImportVo.setRukushuoming("说明1111");
        ImportVo.setRukuDate("2021-05-05");
        ImportVo.setJibanren(loginUser.getStaffName());


        List<zichanImportVo> Im = Collections.singletonList(ImportVo);
        List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区(必填)", "名称(必填)", "类别(必填)", "规格(必填)", "入库数量(必填)", "单位(必填)", "入库说明(必填)", "入库时间" +
                        "(必填)", "经办人(必填)"},
                Im,
                new String[]{"campusName", "articleName", "leibie", "guige", "rukunum", "danwei", "rukushuoming", "rukuDate", "jibanren"});
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "资产管理导入模板.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @ApiOperation("导入资产管理")
    @ResponseBody
    @RequestMapping(value = "zichangExcel", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson zichangExcel(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        String errList = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date testDate = null;

        long t1 = System.currentTimeMillis();
        int okCount = 0;
        List<zichanImportVo> list = importExcel.readExcel("", zichanImportVo.class, file);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                zichanImportVo item = list.get(i);
                if (item.getCampusName() == null || item.getCampusName() == "") {
                    errList += "第" + (i + 1) + "行:校区为空,";
                } else if (item.getArticleName() == null || item.getArticleName() == "") {
                    errList += "第" + (i + 1) + "行:名称为空,";
                } else if (item.getLeibie() == null || item.getLeibie() == "") {
                    errList += "第" + (i + 1) + "行:类别为空,";
                } else if (item.getGuige() == null || item.getGuige() == "") {
                    errList += "第" + (i + 1) + "行:规格为空,";
                } else if (item.getRukunum() == null || item.getRukunum() == "") {
                    errList += "第" + (i + 1) + "行:入库数量为空,";
                } else if (item.getDanwei() == null || item.getDanwei() == "") {
                    errList += "第" + (i + 1) + "行:单位为空,";
                } else if (item.getRukushuoming() == null || item.getRukushuoming() == "") {
                    errList += "第" + (i + 1) + "行:入库说明为空,";
                } else if (item.getRukuDate() == null || item.getRukuDate() == "") {
                    errList += "第" + (i + 1) + "行:入库时间为空,";
                } else if (item.getJibanren() == null || item.getJibanren() == "") {
                    errList += "第" + (i + 1) + "行:经办人为空,";
                } else {
                    Pxcampustable cam = iPxcampustableService.getOne(
                            new QueryWrapper<Pxcampustable>()
                                    .eq("campusName", item.getCampusName())
                                    .eq("qiyeID", loginUser.getQiyeID())
                    );

                    Pxstafftable staff = iPxstafftableService.getOne(new QueryWrapper<Pxstafftable>()
                            .eq("qiyeID", loginUser.getQiyeID())
                            .eq("staffName", item.getJibanren())
                    );
                    if (cam == null) {
                        errList += "第" + (i + 1) + "行:系统找不到此校区,";
                    } else if (staff == null) {
                        errList += "第" + (i + 1) + "行:系统找不到经办人,";
                    } else {
                        Long typeid = null;
                        Long suppid = null;
                        Pxassetsstyletable typetab = iPxassetsstyletableService.getOne(new QueryWrapper<Pxassetsstyletable>()
                                .eq("assetsName", item.getArticleName())
                                .eq("qiyeID", loginUser.getQiyeID())
                        );
                        Pxassetstable supptab = iPxassetstableService.getOne(new QueryWrapper<Pxassetstable>()
                                .eq("assetsName", item.getArticleName())
                                .eq("qiyeID", loginUser.getQiyeID())
                        );

                        if (typetab == null) {
                            Pxassetsstyletable add = new Pxassetsstyletable();
                            add
                                    .setAssetsName(item.getLeibie())
                                    .setQiyeID(loginUser.getQiyeID());
                            iPxassetsstyletableService.save(add);
                            typeid = add.getId();
                        } else {
                            typeid = typetab.getId();
                        }

                        BigDecimal supnum = new BigDecimal(item.getRukunum());
                        if (supptab == null) {
                            Pxassetstable add = new Pxassetstable();
                            add
                                    .setCampusID(cam.getId())
                                    .setAssetsName(item.getArticleName())
                                    .setLeibie(String.valueOf(typeid))
                                    .setGuige(item.getGuige())
                                    .setDanwei(item.getDanwei())
                                    .setNum(supnum)
                                    .setQiyeID(loginUser.getQiyeID());

                            iPxassetstableService.save(add);
                            suppid = add.getId();
                        } else {
                            suppid = supptab.getId();
                            supptab.setNum(supptab.getNum().add(supnum));
                        }

                        Pxassetsaddtable tw = new Pxassetsaddtable();
                        Date rDT = null;
                        try {
                            rDT = sdf.parse(item.getRukuDate());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        tw
                                .setAssetsID(suppid)
                                .setNum(supnum)
                                .setBeizhu(item.getRukushuoming())
                                .setAddStaffID(loginUser.getStaffID())
                                .setAddTime(rDT)
                                .setQiyeID(loginUser.getQiyeID());
                        iPxassetsaddtableService.save(tw);
                        okCount += 1;

                    }
                }
            }
        } else {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ajaxJson.setMsg("空数据表！");
            ajaxJson.setCode("N");
            return ajaxJson;
        }

        if (errList.length() > 0) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg(errList);
            return ajaxJson;
        }

        ajaxJson.setMsg("成功导入" + okCount + "条");
        return ajaxJson;
    }


    @RequestMapping(value = "/exportgudingzichang", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出固定资产信息")
    public void exportgudingzichang(HttpServletResponse response,
                                    @RequestParam(required = false) String campusID,
                                    @RequestParam(required = true) Integer type,
                                    HttpServletRequest request
    ) {
        QueryWrapper queryWrapper = new QueryWrapper();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        queryWrapper.eq("a.qiyeID", loginUser.getQiyeID());
        if (type == 1) {//固定资产用品导出
            if (StringUtils.isNotBlank(campusID)) {
                queryWrapper.eq("a.campusID", campusID);
            }
            List<assetsVO> assetsVOList = iPxassetstableService.GetAllAssetsList(queryWrapper);
            List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "名称 ", " 类别 ", " 规格 ", " 库存数量 ", " 单位 "},
                    assetsVOList,
                    new String[]{"campusName", "assetsName", "styleName", "guige", "num", "danwei"});

            try {
                ExportExcel.exportExcel(response, list, "Sheet1", "固定资产导出.xls", 15);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type == 2) {//登记记录导出
            if (StringUtils.isNotBlank(campusID)) {
                queryWrapper.eq("b.campusID", campusID);
            }
            List<dengjiassetsVO> pxoutjlList = iPxassetstableService.GetListAssetsDengjiList(queryWrapper);
            List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "名称", "类别", "规格", "登记数量", "单位", "登记说明", "登记时间 ", "经办人"},
                    pxoutjlList,
                    new String[]{"campusName", "assetsName", "styleName", "guige", "num", "danwei", "beizhu", "addTime", "staffName"});

            try {
                ExportExcel.exportExcel(response, list, "Sheet1", "登记记录导出.xls", 15);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type == 3) {//报废记录导出
            if (StringUtils.isNotBlank(campusID)) {
                queryWrapper.eq("b.campusID", campusID);

            }
            List<dengjiassetsVO> baofeiList = iPxassetstableService.GetListAssetsBaofeiList(queryWrapper);
            List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "名称", "类别", "规格", "报废数量", "单位", "报废说明", "报废时间 ", "经办人"},
                    baofeiList,
                    new String[]{"campusName", "assetsName", "styleName", "guige", "num", "danwei", "beizhu", "addTime", "staffName"});

            try {
                ExportExcel.exportExcel(response, list, "Sheet1", "报废记录导出.xls", 15);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 保存固定资产报废信息
     * @param form
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/savezichanBaoFeiInfo", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存固定资产报废信息")
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson savezichanBaoFeiInfo(@RequestBody beofeiForm form,HttpServletRequest request) throws ParseException {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();
        Long staffID = loginUser.getStaffID();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Pxassetsouttable pxassetsouttable = new Pxassetsouttable();
        pxassetsouttable.setAddStaffID(Long.valueOf(form.getOutStaffId()));
        pxassetsouttable.setAssetsID(Long.valueOf(form.getId()));
        pxassetsouttable.setBeizhu(form.getBeizhu());
        pxassetsouttable.setAddTime(dateFormat.parse(form.getOutDate()));
        pxassetsouttable.setNum(form.getOutNum());
        pxassetsouttable.setQiyeID(qiyeID);
        ajaxJson.setSuccess(iPxassetsouttableService.save(pxassetsouttable));
        Pxassetstable pxassetstable = iPxassetstableService.getById(form.getId());
        pxassetstable.setNum(pxassetstable.getNum().subtract(form.getOutNum()));
        iPxassetstableService.updateById(pxassetstable);
        return ajaxJson;
    }
    //endregion

}
