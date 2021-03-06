package classTest;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Cain
 * @Description
 * @date 2018/11/4
 */
public class ClassTest {
    public static void main(String[] args) throws Exception {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(java.lang.String name) throws ClassNotFoundException {
                java.lang.String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream is = getClass().getResourceAsStream(fileName);
                if (is == null) {
                    return super.loadClass(name);
                }
                try {
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Object obj = myLoader.loadClass("java.lang.MyString").newInstance();
        System.out.println(obj.getClass());
    }
}
