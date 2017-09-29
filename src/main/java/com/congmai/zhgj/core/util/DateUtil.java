package com.congmai.zhgj.core.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;

public class DateUtil {
	
	/**
	 * 日期所在月的天数
	 * 如：“2011-02-02”所在2月份的天数为28天
	 * @param date
	 * @return
	 */
	public static int getDayCountInMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 得到日期所在月的最后一天
	 * 如：“2011-02-02”所在2月份的最后一天为“2011-02-28”
	 * @param date
	 * @return
	 */
	public static Date getMonthLastDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, getDayCountInMonth(date));

		return calendar.getTime();
	}
	
	/**
	 * 得到日期所在月的第一天
	 * 如：“2011-02-02”所在2月份的第一天为“2011-02-01”
	 * @param date
	 * @return
	 */
	public static Date getMonthFirstDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);

		return calendar.getTime();
	}
	
	/**
	 * 为日期添加指定的月数
	 * @param date
	 * @param num
	 * @return
	 */
	public static Date addMonth(Date date, int num) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, num);
		return cal.getTime();
	}
	
	/**
	 * 为日期增加指定天数
	 * @param date 日期
	 * @param num 数量
	 * @return 增加后的日期
	 * 
	 */
	public static Date addDay(Date date, int num) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, num);
		return cal.getTime();
	}
	
	/**
	 * 格式化日期
	 * @param formatStr 日期格式。如“yyyy-MM-dd”
	 * @param date
	 * @return
	 */
	public static String format(String formatStr, Date date) {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		return format.format(date);
	}
	
	/**
	 * 将格式化的日期字符串转换成日期。
	 * @param formatStr 日期格式。如“yyyy-MM-dd”
	 * @param dateStr 格式化的日期字符串。如“2011-02-16”
	 * @return
	 * @throws ParseException
	 */
	public static Date parse(String formatStr, String dateStr) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		return format.parse(dateStr);
	}
	
	/**
	 * 得到日期在月份中的天数。
	 * 如“2011-02-08”在二月份中的天数为8
	 * @param date
	 * @return
	 */
	public static String getDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
	}
	
	/**
	 * 将日期的时、分、秒和毫秒设置为零，只留下年月日。
	 * 如“2011-02-03 23:12:34.345” 转换为“2011-02-03 00:00:00.000”
	 * @param date
	 * @return
	 */
	public static Date getYMDDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		return calendar.getTime();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		String formatStr = "yyyy-MM-dd HH:mm:ss.SSS";
		String dateStr = "2011-02-08 20:34:28.128";
		
		Date date = parse(formatStr, dateStr);
	/*	System.out.println(format(formatStr, addDay(date, 1)));
		System.out.println(format(formatStr, addDay(date, -1)));
		System.out.println(format(formatStr, addMonth(date, 1)));
		System.out.println(format(formatStr, addMonth(date, -1)));
		System.out.println(format(formatStr, getMonthFirstDay(date)));
		System.out.println(format(formatStr, getMonthLastDay(date)));
		System.out.println(format(formatStr, getMonthLastDay(addMonth(date, -1))));
		System.out.println(getDayOfMonth(date));
		System.out.println(format(formatStr, getYMDDate(date)));*/
		System.out.println(getYearFirstDay() );
		
		/*String time = formatDateTime("2015-10-12 12:41:00");
		System.out.println("time:"+time);
		time = formatDateTime("2015-10-12 05:45:00");
		System.out.println("time:"+time);
		time = formatDateTime("2015-10-10 15:43:00");
		System.out.println("time:"+time);*/
		
	}
	
	
	public static String asString(java.util.Date date) {
		if (date == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date);
	}

	public static String remainTime(String startTime, String limitDay) {
		SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		SimpleDateFormat format2 = new java.text.SimpleDateFormat("yyyy-MM-dd");
		Date fromDate = null;
		try {
			if(startTime.length()==10){
				fromDate = format2.parse(startTime);
			}else{
				fromDate = format.parse(startTime);
			}
			fromDate = DateUtil.addDay(fromDate,Integer.parseInt(limitDay));
			Date toDate = new Date();  
			long from = fromDate.getTime();  
			long to = toDate.getTime();  
			int days = (int) ((from - to)/(1000 * 60 * 60 * 24));  
			fromDate = DateUtil.addDay(fromDate,-days);
			from = fromDate.getTime();
			int hours = (int) ((from - to)/(1000 * 60 * 60));
					
			return days+"天"+hours+"小时";
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 格式化时间
	 * @param time
	 * @return
	 */
	public static String formatDateTime(String time) {
		SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		SimpleDateFormat format2 = new java.text.SimpleDateFormat("yyyy-MM-dd");
		if(time==null ||"".equals(time)){
			return "";
		}
		Date date = null;
		try {
			if(time.length()==10){
				date = format2.parse(time);
			}else{
				date = format.parse(time);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Calendar current = Calendar.getInstance();
		
		Calendar tomorrow = Calendar.getInstance();	//明天
		
		tomorrow.set(Calendar.YEAR, current.get(Calendar.YEAR));
		tomorrow.set(Calendar.MONTH, current.get(Calendar.MONTH));
		tomorrow.set(Calendar.DAY_OF_MONTH,current.get((Calendar.DAY_OF_MONTH))+1);
		//  Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
		tomorrow.set( Calendar.HOUR_OF_DAY, 0);
		tomorrow.set( Calendar.MINUTE, 0);
		tomorrow.set(Calendar.SECOND, 0);
		
		
		Calendar today = Calendar.getInstance();	//今天
		
		today.set(Calendar.YEAR, current.get(Calendar.YEAR));
		today.set(Calendar.MONTH, current.get(Calendar.MONTH));
		today.set(Calendar.DAY_OF_MONTH,current.get(Calendar.DAY_OF_MONTH));
		//  Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
		today.set( Calendar.HOUR_OF_DAY, 0);
		today.set( Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		
		Calendar noontoday = Calendar.getInstance();	//今天中午
		
		noontoday.set(Calendar.YEAR, current.get(Calendar.YEAR));
		noontoday.set(Calendar.MONTH, current.get(Calendar.MONTH));
		noontoday.set(Calendar.DAY_OF_MONTH,current.get(Calendar.DAY_OF_MONTH));
		noontoday.set( Calendar.HOUR_OF_DAY, 12);
		noontoday.set( Calendar.MINUTE, 0);
		noontoday.set(Calendar.SECOND, 0);
		
		current.setTime(date);
		
		if(current.after(noontoday) && current.before(tomorrow)){
			return "下午"+time.split(" ")[1].substring(0,5);
		}else if(current.after(today) && current.before(noontoday)){
			return "上午"+time.split(" ")[1].substring(0,5);
		}else{
			return new SimpleDateFormat("MM月dd日").format(date);
		}
	}
	
	/**
	 * 传入时间与当前时间间隔
	 * @param createtime
	 * @return
	 */
	public static String getInterval(String createtime) { //传入的时间格式必须类似于2012-8-21 17:53:20这样的格式  
        String interval = null;  
          
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        ParsePosition pos = new ParsePosition(0);  
        Date d1 = (Date) sd.parse(createtime, pos);  
          
        //用现在距离1970年的时间间隔new Date().getTime()减去以前的时间距离1970年的时间间隔d1.getTime()得出的就是以前的时间与现在时间的时间间隔  
        long time = new Date().getTime() - d1.getTime();// 得出的时间间隔是毫秒  
          
        if(time/1000 < 10 && time/1000 >= 0) {  
        //如果时间间隔小于10秒则显示“刚刚”time/10得出的时间间隔的单位是秒  
            interval ="刚刚";  
              
        } else if(time/1000 < 60 && time/1000 > 0) {  
        //如果时间间隔小于60秒则显示多少秒前  
            int se = (int) ((time%60000)/1000);  
            interval = se + "秒前";  
              
        } else if(time/60000 < 60 && time/60000 > 0) {  
        //如果时间间隔小于60分钟则显示多少分钟前  
            int m = (int) ((time%3600000)/60000);//得出的时间间隔的单位是分钟  
            interval = m + "分钟前";  
              
        } else if(time/3600000 < 24 && time/3600000 >= 0) {  
        //如果时间间隔小于24小时则显示多少小时前  
            int h = (int) (time/3600000);//得出的时间间隔的单位是小时  
            interval = h + "小时前";  
              
        }else if(time/(3600000*24) <= 7 && time/(3600000*24)>0){
        	 int date = (int) (time/(3600000*24));//得出的时间间隔的单位是小时  
             interval = date + "天前";
        }else{  
            //大于24小时，则显示正常的时间，但是不显示秒  
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
  
            ParsePosition pos2 = new ParsePosition(0);  
            Date d2 = (Date) sdf.parse(createtime, pos2);  
  
            interval = sdf.format(d2);  
        }  
        return interval;  
    }  


    public static int daysBetween(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }

	public static long timeBetween(Date smdate,Date bdate) throws ParseException
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		smdate=sdf.parse(sdf.format(smdate));
		bdate=sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();

		return time2-time1;
	}
	public static  String getYearFirstDay() throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-01-01");  
		Date date=new Date();
		String dateString=sdf.format(date);
		return dateString;
	}   
	
	/**
	 * 格式化日期为字符串，格式为年月日时分秒
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateTimess(Date date) {
		String result = "";
		if (date != null) {
			result = DateFormatUtils.format(date,
					"yyyy-MM-dd HH:mm:ss");
		}
		return result;
	}
}  
