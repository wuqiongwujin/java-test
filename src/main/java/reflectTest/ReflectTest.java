package reflectTest;

import java.lang.reflect.Field;

/**
 * @Description
 * @author Cain
 * @date 2019/9/20
 */
public class ReflectTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Circular circular = new Circular();
        Field field = Circular.class.getDeclaredField("x");
        field.setAccessible(true);
        //field.set(circular, 2);
        System.out.println(field.get(circular));
    }
}
