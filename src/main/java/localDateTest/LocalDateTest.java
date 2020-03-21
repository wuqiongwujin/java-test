package localDateTest;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * @Description
 * @Author Cain
 * @date 2020/3/21
 */
public class LocalDateTest {

	public static void main(String[] args) {
		long timestamp = (new Date()).getTime();
		LocalDate localDate = Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDate();
		System.out.println(localDate);
		System.out.println(localDate.getMonth());
		System.out.println(localDate.getDayOfMonth());
	}
}
