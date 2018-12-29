package threadPoolTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Cain
 * @Description
 * @date 2018/10/25
 */
public class SingleThreadScheduleExecutorTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        ((ScheduledExecutorService) executorService).schedule(()-> System.out.println("thread1"), 3, TimeUnit.SECONDS);
        Executors.newWorkStealingPool();
    }
}
