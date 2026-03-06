package com.xwcloud.cloud.common;

import org.apache.commons.lang.StringUtils;

import javax.net.ssl.*;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class HTTPUtils {

    private static SSLContext sslContext = null;

    private static HostnameVerifier verifier = new HostnameVerifier()
    {
        public boolean verify(String s, SSLSession sslSession) {
            return false;
        }
    };

    private static SSLSocketFactory sslSocketFactory = null;




    public static String doPost(String url, Map<String, String> params, int connectTimeout, int readTimeout)
            throws IOException
    {
        return doPost(url, params, "UTF-8", connectTimeout, readTimeout);
    }

    public static String doPost(String url, Map<String, String> params, String charset, int connectTimeout, int readTimeout) throws IOException {
        String contentType = new StringBuilder().append("application/x-www-form-urlencoded;charset=").append(charset).toString();
        String query = buildQuery(params, charset);
        byte[] content = new byte[0];
        if (query != null) {
            content = query.getBytes(charset);
        }
        return doPost(url, contentType, content, connectTimeout, readTimeout);
    }

    public static String doPost(String url, String contentType, byte[] content, int connectTimeout, int readTimeout) throws IOException {
        String responseStr = null;
        HttpURLConnection conn = null;
        OutputStream out = null;
        URL Url=null;
        try {
            if(url.startsWith("https")){
                Url = new URL(null,url,new sun.net.www.protocol.https.Handler());
            }else{
                Url= new URL(url);
            }
            conn = getConnection(Url, "POST", contentType);
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            try
            {
                out = conn.getOutputStream();
                out.write(content);
                responseStr = getResponseAsString(conn);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (out != null) {
                out.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return responseStr;
    }

    private static HttpURLConnection getConnection(URL url, String method, String contentType) throws IOException {
        URLConnection connection = null;
        if ("https".equals(url.getProtocol())) {
            HttpsURLConnection httpsConn = (HttpsURLConnection)url.openConnection();
            httpsConn.setSSLSocketFactory(sslSocketFactory);
            httpsConn.setHostnameVerifier(verifier);
            connection = httpsConn;
        } else {
            connection = url.openConnection();
        }
        ((HttpURLConnection)connection).setRequestMethod(method);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestProperty("Accept", "text/xml,text/javascript,text/html");
        connection.setRequestProperty("User-Agent", "aop-sdk-java");
        connection.setRequestProperty("Content-Type", contentType);
        return (HttpURLConnection)connection;
    }

    public static String buildQuery(Map<String, String> params, String charset) throws IOException
    {
        if ((params != null) && (!params.isEmpty())) {
            StringBuilder query = new StringBuilder();
            boolean hasParam = false;
            Iterator iterator = params.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry entry = (Entry)iterator.next();
                String name = (String)entry.getKey();
                String value = (String)entry.getValue();
                if (StringUtil.areNotEmpty(new String[] { name, value })) {
                    if (hasParam)
                        query.append("&");
                    else {
                        hasParam = true;
                    }
                    query.append(name).append("=").append(URLEncoder.encode(value, charset));
                }
            }
            return query.toString();
        }
        return null;
    }



    protected static String getResponseAsString(HttpURLConnection connection) throws IOException
    {
        String charset = getResponseCharset(connection.getContentType());
        InputStream es = connection.getErrorStream();
        if (es == null) {
            return getStreamAsString(connection.getInputStream(), charset);
        }
        String msg = getStreamAsString(es, charset);
        if (StringUtils.isEmpty(msg)) {
            throw new IOException(new StringBuilder().append(connection.getResponseCode()).append(":").append(connection.getResponseMessage()).toString());
        }
        throw new IOException(msg);
    }

    private static String getStreamAsString(InputStream stream, String charset) throws IOException
    {
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
            StringWriter writer = new StringWriter();
            char[] chars = new char[256];
            boolean count = false;
            int count1;
            while ((count1 = reader.read(chars)) > 0) {
                writer.write(chars, 0, count1);
            }
            String var6 = writer.toString();
            return var6;
        } finally {
            if (stream != null)
                stream.close();
        }
    }

    private static String getResponseCharset(String ctype)
    {
        String charset = "UTF-8";
        if (!StringUtils.isEmpty(ctype)) {
            String[] params = ctype.split(";");
            String[] var3 = params;
            int var4 = params.length;

            for (int var5 = 0; var5 < var4; var5++) {
                String param = var3[var5];
                param = param.trim();
                if (param.startsWith("charset")) {
                    String[] pair = param.split("=", 2);
                    if ((pair.length != 2) || (StringUtils.isEmpty(pair[1]))) break;
                    charset = pair[1].trim(); break;
                }

            }

        }

        return charset;
    }

    static
    {
        try
        {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new SecureRandom());
            sslContext.getClientSessionContext().setSessionTimeout(15);
            sslContext.getClientSessionContext().setSessionCacheSize(1000);
            sslSocketFactory = sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class DefaultTrustManager
            implements X509TrustManager
    {
        public void checkClientTrusted(X509Certificate[] var1, String var2)
                throws CertificateException
        {
        }

        public void checkServerTrusted(X509Certificate[] var1, String var2)
                throws CertificateException
        {
        }

        public X509Certificate[] getAcceptedIssuers()
        {
            return null;
        }
    }


    public static HashMap<String, String>ParamToHashMap (HttpServletRequest request){
        HashMap<String, String> map = new HashMap<String, String> ();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String) temp.nextElement();
                String value = request.getParameter(en);
                if (value==null||value.equals("null")){
                    value="";
                }
                map.put(en, value);
            }
        }
       return map;
    }
}
