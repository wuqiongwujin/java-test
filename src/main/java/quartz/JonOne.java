package quartz;

import org.quartz.*;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author Cain
 * @date 2020/3/21
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Component
public class JonOne implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap data=context.getTrigger().getJobDataMap();
		String invokeParam =(String) data.get("invokeParam");
		//在这里实现业务逻辑
	}

}
