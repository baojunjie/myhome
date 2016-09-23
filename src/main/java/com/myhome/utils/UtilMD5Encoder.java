package com.myhome.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.dao.DataAccessException;

public class UtilMD5Encoder {
	public static String encodePassword(String origPwd, Object salt) throws DataAccessException {
		String password = new SimpleHash("md5", origPwd, ByteSource.Util.bytes(salt), 2).toHex();
		return password;// UtilMD5.getMD5ofStr(origPwd+saltStr);
	}

	public static boolean isPasswordValid(String encPwd, String origPwd, Object salt) throws DataAccessException {
		String password = new SimpleHash("md5", origPwd, ByteSource.Util.bytes(salt), 2).toHex();
		return encPwd.equals(password);
	}

	public static void main(String[] args) {
		System.out.print(encodePassword("111111", "tvcteb"));
		String password = new SimpleHash("md5", "111111", ByteSource.Util.bytes("seijcm"), 2).toHex();
		System.out.println(password);
		System.out.println(UtilMD5Encoder.isPasswordValid(password, "gwb", "111111"));
	}
//	public static String encodePassword(String origPwd, Object salt)
//			throws DataAccessException {
//		String saltStr = (String)salt;
//		return UtilMD5.getMD5ofStr(origPwd+saltStr);
//	}
//
//	public static boolean isPasswordValid(String encPwd, String origPwd, Object salt)
//			throws DataAccessException {
//		return encPwd.equals(encodePassword(origPwd, salt));
//	}
//	
//	public static void main(String[] args) {
//	    System.out.println( UtilMD5.getMD5ofStr("123456"));
//	  //  ,
//	   System.out.println( UtilMD5.getMD5ofStr("123456").toLowerCase());
//	   
//	   
//	  System.out.println( encodePassword("e10adc3949ba59abbe56e057f20f883e","cjsunt"));
//    }
}
