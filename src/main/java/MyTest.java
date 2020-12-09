import com.google.gson.Gson;
import com.hupun.scm.common.util.JsonConverter;
import org.springframework.util.Assert;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Description volatile修饰的变量与其他变量联合加入if判断会出现数据不安共享
 * @Author Cain
 * @date 2020/5/11
 */
public class MyTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3));
        System.out.println(list.stream().filter(i -> i>1).count());
    }

}
