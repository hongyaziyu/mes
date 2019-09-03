package cn.ssm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Title: getNextDay.java
 * @Package cn.ssm.util
 * @Description:
 * @author 魏亮旗
 * @date 2017-6-8 下午5:16:22
 * @version V1.0
 */
public class getNextDay {

	@SuppressWarnings("static-access")
	public static String getNextDate(Date date) {
		if (date != null) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			String nextday = sf.format(date);
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
			nextday = sf.format(calendar.getTime());
			return nextday;
		}
		return null;

	}

	@SuppressWarnings("static-access")
	public static String getNextDate(String dateString) {
		if (dateString != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date;
			try {
				date = sdf.parse(dateString);
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
				String nextday = sdf.format(calendar.getTime());
				return nextday;
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return null;
		}
		return null;

	}

	public static void main(String[] args) {
		Date date = new Date();// 新建此时的的系统时间
		System.out.println(getNextDate(date));// 返回明天的时间
		String dateString = "1009-1-0";
		System.out.println(getNextDate(dateString));
	}

}
