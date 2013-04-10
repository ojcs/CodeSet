package sep.util.time;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public final class TimeUtil {
	/** 判断当前日期是否在两个日期之间 */
	public static boolean betweenStartDateAndEndDate(final Date begin, final Date end) {
		Date current = new Date();
		Date endDate = dateAdd(end, Calendar.DATE, 1);
		return current.after(begin) && current.before(endDate);
	}

	/** 根据生日去用户年龄 */
	public static int birthdayToAge(Date birthday) {
		if (birthday == null) {
			return -1;
		} else if (new Date().before(birthday)) {
			return 0;
		}
		final int yearNow = dateGet(Calendar.YEAR);
		final int yearBirth = dateGet(birthday, Calendar.YEAR);
		return yearNow - yearBirth;
	}

	/** 比较两个日期{after(1),before(-1),other(0)} */
	public static int compare(Date date1, Date date2) {
		return date1.after(date2) ? 1 : date1.before(date2) ? -1 : 0;
	}

	protected static Date dateAdd(Date date, int field, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}

	protected static Date dateAdd(int field, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(field, amount);
		return calendar.getTime();
	}

	protected static int dateGet(Date date, int field) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(field);
	}

	protected static int dateGet(int field) {
		return Calendar.getInstance().get(field);
	}

	protected static Date dateSet(Date date, int field, int value) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(field, value);
		return calendar.getTime();
	}

	/** 取得某天所在周的第一天 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
		return calendar.getTime();
	}

	/**
	 * 取得某年某周的第一天
	 * 对于交叉:
	 * 2008-12-29到2009-01-04属于2008年的最后一周,
	 * 2009-01-05为2009年第一周的第一天
	 */
	public static Date getFirstDayOfWeek(int year, int week) {
		Calendar calendarFirst = Calendar.getInstance();
		calendarFirst.set(year, 0, 7);
		int dateFirst = dateGet(getFirstDayOfWeek(calendarFirst.getTime()),
				Calendar.DATE);

		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DATE, dateFirst);
		calendar.add(Calendar.DATE, (week - 1) * 7);
		return getFirstDayOfWeek(calendar.getTime());
	}

	/** 取得某月的的最后一天 */
	public static Date getLastDayOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DATE, 1);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}

	/** 取得某天所在周的最后一天 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() + 6);
		return calendar.getTime();
	}

	/**
	 * 取得某年某周的最后一天
	 * 对于交叉:
	 * 2008-12-29到2009-01-04属于2008年的最后一周,
	 * 2009-01-04为2008年最后一周的最后一天
	 */
	public static Date getLastDayOfWeek(int year, int week) {
		Calendar calendarLast = Calendar.getInstance();
		calendarLast.set(year, 0, 7);
		int dateFirst = dateGet(getLastDayOfWeek(calendarLast.getTime()),
				Calendar.DATE);

		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DATE, dateFirst);
		calendar.add(Calendar.DATE, (week - 1) * 7);
		return getLastDayOfWeek(calendar.getTime());
	}

	/** 取得某一年共有多少周 */
	public static int getMaxWeekNumOfYear(int year) {
		Calendar calendar = new GregorianCalendar();
		calendar.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
		return getWeekOfYear(calendar.getTime());
	}

	/** 当前年份 */
	public static int getTodayYear() {
		return dateGet(Calendar.YEAR);
	}

	/** 获取某一年某一周的日期 */
	public static Date[] getWeekDays(int year, int week) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getFirstDayOfWeek(year, week));

		Date[] dates = new Date[7];
		for (int i = 0; i < 7; i++) {
			calendar.add(Calendar.DATE, 1);
			dates[i] = calendar.getTime();
		}
		return dates;
	}

	/** 取得某天是一年中的多少周 */
	public static int getWeekOfYear(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setMinimalDaysInFirstWeek(7);
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	public static Date nextDay(Date date) {
		return dateAdd(date, Calendar.DAY_OF_MONTH, 1);
	}
	
	private TimeUtil() {
	}
}
