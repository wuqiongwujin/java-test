package redis.jedis;

import io.lettuce.core.dynamic.Commands;
import io.lettuce.core.dynamic.annotation.Command;
import java.util.List;

/**
 * @Description
 * @Author Cain
 * @date 2020/10/6
 */
public interface RedisCommand extends Commands {

    @Command("CL.THROTTLE ?0 ?1 ?2 ?3 ?4")
    List<Object> throttle(String key, long initCapactiy, long operationCount, long secondCount, long getCount);
}
