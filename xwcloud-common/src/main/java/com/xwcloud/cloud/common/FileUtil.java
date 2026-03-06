package com.xwcloud.cloud.common;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {

    /**
     * 方法 1：使用 BufferedWriter 写文件 - 推荐优先使用方法 1
     * @param filepath 文件目录
     * @param content  待写入内容
     * @throws IOException
     */
    public static void writeSysLog(String filepath, String content) throws IOException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String nowTime = df.format(new Date());// new Date()为获取当前系统时间
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filepath,true))) {
            bufferedWriter.write("\n"+nowTime+" "+content);
        }
    }

    /**
     * 方法 2：使用 FileWriter 写文件
     * @param filepath 文件目录
     * @param content  待写入内容
     * @throws IOException
     */
    public static void writeSysLog2(String filepath, String content) throws IOException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String nowTime = df.format(new Date());// new Date()为获取当前系统时间
        try (FileWriter fileWriter = new FileWriter(filepath,true)) {
            fileWriter.append("\n"+nowTime+" "+content);
        }
    }
}
