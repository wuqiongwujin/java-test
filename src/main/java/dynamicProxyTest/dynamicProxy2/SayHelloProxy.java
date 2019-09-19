package dynamicProxyTest.dynamicProxy2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Cain
 * @Package dynamicProxyTest.dynamicProxy2
 * @Description
 * @date 2018/8/29
 */
public class SayHelloProxy implements InvocationHandler {

    private Object target;

    public Object bind(Object target){
        this.target = target;
        //动态生成代理对象，该对象持有SayHelloProxy对象
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //使用反射调用指定的target对象的某个方法
        return method.invoke(target, args);
    }
}
