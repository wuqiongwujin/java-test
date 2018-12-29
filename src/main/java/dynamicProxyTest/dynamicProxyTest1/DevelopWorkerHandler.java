package dynamicProxyTest.dynamicProxyTest1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Cain
 * @Package dynamicProxyTest
 * @Description
 * @date 2018/8/10
 */
public class DevelopWorkerHandler implements InvocationHandler {

    private Object target;

    public
    DevelopWorkerHandler(Object target){
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target, args);
    }
}
