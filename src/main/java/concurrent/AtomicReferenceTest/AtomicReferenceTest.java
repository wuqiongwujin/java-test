package concurrent.AtomicReferenceTest;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Cain
 * @Description
 * @date 2018/12/6
 */
public class AtomicReferenceTest {
    public static void main(String[] args) {
        AtomicReference atomicReference = new AtomicReference();
        Count count = new Count();
//        count.setA(1);
//        count.setB(1);
        System.out.println(atomicReference.compareAndSet(count,count));
    }
}

class Count {
    private int a;
    private int b;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}
