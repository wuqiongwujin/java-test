package concurrent.semaphoreTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Cain
 * @Description
 * @date 2018/10/27
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        Playground playground = new Playground(3);
        Random random = new Random();
        for(int i=1; i<=10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        try {
                            String threadName = Thread.currentThread().getName();
                            Playground.Track track = playground.getUnusedTrack();
                            System.out.println(threadName+"获得"+track.getTrackName());
                            TimeUnit.SECONDS.sleep(random.nextInt(10));
                            playground.giveBackTrack(track);
                            System.out.println(threadName+"释放"+track.getTrackName());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }
}
