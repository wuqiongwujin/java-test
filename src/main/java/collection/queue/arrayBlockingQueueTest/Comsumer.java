package collection.queue.arrayBlockingQueueTest;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Cain
 * @Package collection.queue.arrayBlockingQueueTest
 * @Description
 * @date 2018/9/17
 */
public class Comsumer implements Runnable{
    private ArrayBlockingQueue<Apple> queue;
    public Comsumer(ArrayBlockingQueue queue){
        this.queue = queue;
    }
    public void cusum(){
        while (true){
            try {
                Thread.sleep(3000);
                queue.take();
                System.out.println("消费了一个苹果");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void run() {
        cusum();
    }
}
