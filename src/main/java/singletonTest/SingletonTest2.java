package singletonTest;

/**
 * @author Cain
 * @Description
 *
 * 饿汉式
 * 优点：加载类时就实例化了，避免了多线程并发的问题
 * 缺点：没有达到延时加载的目的
 *
 * @date 2018/10/29
 */
public class SingletonTest2 {
    private static SingletonTest2 singletonTest2;

    static{
        singletonTest2 = new SingletonTest2();
    }

    private SingletonTest2(){

    }

    public SingletonTest2 getInstance(){
        return singletonTest2;
    }
}
