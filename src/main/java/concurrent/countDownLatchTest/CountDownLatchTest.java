package concurrent.countDownLatchTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Cain
 * @Description
 * @date 2018/10/28
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        for(int i=1;i<=3;i++){
            new Thread(new Athlete(i+"", countDownLatch)).start();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("倒计时开始");
                while (countDownLatch.getCount()>0){
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(countDownLatch.getCount());
                    countDownLatch.countDown();
                }
            }
        }).start();
    }
}
