package concurrent.atomicBooleanTest;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Cain
 * @Package PACKAGE_NAME
 * @Description
 * @date 2018/10/18
 */
public class AtomicBooleanTest implements Runnable{
    public static AtomicBoolean exits = new AtomicBoolean(true);
    public static void main(String[] args) {
        AtomicBooleanTest abd = new AtomicBooleanTest();
        Thread t1 = new Thread(abd);
        Thread t2 = new Thread(abd);
        t1.start();
        t2.start();
    }

    @Override
    public void run() {
        System.out.println("begin run");
        System.out.println("real " + exits.get());
        if(exits.compareAndSet(true,false)){
            System.out.println(Thread.currentThread().getName() + "  " + exits.get() );
            exits.set(true);
        }else{
            run();
        }
    }
}
