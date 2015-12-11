package com.sys.pm.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public final class ImageUtil {
	private static final int SIZE = 5;//显示字符个数
	private static final int LINES = 20;//干扰线数量
	private static final int WIDTH = 200;//图片宽度
	private static final int HEIGHT = 100;//图片高度
	private static final int FONT_SIZE = 60;//字体大小
	
	public static Map<String,BufferedImage> createImage() {
		StringBuffer sb = new StringBuffer();
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		Graphics graphic = image.getGraphics();
		graphic.setColor(Color.LIGHT_GRAY);
		graphic.fillRect(0, 0, WIDTH, HEIGHT);
		String[] chars =getCodeByLength(SIZE);
		Random ran = new Random();
		//画随机字符
		for(int i=1;i<=SIZE;i++){
			int r = ran.nextInt(chars.length);
			graphic.setColor(getRandomColor());
			graphic.setFont(new Font(null,Font.BOLD+Font.ITALIC,FONT_SIZE));
			graphic.drawString(chars[r],(i-1)*WIDTH/SIZE , HEIGHT/2);
			sb.append(chars[r]);//将字符保存，存入Session
		}
		//画干扰线
		for(int i=1;i<=LINES;i++){
			graphic.setColor(getRandomColor());
			graphic.drawLine(ran.nextInt(WIDTH), ran.nextInt(HEIGHT), ran.nextInt(WIDTH),ran.nextInt(HEIGHT));
		}
		Map<String,BufferedImage> map = new HashMap<String,BufferedImage>();
		map.put(sb.toString(), image);
		return map;
	}
	
	public static InputStream change(BufferedImage image) throws Exception{
		//将image图片压缩成JPEG
		ByteArrayOutputStream bos = 
				new ByteArrayOutputStream();
		JPEGImageEncoder encode = 
			JPEGCodec.createJPEGEncoder(bos);
		encode.encode(image);
		//将bos中存储的JPEG格式图片字节取出
		byte[] bytes = bos.toByteArray();
		return new ByteArrayInputStream(bytes);
	}
	
	private static Color getRandomColor(){
		Random ran = new Random();
		Color color = new Color(ran.nextInt(256),ran.nextInt(256),ran.nextInt(256));
		return color;
	}
	public static String[] getCodeByLength(int size){
		String[] chars = new String[size];
		Random random = new Random();
		for (int i = 0; i < size; i++) {
			if (random.nextInt(2)==0) {
				chars[i]=(char)(random.nextInt(9)+49)+"";
			}else {
				chars[i]=(char)(random.nextInt(25)+66)+"";
			}
		}
		return chars;	
	}
}
