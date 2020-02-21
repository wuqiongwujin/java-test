package dynamicProxyTest.cglibProxyTest;

/**
 * @author Cain
 * @Package dynamicProxyTest.cglibProxyTest
 * @Description
 * @date 2018/9/1
 */
public class CglibProxyTest {
    public static void main(String[] args) {
        SayHelloService sayHelloService = new SayHelloService();
        SayHelloProxy sayHelloProxy = new SayHelloProxy();
        SayHelloService sayHelloService1 = (SayHelloService) sayHelloProxy.getInstance(sayHelloService);
        sayHelloService1.sayHello("123");
    }
}
