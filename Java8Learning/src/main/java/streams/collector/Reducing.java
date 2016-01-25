package streams.collector;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;

import java.util.Comparator;
import java.util.IntSummaryStatistics;

import streams.Dish;
import streams.MenuBased;

public class Reducing extends MenuBased {

	public static void main(String[] args) {
		Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);

		menu.stream().collect(maxBy(dishCaloriesComparator)).ifPresent(System.out::println);

		int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
		System.out.println(totalCalories);

		IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
		System.out.println(menuStatistics);

		String joinedString = menu.stream().map(Dish::getName).collect(joining(", "));
		System.out.println(joinedString);

		// reducing method example
		totalCalories = menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
		System.out.println(totalCalories);
	}

}
