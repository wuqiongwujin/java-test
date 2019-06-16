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

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("develop")) {
            return "I am " + method.invoke(target, args);
        }
        return method.invoke(target, args);
    }
}
