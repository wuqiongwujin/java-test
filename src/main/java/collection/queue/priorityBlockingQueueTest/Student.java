package collection.queue.priorityBlockingQueueTest;

/**
 * @author Cain
 * @Description
 * @date 2018/10/27
 */
public class Student implements Comparable {
    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(Object o) {
        if(this.score > ((Student)o).score){
            return 1;
        }else if(this.score < ((Student)o).score){
            return -1;
        }
        return 0;
    }
}
