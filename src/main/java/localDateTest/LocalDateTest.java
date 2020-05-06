package localDateTest;


import com.google.gson.Gson;

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
		LocalDate localDate = LocalDate.now().plusMonths(-1);
		Date date = Date.from(localDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant());
		System.out.println(new Gson().toJson(date));
	}
}
