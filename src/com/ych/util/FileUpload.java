package com.ych.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;


import Decoder.BASE64Decoder;

public class FileUpload {

	/**
	* 将字符串转为图片
	* @param imgStr
	* @return
	*/
	public static boolean generateImage(String imgStr,String imgpath)throws Exception {// 对字节数组字符串进行Base64解码并生成图片
	if (imgStr == null) // 图像数据为空
	return false;

	try {
	// Base64解码
	byte[] b= new BASE64Decoder().decodeBuffer(imgStr);
	for (int i = 0; i < b.length; ++i) {
	if (b[i] < 0) {// 调整异常数据
	b[i] += 256;
	}
	}
	// 生成jpeg图片
	String imgFilePath = imgpath;// 新生成的图片
	OutputStream out = new FileOutputStream(imgFilePath);
	out.write(b);
	out.flush();
	out.close();
	return true;
	} catch (Exception e) {
	throw e;
	}
	}
	
}
