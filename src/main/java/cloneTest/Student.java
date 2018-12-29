package cloneTest;

/**
 * @author Cain
 * @Package cloneTest
 * @Description
 * @date 2018/9/29
 */
public class Student implements Cloneable{
    private String name;
    private Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Student student = (Student) super.clone();
        Address address = (Address) student.getAddress().clone();
        student.setAddress(address);
        return student;
    }
}
