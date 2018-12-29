package dynamicProxyTest.dynamicProxy2;

/**
 * @author Cain
 * @Package dynamicProxyTest.dynamicProxy2
 * @Description
 * @date 2018/8/29
 */
public class SayHelloService implements SayHelloInterface {

    public String sayHello(String str) {
        return str;
    }
}
