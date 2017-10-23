package com.quark.chat.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 时间日期的工具类
 */
public class DateTimeUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(DateTimeUtil.class);
	private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	private static final String DEFAULT_TIME_PATTERN = "HH:mm:ss";

    /**
     * 获取当天的字符串
     * @return
     */
    public static String getTodayStr(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        return sdf.format(new Date());
    }

    public static String getTodayStr2(){
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        return sdf.format(new Date());
    }

	public static String getCurrentTime(){
		return getCurrentTime(DEFAULT_TIME_PATTERN);
	}


    /**
     * 获取当前时间的字符串
     * @return
     */
    public static String getCurrentDateTime(){
    	return getCurrentTime(DEFAULT_DATE_PATTERN);
    }
    
    /**
     * 获取当前时间的字符串
     * @param format 字符串格式，如：yy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurrentTime(String format){
    	SimpleDateFormat sdf = new SimpleDateFormat(format);
    	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    	return sdf.format(timestamp);
    }
    
    /**
     * 获取当前的月份
     * @return
     */
    public static String getCurrentMonth(){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        return sdf.format(new Date());
    }

    /**
     * 比较两个时间，如果返回大于0，time1大于time2,如果返回-1，time1小于time2，返回0则相等
     * @param time1
     * @param time2
     * @return
     * @throws ParseException
     */
    public static int compareTime(String time1,String time2) throws ParseException{
    	SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
    	Date date1 = sdf.parse(time1);
    	Date date2 = sdf.parse(time2);
    	long result = date1.getTime() - date2.getTime();
    	if(result > 0){
    		return 1;
    	}else if(result==0){
    		return 0;
    	}else{
    		return -1;
    	}
    }
    
    /**
     * 转换字符串成日期对象
     * @param dateStr 日期字符串
     * @param format 格式，如：yy-MM-dd HH:mm:ss
     * @return
     */
    public static Date convertStrToDate(String dateStr,String format){
    	if(!BlankUtil.isBlank(dateStr)&&!BlankUtil.isBlank(format)){
    		try{
	    		SimpleDateFormat sdf = new SimpleDateFormat(format);
	    		return sdf.parse(dateStr);
	    	}catch (Exception e) {
	    		logger.warn("convertDate fail, date is "+ dateStr, e);
			}
    	}
    	return null;
    }
    
    /**
     * 把字符串日期转换成另一种格式
     * @param dateStr 字符串日期
     * @param format 转换日期格式
     * @param otherFormat 转换日期格式
     * @return
     */
    public static String convertDate(String dateStr,String format,String otherFormat){
    	try{
	    	Date date = convertStrToDate(dateStr, format);
			SimpleDateFormat sdf = new SimpleDateFormat(otherFormat);
			return sdf.format(date);
    	}catch (Exception e) {
    		logger.warn("convertDate fail, date is "+ dateStr, e);
		}
    	return null;
    }
    
    /**
     * 把字符串日期转换成另一种格式
     * @param dateStr 字符串日期
     * @param format 转换格式
     * @return
     */
    public static String convertDate(String dateStr,String format){
    	return convertDate(dateStr, DEFAULT_DATE_PATTERN,format);
    }
    
    
    public static void main(String[] args) {
    	System.out.println(convertDate("2015-11-10 20:33", "yy年MM月dd日 hh时"));
		System.out.println(getCurrentMonth());
	}
}
