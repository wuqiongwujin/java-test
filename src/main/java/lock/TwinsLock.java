package lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Description
 * @Author Cain
 * @date 2020/5/7
 */
public class TwinsLock {

    public static TwinsLock twinsLock = new TwinsLock();

    public static void main(String[] args) {
        for (int i=1; i<=3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (twinsLock.lock()) {
                        System.out.println("获取锁成功");
                    }
                }
            }).start();
        }
    }

    private Sync sync = new Sync(2);

    private class Sync extends AbstractQueuedSynchronizer {
        protected Sync(int state) {
            super();
            setState(state);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            while (true) {
                int current = getState();
                int update = current - arg;
                if (compareAndSetState(current, update)) {
                    return update;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            int current = getState();
            int update = current + arg;
            return compareAndSetState(current, update);
        }
    }

    public boolean lock() {
        sync.tryAcquireShared(1);
        return true;
    }

    public boolean relase() {
        return sync.tryReleaseShared(1);
    }
}
