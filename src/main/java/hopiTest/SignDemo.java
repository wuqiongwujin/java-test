package hopiTest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Description 万里牛开放平台签名方法示例
 * @date 2019/1/17
 */
public class SignDemo {

    public static String appKey = "3323445933";
    public static String appSecret = "4a684d9332fb065f05d25cdc7994a3f6";
    static final Charset charset = Charset.forName("utf-8");

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        TreeMap<String, Object> parameter = new TreeMap<String,Object>();
        setParameterMap(parameter);
        String sign = getSign(parameter);
        System.out.println("sing:"+sign);
        parameter.put("_sign", sign);
    }

    private static void setParameterMap(TreeMap<String,Object> parameter) {
        parameter.put("_app",appKey);
        long date = System.currentTimeMillis();
        System.out.println("timestamp:"+date);
        parameter.put("_t",1550538629784L);
        //2019-01-23 00:00:00
        parameter.put("start_create_time",1548259200000L);
        //2019-01-23 23:59:59
        parameter.put("end_create_time",1548320954000L);
        parameter.put("shop_nick","门店A");
        parameter.put("shop_type",-2);
        parameter.put("trade_type",50);
        parameter.put("page_no",1);
        parameter.put("page_size",3);
    }

    private static String getSign(TreeMap<String, Object> parameter) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        StringBuilder paramStr = new StringBuilder();
        paramStr.append(appSecret);
        for (Map.Entry<String,Object> entry : parameter.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue() + "";
            if (key.equals(parameter.lastKey())) {
                //必须URLEncoder.encode
                paramStr.append(URLEncoder.encode(key, charset.name()));
                paramStr.append("=");
                paramStr.append(URLEncoder.encode(value, charset.name()));
            } else {
                paramStr.append(URLEncoder.encode(key, charset.name()));
                paramStr.append("=");
                paramStr.append(URLEncoder.encode(value, charset.name()));
                paramStr.append("&");
            }
        }
        paramStr.append(appSecret);
        //md5和MD5没有区别
        byte[] bs = MessageDigest.getInstance("md5").digest(paramStr.toString().getBytes(Charset.forName("utf-8")));
        return hex(bs).toUpperCase();
    }

    /**
     * 转换十六进制字符串
     * @param bs 字节集
     * @return 字符串
     */
    private static String hex(byte[] bs) {
        StringBuilder buf = new StringBuilder(bs.length * 2);
        int bit = 0;
        for (byte b : bs) {
            bit = (b & 0x0f0) >> 4;
            buf.append(Integer.toHexString(bit));
            bit = (b & 0x0f);
            buf.append(Integer.toHexString(bit));
        }
        return buf.toString();
    }
}
