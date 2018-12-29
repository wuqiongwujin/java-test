package threadExceptionTest;

/**
 * @author Cain
 * @Package threadExceptionTest
 * @Description
 * @date 2018/10/21
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught:"+e);
    }
}
