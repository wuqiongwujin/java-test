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
        //返回一个代理对象，该对象持有sayHelloProxy
        SayHelloInterface sayHelloService = (SayHelloInterface) sayHelloProxy.bind(new SayHelloService());
        //执行代理对象的sayHello方法，该方法会调用sayHelloProxy的invoke方法并传入参数"Hello"和参数SayHelloService的方法sayHello()
        System.out.println(sayHelloService.sayHello("Hello"));
    }
}
