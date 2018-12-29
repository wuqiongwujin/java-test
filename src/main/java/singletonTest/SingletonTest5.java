package singletonTest;

/**
 * @author Cain
 * @Description
 *
 * 内部静态内
 * 优点：加载类时不会实例化对象，达到了延时加载的目的，获取实例的时候实例化对象，避免了多线程并发的问题
 * 推荐使用
 *
 * @date 2018/10/29
 */
public class SingletonTest5 {
    private static SingletonTest5 singletonTest5;

    static private class SingleInstance {
        public static SingletonTest5 INSTANCE = new SingletonTest5();
    }

    public static SingletonTest5 getInstance(){
        return SingleInstance.INSTANCE;
    }
}
