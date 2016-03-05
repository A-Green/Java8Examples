package datetime;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class PerionAndDurationExamples {

	public static void main(String[] args) {
		Instant instant1 = Instant.now();
		Instant instant2 = Instant.now();

		LocalDateTime dateTime1 = LocalDateTime.now();
		LocalDateTime dateTime2 = LocalDateTime.now();

		// do not mix instant and datetime. Use duration for minutes, seconds and less
		Duration d1 = Duration.between(dateTime1, dateTime2);
		Duration d2 = Duration.between(instant1, instant2);
		Duration threeMinutes = Duration.ofMinutes(3);
		threeMinutes = Duration.of(3, ChronoUnit.MINUTES);

		//use period for days, weeks, years
		Period tenDays = Period.between(LocalDate.of(2014, 3, 8), LocalDate.of(2014, 3, 18));
		tenDays = Period.ofDays(10);
		Period threeWeeks = Period.ofWeeks(3);
		Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
	}

}
