package concurrent.readWriteLockTest;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Cain
 * @Description
 * @date 2018/10/27
 */
public class readWriteLockTest {
    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        lock.readLock();
        lock.writeLock();
    }

}
