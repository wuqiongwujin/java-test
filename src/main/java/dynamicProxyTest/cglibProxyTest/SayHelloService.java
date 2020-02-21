package dynamicProxyTest.cglibProxyTest;

/**
 * @author Cain
 * @Package dynamicProxyTest.cglibProxyTest
 * @Description
 * @date 2018/9/1
 */
public class SayHelloService {

    static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public void sayHello(String id){
        System.out.println(threadLocal.get());
    }
}
