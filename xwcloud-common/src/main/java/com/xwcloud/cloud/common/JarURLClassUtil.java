package com.xwcloud.cloud.common;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 调用外部Jar包工具类，可链式调用
 */
public class JarURLClassUtil {
    private Class clazz;
    private Method method;
    private URLClassLoader urlClassLoader;

    /**
     * 构造函数
     *
     * @param filePath    文件路径
     * @param packageName 包名称，全名，例: com.test.package
     */
    public JarURLClassUtil(String filePath, String packageName) throws Exception {
        setClazz(filePath, packageName);
    }

    /**
     * 设置jar包
     *
     * @param filePath    文件路径
     * @param packageName 包名称，全名，例: com.test.package
     * @return
     * @throws Exception
     */
    public JarURLClassUtil setClazz(String filePath, String packageName) throws Exception {
        File file = new File(filePath);
        if (!file.isFile()) {
            throw new Exception("没有找到文件");
        }
        URL url = file.toURI().toURL();
        // 使用静态的类加载器
        urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        // 将URL添加进静态类加载器中，静态类加载器需要这样调用
        Method add = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});
        add.setAccessible(true);
        add.invoke(urlClassLoader, new Object[]{url});
        // 加载jar包名称
        clazz = urlClassLoader.loadClass(packageName);
        return this;
    }

    /**
     * 设置调用类
     *
     * @param methodName    方法名
     * @param parameterType 形参类型
     * @return 返回值
     */
    public JarURLClassUtil setMethod(String methodName, Class[] parameterType) throws NoSuchMethodException {
        if (parameterType == null) {
            method = clazz.getDeclaredMethod(methodName);
        } else {
            method = clazz.getDeclaredMethod(methodName, parameterType);
        }
        return this;
    }

    /**
     * 设置调用类
     *
     * @param methodName    方法名
     * @param parameterType 形参类型
     * @return 返回值
     * @throws Exception
     */
    public JarURLClassUtil setMethod(String methodName, String[] parameterType) throws NoSuchMethodException {
        if (parameterType == null) {
            method = clazz.getDeclaredMethod(methodName);
        } else {
            method = clazz.getDeclaredMethod(methodName, getTypeClazzArray(parameterType));
        }
        return this;
    }

    public Class getClazz() {
        return clazz;
    }

    public Method getMethod() {
        return method;
    }

    /**
     * 调用方法
     *
     * @param args 实参
     * @return 调用方法返回值
     * @throws Exception
     */
    public Object call(Object[] args) throws Exception {
        method.setAccessible(true); // 解除私有限制
        Object object = null;
        if (args == null) {
            object = method.invoke(clazz.newInstance());
            /*urlClassLoader.close();*/
        } else {
            object = method.invoke(clazz.newInstance(), args);//需要两个参数，一个是要调用的对象（获取有反射），一个是实参
            /*urlClassLoader.close();*/
        }
        return object;
    }

    /**
     * 将字符串数组转换成class数组
     *
     * @param strings
     * @return
     */
    public static Class[] getTypeClazzArray(String[] strings) {
        Class[] clazzs = new Class[strings.length];
        for (int i = 0; i < strings.length; i++) {
            clazzs[i] = getTypeClazz(strings[i]);
        }
        return clazzs;
    }

    /**
     * 将字符串数组转换成class
     *
     * @param
     * @return
     * @throws ClassNotFoundException
     */
    public static Class getTypeClazz(String str) {
        String tmpstr = str;
        if (str.contains("byte")) {
            tmpstr = str.replace("byte", "Byte");
        } else if (str.contains("char")) {
            tmpstr = str.replace("char", "Character");
        } else if (str.contains("short")) {
            tmpstr = str.replace("short", "Short");
        } else if (str.contains("int")) {
            tmpstr = str.replace("int", "Integer");
        } else if (str.contains("long")) {
            tmpstr = str.replace("long", "Long");
        } else if (str.contains("float")) {
            tmpstr = str.replace("float", "Float");
        } else if (str.contains("double")) {
            tmpstr = str.replace("double", "Double");
        } else if (str.contains("boolean")) {
            tmpstr = str.replace("boolean", "Boolean");
        }
        try {
            return Class.forName("java.lang." + tmpstr);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将值转换成对应类型
     *
     * @param value
     * @param type
     * @return
     */
    public static Object StrTransformObject(String value, String type) {
        Object tmp_value = value;
        if (type.contains("byte") || type.contains("Byte")) {
            tmp_value = Byte.parseByte(value);
        } else if (type.contains("char") || type.contains("Character")) {
            tmp_value = value.charAt(0);
        } else if (type.contains("short") || type.contains("Short")) {
            tmp_value = Short.parseShort(value);
        } else if (type.contains("int") || type.contains("Integer")) {
            tmp_value = Integer.parseInt(value);
        } else if (type.contains("long") || type.contains("Long")) {
            tmp_value = Long.parseLong(value);
        } else if (type.contains("float") || type.contains("Float")) {
            tmp_value = Float.parseFloat(value);
        } else if (type.contains("double") || type.contains("Double")) {
            tmp_value = Double.parseDouble(value);
        } else if (type.contains("boolean") || type.contains("Boolean")) {
            tmp_value = Boolean.parseBoolean(value);
        }

        return tmp_value;
    }

    /**
     * 格式化参数类型，并用XML进行参数顺序验证
     *
     * @param xml      参数格式
     * @param nodeName 参数所在的节点
     * @param attrName 参数名称所在节点的属性名称(排序属性名)
     * @return
     */
    public static List<String> FormatParByXML(String xml, String nodeName, String attrName) throws ParserConfigurationException, SAXException, IOException {
        List<String> list = new ArrayList<String>();
        Document doc = null;
        doc = XmlUtil.StrParseForDoc(xml);
        Node appSettingsNode = doc.getElementsByTagName(nodeName).item(0);
        NodeList nodeList = appSettingsNode.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element) {
                Element element = (Element) node;
                String mapkey = element.getAttribute(attrName);
                list.add(mapkey);
            }
        }
        return list;
    }

    /**
     * 验证实参 ， 并根据XML排序
     *
     * @param xml          参数格式
     * @param nodeName     参数所在的节点
     * @param attrName     参数名称所在节点的属性名称(排序属性名)
     * @param typeAttrName 类型属性名称
     * @param cmdMap       参数
     * @return
     */
    public static List<Object> FormatArgByXML(String xml, String nodeName, String attrName, String typeAttrName, Map<String, String> cmdMap) throws IllegalAccessException, InstantiationException, ParserConfigurationException, SAXException, IOException {
        List<Object> list = new ArrayList<Object>();
        Document doc = null;
        doc = XmlUtil.StrParseForDoc(xml);
        Node appSettingsNode = doc.getElementsByTagName(nodeName).item(0);
        NodeList nodeList = appSettingsNode.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element) {
                Element element = (Element) node;
                // 获取key值
                String mapkey = element.getAttribute(attrName);
                // 获取类型字符串
                String typeStr = element.getAttribute(typeAttrName);
                // 转换成对应类型的实参
                Object object = StrTransformObject(cmdMap.get(mapkey), typeStr);
                list.add(object);
            }
        }
        return list;
    }

    /**
     * 执行jar包
     *
     * @param jarPath   jar包路径
     * @param envps     传入参数
     * @param jarFolder jar包所在的文件夹路径
     * @throws IOException
     */
    public static void callJAR(String jarPath, String[] envps, String jarFolder) throws IOException {
        // String param1,Integer param2,Boolean param3
        Process process = null;
        String args = "";
        for (int i = 0; i < envps.length; i++) {
            args += " " + envps[i];
        }
        // 传参顺序不能改变
        String cmd = "java " + args + " -jar " + jarPath;
        System.out.println("---------CMD命令字符串---------");
        System.out.println(cmd);
        process = Runtime.getRuntime().exec(cmd, null, new File(jarFolder));
        InputStream in = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(in, "GBK");
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

    }

    /**
     * 格式化实参为字符串数组
     *
     * @param xml      参数格式
     * @param nodeName 参数所在的节点
     * @param attrName 参数名称所在节点的属性名称(排序属性名)
     * @param cmdMap   参数
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static String[] FormatArgToStrArray(String xml, String nodeName, String attrName, Map<String, String> cmdMap) throws ParserConfigurationException, SAXException, IOException {
        List<Object> list = new ArrayList<Object>();
        Document doc = null;
        doc = XmlUtil.StrParseForDoc(xml);
        Node appSettingsNode = doc.getElementsByTagName(nodeName).item(0);
        NodeList nodeList = appSettingsNode.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element) {
                Element element = (Element) node;
                // 获取key值
                String mapkey = element.getAttribute(attrName);
                String value = cmdMap.get(mapkey);
                if (value == null) {
                    list.add("-D" + mapkey + "=");
                } else {
                    list.add("-D" + mapkey + "=" + value);
                }

            }
        }
        return list.toArray(new String[]{});
    }
}
