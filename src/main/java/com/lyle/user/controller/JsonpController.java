package com.lyle.user.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.util.JSONPObject;

/**
 * @author Lyle
 */
@Controller
public class JsonpController {
	
	/**
	 * 手动拼装jsonp格式
	 * @param isCallBack
	 * @param response
	 */
	@RequestMapping(value="/assembly.htm",method=RequestMethod.GET)
	public void get(String isCallBack,HttpServletResponse response){
		HashMap<String, Object> user = new HashMap<String, Object>();
		user.put("name", "乔布斯");
		user.put("career","geek");
		String json = JSON.toJSONString(user);
		 try {  
			 response.setContentType("text/html");
			 response.setCharacterEncoding("UTF-8");
	         response.getWriter().write(isCallBack + "(" + json +")");
	     } catch (IOException e) {  
	         e.printStackTrace();  
	     }  
	}
	
	/**
	 * 使用com.fasterxml.jackson.databind.util.JSONPObject;
	 * @param isCallBack
	 * @return
	 */
	@RequestMapping(value="/jsonp.htm",method=RequestMethod.GET)
	public @ResponseBody JSONPObject jsonp(String isCallBack){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", "张三丰");
		map.put("kongfu", "太极拳");
		map.put("age", 100);
		map.put("sex", "男");
		return new JSONPObject(isCallBack, map);
	}
}
