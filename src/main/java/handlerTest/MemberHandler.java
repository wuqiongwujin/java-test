package handlerTest;

/**
 * @author Cain
 * @Package handlerTest
 * @Description
 * @date 2018/7/16
 */
public class MemberHandler implements Handler<MemberResponse> {
    public Class<MemberResponse> getResponse() {
        return MemberResponse.class;
    }
}
