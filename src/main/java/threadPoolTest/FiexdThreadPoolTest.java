package threadPoolTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FiexdThreadPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        List<Future<Integer>> futureList = new ArrayList<Future<Integer>>();
        for(int i=1;i<=10;i++){
            futureList.add(threadPool.submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                    return 1;
                }
            }));
        }
        int count = 0;
        for(Future<Integer> future : futureList){
            count += future.get();
        }
        if(threadPool != null && !threadPool.isShutdown()){
            threadPool.awaitTermination(3, TimeUnit.SECONDS);
        }
//        threadPool.shutdown();
        System.out.println(count);
    }
}
