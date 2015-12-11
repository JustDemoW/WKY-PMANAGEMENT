package com.sys.pm.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sys.pm.common.Constant;





/**
 * 封装cookie操作工具，对cookie进行：删除，添加，查询的操作。
 * 查询：依据cookie的name取出value
 * 删除：依据cookie的name设置空值重新添加到cookie中
 * @author Administrator
 *
 */
public class CookieUtil {
	//添加Cookie到客户端
	public static void addCookie(String name,String value,HttpServletResponse response) throws UnsupportedEncodingException{
		Cookie cookie = new Cookie(name,URLEncoder.encode(value, "utf-8"));
		cookie.setMaxAge(Constant.COOKIE_AGE_DEFAULT);
		cookie.setPath(Constant.COOKIE_PATH);
		response.addCookie(cookie);
	}
	//删除Cookie
	public static void delCookie(String name,HttpServletResponse response){
		Cookie cookie = new Cookie(name,"");
		cookie.setMaxAge(0);
		cookie.setPath(Constant.COOKIE_PATH);
		response.addCookie(cookie);
	}
	//从客户端寻找Cookie
	public static String findCookie(String name,HttpServletRequest request) throws Exception{
		String value = null;
		Cookie[] cookies = request.getCookies();
		for (int i=0;i<cookies.length;i++) {
			Cookie cookie =cookies[i];
			if(cookie.getName().equals(name)){
				value = URLDecoder.decode(cookie.getValue(), "utf-8");
			}
		}
		return value;
	}
}
