package collection.queue.arrayBlockingQueueTest;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Cain
 * @Package collection.queue.arrayBlockingQueueTest
 * @Description
 * @date 2018/9/17
 */
public class ArrayBlockingQueuTest {
    public static void main(String[] args) {
        final ArrayBlockingQueue<Apple> queue = new ArrayBlockingQueue(2);
        new Thread(new Producer(queue)).start();
        new Thread(new Producer(queue)).start();
        new Thread(new Comsumer(queue)).start();
        new Thread(new Comsumer(queue)).start();
        new Thread(new Comsumer(queue)).start();
        new Thread(new Comsumer(queue)).start();
    }
}
