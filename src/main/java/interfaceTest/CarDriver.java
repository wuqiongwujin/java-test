package interfaceTest;

/**
 * @author Cain
 * @Description
 * @date 2018/12/14
 */
public class CarDriver extends Driver {
    public static void main(String[] args) {
        Class[] classes = Driver.class.getInterfaces();
        for (Class clazz : classes) {
            System.out.println(clazz.getName());
        }

        Class[] classes1 = CarDriver.class.getInterfaces();
        for (Class clazz : classes1) {
            System.out.println(clazz.getName());
        }

        Class[] classes2 = CarDriver.class.getSuperclass().getInterfaces();
        for (Class clazz : classes2) {
            System.out.println(clazz.getName());
        }
    }
}
