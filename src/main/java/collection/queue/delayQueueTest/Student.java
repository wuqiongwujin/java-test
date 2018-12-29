package collection.queue.delayQueueTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author Cain
 * @Description
 * @date 2018/10/26
 */
public class Student implements Delayed {

    private String name;
    private long workingTime;
    private long submitTime;
    private CountDownLatch countDownLatch;

    public Student(String name, long workingTime, CountDownLatch countDownLatch) {
        this.name = name;
        this.workingTime = workingTime;
        this.submitTime = TimeUnit.NANOSECONDS.convert(workingTime, TimeUnit.SECONDS) + System.nanoTime();
        this.countDownLatch = countDownLatch;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        //获取剩余时间
        return unit.convert(this.submitTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if(this.workingTime > ((Student)o).workingTime){
            return 1;
        }else if(this.workingTime < ((Student)o).workingTime){
            return -1;
        }
        return 0;
    }

    public String getName() {
        return name;
    }

    public long getWorkingTime() {
        return workingTime;
    }

    public long getSubmitTime() {
        return submitTime;
    }
}
