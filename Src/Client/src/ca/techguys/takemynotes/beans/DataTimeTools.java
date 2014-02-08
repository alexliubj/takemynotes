package ca.techguys.takemynotes.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**

 * 
 * @author shupeng
 */
public class DataTimeTools {
	public Calendar calendar;
	public Date date;
	public SimpleDateFormat format;
	public final static String DATE_FORMAT_YEAR_MONTH_DAY_HOURS_MINUTES_SECONDS = "yyyy-MM-dd HH:mm:ss";
	public final static String DATE_FORMAT_YEARMONTHDAY = "yyyyMMdd";
	public final static String DATE_FORMAT_YEAR_MONTH_DAY = "yyyy-MM-dd";
	public final static String DATE_FORMAT_YEAR_MONTH_DAY_HOURS_MINUTES = "yyyy-MM-dd HH:mm";
	public final static String DATE_FORMAT_YEAR_MONTH_DAY_HOURS_MINUTES_SECONDS_AD = "yyyy-MM-ddHH:mm:ss";
	public final static String DATE_FORMAT_YEAR_MONTH_DAY_HOURS_MINUTES_SECONDS_PM = "yyyyMMddHHmmss";

	public DataTimeTools(String DateFormat) {
		format = new SimpleDateFormat(DateFormat);
	}

	/**

	 * @param time
	 * @return long
	 */
	public long getLongTimeForString(String time) {
		date = new Date();
		try {
			date = format.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date.getTime();
	}

	/**

	 * @param time
	 * @return String
	 */
	public String getStringTimeForLong(long time) {
		date = new Date(time);
		return format.format(date);
	}

	/**
	 * ��规����ユ��long杩����骞�
	 * 
	 * @param time
	 * @return
	 */
	public int getYearForLong(long time) {
		calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		return calendar.get(Calendar.YEAR);
	}

	/**

	 * @param time
	 * @return
	 */
	public int getMonthForLong(long time) {
		calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**

	 * @param time
	 * @return
	 */
	public int getDateForLong(long time) {
		calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * ��规����ユ��long杩�������
	 * 
	 * @param time
	 * @return
	 */
	public int getDayForLong(long time) {
		calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**

	 * @param time
	 * @return
	 */
	public int getHourForLong(long time) {
		calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**

	 * @param time
	 * @return
	 */
	public int getMinuteForLong(long time) {
		calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		return calendar.get(Calendar.MINUTE);
	}
}
