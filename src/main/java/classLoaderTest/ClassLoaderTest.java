package classLoaderTest;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Cain
 * @Description
 * @date 2019/1/1
 */
public class ClassLoaderTest {
    public static void main(java.lang.String[] args) throws Exception {
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
        Object obj = myLoader.loadClass("java.lang.String").newInstance();
        System.out.println(obj.getClass());
//        obj.setVar("myString");
    }
}

