package com.xwcloud.cloud.homeschool.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.*;
import com.xwcloud.cloud.homeschool.Service.*;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.*;
import com.xwcloud.cloud.overall.LogUtils;
import com.xwcloud.cloud.overall.Utils.OAuthUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/homeschool/pxbooks")
@Api(tags = "图书借阅")
public class PxbooksController {

    @Autowired
    IPxbookstableService iPxbookstableService;
    @Autowired
    IPxbooksaddtableService iPxbooksaddtableService; // 入库
    @Autowired
    IPxbooksouttableService iPxbooksouttableService; // 出库
    @Autowired
    IPxbooksborrowtableService iPxbooksborrowtableService; // 借阅
    @Autowired
    IPxbooksreturntableService iPxbooksreturntableService; // 归还


    @Autowired
    LogUtils logUtils;

    @RequestMapping(value = "/getPxbooksPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取图书列表分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = true),
            @ApiImplicitParam(name = "current", value = "页码", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "bookName", value = "图书名称", required = false),
            @ApiImplicitParam(name = "ISBN", value = "ISBN", required = false),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson getPxbooksPage(HttpServletRequest request,
                                   @RequestParam(required = false, defaultValue = "10") long size,
                                   @RequestParam(required = false, defaultValue = "1") long current,
                                   @RequestParam(required = false) String campusID,
                                   @RequestParam(required = false) String bookName,
                                   @RequestParam(required = false) String ISBN
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("books.qiyeID", qiyeID);
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("books.campusID", campusID);
        }
        if (StringUtils.isNotBlank(bookName)) {
            queryWrapper.like("books.booksName", bookName);
        }
        if (StringUtils.isNotBlank(ISBN)) {
            queryWrapper.like("books.ISBN", ISBN);
        }
        queryWrapper.orderByDesc("books.id");
        Page<PxbookstableVo> page = new Page<>(current, size);
        page = iPxbookstableService.getPage(page, queryWrapper);
        ajaxJson.setObj(page);

        return ajaxJson;
    }

