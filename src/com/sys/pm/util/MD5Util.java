package com.sys.pm.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class MD5Util {
	/**
	 * MD5����
	 * @param psw
	 * @return 
	 */
	public static String digestMD5(String psw){
		MessageDigest md;
		try {
			//��ԭ�ַ�������MD5���ܳ�byte[]
			md = MessageDigest.getInstance("MD5");
			byte[] bbs = md.digest(psw.getBytes());
			//����Base64�㷨��byte[]������ַ���
			BASE64Encoder base64 = new BASE64Encoder();
			String s = base64.encode(bbs);
			return s;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
}
