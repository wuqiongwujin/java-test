package hopiTest;

import com.hupun.hopi.HopiRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 万里牛接口请求示例
 * @date 2019/1/24
 */
public class PosSoldTradeDemo {

    // 湖畔正式环境地址
    //public static String url="https://open-api.hupun.com/api";
    // 湖畔正式环境地址
    public static String url="http://114.67.231.162/api";
    // 湖畔分配给用户的appkey
    public static String appKey = "3323445933";
    // 湖畔分配给用户的密钥
    public static String appSecret = "4a684d9332fb065f05d25cdc7994a3f6";

    public static void main(String[] args) {
        HopiRequest req = new HopiRequest(url, appKey, appSecret, "");
        queryPosSoldTrade(req);
    }

    /**
     * 查询门店流水
     * @param req
     */
    public static void queryPosSoldTrade(HopiRequest req) {
        try {
            Map<String, Object> ps = new HashMap();
            ps.put("start_create_time",1548259200000L);
            ps.put("end_create_time", 1548320954000L);
            ps.put("shop_nick","门店A");
            ps.put("shop_type", -2);
            ps.put("trade_type", 50);
            ps.put("page_no", 1);
            ps.put("page_size", 3);
            String result = req.request("/nr/openpostradeturnover/querypossoldtrade", ps);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
