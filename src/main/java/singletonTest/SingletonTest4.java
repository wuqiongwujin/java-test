package singletonTest;

/**
 * @author Cain
 * @Description
 *
 * 双重检查
 * 优点：达到延时加载目的，避免了多线程并发问题，效率高，推荐
 *
 * @date 2018/10/29
 */
public class SingletonTest4 {

    private static SingletonTest4 singletonTest4;

    private SingletonTest4(){}

    public static SingletonTest4 getInstance(){
        if(singletonTest4 == null){
            synchronized (SingletonTest4.class){
                if(singletonTest4 == null){
                    singletonTest4 = new SingletonTest4();
                }
            }
        }
        return singletonTest4;
    }
}
