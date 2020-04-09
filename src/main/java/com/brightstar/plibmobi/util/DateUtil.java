package com.brightstar.plibmobi.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public final class DateUtil extends DateUtils {

	private static SimpleDateFormat dateFormat;
	private static SimpleDateFormat timeFormat;
	private static SimpleDateFormat datetimeFormat;
	private static SimpleDateFormat datetimeSecFormat;
	
	/** dd/MM/yyyy */
	public static final String DATE_PATTERN                        = "dd/MM/yyyy";
	/** dd/MM/yyyy HH:mm */
	public static final String DATE_TIME_PATTERN                   = "dd/MM/yyyy HH:mm";
	/** dd/MM/yyyy HH:mm:ss */
	public static final String DATE_TIME_SEC_PATTERN               = "dd/MM/yyyy HH:mm:ss";

	/** dd/MM/yy HH:mm:ss */
	private static final String DATE_TIME_SEC_PATTERN_SHORT_YEAR    = "dd/MM/yy HH:mm:ss";
	/** HH:mm:ss */
	public static final String TIME_PATTERN                        = "HH:mm:ss";
	/** hh:mm:ss a */
	private static final String TIME_AM_PM_PATTERN                  = "hh:mm:ss a";
	
	public static final String DATE_TIME_PATTERN_WS                 = "yyyy/MM/dd HH:mm:ss";
	
	private DateUtil(){
	}

	static {
		dateFormat = new SimpleDateFormat(DATE_PATTERN);
		timeFormat = new SimpleDateFormat(TIME_PATTERN);
		datetimeFormat = new SimpleDateFormat(DATE_TIME_PATTERN);
		new SimpleDateFormat(TIME_AM_PM_PATTERN);
		new SimpleDateFormat(DATE_TIME_SEC_PATTERN_SHORT_YEAR);
		datetimeSecFormat = new SimpleDateFormat(DATE_TIME_SEC_PATTERN);
	}
	
	
	
	
	
	
	
	
	/**
	 * Formats a date into a date string.
	 * 
	 * @param date the date value to be formatted into a date string
	 * @param lenient
	 * @return the formatted date string.
	 */
	private static String formatDate(Date date, boolean lenient) {
		String result = null;
		try {
			dateFormat.setLenient(lenient);
			result = dateFormat.format(date);
		} catch (Exception e) {
			// should never happen
		}
		return result;
	}

	/**
	 * Formats a date into a date string.
	 * 
	 * @param date
	 *            the date value to be formatted into a date string
	 * @return the formatted date string.
	 */
	public static String formatDate(Date date) {
		return formatDate(date, false);
	}
	
	
	
	private static Date formatString(String date, boolean lenient) {
		Date result = null;
		try {
			dateFormat.setLenient(lenient);
			result = dateFormat.parse(date);
		} catch (Exception e) {
			// should never happen
		}
		return result;
	}

	public static Date formatString(String date) {
		return formatString(date, false);
	}
	
	public static Date formatStringToDateTimeSec(String date) throws Exception {
        Date result = null;
        try {
            datetimeSecFormat.setLenient(false);
            result = datetimeSecFormat.parse(date);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return result;
    }
	
	/**
	 * Formats the given date according to the specified pattern.
	 * 
	 * @param date
	 *            - The date to format.
	 * @param pattern
	 *            - The pattern to use for formatting the date.
	 * @return A formatted date string.
	 */
	public static String formatDate(Date date, String pattern) {
		String result = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			result = formatter.format(date);
		} catch (Exception e) {
			// should never happen
		}
		return result;
	}

	/**
	 * Formats the given date according to the specified pattern.
	 * 
	 * @param date
	 *            - The date to format.
	 * @param pattern
	 *            - The pattern to use for formatting the date.
	 * @return A formatted date string.
	 */
	public static String formatDatetime(Date date) {
		String result = null;
		try {
			result = datetimeFormat.format(date);
		} catch (Exception e) {
			// should never happen
		}
		return result;
	}
	
    public static String formatTime(Date date) {
        String result = null;
        try {
            result = timeFormat.format(date);
        } catch (Exception e) {
            // should never happen
        }
        return result;
    }

	public static int countDayBetween(Date begin, Date end) {
		int daysBetween = 0; 

		if (begin != null && end != null) {
			Calendar calini = Calendar.getInstance();
			Calendar calfim = Calendar.getInstance();
			calini.setTime(begin);
			calfim.setTime(end);

			Calendar date = (Calendar) calini.clone();  

			while (date.before(calfim)) {  
				date.add(Calendar.DAY_OF_MONTH, 1);  
				daysBetween++;  
			}
		}
		return daysBetween;
	}
	
	public static Calendar getMinTruncDate(Date date) {
        Calendar minDate = null;
        if (date != null) {
            minDate = Calendar.getInstance();
            minDate.setTime(date);
            minDate.set(Calendar.HOUR, 0);
            minDate.set(Calendar.MINUTE, 0);
            minDate.set(Calendar.SECOND, 0);
        }
        return minDate;
    }
	
	public static Calendar getMaxTruncDate(Date date) {
        Calendar maxDate = null;
        
        if (date != null) {
            maxDate = Calendar.getInstance();
            maxDate.setTime(date);
            maxDate.set(Calendar.HOUR, 23);
            maxDate.set(Calendar.MINUTE, 59);
            maxDate.set(Calendar.SECOND, 59);
        }
        return maxDate;
    }
}