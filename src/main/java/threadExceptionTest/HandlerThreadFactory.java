package threadExceptionTest;

import java.util.concurrent.ThreadFactory;

/**
 * @author Cain
 * @Package threadExceptionTest
 * @Description
 * @date 2018/10/21
 */
public class HandlerThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        return t;
    }
}
