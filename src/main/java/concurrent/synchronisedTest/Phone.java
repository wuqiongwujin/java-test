package concurrent.synchronisedTest;

import java.util.concurrent.TimeUnit;

/**
 * @author Cain
 * @Description
 * @date 2018/10/27
 */
public class Phone {

    public synchronized void call(){
        System.out.println("打电话");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("打电话over");
    }

    public synchronized void playMuisc(){
        System.out.println("播放音乐");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("播放音乐over");
    }
}
