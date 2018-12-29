package singletonTest;

/**
 * @author Cain
 * @Description
 *
 * 饿汉式静态常量
 * 优点：加载类时初始化静态常量，避免了线程并发的问题
 * 缺点：没有达到延时实例化的目的，造成内存浪费
 *
 * @date 2018/10/29
 */
public class SingletonTest1 {
    private final static SingletonTest1 singletonTest1 = new SingletonTest1();

    //引用这个静态变量时会加载这个类从而初始化singletonTest1
    public static String staticStr = "STATIC";

    private SingletonTest1 (){

    }

    public SingletonTest1 getSingletonTest1(){
        return singletonTest1;
    }

}
