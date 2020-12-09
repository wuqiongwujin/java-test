package threadPoolTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FiexdThreadPoolTest {
    public static void main(String[] args)  {
        List<Future<String>> futureList = new ArrayList<>();
        MyFiexdThreadPool threadPool = new MyFiexdThreadPool(2, 2);
        for (int i = 0; i<=20; i++) {
            int finalI = i;
            futureList.add(threadPool.submit(() -> {
                System.out.println(Thread.currentThread().getName()+"运行结束");
                Thread.sleep(1000);
                return Thread.currentThread().getName();
            }));
        }
        for (Future<String> future : futureList) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
