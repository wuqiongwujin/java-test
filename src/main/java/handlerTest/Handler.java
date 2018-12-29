package handlerTest;

/**
 * @author Cain
 * @Package handlerTest
 * @Description
 * @date 2018/7/16
 */
public interface Handler<R extends Response> {
    public Class<R> getResponse();
}
