package singletonTest;

/**
 * @author Cain
 * @Description
 *
 * 懒汉式
 * 优点：达到了延时加载的目的，也避免了多线程并发的问题
 * 缺点：多线程获取时，存在线程阻塞问题
 *
 * @date 2018/10/29
 */
public class SingletonTest3 {

    private static SingletonTest3 singletonTest3;

    private SingletonTest3(){}

    public static synchronized SingletonTest3 getInstonce(){
        if(singletonTest3 == null){
            singletonTest3 = new SingletonTest3();
            return singletonTest3;
        }
        return singletonTest3;
    }
}
