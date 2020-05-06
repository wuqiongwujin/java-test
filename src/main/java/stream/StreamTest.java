package stream;

import bean.Animal;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamTest {

    public static final int k = 4;

    public static void main(String[] args) throws Exception {
        List<Animal> animalList = new ArrayList<>();
        {
            Animal animal = new Animal();
            animal.setType("1");
            animal.setSqe("1");
            animalList.add(animal);
        }
        {
            Animal animal = new Animal();
            animal.setType("2");
            animal.setSqe("2");
            animalList.add(animal);
        }
        {
            Animal animal = new Animal();
            animal.setType("2");
            animal.setSqe("3");
            animalList.add(animal);
        }
        {
            Animal animal = new Animal();
            animal.setType("3");
            animal.setSqe("4");
            animalList.add(animal);
        }
        filterTest(animalList);
    }

    public static void mapTest() {

    }

    public static void sumTest() {
        List<User> users = new ArrayList<>();
        {
            User user = new User();
            user.setAge(1);
            users.add(user);
        }
        {
            User user = new User();
            user.setAge(2);
            users.add(user);
        }
	    System.out.println();
    }

    public static void filterTest(List<Animal> animalList) {
        Map<String, Animal> filter = animalList.stream().filter(animal -> animal.getType().equals("2")).collect(Collectors.toMap(Animal::getSqe, animal -> animal));
        System.out.println(new Gson().toJson(filter));
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

