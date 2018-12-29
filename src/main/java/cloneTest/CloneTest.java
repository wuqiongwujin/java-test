package cloneTest;

/**
 * @author Cain
 * @Package cloneTest
 * @Description
 * @date 2018/9/29
 */
public class CloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Address address1 = new Address();
        address1.setAddress("杭州");
        Student student1 = new Student();
        student1.setName("wuqiong");
        student1.setAddress(address1);
        Student student2 = (Student) student1.clone();
        student2.getAddress().setAddress("潢川");
        System.out.println(student1.getAddress().getAddress());
    }
}
