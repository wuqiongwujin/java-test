package classTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Cain
 * @Description
 * @date 2018/11/8
 */
public class MethodTest {

    public void print(String msg){
        System.out.println(msg);
    }

    public static void main(String[] args) {
        try {
//            Class<?> clzz = Class.forName("java.lang.String");
            Method method = MethodTest.class.getMethod("print", String.class);
            method.invoke(MethodTest.class.newInstance(), "Hello");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
