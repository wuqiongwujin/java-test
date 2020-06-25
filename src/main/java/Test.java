import org.springframework.web.client.RestTemplate;

/**
 * @Description
 * @Author Cain
 * @date 2020/5/11
 */
public class Test {

    static RestTemplate template = new RestTemplate();

    public static void main(String[] args) {
        String url = "http://127.0.0.1:8091/user/getUser";
        template.getForObject(url, User.class);
    }

    static class User {

    }
}
