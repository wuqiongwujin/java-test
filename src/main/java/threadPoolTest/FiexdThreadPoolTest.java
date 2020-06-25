package threadPoolTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FiexdThreadPoolTest {
    public static void main(String[] args)  {
        MyFiexdThreadPool threadPool = new MyFiexdThreadPool(2, 100);
        for (int i = 0; i<=2000000; i++) {
            int finalI = i;
            try {
                threadPool.execute(() -> {
                    Byte[] bytes = new Byte[1024 * 1000];
                    Thread.currentThread().setName("线程"+ finalI);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"运行结束");
                });
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
