package com.xwcloud.cloud.common;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class IpUtil {

    public static String gethostIp() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();//获取的是本地的IP地址 //PC-20140317PXKX/192.168.0.121
        String hostAddress = address.getHostAddress();//192.168.0.121
        return hostAddress;
    }
}
