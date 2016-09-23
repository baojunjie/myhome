package com.myhome.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageUpload {
	private static Logger logger = LoggerFactory.getLogger(ImageUpload.class);

	public static Map<String, String> imageUpload(String imageFile, String path) {
		Map<String, String> map = new HashMap<String, String>();
		// String filePath = "";
		String fileName = "";
		String orginimg = "";
		String thumb_path = "";
		BASE64 decoder = new BASE64();
		try {
			// 获取存储路径
			String rootPath = BaseCodeParam.getObject("image_upload");
			String image_url = BaseCodeParam.getObject("image_url");
			// 图片存取文件夹
			// filePath = DateUtil.toStringDS(new Date());
			// 图片名称
			fileName = DateUtil.getDateTime() + String.valueOf((int) ((Math.random() * 9 + 1) * 100000)) + ".jpg";

			// File file = new File("src/main/webapp/"+rootPath + "/" +
			// filePath);

			File file = new File(rootPath);

			// File file = new File("src/main/webapp/"+rootPath + "/" +
			// filePath);

			logger.info("----------------!file.exists()------------" + !file.exists());
			if (!file.exists()) {
				file.mkdirs();
			}
			// Base64解码
			byte[] b = decoder.decryptBASE64(imageFile.replaceAll(" ", "+"));
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			// OutputStream out = new
			// FileOutputStream("src/main/webapp/"+rootPath+"/"+filePath+"/"+fileName);
			OutputStream out = null;
			try {
				out = new FileOutputStream(rootPath + "/" + fileName);
				out.write(b);
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("---------------------------------上传失败----------------------" + e.getMessage());
			} finally {
				out.close();
			}

			orginimg = image_url + "/" + fileName;
			thumb_path = image_url + "/" + "thumb_" + fileName;
			logger.info("----------------orginimg------------" + orginimg.length());
			logger.info("----------------thumb_path------------" + thumb_path.length());
			// new
			// ImageUtil().thumbnailImage("src/main/webapp/"+rootPath+"/"+filePath+"/"+fileName,
			// 150, 100);
			// new
			// ImageUtil().thumbnailImage("src/main/webapp/"+rootPath+"/"+filePath+"/"+fileName,
			// 150, 100);
			new ImageUtil().thumbnailImage(rootPath + "/" + fileName, 750, 750);
			map.put("orginimg", orginimg);
			map.put("thumb_path", thumb_path);
		} catch (Exception e) {
			logger.error("---------------------------------上传失败----------------------");
			logger.error("---------------------------------上传失败----------------------" + e.getMessage());
			return null;
		}
		return map;
	}
}

// public class ImageUpload {
// private static Logger logger = LoggerFactory.getLogger(ImageUpload.class);
// public static Map<String,String> imageUpload(String imageFile,String path ) {
// Map<String,String> map=new HashMap<String, String>();
// // String filePath = "";
// String fileName = "";
// String orginimg = "";
// String thumb_path="";
// BASE64 decoder = new BASE64();
// try {
// // 获取存储路径
// String rootPath = BaseCodeParam.getObject("image_upload")+"/"+path;
// String image_url = BaseCodeParam.getObject("image_url")+"/"+path;
// // 图片存取文件夹
// // filePath = DateUtil.toStringDS(new Date());
// // 图片名称
// fileName = DateUtil.getDateTime()
// + String.valueOf((int) ((Math.random() * 9 + 1) * 100000))
// + ".jpg";
//
// // File file = new File("src/main/webapp/"+rootPath + "/" + filePath);
// File file = new File(rootPath);
// // File file = new File("src/main/webapp/"+rootPath + "/" + filePath);
//
// logger.info("----------------!file.exists()------------"+!file.exists());
// if (!file.exists()) {
// file.mkdirs();
// }
// // Base64解码
// byte[] b = decoder.decryptBASE64(imageFile.replaceAll(" ", "+"));
// for (int i = 0; i < b.length; ++i) {
// if (b[i] < 0) {// 调整异常数据
// b[i] += 256;
// }
// }
// // 生成jpeg图片
// // OutputStream out = new
// FileOutputStream("src/main/webapp/"+rootPath+"/"+filePath+"/"+fileName);
// OutputStream out = null;
// try {
// out = new FileOutputStream(rootPath+"/"+fileName);
// out.write(b);
// out.flush();
// out.close();
// } catch (Exception e) {
// e.printStackTrace();
// logger.error("---------------------------------上传失败----------------------"+e.getMessage());
// }finally{
// out.close();
// }
//
// orginimg=image_url+"/"+fileName;
// thumb_path=image_url+"/"+"thumb_"+fileName;
// logger.info("----------------orginimg------------"+orginimg.length());
// logger.info("----------------thumb_path------------"+thumb_path.length());
// // new
// ImageUtil().thumbnailImage("src/main/webapp/"+rootPath+"/"+filePath+"/"+fileName,
// 150, 100);
// // new
// ImageUtil().thumbnailImage("src/main/webapp/"+rootPath+"/"+filePath+"/"+fileName,
// 150, 100);
// new ImageUtil().thumbnailImage(rootPath+"/"+fileName, 750, 750);
// map.put("orginimg", orginimg);
// map.put("thumb_path", thumb_path);
// } catch (Exception e) {
// logger.error("---------------------------------上传失败----------------------");
// logger.error("---------------------------------上传失败----------------------"+e.getMessage());
// return null;
// }
// return map;
// }
// }
