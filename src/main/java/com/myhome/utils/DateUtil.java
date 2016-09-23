package com.myhome.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
/**
 * ------------------------------------------------------------------
 * 版权所有 Copyright (C) 2014 杭州热讯电子商务有限公司
 *
 * 项目名称：银行接口-服务端
 *
 * 模块名称：日期时间工具类
 *
 * 文件名：DateUtil.java
 * 
 * 创建者： 戴耀强 （kiccleaf@163.com）
 * 
 * 创建时间：2014年5月29日-上午10:52:22
 * 
 * 描述：TODO
 *-------------------------------------------------------------------
 */
public class DateUtil {
	
	public static final String[] zodiacArr = { "猴", "鸡", "狗", "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊" };
	 
	public static final String[] constellationArr = { "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座" };
	 
	public static final int[] constellationEdgeDay = { 20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22 };

	/**
	 * 
	* @Title: addMonth
	* @Description: TODO(月份加monthCount个月)
	* @param @param date 要增加月份的日期，格式为：yyyy-MM-dd
	* @param @param monthCount 增加的月数
	* @param @return    设定文件 返回增加后的日期
	* @return Date    返回类型
	* @throws
	 */
	public static Date addMonth(Date date,int monthCount){
		// 创建格式化格式  
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.MONTH,monthCount);//日期加monthCount个月
        return rightNow.getTime();
	}
	/**
	 * 
	* @Title: addDay
	* @Description: TODO(制定日期增加dayCount天)
	* @param @param date 要增加天数的日期，格式为：yyyy-MM-dd
	* @param @param dayCount 增加的天数
	* @param @return    设定文件 返回增加后的日期
	* @return Date    返回类型
	* @throws
	 */
	public static Date addDay(Date date,int dayCount){
		// 创建格式化格式  
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.DATE,dayCount);//日期加monthCount个月
        return rightNow.getTime();
	}
	/**
	 * 将日期格式化为 yyyy-MM－dd 的字符串
	 * @param date
	 * @return
	 */
	public static String formatDateToStringForYYYYMMDD(Date date){
		String value = null;
		if(date == null)return null;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		value = format.format(date);
		
		return value;
	}
	/**
	 * 将日期格式化为 yyyy年MM月dd日 的字符串
	 * @param date
	 * @return
	 */
	public static String formatDateToString(Date date){
		if(date == null)return null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
		String datestr=sdf.format(date);
		return datestr;
	}
	
	/** 
     * 得到当前日期的月首 格式为：2009-08-01 
     */  
    public static String monthFist() {  
        Calendar localTime = Calendar.getInstance();  
        String strY = null;								// 日期属性：日  
        int x = localTime.get(Calendar.YEAR); 			// 日期属性：年  
        int y = localTime.get(Calendar.MONTH) + 1; 		// 日期属性：月  
        strY = y >= 10 ? String.valueOf(y) : ("0" + y); // 组合月份  
        return x + "-" + strY + "-01"; 					// 最后组合成yyyy-MM-dd形式字符串  
    }  
    
    
  
    /** 
     * 得到上个月月首 格式为：2009-08-01 
     */  
    public static String beforeMonth() {  
        Calendar localTime = Calendar.getInstance();  
        localTime.add(Calendar.MONTH, -1); // 通过提取这个月计算上个月号  
        String strz = null;  
        int x = localTime.get(Calendar.YEAR); // 得到年  
        int y = localTime.get(Calendar.MONTH) + 1; // 得到月  
        strz = y >= 10 ? String.valueOf(y) : ("0" + y);  
        return x + "-" + strz + "-01";  
    }  
  
    /** 
     * 得到当前日期 格式为：2009-08-01 
     */  
    public static String curDate() {  
        // 分别根据日历时间提取当前年月日组合成字符串  
        Calendar localTime = Calendar.getInstance();  
        int x = localTime.get(Calendar.YEAR);  
        int y = localTime.get(Calendar.MONTH) + 1;  
        int z = localTime.get(Calendar.DAY_OF_MONTH);  
        return x + "-" + y + "-" + z;  
    } 
    
    
    /**
     * 指定的日期加一个月
     * @param strdate
     * @return
     */
    @SuppressWarnings("static-access")
	public static String addMonth(String strdate) {  
        Date date = new Date(); // 构造一个日期型中间变量  
        String dateresult = null; // 返回的日期字符串  
        // 创建格式化格式  
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
        // 加减日期所用  
        GregorianCalendar gc = new GregorianCalendar();  
        try {  
            date = df.parse(strdate); // 将字符串格式化为日期型  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        gc.setTime(date); // 得到gc格式的时间  
        gc.add(2, 1); // 2表示月的加减，年代表1依次类推(周,天。。)  
        // 把运算完的时间从新赋进对象  
        gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));  
        // 在格式化回字符串时间  
        dateresult = df.format(gc.getTime());  
        return dateresult;  
    }
    
    
    /** 
     * 给定的日期减一天 格式为：2009-08-01 
     */  
    @SuppressWarnings("static-access")
	public static String subDay(String strdate) {  
        Date date = new Date(); // 构造一个日期型中间变量  
        String dateresult = null; // 返回的日期字符串  
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
        GregorianCalendar gc = new GregorianCalendar();  
        try {  
            date = df.parse(strdate); // 将字符串格式化为日期型  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        gc.setTime(date); // 得到gc格式的时间  
        gc.add(5, -1); // 2表示月的加减，年代表1依次类推(３周....5天。。)  
        // 把运算完的时间从新赋进对象  
        gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));  
        // 在格式化回字符串时间  
        dateresult = df.format(gc.getTime());  
        return dateresult;  
    }  
  
    /** 
     * 给定的日期加一天 格式为：2009-08-01 
     */  
    @SuppressWarnings("static-access")
	public static String addDay(String strdate) {  
        Date date = new Date(); // 构造一个日期型中间变量  
        String dateresult = null; // 返回的日期字符串  
        // 创建格式化格式  
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
        // 加减日期所用  
        GregorianCalendar gc = new GregorianCalendar();  
        try {  
            date = df.parse(strdate); // 将字符串格式化为日期型  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        gc.setTime(date); // 得到gc格式的时间  
        gc.add(5, 1); // 2表示月的加减，年代表1依次类推(３周....5天。。)  
        // 把运算完的时间从新赋进对象  
        gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));  
        // 在格式化回字符串时间  
        dateresult = df.format(gc.getTime());  
        return dateresult;  
    } 
    
    
    /** 
     * 给定的日期加一天 格式为：2009-08-01 
     */  
    @SuppressWarnings("static-access")
	public static String addDay(Date date) {  
    	
    	if(date == null)return null;
    	
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
    	String dateresult = null; 
    	GregorianCalendar gc = new GregorianCalendar(); 
        gc.setTime(date); 
        gc.add(5, 1); // 2表示月的加减，年代表1依次类推(３周....5天。。)  
        
        gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));  
        // 在格式化回字符串时间  
        dateresult = df.format(gc.getTime());  
        return dateresult;  
    } 
    
    /**
     * 根据日期返回星期
     * @param date
     * @return
     */
    public static String getWeekByDate(Date date){
    	
    	if(date == null)return null;
    	String[] week = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};	
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	//SimpleDateFormat dateformat = new SimpleDateFormat("EEE");
		return week[c.get(Calendar.DAY_OF_WEEK)-1];
    	
    }
	/**
	 * 日期转换星期
	 * @param strDate
	 * @return
	 */
	public static String getDateToWeek(Date strDate) {

		final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五","星期六" };
		Calendar c = Calendar.getInstance();
		c.setTime(strDate); // 当时间set 进calendar 里面
		int i = c.get(Calendar.DAY_OF_WEEK); // 取星期
		return dayNames[i - 1];
	}
    
    /**
     * 根据日期返回星期
     * @param date 格式2012－10－23
     * @return星期X
     */
    public static String getWeekByDate(String strDate){
    	
    	if(strDate == null)return null;
    	String[] week = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};	
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = null;
    	try {
			 date = dateFormat.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	//SimpleDateFormat dateformat = new SimpleDateFormat("EEE");
		return week[c.get(Calendar.DAY_OF_WEEK)-1];	
    }
    /**
     * 获取13位时间戳(1401433624722)
     * @return
     */
    public static final String getDateTime() {
    	Date date = new Date();
		long time = date.getTime();
		return String.valueOf(time) ;
		
	}
    /** 日期格式：yyyy */
    public static final String DY = "yyyy";

    /** 日期格式：yyyyMM */
    public static final String DM = "yyyyMM";

    /** 日期格式：yyyyMMdd */
    public static final String DS = "yyyyMMdd";

    /** 日期格式：yyyy-MM-dd */
    public static final String DW = "yyyy-MM-dd";

    /** 日期格式：yyyy-MM-dd HH:mm:ss */
    public static final String DL = "yyyy-MM-dd HH:mm:ss";

    /** 日期格式：yyyyMMddHHmmss */
    public static final String DH = "yyyyMMddHHmmss";
    /**
     * 尝试解析时间。
     * <p/>
     * yyyyMMdd -> yyyy-MM-dd -> yyyy-MM-dd HH:mm:ss
     */
    public static final Date tryParseDate(String dateValue) {
        if (StringUtils.isBlank(dateValue)) {
            return null;
        }

        Date value = null;
        try {
            value = toDateDS(dateValue);
            if (value == null) {
                value = toDateDW(dateValue);

                if (value == null) {
                    value = toDateDL(dateValue);
                }
            }
        } catch (Exception e) {
            // ignore
        }

        return value;
    }

    /**
     * 解析日期：yyyy
     */
    public static final Date toDateDY(String dateValue) {
        return toDate(dateValue, DY);
    }

    /**
     * 解析日期：yyyyMM
     */
    public static final Date toDateDM(String dateValue) {
        return toDate(dateValue, DM);
    }

    /**
     * 解析日期：yyyyMMdd
     */
    public static final Date toDateDS(String dateValue) {
        return toDate(dateValue, DS);
    }

    /**
     * 解析日期：yyyy-MM-dd
     */
    public static final Date toDateDW(String dateValue) {
        return toDate(dateValue, DW);
    }

    /**
     * 解析日期：yyyy-MM-dd HH:mm:ss
     */
    public static final Date toDateDL(String dateValue) {
        return toDate(dateValue, DL);
    }
    /**
     * 解析日期：yyyyMMddHHmmss
     */
    public static final Date toDateDH(String dateValue) {
        return toDate(dateValue, DH);
    }
    /**
     * 解析日期
     */
    public static final Date toDate(String dateValue, String format) {
        Date value = null;
        try {
            value = newDateFormat(format).parse(dateValue);
        } catch (Exception e) {
            // ignore
        }

        return value;
    }

    /**
     * 格式化
     */
    public static final String toString(Date date, String format) {
        String value = null;
        try {
            value = newDateFormat(format).format(date);
        } catch (Exception e) {
            // ignore
        }

        return value;
    }

    /**
     * 格式化：yyyy
     */
    public static final String toStringDY(Date date) {
        return toString(date, DY);
    }

    /**
     * 格式化：yyyyMM
     */
    public static final String toStringDM(Date date) {
        return toString(date, DM);
    }

    /**
     * 格式化：yyyyMMdd
     */
    public static final String toStringDS(Date date) {
        return toString(date, DS);
    }

    /**
     * 格式化：yyyy-MM-dd
     */
    public static final String toStringDW(Date date) {
        return toString(date, DW);
    }

    /**
     * 格式化：yyyy-MM-dd HH:mm:ss
     */
    public static final String toStringDL(Date date) {
        return toString(date, DL);
    }
    /**
     * 格式化：yyyyMMddHHmmss
     */
    public static final String toStringDH(Date date) {
        return toString(date, DH);
    }
    /**
     * 日期格式化器
     */
    public static final DateFormat newDateFormat(String format) {
        return new SimpleDateFormat(format, Locale.getDefault());
    }

    /**
     * 日期格式化器
     */
    public static final DateFormat newDateFormat(String format, Locale locale) {
        return new SimpleDateFormat(format, locale);
    }
    /**
     * 获取字符串日期截取十分钟：yyyy-MM-dd HH:m
     */
    public static final String getStringDate() {
        return toString(new Date(), "yyyy-MM-dd HH:mm").substring(0,15).trim();
    }
    
    /** 
     * 时间戳转换成日期格式字符串 
     * @param seconds 精确到秒的字符串 
     * @param formatStr 
     * @return 
     */  
    public static String timeStamp2Date(String seconds,String format) {  
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){  
            return "";  
        }  
        if(format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";  
        SimpleDateFormat sdf = new SimpleDateFormat(format);  
        return sdf.format(new Date(Long.valueOf(seconds)));  
    }  
    
    public static void main(String[] args) {
        String date = timeStamp2Date("1439192560000", "yyyy年MM月dd HH:mm:ss");  
        
        Date birthday = java.sql.Date.valueOf("2009-09-10");
        
        System.out.println("birthday="+getage(birthday));  
	}
    /**
     * 根据出生日期获得年龄
     * @author lqf
     */
    public static int getage(Date birthday){
    	if(birthday	==null)
    		throw new RuntimeException();
    	int age = 0;
    	Date now = new Date();
    	SimpleDateFormat format_y = new SimpleDateFormat("yyyy");
    	SimpleDateFormat format_M = new SimpleDateFormat("MM");	
    	String birth_year = format_y.format(birthday);
    	String this_year = format_y.format(now);
    	String birth_month = format_M.format(birthday);
    	String this_month = format_M.format(now);
    	age = Integer.parseInt(this_year) - Integer.parseInt(birth_year);
    	if(this_month.compareTo(birth_month)<0){
    		age -=1;
    	}
    	if(age<0){
    		age=0;
    	}
    	return age;
    }
    /**
     * 根据日期获取生肖
     * @return
     */
    public static String getZodica(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return zodiacArr[cal.get(Calendar.YEAR) % 12];
    }
     
    /**
     * 根据日期获取星座
     * @return
     */
    public static String getConstellation(Date date) {
        if (date == null) {
            return "";
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        if (day < constellationEdgeDay[month]) {
            month = month - 1;
        }
        if (month >= 0) {
            return constellationArr[month];
        }
        // default to return 魔羯
        return constellationArr[11];
    }   
    
 
}

