package com.xwcloud.cloud.sys.Service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface FileService {
    /**
     * 多文件上传
     * @param file
     * @return
     */
    Map<String, List<String>> uploadImgs(MultipartFile[] file);
}
