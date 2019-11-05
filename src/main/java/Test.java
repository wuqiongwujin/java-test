import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {

    public static void main(String[] args) throws Exception {
        BigDecimal price = new BigDecimal("26");
        BigDecimal num = new BigDecimal("1.908");
        System.out.println(price.multiply(num).divide(num, 5));
    }
}