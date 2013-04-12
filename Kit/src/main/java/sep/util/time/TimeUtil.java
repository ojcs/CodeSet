package sep.util.time;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.DECEMBER;
import static java.util.Calendar.JANUARY;
import static java.util.Calendar.MONDAY;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.SUNDAY;
import static java.util.Calendar.WEEK_OF_YEAR;
import static java.util.Calendar.YEAR;
import static java.util.Calendar.getInstance;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public final class TimeUtil {
	/** 判断当前日期是否在两个日期之间 */
	public static boolean betweenStartDateAndEndDate(final Date begin, final Date end) {
		Date current = new Date();
		return current.after(begin) && current.before(end);
	}

	/** 根据生日去用户年龄 */
	public static int birthdayToAge(Date birthday) {
		if (birthday == null) {
			return -1;
		} else if (new Date().before(birthday)) {
			return 0;
		}
		
		final int yearNow = dateGet(YEAR);
		final int yearBirth = dateGet(birthday, YEAR);
		return yearNow - yearBirth;
	}

	protected static Date dateAdd(Date date, int field, int amount) {
		Calendar calendar = getInstance();
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}

	protected static Date dateAdd(int field, int amount) {
		Calendar calendar = getInstance();
		calendar.add(field, amount);
		return calendar.getTime();
	}

	protected static int dateGet(Date date, int field) {
		Calendar calendar = getInstance();
		calendar.setTime(date);
		return calendar.get(field);
	}

	protected static int dateGet(int field) {
		return getInstance().get(field);
	}

	protected static Date dateSet(Date date, int field, int value) {
		Calendar calendar = getInstance();
		calendar.setTime(date);
		calendar.set(field, value);
		return calendar.getTime();
	}

	/** 上上月第一天，时间为当前时间 */
	public static Date getFirstDayOfLastLastMonth() {
		Calendar calendar = getInstance(); 
		calendar.set(DAY_OF_MONTH, 1);
		calendar.add(MONTH, -2);
		return calendar.getTime();
	}

	/** 上上周第一天(星期一) */
	public static Date getFirstDayOfLastLastWeek(){
		Calendar calendar = getInstance();
		// 默认时，每周第一天为星期日，需要更改为星期一
		calendar.setFirstDayOfWeek(MONDAY);
		calendar.add(DAY_OF_MONTH, -14);
		calendar.set(DAY_OF_WEEK, MONDAY);
		return calendar.getTime();
	}

	/** 上月第一天，时间为当前时间 */
	public static Date getFirstDayOfLastMonth() {
		Calendar calendar = getInstance(); 
		calendar.set(DAY_OF_MONTH, 1);
		calendar.add(MONTH, -1);
		return calendar.getTime();
	}

	/** 上周第一天(星期一) */
	public static Date getFirstDayOfLastWeek(){
		Calendar calendar = getInstance();
		// 默认时，每周第一天为星期日，需要更改为星期一
		calendar.setFirstDayOfWeek(MONDAY);
		calendar.add(DAY_OF_MONTH, -7);
		calendar.set(DAY_OF_WEEK, MONDAY);
		return calendar.getTime();
	}

	/** 当月第一天，时间为当前时间 */
	public static Date getFirstDayOfMonth() {
		Calendar calendar = getInstance();
		calendar.set(DAY_OF_MONTH, calendar.getActualMinimum(DAY_OF_MONTH));
		return calendar.getTime();
	}

	/** 本周第一天(星期一) */
	public static Date getFirstDayOfWeek() {
		Calendar calendar = getInstance();
		// 默认时，每周第一天为星期日，需要更改为星期一
		calendar.setFirstDayOfWeek(MONDAY);
		calendar.set(DAY_OF_WEEK, MONDAY);
		return calendar.getTime();
	}

	/** 取得某天所在周的第一天 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setFirstDayOfWeek(MONDAY);
		calendar.setTime(date);
		calendar.set(DAY_OF_WEEK, calendar.getFirstDayOfWeek());
		return calendar.getTime();
	}

	/**
	 * 取得某年某周的第一天
	 * 对于交叉:
	 * 2008-12-29到2009-01-04属于2008年的最后一周,
	 * 2009-01-05为2009年第一周的第一天
	 */
	public static Date getFirstDayOfWeek(int year, int week) {
		Calendar calendarFirst = getInstance();
		calendarFirst.set(year, 0, 7);
		int dateFirst = dateGet(getFirstDayOfWeek(calendarFirst.getTime()), DAY_OF_MONTH);

		Calendar calendar = new GregorianCalendar();
		calendar.set(YEAR, year);
		calendar.set(MONTH, JANUARY);
		calendar.set(DAY_OF_MONTH, dateFirst);
		calendar.add(DAY_OF_MONTH, (week - 1) * 7);
		return getFirstDayOfWeek(calendar.getTime());
	}

	/** 返回去年第一个月 */
	public static Date getFirstMonthOfLastYear() {
		Calendar calendar = getInstance(); 
		calendar.set(MONTH, 0);
		calendar.add(YEAR, -1);
		return calendar.getTime();
	}

	/** 上上月最后一天，时间为当前时间 */
	public static Date getLastDayOfLastLastMonth() {
		Calendar calendar = getInstance();  
		calendar.add(MONTH, -2);
	    calendar.set(DAY_OF_MONTH, calendar.getActualMaximum(DAY_OF_MONTH));    
		return calendar.getTime();
	}
	
	/** 上上周最后一天(星期日) */
	public static Date getLastDayOfLastLastWeek() {
		Calendar calendar = getInstance();
		// 默认时，每周第一天为星期日，需要更改为星期一
		calendar.setFirstDayOfWeek(MONDAY);
		calendar.add(DAY_OF_MONTH, -14);
		calendar.set(DAY_OF_WEEK, SUNDAY);
		return calendar.getTime();
	}
	
	/** 上月最后一天，时间为当前时间 */
	public static Date getLastDayOfLastMonth() {
		Calendar calendar = getInstance();  
		calendar.add(MONTH, -1);
	    calendar.set(DAY_OF_MONTH, calendar.getActualMaximum(DAY_OF_MONTH));    
		return calendar.getTime();
	}
	
	/** 上周最后一天(星期日) */
	public static Date getLastDayOfLastWeek() {
		Calendar calendar = getInstance();
		// 默认时，每周第一天为星期日，需要更改为星期一
		calendar.setFirstDayOfWeek(MONDAY);
		calendar.add(DAY_OF_MONTH, -7);
		calendar.set(DAY_OF_WEEK, SUNDAY);
		return calendar.getTime();
	}
	
	/** 当月最后一天，时间为当前时间 */
	public static Date getLastDayOfMonth() {
		Calendar calendar = getInstance();    
	    calendar.set(DAY_OF_MONTH, calendar.getActualMaximum(DAY_OF_MONTH));    
		return calendar.getTime();
	}
	
	/** 取得某月的的最后一天 */
	public static Date getLastDayOfMonth(int year, int month) {
		Calendar calendar = getInstance();
		calendar.set(YEAR, year);
		calendar.set(MONTH, month - 1);
		calendar.set(DAY_OF_MONTH, 1);
		calendar.add(MONTH, 1);
		calendar.add(DAY_OF_MONTH, -1);
		return calendar.getTime();
	}
	
	/** 本周最后一天(星期日) */
	public static Date getLastDayOfWeek() {
		Calendar calendar = getInstance();
		// 默认时，每周第一天为星期日，需要更改为星期一
		calendar.setFirstDayOfWeek(MONDAY);
		calendar.set(DAY_OF_WEEK, SUNDAY);
		return calendar.getTime();
	}
	
	/** 取得某天所在周的最后一天 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setFirstDayOfWeek(MONDAY);
		calendar.setTime(date);
		calendar.set(DAY_OF_WEEK, calendar.getFirstDayOfWeek() + 6);
		return calendar.getTime();
	}

	/**
	 * 取得某年某周的最后一天
	 * 对于交叉:
	 * 2008-12-29到2009-01-04属于2008年的最后一周,
	 * 2009-01-04为2008年最后一周的最后一天
	 */
	public static Date getLastDayOfWeek(int year, int week) {
		Calendar calendarLast = getInstance();
		calendarLast.set(year, 0, 7);
		int dateFirst = dateGet(getLastDayOfWeek(calendarLast.getTime()), DAY_OF_MONTH);

		Calendar calendar = new GregorianCalendar();
		calendar.set(YEAR, year);
		calendar.set(MONTH, JANUARY);
		calendar.set(DAY_OF_MONTH, dateFirst);
		calendar.add(DAY_OF_MONTH, (week - 1) * 7);
		return getLastDayOfWeek(calendar.getTime());
	}
	
	/** 返回去年最后一个月 */
	public static Date getLastMonthOfLastYear() {
		Calendar calendar = getInstance(); 
		calendar.set(MONTH, DECEMBER);
		calendar.add(YEAR, -1);
		return calendar.getTime();
	}
	
	/** 返回今年最后一个月 */
	public static Date getLastMonthOfThisYear() {
		Calendar calendar = getInstance(); 
		calendar.set(MONTH, DECEMBER);
		return calendar.getTime();
	}

	/** 取得某一年共有多少周 */
	public static int getMaxWeekNumOfYear(int year) {
		Calendar calendar = new GregorianCalendar();
		calendar.set(year, DECEMBER, 31, 23, 59, 59);
		return getWeekOfYear(calendar.getTime());
	}
	
	/** 前天 */
	public static Date getTheDayBeforeYesterday() {
		return dateAdd(DAY_OF_MONTH, -2);
	}
	
	/** 当前年份 */
	public static int getTodayYear() {
		return dateGet(YEAR);
	}

	/** 获取某一年某一周的日期 */
	public static Date[] getWeekDays(int year, int week) {
		Calendar calendar = getInstance();
		calendar.setTime(getFirstDayOfWeek(year, week));

		Date[] dates = new Date[7];
		for (int i = 0; i < 7; i++) {
			dates[i] = calendar.getTime();
			calendar.add(DAY_OF_MONTH, 1);
		}
		return dates;
	}
	
	/** 取得某天是一年中的多少周 */
	public static int getWeekOfYear(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setFirstDayOfWeek(MONDAY);
		calendar.setMinimalDaysInFirstWeek(7);
		calendar.setTime(date);
		return calendar.get(WEEK_OF_YEAR);
	}
	
	/** 昨天 */
	public static Date getYesterDay() {
		return dateAdd(DAY_OF_MONTH, -1);
	}

	public static Date nextDay(Date date) {
		return dateAdd(date, DAY_OF_MONTH, 1);
	}
	
	private TimeUtil() {
	}
}
