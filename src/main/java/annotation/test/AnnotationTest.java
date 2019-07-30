package annotation.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description
 * @date 2019/7/23
 */
@MyTargetType("类注解新值")
public class AnnotationTest {

    @MyTargetFiled
    public String name = "姓名";
    @MyTargetMethod("测试方法")
    public void test(@MyTargetParameter String args) {
        System.out.println("参数值:"+args);
    }

    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, IllegalAccessException {
        // 获取类上的注解MyAnTargetType
        MyTargetType t = AnnotationTest.class.getAnnotation(MyTargetType.class);
        System.out.println("类注解值:" + t.value());

        Method method = AnnotationTest.class.getMethod("test", String.class);
        MyTargetMethod m = method.getAnnotation(MyTargetMethod.class);
        System.out.println("方法注解值:" + m.value());

        Annotation[][] annotations = method.getParameterAnnotations();
        for (Annotation annotation[] : annotations) {
            for (Annotation annotation1 : annotation) {
                System.out.println("参数注解值:"+((MyTargetParameter)annotation1).value());
            }
        }

        method.invoke(new AnnotationTest(), "新方法参数");

        MyTargetFiled f = AnnotationTest.class.getDeclaredField("name").getAnnotation(MyTargetFiled.class);
        System.out.println("属性注解值:"+f.value());



    }
}
