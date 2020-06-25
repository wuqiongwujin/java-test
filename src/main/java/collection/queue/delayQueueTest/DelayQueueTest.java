package collection.queue.delayQueueTest;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.DelayQueue;

/**
 * @author Cain
 * @Description
 * @date 2018/10/26
 */
public class DelayQueueTest {
    public static void main(String[] args) {
        Queue<Student> studentQueue = new DelayQueue<>();
        Random random = new Random();
        int studentNumber = 10;
        CountDownLatch countDownLatch = new CountDownLatch(10);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1; i<=studentNumber; i++){
                    String studentName = "第"+i+"名学生";
                    long workingTime = random.nextInt(30);
                    Student student = new Student(studentName, workingTime, countDownLatch);
                    ((DelayQueue<Student>) studentQueue).put(student);
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        Student student = ((DelayQueue<Student>) studentQueue).take();
                        System.out.println(student.getName()+"交卷"+",用时:"+student.getWorkingTime()+"秒");
                        countDownLatch.countDown();
                        if(countDownLatch.getCount() == 0){
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
