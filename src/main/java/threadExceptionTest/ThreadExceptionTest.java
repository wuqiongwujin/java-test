package threadExceptionTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Cain
 * @Package threadExceptionTest
 * @Description
 * @date 2018/10/21
 */
public class ThreadExceptionTest {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool(new HandlerThreadFactory());
        threadPool.execute(new ExceptionThread());
    }
}
