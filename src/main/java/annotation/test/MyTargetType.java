package annotation.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义一个可以注解在Class,interface,enum上的注解
 * @Description
 * @date 2019/7/23
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyTargetType {
    String value() default "类接口注解默认值";
}
