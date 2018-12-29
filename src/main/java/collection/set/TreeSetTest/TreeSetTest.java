package collection.set.TreeSetTest;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Cain
 * @Description
 * @date 2018/11/5
 */
public class TreeSetTest {
    public static void main(String[] args) {
        Set<Student> set = new TreeSet();
        Student student = new Student();
        student.setName("小明");
        student.setScore(80);
        set.add(student);
        Student student1 = new Student();
        student1.setName("李华");
        student1.setScore(70);
        set.add(student1);
        Student student2 = new Student();
        student2.setName("小红");
        student2.setScore(85);
        set.add(student2);
        Iterator<Student> iterator = set.iterator();
        while (iterator.hasNext()){
            Student s = iterator.next();
            System.out.println(s.getName()+":"+s.getScore());
        }
    }
}
