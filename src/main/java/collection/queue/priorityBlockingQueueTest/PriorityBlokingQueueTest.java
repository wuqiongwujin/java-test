package collection.queue.priorityBlockingQueueTest;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author Cain
 * @Description
 * @date 2018/10/27
 */
public class PriorityBlokingQueueTest {
    public static void main(String[] args) {
        PriorityBlockingQueue<Student> studentPriorityBlockingQueue = new PriorityBlockingQueue<>();
        Random random = new Random();
        for(int i=1; i<=10; i++){
            String studentName = "第"+i+"名同学";
            int score = random.nextInt(100);
            Student student = new Student(studentName, score);
            studentPriorityBlockingQueue.put(student);
        }
        try {
            studentPriorityBlockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
