package com.xwcloud.cloud.homeschool.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.homeschool.Service.IPxstutableService;
import com.xwcloud.cloud.homeschool.Service.IPxwxusertableService;
import com.xwcloud.cloud.model.Sso.LoginUser;

import com.xwcloud.cloud.model.Vo.PxwxusertableVo;
import com.xwcloud.cloud.model.entity.Pxstutable;
import com.xwcloud.cloud.model.entity.Pxwxusertable;
import com.xwcloud.cloud.overall.LogUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/homeschool/pxstuweixin")
@Api(tags = "学员微信账号")
public class PxstuweixinController {

    @Autowired
    IPxwxusertableService iPxwxusertableService;

    @Autowired
    LogUtils logUtils;

    @Autowired
    IPxstutableService iPxstutableService;

    @RequestMapping(value = "/GetStuweixinPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取学员微信配置列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = false),
            @ApiImplicitParam(name = "current", value = "页码", required = false),
            @ApiImplicitParam(name = "campusID", value = "校区ID", required = false),
            @ApiImplicitParam(name = "xuehao", value = "学号", required = false),
            @ApiImplicitParam(name = "stuName", value = "学生名称", required = false),
            @ApiImplicitParam(name = "banzhurenName", value = "班主任名称", required = false),
            @ApiImplicitParam(name = "tel", value = "电话", required = false),
    })
    public AjaxJson GetStuweixinPages(HttpServletRequest request,
                                      @RequestParam(required = false, defaultValue = "10") long size,
                                      @RequestParam(required = false, defaultValue = "1") long current,
                                      @RequestParam(required = false) String campusID,
                                      @RequestParam(required = false) String xuehao,
                                      @RequestParam(required = false) String stuName,
                                      @RequestParam(required = false) String banzhurenName,
                                      @RequestParam(required = false) String tel
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<PxwxusertableVo> page = new Page<>(current, size);
        QueryWrapper<PxwxusertableVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("b.qiyeID", loginUser.getQiyeID());
        if (StringUtils.isNotBlank(campusID)) {
            queryWrapper.eq("b.campusID", campusID);
        }
        if (StringUtils.isNotBlank(xuehao)) {
            queryWrapper.like("b.zidingyiStuID", xuehao).or(a -> a.eq("b.id", xuehao));
        }
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("b.stuName", stuName);
        }
        if (StringUtils.isNotBlank(banzhurenName)) {
            queryWrapper.like("c.staffName", banzhurenName);
        }
        if (StringUtils.isNotBlank(tel)) {
            queryWrapper.like("a.tel", tel);
        }
        page = iPxwxusertableService.getPage(page, queryWrapper);
        ajaxJson.setObj(page);

        return ajaxJson;
    }

    @RequestMapping(value = "/editStuWxInfo", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "修改学员微信配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "图书ID", required = true),
            @ApiImplicitParam(name = "tel", value = "电话", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson editStuWxInfo(HttpServletRequest request,
                                  long id,
                                  String tel
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
            Pxstutable pxstutable = iPxwxusertableService.getStuById(id, loginUser.getQiyeID());
            String oldTel = pxstutable.getParentTel();
            iPxwxusertableService.UpdateStuParentTel(id, tel);
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("tel", oldTel);
            queryWrapper.eq("qiyeID", loginUser.getQiyeID());
            List<Pxwxusertable> pxwxusertable = iPxwxusertableService.list(queryWrapper);
            for (Pxwxusertable item : pxwxusertable) {
                item.setTel(tel);
                iPxwxusertableService.updateById(item);
            }
            ajaxJson.setSuccess(true);
            return ajaxJson;
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }

    }

    @RequestMapping(value = "/freeze", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "冻结")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学生ID", required = true)
    })
    public AjaxJson freeze(HttpServletRequest request,
                           long stuID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Pxstutable stu = iPxwxusertableService.getStuById(stuID, loginUser.getQiyeID());
        stu.setActivity(1);
        ajaxJson.setSuccess(iPxstutableService.updateById(stu));

        return ajaxJson;
    }

    @RequestMapping(value = "/thaw", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "解冻")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学生ID", required = true)
    })
    public AjaxJson thaw(HttpServletRequest request,
                         long stuID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Pxstutable stu = iPxwxusertableService.getStuById(stuID, loginUser.getQiyeID());
        stu.setActivity(2);
        ajaxJson.setSuccess(iPxstutableService.updateById(stu));

        return ajaxJson;
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "密码重置", notes = "待开发")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学生ID", required = true)
    })
    public AjaxJson resetPassword(HttpServletRequest request,
                                  long stuID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Pxstutable stu = iPxwxusertableService.getStuById(stuID, loginUser.getQiyeID());
        try {
            iPxwxusertableService.UpdateStuWechatPassword(encodeByMd5("123456"), stuID, stu.getQiyeID());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ajaxJson.setSuccess(true);
        return ajaxJson;
    }

    public static String encodeByMd5(String string) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // 确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        Base64.Encoder base64Encoder = Base64.getEncoder();
        // 加密字符串
        return base64Encoder.encodeToString(md5.digest(string.getBytes("utf-8")));
    }

    @RequestMapping(value = "/unbindWeChat", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "解除微信绑定", notes = "待开发")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "图书ID", required = true),
    })
    public AjaxJson unbindWeChat(HttpServletRequest request, long stuID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
            Pxstutable pxstutable = iPxwxusertableService.getStuById(stuID, loginUser.getQiyeID());
            iPxwxusertableService.DeleteWxuser(pxstutable.getParentTel(), pxstutable.getQiyeID());
            ajaxJson.setSuccess(true);
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/microMallAccountAssociation", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "微商城账号关联", notes = "待开发")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "图书ID", required = true),
    })
    public AjaxJson microMallAccountAssociation(HttpServletRequest request,
                                                long size, long current
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");


        return ajaxJson;
    }

    @RequestMapping(value = "/transferAmount", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "转金额/积分", notes = "待开发")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "图书ID", required = true),
    })
    public AjaxJson transferAmount(HttpServletRequest request,
                                   long size, long current
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");


        return ajaxJson;
    }
}
