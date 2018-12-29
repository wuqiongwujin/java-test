package concurrent.synchronisedTest;

/**
 * @author Cain
 * @Description
 * @date 2018/10/27
 */
public class SynchronisedTest {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    phone.call();
                    Thread.yield();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    phone.playMuisc();
                    Thread.yield();
                }
            }
        }).start();
    }
}
