package com.amayadream.email.tools;

import java.util.Calendar;

/**
 * 获取当前时间的工具类
 * @author:Amayadream
 * @date:2015-8-2 下午10:13:18
 */
public class getDate {
	Calendar now = Calendar.getInstance();		//初始化
	String year = String.valueOf(now.get(Calendar.YEAR));
	String month = String.valueOf(now.get(Calendar.MONTH)+1);
	String day = String.valueOf(now.get(Calendar.DAY_OF_MONTH));
	String hour = String.valueOf(now.get(Calendar.HOUR_OF_DAY));
	String minute = String.valueOf(now.get(Calendar.MINUTE));
	String second = String.valueOf(now.get(Calendar.SECOND));
	
	/**
	 * 获取当前年份
	 * @return
	 */
	public String getYear(){
		return year;
	}
	
	/**
	 * 获取当前月份
	 * @return
	 */
	public String getMonth(){
		return month;
	}
	
	/**
	 * 获取当前日期
	 * @return
	 */
	public String getDay(){
		return day;
	}
	
	/**
	 * 获取当前小时
	 * @return
	 */
	public String getHour(){
		return hour;
	}
	
	/**
	 * 获取当前分钟
	 * @return
	 */
	public String getMinute(){
		return minute;
	}
	
	/**
	 * 获取当前秒
	 * @return
	 */
	public String getSecond(){
		return second;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDateNow(){
		return year + "-" + (Integer.parseInt(month) < 10 ? ("0" + month) : month) + "-" + (Integer.parseInt(day) < 10 ? ("0" + day) : day);
	}
	
	/**
	 * 按照格式获取当前时间
	 * @param full	int,是否满位,0为否,1为是
	 * @param tag	String,-是用-作为连接符,a是使用年月日
	 * @return
	 */
	public String getDateNow(String isFull,String tag){
		String date = "";
		if(isFull=="full"){
			if(tag=="-"){
				date = year + "-" + (Integer.parseInt(month) < 10 ? ("0" + month) : month) + "-" + (Integer.parseInt(day) < 10 ? ("0" + day) : day);
			}
			if(tag=="a"){
				date = year + "年" + (Integer.parseInt(month) < 10 ? ("0" + month) : month) + "月" + (Integer.parseInt(day) < 10 ? ("0" + day) : day + "日");
			}
			else{
				date = year + "-" + (Integer.parseInt(month) < 10 ? ("0" + month) : month) + "-" + (Integer.parseInt(day) < 10 ? ("0" + day) : day);
			}
		}
		else{
			if(tag=="-"){
				date = year + "-" + month + "-" + day;
			}
			if(tag=="a"){
				date = year + "年" + month + "月" + day + "日";
			}
			else{
				date = year + "-" + month + "-" + day;
			}
		}
		return date;
	}

	public String getTimeNow(){
		return (Integer.parseInt(hour) < 10 ? ("0" + hour) : hour) + ":" + (Integer.parseInt(minute) < 10 ? ("0" + minute) : minute) + ":" + (Integer.parseInt(second) < 10 ? ("0" + second) : second);
	}
	
	public String getDateTime(){
		return year + "-" + (Integer.parseInt(month) < 10 ? ("0" + month) : month) + "-" + (Integer.parseInt(day) < 10 ? ("0" + day) : day) + " " + (Integer.parseInt(hour) < 10 ? ("0" + hour) : hour) + ":" + (Integer.parseInt(minute) < 10 ? ("0" + minute) : minute) + ":" + (Integer.parseInt(second) < 10 ? ("0" + second) : second);
	}
	
	/**
	 * 按照出生年份参数计算年龄
	 * @param year	出生年份
	 * @return
	 */
	public int getAge(int year){
		int age = Integer.parseInt(getYear()) - year;
		return age;
	}
}
