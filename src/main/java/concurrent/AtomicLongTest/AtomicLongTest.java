package concurrent.AtomicLongTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Cain
 * @Package concurrent.AtomicLongTest
 * @Description
 * @date 2018/9/3
 */
public class AtomicLongTest {
    public static void main(String[] args) throws InterruptedException {
        final Map<String, AtomicLong> map = new ConcurrentHashMap<String, AtomicLong>();
        map.put("total", new AtomicLong(0));
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        final List<Future<Boolean>> futureList = new ArrayList<Future<Boolean>>();
        for(int i=1; i<= 1000; i++){
            futureList.add(executorService.submit(new Callable<Boolean>() {
                public Boolean call() {
                    AtomicLong total = map.get("total");
                    total.getAndAdd(1);
                    System.out.println("任务"+total.get());
                    try {
                        Thread.sleep(10*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("任务"+total.get()+"结束");
                    return true;
                }
            }));
        }
        new Thread(){
            public void run(){
                System.out.println("等待异步任务结束");
                for(Future<Boolean> future : futureList){
                    try {
                        System.out.println(future.get(20*1000, TimeUnit.SECONDS) ? "true" : "false");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        System.out.println("function return");
    }
}
