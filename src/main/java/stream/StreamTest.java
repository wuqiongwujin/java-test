package stream;

import com.google.gson.Gson;
import net.sf.cglib.core.Local;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamTest {

    public static final int k = 4;

    public static void main(String[] args) throws Exception {
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setId("1");
        user.setName("wq");
        user.setAge(10);
        userList.add(user);
        User user2 = new User();
        user2.setId("2");
        user2.setName("wq");
        user2.setAge(11);
        userList.add(user2);
        List<User> newList = userList.stream().sorted((o1, o2) -> {
            if (o1.getAge() > o2.getAge()) {
                return 1;
            } else {
                return -1;
            }}).collect(Collectors.toList());
        System.out.println(new Gson().toJson(newList));
    }

    static class User {
        private String id;
        private String name;
        private int age;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}

