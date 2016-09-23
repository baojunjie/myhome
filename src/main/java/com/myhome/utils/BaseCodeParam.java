package com.myhome.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

/**
 * 
*项目名称：fqmall
*包名： com.yuanjing.fqmall.utils
*类名称：BaseCodeParam
*类描述：读取system.properties文件中的配置属性，实际开发中自行编辑
*创建人：张斌龙
*创建时间：2014年7月30日下午2:56:12
*修改人：张斌龙
*修改时间：2014年7月30日下午2:56:12
*修改备注：
*@version
 */
public class BaseCodeParam {
	//菜单连接url
	public static String menuUrl = "manager_menu_pid.html";
	//根菜单url
	public static String rootMenuUrl = "manager_add_rootmenu.html";
	//连接目标
	public static String target = "ifrmRight";
	//根菜单pid
	public static String rootPid = "0";
	//根菜单parendid
	public static String rootParendid = "root";
	//根菜单名称
	public static String permissionName = "根菜单";
	public static String imageUpload = "image_upload";
	public static String imageUrl = "image_url";
	public static String getObject(String key){
		Properties prop = new Properties();
		try {
			Reader inputStream = new InputStreamReader(BaseCodeParam.class.getClassLoader().getResourceAsStream("system.properties"),"UTF-8");
			prop.load(inputStream);
			return prop.get(key).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
	    System.out.print(getObject(imageUpload));
//	    System.out.print(getObject(imageUrl));
    }
}
