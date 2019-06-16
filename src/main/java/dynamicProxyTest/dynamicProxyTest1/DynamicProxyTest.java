package dynamicProxyTest.dynamicProxyTest1;


import java.lang.reflect.Proxy;

/**
 * @author Cain
 * @Package dynamicProxyTest
 * @Description
 * @date 2018/8/10
 */
public class DynamicProxyTest {

    public static void main(String[] args) {
        JavaDevelop javaDevelop = new JavaDevelop();
        DevelopWorker developWorker = (DevelopWorker) Proxy.newProxyInstance(DevelopWorker.class.getClassLoader(),
                new Class[] {DevelopWorker.class}, new DevelopWorkerHandler(javaDevelop));

        System.out.println(developWorker.develop());
        System.out.println(developWorker.play());
    }
}
