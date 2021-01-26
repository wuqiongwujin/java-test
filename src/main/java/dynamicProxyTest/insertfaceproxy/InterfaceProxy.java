package dynamicProxyTest.insertfaceproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description
 * @Author Cain
 * @date 2020/12/16
 */
public class InterfaceProxy<T> implements InvocationHandler {



    public InterfaceProxy() {

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        System.out.println("after");
        return "result";
    }
}
