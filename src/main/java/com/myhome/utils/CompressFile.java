package com.myhome.utils;     
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipInputStream;

import org.springframework.web.multipart.MultipartFile;

import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;
 
/**
 * <p>
 * Title: 解压缩文件
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: yourcompany
 * </p>
 * 
 * @author yourcompany
 * @version 1.0
 */
public class CompressFile {
     
 
    /**
     * 压缩文件
     * 
     * @param srcfile
     *            File[] 需要压缩的文件列表
     * @param zipfile
     *            File 压缩后的文件
     */
    public static void ZipFiles(MultipartFile file, String path) {
        try {
		      File targetFileArtist = new File(path, file.getOriginalFilename());
               if (!targetFileArtist.exists()) {
                   targetFileArtist.mkdirs();
               }
               file.transferTo(targetFileArtist);
		      if(file.getOriginalFilename().endsWith(".rar")) {
		    	    unRarFile(targetFileArtist.getPath());
		    	    targetFileArtist.delete();
		        	System.out.println("压缩完成.");
		        }else if(file.getOriginalFilename().endsWith(".zip")){
		        	unZip(targetFileArtist.getPath());
		        	targetFileArtist.delete();
		        	System.out.println("压缩完成.");
		        }else{
		        	System.out.println("压缩文件格式不对.");
		        }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("出错-------"+e.toString());
        }
    }
    /** 
    * 根据原始rar路径，解压到指定文件夹下.      
    * @param srcRarPath 原始rar路径 
    * @param dstDirectoryPath 解压到的文件夹      
    */
    public static void unRarFile(String path) {
    	String savepath="";
    	if(path.matches(".*/.*")){
        	savepath = path.substring(0, path.lastIndexOf("/")) + "/";  
        }else{
        	savepath = path.substring(0, path.lastIndexOf("\\")) + "\\";
        } 
    	File fileold = new File(savepath);
        if (!fileold.exists()) {// 目标目录不存在时，创建该文件夹
        	fileold.mkdirs();
        }
        Archive a = null;
        try {
            a = new Archive(new File(path));
            if (a != null) {
                a.getMainHeader().print(); // 打印文件信息.
                FileHeader fh = a.nextFileHeader();
                while (fh != null) {
                    if (fh.isDirectory()) { // 文件夹 
//                        File fol = new File(savepath + File.separator
//                                + fh.getFileNameString());
//                        fol.mkdirs();
                    } else { // 文件
                        File out = new File(savepath+ (fh.getFileNameString().matches(".*/.*")?fh.getFileNameString().substring(fh.getFileNameString().lastIndexOf("/")).trim():fh.getFileNameString().substring(fh.getFileNameString().lastIndexOf("\\")).trim()));
                        //System.out.println(out.getAbsolutePath());
                        try {// 之所以这么写try，是因为万一这里面有了异常，不影响继续解压. 
                            if (!out.exists()) {
                                if (!out.getParentFile().exists()) {// 相对路径可能多级，可能需要创建父目录. 
                                    out.getParentFile().mkdirs();
                                }
                                out.createNewFile();
                            }
                            FileOutputStream os = new FileOutputStream(out);
                            a.extractFile(fh, os);
                            os.close();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    fh = a.nextFileHeader();
                }
                a.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static final int buffer = 2048; 
    public static void main(String[] args) {
    	String path ="D:/Users/20150107/myhome_web2/src/main/webapp/temp/961181/zzzzz.rar";
    	unRarFile(path);    	 
	}
    public static void unZip(String path)   
    {  
        int count = -1;  
        int index = -1;  
          
        String savepath = "";  
        boolean flag = false;  
        if(path.matches(".*/.*")){
        	savepath = path.substring(0, path.lastIndexOf("/")) + "/";  
        }else{
        	savepath = path.substring(0, path.lastIndexOf("\\")) + "\\";
        }  
        try   
        {  
            BufferedOutputStream bos = null;  
            java.util.zip.ZipEntry entry = null;  
            FileInputStream fis = new FileInputStream(path);   
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));  
              
            while((entry = zis.getNextEntry()) != null) 
            {  
                byte data[] = new byte[buffer];   
  
                String temp = entry.getName();  
                flag = isPics(temp);  
                if(!flag)  
                    continue;  
                  
                index = temp.lastIndexOf("/");  
                if(index > -1)  
                    temp = temp.substring(index+1);  
                temp = savepath + temp;  
                  
                File f = new File(temp);  
                f.createNewFile();  
  
                FileOutputStream fos = new FileOutputStream(f);  
                bos = new BufferedOutputStream(fos, buffer);  
                  
                while((count = zis.read(data, 0, buffer)) != -1)   
                {  
                    bos.write(data, 0, count);  
                }  
  
                bos.flush();  
                bos.close();  
            }  
  
            zis.close();  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
      
    public static boolean isPics(String filename)  
    {  
        boolean flag = false;  
          
        if(filename.endsWith(".jpg") || filename.endsWith(".gif")  || filename.endsWith(".bmp") || filename.endsWith(".png")||filename.endsWith(".JPG")||filename.endsWith(".jpeg") )  
            flag = true;  
          
        return flag;  
    }  
 
}
