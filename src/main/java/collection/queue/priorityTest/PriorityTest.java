package collection.queue.priorityTest;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author Cain
 * @Description
 * @date 2018/10/27
 */
public class PriorityTest {
    public static void main(String[] args) {
        PriorityQueue<Student> studentPriorityQueue = new PriorityQueue<Student>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if(((Student)o1).getScore() > ((Student)o2).getScore()){
                    return -1;
                }else if(((Student)o1).getScore() < ((Student)o2).getScore()){
                    return 1;
                }
                return 0;
            }
        });
        Random random = new Random();
        for(int i=1; i<=10; i++){
            String studentName = "第"+i+"名同学";
            int score = random.nextInt(100);
            Student student = new Student(studentName, score);
            studentPriorityQueue.add(student);
        }
        Iterator<Student> iterator = studentPriorityQueue.iterator();
        while (iterator.hasNext()){
            Student student = iterator.next();
            System.out.println(student.getName()+","+student.getScore());
        }
        System.out.println("======");
        while (studentPriorityQueue.peek() != null){
            Student student = studentPriorityQueue.poll();
            System.out.println(student.getName()+","+student.getScore());
        }
    }
}
