package threadExceptionTest;

import java.util.concurrent.*;

/**
 * @Description
 * @date 2019/5/6
 */
public class ThreadRejectTest {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2,0L,
                TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(3), new ThreadFactory() {

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                if (t.isDaemon())
                    t.setDaemon(false);
                if (t.getPriority() != Thread.NORM_PRIORITY)
                    t.setPriority(Thread.NORM_PRIORITY);
                return t;
            }
        },
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        throw new RejectedExecutionException("线程池已满，任务被拒绝,"+r.toString());
                    }
                });
        for (int i=0; i<=100; i++) {
            int finalI = i + 1;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public String toString() {
                    return "第"+finalI + "个测试线程";
                }
            });
        }
    }
}
