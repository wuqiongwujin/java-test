package redis.jedis;

import com.google.gson.Gson;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.dynamic.RedisCommandFactory;

import java.util.List;


/**
 * @Description
 * @Author Cain
 * @date 2020/10/4
 */
public class JedisTest {

    static RedisClient redisClient = RedisClient.create("redis://127.0.0.1:6379");
    static StatefulRedisConnection<String, String> conn = redisClient.connect();
    static RedisCommandFactory factory = new RedisCommandFactory(conn);
    static RedisCommand commands = factory.getCommands(RedisCommand.class);

    public static void main(String[] args) {

        String key = "order";
        for (int i=1; i<= 100; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println("线程"+ finalI +"下单结果:" + throllte(key));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public static boolean throllte(String key) {
        List<Object> results = commands.throttle(key, 10, 3, 60, 1);
        System.out.println(new Gson().toJson(results));
        if (results.get(0).toString().equals("0")) {
            System.out.println("下单成功");
            return true;
        }
        System.out.println("限制下单,睡眠"+results.get(3).toString()+"秒后重试");
        long time = Long.valueOf(results.get(3).toString())*1000;
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return throllte(key);
    }
}
