package threadPoolTest;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
    protected AbstractThreadPool(int size, int blockingSize) {
        service = new ThreadPoolExecutor(size, size,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(blockingSize));
    }

    /**
     * 执行异步任务
     * @param command 任务
     */
    protected void execute(Runnable command) {
        service.execute(command);
    }
}
