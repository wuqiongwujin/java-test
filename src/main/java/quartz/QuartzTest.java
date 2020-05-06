package quartz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Description
 * @Author Cain
 * @date 2020/3/21
 */
@EnableWebMvc
@SpringBootApplication
public class QuartzTest {

	private static Log logger = LogFactory.getLog(QuartzTest.class);
	public static void main(String[] args) {
		SpringApplication.run(QuartzTest.class, args);
		logger.info("spring boot start=======");
	}
}
