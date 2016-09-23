package com.myhome.utils;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CutImage {
	private static Logger logger = LoggerFactory.getLogger(CutImage.class);
    public static void cutImage(String src,String path,int x,int y,int w,int h) throws IOException{   
    	
    	Long start=System.currentTimeMillis();
    	logger.error("---------------------------------------"+start+"图片生成缩略图开始");
    	String endname = src.substring(src.lastIndexOf(".")+1);
    	if((endname.toLowerCase()).equals("jpg")){
	    	Iterator iterator = ImageIO.getImageReadersByFormatName("jpg");   
	        ImageReader reader = (ImageReader)iterator.next();   
	        InputStream in=new FileInputStream(src);  
	        ImageInputStream iis = ImageIO.createImageInputStream(in);   
	        reader.setInput(iis, true);   
	        ImageReadParam param = reader.getDefaultReadParam();   
	        Rectangle rect = new Rectangle(x, y, w,h);    
	        param.setSourceRegion(rect);   
	        BufferedImage bi = reader.read(0,param);     
	        try {
		        ImageIO.write(bi, "jpg", new File(path));
	        } catch (Exception e) {
	        	logger.error("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+e.getMessage());
		        e.printStackTrace();
	        } 
    	}else if((endname.toLowerCase()).equals("png")){
    		Iterator iterator = ImageIO.getImageReadersByFormatName("png");   
	        ImageReader reader = (ImageReader)iterator.next();   
	        InputStream in=new FileInputStream(src);  
	        ImageInputStream iis = ImageIO.createImageInputStream(in);   
	        reader.setInput(iis, true);   
	        ImageReadParam param = reader.getDefaultReadParam();   
	        Rectangle rect = new Rectangle(x, y, w,h);    
	        param.setSourceRegion(rect);   
	        BufferedImage bi = reader.read(0,param);     
	        try {
		        ImageIO.write(bi, "jpg", new File(path));
	        } catch (Exception e) {
	        	logger.error("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+e.getMessage());
		        e.printStackTrace();
	        } 
    	}else if((endname.toLowerCase()).equals("jpeg")){
    		Iterator iterator = ImageIO.getImageReadersByFormatName("jpeg");   
	        ImageReader reader = (ImageReader)iterator.next();   
	        InputStream in=new FileInputStream(src);  
	        ImageInputStream iis = ImageIO.createImageInputStream(in);   
	        reader.setInput(iis, true);   
	        ImageReadParam param = reader.getDefaultReadParam();   
	        Rectangle rect = new Rectangle(x, y, w,h);    
	        param.setSourceRegion(rect);   
	        BufferedImage bi = reader.read(0,param);     
	        try {
		        ImageIO.write(bi, "jpeg", new File(path));
	        } catch (Exception e) {
	        	logger.error("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+e.getMessage());
		        e.printStackTrace();
	        } 
    	}else if((endname.toLowerCase()).equals("JPG")){
    		Iterator iterator = ImageIO.getImageReadersByFormatName("JPG");   
	        ImageReader reader = (ImageReader)iterator.next();   
	        InputStream in=new FileInputStream(src);  
	        ImageInputStream iis = ImageIO.createImageInputStream(in);   
	        reader.setInput(iis, true);   
	        ImageReadParam param = reader.getDefaultReadParam();   
	        Rectangle rect = new Rectangle(x, y, w,h);    
	        param.setSourceRegion(rect);   
	        BufferedImage bi = reader.read(0,param);     
	        try {
		        ImageIO.write(bi, "JPG", new File(path));
	        } catch (Exception e) {
	        	logger.error("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+e.getMessage());
		        e.printStackTrace();
	        } 
    	}
    	
    	
    	 Long end=System.currentTimeMillis();
         logger.error("---------------------------------------"+(end-start)+"图片生成缩略图结束");

 }  
    public static void main(String[] args) {
        try {
            cutImage("D:\\Users\\new\\webmyhome\\src\\main\\webapp\\11111\\aaaaaab.jpg","D:\\Users\\new\\webmyhome\\src\\main\\webapp\\11111\\aaaaaab1.jpg",1,2,1,2);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
