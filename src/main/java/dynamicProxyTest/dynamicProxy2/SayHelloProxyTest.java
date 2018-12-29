package dynamicProxyTest.dynamicProxy2;

/**
 * @author Cain
 * @Package dynamicProxyTest.dynamicProxy2
 * @Description
 * @date 2018/8/29
 */
public class SayHelloProxyTest {
    public static void main(String[] args) {
        SayHelloProxy sayHelloProxy = new SayHelloProxy();
        SayHelloInterface sayHelloService = (SayHelloInterface) sayHelloProxy.bind(new SayHelloService());
        System.out.println(sayHelloService.sayHello("Hello"));
    }
}
