package com.xwcloud.cloud.sys.Controller;


import com.alibaba.nacos.api.utils.StringUtils;

import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.tupianerweimaUtil;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.entity.Pxmenutable;
import com.xwcloud.cloud.model.entity.Pxpowertable;
import com.xwcloud.cloud.model.entity.Pxstafftable;

import com.xwcloud.cloud.model.Vo.menubuttonslistVO;
import com.xwcloud.cloud.sys.Service.FileService;
import com.xwcloud.cloud.sys.Service.IPxmenutableService;
import com.xwcloud.cloud.sys.Service.IPxpowertableService;
import com.xwcloud.cloud.sys.Service.IPxstafftableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yinqi
 * @since 2020-10-20
 */
@Controller
@RequestMapping("/sys/PowerManagement")
@Api(tags = "权限管理")
public class PowerManagementController {
    @Autowired
    IPxmenutableService iPxmenutableService;

    @Autowired
    IPxstafftableService iPxstafftableService;

    @Resource
    private FileService fileService;

    @Autowired
    IPxpowertableService iPxpowertableService;

//    @Autowired
//    private Producer producer;

    //region 加载菜单信息

    /**
     * 加载功能菜单信息
     *
     * @return
     */
    @RequestMapping(value = "/getAllMenu", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询所有菜单信息")
    public AjaxJson getAllMenu(long staffpostID, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        List<Pxmenutable> pxmenutableList = iPxmenutableService.querymenuList(staffpostID, loginUser.getQiyeID());
        ajaxJson.setObj(pxmenutableList);
        return ajaxJson;
    }

    /**
     * 保存权限信息
     *
     * @param data
     * @param request
     * @return
     */
    @RequestMapping(value = "/savepowerInfo", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存权限信息")
    public AjaxJson savepowerInfo(@RequestBody Pxmenutable data, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
            for (Pxmenutable item : data.getTreeList()) {
                List<Pxpowertable> pxpowertable = iPxpowertableService.GetPowersBystaffpostIDandmenuID(item.getId(), data.getStaffpostID(), loginUser.getQiyeID());
                if (pxpowertable.size() > 0) {
                    //证明已存在权限，修改即可
                    String otherbtns = "";
                    Pxpowertable updatepowertable = pxpowertable.get(0);
                    updatepowertable.setDataFanwei(item.getDatafanwei());
                    updatepowertable.setQitabtn(null);
                    for (menubuttonslistVO itembtn : item.getMenubtns()) {
                        if (itembtn.getButtonName().equals("添加")) {
                            updatepowertable.setInsertbtn(itembtn.getIscheck());
                        } else if (itembtn.getButtonName().equals("修改")) {
                            updatepowertable.setUpdatebtn(itembtn.getIscheck());
                        } else if (itembtn.getButtonName().equals("删除")) {
                            updatepowertable.setDeletebtn(itembtn.getIscheck());
                        } else if (itembtn.getButtonName().equals("导出")) {
                            updatepowertable.setDaochubtn(itembtn.getIscheck());
                        } else if (itembtn.getButtonName().equals("导入")) {
                            updatepowertable.setDaorubtn(itembtn.getIscheck());
                        } else {
                            otherbtns += itembtn.getId() + ",";
                        }
                    }
                    updatepowertable.setQitabtn(otherbtns);
                    ajaxJson.setSuccess(iPxpowertableService.updateById(updatepowertable));
                } else {
//                不存在权限，需要新增权限数据
                    String otherbtns = "";
                    Pxpowertable pxpowertablenew = new Pxpowertable();
                    pxpowertablenew.setMenuID(item.getId());
                    pxpowertablenew.setStaffpostID(data.getStaffpostID());
                    pxpowertablenew.setQiyeID(loginUser.getQiyeID());
                    pxpowertablenew.setDataFanwei(item.getDatafanwei());
                    pxpowertablenew.setInsertbtn(false);
                    pxpowertablenew.setDaorubtn(false);
                    pxpowertablenew.setUpdatebtn(false);
                    pxpowertablenew.setDeletebtn(false);
                    pxpowertablenew.setDaochubtn(false);
                    pxpowertablenew.setPringbtn(false);
                    for (menubuttonslistVO itembtn : item.getMenubtns()) {
                        if (itembtn.getButtonName().equals("添加")) {
                            pxpowertablenew.setInsertbtn(itembtn.getIscheck());
                        } else if (itembtn.getButtonName().equals("修改")) {
                            pxpowertablenew.setUpdatebtn(itembtn.getIscheck());
                        } else if (itembtn.getButtonName().equals("删除")) {
                            pxpowertablenew.setDeletebtn(itembtn.getIscheck());
                        } else if (itembtn.getButtonName().equals("导出")) {
                            pxpowertablenew.setDaochubtn(itembtn.getIscheck());
                        } else if (itembtn.getButtonName().equals("导入")) {
                            pxpowertablenew.setDaorubtn(itembtn.getIscheck());
                        } else {
                            otherbtns += itembtn.getId() + ",";
                        }
                    }
                    pxpowertablenew.setQitabtn(otherbtns);
                    ajaxJson.setSuccess(iPxpowertableService.save(pxpowertablenew));
                }
            }
            return ajaxJson;
        } catch (Exception e) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("保存权限信息出错了！");
            return ajaxJson;
        }
    }

