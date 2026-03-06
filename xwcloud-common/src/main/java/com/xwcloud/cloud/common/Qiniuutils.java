package com.xwcloud.cloud.common;


import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;


import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import java.io.File;
import java.io.FileInputStream;


public class Qiniuutils {

    //设置好账号的ACCESS_KEY和SECRET_KEY
    final static String ACCESS_KEY = "nbRdDTLM9eq3TPj6nW4WD6WgGzUhzbXXtCHGO6EH";
    final static String SECRET_KEY = "j622Rbc1zO3S-bVuMrqhg0UAF9293-bZLMDHlIlb";
    //要上传的空间
    final static String BUCKET_NAME = "px11";

    /**
     * 七牛云上传图片
     *
     * @param localFilePath
     * @return
     */
    public static String uploadQNImg(FileInputStream localFilePath, String fileName) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg;
        cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = ACCESS_KEY;
        String secretKey = SECRET_KEY;
        String bucket = BUCKET_NAME;
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = fileName + ".jpg";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        String result = null;
        try {
            Response response = uploadManager.put(localFilePath, key, upToken, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            result = "http://imgs.jxb666.com/" + putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
            }
            result = null;
        }
        return result;
    }

    /**
     * 通过key删除七牛云上的图片
     */
    public static Boolean delete(String key) throws QiniuException {

        Configuration cfg = new Configuration(Zone.zone2());
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete("liqinglin0314", key);
            return true;
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            return false;
        }
    }
}
