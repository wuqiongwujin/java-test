package enumTest;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author Cain
 * @date 2021/1/13
 */
public class EnumTest {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("1","2");
        System.out.println(String.join(",", list));
    }
}
