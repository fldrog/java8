package java8.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class DateTime {

	public static void main(String[] args) {
		LocalDate date = LocalDate.of(1986, Month.of(11), 7);
		Period p = Period.between(date, LocalDate.now());
		System.out.println(p);

		LocalDate date2 = LocalDate.of(1986, Month.of(10), 3);
		Period p2 = Period.between(date2, LocalDate.now());
		System.out.println(p2);

		System.out.println(date.until(LocalDate.now()).get(ChronoUnit.YEARS));

		//adjustemnts
		LocalDate nextSunday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
		System.out.println(nextSunday);

		//localtime
		LocalTime time = LocalTime.of(10, 20);
		LocalTime updated = time.plusHours(10);
		System.out.println(time);
		System.out.println(updated);

		//Zoned Time
		ZonedDateTime zonetime =
				ZonedDateTime.of(1564, Month.APRIL.getValue(), 23, 10, 0, 0, 0, ZoneId.of("Europe/London"));
		ZonedDateTime convertNExt = zonetime.plus(Period.ofMonths(10)).withZoneSameInstant(ZoneId.of("US/Central"));
		System.out.println(zonetime);
		System.out.println(convertNExt);
		System.out.println(DateTimeFormatter.BASIC_ISO_DATE.format(convertNExt));
	}
}
