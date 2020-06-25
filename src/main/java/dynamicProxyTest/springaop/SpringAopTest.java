package dynamicProxyTest.springaop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

/**
 * @Description
 * @date 2020/6/25
 */
public class SpringAopTest {

    public static void main(String[] args) {
        MyService myService = new MyService();
        ProxyFactory factory = new ProxyFactory(myService);
        // 启用CGLIB代理
        factory.setOptimize(true);
        factory.addAdvice(new MyInterceptor());
        MyService myServiceProxy = (MyService) factory.getProxy();
        myServiceProxy.query();
    }

    public static class MyInterceptor implements MethodInterceptor {

        @Override
        public Object invoke(MethodInvocation methodInvocation) throws Throwable {
            System.out.println("before");
            Object result = methodInvocation.proceed();
            System.out.println("after");
            return result;
        }
    }
}
