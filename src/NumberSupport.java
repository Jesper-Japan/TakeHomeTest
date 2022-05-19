import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NumberSupport {

	/**
	 * Takes a number and returns it with the correct ordinal suffix.
	 * 
	 * @param Expects: Positive integer.
	 * @return The provided number with the correct ordinal suffix as a string.
	 */
	public static String ordinalNumberSuffix(int n) {
		if(n < 1) {
			throw new IllegalArgumentException("Please provide positive integer");
		}
		
		// The ordinal number suffix depends exclusively on the last 2 digits, so we only test on those.
		int digits = n % 100;
		
		if (digits == 11 || digits == 12 || digits == 13) {
			return n + "th";
		}
			
		switch(digits % 10) {
			case 1: 
				return n + "st";
			case 2:
				return n + "nd";
			case 3:
				return n + "rd";
			default:
				return n + "th";
		}
	}
	
	
	/**
	 * Counts the number of Sundays in a date range, including both the first and the last day in the period.
	 * 
	 * @param startDate, endDate: Expects, dates in the format "dd-MM-yyyy".
	 * Additionally, expects the start date to be a date prior to the end date.
	 * @return The number of Sundays in the date range, including both the first and last day in the period.
	 */
	public static int countSundays(String startDate, String endDate) {
		if(startDate == null) throw new NullPointerException("Start Date string is null");
		if(endDate == null) throw new NullPointerException("End date string is null");
		
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date startDateConv;
		Date endDateConv;
		
		try {
			startDateConv = format.parse(startDate);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Please provide start date in format dd-MM-yyyy. Provided start date: " + startDate);
		}
		try {
			endDateConv = format.parse(endDate);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Please provide end date in format dd-MM-yyyy. Provided end date: " + endDate);
		}
		
		if(startDateConv.compareTo(endDateConv) >= 0) {
			throw new IllegalArgumentException("The end date has to be after the start date. Provided start date: " + startDate + ". Provided end date: " + endDate);
		}
		
		Calendar cal = Calendar.getInstance(); 
		Calendar calEndDate = Calendar.getInstance();
		cal.setTime(startDateConv);
		calEndDate.setTime(endDateConv);
		
		// Counts the start date as well if it is a Sunday.
		int sundayCount = cal.get(Calendar.DAY_OF_WEEK) == 7 ? 1 : 0;
		
		while(cal.getTime().compareTo(endDateConv) < 0) {
			cal.add(Calendar.DAY_OF_MONTH, 1);
			if(cal.get(Calendar.DAY_OF_WEEK) == 7) {
				sundayCount++;
			}
		}
		
		return sundayCount;
	}
}
