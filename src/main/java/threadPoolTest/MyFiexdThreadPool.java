package threadPoolTest;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @Description
 * @Author Cain
 * @date 2020/5/25
 */
public class MyFiexdThreadPool extends AbstractThreadPool {

    public MyFiexdThreadPool(int size) {
        super(size);
    }

    /**
     * 抽象线程池构造函数
     *
     * @param size         核心数
     * @param blockingSize 阻塞队列大小
     */
    public MyFiexdThreadPool(int size, int blockingSize) {
        super(size, blockingSize, "测试");
    }

    @Override
    public void execute(Runnable command) {
        super.execute(command);
    }

    @Override
    public Future submit(Callable task) {
        return super.submit(task);
    }
}
