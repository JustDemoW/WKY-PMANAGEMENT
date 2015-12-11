package com.sys.pm.common;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Map;

import com.sys.pm.util.ImageUtil;


/**
 * 生成验证码图片
 * @author Administrator
 *
 */
public class CheckCodeAction extends BaseAction {
	//input
	private String code;
	//output
	private InputStream imagestream;
	private boolean ok = false;

	public String check(){
		String sessioncode = (String)session.get("code");
		if(code.equalsIgnoreCase(sessioncode)){
			ok = true;
		}
		return "check_success";
	}
	public String img() throws Exception{
		Map<String, BufferedImage> map = ImageUtil.createImage();
		String code = map.keySet().iterator().next();
		session.put("code", code);
		BufferedImage image = map.get(code);
		imagestream = ImageUtil.change(image);
		return "img_success";
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public InputStream getImagestream() {
		return imagestream;
	}
	public void setImagestream(InputStream imagestream) {
		this.imagestream = imagestream;
	}
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
}
