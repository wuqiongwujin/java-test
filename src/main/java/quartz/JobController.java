package quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author Cain
 * @date 2020/3/21
 */
@RestController
public class JobController {

	@Autowired
	private JobManager jobManager;
//	@Autowired
//	private AppQuartzService appQuartzService;

	//添加一个job
	@RequestMapping(value="/addJob",method=RequestMethod.POST)
	public void addjob(@RequestBody AppQuartz appQuartz) throws Exception {
		//appQuartzService.insertAppQuartzSer(appQuartz);
		System.out.println(jobManager.addJob(appQuartz));
	}
}
