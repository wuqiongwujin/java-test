package hopiTest.crmopen;

import com.hupun.hopi.HopiRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 万里牛开放平台更新会员接口调用示例
 * @date 2019/2/24
 */
public class UpdateCustomDemo {
    // 湖畔正式环境地址
    //public static String url="https://open-api.hupun.com/api";
    // 湖畔正式环境地址
    public static String url="http://114.67.231.162/api";
    // 湖畔分配给用户的appkey
    public static String appKey = "3423426057";
    // 湖畔分配给用户的密钥
    public static String appSecret = "fec9ad0cc5890ee8259469129ff6d050";

    public static void main(String[] args) {
        HopiRequest req = new HopiRequest(url, appKey, appSecret, "");
        getCustom(req);
    }

    private static void getCustom(HopiRequest req) {
        Map<String,Object> parameters = new HashMap<String,Object>();
        parameters.put("nick_type", -2);
        parameters.put("nick_name", "wq");
        parameters.put("nick_name", "wq");
        parameters.put("province", "浙江");
        try {
            String result = req.request("/crm/open/customer/updatecustom", parameters);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
