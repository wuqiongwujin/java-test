package socketTest;

import java.io.Serializable;

/**
 * @author Cain
 * @Description
 * @date 2018/11/8
 */
public class Student implements Serializable {

    private static final long serialVersionUID = -6128878621576168877L;

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
