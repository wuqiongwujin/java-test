package threadPoolTest;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Cain
 * @Description
 * @date 2018/10/25
 */
public class ScheduledThreadPoolTest {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
//        ((ScheduledExecutorService) threadPool).schedule(new Thread(() -> System.out.println("thread1")), 5, TimeUnit.SECONDS);
//        ((ScheduledExecutorService) threadPool).schedule(() -> System.out.println("thread2"), 3, TimeUnit.SECONDS);

        //第一次启动延时1秒执行，以后每2秒执行一次
//        executorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("start");
//            }
//        },1, 2, TimeUnit.SECONDS);

        //此方法无论任务执行时间长短，都是当第一个任务执行完成之后，延迟指定时间再开始执行第二个任务
        executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("start");
            }
        },1,2, TimeUnit.SECONDS);
    }
}
