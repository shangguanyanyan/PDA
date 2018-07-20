package com.jxjr.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateFun {
	private static Calendar cal;  
	private static SimpleDateFormat sdf; 
	private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	/**
	 * 天数加减
	 * @param date
	 * @param day
	 * */
	public static String subDay(String date,int day) throws ParseException{
		cal= Calendar.getInstance();
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d=sdf.parse(date);
		cal.setTime(d);
		cal.add(Calendar.DAY_OF_MONTH, day);
		return sdf.format(cal.getTime());  
	}
	
	/**
	 * 月份加减
	 * @param date
	 * @param month
	 * */
	public static String subMonth(String date,int month) throws ParseException{
		cal= Calendar.getInstance();
		sdf = new SimpleDateFormat("yyyy-MM");
		Date d=sdf.parse(date);
		cal.setTime(d);
		cal.add(Calendar.MONTH, month);
		return sdf.format(cal.getTime());  
	}
	
	public static String getCurrentDate(String pattern) {
		sdf = new SimpleDateFormat(pattern);
		Date today = new Date();
		String tString = sdf.format(today);
		return tString;
	}

	public static String getCurrentTime() {
		String pattern = "HH:mm:ss";
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		Date today = new Date();
		String tString = df.format(today);
		return tString;
	}


	/**
	 * 得到UTC时间，类型为字符串，格式为"yyyy-MM-dd HH:mm" 如果获取失败，返回null
	 * 
	 * @return
	 */
	public static long getUTCTime(String time, String format) {
		Calendar cal = Calendar.getInstance();
		try {
			// 设置时间
			cal.setTime(new SimpleDateFormat(format).parse(time));
			// 2、取得时间偏移量：
			int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);
			// 3、取得夏令时差：
			int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);
			cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
			return cal.getTimeInMillis();
		} catch (ParseException e) {
			return 0;
		}
	}

	/**
	 * 将UTC时间转换为东八区时间
	 * 
	 * @param UTCTime
	 * @return
	 */
	public static String getLocalTimeFromUTC(String UTCTime) {
		java.util.Date UTCDate = null;
		String localTimeStr = null;
		try {
			UTCDate = format.parse(UTCTime);
			format.setTimeZone(TimeZone.getTimeZone("GMT-8"));
			localTimeStr = format.format(UTCDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return localTimeStr;
	}

	public static String Date2String(Date d, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(d);
	}

	public static Date String2Date(String date, String format) {
		sdf = new SimpleDateFormat(format);
		if (date.equals(""))
			return null;
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getCurrent() {
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = new Date();
		String tString = sdf.format(today);
		return tString;
	}

	
	public static void main(String[] args) throws ParseException {
		System.out.println(DateFun.subDay("2016-05-01", -1));
		System.out.println(DateFun.subDay("2016-05-31", 1));
		System.out.println(DateFun.subMonth("2016-01-31", -1));
		System.out.println(DateFun.subMonth("2016-12-31", 1));
	}
}
