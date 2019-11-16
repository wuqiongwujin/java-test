import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {

    public static void main(String[] args) throws Exception {
        test2();
    }

    public static void test2() throws RuntimeException{
        try {
            test();
            System.out.println("1");
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public static void test() throws RuntimeException {
        throw new RuntimeException("test");
    }
}