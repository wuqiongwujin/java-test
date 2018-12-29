package collection.set.TreeSetTest;

import java.util.Comparator;

/**
 * @author Cain
 * @Description
 * @date 2018/11/5
 */
public class Student implements Comparable {
    private String name;
    /**分数*/
    private int score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(Object o) {
        if(this.getScore() > ((Student)o).getScore()){
            return 1;
        }else if(this.getScore() < ((Student)o).getScore()){
            return -1;
        }
        return 0;
    }
}
