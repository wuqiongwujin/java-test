package streamtest;

import bean.Animal;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author Cain
 * @date 2020/3/19
 */
public class StreamTest {

	public static void main(String[] args) {
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
		//Map<String,bean.Animal> animalMap = animalList.stream().distinct().collect(Collectors.toMap(bean.Animal::getType,Function.identity()));
		Map<String,List<Animal>> animalMap = animalList.stream().collect(Collectors.toMap(Animal::getType, animal -> new ArrayList<>(Arrays.asList(animal)), (animalsOld, animalsNew) -> {
			animalsOld.addAll(animalsNew);
			return animalsOld;
		}));
		System.out.println(new Gson().toJson(animalMap));
	}
}
