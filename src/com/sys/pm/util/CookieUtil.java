package com.sys.pm.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sys.pm.common.Constant;





/**
 * ��װcookie�������ߣ���cookie���У�ɾ������ӣ���ѯ�Ĳ�����
 * ��ѯ������cookie��nameȡ��value
 * ɾ��������cookie��name���ÿ�ֵ������ӵ�cookie��
 * @author Administrator
 *
 */
public class CookieUtil {
	//���Cookie���ͻ���
	public static void addCookie(String name,String value,HttpServletResponse response) throws UnsupportedEncodingException{
		Cookie cookie = new Cookie(name,URLEncoder.encode(value, "utf-8"));
		cookie.setMaxAge(Constant.COOKIE_AGE_DEFAULT);
		cookie.setPath(Constant.COOKIE_PATH);
		response.addCookie(cookie);
	}
	//ɾ��Cookie
	public static void delCookie(String name,HttpServletResponse response){
		Cookie cookie = new Cookie(name,"");
		cookie.setMaxAge(0);
		cookie.setPath(Constant.COOKIE_PATH);
		response.addCookie(cookie);
	}
	//�ӿͻ���Ѱ��Cookie
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
