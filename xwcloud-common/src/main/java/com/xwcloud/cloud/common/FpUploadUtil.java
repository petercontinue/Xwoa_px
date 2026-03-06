package com.xwcloud.cloud.common;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FpUploadUtil {

    /**
     *
     * @param request  请求数据
     * @param response 响应数据
     * @param guid     文件guid
     * @param chunk    分割序号
     * @param file     分割文件
     * @param chunks   分割文件总数量
     * @param filePath  文件存储路径
     * @param fileSize  单个文件的大小
     * @return  0成功  -1写入文件失败 -2文件大小不匹配
     */
    public  int bigFile(HttpServletRequest request, HttpServletResponse response, String guid, Integer chunk, MultipartFile file, Integer chunks, String filePath, long fileSize){
        try {
            //判断是否是文件上传
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (isMultipart) {
                //如果有分片参数进行分片上传
                if (chunks!=null){
                    //分片上传
                    // 临时目录用来存放所有分片文件
                    String tempFileDir = filePath + guid;
                    //创建临时目录
                    File parentFileDir = new File(tempFileDir);
                    //如果没有目录
                    if (!parentFileDir.exists()) {
                        //创建目录
                        parentFileDir.mkdirs();
                    }
                    // 分片处理时，前台会多次调用上传接口，每次都会上传文件的一部分到后台
                    File tempPartFile = new File(parentFileDir, guid + "_" + chunk + ".part");
                    if (file.getSize()==fileSize){
                        FileUtils.copyInputStreamToFile(file.getInputStream(), tempPartFile);
                    }else {
                        return -2;//文件大小不匹配
                    }
                    return 0;
                }else {
                    //如果没有分片参数进行单文件上传
                    int sing=SingleFile(file,filePath);
                    return sing;
                }

            }else {
                int sing=SingleFile(file,filePath);
                return sing;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 临时目录用来存放所有分片文件
            File parentFileDir = new File(filePath + guid);
            //如果有目录
            if (parentFileDir.exists()) {
                // 删除临时目录中的分片文件
                try {
                    FileUtils.deleteDirectory(parentFileDir);

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            //删除单文件上传的临时文件
            File destTempFile = new File(filePath + "/merge",  file.getName());
            if (destTempFile.exists()){
                try {
                    FileUtils.deleteDirectory(destTempFile);

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            return -1;//写入文件失败
        }
    }

    /**
     * 合并文件
     * @param guid
     * @param fileName
     * @throws Exception
     */
    public int  mergeFile(String guid, String fileName,String filePath){
        // 得到 destTempFile 就是最终的文件
        try {
            File parentFileDir = new File(filePath + guid);
            if(parentFileDir.isDirectory()){
                File destTempFile = new File(filePath , fileName);
                if(!destTempFile.exists()){
                    //先得到文件的上级目录，并创建上级目录，在创建文件,
                    destTempFile.getParentFile().mkdir();
                    try {
                        //创建文件
                        destTempFile.createNewFile(); //上级目录没有创建，这里会报错
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(parentFileDir.listFiles().length);
                for (int i = 0; i < parentFileDir.listFiles().length; i++) {
                    File partFile = new File(parentFileDir, guid + "_" + i + ".part");
                    FileOutputStream destTempfos = new FileOutputStream(destTempFile, true);
                    //遍历"所有分片文件"到"最终文件"中
                    //FileUtils.copyFile(partFile, destTempfos);
                    FileInputStream fis = new FileInputStream(partFile);
                    try {
                        IOUtils.copyLarge(fis, destTempfos);
                    } finally {
                        fis.close();
                    }
                    destTempfos.close();
                }
                // 删除临时目录中的分片文件
                FileUtils.deleteDirectory(parentFileDir);
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return -2;
    }

    /**
     * 单文件上传保存
     * @param file
     */
    public int SingleFile(MultipartFile file,String filePath){
        //单文件上传
        try {

            String fileName=file.getOriginalFilename();
            File destTempFile = new File(filePath,  fileName);

            if(!destTempFile.exists()){//如果没有文件
                destTempFile.getParentFile().mkdirs();
                destTempFile.createNewFile(); //上级目录没有创建，这里会报错
            }

            FileUtils.copyInputStreamToFile(file.getInputStream(), destTempFile);
            return 0;
        } catch (IOException e) {

            e.printStackTrace();
            return -1;
        }
    }

    public int checkFileComplete(String guid,int chunks,String filePath,String fileName){
        String fileDir=filePath+guid;
        File myfile= new File(filePath+"/"+fileName);
        if (myfile.exists()){
            myfile.delete();
            return -1;
        }
        for(int i =0;i<chunks;i++){
            String fName=guid + "_" + i + ".part";
            File file = new File(fileDir+"/"+fName);
            if (!file.exists()){
                return i;
            }
        }
        return chunks;
    }

    public class uploadArg{
        String guid;
        Integer chunk;
        MultipartFile file;
        Integer chunks;

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public Integer getChunk() {
            return chunk;
        }

        public void setChunk(Integer chunk) {
            this.chunk = chunk;
        }

        public MultipartFile getFile() {
            return file;
        }

        public void setFile(MultipartFile file) {
            this.file = file;
        }

        public Integer getChunks() {
            return chunks;
        }

        public void setChunks(Integer chunks) {
            this.chunks = chunks;
        }
    }
}
