package com.xwcloud.cloud.common;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * $.ajax后需要接受的JSON
 * 
 * @author
 * 
 */
@Slf4j
public class AjaxJson implements Serializable {

	private boolean success = true;// 是否成功
	private String msg = "操作成功";// 提示信息
	private Object obj = null;// 其他信息
	private String code="Y";//错误代码
	private Object attributes;// 其他参数
	public Object getAttributes() {
		return attributes;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
		if (!success){
			this.msg= this.msg.equals("操作成功")?"操作失败":this.msg;
		}
	}

	/**
	 * 使用response输出AjaxJson
	 * @Author hjp
	 * @CreateTime 2020/12/06 11:23
	 * @Param  json AjaxJson数据
	 * @Return void
	 */
	public static void responseJson(ServletResponse response, AjaxJson json){
		PrintWriter out = null;
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			out = response.getWriter();
			out.println(JSON.toJSONString(json));
		} catch (Exception e) {
			//log.error("【JSON输出异常】"+e);
			AjaxJson ajaxJson = new AjaxJson();
			ajaxJson.setCode("500");
			ajaxJson.setMsg("【JSON输出异常】"+e);
			AjaxJson.responseJson(response,ajaxJson);
		}finally{
			if(out!=null){
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 使用response输出Map Json
	 * @Author hjp
	 * @CreateTime 2020/12/06 11:23
	 * @Param  resultMap 数据
	 * @Return void
	 */
	public static void responseMap(ServletResponse response, Map<String, Object> resultMap){
		PrintWriter out = null;
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			out = response.getWriter();
			out.println(JSON.toJSONString(resultMap));
		} catch (Exception e) {
			//log.error("【JSON输出异常】"+e);
			AjaxJson ajaxJson = new AjaxJson();
			ajaxJson.setCode("500");
			ajaxJson.setMsg("【JSON输出异常】"+e);
			AjaxJson.responseJson(response,ajaxJson);
		}finally{
			if(out!=null){
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 返回成功示例
	 * @Author Sans
	 * @CreateTime 2019/9/28 11:29
	 * @Param  resultMap  返回数据MAP
	 * @Return Map<String,Object> 返回数据MAP
	 */
	public static Map<String, Object> resultSuccess(Map<String, Object> resultMap){
		resultMap.put("message","操作成功");
		resultMap.put("code", 200);
		return resultMap;
	}
	/**
	 * 返回失败示例
	 * @Author Sans
	 * @CreateTime 2019/9/28 11:31
	 * @Param  resultMap  返回数据MAP
	 * @Return Map<String,Object> 返回数据MAP
	 */
	public static Map<String, Object> resultError(Map<String, Object> resultMap){
		resultMap.put("message","操作失败");
		resultMap.put("code",500);
		return resultMap;
	}
	/**
	 * 通用示例
	 * @Author Sans
	 * @CreateTime 2019/9/28 11:35
	 * @Param  code 信息码
	 * @Param  msg  信息
	 * @Return Map<String,Object> 返回数据MAP
	 */
	public static Map<String, Object> resultCode(Integer code,String msg){
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("message",msg);
		resultMap.put("code",code);
		return resultMap;
	}

}
