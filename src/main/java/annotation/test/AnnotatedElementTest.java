package annotation.test;

import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.AnnotatedElement;

/**
 * @Description
 * @Author Cain
 * @date 2021/7/13
 */
@Transactional
public class AnnotatedElementTest {

    public static void main(String[] args) {
        AnnotatedElement annotatedElement = AnnotatedElementTest.class;
        Transactional transactional = annotatedElement.getAnnotation(Transactional.class);
        System.out.println(transactional.value());
    }
}
