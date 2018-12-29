package concurrent.atomicStampedReferenceTest;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author Cain
 * @Description
 * @date 2018/12/6
 */
public class AtomicStampedReferenceTest {
    public static void main(String[] args) {
        Counter counter = new Counter();
        int stamp = 1;
        AtomicStampedReference<Counter> atomicStampedReference = new AtomicStampedReference<Counter>(counter, stamp);
        counter.setCount(2);
        Counter counter1 = new Counter();
        counter1.setCount(2);
        int newStamp = 2;
        System.out.println(atomicStampedReference.compareAndSet(counter,counter,stamp,newStamp));
        int expectedStamp = newStamp;
        newStamp = 3;
        System.out.println(atomicStampedReference.compareAndSet(counter,counter,expectedStamp,newStamp));
    }
}

class Counter {
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
