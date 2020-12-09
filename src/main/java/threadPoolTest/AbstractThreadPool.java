package threadPoolTest;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description
 * @date 2020/5/25
 */
public abstract class AbstractThreadPool {

    private final ThreadPoolExecutor service;

    public AbstractThreadPool(int size) {
        this.service = (ThreadPoolExecutor) Executors.newFixedThreadPool(size);
    }

    /**
     * 抽象线程池构造函数
     * @param size 核心数
     * @param blockingSize 阻塞队列大小
     */
    protected AbstractThreadPool(int size, int blockingSize, String threadName) {
        service = new ThreadPoolExecutor(size, size,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(blockingSize),
                new MyThreadFactory(threadName),
                new RetryReject());
    }

    /**
     * 执行异步任务
     * @param command 任务
     */
    protected void execute(Runnable command) {
        service.execute(command);
    }

    protected Future submit(Callable task) {
        return service.submit(task);
    }

    public class MyReject implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            throw new RejectedExecutionException("添加任务被拒绝");
        }
    }

    public class RetryReject implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            if (!executor.isShutdown()) {
                System.out.println("线程池已满,等待3秒后重试");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                }
                executor.submit(r);
            }
        }
    }

    public static class MyThreadFactory implements ThreadFactory {

        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;
        private final String threadName;

        public MyThreadFactory(String threadName) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
            this.threadName = threadName;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    this.threadName + "-" + namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

}
