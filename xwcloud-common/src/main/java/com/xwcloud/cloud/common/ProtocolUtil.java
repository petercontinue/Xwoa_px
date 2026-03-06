package com.xwcloud.cloud.common;

import java.util.HashMap;

public class ProtocolUtil {
    public static HashMap<String,String> parsingStr(String str){
        HashMap<String,String> hashMap = new HashMap<>();
        String[] parameterStrs = str.split(";");
        for (int i = 0; i < parameterStrs.length; i++) {
            String[] keyAndValue = parameterStrs[i].split("=");
            hashMap.put(keyAndValue[0],keyAndValue[1]);
        }
        return hashMap;
    }
}
