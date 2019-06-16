package threadPoolTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description
 * @date 2018/10/25
 */
public class SingleThreadExecutorTest {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Executors.newSingleThreadScheduledExecutor();
    }
}