    @RequestMapping(value = "/saveCampusPower", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "保存校区权限范围")
    public AjaxJson saveCampusPower(long staffpostID, String campusIDs, long menuID, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        List<Pxpowertable> pxpowertable = iPxpowertableService.GetPowersBystaffpostIDandmenuID(menuID, staffpostID, loginUser.getQiyeID());
        if (pxpowertable.size() > 0) {
            //证明已存在权限，修改即可
            Pxpowertable updatepowertable = pxpowertable.get(0);
            updatepowertable.setDataFanwei(campusIDs);
            ajaxJson.setSuccess(iPxpowertableService.updateById(updatepowertable));
        } else {
//                不存在权限，需要新增权限数据
            String otherbtns = "";
            Pxpowertable pxpowertablenew = new Pxpowertable();
            pxpowertablenew.setMenuID(menuID);
            pxpowertablenew.setStaffpostID(staffpostID);
            pxpowertablenew.setQiyeID(loginUser.getQiyeID());
            pxpowertablenew.setDataFanwei(campusIDs);
            pxpowertablenew.setInsertbtn(false);
            pxpowertablenew.setDaorubtn(false);
            pxpowertablenew.setUpdatebtn(false);
            pxpowertablenew.setDeletebtn(false);
            pxpowertablenew.setDaochubtn(false);
            pxpowertablenew.setPringbtn(false);
            ajaxJson.setSuccess(iPxpowertableService.save(pxpowertablenew));
        }
        return ajaxJson;
    }
    //endregion

    //region 按照权限加载按钮信息
    @RequestMapping(value = "/GetPowerButton", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "根据权限查询操作按钮")
    public AjaxJson GetPowerButton(Long menuID, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        long qiyeID = loginUser.getQiyeID();
        Long staffID = Long.valueOf(loginUser.getStaffID());
        AjaxJson ajaxJson = new AjaxJson();
        Pxstafftable pxstafftable = iPxstafftableService.getById(staffID);
        ajaxJson.setObj(iPxmenutableService.getPowerbuttonList(menuID, pxstafftable.getStaffPostID(), qiyeID));
        return ajaxJson;
    }
    //endregion

    //region 七牛云上传图片
    @RequestMapping(value = "/upLoadImgs", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "上传图片")
    public AjaxJson upLoadImgs(@RequestParam("file") MultipartFile[] files) {
        AjaxJson ajaxJson = new AjaxJson();
        if (StringUtils.isBlank(files[0].getOriginalFilename())) {
            ajaxJson.setMsg("未发现可上传图片");
        } else {
            Map<String, List<String>> map = new HashMap<>();
            map = fileService.uploadImgs(files);
            List<String> resultList = map.get("result");
            if ("error".equals(resultList.get(0))) {
                ajaxJson.setMsg("上传图片出错了");
            } else {
                ajaxJson.setObj(resultList);
            }
        }
        return ajaxJson;
    }

    //endregion

    //region 生成图片验证码

//    @GetMapping("captcha.jpg")
//    public void captcha(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
//        response.setHeader("Cache-Control", "no-store, no-cache");
//        response.setContentType("image/jpeg");
//
//        // 生成文字验证码
//        String text = producer.createText();
//        // 生成图片验证码
//        BufferedImage image = producer.createImage(text);
//        // 保存到验证码到 session
//        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);
//
//        ServletOutputStream out = response.getOutputStream();
//        ImageIO.write(image, "jpg", out);
//        IOUtils.closeQuietly(out);
//    }@IgnoreSecurity
    @ResponseBody
    @GetMapping("/createImg")
    @ApiOperation(value = "加载图片二维码")
    public void createImg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        tupianerweimaUtil randomValidateCode = new tupianerweimaUtil();
        randomValidateCode.getRandcode(request, response);//输出验证码图片
        //将生成的随机验证码存放到redis中
        // redisService.setForValue("RANDOMVALIDATECODEKEY",(String)request.getSession().getAttribute("RANDOMREDISKEY "),Long.parseLong("60000"));
    }

    //endregion

}
