package collection.queue.linkedBlockingDequeTest;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author Cain
 * @Description
 * @date 2018/10/27
 */
public class LinkedBlockingDequeueTest {
    public static void main(String[] args) {
        LinkedBlockingDeque deque = new LinkedBlockingDeque();
        try {
            deque.put("");
            deque.take();
            deque.push("");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
