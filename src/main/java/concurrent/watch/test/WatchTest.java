package concurrent.watch.test;

import org.springframework.util.StopWatch;

/**
 * @Description
 * @date 2019/7/11
 */
public class WatchTest {
    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch("消费者耗时");
        stopWatch.start("任务1");
        Thread.sleep(1000);
        stopWatch.stop();
        stopWatch.start("任务2");
        Thread.sleep(2000);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}
