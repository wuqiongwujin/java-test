package collection.queue.arrayBlockingQueueTest;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Cain
 * @Package collection.queue.arrayBlockingQueueTest
 * @Description
 * @date 2018/9/17
 */
public class Producer implements Runnable{
    private ArrayBlockingQueue<Apple> queue;
    public Producer(ArrayBlockingQueue queue){
        this.queue = queue;
    }

    public void produce(){
        while (true){
            Apple apple = new Apple();
            try {
                queue.put(apple);
                System.out.println("生产了一个苹果");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        produce();
    }
}