    @RequestMapping(value = "/getPxbook", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取图书")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "图书ID", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson getPxbook(HttpServletRequest request,
                              String id
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        queryWrapper.eq("qiyeID", qiyeID);
        Pxbookstable pxbookstable = iPxbookstableService.getOne(queryWrapper);
        if (pxbookstable != null) {
            ajaxJson.setObj(pxbookstable);
        } else {
            ajaxJson.setSuccess(false);
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/editBook", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改图书")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "图书ID", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区", required = true),
            @ApiImplicitParam(name = "booksName", value = "图书名称", required = true),
            @ApiImplicitParam(name = "allnum", value = "总本数", required = true),
            @ApiImplicitParam(name = "cunnum", value = "剩余数", required = true),
            @ApiImplicitParam(name = "iSdaance", value = "是否带答案", required = true),
            @ApiImplicitParam(name = "iSdisc", value = "是否带光盘", required = true),
            @ApiImplicitParam(name = "author", value = "作者", required = true),
            @ApiImplicitParam(name = "press", value = "出版社", required = true),
            @ApiImplicitParam(name = "chubanDate", value = "出版日期", required = true),
            @ApiImplicitParam(name = "banci", value = "版次", required = true),
            @ApiImplicitParam(name = "ISBN", value = "isbn", required = true),
            @ApiImplicitParam(name = "图书存放位置编码", value = "bookLocationCode", required = true),
            @ApiImplicitParam(name = "说明", value = "shuoming", required = true)
    })
    public AjaxJson editBook(HttpServletRequest request,
                             Pxbookstable pxbookstable
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        pxbookstable.setQiyeID(qiyeID);
        ajaxJson.setObj(iPxbookstableService.updateById(pxbookstable));

        return ajaxJson;
    }

    @RequestMapping(value = "/delBook", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除图书")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "IDs", value = "删除图书", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson delBook(HttpServletRequest request,
                            String IDs
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        List<Pxbookstable> list = iPxbookstableService.list(queryWrapper);
        if (list == null || list.size() == 0) {
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        String[] strings = IDs.split(",");
        ajaxJson.setSuccess(iPxbookstableService.removeByIds(Arrays.asList(strings)));
        return ajaxJson;
    }

    @RequestMapping(value = "/warehousing", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class})
    @ApiOperation(value = "入库")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "campusID", value = "校区", required = true),
            @ApiImplicitParam(name = "booksName", value = "图书名称", required = true),
            @ApiImplicitParam(name = "allnum", value = "总本数", required = true),
            @ApiImplicitParam(name = "cunnum", value = "剩余数", required = true),
            @ApiImplicitParam(name = "iSdaance", value = "是否带答案", required = true),
            @ApiImplicitParam(name = "iSdisc", value = "是否带光盘", required = true),
            @ApiImplicitParam(name = "author", value = "作者", required = true),
            @ApiImplicitParam(name = "press", value = "出版社", required = true),
            @ApiImplicitParam(name = "chubanDate", value = "出版日期", required = true),
            @ApiImplicitParam(name = "banci", value = "版次", required = true),
            @ApiImplicitParam(name = "ISBN", value = "isbn", required = true),
            @ApiImplicitParam(name = "bookLocationCode", value = "图书放置编码", required = true),
            @ApiImplicitParam(name = "shuoming", value = "说明", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
            @ApiImplicitParam(name = "addstaffID", value = "入库人", required = true),
            @ApiImplicitParam(name = "addDate", value = "入库时间", required = true),
    })
    public AjaxJson warehousing(HttpServletRequest request,
                                Pxbookstable pxbookstable,
                                String addstaffID, String addDate

    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        if (pxbookstable.getId() != null) {
            Pxbookstable book = iPxbookstableService.getById(pxbookstable.getId());
            int allnum = book.getAllnum() + pxbookstable.getAllnum();
            book.setAllnum(allnum);
            iPxbookstableService.updateById(book);
        } else {
            pxbookstable.setChubanDate(new Date());
            pxbookstable.setCunnum(pxbookstable.getAllnum());
            pxbookstable.setQiyeID(loginUser.getQiyeID());
            iPxbookstableService.save(pxbookstable);
        }
        // 写入库记录表
        Pxbooksaddtable pxbooksaddtable = new Pxbooksaddtable();
        pxbooksaddtable.setBooksID(pxbookstable.getId());
        pxbooksaddtable.setAddnum(pxbookstable.getAllnum());// 入库数量填的是总数量
        pxbooksaddtable.setAddstaffID(addstaffID);
        pxbooksaddtable.setAddDate(DateUtil.toDate(addDate, "yyyy-MM-dd"));
        pxbooksaddtable.setBeizhu(pxbookstable.getShuoming());
        pxbooksaddtable.setQiyeID(loginUser.getQiyeID());
        iPxbooksaddtableService.save(pxbooksaddtable);

        ajaxJson.setSuccess(true);

        return ajaxJson;
    }


    @RequestMapping(value = "/outOfStock", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class})
    @ApiOperation(value = "出库")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookID", value = "图书ID", required = true),
            @ApiImplicitParam(name = "outNum", value = "出库数量", required = true),
            @ApiImplicitParam(name = "beizhu", value = "备注", required = false),
            @ApiImplicitParam(name = "outstaffID", value = "出库人ID", required = true),
            @ApiImplicitParam(name = "outDate", value = "出库时间", required = true)
    })
    public AjaxJson outOfStock(HttpServletRequest request,
                               String bookID,
                               int outNum,
                               @RequestParam(required = false) String beizhu,
                               String outstaffID,
                               String outDate
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", bookID);
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        Pxbookstable pxbookstable = iPxbookstableService.getOne(queryWrapper);
        // 减总数量
        int total = pxbookstable.getAllnum() - outNum;
        pxbookstable.setAllnum(total);
        // 减课借本数
        int availableNum = pxbookstable.getCunnum() - outNum;
        pxbookstable.setCunnum(availableNum);
        if (iPxbookstableService.updateById(pxbookstable)) {
            Pxbooksouttable pxbooksouttable = new Pxbooksouttable();
            pxbooksouttable.setBooksID(pxbookstable.getId());
            pxbooksouttable.setBeizhu(beizhu);
            pxbooksouttable.setOutDate(DateUtil.toDate(outDate, "yyyy-MM-dd"));
            pxbooksouttable.setOutstaffID(outstaffID);
            pxbooksouttable.setOutnum(outNum);
            pxbooksouttable.setQiyeID(loginUser.getQiyeID());
            iPxbooksouttableService.save(pxbooksouttable);
        }
        ajaxJson.setSuccess(true);

        return ajaxJson;
    }

    @RequestMapping(value = "/inboundRecordPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "入库记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false)
    })
    public AjaxJson inboundRecordPage(HttpServletRequest request,
                                      @RequestParam(required = false, defaultValue = "10") long size,
                                      @RequestParam(required = false, defaultValue = "1") long current
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<PxbooksaddtableVo> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("booksadd.qiyeID", loginUser.getQiyeID());
        page = iPxbooksaddtableService.getPage(page, null);
        ajaxJson.setObj(page);

        return ajaxJson;
    }

    @RequestMapping(value = "/outboundRecordsPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "出库记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false)
    })
    public AjaxJson outboundRecordsPage(HttpServletRequest request,
                                        @RequestParam(required = false, defaultValue = "10") long size,
                                        @RequestParam(required = false, defaultValue = "1") long current
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<PxbooksouttableVo> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("booksout.qiyeID", loginUser.getQiyeID());
        page = iPxbooksouttableService.getPage(page, queryWrapper);
        ajaxJson.setObj(page);

        return ajaxJson;
    }


    @RequestMapping(value = "/getBorrowingRecordsPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "借书记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = true),
            @ApiImplicitParam(name = "current", value = "页码", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "bookName", value = "图书名称", required = false),
            @ApiImplicitParam(name = "role", value = "角色", required = false),
            @ApiImplicitParam(name = "returnDate", value = "还书时间", required = false),
            @ApiImplicitParam(name = "dostaffName", value = "操作人名称", required = false),
            @ApiImplicitParam(name = "doDate", value = "操作时间", required = false)
    })
    public AjaxJson getBorrowingRecordsPage(HttpServletRequest request,
                                            @RequestParam(required = false, defaultValue = "10") long size,
                                            @RequestParam(required = false, defaultValue = "1") long current,
                                            @RequestParam(required = false) String campusID,
                                            @RequestParam(required = false) String bookName,
                                            @RequestParam(required = false) String role,
                                            @RequestParam(required = false) String returnstartDate,
                                            @RequestParam(required = false) String returnendDate,
                                            @RequestParam(required = false) String dostaffName,
                                            @RequestParam(required = false) String dostartDate,
                                            @RequestParam(required = false) String doendDate
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<PxbooksborrowtableVo> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("borrow.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("campus.id", campusID);
        }
        if (StringUtils.isNotBlank(bookName)) {
            queryWrapper.like("books.booksName", bookName);
        }
        if (StringUtils.isNotBlank(dostaffName)) {
            queryWrapper.like("staff.staffName", dostaffName);
        }
        if (StringUtils.isNotBlank(role)) {
            queryWrapper.eq("borrow.role", role);
        }
        if (StringUtils.isNotBlank(returnstartDate)) {
            queryWrapper.ge("borrow.endDate", returnstartDate);
        }
        if (StringUtils.isNotBlank(returnendDate)) {
            queryWrapper.le("borrow.endDate", returnendDate);
        }
        if (StringUtils.isNotBlank(dostartDate)) {
            queryWrapper.ge("borrow.doDate", dostartDate);
        }
        if (StringUtils.isNotBlank(doendDate)) {
            queryWrapper.le("borrow.doDate", doendDate);
        }
        queryWrapper.orderByDesc("borrow.doDate");
        page = iPxbooksborrowtableService.getPage(page, queryWrapper);
        ajaxJson.setObj(page);

        return ajaxJson;
    }


    @RequestMapping(value = "/borrowBooks", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class})
    @ApiOperation(value = "借书")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "booksID", value = "图书ID", required = true),
            @ApiImplicitParam(name = "people", value = "借书人ID", required = true),
            @ApiImplicitParam(name = "role", value = "角色", required = true),
            @ApiImplicitParam(name = "borrownum", value = "借阅数量", required = true),
            @ApiImplicitParam(name = "endDate", value = "还书时间", required = true),
            @ApiImplicitParam(name = "dostaffID", value = "操作人ID", required = true),
            @ApiImplicitParam(name = "doDate", value = "操作时间", required = true),
            @ApiImplicitParam(name = "beizhu", value = "备注", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson borrowBooks(HttpServletRequest request,
                                Pxbooksborrowtable pxbooksborrowtable
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        // 添加借书记录
        pxbooksborrowtable.setQiyeID(loginUser.getQiyeID());
        if (iPxbooksborrowtableService.save(pxbooksborrowtable)) {
            // 减去图书剩余数量
            Pxbookstable book = iPxbookstableService.getById(pxbooksborrowtable.getBooksID());
            int surplusNum = book.getCunnum() - pxbooksborrowtable.getBorrownum();
            book.setCunnum(surplusNum);
            iPxbookstableService.updateById(book);
        }
        ajaxJson.setSuccess(true);
        return ajaxJson;
    }

    @RequestMapping(value = "/returnBook", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class})
    @ApiOperation(value = "还书")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "booksID", value = "图书ID", required = true),
            @ApiImplicitParam(name = "people", value = "借书人ID", required = true),
            @ApiImplicitParam(name = "role", value = "角色", required = true),
            @ApiImplicitParam(name = "returnnum", value = "归还数量", required = true),
            @ApiImplicitParam(name = "endDate", value = "还书时间", required = true),
            @ApiImplicitParam(name = "dostaffID", value = "操作人ID", required = true),
            @ApiImplicitParam(name = "doDate", value = "操作时间", required = true),
            @ApiImplicitParam(name = "beizhu", value = "备注", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson returnBook(HttpServletRequest request,
                               String jieshuID,
                               Integer guihuanNum,
                               String shuoming,
                               String guihuansj,
                               Long jingbanrenID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Pxbooksborrowtable pxbooksborrowtable = iPxbooksborrowtableService.getById(jieshuID);
        Pxbooksreturntable pxbooksreturntable = new Pxbooksreturntable();
        pxbooksreturntable.setPeople(pxbooksborrowtable.getPeople());
        pxbooksreturntable.setRole(pxbooksborrowtable.getRole());
        pxbooksreturntable.setReturnnum(guihuanNum);

        pxbooksreturntable.setReturnDate(DateUtil.toDate(guihuansj, "yyyy-MM-dd"));
        pxbooksreturntable.setDostaffID(jingbanrenID);
        pxbooksreturntable.setDoDate(new Date());
        pxbooksreturntable.setBeizhu(shuoming);
        pxbooksreturntable.setBooksID(pxbooksborrowtable.getBooksID());
        pxbooksreturntable.setQiyeID(loginUser.getQiyeID());
        // 添加还书记录
        if (iPxbooksreturntableService.save(pxbooksreturntable)) {
            /* 增加剩余数量 */
            Pxbookstable book = iPxbookstableService.getById(pxbooksreturntable.getBooksID());
            int surplusNum = book.getCunnum() + pxbooksreturntable.getReturnnum();
            book.setCunnum(surplusNum);
            iPxbookstableService.updateById(book);
        }
        ajaxJson.setSuccess(true);

        return ajaxJson;
    }

    @RequestMapping(value = "/borrowingRecordsPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "还书记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "页面大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false)
    })
    public AjaxJson borrowingRecordsPage(HttpServletRequest request,
                                         @RequestParam(required = false, defaultValue = "10") long size,
                                         @RequestParam(required = false, defaultValue = "1") long current,
                                         String jieshuID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<PxbooksreturntableVo> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("returnbook.qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("returnbook.booksID", jieshuID);
        page = iPxbooksreturntableService.getPage(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/exportBooks", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出图书")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "exportType", value = "1.图书记录导出;2.借阅记录导出;3.归还记录导出", required = true),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = true),
            @ApiImplicitParam(name = "startDoDate", value = "操作日期开始", required = false),
            @ApiImplicitParam(name = "endDoDate", value = "操作日期结束", required = false)
    })
    public void exportBooks(HttpServletRequest request, HttpServletResponse response,
                            int exportType, // 1.图书记录导出;2.借阅记录导出;3.归还记录导出
                            @RequestParam(required = false) String campusID,
                            @RequestParam(required = false) String startDoDate,// 开始时间
                            @RequestParam(required = false) String endDoDate// 结束时间
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("books.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("books.campusID", campusID);
        }
        if (StringUtils.isNotBlank(startDoDate) && exportType != 1) {
            queryWrapper.ge("borrow.doDate", startDoDate);
        }
        if (StringUtils.isNotBlank(endDoDate) && exportType != 1) {
            queryWrapper.le("borrow.doDate", endDoDate);
        }
        switch (exportType) {
            case 1: // 图书记录导出
                List<PxbookstableVo> pxbookstableVoList = iPxbookstableService.getJoinList(queryWrapper);
                List<List<Object>> list = ExportExcel.formatDataToList(new String[]{"校区", "图书名称", "总数", "库存", "作者",
                                "出版社", "出版时间", "版次", "ISBN", "是否带答案", "是否带光盘"},
                        pxbookstableVoList,
                        new String[]{"campusName", "booksName", "allnum", "cunnum", "author", "press", "chubanDate-D",
                                "banci", "ISBN", "iSdaance", "iSdisc"});

                try {
                    ExportExcel.exportExcel(response, list, "图书管理", "图书管理导出.xls", 15);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:// 借阅记录导出
                List<PxbooksborrowtableVo> pxbooksborrowtableVos = iPxbooksborrowtableService.getJoinList(queryWrapper);
                List<List<Object>> borrowlist = ExportExcel.formatDataToList(new String[]{"校区", "图书名称", "借阅数", "借阅人", "角色",
                                "归还时间", "说明", "经办人", "经办时间", "归还数"},
                        pxbooksborrowtableVos,
                        new String[]{"campusName", "booksName", "borrownum", "peopleName", "role", "endDate-DT", "beizhu",
                                "dostaffName", "doDate-DT", "returnNum"});
                try {
                    ExportExcel.exportExcel(response, borrowlist, "借阅记录", "借阅记录导出.xls", 15);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 3:// 归还记录导出
                List<PxbooksreturntableVo> pxbooksreturntableVoList = iPxbooksreturntableService.getJoinList(queryWrapper);
                List<List<Object>> returnlist = ExportExcel.formatDataToList(new String[]{"校区", "图书名称", "归还数", "借阅人", "角色",
                                "归还时间", "说明", "经办人", "经办时间"},
                        pxbooksreturntableVoList,
                        new String[]{"campusName", "booksName", "returnnum", "peopleName", "role", "returnDate-DT", "beizhu",
                                "dostaffName", "doDate-DT"});
                try {
                    ExportExcel.exportExcel(response, returnlist, "归还记录", "归还记录导出.xls", 15);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    @RequestMapping(value = "/importBooks", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "导入图书")
    @ApiImplicitParams({
    })
    public AjaxJson importBooks(HttpServletRequest request
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");

        try {
            List<PxbookstableVo> list = ExcelUtils.importExcel(PxbookstableVo.class, new String[]{"campusName", "booksName",
                    "allnum-I", "iSdaance-B", "iSdisc-B", "author", "press", "chubanDate-D", "banci", "isbn", "shuoming"
            }, "C:\\Users\\xw3q.com\\Desktop\\图书导入-模板.xls", 11);
            /* 映射校区ID */
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.ne("isOpen", 2);
            List<Pxcampustable> campusList = iPxbookstableService.getCampusList(null);
            List<Pxbookstable> pxbookstables = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                for (Pxcampustable campus : campusList) {
                    if (list.get(i).getCampusName().equals(campus.getCampusName())) {
                        list.get(i).setCampusID(campus.getId());
                    }
                }
                Pxbookstable pxbookstable = list.get(i);
                pxbookstable.setCunnum(pxbookstable.getAllnum());
                pxbookstables.add(pxbookstable);
                list.get(i).setQiyeID(loginUser.getQiyeID());
            }
            iPxbookstableService.saveBatch(pxbookstables);
            ajaxJson.setSuccess(true);
        } catch (Exception exception) {
            exception.printStackTrace();
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("导入Excel数据错误!");
        }

        return ajaxJson;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    // 权限认证
    // @PreAuthorize("hasPermission('/admin/userList','sys:user:info')")
    //@PreAuthorize("hasAnyRole('bbb')")
    //@PreAuthorize("hasPermission('/admin/userList','sys:user:info')")
    public AjaxJson Test(HttpServletRequest request, Principal user, Authentication authentication) {
        Map<String, Object> tokenInfo = OAuthUtils.getJwtTokenInfo(authentication, "xw_key");
        List<Map<String, Object>> mapList = iPxbooksaddtableService.listMaps();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson json = new AjaxJson();
        json.setMsg("测试成功");
        json.setObj(loginUser);
        return json;
    }


    /**
     * 图书信息导入
     *
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/readExcel", method = RequestMethod.POST)
    public AjaxJson readExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        List<String> errlist = new ArrayList<>();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long t1 = System.currentTimeMillis();
        //excelDataTestVO：对应excel表格数据每一列的数据
        List<excelDataTestVO> list = importExcel.readExcel("", excelDataTestVO.class, file);
        for (excelDataTestVO item : list) {
            //判断非空行
            if (!StringUtils.isNotBlank(item.getCampusName())) {
                errlist.add("校区为空");
            } else if (!StringUtils.isNotBlank(item.getBookName())) {
                errlist.add("图书名称为空");
            } else if (!StringUtils.isNotBlank(item.getBookNumber())) {
                errlist.add("数量为空");
            } else if (!StringUtils.isNotBlank(item.getIshaveAnser())) {
                errlist.add("是否带答案册为空");
            } else if (!StringUtils.isNotBlank(item.getIshaveguanpan())) {
                errlist.add("是否带光盘为空");
            } else {
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("campusName", item.getCampusName());
                queryWrapper.eq("qiyeID", loginUser.getQiyeID());
                Pxcampustable campustab = iPxbookstableService.getCampusList(queryWrapper).get(0);
                if (campustab == null) {
                    errlist.add("系统找不到此校区");
                } else {
                    //正常数据全部录入
                    QueryWrapper queryWrapper1 = new QueryWrapper();
                    queryWrapper1.eq("booksName", item.getBookName());
                    queryWrapper1.eq("qiyeID", loginUser.getQiyeID());
                    Pxbookstable supptab = iPxbookstableService.getOne(queryWrapper1);

                    if (supptab == null)//判断是否存在，不存在则添加
                    {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                        supptab = new Pxbookstable();

                        supptab.setCampusID(campustab.getId());
                        supptab.setBooksName(item.getBookName());
                        supptab.setAllnum(Integer.valueOf(item.getBookNumber()));
                        supptab.setCunnum(Integer.valueOf(item.getBookNumber()));
                        supptab.setISdaance(item.getIshaveAnser() == "是" ? true : false);
                        supptab.setISdisc(item.getIshaveguanpan() == "是" ? true : false);
                        supptab.setQiyeID(loginUser.getQiyeID());
                        if (StringUtils.isNotEmpty(item.getAuther())) {
                            supptab.setAuthor(item.getAuther());
                        }
                        if (StringUtils.isNotBlank(item.getChubanshe())) {
                            supptab.setPress(item.getChubanshe());
                        }
                        if (StringUtils.isNotBlank(item.getChubanDate())) {
                            try {
                                supptab.setChubanDate(simpleDateFormat.parse(item.getChubanDate()));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        if (StringUtils.isNotEmpty(item.getBanci())) {
                            supptab.setBanci(item.getBanci());
                        }
                        if (StringUtils.isNotBlank(item.getISBN())) {
                            supptab.setIsbn(item.getISBN());
                        }
                        iPxbookstableService.save(supptab);
                    } else {
                        supptab.setAllnum(supptab.getAllnum() + Integer.valueOf(item.getBookNumber()));//加可存数量
                        supptab.setCunnum(supptab.getCunnum() + Integer.valueOf(item.getBookNumber()));//加可存数量
                        iPxbookstableService.updateById(supptab);
                    }
                    Pxbooksaddtable rutab = new Pxbooksaddtable();
                    rutab.setBooksID(supptab.getId());
                    rutab.setAddnum(Integer.valueOf(item.getBookNumber()));
                    rutab.setAddDate(new Date());
                    rutab.setAddstaffID(loginUser.getStaffID() + "");
                    rutab.setBeizhu(item.getBeizhu());
                    rutab.setQiyeID(loginUser.getQiyeID());
                    iPxbooksaddtableService.save(rutab);
                }

            }
        }
        ajaxJson.setSuccess(true);
        return ajaxJson;
    }

    @ResponseBody
    @RequestMapping(value = "/tushuInfoMuban", method = RequestMethod.GET)
    public void tushuInfoMuban(HttpServletResponse response, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");

        String[] headlist = {"校区", "名称", "总数量", "是否带答案侧", "是否带光盘", "作者 （选填）", "出版社（选填）", "出版时间 （选填）", "版次（选填）", "ISBN（选填）", "备注（选填）"};
        List<String> HD = Arrays.asList(headlist);
        List<String> titleList = new ArrayList<String>(); //合并的列表

        String[] shujulist = {"campusName", "bookName", "bookNumber", "ishaveAnser", "ishaveguanpan", "auther", "chubanshe", "chubanDate", "banci", "ISBN", "beizhu"};
        titleList.addAll(HD);
        List<excelDataTestVO> listx = new ArrayList<>();
        excelDataTestVO excelDataTestVO = new excelDataTestVO();
        excelDataTestVO.setAuther("金庸");
        excelDataTestVO.setBanci("1");
        excelDataTestVO.setBeizhu("备注信息");
        excelDataTestVO.setBookName("射雕英雄传");
        excelDataTestVO.setBookNumber("5");
        excelDataTestVO.setCampusName("桃花岛校区");
        excelDataTestVO.setChubanDate("2018/12/6");
        excelDataTestVO.setChubanshe("人民出版社");
        excelDataTestVO.setISBN("8888888888");
        excelDataTestVO.setIshaveAnser("否");
        excelDataTestVO.setIshaveguanpan("是");
        listx.add(excelDataTestVO);
        String[] newArr = titleList.toArray(new String[titleList.size()]);

        List<List<Object>> list = ExportExcel.formatDataToList(newArr, listx, shujulist);
        try {
            ExportExcel.exportExcel(response, list, "Sheet1", "工资导入模板.xls", 15);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
