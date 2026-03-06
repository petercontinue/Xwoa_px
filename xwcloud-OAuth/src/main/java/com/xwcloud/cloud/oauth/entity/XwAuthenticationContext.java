package com.xwcloud.cloud.oauth.entity;

public class XwAuthenticationContext {
    private static ThreadLocal<XwAuthentication> holder = new ThreadLocal<>();

    public static void set(XwAuthentication xwAuthentication){
        holder.set(xwAuthentication);
    }

    public static XwAuthentication get(){
        return holder.get();
    }

    public static void remove(){
        holder.remove();
    }
}
