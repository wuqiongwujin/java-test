import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @date 2020/5/11
 */
public class TakeTea {

    private static volatile int teamNum = 15;
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread a = new Thread(() -> {
            while (teamNum > 3) {
                lock.lock();
                // A客户每次拿3杯
                teamNum -= 3;
                System.out.println("A客户每次拿3杯剩余"+teamNum);
                lock.unlock();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread b = new Thread(() -> {
            while(teamNum > 2) {
                lock.lock();
                // B客户每次拿2杯
                teamNum -= 2;
                System.out.println("B客户每次拿2杯剩余"+teamNum);
                lock.unlock();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        a.start();
        b.start();
        try {
            a.join();
            b.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(teamNum);
    }
}
