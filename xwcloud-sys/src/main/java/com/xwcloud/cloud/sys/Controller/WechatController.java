package com.xwcloud.cloud.sys.Controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.wxpay.sdk.WXPayUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.xwcloud.cloud.common.AesCbcUtil;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.Httprequests;
import com.xwcloud.cloud.common.Message.TemplateParam;
import com.xwcloud.cloud.common.aliyun.AliyunSmsUtil;
import com.xwcloud.cloud.common.redis.RedisUtil;
import com.xwcloud.cloud.common.wechatpay.WeChatConfig;
import com.xwcloud.cloud.common.wechatpay.WeChatPayDto;
import com.xwcloud.cloud.common.wechatpay.WeChatPayUtil;
import com.xwcloud.cloud.common.wechattuisong.TemplateData;
import com.xwcloud.cloud.common.wechattuisong.WxMssVo;
import com.xwcloud.cloud.model.Form.loginweixinForm;
import com.xwcloud.cloud.model.OA.OaKehu;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.entity.*;
import com.xwcloud.cloud.sys.Service.*;
import com.xwcloud.cloud.sys.Utils.VericodeUtil;
import com.xwcloud.cloud.sys.config.WeiXinConfig;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;

@Controller
@Slf4j
@RequestMapping("/weixin")
public class WechatController {

    @Autowired
    private RedisUtil redisUtil;   //使用时切记：要像这样注入进来，而不是用new RedisUtil()来创建对象

    @Autowired
    private WeiXinService weiXinService;
    @Autowired
    private WeiXinConfig weiXinConfig;

    @Autowired
    private IPxstutableService iPxstutableService;

    @Autowired
    private WeChatPayUtil weChatPayUtil;

    @Autowired
    private IWscUserService iWscUserService;

    @Value("${xw.SERVER_GATEWAY}")
    String SERVER_GATEWAY;

    @Autowired
    private IOaKehuService iOaKehuService;

    @Autowired
    private IPxstafftableService iPxstafftableService;

    @Autowired
    private IGzhAlluserService iGzhAlluserService;

    @Autowired
    private IWXMsgPushSevice iwxMsgPushSevice;

    @Autowired
    IWscUserBindService iWscUserBindService;

    @Autowired
    private IWscOrderService iWscOrderService;

    /**
     * 获取微信用户信息
     *
     * @param code
     * @param request
     * @param response
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping("/getWeiXinUserInfo")
    @ResponseBody
    public String getWeiXinUserInfo(String code, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        //第一步：用户同意授权，获取code
        if (code == null) {
            String url = URLEncoder.encode(request.getRequestURL().toString());
            url = SERVER_GATEWAY + "/xwcloud-sys/weixin/getWeiXinUserInfo";
            String authorizeUrl = weiXinService.buildAuthorizeURL(url);
            response.sendRedirect(authorizeUrl);
            return null;
        }
        //第二步：通过code换取网页授权access_token
        String htmlInfo = "";
        Map<String, Object> accesstokenInfo = weiXinService.getACCESSTOKEN(code);
        String errcode = (String) accesstokenInfo.get("errcode");
        if (StringUtils.isEmpty(errcode)) {
            //第四步：拉取用户信息(需scope为 snsapi_userinfo)
            Map<String, Object> weiXinUserInfo = weiXinService.getWeiXinUserInfo(accesstokenInfo);
            String userInfohtml = createUserInfoHtml(weiXinUserInfo);
            return userInfohtml;
        }
        return htmlInfo;
    }

    /**
     * 生成微信用户信息Html
     *
     * @param weiXinUserInfo
     * @return
     */
    private String createUserInfoHtml(Map<String, Object> weiXinUserInfo) {
        String wxOpenId = (String) weiXinUserInfo.get("openid");
        String nickname = (String) weiXinUserInfo.get("nickname");
        Double sex = (Double) weiXinUserInfo.get("sex");
        String sexString = "";
        if (sex == 1.0d) {
            sexString = "男";
        } else {
            sexString = "女";
        }
        String province = (String) weiXinUserInfo.get("province");
        String city = (String) weiXinUserInfo.get("city");
        String country = (String) weiXinUserInfo.get("country");
        String headimgurl = (String) weiXinUserInfo.get("headimgurl");

        String htmlInfo = "<!DOCTYPE html>" +
                "<html lang='en'>" +
                "<head>" +
                "  <meta charset='UTF-8'>" +
                "  <meta name='viewport' content='initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no'>" +
                "</head>" +
                "<body>" +
                "<h3>用户openId</h3>" +
                "<p>" + wxOpenId + "</p>" +
                "<h3>普通用户昵称</h3>" +
                "<p>" + nickname + "</p>" +
                "<h3>用户性别</h3>" +
                "<p>" + sexString + "</p>" +
                "<h3>用户所在地</h3>" +
                "<p>" + country + province + city + "</p>" +
                "<h3>用户头像</h3>" +
                "<img src=\"" + headimgurl + "\"></img>" +
                "</body>" +
                "</html>";
        return htmlInfo;
    }

