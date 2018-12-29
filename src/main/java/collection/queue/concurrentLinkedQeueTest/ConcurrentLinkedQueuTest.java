package collection.queue.concurrentLinkedQeueTest;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Cain
 * @Description
 * @date 2018/10/27
 */
public class ConcurrentLinkedQueuTest {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue();
        Random random = new Random();
        for(int i=1; i<= 10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    queue.offer(random.nextInt(100));
                }
            }).start();
        }
        for(int i=1; i<=10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(queue.poll());
                }
            }).start();
        }
    }
}
