package hopiTest.crmopen;

import com.hupun.hopi.HopiRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 万里牛开放平台查询会员接口调用示例
 * @date 2019/2/22
 */
public class QueryCustomDemo {
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
        parameters.put("mobile", "13298188010");
        parameters.put("page_no", 1);
        parameters.put("page_size", 2);
        try {
            String result = req.request("/crm/open/customer/querycustom", parameters);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
