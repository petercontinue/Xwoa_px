package com.xwcloud.cloud.common;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.util.Base64;

public class HttpAuthUtils {

    public static HttpResponse postAuth(String url, String auth, JSONObject obj){
        try {
            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpPost httppost = new HttpPost(url);
            //添加http头信息
            byte[] authbase = Base64.getEncoder().encode(auth.getBytes());
            String sauth = new String(authbase);
            System.out.println(sauth);
            httppost.addHeader("Authorization", "Basic "+sauth); //认证token
            httppost.addHeader("Content-Type", "application/json");
            httppost.addHeader("User-Agent", "imgfornote");
            httppost.setEntity(new StringEntity(obj.toString()));
            HttpResponse response;
            response = httpclient.execute(httppost);
            //检验状态码，如果成功接收数据
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HttpResponse getAuth(String url,String auth){
        try {
            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpGet httpget = new HttpGet(url);
            //添加http头信息
            byte[] authbase = Base64.getEncoder().encode(auth.getBytes());
            String sauth = new String(authbase);
            System.out.println(sauth);
            httpget.addHeader("Authorization", "Basic "+sauth); //认证token
            httpget.addHeader("Content-Type", "application/json");
            httpget.addHeader("User-Agent", "imgfornote");
            HttpResponse response;
            response = httpclient.execute(httpget);
            //检验状态码，如果成功接收数据
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static HttpResponse putAuth(String url,String auth,JSONObject obj){
        try {
            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpPut httpput = new HttpPut(url);
            //添加http头信息
            byte[] authbase = Base64.getEncoder().encode(auth.getBytes());
            String sauth = new String(authbase);
            System.out.println(sauth);
            httpput.addHeader("Authorization", "Basic "+sauth); //认证token
            httpput.addHeader("Content-Type", "application/json");
            httpput.addHeader("User-Agent", "imgfornote");
            httpput.setEntity(new StringEntity(obj.toString()));
            HttpResponse response;
            response = httpclient.execute(httpput);
            //检验状态码，如果成功接收数据
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static HttpResponse deleteAuth(String url,String auth,JSONObject obj){
        try {
            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpDelete httpdelete = new HttpDelete(url);
            //添加http头信息
            byte[] authbase = Base64.getEncoder().encode(auth.getBytes());
            String sauth = new String(authbase);
            System.out.println(sauth);
            httpdelete.addHeader("Authorization", "Basic "+sauth); //认证token
            httpdelete.addHeader("Content-Type", "application/json");
            httpdelete.addHeader("User-Agent", "imgfornote");
            HttpResponse response;
            response = httpclient.execute(httpdelete);
            //检验状态码，如果成功接收数据
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
