package com.xwcloud.cloud.oa.controller.smallProgram;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.google.common.collect.Lists;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.oa.controller.smallProgram.auth.ApiQueryAuthInfo;
import com.xwcloud.cloud.oa.controller.smallProgram.common.ApiModifyDomainInfo;
import com.xwcloud.cloud.oa.controller.smallProgram.common.WechatThirdConfig;
import com.xwcloud.cloud.oa.controller.smallProgram.error.WechatErrorException;
import com.xwcloud.cloud.oa.controller.smallProgram.xcx.*;
import com.xwcloud.cloud.oa.service.WechatThirdAuthService;
import com.xwcloud.cloud.oa.service.WechatThirdMiniProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


@RestController
@RequestMapping("/wechatThirdAuth")
@JacksonXmlRootElement(localName = "xml")
public class WechatThirdAuthController {

    @Autowired
    private WechatThirdConfig wechatThirdConfig;

    @Autowired
    private WechatThirdAuthService wechatThirdAuthService;

    @Autowired
    private WechatThirdMiniProgramService wechatThirdMiniProgramService;

    @ResponseBody
    @RequestMapping(value = "/TestFunction", method = RequestMethod.GET)
    public AjaxJson TestFunction() {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setMsg("测试成功！");
        ajaxJson.setSuccess(true);
        return ajaxJson;
    }

    /**
     * 一键授权按钮
     *
     * @param response
     * @return
     * @throws Exception
     */
    @GetMapping("/goto_auth_url_show")
    public String gotoPreAuthUrlShow(HttpServletResponse response) throws Exception {
        return "<a href='goto_auth_url'>一键授权</a>";
    }


    /**
     * 授权注册页面扫码授权   显示二维码
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/goto_auth_url", method = RequestMethod.GET)
    public void gotoPreAuthUrl(HttpServletRequest request, HttpServletResponse response) throws WechatErrorException {
        String host = request.getHeader("host");
        String url = "http://" + host + "/wechatThirdAuth/jump";
        try {
            url = wechatThirdAuthService.getPreAuthUrl(url, null, null);
            response.sendRedirect(url);
        } catch (WechatErrorException | IOException e) {
            System.out.println("gotoPreAuthUrl" + e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 授权成功回调链接
     *
     * @param authorizationCode 这边可以直接 重定向自己前台的页面   也可判断权限是否全部勾选完成  授权完成后把授权小程序的信息保存好
     * @return
     */
    @GetMapping("/jump")
    public ApiQueryAuthInfo jump(@RequestParam("auth_code") String authorizationCode,
                                 @RequestParam(value = "expires_in", required = false) Integer expiresIn, HttpServletResponse response) {
        try {
            ApiQueryAuthInfo apiQueryAuthInfo = wechatThirdAuthService.getApiQueryAuthInfo(authorizationCode);
            System.out.println("getQueryAuth" + apiQueryAuthInfo);
            return apiQueryAuthInfo;
        } catch (WechatErrorException e) {
            System.out.println("gotoPreAuthUrl" + e);
            throw new RuntimeException(e);
        }
    }


