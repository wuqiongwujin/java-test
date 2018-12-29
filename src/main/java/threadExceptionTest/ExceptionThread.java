package threadExceptionTest;

/**
 * @author Cain
 * @Package threadExceptionTest
 * @Description
 * @date 2018/10/21
 */
public class ExceptionThread implements Runnable {

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
