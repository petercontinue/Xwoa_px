package com.xwcloud.cloud.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class JwtUtils {

    /**
     * 利用jwt解析token信息.
     * @param token 要解析的token信息
     * @param secret 用于进行签名的秘钥
     * @return
     * @throws Exception
     */
    public static Optional<Claims> getClaimsFromToken(String token, String secret) throws Exception {
        Claims claims;
        try {
            claims =Jwts.parser()
                    .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token)
                    .getBody();
            return Optional.of(claims);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     * 验证token是否过期
     * @param tooken 要解析的token信息
     * @param secret 用于进行签名的秘钥
     * @return true 表示过期，false表示不过期，如果没有设置过期时间，则也不认为过期
     * @throws Exception
     */
    public static boolean isExpired(String tooken,String secret) throws Exception{
        Optional<Claims> claims= getClaimsFromToken(tooken,secret);
        if(claims.isPresent()){
            Date expiration = claims.get().getExpiration();
            return expiration.before(new Date());
        }
        return false;
    }

    /**
     * 获取tooken中的参数值
     * @param token 要解析的token信息
     * @param secret 用于进行签名的秘钥
     * @return
     * @throws Exception
     */
    public static Map<String,Object> extractInfo(String token, String secret) {
        Optional<Claims> claims = null;
        try {
            claims = getClaimsFromToken(token,secret);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if(claims.isPresent()){
            Map<String,Object> info = new HashMap<String,Object>();
            Set<String> keySet = claims.get().keySet();
            //通过迭代，提取token中的参数信息
            Iterator<String> iterator = keySet.iterator();
            while(iterator.hasNext()){
                String key = iterator.next();
                Object value =  claims.get().get(key);
                info.put(key,value);

            }
            return info;
        }
        return null;
    }

}