    /**
     * 初始化微信JSSDK配置信息
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/initWXJSSDKConfigInfo")
    public String initWXJSConfig(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String shareUrl = request.getParameter("shareUrl");//分享的URL
        Map map = weiXinService.initJSSDKConfigInfo(shareUrl);
        String json = weiXinService.mapToJson(map);
        return json;
    }

    @ResponseBody
    @GetMapping("/createImg")
    public AjaxJson createImg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AjaxJson ajaxJson = new AjaxJson();
        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);


        VericodeUtil vericodeUtil = new VericodeUtil();
        String vericodehavedouhao = vericodeUtil.getRandomStr();   //生成验证码字符串，注意各个字母是用","间隔的
        String[] tempArr = vericodehavedouhao.split(",");
        String vericodeSt = StringUtils.join(tempArr, "");   //拼接成字符串，拼接后即为验证码
        log.info("==验证码:" + vericodeSt);
        String id = UUID.randomUUID().toString();
        String uid = id.replaceAll("-", "");
        redisUtil.set(uid, vericodeSt, 120);       //验证码字符串存入redis，有效期两分钟
        //vericodeUtil.getRandcode2(request, response, vericodehavedouhao);//输出验证码图片
        ajaxJson.setMsg(uid + "_" + vericodeSt);
        return ajaxJson;
    }

    /**
     * 微信登录
     *
     * @param form
     * @param request
     * @return
     */
    @RequestMapping(value = "/login_by_weixin", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson loginByWeixin(@RequestBody loginweixinForm form, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();

        String sendGet = iPxstutableService.loginByWeixin(form.getCode(), form.getEncryptedData(), form.getIv()); //根据code去调用接口获取用户openid和session_key

        JSONObject json = JSONObject.parseObject(sendGet);
        System.out.println("返回过来的json数据:" + json.toString());
        String sessionkey = json.get("session_key").toString(); //会话秘钥
        String openid = json.get("openid").toString(); //用户唯一标识
        String unionid = json.get("unionid").toString();
        try {
            //拿到用户session_key和用户敏感数据进行解密，拿到用户信息。
            String decrypts = AesCbcUtil.decrypt(form.getEncryptedData(), sessionkey, form.getIv(), "utf-8");//解密
            System.out.println("解密之后的字符串：" + decrypts);
            JSONObject jsons = JSONObject.parseObject(decrypts);
            jsons.fluentPut("openID", openid);
            jsons.fluentPut("unionid", unionid);
            ajaxJson.setObj(jsons);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("解密出错了，错误信息：" + e.getMessage());
            ajaxJson.setMsg(e.getMessage());
        }
        return ajaxJson;
    }
    //region 小程序支付

    /**
     * 生成预支付订单
     *
     * @param param
     * @return
     * @SneakyThrows
     */
    @PostMapping("/getYuezhifuDingdan")
    @ResponseBody
    @ApiOperation(value = "生成预支付订单")
    public AjaxJson getPrePayInfo(HttpServletRequest request,@RequestBody WeChatPayDto param) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        String payType = param.getPayType();
        if ("11".equals(payType)) {
            Map<String, String> resultMap = null;
            String openId = loginUser.getOpenid();
            try {
                resultMap = weChatPayUtil.getPrePayInfo(param, openId);
            } catch (Exception e) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("生成微信预支付订单失败");
            }
            // 处理公司业务
            ajaxJson.setObj(resultMap);
            return ajaxJson;
        }
        return null;
    }

    /**
     * 支付成功回调函数
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
//    @RequestMapping(value = "/weChatPay",method = RequestMethod.POST)
//    @ResponseBody
//    public String wxNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        System.out.println("商城回调方法");
//        Map orderMap = new HashMap();
//        BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream()));
//        String line = null;
//        StringBuilder sb = new StringBuilder();
//        while ((line = br.readLine()) != null) {
//            sb.append(line);
//        }
//        String notityXml = sb.toString();
//        String resXml = "";
//        Map resPrint = new HashMap();
//        Map<String, String> resultMap = WXPayUtil.xmlToMap(notityXml);
//        String returnCode = (String) resultMap.get("return_code");//业务结果
//        String orderNo = resultMap.get("out_trade_no");//订单号
//        String sign = resultMap.get("sign");//获取微信签名
//        resultMap.remove("sign");//去除签名字段
//        String signNew = WXPayUtil.generateSignature(resultMap, WeChatConfig.WECHAT_key); //重新签名
//        if (signNew.equals(sign)) {
//            if ("SUCCESS".equals(returnCode)) {
//                System.out.println(signNew + "ppppp");
//                resPrint.put("return_code", "SUCCESS");
//                resPrint.put("return_msg", "ok");
//                resXml = WXPayUtil.mapToXml(resPrint);
//                orderMap.put("orderStatus", 1);
//                orderMap.put("orderNo", orderNo);
//                // 自己的业务逻辑
//
//                return WxPayNotifyResponse.success("成功");
//            } else {
//                System.out.println("业务结果失败");
//                return null;//WxPayNotifyResponse.success("code:" + 9999 + "微信回调结果异常,异常原因:");
//            }
//
//        } else {
//            resPrint.put("return_code", "FAIL");
//            resPrint.put("return_msg", "签名失败");
//            resXml = WXPayUtil.mapToXml(resPrint);
//        }
//        log.info(resXml);
//        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
//        out.write(resXml.getBytes());
//        out.flush();
//        out.close();
//        br.close();
//        return null;
//    }


    @RequestMapping(value = "/weChatPay")
    public void wxNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("支付成功，进入回调函数");
        BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        //sb为微信返回的xml
        String notityXml = sb.toString();
        String resXml = "";
        System.out.println("接收到的报文：" + notityXml);
        @SuppressWarnings("unchecked")
        Map<String, String> map = WXPayUtil.xmlToMap(notityXml);

        String returnCode = (String) map.get("return_code");
        String sign = map.get("sign");//获取微信签名
        String orderNo = map.get("out_trade_no");//订单号
        System.out.println("订单号：" + orderNo);
        if ("SUCCESS".equals(returnCode)) {
            System.out.println("签名验证成功！");
            //验证签名是否正确
//            Map<String, String> validParams = PayUtil.paraFilter(map);  //回调验签时需要去除sign和空值参数
//            String validStr = PayUtil.createLinkString(validParams);//把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
           // String sign = WXPayUtil.generateSignature(validParams,WeChatConfig.WECHAT_key).toUpperCase();//拼装生成服务器端验证的签名
            String signNew = WXPayUtil.generateSignature(map, WeChatConfig.WECHAT_key); //重新签名
            // 因为微信回调会有八次之多,所以当第一次回调成功了,那么我们就不再执行逻辑了

            //根据微信官网的介绍，此处不仅对回调的参数进行验签，还需要对返回的金额与系统订单的金额进行比对等
            if (signNew.equals(sign)) {
                System.out.println("判断成功！");
                /**此处添加自己的业务逻辑代码start**/
                // bla bla bla....
                QueryWrapper queryWrapper=new QueryWrapper();
                queryWrapper.eq("orderNumber",orderNo);
                WscOrder wscOrder=iWscOrderService.getOne(queryWrapper);
                System.out.println("订单号：" + wscOrder.getOrderNumber());
                wscOrder.setOrderState(2);
                wscOrder.setPayDateTime(new Date());
                iWscOrderService.updateById(wscOrder);
                /**此处添加自己的业务逻辑代码end**/
                //通知微信服务器已经支付成功
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            } else {
                System.out.println("微信支付回调失败!签名不一致");
            }
        } else {
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }
        System.out.println(resXml);
        System.out.println("微信支付回调数据结束");
        BufferedOutputStream out = new BufferedOutputStream(
                response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
    }
    //endregion

    //region 消息推送

    /**
     * 获取用户Access_Token
     *
     * @return
     */
    public String getAccessToken() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("APPID", "wxca12aabb9d440265");  //微信小程序AppID
        params.put("APPSECRET", "6d385686fedfe8c8dea57de9c87a2e1e");  //微信小程序AppSecret
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={APPID}&secret={APPSECRET}", String.class, params);
        String body = responseEntity.getBody();
        JSONObject object = JSON.parseObject(body);
        String Access_Token = object.getString("access_token");
        String expires_in = object.getString("expires_in");
        System.out.println("有效时长expires_in：" + expires_in);
        return Access_Token;
    }

    /**
     * 模板消息推送
     *
     * @param openid
     * @return
     */
    @RequestMapping(value = "/pushMessage", method = RequestMethod.GET)
    @ResponseBody
    public String pushMessage(String openid) {
        RestTemplate restTemplate = new RestTemplate();
        //这里简单起见我们每次都获取最新的access_token（时间开发中，应该在access_token快过期时再重新获取）
        String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + getAccessToken();
        //拼接推送的模版
        WxMssVo wxMssVo = new WxMssVo();
        wxMssVo.setTouser("oH3Ol5NtxEJY-2b6qcfYslVirBbw");//用户的openid（要发送给那个用户，通常这里应该动态传进来的）
        wxMssVo.setTemplate_id("rpRWI4cEqFpbXfbxyYs8MiyLMUizcU-DQlIG-nijTsM");//订阅消息模板id（上课提醒）
//        wxMssVo.setTemplate_id("rpRWI4cEqFpbXfbxyYs8MiyLMUizcU-DQlIG-nijTsM");//订阅消息模板ID
        wxMssVo.setPage("pages/index/index");

        Map<String, TemplateData> m = new HashMap<>(3);

//        m.put("thing1",new TemplateData("置信银河广场枭为科技有限公司"));
//        m.put("thing2",new TemplateData("置信银河广场枭为科技有限公司"));
//        m.put("thing3",new TemplateData("置信银河广场枭为科技有限公司"));

        m.put("thing1", new TemplateData("JAVA面向对象程序设计"));
        m.put("thing6", new TemplateData("置信银河广场枭为科技有限公司"));
        m.put("name12", new TemplateData("枭老师"));
        m.put("time5", new TemplateData("2021年6月18日 18:00:00"));
        m.put("time3", new TemplateData("2020年12月16日 20:00:00"));
        m.put("thing16", new TemplateData("基础知识学习提高班"));
        wxMssVo.setData(m);
        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(url, wxMssVo, String.class);
        return responseEntity.getBody();
    }
    //endregion

    //region 用户注册

    /**
     * 微信小程序用户授权登录，保存获取到的用户信息
     *
     * @param nickName
     * @param openid
     * @param headImage
     * @param sex
     * @param diqu
     * @param addr
     * @param fid
     * @param qiyeID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/SaveAddWechatUser", method = RequestMethod.GET)
    @ApiOperation(value = "保存微信用户信息")
    public AjaxJson SaveAddWechatUser(String nickName, String openid, String headImage, String sex, String diqu,String phoneNumber, String addr, long fid, long qiyeID, String unionid) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("openid", openid);
        queryWrapper.eq("qiyeID", qiyeID);
        WscUser user = iWscUserService.getOne(queryWrapper);
        if (user != null) {
            user.setUnionid(unionid);
            iWscUserService.updateById(user);
            ajaxJson.setMsg("已存在用户信息");
        } else {
            WscUser wscUser = new WscUser();
            wscUser.setAddTime(new Date());
            wscUser.setNickName(nickName);
            wscUser.setOpenid(openid);
            wscUser.setHeadImage(headImage);
            wscUser.setSex(sex);
            wscUser.setDiqu(diqu);
            wscUser.setAddr(addr);
            wscUser.setFid(fid);
            wscUser.setQiyeID(qiyeID);
            wscUser.setUserType(1);
            wscUser.setUnionid(unionid);
            wscUser.setTuikeLevelID(0L);
            wscUser.setScRemainMoney(new BigDecimal(0));
            wscUser.setScRemainyongjin(new BigDecimal(0));
            wscUser.setSmsRemain(0);
            wscUser.setScWeijieYongjin(new BigDecimal(0));
            wscUser.setScYijieYongjin(new BigDecimal(0));
            wscUser.setScJifen(new BigDecimal(0));
            wscUser.setPhoneNumber(phoneNumber);
            ajaxJson.setSuccess(iWscUserService.save(wscUser));
        }
        return ajaxJson;
    }

    /**
     * 把微信用户的手机号填上
     *
     * @param openid
     * @param qiyeID
     * @param phoneNumber
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateWechatUserAddPhoneNumber", method = RequestMethod.GET)
    @ApiOperation(value = "把微信用户的手机号填上")
    public AjaxJson updateWechatUserAddPhoneNumber(String openid, long qiyeID, String phoneNumber) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("openid", openid);
        queryWrapper.eq("qiyeID", qiyeID);
        List<WscUser> wxusers = iWscUserService.list(queryWrapper);
        if (wxusers.size() <= 0) {
            ajaxJson.setMsg("此用户不存在");
        } else {
            for (WscUser item : wxusers) {
                item.setPhoneNumber(phoneNumber);
                iWscUserService.updateById(item);
            }
        }
        ajaxJson.setMsg("操作成功");
        return ajaxJson;
    }


    //endregion

    //region
    @ResponseBody
    @RequestMapping(value = "/GetAllqiyeInfoByuserPhone", method = RequestMethod.GET)
    @ApiOperation(value = "根据电话号码查询该号码所属的所有机构")
    public AjaxJson GetAllqiyeInfoByuserPhone(String PhoneNumber) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iOaKehuService.GetAlljigouByPhoneNumber(PhoneNumber));
        return ajaxJson;
    }


    @ResponseBody
    @GetMapping("getalljigouInwxApplets")
    @ApiOperation(value = "根据电话号码查询该号码所属的所有机构(老师与学员)")
    public AjaxJson getalljigouInwxApplets(String PhoneNumber) {
        AjaxJson ajaxJson = new AjaxJson();
        List<Pxstafftable> tealist = iPxstafftableService.list(new QueryWrapper<Pxstafftable>()
                .eq("staffTel", PhoneNumber)
        );
        List<WscUserBind> stulist = iWscUserBindService.list(new QueryWrapper<WscUserBind>()
                .eq("phoneNumber", PhoneNumber)
        );

        String allqiyeID = "";
        if (tealist.size() > 0) {
            for (Pxstafftable item : tealist) {
                allqiyeID += item.getQiyeID() + ",";
            }
        }
        if (stulist.size() > 0) {
            for (WscUserBind items : stulist) {
                allqiyeID += items.getQiyeID() + ",";
            }
        }

        String[] allqyIDs = allqiyeID.split(",");
        List<OaKehu> alljigou = iOaKehuService.list(new QueryWrapper<OaKehu>()
                .in("id", allqyIDs)
                .last(" GROUP BY id ")
        );

        ajaxJson.setObj(alljigou);
        return ajaxJson;

    }


    @GetMapping("getUserIdentity")
    @ResponseBody
    @ApiOperation("多机构情况下选中某一机构时获取用户对应有哪些身份") //学员  老师
    public AjaxJson getUserIdentity(HttpServletRequest request,String PhoneNumber, String qiyeID) {
        AjaxJson ajaxJson = new AjaxJson();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        List<Pxstafftable> tealist = iPxstafftableService.list(new QueryWrapper<Pxstafftable>()
                .eq("staffTel", PhoneNumber)
                .eq("qiyeID",qiyeID)
        );
        List<WscUserBind> stulist = iWscUserBindService.list(new QueryWrapper<WscUserBind>()
                .eq("phoneNumber", PhoneNumber)
                .eq("qiyeID",qiyeID)
        );
        if(tealist.size()>0){
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Identity","老师");
            jsonArray.add(jsonObject1);
        }

        if(stulist.size()>0){
            JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("Identity","学员");
            jsonArray.add(jsonObject2);
        }
        jsonObject.put("sites", jsonArray);
        ajaxJson.setObj(jsonObject);
        return ajaxJson;
    }


    /**
     * 柑橘手机号码获取短信验证码
     *
     * @param phoneNumber
     * @return
     */
    @SneakyThrows
    @ResponseBody
    @RequestMapping(value = "/SendShortMessage", method = RequestMethod.GET)
    @ApiOperation(value = "发送短信验证码")
    public AjaxJson SendShortMessage(String phoneNumber) {
        AjaxJson ajaxJson = new AjaxJson();
        String yanzhengma = String.valueOf(new Random().nextInt(999999));
        SendSmsResponse sendSmsResponse = AliyunSmsUtil.sendSmsYzm(phoneNumber, yanzhengma);  //发送短信验证码
        redisUtil.set(phoneNumber, yanzhengma, 120);       //验证码字符串存入redis，有效期两分钟
        ajaxJson.setObj(sendSmsResponse);
        return ajaxJson;
    }

    /**
     * 保存重置密码信息
     *
     * @param telPhoneNumber
     * @param dxyanzhengma
     * @param newPassword
     * @param qiyeID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/UpdateUserPassword", method = RequestMethod.GET)
    @ApiOperation(value = "重置密码")
    public AjaxJson UpdateUserPassword(String telPhoneNumber, String dxyanzhengma, String newPassword, long qiyeID) {
        AjaxJson ajaxJson = new AjaxJson();
        String saveyzm = redisUtil.get(telPhoneNumber).toString();
        if (!saveyzm.equals(dxyanzhengma)) {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("短信验证码输入错误");
        } else {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("qiyeID", qiyeID);
            queryWrapper.eq("staffTel", telPhoneNumber);
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            Pxstafftable pxstafftable = iPxstafftableService.getOne(queryWrapper);
            if (pxstafftable == null) {
                ajaxJson.setCode("N");
                ajaxJson.setMsg("当前系统未找到此用户！");
            } else {
                pxstafftable.setPassword(encoder.encode(newPassword));
                iPxstafftableService.updateById(pxstafftable);
                ajaxJson.setMsg("重置密码成功");
            }
        }
        return ajaxJson;
    }
    //endregion

    @ResponseBody
    @RequestMapping(value = "/GetUserJigouName", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户所属机构名称")
    public AjaxJson GetUserJigouName(HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iOaKehuService.getById(loginUser.getQiyeID()));
        return ajaxJson;
    }


    //region 公众号与小程序互通相关操作
    @ResponseBody
    @RequestMapping(value = "/getgzhAccessToken", method = RequestMethod.GET)
    public List<Map<String, Object>> getgzhAccessToken() {
        String appId = "wx105072705814cae3";
        String appSecret = "060f475e084f44d6bdbbc8615203c192";
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                + appId + "&secret=" + appSecret;
        JsonObject accessTokenInfo = null;
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            String response = EntityUtils.toString(httpEntity, "utf-8");
            Gson token_gson = new Gson();
            accessTokenInfo = token_gson.fromJson(response, JsonObject.class);
            String access_token = accessTokenInfo.get("access_token").toString().replaceAll("\"", "");
            String requestuserurl = "https://api.weixin.qq.com/cgi-bin/user/get";
            String returndata = Httprequests.sendGet(requestuserurl, "access_token=" + access_token);
            JSONObject jsons = JSONObject.parseObject(returndata);
            Map<String, Object> result = jsons;
            Map<String, Object> map2 = (Map<String, Object>) result.get("data");
            List<String> list2 = (List<String>) map2.get("openid");
            List<Map<String, Object>> list = new ArrayList<>();
            for (String s : list2) {
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("openid", s);
                List<GzhAlluser> alluserList = iGzhAlluserService.list(queryWrapper);
                if (alluserList.size() == 0) {
                    String send_url2 = "https://api.weixin.qq.com/cgi-bin/user/info";
                    String ssss = "";
                    System.out.println("要获取用户的OpenID：" + s);
                    //通过工具类把请求接口和参数进行请求并返回调用结果
                    Map<String, Object> resultX = JSONObject.parseObject(Httprequests.sendGet(send_url2, "access_token=" + access_token + "&openid=" + s + "&lang=zh_CN"));
                    System.out.println("获取到的用户信息：" + resultX.toString());
                    System.out.println(resultX.get("subscribe").toString().equals("1"));
                    if (resultX.get("subscribe").toString().equals("1")) {
                        GzhAlluser gzhAlluser = new GzhAlluser();
                        gzhAlluser.setNickname((String) resultX.get("nickname"));
                        gzhAlluser.setOpenid((String) resultX.get("openid"));
                        gzhAlluser.setSubscribe(resultX.get("subscribe").toString());
                        gzhAlluser.setSubscribeTime(resultX.get("subscribe_time").toString());
                        gzhAlluser.setUnionid((String) resultX.get("unionid"));
                        iGzhAlluserService.save(gzhAlluser);
                        list.add(resultX);
                    }
                }
            }
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    /**
     * 测试模板消息推送
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/TestSendMessage", method = RequestMethod.GET)
    public AjaxJson TestSendMessage() {
        AjaxJson ajaxJson = new AjaxJson();
        //用户关注公众号之后的openID
        String openID="oFaPK1XGvFNmKBHGMsohHnH0HtwM";
        //模板消息ID
        String templateId= "66c5liowLy0kxCkLXjpVsmp8JpFVEHs_SAZcakX5AgQ";
        //点击消息跳转小程序链接
        String url = "";
        //模板消息内容信息，与模板消息使用一致
        List<TemplateParam> paras = new ArrayList<>();
        paras.add(new TemplateParam("first", "王小二同学，你有一个消课通知", "#FF3333"));
        paras.add(new TemplateParam("keyword1", "小学语文一对一", "#0044BB"));
        paras.add(new TemplateParam("keyword2", "20", "#0044BB"));
        paras.add(new TemplateParam("keyword3", "2021-08-11 08:00:00-10:00:00", "#0044BB"));
        paras.add(new TemplateParam("keyword4", "学员按时上下课，正常消课", "#0044BB"));
        paras.add(new TemplateParam("remark", "学员正常考勤", "#0044BB"));
        boolean back = iwxMsgPushSevice.wxMsgConsumptionSuccess(openID,templateId,url,paras);
        if (back) {
            ajaxJson.setCode("Y");
            ajaxJson.setMsg("消息发送成功！");
        } else {
            ajaxJson.setCode("N");
            ajaxJson.setMsg("消息发送失败！");
        }
        return ajaxJson;
    }
    //endregion
}