    /**
     * 授权完成后可提交审核
     */
    @PostMapping("/commit")
    public Boolean commit() throws Exception {

        /**
         * 授权小程序的id
         */
        String authorizerAppId = null;
        /**
         * 1、使用token调用微信接口modify_domain更换小程序的服务器域名，以便代替微信对小程序或公众号进行运营管理。
         */
        ApiModifyDomainInfo apiModifyDomainInfo = wechatThirdMiniProgramService.modifyDomain(authorizerAppId, "set", wechatThirdConfig.getWebViewDomain(), wechatThirdConfig.getWebViewDomain(), wechatThirdConfig.getWebViewDomain(), wechatThirdConfig.getWebViewDomain());


        /**
         * 2、在微信第三方平台设置页面里上传一个开发小程序的代码的，并添加为模板，取得模板ID
         */
        List<TemplateListResult> templateListResultList = wechatThirdMiniProgramService.getTemplateList();
        /**
         * 取最新的一个模板
         */
        Collections.sort(templateListResultList, Comparator.comparing(TemplateListResult::getCreateTime).reversed());
        TemplateListResult template = templateListResultList.get(0);


        /**
         * 3、在第三方平台开发程序中调用微信接口/wxa/commit传入参数：ext_json
         */
        Map<String, Object> map = new HashMap<>();
        map.put("tenantId", 1);//自定义参数
        ApiCodeCommitExtParameter apiCodeCommitExtParameter = ApiCodeCommitExtParameter
                .builder().extAppid(authorizerAppId)
                .extEnable(true)
                .directCommit(false)
                .ext(map)
                //.extPages(pageMap)
                //.pages(Lists.newArrayList("index","search/index"))
                //.networkTimeout(ApiCodeCommitExtParameter.NetworkTimeout.builder().build())
                //.tabBar(ApiCodeCommitExtParameter.TabBar.builder().build())
                .window(ApiCodeCommitExtParameter.Window
                        .builder()
                        .navigationBarTitleText("小程序名称")
                        .build()).build();

        /**
         * 4、在第三方平台开发程序中调用微信接口/wxa/commit传入参数：ext_json
         */
        ApiSetWebviewDomainInfo apiSetWebviewDomainInfo = wechatThirdMiniProgramService.codeCommit(authorizerAppId, template.getTemplateId(), template.getUserVersion(), template.getUserDesc(), apiCodeCommitExtParameter);


        /**
         * 5 获取授权小程序帐号的可选类目
         */
        CategoryListResult categoryList = wechatThirdMiniProgramService.getCategoryList(authorizerAppId);

        /**
         * 6 获取小程序的第三方提交代码的页面配置（仅供第三方开发者代小程序调用）
         */
        PageListResult pageList = wechatThirdMiniProgramService.getPageList(authorizerAppId);


        /**
         * 7 将第三方提交的代码包提交审核
         */
        List<SubmitAuditParameter.Item> items = Lists.newArrayList();
        for (int i = 0; i < categoryList.getCategoryList().size(); i++) {
            CategoryListResult.CategoryList category = categoryList.getCategoryList().get(i);
            SubmitAuditParameter.Item item = SubmitAuditParameter.Item.builder()
                    .address(pageList.getPageList().get(i))
                    .tag(getMiniPragramTag(category.getFirstClass(), category.getSecondClass(), category.getThirdClass()))
                    .first_class(category.getFirstClass())
                    .first_id(category.getFirstId())
                    .second_class(category.getSecondClass())
                    .third_class(category.getThirdClass())
                    .third_id(category.getThirdId())
                    .second_id(category.getSecondId())
                    .title("小程序名称")
                    .build();
            items.add(item);
        }
        SubmitAuditParameter submitAuditParameter = SubmitAuditParameter
                .builder()
                .item_list(items).build();
        SubmitAuditResult submitAuditResult = wechatThirdMiniProgramService.submitAudit(authorizerAppId, submitAuditParameter);


        /**
         * 查询某个指定版本的审核状态（仅供第三方代小程序调用）
         * 审核状态，其中0为审核成功，1为审核失败，2为审核中，3已提交审核
         */
        AuditStatusResult auditStatus = wechatThirdMiniProgramService.getAuditStatus(authorizerAppId, submitAuditResult.getAuditId());

        /**
         * 更新数据库小程序的审核状态
         */
        return auditStatus.getAuditid() != null ? true : false;
    }

    /**
     * 手动发布已通过审核的小程序
     */
    @PostMapping("/releaes/{authorizerAppid}")
    public Boolean releaesAudited(@PathVariable("authorizerAppid") String authorizerAppid) throws Exception {
        ApiSetWebviewDomainInfo apiSetWebviewDomainInfo = wechatThirdMiniProgramService.releaesAudited(authorizerAppid);
        return apiSetWebviewDomainInfo == null ? false : true;
    }

    /**
     * 手动发布已通过审核的小程序
     * 87013  撤回次数达到上限（每天一次，每个月10次）  慎用
     */
    @PostMapping("/undo/{authorizerAppid}")
    public Boolean undoCodeAudit(@PathVariable("authorizerAppid") String authorizerAppid) throws Exception {
        ApiSetWebviewDomainInfo apiSetWebviewDomainInfo = wechatThirdMiniProgramService.undoCodeAudit(authorizerAppid);
        return apiSetWebviewDomainInfo == null ? false : true;
    }


    private String getMiniPragramTag(String firstClass, String secondClass, String thirdClass) {
        String tag = null;
        if (firstClass != null && this.length(firstClass) < 20) {
            tag = firstClass;
        }
        if (secondClass != null && this.length(secondClass) < 20) {
            tag = tag + " " + secondClass;
        }
        if (this.length(tag) < 20 && thirdClass != null && this.length(thirdClass) < 20) {
            tag = tag + " " + thirdClass;
        }
        return tag;
    }


    /**
     * 获取字符串的长度，如果有中文，则每个中文字符计为2位     * @param value 指定的字符串     * @return 字符串的长度
     */
    public int length(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }

}
