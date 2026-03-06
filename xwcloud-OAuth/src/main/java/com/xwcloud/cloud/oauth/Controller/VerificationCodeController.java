package com.xwcloud.cloud.oauth.Controller;

import com.xwcloud.cloud.common.AjaxJson;

import com.xwcloud.cloud.common.redis.RedisUtil;
import com.xwcloud.cloud.common.verificationCodeUtils;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

@Controller
@Api(tags = "验证码")
public class VerificationCodeController {

    @Autowired
    private RedisUtil redisUtil;


    /**
     * 验证验证码
     * @param Identity  请求表识/
     * @return
     */
    @RequestMapping(value = "/checkVerificationCode")
    @ResponseBody
    public AjaxJson checkVerificationCode(String verificationCode, String Identity) {
        AjaxJson ajaxJson =new AjaxJson();
        // 1.判断验证码
        String redisVerCode = (String) redisUtil.get(Identity);
        if (StringUtils.isBlank(redisVerCode)){
            // 验证码失效
            ajaxJson.setMsg("验证码失效,请重新输入验证码");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        ajaxJson.setSuccess(true);
        return ajaxJson;
    }

    /**
     * 获取验证码
     * @param Identity  请求表识/
     * @return
     */
    @RequestMapping(value = "/getVerificationCode")
    @ResponseBody
    public void getVerificationCode(HttpServletResponse resp, String Identity) {
        try {
            verificationCodeUtils verUtils = new verificationCodeUtils();
            verificationCodeUtils.VerificationCode verCodeObj = verUtils.generateVerificationCode(90,20);
            // 判断redis中是否有对应的key,有就删除数据
            if (redisUtil.hasKey(Identity)){
                redisUtil.del(Identity);
            }
            // 将验证码写入缓存,设定失效时间是5分钟
            redisUtil.set(Identity,verCodeObj.getVerCode(),60*5);
            resp.setHeader("Pragma", "no-cache");
            resp.setHeader("Cache-Control", "no-cache");
            resp.setDateHeader("Expires", 0);
            resp.setContentType("image/jpeg");
            // 将图像输出到Servlet输出流中。
            ServletOutputStream sos = resp.getOutputStream();
            ImageIO.write(verCodeObj.getImage(), "jpeg", sos);
            sos.close();
        }catch (Exception exception){
            exception.printStackTrace();
            System.out.println("生成验证码失败");
        }

    }

}
