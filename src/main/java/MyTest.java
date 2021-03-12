import calenderTest.DateUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.hupun.calf.util.DateUtil;
import com.hupun.nr.crm.domain.member.query.NRMemberQuery;
import com.hupun.scm.common.util.JsonConverter;
import com.hupun.scm.common.util.UUIDGenerator;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description volatile修饰的变量与其他变量联合加入if判断会出现数据不安共享
 * @Author Cain
 * @date 2020/5/11
 */
public class MyTest {

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        NRMemberQuery query = new NRMemberQuery();
        query.setCompanyID("123");
        query.setMobile("132");
        query.setSourceIDs(Arrays.asList("A","B","C"));
        JsonElement jsonElement = new Gson().toJsonTree(query);
        Set<Map.Entry<String, JsonElement>> entrySet = jsonElement.getAsJsonObject().entrySet();
        for (Map.Entry<String, JsonElement> entry : entrySet) {
            String name = entry.getKey();
            String params = new Gson().toJson(entry.getValue());
            System.out.println("name:" + name +"\t,params:"+params);
        }
    }

}
