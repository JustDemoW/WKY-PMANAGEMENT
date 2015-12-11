package com.sys.pm.util;

import java.util.Random;
import java.util.UUID;

public class VerifyUtil {
	public static String createVerifyCode(){
		UUID uuid = UUID.randomUUID();
		String codestr = uuid.toString();
		String[] emailcodes = codestr.split("-");
		Random random = new Random();
		String emailcode = emailcodes[random.nextInt(4)].toUpperCase();
		return emailcode;
	}
}
