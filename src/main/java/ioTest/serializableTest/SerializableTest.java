package ioTest.serializableTest;

import com.google.gson.Gson;

import java.io.*;

/**
 * @author Cain
 * @Description
 * @date 2018/10/29
 */
public class SerializableTest {
    public static void main(String[] args) {
//        Student student = new Student();
//        student.setName("无穷");
//        student.setAge(25);
//        student.setAddress("亚港");
//        student.setHeight("168");
        //serializeStudent(student);
        //student.setAddress("潢川");
        //serializeStudent(student);
        //deserializeStudent();
        Pupil pupil = new Pupil();
        pupil.setGrade("2");
        serializeStudent(pupil);
        deserializePupil();
    }

    public static void serializeStudent(Student student){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("/Users/wuqiong/workspace/test.txt"));
            ObjectOutputStream writer = new ObjectOutputStream(fileOutputStream);
            writer.writeObject(student);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void deserializeStudent(){
        try {
            FileInputStream fileInputStream = new FileInputStream(new File("/Users/wuqiong/workspace/test.txt"));
            ObjectInputStream reader = new ObjectInputStream(fileInputStream);
            Student student = (Student) reader.readObject();
            System.out.println(new Gson().toJson(student));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deserializePupil() {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File("/Users/wuqiong/workspace/test.txt"));
            ObjectInputStream reader = new ObjectInputStream(fileInputStream);
            Pupil pupil = (Pupil) reader.readObject();
            System.out.println(new Gson().toJson(pupil));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private int age;
    private static String address;
    transient private String height;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

}

class Pupil extends Student {

    private String grade;
    private String school;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}