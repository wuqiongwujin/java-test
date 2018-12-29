package concurrent.volatileTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Cain
 * @Description
 * @date 2018/11/19
 */
public class VolatileTest {
    private static volatile int num = 0;
    private static int THREAD_NUM = 1000;
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_NUM);
        List<Future<Boolean>> futureList = new ArrayList<Future<Boolean>>();
        for (int i = 1; i<=THREAD_NUM; i++) {
            futureList.add(executor.submit(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    Thread.sleep(1000);
                    num = num + 1;
                    return true;
                }
            }));
        }
        for (Future<Boolean> future : futureList) {
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        System.out.println(num);
    }
}
