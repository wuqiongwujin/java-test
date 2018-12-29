package singletonTest;

/**
 * @author Cain
 * @Description
 *
 * 优点：避免多线程同步问题，防止反序列化重新创建新的对象
 * 缺点：写法复杂
 *
 * @date 2018/10/29
 */
public class SingletonTest7Factory{

    enum SingletonTest7Enum {
        INSTANCE;

        private SingletonTest7 singletonTest7;

        private SingletonTest7Enum(){
            singletonTest7 = new SingletonTest7();
        }

        public SingletonTest7 getInstance(){
            return singletonTest7;
        }

    }

    public static SingletonTest7 getInstance(){
        return SingletonTest7Enum.INSTANCE.getInstance();
    }
}

class SingletonTest7{

}
