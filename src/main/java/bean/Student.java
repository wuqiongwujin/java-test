package bean;

/**
 * @author Cain
 * @Package PACKAGE_NAME
 * @Description
 * @date 2018/10/7
 */
public class Student implements Study {

    private int age;

    public static int STATUS_CLASS = 0;

    private Dog dog = new Dog();

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void listen() {
        System.out.println("听");
    }
    public void read(){
        System.out.println("student重写read");
    }
    public void write(){
        System.out.println("student重写write");
    }

//    @Override
//    public int compareTo(Object o) {
//        if(this.getAge() > ((bean.Student)o).getAge()){
//            return -1;
//        }else if(this.getAge() < ((bean.Student)o).getAge()){
//            return 1;
//        }
//        return 0;
//    }
}
