package collection.queue.linkedBlockingQueueTest;


import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Cain
 * @Package collection.queue.linkedBlockingQueueTest
 * @Description
 * @date 2018/10/21
 */
public class LinkedBlockingQueueTest {
    public static void main(String[] args) {
        Queue queue = new LinkedBlockingQueue(1);
        try {
            ((LinkedBlockingQueue) queue).put("1");
            System.out.println("put 1");
            ((LinkedBlockingQueue) queue).take();
            ((LinkedBlockingQueue) queue).put("2");
            System.out.println("put 2");
            ((LinkedBlockingQueue) queue).take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
