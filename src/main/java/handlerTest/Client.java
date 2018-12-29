package handlerTest;

import com.alibaba.fastjson.JSON;

/**
 * @author Cain
 * @Package handlerTest
 * @Description
 * @date 2018/7/16
 */
public class Client {
    public Response execute(Handler handler){
        Response response = (Response) JSON.parseObject("{\"mId\":\"123\"}", handler.getResponse());
        return response;
    }

    public static void main(String[] args) {
        Client client = new Client();
        MemberHandler memberHandler = new MemberHandler();
        MemberResponse memberResponse = (MemberResponse) client.execute(memberHandler);
        System.out.println(memberResponse.getmId());
    }
}
