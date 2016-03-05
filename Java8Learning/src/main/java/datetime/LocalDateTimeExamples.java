package datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class LocalDateTimeExamples {

	public static void main(String[] args) {
		LocalDateExamples();
		localTimeExamples();
		localDateTimeExamples();
		editLocalDateExamples();
	}

	public static void LocalDateExamples() {
		LocalDate date = LocalDate.of(2014, 3, 18);
		date = LocalDate.parse("2014-03-18");
		LocalDate today = LocalDate.now();

		int year = date.getYear();
		Month month = date.getMonth();
		int day = date.getDayOfMonth();
		DayOfWeek dow = date.getDayOfWeek();
		int len = date.lengthOfMonth();
		boolean leap = date.isLeapYear();

		// using TemporalField interface implementation
		year = date.get(ChronoField.YEAR);
		int monthNumber = date.get(ChronoField.MONTH_OF_YEAR);
		day = date.get(ChronoField.DAY_OF_MONTH);
	}

	public static void localTimeExamples() {
		LocalTime time = LocalTime.of(13, 45, 20);
		time = LocalTime.parse("13:45:20");
		int hour = time.getHour();
		int minute = time.getMinute();
		int second = time.getSecond();
	}

	public static void localDateTimeExamples() {
		// 2014-03-18T13:45:20
		LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
		LocalDate date = dt1.toLocalDate();
		LocalTime time = dt1.toLocalTime();
		LocalDateTime dt2 = LocalDateTime.of(date, time);
		LocalDateTime dt3 = date.atTime(13, 45, 20);
		LocalDateTime dt4 = date.atTime(time);
		LocalDateTime dt5 = time.atDate(date);
	}

	public static void editLocalDateExamples() {
		LocalDate date1 = LocalDate.of(2014, 3, 18);
		System.out.println(date1);
		LocalDate date2 = date1.withYear(2011);
		System.out.println(date2);
		LocalDate date3 = date2.withDayOfMonth(25);
		System.out.println(date3);
		LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 9);
		System.out.println(date4);

		date1 = LocalDate.of(2014, 3, 18);
		System.out.println(date1);
		date2 = date1.plusWeeks(1);
		System.out.println(date2);
		date3 = date2.minusYears(3);
		System.out.println(date3);
		date4 = date3.plus(6, ChronoUnit.MONTHS);
		System.out.println(date4);
	}

}
