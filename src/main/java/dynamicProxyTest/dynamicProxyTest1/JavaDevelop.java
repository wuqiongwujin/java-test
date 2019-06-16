package dynamicProxyTest.dynamicProxyTest1;

/**
 * @author Cain
 * @Package dynamicProxyTest
 * @Description
 * @date 2018/8/10
 */
public class JavaDevelop implements DevelopWorker {

    @Override
    public String develop() {
        return "java develop";
    }

    @Override
    public String play() {
        return "play";
    }
}
