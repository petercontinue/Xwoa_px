package com.xwcloud.cloud.common;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EXEUtil {
    /**
     * 启动exe执行分析调度
     */

    public static void callEXE(String exePath, List<String> cmd) throws IOException {
        System.out.println("执行EXE传参");
        cmd.add(0, exePath);
        Process process = null;
        ProcessBuilder pb = new ProcessBuilder(cmd);
        //pb.command(cmd);
        process = pb.start();
        System.out.println("拉起EXE程序");
        InputStream in = process.getInputStream();
        System.out.println("获取输出流");
        InputStreamReader isr = new InputStreamReader(in, "GBK");
        System.out.println("获取输出缓存");
        BufferedReader br = new BufferedReader(isr);

        while ( process.isAlive()) {
            if (br.readLine()!=null){
                String line =br.readLine();
                System.out.println("读取输出字符串");
                System.out.println(line);
            }else {
                System.out.println("输出字符串为null,但是没有销毁进程"); 
            }
            System.out.println("在获取字符串循环中");
        }
        System.out.println("已经跳出循环");

            System.out.println("销毁进程");
            process.destroy();

    }

    /**
     * 格式化 参数，并用XML进行参数顺序验证
     *
     * @param xml      参数格式
     * @param nodeName 参数所在的节点
     * @param attrName 参数名称所在节点的属性名称
     * @param cmdMap   参数
     * @throws Exception
     */
    public static List<String> FormatArgByXML(String xml, String nodeName, String attrName, Map<String, String> cmdMap) throws ParserConfigurationException, SAXException, IOException {
        List<String> list = new ArrayList<String>();
        Document doc = null;
        doc = XmlUtil.parseForDoc(xml,"UTF-8");
        Node appSettingsNode = doc.getElementsByTagName(nodeName).item(0);
        NodeList nodeList = appSettingsNode.getChildNodes();
        for (int i=0;i<nodeList.getLength();i++){
            Node node = nodeList.item(i);
            if(node instanceof Element){
                Element element = (Element) node;
                String mapkey = element.getAttribute(attrName);
                list.add(cmdMap.get(mapkey));
            }
        }
        return list;
    }

    /**
     * 格式化 参数，并用XML进行参数顺序验证
     *
     * @param xml      参数格式
     * @param nodeName 参数所在的节点
     * @param attrName 参数名称所在节点的属性名称
     * @param cmdMap   参数
     * @throws Exception
     */
    public static List<String> FormatArgByXMLIsupdata(String xml, String nodeName, String attrName, Map<String, String> cmdMap) throws ParserConfigurationException, SAXException, IOException {
        List<String> list = new ArrayList<String>();
        Document doc = null;
        doc = XmlUtil.parseForDoc(xml,"UTF-8");
        Node appSettingsNode = doc.getElementsByTagName(nodeName).item(0);
        NodeList nodeList = appSettingsNode.getChildNodes();
        for (int i=0;i<nodeList.getLength();i++){
            Node node = nodeList.item(i);
            if(node instanceof Element){
                Element element = (Element) node;
                String mapkey = element.getAttribute(attrName);
                boolean ispath=isPath(element);
                if (ispath){
                    list.add(cmdMap.get("RKSJ"));
                }else {
                    list.add(cmdMap.get(mapkey));
                }
            }
        }
        return list;
    }

    public static boolean isPath(Element element){
        return element.getAttribute("ispath").equals("true");
    }
}
