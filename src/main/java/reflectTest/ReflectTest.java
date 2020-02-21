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
        {
            Field field = Circular.class.getDeclaredField("x");
            field.setAccessible(true);
            field.set(circular, object());
            System.out.println(field.get(circular));
        }
        {
            Field field = Circular.class.getDeclaredField("b");
            field.setAccessible(true);
            field.set(circular, booleanT());
            System.out.println(field.get(circular));
        }
    }

    public static Object object() {
        return 2;
    }

    public static Object booleanT() {
        return true;
    }
}
