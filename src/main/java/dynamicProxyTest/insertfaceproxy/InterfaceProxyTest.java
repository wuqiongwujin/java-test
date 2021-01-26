package dynamicProxyTest.insertfaceproxy;

import java.lang.reflect.Proxy;

/**
 * @Description
 * @Author Cain
 * @date 2020/12/16
 */
public class InterfaceProxyTest {

    public static void main(String[] args) {
        ProxyInterface proxyInterface = (ProxyInterface) Proxy.newProxyInstance(ProxyInterface.class.getClassLoader(),
                new Class[]{ProxyInterface.class}, new InterfaceProxy<ProxyInterface>());
        proxyInterface.test();
    }
}
