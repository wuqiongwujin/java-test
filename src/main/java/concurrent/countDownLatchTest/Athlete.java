package concurrent.countDownLatchTest;

import java.util.concurrent.CountDownLatch;

/**
 * @author Cain
 * @Description
 * @date 2018/10/28
 */
public class Athlete implements Runnable{
    private CountDownLatch countDownLatch;
    private String name;

    public Athlete(String name, CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
        this.name = name;
    }

    public void run() {
        System.out.println("选手"+name+"等待");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("选手"+name+"起跑");
    }
}
