package com.xwcloud.cloud.sys.Controller;


import com.xwcloud.cloud.common.aliyun.AliyunOssUtil;
import com.xwcloud.cloud.model.Sso.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Slf4j
@Controller
@RequestMapping("/aliyun")
public class AliyunOssController {

    @ResponseBody
    @RequestMapping(value = "/uploadFileToAliOss", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    private String uploadFileToAliOss(HttpServletRequest request, @RequestPart("file") MultipartFile multipartFile) throws IOException {

        // 用来获取其他参数
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();

        if (!multipartFile.isEmpty()) {
            String Url = AliyunOssUtil.uploadFile(multipartFile,qiyeID.toString());
            log.info("文件Url:" + Url);
            return Url;
        }
        return "上传失败";
    }

    @ResponseBody
    @RequestMapping(value = "/delFileFromAliOss", method = RequestMethod.DELETE)
    private String delFileFromAliOss(HttpServletRequest request,String delFilePath) throws IOException {

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long qiyeID = loginUser.getQiyeID();

        String url1 = "https://xjbok-oss.oss-cn-beijing.aliyuncs.com/";
        String key = delFilePath.substring(url1.length());

        if (!key.isEmpty()) {
            boolean bol = AliyunOssUtil.deleteOssFile(key);
            if(bol==true)
                return "删除成功";
            else
                return "删除失败";
        }
        return "删除失败";
    }

}
