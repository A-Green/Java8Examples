package streams.base;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import streams.Dish;
import streams.MenuBased;

public class CommonExamples extends MenuBased {

	public static void main(String[] args) {

		commonUsageExample();

		flatMapExample();

		twoStreamsExapmle();

		matchExamples();

		reduceExapmles();

		peekExample();
	}

	public static void commonUsageExample() {
		List<String> threeHighCaloricDishNames =
				menu.stream()
				.filter(d -> d.getCalories() > 300)
				.map(Dish::getName)
				.limit(1)
				.collect(toList());
		System.out.println(threeHighCaloricDishNames);
				
		List<Dish> vegans = menu.parallelStream()
				.filter(Dish::isVegetarian)
				.collect(toList());
		System.out.println(vegans);

		List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
		numbers.stream()
				.filter(i -> i % 2 == 0)
				.distinct()
				.skip(1)
				.forEach(i -> System.out.print(i + " "));
		
		System.out.println("");
	}

	//return only pairs whose sum is divisible by 3
	public static void twoStreamsExapmle() {
		List<Integer> quiz = Arrays.asList(1,2,3,4,5);
		List<Integer> quiz2 = Arrays.asList(1,2);

		List<int[]> pairs =
				quiz.stream()
				.flatMap(i -> quiz2.stream()
									.filter(j -> (i + j) % 3 == 0)
									.map(j -> new int[]{i, j}))
				.collect(toList());
		pairs.stream().forEach(System.out::print);
		System.out.println("");
	}

	//uniqueCharacters in menu names
	public static void flatMapExample() {
		List<String> uniqueCharacters = menu.stream()
				.map(Dish::getName)
				.map(w -> w.split(""))
				.flatMap(Arrays::stream)
				.distinct()
				.collect(toList());
		System.out.println(uniqueCharacters);
	}

	public static void matchExamples() {
		if(menu.stream().anyMatch(Dish::isVegetarian)){
			System.out.println("The menu is (somewhat) vegetarian friendly!!");
			}

		if (menu.stream().allMatch(d -> d.getCalories() < 1000)) {
			System.out.println("The menu is healthy!!");
		}

		if (menu.stream().noneMatch(d -> d.getCalories() >= 1000)) {
			System.out.println("The menu is healthy using noneMatch!!");
		}
	}

	public static void reduceExapmles() {

		int caloriesCount = menu.stream()
				.map(Dish::getCalories)
				.reduce(0, (a,b) -> a + b);

		//another smarter way
		caloriesCount = menu.stream()
				.map(Dish::getCalories)
				.reduce(0, Integer::sum);
		System.out.println(caloriesCount);

		//without initial value. returns Optional
		menu.stream()
				.map(Dish::getCalories)
				.reduce(Integer::sum)
				.ifPresent(System.out::println);
		
		Optional<Integer> min = menu.stream()
				.map(Dish::getCalories)
				.reduce((x,y) -> x < y ? x : y);
		System.out.println("min: " + min.get());
		
		Optional<Integer> max = menu.stream()
				.map(Dish::getCalories)
				.reduce(Integer::max);
		System.out.println("max: " + max.get());
	}

	// peak is useful for debug
	public static void peekExample() {
		List<Integer> result =
				menu.stream()
				.peek(x -> System.out.println("from stream: " + x))
				.map(Dish::getCalories)
				.peek(x -> System.out.println("after map: " + x))
				.filter(x -> x % 2 == 0)
				.peek(x -> System.out.println("after filter: " + x))
				.limit(3)
				.peek(x -> System.out.println("after limit: " + x))
				.collect(toList());
		System.out.println(result);
	}
}
