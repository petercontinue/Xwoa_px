package com.xwcloud.cloud.common;


import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.EncryptionMethod;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

public class ZipUtils {

    private static  String _charset="GBK";

    public static String getCharset() {
        return _charset;
    }

    public static void setCharset(String charset) {
        _charset = charset;
    }

    /**
     * 压缩文件夹
     * @param zipPath
     * @param folderPath
     * @return
     */
    public static ZipFile compressFolderToZip(String zipPath, String folderPath){
        ZipFile zipFile = new ZipFile(zipPath);
        zipFile.setCharset(Charset.forName(_charset));
        try {
            zipFile.addFolder(new File(folderPath));
            return zipFile;
        } catch (ZipException e) {
            e.printStackTrace();
        }
        return zipFile;
    }

    /**
     * 加密压缩
     * @param zipPath
     * @param fileList
     * @param password
     * @param isAES
     * @return
     */
    public static ZipFile compressFileToZip(String zipPath, List<File> fileList,String password, boolean isAES){
        ZipFile zipFile = new ZipFile(zipPath);
        zipFile.setCharset(Charset.forName(_charset));
        ZipParameters parameters = new ZipParameters();
        if (isAES){
            parameters.setEncryptFiles(true);
            parameters.setEncryptionMethod(EncryptionMethod.AES);
            parameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
        }

        if (password!=null&&!password.isEmpty()){
            zipFile.setPassword(password.toCharArray());
        }

        try {
            zipFile.addFiles(fileList, parameters);
            return zipFile;
        } catch (ZipException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ZIP文件一览
     * @param zipPath
     * @return
     */
    public static List<FileHeader> getZipFileList(String zipPath){
        ZipFile zipFile= new ZipFile(zipPath);
        try {
            zipFile.setCharset(Charset.forName(_charset));
            List<FileHeader> fileHandlers =zipFile.getFileHeaders();
            return fileHandlers;
        } catch (ZipException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解压Zip (不带密码)
     * @param zipPath
     * @param toPath
     * @return
     */
    public static ZipFile unZipAll(String zipPath,String toPath){
        return unZipAll(zipPath, toPath,null);
    }

    /**
     * 解压所有文件（带密码）
     * @param zipPath
     * @param toPath
     * @param password
     * @return
     */
    public static ZipFile unZipAll(String zipPath,String toPath,String password){
        ZipFile zipFile = new ZipFile(zipPath);
        zipFile.setCharset(Charset.forName(_charset));
        if (password!=null&&!password.isEmpty()){
            zipFile.setPassword(password.toCharArray());
        }
        try {
            zipFile.extractAll(toPath);
            return zipFile;
        } catch (ZipException e) {
            e.printStackTrace();
        }
        return null;
    }
}
