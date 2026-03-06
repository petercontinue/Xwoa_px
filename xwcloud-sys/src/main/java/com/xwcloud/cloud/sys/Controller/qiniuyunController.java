package com.xwcloud.cloud.sys.Controller;

import com.xwcloud.cloud.common.Qiniuutils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/qiniu")
public class qiniuyunController {

    @RequestMapping("/toIndex")
    public String index() {
        return "img";
    }

    @ResponseBody
    @RequestMapping(value = "/image", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    private String postUserInforUpDate(HttpServletRequest request, @RequestPart("file") MultipartFile multipartFile) throws IOException {

        // 用来获取其他参数
        MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
        String name = params.getParameter("username");
        if (!multipartFile.isEmpty()) {
            FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
            String path = Qiniuutils.uploadQNImg(inputStream, UUID.randomUUID().toString()); // KeyUtil.genUniqueKey()生成图片的随机名
            System.out.print("七牛云返回的图片链接:" + path);
            return path;
        }
        return "上传失败";
    }




}
