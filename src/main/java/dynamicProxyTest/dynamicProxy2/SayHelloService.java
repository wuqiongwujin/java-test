package dynamicProxyTest.dynamicProxy2;

/**
 * @author Cain
 * @Package dynamicProxyTest.dynamicProxy2
 * @Description
 * @date 2018/8/29
 */
public class SayHelloService implements SayHelloInterface {

    private int a = 1;

    public String sayHello(String str) {
        System.out.println(a);
        return str;
    }
}
