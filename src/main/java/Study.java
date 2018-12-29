/**
 * @author Cain
 * @Package PACKAGE_NAME
 * @Description
 * @date 2018/10/7
 */
public interface Study {
    Animal animal = new Animal();
    void listen();
    default void read(){
        System.out.println("读书");
    }
    static void write(){
        System.out.println("书写");
    }
}
