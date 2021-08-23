package weimob.extension;

import com.alibaba.fastjson.JSON;
import com.hupun.nr.crm.domain.weimob.extension.base.request.WeimobCommonRequest;
import com.hupun.nr.crm.domain.weimob.extension.base.response.WeimobBaseResponse;
import com.hupun.scm.common.util.JsonConverter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Type;
import java.security.MessageDigest;

/**
 * @Description
 * @Author Cain
 * @date 2021/5/6
 */
public class WimobExtensionTest {
    public static void main(String[] args) throws Exception {
        RestTemplate RESTTEMPLATE = new RestTemplate();
        String url = "https://crm-a.hupun.com/weimob/extension/points/queryMemberPoints";
        WeimobCommonRequest request = new WeimobCommonRequest();
        request.setTimestamp(String.valueOf(System.currentTimeMillis()));
        request.setParams("{\"pid\":100000262150,\"sourceObjectList\":[{\"source\":1,\"sourceAppId\":\"wx07d5708cfa5bc0ae\",\"sourceOpenId\":\"oEek95eyByGHPygcN8Qxrwu0dz1w\"},{\"source\":2,\"sourceAppId\":\"wx07d5708cfa5bc0ae\",\"sourceOpenId\":\"oWXth1jwCe1FrlFL1eKi-nYRZMI0\"},{\"source\":4,\"sourceAppId\":\"default\",\"sourceOpenId\":\"17745042160\"}],\"storeId\":2165069150,\"wid\":2459367043}");
        request.setSign(encrypt(request.getParams()));

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-type", "application/json");
        HttpEntity<String> requestEntity = new HttpEntity<String>(JsonConverter.toJSON(request), requestHeaders);
        ResponseEntity<String> response = RESTTEMPLATE.exchange(url, HttpMethod.POST, requestEntity, String.class, request);
        BaseResponse baseResponse = null;
        try {
            baseResponse = JSON.parseObject(response.getBody(), BaseResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("请求外部接口出错");
        }
        if (baseResponse.getCode() != null &&
                !"0".equals(baseResponse.getCode().getErrcode())) {
            throw new RuntimeException("请求外部接口出错");
        }
        if (baseResponse.getData() != null) {
            String dataStr = JSON.toJSONString(baseResponse.getData());
            System.out.println(dataStr);
        }
    }

    /**
     * 加密
     * @param params
     * @return
     * @throws RuntimeException
     */
    private static String encrypt(String params) throws RuntimeException {
        if (StringUtils.isEmpty(params)) {
            params = "";
        }
        String clientSecret = "F33A54F5D49772677619B15A98B3BBCD";
        params += "&"+clientSecret;
        StringBuffer sb = new StringBuffer(32);
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(params.getBytes("utf-8"));
            for (int i = 0; i < array.length; i++) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
