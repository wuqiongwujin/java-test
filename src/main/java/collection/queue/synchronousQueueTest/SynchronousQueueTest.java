package collection.queue.synchronousQueueTest;

import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Cain
 * @Description
 * @date 2018/10/25
 */
public class SynchronousQueueTest {
    public static void main(String[] args) {
        Queue<Integer> queue = new SynchronousQueue();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        new Thread(() -> {
            try {
                for(int i=1; i<=5; i++){
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("put1:"+i);
                    ((SynchronousQueue) queue).put(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                for(int i=10; i<=15; i++){
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("put2:"+i);
                    ((SynchronousQueue) queue).put(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(((SynchronousQueue<Integer>) queue).take());
                    System.out.println(((SynchronousQueue<Integer>) queue).take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        ).start();

//        new Thread(() -> {
//            try {
//                while (countDownLatch.getCount() > 0){
//                    TimeUnit.SECONDS.sleep(1);
//                    System.out.println("take1:"+((SynchronousQueue) collection.queue).take());
//                    countDownLatch.countDown();
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//        new Thread(() -> {
//            try {
//                while (countDownLatch.getCount() > 0){
//                    TimeUnit.SECONDS.sleep(1);
//                    System.out.println("take2:"+((SynchronousQueue) collection.queue).take());
//                    countDownLatch.countDown();
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
    }
}
