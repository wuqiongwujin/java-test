package hopiTest;

import com.hupun.hopi.HopiRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @date 2019/2/22
 */
public class GetCustomDemo {
    // 湖畔正式环境地址
    //public static String url="https://open-api.hupun.com/api";
    // 湖畔正式环境地址
    public static String url="http://114.67.231.162/api";
    // 湖畔分配给用户的appkey
    public static String appKey = "3823426046";
    // 湖畔分配给用户的密钥
    public static String appSecret = "2e9ddb0bbe1d798a30d2583ce00d6d34";

    public static void main(String[] args) {
        HopiRequest req = new HopiRequest(url, appKey, appSecret, "");
        getCustom(req);
    }

    private static void getCustom(HopiRequest req) {
        Map<String,Object> parameters = new HashMap<String,Object>();
        parameters.put("com_uid", "86C3DB2CD684347BAC23450CFC169E41");
        parameters.put("nick_type", "1");
        parameters.put("nick_name", "1");
        try {
            String result = req.request("/crm/crm/customer/getcustomdetail", parameters);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
