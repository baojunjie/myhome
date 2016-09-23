package com.myhome.utils;
/**
 * 图片路径
 * @author lqf
 *
 */
public class PicturePath {
    
    //基础路径
    public static String basePath = BaseCodeParam.getObject(BaseCodeParam.imageUpload);
    //艺术家图片上传
    public static String artist = basePath+"/artist";
    //根菜单url
    public static String works = basePath+"/works";
    //连接目标
    public static String teacher = basePath+"/teacher";
    //幸运小朋友头像
    public static String helpchild = basePath+"/helpchild";
    //幸运小朋友附加图片
    public static String helppic = basePath+"/helppic";
    //公司logo
    public static String comlogo = basePath+"/comlogo";
    //公司营业执照
    public static String comlicence = basePath+"/comlicence";
    //公司证书文件
    public static String comfile = basePath+"/comfile";
    //老师照片
    public static String teacherphoto = basePath+"/teacherphoto";
    //用户头像
    public static String user = basePath+"/user";
    //法人身份证(正面)
    public static String comidCodePositive = basePath+"/comidCodePositive";
    //法人身份证(反面)
    public static String comidCodeBack = basePath+"/comidCodeBack";
    //临时文件夹
    public static String temp = basePath+"/temp";

    public static String myBalcony = basePath+"/myBalcony";;
   

}