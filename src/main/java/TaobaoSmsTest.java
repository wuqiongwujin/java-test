import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.OpencrmSmsSingleSendRequest;
import com.taobao.api.response.OpencrmSmsSingleSendResponse;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Description
 * @Author Cain
 * @date 2021/9/15
 */
public class TaobaoSmsTest {

    public static void main(String[] args) {
        String url = "http://gw.api.taobao.com/router/rest";
        String sessionKey = "6200115f47e27096b82a2d4ZZe34hj673fbf7ea30469c08389377179";
        TaobaoClient client = new DefaultTaobaoClient(url, "12662943", "9a32a6d060348ea43859a012ced8380a");
        OpencrmSmsSingleSendRequest req = new OpencrmSmsSingleSendRequest();
        OpencrmSmsSingleSendRequest.ShortMessageDeliverTaskContent obj1 = new OpencrmSmsSingleSendRequest.ShortMessageDeliverTaskContent();
        obj1.setTplPlh("content");
        obj1.setDeliverTargets(new ArrayList<>(Arrays.asList("AAFsAeArAAB-m7sAAUafxn5k")));
        obj1.setSignature("jinse01官方旗舰店");
        obj1.setTplCode("SMS_3385002");
        obj1.setOutNodeInstId(100015L);
        obj1.setMsgType(1L);
        // 自定义手机号获取规则
        //obj1.setMobileAcquisitionRule(new ArrayList<>(Arrays.asList(3,1,2)));
        // 指定订单号列表，自定义手机号规则包括指定订单号方式时需要提供
        //obj1.setLogisticsOrderIdList(""233425645","233835645"");
        obj1.setTemplateId("111111111");
        //obj1.setSmsUpExtendCode("12345");
        obj1.setExtendName("XXX天猫期舰店");
        req.setTaskContent(obj1);
        OpencrmSmsSingleSendResponse rsp = null;
        try {
            rsp = client.execute(req, sessionKey);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        System.out.println(rsp.getBody());
    }
}
