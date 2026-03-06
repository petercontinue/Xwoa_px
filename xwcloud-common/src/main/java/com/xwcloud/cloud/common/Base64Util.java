package com.xwcloud.cloud.common;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Base64Util {
    /**
     * Base64编码
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String Base64Encoded(String str) throws UnsupportedEncodingException {
        BASE64Encoder encoder = new BASE64Encoder();
        byte[] textByte = str.getBytes("UTF-8");
        return encoder.encode(textByte);
    }

    /**
     * Base64解码
     * @param str
     * @return
     * @throws IOException
     */
    public static String Base64Decoder(String str) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
       return new String(decoder.decodeBuffer(str), "UTF-8");
    }
}
