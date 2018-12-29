package threadLocalTest;

/**
 * @author Cain
 * @Package threadLocalTest
 * @Description
 * @date 2018/9/26
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        new MyConnection("Thread-1").start();
        new MyConnection("Thread-2").start();
    }
}
