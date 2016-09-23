package com.myhome.utils;

import java.util.List;


public class CommonUtils {
	    public CommonUtils() {
	    }
	    /**
	     * 判断 List<T>为空
	     * @author lqf
	     * @param <T>
	     */
	    public static <T> boolean isEmpty(List<T> list) {
	        if(list!=null&&list.size()>0){
	         return false;
	         }else{
	        	return true; 
	         }
	    }
	    /**
	     * 判断 List<T>不为空
	     * @author lqf
	     * @param <T>
	     */
	    public static <T> boolean isNotEmpty(List<T> list) {
	        if(list!=null&&list.size()>0){
	         return true;
	         }else{
	        	return false; 
	         }
	    }

}
