package mapTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cain
 * @Package mapTest
 * @Description
 * @date 2018/7/23
 */
public class EntryTest {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<String>();
        stringList.add("a");
        stringList.add("b");
        for(String str : stringList){
            stringList.remove(str);
        }
        System.out.println(stringList);
    }
}
