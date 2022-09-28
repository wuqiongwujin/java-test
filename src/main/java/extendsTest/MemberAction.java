package extendsTest;

import com.google.common.base.Splitter;
import net.sf.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

/**
 * @Description
 * @Author Cain
 * @date 2022/2/7
 */
public class MemberAction extends AbstractAction {
    @Override
    protected void deal() {
        System.out.println("MemberAction deal");
    }

    public static void main(String[] args) {

    }

    public static String getParam(String url, String name) {
        try {
            String urlString = URLDecoder.decode("https%3A%2F%2Fh5.weidian.com%2Fm%2Fmkt-member%2FmemberVerification.html%3FbuyerId%3D1360242702%26beginTime%3D1645166457551%26shopId%3D1149663141%23%2F", "utf-8");
            System.out.println(getParam(urlString, "buyerId"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String params = url.substring(url.indexOf("?") + 1, url.length());
        List<String> paramPairList = new ArrayList<>(Arrays.asList(params.split("&")));
        Map<String, String> paramMap = new HashMap<>();
        for (String paramPair : paramPairList) {
            if (paramPair.contains("=")) {
                String[] paramArray = paramPair.split("=");
                paramMap.put(paramArray[0],paramArray[1]);
            }
        }
        return paramMap.get(name);
    }
}
