import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.parser.json.JsonConverter;
import com.alipay.api.request.AlipayMarketingVoucherSendRequest;
import com.alipay.api.response.AlipayMarketingVoucherSendResponse;
import com.google.gson.Gson;
import jdk.nashorn.internal.parser.JSONParser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add(null);
        System.out.println(list.get(10));
    }

}