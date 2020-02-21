import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Description
 * @Author Cain
 * @date 2020/2/17
 */
public class Test {

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("1","1");
        map.put("2","2");
        for (Iterator<Map.Entry<String,String>> it = map.entrySet().iterator(); it.hasNext(); ){
            Map.Entry<String,String> entry = it.next();
            String key = entry.getKey();
            String value = entry.getValue();
            it.remove();
        }

    }
}
