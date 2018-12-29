package threadLocalTest;

/**
 * @author Cain
 * @Package threadLocalTest
 * @Description
 * @date 2018/9/26
 */
public class MyConnection extends Thread{

    private static ThreadLocal<String> shared1 = new ThreadLocal<String>();
    private static ThreadLocal<String> shared2 = new ThreadLocal<String>();

    public MyConnection(){

    }

    public MyConnection(String threadName){
        super(threadName);
    }

    public void run() {
        shared1.set(Thread.currentThread().getName());
        if(Thread.currentThread().getName().equals("Thread-1")){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(shared1.get());
        shared1.remove();
    }

}

